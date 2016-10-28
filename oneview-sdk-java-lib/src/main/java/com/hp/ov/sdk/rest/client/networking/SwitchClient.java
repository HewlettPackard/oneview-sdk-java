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

import java.util.List;

import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.SwitchPortStatistics;
import com.hp.ov.sdk.dto.networking.SwitchStatistics;
import com.hp.ov.sdk.dto.networking.switches.Switch;
import com.hp.ov.sdk.rest.client.common.AddableResource;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.RemovableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(SwitchClient.SWITCHES_URI)
public interface SwitchClient extends
        SearchableResource<Switch>,
        AddableResource<Switch>,
        UpdatableResource<Switch>,
        RemovableResource,
        PatchableResource {

    String SWITCHES_URI = "/rest/switches";
    String SWITCHES_ENVIRONMENT_CONFIGURATION_URI = "/environmentalConfiguration";
    String SWITCHES_REFRESH_URI = "/refresh";
    String SWITCHES_STATISTICS_URI = "/statistics";
    String SWITCHES_UPDATE_PORTS_URI = "/update-ports";

    /**
     * Requests a refresh for the specified switch.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the
     *         process.
     */
    @Endpoint(uri = "/{resourceId}" + SWITCHES_REFRESH_URI, method = HttpMethod.PATCH)
    TaskResource refresh(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * Retrieves the environmental configuration for the specified switch.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} object containing the
     *         environment configuration for the switch.
     */
    @Endpoint(uri = "/{resourceId}" + SWITCHES_ENVIRONMENT_CONFIGURATION_URI)
    EnvironmentalConfiguration getEnvironmentalConfiguration(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the statistics for the specified switch.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     *
     * @return {@link SwitchStatistics} object containing the the statistics for
     *         the switch.
     */
    @Endpoint(uri = "/{resourceId}" + SWITCHES_STATISTICS_URI)
    SwitchStatistics getStatistics(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the statistics for the specified switch port.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param portName
     *            port name for which the statistics data should be retrieved.
     *
     * @return {@link SwitchPortStatistics} object containing the the statistics for
     *         the switch port.
     */
    @Endpoint(uri = "/{resourceId}" + SWITCHES_STATISTICS_URI + "/{portName}")
    SwitchPortStatistics getPortStatistics(@PathParam("resourceId") String resourceId, @PathParam("portName") String portName);

    /**
     * The module takes in a {@link List}&lt;{@link Port}&gt; object and updates
     * the existing switch ports based on the resource identifier.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param ports
     *            containing the switch ports details, used to update the switch
     *            ports.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the
     *         process.
     */
    @Endpoint(uri = "/{resourceId}" + SWITCHES_UPDATE_PORTS_URI, method = HttpMethod.PUT, forceReturnTask = true)
    TaskResource updatePorts(@PathParam("resourceId") String resourceId, @BodyParam List<Port> ports, RequestOption... options);

}
