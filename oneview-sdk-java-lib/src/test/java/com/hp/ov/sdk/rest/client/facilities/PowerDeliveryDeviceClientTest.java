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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class PowerDeliveryDeviceClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private PowerDeliveryDeviceClient powerDeliveryDeviceClient;

    @Test
    public void shouldGetPowerDeliveryDeviceById() {
        powerDeliveryDeviceClient.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.POWER_DEVICE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, PowerDeliveryDevice.class);
    }

    @Test
    public void shouldGetAllPowerDeliveryDevice() {
        powerDeliveryDeviceClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.POWER_DEVICE_URI, PowerDeliveryDevice.class);
    }

    @Test
    public void shouldGetPowerDeliveryDeviceByName() {
        powerDeliveryDeviceClient.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.POWER_DEVICE_URI,
                PowerDeliveryDevice.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldAddPowerDeliveryDevice() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();

        powerDeliveryDeviceClient.add(powerDeliveryDevice);

        Request request = new Request(HttpMethodType.POST, ResourceUris.POWER_DEVICE_URI, powerDeliveryDevice);

        then(baseClient).should().executeRequest(request, PowerDeliveryDevice.class);
    }

    @Test
    public void shouldAddDiscoveredPowerDeliveryDevice() {
        ImportPdd importPdd = new ImportPdd();

        powerDeliveryDeviceClient.add(importPdd, false);

        then(baseClient).should().createResource(ResourceUris.POWER_DEVICE_DISCOVERY_URI, importPdd, false);
    }

    @Test
    public void shouldUpdatePowerDeliveryDevice() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();

        powerDeliveryDeviceClient.update(ANY_RESOURCE_ID, powerDeliveryDevice);

        String expectedUri = ResourceUris.POWER_DEVICE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, powerDeliveryDevice);

        then(baseClient).should().executeRequest(request, PowerDeliveryDevice.class);
    }

    @Test
    public void shouldRemovePowerDeliveryDevice() {
        powerDeliveryDeviceClient.remove(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.POWER_DEVICE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldRemovePowerDeliveryDeviceByFilter() {
        powerDeliveryDeviceClient.removeByFilter(ANY_RESOURCE_NAME, false);

        UrlParameter filter = new UrlParameter("filter", ANY_RESOURCE_NAME);

        then(baseClient).should().deleteResource(ResourceUris.POWER_DEVICE_URI, false, filter);
    }

    @Test
    public void shouldRemovePowerDeliveryDeviceSynchronously() {
        powerDeliveryDeviceClient.remove(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.POWER_DEVICE_SYNCHRONOUS_URI;

        Request expectedRequest = new Request(HttpMethodType.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, String.class);
    }

    @Test
    public void shouldGetPowerDeliveryDevicePowerState() {
        given(baseClient.getResource(anyString(), any(Class.class))).willReturn("\"On\"");

        powerDeliveryDeviceClient.getPowerState(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.POWER_DEVICE_POWER_STATE_URI;

        then(baseClient).should().getResource(expectedUri, String.class);
    }

    @Test
    public void shouldUpdatePowerDeliveryDevicePowerState() {
        OutletState powerState = new OutletState();

        powerDeliveryDeviceClient.updatePowerState(ANY_RESOURCE_ID, powerState, false);

        String expectedUri = ResourceUris.POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.POWER_DEVICE_POWER_STATE_URI;

        then(baseClient).should().updateResource(expectedUri, powerState, false);
    }

    @Test
    public void shouldUpdatePowerDeliveryDeviceRefreshState() {
        PowerDeliveryDeviceRefreshRequest refreshState = new PowerDeliveryDeviceRefreshRequest();

        powerDeliveryDeviceClient.updateRefreshState(ANY_RESOURCE_ID, refreshState, false);

        String expectedUri = ResourceUris.POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.POWER_DEVICE_REFRESH_STATE_URI;

        then(baseClient).should().updateResource(expectedUri, refreshState, false);
    }

    @Test
    public void shouldGetPowerDeliveryDeviceUidState() {
        given(baseClient.getResource(anyString(), any(Class.class))).willReturn("\"On\"");

        powerDeliveryDeviceClient.getUidState(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.POWER_DEVICE_UID_STATE_URI;

        then(baseClient).should().getResource(expectedUri, String.class);
    }

    @Test
    public void shouldUpdatePowerDeliveryDeviceUidState() {
        OutletState uidState = new OutletState();

        powerDeliveryDeviceClient.updateUidState(ANY_RESOURCE_ID, uidState, false);

        String expectedUri = ResourceUris.POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.POWER_DEVICE_UID_STATE_URI;

        then(baseClient).should().updateResource(expectedUri, uidState, false);
    }

    @Test
    public void shouldGetPowerDeliveryDeviceUtilization() {
        powerDeliveryDeviceClient.getUtilization(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.POWER_DEVICE_UTILIZATION_URI;

        then(baseClient).should().getResource(expectedUri, UtilizationData.class);
    }

}
