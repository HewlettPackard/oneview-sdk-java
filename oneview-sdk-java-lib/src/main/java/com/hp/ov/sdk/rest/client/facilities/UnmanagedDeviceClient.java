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
package com.hp.ov.sdk.rest.client.facilities;

import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.facilities.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hp.ov.sdk.rest.reflect.QueryParam;

@Api(UnmanagedDeviceClient.UNMANAGED_DEVICE_URI)
public interface UnmanagedDeviceClient extends
        SearchableResource<UnmanagedDevice> {

    String UNMANAGED_DEVICE_URI = "/rest/unmanaged-devices";
    String ENVIRONMENT_CONFIGURATION_URI = "/environmentalConfiguration";

    /**
     * Adds a resource according to the provided <code>resource</code> object.
     *
     * <p>According to the resource type, the add action can take some time to complete.
     * Thus, it is possible to specify a timeout using an implementation of {@link RequestOption}
     * called {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout is specified,
     * the default behavior is to wait until the add action completes. Below is an example that
     * illustrates how the timeout can be specified:
     *
     * <pre>{@code
     *     SomeClient client = oneViewClient.someClient();
     *     SomeResource resource = new SomeResource();
     *     TaskResource task = client.add(resource, TaskTimeout.of(5000)); //5 secs
     * }</pre>
     *
     * @param resource object containing the details of the resource that should be added.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link UnmanagedDevice} task containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    UnmanagedDevice add(@BodyParam UnmanagedDevice resource, RequestOption... options);

    /**
     * Updates the resource identified by <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * <p>According to the resource type, the update action can take some time to complete.
     * Thus, it is possible to specify a timeout using an implementation of {@link RequestOption}
     * called {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout is specified,
     * the default behavior is to wait until the update action completes. Below is an example that
     * illustrates how the timeout can be specified:
     *
     * <pre>{@code
     *     SomeClient client = oneViewClient.someClient();
     *     SomeResource resource = client.getByName("resourceName");
     *     //do some changes to the resource
     *     TaskResource task = client.update(resource.getResourceId(), resource, TaskTimeout.of(5000)); //5 secs
     * }</pre>
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param resource object containing the details of the resource that should be created.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link UnmanagedDevice} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    UnmanagedDevice update(@PathParam("resourceId") String resourceId,
            @BodyParam UnmanagedDevice resource, RequestOption... options);

    /**
     * Removes the resource identified by the provided <code>resourceId</code>.
     *
     * <p>According to the resource type, the remove action can take some time to complete.
     * Thus, it is possible to specify a timeout using an implementation of {@link RequestOption}
     * called {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout is specified,
     * the default behavior is to wait until the remove action completes. Below is an example that
     * illustrates how the timeout can be specified:
     *
     * <pre>{@code
     *     String resourceName = "someResourceName";
     *     SomeResource resource = client.getByName(resourceName);
     *     TaskResource task = client.remove(resource.getResourceId(), TaskTimeout.of(5000)); //5 secs
     * }</pre>
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link String} containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String remove(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * Removes the {@link UnmanagedDevice}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(method = HttpMethod.DELETE)
    TaskResource removeByFilter(@QueryParam(key = "filter") String filter, RequestOption ... options);

    /**
     * Returns a description of the environmental configuration (supported feature set,
     * calibrated minimum and maximum power, location and dimensions, ...) of the resource.
     *
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} containing the description of the
     * environmental configuration.
     */
    @Endpoint(uri = "/{resourceId}" + ENVIRONMENT_CONFIGURATION_URI)
    EnvironmentalConfiguration getEnvironmentalConfiguration(@PathParam("resourceId") String resourceId);
}
