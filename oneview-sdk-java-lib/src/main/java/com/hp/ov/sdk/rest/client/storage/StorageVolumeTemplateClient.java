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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.ConnectableStorageVolumeTemplate;
import com.hp.ov.sdk.dto.storage.StorageVolumeTemplate;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(StorageVolumeTemplateClient.STORAGE_VOLUME_TEMPLATE_URI)
public interface StorageVolumeTemplateClient extends SearchableResource<StorageVolumeTemplate> {

    String STORAGE_VOLUME_TEMPLATE_URI = "/rest/storage-volume-templates";
    String STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI = "/connectable-volume-templates";

    /**
     * Creates a storage volume template according to the provided {@link StorageVolumeTemplate} object.
     *
     * @param storageVolumeTemplate object containing the storage volume template details.
     *
     * @return {@link StorageVolumeTemplate} containing the created storage volume template.
     */
    @Endpoint(method = HttpMethod.POST)
    StorageVolumeTemplate create(@BodyParam StorageVolumeTemplate storageVolumeTemplate);

    /**
     * Updates a storage volume template identified by the given resource identifier.
     *
     * @param resourceId storage volume template resource identifier as seen in HPE OneView.
     * @param storageVolumeTemplate object containing the storage volume template details.
     *
     * @return {@link StorageVolumeTemplate} containing the updated storage volume template.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    StorageVolumeTemplate update(@PathParam("resourceId") String resourceId,
            @BodyParam StorageVolumeTemplate storageVolumeTemplate);

    /**
     * Deletes the storage volume template identified by the given resource identifier.
     *
     * @param resourceId storage volume template resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the result of the operation. The string "{}" indicates
     * that the server has fulfilled the request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String delete(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ConnectableStorageVolumeTemplate}&gt; containing
     * details for all the storage volume templates available on the specified networks based on the storage
     * system port's expected network connectivity. If there are no storage volume templates that meets
     * the specified connectivity criteria an empty collection will be returned.
     *
     * @return {@link ResourceCollection}&lt;{@link ConnectableStorageVolumeTemplate}&gt; containing
     * the details for all found connectable storage volume templates.
     */
    @Endpoint(uri = STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI)
    ResourceCollection<ConnectableStorageVolumeTemplate> getConnectableVolumeTemplates();

}
