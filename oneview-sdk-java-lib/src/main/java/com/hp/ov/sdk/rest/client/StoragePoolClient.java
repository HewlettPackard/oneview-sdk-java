/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StoragePoolClient {

    /**
     * The module aids in fetching the StoragePool details for the specified
     * StoragePool resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StoragePool as seen in HP OneView.
     * @return storagePoolDto, which is a object containing the StoragePool
     *         details.
     */
    public StoragePool getStoragePool(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the StoragePool details for all the
     * StoragePool found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return storagePoolCollectionDto, which is a object containing a
     *         collection of StoragePool details.
     */
    public StoragePoolCollection getAllStoragePools(final RestParams params);

    /**
     * The module aids in fetching the StoragePool details for the StoragePool
     * name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The name is the StoragePool name as seen in HP OneView.
     * @return storagePoolDto, which is a object containing the StoragePool
     *         details.
     */
    public StoragePool getStoragePoolByName(final RestParams params, final String name, final String storageSystemUri);

    /**
     * The module aids in creation of StoragePool when provided with the
     * StoragePool details as AddStoragePool object or JsonRequest.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param storagePoolDto
     *            This is a object containing the StoragePool details, used to
     *            create a StoragePool.
     * @param useJsonRequest
     *            The JsonRequest body is part of AddStoragePool Object which
     *            takes in a String containing the new StoragePool Details,
     *            which is converted to AddStoragePool Object using adaptor and
     *            processed.
     * @return String, is Created if successful.
     */
    public String createStoragePool(final RestParams params, final AddStoragePool storagePoolDto, final boolean useJsonRequest);

    /**
     * This module aids in refreshing the storage pool. To request a refresh of
     * a storage pool user must set the "refreshState" attribute to
     * RefreshPending state
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StoragePool as seen in HP OneView.
     * @param storagePoolDto
     *            This is a object containing the StoragePool details, used to
     *            update a StoragePool.
     * @param useJsonRequest
     *            The JsonRequest body is part of StoragePool Object which takes
     *            in a String containing the update to be made, which is
     *            converted to StoragePool Object using adaptor and processed.
     * @return String, is Updated if successful.
     */
    public String updateStoragePool(final RestParams params, final String resourceId, final StoragePool storagePoolDto,
            final boolean useJsonRequest);

    /**
     * The module aids in deleting a StoragePool for the specified StoragePool
     * resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for StoragePool as seen in HP OneView.
     * @return String, is Deleted if successful.
     */
    public String deleteStoragePool(final RestParams params, final String resourceId);

}
