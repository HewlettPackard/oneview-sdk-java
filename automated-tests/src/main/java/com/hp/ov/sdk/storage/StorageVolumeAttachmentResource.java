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

import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.storage.StorageVolume;
import com.hp.ov.sdk.dto.storage.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.storage.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.server.ServerProfileClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeAttachmentClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeClient;

public class StorageVolumeAttachmentResource extends BasicResource implements Resource {

    private static StorageVolumeAttachmentResource instance;

    private StorageVolumeAttachmentClient client;
    private StorageVolumeClient volumeClient;
    private ServerProfileClient serverProfileClient;

    public StorageVolumeAttachmentResource() {
        client = oneViewClient.storageVolumeAttachment();
        volumeClient = oneViewClient.storageVolume();
        serverProfileClient = oneViewClient.serverProfile();
    }

    public static StorageVolumeAttachmentResource getInstance() {
        if (instance == null) {
            instance = new StorageVolumeAttachmentResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        StorageVolume volume = (StorageVolume) getResource(volumeClient.getByName(name));
        String volumeUri = volume.getUri();
        StorageVolumeAttachment attachment = null;
        for (StorageVolumeAttachment va : client.getAll().getMembers()) {
            if (va.getStorageVolumeUri().equals(volumeUri)) {
                attachment = va;
                break;
            }
        }
        return attachment == null ? "" : attachment.getResourceId();
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

    public int getAllAttachmentPath(String id) {
        return client.getAllAttachmentPaths(id).getMembers().size();
    }

    public String getVolumeAttachmentPath(String id) {
        List<StorageVolumeAttachmentPath> paths = client.getAllAttachmentPaths(id).getMembers();
        StorageVolumeAttachmentPath path = null;
        for (StorageVolumeAttachmentPath p : paths) {
            if (p.getUri().contains(id)) {
                path = p;
            }
        }
        return path == null ? "" : path.getResourceId();
    }

    public int getExtraUnmanagedVolumeAttachment() {
        return getCount(client.getExtraUnmanagedStorageVolumes());
    }

    public String repairExtraUnmanagedStorageVolumeAttachment() {
        ServerProfile serverProfile = (ServerProfile) getResource(
                serverProfileClient.getByName(resourceProperties.get("serverProfile")));

        ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();
        repair.setType("ExtraUnmanagedStorageVolumes");
        repair.setResourceUri(
                String.format("%s/%s", ServerProfileClient.SERVER_PROFILE_URI, serverProfile.getResourceId()));

        return taskToString(client.repairExtraPresentations(repair));
    }

}
