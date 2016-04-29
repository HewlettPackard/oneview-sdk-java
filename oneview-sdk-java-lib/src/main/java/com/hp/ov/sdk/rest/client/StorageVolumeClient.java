/*
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
 */
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.AttachableStorageVolumeCollection;
import com.hp.ov.sdk.dto.ExtraStorageVolumeCollection;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.StorageVolumeCollection;
import com.hp.ov.sdk.dto.StorageVolumeSnapshot;
import com.hp.ov.sdk.dto.StorageVolumeSnapshotCollection;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StorageVolumeClient {

    String REPAIR_FILTER = "alertFixType=ExtraManagedStorageVolumePaths";

    /**
     * The module aids in fetching the storage volume details for the specified
     * storage volume resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage volume as seen in HPE OneView.
     * @return {@link StorageVolumeV2} containing the storage volume details.
     */
    StorageVolumeV2 getStorageVolume(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the storage volume details for all the
     * storage volumes found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link StorageVolumeCollection} containing a collection of storage volume details.
     */
    StorageVolumeCollection getAllStorageVolumes(final RestParams params);

    /**
     * The module aids in fetching the storage volume details for the
     * storage volume name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the storage volume name as seen in HPE OneView.
     * @return {@link StorageVolumeV2} containing the storage volume details.
     */
    StorageVolumeV2 getStorageVolumeByName(final RestParams params, final String name);

    /**
     * The module aids in creation of storage volume when provided with the
     * storage volume details as an AddStorageVolume object or JsonRequest. It can
     * process the request asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param addStorageVolumeDto
     *            Object containing the storage volume details, used to create a storage volume.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of AddStorageVolumeV2 object which
     *            takes in a String containing new storage volume details, which
     *            is converted to a StorageVolume object using adaptor and
     *            processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createStorageVolume(final RestParams params, final AddStorageVolumeV2 addStorageVolumeDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in an StorageVolumeV2 object or JsonRequest and updates
     * the existing storage volume based on the resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage volume as seen in HPE OneView.
     * @param storageVolumeDto
     *            Object containing the storage volume details, used to update a storage volume.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageVolumeV2 object which
     *            takes in a String containing the update to be made, which is
     *            converted to StorageVolumeV2 object using an adaptor and processed.
     * @return String <code>Updated</code> if successful.
     */
    String updateStorageVolume(final RestParams params, final String resourceId, final StorageVolumeV2 storageVolumeDto,
            final boolean useJsonRequest);

    /**
     * The module aids in deleting a storage volume for the specified storage volume
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for storage volume as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteStorageVolume(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in fetching the attachable volumes that are connected to the
     * specified networks based on the storage system port's expected network connectivity.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link AttachableStorageVolumeCollection} containing the attached storage
     *         volume details.
     */
    AttachableStorageVolumeCollection getAttachableVolumes(final RestParams params);

    /**
     * Returns a snapshot of a storage volume.
     *
     * @param params structure containing the connection details.
     * @param storageVolumeId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshotId resource identifier for the snapshot of the storage volume.
     *
     * @return {@link StorageVolumeSnapshot} or <code>null</code> in case the parameter snapshotId
     * does not match any existing storage volume snapshot.
     */
    StorageVolumeSnapshot getStorageVolumeSnapshot(RestParams params, String storageVolumeId, String snapshotId);

    /**
     * Returns all the snapshots available of an existing storage volume.
     *
     * @param params structure containing the connection details.
     * @param storageVolumeId resource identifier for the storage volume as seen in HPE OneView.
     *
     * @return {@link StorageVolumeSnapshotCollection} containing all snapshots for
     * the storage volume.
     */
    StorageVolumeSnapshotCollection getAllStorageVolumeSnapshots(RestParams params, String storageVolumeId);

    /**
     * Creates a snapshot for the storage volume specified by the resource identifier.
     *
     * @param params structure containing the connection details.
     * @param storageVolumeId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshot {@link StorageVolumeSnapshot} object containing the data to be used during
     * the snapshot creation.
     * @param aSync flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    TaskResourceV2 createStorageVolumeSnapshot(RestParams params, String storageVolumeId,
            StorageVolumeSnapshot snapshot, boolean aSync);

    /**
     * Removes a storage volume snapshot from HPE OneView and storage system.
     *
     * @param params structure containing the connection details.
     * @param storageVolumeId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshotId resource identifier for the snapshot of the storage volume.
     * @param aSync flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    TaskResourceV2 deleteStorageVolumeSnapshot(RestParams params, String storageVolumeId,
            String snapshotId, boolean aSync);

    /**
     * Returns the list of extra managed storage volume paths.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link ExtraStorageVolumeCollection} containing the resources found.
     */
    ExtraStorageVolumeCollection getExtraManagedStorageVolumePaths(RestParams params);

    /**
     * Removes extra presentations from a specified storage volume on the storage system.
     *
     * @param params structure containing the connection details.
     * @param repair information about the extra paths to delete.
     * @param aSync flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    TaskResourceV2 repairExtraManagedStorageVolumePath(RestParams params,
            ExtraStorageVolumeRepair repair, boolean aSync);

    /**
     * The module aids in fetching the storage volume resource identifier for the
     * storage volume name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the storage volume name as seen in HPE OneView.
     * @return String which is the resource identifier for the storage volume name as seen
     *         in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
