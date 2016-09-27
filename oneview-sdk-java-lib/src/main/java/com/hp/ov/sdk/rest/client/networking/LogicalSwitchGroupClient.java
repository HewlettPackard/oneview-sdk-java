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
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.LogicalSwitchGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalSwitchGroupClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalSwitchGroupClient.class);

    private final BaseClient baseClient;

    public LogicalSwitchGroupClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link LogicalSwitchGroup} details for the specified logical switch group.
     *
     * @param resourceId logical switch group resource identifier as seen in HPE OneView.
     *
     * @return {@link LogicalSwitchGroup} object containing the details.
     */
    public LogicalSwitchGroup getById(String resourceId) {
        LOGGER.info("LogicalSwitchGroupClient : getById : Start");

        LogicalSwitchGroup logicalSwitchGroup = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_SWITCH_GROUPS_URI, resourceId), LogicalSwitchGroup.class);

        LOGGER.info("LogicalSwitchGroupClient : getById : End");

        return logicalSwitchGroup;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalSwitchGroup}&gt; containing the details
     * for all the available logical switch groups found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalSwitchGroup}&gt; containing
     * the details for all found logical switch groups.
     */
    public ResourceCollection<LogicalSwitchGroup> getAll() {
        LOGGER.info("LogicalSwitchGroupClient : getAll : Start");

        ResourceCollection<LogicalSwitchGroup> logicalSwitchGroups = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, LogicalSwitchGroup.class);

        LOGGER.info("LogicalSwitchGroupClient : getAll : End");

        return logicalSwitchGroups;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalSwitchGroup}&gt; containing details
     * for the available logical switch groups found under the current HPE OneView that match the name.
     *
     * @param name logical switch group name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalSwitchGroup}&gt; containing
     * the details for the found logical switch groups.
     */
    public ResourceCollection<LogicalSwitchGroup> getByName(String name) {
        LOGGER.info("LogicalSwitchGroupClient : getByName : Start");

        ResourceCollection<LogicalSwitchGroup> logicalSwitchGroups = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, LogicalSwitchGroup.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("LogicalSwitchGroupClient : getByName : End");

        return logicalSwitchGroups;
    }

    /**
     * Creates a logical switch group according to the provided {@link LogicalSwitchGroup} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param logicalSwitchGroup object containing the logical switch details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource create(LogicalSwitchGroup logicalSwitchGroup, boolean aSync) {
        LOGGER.info("LogicalSwitchGroupClient : create : Start");

        TaskResource taskResource = baseClient.createResource(
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, logicalSwitchGroup, aSync);

        LOGGER.info("LogicalSwitchGroupClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link LogicalSwitchGroup} identified by the given resource identifier.
     *
     * @param resourceId logical switch group resource identifier as seen in HPE OneView.
     * @param logicalSwitchGroup object containing the logical switch details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource update(String resourceId, LogicalSwitchGroup logicalSwitchGroup, boolean aSync) {
        LOGGER.info("LogicalSwitchGroupClient : update : Start");

        TaskResource taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_SWITCH_GROUPS_URI, resourceId), logicalSwitchGroup, aSync);

        LOGGER.info("LogicalSwitchGroupClient : update : End");

        return taskResource;
    }

    /**
     * Deletes the {@link LogicalSwitchGroup} identified by the given resource identifier.
     *
     * @param resourceId logical switch group resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource delete(String resourceId, boolean aSync) {
        LOGGER.info("LogicalSwitchGroupClient : delete : Start");

        TaskResource taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_SWITCH_GROUPS_URI, resourceId), aSync);

        LOGGER.info("LogicalSwitchGroupClient : delete : End");

        return taskResource;
    }

}
