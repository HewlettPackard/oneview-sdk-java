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
package com.hp.ov.sdk.rest.client.facilities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.facilities.datacenter.DataCenter;
import com.hp.ov.sdk.dto.facilities.datacenter.VisualContent;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class DataCenterClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataCenterClient.class);

    private final BaseClient baseClient;

    public DataCenterClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link DataCenter} details for the specified data center.
     *
     * @param resourceId data center resource identifier as seen in HPE OneView.
     *
     * @return {@link DataCenter} object containing the details.
     */
    public DataCenter getById(String resourceId) {
        LOGGER.info("DataCenterClient : getById : Start");

        DataCenter dataCenter = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.DATA_CENTER_URI, resourceId), DataCenter.class);

        LOGGER.info("DataCenterClient : getById : End");

        return dataCenter;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link DataCenter}&gt; containing details
     * for all the available data centers found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link DataCenter}&gt; containing
     * the details for all found data centers.
     */
    public ResourceCollection<DataCenter> getAll() {
        LOGGER.info("DataCenterClient : getAll : Start");

        ResourceCollection<DataCenter> dataCenters = baseClient.getResourceCollection(
                ResourceUris.DATA_CENTER_URI, DataCenter.class);

        LOGGER.info("DataCenterClient : getAll : End");

        return dataCenters;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link DataCenter}&gt; containing details
     * for the available data centers found under the current HPE OneView that match the name.
     *
     * @param name data center name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link DataCenter}&gt; containing
     * the details for the found data centers.
     */
    public ResourceCollection<DataCenter> getByName(String name) {
        LOGGER.info("DataCenterClient : getByName : Start");

        ResourceCollection<DataCenter> dataCenters = baseClient.getResourceCollection(
                ResourceUris.DATA_CENTER_URI, DataCenter.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("DataCenterClient : getByName : End");

        return dataCenters;
    }

    /**
     * Adds a data center according to the provided {@link DataCenter} object.
     *
     * @param dataCenter object containing the data center details.
     *
     * @return {@link DataCenter} containing the added data center.
     */
    public DataCenter add(DataCenter dataCenter) {
        LOGGER.info("DataCenterClient : add : Start");

        Request request = new Request(HttpMethodType.POST, ResourceUris.DATA_CENTER_URI, dataCenter);
        DataCenter createdDataCenter = this.baseClient.executeRequest(request, DataCenter.class);

        LOGGER.info("DataCenterClient : add : End");

        return createdDataCenter;
    }

    /**
     * Updates a {@link DataCenter} identified by the given resource identifier.
     *
     * @param resourceId data center resource identifier as seen in HPE OneView.
     * @param dataCenter object containing the data center details.
     *
     * @return {@link DataCenter} containing the data center updated.
     */
    public DataCenter update(String resourceId, DataCenter dataCenter) {
        LOGGER.info("DataCenterClient : update : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.DATA_CENTER_URI, resourceId), dataCenter);
        DataCenter updatedDataCenter = this.baseClient.executeRequest(request, DataCenter.class);

        LOGGER.info("DataCenterClient : update : End");

        return updatedDataCenter;
    }

    /**
     * Removes the {@link DataCenter} identified by the given resource identifier.
     *
     * @param resourceId data center resource identifier as seen in HPE OneView.
     *
     * @return String value containing the result of the process.
     */
    public String remove(String resourceId) {
        LOGGER.info("DataCenterClient : remove : Start");

        Request request = new Request(HttpMethodType.DELETE,
                UrlUtils.createUrl(ResourceUris.DATA_CENTER_URI, resourceId));
        String response = this.baseClient.executeRequest(request, String.class);

        LOGGER.info("DataCenterClient : remove : End");

        return response;
    }

    /**
     * Removes the {@link DataCenter}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 removeByFilter(String filter, boolean aSync) {
        LOGGER.info("DataCenterClient : removeByFilter : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(ResourceUris.DATA_CENTER_URI, aSync,
                new UrlParameter("filter", filter));

        LOGGER.info("DataCenterClient : removeByFilter : End");

        return taskResource;
    }

    /**
     * Retrieves a {@link List}&lt;{@link VisualContent}&gt; describing each rack
     * within the data center.
     *
     * @param resourceId data center resource identifier as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link VisualContent}&gt; containing the details of each rack
     * within the data center.
     */
    public List<VisualContent> getVisualContent(String resourceId) {
        LOGGER.info("DataCenterClient : getVisualContent : Start");

        List<VisualContent> visualContents = baseClient.getResourceList(UrlUtils.createUrl(
                ResourceUris.DATA_CENTER_URI, resourceId, ResourceUris.DATA_CENTER_VISUAL_CONTENT_URI),
                VisualContent.class);

        LOGGER.info("DataCenterClient : getVisualContent : End");

        return visualContents;
    }

}
