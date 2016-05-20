/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.ServerProfileAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.ServerProfileHealth;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;


public class ServerProfileClientImpl implements ServerProfileClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProfileClientImpl.class);
    private static final int TIMEOUT = 1200000; // in milliseconds = 20 mins

    private final ResourceAdaptor resourceAdaptor;
    private final ServerProfileAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    private ServerProfileClientImpl(HttpRestClient httpClient, ResourceAdaptor resourceAdaptor,
            ServerProfileAdaptor adaptor, TaskAdaptor taskAdaptor,
            TaskMonitorManager taskMonitor) {

        this.httpClient = httpClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static ServerProfileClient getClient() {
        return new ServerProfileClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new ServerProfileAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
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

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO
        final ServerProfile serverProfileDto = adaptor.buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("ServerProfileClientImpl : getServerProfile : Name :" + serverProfileDto.getName());
        LOGGER.info("ServerProfileClientImpl : getServerProfile : End");

        return serverProfileDto;
    }

    @Override
    public ResourceCollection<ServerProfile> getAllServerProfile(final RestParams params) {
        LOGGER.info("ServerProfileClientImpl : getAllServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAllServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILES,
                    null);
        }

        ResourceCollection<ServerProfile> serverProfileCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, ServerProfile.class);

        LOGGER.debug("ServerProfileClientImpl : getAllServerProfile : Count :" + serverProfileCollectionDto.getCount());
        LOGGER.info("ServerProfileClientImpl : getAllServerProfile : End");

        return serverProfileCollectionDto;
    }

    @Override
    public ServerProfile getServerProfileByName(final RestParams params, final String name) {
        ServerProfile serverProfileDto = null;
        LOGGER.info("ServerProfileClientImpl : getServerProfileByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getServerProfileByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILES,
                    null);
        }

        ResourceCollection<ServerProfile> serverProfileCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, ServerProfile.class);

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
    public ServerProfileCompliancePreview getServerProfileCompliancePreview(RestParams params, String resourceId) {
        LOGGER.info("ServerProfileClientImpl : getServerProfileCompliancePreview : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI,
                resourceId,
                SdkConstants.COMPLIANCE_PREVIEW));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getServerProfileCompliancePreview : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO
        final ServerProfileCompliancePreview compliancePreviewDto = adaptor.buildCompliancePreviewDto(returnObj, params.getApiVersion());

        LOGGER.debug("ServerProfileClientImpl : getServerProfileCompliancePreview : DTO :" + compliancePreviewDto);
        LOGGER.info("ServerProfileClientImpl : getServerProfileCompliancePreview : End");

        return compliancePreviewDto;
    }

    @Override
    public ServerProfileHealth getServerProfileMessages(RestParams params, String resourceId) {
        LOGGER.info("ServerProfileClientImpl : getServerProfileMessages : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI,
                resourceId,
                SdkConstants.MESSAGES));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getServerProfileMessages : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO
        final ServerProfileHealth healthDto = adaptor.buildHealthDto(returnObj, params.getApiVersion());

        LOGGER.debug("ServerProfileClientImpl : getServerProfileMessages : DTO :" + healthDto);
        LOGGER.info("ServerProfileClientImpl : getServerProfileMessages : End");

        return healthDto;
    }

    @Override
    public ServerProfile getServerProfileTransformation(RestParams params, String resourceId,
            String serverHardwareTypeUri, String enclosureGroupUri) {
        LOGGER.info("ServerProfileClientImpl : getServerProfileTransformation : Start");

        final String query = "?serverHardwareTypeUri=" + serverHardwareTypeUri + "&enclosureGroupUri=" + enclosureGroupUri;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI,
                resourceId,
                SdkConstants.TRANSFORMATION,
                query));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getServerProfileTransformation : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO
        final ServerProfile profileDto = adaptor.buildServerProfileDto(returnObj, params.getApiVersion());

        LOGGER.debug("ServerProfileClientImpl : getServerProfileTransformation : DTO :" + profileDto);
        LOGGER.info("ServerProfileClientImpl : getServerProfileTransformation : End");

        return profileDto;
    }

    @Override
    public AvailableNetworks getAvailableNetworksForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri) {
        LOGGER.info("ServerProfileClientImpl : getAvailableNetworksForServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("serverHardwareTypeUri", serverHardwareTypeUri);
        query.put("enclosureGroupUri", enclosureGroupUri);
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.AVAILABLE_NETWORKS_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final AvailableNetworks availableNetworksDto = adaptor.buildAvailableNetworkDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : getAvailableNetworksForServerProfile : networkSet :"
                    + availableNetworksDto.getNetworkSets());

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
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.AVAILABLE_SERVERS_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor.buildAvailableServerDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : name :"
                + availableServersCollectionDto.get(0).getName());

        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public List<AvailableServers> getAvailableServersForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri) {
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("serverHardwareTypeUri", serverHardwareTypeUri);
        query.put("enclosureGroupUri", enclosureGroupUri);
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.AVAILABLE_SERVERS_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor.buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto.size() > 0) {
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

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("profileUri", profileUri);
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.AVAILABLE_SERVERS_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final List<AvailableServers> availableServersCollectionDto = adaptor.buildAvailableServerDto(returnObj);

        if (availableServersCollectionDto.size() > 0) {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : name :"
                    + availableServersCollectionDto.get(0).getName());
        } else {
            LOGGER.debug("ServerProfileClientImpl : getAvailableServersForServerProfile : Not Found for profile  :" + profileUri);
        }
        LOGGER.info("ServerProfileClientImpl : getAvailableServersForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public ResourceCollection<AvailableStorageSystem> getAvailableStorageSystemsForServerProfile(RestParams params,
            String enclosureGroupUri, String serverHardwareTypeUri) {
        LOGGER.info("ServerProfileClientImpl : getAvailableStorageSystemsForServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        if (StringUtils.isEmpty(enclosureGroupUri) || StringUtils.isEmpty(serverHardwareTypeUri)) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("serverHardwareTypeUri", serverHardwareTypeUri);
        query.put("enclosureGroupUri", enclosureGroupUri);
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.AVAILABLE_STORAGE_SYSTEMS));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableStorageSystemsForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }

        ResourceCollection<AvailableStorageSystem> availableServersCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, AvailableStorageSystem.class);

        if (availableServersCollectionDto.getTotal() > 0) {
            LOGGER.debug("ServerProfileClientImpl : getAvailableStorageSystemsForServerProfile : value :"
                    + availableServersCollectionDto.getMembers().get(0).getStorageSystemName());
        } else {
            LOGGER.debug("ServerProfileClientImpl : getAvailableStorageSystemsForServerProfile : Not Found for server hardware :"
                    + serverHardwareTypeUri + "and enclosure group " + enclosureGroupUri);
        }
        LOGGER.info("ServerProfileClientImpl : getAvailableStorageSystemsForServerProfile : End");

        return availableServersCollectionDto;
    }

    @Override
    public AvailableStorageSystem getAvailableStorageSystemForServerProfile(RestParams params, String enclosureGroupUri,
            String serverHardwareTypeUri, String storageSystemId) {
        LOGGER.info("ServerProfileClientImpl : getAvailableStorageSystemForServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        if (StringUtils.isEmpty(enclosureGroupUri)
                || StringUtils.isEmpty(serverHardwareTypeUri)
                || StringUtils.isEmpty(storageSystemId)) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        } else {
            Map<String, String> query = new HashMap<String, String>();
            query.put("serverHardwareTypeUri", serverHardwareTypeUri);
            query.put("enclosureGroupUri", enclosureGroupUri);
            query.put("storageSystemId", storageSystemId);
            params.setQuery(query);
        }

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.AVAILABLE_STORAGE_SYSTEM));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableStorageSystemForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final AvailableStorageSystem availableStorageSystemDto = adaptor.buildAvailableStorageSystemDto(returnObj, params.getApiVersion());

        LOGGER.info("ServerProfileClientImpl : getAvailableStorageSystemForServerProfile : End");

        return availableStorageSystemDto;
    }

    @Override
    public AvailableTargets getAvailableTargetsForServerProfile(RestParams params) {
        return this.getAvailableTargetsForServerProfile(params, null, null, null);
    }

    @Override
    public AvailableTargets getAvailableTargetsForServerProfile(RestParams params, String enclosureGroupUri,
            String serverHardwareTypeUri, String profileUri) {
        LOGGER.info("ServerProfileClientImpl : getAvailableTargetsForServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        if (!Strings.isNullOrEmpty(serverHardwareTypeUri)) {
            query.put("serverHardwareTypeUri", serverHardwareTypeUri);
        }
        if (!Strings.isNullOrEmpty(enclosureGroupUri)) {
            query.put("enclosureGroupUri", enclosureGroupUri);
        }
        if (!Strings.isNullOrEmpty(profileUri)) {
            query.put("profileUri", profileUri);
        }
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.AVAILABLE_TARGETS));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getAvailableTargetsForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final AvailableTargets availableTargetsDto = adaptor.buildAvailableTargetsDto(returnObj, params.getApiVersion());

        LOGGER.info("ServerProfileClientImpl : getAvailableTargetsForServerProfile : End");

        return availableTargetsDto;
    }

    @Override
    public ProfilePorts getProfilePortsForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri) {
        LOGGER.info("ServerProfileClientImpl : getProfilePortsForServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("serverHardwareTypeUri", serverHardwareTypeUri);
        query.put("enclosureGroupUri", enclosureGroupUri);
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.PROFILE_PORTS_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileClientImpl : getProfilePortsForServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE,
                    null);
        }
        // Call adaptor to convert to DTO

        final ProfilePorts profilePortsDto = adaptor.buildProfilePortsDto(returnObj, params.getApiVersion());

        if (profilePortsDto.getPorts().size() > 0) {
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

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

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
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);
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
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileDto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);
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
    public TaskResourceV2 patchServerProfile(final RestParams params, final String resourceId, final Patch patchDto, final boolean aSync) {
        LOGGER.info("ServerProfileClientImpl : patchEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (patchDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_PROFILE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PATCH);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        JSONArray jsonArray = adaptor.buildJsonArrayDto(patchDto);
        returnObj = httpClient.sendRequest(params, jsonArray);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileClientImpl : patchServerProfile : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileClientImpl : patchServerProfile : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileClientImpl : patchServerProfile : End");

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
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI,
                resourceId));

        final String returnObj = httpClient.sendRequest(params);
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

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        if (StringUtils.isNotBlank(filter) && null != match) {
            if (match) {
                Map<String, String> query = new HashMap<String, String>();
                query.put("filter", "name matches '%" + filter + "%'");
                params.setQuery(query);
            } else {
                Map<String, String> query = new HashMap<String, String>();
                query.put("filter", "name='" + filter + "'");
                params.setQuery(query);
            }
        } else {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_URI));

        final String returnObj = httpClient.sendRequest(params);
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

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        ServerProfile serverProfileDto = getServerProfileByName(params, name);

        if (null != serverProfileDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(serverProfileDto.getUri());
        }
        return resourceId;
    }

}
