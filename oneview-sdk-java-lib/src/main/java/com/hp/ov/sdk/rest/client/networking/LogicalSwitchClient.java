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
import com.hp.ov.sdk.dto.networking.logicalswitches.AddLogicalSwitch;
import com.hp.ov.sdk.dto.networking.logicalswitches.LogicalSwitch;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(LogicalSwitchClient.LOGICAL_SWITCHES_URI)
public interface LogicalSwitchClient extends
        SearchableResource<LogicalSwitch>,
        CreatableResource<AddLogicalSwitch>,
        UpdatableResource<AddLogicalSwitch>,
        DeletableResource {

    String LOGICAL_SWITCHES_URI = "/rest/logical-switches";
    String LOGICAL_SWITCHES_REFRESH_URI = "/refresh";

    /**
     * Executes a refresh action for a logical switch identified by the provided
     * resource identifier. The request can be processed asynchronously or synchronously.
     *
     * @param resourceId logical switch identifier as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_SWITCHES_REFRESH_URI, method = HttpMethod.PUT)
    TaskResource refresh(@PathParam("resourceId") String resourceId, RequestOption ... options);

}
