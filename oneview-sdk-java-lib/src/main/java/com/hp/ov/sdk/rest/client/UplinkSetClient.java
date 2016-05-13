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

import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface UplinkSetClient {

    /**
     * The module aids in fetching the uplink set details for the specified
     * uplink set resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for uplink set as seen in HPE OneView.
     * @return {@link UplinkSets} containing a the uplink set details.
     */
    UplinkSets getUplinkSet(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the uplink set details for all the uplink sets
     * found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link UplinkSetCollectionV2} containing a collection of uplink set details.
     */
    UplinkSetCollectionV2 getAllUplinkSet(final RestParams params);

    /**
     * The module aids in deleting a uplink set for the specified uplink set
     * resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for uplink set as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteUplinkSet(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module takes in an UplinkSet object or a JsonRequest and updates the
     * existing uplink set based on the resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for uplink set as seen in HPE OneView.
     * @param uplinkDto
     *            Object containing the uplink set details, used to update a uplink set.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of UplinkSet object which takes
     *            in a String containing the update to be made, which is
     *            converted to UplinkSet object using an adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateUplinkSet(final RestParams params, final String resourceId, final UplinkSets uplinkDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in fetching the uplink set details for the specified
     * uplink set name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param uplinkSetName
     *            The uplinkSetName is the uplink set name as seen in HPE OneView.
     * @return {@link UplinkSets} containing the uplink set details.
     */
    UplinkSets getUplinkSetsByName(final RestParams params, final String uplinkSetName);

    /**
     * This module aids in creation of uplink set.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param uplinkSetDto
     *            Object containing the uplink set details, used to create a uplink set.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of UplinkSet object which takes
     *            in a String containing the update to be made, which is
     *            converted to UplinkSets object using an adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createUplinkSet(final RestParams params, final UplinkSets uplinkSetDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module aids in fetching the uplink set resource identifier for the uplink set name as
     * specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the uplink set name as seen in HPE OneView.
     * @return String which is the resource identifier for the uplink set name as seen in
     *         HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
