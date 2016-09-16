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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.ServerProfileHealth;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class ServerProfileClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProfileClient.class);
    private static final int TIMEOUT = 1200000;

    private final BaseClient baseClient;

    public ServerProfileClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link ServerProfile} details for the specified server profile.
     *
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfile} object containing the details.
     */
    public ServerProfile getById(String resourceId) {
        LOGGER.info("ServerProfileClient : getById : Start");

        ServerProfile serverProfile = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_URI, resourceId), ServerProfile.class);

        LOGGER.info("ServerProfileClient : getById : End");

        return serverProfile;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerProfile}&gt; containing the details
     * for all the available server profile found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ServerProfile}&gt; containing
     * the details for all found server profiles.
     */
    public ResourceCollection<ServerProfile> getAll() {
        LOGGER.info("ServerProfileClient : getAll : Start");

        ResourceCollection<ServerProfile> serverProfiles = baseClient.getResourceCollection(
                ResourceUris.SERVER_PROFILE_URI, ServerProfile.class);

        LOGGER.info("ServerProfileClient : getAll : End");

        return serverProfiles;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerProfile}&gt; containing details
     * for the available server profile found under the current HPE OneView that match the name.
     *
     * @param name server profile name as seen in HPE OneView.
     *
     * @return {@link ServerProfile} object containing the details.
     */
    public ResourceCollection<ServerProfile> getByName(String name) {
        LOGGER.info("ServerProfileClient : getByName : Start");

        ResourceCollection<ServerProfile> serverProfiles = baseClient.getResourceCollection(
                ResourceUris.SERVER_PROFILE_URI, ServerProfile.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("ServerProfileClient : getByName : End");

        return serverProfiles;
    }

    /**
     * Creates a server profile according to the provided {@link ServerProfile} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param serverProfile object containing the server profile details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(ServerProfile serverProfile, boolean aSync) {
        LOGGER.info("ServerProfileClient : create : Start");

        Request request = new Request(HttpMethod.POST, ResourceUris.SERVER_PROFILE_URI, serverProfile);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileClient : create : End");

        return taskResource;
    }

    /**
     * Removes the server profile identified by the given resource identifier.
     *
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("ServerProfileClient : delete : Start");

        Request request = new Request(HttpMethod.DELETE,
                UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_URI, resourceId));

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileClient : delete : End");

        return taskResource;
    }

    /**
     * Removes the {@link ServerProfile}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 deleteByFilter(String filter, boolean aSync) {
        LOGGER.info("ServerProfileClient : deleteByFilter : Start");

        Request request = new Request(HttpMethod.DELETE, ResourceUris.SERVER_PROFILE_URI);

        request.addQuery(new UrlParameter("filter", filter));
        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileClient : deleteByFilter : End");

        return taskResource;
    }

    /**
     * Updates a {@link ServerProfile} identified by the given resource identifier.
     * 
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     * @param serverProfile object containing the server profile details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, ServerProfile serverProfile, boolean aSync) {
        LOGGER.info("ServerProfileClient : update : Start");

        String resourceUri = UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_URI, resourceId);
        Request request = new Request(HttpMethod.PUT, resourceUri, serverProfile);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileClient : update : End");

        return taskResource;
    }

    /**
     * Use the PATCH to update the server profile.
     *
     * <br>Ex:
     * <br>To update the server profile from the server profile template:
     * <br>Operation: replace
     * <br>Path: /templateCompliance
     * <br>Value: Compliant
     *
     * @param resourceId server hardware resource identifier as seen in HPE OneView.
     * @param patch object containing the patch details, used to update a server profile.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 patch(String resourceId, Patch patch, boolean aSync) {

        LOGGER.info("ServerProfileClient : patch : Start");

        String resourceUri = UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_URI, resourceId);
        Request request = new Request(HttpMethod.PATCH, resourceUri, patch);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileClient : patch : End");

        return taskResource;
    }

    /**
     * Retrieves the preview of manual and automatic updates required to make the
     * server profile consistent with its template.
     *
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfileCompliancePreview} object containing the details.
     */
    public ServerProfileCompliancePreview getCompliancePreview(String resourceId) {
        LOGGER.info("ServerProfileClient : getCompliancePreview : Start");

        ServerProfileCompliancePreview compliancePreview = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_URI, resourceId,
                        ResourceUris.SERVER_PROFILE_COMPLIANCE_PREVIEW_URI),
                ServerProfileCompliancePreview.class);

        LOGGER.info("ServerProfileClient : getCompliancePreview : End");

        return compliancePreview;
    }

    /**
     * Returns the error or status messages associated with the specified server profile.
     *
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfileHealth} object containing the details.
     */
    public ServerProfileHealth getMessages(String resourceId) {
        LOGGER.info("ServerProfileClient : getMessages : Start");

        ServerProfileHealth profileHealth = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_URI, resourceId,
                        ResourceUris.SERVER_PROFILE_COMPLIANCE_MESSAGES_URI),
                ServerProfileHealth.class);

        LOGGER.info("ServerProfileClient : getMessages : End");

        return profileHealth;
    }

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
     * @param resourceId server profile resource identifier as seen in HPE OneView.
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link ServerProfile} object containing the details.
     */
    public ServerProfile getTransformation(String resourceId, String serverHardwareTypeUri,
            String enclosureGroupUri) {
        LOGGER.info("ServerProfileClient : getTransformation : Start");

        String uri = UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_URI, resourceId,
                ResourceUris.SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI);
        Request request = new Request(HttpMethod.GET, uri);

        request.addQuery(new UrlParameter("serverHardwareTypeUri", serverHardwareTypeUri));
        request.addQuery(new UrlParameter("enclosureGroupUri", enclosureGroupUri));

        ServerProfile serverProfile = baseClient.executeRequest(request, ServerProfile.class);

        LOGGER.info("ServerProfileClient : getTransformation : End");

        return serverProfile;
    }

    /**
     * The module aids in retrieving the list of ethernet networks, fibre
     * channel networks and network sets that are available to a server profile
     * along with their respective ports for the specified enclosure group URI and
     * server hardware type URI.
     *
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link AvailableNetworks} object containing the details.
     */
    public AvailableNetworks getAvailableNetworks(String serverHardwareTypeUri, String enclosureGroupUri) {
        LOGGER.info("ServerProfileClient : getAvailableNetworks : Start");

        Request request = new Request(HttpMethod.GET, ResourceUris.AVAILABLE_NETWORKS_URI);

        request.addQuery(new UrlParameter("serverHardwareTypeUri", serverHardwareTypeUri));
        request.addQuery(new UrlParameter("enclosureGroupUri", enclosureGroupUri));

        AvailableNetworks networks = baseClient.executeRequest(request, AvailableNetworks.class);

        LOGGER.info("ServerProfileClient : getAvailableNetworks : End");

        return networks;
    }

    /**
     * Returns a list of the servers that are available for assignment to the
     * server profile. This API differs from the
     * /rest/server-profiles/available-targets API in that this API does not
     * include empty device bays.
     *
     * @return {@link List}&lt;{@link AvailableServers}&gt; containing a collection of
     * available servers details.
     */
    public List<AvailableServers> getAvailableServers() {
        LOGGER.info("ServerProfileClient : getAvailableServers : Start");

        List<AvailableServers> servers = baseClient.getResourceList(ResourceUris.AVAILABLE_SERVERS_URI,
                AvailableServers.class);

        LOGGER.info("ServerProfileClient : getAvailableServers : End");

        return servers;
    }

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
    public List<AvailableServers> getAvailableServers(String serverHardwareTypeUri, String enclosureGroupUri) {
        LOGGER.info("ServerProfileClient : getAvailableServers : Start");

        List<AvailableServers> servers = baseClient.getResourceList(
                ResourceUris.AVAILABLE_SERVERS_URI,
                AvailableServers.class,
                new UrlParameter("serverHardwareTypeUri", serverHardwareTypeUri),
                new UrlParameter("enclosureGroupUri", enclosureGroupUri));

        LOGGER.info("ServerProfileClient : getAvailableServers : End");

        return servers;
    }

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
    public List<AvailableServers> getAvailableServers(String profileUri) {
        LOGGER.info("ServerProfileClient : getAvailableServers : Start");

        List<AvailableServers> servers = baseClient.getResourceList(ResourceUris.AVAILABLE_SERVERS_URI,
                AvailableServers.class, new UrlParameter("profileUri", profileUri));

        LOGGER.info("ServerProfileClient : getAvailableServers : End");

        return servers;
    }

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
    public ResourceCollection<AvailableStorageSystem> getAvailableStorageSystems(String serverHardwareTypeUri,
            String enclosureGroupUri) {
        LOGGER.info("ServerProfileClient : getAvailableStorageSystems : Start");

        ResourceCollection<AvailableStorageSystem> storageSystems = this.baseClient.getResourceCollection(
                ResourceUris.AVAILABLE_STORAGE_SYSTEMS, AvailableStorageSystem.class,
                new UrlParameter("serverHardwareTypeUri", serverHardwareTypeUri),
                new UrlParameter("enclosureGroupUri", enclosureGroupUri));

        LOGGER.info("ServerProfileClient : getAvailableStorageSystems : End");

        return storageSystems;
    }

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
    public AvailableStorageSystem getAvailableStorageSystem(String serverHardwareTypeUri,
            String enclosureGroupUri, String storageSystemId) {
        LOGGER.info("ServerProfileClient : getAvailableStorageSystem : Start");

        AvailableStorageSystem storageSystem = this.baseClient.getResource(
                ResourceUris.AVAILABLE_STORAGE_SYSTEM, AvailableStorageSystem.class,
                new UrlParameter("serverHardwareTypeUri", serverHardwareTypeUri),
                new UrlParameter("enclosureGroupUri", enclosureGroupUri),
                new UrlParameter("storageSystemId", storageSystemId));

        LOGGER.info("ServerProfileClient : getAvailableStorageSystem : End");

        return storageSystem;
    }

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
    public AvailableTargets getAvailableTargets(Optional<String> serverHardwareTypeUri,
            Optional<String> enclosureGroupUri,
            Optional<String> profileUri) {
        LOGGER.info("ServerProfileClient : getAvailableTargets : Start");

        List<UrlParameter> parameters = new ArrayList<>();

        if (serverHardwareTypeUri.isPresent()) {
            parameters.add(new UrlParameter("serverHardwareTypeUri", serverHardwareTypeUri.get()));
        }
        if (enclosureGroupUri.isPresent()) {
            parameters.add(new UrlParameter("enclosureGroupUri", enclosureGroupUri.get()));
        }
        if (profileUri.isPresent()) {
            parameters.add(new UrlParameter("profileUri", profileUri.get()));
        }

        AvailableTargets targets = this.baseClient.getResource(
                ResourceUris.AVAILABLE_TARGETS, AvailableTargets.class,
                parameters.toArray(new UrlParameter[parameters.size()]));

        LOGGER.info("ServerProfileClient : getAvailableTargets : End");

        return targets;
    }

    /**
     * Returns the port model associated server hardware type and enclosure group.
     *
     * @param serverHardwareTypeUri string specifying the server hardware type URI.
     * @param enclosureGroupUri string specifying the enclosure group URI.
     *
     * @return {@link ProfilePorts} containing the profile ports details.
     */
    public ProfilePorts getProfilePorts(String serverHardwareTypeUri, String enclosureGroupUri) {
        LOGGER.info("ServerProfileClient : getProfilePorts : Start");

        ProfilePorts profilePorts = this.baseClient.getResource(
                ResourceUris.PROFILE_PORTS_URI, ProfilePorts.class,
                new UrlParameter("serverHardwareTypeUri", serverHardwareTypeUri),
                new UrlParameter("enclosureGroupUri", enclosureGroupUri));

        LOGGER.info("ServerProfileClient : getProfilePorts : End");

        return profilePorts;
    }

}
