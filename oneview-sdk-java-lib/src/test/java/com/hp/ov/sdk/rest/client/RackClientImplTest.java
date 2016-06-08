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
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class RackClientImplTest {

    private static final String ANY_RACK_RESOURCE_ID = "random-UUID";
    private static final String ANY_RACK_FILTER = "name='random-Name'";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private RackClientImpl rackClient;

    @Test
    public void shouldReturnRackClient() {
        assertThat(RackClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetRackWithoutParams() {
        this.rackClient.getRack(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetRack() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.getRack(new RestParams(), null);
    }

    @Test
    public void shouldGetRack() {
        String rackValue = "{\"category\":\"racks\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(rackValue);
        given(adaptor.buildResourceObject(anyString(), eq(Rack.class))).willReturn(new Rack());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.RACK_URI, ANY_RACK_RESOURCE_ID));

        this.rackClient.getRack(new RestParams(), ANY_RACK_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(rackValue), eq(Rack.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllRacksWithoutParams() {
        this.rackClient.getAllRacks(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllRacks() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.getAllRacks(new RestParams());
    }

    @Test
    public void shouldGetAllRacks() {
        String rackCollectionValue = "{\"type\":\"ErmResourceCollection\"," +
                "\"members\":[{\"category\":\"racks\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(rackCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(Rack.class)))
                .willReturn(new ResourceCollection<Rack>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.RACK_URI));

        this.rackClient.getAllRacks(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(rackCollectionValue), eq(Rack.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetRackByNameWithoutParams() {
        String anyRackName = "random-NAME";

        this.rackClient.getRackByName(null, anyRackName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetRackByName() {
        String anyRackName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.getRackByName(new RestParams(), anyRackName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyRackCollectionIsReturnedForTheGivenName() {
        String anyRackName = "random-NAME";
        String rackCollectionValue = "{\"type\":\"ErmResourceCollection\"," +
                "\"members\": []}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(rackCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(Rack.class)))
                .willReturn(new ResourceCollection<Rack>());

        this.rackClient.getRackByName(new RestParams(), anyRackName);
    }

    @Test
    public void shouldGetRackByName() {
        String anyRackName = "random-NAME";
        String rackCollectionValue = "{\"type\":\"ErmResourceCollection\"," +
                "\"members\": [{\"category\":\"racks\"}]}";

        ResourceCollection<Rack> rackCollection = new ResourceCollection<>();
        Rack rack = new Rack();

        rackCollection.setMembers(Lists.newArrayList(rack));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(rackCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(Rack.class))).willReturn(rackCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + anyRackName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.RACK_URI));

        Rack response = this.rackClient.getRackByName(new RestParams(), anyRackName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(rackCollectionValue, Rack.class);

        assertThat(response, is(sameInstance(rack)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddRackWithoutParams() {
        this.rackClient.addRack(null, new Rack());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddRackWithoutRequest() {
        this.rackClient.addRack(new RestParams(), null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForAddRack() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.addRack(new RestParams(), new Rack());
    }

    @Test
    public void shouldAddRack() {
        String rackValue = "{\"category\":\"racks\"}";
        JSONObject jsonRequest = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(rackValue);
        given(adaptor.buildJsonRequest(any(Rack.class), any(ApiVersion.class))).willReturn(jsonRequest);
        given(adaptor.buildResourceObject(anyString(), eq(Rack.class))).willReturn(new Rack());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.RACK_URI));

        this.rackClient.addRack(new RestParams(), new Rack());

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonRequest));
        then(adaptor).should().buildResourceObject(eq(rackValue), eq(Rack.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateRackWithoutParams() {
        this.rackClient.updateRack(null, ANY_RACK_RESOURCE_ID, new Rack());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateRackWithoutRequest() {
        this.rackClient.updateRack(new RestParams(), ANY_RACK_RESOURCE_ID, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateRack() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.updateRack(new RestParams(), ANY_RACK_RESOURCE_ID, new Rack());
    }

    @Test
    public void shouldUpdateRack() {
        String rackValue = "{\"category\":\"racks\"}";
        JSONObject jsonRequest = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(rackValue);
        given(adaptor.buildJsonRequest(any(Rack.class), any(ApiVersion.class))).willReturn(jsonRequest);
        given(adaptor.buildResourceObject(anyString(), eq(Rack.class))).willReturn(new Rack());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.RACK_URI, ANY_RACK_RESOURCE_ID));

        this.rackClient.updateRack(new RestParams(), ANY_RACK_RESOURCE_ID, new Rack());

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonRequest));
        then(adaptor).should().buildResourceObject(eq(rackValue), eq(Rack.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveRackWithoutParams() {
        this.rackClient.removeRack(null, ANY_RACK_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRemoveRack() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.removeRack(new RestParams(), ANY_RACK_RESOURCE_ID);
    }

    @Test
    public void shouldRemoveRack() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("{}");

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.RACK_URI, ANY_RACK_RESOURCE_ID));

        String response = this.rackClient.removeRack(new RestParams(), ANY_RACK_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));

        assertThat(response, is(equalTo("Removed")));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveRackByFilterWithoutParams() {
        this.rackClient.removeRackByFilter(null, ANY_RACK_FILTER, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveRackByFilterWithoutFilter() {
        this.rackClient.removeRackByFilter(new RestParams(), null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRemoveRackByFilter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.removeRackByFilter(new RestParams(), ANY_RACK_FILTER, false);
    }

    @Test
    public void shouldRemoveRackByFilter() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        Map<String, String> query = new HashMap<>();
        query.put("filter", ANY_RACK_FILTER);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),ResourceUris.RACK_URI));
        expectedRestParams.setQuery(query);

        this.rackClient.removeRackByFilter(new RestParams(), ANY_RACK_FILTER, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetDeviceTopologyWithoutParams() {
        this.rackClient.getDeviceTopology(null, ANY_RACK_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetDeviceTopology() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.rackClient.getDeviceTopology(new RestParams(), ANY_RACK_RESOURCE_ID);
    }

    @Test
    public void shouldGetDeviceTopology() {
        String deviceTopologyValue = "{\"peakTemp\": 30, \"peakTempTime\": 1465432500000}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(deviceTopologyValue);
        given(adaptor.buildResourceObject(anyString(), eq(TopologyInformation.class)))
                .willReturn(new TopologyInformation());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.RACK_URI, ANY_RACK_RESOURCE_ID, ResourceUris.RACK_DEVICE_TOPOLOGY));

        this.rackClient.getDeviceTopology(new RestParams(), ANY_RACK_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(deviceTopologyValue), eq(TopologyInformation.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        RackClient localRackClient = spy(this.rackClient);

        doReturn(null).when(localRackClient).getRackByName(any(RestParams.class), anyString());

        //when
        String resourceId = localRackClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String rackId = "AF5FBE7D-271F-4F4F-B4B4-D0017970E590";
        String rackUri = "/rest/racks/" + rackId;

        //given
        RackClient localRackClient = spy(this.rackClient);
        Rack rack = new Rack();

        rack.setUri(rackUri);

        doReturn(rack).when(localRackClient).getRackByName(any(RestParams.class), anyString());

        //when
        String resourceId = localRackClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(rackId)));
    }
}
