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

import static com.hp.ov.sdk.rest.client.storage.StorageSystemClient.STORAGE_POOL_STORAGE_SYSTEM_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageSystemClient.STORAGE_SYSTEM_HOST_TYPES_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageSystemClient.STORAGE_SYSTEM_MANAGED_PORTS_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageSystemClient.STORAGE_SYSTEM_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.storage.StoragePool;
import com.hp.ov.sdk.dto.storage.StorageSystem;
import com.hp.ov.sdk.dto.storage.StorageTargetPort;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class StorageSystemClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_PORT_ID = "random-port-id";

    private BaseClient baseClient = mock(BaseClient.class);
    private StorageSystemClient client = Reflection.newProxy(StorageSystemClient.class,
            new ClientRequestHandler<>(baseClient, StorageSystemClient.class));

    @Test
    public void shouldGetStorageSystem() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = STORAGE_SYSTEM_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(StorageSystem.class).getType());
    }

    @Test
    public void shouldGetAllStorageSystem() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, STORAGE_SYSTEM_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageSystem>>() {}.getType());
    }

    @Test
    public void shouldGetStorageSystemCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, STORAGE_SYSTEM_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageSystem>>() {}.getType());
    }

    @Test
    public void shouldAddStorageSystem() {
        AddStorageSystemCredentials storageSystemCredentials = new AddStorageSystemCredentials();

        client.add(storageSystemCredentials);

        Request expectedRequest = new Request(HttpMethod.POST, STORAGE_SYSTEM_URI, storageSystemCredentials);
        expectedRequest.setEntity(storageSystemCredentials);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateStorageSystem() {
        StorageSystem storageSystem = new StorageSystem();

        client.update(ANY_RESOURCE_ID, storageSystem);

        String expectedUri = STORAGE_SYSTEM_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(storageSystem);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRemoveStorageSystem() {
        client.remove(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = STORAGE_SYSTEM_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetStorageSystemPools() {
        client.getStoragePools(ANY_RESOURCE_ID);

        String expectedUri = STORAGE_SYSTEM_URI
                + "/" + ANY_RESOURCE_ID
                + STORAGE_POOL_STORAGE_SYSTEM_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StoragePool>>() {}.getType());
    }

    @Test
    public void shouldGetStorageSystemAllManagedPorts() {
        client.getAllManagedPorts(ANY_RESOURCE_ID);

        String expectedUri = STORAGE_SYSTEM_URI
                + "/" + ANY_RESOURCE_ID
                + STORAGE_SYSTEM_MANAGED_PORTS_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageTargetPort>>() {}.getType());
    }

    @Test
    public void shouldGetStorageSystemManagedPort() {
        client.getManagedPort(ANY_RESOURCE_ID, ANY_PORT_ID);

        String expectedUri = STORAGE_SYSTEM_URI
                + "/" + ANY_RESOURCE_ID
                + STORAGE_SYSTEM_MANAGED_PORTS_URI
                + "/" + ANY_PORT_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(StorageTargetPort.class).getType());
    }

    @Test
    public void shouldGetStorageSystemHostTypes() {
        client.getHostTypes();

        String expectedUri = STORAGE_SYSTEM_URI + STORAGE_SYSTEM_HOST_TYPES_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<String>>() {}.getType());
    }

}
