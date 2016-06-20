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
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.MatcherAssert;
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
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class ServerHardwareTypeClientImplTest {

    private static final String ANY_SERVER_HARDWARE_TYPE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private ServerHardwareTypeClientImpl serverClient;

    @Test
    public void shouldReturnServerHardwareTypeClient() {
        assertThat(ServerHardwareTypeClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareTypeWithoutParams() {
        this.serverClient.getServerHardwareType(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareType() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareType(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardware() {
        String serverHardwareTypeValue = "{\"type\":\"server-hardware-type-4\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareTypeValue);
        given(adaptor.buildResourceObject(anyString(), eq(ServerHardwareType.class)))
                .willReturn(new ServerHardwareType());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_TYPE_URI, ANY_SERVER_HARDWARE_TYPE_ID));

        this.serverClient.getServerHardwareType(new RestParams(), ANY_SERVER_HARDWARE_TYPE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareTypeValue), eq(ServerHardwareType.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllServerHardwareTypesWithoutParams() {
        this.serverClient.getAllServerHardwareTypes(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllServerHardwareTypes() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getAllServerHardwareTypes(new RestParams());
    }

    @Test
    public void shouldGetAllServerHardwareTypes() {
        String serverHardwareTypeCollectionValue = "{\"type\":\"server-hardware-type-list-4\"," +
                "\"members\":[{\"type\":\"server-hardware-type-4\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareTypeCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(ServerHardwareType.class)))
                .willReturn(new ResourceCollection<ServerHardwareType>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_TYPE_URI));

        this.serverClient.getAllServerHardwareTypes(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(serverHardwareTypeCollectionValue),
                eq(ServerHardwareType.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareTypeByNameWithoutParams() {
        String anyServerHardwareTypeName = "random-NAME";

        this.serverClient.getServerHardwareTypeByName(null, anyServerHardwareTypeName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareTypeByName() {
        String anyServerHardwareTypeName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareTypeByName(new RestParams(), anyServerHardwareTypeName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoServerHardwareIsFoundForTheGivenName() {
        String anyServerHardwareTypeName = "random-NAME";
        String serverHardwareTypeCollectionValue = "{\"type\":\"server-hardware-type-list-4\"," +
                "\"members\":[]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareTypeCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(ServerHardwareType.class)))
                .willReturn(new ResourceCollection<ServerHardwareType>());

        this.serverClient.getServerHardwareTypeByName(new RestParams(), anyServerHardwareTypeName);
    }

    @Test
    public void shouldGetServerHardwareByName() {
        String anyServerHardwareTypeName = "random-NAME";
        String serverHardwareTypeCollectionValue = "{\"type\":\"server-hardware-type-list-4\"," +
                "\"members\":[{\"type\":\"server-hardware-type-4\"}]}";
        ResourceCollection<ServerHardwareType> serverHardwareTypeCollection = new ResourceCollection<>();

        ServerHardwareType serverHardwareType = new ServerHardwareType();
        serverHardwareTypeCollection.setMembers(Lists.newArrayList(serverHardwareType));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareTypeCollectionValue);
        given(adaptor.buildResourceCollection(serverHardwareTypeCollectionValue, ServerHardwareType.class))
                .willReturn(serverHardwareTypeCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyServerHardwareTypeName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_TYPE_URI));

        ServerHardwareType response = this.serverClient.getServerHardwareTypeByName(new RestParams(),
                anyServerHardwareTypeName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(serverHardwareTypeCollectionValue),
                eq(ServerHardwareType.class));

        assertThat(response, sameInstance(serverHardwareType));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwareTypeWithoutParams() {
        this.serverClient.updateServerHardwareType(null, ANY_SERVER_HARDWARE_TYPE_ID,
                new ServerHardwareTypeUpdate());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwareTypeWithoutRequest() {
        this.serverClient.updateServerHardwareType(new RestParams(), ANY_SERVER_HARDWARE_TYPE_ID, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateServerHardwareType() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.updateServerHardwareType(new RestParams(), ANY_SERVER_HARDWARE_TYPE_ID,
                new ServerHardwareTypeUpdate());
    }

    @Test
    public void shouldUpdateServerHardwareType() {
        String serverHardwareTypeValue = "{\"type\":\"server-hardware-type-4\"}";
        ServerHardwareTypeUpdate update = new ServerHardwareTypeUpdate();
        JSONObject jsonObject = new JSONObject();

        given(adaptor.buildJsonRequest(eq(update), any(ApiVersion.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(serverHardwareTypeValue);
        given(adaptor.buildResourceObject(serverHardwareTypeValue, ServerHardwareType.class))
                .willReturn(new ServerHardwareType());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_TYPE_URI, ANY_SERVER_HARDWARE_TYPE_ID));

        this.serverClient.updateServerHardwareType(new RestParams(), ANY_SERVER_HARDWARE_TYPE_ID, update);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(serverHardwareTypeValue, ServerHardwareType.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteServerHardwareTypeWithoutParams() {
        this.serverClient.deleteServerHardwareType(null, ANY_SERVER_HARDWARE_TYPE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteServerHardwareType() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.deleteServerHardwareType(new RestParams(), ANY_SERVER_HARDWARE_TYPE_ID, false);
    }

    @Test
    public void shouldDeleteServerHardwareType() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_TYPE_URI, ANY_SERVER_HARDWARE_TYPE_ID));

        this.serverClient.deleteServerHardwareType(new RestParams(), ANY_SERVER_HARDWARE_TYPE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() throws IOException {
        //given
        ServerHardwareTypeClient localServerClient = spy(this.serverClient);
        ServerHardwareType serverHardwareType = new ServerHardwareType();

        doReturn(serverHardwareType).when(localServerClient).getServerHardwareTypeByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localServerClient.getId(new RestParams(), "");

        //then
        MatcherAssert.assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String expectedResourceId = "c1067255-210d-454a-b199-c1faa98e0c97";
        String networkUri = "rest/server-hardware-types/c1067255-210d-454a-b199-c1faa98e0c97";

        //given
        ServerHardwareTypeClient localServerClient = spy(this.serverClient);
        ServerHardwareType serverHardwareType = new ServerHardwareType();

        serverHardwareType.setUri(networkUri);

        doReturn(serverHardwareType).when(localServerClient).getServerHardwareTypeByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localServerClient.getId(new RestParams(), null);

        //then
        MatcherAssert.assertThat(resourceId, is(equalTo(expectedResourceId)));
    }
}
