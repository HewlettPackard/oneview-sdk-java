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
import com.hp.ov.sdk.dto.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.LogicalSwitchGroupCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class LogicalSwitchGroupClientImplTest {

    private static final String RESOURCE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private LogicalSwitchGroupClientImpl switchClient;

    @Test
    public void shouldReturnLogicalSwitchGroupClient() {
        assertThat(LogicalSwitchGroupClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetLogicalSwitchGroupWithoutParams() {
        this.switchClient.getLogicalSwitchGroup(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetLogicalSwitchGroup() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getLogicalSwitchGroup(new RestParams(), null);
    }

    @Test
    public void shouldGetLogicalSwitchGroup() {
        String logicalSwitchGroupValue = "{\"type\":\"logical-switch-group\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchGroupValue);
        given(adaptor.buildResourceObject(anyString(), eq(LogicalSwitchGroup.class)))
                .willReturn(new LogicalSwitchGroup());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, RESOURCE_ID));

        this.switchClient.getLogicalSwitchGroup(new RestParams(), RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(logicalSwitchGroupValue),
                eq(LogicalSwitchGroup.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetLogicalSwitchGroupByNameWithoutParams() {
        this.switchClient.getLogicalSwitchGroupByName(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetLogicalSwitchGroupByName() {
        String anyLogicalSwitchGroupName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getLogicalSwitchGroupByName(new RestParams(), anyLogicalSwitchGroupName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoLogicalSwitchGroupIsFoundForTheGivenName() {
        String anyLogicalSwitchGroupName = "random-NAME";
        String logicalSwitchGroupCollectionValue = "{\"type\":\"LogicalSwitchGroupCollection\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchGroupCollectionValue);
        given(adaptor.buildResourceObject(anyString(), eq(LogicalSwitchGroupCollection.class)))
                .willReturn(new LogicalSwitchGroupCollection());

        this.switchClient.getLogicalSwitchGroupByName(new RestParams(), anyLogicalSwitchGroupName);
    }

    @Test
    public void shouldGetLogicalSwitchGroupByName() {
        String anyLogicalSwitchGroupName = "random-NAME";
        String logicalSwitchGroupCollectionValue = "{\"type\":\"LogicalSwitchGroupCollection\"}";
        LogicalSwitchGroupCollection logicalSwitchGroupCollection = new LogicalSwitchGroupCollection();

        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();
        logicalSwitchGroupCollection.setMembers(Lists.newArrayList(logicalSwitchGroup));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchGroupCollectionValue);
        given(adaptor.buildResourceObject(anyString(), eq(LogicalSwitchGroupCollection.class)))
                .willReturn(logicalSwitchGroupCollection);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestQueryUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, UrlUtils.createFilterString(anyLogicalSwitchGroupName)));

        LogicalSwitchGroup group = this.switchClient.getLogicalSwitchGroupByName(new RestParams(),
                anyLogicalSwitchGroupName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(logicalSwitchGroupCollectionValue),
                eq(LogicalSwitchGroupCollection.class));

        assertThat(group, sameInstance(logicalSwitchGroup));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllLogicalSwitchGroupsWithoutParams() {
        this.switchClient.getAllLogicalSwitchGroups(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllLogicalSwitchGroups() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getAllLogicalSwitchGroups(new RestParams());
    }

    @Test
    public void shouldGetAllLogicalSwitchGroups() {
        String logicalSwitchGroupCollectionValue = "{\"type\":\"LogicalSwitchGroupCollection\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(logicalSwitchGroupCollectionValue);
        given(adaptor.buildResourceObject(anyString(), eq(LogicalSwitchGroupCollection.class)))
                .willReturn(new LogicalSwitchGroupCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI));

        this.switchClient.getAllLogicalSwitchGroups(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(logicalSwitchGroupCollectionValue),
                eq(LogicalSwitchGroupCollection.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateLogicalSwitchGroupWithoutParams() {
        this.switchClient.createLogicalSwitchGroup(null, new LogicalSwitchGroup(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateLogicalSwitchGroupWithoutRequest() {
        this.switchClient.createLogicalSwitchGroup(new RestParams(), null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForCreateLogicalSwitchGroup() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.createLogicalSwitchGroup(new RestParams(), new LogicalSwitchGroup(), false);
    }

    @Test
    public void shouldCreateLogicalSwitchGroup() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(logicalSwitchGroup), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI));

        TaskResourceV2 task = this.switchClient.createLogicalSwitchGroup(new RestParams(), logicalSwitchGroup, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);

        assertThat(task, is(sameInstance(taskResource)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateLogicalSwitchGroupWithoutParams() {
        this.switchClient.updateLogicalSwitchGroup(null, RESOURCE_ID, new LogicalSwitchGroup(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateLogicalSwitchGroupWithoutRequest() {
        this.switchClient.updateLogicalSwitchGroup(new RestParams(), RESOURCE_ID, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateLogicalSwitchGroup() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.updateLogicalSwitchGroup(new RestParams(), RESOURCE_ID, new LogicalSwitchGroup(), false);
    }

    @Test
    public void shouldUpdateLogicalSwitchGroup() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(logicalSwitchGroup), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, RESOURCE_ID));

        TaskResourceV2 task = this.switchClient.updateLogicalSwitchGroup(new RestParams(), RESOURCE_ID,
                logicalSwitchGroup, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);

        assertThat(task, is(sameInstance(taskResource)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteLogicalSwitchGroupWithoutParams() {
        this.switchClient.deleteLogicalSwitchGroup(null, RESOURCE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteLogicalSwitchGroup() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.deleteLogicalSwitchGroup(new RestParams(), RESOURCE_ID, false);
    }

    @Test
    public void shouldDeleteLogicalSwitchGroup() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, RESOURCE_ID));

        this.switchClient.deleteLogicalSwitchGroup(new RestParams(), RESOURCE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        LogicalSwitchGroupClient localSwitchClient = spy(this.switchClient);

        doReturn(null).when(localSwitchClient).getLogicalSwitchGroupByName(any(RestParams.class), anyString());

        //when
        String resourceId = localSwitchClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String logicalSwitchGroupId = "b231a2fe-5fc8-43de-997b-324b7a1fbcca";
        String logicalSwitchGroupUri = "/rest/logical-switch-groups/" + logicalSwitchGroupId;

        //given
        LogicalSwitchGroupClient localSwitchClient = spy(this.switchClient);
        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();

        logicalSwitchGroup.setUri(logicalSwitchGroupUri);

        doReturn(logicalSwitchGroup).when(localSwitchClient).getLogicalSwitchGroupByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localSwitchClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(logicalSwitchGroupId)));
    }

}