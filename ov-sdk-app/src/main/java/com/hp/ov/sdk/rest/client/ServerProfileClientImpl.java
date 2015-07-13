/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.ServerProfileAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerHardware;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class ServerProfileClientImpl implements ServerProfileClient
{

    private static final Logger logger = LoggerFactory
            .getLogger(ServerProfileClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ServerProfileAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Autowired
    private ServerHardwareClientImpl serverHardwareClientImpl;

    @Override
    public ServerProfile getServerProfile(final RestParams params, final String resourceId)
    {
        logger.info("ServerProfileClientImpl : getServerProfile : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getServerProfile : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }
        // Call adaptor to convert to DTO
        final ServerProfile serverProfileDto = adaptor.buildDto(returnObj);

        logger.debug("ServerProfileClientImpl : getServerProfile : Name :"
                + serverProfileDto.getName());
        logger.info("ServerProfileClientImpl : getServerProfile : End");

        return serverProfileDto;
    }

    @Override
    public ServerProfileCollection getAllServerProfile(final RestParams params)
    {
        logger.info("ServerProfileClientImpl : getAllServerProfile : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getAllServerProfile : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILES, null);
        }
        // Call adaptor to convert to DTO

        final ServerProfileCollection serverProfileCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("ServerProfileClientImpl : getAllServerProfile : Count :"
                + serverProfileCollectionDto.getCount());
        logger.info("ServerProfileClientImpl : getAllServerProfile : End");

        return serverProfileCollectionDto;
    }

    @Override
    public ServerProfile getServerProfileByName(final RestParams params, final String name)
    {
        ServerProfile serverProfileDto = null;
        logger.info("ServerProfileClientImpl : getServerProfileByName : Start");
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
                ResourceUris.SERVER_PROFILE_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getServerProfileByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILES, null);
        }
        // Call adaptor to convert to DTO

        final ServerProfileCollection serverProfileCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (serverProfileCollectionDto.getCount() != 0)
        {
            serverProfileDto = serverProfileCollectionDto.getMembers().get(0);
        }
        else
        {
            serverProfileDto = null;
        }

        if (serverProfileDto == null) 
        {
        logger.error("ServerProfileClientImpl : getServerProfileByName : Not found for name :"
                + name);
        throw new SDKResourceNotFoundException(
                SDKErrorEnum.resourceNotFound, null, null, null,
                SdkConstants.SERVER_PROFILE, null);
        }
        logger.info("ServerProfileClientImpl : getServerProfileByName : End");

        return serverProfileDto;
    }

    @Override
    public AvailableNetworks getAvailableNetworksForServerProfile(
            final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri)
    {
        logger.info("ServerProfileClientImpl : getAvailableNetworksForServerProfile : Start");

        final String query = "serverHardwareTypeUri=" + serverHardwareTypeUri
                + "&enclosureGroupUri=" + enclosureGroupUri;

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.AVAILABLE_NETWORKS_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }
        // Call adaptor to convert to DTO

        final AvailableNetworks availableNetworksDto = adaptor.buildAvailableNetworkDto(returnObj);

        if (availableNetworksDto != null)
        {
            logger.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : networkSet :"
                    + availableNetworksDto.getNetworkSets());
        }
        else
        {
            logger.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : Not Found for server hardware :"
                    + serverHardwareTypeUri
                    + "and enclosure group "
                    + enclosureGroupUri);
        }
        logger.info("ServerProfileClientImpl : getAvailableNetworksForServerProfile : End");

        return availableNetworksDto;
    }

    @Override
    public List<AvailableServers> getAvailableServersForServerProfile(
            final RestParams params)
    {
        logger.info("ServerProfileClientImpl : getAvailableServersForServerProfile : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.AVAILABLE_SERVERS_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor
                .buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto != null)
        {
            logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : name :"
                    + availableServersCollectionDto.get(0).getName());
        }
        else
        {
            logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : Not Found ");
        }
        logger.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public List<AvailableServers> getAvailableServersForServerProfile(
            final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri)
    {
        logger.info("ServerProfileClientImpl : getAvailableServersForServerProfile : Start");

        final String query = "serverHardwareTypeUri=" + serverHardwareTypeUri
                + "&enclosureGroupUri=" + enclosureGroupUri;

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.AVAILABLE_SERVERS_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor
                .buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto.get(0) != null)
        {
            logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : value :"
                    + availableServersCollectionDto.get(0).getName());
        }
        else
        {
            logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : Not Found for server hardware :"
                    + serverHardwareTypeUri
                    + "and enclosure group "
                    + enclosureGroupUri);
        }
        logger.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public List<AvailableServers> getAvailableServersForServerProfile(
            final RestParams params, final String profileUri)
    {
        logger.info("ServerProfileClientImpl : getAvailableServersForServerProfile : Start");

        final String query = "profileUri=" + profileUri;

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.AVAILABLE_SERVERS_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor
                .buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto != null)
        {
            logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : name :"
                    + availableServersCollectionDto.get(0).getName());
        }
        else
        {
            logger.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : Not Found for profile  :"
                    + profileUri);
        }
        logger.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public ProfilePorts getProfilePortsForServerProfile(final RestParams params,
            final String serverHardwareTypeUri, final String enclosureGroupUri)
    {
        logger.info("ServerProfileClientImpl : getProfilePortsForServerProfile : Start");

        final String query = "serverHardwareTypeUri=" + serverHardwareTypeUri
                + "&enclosureGroupUri=" + enclosureGroupUri;

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.PROFILE_PORTS_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : getProfilePortsForServerProfile : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }
        // Call adaptor to convert to DTO

        final ProfilePorts profilePortsDto = adaptor.buildProfilePortsDto(returnObj);

        if (profilePortsDto != null)
        {
            logger.debug("ServerProfileClientImpl : getProfilePortsForServerProfile : networkSet :"
                    + profilePortsDto.toString());
        }
        else
        {
            logger.debug("ServerProfileClientImpl : getProfilePortsForServerProfile : Not Found for server hardware :"
                    + serverHardwareTypeUri
                    + "and enclosure group "
                    + enclosureGroupUri);
        }
        logger.info("ServerProfileClientImpl : getProfilePortsForServerProfile : End");

        return profilePortsDto;
    }

    @Override
    public TaskResourceV2 createServerProfile(final RestParams params,
            final ServerProfile serverProfileDto, final boolean aSync,
            final boolean useJsonRequest)
    {
        logger.info("ServerProfileClientImpl : createServerProfile : Start");
        String returnObj = null;

        // validate params
        if (serverProfileDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.SERVER_PROFILE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("ServerProfileClientImpl : createServerProfile : returnObj ="
                + returnObj);
        logger.debug("ServerProfileClientImpl : createServerProfile : taskResource ="
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
        logger.info("ServerProfileClientImpl : createServerProfile : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateServerProfile(final RestParams params,
            final String resourceId, final ServerProfile serverProfileDto,
            final boolean aSync, final boolean useJsonRequest)
    {
        logger.info("ServerProfileClientImpl : updateServerProfile : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (serverProfileDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.SERVER_PROFILE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating server profile dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("ServerProfileClientImpl : updateServerProfile : returnObj ="
                + returnObj);
        logger.debug("ServerProfileClientImpl : updateServerProfile : taskResource ="
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
        logger.info("ServerProfileClientImpl : updateServerProfile : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteServerProfile(final RestParams params,
            final String resourceId, final boolean aSync)
    {
        logger.info("ServerProfileClientImpl : deleteServerProfile : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : deleteServerProfile : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("ServerProfileClientImpl : deleteServerProfile : returnObj ="
                + returnObj);
        logger.debug("ServerProfileClientImpl : deleteServerProfile : taskResource ="
                + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("ServerProfileClientImpl : deleteServerProfile : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteServerProfileByFilter(final RestParams params,
            final String filter, final Boolean match, final boolean aSync)
    {
        logger.info("ServerProfileClientImpl : deleteServerProfileByFilter : Start");

        String query = null;
        if (match)
        {
            query = "filter=\"name matches \'%25" + filter + "%25\'\"";
        }
        else
        {
            query = "filter=name=\"" + filter + "\"";
        }

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("ServerProfileClientImpl : deleteServerProfileByFilter : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("ServerProfileClientImpl : deleteServerProfileByFilter : returnObj ="
                + returnObj);
        logger.debug("ServerProfileClientImpl : deleteServerProfileByFilter : taskResource ="
                + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("ServerProfileClientImpl : deleteServerProfileByFilter : End");

        return taskResourceV2;
    }

    //Works for shared SAN
    //TODO - The code needs to be updated when its a private LUN.
    @Override
    public TaskResourceV2 copyServerProfile(final RestParams params,
            final String sourceName, final String destinationBay, final String profileName,
            final boolean aSync)
    {

        String returnObj = null;
        ServerProfile serverProfileDto = null;

        serverProfileDto = getServerProfileByName(params, sourceName);
        final ServerHardwareCollection serverHardwareCollectionDto = serverHardwareClientImpl
                .getServerHardwareWithNoProfile(params);

        ServerHardware serverHardwareDto = null;
        for (final ServerHardware serverHardware : new ArrayList<>(
                serverHardwareCollectionDto.getMembers()))
        {
            if (serverHardware.getName().equals(destinationBay))
            {
                System.out.println(serverHardware.getName());
                logger.info("ServerHardwareClientImpl : getServerHardwareByName : End");
                serverHardwareDto = serverHardware;
            }
        }

        // validate params
        if (serverHardwareDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.SERVER_HARDWARE, null);
        }

        if (!(serverProfileDto.getServerHardwareTypeUri()
                .equals(serverHardwareDto.getServerHardwareTypeUri())))
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.SERVER_PROFILE, null);
        }

        // Currently supporting shared volume
        // TODO-Code modification to be added to handle non shared volumes.
        final List<ProfileConnectionV3> connections = serverProfileDto
                .getConnections();
        final List<ProfileConnectionV3> newConnections = new ArrayList<ProfileConnectionV3>();
        for (final ProfileConnectionV3 connection : connections)
        {
            connection.setMac(null);
            connection.setWwnn(null);
            connection.setWwpn(null);
            newConnections.add(connection);
        }

        serverProfileDto.setConnections(newConnections);

        // Add server profile details for current bay
        serverProfileDto.setName(profileName);
        serverProfileDto.setServerHardwareUri(serverHardwareDto.getUri());

        // validate params
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("ServerProfileClientImpl : copyServerProfile : returnObj ="
                + returnObj);
        logger.debug("ServerProfileClientImpl : copyServerProfile : taskResource ="
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
        logger.info("ServerProfileClientImpl : copyServerProfile : End");

        return taskResourceV2;

    }
    // TODO - implement the remaining update methods and GetByName method

}
