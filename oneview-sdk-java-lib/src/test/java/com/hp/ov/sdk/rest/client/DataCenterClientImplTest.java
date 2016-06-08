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

import java.lang.reflect.Type;
import java.util.ArrayList;
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
import com.hp.ov.sdk.dto.datacenter.DataCenter;
import com.hp.ov.sdk.dto.datacenter.VisualContent;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class DataCenterClientImplTest {

    private static final String ANY_DATA_CENTER_RESOURCE_ID = "random-UUID";
    private static final String ANY_DATA_CENTER_FILTER = "name='random-Name'";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private DataCenterClientImpl dataCenterClient;

    @Test
    public void shouldReturnDataCenterClient() {
        assertThat(DataCenterClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetDataCenterWithoutParams() {
        this.dataCenterClient.getDataCenter(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetDataCenter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.getDataCenter(new RestParams(), null);
    }

    @Test
    public void shouldGetDataCenter() {
        String dataCenterValue = "{\"category\":\"datacenters\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(dataCenterValue);
        given(adaptor.buildResourceObject(anyString(), eq(DataCenter.class))).willReturn(new DataCenter());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.DATA_CENTER_URI, ANY_DATA_CENTER_RESOURCE_ID));

        this.dataCenterClient.getDataCenter(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(dataCenterValue), eq(DataCenter.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllDataCentersWithoutParams() {
        this.dataCenterClient.getAllDataCenters(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllDataCenters() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.getAllDataCenters(new RestParams());
    }

    @Test
    public void shouldGetAllDataCenters() {
        String dataCenterCollectionValue = "{\"category\":\"datacenters\"," +
                "\"members\":[{\"category\":\"datacenters\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(dataCenterCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(DataCenter.class)))
                .willReturn(new ResourceCollection<DataCenter>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.DATA_CENTER_URI));

        this.dataCenterClient.getAllDataCenters(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(dataCenterCollectionValue), eq(DataCenter.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetDataCenterByNameWithoutParams() {
        String anyDataCenterName = "random-NAME";

        this.dataCenterClient.getDataCenterByName(null, anyDataCenterName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetDataCenterByName() {
        String anyDataCenterName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.getDataCenterByName(new RestParams(), anyDataCenterName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyDataCenterCollectionIsReturnedForTheGivenName() {
        String anyDataCenterName = "random-NAME";
        String dataCenterCollectionValue = "{\"category\":\"datacenters\"," +
                "\"members\": []}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(dataCenterCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(DataCenter.class)))
                .willReturn(new ResourceCollection<DataCenter>());

        this.dataCenterClient.getDataCenterByName(new RestParams(), anyDataCenterName);
    }

    @Test
    public void shouldGetDataCenterByName() {
        String anyDataCenterName = "random-NAME";
        String dataCenterCollectionValue = "{\"category\":\"datacenters\"," +
                "\"members\": [{\"category\":\"datacenters\"}]}";

        ResourceCollection<DataCenter> dataCenterCollection = new ResourceCollection<>();
        DataCenter dataCenter = new DataCenter();

        dataCenterCollection.setMembers(Lists.newArrayList(dataCenter));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(dataCenterCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(DataCenter.class)))
                .willReturn(dataCenterCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyDataCenterName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.DATA_CENTER_URI));

        DataCenter response = this.dataCenterClient.getDataCenterByName(new RestParams(), anyDataCenterName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(dataCenterCollectionValue, DataCenter.class);

        assertThat(response, is(sameInstance(dataCenter)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddDataCenterWithoutParams() {
        this.dataCenterClient.addDataCenter(null, new DataCenter());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddDataCenterWithoutRequest() {
        this.dataCenterClient.addDataCenter(new RestParams(), null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForAddDataCenter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.addDataCenter(new RestParams(), new DataCenter());
    }

    @Test
    public void shouldAddDataCenter() {
        String dataCenterValue = "{\"category\":\"datacenters\"}";
        JSONObject jsonRequest = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(dataCenterValue);
        given(adaptor.buildJsonRequest(any(DataCenter.class), any(ApiVersion.class))).willReturn(jsonRequest);
        given(adaptor.buildResourceObject(anyString(), eq(DataCenter.class))).willReturn(new DataCenter());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.DATA_CENTER_URI));

        this.dataCenterClient.addDataCenter(new RestParams(), new DataCenter());

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonRequest));
        then(adaptor).should().buildResourceObject(eq(dataCenterValue), eq(DataCenter.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateDataCenterWithoutParams() {
        this.dataCenterClient.updateDataCenter(null, ANY_DATA_CENTER_RESOURCE_ID, new DataCenter());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateDataCenterWithoutRequest() {
        this.dataCenterClient.updateDataCenter(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateDataCenter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.updateDataCenter(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID, new DataCenter());
    }

    @Test
    public void shouldUpdateDataCenter() {
        String dataCenterValue = "{\"category\":\"datacenters\"}";
        JSONObject jsonRequest = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(dataCenterValue);
        given(adaptor.buildJsonRequest(any(DataCenter.class), any(ApiVersion.class))).willReturn(jsonRequest);
        given(adaptor.buildResourceObject(anyString(), eq(DataCenter.class))).willReturn(new DataCenter());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.DATA_CENTER_URI, ANY_DATA_CENTER_RESOURCE_ID));

        this.dataCenterClient.updateDataCenter(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID, new DataCenter());

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonRequest));
        then(adaptor).should().buildResourceObject(eq(dataCenterValue), eq(DataCenter.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveDataCenterWithoutParams() {
        this.dataCenterClient.removeDataCenter(null, ANY_DATA_CENTER_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRemoveDataCenter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.removeDataCenter(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID);
    }

    @Test
    public void shouldRemoveDataCenter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("{}");

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.DATA_CENTER_URI, ANY_DATA_CENTER_RESOURCE_ID));

        String response = this.dataCenterClient.removeDataCenter(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));

        assertThat(response, is(equalTo("Removed")));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveDataCenterByFilterWithoutParams() {
        this.dataCenterClient.removeDataCenterByFilter(null, ANY_DATA_CENTER_FILTER, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveDataCenterByFilterWithoutFilter() {
        this.dataCenterClient.removeDataCenterByFilter(new RestParams(), null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRemoveDataCenterByFilter() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.removeDataCenterByFilter(new RestParams(), ANY_DATA_CENTER_FILTER, false);
    }

    @Test
    public void shouldRemoveDataCenterByFilter() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        Map<String, String> query = new HashMap<>();
        query.put("filter", ANY_DATA_CENTER_FILTER);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),ResourceUris.DATA_CENTER_URI));
        expectedRestParams.setQuery(query);

        this.dataCenterClient.removeDataCenterByFilter(new RestParams(), ANY_DATA_CENTER_FILTER, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetVisualContentWithoutParams() {
        this.dataCenterClient.getVisualContent(null, ANY_DATA_CENTER_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetVisualContent() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.dataCenterClient.getVisualContent(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID);
    }

    @Test
    public void shouldGetVisualContent() {
        String visualContentValue = "[{\"name\":\"Rack 221\"}, {\"name\":\"Rack 222\"}]";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(visualContentValue);
        given(adaptor.buildListOfResourceObject(anyString(), eq(VisualContent.class))).willReturn(new ArrayList<>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.DATA_CENTER_URI, ANY_DATA_CENTER_RESOURCE_ID, ResourceUris.DATA_CENTER_VISUAL_CONTENT_URI));

        this.dataCenterClient.getVisualContent(new RestParams(), ANY_DATA_CENTER_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildListOfResourceObject(eq(visualContentValue), any(Type.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        DataCenterClient localDataCenterClient = spy(this.dataCenterClient);

        doReturn(null).when(localDataCenterClient).getDataCenterByName(any(RestParams.class), anyString());

        //when
        String resourceId = localDataCenterClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String dataCenterId = "AF5FBE7D-271F-4F4F-B4B4-D0017970E590";
        String dataCenterUri = "/rest/datacenters/" + dataCenterId;

        //given
        DataCenterClient localDataCenterClient = spy(this.dataCenterClient);
        DataCenter dataCenter = new DataCenter();

        dataCenter.setUri(dataCenterUri);

        doReturn(dataCenter).when(localDataCenterClient).getDataCenterByName(any(RestParams.class), anyString());

        //when
        String resourceId = localDataCenterClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(dataCenterId)));
    }
}
