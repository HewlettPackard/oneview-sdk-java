/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.EnclosureGroupAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.EnclosureGroupCollectionV2;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class EnclosureGroupClientImpl implements EnclosureGroupClient
{

    public static final Logger logger = LoggerFactory
            .getLogger(EnclosureGroupClientImpl.class);

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private EnclosureGroupAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Override
    public EnclosureGroups getEnclosureGroup(final RestParams params,
            final String resourceId)
    {
        logger.info("EnclosureGroupClientImpl : getEnclosureGroup : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureGroupClientImpl : getEnclosureGroup : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.ENCLOSURE_GROUP, null);
        }
        // Call adaptor to convert to DTO
        final EnclosureGroups enclosureGroupDto = adaptor.buildDto(returnObj);

        logger.debug("EnclosureGroupClientImpl : getEnclosureGroup : Name :"
                + enclosureGroupDto.getName());
        logger.info("EnclosureGroupClientImpl : getEnclosureGroup : End");

        return enclosureGroupDto;
    }

    @Override
    public EnclosureGroupCollectionV2 getAllEnclosureGroups(final RestParams params)
    {
        logger.info("EnclosureGroupClientImpl : getAllEnclosureGroups : Start");
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI));

        // call rest client
        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureGroupClientImpl : getAllEnclosureGroups : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.ENCLOSURE_GROUPS, null);
        }
        // Call adaptor to convert to DTO

        final EnclosureGroupCollectionV2 enclosureGroupCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("EnclosureGroupClientImpl : getAllEnclosureGroups : members count :"
                + enclosureGroupCollectionDto.getCount());
        logger.info("EnclosureGroupClientImpl : getAllEnclosureGroups : End");

        return enclosureGroupCollectionDto;
    }

    @Override
    public EnclosureGroups getEnclosureGroupByName(final RestParams params,
            final String name)
    {
        EnclosureGroups enclosureGroupDto = null;
        logger.info("EnclosureGroupClientImpl : getEnclosureGroupByName : Start");
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
                ResourceUris.ENCLOSURE_GROUP_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureGroupClientImpl : getEnclosureGroupByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.ENCLOSURE_GROUPS, null);
        }
        // Call adaptor to convert to DTO

        final EnclosureGroupCollectionV2 enclosureGroupCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (enclosureGroupCollectionDto.getCount() != 0)
        {
            enclosureGroupDto = enclosureGroupCollectionDto.getMembers().get(0);
        }
        else
        {
            enclosureGroupDto = null;
        }

        if (enclosureGroupDto == null)
        {
            logger.error("EnclosureGroupClientImpl : getEnclosureGroupByName : resource not Found for name :"
                    + name);
            throw new SDKResourceNotFoundException(
                    SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.ENCLOSURE_GROUP, null);
        }
        logger.info("EnclosureGroupClientImpl : getEnclosureGroupByName : End");

        return enclosureGroupDto;
    }

    @Override
    public EnclosureGroups createEnclosureGroup(final RestParams params,
            final EnclosureGroups dto, final boolean useJsonRequest)
    {
        logger.info("EnclosureGroupClientImpl : createEnclosureGroupV2 : Start");
        String returnObj = null;

        // validate params
        if (dto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.ENCLOSURE_GROUP, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(dto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to enclosureresourceV2
        final EnclosureGroups enclosureGroupV2 = (EnclosureGroups) taskAdaptor
                .buildClassDto(returnObj, EnclosureGroups.class);

        logger.debug("EnclosureGroupClientImpl : createEnclosureGroupV2 : returnObj ="
                + returnObj);
        logger.debug("EnclosureGroupClientImpl : createEnclosureGroupV2 : enclosureObject ="
                + enclosureGroupV2);

        logger.info("EnclosureGroupClientImpl : createEnclosureGroupV2 : End");

        return enclosureGroupV2;
    }

    @Override
    public EnclosureGroups updateEnclosureGroup(final RestParams params,
            final String resourceId, final EnclosureGroups enclosureGroupDto,
            final boolean useJsonRequest)
    {
        logger.info("EnclosureGroupClientImpl : updateEnclosureGroupV2 : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (enclosureGroupDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.ENCLOSURE_GROUP, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(enclosureGroupDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        final EnclosureGroups enclosureGroupV2 = (EnclosureGroups) taskAdaptor
                .buildClassDto(returnObj, EnclosureGroups.class);

        logger.debug("EnclosureGroupClientImpl : updateEnclosureGroupV2 : returnObj ="
                + returnObj);
        logger.debug("EnclosureGroupClientImpl : updateEnclosureGroupV2 : enclosureObject ="
                + enclosureGroupV2);

        logger.info("EnclosureGroupClientImpl : updateEnclosureGroupV2 : End");

        return enclosureGroupV2;

    }

    @Override
    public String deleteEnclosureGroup(final RestParams params, final String resourceId)
    {
        logger.info("EnclosureGroupClientImpl : deleteEnclosureGroupV2 : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("EnclosureGroupClientImpl : deleteEnclosureGroupV2 : response from OV :"
                + returnObj);
        /************ Returns Response code 204 *********************************/

        logger.debug("EnclosureGroupClientImpl : deleteEnclosureGroupV2 : returnObj ="
                + returnObj);

        logger.info("EnclosureGroupClientImpl : deleteEnclosureGroupV2 : End");

        return "Deleted";
    }

    //TODO
    @Override
    public String getConfigurationScript(final RestParams params, final String resourceId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    //TODO
    @Override
    public String updateConfigurationScript(final RestParams params,
            final String resourceId, final String scriptData)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
