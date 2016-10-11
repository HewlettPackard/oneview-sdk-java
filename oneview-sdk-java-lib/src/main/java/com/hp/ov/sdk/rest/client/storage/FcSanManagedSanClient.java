/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client.storage;

import java.util.List;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.fcsans.EndpointResponse;
import com.hp.ov.sdk.dto.fcsans.EndpointsCsvFileResponse;
import com.hp.ov.sdk.dto.fcsans.LocateSanResponse;
import com.hp.ov.sdk.dto.fcsans.SanRequest;
import com.hp.ov.sdk.dto.fcsans.SanResponse;
import com.hp.ov.sdk.dto.storage.FcSansManagedSanTask;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(FcSanManagedSanClient.FC_SANS_MANAGED_SAN_URI)
public interface FcSanManagedSanClient extends SearchableResource<SanResponse> {

    String FC_SANS_MANAGED_SAN_URI = "/rest/fc-sans/managed-sans";
    String FC_SANS_MANAGED_SAN_ENDPOINTS_URI = "/endpoints";
    String FC_SANS_MANAGED_SAN_ISSUES_URI = "/issues";
    String FC_SANS_WWN_LOCATE_URI = "?locate=";

    /**
     * Updates a managed SAN identified by the given resource identifier.
     *
     * @param resourceId managed SAN resource identifier as seen in HPE OneView.
     * @param sanRequest object containing the managed SAN details.
     *
     * @return {@link SanResponse} containing the updated managed SAN details.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    SanResponse update(@PathParam("resourceId") String resourceId, @BodyParam SanRequest sanRequest);

    /**
     * Reports all endpoints known within the specified SAN and the data about each endpoint.
     *
     * @param resourceId managed SAN resource identifier as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link EndpointResponse}&gt; containing
     * the details for all found endpoints of a given managed SAN.
     */
    @Endpoint(uri = "/{resourceId}" + FC_SANS_MANAGED_SAN_ENDPOINTS_URI)
    ResourceCollection<EndpointResponse> getEndpoints(@PathParam("resourceId") String resourceId);

    /**
     * Creates a new SAN issues report indicating any connectivity issues in the specified SAN.
     *
     * @param resourceId managed SAN resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link FcSansManagedSanTask} which contains the task status for the process
     *          and the report data.
     */
    @Endpoint(uri = "/{resourceId}" + FC_SANS_MANAGED_SAN_ISSUES_URI, method = HttpMethod.POST)
    FcSansManagedSanTask createIssuesReport(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * Creates an endpoints CSV file for a SAN. Clients should perform a GET request on the
     * URI in the response to download the file.
     *
     * @param resourceId managed SAN resource identifier as seen in HPE OneView.
     *
     * @return {@link EndpointsCsvFileResponse} contains the data of the created CSV file.
     */
    @Endpoint(uri = "/{resourceId}" + FC_SANS_MANAGED_SAN_ENDPOINTS_URI, method = HttpMethod.POST)
    EndpointsCsvFileResponse createEndpointsCsv(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves a list of associations between provided WWNs and the SANs (if any) on which they reside
     *
     * @param wwn The WWN that may be associated with the SAN.
     *
     * @return A list of {@link LocateSanResponse} containing the details for all associations found.
     */
    @Endpoint(uri = FC_SANS_WWN_LOCATE_URI + "{wwn}")
    List<LocateSanResponse> getWwnAssociations(@PathParam("wwn") String wwn);

}
