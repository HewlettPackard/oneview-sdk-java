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
package com.hp.ov.sdk.rest.client.server;

import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(ServerHardwareTypeClient.SERVER_HARDWARE_TYPE_URI)
public interface ServerHardwareTypeClient extends
        SearchableResource<ServerHardwareType>,
        DeletableResource {

    String SERVER_HARDWARE_TYPE_URI = "/rest/server-hardware-types";

    /**
     * Updates a {@link ServerHardwareType} identified by the given resource identifier.
     *
     * @param resourceId server hardware type resource identifier as seen in HPE OneView.
     * @param serverHardwareTypeUpdate object containing the logical switch details.
     *
     * @return {@link ServerHardwareType} containing the updated server hardware type.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    ServerHardwareType update(@PathParam("resourceId") String resourceId,
            @BodyParam ServerHardwareTypeUpdate serverHardwareTypeUpdate);

}
