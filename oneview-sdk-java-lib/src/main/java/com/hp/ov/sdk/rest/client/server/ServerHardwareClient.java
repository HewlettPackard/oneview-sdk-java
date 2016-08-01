/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.BiosSettings;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.RefreshStateRequest;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.servers.serverhardware.AddServer;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class ServerHardwareClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHardwareClient.class);
    private static final int TIMEOUT = 240000;

    private final BaseClient baseClient;

    public ServerHardwareClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link ServerHardware} details for the specified server hardware.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerHardware} object containing the details.
     */
    public ServerHardware getById(String resourceId) {
        LOGGER.info("ServerHardwareClient : getById : Start");

        ServerHardware serverHardware = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI, resourceId), ServerHardware.class);

        LOGGER.info("ServerHardwareClient : getById : End");

        return serverHardware;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerHardware}&gt; containing the details
     * for all the available server hardware found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ServerHardware}&gt; containing
     * the details for all found server hardware.
     */
    public ResourceCollection<ServerHardware> getAll() {
        LOGGER.info("ServerHardwareClient : getAll : Start");

        ResourceCollection<ServerHardware> serverHardware = baseClient.getResourceCollection(
                ResourceUris.SERVER_HARDWARE_URI, ServerHardware.class);

        LOGGER.info("ServerHardwareClient : getAll : End");

        return serverHardware;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerHardware}&gt; containing details
     * for the available server hardware found under the current HPE OneView that match the name.
     *
     * @param name server hardware name as seen in HPE OneView.
     *
     * @return {@link ServerHardware} object containing the details.
     */
    public ResourceCollection<ServerHardware> getByName(String name) {
        LOGGER.info("ServerHardwareClient : getByName : Start");

        ResourceCollection<ServerHardware> serverHardware = baseClient.getResourceCollection(
                ResourceUris.SERVER_HARDWARE_URI, ServerHardware.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("ServerHardwareClient : getByName : End");

        return serverHardware;
    }

    /**
     * Adds a server hardware according to the provided {@link AddServer} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param addServer object containing the server hardware details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 add(AddServer addServer, boolean aSync) {
        LOGGER.info("ServerHardwareClient : add : Start");

        Request request = new Request(HttpMethodType.POST, ResourceUris.SERVER_HARDWARE_URI, addServer);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerHardwareClient : add : End");

        return taskResource;
    }

    /**
     * Removes the server hardware identified by the given resource identifier.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 remove(String resourceId, boolean aSync) {
        LOGGER.info("ServerHardwareClient : remove : Start");

        Request request = new Request(HttpMethodType.DELETE,
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI, resourceId));

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerHardwareClient : remove : End");

        return taskResource;
    }

    /**
     * Requests a power operation to change the power state of the physical server.
     * 
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param serverPowerControlRequest object containing the ServerPowerControlRequest details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 updatePowerState(String resourceId,
            ServerPowerControlRequest serverPowerControlRequest, boolean aSync) {

        LOGGER.info("ServerHardwareClient : updatePowerState : Start");

        TaskResourceV2 taskResource = this.baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI, resourceId, ResourceUris.POWER_STATE_URI),
                serverPowerControlRequest, aSync);

        LOGGER.info("ServerHardwareClient : updatePowerState : End");

        return taskResource;
    }

    /**
     * Refreshes the server hardware to fix configuration issues.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param refreshStateRequest object containing the refresh state request details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 updateRefreshState(String resourceId,
            RefreshStateRequest refreshStateRequest, boolean aSync) {

        LOGGER.info("ServerHardwareClient : updateRefreshState : Start");

        String resourceUri = UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                resourceId, ResourceUris.SERVER_HARDWARE_REFRESH_STATE_URI);
        Request request = new Request(HttpMethodType.PUT, resourceUri, refreshStateRequest);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerHardwareClient : updateRefreshState : End");

        return taskResource;
    }

    /**
     * Retrieves the list of BIOS/UEFI values currently set on the physical server.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link BiosSettings} containing the collection of bios settings state details.
     */
    public BiosSettings getBios(String resourceId) {
        LOGGER.info("ServerHardwareClient : getBios : Start");

        BiosSettings biosSettings = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                        resourceId, ResourceUris.SERVER_HARDWARE_BIOS_URI),
                BiosSettings.class);

        LOGGER.info("ServerHardwareClient : getBios : End");

        return biosSettings;
    }

    /**
     * Retrieves the settings that describe the environmental configuration
     * (supported feature set, calibrated minimum and maximum power,
     * location and dimensions, ...) of the server hardware resource.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} containing the environmental configuration details.
     */
    public EnvironmentalConfiguration getEnvironmentConfiguration(String resourceId) {
        LOGGER.info("ServerHardwareClient : getEnvironmentConfiguration : Start");

        EnvironmentalConfiguration environmentalConfiguration = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                        resourceId, ResourceUris.ENVIRONMENT_CONFIGURATION_URI),
                EnvironmentalConfiguration.class);

        LOGGER.info("ServerHardwareClient : getEnvironmentConfiguration : End");

        return environmentalConfiguration;
    }

    /**
     * Updates the calibrated max power of an unmanaged or unsupported server hardware resource.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param environmentalConfigurationUpdate details of the environmental configuration.
     *
     * @return {@link EnvironmentalConfiguration} containing the environmental configuration details.
     */
    public EnvironmentalConfiguration updateEnvironmentConfiguration(String resourceId,
            EnvironmentalConfigurationUpdate environmentalConfigurationUpdate) {

        LOGGER.info("ServerHardwareClient : updateEnvironmentConfiguration : Start");

        String requestUri = UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                resourceId, ResourceUris.ENVIRONMENT_CONFIGURATION_URI);
        Request request = new Request(HttpMethodType.PUT, requestUri, environmentalConfigurationUpdate);

        EnvironmentalConfiguration environmentalConfiguration = this.baseClient.executeRequest(request,
                EnvironmentalConfiguration.class);

        LOGGER.info("ServerHardwareClient : updateEnvironmentConfiguration : End");

        return environmentalConfiguration;
    }

    /**
     * Updates the iLO firmware on a physical server to a minimum iLO  firmware version
     * required by HPE OneView to manage the server.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param aSync flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 updateMpFirmwareVersion(String resourceId, boolean aSync) {
        LOGGER.info("ServerHardwareClient : updateMpFirmwareVersion : Start");

        String requestUri = UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                resourceId, ResourceUris.SERVER_HARDWARE_MP_FIRMWARE_URI);
        Request request = new Request(HttpMethodType.PUT, requestUri);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerHardwareClient : updateMpFirmwareVersion : End");

        return taskResource;
    }

    /**
     * Retrieves the URL to launch a Single Sign-On (SSO) session for the iLO web interface.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link IloSsoUrlResult} containing the iLO SSO URL details.
     */
    public IloSsoUrlResult getIloSsoUrl(String resourceId) {
        LOGGER.info("ServerHardwareClient : getIloSsoUrl : Start");

        IloSsoUrlResult iloSsoUrlResult = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                        resourceId, ResourceUris.SERVER_HARDWARE_ILO_SSO_URI),
                IloSsoUrlResult.class);

        LOGGER.info("ServerHardwareClient : getIloSsoUrl : End");

        return iloSsoUrlResult;
    }

    /**
     * This module aids in generating a Single Sign-On (SSO) session for the iLO
     * Java Applet console and returns the URL to launch it. If the server
     * hardware is unmanaged or unsupported, the resulting URL will not use SSO
     * and the iLO Java Applet will prompt for credentials.
     * <p>
     * Note: this is not supported on G7/iLO3 or earlier servers.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link JavaRemoteConsoleUrlResult} containing the Java remote console URL details.
     */
    public JavaRemoteConsoleUrlResult getJavaRemoteConsoleUrl(String resourceId) {
        LOGGER.info("ServerHardwareClient : getJavaRemoteConsoleUrl : Start");

        JavaRemoteConsoleUrlResult javaRemoteConsoleUrlResult = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                        resourceId, ResourceUris.SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI),
                JavaRemoteConsoleUrlResult.class);

        LOGGER.info("ServerHardwareClient : getJavaRemoteConsoleUrl : End");

        return javaRemoteConsoleUrlResult;
    }

     /**
     * Generates a Single Sign-On (SSO) session for the iLO Integrated Remote
     * Console Application (IRC) and returns the URL to launch it. If the server
     * hardware is unmanaged or unsupported, the resulting URL will not use SSO
     * and the IRC application will prompt for credentials. Use of this URL
     * requires a previous installation of the iLO IRC and is supported only
     * on Windows client.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link RemoteConsoleUrlResult} containing the remote console URL details.
     */
    public RemoteConsoleUrlResult getRemoteConsoleUrl(String resourceId) {
        LOGGER.info("ServerHardwareClient : getRemoteConsoleUrl : Start");

        RemoteConsoleUrlResult remoteConsoleUrlResult = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                        resourceId, ResourceUris.SERVER_HARDWARE_REMOTE_CONSOLE_URI),
                RemoteConsoleUrlResult.class);

        LOGGER.info("ServerHardwareClient : getRemoteConsoleUrl : End");

        return remoteConsoleUrlResult;
    }

    /**
     * Retrieves the {@link UtilizationData} details for the specified server hardware.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link UtilizationData} object containing the details.
     */
    public UtilizationData getUtilization(String resourceId) {
        LOGGER.info("ServerHardwareClient : getUtilization : Start");

        UtilizationData utilizationData = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_URI,
                        resourceId, ResourceUris.SERVER_HARDWARE_UTILIZATION_URI),
                UtilizationData.class);

        LOGGER.info("ServerHardwareClient : getUtilization : End");

        return utilizationData;
    }

}
