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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FcNetworkClient {

    /**
     * The module aids in fetching the FC network details for the specified
     * FC network resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for FC network as seen in HPE OneView.
     * @return {@link FcNetwork} containing the FC network details.
     */
    FcNetwork getFcNetwork(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the FC network details, where start and count
     * values are specified to fetch the number of FC networks.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param start
     *            Integer value specifying the start count.
     * @param count
     *            Integer value specifying the number of FC network fetched at once.
     * @return {@link ResourceCollection}&lt;{@link FcNetwork}&gt; containing
     * the details for all found FC networks.
     */
    ResourceCollection<FcNetwork> getFcNetworkByFilter(final RestParams params, final Integer start, final Integer count);

    /**
     * The module aids in fetching the FC network details for all FC network
     * registered under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link FcNetwork}&gt; containing
     * the details for all found FC networks.
     */
    ResourceCollection<FcNetwork> getAllFcNetworks(final RestParams params);

    /**
     * The module aids in fetching the FC network details for the specified
     * FC network name.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The resourceName is the FC network name as seen in HPE OneView.
     * @return {@link FcNetwork} containing the FC network details.
     */
    FcNetwork getFcNetworkByName(final RestParams params, final String name);

    /**
     * The module aids in creation of an FC network when provided with the FC network
     * details as an FcNetwork object or JsonRequest. It can process the request
     * asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param fcNetworkDto
     *            This is a object containing the FC network details, used to
     *            create FC network.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of FcNetwork object which takes
     *            in a String containing the new FC network details, which is
     *            converted to FcNetwork object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createFcNetwork(final RestParams params, final FcNetwork fcNetworkDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module takes in an FcNetwork object or JsonRequest and updates the
     * existing FC network based on the resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for FC network as seen in HPE OneView.
     * @param fcNetworkDto
     *            This is a object containing the update to be made to existing
     *            FC network pointed to by the above mentioned resource identifier.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of FcNetwork object which takes
     *            in a String containing the update to be made, which is
     *            converted to FcNetwork object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateFcNetwork(final RestParams params, final String resourceId, final FcNetwork fcNetworkDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting an FC network for the specified FC network
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for FC network as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteFcNetwork(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the FC network resource identifier for the FC network name
     * as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the FC network name as seen in HPE OneView.
     * @return String which is the resource identifier for the FC network name as seen in
     *         HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
