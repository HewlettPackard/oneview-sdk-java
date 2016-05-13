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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.adaptors.UplinkSetAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class UplinkSetClientTest {

    private RestParams params;

    @Mock
    private UplinkSetAdaptor adaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitorManager;
    @Mock
    private HttpRestClient restClient;

    @InjectMocks
    private UplinkSetClientImpl client;

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private String uplinkSetJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }

    @Test
    public void testGetUplinkSet() {
        uplinkSetJson = this.getJsonFromFile("UplinkSetGet.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(uplinkSetJson);

        Mockito.when(adaptor.buildDto(Mockito.anyString(), Mockito.anyDouble()))
        .thenReturn(new UplinkSetAdaptor().buildDto(uplinkSetJson, 200));

        UplinkSets uplinkSetDto = client.getUplinkSet(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UPLINK_SETS_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(uplinkSetDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetUplinkSetWithNullParams() {
        client.getUplinkSet(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetUplinkSetWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getUplinkSet(params, resourceId);
    }

    @Test
    public void testGetAllUplinkSet() {
        uplinkSetJson = this.getJsonFromFile("UplinkSetGetAll.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(uplinkSetJson);

        Mockito.when(adaptor.buildCollectionDto(Mockito.anyString()))
        .thenReturn(new UplinkSetAdaptor().buildCollectionDto(uplinkSetJson));

        UplinkSetCollectionV2 upLinkSetCollection = client.getAllUplinkSet(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UPLINK_SETS_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(upLinkSetCollection);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                upLinkSetCollection.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllUplinkSetWithNullParams() {
        client.getAllUplinkSet(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllUplinkSetWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllUplinkSet(params);
    }

    @Test
    public void testDeleteUplinkSet() {
        String jsonDeleteTaskCompleted = this.getJsonFromFile("UplinkSetCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonDeleteTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonDeleteTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.deleteUplinkSet(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UPLINK_SETS_URI, resourceId));
        rp.setType(HttpMethodType.DELETE);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertEquals("A success delete uplink set call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteUplinkSetWithNullParams() {
        client.deleteUplinkSet(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testDeleteUplinkSetWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.deleteUplinkSet(params, resourceId, false);
    }

    @Test
    public void testUpdateUplinkSet() {
        uplinkSetJson = this.getJsonFromFile("UplinkSetGet.json");
        UplinkSets uplinkSetDto = new UplinkSetAdaptor().buildDto(uplinkSetJson);

        String jsonCreateTaskCompleted = this.getJsonFromFile("UplinkSetCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateUplinkSet(
                params,
                resourceId,
                uplinkSetDto,
                false,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UPLINK_SETS_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update uplink set call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateUplinkSetWithNullParams() {
        client.updateUplinkSet(null, resourceId, new UplinkSets(), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateUplinkSetWithNullDto() {
        client.updateUplinkSet(params, resourceId, null, false, false);
    }

    @Test
    public void testGetUplinkSetByName() {
        uplinkSetJson = this.getJsonFromFile("UplinkSetGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(uplinkSetJson);

        Mockito.when(adaptor.buildCollectionDto(Mockito.anyString(), Mockito.anyDouble()))
        .thenReturn(new UplinkSetAdaptor().buildCollectionDto(uplinkSetJson, 200));

        UplinkSets uplinkSetDto = client.getUplinkSetsByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.UPLINK_SETS_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(uplinkSetDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetUplinkSetByNameWithNullParams() {
        client.getUplinkSetsByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetUplinkSetsByNameWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getUplinkSetsByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetUplinkSetByNameWithNoMembers() {
        UplinkSetCollectionV2 uplinkSetCollection = new UplinkSetAdaptor().buildCollectionDto(this.getJsonFromFile("UplinkSetGetByName.json"));
        uplinkSetCollection.setCount(0);
        uplinkSetJson = new Gson().toJson(uplinkSetCollection);

        Mockito.when(adaptor.buildCollectionDto(Mockito.anyString(), Mockito.anyDouble()))
        .thenReturn(new UplinkSetAdaptor().buildCollectionDto(uplinkSetJson, 200));

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(uplinkSetJson);

        client.getUplinkSetsByName(params, resourceName);
    }

    @Test
    public void testCreateUplinkSet() {
        uplinkSetJson = this.getJsonFromFile("UplinkSetGet.json");
        UplinkSets uplinkSetDto = new UplinkSetAdaptor().buildDto(uplinkSetJson);

        String jsonCreateTaskCompleted = this.getJsonFromFile("UplinkSetCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.createUplinkSet(
                params,
                uplinkSetDto,
                false,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UPLINK_SETS_URI));
        rp.setType(HttpMethodType.POST);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success create uplink set call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateUplinkSetWithNullParams() {
        uplinkSetJson = this.getJsonFromFile("UplinkSetGet.json");
        UplinkSets uplinkSetDto = new UplinkSetAdaptor().buildDto(uplinkSetJson);
        client.createUplinkSet(null, uplinkSetDto, false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateUplinkSetWithNullDto() {
        client.createUplinkSet(params, null, false, false);
    }

    @Test
    public void testGetId() {
        uplinkSetJson = this.getJsonFromFile("UplinkSetGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(uplinkSetJson);

        Mockito.when(adaptor.buildCollectionDto(Mockito.anyString(), Mockito.anyDouble()))
        .thenReturn(new UplinkSetAdaptor().buildCollectionDto(uplinkSetJson, 200));

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.UPLINK_SETS_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(id);
        assertEquals("Based on the JSON file, the return ID must be \"" + resourceId + "\"", resourceId, id);
    }

    private String getJsonFromFile(String filename) {
        String json = "";
        try {
            json = IOUtils.toString(
                    this.getClass().getResourceAsStream(filename),
                    "UTF-8"
                  );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
