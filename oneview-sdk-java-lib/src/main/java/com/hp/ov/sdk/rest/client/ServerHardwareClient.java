/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.AddServer;
import com.hp.ov.sdk.dto.BiosSettingsStateCollection;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.ServerHardware;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ServerHardwareClient {

    /**
     * The module aids in fetching the ServerHardware details for the specified
     * ServerHardware resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return serverHardwareDto, which is a object containing the
     *         ServerHardware details.
     */
    public ServerHardware getServerHardware(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the ServerHardware details for all the
     * ServerHardware found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return serverHardwareCollectionDto, which is a object containing a
     *         collection of ServerHardware details.
     */
    public ServerHardwareCollection getAllServerHardwares(final RestParams params);

    /**
     * The module aids in fetching the ServerHardware details for all the
     * ServerHardware found with no server profile under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return serverHardwareCollectionDto, which is a object containing a
     *         collection of ServerHardware details.
     */
    public ServerHardwareCollection getServerHardwareWithNoProfile(final RestParams params);

    /**
     * The module aids in fetching the ServerHardware details for the
     * ServerHardware name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param destinationBay
     *            The destinationBay is the ServerHardware name as seen in HP
     *            OneView.
     * @return serverHardwareDto, which is a object containing the
     *         ServerHardware details.
     */
    public ServerHardware getServerHardwareByName(final RestParams params, final String destinationBay);

    /**
     * Requests a power operation to change the power state of the physical
     * server.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @param serverPowerControlRequestDto
     *            This is a object containing the ServerPowerControlRequest
     *            details, used to power off server.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of ServerPowerControlRequest
     *            Object which takes in a String containing the update to be
     *            made, which is converted to ServerPowerControlRequest Object
     *            using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 powerServer(final RestParams params, final String resourceId,
            final ServerPowerControlRequest serverPowerControlRequestDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * Fetches the server state of the server for the specified ServerHardware
     * resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return String, specifying the On or Off state of server
     */
    public String getPowerState(final RestParams params, final String resourceId);

    /**
     * This module aids in adding a rack-mount server for management by the
     * appliance
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param addServer
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createServerHardware(final RestParams params, final AddServer addServerDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * This module aids in fetching the list of BIOS/UEFI values currently set
     * on the physical server.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return biosSettingsStateCollection, which is a object containing the
     *         collection of biosSettingsState details.
     */
    public BiosSettingsStateCollection getBiosForServerHardware(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the settings that describe the environmental
     * configuration (supported feature set, calibrated minimum & maximum power,
     * location & dimensions, ...) of the server hardware resource.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return environmentalConfiguration, which is a object containing the
     *         environmentalConfiguration details.
     */
    public EnvironmentalConfiguration getEnvironmentConfigurationForServerHardware(final RestParams params, final String resourceId);

    /**
     * THis module aids in updating/setting the calibrated max power of an
     * unmanaged or unsupported server hardware resource.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @param environmentalConfigurationUpdateDto
     * @return environmentalConfiguration, which is a object containing the
     *         environmentalConfiguration details.
     */
    public EnvironmentalConfiguration updateEnvironmentConfigurationForServerHardware(final RestParams params,
            final String resourceId, final EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto);

    /**
     * THis module fetches the URL to launch a Single Sign-On (SSO) session for
     * the iLO web interface
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return iloSsoUrlResult, which is a object containing the iloSsoUrlResult
     *         details.
     */
    public IloSsoUrlResult getIloSsoUrlForServerHardware(final RestParams params, final String resourceId);

    /**
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return javaRemoteConsoleUrlResult, which is a object containing the
     *         javaRemoteConsoleUrlResult details.
     */
    public JavaRemoteConsoleUrlResult getJavaRemoteConsoleUrlForServerHardware(final RestParams params, final String resourceId);

    /**
     * This module aids in generating a Single Sign-On (SSO) session for the iLO
     * Java Applet console and returns the URL to launch it. If the server
     * hardware is unmanaged or unsupported, the resulting URL will not use SSO
     * and the iLO Java Applet will prompt for credentials. Note, this is not
     * supported on G7/iLO3 or earlier servers.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateMpFirmwareVersionForServerHardware(final RestParams params, final String resourceId);

    /**
     * This module aids in the generating a Single Sign-On (SSO) session for the
     * iLO Integrated Remote Console Application (IRC) and returns the URL to
     * launch it
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return remoteConsoleUrlResult, which is a object containing the
     *         remoteConsoleUrlResult details.
     */
    public RemoteConsoleUrlResult getRemoteConsoleUrlForServerHardware(final RestParams params, final String resourceId);

    /**
     * This module aids in retrieving historical utilization data for the
     * specified resource, metrics, and time span.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for ServerHardware as seen in HP OneView.
     * @return utilizationData, which is a object containing the utilizationData
     *         details.
     */
    public UtilizationData getUtilizationForServerHardware(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the ServerHardware details for the
     * ServerHardware name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the ServerHardware name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the ServerHardware name as
     *         seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
