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
package com.hp.ov.sdk.rest.client.networking;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterConnectRefreshRequest;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnect;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(SasInterconnectClient.SAS_INTERCONNECT_URI)
public interface SasInterconnectClient extends
        SearchableResource<SasInterconnect>,
        PatchableResource {

    String SAS_INTERCONNECT_URI = "/rest/sas-interconnects";
    String SAS_INTERCONNECT_URI_REFRESH = "/refreshState";

    /**
     * Refresh a {@link SasInterconnect} identified by the given resource identifier.
     *
     * @param resourceId SAS interconnect resource identifier as seen in HPE OneView.
     * @param requestBody Initial SAS interconnect refresh state. At the moment refreshPending is permitted.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + SAS_INTERCONNECT_URI_REFRESH, method = HttpMethod.PUT, forceReturnTask = true)
    TaskResource refreshState(@PathParam("resourceId") String resourceId,
            @BodyParam SasInterConnectRefreshRequest requestBody, RequestOption... options);

}
