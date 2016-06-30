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
import com.hp.ov.sdk.dto.AddLogicalSwitch;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LogicalSwitch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalSwitchClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalSwitchClient.class);

    private final BaseClient baseClient;

    public LogicalSwitchClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link LogicalSwitch} details for the specified logical switch.
     *
     * @param resourceId logical switch resource identifier as seen in HPE OneView.
     *
     * @return {@link LogicalSwitch} object containing the details.
     */
    public LogicalSwitch getById(String resourceId) {
        LOGGER.info("LogicalSwitchClient : getById : Start");

        LogicalSwitch logicalSwitch = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_SWITCHES_URI, resourceId), LogicalSwitch.class);

        LOGGER.info("LogicalSwitchClient : getById : End");

        return logicalSwitch;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalSwitch}&gt; containing the details
     * for all the available logical switches found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalSwitch}&gt; containing
     * the details for all found logical switches.
     */
    public ResourceCollection<LogicalSwitch> getAll() {
        LOGGER.info("LogicalSwitchClient : getAll : Start");

        ResourceCollection<LogicalSwitch> logicalSwitches = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_SWITCHES_URI, LogicalSwitch.class);

        LOGGER.info("LogicalSwitchClient : getAll : End");

        return logicalSwitches;
    }

    /**
     * Retrieves the {@link LogicalSwitch} details for the specified logical switch identified by name.
     *
     * @param name logical switch name as seen in HPE OneView.
     *
     * @return {@link LogicalSwitch} object containing the details.
     */
    public LogicalSwitch getByName(String name) {
        LOGGER.info("LogicalSwitchClient : getByName : Start");

        LogicalSwitch logicalSwitch;
        ResourceCollection<LogicalSwitch> logicalSwitches = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_SWITCHES_URI, LogicalSwitch.class, UrlParameter.getFilterByNameParameter(name));

        if (!logicalSwitches.isEmpty()) {
            logicalSwitch = logicalSwitches.getMembers().get(0);
        } else {
            LOGGER.info("LogicalSwitchClient : getByName : No logical switch found for the name " + name);

            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound,
                    null, null, null, SdkConstants.LOGICAL_SWITCHES, null);
        }
        LOGGER.info("LogicalSwitchClient : getByName : End");

        return logicalSwitch;
    }

    /**
     * Creates a logical switch according to the provided {@link AddLogicalSwitch} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param logicalSwitch object containing the logical switch details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(AddLogicalSwitch logicalSwitch, boolean aSync) {
        LOGGER.info("LogicalSwitchClient : create : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                ResourceUris.LOGICAL_SWITCHES_URI, logicalSwitch, aSync);

        LOGGER.info("LogicalSwitchClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link LogicalSwitch} identified by the given resource identifier.
     *
     * @param resourceId logical switch resource identifier as seen in HPE OneView.
     * @param logicalSwitch object containing the logical switch details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, AddLogicalSwitch logicalSwitch, boolean aSync) {
        LOGGER.info("LogicalSwitchClient : update : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_SWITCHES_URI, resourceId), logicalSwitch, aSync);

        LOGGER.info("LogicalSwitchClient : update : End");

        return taskResource;
    }

    /**
     * Deletes the {@link LogicalSwitch} identified by the given resource identifier.
     *
     * @param resourceId logical switch resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("LogicalSwitchClient : delete : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_SWITCHES_URI, resourceId), aSync);

        LOGGER.info("LogicalSwitchClient : delete : End");

        return taskResource;
    }

    /**
     * Executes a refresh action for a logical switch identified by the provided
     * resource identifier. The request can be processed asynchronously or synchronously.
     *
     * @param resourceId logical switch identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 refresh(String resourceId, boolean aSync) {
        LOGGER.info("LogicalSwitchClient : refresh : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.LOGICAL_SWITCHES_URI, resourceId,
                        ResourceUris.LOGICAL_SWITCHES_REFRESH_URI));

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalSwitchClient : refresh : End");

        return taskResource;
    }

}
