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
package com.hp.ov.sdk.rest.client.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplate;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class StorageVolumeTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeTemplateClient.class);

    private final BaseClient baseClient;

    public StorageVolumeTemplateClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link StorageVolumeTemplate} details for the specified storage volume template.
     *
     * @param resourceId storage volume template resource identifier as seen in HPE OneView.
     *
     * @return {@link StorageVolumeTemplate} object containing the details.
     */
    public StorageVolumeTemplate getById(String resourceId) {
        LOGGER.info("StorageVolumeTemplateClient : getById : Start");

        StorageVolumeTemplate storageVolumeTemplate = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId), StorageVolumeTemplate.class);

        LOGGER.info("StorageVolumeTemplateClient : getById : End");

        return storageVolumeTemplate;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StorageVolumeTemplate}&gt; containing details
     * for all the available storage volume templates found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageVolumeTemplate}&gt; containing
     * the details for all found storage volume templates.
     */
    public ResourceCollection<StorageVolumeTemplate> getAll() {
        LOGGER.info("StorageVolumeTemplateClient : getAll : Start");

        ResourceCollection<StorageVolumeTemplate> storageVolumeTemplates = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, StorageVolumeTemplate.class);

        LOGGER.info("StorageVolumeTemplateClient : getAll : End");

        return storageVolumeTemplates;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StorageVolumeTemplate}&gt; containing details
     * for the available storage volume templates found under the current HPE OneView that match the name.
     *
     * @param name storage volume template name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageVolumeTemplate}&gt; containing
     * the details for the found storage volume templates.
     */
    public ResourceCollection<StorageVolumeTemplate> getByName(String name) {
        LOGGER.info("StorageVolumeTemplateClient : getByName : Start");

        ResourceCollection<StorageVolumeTemplate> storageVolumeTemplates = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, StorageVolumeTemplate.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("StorageVolumeTemplateClient : getByName : End");

        return storageVolumeTemplates;
    }

    /**
     * Creates a storage volume template according to the provided {@link StorageVolumeTemplate} object.
     *
     * @param storageVolumeTemplate object containing the storage volume template details.
     *
     * @return {@link StorageVolumeTemplate} containing the created storage volume template.
     */
    public StorageVolumeTemplate create(StorageVolumeTemplate storageVolumeTemplate) {
        LOGGER.info("StorageVolumeTemplateClient : create : Start");

        Request request = new Request(HttpMethodType.POST,
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, storageVolumeTemplate);

        StorageVolumeTemplate storageVolumeTemplateCreated = baseClient.executeRequest(request,
                StorageVolumeTemplate.class);

        LOGGER.info("StorageVolumeTemplateClient : create : End");

        return storageVolumeTemplateCreated;
    }

    /**
     * Updates a storage volume template identified by the given resource identifier.
     *
     * @param resourceId storage volume template resource identifier as seen in HPE OneView.
     * @param storageVolumeTemplate object containing the storage volume template details.
     *
     * @return {@link StorageVolumeTemplate} containing the updated storage volume template.
     */
    public StorageVolumeTemplate update(String resourceId, StorageVolumeTemplate storageVolumeTemplate) {
        LOGGER.info("StorageVolumeTemplateClient : update : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId), storageVolumeTemplate);

        StorageVolumeTemplate storageVolumeTemplateUpdated = this.baseClient.executeRequest(request,
                StorageVolumeTemplate.class);

        LOGGER.info("StorageVolumeTemplateClient : update : End");

        return storageVolumeTemplateUpdated;
    }

    /**
     * Deletes the storage volume template identified by the given resource identifier.
     *
     * @param resourceId storage volume template resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the result of the operation. The string "{}" indicates
     * that the server has fulfilled the request.
     */
    public String delete(String resourceId) {
        LOGGER.info("StorageVolumeTemplateClient : delete : Start");

        Request request = new Request(HttpMethodType.DELETE,
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        String response = baseClient.executeRequest(request, String.class);

        LOGGER.info("StorageVolumeTemplateClient : delete : End");

        return response;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ConnectableStorageVolumeTemplate}&gt; containing
     * details for all the storage volume templates available on the specified networks based on the storage
     * system port's expected network connectivity. If there are no storage volume templates that meets
     * the specified connectivity criteria an empty collection will be returned.
     *
     * @return {@link ResourceCollection}&lt;{@link ConnectableStorageVolumeTemplate}&gt; containing
     * the details for all found connectable storage volume templates.
     */
    public ResourceCollection<ConnectableStorageVolumeTemplate> getConnectableVolumeTemplates() {
        LOGGER.info("StorageVolumeTemplateClient : getAll : Start");

        ResourceCollection<ConnectableStorageVolumeTemplate> connectableStorageVolumeTemplates
                = baseClient.getResourceCollection(UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_TEMPLATE_URI,
                ResourceUris.STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI),
                ConnectableStorageVolumeTemplate.class);

        LOGGER.info("StorageVolumeTemplateClient : getAll : End");

        return connectableStorageVolumeTemplates;
    }

}
