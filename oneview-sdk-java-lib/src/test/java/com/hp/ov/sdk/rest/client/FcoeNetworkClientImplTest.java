/*******************************************************************************
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
 *******************************************************************************/
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
import com.hp.ov.sdk.dto.FcoeNetwork;
import com.hp.ov.sdk.dto.HttpMethodType;
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
public class FcoeNetworkClientImplTest {

    private static final String ANY_FCOE_RESOURCE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private FcoeNetworkClientImpl fcoeClient;

    @Test
    public void shouldReturnFcoeNetworkClient() {
        assertThat(FcoeNetworkClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetFcoeNetworkWithoutParams() {
        this.fcoeClient.getFcoeNetwork(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetFcoeNetwork() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.fcoeClient.getFcoeNetwork(new RestParams(), null);
    }

    @Test
    public void shouldGetFcoeNetwork() {
        String storageVolumeAttachmentValue = "{\"type\":\"fcoe-network\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeAttachmentValue);
        given(adaptor.buildResourceObject(anyString(), eq(FcoeNetwork.class))).willReturn(new FcoeNetwork());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FCOE_NETWORK_URI, ANY_FCOE_RESOURCE_ID));

        this.fcoeClient.getFcoeNetwork(new RestParams(), ANY_FCOE_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(storageVolumeAttachmentValue), eq(FcoeNetwork.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllFcoeNetworksWithoutParams() {
        this.fcoeClient.getAllFcoeNetworks(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllFcoeNetworks() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.fcoeClient.getAllFcoeNetworks(new RestParams());
    }

    @Test
    public void shouldGetAllFcoeNetworks() {
        String fcoeNetworkCollectionValue = "{\"type\":\"FcoeNetworkCollection\"," +
                "\"members\":[{\"type\":\"fcoe-network\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(fcoeNetworkCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(FcoeNetwork.class)))
                .willReturn(new ResourceCollection<FcoeNetwork>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FCOE_NETWORK_URI));

        this.fcoeClient.getAllFcoeNetworks(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(fcoeNetworkCollectionValue), eq(FcoeNetwork.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetFcoeNetworkByNameWithoutParams() {
        String anyFcoeNetworkName = "random-NAME";

        this.fcoeClient.getFcoeNetworkByName(null, anyFcoeNetworkName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetFcoeNetworkByName() {
        String anyFcoeNetworkName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.fcoeClient.getFcoeNetworkByName(new RestParams(), anyFcoeNetworkName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyFcoeNetworkCollectionIsReturnedForTheGivenName() {
        String anyFcoeNetworkName = "random-NAME";
        String fcoeNetworkCollectionValue = "{\"type\":\"FcoeNetworkCollection\"," +
                "\"members\": []}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(fcoeNetworkCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(FcoeNetwork.class)))
                .willReturn(new ResourceCollection<FcoeNetwork>());

        this.fcoeClient.getFcoeNetworkByName(new RestParams(), anyFcoeNetworkName);
    }

    @Test
    public void shouldGetFcoeNetworkByName() {
        String anyFcoeNetworkName = "random-NAME";
        String fcoeNetworkCollectionValue = "{\"type\":\"FcoeNetworkCollection\"," +
                "\"members\": [{\"type\":\"fcoe-network\"}]}";

        ResourceCollection<FcoeNetwork> fcoeNetworkCollection = new ResourceCollection<>();
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        fcoeNetworkCollection.setMembers(Lists.newArrayList(fcoeNetwork));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(fcoeNetworkCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(FcoeNetwork.class)))
                .willReturn(fcoeNetworkCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyFcoeNetworkName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FCOE_NETWORK_URI));

        FcoeNetwork response = this.fcoeClient.getFcoeNetworkByName(new RestParams(), anyFcoeNetworkName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(fcoeNetworkCollectionValue, FcoeNetwork.class);

        assertThat(response, is(sameInstance(fcoeNetwork)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateFcoeNetworkWithoutParams() {
        this.fcoeClient.createFcoeNetwork(null, new FcoeNetwork(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateFcoeNetworkWithoutRequest() {
        this.fcoeClient.createFcoeNetwork(new RestParams(), null, false);
    }

    @Test
    public void shouldCreateFcoeNetwork() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        FcoeNetwork fcoeNetwork = new FcoeNetwork();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(fcoeNetwork), any(Double.class)))
                .willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject)))
                .willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FCOE_NETWORK_URI));

        TaskResourceV2 task = this.fcoeClient.createFcoeNetwork(new RestParams(), fcoeNetwork, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);

        assertThat(task, is(sameInstance(taskResource)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateFcoeNetworkWithoutParams() {
        this.fcoeClient.updateFcoeNetwork(null, ANY_FCOE_RESOURCE_ID, new FcoeNetwork(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateFcoeNetworkWithoutRequest() {
        this.fcoeClient.updateFcoeNetwork(new RestParams(), ANY_FCOE_RESOURCE_ID, null, false);
    }

    @Test
    public void shouldUpdateFcoeNetwork() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        FcoeNetwork fcoeNetwork = new FcoeNetwork();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(fcoeNetwork), any(Double.class)))
                .willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject)))
                .willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FCOE_NETWORK_URI, ANY_FCOE_RESOURCE_ID));

        TaskResourceV2 task = this.fcoeClient.updateFcoeNetwork(new RestParams(),
                ANY_FCOE_RESOURCE_ID, fcoeNetwork, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);

        assertThat(task, is(sameInstance(taskResource)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteFcoeNetworkWithoutParams() {
        this.fcoeClient.deleteFcoeNetwork(null, ANY_FCOE_RESOURCE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteFcoeNetwork() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.fcoeClient.deleteFcoeNetwork(new RestParams(), ANY_FCOE_RESOURCE_ID, false);
    }

    @Test
    public void shouldDeleteFcoeNetwork() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FCOE_NETWORK_URI, ANY_FCOE_RESOURCE_ID));

        this.fcoeClient.deleteFcoeNetwork(new RestParams(), ANY_FCOE_RESOURCE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        FcoeNetworkClient localFcoeClient = spy(this.fcoeClient);

        doReturn(null).when(localFcoeClient).getFcoeNetworkByName(any(RestParams.class), anyString());

        //when
        String resourceId = localFcoeClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String fcoeNetworkId = "AF5FBE7D-271F-4F4F-B4B4-D0017970E590";
        String fcoeNetworkUri = "/rest/fcoe-networks/" + fcoeNetworkId;

        //given
        FcoeNetworkClient localFcoeClient = spy(this.fcoeClient);
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        fcoeNetwork.setUri(fcoeNetworkUri);

        doReturn(fcoeNetwork).when(localFcoeClient).getFcoeNetworkByName(any(RestParams.class), anyString());

        //when
        String resourceId = localFcoeClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(fcoeNetworkId)));
    }
}
