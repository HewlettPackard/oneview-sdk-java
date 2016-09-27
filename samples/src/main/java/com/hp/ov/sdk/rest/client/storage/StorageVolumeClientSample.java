/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.AddStorageVolume;
import com.hp.ov.sdk.dto.AttachableStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolume;
import com.hp.ov.sdk.dto.StorageVolumeProvisioningParameters;
import com.hp.ov.sdk.dto.StorageVolumeSnapshot;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * StorageVolumeClientSample is a sample program consume the container that is defined over the storage pool in HPE OneView.
 * It invokes APIs of StorageVolumeClient which is in sdk library to perform GET/PUT/POST/DELETE operations on storage
 *  volume resource
 */
public class StorageVolumeClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeClientSample.class);

    private final StorageVolumeClient storageVolumeClient;
    private final StoragePoolClient storagePoolClient;
    private final StorageSystemClient storageSystemClient;

    // These are variables to be defined by user
    // ================================
    public static final String STORAGE_VOLUME_NAME = "Volume_Sample";
    private static final String STORAGE_VOLUME_NAME_UPDATED = STORAGE_VOLUME_NAME + "_Updated";
    private static final String STORAGE_VOLUME_RESOURCE_ID = "C470D4AA-B933-4D8B-999C-2F44F95233D8";
    private static final String PRIVATE_STORAGE_VOLUME_NAME = "Private_Volume_Sample";
    private static final String STORAGE_VOLUME_SNAPSHOT_ID = "9324AE01-B655-4C2F-B703-0C71610F94E9";
    private static final String STORAGE_VOLUME_CAPACITY = "1234567898";
    // ================================

    private StorageVolumeClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.storageVolumeClient = oneViewClient.storageVolume();
        this.storagePoolClient = oneViewClient.storagePool();
        this.storageSystemClient = oneViewClient.storageSystem();
    }

    private void getStorageVolume() {
        StorageVolume storageVolume = this.storageVolumeClient.getById(STORAGE_VOLUME_RESOURCE_ID);

        LOGGER.info("StorageVolume object returned to client : " + storageVolume.toJsonString());
    }

    private void getAllStorageVolumes() {
        ResourceCollection<StorageVolume> storageVolumes = this.storageVolumeClient.getAll();

        LOGGER.info("StorageVolumes returned to client : " + storageVolumes.toJsonString());
    }

    private void getStorageVolumeByName() {
        StorageVolume storageVolume = this.storageVolumeClient.getByName(STORAGE_VOLUME_NAME).get(0);

        LOGGER.info("StorageVolume object returned to client : " + storageVolume.toJsonString());
    }

    private void createStorageVolume() {
        AddStorageVolume addStorageVolume = buildStorageVolume();

        TaskResource taskResource = storageVolumeClient.create(addStorageVolume, false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateStorageVolume() {
        StorageVolume storageVolume = this.storageVolumeClient.getByName(STORAGE_VOLUME_NAME).get(0);

        storageVolume.setName(STORAGE_VOLUME_NAME_UPDATED);

        String response = storageVolumeClient.update(storageVolume.getResourceId(), storageVolume);

        LOGGER.info("Response returned to client : " + response);
    }

    private void deleteStorageVolume() {
        StorageVolume storageVolume = this.storageVolumeClient.getByName(STORAGE_VOLUME_NAME_UPDATED).get(0);
        TaskResource taskResource = this.storageVolumeClient.delete(storageVolume.getResourceId(), false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void createPrivateStorageVolume() {
        AddStorageVolume addStorageVolume = buildPrivateStorageVolume();

        TaskResource taskResource = storageVolumeClient.create(addStorageVolume, false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void getAttachableVolumes() {
        ResourceCollection<AttachableStorageVolume> attachableVolumes = this.storageVolumeClient.getAttachableVolumes();

        LOGGER.info("AttachableStorageVolumes returned to client : " + attachableVolumes.toJsonString());
    }

    private void getStorageVolumeSnapshot() {
        StorageVolumeSnapshot storageVolumeSnapshot = this.storageVolumeClient.getSnapshot(
                STORAGE_VOLUME_RESOURCE_ID, STORAGE_VOLUME_SNAPSHOT_ID);

        LOGGER.info("StorageVolumeSnapshot object returned to client : " + storageVolumeSnapshot.toJsonString());
    }

    private void getAllStorageVolumeSnapshots() {
        StorageVolume storageVolume = this.storageVolumeClient.getByName(STORAGE_VOLUME_NAME).get(0);
        ResourceCollection<StorageVolumeSnapshot> storageVolumeSnapshots = this.storageVolumeClient.getAllSnapshots(
                storageVolume.getResourceId());

        LOGGER.info("StorageVolumeSnapshots returned to client : " + storageVolumeSnapshots.toJsonString());
    }

    private void createStorageVolumeSnapshot() {
        StorageVolume storageVolume = this.storageVolumeClient.getByName(STORAGE_VOLUME_NAME).get(0);
        StorageVolumeSnapshot snapshot = buildStorageVolumeSnapshot();

        TaskResource taskResource = storageVolumeClient.createSnapshot(
                storageVolume.getResourceId(), snapshot, false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void deleteStorageVolumeSnapshot() {
        StorageVolume storageVolume = this.storageVolumeClient.getByName(STORAGE_VOLUME_NAME).get(0);
        StorageVolumeSnapshot storageVolumeSnapshot = this.storageVolumeClient.getAllSnapshots(
                storageVolume.getResourceId()).get(0);

        TaskResource taskResource = this.storageVolumeClient.deleteSnapshot(
                storageVolume.getResourceId(), storageVolumeSnapshot.getResourceId(), false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void getExtraManagedStorageVolumePaths() {
        ResourceCollection<ExtraStorageVolume> extraManagedPaths = this.storageVolumeClient.getExtraManagedPaths();

        LOGGER.info("ExtraStorageVolumes returned to client : " + extraManagedPaths.toJsonString());
    }

    private void repairExtraManagedStorageVolumePath() {
        StorageVolume storageVolume = this.storageVolumeClient.getByName(STORAGE_VOLUME_NAME).get(0);

        ExtraStorageVolumeRepair deleteExtraManagedStorageVolume = new ExtraStorageVolumeRepair();

        deleteExtraManagedStorageVolume.setType("ExtraManagedStorageVolumePaths");
        deleteExtraManagedStorageVolume.setResourceUri(storageVolume.getUri());

        TaskResource taskResource = this.storageVolumeClient.repairExtraManagedPath(
                deleteExtraManagedStorageVolume, false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private StorageVolumeSnapshot buildStorageVolumeSnapshot() {
        StorageVolumeSnapshot snapshot = new StorageVolumeSnapshot();

        snapshot.setType("Snapshot");
        snapshot.setName("{volumeName}_{timestamp}_SAMPLE");
        snapshot.setDescription("Custom description");

        return snapshot;
    }

    private AddStorageVolume buildStorageVolume() {
        String storageSystemUri = storageSystemClient.getByName(
                StorageSystemClientSample.STORAGE_SYSTEM_NAME).get(0).getUri();
        String storagePoolUri = storagePoolClient.getByName(
                StoragePoolClientSample.STORAGE_POOL_NAME, storageSystemUri).get(0).getUri();

        AddStorageVolume dto = new AddStorageVolume();

        dto.setName(STORAGE_VOLUME_NAME);
        dto.setDescription("Volume description");
        dto.setStorageSystemUri(storageSystemUri);
        dto.setSnapshotPoolUri(storagePoolUri); //v200

        StorageVolumeProvisioningParameters provisioningParameters = new StorageVolumeProvisioningParameters();

        provisioningParameters.setProvisionType("Full");
        provisioningParameters.setShareable(true);
        provisioningParameters.setRequestedCapacity(STORAGE_VOLUME_CAPACITY);
        provisioningParameters.setStoragePoolUri(storagePoolUri);
        dto.setProvisioningParameters(provisioningParameters);

        return dto;
    }

    private AddStorageVolume buildPrivateStorageVolume() {
        String storageSystemUri = storageSystemClient.getByName(
                StorageSystemClientSample.STORAGE_SYSTEM_NAME).get(0).getUri();
        String storagePoolUri = storagePoolClient.getByName(
                StoragePoolClientSample.STORAGE_POOL_NAME, storageSystemUri).get(0).getUri();

        AddStorageVolume dto = new AddStorageVolume();

        dto.setName(PRIVATE_STORAGE_VOLUME_NAME);
        dto.setDescription("Volume description");
        dto.setStorageSystemUri(storageSystemUri);
        dto.setSnapshotPoolUri(storagePoolUri); //v200

        StorageVolumeProvisioningParameters provisioningParameters = new StorageVolumeProvisioningParameters();
        provisioningParameters.setProvisionType("Full");
        provisioningParameters.setShareable(false);
        provisioningParameters.setRequestedCapacity(STORAGE_VOLUME_CAPACITY);
        provisioningParameters.setStoragePoolUri(storagePoolUri);
        dto.setProvisioningParameters(provisioningParameters);

        return dto;
    }

    public static void main(final String[] args) throws Exception {
        StorageVolumeClientSample client = new StorageVolumeClientSample();

        client.createStorageVolume();
        client.createPrivateStorageVolume();
        client.getStorageVolume();
        client.getAllStorageVolumes();
        client.getStorageVolumeByName();
        client.getAttachableVolumes();
        client.createStorageVolumeSnapshot();
        client.getStorageVolumeSnapshot();
        client.getAllStorageVolumeSnapshots();
        client.deleteStorageVolumeSnapshot();
        client.getExtraManagedStorageVolumePaths();
        client.repairExtraManagedStorageVolumePath();

        client.updateStorageVolume();
        client.deleteStorageVolume();
    }
}
