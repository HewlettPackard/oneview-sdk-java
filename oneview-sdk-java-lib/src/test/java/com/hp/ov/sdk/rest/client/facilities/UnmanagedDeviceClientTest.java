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

import static com.hp.ov.sdk.rest.client.facilities.UnmanagedDeviceClient.ENVIRONMENT_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.facilities.UnmanagedDeviceClient.UNMANAGED_DEVICE_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.facilities.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.client.GenericFilter;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class UnmanagedDeviceClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private UnmanagedDeviceClient client = Reflection.newProxy(UnmanagedDeviceClient.class,
            new ClientRequestHandler<>(baseClient, UnmanagedDeviceClient.class));

    @Test
    public void shouldGetUnmanagedDevice() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = UNMANAGED_DEVICE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(UnmanagedDevice.class).getType());
    }

    @Test
    public void shouldGetAllUnmanagedDevice() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, UNMANAGED_DEVICE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<UnmanagedDevice>>() {}.getType());
    }

    @Test
    public void shouldGetUnmanagedDeviceCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, UNMANAGED_DEVICE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<UnmanagedDevice>>() {}.getType());
    }

    @Test
    public void shouldAddUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

        client.add(unmanagedDevice);

        Request expectedRequest = new Request(HttpMethod.POST, UNMANAGED_DEVICE_URI);
        expectedRequest.setEntity(unmanagedDevice);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(UnmanagedDevice.class).getType());
    }

    @Test
    public void shouldUpdateUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

        client.update(ANY_RESOURCE_ID, unmanagedDevice);

        String expectedUri = UNMANAGED_DEVICE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, unmanagedDevice);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(UnmanagedDevice.class).getType());
    }

    @Test
    public void shouldRemoveUnmanagedDevice() {
        client.remove(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = UNMANAGED_DEVICE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldRemoveUnmanagedDeviceByFilter() {
        GenericFilter filter = new GenericFilter();
        filter.setFilter("'name' = '" + ANY_RESOURCE_NAME + "'");
        client.removeByFilter(filter, TaskTimeout.of(321));

        String expectedUri = UNMANAGED_DEVICE_URI;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.addQuery(new UrlParameter("filter", filter.parameters().get(0).getValue()));

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetUnmanagedDeviceEnvironmentalConfiguration() {
        client.getEnvironmentalConfiguration(ANY_RESOURCE_ID);

        String expectedUri = UNMANAGED_DEVICE_URI
                + "/" + ANY_RESOURCE_ID
                + ENVIRONMENT_CONFIGURATION_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EnvironmentalConfiguration.class).getType());
    }

}
