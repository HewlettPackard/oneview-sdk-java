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
     * @param resource object containing the details of the resource that should be added.
     *
     * @return {@link UnmanagedDevice} task containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    UnmanagedDevice add(@BodyParam UnmanagedDevice resource);

    /**
     * Updates the resource identified by the <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link UnmanagedDevice} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    UnmanagedDevice update(@PathParam("resourceId") String resourceId,
            @BodyParam UnmanagedDevice resource);

    /**
     * Removes the resource identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String remove(@PathParam("resourceId") String resourceId);

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
     * calibrated minimum and maximum power, location, and dimensions, ...) of the resource.
     *
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} containing the description of the
     * environmental configuration.
     */
    @Endpoint(uri = "/{resourceId}" + ENVIRONMENT_CONFIGURATION_URI)
    EnvironmentalConfiguration getEnvironmentalConfiguration(@PathParam("resourceId") String resourceId);
}
