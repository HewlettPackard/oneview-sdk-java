/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

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
import com.hp.ov.sdk.util.UrlUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ServerProfileClientImpl implements ServerProfileClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProfileClientImpl.class);
    private static final int TIMEOUT = 1200000; // in milliseconds = 20 mins

    private final ServerProfileAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final ServerHardwareClient serverHardwareClientImpl;

    private JSONObject jsonObject;

    protected ServerProfileClientImpl(ServerProfileAdaptor adaptor, TaskAdaptor taskAdaptor,
        TaskMonitorManager taskMonitor, ServerHardwareClient serverHardwareClientImpl) {

        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
        this.serverHardwareClientImpl = serverHardwareClientImpl;
    }

    public static ServerProfileClient getClient() {
        return new ServerProfileClientImpl(new ServerProfileAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance(),
                ServerHardwareClientImpl.getClient());
    }

    @Override
    public ServerProfile getServerProfile(final RestParams params, final String resourceId) {
        LOGGER.info("ServerProfileClientImpl : getServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO
        final ServerProfile serverProfileDto = adaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : getServerProfile : Name :" + serverProfileDto.getName());
        LOGGER.info("ServerProfileClientImpl : getServerProfile : End");

        return serverProfileDto;
    }

    @Override
    public ServerProfileCollection getAllServerProfile(final RestParams params) {
        LOGGER.info("ServerProfileClientImpl : getAllServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getAllServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILES,
                    null);
        }
        // Call adaptor to convert to DTO

        final ServerProfileCollection serverProfileCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : getAllServerProfile : Count :" + serverProfileCollectionDto.getCount());
        LOGGER.info("ServerProfileClientImpl : getAllServerProfile : End");

        return serverProfileCollectionDto;
    }

    @Override
    public ServerProfile getServerProfileByName(final RestParams params, final String name) {
        ServerProfile serverProfileDto = null;
        LOGGER.info("ServerProfileClientImpl : getServerProfileByName : Start");
        // final String query = "filter=\"name=\'" + name + "\'\"";
        final String query = UrlUtils.createFilterString(name);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getServerProfileByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILES,
                    null);
        }
        // Call adaptor to convert to DTO

        final ServerProfileCollection serverProfileCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (serverProfileCollectionDto.getCount() != 0) {
            serverProfileDto = serverProfileCollectionDto.getMembers().get(0);
        } else {
            serverProfileDto = null;
        }

        if (serverProfileDto == null) {
            LOGGER.error("ServerProfileClientImpl : getServerProfileByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        LOGGER.info("ServerProfileClientImpl : getServerProfileByName : End");

        return serverProfileDto;
    }

    @Override
    public AvailableNetworks getAvailableNetworksForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri) {
        LOGGER.info("ServerProfileClientImpl : getAvailableNetworksForServerProfile : Start");

        final String query = "serverHardwareTypeUri=" + serverHardwareTypeUri + "&enclosureGroupUri=" + enclosureGroupUri;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.AVAILABLE_NETWORKS_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final AvailableNetworks availableNetworksDto = adaptor.buildAvailableNetworkDto(returnObj);

        if (availableNetworksDto != null) {
            LOGGER.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : networkSet :"
                    + availableNetworksDto.getNetworkSets());
        } else {
            LOGGER.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : Not Found for server hardware :"
                    + serverHardwareTypeUri + "and enclosure group " + enclosureGroupUri);
        }
        LOGGER.info("ServerProfileClientImpl : getAvailableNetworksForServerProfile : End");

        return availableNetworksDto;
    }

    @Override
    public List<AvailableServers> getAvailableServersForServerProfile(final RestParams params) {
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.AVAILABLE_SERVERS_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor.buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto != null) {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : name :"
                    + availableServersCollectionDto.get(0).getName());
        } else {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : Not Found ");
        }
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public List<AvailableServers> getAvailableServersForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri) {
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : Start");

        final String query = "serverHardwareTypeUri=" + serverHardwareTypeUri + "&enclosureGroupUri=" + enclosureGroupUri;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.AVAILABLE_SERVERS_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor.buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto.get(0) != null) {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : value :"
                    + availableServersCollectionDto.get(0).getName());
        } else {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : Not Found for server hardware :"
                    + serverHardwareTypeUri + "and enclosure group " + enclosureGroupUri);
        }
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public List<AvailableServers> getAvailableServersForServerProfile(final RestParams params, final String profileUri) {
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : Start");

        final String query = "profileUri=" + profileUri;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.AVAILABLE_SERVERS_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor.buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto != null) {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : name :"
                    + availableServersCollectionDto.get(0).getName());
        } else {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : Not Found for profile  :" + profileUri);
        }
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public ProfilePorts getProfilePortsForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri) {
        LOGGER.info("ServerProfileClientImpl : getProfilePortsForServerProfile : Start");

        final String query = "serverHardwareTypeUri=" + serverHardwareTypeUri + "&enclosureGroupUri=" + enclosureGroupUri;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.PROFILE_PORTS_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : getProfilePortsForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final ProfilePorts profilePortsDto = adaptor.buildProfilePortsDto(returnObj);

        if (profilePortsDto != null) {
            LOGGER.debug("ServerProfileClientImpl : getProfilePortsForServerProfile : networkSet :" + profilePortsDto.toString());
        } else {
            LOGGER.debug("ServerProfileClientImpl : getProfilePortsForServerProfile : Not Found for server hardware :"
                    + serverHardwareTypeUri + "and enclosure group " + enclosureGroupUri);
        }
        LOGGER.info("ServerProfileClientImpl : getProfilePortsForServerProfile : End");

        return profilePortsDto;
    }

    @Override
    public TaskResourceV2 createServerProfile(final RestParams params, final ServerProfile serverProfileDto, final boolean aSync,
            final boolean useJsonRequest) {
        LOGGER.info("ServerProfileClientImpl : createServerProfile : Start");
        String returnObj = null;

        // validate params
        if (serverProfileDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_PROFILE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : createServerProfile : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileClientImpl : createServerProfile : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileClientImpl : createServerProfile : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateServerProfile(final RestParams params, final String resourceId,
            final ServerProfile serverProfileDto, final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("ServerProfileClientImpl : updateServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (serverProfileDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_PROFILE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating server profile dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : updateServerProfile : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileClientImpl : updateServerProfile : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileClientImpl : updateServerProfile : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteServerProfile(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.info("ServerProfileClientImpl : deleteServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : deleteServerProfile : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : deleteServerProfile : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileClientImpl : deleteServerProfile : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileClientImpl : deleteServerProfile : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteServerProfileByFilter(final RestParams params, final String filter, final Boolean match,
            final boolean aSync) {
        LOGGER.info("ServerProfileClientImpl : deleteServerProfileByFilter : Start");

        String query = null;
        if (match) {
            query = "filter=\"name matches \'%25" + filter + "%25\'\"";
        } else {
            query = "filter=name=\"" + filter + "\"";
        }

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("ServerProfileClientImpl : deleteServerProfileByFilter : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : deleteServerProfileByFilter : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileClientImpl : deleteServerProfileByFilter : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileClientImpl : deleteServerProfileByFilter : End");

        return taskResourceV2;
    }

    // Works for shared SAN
    // TODO - The code needs to be updated when its a private LUN.
    @Override
    public TaskResourceV2 copyServerProfile(final RestParams params, final String sourceName, final String destinationBay,
            final String profileName, final boolean aSync) {

        String returnObj = null;
        ServerProfile serverProfileDto = null;

        serverProfileDto = getServerProfileByName(params, sourceName);
        final ServerHardwareCollection serverHardwareCollectionDto = serverHardwareClientImpl
                .getServerHardwareWithNoProfile(params);

        ServerHardware serverHardwareDto = null;
        for (final ServerHardware serverHardware : new ArrayList<>(serverHardwareCollectionDto.getMembers())) {
            if (serverHardware.getName().equals(destinationBay)) {
                System.out.println(serverHardware.getName());
                LOGGER.info("ServerHardwareClientImpl : getServerHardwareByName : End");
                serverHardwareDto = serverHardware;
            }
        }

        // validate params
        if (serverHardwareDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_HARDWARE,
                    null);
        }

        if (!(serverProfileDto.getServerHardwareTypeUri().equals(serverHardwareDto.getServerHardwareTypeUri()))) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_PROFILE, null);
        }

        // Currently supporting shared volume
        // TODO-Code modification to be added to handle non shared volumes.
        final List<ProfileConnectionV3> connections = serverProfileDto.getConnections();
        final List<ProfileConnectionV3> newConnections = new ArrayList<ProfileConnectionV3>();
        for (final ProfileConnectionV3 connection : connections) {
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
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : copyServerProfile : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileClientImpl : copyServerProfile : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileClientImpl : copyServerProfile : End");

        return taskResourceV2;

    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        ServerProfile serverProfileDto = getServerProfileByName(creds, name);

        if (null != serverProfileDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(serverProfileDto.getUri());
        }
        return resourceId;
    }
    // TODO - implement the remaining update methods and GetByName method

}
