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
import com.hp.ov.sdk.dto.networking.fcoenetworks.FcoeNetwork;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class FcoeNetworkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcoeNetworkClient.class);

    private final BaseClient baseClient;

    public FcoeNetworkClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link FcoeNetwork} details for the specified FCoE network.
     *
     * @param resourceId FCoE network resource identifier as seen in HPE OneView.
     *
     * @return {@link FcoeNetwork} object containing the details.
     */
    public FcoeNetwork getById(String resourceId) {
        LOGGER.info("FcoeNetworkClient : getById : Start");

        FcoeNetwork fcoeNetwork = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.FCOE_NETWORK_URI, resourceId), FcoeNetwork.class);

        LOGGER.info("FcoeNetworkClient : getById : End");

        return fcoeNetwork;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link FcoeNetwork}&gt; containing the details
     * for all the available FCoE networks found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link FcoeNetwork}&gt; containing
     * the details for all found FCoE networks.
     */
    public ResourceCollection<FcoeNetwork> getAll() {
        LOGGER.info("FcoeNetworkClient : getAll : Start");

        ResourceCollection<FcoeNetwork> fcoeNetworks = baseClient.getResourceCollection(
                ResourceUris.FCOE_NETWORK_URI, FcoeNetwork.class);

        LOGGER.info("FcoeNetworkClient : getAll : End");

        return fcoeNetworks;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link FcoeNetwork}&gt; containing details
     * for the available FCoE networks found under the current HPE OneView that match the name.
     *
     * @param name FCoE network name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link FcoeNetwork}&gt; containing
     * the details for the found FCoE networks.
     */
    public ResourceCollection<FcoeNetwork> getByName(String name) {
        LOGGER.info("FcoeNetworkClient : getByName : Start");

        ResourceCollection<FcoeNetwork> fcoeNetworks = baseClient.getResourceCollection(
                ResourceUris.FCOE_NETWORK_URI, FcoeNetwork.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("FcoeNetworkClient : getByName : End");

        return fcoeNetworks;
    }

    /**
     * Creates a FCoE network according to the provided {@link FcoeNetwork} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param fcoeNetwork object containing the FCoE network details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource create(FcoeNetwork fcoeNetwork, boolean aSync) {
        LOGGER.info("FcoeNetworkClient : create : Start");

        TaskResource taskResource = baseClient.createResource(ResourceUris.FCOE_NETWORK_URI, fcoeNetwork, aSync);

        LOGGER.info("FcoeNetworkClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link FcoeNetwork} identified by the given resource identifier.
     *
     * @param resourceId FCoE network resource identifier as seen in HPE OneView.
     * @param fcoeNetwork object containing the FCoE network details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource update(String resourceId, FcoeNetwork fcoeNetwork, boolean aSync) {
        LOGGER.info("FcoeNetworkClient : update : Start");

        TaskResource taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.FCOE_NETWORK_URI, resourceId), fcoeNetwork, aSync);

        LOGGER.info("FcoeNetworkClient : update : End");

        return taskResource;
    }

    /**
     * Deletes the {@link FcoeNetwork} identified by the given resource identifier.
     *
     * @param resourceId FCoE network resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource delete(String resourceId, boolean aSync) {
        LOGGER.info("FcoeNetworkClient : delete : Start");

        TaskResource taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.FCOE_NETWORK_URI, resourceId), aSync);

        LOGGER.info("FcoeNetworkClient : delete : End");

        return taskResource;
    }

}
