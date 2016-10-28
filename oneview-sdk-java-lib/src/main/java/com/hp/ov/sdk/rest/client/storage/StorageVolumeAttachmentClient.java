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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolume;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.storage.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.storage.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

/**
 * Storage volume attachments represents the connection information for the storage volume.
 */
@Api(StorageVolumeAttachmentClient.STORAGE_VOLUME_ATTACHMENT_URI)
public interface StorageVolumeAttachmentClient extends SearchableResource<StorageVolumeAttachment> {

    String STORAGE_VOLUME_ATTACHMENT_URI = "/rest/storage-volume-attachments";
    String STORAGE_VOLUME_ATTACHMENT_PATH_URI = "/paths";
    String STORAGE_VOLUME_ATTACHMENT_REPAIR_URI = "/repair";

    /**
     * Returns the storage volume attachment details for an available storage volume attachment.
     *
     * @param attachmentId storage volume attachment identifier as seen in HPE OneView.
     * @param pathId storage volume attachment path identifier as seen in HPE OneView.
     *
     * @return {@link StorageVolumeAttachment} object containing the storage volume attachment details.
     */
    @Endpoint(uri = "/{attachmentId}" + STORAGE_VOLUME_ATTACHMENT_PATH_URI + "/{pathId}")
    StorageVolumeAttachmentPath getAttachmentPath(@PathParam("attachmentId") String attachmentId,
            @PathParam("pathId") String pathId);

    /**
     * Returns all storage volume attachment paths for a storage volume attachment.
     *
     * @param attachmentId storage volume attachment identifier as seen in HPE OneView.
     *
     * @return {@link List} of {@link StorageVolumeAttachmentPath} containing the details.
     */
    @Endpoint(uri = "/{attachmentId}" + STORAGE_VOLUME_ATTACHMENT_PATH_URI)
    List<StorageVolumeAttachmentPath> getAllAttachmentPaths(@PathParam("attachmentId") String attachmentId);

    /**
     * Returns the extra storage volume details for all the available extra storage volumes
     * found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ExtraStorageVolume}&gt; containing
     * the details for all found extra storage volumes.
     */
    @Endpoint(uri = STORAGE_VOLUME_ATTACHMENT_REPAIR_URI + "?alertFixType=ExtraUnmanagedStorageVolumes")
    ResourceCollection<ExtraStorageVolume> getExtraUnmanagedStorageVolumes();

    /**
     * Removes extra presentations of a storage volume attachment from a specific server profile.
     *
     * @param repair data of the server profile that should have their storage volumes repaired.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = STORAGE_VOLUME_ATTACHMENT_REPAIR_URI, method = HttpMethod.POST)
    TaskResource repairExtraPresentations(@BodyParam ExtraStorageVolumeRepair repair, RequestOption... options);

}
