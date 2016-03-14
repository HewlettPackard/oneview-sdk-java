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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;
import com.hp.ov.sdk.adaptors.LogicalInterconnectGroupAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpRestClient.class, TaskMonitorManager.class})
public class LogicalInterconnectGroupClientTest {

    private RestParams params;
    private LogicalInterconnectGroupAdaptor adaptor;
    private LogicalInterconnectGroupClient client;

    private final String resourceId = "random-UUID";
    private final String resourceName = "random-name";
    private final String settingsId = "settings-id";

    private String ligJson = "";

    @Mock
    private TaskMonitorManager taskMonitorManager;
    private TaskAdaptor taskAdaptor;

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
        taskAdaptor = TaskAdaptor.getInstance();
        adaptor = new LogicalInterconnectGroupAdaptor();

        PowerMockito.mockStatic(HttpRestClient.class);
        PowerMockito.mockStatic(TaskMonitorManager.class);
        PowerMockito.when(TaskMonitorManager.getInstance()).thenReturn(taskMonitorManager);

        this.client = LogicalInterconnectGroupClientImpl.getClient();
    }

    @Test
    public void testGetLogicalInterconnectGroup() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        LogicalInterconnectGroups ligDto = client.getLogicalInterconnectGroup(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(ligDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectGroupWithNullParams() {
        client.getLogicalInterconnectGroup(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectGroupWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectGroup(params, resourceId);
    }

    @Test
    public void testGetAllLogicalInterconnectGroups() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        LogicalInterconnectGroupCollectionV2 ligList = client.getAllLogicalInterconnectGroups(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(ligList);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                ligList.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllLogicalInterconnectGroupsWithNullParams() {
        client.getAllLogicalInterconnectGroups(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllLogicalInterconnectGroupsWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllLogicalInterconnectGroups(params);
    }

    @Test
    public void testGetLogicalInterconnectGroupByName() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        LogicalInterconnectGroups ligDto = client.getLogicalInterconnectGroupByName(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI,
                UrlUtils.createFilterString(resourceName)));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(ligDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectGroupByNameWithNullParams() {
        client.getLogicalInterconnectGroupByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectGroupByNameWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectGroupByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetLogicalInterconnectGroupByNameWithNoMembers() {
        LogicalInterconnectGroupCollectionV2 ligCollection = adaptor.buildCollectionDto(this.getJsonFromFile("LogicalInterconnectGroupGetByName.json"));
        ligCollection.setCount(0);
        ligJson = new Gson().toJson(ligCollection);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        client.getLogicalInterconnectGroupByName(params, resourceName);
    }

    @Test
    public void testCreateLogicalInterconnectGroup() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGet.json");
        LogicalInterconnectGroups ligDto = adaptor.buildDto(ligJson);

        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectGroupCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonCreateTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.createLogicalInterconnectGroup(params, ligDto, false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI));
        rp.setType(HttpMethodType.POST);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success create logical interconnect group call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateLogicalInterconnectGroupWithNullParams() {
        client.createLogicalInterconnectGroup(null, new LogicalInterconnectGroups(), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateLogicalInterconnectGroupWithNullDto() {
        client.createLogicalInterconnectGroup(params, null, false, false);
    }

    @Test
    public void testUpdateLogicalInterconnectGroup() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGet.json");
        List<UplinkSet> uplinkSetDto = Arrays.asList(new UplinkSet());

        String jsonUpdateTaskCompleted = this.getJsonFromFile("LogicalInterconnectGroupCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonUpdateTaskCompleted);

        // GET
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        // UPDATE
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonUpdateTaskCompleted);

        // TASK STATUS
        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateLogicalInterconnectGroup(params, resourceId, uplinkSetDto, false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update logical interconnect group call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectGroupWithNullParams() {
        List<UplinkSet> uplinkSetDto = Arrays.asList(new UplinkSet());
        client.updateLogicalInterconnectGroup(null, resourceId, uplinkSetDto, false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectGroupWithNullDto() {
        client.updateLogicalInterconnectGroup(params, resourceId, null, false, false);
    }

    @Test
    public void testDeleteLogicalInterconnectGroup() {
        String jsonDeleteTaskCompleted = this.getJsonFromFile("LogicalInterconnectGroupCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonDeleteTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonDeleteTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.deleteLogicalInterconnectGroup(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));
        rp.setType(HttpMethodType.DELETE);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success delete logical interconnect group call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteLogicalInterconnectGroupWithNullParams() {
        client.deleteLogicalInterconnectGroup(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testDeleteLogicalInterconnectGroupWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.deleteLogicalInterconnectGroup(params, resourceId, false);
    }

    @Test
    public void testGetDefaultInterconnectSettings() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGetDefaultInterconnectSettings.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        InterconnectSettingsV2 settingsDto = client.getDefaultInterconnectSettings(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, SdkConstants.DEFAULT_SETTINGS));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(settingsDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetDefaultInterconnectSettingsWithNullParams() {
        client.getDefaultInterconnectSettings(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetDefaultInterconnectSettingsWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getDefaultInterconnectSettings(params);
    }

    @Test
    public void testGetInterconnectSettings() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGetDefaultInterconnectSettings.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        InterconnectSettingsV2 settingsDto = client.getInterconnectSettings(params, resourceId, settingsId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI,
                resourceId,
                SdkConstants.SETTINGS,
                settingsId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(settingsDto);
    }

    @Test
    public void testGetInterconnectSettingsV200() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGetDefaultInterconnectSettings.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        params.setApiVersion(200);
        InterconnectSettingsV2 settingsDto = client.getInterconnectSettings(params, resourceId);

        RestParams rp = new RestParams();
        rp.setApiVersion(200);
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI,
                resourceId,
                SdkConstants.SETTINGS));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(settingsDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectSettingsWithNullParams() {
        client.getInterconnectSettings(null, resourceId, settingsId);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectSettingsWithBlankResourceId() {
        client.getInterconnectSettings(null, "", settingsId);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetInterconnectSettingsWithBlankSettingsId() {
        client.getInterconnectSettings(null, resourceId, "");
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetInterconnectSettingsWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getInterconnectSettings(params, resourceId, settingsId);
    }

    @Test
    public void testGetId() {
        ligJson = this.getJsonFromFile("LogicalInterconnectGroupGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(ligJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI,
                UrlUtils.createFilterString(resourceName)));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

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
