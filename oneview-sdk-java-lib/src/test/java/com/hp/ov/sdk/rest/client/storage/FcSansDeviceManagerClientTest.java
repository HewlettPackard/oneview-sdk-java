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

package com.hp.ov.sdk.rest.client.storage;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class FcSansDeviceManagerClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_PROVIDER_URI = "random-Provider-URI";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private FcSanDeviceManagerClient client;

    @Test
    public void shouldGetFcSanDeviceManagerById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.FC_SANS_DEVICE_MANAGER_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, DeviceManagerResponse.class);
    }

    @Test
    public void shouldGetAllFcSanDeviceManager() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.FC_SANS_DEVICE_MANAGER_URI,
                DeviceManagerResponse.class);
    }

    @Test
    public void shouldGetFcSanDeviceManagerByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.FC_SANS_DEVICE_MANAGER_URI,
                DeviceManagerResponse.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldAddFcSanDeviceManager() {
        DeviceManagerResponse deviceManager = new DeviceManagerResponse();

        this.client.add(ANY_PROVIDER_URI, deviceManager, false);

        Request expectedRequest = new Request(HttpMethodType.POST, ANY_PROVIDER_URI, deviceManager);

        expectedRequest.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateFcSanDeviceManager() {
        DeviceManagerResponse deviceManager = new DeviceManagerResponse();

        this.client.update(ANY_RESOURCE_ID, deviceManager, false);

        String expectedUri = ResourceUris.FC_SANS_DEVICE_MANAGER_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, deviceManager);

        expectedRequest.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldRemoveFcSanDeviceManagerApiVersion300() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_300);

        this.client.remove(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.FC_SANS_DEVICE_MANAGER_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldRemoveFcSanDeviceManagerApiVersion200() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_200);

        this.client.remove(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.FC_SANS_DEVICE_MANAGER_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, String.class);
    }

}
