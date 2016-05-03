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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

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
import com.hp.ov.sdk.dto.AddLogicalSwitch;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LogicalSwitch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class LogicalSwitchClientImplTest {

    private static final String RESOURCE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private LogicalSwitchClientImpl switchClient;

    @Test
    public void shouldReturnLogicalSwitchClient() {
        assertThat(LogicalSwitchClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetLogicalSwitchWithoutParams() {
        this.switchClient.getLogicalSwitch(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetLogicalSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getLogicalSwitch(new RestParams(), null);
    }

    @Test
    public void shouldGetLogicalSwitch() {
        String logicalSwitchValue = "{\"type\":\"logical-switch\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchValue);
        given(adaptor.buildResourceObject(anyString(), eq(LogicalSwitch.class))).willReturn(new LogicalSwitch());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI, RESOURCE_ID));

        this.switchClient.getLogicalSwitch(new RestParams(), RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(logicalSwitchValue), eq(LogicalSwitch.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetLogicalSwitchByNameWithoutParams() {
        this.switchClient.getLogicalSwitchByName(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetLogicalSwitchByName() {
        String anyLogicalSwitchName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getLogicalSwitchByName(new RestParams(), anyLogicalSwitchName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoLogicalSwitchIsFoundForTheGivenName() {
        String anyLogicalSwitchName = "random-NAME";
        String logicalSwitchCollectionValue = "{\"type\":\"LogicalSwitchCollection\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(LogicalSwitch.class)))
                .willReturn(new ResourceCollection<LogicalSwitch>());

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyLogicalSwitchName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI));

        this.switchClient.getLogicalSwitchByName(new RestParams(), anyLogicalSwitchName);
    }

    @Test
    public void shouldGetLogicalSwitchByName() {
        String anyLogicalSwitchName = "random-NAME";
        String logicalSwitchCollectionValue = "{\"type\":\"LogicalSwitchCollection\"}";
        ResourceCollection<LogicalSwitch> logicalSwitchCollection = new ResourceCollection<>();

        LogicalSwitch expectedLogicalSwitch = new LogicalSwitch();
        logicalSwitchCollection.setMembers(Lists.newArrayList(expectedLogicalSwitch));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(LogicalSwitch.class)))
                .willReturn(logicalSwitchCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyLogicalSwitchName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI));

        LogicalSwitch logicalSwitch = this.switchClient.getLogicalSwitchByName(new RestParams(),
                anyLogicalSwitchName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(logicalSwitchCollectionValue), eq(LogicalSwitch.class));

        assertThat(logicalSwitch, sameInstance(expectedLogicalSwitch));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllLogicalSwitchesWithoutParams() {
        this.switchClient.getAllLogicalSwitches(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllLogicalSwitches() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getAllLogicalSwitches(new RestParams());
    }

    @Test
    public void shouldGetAllLogicalSwitches() {
        String logicalSwitchValue = "{\"type\":\"LogicalSwitchCollection\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchValue);
        given(adaptor.buildResourceCollection(anyString(), eq(LogicalSwitch.class)))
                .willReturn(new ResourceCollection<LogicalSwitch>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI));

        this.switchClient.getAllLogicalSwitches(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(logicalSwitchValue), eq(LogicalSwitch.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateLogicalSwitchWithoutParams() {
        this.switchClient.createLogicalSwitch(null, new AddLogicalSwitch(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateLogicalSwitchWithoutRequest() {
        this.switchClient.createLogicalSwitch(new RestParams(), null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForCreateLogicalSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.createLogicalSwitch(new RestParams(), new AddLogicalSwitch(), false);
    }

    @Test
    public void shouldCreateLogicalSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        AddLogicalSwitch addLogicalSwitch = new AddLogicalSwitch();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(addLogicalSwitch), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI));

        TaskResourceV2 task = this.switchClient.createLogicalSwitch(new RestParams(), addLogicalSwitch, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);

        assertThat(task, is(sameInstance(taskResource)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateLogicalSwitchWithoutParams() {
        this.switchClient.updateLogicalSwitch(null, RESOURCE_ID, new AddLogicalSwitch(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateLogicalSwitchWithoutRequest() {
        this.switchClient.updateLogicalSwitch(new RestParams(), RESOURCE_ID, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateLogicalSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.updateLogicalSwitch(new RestParams(), RESOURCE_ID, new AddLogicalSwitch(), false);
    }

    @Test
    public void shouldUpdateLogicalSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        AddLogicalSwitch addLogicalSwitch = new AddLogicalSwitch();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(addLogicalSwitch), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI, RESOURCE_ID));

        TaskResourceV2 task = this.switchClient.updateLogicalSwitch(new RestParams(), RESOURCE_ID,
                addLogicalSwitch, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);

        assertThat(task, is(sameInstance(taskResource)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteLogicalSwitchWithoutParams() {
        this.switchClient.deleteLogicalSwitch(null, RESOURCE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteLogicalSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.deleteLogicalSwitch(new RestParams(), RESOURCE_ID, false);
    }

    @Test
    public void shouldDeleteLogicalSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI, RESOURCE_ID));

        this.switchClient.deleteLogicalSwitch(new RestParams(), RESOURCE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRefreshLogicalSwitchWithoutParams() {
        this.switchClient.refreshLogicalSwitch(null, RESOURCE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRefreshLogicalSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.refreshLogicalSwitch(new RestParams(), RESOURCE_ID, false);
    }

    @Test
    public void shouldRefreshLogicalSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI, RESOURCE_ID, ResourceUris.LOGICAL_SWITCHES_REFRESH_URI));

        this.switchClient.refreshLogicalSwitch(new RestParams(), RESOURCE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        LogicalSwitchClient localSwitchClient = spy(this.switchClient);

        doReturn(null).when(localSwitchClient).getLogicalSwitchByName(any(RestParams.class), anyString());

        //when
        String resourceId = localSwitchClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String logicalSwitchId = "b231a2fe-5fc8-43de-997b-324b7a1fbcca";
        String logicalSwitchUri = "/rest/logical-switches/" + logicalSwitchId;

        //given
        LogicalSwitchClient localSwitchClient = spy(this.switchClient);
        LogicalSwitch logicalSwitch = new LogicalSwitch();

        logicalSwitch.setUri(logicalSwitchUri);

        doReturn(logicalSwitch).when(localSwitchClient).getLogicalSwitchByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localSwitchClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(logicalSwitchId)));
    }
}