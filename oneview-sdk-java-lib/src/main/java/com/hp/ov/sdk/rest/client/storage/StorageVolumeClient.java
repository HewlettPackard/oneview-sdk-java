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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddStorageVolume;
import com.hp.ov.sdk.dto.AttachableStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolume;
import com.hp.ov.sdk.dto.StorageVolumeSnapshot;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class StorageVolumeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeClient.class);

    private final BaseClient baseClient;

    public StorageVolumeClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link StorageVolume} details for the specified storage volume.
     *
     * @param resourceId storage volume resource identifier as seen in HPE OneView.
     *
     * @return {@link StorageVolume} object containing the details.
     */
    public StorageVolume getById(String resourceId) {
        LOGGER.info("StorageVolumeClient : getById : Start");

        StorageVolume storageVolume = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_URI, resourceId), StorageVolume.class);

        LOGGER.info("StorageVolumeClient : getById : End");

        return storageVolume;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StorageVolume}&gt; containing details
     * for all the available storage volumes found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageVolume}&gt; containing
     * the details for all found storage volumes.
     */
    public ResourceCollection<StorageVolume> getAll() {
        LOGGER.info("StorageVolumeClient : getAll : Start");

        ResourceCollection<StorageVolume> storageVolumes = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_URI, StorageVolume.class);

        LOGGER.info("StorageVolumeClient : getAll : End");

        return storageVolumes;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link StorageVolume}&gt; containing details
     * for the available storage volumes found under the current HPE OneView that match the name.
     *
     * @param name storage volume name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageVolume}&gt; containing
     * the details for the found storage volumes.
     */
    public ResourceCollection<StorageVolume> getByName(String name) {
        LOGGER.info("StorageVolumeClient : getByName : Start");

        ResourceCollection<StorageVolume> storageVolumes = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_URI, StorageVolume.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("StorageVolumeClient : getByName : End");

        return storageVolumes;
    }

    /**
     * Creates a storage volume according to the provided {@link AddStorageVolume} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param addStorageVolume object containing the storage volume details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(AddStorageVolume addStorageVolume, boolean aSync) {
        LOGGER.info("StorageVolumeClient : create : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                ResourceUris.STORAGE_VOLUME_URI, addStorageVolume, aSync);

        LOGGER.info("StorageVolumeClient : create : End");

        return taskResource;
    }

    /**
     * Updates a {@link StorageVolume} identified by the given resource identifier.
     *
     * @param resourceId storage volume resource identifier as seen in HPE OneView.
     * @param storageVolume object containing the storage volume details.
     *
     * @return {@link String} containing the result of the operation.
     */
    public String update(String resourceId, StorageVolume storageVolume) {
        LOGGER.info("StorageVolumeClient : update : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_URI, resourceId), storageVolume);

        String response = baseClient.executeRequest(request, String.class);

        LOGGER.info("StorageVolumeClient : update : End");

        return response;
    }

    /**
     * Deletes the {@link StorageVolume} identified by the given resource identifier.
     *
     * @param resourceId storage volume resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("StorageVolumeClient : delete : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_URI, resourceId), aSync);

        LOGGER.info("StorageVolumeClient : delete : End");

        return taskResource;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link AttachableStorageVolume}&gt; containing details
     * for all the attachable volumes that are connected to the specified networks based on the
     * storage system port's expected network connectivity.
     *
     * @return {@link ResourceCollection}&lt;{@link AttachableStorageVolume}&gt; containing
     * the details for all found attachable storage volumes.
     */
    public ResourceCollection<AttachableStorageVolume> getAttachableVolumes() {
        LOGGER.info("StorageVolumeClient : getAttachableVolumes : Start");

        ResourceCollection<AttachableStorageVolume> attachableStorageVolumes = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_ATTACHABLE_URI, AttachableStorageVolume.class);

        LOGGER.info("StorageVolumeClient : getAttachableVolumes : End");

        return attachableStorageVolumes;
    }

    /**
     * Retrieves the {@link StorageVolumeSnapshot} details for the specified storage volume snapshot.
     *
     * @param storageVolumeId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshotId resource identifier for the snapshot of the storage volume.
     *
     * @return {@link StorageVolumeSnapshot} or <code>null</code> in case the parameter snapshotId
     * does not match any existing storage volume snapshot.
     */
    public StorageVolumeSnapshot getSnapshot(String storageVolumeId, String snapshotId) {
        LOGGER.info("StorageVolumeClient : getSnapshot : Start");

        StorageVolumeSnapshot storageVolumeSnapshot = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                        ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI, snapshotId),
                StorageVolumeSnapshot.class);

        LOGGER.info("StorageVolumeClient : getSnapshot : End");

        return storageVolumeSnapshot;
    }

    /**
     * Returns all the snapshots available for an existing storage volume.
     *
     * @param storageVolumeId resource identifier for the storage volume as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link StorageVolumeSnapshot}&gt; containing
     * the details for all found storage volume snapshots.
     */
    public ResourceCollection<StorageVolumeSnapshot> getAllSnapshots(String storageVolumeId) {
        LOGGER.info("StorageVolumeClient : getAllSnapshots : Start");

        ResourceCollection<StorageVolumeSnapshot> storageVolumeSnapshots = baseClient.getResourceCollection(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                        ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI),
                StorageVolumeSnapshot.class);

        LOGGER.info("StorageVolumeClient : getAllSnapshots : End");

        return storageVolumeSnapshots;
    }

    /**
     * Creates a snapshot for the storage volume specified by the resource identifier.
     *
     * @param storageVolumeId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshot {@link StorageVolumeSnapshot} object containing the data to be used during
     * the snapshot creation.
     * @param aSync flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 createSnapshot(String storageVolumeId, StorageVolumeSnapshot snapshot, boolean aSync) {
        LOGGER.info("StorageVolumeClient : createSnapshot : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                        ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI), snapshot, aSync);

        LOGGER.info("StorageVolumeClient : createSnapshot : End");

        return taskResource;
    }

    /**
     * Deletes a storage volume snapshot from HPE OneView and storage system.
     *
     * @param storageVolumeId resource identifier for a storage volume as seen in HPE OneView.
     * @param snapshotId resource identifier for the snapshot of the storage volume.
     * @param aSync flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 deleteSnapshot(String storageVolumeId, String snapshotId, boolean aSync) {
        LOGGER.info("StorageVolumeClient : deleteSnapshot : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                        ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI, snapshotId), aSync);

        LOGGER.info("StorageVolumeClient : deleteSnapshot : End");

        return taskResource;
    }

    /**
     * Returns the list of extra managed storage volume paths.
     *
     * @return {@link ResourceCollection}&lt;{@link ExtraStorageVolume}&gt; containing
     * the details for all found extra storage volumes.
     */
    public ResourceCollection<ExtraStorageVolume> getExtraManagedPaths() {
        LOGGER.info("StorageVolumeClient : getExtraManagedPaths : Start");

        ResourceCollection<ExtraStorageVolume> extraAccessList = baseClient.getResourceCollection(
                ResourceUris.STORAGE_VOLUME_REPAIR_URI,
                ExtraStorageVolume.class,
                new UrlParameter("alertFixType", "ExtraManagedStorageVolumePaths"));

        LOGGER.info("StorageVolumeClient : getExtraManagedPaths : End");

        return extraAccessList;
    }

    /**
     * Removes extra presentations from a specified storage volume on the storage system.
     *
     * @param repair information about the extra paths to delete.
     * @param aSync flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 repairExtraManagedPath(ExtraStorageVolumeRepair repair, boolean aSync) {
        LOGGER.info("StorageVolumeClient : repairExtraManagedPath : Start");

        TaskResourceV2 taskResource = baseClient.createResource(ResourceUris.STORAGE_VOLUME_REPAIR_URI, repair, aSync);

        LOGGER.info("StorageVolumeClient : repairExtraManagedPath : End");

        return taskResource;
    }

}
