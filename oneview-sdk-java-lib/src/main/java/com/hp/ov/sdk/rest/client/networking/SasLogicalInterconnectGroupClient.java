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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasLogicalInterconnectGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class SasLogicalInterconnectGroupClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalInterconnectGroupClient.class);

    protected static final String SAS_LOGICAL_INTERCONNECT_GROUP_URI = "/rest/sas-logical-interconnect-groups";

    private final BaseClient baseClient;

    public SasLogicalInterconnectGroupClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link SasLogicalInterconnectGroup} details for the specified SAS logical interconnect group.
     *
     * @param resourceId SAS logical interconnect group resource identifier as seen in HPE OneView.
     *
     * @return {@link SasLogicalInterconnectGroup} object containing the details.
     */
    public SasLogicalInterconnectGroup getById(String resourceId) {
        LOGGER.info("SasLogicalInterconnectGroupClient : getById : Start");

        SasLogicalInterconnectGroup interconnectGroup = baseClient.getResource(
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_GROUP_URI, resourceId),
                SasLogicalInterconnectGroup.class);

        LOGGER.info("SasLogicalInterconnectGroupClient : getById : End");

        return interconnectGroup;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasLogicalInterconnectGroup}&gt; containing the details
     * for all the available SAS logical interconnect groups found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasLogicalInterconnectGroup}&gt; containing
     * the details for all found SAS logical interconnect groups.
     */
    public ResourceCollection<SasLogicalInterconnectGroup> getAll() {
        LOGGER.info("SasLogicalInterconnectGroupClient : getAll : Start");

        ResourceCollection<SasLogicalInterconnectGroup> interconnectGroups = baseClient.getResourceCollection(
                SAS_LOGICAL_INTERCONNECT_GROUP_URI,
                SasLogicalInterconnectGroup.class);

        LOGGER.info("SasLogicalInterconnectGroupClient : getAll : End");

        return interconnectGroups;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasLogicalInterconnectGroup}&gt; containing details
     * for the available SAS logical interconnect groups found under the current HPE OneView that match the name.
     *
     * @param name SAS logical interconnect group name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasLogicalInterconnectGroup}&gt; containing
     * the details for the found SAS logical interconnect groups.
     */
    public ResourceCollection<SasLogicalInterconnectGroup> getByName(String name) {
        LOGGER.info("SasLogicalInterconnectGroupClient : getByName : Start");

        ResourceCollection<SasLogicalInterconnectGroup> interconnectGroups = baseClient.getResourceCollection(
                SAS_LOGICAL_INTERCONNECT_GROUP_URI,
                SasLogicalInterconnectGroup.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("SasLogicalInterconnectGroupClient : getByName : End");

        return interconnectGroups;
    }

    /**
     * Creates a SAS logical interconnect group according to the provided {@link SasLogicalInterconnectGroup} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param interconnectGroup object containing the SAS logical interconnect group details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(SasLogicalInterconnectGroup interconnectGroup, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectGroupClient : create : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                SAS_LOGICAL_INTERCONNECT_GROUP_URI, interconnectGroup, aSync);

        LOGGER.info("SasLogicalInterconnectGroupClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link SasLogicalInterconnectGroup} identified by the given resource identifier.
     *
     * @param resourceId SAS logical interconnect group resource identifier as seen in HPE OneView.
     * @param interconnectGroup object containing the SAS logical interconnect group details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, SasLogicalInterconnectGroup interconnectGroup, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectGroupClient : update : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_GROUP_URI, resourceId), interconnectGroup, aSync);

        LOGGER.info("SasLogicalInterconnectGroupClient : update : End");

        return taskResource;
    }

    /**
     * Deletes the {@link SasLogicalInterconnectGroup} identified by the given resource identifier.
     *
     * @param resourceId SAS logical interconnect group resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectGroupClient : delete : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_GROUP_URI, resourceId), aSync);

        LOGGER.info("SasLogicalInterconnectGroupClient : delete : End");

        return taskResource;
    }

}
