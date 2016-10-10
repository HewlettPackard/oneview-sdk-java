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

import static com.hp.ov.sdk.rest.client.storage.StorageVolumeAttachmentClient.STORAGE_VOLUME_ATTACHMENT_PATH_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageVolumeAttachmentClient.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageVolumeAttachmentClient.STORAGE_VOLUME_ATTACHMENT_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeAttachmentClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    private BaseClient baseClient = mock(BaseClient.class);
    private StorageVolumeAttachmentClient client = Reflection.newProxy(StorageVolumeAttachmentClient.class,
            new ClientRequestHandler<>(baseClient, StorageVolumeAttachmentClient.class));

    @Test
    public void shouldGetStorageVolumeAttachmentById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_ATTACHMENT_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(StorageVolumeAttachment.class).getType());
    }

    @Test
    public void shouldGetAllStorageVolumeAttachments() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, STORAGE_VOLUME_ATTACHMENT_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageVolumeAttachment>>() {}.getType());
    }

    @Test
    public void shouldGetAttachmentPath() {
        client.getAttachmentPath(ANY_RESOURCE_ID,
                ANY_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_ATTACHMENT_URI
                + "/" + ANY_RESOURCE_ID
                + STORAGE_VOLUME_ATTACHMENT_PATH_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(StorageVolumeAttachmentPath.class).getType());
    }

    @Test
    public void shouldGetAllAttachmentPaths() {
        client.getAllAttachmentPaths(ANY_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_ATTACHMENT_URI
                + "/" + ANY_RESOURCE_ID
                + STORAGE_VOLUME_ATTACHMENT_PATH_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<StorageVolumeAttachmentPath>>() {}.getType());
    }

    @Test
    public void shouldGetExtraUnmanagedStorageVolumes() {
        client.getExtraUnmanagedStorageVolumes();

        String expectedUri = STORAGE_VOLUME_ATTACHMENT_URI
                + STORAGE_VOLUME_ATTACHMENT_REPAIR_URI
                + "?alertFixType=ExtraUnmanagedStorageVolumes";
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ExtraStorageVolume>>() {}.getType());
    }

    @Test
    public void shouldRepairExtraUnmanagedAttachment() {
        ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();

        client.repairExtraPresentations(repair, TaskTimeout.of(321));

        String expectedUri = STORAGE_VOLUME_ATTACHMENT_URI
                + STORAGE_VOLUME_ATTACHMENT_REPAIR_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, repair);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }
}
