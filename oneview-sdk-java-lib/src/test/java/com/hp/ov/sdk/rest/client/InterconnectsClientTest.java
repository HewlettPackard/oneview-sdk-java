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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.hp.ov.sdk.adaptors.InterconnectAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.dto.generated.Port;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectsClientTest {

    private RestParams params;

    @Spy
    private ResourceAdaptor resourceAdaptor;
    @Spy
    private InterconnectAdaptor adaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitorManager;
    @Mock
    private HttpRestClient restClient;

    @InjectMocks
    private InterconnectsClientImpl client;

    private final String resourceId = "random-UUID";
    private final String resourceName = "random-name";

    private String intJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }

    @Test
    public void testGetInterconnectById() {
        intJson = this.getJsonFromFile("InterconnectGet.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        Interconnects interconnectDto = client.getInterconnectById(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(interconnectDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectByIdWithNullParams() {
        client.getInterconnectById(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetInterconnectByIdWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getInterconnectById(params, resourceId);
    }

    @Test
    public void testGetInterconnectByName() {
        intJson = this.getJsonFromFile("InterconnectGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        Interconnects interconnectDto = client.getInterconnectByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(interconnectDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectByNameWithNullParams() {
        client.getInterconnectByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetInterconnectByNameWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getInterconnectByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetInterconnectByNameWithNoMembers() {
        intJson = new Gson().toJson(new ResourceCollection<Interconnects>());

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        client.getInterconnectByName(params, resourceName);
    }

    @Test
    public void testGetAllInterconnects() {
        intJson = this.getJsonFromFile("InterconnectGetAll.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        ResourceCollection<Interconnects> interconnects = client.getAllInterconnects(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(interconnects);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                1, interconnects.getCount());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllInterconnectsWithNullParams() {
        client.getAllInterconnects(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllInterconnectsWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllInterconnects(params);
    }

    @Test
    public void testPatchInterconnect() {
        String jsonUpdateTaskCompleted = this.getJsonFromFile("InterconnectTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonUpdateTaskCompleted);

        // UPDATE
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONArray.class)))
        .thenReturn(jsonUpdateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        // TASK STATUS
        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        Patch patchDto = new Patch();
        patchDto.setOp(PatchOperation.replace);
        patchDto.setPath("/powerState");
        patchDto.setValue("Off");

        TaskResourceV2 result = client.patchInterconnect(params, resourceId, patchDto, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId));
        rp.setType(HttpMethodType.PATCH);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONArray.class));

        assertNotNull(result);
        assertEquals("A success patch interconnect port call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testPatchInterconnectWithNullParams() {
        client.patchInterconnect(null, resourceId, new Patch(), false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testPatchInterconnectWithNullDto() {
        client.patchInterconnect(params, resourceId, null, false);
    }

    @Test
    public void testUpdateInterconnectPort() {
        intJson = this.getJsonFromFile("InterconnectGet.json");

        String jsonUpdateTaskCompleted = this.getJsonFromFile("InterconnectTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonUpdateTaskCompleted);

        // GET
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        // UPDATE
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonUpdateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        // TASK STATUS
        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateInterconnectPort(params, resourceId, new Port(), false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.PORTS));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update interconnect port call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateInterconnectPortWithNullParams() {
        client.updateInterconnectPort(null, resourceId, new Port(), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateInterconnectPortWithNullDto() {
        client.updateInterconnectPort(params, resourceId, null, false, false);
    }

    @Test
    public void testResetInterconnectPortProtection() {
        intJson = this.getJsonFromFile("InterconnectGet.json");

        String jsonUpdateTaskCompleted = this.getJsonFromFile("InterconnectTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonUpdateTaskCompleted);

        // GET
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        // UPDATE
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonUpdateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        // TASK STATUS
        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.resetInterconnectPortProtection(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.RESET_PORT_PROTECTION));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertEquals("A success reset interconnect port call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testResetInterconnectPortProtectionWithNullParams() {
        client.resetInterconnectPortProtection(null, resourceId, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testResetInterconnectPortProtectionWithNullResourceId() {
        client.resetInterconnectPortProtection(params, null, false);
    }

    @Test
    public void testGetInterconnectStatistics() {
        intJson = this.getJsonFromFile("InterconnectGetStatistics.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        InterconnectsStatistics statisticsDto = client.getInterconnectStatistics(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.STATISTICS));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(statisticsDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectStatisticsWithNullParams() {
        client.getInterconnectStatistics(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetInterconnectStatisticsWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getInterconnectStatistics(params, resourceId);
    }

    @Test
    public void testGetInterconnectPortStatistics() {
        intJson = this.getJsonFromFile("InterconnectGetPortStatistics.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        PortStatistics statisticsDto = client.getInterconnectPortStatistics(params, resourceId, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.STATISTICS,
                resourceName));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(statisticsDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectPortStatisticsWithNullParams() {
        client.getInterconnectPortStatistics(null, resourceId, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetInterconnectPortStatisticsWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getInterconnectPortStatistics(params, resourceId, resourceName);
    }

    @Test
    public void testGetInterconnectSubportStatistics() {
        intJson = this.getJsonFromFile("InterconnectGetSubportStatistics.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        SubportStatistics statisticsDto = client.getInterconnectSubportStatistics(params, resourceId, resourceName, 1);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.STATISTICS,
                resourceName,
                SdkConstants.SUBPORT,
                1));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(statisticsDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectSubportStatisticsWithNullParams() {
        client.getInterconnectSubportStatistics(null, resourceId, resourceName, 1);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetInterconnectSubportStatisticsWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getInterconnectSubportStatistics(params, resourceId, resourceName, 1);
    }

    @Test
    public void testUpdateInterconnectPorts() {
        intJson = this.getJsonFromFile("InterconnectGet.json");

        String jsonUpdateTaskCompleted = this.getJsonFromFile("InterconnectTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonUpdateTaskCompleted);

        // GET
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        // UPDATE
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONArray.class)))
        .thenReturn(jsonUpdateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        // TASK STATUS
        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateInterconnectPorts(params, resourceId, Arrays.asList(new Port()), false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.UPDATE_PORTS));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONArray.class));

        assertEquals("A success update interconnect ports call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateInterconnectPortsWithNullParams() {
        client.updateInterconnectPorts(null, resourceId, Arrays.asList(new Port()), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateInterconnectPortsWithNullDto() {
        client.updateInterconnectPorts(params, resourceId, null, false, false);
    }

    @Test
    public void testGetInterconnectNamedServers() {
        intJson = this.getJsonFromFile("InterconnectGetNamedServers.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        List<NameServer> nameServersDto = client.getInterconnectNamedServers(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.NAME_SERVERS));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(nameServersDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectNamedServersWithNullParams() {
        client.getInterconnectNamedServers(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetInterconnectNamedServersWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getInterconnectNamedServers(params, resourceId);
    }

    @Test
    public void testGetId() {
        intJson = this.getJsonFromFile("InterconnectGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(intJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI));
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
