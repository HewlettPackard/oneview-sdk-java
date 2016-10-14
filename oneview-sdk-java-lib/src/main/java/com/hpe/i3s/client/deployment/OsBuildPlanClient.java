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
import com.hpe.i3s.dto.deployment.osbuildplan.OeBuildPlan;

@Api(OsBuildPlanClient.BUILD_PLAN_URI)
public interface OsBuildPlanClient extends
        SearchableResource<OeBuildPlan> {

    String BUILD_PLAN_URI = "/rest/build-plans";

    /**
     * Creates a resource according to the provided <code>resource</code> object.
     *
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link OeBuildPlan} object containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    OeBuildPlan create(@BodyParam OeBuildPlan resource);


    /**
     * Updates the resource identified by <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link OeBuildPlan} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    OeBuildPlan update(@PathParam("resourceId") String resourceId, @BodyParam OeBuildPlan resource);


    /**
     * Deletes the resource identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link String} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String delete(@PathParam("resourceId") String resourceId);
}
