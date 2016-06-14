/*
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
 */
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddServer;
import com.hp.ov.sdk.dto.BiosSettings;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.RefreshStateRequest;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerHardware;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;


public class ServerHardwareClientImpl implements ServerHardwareClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHardwareClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    private ServerHardwareClientImpl(HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static ServerHardwareClient getClient() {
        return new ServerHardwareClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public ServerHardware getServerHardware(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardware : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_URI, resourceId));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ServerHardwareClient : getServerHardware : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        ServerHardware serverHardwareDto = adaptor.buildResourceObject(returnObj, ServerHardware.class);

        LOGGER.trace("ServerHardwareClientImpl : getServerHardware : End");

        return serverHardwareDto;
    }

    @Override
    public ServerHardware getServerHardwareByName(final RestParams params, final String name) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareClientImpl : getServerHardwareByName : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        ServerHardware serverHardware = null;
        ResourceCollection<ServerHardware> collection = adaptor.buildResourceCollection(response,
                ServerHardware.class);

        if (collection.getCount() > 0) {
            serverHardware = collection.getMembers().get(0);
        }

        if (serverHardware == null) {
            LOGGER.error("ServerHardwareClientImpl : getServerHardwareByName : no server hardware " +
                    "found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareByName : End");

        return serverHardware;
    }

    @Override
    public ResourceCollection<ServerHardware> getAllServerHardware(final RestParams params) {
        LOGGER.trace("ServerHardwareClientImpl : getAllServerHardware : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_URI));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ServerHardwareClientImpl : getAllServerHardware : response from OV : " + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARES, null);
        }

        ResourceCollection<ServerHardware> serverHardwareCollectionDto = adaptor.buildResourceCollection(returnObj,
                ServerHardware.class);

        LOGGER.debug("ServerHardwareClientImpl : getAllServerHardware : members count : "
                + serverHardwareCollectionDto.getCount());
        LOGGER.trace("ServerHardwareClientImpl : getAllServerHardware : End");

        return serverHardwareCollectionDto;
    }

    @Override
    public TaskResourceV2 createServerHardware(RestParams params, AddServer addServerDto, final boolean aSync) {
        LOGGER.info("ServerHardwareClientImpl : createServerHardware : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (addServerDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.SERVER_HARDWARES, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(addServerDto, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("ServerHardwareClientImpl : createServerHardware : returnObj = " + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        TaskResourceV2 taskResourceV2 = adaptor.buildResourceObject(returnObj, TaskResourceV2.class);

        LOGGER.debug("ServerHardwareClientImpl : createServerHardware : taskResource =" + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerHardwareClientImpl : createServerHardware : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteServerHardware(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.trace("ServerHardwareClientImpl : deleteServerHardware : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_URI, resourceId));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ServerHardwareClient : deleteServerHardware : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        TaskResourceV2 taskResourceV2 = adaptor.buildResourceObject(returnObj, TaskResourceV2.class);

        LOGGER.debug("ServerHardwareClientImpl : deleteServerHardware : taskResource =" + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("ServerHardwareClientImpl : deleteServerHardware : End");

        return taskResourceV2;
    }

    @Override
    public BiosSettings getServerHardwareBios(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareBios : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.SERVER_HARDWARE_BIOS_URI));

        String returnObj = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareClient : getServerHardwareBios : response from OV : " + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        BiosSettings biosSettings = adaptor.buildResourceObject(returnObj, BiosSettings.class);

        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareBios : End");

        return biosSettings;
    }

    @Override
    public EnvironmentalConfiguration getServerHardwareEnvironmentConfiguration(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareEnvironmentConfiguration : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        String returnObj = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareClient : getServerHardwareEnvironmentConfiguration : response from OV : "
                + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        EnvironmentalConfiguration configuration = adaptor.buildResourceObject(returnObj,
                EnvironmentalConfiguration.class);

        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareEnvironmentConfiguration : End");

        return configuration;
    }

    @Override
    public EnvironmentalConfiguration updateServerHardwareEnvironmentConfiguration(RestParams params, String resourceId,
            EnvironmentalConfigurationUpdate environmentalConfigurationUpdate) {
        LOGGER.trace("ServerHardwareClientImpl : updateServerHardwareEnvironmentConfiguration : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (environmentalConfigurationUpdate == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.SERVER_HARDWARES, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(environmentalConfigurationUpdate, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("ServerHardwareClient : updateServerHardwareEnvironmentConfiguration : response from OV : "
                + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        EnvironmentalConfiguration configuration = adaptor.buildResourceObject(returnObj,
                EnvironmentalConfiguration.class);

        LOGGER.trace("ServerHardwareClientImpl : updateServerHardwareEnvironmentConfiguration : End");

        return configuration;
    }

    @Override
    public IloSsoUrlResult getServerHardwareIloSsoUrl(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareIloSsoUrl : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.SERVER_HARDWARE_ILO_SSO_URI));

        String returnObj = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareClient : getServerHardwareIloSsoUrl : response from OV : "
                + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        IloSsoUrlResult iloSsoUrlResult = adaptor.buildResourceObject(returnObj, IloSsoUrlResult.class);

        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareIloSsoUrl : End");

        return iloSsoUrlResult;
    }

    @Override
    public JavaRemoteConsoleUrlResult getServerHardwareJavaRemoteConsoleUrl(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareJavaRemoteConsoleUrl : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI));

        String returnObj = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareClient : getServerHardwareJavaRemoteConsoleUrl : response from OV : "
                + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        JavaRemoteConsoleUrlResult javaRemoteConsoleUrlResult = adaptor.buildResourceObject(returnObj,
                JavaRemoteConsoleUrlResult.class);

        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareJavaRemoteConsoleUrl : End");

        return javaRemoteConsoleUrlResult;
    }

    @Override
    public TaskResourceV2 updateServerHardwareMpFirmwareVersion(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("ServerHardwareClientImpl : updateServerHardwareMpFirmwareVersion : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.SERVER_HARDWARE_MP_FIRMWARE_URI));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ServerHardwareClient : updateServerHardwareMpFirmwareVersion : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        TaskResourceV2 taskResourceV2 = adaptor.buildResourceObject(returnObj, TaskResourceV2.class);

        LOGGER.debug("ServerHardwareClientImpl : updateServerHardwareMpFirmwareVersion : taskResource =" + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("ServerHardwareClientImpl : updateServerHardwareMpFirmwareVersion : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateServerHardwarePowerState(final RestParams params, final String resourceId,
            final ServerPowerControlRequest serverPowerControlRequestDto, final boolean aSync) {

        LOGGER.trace("ServerHardwareClientImpl : updateServerHardwarePowerState : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (serverPowerControlRequestDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.POWER_STATE, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_URI, resourceId,
                ResourceUris.POWER_STATE_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(serverPowerControlRequestDto, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        LOGGER.debug("ServerHardwareClientImpl : updateServerHardwarePowerState : returnObj = " + returnObj);

        TaskResourceV2 taskResourceV2 = adaptor.buildResourceObject(returnObj, TaskResourceV2.class);

        LOGGER.debug("ServerHardwareClientImpl : updateServerHardwarePowerState : taskResource = " + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerHardwareClientImpl : updateServerHardwarePowerState : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateServerHardwareRefreshState(final RestParams params, final String resourceId,
            RefreshStateRequest refreshStateRequest, final boolean aSync) {

        LOGGER.trace("ServerHardwareClientImpl : updateServerHardwareRefreshState : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (refreshStateRequest == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.POWER_STATE, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.SERVER_HARDWARE_REFRESH_STATE_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(refreshStateRequest, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        LOGGER.debug("ServerHardwareClientImpl : updateServerHardwareRefreshState : returnObj = " + returnObj);

        TaskResourceV2 taskResourceV2 = adaptor.buildResourceObject(returnObj, TaskResourceV2.class);

        LOGGER.debug("ServerHardwareClientImpl : updateServerHardwareRefreshState : taskResource = " + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerHardwareClientImpl : updateServerHardwareRefreshState : End");

        return taskResourceV2;
    }

    @Override
    public RemoteConsoleUrlResult getServerHardwareRemoteConsoleUrl(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareRemoteConsoleUrl : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.SERVER_HARDWARE_REMOTE_CONSOLE_URI));

        String returnObj = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareClient : getServerHardwareRemoteConsoleUrl : response from OV : "
                + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        RemoteConsoleUrlResult remoteConsoleUrlResult = adaptor.buildResourceObject(returnObj,
                RemoteConsoleUrlResult.class);

        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareRemoteConsoleUrl : End");

        return remoteConsoleUrlResult;
    }

    @Override
    public UtilizationData getServerHardwareUtilization(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareUtilization : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.SERVER_HARDWARE_UTILIZATION_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareClientImpl : getServerHardwareUtilization : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE, null);
        }

        UtilizationData utilizationData = adaptor.buildResourceObject(response, UtilizationData.class);

        LOGGER.trace("ServerHardwareClientImpl : getServerHardwareUtilization : End");

        return utilizationData;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        ServerHardware serverHardwareDto = getServerHardwareByName(params, name);

        if (null != serverHardwareDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(serverHardwareDto.getUri());
        }
        return resourceId;
    }

}
