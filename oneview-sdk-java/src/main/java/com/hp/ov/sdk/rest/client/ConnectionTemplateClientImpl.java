/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.ConnectionTemplateAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ConnectionTemplateCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class ConnectionTemplateClientImpl implements ConnectionTemplateClient
{

    private static final Logger logger = LoggerFactory
            .getLogger(ConnectionTemplateClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ConnectionTemplateAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Override
    public ConnectionTemplate getConnectionTemplate(final RestParams params,
            final String resourceId)
    {
        ConnectionTemplate connectionTemplateDto = null;
        logger.info("ConnectionTemplateImpl : getConnectionTemplate : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ConnectionTemplateImpl : getConnectionTemplate : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }
        // Call adaptor to convert to DTO

        connectionTemplateDto = adaptor.buildDto(returnObj);

        logger.debug("ConnectionTemplateImpl : getConnectionTemplate : name :"
                + connectionTemplateDto.getName());
        logger.info("ConnectionTemplateImpl : getConnectionTemplate : End");

        return connectionTemplateDto;
    }

    @Override
    public ConnectionTemplate getConnectionTemplateByName(final RestParams params,
            final String resourceName)
    {
        ConnectionTemplate connectionTemplateDto = null;
        ConnectionTemplateCollection connectionTemplateCollectionDto = null;
        logger.info("ConnectionTemplateClientImpl : getConnectionTemplateByName : Start");
        final String query = "filter=\"name matches \'" + resourceName + "\'\"";

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ConnectionTemplateClientImpl : getConnectionTemplateByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }
        // Call adaptor to convert to DTO

        connectionTemplateCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (connectionTemplateCollectionDto.getCount() != 0)
        {
            connectionTemplateDto = connectionTemplateCollectionDto
                    .getMembers().get(0);
        }
        else
        {
            connectionTemplateDto = null;
        }

        if (connectionTemplateDto == null)
        {
            logger.error("ConnectionTemplateClientImpl : getConnectionTemplateByName : resource not Found for name :"
                    + resourceName);
            throw new SDKResourceNotFoundException(
                    SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }
        logger.info("ConnectionTemplateClientImpl : getConnectionTemplateByName : End");

        return connectionTemplateDto;
    }

    @Override
    public ConnectionTemplate updateConnectionTemplate(final RestParams params,
            final String resourceId, ConnectionTemplate connectionTemplateDto,
            final boolean useJsonRequest)
    {
        logger.info("ConnectionTemplateClientImpl : updateConnectionTemplate : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (connectionTemplateDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.CONNECTION_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));
        String returnObj = null;

        // TODO-check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating connectionTemplate dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(connectionTemplateDto);

        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        connectionTemplateDto = adaptor.buildDto(returnObj);

        logger.debug("ConnectionTemplateClientImpl : updateConnectionTemplate : returnObj ="
                + returnObj);

        logger.info("ConnectionTemplateClientImpl : updateConnectionTemplate : End");

        return connectionTemplateDto;
    }

    //TODO
    @Override
    public ConnectionTemplateCollection getAllConnectionTemplates(
            final RestParams params)
    {
        // TODO Auto-generated method stub
        return null;
    }

    //TODO
    @Override
    public ConnectionTemplate getDefaultConnectionTemplateForConnectionTemplate(
            final RestParams params)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
