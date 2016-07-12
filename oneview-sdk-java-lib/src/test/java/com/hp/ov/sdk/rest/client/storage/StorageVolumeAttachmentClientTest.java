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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeAttachmentClientTest {

    private static final String ANY_STORAGE_VOLUME_ATTACHMENT_RESOURCE_ID = "random-UUID";
    private static final String ANY_STORAGE_VOLUME_ATTACHMENT_PATH_RESOURCE_ID = "random-UUID";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private StorageVolumeAttachmentClient storageVolumeAttachmentClient;

    @Test
    public void shouldGetStorageVolumeAttachmentById() {
        storageVolumeAttachmentClient.getById(ANY_STORAGE_VOLUME_ATTACHMENT_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI
                + "/" + ANY_STORAGE_VOLUME_ATTACHMENT_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, StorageVolumeAttachment.class);
    }

    @Test
    public void shouldGetAllStorageVolumeAttachments() {
        storageVolumeAttachmentClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI,
                StorageVolumeAttachment.class);
    }

    @Test
    public void shouldGetAttachmentPath() {
        storageVolumeAttachmentClient.getAttachmentPath(ANY_STORAGE_VOLUME_ATTACHMENT_RESOURCE_ID,
                ANY_STORAGE_VOLUME_ATTACHMENT_PATH_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI
                + "/" + ANY_STORAGE_VOLUME_ATTACHMENT_RESOURCE_ID
                + "/" + ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI
                + "/" + ANY_STORAGE_VOLUME_ATTACHMENT_PATH_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, StorageVolumeAttachmentPath.class);
    }

    @Test
    public void shouldGetAllAttachmentPaths() {
        storageVolumeAttachmentClient.getAllAttachmentPaths(ANY_STORAGE_VOLUME_ATTACHMENT_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI
                + "/" + ANY_STORAGE_VOLUME_ATTACHMENT_RESOURCE_ID
                + "/" + ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI;

        then(baseClient).should().getResourceList(expectedUri, new TypeToken<List<StorageVolumeAttachmentPath>>() {});
    }

    @Test
    public void shouldGetExtraUnmanagedAttachments() {
        storageVolumeAttachmentClient.getExtraUnmanagedAttachments();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI,
                ExtraStorageVolume.class, new UrlParameter("alertFixType", "ExtraUnmanagedStorageVolumes"));
    }

    @Test
    public void shouldRepairExtraUnmanagedAttachment() {
        ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();

        storageVolumeAttachmentClient.repairExtraUnmanagedAttachment(repair, false);

        then(baseClient).should().createResource(ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI, repair, false);
    }
}
