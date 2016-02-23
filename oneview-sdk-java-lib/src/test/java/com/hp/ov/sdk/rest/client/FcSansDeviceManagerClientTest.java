/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.mockito.BDDMockito.anyObject;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.doReturn;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.spy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import java.io.IOException;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.DeviceManagerAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.DeviceManagerResponseCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FcSansDeviceManagerClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    private static final String ANY_DEVICE_MANAGER = "{\"connectionInfo\":[" +
            "{\"name\":\"Host\",\"value\":\"brocade-device-manager.domain.com\"}," +
            "{\"name\":\"Port\",\"value\":5989}," +
            "{\"name\":\"Username\",\"value\":\"Administrator\"}," +
            "{\"name\":\"Password\",\"value\":\"password\"}," +
            "{\"name\":\"UseSsl\",\"value\":true}]}";

    @Mock
    private DeviceManagerAdaptor adaptor;
    @Mock
    private HttpRestClient client;

    @InjectMocks
    private FcSansDeviceManagerClientImpl deviceManagerClient;

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateDeviceManagerWithoutParams() {
        this.deviceManagerClient.createDeviceManager(null, null, null, false, false);
    }

    @Test
    public void shouldCreateDeviceManager() throws IOException {
        String providerUri = "random-URI";
        JSONObject jsonObject = new JSONObject(ANY_DEVICE_MANAGER);

        given(adaptor.buildDto(anyObject())).willReturn(new DeviceManagerResponse());
        given(adaptor.buildJsonObjectFromDto(any(DeviceManagerResponse.class))).willReturn(jsonObject);
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(ANY_DEVICE_MANAGER);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), providerUri));

        this.deviceManagerClient.createDeviceManager(new RestParams(), providerUri, new DeviceManagerResponse(), false, false);

        then(client).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildDto(ANY_DEVICE_MANAGER);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllDeviceManagersWithoutParams() {
        this.deviceManagerClient.getAllDeviceManager(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllDeviceManagers() throws IOException {
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn("");

        this.deviceManagerClient.getAllDeviceManager(new RestParams());
    }

    @Test
    public void shouldGetAllDeviceManagers() throws IOException {
        String deviceManagerResponseList = this.getJsonFromFile("DeviceManagerResponseList.json");

        given(client.sendRequest(any(RestParams.class))).willReturn(deviceManagerResponseList);
        given(adaptor.buildCollectionDto(anyObject())).willReturn(new DeviceManagerResponseCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI));

        this.deviceManagerClient.getAllDeviceManager(new RestParams());

        then(client).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildCollectionDto(deviceManagerResponseList);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetDeviceManagerWithoutParams() {
        this.deviceManagerClient.getDeviceManager(null, ANY_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetDeviceManager() throws IOException {
        given(client.sendRequest(any(RestParams.class))).willReturn("");

        this.deviceManagerClient.getDeviceManager(new RestParams(), ANY_RESOURCE_ID);
    }

    @Test
    public void shouldGetDeviceManager() throws IOException {
        String deviceManagerResponse = this.getJsonFromFile("DeviceManagerResponse.json");

        given(client.sendRequest(any(RestParams.class))).willReturn(deviceManagerResponse);
        given(adaptor.buildDto(anyObject())).willReturn(new DeviceManagerResponse());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI, ANY_RESOURCE_ID));

        this.deviceManagerClient.getDeviceManager(new RestParams(), ANY_RESOURCE_ID);

        then(client).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildDto(deviceManagerResponse);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteDeviceManagerWithoutParams() {
        this.deviceManagerClient.deleteDeviceManager(null, ANY_RESOURCE_ID);
    }

    @Test
    public void shouldDeleteDeviceManager() {
        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI, ANY_RESOURCE_ID));

        this.deviceManagerClient.deleteDeviceManager(new RestParams(), ANY_RESOURCE_ID);

        then(client).should().sendRequest(eq(expectedRestParams));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateDeviceManagerWithoutParams() {
        this.deviceManagerClient.updateDeviceManager(null, ANY_RESOURCE_ID, null, false);
    }

    @Test
    public void shouldUpdateDeviceManager() throws IOException {
        JSONObject jsonObject = new JSONObject(ANY_DEVICE_MANAGER);

        given(adaptor.buildDto(anyObject())).willReturn(new DeviceManagerResponse());
        given(adaptor.buildJsonObjectFromDto(any(DeviceManagerResponse.class))).willReturn(jsonObject);
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(ANY_DEVICE_MANAGER);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI, ANY_RESOURCE_ID));

        this.deviceManagerClient.updateDeviceManager(new RestParams(), ANY_RESOURCE_ID, new DeviceManagerResponse(), false);

        then(client).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildDto(ANY_DEVICE_MANAGER);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRetrieveDeviceManagerByNameWithoutParams() {
        this.deviceManagerClient.getDeviceManagerByName(null, ANY_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetDeviceManagerByName() throws IOException {
        given(client.sendRequest(any(RestParams.class))).willReturn(null);

        this.deviceManagerClient.getDeviceManagerByName(new RestParams(), "");
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoDeviceManagerIsFoundForTheGivenName() throws IOException {
        String anyName = "random-NAME";
        String deviceManagerResponseList = this.getJsonFromFile("DeviceManagerResponseList.json");

        DeviceManagerResponseCollection deviceManagerResponse = new DeviceManagerResponseCollection();
        deviceManagerResponse.setCount(0);

        given(client.sendRequest(any(RestParams.class))).willReturn(deviceManagerResponseList);
        given(adaptor.buildCollectionDto(anyObject())).willReturn(deviceManagerResponse);

        this.deviceManagerClient.getDeviceManagerByName(new RestParams(), anyName);
    }

    @Test
    public void shouldGetDeviceManagerByName() throws IOException {
        String anyName = "random-NAME";
        String deviceManagerResponseList = this.getJsonFromFile("DeviceManagerResponseList.json");

        DeviceManagerResponseCollection deviceManagerResponse = new DeviceManagerResponseCollection();

        deviceManagerResponse.setCount(1);
        deviceManagerResponse.setMembers(Lists.newArrayList(new DeviceManagerResponse()));

        given(client.sendRequest(any(RestParams.class))).willReturn(deviceManagerResponseList);
        given(adaptor.buildCollectionDto(anyObject())).willReturn(deviceManagerResponse);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestQueryUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI, UrlUtils.createQueryString(anyName)));

        this.deviceManagerClient.getDeviceManagerByName(new RestParams(), anyName);

        then(client).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildCollectionDto(deviceManagerResponseList);
    }

    @Test
    public void shouldReturnEmptyStringForGetId() throws IOException {
        //given
        FcSansDeviceManagerClient localDeviceManagerClient = spy(this.deviceManagerClient);
        DeviceManagerResponse deviceManagerResponse = new DeviceManagerResponse();

        doReturn(deviceManagerResponse).when(localDeviceManagerClient).getDeviceManagerByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localDeviceManagerClient.getId(new RestParams(), null);

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String expectedResourceId = "637ccea4-4be7-4fb7-8aa4-54433599c777";
        String deviceManagerUri = "/rest/fc-sans/device-managers/637ccea4-4be7-4fb7-8aa4-54433599c777";

        //given
        FcSansDeviceManagerClient localDeviceManagerClient = spy(this.deviceManagerClient);
        DeviceManagerResponse deviceManagerResponse = new DeviceManagerResponse();

        deviceManagerResponse.setUri(deviceManagerUri);

        doReturn(deviceManagerResponse).when(localDeviceManagerClient).getDeviceManagerByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localDeviceManagerClient.getId(new RestParams(), null);

        //then
        assertThat(resourceId, is(equalTo(expectedResourceId)));
    }

    private String getJsonFromFile(String filename) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(filename), "UTF-8");
    }
}
