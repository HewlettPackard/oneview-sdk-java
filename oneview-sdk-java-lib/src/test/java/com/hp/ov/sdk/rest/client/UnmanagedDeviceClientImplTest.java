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

package com.hp.ov.sdk.rest.client;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.doReturn;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.spy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.uncategorized.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class UnmanagedDeviceClientImplTest {

    private static final String ANY_UNMANAGED_DEVICE_RESOURCE_ID = "random-UUID";
    private static final String ANY_UNMANAGED_DEVICE_FILTER = "name='random-Name'";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private UnmanagedDeviceClientImpl unmanagedDeviceClient;

    @Test
    public void shouldReturnUnmanagedDeviceClient() {
        assertThat(UnmanagedDeviceClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetUnmanagedDeviceWithoutParams() {
        this.unmanagedDeviceClient.getUnmanagedDevice(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetUnmanagedDevice() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.getUnmanagedDevice(new RestParams(), null);
    }

    @Test
    public void shouldGetUnmanagedDevice() {
        String unmanagedDeviceValue = "{\"category\":\"unmanaged-devices\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(unmanagedDeviceValue);
        given(adaptor.buildResourceObject(anyString(), eq(UnmanagedDevice.class))).willReturn(new UnmanagedDevice());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI, ANY_UNMANAGED_DEVICE_RESOURCE_ID));

        this.unmanagedDeviceClient.getUnmanagedDevice(new RestParams(), ANY_UNMANAGED_DEVICE_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(unmanagedDeviceValue), eq(UnmanagedDevice.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllUnmanagedDevicesWithoutParams() {
        this.unmanagedDeviceClient.getAllUnmanagedDevices(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllUnmanagedDevices() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.getAllUnmanagedDevices(new RestParams());
    }

    @Test
    public void shouldGetAllUnmanagedDevices() {
        String unmanagedDeviceCollectionValue = "{\"category\":\"unmanaged-devices\"," +
                "\"members\":[{\"category\":\"unmanaged-devices\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(unmanagedDeviceCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(UnmanagedDevice.class)))
                .willReturn(new ResourceCollection<UnmanagedDevice>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI));

        this.unmanagedDeviceClient.getAllUnmanagedDevices(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(unmanagedDeviceCollectionValue), eq(UnmanagedDevice.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetUnmanagedDeviceByNameWithoutParams() {
        String anyUnmanagedDeviceName = "random-NAME";

        this.unmanagedDeviceClient.getUnmanagedDeviceByName(null, anyUnmanagedDeviceName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetUnmanagedDeviceByName() {
        String anyUnmanagedDeviceName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.getUnmanagedDeviceByName(new RestParams(), anyUnmanagedDeviceName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyUnmanagedDeviceCollectionIsReturnedForTheGivenName() {
        String anyUnmanagedDeviceName = "random-NAME";
        String unmanagedDeviceCollectionValue = "{\"category\":\"unmanaged-devices\"," +
                "\"members\": []}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(unmanagedDeviceCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(UnmanagedDevice.class)))
                .willReturn(new ResourceCollection<UnmanagedDevice>());

        this.unmanagedDeviceClient.getUnmanagedDeviceByName(new RestParams(), anyUnmanagedDeviceName);
    }

    @Test
    public void shouldGetUnmanagedDeviceByName() {
        String anyUnmanagedDeviceName = "random-NAME";
        String unmanagedDeviceCollectionValue = "{\"category\":\"unmanaged-devices\"," +
                "\"members\": [{\"category\":\"unmanaged-devices\"}]}";

        ResourceCollection<UnmanagedDevice> unmanagedDeviceCollection = new ResourceCollection<>();
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

        unmanagedDeviceCollection.setMembers(Lists.newArrayList(unmanagedDevice));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(unmanagedDeviceCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(UnmanagedDevice.class)))
                .willReturn(unmanagedDeviceCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + anyUnmanagedDeviceName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI));

        UnmanagedDevice response = this.unmanagedDeviceClient.getUnmanagedDeviceByName(new RestParams(),
                anyUnmanagedDeviceName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(unmanagedDeviceCollectionValue, UnmanagedDevice.class);

        assertThat(response, is(sameInstance(unmanagedDevice)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddUnmanagedDeviceWithoutParams() {
        this.unmanagedDeviceClient.addUnmanagedDevice(null, new UnmanagedDevice());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddUnmanagedDeviceWithoutRequest() {
        this.unmanagedDeviceClient.addUnmanagedDevice(new RestParams(), null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForAddUnmanagedDevice() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.addUnmanagedDevice(new RestParams(), new UnmanagedDevice());
    }

    @Test
    public void shouldAddUnmanagedDevice() {
        String unmanagedDeviceValue = "{\"category\":\"unmanaged-devices\"}";
        JSONObject jsonRequest = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(unmanagedDeviceValue);
        given(adaptor.buildJsonRequest(any(UnmanagedDevice.class), any(ApiVersion.class))).willReturn(jsonRequest);
        given(adaptor.buildResourceObject(anyString(), eq(UnmanagedDevice.class))).willReturn(new UnmanagedDevice());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI));

        this.unmanagedDeviceClient.addUnmanagedDevice(new RestParams(), new UnmanagedDevice());

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonRequest));
        then(adaptor).should().buildResourceObject(eq(unmanagedDeviceValue), eq(UnmanagedDevice.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateUnmanagedDeviceWithoutParams() {
        this.unmanagedDeviceClient.updateUnmanagedDevice(null, ANY_UNMANAGED_DEVICE_RESOURCE_ID, new UnmanagedDevice());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateUnmanagedDeviceWithoutRequest() {
        this.unmanagedDeviceClient.updateUnmanagedDevice(new RestParams(), ANY_UNMANAGED_DEVICE_RESOURCE_ID, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateUnmanagedDevice() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.updateUnmanagedDevice(new RestParams(), ANY_UNMANAGED_DEVICE_RESOURCE_ID,
                new UnmanagedDevice());
    }

    @Test
    public void shouldUpdateUnmanagedDevice() {
        String unmanagedDeviceValue = "{\"category\":\"unmanaged-devices\"}";
        JSONObject jsonRequest = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(unmanagedDeviceValue);
        given(adaptor.buildJsonRequest(any(UnmanagedDevice.class), any(ApiVersion.class))).willReturn(jsonRequest);
        given(adaptor.buildResourceObject(anyString(), eq(UnmanagedDevice.class))).willReturn(new UnmanagedDevice());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI, ANY_UNMANAGED_DEVICE_RESOURCE_ID));

        this.unmanagedDeviceClient.updateUnmanagedDevice(new RestParams(), ANY_UNMANAGED_DEVICE_RESOURCE_ID,
                new UnmanagedDevice());

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonRequest));
        then(adaptor).should().buildResourceObject(eq(unmanagedDeviceValue), eq(UnmanagedDevice.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveUnmanagedDeviceWithoutParams() {
        this.unmanagedDeviceClient.removeUnmanagedDevice(null, ANY_UNMANAGED_DEVICE_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRemoveUnmanagedDevice() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.removeUnmanagedDevice(new RestParams(), ANY_UNMANAGED_DEVICE_RESOURCE_ID);
    }

    @Test
    public void shouldRemoveUnmanagedDevice() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("{}");

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI, ANY_UNMANAGED_DEVICE_RESOURCE_ID));

        String response = this.unmanagedDeviceClient.removeUnmanagedDevice(new RestParams(),
                ANY_UNMANAGED_DEVICE_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));

        assertThat(response, is(equalTo("Removed")));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveUnmanagedDeviceByFilterWithoutParams() {
        this.unmanagedDeviceClient.removeUnmanagedDeviceByFilter(null, ANY_UNMANAGED_DEVICE_FILTER, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveUnmanagedDeviceByFilterWithoutFilter() {
        this.unmanagedDeviceClient.removeUnmanagedDeviceByFilter(new RestParams(), null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRemoveUnmanagedDeviceByFilter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.removeUnmanagedDeviceByFilter(new RestParams(), ANY_UNMANAGED_DEVICE_FILTER, false);
    }

    @Test
    public void shouldRemoveUnmanagedDeviceByFilter() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        Map<String, String> query = new HashMap<>();
        query.put("filter", ANY_UNMANAGED_DEVICE_FILTER);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI));
        expectedRestParams.setQuery(query);

        this.unmanagedDeviceClient.removeUnmanagedDeviceByFilter(new RestParams(), ANY_UNMANAGED_DEVICE_FILTER, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetEnvironmentalConfigurationWithoutParams() {
        this.unmanagedDeviceClient.getEnvironmentalConfiguration(null, ANY_UNMANAGED_DEVICE_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetEnvironmentalConfiguration() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.unmanagedDeviceClient.getEnvironmentalConfiguration(new RestParams(), ANY_UNMANAGED_DEVICE_RESOURCE_ID);
    }

    @Test
    public void shouldGetEnvironmentalConfiguration() {
        String environmentalConfigurationValue = "[{\"licenseRequirement\":\"None\",\"rackUHeight\":42}]";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(environmentalConfigurationValue);
        given(adaptor.buildResourceObject(anyString(), eq(EnvironmentalConfiguration.class)))
                .willReturn(new EnvironmentalConfiguration());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.UNMANAGED_DEVICE_URI, ANY_UNMANAGED_DEVICE_RESOURCE_ID,
                ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        this.unmanagedDeviceClient.getEnvironmentalConfiguration(new RestParams(), ANY_UNMANAGED_DEVICE_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(environmentalConfigurationValue),
                eq(EnvironmentalConfiguration.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        UnmanagedDeviceClient localUnmanagedDeviceClient = spy(this.unmanagedDeviceClient);

        doReturn(null).when(localUnmanagedDeviceClient).getUnmanagedDeviceByName(any(RestParams.class), anyString());

        //when
        String resourceId = localUnmanagedDeviceClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String unmanagedDeviceId = "AF5FBE7D-271F-4F4F-B4B4-D0017970E590";
        String unmanagedDeviceUri = "/rest/unmanaged-devices/" + unmanagedDeviceId;

        //given
        UnmanagedDeviceClient localUnmanagedDeviceClient = spy(this.unmanagedDeviceClient);
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

        unmanagedDevice.setUri(unmanagedDeviceUri);

        doReturn(unmanagedDevice).when(localUnmanagedDeviceClient)
                .getUnmanagedDeviceByName(any(RestParams.class), anyString());

        //when
        String resourceId = localUnmanagedDeviceClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(unmanagedDeviceId)));
    }
}
