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
import com.hp.ov.sdk.dto.networking.uplinksets.UplinkSet;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class UplinkSetClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UplinkSetClient.class);

    private final BaseClient baseClient;

    public UplinkSetClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * The module aids in fetching the uplink set details for the specified
     * uplink set resource identifier.
     *
     * @param resourceId resource identifier for uplink set as seen in HPE OneView.
     *
     * @return {@link UplinkSet} containing the uplink set details.
     */
    public UplinkSet getById(String resourceId) {
        LOGGER.info("UplinkSetClient : getById : Start");

        UplinkSet uplinkSet = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.UPLINK_SETS_URI, resourceId), UplinkSet.class);

        LOGGER.info("UplinkSetClient : getById : End");

        return uplinkSet;
    }

    /**
     * The module aids in fetching the uplink set details for all the
     * uplink sets found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link UplinkSet}&gt; containing
     * the details for all found uplink sets.
     */
    public ResourceCollection<UplinkSet> getAll() {
        LOGGER.info("UplinkSetClient : getAll : Start");

        ResourceCollection<UplinkSet> uplinkSets = baseClient.getResourceCollection(
                ResourceUris.UPLINK_SETS_URI, UplinkSet.class);

        LOGGER.info("UplinkSetClient : getAll : End");

        return uplinkSets;
    }

    /**
     * The module aids in fetching the uplink set details for the uplink set
     * name as specified in HPE OneView.
     *
     * @param name uplink set name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link UplinkSet}&gt; containing
     * the details for all found uplink sets.
     */
    public ResourceCollection<UplinkSet> getByName(String name) {
        LOGGER.info("UplinkSetClient : getByName : Start");

        ResourceCollection<UplinkSet> uplinkSets = baseClient.getResourceCollection(
                ResourceUris.UPLINK_SETS_URI, UplinkSet.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("UplinkSetClient : getByName : End");

        return uplinkSets;
    }

    /**
     * The module aids in the creation of a uplink set when provided with the
     * uplink set details as a UplinkSet object. It can process
     * the request asynchronously or synchronously, based on the flag input.
     *
     * @param uplinkSet containing the uplink set details, used to create a uplink set.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource create(UplinkSet uplinkSet, boolean aSync) {
        LOGGER.info("UplinkSetClient : create : Start");

        TaskResource taskResource = baseClient.createResource(
                ResourceUris.UPLINK_SETS_URI, uplinkSet, aSync);

        LOGGER.info("UplinkSetClient : create : End");

        return taskResource;
    }

    /**
     * The module takes in a UplinkSet object and updates the existing uplink
     * set based on the resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param resourceId resource identifier for uplink set as seen in HPE OneView.
     * @param uplinkSet containing the uplink set details, used to update a uplink set.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource update(String resourceId, UplinkSet uplinkSet, boolean aSync) {
        LOGGER.info("UplinkSetClient : update : Start");

        TaskResource taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.UPLINK_SETS_URI, resourceId), uplinkSet, aSync);

        LOGGER.info("UplinkSetClient : update : End");

        return taskResource;
    }

    /**
     * The module aids in deleting a uplink set for the specified uplink set
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param resourceId resource identifier for uplink set as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource delete(String resourceId, boolean aSync) {
        LOGGER.info("UplinkSetClient : delete : Start");

        TaskResource taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.UPLINK_SETS_URI, resourceId), aSync);

        LOGGER.info("UplinkSetClient : delete : End");

        return taskResource;
    }

}
