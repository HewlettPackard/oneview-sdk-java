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

import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplate;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StorageVolumeTemplateClient {

    /**
     * The module aids in fetching the storage volume template details for the
     * specified storage volume template resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage volume template as seen in HPE OneView.
     * @return {@link StorageVolumeTemplate} containing the storage volume template details.
     */
    StorageVolumeTemplate getStorageVolumeTemplate(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the storage volume template details for all the
     * storage volume templates found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link StorageVolumeTemplate}&gt; containing
     * the details for all storage volume templates.
     */
    ResourceCollection<StorageVolumeTemplate> getAllStorageVolumeTemplates(final RestParams params);

    /**
     * The module aids in fetching the storage volume template details for the
     * storage volume template name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the storage volume template name as seen in HPE OneView.
     * @return {@link StorageVolumeTemplate} containing the storage volume template details.
     */
    StorageVolumeTemplate getStorageVolumeTemplateByName(final RestParams params, final String name);

    /**
     * The module aids in the creation of a storage volume template when provided with
     * the storage volume template details as a StorageVolumeTemplate object or
     * JsonRequest. It can process the request asynchronously or synchronously,
     * based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param storageVolumeTemplateDto
     *            Object containing the storage volume template details,
     *            used to create a storage volume template.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageVolumeTemplate object
     *            which takes in a String containing the new storage volume template
     *            details, which is converted to a StorageVolumeTemplate object
     *            using an adaptor and processed.
     * @return {@link StorageVolumeTemplate} containing the storage volume template details.
     */
    StorageVolumeTemplate createStorageVolumeTemplate(final RestParams params,
            final StorageVolumeTemplate storageVolumeTemplateDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in an StorageVolumeTemplate object or JsonRequest and
     * updates the existing storage volume template based on the resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage volume template as seen in HPE OneView.
     * @param storageVolumeTemplateDto
     *            This is a object containing the storage volume template details,
     *            used to update a storage volume template.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageVolumeTemplate object
     *            which takes in a String containing the update to be made,
     *            which is converted to StorageVolumeTemplate object using an
     *            adaptor and processed.
     * @return {@link StorageVolumeTemplate} containing the storage volume template details.
     */
    StorageVolumeTemplate updateStorageVolumeTemplate(final RestParams params, final String resourceId,
            final StorageVolumeTemplate storageVolumeTemplateDto, final boolean useJsonRequest);

    /**
     * The module aids in deleting a storage volume template for the specified
     * storage volume template resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage volume template as seen in HPE OneView.
     * @return String <code>Deleted</code> if successful.
     */
    String deleteStorageVolumeTemplate(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the storage volume templates that are
     * available on the specified networks based on the storage system port's
     * expected network connectivity. If there are no storage volume templates
     * that meets the specified connectivity criteria, an empty collection will
     * be returned.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link ConnectableStorageVolumeTemplate}&gt; containing
     * the details for all found connectable storage volume templates.
     *
     */
    ResourceCollection<ConnectableStorageVolumeTemplate> getConnectableVolumeTemplates(final RestParams params);

    /**
     * The module aids in fetching the storage volume template resource identifier for the
     * storage volume template name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the storage volume template name as seen in HPE OneView.
     * @return String which is the resource identifier for the storage volume template name
     *         as seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);

}
