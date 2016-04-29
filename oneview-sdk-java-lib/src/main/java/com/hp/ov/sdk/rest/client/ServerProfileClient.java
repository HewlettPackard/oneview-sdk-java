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

import java.util.List;

import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableStorageSystems;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.ServerProfileHealth;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ServerProfileClient {

    /**
     * The module aids in fetching the server profile details for the specified
     * server profile resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile as seen in HPE OneView.
     * @return {@link ServerProfile} containing the server profile details.
     */
     ServerProfile getServerProfile(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the server profile details for all
     * the server profiles found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ServerProfileCollection} containing a collection of server profile details.
     */
     ServerProfileCollection getAllServerProfile(final RestParams params);

    /**
     * The module aids in fetching the server profile details for the
     * server profile name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The resourceName is the server profile name as seen in HPE OneView.
     * @return {@link ServerProfile} containing the server profile details.
     */
     ServerProfile getServerProfileByName(final RestParams params, final String name);

    /**
     * Returns the preview of manual and automatic updates required to make the
     * server profile consistent with its template.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile as seen in HPE OneView.
     * @return {@link ServerProfileCompliancePreview} containing the server profile
     *         compliance preview details.
     */
    ServerProfileCompliancePreview getServerProfileCompliancePreview(final RestParams params, final String resourceId);

    /**
     * Returns the error or status messages associated with the specified server profile.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile as seen in HPE OneView.
     * @return {@link ServerProfileHealth} containing the server profile health details.
     */
    ServerProfileHealth getServerProfileMessages(final RestParams params, final String resourceId);

    /**
     * Transforms an existing profile by supplying a new server hardware type and/or
     * enclosure group. A profile will be returned with a new configuration based on
     * the capabilities of the supplied server hardware type and/or enclosure group.
     * All deployed connections will have their port assignment set to 'Auto'. Re-selection
     * of the server hardware may also be required. The new profile can subsequently be
     * used for the PUT https://{appl}/rest/server-profiles/{id} API but is not guaranteed
     * to pass validation. Any incompatibilities will be flagged when the transformed server
     * profile is submitted.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile as seen in HPE OneView.
     * @param serverHardwareTypeUri
     *            String, specifying the server hardware type URI.
     * @param enclosureGroupUri
     *            String, specifying the enclosure group URI.
     * @return {@link ServerProfile} containing the server profile details.
     */
    ServerProfile getServerProfileTransformation(final RestParams params, final String resourceId,
            final String serverHardwareTypeUri, final String enclosureGroupUri);

    /**
     * The module aids in retrieving the list of ethernet networks, fibre
     * channel networks and network sets that are available to a server profile
     * along with their respective ports for the specified enclosure group URI and
     * server hardware type URI.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param serverHardwareTypeUri
     *            String, specifying the server hardware type URI.
     * @param enclosureGroupUri
     *            String, specifying the enclosure group URI.
     * @return {@link AvailableNetworks} containing the AvailableNetworks details.
     */
    AvailableNetworks getAvailableNetworksForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri);

    /**
     * Returns a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link List} of {@link AvailableServers} containing a collection of available servers details.
     */
    List<AvailableServers> getAvailableServersForServerProfile(final RestParams params);

    /**
     * Returns a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays for specified server hardware type URI and
     * enclosure group URI.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param serverHardwareTypeUri
     *            String, specifying the server hardware type URI.
     * @param enclosureGroupUri
     *            String, specifying the enclosure group URI.
     * @return {@link List} of {@link AvailableServers} containing a collection of available servers details.
     */
    List<AvailableServers> getAvailableServersForServerProfile(final RestParams params, final String serverHardwareTypeUri,
            final String enclosureGroupUri);

    /**
     * Returns a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays for given profile URI.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param profileUri
     *            String, specifying the server profile URI.
     * @return {@link List} of {@link AvailableServers} containing a collection of available servers details.
     */
    List<AvailableServers> getAvailableServersForServerProfile(final RestParams params, final String profileUri);

    /**
     * Returns a specific storage system and its associated volumes that are
     * available to the server profile based on the given server hardware type and
     * enclosure group.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param enclosureGroupUri
     *            String, specifying the enclosure group URI.
     * @param serverHardwareTypeUri
     *            String, specifying the server hardware type URI.
     * @param storageSystemId
     *            String, specifying the storage system identifier.
     * @return {@link AvailableStorageSystem} containing the storage system details.
     */
    AvailableStorageSystem getAvailableStorageSystemForServerProfile(final RestParams params, final String enclosureGroupUri,
            final String serverHardwareTypeUri, final String storageSystemId);

    /**
     * Returns the list of the storage systems and their associated volumes that
     * are available to the server profile based on the given server hardware type and
     * enclosure group.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param enclosureGroupUri
     *            String, specifying the enclosure group URI.
     * @param serverHardwareTypeUri
     *            String, specifying the server hardware type URI.
     * @return {@link AvailableStorageSystems} containing a collection of storage system details.
     */
    AvailableStorageSystems getAvailableStorageSystemsForServerProfile(final RestParams params, final String enclosureGroupUri,
            final String serverHardwareTypeUri);

    /**
     * Returns a list of the target servers and empty device bays that are available
     * for assignment to the server profile. This replaces the
     * /rest/server-profiles/available-servers API.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link AvailableTargets} containing the available target details.
     */
    AvailableTargets getAvailableTargetsForServerProfile(final RestParams params);

    /**
     * Returns a list of the target servers and empty device bays that are available
     * for assignment to the server profile. This replaces the
     * /rest/server-profiles/available-servers API.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param enclosureGroupUri
     *            String, specifying the enclosure group URI.
     * @param serverHardwareTypeUri
     *            String, specifying the server hardware type URI.
     * @param profileUri
     *            String, specifying the server profile URI.
     * @return {@link AvailableTargets} containing the available target details.
     */
    AvailableTargets getAvailableTargetsForServerProfile(final RestParams params, final String enclosureGroupUri,
            final String serverHardwareTypeUri, final String profileUri);

    /**
     * Returns the port model associated server hardware type and enclosure group.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param serverHardwareTypeId
     *            String, specifying the server hardware type identifier.
     * @param enclosureGroupId
     *            String, specifying the enclosure group identifier.
     * @return {@link ProfilePorts} containing the profile ports details.
     */
    ProfilePorts getProfilePortsForServerProfile(final RestParams params, final String serverHardwareTypeId,
            final String enclosureGroupId);

    /**
     * The module aids in the creation of a server profile when provided with the
     * server profile details as a ServerProfile object or JsonRequest. It can
     * process the request asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param serverProfileDto
     *            Object containing the server profile details, used to create a server profile.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of ServerProfile object which
     *            takes in a String containing the new server profile details,
     *            which is converted to ServerProfile object using an adaptor and
     *            processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createServerProfile(final RestParams params, final ServerProfile serverProfileDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in a ServerProfile object or JsonRequest and updates
     * the existing server profile based on the resource identifier. It can process the
     * request asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile as seen in HPE OneView.
     * @param serverProfileDto
     *            Object containing the server profile details, used to update a server profile.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of ServerProfile object which
     *            takes in a String containing the update to be made, which is
     *            converted to ServerProfile object using an adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateServerProfile(final RestParams params, final String resourceId,
            final ServerProfile serverProfileDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * Use the PATCH REST API to update the server profile.
     *
     * Ex:
     * To update the server profile from the server profile template:
     * Operation: replace
     * Path: /templateCompliance
     * Value: Compliant
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile as seen in HPE OneView.
     * @param patchDto
     *            Object containing the patch details, used to update a server profile.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 patchServerProfile(final RestParams params, final String resourceId, final Patch patchDto, final boolean aSync);

    /**
     * The module aids in deleting a server profile for the specified
     * server profile resource identifier. It can process the request asynchronously or
     * synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server profile as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteServerProfile(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This module aids in deleting all server profile objects from the
     * appliance that match the provided filter.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param filter
     *            The filter is the server profile name as seen in HPE OneView.
     * @param match
     *            Flag input to process filter with %filter% or just filter.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteServerProfileByFilter(final RestParams params, final String filter, final Boolean match,
            final boolean aSync);

    /**
     * The module aids in fetching the server profile resource identifier for the server profile
     * name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the server profile name as seen in HPE OneView.
     * @return String which is the resource identifier for the server profile name as seen
     *         in HPE OneView.
     */
    String getId(final RestParams params, final String name);

}
