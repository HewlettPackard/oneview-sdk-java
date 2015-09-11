/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface NetworkSetClient {

    /**
     * The module aids in fetching the NetworkSets details for the specified
     * NetworkSets resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for NetworkSet as seen in HP OneView.
     * @return networkSetDto, which is a object containing the NetworkSet
     *         details.
     */
    public NetworkSets getNetworkSets(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the NetworkSet details for all the
     * NetworkSets found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return networkSetCollectionDto, which is a object containing a
     *         collection of NetworkSet details.
     */
    public NetworkSetCollection getAllNetworkSets(final RestParams params);

    /**
     * The module aids in fetching the NetworkSet details for the NetworkSet
     * name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the NetworkSet name as seen in HP OneView.
     * @return networkSetDto, which is a object containing the NetworkSet
     *         details.
     */
    public NetworkSets getNetworkSetsByName(final RestParams params, final String name);

    /**
     * The module aids in creation of NetworkSet when provided with the
     * NetworkSet details as NetworkSet object or JsonRequest. It can process
     * the request asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param networkSet
     *            This is a object containing the NetworkSet details, used to
     *            create a NetworkSet
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of NetworkSet Object which takes
     *            in a String containing the new NetworkSet details, which is
     *            converted to NetworkSet Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createNetworkSet(final RestParams params, final NetworkSets networkSet, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an NetworkSet object or JsonRequest and updates the
     * existing NetworkSet based on resource Id. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for NetworkSet as seen in HP OneView.
     * @param networkSetDto
     *            This is a object containing the NetworkSet details, used to
     *            update a NetworkSet
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of NetworkSet Object which takes
     *            in a String containing the update to be made, which is
     *            converted to NetworkSet Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateNetworkSet(final RestParams params, final String resourceId, final NetworkSets networkSetDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a NetworkSet for the specified NetworkSet
     * resourceId. It can process the request asynchronously or synchronously
     * based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for NetworkSet as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteNetworkSet(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the NetworkSet details for the NetworkSet
     * name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the NetworkSet name as seen in HP OneView.
     * @return String, which is a resource Id for the NetworkSet name as seen in
     *         HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
