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

import java.util.List;

import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface NetworkClient {

    /**
     * The module aids in fetching the Network details for the specified ethernet network
     * resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for ethernet network as seen in HPE OneView.
     * @return {@link Network} containing the ethernet network details.
     */
    Network getNetwork(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the ethernet network details for all the ethernet networks
     * found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link NetworkCollection} containing the collection of ethernet network details.
     */
    NetworkCollection getAllNetworks(final RestParams params);

    /**
     * The module aids in fetching the ethernet network details for the ethernet network name as
     * specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The resourceName is the ethernet network name as seen in HPE OneView.
     * @return {@link Network} containing the ethernet network details.
     */
    Network getNetworkByName(final RestParams params, final String name);

    /**
     * The module aids in creation of an ethernet network when provided with the ethernet network
     * details as a Network object or JsonRequest. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * <b>ATTENTION:</b> if you use async mode equals to <code>true</code>
     * and you set some connection template data, this information will not be used.
     * Thus, we strongly recommend the value <code>false</code> for the async flag if
     * you want to set bandwidth values.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param networkDto
     *            This is a object containing the ethernet network details, used to
     *            create a ethernet network.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously
     *            (preferable <code>false</code>).
     * @param useJsonRequest
     *            The JsonRequest body is part of Network object which takes in
     *            a String containing the new ethernet network details, which is
     *            converted to Network object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createNetwork(final RestParams params, final Network networkDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in a Network object or JsonRequest and updates the
     * existing ethernet network based on the resource identifier. It can
     * process the request asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for ethernet network as seen in HPE OneView.
     * @param networkDto
     *            This is a object containing the ethernet network details, used to
     *            update a ethernet network
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Network object which takes in
     *            a String containing the update to be made, which is converted
     *            to Network object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateNetwork(final RestParams params, final String resourceId, final Network networkDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a ethernet network for the specified ethernet network
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for ethernet network as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteNetwork(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in creation of set of ethernet networks when provided with the
     * ethernet network details as a BulkEthernetNetwork object or a JsonRequest containing
     * a VLAN identifier range. It can process the request asynchronously or synchronously,
     * based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param bulkEthernetDto
     *            This is a object containing the BulkEthernetNetwork details,
     *            used to create a set of ethernet networks for the mentioned range with
     *            VLAN name as suffix.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of BulkEthernetNetwork object
     *            which takes in a String containing the new bulk ethernet network
     *            details, which is converted to a BulkEthernetNetwork object
     *            using an adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createNetworkInBulk(final RestParams params, final BulkEthernetNetwork bulkEthernetDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * Returns a list of profile URIs for the ethernet network.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for ethernet network as seen in HPE OneView.
     *
     * @return list of profile URIs for the specified ethernet network.
     */
    List<String> getNetworkAssociatedProfiles(RestParams params, String resourceId);

    /**
     * Returns the uplink sets which are using an ethernet network.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for ethernet network as seen in HPE OneView.
     *
     * @return list of uplink sets using the specified ethernet network.
     */
    List<String> getNetworkAssociatedUplinkGroups(RestParams params, String resourceId);

    /**
     * The module aids in fetching the ethernet network resource identifier for the
     * ethernet network name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the ethernet network name as seen in HPE OneView.
     * @return String which is the resource identifier for the ethernet network name as seen in
     *         HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
