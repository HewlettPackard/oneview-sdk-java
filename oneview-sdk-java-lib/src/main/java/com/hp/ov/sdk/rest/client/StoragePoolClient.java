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

import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StoragePoolClient {

    /**
     * The module aids in fetching the StoragePool details for the specified
     * storage pool resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage pool as seen in HPE OneView.
     * @return {@link StoragePool} containing the storage pool details.
     */
    StoragePool getStoragePool(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the storage pool details for all the
     * storage pools found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing
     * the details for all found storage pools.
     */
    ResourceCollection<StoragePool> getAllStoragePools(final RestParams params);

    /**
     * The module aids in fetching the storage pool details for the storage pool
     * name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the storage pool name as seen in HPE OneView.
     * @param storageSystemUri
     *            URI of the storage system.
     * @return {@link StoragePool} containing the storage pool details.
     */
    StoragePool getStoragePoolByName(final RestParams params, final String name, final String storageSystemUri);

    /**
     * The module aids in creation of storage pool when provided with the
     * storage pool details as an AddStoragePool object or JsonRequest.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param storagePoolDto
     *            Object containing the storage pool details, used to create a storage pool.
     * @param useJsonRequest
     *            The JsonRequest body is part of AddStoragePool object which
     *            takes in a String containing the new storage pool details,
     *            which is converted to AddStoragePool object using adaptor and
     *            processed.
     * @return String <code>Created</code> if successful.
     */
    String createStoragePool(final RestParams params, final AddStoragePool storagePoolDto, final boolean useJsonRequest);

    /**
     * This module aids in refreshing the storage pool. To request a refresh of
     * a storage pool, the user must set the "refreshState" attribute to
     * {@link com.hp.ov.sdk.dto.RefreshState#RefreshPending} state.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage pool as seen in HPE OneView.
     * @param storagePoolDto
     *            Object containing the storage pool details, used to update a storage pool.
     * @param useJsonRequest
     *            The JsonRequest body is part of StoragePool object which takes
     *            in a String containing the update to be made, which is
     *            converted to StoragePool object using an adaptor and processed.
     * @return String, is Updated if successful.
     */
    String updateStoragePool(final RestParams params, final String resourceId, final StoragePool storagePoolDto,
            final boolean useJsonRequest);

    /**
     * The module aids in deleting a storage pool for the specified storage pool
     * resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage pool as seen in HPE OneView.
     * @return String <code>Deleted</code> if successful.
     */
    String deleteStoragePool(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the storage pool resource identifier for the storage pool
     * name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The resourceName is the storage pool name as seen in HPE OneView.
     * @param storageSystemUri
     *            The uri of the storage system the storage pool is associated with.
     * @return String which is the resource identifier for the storage pool name as seen
     *         in HPE OneView.
     */
    String getId(final RestParams params, final String name, final String storageSystemUri);

}
