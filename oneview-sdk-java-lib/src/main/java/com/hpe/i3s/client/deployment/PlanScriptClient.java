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
package com.hpe.i3s.client.deployment;

import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hpe.i3s.dto.deployment.planscript.PlanScript;
import com.hpe.i3s.dto.deployment.planscript.ScriptDifferences;

@Api(PlanScriptClient.PLAN_SCRIPT_URI)
public interface PlanScriptClient extends
        SearchableResource<PlanScript> {

    String PLAN_SCRIPT_URI = "/rest/plan-scripts";
    String PLAN_SCRIPT_DIFFERENCES_URI = "/differences";

    /**
     * Creates a resource according to the provided <code>resource</code> object.
     *
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link PlanScript} object containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    PlanScript create(@BodyParam PlanScript resource);

    /**
     * Updates the resource identified by <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId resource identifier
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link PlanScript} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    PlanScript update(@PathParam("resourceId") String resourceId, @BodyParam PlanScript resource);

    /**
     * Deletes the resource identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier
     *
     * @return {@link String} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String delete(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the modified contents of the selected Plan Script according to
     * the provided <code>requestBody</code> object, as per the selected attributes.
     *
     * @param resourceId resource identifier
     * @param script object containing the script to be compared.
     *
     * @return {@link ScriptDifferences} object containing the result of this request.
     */
    @Endpoint(uri = PLAN_SCRIPT_DIFFERENCES_URI + "/{resourceId}", method = HttpMethod.POST)
    ScriptDifferences getDifferences(@PathParam("resourceId") String resourceId, @BodyParam String script);

}
