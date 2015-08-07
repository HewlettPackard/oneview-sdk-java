/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.FcNetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FcNetworkClient {

    /**
     * The module aids in fetching the FcNetwork details for the specified
     * FcNetwork resourceId
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for FcNetwork as seen in HP OneView.
     * @return fcNetworkDto, which is a object containing the FcNetwork details.
     */
    public FcNetwork getFcNetwork(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the FcNetwork details, where start and count
     * values are specified to fetch number of FcNetwork.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param start
     *            Integer value specifying the start count
     * @param count
     *            Integer value specifying the number of FcNetwork fetched at
     *            once.
     * @return fcNetworkCollectionDto, which is a object containing a collection
     *         of FcNetwork details.
     */
    public FcNetworkCollection getFcNetworkByFilter(final RestParams params, final Integer start, final Integer count);

    /**
     * The module aids in fetching the FcNetwork details for all FcNetwork
     * registered under current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return fcNetworkCollectionDto, which is a object containing the
     *         collection of FcNetwork details.
     */
    public FcNetworkCollection getAllFcNetworks(final RestParams params);

    /**
     * The module aids in fetching the FcNetwork details for the specified
     * FcNetwork name.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the FcNetwork name as seen in HP OneView.
     * @return fcNetworkDto, which is a object containing the FcNetwork details.
     */
    public FcNetwork getFcNetworkByName(final RestParams params, final String name);

    /**
     * The module aids in creation of FcNetwork when provided with the FcNetwork
     * details as FcNetwork object or JsonRequest. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param fcNetworkDto
     *            This is a object containing the FcNetwork details, used to
     *            create FcNetwork
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of FcNetwork Object which takes
     *            in a String containing the new FcNetwork details, which is
     *            converted to FcNetwork Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createFcNetwork(final RestParams params, final FcNetwork fcNetworkDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an FcNetwork object or JsonRequest and updates the
     * existing FcNetwork based on resource Id.It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for FcNetwork as seen in HP OneView.
     * @param fcNetworkDto
     *            This is a object containing the update to be made to existing
     *            FcNetwork pointed to by the above mentioned resourceId
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of FcNetwork Object which takes
     *            in a String containing the update to be made, which is
     *            converted to FcNetwork Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateFcNetwork(final RestParams params, final String resourceId, final FcNetwork fcNetworkDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a FcNetwork for the specified FcNetwork
     * resourceId. It can process the request asynchronously or synchronously
     * based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for FcNetwork as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteFcNetwork(final RestParams params, final String resourceId, final boolean aSync);

}
