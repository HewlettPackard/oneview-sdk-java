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

import static com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient.POWER_DEVICE_DISCOVERY_URI;
import static com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient.POWER_DEVICE_POWER_STATE_URI;
import static com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient.POWER_DEVICE_REFRESH_STATE_URI;
import static com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient.POWER_DEVICE_SYNCHRONOUS_URI;
import static com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient.POWER_DEVICE_UID_STATE_URI;
import static com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient.POWER_DEVICE_URI;
import static com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient.POWER_DEVICE_UTILIZATION_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.Light;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Power;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class PowerDeliveryDeviceClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private PowerDeliveryDeviceClient client = Reflection.newProxy(PowerDeliveryDeviceClient.class,
            new ClientRequestHandler<>(baseClient, PowerDeliveryDeviceClient.class));

    @Test
    public void shouldGetPowerDeliveryDeviceById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = POWER_DEVICE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(PowerDeliveryDevice.class).getType());
    }

    @Test
    public void shouldGetAllPowerDeliveryDevice() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, POWER_DEVICE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<PowerDeliveryDevice>>() {}.getType());
    }

    @Test
    public void shouldGetPowerDeliveryDeviceByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, POWER_DEVICE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<PowerDeliveryDevice>>() {}.getType());
    }

    @Test
    public void shouldAddPowerDeliveryDevice() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();

        client.add(powerDeliveryDevice);

        Request expectedRequest = new Request(HttpMethod.POST, POWER_DEVICE_URI, powerDeliveryDevice);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(PowerDeliveryDevice.class).getType());
    }

    @Test
    public void shouldAddDiscoveredPowerDeliveryDevice() {
        ImportPdd importPdd = new ImportPdd();

        client.add(importPdd, TaskTimeout.of(321));

        String expectedUri = POWER_DEVICE_URI
                + POWER_DEVICE_DISCOVERY_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, importPdd);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdatePowerDeliveryDevice() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();

        client.update(ANY_RESOURCE_ID, powerDeliveryDevice);

        String expectedUri = POWER_DEVICE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, powerDeliveryDevice);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(PowerDeliveryDevice.class).getType());
    }

    @Test
    public void shouldRemovePowerDeliveryDevice() {
        client.remove(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = POWER_DEVICE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRemovePowerDeliveryDeviceByFilter() {
        String filter = "'name' = '" + ANY_RESOURCE_NAME + "'";
        client.removeByFilter(filter, TaskTimeout.of(321));

        String expectedUri = POWER_DEVICE_URI;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.addQuery(new UrlParameter("filter", filter));
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRemovePowerDeliveryDeviceSynchronously() {
        client.remove(ANY_RESOURCE_ID);

        String expectedUri = POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + POWER_DEVICE_SYNCHRONOUS_URI;

        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldGetPowerDeliveryDevicePowerState() {
        client.getPowerState(ANY_RESOURCE_ID);

        String expectedUri = POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + POWER_DEVICE_POWER_STATE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Power.class).getType());
    }

    @Test
    public void shouldUpdatePowerDeliveryDevicePowerState() {
        OutletState powerState = new OutletState();

        client.updatePowerState(ANY_RESOURCE_ID, powerState);

        String expectedUri = POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + POWER_DEVICE_POWER_STATE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(powerState);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdatePowerDeliveryDeviceRefreshState() {
        PowerDeliveryDeviceRefreshRequest refreshState = new PowerDeliveryDeviceRefreshRequest();

        client.updateRefreshState(ANY_RESOURCE_ID, refreshState);

        String expectedUri = POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + POWER_DEVICE_REFRESH_STATE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(refreshState);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetPowerDeliveryDeviceUidState() {
        client.getUidState(ANY_RESOURCE_ID);

        String expectedUri = POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + POWER_DEVICE_UID_STATE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Light.class).getType());
    }

    @Test
    public void shouldUpdatePowerDeliveryDeviceUidState() {
        OutletState uidState = new OutletState();

        client.updateUidState(ANY_RESOURCE_ID, uidState);

        String expectedUri = POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + POWER_DEVICE_UID_STATE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(uidState);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetPowerDeliveryDeviceUtilization() {
        client.getUtilization(ANY_RESOURCE_ID);

        String expectedUri = POWER_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + POWER_DEVICE_UTILIZATION_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(UtilizationData.class).getType());
    }

}
