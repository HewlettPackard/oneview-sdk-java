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

import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface NetworkSetClient {

    /**
     * The module aids in fetching the network set details for the specified
     * network set resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for network set as seen in HPE OneView.
     * @return {@link NetworkSets} containing the network set details.
     */
    NetworkSets getNetworkSets(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the network set details for all the
     * network sets found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link NetworkSetCollection} containing a collection of network set details.
     */
    NetworkSetCollection getAllNetworkSets(final RestParams params);

    /**
     * The module aids in fetching the network set details for the network set
     * name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the network set name as seen in HPE OneView.
     * @return {@link NetworkSets} containing the network set details.
     */
    NetworkSets getNetworkSetsByName(final RestParams params, final String name);

    /**
     * The module aids in the creation of a network set when provided with the
     * network set details as a NetworkSets object or JsonRequest. It can process
     * the request asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param networkSet
     *            Object containing the network set details, used to create a network set.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of NetworkSets object which takes
     *            in a String containing the new network set details, which is
     *            converted to a NetworkSets object using an adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createNetworkSet(final RestParams params, final NetworkSets networkSet, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in a NetworkSet object or JsonRequest and updates the
     * existing network set based on the resource identifier. It can process
     * the request asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for network set as seen in HPE OneView.
     * @param networkSetDto
     *            Object containing the network set details, used to update a network set.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of NetworkSet object which takes
     *            in a String containing the update to be made, which is
     *            converted to NetworkSet object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateNetworkSet(final RestParams params, final String resourceId, final NetworkSets networkSetDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a network set for the specified network set
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for network set as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteNetworkSet(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the network set resource identifier for the network set
     * name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the network set name as seen in HPE OneView.
     * @return String which is the resource identifier for the network set name as seen in
     *         HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
