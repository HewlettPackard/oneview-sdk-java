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
package com.hp.ov.sdk.rest.client.storage;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StorageSystem;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * StoragePoolClientSample is a sample program consumes the set of disk from the storage system managed
 * by HPE OneView. It invokes APIs of StoragePoolClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on storage pool resource
 */
public class StoragePoolClientSample {

    private final StoragePoolClient storagePoolClient;
    private final StorageSystemClient storageSystemClient;

    // These are variables to be defined by user
    // ================================
    public static final String STORAGE_POOL_NAME = "FST_CPG1";

    private static final String STORAGE_POOL_RESOURCE_ID = "7345EE57-7544-4092-9311-102E6AE30CDF";
    // ================================

    private StoragePoolClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.storagePoolClient = oneViewClient.storagePool();
        this.storageSystemClient = oneViewClient.storageSystem();
    }

    private void getStoragePoolById() {
        StoragePool storagePool = this.storagePoolClient.getById(STORAGE_POOL_RESOURCE_ID);

        System.out.println("StoragePoolClientSample : getStoragePool : " +
                "StoragePool object returned to client : " + storagePool.toJsonString());
    }

    private void getAllStoragePools() {
        ResourceCollection<StoragePool> storagePools = this.storagePoolClient.getAll();

        System.out.println("StoragePoolClientSample : getAllStoragePools : " +
                "StoragePools returned to client : " + storagePools.toJsonString());
    }

    private void getStoragePoolByName() {
        StoragePool storagePool = this.storagePoolClient.getByName(STORAGE_POOL_NAME, getStorageSystem().getUri()).get(0);

        System.out.println("StoragePoolClientSample : getStoragePoolByName : " +
                "StoragePool object returned to client : " + storagePool.toJsonString());
    }

    private void addStoragePool() {
        AddStoragePool addStoragePool = buildAddStoragePool();

        TaskResourceV2 taskResource = storagePoolClient.add(addStoragePool, false);

        System.out.println("StoragePoolClientSample : addStoragePool : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateStoragePool() {
        StoragePool storagePool = this.storagePoolClient.getByName(STORAGE_POOL_NAME, getStorageSystem().getUri()).get(0);

        storagePool.setRefreshState(RefreshState.RefreshPending);

        TaskResourceV2 taskResource = storagePoolClient.update(storagePool.getResourceId(), storagePool, false);

        System.out.println("StoragePoolClientSample : updateStoragePool : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void removeStoragePool() {
        StoragePool storagePool = this.storagePoolClient.getByName(STORAGE_POOL_NAME, getStorageSystem().getUri()).get(0);
        TaskResourceV2 taskResource = this.storagePoolClient.remove(storagePool.getResourceId(), false);

        System.out.println("StoragePoolClientSample : removeStoragePool : " +
                "Task object returned to client : " + taskResource);
    }

    private AddStoragePool buildAddStoragePool() {
        AddStoragePool addStoragePool = new AddStoragePool();

        addStoragePool.setPoolName(STORAGE_POOL_NAME);
        addStoragePool.setStorageSystemUri(getStorageSystem().getUri());

        return addStoragePool;
    }

    private StorageSystem getStorageSystem() {
        return storageSystemClient.getByName(StorageSystemClientSample.STORAGE_SYSTEM_NAME).get(0);
    }

    public static void main(final String[] args) throws Exception {
        StoragePoolClientSample sample = new StoragePoolClientSample();

        sample.getStoragePoolById();
        sample.getAllStoragePools();
        sample.addStoragePool();
        sample.getStoragePoolByName();
        sample.updateStoragePool();
        sample.removeStoragePool();
    }

}
