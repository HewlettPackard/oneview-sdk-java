/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplateCollection;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StorageVolumeTemplateClient {

    /**
     * The module aids in fetching the StorageVolumeTemplate details for the
     * specified StorageVolumeTemplate resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolumeTemplate as seen in HP
     *            OneView.
     * @return storageVolumeTemplateDto, which is a object containing the
     *         StorageVolumeTemplate details.
     */
    public StorageVolumeTemplate getStorageVolumeTemplate(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the StorageVolumeTemplate details for all the
     * StorageVolumeTemplate found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return storageVolumeTemplateCollectionDto, which is a object containing
     *         a collection of StorageVolumeTemplate details.
     */
    public StorageVolumeTemplateCollection getAllStorageVolumeTemplates(final RestParams params);

    /**
     * The module aids in fetching the StorageVolumeTemplate details for the
     * StorageVolumeTemplate name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The name is the StorageVolumeTemplate name as seen in HP
     *            OneView.
     * @return storageVolumeTemplateDto, which is a object containing the
     *         StorageVolumeTemplate details.
     */
    public StorageVolumeTemplate getStorageVolumeTemplateByName(final RestParams params, final String name);

    /**
     * The module aids in creation of StorageVolumeTemplate when provided with
     * the StorageVolumeTemplate details as StorageVolumeTemplate object or
     * JsonRequest.It can process the request asynchronously or synchronously
     * based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param storageVolumeTemplateDto
     *            This is a object containing the StorageVolumeTemplate details,
     *            used to create a StorageVolumeTemplate.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageVolumeTemplate Object
     *            which takes in a String containing new StorageVolumeTemplate
     *            details, which is converted to StorageVolumeTemplate Object
     *            using adaptor and processed.
     * @return storageVolumeTemplateDto, which is a object containing the
     *         StorageVolumeTemplate details.
     */
    public StorageVolumeTemplate createStorageVolumeTemplate(final RestParams params,
            final StorageVolumeTemplate storageVolumeTemplateDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in an StorageVolumeTemplate object or JsonRequest and
     * updates the existing StorageVolumeTemplate based on resource Id.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolumeTemplate as seen in HP
     *            OneView.
     * @param storageVolumeTemplateDto
     *            This is a object containing the StorageVolumeTemplate details,
     *            used to update a StorageVolumeTemplate.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageVolumeTemplate Object
     *            which takes in a String containing the update to be made,
     *            which is converted to StorageVolumeTemplate Object using
     *            adaptor and processed.
     * @return storageVolumeTemplateDto, which is a object containing the
     *         StorageVolumeTemplate details.
     */
    public StorageVolumeTemplate updateStorageVolumeTemplate(final RestParams params, final String resourceId,
            final StorageVolumeTemplate storageVolumeTemplateDto, final boolean useJsonRequest);

    /**
     * The module aids in deleting a StorageVolumeTemplate for the specified
     * StorageVolumeTemplate resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolumeTemplate as seen in HP
     *            OneView.
     * @return String, is Deleted if successful.
     */
    public String deleteStorageVolumeTemplate(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the storage volume templates that are
     * available on the specified networks based on the storage system port's
     * expected network connectivity. If there are no storage volume templates
     * that meets the specified connectivity criteria an empty collection will
     * be returned
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return ConnectableStorageVolumeTemplateCollection, fetches the storage
     *         volume template on specified networks based on the storage system
     *         port.
     * 
     */
    public ConnectableStorageVolumeTemplateCollection getConnectableVolumeTemplates(final RestParams params);

    /**
     * The module aids in fetching the StorageVolumeTemplate details for the
     * StorageVolumeTemplate name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the StorageVolumeTemplate name as seen in
     *            HP OneView.
     * @return String, which is a resource Id for the StorageVolumeTemplate name
     *         as seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);

}
