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

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.ImportPdd;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.Light;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.OutletState;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.Power;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDeviceRefreshRequest;
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
     * @param resource object containing the details of the resource that should be added.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @see com.hp.ov.sdk.rest.client.common.AddableResource#add(Object, RequestOption...)
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = POWER_DEVICE_DISCOVERY_URI, method = HttpMethod.POST)
    TaskResource add(@BodyParam ImportPdd resource, RequestOption... options);

    /**
     * Adds a resource according to the provided <code>resource</code> object.
     *
     * @param resource object containing the details of the resource that should be added.
     *
     * @return the created {@link PowerDeliveryDevice}.
     */
    @Endpoint(method = HttpMethod.POST)
    PowerDeliveryDevice add(@BodyParam PowerDeliveryDevice resource);

    /**
     * Updates the resource identified by the <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param powerDeliveryDevice object containing the details of the resource that should be created.
     *
     * @return the updated {@link PowerDeliveryDevice}.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    PowerDeliveryDevice update(@PathParam("resourceId") String resourceId,
            @BodyParam PowerDeliveryDevice powerDeliveryDevice);

    /**
     * Removes the {@link PowerDeliveryDevice}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously, although the method can process the request asynchronously or
     * synchronously, based on the {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}
     * specified.
     *
     * @param filter A general filter string that narrows the list of resources.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(method = HttpMethod.DELETE)
    TaskResource removeByFilter(@QueryParam(key = "filter") String filter, RequestOption ... options);

    /**
     * Removes the resource identified by the provided <code>resourceId</code>.
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
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link Power} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_POWER_STATE_URI)
    Power getPowerState(@PathParam("resourceId") String resourceId);

    /**
     * Updates the power state of the power device identified by the provided resource identifier.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param outletState power state of the power delivery device.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_POWER_STATE_URI, method = HttpMethod.PUT)
    TaskResource updatePowerState(@PathParam("resourceId") String resourceId,
            @BodyParam OutletState outletState, RequestOption... options);

    /**
     * Updates the refresh state of the power device identified by the provided resource identifier.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param refreshState refresh state of the power delivery device.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
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
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link Light} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_UID_STATE_URI)
    Light getUidState(@PathParam("resourceId") String resourceId);

    /**
     * Updates the unit identification (UID) light state of the power device
     * identified by the provided resource identifier.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param outletState identification (UID) light state of the power delivery device.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
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
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link UtilizationData} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + POWER_DEVICE_UTILIZATION_URI)
    UtilizationData getUtilization(@PathParam("resourceId") String resourceId);

}
