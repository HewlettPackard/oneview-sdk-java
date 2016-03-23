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

import com.hp.ov.sdk.dto.AddServer;
import com.hp.ov.sdk.dto.BiosSettings;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.RefreshStateRequest;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.ServerHardware;
import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ServerHardwareClient {

    /**
     * The module aids in fetching the ServerHardware details for the specified
     * ServerHardware resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return {@link ServerHardware} which is a object containing the
     *         ServerHardware details.
     */
    ServerHardware getServerHardware(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the ServerHardware details for all the
     * ServerHardware found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @return {@link ServerHardwareCollection}, which is a object containing a
     *         collection of ServerHardware details.
     */
    ServerHardwareCollection getAllServerHardware(final RestParams params);

    /**
     * The module aids in fetching the ServerHardware details for the
     * ServerHardware name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param destinationBay
     *            The destinationBay is the ServerHardware name as seen in HP
     *            OneView.
     * @return {@link ServerHardware} which is a object containing the
     *         ServerHardware details.
     */
    ServerHardware getServerHardwareByName(final RestParams params, final String destinationBay);

    /**
     * Requests a power operation to change the power state of the physical
     * server.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @param serverPowerControlRequestDto
     *            This is a object containing the ServerPowerControlRequest details
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} which returns the task status for the process
     */
    TaskResourceV2 updateServerHardwarePowerState(final RestParams params, final String resourceId,
            final ServerPowerControlRequest serverPowerControlRequestDto, final boolean aSync);

    /**
     * Refreshes the server hardware to fix configuration issues.
     *
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @param refreshStateRequest
     *            This is a object containing the RefreshStateRequest details.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} which returns the task status for the process
     */
    TaskResourceV2 updateServerHardwareRefreshState(final RestParams params, final String resourceId,
            final RefreshStateRequest refreshStateRequest, final boolean aSync);

    /**
     * This module aids in adding a rack-mount server for management by the
     * appliance
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param addServerDto
     *            Details of the server hardware.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} which returns the task status for the process
     */
    TaskResourceV2 createServerHardware(final RestParams params, final AddServer addServerDto,
            final boolean aSync);

    /**
     * The module aids in deleting a Server Hardware for the specified Server Hardware
     * resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for Server Hardware as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} which returns the task status for the process
     */
    TaskResourceV2 deleteServerHardware(final RestParams params, final String resourceId, final boolean aSync);
 
    /**
     * This module aids in fetching the list of BIOS/UEFI values currently set
     * on the physical server.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return {@link BiosSettings}, which is a object containing the
     *         collection of biosSettingsState details.
     */
    BiosSettings getServerHardwareBios(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the settings that describe the environmental
     * configuration (supported feature set, calibrated minimum & maximum power,
     * location & dimensions, ...) of the server hardware resource.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return {@link EnvironmentalConfiguration}, which is a object containing the
     *         environmentalConfiguration details.
     */
    EnvironmentalConfiguration getServerHardwareEnvironmentConfiguration(final RestParams params,
            final String resourceId);

    /**
     * This module aids in updating/setting the calibrated max power of an
     * unmanaged or unsupported server hardware resource.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @param environmentalConfigurationUpdateDto
     *            Details of the environmental configuration.
     * @return {@link EnvironmentalConfiguration}, which is a object containing the
     *         environmentalConfiguration details.
     */
    EnvironmentalConfiguration updateServerHardwareEnvironmentConfiguration(final RestParams params,
            final String resourceId, final EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto);

    /**
     * This module fetches the URL to launch a Single Sign-On (SSO) session for
     * the iLO web interface
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return {@link IloSsoUrlResult}, which is a object containing the iloSsoUrlResult
     *         details.
     */
    IloSsoUrlResult getServerHardwareIloSsoUrl(final RestParams params, final String resourceId);

    /**
     * This module aids in generating a Single Sign-On (SSO) session for the iLO
     * Java Applet console and returns the URL to launch it. If the server
     * hardware is unmanaged or unsupported, the resulting URL will not use SSO
     * and the iLO Java Applet will prompt for credentials. Note, this is not
     * supported on G7/iLO3 or earlier servers.
     *
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return {@link JavaRemoteConsoleUrlResult}, which is a object containing the
     *         javaRemoteConsoleUrlResult details.
     */
    JavaRemoteConsoleUrlResult getServerHardwareJavaRemoteConsoleUrl(final RestParams params, final String resourceId);

    /**
     * Updates the iLO firmware on a physical server to a minimum ILO
     * firmware version required by OneView to manage the server.
     *
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} which returns the task status for the process
     */
    TaskResourceV2 updateServerHardwareMpFirmwareVersion(final RestParams params, final String resourceId, boolean aSync);

    /**
     * Generates a Single Sign-On (SSO) session for the iLO Integrated Remote
     * Console Application (IRC) and returns the URL to launch it. If the server
     * hardware is unmanaged or unsupported, the resulting URL will not use SSO
     * and the IRC application will prompt for credentials. Use of this URL
     * requires a previous installation of the iLO IRC and is supported only
     * on Windows client.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return {@link RemoteConsoleUrlResult}, which is a object containing the
     *         remoteConsoleUrlResult details.
     */
    RemoteConsoleUrlResult getServerHardwareRemoteConsoleUrl(final RestParams params, final String resourceId);

    //TODO filters are not supported yet
    /**
     * This module aids in retrieving historical utilization data for the
     * specified resource, metrics, and time span.
     *
     * <b>Filters are not supported yet.</b>
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return {@link UtilizationData}, which is a object containing the utilizationData
     *         details.
     */
    UtilizationData getServerHardwareUtilization(RestParams params, String resourceId);

    /**
     * The module aids in fetching the ServerHardware details for the
     * ServerHardware name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param name
     *            The resourceName is the ServerHardware name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the ServerHardware name as
     *         seen in HPOneView.
     */
    String getId(final RestParams params, final String name);

}
