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

import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.servers.serverhardware.AddServer;
import com.hp.ov.sdk.dto.servers.serverhardware.BiosSettings;
import com.hp.ov.sdk.dto.servers.serverhardware.IloSsoUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.RefreshStateRequest;
import com.hp.ov.sdk.dto.servers.serverhardware.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerFirmwareInventory;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerPowerControlRequest;
import com.hp.ov.sdk.rest.client.common.AddableResource;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.RemovableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hp.ov.sdk.rest.reflect.QueryParam;

@Api(ServerHardwareClient.SERVER_HARDWARE_URI)
public interface ServerHardwareClient extends
        AddableResource<AddServer>,
        SearchableResource<ServerHardware>,
        RemovableResource,
        PatchableResource {

    String SERVER_HARDWARE_URI = "/rest/server-hardware";

    String SERVER_HARDWARE_REFRESH_STATE_URI = "/refreshState";
    String SERVER_HARDWARE_FIRMWARE_URI = "/firmware";
    String SERVER_HARDWARE_FIRMWARE = "/*/firmware";
    String POWER_STATE_URI = "/powerState";
    String SERVER_HARDWARE_BIOS_URI = "/bios";
    String ENVIRONMENT_CONFIGURATION_URI = "/environmentalConfiguration";
    String SERVER_HARDWARE_MP_FIRMWARE_URI = "/mpFirmwareVersion";
    String SERVER_HARDWARE_ILO_SSO_URI = "/iloSsoUrl";
    String SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI = "/javaRemoteConsoleUrl";
    String SERVER_HARDWARE_REMOTE_CONSOLE_URI = "/remoteConsoleUrl";
    String SERVER_HARDWARE_UTILIZATION_URI = "/utilization";

    /**
     * Requests a power operation to change the power state of the physical server.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param serverPowerControlRequest object containing the ServerPowerControlRequest details.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} which returns the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_STATE_URI, method = HttpMethod.PUT)
    TaskResource updatePowerState(@PathParam("resourceId") String resourceId,
            @BodyParam ServerPowerControlRequest serverPowerControlRequest,
            RequestOption ... options);

    /**
     * Refreshes the server hardware to fix configuration issues.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param refreshStateRequest object containing the refresh state request details.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} which returns the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_REFRESH_STATE_URI, method = HttpMethod.PUT)
    TaskResource updateRefreshState(@PathParam("resourceId") String resourceId,
            @BodyParam RefreshStateRequest refreshStateRequest,
            RequestOption ... options);

    /**
     * Retrieves the list of BIOS/UEFI values currently set on the physical server.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link BiosSettings} containing the collection of bios settings state details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_BIOS_URI)
    BiosSettings getBios(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the settings that describe the environmental configuration
     * (supported feature set, calibrated minimum and maximum power,
     * location and dimensions, ...) of the server hardware resource.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} containing the environmental configuration details.
     */
    @Endpoint(uri = "/{resourceId}" + ENVIRONMENT_CONFIGURATION_URI)
    EnvironmentalConfiguration getEnvironmentConfiguration(@PathParam("resourceId") String resourceId);

    /**
     * Updates the calibrated max power of an unmanaged or unsupported server hardware resource.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param environmentalConfigurationUpdate details of the environmental configuration.
     *
     * @return {@link EnvironmentalConfiguration} containing the environmental configuration details.
     */
    @Endpoint(uri = "/{resourceId}" + ENVIRONMENT_CONFIGURATION_URI, method = HttpMethod.PUT)
    EnvironmentalConfiguration updateEnvironmentConfiguration(@PathParam("resourceId") String resourceId,
            @BodyParam EnvironmentalConfigurationUpdate environmentalConfigurationUpdate);

    /**
     * Updates the iLO firmware on a physical server to a minimum iLO  firmware version
     * required by HPE OneView to manage the server.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     * @return {@link TaskResource} which returns the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_MP_FIRMWARE_URI, method = HttpMethod.PUT)
    TaskResource updateMpFirmwareVersion(@PathParam("resourceId") String resourceId,
            RequestOption ... options);

    /**
     * Retrieves the URL to launch a Single Sign-On (SSO) session for the iLO web interface.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link IloSsoUrlResult} containing the iLO SSO URL details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_ILO_SSO_URI)
    IloSsoUrlResult getIloSsoUrl(@PathParam("resourceId") String resourceId);

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
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI)
    JavaRemoteConsoleUrlResult getJavaRemoteConsoleUrl(@PathParam("resourceId") String resourceId);

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
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_REMOTE_CONSOLE_URI)
    RemoteConsoleUrlResult getRemoteConsoleUrl(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the {@link UtilizationData} details for the specified server hardware.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link UtilizationData} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_UTILIZATION_URI)
    UtilizationData getUtilization(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the {@link ServerFirmwareInventory} details for the specified server hardware.
     *
     * @param filter used specify some filters that will be applied to narrow the results.
     *
     * @return {@link ResourceCollection}&lt;{@link ServerFirmwareInventory}&gt; containing
     * the details for all found server hardware.
     */
    @Endpoint(uri = SERVER_HARDWARE_FIRMWARE)
    ResourceCollection<ServerFirmwareInventory> getServerFirmwareInventoryByFilter(
            @QueryParam() FirmwareInventoryFilter filter);

    /**
     * Retrieves the {@link ServerFirmwareInventory} details for the specified server hardware.
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerFirmwareInventory} object containing the details.
     *
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_HARDWARE_FIRMWARE_URI)
    ServerFirmwareInventory getServerFirmwareInventory(@PathParam("resourceId") String resourceId);

}
