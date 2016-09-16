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

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.facilities.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class UnmanagedDeviceClientTest {

    private static final String ANY_UNMANAGED_DEVICE_RESOURCE_ID = "random-UUID";
    private static final String ANY_UNMANAGED_DEVICE_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private UnmanagedDeviceClient unmanagedDeviceClient;

    @Test
    public void shouldGetUnmanagedDevice() {
        unmanagedDeviceClient.getById(ANY_UNMANAGED_DEVICE_RESOURCE_ID);

        String expectedUri = ResourceUris.UNMANAGED_DEVICE_URI + "/" + ANY_UNMANAGED_DEVICE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, UnmanagedDevice.class);
    }

    @Test
    public void shouldGetAllUnmanagedDevice() {
        unmanagedDeviceClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.UNMANAGED_DEVICE_URI, UnmanagedDevice.class);
    }

    @Test
    public void shouldGetUnmanagedDeviceCollectionByName() {
        unmanagedDeviceClient.getByName(ANY_UNMANAGED_DEVICE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.UNMANAGED_DEVICE_URI,
                UnmanagedDevice.class, UrlParameter.getFilterByNameParameter(ANY_UNMANAGED_DEVICE_RESOURCE_NAME));
    }

    @Test
    public void shouldAddUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

        unmanagedDeviceClient.add(unmanagedDevice);

        Request request = new Request(HttpMethod.POST, ResourceUris.UNMANAGED_DEVICE_URI, unmanagedDevice);

        then(baseClient).should().executeRequest(request, UnmanagedDevice.class);
    }

    @Test
    public void shouldUpdateUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

        unmanagedDeviceClient.update(ANY_UNMANAGED_DEVICE_RESOURCE_ID, unmanagedDevice);

        String expectedUri = ResourceUris.UNMANAGED_DEVICE_URI + "/" + ANY_UNMANAGED_DEVICE_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, unmanagedDevice);

        then(baseClient).should().executeRequest(request, UnmanagedDevice.class);
    }

    @Test
    public void shouldRemoveUnmanagedDevice() {
        unmanagedDeviceClient.remove(ANY_UNMANAGED_DEVICE_RESOURCE_ID);

        String expectedUri = ResourceUris.UNMANAGED_DEVICE_URI + "/" + ANY_UNMANAGED_DEVICE_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldRemoveUnmanagedDeviceByFilter() {
        unmanagedDeviceClient.removeByFilter(ANY_UNMANAGED_DEVICE_RESOURCE_NAME, false);

        UrlParameter filter = new UrlParameter("filter", ANY_UNMANAGED_DEVICE_RESOURCE_NAME);

        then(baseClient).should().deleteResource(ResourceUris.UNMANAGED_DEVICE_URI, false, filter);
    }

    @Test
    public void shouldGetUnmanagedDeviceEnvironmentalConfiguration() {
        unmanagedDeviceClient.getEnvironmentalConfiguration(ANY_UNMANAGED_DEVICE_RESOURCE_ID);

        String expectedUri = ResourceUris.UNMANAGED_DEVICE_URI + "/" + ANY_UNMANAGED_DEVICE_RESOURCE_ID
                + "/" + ResourceUris.ENVIRONMENT_CONFIGURATION_URI;

        then(baseClient).should().getResource(expectedUri, EnvironmentalConfiguration.class);
    }

}
