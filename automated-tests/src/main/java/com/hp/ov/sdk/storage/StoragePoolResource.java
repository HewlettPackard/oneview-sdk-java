/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.storage;

import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.AddStoragePool;
import com.hp.ov.sdk.dto.storage.StoragePool;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.rest.client.storage.StoragePoolClient;

public class StoragePoolResource extends BasicResource implements CreateResource, RemoveResource {

    private static StoragePoolResource instance;

    private StoragePoolClient client;

    private static String resourceName;

    private static String systemUri;

    private StoragePoolResource() {
        category.put("V_300", "StoragePoolV2");
        category.put("V_200", "StoragePoolV2");
        client = oneViewClient.storagePool();
    }

    public static StoragePoolResource getInstance() {
        if (instance == null) {
            instance = new StoragePoolResource();
        }
        return instance;
    }

    public void setStorageSystemUri(String uri) {
        systemUri = uri;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    public String getUri() {
        ResourceCollection<StoragePool> storages = client.getByName(resourceName);
        for (StoragePool pool : storages.getMembers()) {
            if (pool.getName().equals(resourceName)) {
                return pool.getUri();
            }
        }
        return "";
    }

    @Override
    public String findByName(String name) {
        StoragePool storagePool = (StoragePool) getResource(client.getByName(name));
        return storagePool == null ? "" : storagePool.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        client.add(builder());
    }

    @Override
    public String remove(String id) {
        return taskToString(client.remove(id));
    }

    @Override
    public AddStoragePool builder() {
        AddStoragePool storagePool = new AddStoragePool();
        resourceName = resourceProperties.get("name");
        storagePool.setPoolName(resourceName);
        storagePool.setStorageSystemUri(systemUri);
        return storagePool;
    }

}
