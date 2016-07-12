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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

/**
 * Storage volume attachments represents the connection information for the storage volume.
 */
public class StorageVolumeAttachmentClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeAttachmentClient.class);

    private final BaseClient baseClient;

    public StorageVolumeAttachmentClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Returns the storage volume attachment details for the specified storage
     * volume attachment resource identifier.
     *
     * @param resourceId storage volume attachment identifier as seen in HPE OneView.
     *
     * @return {@link StorageVolumeAttachment} object containing the storage
     * volume attachment details.
     */
    public StorageVolumeAttachment getById(String resourceId) {
        LOGGER.info("StorageVolumeAttachmentClient : getById : Start");

        StorageVolumeAttachment storageVolumeAttachment = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, resourceId),
                StorageVolumeAttachment.class);

        LOGGER.info("StorageVolumeAttachmentClient : getById : End");

        return storageVolumeAttachment;
    }

    /**
     * Returns the storage volume attachments details for all the
     * available storage volume attachments found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageVolumeAttachment}&gt; containing
     * the details for all found storage volume attachments.
     */
    public ResourceCollection<StorageVolumeAttachment> getAll() {
        LOGGER.info("StorageVolumeAttachmentClient : getAll : Start");

        ResourceCollection<StorageVolumeAttachment> storageVolumesAttachments = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, StorageVolumeAttachment.class);

        LOGGER.info("StorageVolumeAttachmentClient : getAll : End");

        return storageVolumesAttachments;
    }

    /**
     * Returns the storage volume attachment details for a available storage volume attachment.
     *
     * @param attachmentId storage volume attachment identifier as seen in HPE OneView.
     * @param pathId storage volume attachment path identifier as seen in HPE OneView.
     *
     * @return {@link StorageVolumeAttachment} object containing the storage volume attachment details.
     */
    public StorageVolumeAttachmentPath getAttachmentPath(String attachmentId, String pathId) {
        LOGGER.info("StorageVolumeAttachmentClient : getAttachmentPath : Start");

        StorageVolumeAttachmentPath storageVolumeAttachmentPath = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, attachmentId,
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI, pathId),
                StorageVolumeAttachmentPath.class);

        LOGGER.info("StorageVolumeAttachmentClient : getAttachmentPath : End");

        return storageVolumeAttachmentPath;
    }

    /**
     * Returns all the storage volume attachment paths for a storage volume attachment.
     *
     * @param attachmentId storage volume attachment identifier as seen in HPE OneView.
     *
     * @return {@link List} of {@link StorageVolumeAttachmentPath} containing the details.
     */
    public List<StorageVolumeAttachmentPath> getAllAttachmentPaths(String attachmentId) {
        LOGGER.info("StorageVolumeAttachmentClient : getAllAttachmentPaths : Start");

        List<StorageVolumeAttachmentPath> storageVolumeAttachmentPaths = baseClient.getResourceList(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, attachmentId,
                        ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI),
                new TypeToken<List<StorageVolumeAttachmentPath>>() {});

        LOGGER.info("StorageVolumeAttachmentClient : getAllAttachmentPaths : End");

        return storageVolumeAttachmentPaths;
    }

    /**
     * Returns the extra storage volume details for all the available extra storage volumes
     * found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ExtraStorageVolume}&gt; containing
     * the details for all found extra storage volumes.
     */
    public ResourceCollection<ExtraStorageVolume> getExtraUnmanagedAttachments() {
        LOGGER.info("StorageVolumeAttachmentClient : getExtraUnmanagedAttachments : Start");

        ResourceCollection<ExtraStorageVolume> extraAccessList = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI,
                ExtraStorageVolume.class,
                new UrlParameter("alertFixType", "ExtraUnmanagedStorageVolumes"));

        LOGGER.info("StorageVolumeAttachmentClient : getExtraUnmanagedAttachments : End");

        return extraAccessList;
    }

    /**
     * Removes extra presentations of a storage volume attachment from a specific server profile.
     *
     * @param repair data of the server profile that should have their storage volumes repaired.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 repairExtraUnmanagedAttachment(ExtraStorageVolumeRepair repair, boolean aSync) {
        LOGGER.info("StorageVolumeAttachmentClient : repairExtraUnmanagedAttachment : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI, repair, aSync);

        LOGGER.info("StorageVolumeAttachmentClient : repairExtraUnmanagedAttachment : End");

        return taskResource;
    }

}
