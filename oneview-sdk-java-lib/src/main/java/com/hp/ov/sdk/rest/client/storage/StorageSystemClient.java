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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StorageSystem;
import com.hp.ov.sdk.dto.StorageTargetPort;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class StorageSystemClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageSystemClient.class);

    private final BaseClient baseClient;

    public StorageSystemClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link StorageSystem} details for the specified storage system.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     *
     * @return {@link StorageSystem} object containing the details.
     */
    public StorageSystem getById(String resourceId) {
        LOGGER.info("StorageSystemClient : getById : Start");

        StorageSystem storageSystem = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_SYSTEM_URI, resourceId), StorageSystem.class);

        LOGGER.info("StorageSystemClient : getById : End");

        return storageSystem;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StorageSystem}&gt; containing details
     * for all the available storage systems found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageSystem}&gt; containing
     * the details for all found storage systems.
     */
    public ResourceCollection<StorageSystem> getAll() {
        LOGGER.info("StorageSystemClient : getAll : Start");

        ResourceCollection<StorageSystem> storageSystems = baseClient.getResourceCollection(
                ResourceUris.STORAGE_SYSTEM_URI, StorageSystem.class);

        LOGGER.info("StorageSystemClient : getAll : End");

        return storageSystems;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StorageSystem}&gt; containing details
     * for the available storage systems found under the current HPE OneView that match the name.
     *
     * @param name storage system name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageSystem}&gt; containing
     * the details for the found storage systems.
     */
    public ResourceCollection<StorageSystem> getByName(String name) {
        LOGGER.info("StorageSystemClient : getByName : Start");

        ResourceCollection<StorageSystem> storageSystems = baseClient.getResourceCollection(
                ResourceUris.STORAGE_SYSTEM_URI, StorageSystem.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("StorageSystemClient : getByName : End");

        return storageSystems;
    }

    /**
     * Adds a storage system according to the provided {@link AddStorageSystemCredentials} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param storageSystemCredentials object containing the storage system credential details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 add(AddStorageSystemCredentials storageSystemCredentials, boolean aSync) {
        LOGGER.info("StorageSystemClient : add : Start");

        Request request = new Request(HttpMethod.POST, ResourceUris.STORAGE_SYSTEM_URI, storageSystemCredentials);

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("StorageSystemClient : add : End");

        return taskResource;
    }

    /**
     * Updates a storage system identified by the given resource identifier.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     * @param storageSystem object containing the storage system details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, StorageSystem storageSystem, boolean aSync) {
        LOGGER.info("StorageSystemClient : update : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(ResourceUris.STORAGE_SYSTEM_URI, resourceId), storageSystem);

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("StorageSystemClient : update : End");

        return taskResource;
    }

    /**
     * Removes the storage system identified by the given resource identifier.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 remove(String resourceId, boolean aSync) {
        LOGGER.info("StorageSystemClient : remove : Start");

        Request request = new Request(HttpMethod.DELETE,
                UrlUtils.createUrl(ResourceUris.STORAGE_SYSTEM_URI, resourceId));

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("StorageSystemClient : remove : End");

        return taskResource;
    }

    /**
     * Retrieves a list of storage pools belonging to the specified storage system.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing
     * the details for all found storage pools.
     */
    public ResourceCollection<StoragePool> getStoragePools(String resourceId) {
        LOGGER.info("StorageSystemClient : getStoragePools : Start");

        ResourceCollection<StoragePool> storagePools = baseClient.getResourceCollection(UrlUtils.createUrl(
                ResourceUris.STORAGE_SYSTEM_URI, resourceId, ResourceUris.STORAGE_POOL_STORAGE_SYSTEM_URI),
                StoragePool.class);

        LOGGER.info("StorageSystemClient : getStoragePools : End");

        return storagePools;
    }

    /**
     * Retrieves all managed target ports for the specified storage system.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageTargetPort}&gt; containing
     * the details for all found storage target ports.
     */
    public ResourceCollection<StorageTargetPort> getAllManagedPorts(String resourceId) {
        LOGGER.info("StorageSystemClient : getAllManagedPorts : Start");

        ResourceCollection<StorageTargetPort> allManagedPorts = baseClient.getResourceCollection(UrlUtils.createUrl(
                ResourceUris.STORAGE_SYSTEM_URI, resourceId, ResourceUris.MANAGED_PORTS_STORAGE_SYSTEM_URI),
                StorageTargetPort.class);

        LOGGER.info("StorageSystemClient : getAllManagedPorts : End");

        return allManagedPorts;
    }

    /**
     * Retrieves a specific managed target port for the specified storage system.
     *
     * @param resourceId storage system resource identifier as seen in HPE OneView.
     * @param managedPortId target port identifier where the managed port is defined.
     *
     * @return {@link StorageTargetPort} containing the storage target port details.
     */
    public StorageTargetPort getManagedPort(String resourceId, String managedPortId) {
        LOGGER.info("StorageSystemClient : getManagedPort : Start");

        StorageTargetPort managedPort = baseClient.getResource(UrlUtils.createUrl(
                ResourceUris.STORAGE_SYSTEM_URI, resourceId,
                ResourceUris.MANAGED_PORTS_STORAGE_SYSTEM_URI, managedPortId),
                StorageTargetPort.class);

        LOGGER.info("StorageSystemClient : getManagedPort : End");

        return managedPort;
    }

    /**
     * Retrieves the list of supported storage host types.
     *
     * @return a list containing the names of the supported host types.
     */
    public List<String> getHostTypes() {
        LOGGER.info("StorageSystemClient : getHostTypes : Start");

        List<String> hostTypes = baseClient.getResourceList(ResourceUris.STORAGE_SYSTEM_HOST_TYPES_URI, String.class);

        LOGGER.info("StorageSystemClient : getHostTypes : End");

        return hostTypes;
    }

}
