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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class StorageVolumeAttachmentClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeAttachmentClientSample.class);

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

        LOGGER.info("Storage volume attachment returned to client: {}", storageVolumeAttachment.toJsonString());
    }

    private void getAllStorageVolumeAttachments() {
        ResourceCollection<StorageVolumeAttachment> storageVolumeAttachments = this.storageVolumeAttachmentClient.getAll();

        LOGGER.info("Storage volume attachments returned to client: {}", storageVolumeAttachments.toJsonString());
    }

    private void getStorageVolumeAttachmentPath() {
        StorageVolumeAttachmentPath storageVolumeAttachmentPath = this.storageVolumeAttachmentClient.getAttachmentPath(
                STORAGE_VOLUME_ATTACHMENT_ID, STORAGE_VOLUME_ATTACHMENT_PATH_ID);

        LOGGER.info("Storage volume attachment path returned to client: {}", storageVolumeAttachmentPath.toJsonString());
    }

    private void getAllStorageVolumeAttachmentPaths() {
        List<StorageVolumeAttachmentPath> storageVolumeAttachmentPaths
                = this.storageVolumeAttachmentClient.getAllAttachmentPaths(STORAGE_VOLUME_ATTACHMENT_ID);

        LOGGER.info("Storage volume attachment paths returned to client: {}",
                JsonPrettyPrinter.print(storageVolumeAttachmentPaths));
    }

    private void getExtraUnmanagedStorageVolumes() {
        ResourceCollection<ExtraStorageVolume> extraStorageVolumes
                = this.storageVolumeAttachmentClient.getExtraUnmanagedStorageVolumes();

        LOGGER.info("Extra unmanaged storage volume attachments returned to client: {}",
                JsonPrettyPrinter.print(extraStorageVolumes));
    }

    private void repairExtraPresentationsFromServerProfile() {
        ExtraStorageVolumeRepair repair = new ExtraStorageVolumeRepair();

        repair.setType("ExtraUnmanagedStorageVolumes");
        repair.setResourceUri(String.format("%s/%s", ResourceUris.SERVER_PROFILE_URI, SERVER_PROFILE_ID));

        TaskResource task = this.storageVolumeAttachmentClient.repairExtraPresentations(repair);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    public static void main(String[] args) {
        StorageVolumeAttachmentClientSample client = new StorageVolumeAttachmentClientSample();

        client.getAllStorageVolumeAttachments();
        client.getStorageVolumeAttachment();
        client.getAllStorageVolumeAttachmentPaths();
        client.getStorageVolumeAttachmentPath();
        client.getExtraUnmanagedStorageVolumes();
        client.repairExtraPresentationsFromServerProfile();
    }

}
