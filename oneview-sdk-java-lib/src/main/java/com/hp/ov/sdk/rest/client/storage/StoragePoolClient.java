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

import com.google.common.base.Predicate;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class StoragePoolClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoragePoolClient.class);

    private final BaseClient baseClient;

    public StoragePoolClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link StoragePool} details for the specified storage pool.
     *
     * @param resourceId storage pool resource identifier as seen in HPE OneView.
     *
     * @return {@link StoragePool} object containing the details.
     */
    public StoragePool getById(String resourceId) {
        LOGGER.info("StoragePoolClient : getById : Start");

        StoragePool storagePool = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_POOL_URI, resourceId), StoragePool.class);

        LOGGER.info("StoragePoolClient : getById : End");

        return storagePool;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing details
     * for all the available storage pools found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing
     * the details for all found storage pools.
     */
    public ResourceCollection<StoragePool> getAll() {
        LOGGER.info("StoragePoolClient : getAll : Start");

        ResourceCollection<StoragePool> storagePools = baseClient.getResourceCollection(
                ResourceUris.STORAGE_POOL_URI, StoragePool.class);

        LOGGER.info("StoragePoolClient : getAll : End");

        return storagePools;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing details
     * for the available storage pools found under the current HPE OneView that match the name.
     *
     * @param name storage pool name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing
     * the details for the found storage pools.
     */
    public ResourceCollection<StoragePool> getByName(String name) {
        LOGGER.info("StoragePoolClient : getByName : Start");

        ResourceCollection<StoragePool> storagePools = baseClient.getResourceCollection(
                ResourceUris.STORAGE_POOL_URI, StoragePool.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("StoragePoolClient : getByName : End");

        return storagePools;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StoragePool}&gt; containing details
     * for the available storage pools found under the current HPE OneView that match the name
     * and are associated to the given storage system.
     *
     * @param name storage pool name as seen in HPE OneView.
     * @param storageSystemUri the storage system to which the storage pool is located.
     *
     * @return {@link List}&lt;{@link StoragePool}&gt; containing the details for the
     * found storage pools for the given storage system.
     */
    public List<StoragePool> getByName(String name, final String storageSystemUri) {
        LOGGER.info("StoragePoolClient : getByName : Start");

        ResourceCollection<StoragePool> storagePools = this.getByName(name);

        List<StoragePool> filteredStoragePools = storagePools.getMembers(new Predicate<StoragePool>() {
            @Override
            public boolean apply(StoragePool input) {
                return (storageSystemUri.equalsIgnoreCase(input.getStorageSystemUri()));
            }
        });

        LOGGER.info("StoragePoolClient : getByName : End");

        return filteredStoragePools;
    }

    /**
     * Adds a storage pool according to the provided {@link AddStoragePool} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param storagePool object containing the storage pool details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 add(AddStoragePool storagePool, boolean aSync) {
        LOGGER.info("StoragePoolClient : add : Start");

        Request request = new Request(HttpMethod.POST, ResourceUris.STORAGE_POOL_URI, storagePool);

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("StoragePoolClient : add : End");

        return taskResource;
    }

    /**
     * Updates a storage pool identified by the given resource identifier.
     *
     * @param resourceId storage pool resource identifier as seen in HPE OneView.
     * @param storagePool object containing the storage pool details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, StoragePool storagePool, boolean aSync) {
        LOGGER.info("StoragePoolClient : update : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(ResourceUris.STORAGE_POOL_URI, resourceId), storagePool);

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("StoragePoolClient : update : End");

        return taskResource;
    }

    /**
     * Removes the storage pool identified by the given resource identifier.
     *
     * @param resourceId storage pool resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 remove(String resourceId, boolean aSync) {
        LOGGER.info("StoragePoolClient : remove : Start");

        Request request = new Request(HttpMethod.DELETE,
                UrlUtils.createUrl(ResourceUris.STORAGE_POOL_URI, resourceId));

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("StoragePoolClient : remove : End");

        return taskResource;
    }

}
