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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.Light;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Power;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class PowerDeliveryDeviceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerDeliveryDeviceClient.class);

    private final BaseClient baseClient;

    public PowerDeliveryDeviceClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link PowerDeliveryDevice} details for the specified power delivery device.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link PowerDeliveryDevice} object containing the details.
     */
    public PowerDeliveryDevice getById(String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClient : getById : Start");

        PowerDeliveryDevice powerDeliveryDevice = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId), PowerDeliveryDevice.class);

        LOGGER.info("PowerDeliveryDeviceClient : getById : End");

        return powerDeliveryDevice;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link PowerDeliveryDevice}&gt; containing details
     * for all the available power delivery devices found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link PowerDeliveryDevice}&gt; containing
     * the details for all found power delivery devices.
     */
    public ResourceCollection<PowerDeliveryDevice> getAll() {
        LOGGER.info("PowerDeliveryDeviceClient : getAll : Start");

        ResourceCollection<PowerDeliveryDevice> powerDevices = baseClient.getResourceCollection(
                ResourceUris.POWER_DEVICE_URI, PowerDeliveryDevice.class);

        LOGGER.info("PowerDeliveryDeviceClient : getAll : End");

        return powerDevices;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link PowerDeliveryDevice}&gt; containing details
     * for the available power delivery devices found under the current HPE OneView that match the name.
     *
     * @param name power delivery device name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link PowerDeliveryDevice}&gt; containing
     * the details for the found power delivery devices.
     */
    public ResourceCollection<PowerDeliveryDevice> getByName(String name) {
        LOGGER.info("PowerDeliveryDeviceClient : getByName : Start");

        ResourceCollection<PowerDeliveryDevice> powerDevices = baseClient.getResourceCollection(
                ResourceUris.POWER_DEVICE_URI, PowerDeliveryDevice.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("PowerDeliveryDeviceClient : getByName : End");

        return powerDevices;
    }

    /**
     * Adds a power delivery device according to the provided {@link PowerDeliveryDevice} object.
     * Use this method to create a representation of power delivery devices that
     * provide power to other resources but cannot otherwise be discovered by
     * the management appliance.
     *
     * @param powerDeliveryDevice object containing the power delivery device details.
     *
     * @return {@link PowerDeliveryDevice} containing the added power delivery device.
     */
    public PowerDeliveryDevice add(PowerDeliveryDevice powerDeliveryDevice) {
        LOGGER.info("PowerDeliveryDeviceClient : add : Start");

        Request request = new Request(HttpMethodType.POST, ResourceUris.POWER_DEVICE_URI, powerDeliveryDevice);
        PowerDeliveryDevice createdPowerDeliveryDevice = this.baseClient.executeRequest(request, PowerDeliveryDevice.class);

        LOGGER.info("PowerDeliveryDeviceClient : add : End");

        return createdPowerDeliveryDevice;
    }

    /**
     * Adds an HPE iPDU and bring all components under management by discovery of
     * its management module. Bring the management module under exclusive management
     * by the appliance, configure any management or data collection settings,
     * and create a private set of administrative credentials to enable ongoing
     * communication and management of the iPDU. The request can be processed
     * synchronously or asynchronously.
     *
     * @param importPdd object containing the power delivery device connection details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 add(ImportPdd importPdd, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClient : add : Start");

        TaskResourceV2 taskResource = this.baseClient.createResource(
                ResourceUris.POWER_DEVICE_DISCOVERY_URI, importPdd, aSync);

        LOGGER.info("PowerDeliveryDeviceClient : add : End");

        return taskResource;
    }

    /**
     * Updates a {@link PowerDeliveryDevice} identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param powerDeliveryDevice object containing the power delivery device details.
     *
     * @return {@link PowerDeliveryDevice} containing the power delivery device updated.
     */
    public PowerDeliveryDevice update(String resourceId, PowerDeliveryDevice powerDeliveryDevice) {
        LOGGER.info("PowerDeliveryDeviceClient : update : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId), powerDeliveryDevice);
        PowerDeliveryDevice updatedPowerDeliveryDevice = this.baseClient.executeRequest(request, PowerDeliveryDevice.class);

        LOGGER.info("PowerDeliveryDeviceClient : update : End");

        return updatedPowerDeliveryDevice;
    }

    /**
     * Removes the {@link PowerDeliveryDevice} identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 remove(String resourceId, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClient : remove : Start");

        TaskResourceV2 taskResource = this.baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId), aSync);

        LOGGER.info("PowerDeliveryDeviceClient : remove : End");

        return taskResource;
    }

    /**
     * Removes the {@link PowerDeliveryDevice}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 removeByFilter(String filter, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClient : removeByFilter : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                ResourceUris.POWER_DEVICE_URI, aSync, new UrlParameter("filter", filter));

        LOGGER.trace("PowerDeliveryDeviceClient : removeByFilter : End");

        return taskResource;
    }

    /**
     * Removes the {@link PowerDeliveryDevice} identified by the given resource identifier.
     * <b>This method only returns after the power device has been completely removed.</b>
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the response from the server.
     */
    public String remove(String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClient : remove : Start");

        Request request = new Request(HttpMethodType.DELETE, UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI,
                resourceId, ResourceUris.POWER_DEVICE_SYNCHRONOUS_URI));

        String response = this.baseClient.executeRequest(request, String.class);

        LOGGER.info("PowerDeliveryDeviceClient : remove : End");

        return response;
    }

    /**
     * Retrieves the power state details for the specified power delivery device.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link Power} object containing the details.
     */
    public Power getPowerState(String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClient : getPowerState : Start");

        String powerStateResponse = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId,
                        ResourceUris.POWER_DEVICE_POWER_STATE_URI),
                String.class);

        Power powerState = Enum.valueOf(Power.class, powerStateResponse.replaceAll("^\"|\"$", ""));

        LOGGER.info("PowerDeliveryDeviceClient : getPowerState : End");

        return powerState;
    }

    /**
     * Updates the power state of the power device identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param outletState power state of the power delivery device.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updatePowerState(String resourceId, OutletState outletState, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClient : updatePowerState : Start");

        TaskResourceV2 taskResource = this.baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId,
                        ResourceUris.POWER_DEVICE_POWER_STATE_URI),
                outletState, aSync);

        LOGGER.info("PowerDeliveryDeviceClient : updatePowerState : End");

        return taskResource;
    }

    /**
     * Updates the refresh state of the power device identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param refreshState refresh state of the power delivery device.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateRefreshState(String resourceId,
            PowerDeliveryDeviceRefreshRequest refreshState, boolean aSync) {

        LOGGER.info("PowerDeliveryDeviceClient : updateRefreshState : Start");

        TaskResourceV2 taskResource = this.baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId,
                        ResourceUris.POWER_DEVICE_REFRESH_STATE_URI),
                refreshState, aSync);

        LOGGER.info("PowerDeliveryDeviceClient : updateRefreshState : End");

        return taskResource;
    }

    /**
     * Retrieves the unit identification (UID) light state details for the specified power delivery device.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link Light} object containing the details.
     */
    public Light getUidState(String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClient : getUidState : Start");

        String uidStateResponse = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId,
                        ResourceUris.POWER_DEVICE_UID_STATE_URI),
                String.class);

        Light lightState = Enum.valueOf(Light.class, uidStateResponse.replaceAll("^\"|\"$", ""));

        LOGGER.info("PowerDeliveryDeviceClient : getUidState : End");

        return lightState;
    }

    /**
     * Updates the unit identification (UID) light state of the power device
     * identified by the given resource identifier.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     * @param outletState identification (UID) light state of the power delivery device.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateUidState(String resourceId, OutletState outletState, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClient : updateUidState : Start");

        TaskResourceV2 taskResource = this.baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId,
                        ResourceUris.POWER_DEVICE_UID_STATE_URI),
                outletState, aSync);

        LOGGER.info("PowerDeliveryDeviceClient : updateUidState : End");

        return taskResource;
    }

    /**
     * Retrieves the {@link UtilizationData} details for the specified power delivery device.
     *
     * @param resourceId power delivery device resource identifier as seen in HPE OneView.
     *
     * @return {@link UtilizationData} object containing the details.
     */
    public UtilizationData getUtilization(String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClient : getUtilization : Start");

        UtilizationData utilizationData = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.POWER_DEVICE_URI, resourceId,
                        ResourceUris.POWER_DEVICE_UTILIZATION_URI),
                UtilizationData.class);

        LOGGER.info("PowerDeliveryDeviceClient : getUtilization : End");

        return utilizationData;
    }

}
