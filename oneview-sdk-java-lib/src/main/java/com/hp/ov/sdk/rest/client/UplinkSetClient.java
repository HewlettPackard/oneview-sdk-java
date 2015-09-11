/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface UplinkSetClient {
    /**
     * The module aids in fetching the UplinkSet details for the specified
     * UplinkSet resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for UplinkSet as seen in HP OneView.
     * @return uplinkSetDto, which is a object containing a the uplinkSet
     *         details.
     */
    public UplinkSets getUplinkSet(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the UplinkSet details for all the UplinkSet
     * found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return uplinkSetCollectionDto, which is a object containing a collection
     *         of uplinkSet details.
     */
    public UplinkSetCollectionV2 getAllUplinkSet(final RestParams params);

    // ToDo - implement the method create, update

    /**
     * The module aids in deleting a UplinkSet for the specified UplinkSet
     * resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for UplinkSet as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteUplinkSet(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module takes in an UplinkSet object or JsonRequest and updates the
     * existing UplinkSet based on resource Id. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for UplinkSet as seen in HP OneView.
     * @param uplinkDto
     *            This is a object containing the UplinkSet details, used to
     *            update a UplinkSet.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of UplinkSet Object which takes
     *            in a String containing the update to be made, which is
     *            converted to UplinkSet Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateUplinkSet(final RestParams params, final String resourceId, final UplinkSets uplinkDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in fetching the UplinkSets details for the specified
     * UplinkSets name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param uplinkSetName
     *            The resourceName is the UplinkSets name as seen in HP OneView.
     * @return uplinkSetsDto, which is a object containing the UplinkSets
     *         details.
     */
    public UplinkSets getUplinkSetsByName(final RestParams params, final String uplinkSetName);

    /**
     * This module aids in creation of uplinkSet.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param uplinkSetDto
     *            This is a object containing the UplinkSet details, used to
     *            create a UplinkSet.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of UplinkSet Object which takes
     *            in a String containing the update to be made, which is
     *            converted to UplinkSets Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createUplinkSet(final RestParams params, final UplinkSets uplinkSetDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module aids in fetching the Switch details for the Switch name as
     * specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Switch name as seen in HP OneView.
     * @return String, which is a resource Id for the Switch name as seen in
     *         HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
