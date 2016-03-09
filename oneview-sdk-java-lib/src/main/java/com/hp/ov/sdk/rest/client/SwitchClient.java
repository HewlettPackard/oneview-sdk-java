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
     * This method aids in fetching the switch for the resourceId specified
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for switch as seen in HP OneView.
     * @return switchDto, which is a object containing the switch details.
     */
    Switch getSwitch(RestParams params, String resourceId);

    /**
     * This method aids in fetching the top of rack switches.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return switchCollectionDto, which is a object containing the collection
     *         of switch details.
     */
    SwitchCollection getAllSwitches(RestParams params);


    /**
     * This method aids in creation of switch.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param switchObj
     *            switchDto, which is a object containing the switch details.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 createSwitch(RestParams params, Switch switchObj, boolean aSync);

    /**
     * This method aids in updating the switch.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for switch as seen in HP OneView.
     * @param switchDto
     *            switchDto, which is a object containing the switch details.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 updateSwitch(RestParams params, String resourceId, Switch switchDto, boolean aSync);

    /**
     * This method aids in deletion of switch
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for switch as seen in HP OneView.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 deleteSwitch(RestParams params, String resourceId, boolean aSync);

    /**
     * This method aids in refreshing the switch.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for switch as seen in HP OneView.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 refreshSwitch(RestParams params, String resourceId, boolean aSync);

    /**
     * This method aids in fetching the environmental configuration for the
     * switch.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for switch as seen in HP OneView.
     * @return environmentConfiguration, contains the environment configuration
     *         for the switch.
     */
    EnvironmentalConfiguration getSwitchEnvironmentalConfiguration(RestParams params, String resourceId);

    /**
     * This method aids in fetching the statistics for the switch.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for switch as seen in HP OneView.
     * @return {@link SwitchStatistics}, contains the statistics for the switch.
     */
    SwitchStatistics getSwitchStatistics(RestParams params, String resourceId);

    /**
     * This method aids in fetching the statistics for the switch.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for switch as seen in HP OneView.
     * @param portName
     *            The port name as seen in HP OneView for which the statistics
     *            data should be retrieved.
     * @return {@link SwitchPortStatistics}, contains the statistics for a
     *         specific switch port.
     */
    SwitchPortStatistics getSwitchPortStatistics(RestParams params, String resourceId, String portName);

    /**
     * The module aids in fetching the Switch details for the Switch name as
     * specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The name is the Switch name as seen in HP OneView.
     * @return switchDto, which is a object containing the Switch details.
     */
    Switch getSwitchByName(RestParams params, String name);

    /**
     * The module aids in fetching the Switch details for the Switch name as
     * specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Switch name as seen in HP OneView.
     * @return String, which is a resource Id for the Switch name as seen in
     *         HPOneView.
     */
    String getId(RestParams params, String name);

}
