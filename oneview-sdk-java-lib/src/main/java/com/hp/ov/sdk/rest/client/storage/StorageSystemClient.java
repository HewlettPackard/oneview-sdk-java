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

import java.util.List;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.storage.StoragePool;
import com.hp.ov.sdk.dto.storage.StorageSystem;
import com.hp.ov.sdk.dto.storage.StorageTargetPort;
import com.hp.ov.sdk.rest.client.common.AddableResource;
import com.hp.ov.sdk.rest.client.common.RemovableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(StorageSystemClient.STORAGE_SYSTEM_URI)
public interface StorageSystemClient extends
        SearchableResource<StorageSystem>,
        AddableResource<AddStorageSystemCredentials>,
        UpdatableResource<StorageSystem>,
        RemovableResource {

    String STORAGE_SYSTEM_URI = "/rest/storage-systems";

    String STORAGE_POOL_STORAGE_SYSTEM_URI = "/storage-pools";
    String STORAGE_SYSTEM_HOST_TYPES_URI = "/host-types";
    String STORAGE_SYSTEM_MANAGED_PORTS_URI = "/managedPorts";

    /**
     * Retrieves a list of storage pools belonging to the specified storage system.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing
     * the details for all found storage pools.
     */
    @Endpoint(uri = "/{resourceId}" + STORAGE_POOL_STORAGE_SYSTEM_URI)
    public ResourceCollection<StoragePool> getStoragePools(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves all managed target ports for the specified storage system.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageTargetPort}&gt; containing
     * the details for all found storage target ports.
     */
    @Endpoint(uri = "/{resourceId}" + STORAGE_SYSTEM_MANAGED_PORTS_URI)
    public ResourceCollection<StorageTargetPort> getAllManagedPorts(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves a specific managed target port for the specified storage system.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     * @param managedPortId target port identifier where the managed port is defined.
     *
     * @return {@link StorageTargetPort} containing the storage target port details.
     */
    @Endpoint(uri = "/{resourceId}" + STORAGE_SYSTEM_MANAGED_PORTS_URI + "/{managedPortId}")
    public StorageTargetPort getManagedPort(
            @PathParam("resourceId") String resourceId,
            @PathParam("managedPortId") String managedPortId);

    /**
     * Retrieves the list of supported storage host types.
     *
     * @return a list containing the names of the supported host types.
     */
    @Endpoint(uri = STORAGE_SYSTEM_HOST_TYPES_URI)
    public List<String> getHostTypes();

}
