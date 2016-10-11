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

import java.util.List;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.SupportDump;
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

    /**
     * Updates the logical enclosure identified by the provided <code>resourceId</code>
     * to make it consistent with the associated enclosure group when the logical enclosure is
     * in an inconsistent state.
     *
     * Since this operation can take some time to complete, it is possible to specify a timeout
     * using the option {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout
     * is specified, the default behavior is to wait until the update completes.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + UPDATE_FROM_GROUP_URI, method = HttpMethod.PUT)
    TaskResource updateFromGroup(@PathParam("resourceId") String resourceId, RequestOption ... options);

    /**
     * Reapplies the logical enclosure configuration in the logical enclosure identified
     * by the provided <code>resourceId</code>.
     *
     * Since this operation can take some time to complete, it is possible to specify a timeout
     * using the option {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout
     * is specified, the default behavior is to wait until the update completes.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + CONFIGURATION_URI, method = HttpMethod.PUT)
    TaskResource updateConfiguration(@PathParam("resourceId") String resourceId, RequestOption ... options);

    /**
     * Retrieves the configuration script ({@link String}) of the logical enclosure
     * identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link List} containing the URIs for the associated server profiles.
     */
    @Endpoint(uri = "/{resourceId}" + SCRIPT_URI)
    String getConfigurationScript(@PathParam("resourceId") String resourceId);

    /**
     * Updates the logical enclosure script identified by <code>resourceId</code> according
     * to the provided script data.
     *
     * Since this operation can take some time to complete, it is possible to specify a timeout
     * using the option {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout
     * is specified, the default behavior is to wait until the update completes.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param scriptData script data to be configured in the logical enclosure.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + SCRIPT_URI, method = HttpMethod.PUT)
    TaskResource updateConfigurationScript(@PathParam("resourceId") String resourceId,
            @BodyParam String scriptData, RequestOption ... options);

    /**
     * Creates a support dump according to the provided {@link SupportDump} object for
     * the logical enclosure identified by the <code>resourceId</code>. A logical enclosure
     * support dump includes content for logical interconnects associated with that
     * logical enclosure and it also contains some appliance support dump content.
     *
     * Since this operation can take some time to complete, it is possible to specify a timeout
     * using the option {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout
     * is specified, the default behavior is to wait until the create action completes.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param supportDump object containing the details about how the support dump should be created.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + SUPPORT_DUMP_URI, method = HttpMethod.POST)
    TaskResource createSupportDump(@PathParam("resourceId") String resourceId,
            @BodyParam SupportDump supportDump, RequestOption ... options);

}
