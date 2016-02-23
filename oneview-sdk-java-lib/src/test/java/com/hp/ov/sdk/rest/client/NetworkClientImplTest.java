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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.BDDMockito.anyObject;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.doReturn;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.spy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import java.io.IOException;
import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.NetworkAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.JsonRequest;
import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Bandwidth;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class NetworkClientImplTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    private static String ethernetNetwork;
    private static String ethernetNetworkCollection;

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ConnectionTemplateClient connectionTemplateClient;
    @Mock
    private NetworkAdaptor adaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private NetworkClientImpl networkClient;

    @BeforeClass
    public static void setupTest() throws IOException {
        Class<NetworkClientImplTest> clazz = NetworkClientImplTest.class;

        ethernetNetwork = IOUtils.toString(clazz.getResourceAsStream("EthernetNetworkResponse.json"), "UTF-8");
        ethernetNetworkCollection = IOUtils.toString(
                clazz.getResourceAsStream("EthernetNetworkCollectionResponse.json"), "UTF-8");
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetEthernetNetworkWithoutParams() {
        this.networkClient.getNetwork(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetEthernetNetwork() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(null);

        this.networkClient.getNetwork(new RestParams(), null);
    }

    @Test
    public void shouldGetEthernetNetwork() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(ethernetNetwork);
        given(adaptor.buildDto(anyString())).willReturn(new Network());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.ETHERNET_URI, ANY_RESOURCE_ID));

        this.networkClient.getNetwork(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildDto(ethernetNetwork);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllEthernetNetworksWithoutParams() {
        this.networkClient.getAllNetworks(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllEthernetNetworks() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(null);

        this.networkClient.getAllNetworks(new RestParams());
    }

    @Test
    public void shouldGetAllEthernetNetworks() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(ethernetNetworkCollection);
        given(adaptor.buildCollectionDto(anyString())).willReturn(new NetworkCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.ETHERNET_URI));

        this.networkClient.getAllNetworks(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildCollectionDto(ethernetNetworkCollection);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetEthernetNetworkByNameWithoutParams() {
        this.networkClient.getNetworkByName(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetEthernetNetworkByName() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(null);

        this.networkClient.getNetworkByName(new RestParams(), null);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoEthernetNetworkIsFoundForTheGivenName() {
        String anyName = "random-NAME";

        NetworkCollection networkCollection = new NetworkCollection();
        networkCollection.setCount(0);

        given(restClient.sendRequest(any(RestParams.class))).willReturn(ethernetNetworkCollection);
        given(adaptor.buildCollectionDto(anyObject())).willReturn(networkCollection);

        this.networkClient.getNetworkByName(new RestParams(), anyName);
    }

    @Test
    public void shouldGetEthernetNetworkByName() {
        String anyName = "random-NAME";
        NetworkCollection networkCollection = new NetworkCollection();
        networkCollection.setCount(1);

        networkCollection.setMembers(Lists.newArrayList(new Network()));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(ethernetNetworkCollection);
        given(adaptor.buildCollectionDto(anyString())).willReturn(networkCollection);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestQueryUrl(expectedRestParams.getHostname(),
                ResourceUris.ETHERNET_URI, UrlUtils.createFilterString(anyName)));

        this.networkClient.getNetworkByName(new RestParams(), anyName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildCollectionDto(ethernetNetworkCollection);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateEthernetNetworkWithoutParams() {
        this.networkClient.createNetwork(null, new Network(), false, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateEthernetNetworkWithoutRequest() {
        this.networkClient.createNetwork(new RestParams(), null, false, false);
    }

    @Test
    public void shouldSynchronousCreateEthernetNetworkWithoutConnectionTemplate() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(adaptor.buildJsonObjectFromDto(any(Network.class), any(Integer.class))).willReturn(jsonObject);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.ETHERNET_URI));

        this.networkClient.createNetwork(new RestParams(), new Network(), false, false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test
    public void shouldAsynchronousCreateEthernetNetworkWithoutConnectionTemplate() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(adaptor.buildJsonObjectFromDto(any(Network.class), any(Integer.class))).willReturn(jsonObject);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.ETHERNET_URI));

        this.networkClient.createNetwork(new RestParams(), new Network(), true, false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test
    public void shouldSynchronousCreateEthernetNetworkWithConnectionTemplate() {
        //given
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        String networkName = "random-NAME";
        String connectionTemplateId = "bfdf881b-be7b-4b5e-ae23-3fdcbd0019e5";
        JSONObject jsonObject = new JSONObject();

        Network network = new Network();
        ConnectionTemplate connectionTemplate = new ConnectionTemplate();

        connectionTemplate.setBandwidth(new Bandwidth());
        network.setConnectionTemplate(connectionTemplate);
        network.setConnectionTemplateUri("/rest/connection-templates/" + connectionTemplateId);
        network.setName(networkName);

        ConnectionTemplate updateConnectionTemplate = new ConnectionTemplate();

        NetworkClient spyNetworkClient = spy(this.networkClient);

        doReturn(network).when(spyNetworkClient).getNetworkByName(any(RestParams.class), eq(networkName));

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(adaptor.buildJsonObjectFromDto(any(Network.class), any(Integer.class))).willReturn(jsonObject);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());
        given(connectionTemplateClient.getConnectionTemplate(any(RestParams.class), any(String.class)))
                .willReturn(updateConnectionTemplate);
        given(connectionTemplateClient.updateConnectionTemplate(any(RestParams.class), any(String.class),
                any(ConnectionTemplate.class), eq(false))).willReturn(new ConnectionTemplate());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.ETHERNET_URI));

        //when
        spyNetworkClient.createNetwork(new RestParams(), network, false, false);

        //then
        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
        then(connectionTemplateClient).should().updateConnectionTemplate(expectedRestParams,
                connectionTemplateId, updateConnectionTemplate, false);
    }

    @Test
    public void shouldAsynchronousCreateEthernetNetworkUsingJsonRequest() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        Network network = new Network();

        network.setJsonRequest(new JsonRequest());

        TaskResourceV2 expectedTask = new TaskResourceV2();

        given(adaptor.buildDto(any(String.class))).willReturn(network);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(adaptor.buildJsonObjectFromDto(any(Network.class), any(Integer.class))).willReturn(jsonObject);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(expectedTask);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.ETHERNET_URI));

        TaskResourceV2 resultTask = this.networkClient.createNetwork(new RestParams(), network, true, true);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);

        assertThat(resultTask, sameInstance(expectedTask));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateEthernetNetworkWithoutParams() {
        this.networkClient.updateNetwork(null, null, new Network(), false, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateEthernetNetworkWithoutRequest() {
        this.networkClient.updateNetwork(new RestParams(), null, null, false, false);
    }

    @Test
    public void shouldUpdateEthernetNetwork() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(adaptor.buildJsonObjectFromDto(any(Network.class), any(Integer.class))).willReturn(jsonObject);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.ETHERNET_URI, ANY_RESOURCE_ID));

        this.networkClient.updateNetwork(new RestParams(), ANY_RESOURCE_ID, new Network(), false, false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteEthernetNetworkWithoutParams() {
        this.networkClient.deleteNetwork(null, ANY_RESOURCE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteEthernetNetwork() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(null);

        this.networkClient.deleteNetwork(new RestParams(), null, false);
    }

    @Test
    public void shouldDeleteEthernetNetwork() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskAsJson);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.ETHERNET_URI, ANY_RESOURCE_ID));

        this.networkClient.deleteNetwork(new RestParams(), ANY_RESOURCE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetEthernetNetworkAssociatedProfilesWithoutParams() {
        this.networkClient.getNetworkAssociatedProfiles(null, ANY_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetEthernetNetworkAssociatedProfiles() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(null);

        this.networkClient.getNetworkAssociatedProfiles(new RestParams(), ANY_RESOURCE_ID);
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedProfiles() {
        String profiles = "[\n" +
                "  \"/rest/server-profiles/96f5ce3a-6de6-4d09-ab5c-f74cdb40e04c\",\n" +
                "  \"/rest/server-profiles/49b0f955-e791-4910-808b-387f9a3badcb\"\n" +
                "]";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(profiles);
        given(adaptor.buildCollectionOfUris(any(String.class))).willReturn(new ArrayList<String>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.ETHERNET_URI, ANY_RESOURCE_ID, ResourceUris.ASSOCIATED_PROFILES));

        this.networkClient.getNetworkAssociatedProfiles(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildCollectionOfUris(profiles);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetEthernetNetworkAssociatedUplinkGroupsWithoutParams() {
        this.networkClient.getNetworkAssociatedUplinkGroups(null, ANY_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetEthernetNetworkAssociatedUplinkGroups() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn(null);

        this.networkClient.getNetworkAssociatedUplinkGroups(new RestParams(), null);
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedUplinkGroups() {
        String uplinkGroups = "[\n" +
                "  \"/rest/uplink-sets/da4e8f0b-e439-4b00-9aee-341ea4143f06\",\n" +
                "  \"/rest/uplink-sets/4d0f4e81-1285-4dfe-8552-f12401b7dd28\"\n" +
                "]";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(uplinkGroups);
        given(adaptor.buildCollectionOfUris(any(String.class))).willReturn(new ArrayList<String>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.ETHERNET_URI, ANY_RESOURCE_ID, ResourceUris.ASSOCIATED_UPLINK_GROUPS));

        this.networkClient.getNetworkAssociatedUplinkGroups(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildCollectionOfUris(uplinkGroups);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateEthernetNetworkInBulkWithoutParams() {
        this.networkClient.createNetworkInBulk(null, new BulkEthernetNetwork(), false, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateEthernetNetworkInBulkWithoutRequest() {
        this.networkClient.createNetworkInBulk(new RestParams(), null, false, false);
    }

    @Test
    public void shouldCreateEthernetNetworkInBulk() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(adaptor.buildJsonObjectFromBulkEthernetDto(any(BulkEthernetNetwork.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.BULK_ETHERNET_URI));

        this.networkClient.createNetworkInBulk(new RestParams(), new BulkEthernetNetwork(), false, false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test
    public void shouldReturnEmptyStringForGetId() throws IOException {
        //given
        NetworkClient localNetworkClient = spy(this.networkClient);
        Network network = new Network();

        doReturn(network).when(localNetworkClient).getNetworkByName(any(RestParams.class), anyString());

        //when
        String resourceId = localNetworkClient.getId(new RestParams(), "");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String expectedResourceId = "c1067255-210d-454a-b199-c1faa98e0c97";
        String networkUri = "rest/ethernet-networks/c1067255-210d-454a-b199-c1faa98e0c97";

        //given
        NetworkClient localNetworkClient = spy(this.networkClient);
        Network network = new Network();

        network.setUri(networkUri);

        doReturn(network).when(localNetworkClient).getNetworkByName(any(RestParams.class), anyString());

        //when
        String resourceId = localNetworkClient.getId(new RestParams(), null);

        //then
        assertThat(resourceId, is(equalTo(expectedResourceId)));
    }

}