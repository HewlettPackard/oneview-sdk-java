/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
/**
 * A SAN represents a physical or logical fibre channel SAN or a Flat SAN (i.e. direct wire attach)
 */
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.SanResponseCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FcSansManagedSanClient {
    /**
     * The module aids in fetching the Managed San details for the specified
     * Managed San resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for Managed San as seen in HP OneView.
     * @return sanResponseDto, which is a object containing the Managed San
     *         details.
     */
    public SanResponse getManagedSan(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the Managed San details for all the Managed
     * San found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return sanResponseCollectionDto, which is a object containing the
     *         collection of Managed San details.
     */
    public SanResponseCollection getAllManagedSan(final RestParams params);

    /**
     * The module aids in fetching the Managed San details for the Managed San
     * name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Managed San name as seen in HP
     *            OneView.
     * @return sanResponseDto, which is a object containing the Managed San
     *         details.
     */
    public SanResponse getManagedSanByName(final RestParams params, final String name);

    /**
     * The module takes in an Managed San object or JsonRequest and updates the
     * existing Managed San based on resource Id. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for Managed San as seen in HP OneView.
     * @param sanResponseDto
     *            This is a object containing the Managed San details, used to
     *            update a Managed San
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Managed San Object which takes
     *            in a String containing the update to be made, which is
     *            converted to Managed San Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public SanResponse updateManagedSan(final RestParams params, final String resourceId, final SanResponse updateSanResponseDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in fetching the ManagedSan details for the ManagedSan
     * name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the ManagedSan name as seen in HP OneView.
     * @return String, which is a resource Id for the ManagedSan name as seen in
     *         HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
