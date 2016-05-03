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
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
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
import com.hp.ov.sdk.dto.SwitchPortStatistics;
import com.hp.ov.sdk.dto.SwitchStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.Switch;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class SwitchClientImplTest {

    private static final String ANY_SWITCH_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private SwitchClientImpl switchClient;

    @Test
    public void shouldReturnSwitchClient() {
        assertThat(SwitchClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetSwitchWithoutParams() {
        this.switchClient.getSwitch(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getSwitch(new RestParams(), null);
    }

    @Test
    public void shouldGetSwitch() {
        String switchValue = "{\"type\":\"switchV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchValue);
        given(adaptor.buildResourceObject(anyString(), eq(Switch.class))).willReturn(new Switch());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI, ANY_SWITCH_ID));

        this.switchClient.getSwitch(new RestParams(), ANY_SWITCH_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(switchValue), eq(Switch.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllSwitchesWithoutParams() {
        this.switchClient.getAllSwitches(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllSwitches() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getAllSwitches(new RestParams());
    }

    @Test
    public void shouldGetAllSwitches() {
        String switchCollectionValue = "{\"type\":\"SwitchCollectionV2\", \"members\":[\"type\":\"switchV2\"]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(Switch.class)))
                .willReturn(new ResourceCollection<Switch>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.SWITCHES_URI));

        this.switchClient.getAllSwitches(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(switchCollectionValue), eq(Switch.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateSwitchWithoutParams() {
        this.switchClient.createSwitch(null, new Switch(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateSwitchWithoutRequest() {
        this.switchClient.createSwitch(new RestParams(), null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForCreateSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.createSwitch(new RestParams(), new Switch(), false);
    }

    @Test
    public void shouldCreateSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), anyString(), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.SWITCHES_URI));

        this.switchClient.createSwitch(new RestParams(), new Switch(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), any(JSONObject.class));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateSwitchWithoutParams() {
        this.switchClient.updateSwitch(null, ANY_SWITCH_ID, new Switch(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateSwitchWithoutRequest() {
        this.switchClient.updateSwitch(new RestParams(), ANY_SWITCH_ID, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.updateSwitch(new RestParams(), ANY_SWITCH_ID, new Switch(), false);
    }

    @Test
    public void shouldUpdateSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), anyString(), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI, ANY_SWITCH_ID));

        this.switchClient.updateSwitch(new RestParams(), ANY_SWITCH_ID, new Switch(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), any(JSONObject.class));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteSwitchWithoutParams() {
        this.switchClient.deleteSwitch(null, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.deleteSwitch(new RestParams(), null, false);
    }

    @Test
    public void shouldDeleteSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), anyString(), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI, ANY_SWITCH_ID));

        this.switchClient.deleteSwitch(new RestParams(), ANY_SWITCH_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRefreshSwitchWithoutParams() {
        this.switchClient.refreshSwitch(null, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRefreshSwitch() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.refreshSwitch(new RestParams(), null, false);
    }

    @Test
    public void shouldRefreshSwitch() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), anyString(), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI, ANY_SWITCH_ID));

        this.switchClient.refreshSwitch(new RestParams(), ANY_SWITCH_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams), any(JSONObject.class));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetSwitchEnvironmentalConfigurationWithoutParams() {
        this.switchClient.getSwitchEnvironmentalConfiguration(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetSwitchEnvironmentalConfiguration() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getSwitchEnvironmentalConfiguration(new RestParams(), null);
    }

    @Test
    public void shouldGetSwitchEnvironmentalConfiguration() {
        String environmentalConfigurationValue = "{\"type\":\"environmentalConfigurationV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(environmentalConfigurationValue);
        given(adaptor.buildResourceObject(anyString(), eq(EnvironmentalConfiguration.class)))
                .willReturn(new EnvironmentalConfiguration());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI, ANY_SWITCH_ID,
                ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        this.switchClient.getSwitchEnvironmentalConfiguration(new RestParams(), ANY_SWITCH_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(environmentalConfigurationValue),
                eq(EnvironmentalConfiguration.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetSwitchStatisticsWithoutParams() {
        this.switchClient.getSwitchStatistics(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetSwitchStatistics() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getSwitchStatistics(new RestParams(), null);
    }

    @Test
    public void shouldGetSwitchStatistics() {
        String statisticsValue = "{\"type\":\"GenericSwitchStatistics\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(statisticsValue);
        given(adaptor.buildResourceObject(anyString(), eq(SwitchStatistics.class)))
                .willReturn(new SwitchStatistics());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI, ANY_SWITCH_ID, ResourceUris.SWITCHES_STATISTICS_URI));

        this.switchClient.getSwitchStatistics(new RestParams(), ANY_SWITCH_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(statisticsValue), eq(SwitchStatistics.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetSwitchPortStatisticsWithoutParams() {
        String anySwitchPortName = "random-NAME";

        this.switchClient.getSwitchPortStatistics(null, null, anySwitchPortName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetSwitchPortStatistics() {
        String anySwitchPortName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getSwitchPortStatistics(new RestParams(), null, anySwitchPortName);
    }

    @Test
    public void shouldGetSwitchPortStatistics() {
        String anySwitchPortName = "random-NAME";
        String portStatisticsValue = "{\"type\":\"GenericSwitchPortStatistics\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(portStatisticsValue);
        given(adaptor.buildResourceObject(anyString(), eq(SwitchPortStatistics.class)))
                .willReturn(new SwitchPortStatistics());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI, ANY_SWITCH_ID,
                ResourceUris.SWITCHES_STATISTICS_URI, anySwitchPortName));

        this.switchClient.getSwitchPortStatistics(new RestParams(), ANY_SWITCH_ID, anySwitchPortName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(portStatisticsValue), eq(SwitchPortStatistics.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetSwitchByNameWithoutParams() {
        String anySwitchName = "random-NAME";

        this.switchClient.getSwitchByName(null, anySwitchName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetSwitchByName() {
        String anySwitchName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getSwitchByName(new RestParams(), anySwitchName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoSwitchIsFoundForTheGivenName() {
        String anySwitchName = "random-NAME";
        String switchCollectionValue = "{\"type\":\"SwitchCollectionV2\", \"members\":[]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(Switch.class)))
                .willReturn(new ResourceCollection<Switch>());

        this.switchClient.getSwitchByName(new RestParams(), anySwitchName);
    }

    @Test
    public void shouldGetSwitchByName() {
        String anySwitchName = "random-NAME";
        String switchCollectionValue = "{\"type\":\"SwitchCollectionV2\", \"members\":[\"type\":\"switchV2\"]}";
        ResourceCollection<Switch> switchCollection = new ResourceCollection<>();
        Switch switchObj = new Switch();

        switchObj.setName(anySwitchName);
        switchCollection.setMembers(Lists.newArrayList(switchObj));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(Switch.class)))
                .willReturn(switchCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anySwitchName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCHES_URI));

        this.switchClient.getSwitchByName(new RestParams(), anySwitchName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(switchCollectionValue), eq(Switch.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() throws IOException {
        //given
        SwitchClient localSwitchClient = spy(this.switchClient);
        Switch switchObj = new Switch();

        doReturn(switchObj).when(localSwitchClient).getSwitchByName(any(RestParams.class), anyString());

        //when
        String resourceId = localSwitchClient.getId(new RestParams(), "");

        //then
        MatcherAssert.assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String expectedResourceId = "c1067255-210d-454a-b199-c1faa98e0c97";
        String switchUri = "rest/switches/c1067255-210d-454a-b199-c1faa98e0c97";

        //given
        SwitchClient localSwitchClient = spy(this.switchClient);
        Switch switchObj = new Switch();

        switchObj.setUri(switchUri);

        doReturn(switchObj).when(localSwitchClient).getSwitchByName(any(RestParams.class), anyString());

        //when
        String resourceId = localSwitchClient.getId(new RestParams(), "");

        //then
        MatcherAssert.assertThat(resourceId, is(equalTo(expectedResourceId)));
    }

}