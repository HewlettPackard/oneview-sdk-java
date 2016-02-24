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

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
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
import com.hp.ov.sdk.adaptors.LogicalEnclosureAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.FirmwareUpdateOn;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LogicalEnclosureList;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.PatchFirmwareValue;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.generated.LogicalEnclosure;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ HttpRestClient.class, TaskMonitorManager.class })
public class LogicalEnclosureClientTest {

    private RestParams params;
    private LogicalEnclosureAdaptor adaptor;
    private LogicalEnclosureClient client;

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private static String enclosureScript = "#script";
    private String logicalEnclosureJson = "";

    @Mock
    private TaskMonitorManager taskMonitorManager;
    private TaskAdaptor taskAdaptor;

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
        taskAdaptor = TaskAdaptor.getInstance();
        adaptor = new LogicalEnclosureAdaptor();

        PowerMockito.mockStatic(HttpRestClient.class);
        PowerMockito.mockStatic(TaskMonitorManager.class);
        PowerMockito.when(TaskMonitorManager.getInstance()).thenReturn(taskMonitorManager);

        this.client = LogicalEnclosureClientImpl.getClient();
    }

    @Test
    public void testGetLogicalEnclosure() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(Mockito.any(RestParams.class))).thenReturn(logicalEnclosureJson);

        LogicalEnclosure logicalEnclosureDto = client.getLogicalEnclosure(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(logicalEnclosureDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalEnclosureWithNullParams() {
        client.getLogicalEnclosure(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalEnclosureWithNullResponse() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalEnclosure(params, resourceId);
    }

    @Test
    public void testGetAllLogicalEnclosures() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(logicalEnclosureJson);

        LogicalEnclosureList logicalEnclosureList = client.getAllLogicalEnclosures(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(logicalEnclosureList);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                logicalEnclosureList.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllLogicalEnclosuresWithNullParams() {
        client.getAllLogicalEnclosures(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllLogicalEnclosuresWithNullResponse() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllLogicalEnclosures(params);
    }

    @Test
    public void testGetLogicalEnclosureByName() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(logicalEnclosureJson);

        LogicalEnclosure logicalEnclosureDto = client.getLogicalEnclosureByName(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_ENCLOSURE_URI,
                UrlUtils.createFilterString(resourceName)));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(logicalEnclosureDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalEnclosureByNameWithNullParams() {
        client.getLogicalEnclosureByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetEnclosureByNameWithNullResponse() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalEnclosureByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetLogicalEnclosureGroupByNameWithNoMembers() {
        LogicalEnclosureList logicalEnclosureList = adaptor.buildCollectionDto(this.getJsonFromFile("LogicalEnclosureGetByName.json"));
        logicalEnclosureList.setCount(0);
        logicalEnclosureJson = new Gson().toJson(logicalEnclosureList);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(logicalEnclosureJson);

        client.getLogicalEnclosureByName(params, resourceName);
    }

    @Test
    public void testCreateLogicalEnclosure() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGet.json");
        AddLogicalEnclosure logicalEnclosureDto = adaptor.buildAddEnclosureDto(logicalEnclosureJson);

        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalEnclosureCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.createLogicalEnclosure(
                params,
                logicalEnclosureDto,
                false,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI));
        rp.setType(HttpMethodType.POST);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success create logical enclosure call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateEnclosureWithNullParams() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGet.json");
        AddLogicalEnclosure logicalEnclosureDto = adaptor.buildAddEnclosureDto(logicalEnclosureJson);
        client.createLogicalEnclosure(null, logicalEnclosureDto, false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateEnclosureWithNullDto() {
        client.createLogicalEnclosure(params, null, false, false);
    }

    @Test
    public void testUpdateLogicalEnclosure() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGet.json");
        LogicalEnclosure LogicalEnclosureDto = adaptor.buildDto(logicalEnclosureJson);

        String jsonUpdateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonUpdateTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonUpdateTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateLogicalEnclosure(
                params,
                resourceId,
                LogicalEnclosureDto,
                false,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update logical enclosure call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalEnclosureWithNullParams() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGet.json");
        LogicalEnclosure logicalEnclosureDto = adaptor.buildDto(logicalEnclosureJson);
        client.updateLogicalEnclosure( null, resourceId, logicalEnclosureDto, false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalEnclosureWithNullDto() {
        client.updateLogicalEnclosure( params, resourceId, null, false, false);
    }

    @Test
    public void testPatchLogicalEnclosure() {
        String jsonPatchTaskCompleted = this.getJsonFromFile("LogicalEnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonPatchTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONArray.class)))
        .thenReturn(jsonPatchTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        Patch patchDto = new Patch();
        patchDto.setOp(PatchOperation.replace);
        patchDto.setPath("/name");
        patchDto.setValue(new PatchFirmwareValue(
                "/something/something",
                FirmwareUpdateOn.EnclosureOnly,
                true));

        TaskResourceV2 result = client.patchLogicalEnclosure( params, resourceId, patchDto , false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.PATCH);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONArray.class));

        assertEquals("A success patch logical enclosure call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testPatchLogicalEnclosureWithNullParams() {
        client.patchLogicalEnclosure( null, resourceId, new Patch(), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testPatchLogicalEnclosureWithNullDto() {
        client.patchLogicalEnclosure( params, resourceId, null, false, false);
    }

    @Test
    public void testDeleteLogicalEnclosure() {
        String jsonDeleteTaskCompleted = this.getJsonFromFile("LogicalEnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonDeleteTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonDeleteTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.deleteLogicalEnclosure(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.DELETE);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success delete enclosure call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteLogicalEnclosureWithNullParams() {
        client.deleteLogicalEnclosure(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testDeleteLogicalEnclosureWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.deleteLogicalEnclosure(params, resourceId, false);
    }

    @Test
    public void testUpdateFromGroup() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalEnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonCreateTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateFromGroup(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_ENCLOSURE_URI,
                resourceId,
                SdkConstants.UPDATE_FROM_GROUP));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success update from group call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateFromGroupWithNullParams() {
        client.updateFromGroup(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdateFromGroupWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.updateFromGroup(params, resourceId, false);
    }

    @Test
    public void testUpdateConfiguration() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalEnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonCreateTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateConfiguration(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.CONFIGURATION));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success update configuration call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateConfigurationWithNullParams() {
        client.updateConfiguration(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdateConfigurationWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.updateConfiguration(params, resourceId, false);
    }

    @Test
    public void testGetScript() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureScript);

        String script = client.getScript(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(script);
        assertEquals(enclosureScript, script);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetScriptWithNullParams() {
        client.getScript(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetScriptWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getScript(params, resourceId);
    }

    @Test
    public void testUpdateScript() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalEnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonCreateTaskCompleted);

        Mockito.when(HttpRestClient.sendStringRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(String.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateScript(params, resourceId, enclosureScript , false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendStringRequestToHPOV(Mockito.eq(rp), Mockito.any(String.class));

        assertEquals("A success update script call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateScriptWithNullParams() {
        client.updateScript(null, resourceId, enclosureScript , false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateScriptWithNullScript() {
        client.updateScript(params, resourceId, null , false);
    }

    @Test
    public void testCreateSupportDump() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalEnclosureCreateTaskCompleted.json");
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

        final SupportDump supportDumpDto = new SupportDump("testDump01", true, false);
        TaskResourceV2 result = client.createSupportDump(params, supportDumpDto, resourceId, false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.SUPPORT_DUMP));
        rp.setType(HttpMethodType.POST);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success create logical enclosure call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateSupportDumpWithNullParams() {
        final SupportDump supportDumpDto = new SupportDump("testDump01", true, false);
        client.createSupportDump(null, supportDumpDto, resourceId, false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateSupportDumpWithNullDto() {
        client.createSupportDump(params, null, resourceId, false, false);
    }

    @Test
    public void testGetId() {
        logicalEnclosureJson = this.getJsonFromFile("LogicalEnclosureGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(logicalEnclosureJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_ENCLOSURE_URI,
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
            json = IOUtils.toString(this.getClass().getResourceAsStream(filename), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
