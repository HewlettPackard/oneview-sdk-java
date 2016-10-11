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

import static com.hp.ov.sdk.rest.client.storage.StorageVolumeClient.ATTACHABLE_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageVolumeClient.REPAIR_FILTER_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageVolumeClient.REPAIR_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageVolumeClient.SNAPSHOTS_URI;
import static com.hp.ov.sdk.rest.client.storage.StorageVolumeClient.STORAGE_VOLUME_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.AddStorageVolume;
import com.hp.ov.sdk.dto.storage.AttachableStorageVolume;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolume;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.storage.StorageVolume;
import com.hp.ov.sdk.dto.storage.StorageVolumeSnapshot;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeClientTest {

    private static final String ANY_STORAGE_VOLUME_RESOURCE_ID = "random-UUID";
    private static final String ANY_STORAGE_VOLUME_RESOURCE_NAME = "random-Name";
    private static final String ANY_SNAPSHOT_RESOURCE_ID = "random-UUID";

    private BaseClient baseClient = mock(BaseClient.class);
    private StorageVolumeClient client = Reflection.newProxy(StorageVolumeClient.class,
            new ClientRequestHandler<>(baseClient, StorageVolumeClient.class));

    @Test
    public void shouldGetStorageVolumeById() {
        client.getById(ANY_STORAGE_VOLUME_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_URI + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(StorageVolume.class).getType());
    }

    @Test
    public void shouldGetAllStorageVolumes() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, STORAGE_VOLUME_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageVolume>>() {}.getType());
    }

    @Test
    public void shouldGetStorageVolumeCollectionByName() {
        client.getByName(ANY_STORAGE_VOLUME_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, STORAGE_VOLUME_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_STORAGE_VOLUME_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageVolume>>() {}.getType());
    }

    @Test
    public void shouldCreateStorageVolume() {
        AddStorageVolume addStorageVolume = new AddStorageVolume();

        client.create(addStorageVolume);

        Request expectedRequest = new Request(HttpMethod.POST, STORAGE_VOLUME_URI);
        expectedRequest.setEntity(addStorageVolume);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateStorageVolume() {
        StorageVolume storageVolume = new StorageVolume();

        client.update(ANY_STORAGE_VOLUME_RESOURCE_ID, storageVolume);

        String expectedUri = STORAGE_VOLUME_URI + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, storageVolume);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldDeleteStorageVolume() {
        client.delete(ANY_STORAGE_VOLUME_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_URI + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetAttachableVolumes() {
        client.getAttachableVolumes();

        String expectedUri = STORAGE_VOLUME_URI + ATTACHABLE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<AttachableStorageVolume>>() {}.getType());
    }

    @Test
    public void shouldGetSnapshotById() {
        client.getSnapshot(ANY_STORAGE_VOLUME_RESOURCE_ID, ANY_SNAPSHOT_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_URI
                + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID
                + SNAPSHOTS_URI
                + "/" + ANY_SNAPSHOT_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(StorageVolumeSnapshot.class).getType());
    }

    @Test
    public void shouldGetAllSnapshots() {
        client.getAllSnapshots(ANY_STORAGE_VOLUME_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_URI
                + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID
                + SNAPSHOTS_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<StorageVolumeSnapshot>>() {}.getType());
    }

    @Test
    public void shouldCreateSnapshot() {
        StorageVolumeSnapshot storageVolumeSnapshot = new StorageVolumeSnapshot();

        client.createSnapshot(ANY_STORAGE_VOLUME_RESOURCE_ID, storageVolumeSnapshot);

        String expectedUri = STORAGE_VOLUME_URI + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID + SNAPSHOTS_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, storageVolumeSnapshot);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteSnapshot() {
        client.deleteSnapshot(ANY_STORAGE_VOLUME_RESOURCE_ID, ANY_SNAPSHOT_RESOURCE_ID);

        String expectedUri = STORAGE_VOLUME_URI
                + "/" + ANY_STORAGE_VOLUME_RESOURCE_ID
                + SNAPSHOTS_URI
                + "/" + ANY_SNAPSHOT_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetExtraManagedPaths() {
        client.getExtraManagedPaths();

        String expectedUri = STORAGE_VOLUME_URI + REPAIR_FILTER_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ExtraStorageVolume>>() {}.getType());
    }

    @Test
    public void shouldRepairExtraManagedPaths() {
        ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();

        client.repairExtraManagedPath(repair);

        String expectedUri = STORAGE_VOLUME_URI + REPAIR_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, repair);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }
}
