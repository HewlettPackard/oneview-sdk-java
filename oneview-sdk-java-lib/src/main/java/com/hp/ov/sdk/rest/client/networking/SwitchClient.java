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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.SwitchPortStatistics;
import com.hp.ov.sdk.dto.networking.SwitchStatistics;
import com.hp.ov.sdk.dto.networking.switches.Switch;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class SwitchClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchClient.class);

    private final BaseClient baseClient;

    public SwitchClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link Switch} details for the specified switch.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     *
     * @return {@link Switch} object containing the details.
     */
    public Switch getById(String resourceId) {
        LOGGER.info("SwitchClient : getById : Start");

        Switch switchObj = baseClient.getResource(UrlUtils.createUrl(ResourceUris.SWITCHES_URI, resourceId),
                Switch.class);

        LOGGER.info("SwitchClient : getById : End");

        return switchObj;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Switch}&gt; containing
     * details for all the available switches found under the current HPE
     * OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Switch}&gt; containing the
     *         details for all found switches.
     */
    public ResourceCollection<Switch> getAll() {
        LOGGER.info("SwitchClient : getAll : Start");

        ResourceCollection<Switch> switches = baseClient.getResourceCollection(ResourceUris.SWITCHES_URI, Switch.class);

        LOGGER.info("SwitchClient : getAll : End");

        return switches;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Switch}&gt; containing
     * details for the available switches found under the current HPE OneView
     * that match the name.
     *
     * @param name
     *            switch name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Switch}&gt; containing the
     *         details for the found switches.
     */
    public ResourceCollection<Switch> getByName(String name) {
        LOGGER.info("SwitchClient : getByName : Start");

        ResourceCollection<Switch> switches = baseClient.getResourceCollection(ResourceUris.SWITCHES_URI, Switch.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("SwitchClient : getByName : End");

        return switches;
    }

    /**
     * Adds a switch according to the provided {@link Switch} object. The
     * request can be processed synchronously or asynchronously.
     *
     * @param switchObj
     *            object containing the switch details.
     * @param aSync
     *            flag to indicate whether the request should be processed
     *            synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    public TaskResourceV2 add(Switch switchObj, boolean aSync) {
        LOGGER.info("SwitchClient : add : Start");

        TaskResourceV2 taskResource = baseClient.createResource(ResourceUris.SWITCHES_URI, switchObj, aSync);

        LOGGER.info("SwitchClient : add : End");

        return taskResource;
    }

    /**
     * Updates a {@link Switch} identified by the given resource identifier.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param switchObj
     *            object containing the switch details.
     * @param aSync
     *            flag to indicate whether the request should be processed
     *            synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    public TaskResourceV2 update(String resourceId, Switch switchObj, boolean aSync) {
        LOGGER.info("SwitchClient : update : Start");

        TaskResourceV2 taskResource = this.baseClient
                .updateResource(UrlUtils.createUrl(ResourceUris.SWITCHES_URI, resourceId), switchObj, aSync);

        LOGGER.info("SwitchClient : update : End");

        return taskResource;
    }

    /**
     * Removes the switch identified by the given resource identifier.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param aSync
     *            flag to indicate whether the request should be processed
     *            synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    public TaskResourceV2 remove(String resourceId, boolean aSync) {
        LOGGER.info("SwitchClient : remove : Start");

        TaskResourceV2 taskResource = baseClient
                .deleteResource(UrlUtils.createUrl(ResourceUris.SWITCHES_URI, resourceId), aSync);

        LOGGER.info("SwitchClient : remove : End");

        return taskResource;
    }

    /**
     * Requests a refresh for the specified switch.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param aSync
     *            flag to indicate whether the request should be processed
     *            synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    public TaskResourceV2 refresh(String resourceId, boolean aSync) {
        LOGGER.info("SwitchClient : refresh : Start");

        Request request = new Request(HttpMethodType.PUT, UrlUtils.createUrl(ResourceUris.SWITCHES_URI, resourceId));
        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("SwitchClient : refresh : End");

        return taskResource;
    }

    /**
     * Retrieves the environmental configuration for the specified switch.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} object containing the 
     *         environment configuration for the switch.
     */
    public EnvironmentalConfiguration getEnvironmentalConfiguration(String resourceId) {
        LOGGER.info("SwitchClient : getEnvironmentalConfiguration : Start");

        EnvironmentalConfiguration environmentalConfiguration = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SWITCHES_URI, resourceId, ResourceUris.ENVIRONMENT_CONFIGURATION_URI),
                EnvironmentalConfiguration.class);

        LOGGER.info("SwitchClient : getEnvironmentalConfiguration : End");

        return environmentalConfiguration;
    }

    /**
     * Retrieves the statistics for the specified switch.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     *
     * @return {@link SwitchStatistics} object containing the the statistics for
     *         the switch.
     */
    public SwitchStatistics getStatistics(String resourceId) {
        LOGGER.info("SwitchClient : getStatistics : Start");

        SwitchStatistics switchStatistics = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SWITCHES_URI, resourceId, ResourceUris.SWITCHES_STATISTICS_URI),
                SwitchStatistics.class);

        LOGGER.info("SwitchClient : getStatistics : End");

        return switchStatistics;
    }

    /**
     * Retrieves the statistics for the specified switch port.
     *
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param portName
     *            port name for which the statistics data should be retrieved.
     *
     * @return {@link SwitchStatistics} object containing the the statistics for
     *         the switch port.
     */
    public SwitchPortStatistics getPortStatistics(String resourceId, String portName) {
        LOGGER.info("SwitchClient : getPortStatistics : Start");

        SwitchPortStatistics switchPortStatistics = baseClient.getResource(UrlUtils.createUrl(ResourceUris.SWITCHES_URI,
                resourceId, ResourceUris.SWITCHES_STATISTICS_URI, portName), SwitchPortStatistics.class);

        LOGGER.info("SwitchClient : getPortStatistics : End");

        return switchPortStatistics;
    }

    /**
     * The module takes in a {@link List}&lt;{@link Port}&gt; object and updates
     * the existing switch ports based on the resource identifier.
     * 
     * @param resourceId
     *            switch resource identifier as seen in HPE OneView.
     * @param ports
     *            containing the switch ports details, used to update the switch
     *            ports.
     * @param aSync
     *            flag to indicate whether the request should be processed
     *            synchronously or asynchronously.
     * 
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    public TaskResourceV2 updatePorts(String resourceId, List<Port> ports, Boolean aSync) {
        LOGGER.info("SwitchClient : updatePorts : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.SWITCHES_URI, resourceId,
                ResourceUris.SWITCHES_UPDATE_PORTS_URI);

        Request request = new Request(HttpMethodType.PUT, updateUri, ports);
        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("SwitchClient : updatePorts : End");

        return taskResource;
    }
}
