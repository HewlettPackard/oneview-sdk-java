/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.dto.StorageSystemCollection;
import com.hp.ov.sdk.dto.StorageSystemV2;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.StorageTargetPortV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface StorageSystemClient
{

    /**
     * The module aids in fetching the StorageSystem details for the specified
     * StorageSystem resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for StorageSystem as seen in HP OneView.
     * @return storageSystemDto, which is a object containing the StorageSystem
     *         details.
     */
    public StorageSystemV2 getStorageSystem(final RestParams params,
            final String resourceId);

    /**
     * This module aids in retrieving a list of storage pools belonging to the
     * storage system referred by the Path property {arrayId} parameters
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param arrayId
     *        The arrayId for StorageSystem as seen in HP OneView.
     * @return storageSystemCollectionDto, which is a object containing a
     *         collection of StorageSystem details.
     */
    public StoragePoolCollection getStoragePoolsForStorageSystem(
            final RestParams params, final String arrayId);

    /**
     * This module aids in retrieving all managed target ports for the specified
     * storage system
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for StorageSystem as seen in HP OneView.
     * @return storageTargetPortCollectionDto, which is a object containing a
     *         collection of StorageTargetPort details.
     */
    public StorageTargetPortCollection getAllManagedPortsForStorageSystem(
            final RestParams params, final String resourceId);

    /**
     * This module aids in retrieving a managed target ports for the specified
     * storage system
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for StorageSystem as seen in HP OneView.
     * @param targetPortId
     *        The targetPortId where the managed port is defined.
     * @return storageTargetPortDto, which is a object containing the
     *         StorageTargetPort details.
     */
    public StorageTargetPortV2 getManagedPortsForStorageSystem(
            final RestParams params, final String resourceId,
            final String targetPortId);

    /**
     * The module aids in fetching the StorageSystem details for all the
     * StorageSystem found under the current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return storageSystemCollectionDto, which is a object containing a
     *         collection StorageSystem details.
     */
    public StorageSystemCollection getAllStorageSystems(final RestParams params);

    /**
     * The module aids in fetching the StorageSystem details for the
     * StorageSystem name as specified in HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param name
     *        The name is the StorageSystem name as seen in HP OneView.
     * @return storageSystemDto, which is a object containing the StorageSystem
     *         details.
     */
    public StorageSystemV2 getStorageSystemByName(final RestParams params,
            final String name);

    /**
     * The module aids in creation of StorageSystem when provided with the
     * StorageSystem details as AddStorageSystemCredential object or
     * JsonRequest.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param addStorageSystemCredentialsDto
     *        This is a object containing the StorageSystem details, used to
     *        create a StorageSystem.
     * @param useJsonRequest
     *        The JsonRequest body is part of StorageSystem Object which
     *        takes in a String containing new storageSystem details, which
     *        is converted to StorageSystem Object using adaptor and
     *        processed.
     * @return String, is Created if successful.
     */
    public String createStorageSystem(final RestParams params,
            final AddStorageSystemCredentials addStorageSystemCredentialsDto,
            final boolean useJsonRequest);

    /**
     * The module takes in an StorageSystem object or JsonRequest and updates
     * the existing StorageSystem based on resource Id. This can also be used
     * for updating the storage path of StorageSystem.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for StorageSystem as seen in HP OneView.
     * @param storageSystemDto
     *        This is a object containing the StorageSystem details, used to
     *        update a StorageSystem.
     * @param useJsonRequest
     *        The JsonRequest body is part of StorageSystem Object which
     *        takes in a String containing the update to be made, which is
     *        converted to StorageSystem Object using adaptor and processed.
     * @return String, is Updated if successful.
     */
    public String updateStorageSystem(final RestParams params,
            final String resourceId, final StorageSystemV2 storageSystemDto,
            final boolean useJsonRequest);

    /**
     * The module aids in deleting a StorageSystem for the specified
     * StorageSystem resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for StorageSystem as seen in HP OneView.
     * @return String, is Deleted if successful.
     */
    public String deleteStorageSystem(final RestParams params,
            final String resourceId);
}
