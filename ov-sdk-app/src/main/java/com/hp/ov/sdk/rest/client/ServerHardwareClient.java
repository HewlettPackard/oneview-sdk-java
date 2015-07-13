/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ServerHardware;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ServerHardwareClient
{

    /**
     * The module aids in fetching the ServerHardware details for the specified
     * ServerHardware resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for ServerHardware as seen in HP OneView.
     * @return serverHardwareDto, which is a object containing the
     *         ServerHardware details.
     */
    public ServerHardware getServerHardware(final RestParams params,
            final String resourceId);

    /**
     * The module aids in fetching the ServerHardware details for all the
     * ServerHardware found under the current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return serverHardwareCollectionDto, which is a object containing a
     *         collection of ServerHardware details.
     */
    public ServerHardwareCollection getAllServerHardwares(
            final RestParams params);

    /**
     * The module aids in fetching the ServerHardware details for all the
     * ServerHardware found with no server profile under the current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return serverHardwareCollectionDto, which is a object containing a
     *         collection of ServerHardware details.
     */
    public ServerHardwareCollection getServerHardwareWithNoProfile(
            final RestParams params);

    /**
     * The module aids in fetching the ServerHardware details for the
     * ServerHardware name as specified in HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param destinationBay
     *        The destinationBay is the ServerHardware name as seen in HP
     *        OneView.
     * @return serverHardwareDto, which is a object containing the
     *         ServerHardware details.
     */
    public ServerHardware getServerHardwareByName(final RestParams params,
            final String destinationBay);

    /**
     * Requests a power operation to change the power state of the physical
     * server.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for ServerHardware as seen in HP OneView.
     * @param serverPowerControlRequestDto
     *        This is a object containing the ServerPowerControlRequest
     *        details, used to power off server.
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *        The JsonRequest body is part of ServerPowerControlRequest
     *        Object which takes in a String containing the update to be
     *        made, which is converted to ServerPowerControlRequest Object
     *        using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 powerServer(final RestParams params,
            final String resourceId,
            final ServerPowerControlRequest serverPowerControlRequestDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * Fetches the server state of the server for the specified ServerHardware
     * resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for ServerHardware as seen in HP OneView.
     * @return String, specifying the On or Off state of server
     */
    public String getPowerState(final RestParams params, final String resourceId);
    
 // TODO - implement the remaining update methods and GetByName method
}
