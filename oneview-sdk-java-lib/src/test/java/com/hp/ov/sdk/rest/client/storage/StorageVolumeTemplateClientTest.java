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
import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplate;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeTemplateClientTest {

    private static final String ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID = "random-UUID";
    private static final String ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private StorageVolumeTemplateClient storageVolumeTemplateClient;

    @Test
    public void shouldGetStorageVolumeTemplateById() {
        storageVolumeTemplateClient.getById(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_TEMPLATE_URI + "/" + ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, StorageVolumeTemplate.class);
    }

    @Test
    public void shouldGetAllStorageVolumeTemplates() {
        storageVolumeTemplateClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, StorageVolumeTemplate.class);
    }

    @Test
    public void shouldGetStorageVolumeTemplateCollectionByName() {
        storageVolumeTemplateClient.getByName(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_TEMPLATE_URI,
                StorageVolumeTemplate.class, UrlParameter.getFilterByNameParameter(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_NAME));
    }

    @Test
    public void shouldCreateStorageVolumeTemplate() {
        StorageVolumeTemplate storageVolumeTemplate = new StorageVolumeTemplate();

        storageVolumeTemplateClient.create(storageVolumeTemplate);

        Request request = new Request(HttpMethod.POST, ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, storageVolumeTemplate);

        then(baseClient).should().executeRequest(request, StorageVolumeTemplate.class);
    }

    @Test
    public void shouldUpdateStorageVolumeTemplate() {
        StorageVolumeTemplate storageVolumeTemplate = new StorageVolumeTemplate();

        storageVolumeTemplateClient.update(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID, storageVolumeTemplate);

        String expectedUri = ResourceUris.STORAGE_VOLUME_TEMPLATE_URI + "/" + ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, storageVolumeTemplate);

        then(baseClient).should().executeRequest(request, StorageVolumeTemplate.class);
    }

    @Test
    public void shouldDeleteStorageVolumeTemplate() {
        storageVolumeTemplateClient.delete(ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_TEMPLATE_URI + "/" + ANY_STORAGE_VOLUME_TEMPLATE_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldGetConnectableStorageVolumeTemplates() {
        storageVolumeTemplateClient.getConnectableVolumeTemplates();

        then(baseClient).should().getResourceCollection(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_TEMPLATE_URI,
                        ResourceUris.STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI), ConnectableStorageVolumeTemplate.class);
    }
}
