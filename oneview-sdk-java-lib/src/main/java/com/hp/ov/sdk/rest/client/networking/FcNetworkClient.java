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
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class FcNetworkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcNetworkClient.class);

    private final BaseClient baseClient;

    public FcNetworkClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link FcNetwork} details for the specified FC network.
     *
     * @param resourceId FC network resource identifier as seen in HPE OneView.
     *
     * @return {@link FcNetwork} object containing the details.
     */
    public FcNetwork getById(String resourceId) {
        LOGGER.info("FcNetworkClient : getById : Start");

        FcNetwork fcNetwork = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.FC_NETWORK_URI, resourceId), FcNetwork.class);

        LOGGER.info("FcNetworkClient : getById : End");

        return fcNetwork;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link FcNetwork}&gt; containing the details
     * for all the available FC networks found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link FcNetwork}&gt; containing
     * the details for all found FC networks.
     */
    public ResourceCollection<FcNetwork> getAll() {
        LOGGER.info("FcNetworkClient : getAll : Start");

        ResourceCollection<FcNetwork> fcNetworks = baseClient.getResourceCollection(
                ResourceUris.FC_NETWORK_URI, FcNetwork.class);

        LOGGER.info("FcNetworkClient : getAll : End");

        return fcNetworks;
    }

    /**
     * Retrieves the {@link FcNetwork} details for the specified FC network identified by name.
     *
     * @param name FC network name as seen in HPE OneView.
     *
     * @return {@link FcNetwork} object containing the details.
     */
    public FcNetwork getByName(String name) {
        LOGGER.info("FcNetworkClient : getByName : Start");

        FcNetwork fcNetwork;
        ResourceCollection<FcNetwork> fcNetworks = baseClient.getResourceCollection(
                ResourceUris.FC_NETWORK_URI, FcNetwork.class, UrlParameter.getFilterByNameParameter(name));

        if (!fcNetworks.isEmpty()) {
            fcNetwork = fcNetworks.getMembers().get(0);
        } else {
            LOGGER.info("FcNetworkClient : getByName : No FC networking found for the name " + name);

            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound,
                    null, null, null, SdkConstants.FC_NETWORK, null);
        }
        LOGGER.info("FcNetworkClient : getByName : End");

        return fcNetwork;
    }

    /**
     * Creates a FC network according to the provided {@link FcNetwork} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param fcNetwork object containing the FC network details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(FcNetwork fcNetwork, boolean aSync) {
        LOGGER.info("FcNetworkClient : create : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                ResourceUris.FC_NETWORK_URI, fcNetwork, aSync);

        LOGGER.info("FcNetworkClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link FcNetwork} identified by the given resource identifier.
     *
     * @param resourceId FC network resource identifier as seen in HPE OneView.
     * @param fcNetwork object containing the FC network details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, FcNetwork fcNetwork, boolean aSync) {
        LOGGER.info("FcNetworkClient : update : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.FC_NETWORK_URI, resourceId), fcNetwork, aSync);

        LOGGER.info("FcNetworkClient : update : End");

        return taskResource;
    }

    /**
     * Deletes the {@link FcNetwork} identified by the given resource identifier.
     *
     * @param resourceId FC network resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("FcNetworkClient : delete : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.FC_NETWORK_URI, resourceId), aSync);

        LOGGER.info("FcNetworkClient : delete : End");

        return taskResource;
    }

}
