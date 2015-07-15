/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.List;

import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ServerProfileClient
{

    /**
     * The module aids in fetching the ServerProfile details for the specified
     * ServerProfile resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for ServerProfile as seen in HP OneView.
     * @return serverProfileDto, which is a object containing the ServerProfile
     *         details.
     */
    public ServerProfile getServerProfile(final RestParams params,
            final String resourceId);

    /**
     * The module aids in fetching the ServerProfileCollection details for all
     * the ServerProfileCollection found under the current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return serverProfileCollectionDto, which is a object containing a
     *         collection of ServerProfile details.
     */
    public ServerProfileCollection getAllServerProfile(final RestParams params);

    /**
     * The module aids in fetching the ServerProfile details for the
     * ServerProfile name as specified in HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param name
     *        The resourceName is the ServerProfile name as seen in HP
     *        OneView.
     * @return serverProfileDto, which is a object containing the ServerProfile
     *         details.
     */
    public ServerProfile getServerProfileByName(final RestParams params,
            final String name);

    /**
     * The module aids in retrieving the list of Ethernet networks, Fibre
     * Channel networks and network sets that are available to a server profile
     * along with their respective ports for the specified enclosureGroupUri and
     * serverHardwareTypeUri
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param serverHardwareTypeUri
     *        String, specifying the Server Hardware Type URI
     * @param enclosureGroupUri
     *        String, specifying the Enclosure Group URI
     * @return availableNetworksDto, which is a object containing the
     *         AvailableNetworks details.
     */
    public AvailableNetworks getAvailableNetworksForServerProfile(
            final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri);

    /**
     * Retrieves a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return availableServersCollectionDto, which is a object containing a
     *         collection of AvailableServers details.
     */
    public List<AvailableServers> getAvailableServersForServerProfile(
            final RestParams params);

    /**
     * Retrieves a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays for specified serverHardwareTypeUri and
     * enclosureGroupUri
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param serverHardwareTypeUri
     *        String, specifying the Server Hardware Type URI.
     * @param enclosureGroupUri
     *        String, specifying the Enclosure Group URI.
     * @return availableServersCollectionDto, which is a object containing a
     *         collection of AvailableServers details.
     */
    public List<AvailableServers> getAvailableServersForServerProfile(
            final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri);

    /**
     * Retrieves a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays for given profileUri
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param profileUri
     *        String, specifying the Server Profile URI.
     * @return availableServersCollectionDto, which is a object containing a
     *         collection of AvailableServers details
     */
    public List<AvailableServers> getAvailableServersForServerProfile(
            final RestParams params, final String profileUri);

    /**
     * Retrieves the port model associated server hardware type and enclosure
     * group.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param serverHardwareTypeId
     *        String, specifying the Server Hardware Type Id.
     * @param enclosureGroupId
     *        String, specifying the Enclosure Group Id.
     * @return profilePortsDto, which is a object containing the ProfilePorts
     *         details.
     */
    public ProfilePorts getProfilePortsForServerProfile(
            final RestParams params, final String serverHardwareTypeId,
            final String enclosureGroupId);

    /**
     * The module aids in creation of ServerProfile when provided with the
     * ServerProfile details as ServerProfile object or JsonRequest. It can
     * process the request asynchronously or synchronously based on flag input.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param serverProfileDto
     *        This is a object containing the ServerProfile details, used to
     *        create a ServerProfile.
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *        The JsonRequest body is part of ServerProfile Object which
     *        takes in a String containing the new ServerProfile details,
     *        which is converted to ServerProfile Object using adaptor and
     *        processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createServerProfile(final RestParams params,
            final ServerProfile serverProfileDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an ServerProfile object or JsonRequest and updates
     * the existing ServerProfile based on resource Id. It can process the
     * request asynchronously or synchronously based on flag input.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for ServerProfile as seen in HP OneView.
     * @param serverProfileDto
     *        This is a object containing the ServerProfile details, used to
     *        update a ServerProfile.
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *        The JsonRequest body is part of ServerProfile Object which
     *        takes in a String containing the update to be made, which is
     *        converted to ServerProfile Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateServerProfile(final RestParams params,
            final String resourceId, final ServerProfile serverProfileDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a ServerProfile for the specified
     * ServerProfile resourceId. It can process the request asynchronously or
     * synchronously based on flag input.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for ServerProfile as seen in HP OneView.
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteServerProfile(final RestParams params,
            final String resourceId, final boolean aSync);

    /**
     * This module aids in deleting all Server Profile objects from the
     * appliance that match the provided filter
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param filter
     *        The filter is the ServerProfile name as seen in HP OneView.
     * @param match
     *        Flag input to process filter with %filter% or just filter
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteServerProfileByFilter(final RestParams params,
            final String filter, final Boolean match, final boolean aSync);

    /**
     * This module aids in creating a new server profile by using an existing
     * server profile/template.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param sourceName
     *        String, specifying the name of Server Profile template.
     * @param destinationBay
     *        String, specifying the name of destination bay.
     * @param profileName
     *        String, specifying the name of new Server Profile to be
     *        created.
     * @param aSync
     *        Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 copyServerProfile(final RestParams params,
            final String sourceName, final String destinationBay,
            final String profileName, final boolean aSync);

    // TODO - implement the remaining update methods and GetByName method

}
