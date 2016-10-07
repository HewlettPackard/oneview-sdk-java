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

import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.Light;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Power;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.rest.client.common.RemovableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hp.ov.sdk.rest.reflect.QueryParam;

@Api(PowerDeliveryDeviceClient.POWER_DEVICE_URI)
public interface PowerDeliveryDeviceClient extends
        SearchableResource<PowerDeliveryDevice>,
        RemovableResource {

    String POWER_DEVICE_URI = "/rest/power-devices";
    String POWER_DEVICE_DISCOVERY_URI = "/discover";
    String POWER_DEVICE_POWER_STATE_URI = "/powerState";
    String POWER_DEVICE_REFRESH_STATE_URI = "/refreshState";
    String POWER_DEVICE_UID_STATE_URI = "/uidState";
    String POWER_DEVICE_UTILIZATION_URI = "/utilization";
    String POWER_DEVICE_SYNCHRONOUS_URI = "/synchronous";

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
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = POWER_DEVICE_DISCOVERY_URI, method = HttpMethod.POST)
    TaskResource add(@BodyParam ImportPdd resource, RequestOption... options);

    /**
     * Adds a resource according to the provided <code>resource</code> object.
     *
     * <pre>{@code
     *     SomeClient client = oneViewClient.someClient();
     *     SomeResource resource = new SomeResource();
     *     TaskResource task = client.add(resource, TaskTimeout.of(5000)); //5 secs
     * }</pre>
     *
     * @param resource object containing the details of the resource that should be added.
     *
     * @return {@link PowerDeliveryDevice} object containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    PowerDeliveryDevice add(@BodyParam PowerDeliveryDevice resource);

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
     * @param powerDeliveryDevice object containing the details of the resource that should be created.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link PowerDeliveryDevice} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    PowerDeliveryDevice update(@PathParam("resourceId") String resourceId,
            @BodyParam PowerDeliveryDevice powerDeliveryDevice, RequestOption... options);

    /**
     * Removes the {@link PowerDeliveryDevice}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the {@link RequestOption} input.
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
     *
     * @return {@link String} containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_SYNCHRONOUS_URI, method = HttpMethod.DELETE)
    String remove(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the power state details for the specified power delivery device.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link Power} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_POWER_STATE_URI)
    Power getPowerState(@PathParam("resourceId") String resourceId);

    /**
     * Updates the power state of the power device identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param outletState power state of the power delivery device.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_POWER_STATE_URI, method = HttpMethod.PUT)
    TaskResource updatePowerState(@PathParam("resourceId") String resourceId,
            @BodyParam OutletState outletState, RequestOption... options);

    /**
     * Updates the refresh state of the power device identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param refreshState refresh state of the power delivery device.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_REFRESH_STATE_URI, method = HttpMethod.PUT)
    TaskResource updateRefreshState(@PathParam("resourceId") String resourceId,
            @BodyParam PowerDeliveryDeviceRefreshRequest refreshState, RequestOption... options);

    /**
     * Retrieves the unit identification (UID) light state details for the specified power delivery device.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link Light} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_UID_STATE_URI)
    Light getUidState(@PathParam("resourceId") String resourceId);

    /**
     * Updates the unit identification (UID) light state of the power device
     * identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param outletState identification (UID) light state of the power delivery device.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_UID_STATE_URI, method = HttpMethod.PUT)
    TaskResource updateUidState(@PathParam("resourceId") String resourceId,
            @BodyParam OutletState outletState, RequestOption... options);

    /**
     * Retrieves the {@link UtilizationData} details for the specified power delivery device.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link UtilizationData} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_UTILIZATION_URI)
    UtilizationData getUtilization(@PathParam("resourceId") String resourceId);

}
