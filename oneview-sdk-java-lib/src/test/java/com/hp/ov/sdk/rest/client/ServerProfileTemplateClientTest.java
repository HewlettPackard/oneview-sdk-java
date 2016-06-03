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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.ServerProfileTemplateAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class ServerProfileTemplateClientTest {

    private RestParams params;

    @Spy
    private ResourceAdaptor resourceAdaptor;
    @Spy
    private ServerProfileTemplateAdaptor adaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitorManager;
    @Mock
    private HttpRestClient restClient;

    @InjectMocks
    private ServerProfileTemplateClientImpl client;

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private String spJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }

    @Test
    public void testGetServerProfileTemplateById() {
        spJson = this.getJsonFromFile("ServerProfileTemplateGet.json");
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn(spJson);

        ServerProfileTemplate serverProfileTemplateDto = client.getServerProfileTemplateById(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(serverProfileTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetServerProfileTemplateByIdWithNullParams() {
        client.getServerProfileTemplateById(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetServerProfileTemplateByIdWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getServerProfileTemplateById(params, resourceId);
    }

    @Test
    public void testGetAllServerProfileTemplate() {
        spJson = this.getJsonFromFile("ServerProfileTemplateGetAll.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(spJson);

        ResourceCollection<ServerProfileTemplate> serverProfileTemplateList = client.getAllServerProfileTemplates(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(serverProfileTemplateList);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                serverProfileTemplateList.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllServerProfileTemplateWithNullParams() {
        client.getAllServerProfileTemplates(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllServerProfileTemplateWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllServerProfileTemplates(params);
    }

    @Test
    public void testGetServerProfileTemplateByName() {
        spJson = this.getJsonFromFile("ServerProfileTemplateGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(spJson);

        ServerProfileTemplate serverProfileTemplateDto = client.getServerProfileTemplateByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(serverProfileTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetServerProfileTemplateByNameWithNullParams() {
        client.getServerProfileTemplateByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetServerProfileTemplateByNameWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getServerProfileTemplateByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetServerProfileTemplateByNameWithNoMembers() {
        spJson = new Gson().toJson(new ResourceCollection<ServerProfileTemplate>());

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(spJson);

        client.getServerProfileTemplateByName(params, resourceName);
    }

    @Test
    public void testGetNewServerProfile() {
        spJson = this.getJsonFromFile("ServerProfileTemplateGet.json");
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn(spJson);

        ServerProfile serverProfileDto = client.getNewServerProfile(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI,
                resourceId,
                SdkConstants.SERVER_PROFILE_TEMPLATE_NEW_PROFILE));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(serverProfileDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetNewServerProfileWithNullParams() {
        client.getNewServerProfile(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetNewServerProfileWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getNewServerProfile(params, resourceId);
    }

    @Test
    public void testCreateServerProfileTemplate() {
        spJson = this.getJsonFromFile("ServerProfileTemplateGet.json");
        ServerProfileTemplate serverProfileTemplateDto = new ServerProfileTemplateAdaptor().buildDto(spJson);

        String jsonCreateTaskCompleted = this.getJsonFromFile("ServerProfileTemplateTaskCompleted.json");
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

        TaskResourceV2 result = client.createServerProfileTemplate(
                params,
                serverProfileTemplateDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI));
        rp.setType(HttpMethodType.POST);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success create server profile template call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateServerProfileTemplateWithNullParams() {
        client.createServerProfileTemplate(
                null,
                new ServerProfileTemplate(),
                false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateServerProfileTemplateWithNullDto() {
        client.createServerProfileTemplate(
                params,
                null,
                false);
    }

    @Test
    public void testUpdateServerProfileTemplate() {
        spJson = this.getJsonFromFile("ServerProfileTemplateGet.json");
        ServerProfileTemplate serverProfileTemplateDto = new ServerProfileTemplateAdaptor().buildDto(spJson);

        String jsonUpdateTaskCompleted = this.getJsonFromFile("ServerProfileTemplateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonUpdateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonUpdateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateServerProfileTemplate(
                params,
                resourceId,
                serverProfileTemplateDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update server profile call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateServerProfileTemplateWithNullParams() {
        client.updateServerProfileTemplate( null, resourceId, new ServerProfileTemplate(), false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateServerProfileTemplateWithNullDto() {
        client.updateServerProfileTemplate( params, resourceId, null, false);
    }

    @Test
    public void testDeleteServerProfileTemplate() {
        String jsonDeleteTaskCompleted = this.getJsonFromFile("ServerProfileTemplateTaskCompleted.json");
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

        taskResourceV2 = client.deleteServerProfileTemplate(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI,
                resourceId));
        rp.setType(HttpMethodType.DELETE);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertEquals("A success delete server profile template call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteServerProfileTemplateWithNullParams() {
        client.deleteServerProfileTemplate(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testDeleteServerProfileTemplateWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.deleteServerProfileTemplate(params, resourceId, false);
    }

    private String getJsonFromFile(String filename) {
        String json = "";
        try {
            json = IOUtils.toString(this.getClass().getResourceAsStream(filename), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
