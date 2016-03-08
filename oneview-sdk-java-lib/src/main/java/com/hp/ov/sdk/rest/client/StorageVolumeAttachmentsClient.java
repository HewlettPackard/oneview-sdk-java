/*******************************************************************************
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
 *******************************************************************************/
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
 * Storage Volume Attachments resource represents the connection information for the volume.
 * Create, Update, Delete operations should be performed through the Server-Profiles API.
 */
public interface StorageVolumeAttachmentsClient {

    String REPAIR_FILTER = "alertFixType=ExtraUnmanagedStorageVolumes";

    /**
     * Retrieves the {@link StorageVolumeAttachment} details for the
     * specified storage volume attachment resourceId.
     *
     * @param params structure containing the connection details.
     * @param resourceId storage volume attachment id as seen in HP OneView.
     *
     * @return {@link StorageVolumeAttachment} object containing the details.
     */
    StorageVolumeAttachment getStorageVolumeAttachment(RestParams params, String resourceId);

    /**
     * Retrieves the {@link StorageVolumeAttachment} details for all the
     * available storage volume attachments found under current HP OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link StorageVolumeAttachmentCollection} object containing the details
     * for all found storage volume attachments.
     */
    StorageVolumeAttachmentCollection getAllStorageVolumeAttachments(RestParams params);

    /**
     * Retrieves the {@link StorageVolumeAttachmentPath} details for a
     * available storage volume attachment.
     *
     * @param params structure containing the connection details.
     * @param attachmentId storage volume attachment id as seen in HP OneView.
     * @param pathId storage volume attachment path id as seen in HP OneView.
     *
     * @return {@link StorageVolumeAttachmentPath} object containing the details.
     */
    StorageVolumeAttachmentPath getStorageVolumeAttachmentPath(RestParams params, String attachmentId, String pathId);

    /**
     * Retrieves all the {@link StorageVolumeAttachmentPath} details for a
     * storage volume attachment.
     *
     * @param params structure containing the connection details.
     * @param attachmentId storage volume attachment id as seen in HP OneView.
     *
     * @return {@link List} of {@link StorageVolumeAttachmentPath} containing the details.
     */
    List<StorageVolumeAttachmentPath> getAllStorageVolumeAttachmentPaths(RestParams params, String attachmentId);

    /**
     * Retrieves the {@link com.hp.ov.sdk.dto.ExtraStorageVolume} details for all the
     * available extra storage volumes found under current HP OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link ExtraStorageVolumeCollection} containing all extra storage
     * volumes found.
     */
    ExtraStorageVolumeCollection getExtraUnmanagedStorageVolumeAttachments(RestParams params);

    /**
     * Removes extra presentations of a storage volume from a specific server profile.
     *
     * @param params structure containing the connection details.
     * @param repair data of the server profile that should have their
     *               storage volumes repaired.
     * @param aSync flag to indicate whether the request should be processed
     *              asynchronously or synchronously.
     *
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 repairExtraUnmanagedStorageVolumeAttachment(RestParams params,
            ExtraStorageVolumeRepair repair, boolean aSync);

}
