/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class StorageVolumeAttachmentClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String STORAGE_VOLUME_ATTACHMENT_ID = "AE541765-4034-40B8-8CE3-547AFC8A058F";
    private static final String STORAGE_VOLUME_ATTACHMENT_PATH_ID = "4E832512-8B26-4CAF-BCE7-FD4568D907E6";
    private static final String SERVER_PROFILE_ID = "07c52c4a-5d24-40ed-9c32-782c8c0643b0";
    // ================================

    private final StorageVolumeAttachmentClient storageVolumeAttachmentClient;

    public StorageVolumeAttachmentClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.storageVolumeAttachmentClient = oneViewClient.storageVolumeAttachment();
    }

    private void getStorageVolumeAttachment() {
        StorageVolumeAttachment storageVolumeAttachment = this.storageVolumeAttachmentClient.getById(
                STORAGE_VOLUME_ATTACHMENT_ID);

        System.out.println("StorageVolumeAttachmentClient : getStorageVolumeAttachment : " +
                "StorageVolumeAttachment object returned to client : " + storageVolumeAttachment.toJsonString());
    }

    private void getAllStorageVolumeAttachments() {
        ResourceCollection<StorageVolumeAttachment> storageVolumeAttachments = this.storageVolumeAttachmentClient.getAll();

        System.out.println("StorageVolumeAttachmentClient : getAllStorageVolumeAttachments : " +
                "StorageVolumeAttachments returned to client : " + storageVolumeAttachments.toJsonString());
    }

    private void getStorageVolumeAttachmentPath() {
        StorageVolumeAttachmentPath storageVolumeAttachmentPath = this.storageVolumeAttachmentClient.getAttachmentPath(
                STORAGE_VOLUME_ATTACHMENT_ID, STORAGE_VOLUME_ATTACHMENT_PATH_ID);

        System.out.println("StorageVolumeAttachmentClient : getStorageVolumeAttachmentPath : " +
                "StorageVolumeAttachmentPath object returned to client : " + storageVolumeAttachmentPath.toJsonString());
    }

    private void getAllStorageVolumeAttachmentPaths() {
        List<StorageVolumeAttachmentPath> storageVolumeAttachmentPaths
                = this.storageVolumeAttachmentClient.getAllAttachmentPaths(STORAGE_VOLUME_ATTACHMENT_ID);

        System.out.println("StorageVolumeAttachmentClient : getAllStorageVolumeAttachmentPaths : " +
                "StorageVolumeAttachmentPaths returned to client : " + storageVolumeAttachmentPaths);
    }

    private void getExtraUnmanagedStorageVolumeAttachments() {
        ResourceCollection<ExtraStorageVolume> extraStorageVolumes
                = this.storageVolumeAttachmentClient.getExtraUnmanagedAttachments();

        System.out.println("StorageVolumeAttachmentClient : getExtraUnmanagedStorageVolumeAttachments : " +
                "ExtraStorageVolumes object returned to client : " + extraStorageVolumes.toJsonString());
    }

    private void repairExtraUnmanagedStorageVolumeAttachment() {
        ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();

        repair.setType("ExtraUnmanagedStorageVolumes");
        repair.setResourceUri(String.format("%s/%s", ResourceUris.SERVER_PROFILE_URI, SERVER_PROFILE_ID));

        TaskResourceV2 task = this.storageVolumeAttachmentClient.repairExtraUnmanagedAttachment(repair, false);

        System.out.println("StorageVolumeAttachmentClient : repairExtraUnmanagedStorageVolumeAttachment :" +
                "Task object returned to client : " + task.toJsonString());
    }

    public static void main(String[] args) {
        StorageVolumeAttachmentClientSample client = new StorageVolumeAttachmentClientSample();

        client.getStorageVolumeAttachment();
        client.getAllStorageVolumeAttachments();
        client.getStorageVolumeAttachmentPath();
        client.getAllStorageVolumeAttachmentPaths();
        client.getExtraUnmanagedStorageVolumeAttachments();
        client.repairExtraUnmanagedStorageVolumeAttachment();
    }

}
