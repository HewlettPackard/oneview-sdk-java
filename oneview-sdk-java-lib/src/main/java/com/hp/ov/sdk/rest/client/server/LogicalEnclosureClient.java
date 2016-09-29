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

import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(LogicalEnclosureClient.LOGICAL_ENCLOSURE_URI)
public interface LogicalEnclosureClient extends
        SearchableResource<LogicalEnclosure>,
        CreatableResource<AddLogicalEnclosure>,
        UpdatableResource<LogicalEnclosure>,
        PatchableResource,
        DeletableResource {

    String LOGICAL_ENCLOSURE_URI = "/rest/logical-enclosures";
    String UPDATE_FROM_GROUP_URI = "/updateFromGroup";
    String CONFIGURATION_URI = "/configuration";
    String SCRIPT_URI = "/script";
    String SUPPORT_DUMP_URI = "/support-dumps";

    @Endpoint(uri = "/{resourceId}" + UPDATE_FROM_GROUP_URI, method = HttpMethod.PUT)
    TaskResource updateFromGroup(@PathParam("resourceId") String resourceId, RequestOption ... options);

    @Endpoint(uri = "/{resourceId}" + CONFIGURATION_URI, method = HttpMethod.PUT)
    TaskResource updateConfiguration(@PathParam("resourceId") String resourceId, RequestOption ... options);

    @Endpoint(uri = "/{resourceId}" + SCRIPT_URI)
    String getConfigurationScript(@PathParam("resourceId") String resourceId);

    @Endpoint(uri = "/{resourceId}" + SCRIPT_URI, method = HttpMethod.PUT)
    TaskResource updateConfigurationScript(@PathParam("resourceId") String resourceId,
            @BodyParam String scriptData, RequestOption ... options);

    @Endpoint(uri = "/{resourceId}" + SUPPORT_DUMP_URI, method = HttpMethod.POST)
    TaskResource createSupportDump(@PathParam("resourceId") String resourceId,
            @BodyParam SupportDump supportDump, RequestOption ... options);

}
