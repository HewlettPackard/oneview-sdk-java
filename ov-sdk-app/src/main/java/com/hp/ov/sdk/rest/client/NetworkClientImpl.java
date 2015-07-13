/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.NetworkAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class NetworkClientImpl implements NetworkClient
{

    private static final Logger logger = LoggerFactory
            .getLogger(NetworkClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ConnectionTemplateClient connectionTemplateClient;

    @Autowired
    private NetworkAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Override
    public Network getNetwork(final RestParams params, final String resourceId)
    {

        logger.info("NetworkClientImpl : getNetwork : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkClient : getNetwork : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKS, null);
        }
        // Call adaptor to convert to DTO
        final Network networkDto = adaptor.buildDto(returnObj);

        logger.debug("NetworkClient : getNetwork : vlanID :"
                + networkDto.getVlanId());
        logger.info("NetworkClientImpl : getNetwork : End");

        return networkDto;
    }

    @Override
    public NetworkCollection getAllNetworks(final RestParams params)
    {
        logger.info("NetworkClientImpl : getAllNetworks : Start");
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI));

        // call rest client
        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkClientImpl : getAllNetworks : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        final NetworkCollection networkCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("NetworkClient : getAllNetworks : members count :"
                + networkCollectionDto.getCount());
        logger.info("NetworkClientImpl : getAllNetworks : End");

        return networkCollectionDto;

    }

    @Override
    public Network getNetworkByName(final RestParams params, final String name)
    {
        logger.info("NetworkClientImpl : getNetworkByName : Start");

        final String query = sdkUtils.createQueryString(name);
        logger.debug("NetworkClientImpl : getNetworkByName : query = " + query);
        Network networkDto = null;
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkClientImpl : getNetworkByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        final NetworkCollection networkCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (networkCollectionDto.getCount() != 0)
        {
            networkDto = networkCollectionDto.getMembers().get(0);
        }
        else
        {
            networkDto = null;
        }

        if (networkDto == null)
        {
            logger.error("NetworkClientImpl : getNetworkByName : resource not Found for name :"
                    + name);
            throw new SDKResourceNotFoundException(
                    SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.NETWORKS, null);
        }
        logger.info("NetworkClientImpl : getNetworkByName : End");

        return networkDto;
    }

    @Override
    public TaskResourceV2 createNetwork(final RestParams params, final Network dto,
            final boolean aSync, final boolean useJsonRequest)
    {
        ConnectionTemplate connectionTemplateDto, tempDto;
        logger.info("NetworkClientImpl : createNetwork : Start");
        String returnObj = null;
        String networkName = null;
        Network networkDto = null;
        // validate params
        if (dto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.NETWORKS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI));

        // check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        if (useJsonRequest == true)
        {
            networkDto = adaptor.buildDto(dto.getJsonRequest()
                    .getBody());
            // create json object
            tempDto = networkDto.getConnectionTemplate();
            networkDto.setConnectionTemplate(null);
            networkName = networkDto.getName();
            jsonObject = adaptor.buildJsonObjectFromDto(networkDto);
        }
        else
        {
            tempDto = dto.getConnectionTemplate();
            dto.setConnectionTemplate(null);
            networkName = dto.getName();
            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(dto);
        }

        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("NetworkClientImpl : createNetwork : returnObj ="
                + returnObj);
        logger.debug("NetworkClientImpl : createNetwork : taskResource ="
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

        if (tempDto != null)
        {
            networkDto = getNetworkByName(params, networkName);
            final String connectionTemplateUri = networkDto
                    .getConnectionTemplateUri();
            connectionTemplateDto = connectionTemplateClient
                    .getConnectionTemplate(params,
                            (connectionTemplateUri
                                    .substring(connectionTemplateUri
                                            .lastIndexOf("/") + 1)));
            connectionTemplateDto.setBandwidth(tempDto.getBandwidth());

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            connectionTemplateDto = connectionTemplateClient
                    .updateConnectionTemplate(params,
                            (connectionTemplateUri
                                    .substring(connectionTemplateUri
                                            .lastIndexOf("/") + 1)),
                            connectionTemplateDto, false);
        }
        logger.info("NetworkClientImpl : createNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateNetwork(final RestParams params, final String resourceId,
            final Network dto, final boolean asyncOrSyncMode,
            final boolean useJsonRequest)
    {
        logger.info("NetworkClientImpl : updateNetwork : Start");
        Network networkDto = null;
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (dto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.NETWORK, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI, resourceId));
        String returnObj = null;

        // check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        if (useJsonRequest == true)
        {
            networkDto = adaptor.buildDto(dto.getJsonRequest()
                    .getBody());
            // create json object
            jsonObject = adaptor.buildJsonObjectFromDto(networkDto);
        }
        else
        {
            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(dto);
        }

        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("NetworkClientImpl : updateNetwork : returnObj ="
                + returnObj);
        logger.debug("NetworkClientImpl : updateNetwork : taskResource ="
                + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && asyncOrSyncMode == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("NetworkClientImpl : updateNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteNetwork(final RestParams params,
            final String resourceId, final boolean aSync)
    {

        logger.info("NetworkClientImpl : deleteNetwork : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkClient : deleteNetwork : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKS, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("NetworkClientImpl : deleteNetwork : returnObj ="
                + returnObj);
        logger.debug("NetworkClientImpl : deleteNetwork : taskResource ="
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
        logger.info("NetworkClientImpl : deleteNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 createNetworkInBulk(final RestParams params,
            final BulkEthernetNetwork bulkEthernetDto, final boolean aSync,
            final boolean useJsonRequest)
    {
        logger.info("NetworkClientImpl : createNetworkInBulk : Start");
        String returnObj = null;

        // validate params
        if (bulkEthernetDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.BULK_ETHERNET_NETWORK, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.BULK_ETHERNET_URI));

        // check for json request in the input bulkEthernetDto. if it is
        // present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network bulkEthernetDto.

        if (useJsonRequest == true)
        {
            final BulkEthernetNetwork bulkEthernetNetworkDto = adaptor
                    .buildBulkEthernetDto(bulkEthernetDto.getJsonRequest()
                            .getBody());
            jsonObject = adaptor
                    .buildJsonObjectFrombulkEthernetDto(bulkEthernetNetworkDto);
        }
        else
        {
            // create JSON request from bulkEthernetDto
            jsonObject = adaptor
                    .buildJsonObjectFrombulkEthernetDto(bulkEthernetDto);
        }
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("NetworkClientImpl : createNetworkInBulk : returnObj ="
                + returnObj);
        logger.debug("NetworkClientImpl : createNetworkInBulk : taskResource ="
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
        logger.info("NetworkClientImpl : createNetworkInBulk : End");

        return taskResourceV2;
    }

}
