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
package com.hp.ov.sdk.rest.client;

import java.util.List;

import com.hp.ov.sdk.dto.ExtraStorageVolumeCollection;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

/**
 * Storage volume attachments represents the connection information for the storage volume.
 */
public interface StorageVolumeAttachmentsClient {

    /**
     * Returns the storage volume attachment details for the specified
     * storage volume attachment resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId storage volume attachment identifier as seen in HPE OneView.
     *
     * @return {@link StorageVolumeAttachment} object containing the storage volume attachment details.
     */
    StorageVolumeAttachment getStorageVolumeAttachment(RestParams params, String resourceId);

    /**
     * Returns the storage volume attachments details for all the
     * available storage volume attachments found under the current HPE OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link StorageVolumeAttachmentCollection} containing the details
     * for all storage volume attachments found.
     */
    StorageVolumeAttachmentCollection getAllStorageVolumeAttachments(RestParams params);

    /**
     * Returns the storage volume attachment details for a available storage volume attachment.
     *
     * @param params structure containing the connection details.
     * @param attachmentId storage volume attachment identifier as seen in HPE OneView.
     * @param pathId storage volume attachment path identifier as seen in HPE OneView.
     *
     * @return {@link StorageVolumeAttachment} object containing the storage volume attachment details.
     */
    StorageVolumeAttachmentPath getStorageVolumeAttachmentPath(RestParams params, String attachmentId, String pathId);

    /**
     * Returns all the storage volume attachment path details for a storage volume attachment.
     *
     * @param params structure containing the connection details.
     * @param attachmentId storage volume attachment identifier as seen in HPE OneView.
     *
     * @return {@link List} of {@link StorageVolumeAttachmentPath} containing the details.
     */
    List<StorageVolumeAttachmentPath> getAllStorageVolumeAttachmentPaths(RestParams params, String attachmentId);

    /**
     * Returns the extra storage volume details for all the available extra storage volumes
     * found under the current HPE OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link ExtraStorageVolumeCollection} containing all extra storage volumes found.
     */
    ExtraStorageVolumeCollection getExtraUnmanagedStorageVolumeAttachments(RestParams params);

    /**
     * Removes extra presentations of a storage volume attachment from a specific server profile.
     *
     * @param params structure containing the connection details.
     * @param repair data of the server profile that should have their storage volumes repaired.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 repairExtraUnmanagedStorageVolumeAttachment(RestParams params,
            ExtraStorageVolumeRepair repair, boolean aSync);

}
