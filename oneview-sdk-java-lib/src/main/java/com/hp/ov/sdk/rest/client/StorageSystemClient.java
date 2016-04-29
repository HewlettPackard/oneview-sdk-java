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

import java.util.List;

import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.dto.StorageSystemCollection;
import com.hp.ov.sdk.dto.StorageSystemV2;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.StorageTargetPortV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StorageSystemClient {

    /**
     * The module aids in fetching the storage system details for the specified
     * storage system resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage system as seen in HPE OneView.
     * @return {@link StorageSystemV2} containing the storage system details.
     */
    StorageSystemV2 getStorageSystem(final RestParams params, final String resourceId);

    /**
     * This module aids in retrieving a list of storage pools belonging to the
     * storage system referred by the path property {arrayId} parameter.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param arrayId
     *            The array identifier for a storage system as seen in HPE OneView.
     * @return {@link StoragePoolCollection} containing a collection of storage system details.
     */
    StoragePoolCollection getStoragePoolsForStorageSystem(final RestParams params, final String arrayId);

    /**
     * This module aids in retrieving all managed target ports for the specified storage system.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for a storage system as seen in HPE OneView.
     * @return {@link StorageTargetPortCollection} containing a collection of storage target port details.
     */
    StorageTargetPortCollection getAllManagedPortsForStorageSystem(final RestParams params, final String resourceId);

    /**
     * This module aids in retrieving a managed target ports for the specified storage system.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage system as seen in HPE OneView.
     * @param targetPortId
     *            The target port identifier where the managed port is defined.
     * @return {2link StorageTargetPortV2} containing the storage target port details.
     */
    StorageTargetPortV2 getManagedPortsForStorageSystem(final RestParams params, final String resourceId,
            final String targetPortId);

    /**
     * The module aids in fetching the list of supported storage host types.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return a list containing the names of the supported host types.
     */
    List<String> getStorageSystemHostTypes(final RestParams params);

    /**
     * The module aids in fetching the storage system details for all the
     * storage system found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link StorageSystemCollection} containing a collection storage system details.
     */
    StorageSystemCollection getAllStorageSystems(final RestParams params);

    /**
     * The module aids in fetching the storage system details for the
     * storage system name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the storage system name as seen in HPE OneView.
     * @return {@link StorageSystemV2} containing the storage system details.
     */
    StorageSystemV2 getStorageSystemByName(final RestParams params, final String name);

    /**
     * The module aids in creation of storage system when provided with the
     * storage system details as {@link AddStorageSystemCredentials} object or
     * JsonRequest.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param addStorageSystemCredentialsDto
     *            Object containing the storage system details, used to create a storage system.
     * @param useJsonRequest
     *            The JsonRequest body is part of AddStorageSystemCredentials object which
     *            takes in a String containing new storage system details, which
     *            is converted to AddStorageSystemCredentials object using an adaptor and
     *            processed.
     * @return String <code>Created</code> if successful.
     */
    String createStorageSystem(final RestParams params, final AddStorageSystemCredentials addStorageSystemCredentialsDto,
            final boolean useJsonRequest);

    /**
     * The module takes in an {@link StorageSystemV2} object or JsonRequest and updates
     * the existing storage system based on resource identifier. This can also be used
     * for updating the storage path of storage system.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage system as seen in HPE OneView.
     * @param storageSystemDto
     *            Object containing the storage system details, used to update a storage system.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageSystemV2 object which
     *            takes in a String containing the update to be made, which is
     *            converted to StorageSystemV2 object using an adaptor and processed.
     * @return String <code>Updated</code>if successful.
     */
    String updateStorageSystem(final RestParams params, final String resourceId, final StorageSystemV2 storageSystemDto,
            final boolean useJsonRequest);

    /**
     * The module aids in deleting a storage system for the specified storage system resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage system as seen in HPE OneView.
     * @return String <code>Deleted</code> if successful.
     */
    String deleteStorageSystem(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the storage system resource identifier for the
     * storage system name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the storage system name as seen in HPE OneView.
     * @return String which is the resource identifier for the storage system name as seen
     *         in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
