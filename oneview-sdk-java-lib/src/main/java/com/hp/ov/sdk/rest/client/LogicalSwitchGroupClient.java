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

package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.LogicalSwitchGroupCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalSwitchGroupClient {

    /**
     * Retrieves the {@link LogicalSwitchGroup} details for the specified
     * logical switch group resourceId.
     *
     * @param params structure containing the connection details.
     * @param resourceId logical switch group id as seen in HP OneView.
     *
     * @return {@link LogicalSwitchGroup} object containing the details.
     */
    LogicalSwitchGroup getLogicalSwitchGroup(RestParams params, String resourceId);

    /**
     * Retrieves the {@link LogicalSwitchGroup} details for the specified
     * logical switch group name.
     *
     * @param params structure containing the connection details.
     * @param name logical switch group name as seen in HP OneView.
     *
     * @return {@link LogicalSwitchGroup} object containing the details.
     */
    LogicalSwitchGroup getLogicalSwitchGroupByName(RestParams params, String name);

    /**
     * Retrieves the logical switch group details for all the
     * logical switch groups under OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link LogicalSwitchGroupCollection} object containing the details
     * for all the logical switch groups found in OneView.
     */
    LogicalSwitchGroupCollection getAllLogicalSwitchGroups(RestParams params);

    /**
     * Creates a logical switch group according to the provided {@link LogicalSwitchGroup}
     * object. The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param logicalSwitchGroup object containing the logical switch group details.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 createLogicalSwitchGroup(RestParams params, LogicalSwitchGroup logicalSwitchGroup,
            boolean aSync);

    /**
     * Updates a logical switch group according to the provided {@link LogicalSwitchGroup}
     * object. The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param resourceId logical switch group id as seen in OneView.
     * @param logicalSwitchGroup object containing the logical switch group details.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 updateLogicalSwitchGroup(RestParams params, String resourceId,
            LogicalSwitchGroup logicalSwitchGroup, boolean aSync);

    /**
     * Deletes a logical switch group identified by the provided resourceId.
     * The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param resourceId logical switch group id as seen in OneView.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 deleteLogicalSwitchGroup(RestParams params, String resourceId, boolean aSync);

    /**
     * Retrieves the resourceId for the specified logical switch group name.
     *
     * @param params structure containing the connection details.
     * @param name logical switch group name as seen in HP OneView.
     *
     * @return the resourceId for the logical switch group
     */
    String getId(RestParams params, String name);

}
