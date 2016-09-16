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

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StorageSystem;
import com.hp.ov.sdk.dto.StorageTargetPort;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class StorageSystemClientTest {

    private static final String ANY_STORAGE_SYSTEM_RESOURCE_ID = "random-UUID";
    private static final String ANY_STORAGE_SYSTEM_MANAGED_PORT_ID = "random-UUID";
    private static final String ANY_STORAGE_SYSTEM_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private StorageSystemClient storageSystemClient;

    @Test
    public void shouldGetStorageSystem() {
        storageSystemClient.getById(ANY_STORAGE_SYSTEM_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_SYSTEM_URI + "/" + ANY_STORAGE_SYSTEM_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, StorageSystem.class);
    }

    @Test
    public void shouldGetAllStorageSystem() {
        storageSystemClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_SYSTEM_URI, StorageSystem.class);
    }

    @Test
    public void shouldGetStorageSystemCollectionByName() {
        storageSystemClient.getByName(ANY_STORAGE_SYSTEM_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_SYSTEM_URI,
                StorageSystem.class, UrlParameter.getFilterByNameParameter(ANY_STORAGE_SYSTEM_RESOURCE_NAME));
    }

    @Test
    public void shouldAddStorageSystem() {
        AddStorageSystemCredentials storageSystemCredentials = new AddStorageSystemCredentials();

        storageSystemClient.add(storageSystemCredentials, false);

        Request request = new Request(HttpMethodType.POST, ResourceUris.STORAGE_SYSTEM_URI, storageSystemCredentials);

        request.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldUpdateStorageSystem() {
        StorageSystem storageSystem = new StorageSystem();

        storageSystemClient.update(ANY_STORAGE_SYSTEM_RESOURCE_ID, storageSystem, false);

        String expectedUri = ResourceUris.STORAGE_SYSTEM_URI + "/" + ANY_STORAGE_SYSTEM_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, storageSystem);

        request.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldRemoveStorageSystem() {
        storageSystemClient.remove(ANY_STORAGE_SYSTEM_RESOURCE_ID, false);

        String expectedUri = ResourceUris.STORAGE_SYSTEM_URI + "/" + ANY_STORAGE_SYSTEM_RESOURCE_ID;
        Request request = new Request(HttpMethodType.DELETE, expectedUri);

        request.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldGetStorageSystemPools() {
        storageSystemClient.getStoragePools(ANY_STORAGE_SYSTEM_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_SYSTEM_URI
                + "/" + ANY_STORAGE_SYSTEM_RESOURCE_ID
                + "/" + ResourceUris.STORAGE_POOL_STORAGE_SYSTEM_URI;

        then(baseClient).should().getResourceCollection(expectedUri, StoragePool.class);
    }

    @Test
    public void shouldGetStorageSystemAllManagedPorts() {
        storageSystemClient.getAllManagedPorts(ANY_STORAGE_SYSTEM_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_SYSTEM_URI
                + "/" + ANY_STORAGE_SYSTEM_RESOURCE_ID
                + "/" + ResourceUris.MANAGED_PORTS_STORAGE_SYSTEM_URI;

        then(baseClient).should().getResourceCollection(expectedUri, StorageTargetPort.class);
    }

    @Test
    public void shouldGetStorageSystemManagedPort() {
        storageSystemClient.getManagedPort(ANY_STORAGE_SYSTEM_RESOURCE_ID, ANY_STORAGE_SYSTEM_MANAGED_PORT_ID);

        String expectedUri = ResourceUris.STORAGE_SYSTEM_URI
                + "/" + ANY_STORAGE_SYSTEM_RESOURCE_ID
                + "/" + ResourceUris.MANAGED_PORTS_STORAGE_SYSTEM_URI
                + "/" + ANY_STORAGE_SYSTEM_MANAGED_PORT_ID;

        then(baseClient).should().getResource(expectedUri, StorageTargetPort.class);
    }

    @Test
    public void shouldGetStorageSystemHostTypes() {
        storageSystemClient.getHostTypes();

        then(baseClient).should().getResourceList(ResourceUris.STORAGE_SYSTEM_HOST_TYPES_URI, String.class);
    }

}
