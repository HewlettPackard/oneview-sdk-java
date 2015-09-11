/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.AttachableStorageVolumeCollection;
import com.hp.ov.sdk.dto.StorageVolumeCollection;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StorageVolumeClient {

    /**
     * The module aids in fetching the StorageVolume details for the specified
     * StorageVolume resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolume as seen in HP OneView.
     * @return storageVolumeDto, which is a object containing the StorageVolume
     *         details.
     */
    public StorageVolumeV2 getStorageVolume(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the StorageVolume details for all the
     * StorageVolume found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return storageVolumeCollectionDto, which is a object containing a
     *         collection of StorageVolume details.
     */
    public StorageVolumeCollection getAllStorageVolumes(final RestParams params);

    /**
     * The module aids in fetching the StorageVolume details for the
     * StorageVolume name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The name is the StorageVolume name as seen in HP OneView.
     * @return storageVolumeDto, which is a object containing the StorageVolume
     *         details.
     */
    public StorageVolumeV2 getStorageVolumeByName(final RestParams params, final String name);

    /**
     * The module aids in creation of StorageVolume when provided with the
     * StorageVolume details as AddStorageVolume object or JsonRequest.It can
     * process the request asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param addStorageVolumeDto
     *            This is a object containing the StorageVolume details, used to
     *            create a StorageVolume.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageVolume Object which
     *            takes in a String containing new StorageVolume details, which
     *            is converted to StorageVolume Object using adaptor and
     *            processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createStorageVolume(final RestParams params, final AddStorageVolumeV2 addStorageVolumeDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in an StorageVolume object or JsonRequest and updates
     * the existing StorageVolume based on resource Id.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolume as seen in HP OneView.
     * @param storageVolumeDto
     *            This is a object containing the StorageVolume details, used to
     *            update a StorageVolume.
     * @param useJsonRequest
     *            The JsonRequest body is part of StorageVolume Object which
     *            takes in a String containing the update to be made, which is
     *            converted to StorageVolume Object using adaptor and processed.
     * @return String, is Updated if successful.
     */
    public String updateStorageVolume(final RestParams params, final String resourceId, final StorageVolumeV2 storageVolumeDto,
            final boolean useJsonRequest);

    /**
     * The module aids in deleting a StorageVolume for the specified
     * StorageVolume resourceId.It can process the request asynchronously or
     * synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StorageVolume as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteStorageVolume(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This method aids in fetching the volumes that are connected on the
     * specified networks based on the storage system port's expected network
     * connectivity.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return attachableStorageVolumeCollection, volume object that are
     *         attached to storage system.
     */
    public AttachableStorageVolumeCollection getAttachableVolumes(final RestParams params);

    /**
     * The module aids in fetching the StorageVolume details for the
     * StorageVolume name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the StorageVolume name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the StorageVolume name as seen
     *         in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
