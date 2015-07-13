/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.EnclosureAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class EnclosureClientImpl implements EnclosureClient
{
    private static final Logger logger = LoggerFactory
            .getLogger(EnclosureClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private EnclosureAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Override
    public Enclosures getEnclosure(final RestParams params, final String resourceId)
    {

        logger.info("EnclosureClientImpl : getEnclosure : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureV2Client : getEnclosure : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final Enclosures enclosureDto = adaptor.buildDto(returnObj);

        logger.debug("EnclosureV2Client : getEnclosure : name :"
                + enclosureDto.getName());
        logger.info("EnclosureClientImpl : getEnclosure : End");

        return enclosureDto;
    }

    @Override
    public EnclosureCollectionV2 getAllEnclosures(final RestParams params)
    {
        logger.info("EnclosureClientImpl : getAllEnclosureV2s : Start");
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_URI));

        // call rest client
        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureClientImpl : getAllEnclosureV2s : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final EnclosureCollectionV2 enclosureCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("EnclosureV2Client : getAllEnclosureV2s : members count :"
                + enclosureCollectionDto.getCount());
        logger.info("EnclosureClientImpl : getAllEnclosureV2s : End");

        return enclosureCollectionDto;

    }

    @Override
    public Enclosures getEnclosureByName(final RestParams params, final String name)
    {
        Enclosures enclosureDto = null;
        logger.info("EnclosureClientImpl : getEnclosureByName : Start");
        final String query = "filter=\"name=\'" + name + "\'\"";

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureClientImpl : getEnclosureByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final EnclosureCollectionV2 enclosureCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (enclosureCollectionDto.getCount() != 0)
        {
            enclosureDto = enclosureCollectionDto.getMembers().get(0);
        }
        else
        {
            enclosureDto = null;
        }

        if (enclosureDto == null) 
        {
            logger.error("EnclosureClientImpl : getEnclosureByName : resource not Found for name :"
                        + name);
            throw new SDKResourceNotFoundException(
                    SDKErrorEnum.resourceNotFound, null, null, null,
                SdkConstants.ENCLOSURE, null);
        }
        logger.info("EnclosureClientImpl : getEnclosureByName : End");

        return enclosureDto;
    }

    @Override
    public TaskResourceV2 createEnclosure(final RestParams params,
            final AddEnclosureV2 enclosureDto, final boolean aSync, final boolean useJsonRequest)
    {
        logger.info("EnclosureClientImpl : createEnclosure : Start");
        String returnObj = null;

        // validate params
        if (enclosureDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(enclosureDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("EnclosureClientImpl : createEnclosure : returnObj ="
                + returnObj);
        logger.debug("EnclosureClientImpl : createEnclosure : taskResource ="
                + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("EnclosureClientImpl : createEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateEnclosure(final RestParams params, final String resourceId,
            final Enclosures enclosureDto, final boolean aSync, final boolean useJsonRequest)
    {
        logger.info("EnclosureClientImpl : updateEnclosure : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (enclosureDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.NETWORK, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(enclosureDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("EnclosureClientImpl : updateEnclosure : returnObj ="
                + returnObj);
        logger.debug("EnclosureClientImpl : updateEnclosure : taskResource ="
                + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("EnclosureClientImpl : updateEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteEnclosure(final RestParams params, final String resourceId,
            final boolean aSync)
    {

        logger.info("EnclosureClientImpl : deleteEnclosure : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureV2Client : deleteEnclosure : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.ENCLOSURE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("EnclosureClientImpl : deleteEnclosure : returnObj ="
                + returnObj);
        logger.debug("EnclosureClientImpl : deleteEnclosure : taskResource ="
                + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("EnclosureClientImpl : deleteEnclosure : End");

        return taskResourceV2;
    }

    //TODO
	@Override
	public SsoUrlData getActiveOaSsoUrl(RestParams param, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 updateCompliance(RestParams param, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 updateConfiguration(RestParams param,
			String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 updateEnclosureFwBaseline(RestParams param,
			String resourceId, FwBaselineConfig fwBaselineConfigDto) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public EnvironmentalConfiguration getEnvironmentalConfiguration(
			RestParams param, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public EnvironmentalConfiguration updateEnvironmentalConfiguration(
			RestParams param, String resourceId,
			EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 updateRefreshState(RestParams param,
			String resourceId, RefreshStateConfig refreshStateConfigDto) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public String getScript(RestParams param, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public TaskResourceV2 updateScript(RestParams param, String resourceId,
			String scriptData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TODO
	@Override
	public SsoUrlData getStandbyOaSsoUrl(RestParams param, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO
	@Override
	public UtilizationData getUtilization(RestParams param, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
