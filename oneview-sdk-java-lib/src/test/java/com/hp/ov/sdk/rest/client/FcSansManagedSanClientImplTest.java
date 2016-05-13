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
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.ManagedSanAdaptor;
import com.hp.ov.sdk.adaptors.ManagedSanEndpointAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.EndpointResponseCollection;
import com.hp.ov.sdk.dto.EndpointsCsvFileResponse;
import com.hp.ov.sdk.dto.FcSansManagedSanTask;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.SanRequest;
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.SanResponseCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class FcSansManagedSanClientImplTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    private static String managedSan;
    private static String managedSanList;
    private static String managedSanEndpoints;

    @Mock
    private HttpRestClient client;
    @Mock
    private ManagedSanAdaptor sanAdaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager monitorManager;
    @Mock
    private ManagedSanEndpointAdaptor endpointAdaptor;
    @InjectMocks
    private FcSansManagedSanClientImpl sanClient;

    @BeforeClass
    public static void setupTest() throws IOException {
        Class<FcSansManagedSanClientImplTest> clazz = FcSansManagedSanClientImplTest.class;

        managedSan = IOUtils.toString(clazz.getResourceAsStream("ManagedSanResponse.json"), "UTF-8");
        managedSanList = IOUtils.toString(clazz.getResourceAsStream("ManagedSanListResponse.json"), "UTF-8");
        managedSanEndpoints = IOUtils.toString(clazz.getResourceAsStream("ManagedSanEndpointsResponse.json"), "UTF-8");
    }

    @Test
    public void shouldReturnClientInstance() {
        assertThat(FcSansManagedSanClientImpl.getClient(), is(not(nullValue())));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetManagedSanWithoutParams() {
        this.sanClient.getManagedSan(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetManagedSan() {
        given(client.sendRequest(any(RestParams.class))).willReturn("");

        this.sanClient.getManagedSan(new RestParams(), null);
    }

    @Test
    public void shouldGetManagedSan() throws IOException {
        given(client.sendRequest(any(RestParams.class))).willReturn(managedSan);
        given(sanAdaptor.buildDto(anyString())).willReturn(new SanResponse());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, ANY_RESOURCE_ID));

        this.sanClient.getManagedSan(new RestParams(), ANY_RESOURCE_ID);

        then(client).should().sendRequest(eq(expectedRestParams));
        then(sanAdaptor).should().buildDto(managedSan);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllManagedSanWithoutParams() {
        this.sanClient.getAllManagedSan(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllManagedSan() {
        given(client.sendRequest(any(RestParams.class))).willReturn("");

        this.sanClient.getAllManagedSan(new RestParams());
    }

    @Test
    public void shouldGetAllManagedSan() {
        given(client.sendRequest(any(RestParams.class))).willReturn(managedSanList);
        given(sanAdaptor.buildCollectionDto(anyString())).willReturn(new SanResponseCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI));

        this.sanClient.getAllManagedSan(new RestParams());

        then(client).should().sendRequest(eq(expectedRestParams));
        then(sanAdaptor).should().buildCollectionDto(managedSanList);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetManagedSanByNameWithoutParams() {
        this.sanClient.getManagedSanByName(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetManagedSanByName() {
        String anyName = "random-NAME";

        given(client.sendRequest(any(RestParams.class))).willReturn("");

        this.sanClient.getManagedSanByName(new RestParams(), anyName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoSanManagerIsFoundForTheGivenName() {
        String anyName = "random-NAME";

        SanResponseCollection sanResponseCollection = new SanResponseCollection();
        sanResponseCollection.setCount(0);

        given(client.sendRequest(any(RestParams.class))).willReturn(managedSanList);
        given(sanAdaptor.buildCollectionDto(anyObject())).willReturn(sanResponseCollection);

        this.sanClient.getManagedSanByName(new RestParams(), anyName);
    }

    @Test
    public void shouldGetSanManagerByName() {
        String anyName = "random-NAME";

        SanResponseCollection sanResponseCollection = new SanResponseCollection();
        sanResponseCollection.setCount(1);
        sanResponseCollection.setMembers(Lists.newArrayList(new SanResponse()));

        given(client.sendRequest(any(RestParams.class))).willReturn(managedSanList);
        given(sanAdaptor.buildCollectionDto(anyObject())).willReturn(sanResponseCollection);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setUrl(UrlUtils.createRestUrl(
                expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI));

        this.sanClient.getManagedSanByName(new RestParams(), anyName);

        then(client).should().sendRequest(eq(expectedRestParams));
        then(sanAdaptor).should().buildCollectionDto(managedSanList);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateManagedSanWithoutParams() {
        this.sanClient.updateManagedSan(null, null, null, false, false);
    }

    @Test
    public void shouldUpdateManagedSan() {
        JSONObject jsonObject = new JSONObject();

        given(sanAdaptor.buildJsonObjectFromDto(any(SanRequest.class), any(Integer.class))).willReturn(jsonObject);
        given(sanAdaptor.buildDto(any(String.class))).willReturn(new SanResponse());
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(managedSan);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, ANY_RESOURCE_ID));

        this.sanClient.updateManagedSan(new RestParams(), ANY_RESOURCE_ID, new SanRequest(), false, false);

        then(client).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(sanAdaptor).should().buildDto(managedSan);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetEndpointsOfManagedSanWithoutParams() {
        this.sanClient.getEndpointsOfManagedSan(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetEndpointsOfManagedSan() {
        String anyName = "random-NAME";

        given(client.sendRequest(any(RestParams.class))).willReturn("");

        this.sanClient.getEndpointsOfManagedSan(new RestParams(), anyName);
    }

    @Test
    public void shouldGetEndpointsOfManagedSan() {
        given(client.sendRequest(any(RestParams.class))).willReturn(managedSanEndpoints);
        given(endpointAdaptor.buildCollectionDto(anyString())).willReturn(new EndpointResponseCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, ANY_RESOURCE_ID, ResourceUris.FC_SANS_MANAGED_SAN_ENDPOINTS));

        this.sanClient.getEndpointsOfManagedSan(new RestParams(), ANY_RESOURCE_ID);

        then(client).should().sendRequest(eq(expectedRestParams));
        then(endpointAdaptor).should().buildCollectionDto(managedSanEndpoints);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateSanIssuesReportWithoutParams() {
        this.sanClient.createManagedSanIssuesReport(null, null, true);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForCreateSanIssuesReport() {
        String anyName = "random-NAME";

        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn("");

        this.sanClient.createManagedSanIssuesReport(new RestParams(), anyName, true);
    }

    @Test
    public void shouldCreateSanIssuesReportAsync() {
        String taskResponse = "{" +
                "\"type\":\"TaskResourceV2\"," +
                "\"name\":\"Create unexpected zoning report\"," +
                "\"uri\":\"/rest/tasks/C9A5F1BC-4B1C-4108-A597-FBA1ACDCCDEC\"}";
        TaskResourceV2 task = new TaskResourceV2();

        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskResponse);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(task);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, ANY_RESOURCE_ID, ResourceUris.FC_SANS_MANAGED_SAN_ISSUES));

        FcSansManagedSanTask sanTask = this.sanClient.createManagedSanIssuesReport(new RestParams(), ANY_RESOURCE_ID, true);

        then(client).should().sendRequest(eq(expectedRestParams), any(JSONObject.class));
        then(taskAdaptor).should().buildDto(taskResponse);

        assertThat(sanTask.getTask(), is(equalTo(task)));
    }
    @Test
    public void shouldCreateSanIssuesReportSync() {
        String taskResponse = "{" +
                "\"type\":\"TaskResourceV2\"," +
                "\"name\":\"Create unexpected zoning report\"," +
                "\"uri\":\"/rest/tasks/C9A5F1BC-4B1C-4108-A597-FBA1ACDCCDEC\"}";
        TaskResourceV2 task = new TaskResourceV2();

        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskResponse);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(task);
        given(monitorManager.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, ANY_RESOURCE_ID, ResourceUris.FC_SANS_MANAGED_SAN_ISSUES));

        FcSansManagedSanTask sanTask = this.sanClient.createManagedSanIssuesReport(new RestParams(), ANY_RESOURCE_ID, false);

        then(client).should().sendRequest(eq(expectedRestParams), any(JSONObject.class));
        then(taskAdaptor).should().buildDto(taskResponse);

        assertThat(sanTask.getTask(), not(sameInstance(task)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateEndpointsCsvOfManagedSanWithoutParams() {
        this.sanClient.createEndpointsCsvOfManagedSan(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForCreateEndpointsCsvOfManagedSan() {
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn("");

        this.sanClient.createEndpointsCsvOfManagedSan(new RestParams(), ANY_RESOURCE_ID);
    }

    @Test
    public void shouldCreateEndpointsCsvOfManagedSan() {
        String endpointsCsvResponse = "{" +
                "\"uri\":\"/rest/fc-sans/managed-sans/e9d79564-1244-4f0e-83a3-215dcb1f6b1f/endpoints/ci-005056a63274-172.18.15.1-SAN1_0-endpoints-2016_02_22_01_37_02.csv.gz\"," +
                "\"type\":\"FCEndpointCsvFile\"," +
                "\"category\":\"fc-endpoints\"," +
                "\"created\":\"2016-02-22T13:37:02.916Z\"," +
                "\"csvFileName\":\"ci-005056a63274-172.18.15.1-SAN1_0-endpoints-2016_02_22_01_37_02.csv.gz\"," +
                "\"eTag\":\"2016_02_22_01_37_02\"," +
                "\"modified\":\"2016-02-22T13:37:02.916Z\"}";

        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(endpointsCsvResponse);
        given(endpointAdaptor.buildEndpointCsvFileResponse(anyString())).willReturn(new EndpointsCsvFileResponse());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, ANY_RESOURCE_ID, ResourceUris.FC_SANS_MANAGED_SAN_ENDPOINTS));

        this.sanClient.createEndpointsCsvOfManagedSan(new RestParams(), ANY_RESOURCE_ID);

        then(client).should().sendRequest(eq(expectedRestParams), any(JSONObject.class));
        then(endpointAdaptor).should().buildEndpointCsvFileResponse(endpointsCsvResponse);
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        String anyName = "random-NAME";
        FcSansManagedSanClient localClient = spy(this.sanClient);
        SanResponse sanResponse = new SanResponse();

        doReturn(sanResponse).when(localClient).getManagedSanByName(any(RestParams.class), anyString());

        //when
        String resourceId = localClient.getId(new RestParams(), anyName);

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String expectedResourceId = "e9d79564-1244-4f0e-83a3-215dcb1f6b1f";
        String managedSanUri = "/rest/fc-sans/managed-sans/e9d79564-1244-4f0e-83a3-215dcb1f6b1f";

        //given
        String anyName = "random-NAME";
        FcSansManagedSanClient localClient = spy(this.sanClient);
        SanResponse sanResponse = new SanResponse();

        sanResponse.setUri(managedSanUri);

        doReturn(sanResponse).when(localClient).getManagedSanByName(any(RestParams.class), anyString());

        //when
        String resourceId = localClient.getId(new RestParams(), anyName);

        //then
        assertThat(resourceId, is(equalTo(expectedResourceId)));
    }

}