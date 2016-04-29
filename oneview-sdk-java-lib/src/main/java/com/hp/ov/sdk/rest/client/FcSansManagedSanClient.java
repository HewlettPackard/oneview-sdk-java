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

import com.hp.ov.sdk.dto.EndpointResponseCollection;
import com.hp.ov.sdk.dto.EndpointsCsvFileResponse;
import com.hp.ov.sdk.dto.FcSansManagedSanTask;
import com.hp.ov.sdk.dto.SanRequest;
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.SanResponseCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

/**
 * A SAN represents a physical or logical fibre channel SAN or a Flat SAN (i.e. direct wire attach).
 */
public interface FcSansManagedSanClient {

    /**
     * The module aids in fetching the managed SAN details for the specified
     * managed SAN resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for managed SAN as seen in HPE OneView.
     * @return {@link SanResponse} containing the managed SAN details.
     */
    SanResponse getManagedSan(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the managed SAN details for all the managed SANs
     * found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link SanResponseCollection} containing the collection of managed SAN details.
     */
    SanResponseCollection getAllManagedSan(final RestParams params);

    /**
     * The module aids in fetching the managed SAN details for the managed SAN
     * name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the managed SAN name as seen in HPE OneView.
     * @return {@link SanResponse} containing the managed SAN details.
     */
    SanResponse getManagedSanByName(final RestParams params, final String name);

    /**
     * The module takes in a SanRequest object or JsonRequest and updates the
     * existing managed SAN based on the resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for managed SAN as seen in HPE OneView.
     * @param updateSanRequest
     *            This is a object containing the managed SAN details, used to
     *            update a managed SAN.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of SanRequest object which takes
     *            in a String containing the update to be made, which is
     *            converted to SanRequest object using adaptor and processed.
     * @return {@link SanResponse} containing the managed SAN details.
     */
    SanResponse updateManagedSan(final RestParams params, final String resourceId, final SanRequest updateSanRequest,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * Reports all endpoints known within the specified SAN, and data about that endpoint.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for managed SAN as seen in HPE OneView.
     * @return {@link EndpointResponseCollection} which contains data of all endpoints
     *          os the specified SAN.
     */
    EndpointResponseCollection getEndpointsOfManagedSan(final RestParams params, final String resourceId);

    /**
     * Creates a new SAN issues report indicating any connectivity issues in the specified SAN.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for managed SAN as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link FcSansManagedSanTask} which contains the task status for the process
     *          and the report data.
     */
    FcSansManagedSanTask createManagedSanIssuesReport(final RestParams params, final String resourceId, boolean aSync);

    /**
     * Creates an endpoints CSV file for a SAN. Clients should perform a GET on the
     * URI in the response to download the file.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for managed SAN as seen in HPE OneView.
     * @return {@link EndpointsCsvFileResponse} contains the data of the created CSV file.
     */
    EndpointsCsvFileResponse createEndpointsCsvOfManagedSan(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the managed SAN resource identifier for the managed SAN
     * name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the managed SAN name as seen in HPE OneView.
     * @return String which is the resource identifier for the managed SAN name as seen in
     *         HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
