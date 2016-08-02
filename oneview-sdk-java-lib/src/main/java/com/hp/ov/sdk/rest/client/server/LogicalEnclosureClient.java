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
package com.hp.ov.sdk.rest.client.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalEnclosureClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalEnclosureClient.class);
    private static final int TIMEOUT = 300000; // in milliseconds

    private final BaseClient baseClient;

    public LogicalEnclosureClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link LogicalEnclosure} details for the specified logical enclosure.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link LogicalEnclosure} object containing the details.
     */
    public LogicalEnclosure getById(String resourceId) {
        LOGGER.info("LogicalEnclosureClient : getById : Start");

        LogicalEnclosure logicalEnclosure = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId), LogicalEnclosure.class);

        LOGGER.info("LogicalEnclosureClient : getById : End");

        return logicalEnclosure;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalEnclosure}&gt; containing the details
     * for all the available logical enclosures found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalEnclosure}&gt; containing
     * the details for all found logical enclosures.
     */
    public ResourceCollection<LogicalEnclosure> getAll() {
        LOGGER.info("LogicalEnclosureClient : getAll : Start");

        ResourceCollection<LogicalEnclosure> logicalEnclosures = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_ENCLOSURE_URI, LogicalEnclosure.class);

        LOGGER.info("LogicalEnclosureClient : getAll : End");

        return logicalEnclosures;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalEnclosure}&gt; containing details
     * for the available logical enclosures found under the current HPE OneView that match the name.
     *
     * @param name logical enclosure name as seen in HPE OneView.
     *
     * @return {@link LogicalEnclosure} object containing the details.
     */
    public ResourceCollection<LogicalEnclosure> getByName(String name) {
        LOGGER.info("LogicalEnclosureClient : getByName : Start");

        ResourceCollection<LogicalEnclosure> logicalEnclosures = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_ENCLOSURE_URI, LogicalEnclosure.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("LogicalEnclosureClient : getByName : End");

        return logicalEnclosures;
    }

    /**
     * Creates a logical enclosure according to the provided {@link LogicalEnclosure} object.
     *
     * @param logicalEnclosure object containing the logical enclosure details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(AddLogicalEnclosure logicalEnclosure, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : create : Start");

        TaskResourceV2 taskResource = this.baseClient.createResource(
                ResourceUris.LOGICAL_ENCLOSURE_URI, logicalEnclosure, aSync);

        LOGGER.info("LogicalEnclosureClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link LogicalEnclosure} identified by the given resource identifier.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     * @param logicalEnclosure object containing the logical enclosure details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, LogicalEnclosure logicalEnclosure, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : update : Start");

        TaskResourceV2 taskResource = this.baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId),
                logicalEnclosure, aSync);

        LOGGER.info("LogicalEnclosureClient : update : End");

        return taskResource;
    }

    /**
     * Performs a PATCH request and updates the existing logical enclosure based
     * on the resource identifier and the content of the {@link Patch} object.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     * @param patch object containing the update to be made to existing logical enclosure.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 patch(String resourceId, Patch patch, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : patch : Start");

        Request request = new Request(HttpMethodType.PATCH,
                UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId), patch);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalEnclosureClient : patch : End");

        return taskResource;
    }

    /**
     * Deletes the {@link LogicalEnclosure} identified by the given resource identifier.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : delete : Start");

        TaskResourceV2 taskResource = this.baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId), aSync);

        LOGGER.info("LogicalEnclosureClient : delete : End");

        return taskResource;
    }

    /**
     * Updates a {@link LogicalEnclosure} identified by the given resource identifier
     * to be consistent with the enclosure group when the logical enclosure is in
     * an inconsistent state.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateFromGroup(String resourceId, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : updateFromGroup : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId,
                ResourceUris.LOGICAL_ENCLOSURE_UPDATE_FROM_GROUP_URI);
        Request request = new Request(HttpMethodType.PUT, updateUri);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalEnclosureClient : updateFromGroup : End");

        return taskResource;
    }

    /**
     * Reapplies the logical enclosure configuration in the logical enclosure
     * identified by the given resource identifier.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateConfiguration(String resourceId, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : updateConfiguration : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId,
                ResourceUris.LOGICAL_ENCLOSURE_CONFIGURATION_URI);
        Request request = new Request(HttpMethodType.PUT, updateUri);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalEnclosureClient : updateConfiguration : End");

        return taskResource;
    }

    /**
     * Retrieves the configuration script for the specified logical enclosure resource identifier.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     *
     * @return the configuration script for the specified logical enclosure.
     */
    public String getConfigurationScript(String resourceId) {
        LOGGER.info("LogicalEnclosureClient : getConfigurationScript : Start");

        Request request = new Request(HttpMethodType.GET,
                UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId,
                        ResourceUris.LOGICAL_ENCLOSURE_SCRIPT_URI));

        String response = baseClient.executeRequest(request, String.class);

        LOGGER.info("LogicalEnclosureClient : getConfigurationScript : End");

        return response;
    }

    /**
     * Updates the configuration script for the specified logical enclosure resource identifier.
     * 
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     * @param scriptData script data to be updated for logical enclosure.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateConfigurationScript(String resourceId, String scriptData, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : updateConfigurationScript : Start");

        String updateUri = UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId,
                ResourceUris.LOGICAL_ENCLOSURE_SCRIPT_URI);

        TaskResourceV2 taskResource = this.baseClient.updateResource(updateUri, scriptData, aSync);

        LOGGER.info("LogicalEnclosureClient : updateConfigurationScript : End");

        return taskResource;
    }

    /**
     * Creates a support dump for the logical enclosure with the specified resource identifier.
     * A logical enclosure support dump includes content for logical interconnects associated
     * with that logical enclosure. By default, it also contains appliance support dump content.
     *
     * @param resourceId logical enclosure resource identifier as seen in HPE OneView.
     * @param supportDump details to create the support dump.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 createSupportDump(String resourceId, SupportDump supportDump, boolean aSync) {
        LOGGER.info("LogicalEnclosureClient : createSupportDump : Start");

        String createUri = UrlUtils.createUrl(ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId,
                ResourceUris.LOGICAL_ENCLOSURE_SUPPORT_DUMP_URI);

        Request request = new Request(HttpMethodType.POST, createUri, supportDump);

        request.setTimeout(300000);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalEnclosureClient : createSupportDump : End");

        return taskResource;
    }
}
