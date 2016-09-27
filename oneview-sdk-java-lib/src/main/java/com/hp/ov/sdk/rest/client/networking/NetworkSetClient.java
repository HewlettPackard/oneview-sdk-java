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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class NetworkSetClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkSetClient.class);

    private final BaseClient baseClient;

    public NetworkSetClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * The module aids in fetching the network set details for the specified
     * network set resource identifier.
     *
     * @param resourceId
     *            The resource identifier for network set as seen in HPE OneView.
     * @return {@link NetworkSet} containing the network set details.
     */
    public NetworkSet getById(String resourceId) {
        LOGGER.info("NetworkSetClient : getById : Start");

        NetworkSet networkSet = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.NETWORK_SETS_URI, resourceId), NetworkSet.class);

        LOGGER.info("NetworkSetClient : getById : End");

        return networkSet;
    }

    /**
     * The module aids in fetching the network set details for all the
     * network sets found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link NetworkSet}&gt; containing
     * the details for all found network sets.
     */
    public ResourceCollection<NetworkSet> getAll() {
        LOGGER.info("NetworkSetClient : getAll : Start");

        ResourceCollection<NetworkSet> networkSets = baseClient.getResourceCollection(
                ResourceUris.NETWORK_SETS_URI, NetworkSet.class);

        LOGGER.info("NetworkSetClient : getAll : End");

        return networkSets;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link NetworkSet}&gt; containing details
     * for the available networks sets found under the current HPE OneView that match the name.
     *
     * @param name networks sets name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link NetworkSet}&gt; containing
     * the details for the found networks sets.
     */
    public ResourceCollection<NetworkSet> getByName(String name) {
        LOGGER.info("NetworkSetClient : getByName : Start");

        ResourceCollection<NetworkSet> networkSets = baseClient.getResourceCollection(
                ResourceUris.NETWORK_SETS_URI, NetworkSet.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("NetworkSetClient : getByName : End");

        return networkSets;
    }

    /**
     * The module aids in the creation of a network set when provided with the
     * network set details as a NetworkSets object. It can process
     * the request asynchronously or synchronously, based on the flag input.
     *
     * @param networkSet
     *            Object containing the network set details, used to create a network set.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource create(NetworkSet networkSet, boolean aSync) {
        LOGGER.info("NetworkSetClient : create : Start");

        TaskResource taskResource = baseClient.createResource(
                ResourceUris.NETWORK_SETS_URI, networkSet, aSync);

        LOGGER.info("NetworkSetClient : create : End");

        return taskResource;
    }

    /**
     * The module takes in a NetworkSet object and updates the existing network
     * set based on the resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param resourceId
     *            The resource identifier for network set as seen in HPE OneView.
     * @param networkSet
     *            Object containing the network set details, used to update a network set.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource update(String resourceId, NetworkSet networkSet, boolean aSync) {
        LOGGER.info("NetworkSetClient : update : Start");

        TaskResource taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.NETWORK_SETS_URI, resourceId), networkSet, aSync);

        LOGGER.info("NetworkSetClient : update : End");

        return taskResource;
    }

    /**
     * The module aids in deleting a network set for the specified network set
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param resourceId
     *            The resource identifier for network set as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource delete(String resourceId, boolean aSync) {
        LOGGER.info("NetworkSetClient : delete : Start");

        TaskResource taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.NETWORK_SETS_URI, resourceId), aSync);

        LOGGER.info("NetworkSetClient : delete : End");

        return taskResource;
    }

}
