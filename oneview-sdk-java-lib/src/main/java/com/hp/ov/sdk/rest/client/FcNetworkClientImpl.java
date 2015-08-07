/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.FcNetworkAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.FcNetworkCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class FcNetworkClientImpl implements FcNetworkClient {

    private static final Logger logger = LoggerFactory.getLogger(FcNetworkClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private FcNetworkAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private UrlUtils urlUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Override
    public FcNetwork getFcNetwork(final RestParams params, final String resourceId) {
        logger.info("FcNetworkClientImpl : getFcNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FcNetworkClientImpl : getFcNetwork : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORK, null);
        }
        // Call adaptor to convert to DTO

        final FcNetwork fcNetworkDto = adaptor.buildDto(returnObj);

        logger.debug("FcNetworkClientImpl : getFcNetwork : name :" + fcNetworkDto.getName());
        logger.info("FcNetworkClientImpl : getFcNetwork : End");

        return fcNetworkDto;
    }

    @Override
    public FcNetworkCollection getFcNetworkByFilter(final RestParams params, final Integer start, final Integer count) {
        logger.info("FcNetworkClientImpl : getFcNetworkByFilter : Start");

        final String query = "start=" + start + "&count=" + count;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FcNetworkClientImpl : getFcNetworkByFilter : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        final FcNetworkCollection fcNetworkCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("FcNetworkClientImpl : getFcNetworkByFilter : count :" + fcNetworkCollectionDto.getCount());
        logger.info("FcNetworkClientImpl : getFcNetworkByFilter : End");

        return fcNetworkCollectionDto;
    }

    @Override
    public FcNetworkCollection getAllFcNetworks(final RestParams params) {
        logger.info("FcNetworkClientImpl : getAllFcNetworks : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FcNetworkClientImpl : getAllFcNetworks : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        final FcNetworkCollection fcNetworkCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("FcNetworkClientImpl : getAllFcNetworks : count :" + fcNetworkCollectionDto.getCount());
        logger.info("FcNetworkClientImpl : getAllFcNetworks : End");

        return fcNetworkCollectionDto;
    }

    @Override
    public FcNetwork getFcNetworkByName(final RestParams params, final String name) {

        logger.info("FcNetworkClientImpl : getFcNetworkByName : Start");

        // final String query = "filter=\"name=\'" + name + "\'\"";
        final String query = urlUtils.createQueryString(name);
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FcNetworkClientImpl : getFcNetworkByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        final FcNetworkCollection fcNetworkCollectionDto = adaptor.buildCollectionDto(returnObj);
        FcNetwork fcNetworkDto;
        if (fcNetworkCollectionDto.getCount() != 0) {
            fcNetworkDto = fcNetworkCollectionDto.getMembers().get(0);
        } else {
            fcNetworkDto = null;
        }

        if (fcNetworkDto == null) {
            logger.error("FcNetworkClientImpl : getFcNetworkByName : resource not Found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.FC_NETWORK, null);
        }
        logger.info("FcNetworkClientImpl : getFcNetworkByName : End");

        return fcNetworkDto;
    }

    @Override
    public TaskResourceV2 createFcNetwork(final RestParams params, final FcNetwork fcNetworkDto, final boolean aSync,
            final boolean useJsonRequest) {
        logger.info("FcNetworkClientImpl : createFcNetwork : Start");
        String returnObj = null;

        // validate params
        if (fcNetworkDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI));

        // check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        if (useJsonRequest == true) {
            final FcNetwork dto = adaptor.buildDto(fcNetworkDto.getJsonRequest().getBody());
            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(dto);

        } else {

            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(fcNetworkDto);

        }
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("FcNetworkClientImpl : createFcNetwork : returnObj =" + returnObj);
        logger.debug("FcNetworkClientImpl : createFcNetwork : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("FcNetworkClientImpl : createFcNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateFcNetwork(final RestParams params, final String resourceId, final FcNetwork fcNetworkDto,
            final boolean aSync, final boolean useJsonRequest) {
        logger.info("FcNetworkClientImpl : updateFcNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (fcNetworkDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating server profile dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(fcNetworkDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("FcNetworkClientImpl : updateFcNetwork : returnObj =" + returnObj);
        logger.debug("FcNetworkClientImpl : updateFcNetwork : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("FcNetworkClientImpl : updateFcNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteFcNetwork(final RestParams params, final String resourceId, final boolean aSync) {
        logger.info("FcNetworkClientImpl : deleteFcNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FcNetworkClientImpl : deleteFcNetwork : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORK, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("FcNetworkClientImpl : deleteFcNetwork : returnObj =" + returnObj);
        logger.debug("FcNetworkClientImpl : deleteFcNetwork : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("FcNetworkClientImpl : deleteFcNetwork : End");

        return taskResourceV2;
    }

}
