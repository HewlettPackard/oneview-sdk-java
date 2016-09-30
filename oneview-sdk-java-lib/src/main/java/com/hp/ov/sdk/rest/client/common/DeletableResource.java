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

package com.hp.ov.sdk.rest.client.common;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

public interface DeletableResource {

    /**
     * Deletes the resource identified by the provided <code>resourceId</code>.
     *
     * <p>According to the resource type, the delete action can take some time to complete.
     * Thus, it is possible to specify a timeout using an implementation of {@link RequestOption}
     * called {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout is specified,
     * the default behavior is to wait until the delete action completes. Below is an example that
     * illustrates how the timeout can be specified:
     *
     * <pre>{@code
     *     String resourceName = "someResourceName";
     *     SomeResource resource = client.getByName(resourceName);
     *     TaskResource task = client.delete(resource.getResourceId(), TaskTimeout.of(5000)); //5 secs
     * }</pre>
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    TaskResource delete(@PathParam("resourceId") String resourceId, RequestOption... options);

}
