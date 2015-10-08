/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface NetworkClient {

    /**
     * The module aids in fetching the Network details for the specified Network
     * resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for Network as seen in HP OneView.
     * @return networkDto, which is a object containing the Network details.
     */
    public Network getNetwork(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the Network details for all the Networks
     * found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return networkCollectionDto, which is a object containing the collection
     *         of Network details.
     */
    public NetworkCollection getAllNetworks(final RestParams params);

    /**
     * The module aids in fetching the Network details for the Network name as
     * specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Network name as seen in HP OneView.
     * @return networkDto, which is a object containing the Network details.
     */
    public Network getNetworkByName(final RestParams params, final String name);

    /**
     * The module aids in creation of Network when provided with the Network
     * details as Network object or JsonRequest. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param networkDto
     *            This is a object containing the Network details, used to
     *            create a Network
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Network Object which takes in
     *            a String containing the new Network details, which is
     *            converted to Network Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createNetwork(final RestParams params, final Network networkDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an Network object or JsonRequest and updates the
     * existing Network based on resource Id. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for Network as seen in HP OneView.
     * @param networkDto
     *            This is a object containing the Network details, used to
     *            update a Network
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Network Object which takes in
     *            a String containing the update to be made, which is converted
     *            to Network Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateNetwork(final RestParams params, final String resourceId, final Network networkDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a Network for the specified Network
     * resourceId. It can process the request asynchronously or synchronously
     * based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for Network as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteNetwork(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in creation of set of Network when provided with the
     * Network details as Network object or JsonRequest containing a range of
     * virtual LAN Id. It can process the request asynchronously or
     * synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param bulkEthernetDto
     *            This is a object containing the BulkEthernetNetwork details,
     *            used to create a set of Networks for the mentioned range with
     *            virtual LAN Name as suffix
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of BulkEthernetNetwork Object
     *            which takes in a String containing the new BulkEthernetNetwork
     *            details, which is converted to BulkEthernetNetwork Object
     *            using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createNetworkInBulk(final RestParams params, final BulkEthernetNetwork bulkEthernetDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in fetching the Network details for the Network name as
     * specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Network name as seen in HP OneView.
     * @return String, which is a resource Id for the Network name as seen in
     *         HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
