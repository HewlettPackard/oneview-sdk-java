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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.storage.AddStorageVolume;
import com.hp.ov.sdk.dto.storage.AttachableStorageVolume;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolume;
import com.hp.ov.sdk.dto.storage.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.storage.StorageVolume;
import com.hp.ov.sdk.dto.storage.StorageVolumeSnapshot;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(StorageVolumeClient.STORAGE_VOLUME_URI)
public interface StorageVolumeClient extends
        SearchableResource<StorageVolume>,
        CreatableResource<AddStorageVolume>,
        DeletableResource {

    String STORAGE_VOLUME_URI = "/rest/storage-volumes";
    String ATTACHABLE_URI = "/attachable-volumes";
    String REPAIR_URI = "/repair";
    String REPAIR_FILTER_URI = "/repair?alertFixType=ExtraManagedStorageVolumePaths";
    String SNAPSHOTS_URI = "/snapshots";

    /**
     * Updates a {@link StorageVolume} identified by the given resource identifier.
     *
     * @param resourceId storage volume resource identifier as seen in HPE OneView.
     * @param storageVolume object containing the storage volume details.
     *
     * @return {@link String} containing the result of the operation.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    String update(@PathParam("resourceId") String resourceId, @BodyParam StorageVolume storageVolume);

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link AttachableStorageVolume}&gt; containing details
     * for all the attachable volumes that are connected to the specified networks based on the
     * storage system port's expected network connectivity.
     *
     * @return {@link ResourceCollection}&lt;{@link AttachableStorageVolume}&gt; containing
     * the details for all found attachable storage volumes.
     */
    @Endpoint(uri = ATTACHABLE_URI)
    ResourceCollection<AttachableStorageVolume> getAttachableVolumes();

    /**
     * Retrieves the {@link StorageVolumeSnapshot} details for the specified storage volume snapshot.
     *
     * @param resourceId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshotId resource identifier for the snapshot of the storage volume.
     *
     * @return {@link StorageVolumeSnapshot} or <code>null</code> in case the parameter snapshotId
     * does not match any existing storage volume snapshot.
     */
    @Endpoint(uri = "/{resourceId}" + SNAPSHOTS_URI + "/{snapshotId}")
    StorageVolumeSnapshot getSnapshot(@PathParam("resourceId") String resourceId,
            @PathParam("snapshotId") String snapshotId);

    /**
     * Returns all snapshots available for an existing storage volume.
     *
     * @param resourceId resource identifier for the storage volume as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageVolumeSnapshot}&gt; containing
     * the details for all found storage volume snapshots.
     */
    @Endpoint(uri = "/{resourceId}" + SNAPSHOTS_URI)
    ResourceCollection<StorageVolumeSnapshot> getAllSnapshots(@PathParam("resourceId") String resourceId);

    /**
     * Creates a snapshot for the storage volume specified by the resource identifier.
     *
     * @param resourceId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshot {@link StorageVolumeSnapshot} object containing the data to be used during
     * the snapshot creation.
     * @param options varargs of {@link RequestOption} which can be used to specify some request options.
     *
     * @return {@link TaskResource} which returns the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + SNAPSHOTS_URI, method = HttpMethod.POST)
    TaskResource createSnapshot(@PathParam("resourceId") String resourceId,
            @BodyParam StorageVolumeSnapshot snapshot, RequestOption ... options);

    /**
     * Deletes a storage volume snapshot from HPE OneView and storage system.
     *
     * @param storageVolumeId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshotId resource identifier for the snapshot of the storage volume.
     * @param options varargs of {@link RequestOption} which can be used to specify some request options.
     *
     * @return {@link TaskResource} which returns the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + SNAPSHOTS_URI + "/{snapshotId}", method = HttpMethod.DELETE)
    TaskResource deleteSnapshot(@PathParam("resourceId") String storageVolumeId,
            @PathParam("snapshotId") String snapshotId, RequestOption ... options);

    /**
     * Returns the list of extra managed storage volume paths.
     *
     * @return {@link ResourceCollection}&lt;{@link ExtraStorageVolume}&gt; containing
     * the details for all found extra storage volumes.
     */
    @Endpoint(uri = REPAIR_FILTER_URI)
    ResourceCollection<ExtraStorageVolume> getExtraManagedPaths();

    /**
     * Removes extra presentations from a specified storage volume on the storage system.
     *
     * @param repair information about the extra paths to delete.
     * @param options varargs of {@link RequestOption} which can be used to specify some request options.
     *
     * @return {@link TaskResource} which returns the task status for the process.
     */
    @Endpoint(uri = REPAIR_URI, method = HttpMethod.POST)
    TaskResource repairExtraManagedPath(@BodyParam ExtraStorageVolumeRepair repair, RequestOption ... options);

}
