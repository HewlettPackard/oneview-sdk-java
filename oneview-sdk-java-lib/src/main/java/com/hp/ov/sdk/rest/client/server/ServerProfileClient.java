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

import java.util.List;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableNetworks;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableServers;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableStorageSystem;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableTargets;
import com.hp.ov.sdk.dto.servers.serverprofile.ProfilePorts;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfileHealth;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(ServerProfileClient.SERVER_PROFILE_URI)
public interface ServerProfileClient extends
        SearchableResource<ServerProfile>,
        CreatableResource<ServerProfile>,
        DeletableResource,
        UpdatableResource<ServerProfile>,
        PatchableResource {

    String SERVER_PROFILE_URI = "/rest/server-profiles";
    String SERVER_PROFILE_AVAILABLE_NETWORKS_URI = "/available-networks";
    String SERVER_PROFILE_AVAILABLE_SERVERS_URI = "/available-servers";
    String SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEM_URI = "/available-storage-system";
    String SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEMS_URI = SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEM_URI + "s";
    String SERVER_PROFILE_AVAILABLE_TARGETS_URI = "/available-targets";
    String SERVER_PROFILE_COMPLIANCE_MESSAGES_URI = "/messages";
    String SERVER_PROFILE_COMPLIANCE_PREVIEW_URI = "/compliance-preview";
    String SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI = "/transformation";
    String SERVER_PROFILE_PROFILE_PORTS_URI = "/profile-ports";

    /**
     * Removes the {@link ServerProfile}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param options varargs of {@link RequestOption} which can be used to specify
+    *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "?filter={filter}", method = HttpMethod.DELETE)
    TaskResource deleteByFilter(@PathParam("filter") String filter, RequestOption... options);

    /**
     * Retrieves a preview of manual and automatic updates required to make the
     * server profile consistent with its template.
     *
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfileCompliancePreview} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_PROFILE_COMPLIANCE_PREVIEW_URI)
    ServerProfileCompliancePreview getCompliancePreview(@PathParam("resourceId") String resourceId);

    /**
     * Returns the error or status messages associated with the specified server profile.
     *
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfileHealth} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_PROFILE_COMPLIANCE_MESSAGES_URI)
    ServerProfileHealth getMessages(@PathParam("resourceId") String resourceId);

    /**
     * Transforms an existing profile by supplying a new server hardware type and
     * enclosure group or both . A profile will be returned with a new configuration based on
     * the capabilities of the supplied server hardware type and/or enclosure group.
     * All deployed connections will have their port assignment set to 'Auto'. Re-selection
     * of the server hardware may also be required. The new profile can subsequently be
     * used for the PUT https://{appl}/rest/server-profiles/{id} API but is not guaranteed
     * to pass validation. Any incompatibilities will be flagged when the transformed server
     * profile is submitted.
     *
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link ServerProfile} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI
            + "?serverHardwareTypeUri={serverHardwareTypeUri}&enclosureGroupUri={enclosureGroupUri}")
    ServerProfile getTransformation(
            @PathParam("resourceId") String resourceId,
            @PathParam("serverHardwareTypeUri") String serverHardwareTypeUri,
            @PathParam("enclosureGroupUri") String enclosureGroupUri);

    /**
     * The module aids in retrieving the list of ethernet networks, fibre
     * channel networks, and network sets that are available to a server profile,
     * along with their respective ports for the specified enclosure group URI and
     * server hardware type URI.
     *
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link AvailableNetworks} object containing the details.
     */
    @Endpoint(uri = SERVER_PROFILE_AVAILABLE_NETWORKS_URI
            + "?serverHardwareTypeUri={serverHardwareTypeUri}&enclosureGroupUri={enclosureGroupUri}")
    AvailableNetworks getAvailableNetworks(
            @PathParam("serverHardwareTypeUri") String serverHardwareTypeUri,
            @PathParam("enclosureGroupUri") String enclosureGroupUri);

    /**
     * Returns a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays.
     *
     * @return {@link List}&lt;{@link AvailableServers}&gt; containing a collection of
     * available servers details.
     */
    @Endpoint(uri = SERVER_PROFILE_AVAILABLE_SERVERS_URI)
    List<AvailableServers> getAvailableServers();

    /**
     * Returns a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays for specified server hardware type URI and
     * enclosure group URI.
     *
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link List}&lt;{@link AvailableServers}&gt; containing a collection of
     * available servers details.
     */
    @Endpoint(uri = SERVER_PROFILE_AVAILABLE_SERVERS_URI
            + "?serverHardwareTypeUri={serverHardwareTypeUri}&enclosureGroupUri={enclosureGroupUri}")
    List<AvailableServers> getAvailableServers(
            @PathParam("serverHardwareTypeUri") String serverHardwareTypeUri,
            @PathParam("enclosureGroupUri") String enclosureGroupUri);

    /**
     * Returns a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays for given profile URI.
     *
     * @param profileUri string specifying the profile URI.
     *
     * @return {@link List}&lt;{@link AvailableServers}&gt; containing a collection of
     * available servers details.
     */
    @Endpoint(uri = SERVER_PROFILE_AVAILABLE_SERVERS_URI
            + "?profileUri={profileUri}")
    List<AvailableServers> getAvailableServers(@PathParam("profileUri") String profileUri);

    /**
     * Returns the list of the storage systems and their associated volumes that
     * are available to the server profile based on the given server hardware type and
     * enclosure group.
     *
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link ResourceCollection}&lt;{@link AvailableStorageSystem}&gt; containing the details
     * for all found available storage systems.
     */
    @Endpoint(uri = SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEMS_URI
            + "?serverHardwareTypeUri={serverHardwareTypeUri}&enclosureGroupUri={enclosureGroupUri}")
    ResourceCollection<AvailableStorageSystem> getAvailableStorageSystems(
            @PathParam("serverHardwareTypeUri") String serverHardwareTypeUri,
            @PathParam("enclosureGroupUri") String enclosureGroupUri);

    /**
     * Returns a specific storage system and its associated volumes that are
     * available to the server profile based on the given server hardware type and
     * enclosure group.
     *
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     * @param storageSystemId string specifying the storage system identifier.
     *
     * @return {@link AvailableStorageSystem} containing the storage system details.
     */
    @Endpoint(uri = SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEM_URI
            + "?serverHardwareTypeUri={serverHardwareTypeUri}&enclosureGroupUri={enclosureGroupUri}&storageSystemId={storageSystemId}")
    AvailableStorageSystem getAvailableStorageSystem(
            @PathParam("serverHardwareTypeUri") String serverHardwareTypeUri,
            @PathParam("enclosureGroupUri") String enclosureGroupUri,
            @PathParam("storageSystemId") String storageSystemId);

    /**
     * Returns a list of the target servers and empty device bays that are available
     * for assignment to the server profile. This replaces the
     * /rest/server-profiles/available-servers API.
     *
     * @param serverHardwareTypeUri optional string specifying the server hardware type URI.
     * @param enclosureGroupUri optional string specifying the enclosure group URI.
     * @param profileUri optional string specifying the server profile URI.
     *
     * @return {@link AvailableTargets} containing the available target details.
     */
    @Endpoint(uri = SERVER_PROFILE_AVAILABLE_TARGETS_URI
            + "?serverHardwareTypeUri={serverHardwareTypeUri}&enclosureGroupUri={enclosureGroupUri}&profileUri={profileUri}")
    AvailableTargets getAvailableTargets(
            @PathParam("serverHardwareTypeUri") String serverHardwareTypeUri,
            @PathParam("enclosureGroupUri") String enclosureGroupUri,
            @PathParam("profileUri") String profileUri);

    /**
     * Returns the port model associated server hardware type and enclosure group.
     *
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link ProfilePorts} containing the profile ports details.
     */
    @Endpoint(uri = SERVER_PROFILE_PROFILE_PORTS_URI
            + "?serverHardwareTypeUri={serverHardwareTypeUri}&enclosureGroupUri={enclosureGroupUri}")
    ProfilePorts getProfilePorts(
            @PathParam("serverHardwareTypeUri") String serverHardwareTypeUri,
            @PathParam("enclosureGroupUri") String enclosureGroupUri);

}
