/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.storage;

import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.AddStorageVolume;
import com.hp.ov.sdk.dto.storage.AttachableStorageVolume;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolume;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.storage.StorageVolume;
import com.hp.ov.sdk.dto.storage.StorageVolumeProvisioningParameters;
import com.hp.ov.sdk.dto.storage.StorageVolumeSnapshot;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeClient;

public class StorageVolumeResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static StorageVolumeResource instance;

    private StorageVolumeClient client;

    private String systemUri;

    private String poolUri;

    private StorageVolumeResource() {
        category.put("V_300", "AddStorageVolumeV3");
        category.put("V_200", "AddStorageVolumeV3");
        client = oneViewClient.storageVolume();
    }

    public static StorageVolumeResource getInstance() {
        if (instance == null) {
            instance = new StorageVolumeResource();
        }
        return instance;
    }

    public void setStorageSystemUri(String systemUri) {
        this.systemUri = systemUri;
    }

    public void setStoragePoolUri(String poolUri) {
        this.poolUri = poolUri;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        StorageVolume storageVolume = (StorageVolume) getResource(client.getByName(name));
        return storageVolume == null ? "" : storageVolume.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        client.create(builder());
    }

    @Override
    public String update(String id) {
        StorageVolume volume = client.getById(id);
        volume.setName(resourceProperties.get("name"));
        volume.setDescription(resourceProperties.get("description"));
        return client.update(id, volume);
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

    public int getAttachableVolumes() {
        ResourceCollection<AttachableStorageVolume> collection = client.getAttachableVolumes();
        return collection != null ? collection.getCount() : -1;
    }

    public int getAllStorageVolumeSnapshots(String id) {
        ResourceCollection<StorageVolumeSnapshot> collection = client.getAllSnapshots(id);
        return collection != null ? collection.getCount() : -1;
    }

    public String getStorageVolumeSnapshots(String id) {
        ResourceCollection<StorageVolumeSnapshot> collection = client.getAllSnapshots(id);
        if (!collection.getMembers().isEmpty()) {
            StorageVolumeSnapshot storageVolumeSnapshot = this.client.getSnapshot(id,
                    collection.getMembers().get(0).getResourceId());
            return storageVolumeSnapshot.toString();
        }
        return "not-found";
    }

    public int getExtraManagedStorageVolumePaths() {
        ResourceCollection<ExtraStorageVolume> collection = client.getExtraManagedPaths();
        return collection != null ? collection.getCount() : -1;
    }

    public void createSnapshot() {
        StorageVolume volume = (StorageVolume) getResource(client.getByName(resourceProperties.get("volume")));

        StorageVolumeSnapshot snapshot = new StorageVolumeSnapshot();
        snapshot.setType(resourceProperties.get("type"));
        snapshot.setName(resourceProperties.get("name"));
        snapshot.setDescription(resourceProperties.get("description"));

        client.createSnapshot(volume.getResourceId(), snapshot);
    }

    public int deleteSnapshot(String resourceID) {
        ResourceCollection<StorageVolumeSnapshot> collection = client.getAllSnapshots(resourceID);
        if (!collection.getMembers().isEmpty()) {
            client.deleteSnapshot(resourceID, collection.getMembers().get(0).getResourceId());
            return 0;
        }
        return -1;
    }

    public int repairExtraManagedStorageVolumePath(String resourceID) {
        StorageVolume storageVolume = client.getById(resourceID);

        ExtraStorageVolumeRepair deleteExtraManagedStorageVolume = new ExtraStorageVolumeRepair();

        deleteExtraManagedStorageVolume.setType("ExtraManagedStorageVolumePaths");
        deleteExtraManagedStorageVolume.setResourceUri(storageVolume.getUri());

        client.repairExtraManagedPath(deleteExtraManagedStorageVolume);

        return 0;
    }

    @Override
    public AddStorageVolume builder() {
        AddStorageVolume storageVolume = new AddStorageVolume();
        storageVolume.setName(resourceProperties.get("name"));
        storageVolume.setDescription(resourceProperties.get("description"));
        // storageVolume.setType(getCategory());
        storageVolume.setStorageSystemUri(systemUri);
        storageVolume.setSnapshotPoolUri(poolUri);

        StorageVolumeProvisioningParameters provisioningParameters = new StorageVolumeProvisioningParameters();
        provisioningParameters.setProvisionType(resourceProperties.get("provisionType"));
        provisioningParameters.setShareable(Boolean.valueOf(resourceProperties.get("shareable")));
        provisioningParameters.setRequestedCapacity(resourceProperties.get("capacity"));
        provisioningParameters.setStoragePoolUri(poolUri);
        storageVolume.setProvisioningParameters(provisioningParameters);

        return storageVolume;
    }
}
