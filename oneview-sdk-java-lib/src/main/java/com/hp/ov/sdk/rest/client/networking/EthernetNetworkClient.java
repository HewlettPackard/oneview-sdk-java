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
package com.hp.ov.sdk.rest.client.networking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.ethernet.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class EthernetNetworkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthernetNetworkClient.class);

    private final BaseClient baseClient;

    public EthernetNetworkClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link Network} details for the specified network.
     *
     * @param resourceId network resource identifier as seen in HPE OneView.
     *
     * @return {@link Network} object containing the details.
     */
    public Network getById(String resourceId) {
        LOGGER.info("NetworkClient : getById : Start");

        Network network = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.ETHERNET_URI, resourceId),Network.class);

        LOGGER.info("NetworkClient : getById : End");

        return network;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Network}&gt; containing the details
     * for all the available networks found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Network}&gt; containing
     * the details for all found networks.
     */
    public ResourceCollection<Network> getAll() {
        LOGGER.info("NetworkClient : getAll : Start");

        ResourceCollection<Network> networks = baseClient.getResourceCollection(
                ResourceUris.ETHERNET_URI, Network.class);

        LOGGER.info("NetworkClient : getAll : End");

        return networks;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Network}&gt; containing details
     * for the available ethernet networks found under the current HPE OneView that match the name.
     *
     * @param name network name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Network}&gt; containing
     * the details for the found ethernet networks.
     */
    public ResourceCollection<Network> getByName(String name) {
        LOGGER.info("NetworkClient : getByName : Start");

        ResourceCollection<Network> networks = baseClient.getResourceCollection(
                ResourceUris.ETHERNET_URI, Network.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("NetworkClient : getByName : End");

        return networks;
    }

    /**
     * Creates a network according to the provided {@link Network} object.
     * The request can be processed synchronously or asynchronously.
     *
     * <b>ATTENTION:</b> if you use async mode equals to <code>true</code>
     * and you set some connection template data, this information will not be used.
     * Thus, we strongly recommend the value <code>false</code> for the async flag if
     * you want to set bandwidth values.
     *
     * @param network object containing the network details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(Network network, boolean aSync) {
        LOGGER.info("NetworkClient : create : Start");

        TaskResourceV2 taskResource = baseClient.createResource(ResourceUris.ETHERNET_URI, network, aSync);

        LOGGER.info("NetworkClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link Network} identified by the given resource identifier.
     *
     * @param resourceId network resource identifier as seen in HPE OneView.
     * @param network object containing the network details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, Network network, boolean aSync) {
        LOGGER.info("NetworkClient : update : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.ETHERNET_URI, resourceId), network, aSync);

        LOGGER.info("NetworkClient : update : End");

        return taskResource;
    }

    /**
     * Deletes the {@link Network} identified by the given resource identifier.
     *
     * @param resourceId network resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("NetworkClient : delete : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.ETHERNET_URI, resourceId), aSync);

        LOGGER.info("NetworkClient : delete : End");

        return taskResource;
    }

    /**
     * Creates a set of ethernet networks when provided with the ethernet network details
     * as a {@link BulkEthernetNetwork} object containing a VLAN identifier range.
     *
     * @param bulkEthernet object containing the bulk ethernet network details,
     * used to create a set of ethernet networks according to the provided range with
     * VLAN as suffix.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 createInBulk(BulkEthernetNetwork bulkEthernet, boolean aSync) {
        LOGGER.info("NetworkClient : createInBulk : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                ResourceUris.BULK_ETHERNET_URI, bulkEthernet, aSync);

        LOGGER.info("NetworkClient : createInBulk : End");

        return taskResource;
    }

    /**
     * Returns a {@link List}&lt;String&gt; containing the profile URIs for the ethernet network.
     *
     * @param resourceId network resource identifier as seen in HPE OneView.
     *
     * @return list of profile URIs for the specified ethernet network.
     */
    public List<String> getAssociatedProfiles(String resourceId) {
        LOGGER.info("NetworkClient : getAssociatedProfiles : Start");

        List<String> associatedProfiles = baseClient.getResourceList(
                UrlUtils.createUrl(ResourceUris.ETHERNET_URI, resourceId, ResourceUris.ASSOCIATED_PROFILES),
                String.class);

        LOGGER.info("NetworkClient : getAssociatedProfiles : End");

        return associatedProfiles;
    }

    /**
     * Returns a {@link List}&lt;String&gt; containing the uplink set URIs which are
     * using the specified ethernet network.
     *
     * @param resourceId network resource identifier as seen in HPE OneView.
     *
     * @return {@link List}&lt;String&gt; of uplink sets in use by the specified ethernet network.
     */
    public List<String> getAssociatedUplinkGroups(String resourceId) {
        LOGGER.info("NetworkClient : getNetworkAssociatedUplinkGroups : Start");

        List<String> associatedUplinkGroups = baseClient.getResourceList(
                UrlUtils.createUrl(ResourceUris.ETHERNET_URI, resourceId, ResourceUris.ASSOCIATED_UPLINK_GROUPS),
                String.class);

        LOGGER.info("NetworkClient : getNetworkAssociatedUplinkGroups : End");

        return associatedUplinkGroups;
    }
}
