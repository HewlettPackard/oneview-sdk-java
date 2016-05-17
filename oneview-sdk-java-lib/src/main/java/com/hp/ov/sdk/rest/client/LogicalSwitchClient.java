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

import com.hp.ov.sdk.dto.AddLogicalSwitch;
import com.hp.ov.sdk.dto.LogicalSwitch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

/**
 * The logical switch resource provides REST APIs for managing logical switches.
 * A logical switch aggregates one or more switches with a common configuration,
 * providing L2/L3 Ethernet and Fibre Channel connectivity to a set of downlinks
 * and uplinks. The logical switch also centralizes the display of switch firmware.
 *
 * A logical switch is constructed from a logical switch group. The logical switch
 * group serves as the initial and ongoing reference for the structure of the
 * logical switch. A logical switch is monitored for consistency to its reference
 * logical switch group. If the logical switch transitions to a 'not consistent' state
 * (because of changes in either the logical switch or the logical switch group),
 * it receives a request to return to a consistent state. The logical switch is
 * also monitored for its stacking health (BiConnected, Connected, and Disconnected)
 * and for its overall health status regarding the network connectivity it provides.
 *
 * The switch map determines the physical membership of switches within a logical switch.
 * The switch map is a list of switch entries, with the switch type permitted and
 * optionally a member ID in each entry. When a switch with the correct switch type and
 * firmware version is found at a location, it is monitored.
 */
public interface LogicalSwitchClient {

    /**
     * Retrieves the {@link LogicalSwitch} details for the specified
     * logical switch resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId logical switch identifier as seen in HPE OneView.
     *
     * @return {@link LogicalSwitch} object containing the details.
     */
    LogicalSwitch getLogicalSwitch(RestParams params, String resourceId);

    /**
     * Retrieves the {@link LogicalSwitch} details for the specified
     * logical switch name.
     *
     * @param params structure containing the connection details.
     * @param name logical switch name as seen in HPE OneView.
     *
     * @return {@link LogicalSwitch} object containing the details.
     */
    LogicalSwitch getLogicalSwitchByName(RestParams params, String name);

    /**
     * Retrieves the logical switch details for all the logical switches
     * under the current HPE OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalSwitch}&gt; containing
     * the details for all found logical switches.
     */
    ResourceCollection<LogicalSwitch> getAllLogicalSwitches(RestParams params);

    /**
     * Creates a logical switch according to the provided {@link LogicalSwitch}
     * object. The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param addLogicalSwitch object containing the logical switch details.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createLogicalSwitch(RestParams params, AddLogicalSwitch addLogicalSwitch, boolean aSync);

    /**
     * Updates a logical switch according to the provided {@link LogicalSwitch}
     * object. The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param resourceId logical switch identifier as seen in HPE OneView.
     * @param addLogicalSwitch object containing the logical switch details.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalSwitch(RestParams params, String resourceId,
            AddLogicalSwitch addLogicalSwitch, boolean aSync);

    /**
     * Removes a logical switch identified by the provided resource identifier.
     * The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param resourceId logical switch identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteLogicalSwitch(RestParams params, String resourceId, boolean aSync);

    /**
     * Executes a refresh action for a logical switch identified by the provided
     * resource identifier. The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param resourceId logical switch identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 refreshLogicalSwitch(RestParams params, String resourceId, boolean aSync);

    /**
     * Retrieves the logical switch resource identifier for the specified logical switch name.
     *
     * @param params structure containing the connection details.
     * @param name logical switch name as seen in HPE OneView.
     *
     * @return the resource identifier for the logical switch.
     */
    String getId(RestParams params, String name);

}
