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

import static com.hp.ov.sdk.rest.client.storage.StorageVolumeTemplateClient.STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageVolumeTemplateClient.STORAGE_VOLUME_TEMPLATE_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplate;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeTemplateClientTest {

    private static final String ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID = "random-UUID";
    private static final String ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private StorageVolumeTemplateClient client = Reflection.newProxy(StorageVolumeTemplateClient.class,
            new ClientRequestHandler<>(baseClient, StorageVolumeTemplateClient.class));

    @Test
    public void shouldGetStorageVolumeTemplateById() {
        client.getById(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_TEMPLATE_URI + "/" + ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(StorageVolumeTemplate.class).getType());
    }

    @Test
    public void shouldGetAllStorageVolumeTemplates() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, STORAGE_VOLUME_TEMPLATE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageVolumeTemplate>>() {}.getType());
    }

    @Test
    public void shouldGetStorageVolumeTemplateCollectionByName() {
        client.getByName(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, STORAGE_VOLUME_TEMPLATE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageVolumeTemplate>>() {}.getType());
    }

    @Test
    public void shouldCreateStorageVolumeTemplate() {
        StorageVolumeTemplate storageVolumeTemplate = new StorageVolumeTemplate();

        client.create(storageVolumeTemplate);

        Request request = new Request(HttpMethod.POST, STORAGE_VOLUME_TEMPLATE_URI, storageVolumeTemplate);

        then(baseClient).should().executeRequest(request,
                TypeToken.of(StorageVolumeTemplate.class).getType());
    }

    @Test
    public void shouldUpdateStorageVolumeTemplate() {
        StorageVolumeTemplate storageVolumeTemplate = new StorageVolumeTemplate();

        client.update(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID, storageVolumeTemplate);

        String expectedUri = STORAGE_VOLUME_TEMPLATE_URI + "/" + ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, storageVolumeTemplate);

        then(baseClient).should().executeRequest(request,
                TypeToken.of(StorageVolumeTemplate.class).getType());
    }

    @Test
    public void shouldDeleteStorageVolumeTemplate() {
        client.delete(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_TEMPLATE_URI + "/" + ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldGetConnectableStorageVolumeTemplates() {
        client.getConnectableVolumeTemplates();

        String expectedUri = STORAGE_VOLUME_TEMPLATE_URI + STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ConnectableStorageVolumeTemplate>>() {}.getType());
    }
}
