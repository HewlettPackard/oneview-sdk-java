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

import java.util.List;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.datacenter.DataCenter;
import com.hp.ov.sdk.dto.datacenter.VisualContent;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface DataCenterClient {

    /**
     * Retrieves the {@link DataCenter} details for the specified
     * data center.
     *
     * @param params structure containing the connection details.
     * @param resourceId data center resource identifier as seen in HPE OneView.
     *
     * @return {@link DataCenter} object containing the details.
     */
    DataCenter getDataCenter(RestParams params, String resourceId);

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link DataCenter}&gt; containing details
     * for all the available data centers found under the current HPE OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link ResourceCollection}&lt;{@link DataCenter}&gt; containing
     * the details for all found data centers.
     */
    ResourceCollection<DataCenter> getAllDataCenters(RestParams params);

    /**
     * Retrieves the {@link DataCenter} details for the specified
     * data center identified by name.
     *
     * @param params structure containing the connection details.
     * @param name data center name as seen in HPE OneView.
     *
     * @return {@link DataCenter} object containing the details.
     */
    DataCenter getDataCenterByName(RestParams params, String name);

    /**
     * Adds a data center according to the provided {@link DataCenter} object.
     *
     * @param params structure containing the connection details.
     * @param dataCenter object containing the data center details.
     *
     * @return {@link DataCenter} containing the added data center.
     */
    DataCenter addDataCenter(RestParams params, DataCenter dataCenter);

    /**
     * Updates a {@link DataCenter} identified by the given resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId data center resource identifier as seen in HPE OneView.
     * @param dataCenter object containing the data center details.
     *
     * @return {@link DataCenter} containing the data center updated.
     */
    DataCenter updateDataCenter(RestParams params, String resourceId, DataCenter dataCenter);

    /**
     * Removes the {@link DataCenter} identified by the given resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId data center resource identifier as seen in HPE OneView.
     *
     * @return String value containing the result of the process.
     */
    String removeDataCenter(RestParams params, String resourceId);

    /**
     * Removes the {@link DataCenter}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param params structure containing the connection details.
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 removeDataCenterByFilter(RestParams params, String filter, boolean aSync);

    /**
     * Retrieves a {@link List}&lt;{@link VisualContent}&gt; describing each rack
     * within the data center.
     *
     * @param params structure containing the connection details.
     * @param resourceId data center resource identifier as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link VisualContent}&gt; containing the details of each rack
     * within the data center.
     */
    List<VisualContent> getVisualContent(RestParams params, String resourceId);

    /**
     * Retrieves the data center resource identifier for the specified
     * data center identified by name.
     *
     * @param params structure containing the connection details.
     * @param name data center name as seen in HPE OneView.
     *
     * @return resource identifier for the data center name as seen in HPE OneView.
     */
    String getId(RestParams params, final String name);
}
