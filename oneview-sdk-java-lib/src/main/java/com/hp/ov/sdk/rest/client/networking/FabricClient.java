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
import com.hp.ov.sdk.dto.networking.fabric.Fabric;
import com.hp.ov.sdk.dto.networking.fabric.VlanPool;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(FabricClient.FABRIC_URI)
public interface FabricClient extends SearchableResource<Fabric> {

    String FABRIC_URI = "/rest/fabrics";
    String RESERVED_VLAN_RANGE_URI = "/reserved-vlan-range";

    /**
     * Retrieves the {@link VlanPool} range for the fabric.
     *
     * @param resourceId fabric resource identifier as seen in HPE OneView.
     *
     * @return {@link VlanPool} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + RESERVED_VLAN_RANGE_URI)
    VlanPool getReservedVlanRange(@PathParam("resourceId") String resourceId);

    /**
     * Updates a {@link VlanPool} range for the fabric.
     *
     * @param resourceId fabric resource identifier as seen in HPE OneView.
     * @param vlanPool object containing the range details.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + RESERVED_VLAN_RANGE_URI, method = HttpMethod.PUT)
    TaskResource updateReservedVlanRange(@PathParam("resourceId") String resourceId,
            @BodyParam VlanPool vlanPool, RequestOption... options);

}
