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
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;

public interface CreatableResource<T> {

    /**
     * Creates a resource according to the provided <code>resource</code> object.
     *
     * <p>Depending on the resource type, the create action can take some time to complete.
     * Therefore, you can specify a timeout using an implementation of {@link RequestOption}
     * called {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout is
     * specified, the default behavior is to wait until the create action completes.
     * The following example shows how to specify the timeout:
     *
     * <pre>{@code
     *     SomeClient client = oneViewClient.someClient();
     *     SomeResource resource = new SomeResource();
     *     TaskResource task = client.create(resource, TaskTimeout.of(5000)); //5 secs
     * }</pre>
     *
     * @param resource object containing the details of the resource that should be created.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    TaskResource create(@BodyParam T resource, RequestOption... options);

}
