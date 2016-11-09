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

package com.hp.ov.sdk.rest.client.settings;

import java.util.List;

import org.apache.http.HttpHeaders;

import com.google.common.reflect.Parameter;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.settings.ResourceAssignment;
import com.hp.ov.sdk.dto.settings.Scope;
import com.hp.ov.sdk.rest.client.common.RetrievableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.RequestInterceptor;
import com.hp.ov.sdk.rest.http.core.client.BasicHeader;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.HeaderParam;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(ScopeClient.SCOPES_URI)
public interface ScopeClient extends
        RetrievableResource<Scope> {

    String SCOPES_URI = "/rest/scopes";
    String RESOURCE_ASSIGNMENTS = "/resource-assignments";

    /**
     * Creates a scope according to the provided <code>scope</code> object.
     *
     * @param scope object containing the details of the scope that should be created.
     *
     * @return {@link Scope} the created scope.
     */
    @Endpoint(method = HttpMethod.POST)
    Scope create(@BodyParam Scope scope);

    /**
     * Updates the scope identified by <code>resourceId</code> according to the
     * provided <code>scope</code> object.
     *
     * @param resourceId scope resource identifier.
     * @param scope object containing the details of the scope that should be updated.
     *
     * @return {@link Scope} the updated scope.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT,
            requestInterceptor = IfMatchRequestInterceptor.class)
    Scope update(@PathParam("resourceId") String resourceId, @BodyParam Scope scope);

    /**
     * Updates the scope identified by <code>resourceId</code> according to the
     * provided <code>scope</code> object.
     * <p>The request is conditionally processed only if the current ETag for
     * the scope matches the ETag passed as parameter.</p>
     *
     * @param resourceId scope resource identifier.
     * @param scope object containing the details of the scope that should be updated.
     * @param eTag scope ETag.
     *
     * @return {@link Scope}
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    Scope update(@PathParam("resourceId") String resourceId, @BodyParam Scope scope,
            @HeaderParam(HttpHeaders.IF_MATCH) String eTag);

    /**
     * Deletes the scope identified by the provided <code>resourceId</code>.
     *
     * @param resourceId scope resource identifier.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}",
            method = HttpMethod.DELETE,
            forceReturnTask = true,
            requestInterceptor = IfMatchRequestInterceptor.class)
    TaskResource delete(@PathParam("resourceId") String resourceId, RequestOption ... options);

    /**
     * Deletes the scope identified by the provided <code>resourceId</code>.
     * <p>The request is conditionally processed only if the current ETag for
     * the scope matches the ETag passed as parameter.</p>
     *
     * @param resourceId scope resource identifier.
     * @param eTag scope ETag.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", forceReturnTask = true, method = HttpMethod.DELETE)
    TaskResource delete(@PathParam("resourceId") String resourceId, @HeaderParam(HttpHeaders.IF_MATCH) String eTag,
            RequestOption ... options);

    class IfMatchRequestInterceptor implements RequestInterceptor {
        @Override
        public Request intercept(Request request, List<Parameter> params, Object[] args) {
            request.addHeader(new BasicHeader(HttpHeaders.IF_MATCH, "*"));

            return request;
        }
    }

    /**
     * Applies a patch on the scope identified by <code>resourceId</code> according to the
     * provided {@link ResourceAssignment} object.
     *
     * @param resourceId scope resource identifier.
     * @param assignment object containing the details of the modifications that should be applied.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + RESOURCE_ASSIGNMENTS, method = HttpMethod.PATCH)
    TaskResource patch(@PathParam("resourceId") String resourceId, @BodyParam ResourceAssignment assignment,
            RequestOption ... options);

}
