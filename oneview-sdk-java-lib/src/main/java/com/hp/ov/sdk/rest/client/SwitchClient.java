/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

import com.hp.ov.sdk.dto.SwitchCollection;
import com.hp.ov.sdk.dto.SwitchPortStatistics;
import com.hp.ov.sdk.dto.SwitchStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.Switch;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface SwitchClient {

    /**
     * This method aids in fetching the switch for the given resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for switch as seen in HPE OneView.
     * @return {@link Switch} containing the switch details.
     */
    Switch getSwitch(RestParams params, String resourceId);

    /**
     * This method aids in fetching the top of rack switches.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link SwitchCollection} containing the collection of switch details.
     */
    SwitchCollection getAllSwitches(RestParams params);


    /**
     * This method aids in the creation of a switch.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param switchObj
     *            Object containing the switch details.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createSwitch(RestParams params, Switch switchObj, boolean aSync);

    /**
     * This method aids in updating a switch.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for switch as seen in HPE OneView.
     * @param switchDto
     *            Object containing the switch details.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateSwitch(RestParams params, String resourceId, Switch switchDto, boolean aSync);

    /**
     * This method aids in deletion of a switch.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for switch as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteSwitch(RestParams params, String resourceId, boolean aSync);

    /**
     * This method aids in refreshing a switch.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for switch as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 refreshSwitch(RestParams params, String resourceId, boolean aSync);

    /**
     * This method aids in fetching the environmental configuration for a switch.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for switch as seen in HPE OneView.
     * @return {@link EnvironmentalConfiguration} containing the environment configuration
     *         for the switch.
     */
    EnvironmentalConfiguration getSwitchEnvironmentalConfiguration(RestParams params, String resourceId);

    /**
     * This method aids in fetching the statistics for a switch.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for switch as seen in HPE OneView.
     * @return {@link SwitchStatistics} containing the statistics for the switch.
     */
    SwitchStatistics getSwitchStatistics(RestParams params, String resourceId);

    /**
     * This method aids in fetching the statistics for the switch.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for switch as seen in HPE OneView.
     * @param portName
     *            The port name as seen in HPE OneView for which the statistics
     *            data should be retrieved.
     * @return {@link SwitchPortStatistics} containing the statistics for a specific switch port.
     */
    SwitchPortStatistics getSwitchPortStatistics(RestParams params, String resourceId, String portName);

    /**
     * The module aids in fetching the switch details for the switch name as
     * specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the switch name as seen in HPE OneView.
     * @return {@link Switch} containing the switch details.
     */
    Switch getSwitchByName(RestParams params, String name);

    /**
     * The module aids in fetching the switch resource identifier for the switch name as
     * specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The resourceName is the switch name as seen in HPE OneView.
     * @return String which is the resource identifier for the switch name as seen in
     *         HPE OneView.
     */
    String getId(RestParams params, String name);

}
