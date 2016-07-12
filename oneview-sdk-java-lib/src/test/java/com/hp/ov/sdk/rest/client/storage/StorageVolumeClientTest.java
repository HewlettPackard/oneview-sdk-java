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
import com.hp.ov.sdk.dto.AddStorageVolume;
import com.hp.ov.sdk.dto.AttachableStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StorageVolume;
import com.hp.ov.sdk.dto.StorageVolumeSnapshot;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeClientTest {

    private static final String ANY_STORAGE_VOLUME_RESOURCE_ID = "random-UUID";
    private static final String ANY_STORAGE_VOLUME_RESOURCE_NAME = "random-Name";
    private static final String ANY_SNAPSHOT_RESOURCE_ID = "random-UUID";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private StorageVolumeClient storageVolumeClient;

    @Test
    public void shouldGetStorageVolumeById() {
        storageVolumeClient.getById(ANY_STORAGE_VOLUME_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_URI + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, StorageVolume.class);
    }

    @Test
    public void shouldGetAllStorageVolumes() {
        storageVolumeClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_URI, StorageVolume.class);
    }

    @Test
    public void shouldGetStorageVolumeCollectionByName() {
        storageVolumeClient.getByName(ANY_STORAGE_VOLUME_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_URI,
                StorageVolume.class, UrlParameter.getFilterByNameParameter(ANY_STORAGE_VOLUME_RESOURCE_NAME));
    }

    @Test
    public void shouldCreateStorageVolume() {
        AddStorageVolume addStorageVolume = new AddStorageVolume();

        storageVolumeClient.create(addStorageVolume, false);

        then(baseClient).should().createResource(ResourceUris.STORAGE_VOLUME_URI, addStorageVolume, false);
    }

    @Test
    public void shouldUpdateStorageVolume() {
        StorageVolume storageVolume = new StorageVolume();

        storageVolumeClient.update(ANY_STORAGE_VOLUME_RESOURCE_ID, storageVolume);

        String expectedUri = ResourceUris.STORAGE_VOLUME_URI + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, storageVolume);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldDeleteStorageVolume() {
        storageVolumeClient.delete(ANY_STORAGE_VOLUME_RESOURCE_ID, false);

        String expectedUri = ResourceUris.STORAGE_VOLUME_URI + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldGetAttachableVolumes() {
        storageVolumeClient.getAttachableVolumes();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_ATTACHABLE_URI,
                AttachableStorageVolume.class);
    }

    @Test
    public void shouldGetSnapshotById() {
        storageVolumeClient.getSnapshot(ANY_STORAGE_VOLUME_RESOURCE_ID, ANY_SNAPSHOT_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_URI
                + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID
                + "/" + ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI
                + "/" + ANY_SNAPSHOT_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, StorageVolumeSnapshot.class);
    }

    @Test
    public void shouldGetAllSnapshots() {
        storageVolumeClient.getAllSnapshots(ANY_STORAGE_VOLUME_RESOURCE_ID);

        String expectedUri = ResourceUris.STORAGE_VOLUME_URI
                + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID
                + "/" + ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI;

        then(baseClient).should().getResourceCollection(expectedUri, StorageVolumeSnapshot.class);
    }

    @Test
    public void shouldCreateSnapshot() {
        StorageVolumeSnapshot storageVolumeSnapshot = new StorageVolumeSnapshot();

        storageVolumeClient.createSnapshot(ANY_STORAGE_VOLUME_RESOURCE_ID, storageVolumeSnapshot, false);

        String expectedUri = ResourceUris.STORAGE_VOLUME_URI
                + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID
                + "/" + ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI;

        then(baseClient).should().createResource(expectedUri, storageVolumeSnapshot, false);
    }

    @Test
    public void shouldDeleteSnapshot() {
        storageVolumeClient.deleteSnapshot(ANY_STORAGE_VOLUME_RESOURCE_ID, ANY_SNAPSHOT_RESOURCE_ID, false);

        String expectedUri = ResourceUris.STORAGE_VOLUME_URI
                + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID
                + "/" + ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI
                + "/" + ANY_SNAPSHOT_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldGetExtraManagedPaths() {
        storageVolumeClient.getExtraManagedPaths();

        then(baseClient).should().getResourceCollection(ResourceUris.STORAGE_VOLUME_REPAIR_URI,
                ExtraStorageVolume.class, new UrlParameter("alertFixType", "ExtraManagedStorageVolumePaths"));
    }

    @Test
    public void shouldRepairExtraManagedPaths() {
        ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();

        storageVolumeClient.repairExtraManagedPath(repair, false);

        then(baseClient).should().createResource(ResourceUris.STORAGE_VOLUME_REPAIR_URI, repair, false);
    }
}
