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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class StoragePoolClientTest {

    private static final String ANY_STORAGE_POOL_RESOURCE_ID = "random-UUID";
    private static final String ANY_STORAGE_POOL_RESOURCE_NAME = "random-Name";
    private static final String ANY_STORAGE_SYSTEM_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private StoragePoolClient storagePoolClient;

    @Test
    public void shouldGetStoragePoolById() {
        storagePoolClient.getById(ANY_STORAGE_POOL_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_POOL_URI + "/" + ANY_STORAGE_POOL_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, StoragePool.class);
    }

    @Test
    public void shouldGetAllStoragePools() {
        storagePoolClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_POOL_URI, StoragePool.class);
    }

    @Test
    public void shouldGetStoragePoolCollectionByStoragePoolName() {
        storagePoolClient.getByName(ANY_STORAGE_POOL_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_POOL_URI,
                StoragePool.class, UrlParameter.getFilterByNameParameter(ANY_STORAGE_POOL_RESOURCE_NAME));
    }

    @Test
    public void shouldGetStoragePoolCollectionByStoragePoolAndStorageSystemName() {
        given(baseClient.getResourceCollection(any(String.class), any(Class.class), any(UrlParameter.class)))
                .willReturn(new ResourceCollection<StoragePool>());

        storagePoolClient.getByName(ANY_STORAGE_POOL_RESOURCE_NAME, ANY_STORAGE_SYSTEM_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_POOL_URI,
                StoragePool.class, UrlParameter.getFilterByNameParameter(ANY_STORAGE_POOL_RESOURCE_NAME));
    }

    @Test
    public void shouldAddStoragePool() {
        AddStoragePool storagePool = new AddStoragePool();

        storagePoolClient.add(storagePool, false);

        Request request = new Request(HttpMethod.POST, ResourceUris.STORAGE_POOL_URI, storagePool);

        request.setForceReturnTask(true);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldUpdateStoragePool() {
        StoragePool storagePool = new StoragePool();

        storagePoolClient.update(ANY_STORAGE_POOL_RESOURCE_ID, storagePool, false);

        String expectedUri = ResourceUris.STORAGE_POOL_URI + "/" + ANY_STORAGE_POOL_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, storagePool);

        request.setForceReturnTask(true);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldRemoveStoragePool() {
        storagePoolClient.remove(ANY_STORAGE_POOL_RESOURCE_ID, false);

        String expectedUri = ResourceUris.STORAGE_POOL_URI + "/" + ANY_STORAGE_POOL_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        request.setForceReturnTask(true);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }
}
