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
import com.hp.ov.sdk.adaptors.PowerDeliveryDeviceAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.Light;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Power;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class PowerDeliveryDeviceClientTest {

    private RestParams params;

    @Spy
    private ResourceAdaptor resourceAdaptor;
    @Spy
    private PowerDeliveryDeviceAdaptor adaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitorManager;
    @Mock
    private HttpRestClient restClient;

    @InjectMocks
    private PowerDeliveryDeviceClientImpl client;

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private String spJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }

    @Test
    public void testGetPowerDeliveryDeviceById() {
        spJson = this.getJsonFromFile("PowerDeliveryDeviceGet.json");
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn(spJson);

        PowerDeliveryDevice PowerDeliveryDeviceDto = client.getPowerDeliveryDeviceById(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(PowerDeliveryDeviceDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetPowerDeliveryDeviceWithNullParams() {
        client.getPowerDeliveryDeviceById(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetPowerDeliveryDeviceWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getPowerDeliveryDeviceById(params, resourceId);
    }

    @Test
    public void testGetAllPowerDeliveryDevices() {
        spJson = this.getJsonFromFile("PowerDeliveryDeviceGetAll.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(spJson);

        ResourceCollection<PowerDeliveryDevice> powerDeliveryDeviceList = client.getAllPowerDeliveryDevices(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(powerDeliveryDeviceList);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                powerDeliveryDeviceList.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllPowerDeliveryDeviceWithNullParams() {
        client.getAllPowerDeliveryDevices(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllPowerDeliveryDeviceWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllPowerDeliveryDevices(params);
    }

    @Test
    public void testGetPowerDeliveryDeviceByName() {
        spJson = this.getJsonFromFile("PowerDeliveryDeviceGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(spJson);

        PowerDeliveryDevice powerDeliveryDeviceDto = client.getPowerDeliveryDeviceByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.POWER_DEVICE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(powerDeliveryDeviceDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetPowerDeliveryDeviceByNameWithNullParams() {
        client.getPowerDeliveryDeviceByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetPowerDeliveryDeviceByNameWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getPowerDeliveryDeviceByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetPowerDeliveryDeviceByNameWithNoMembers() {
        spJson = new Gson().toJson(new ResourceCollection<PowerDeliveryDevice>());

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(spJson);

        client.getPowerDeliveryDeviceByName(params, resourceName);
    }

    @Test
    public void testAddPowerDeliveryDevice() {
        spJson = this.getJsonFromFile("PowerDeliveryDeviceGet.json");
        PowerDeliveryDevice powerDeliveryDeviceDto = new PowerDeliveryDevice();

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(spJson);

        Mockito.doReturn(powerDeliveryDeviceDto).when(adaptor).buildDto(Mockito.anyString(), Mockito.any(ApiVersion.class));

        PowerDeliveryDevice result = client.addPowerDeliveryDevice(
                params,
                powerDeliveryDeviceDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI));
        rp.setType(HttpMethodType.POST);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(result);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testAddPowerDeliveryDeviceWithNullParams() {
        client.addPowerDeliveryDevice(
                null,
                new PowerDeliveryDevice(),
                false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testAddPowerDeliveryDeviceWithNullDto() {
        client.addPowerDeliveryDevice(
                params,
                null,
                false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testAddPowerDeliveryDeviceWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.addPowerDeliveryDevice(
                params,
                new PowerDeliveryDevice(),
                false);
    }

    @Test
    public void testAddPowerDeliveryDeviceByDiscover() {
        spJson = this.getJsonFromFile("PowerDeliveryDeviceGet.json");
        ImportPdd importPddDto = new ImportPdd();

        String jsonCreateTaskCompleted = this.getJsonFromFile("PowerDeliveryDeviceTaskCompleted.json");
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

        TaskResourceV2 result = client.addPowerDeliveryDeviceByDiscover(
                params,
                importPddDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, SdkConstants.DISCOVER));
        rp.setType(HttpMethodType.POST);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success add power delivery device call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreatePowerDeliveryDeviceWithNullParams() {
        client.addPowerDeliveryDeviceByDiscover(
                null,
                new ImportPdd(),
                false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreatePowerDeliveryDeviceWithNullDto() {
        client.addPowerDeliveryDeviceByDiscover(
                params,
                null,
                false);
    }

    @Test
    public void testUpdatePowerDeliveryDevice() {
        spJson = this.getJsonFromFile("PowerDeliveryDeviceGet.json");
        PowerDeliveryDevice powerDeliveryDeviceDto = new PowerDeliveryDevice();

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(spJson);

        Mockito.doReturn(powerDeliveryDeviceDto).when(adaptor).buildDto(Mockito.anyString(), Mockito.any(ApiVersion.class));

        PowerDeliveryDevice result = client.updatePowerDeliveryDevice(
                params,
                resourceId,
                powerDeliveryDeviceDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(result);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDeviceWithNullParams() {
        client.updatePowerDeliveryDevice(null, resourceId, new PowerDeliveryDevice(), false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDeviceWithNullDto() {
        client.updatePowerDeliveryDevice(params, resourceId, null, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdatePowerDeliveryDeviceWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.updatePowerDeliveryDevice(params, resourceId, new PowerDeliveryDevice(), false);
    }

    @Test
    public void testRemovePowerDeliveryDevice() {
        String jsonRemoveTaskCompleted = this.getJsonFromFile("PowerDeliveryDeviceTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonRemoveTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonRemoveTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.removePowerDeliveryDevice(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.POWER_DEVICE_URI,
                resourceId));
        rp.setType(HttpMethodType.DELETE);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertEquals("A success remove power delivery device call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testRemovePowerDeliveryDeviceWithNullParams() {
        client.removePowerDeliveryDevice(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testRemovePowerDeliveryDeviceWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.removePowerDeliveryDevice(params, resourceId, false);
    }

    @Test
    public void testRemovePowerDeliveryDeviceByFilter() {
        String jsonRemoveTaskCompleted = this.getJsonFromFile("PowerDeliveryDeviceTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonRemoveTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonRemoveTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.removePowerDeliveryDeviceByFilter(params, "name matches '%" + resourceName + "%'", true);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name matches '%" + resourceName + "%'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.POWER_DEVICE_URI));
        rp.setType(HttpMethodType.DELETE);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertEquals("A success remove power delivery device call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }


    @Test
    public void testRemovePowerDeliveryDeviceByFilterWithNoMatch() {
        String jsonRemoveTaskCompleted = this.getJsonFromFile("PowerDeliveryDeviceTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonRemoveTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonRemoveTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.removePowerDeliveryDeviceByFilter(params, "name='filter'", false);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='filter'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.POWER_DEVICE_URI));
        rp.setType(HttpMethodType.DELETE);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertEquals("A success remove power delivery device call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testRemovePowerDeliveryDeviceByFilterWithNullFilter() {
        client.removePowerDeliveryDeviceByFilter(params, null, true);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testRemovePowerDeliveryDeviceByFilterWithNullParams() {
        client.removePowerDeliveryDeviceByFilter(null, "filter", true);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testRemovePowerDeliveryDeviceByFilterWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.removePowerDeliveryDeviceByFilter(params, "name='filter'", true);
    }

    @Test
    public void testRemovePowerDeliveryDeviceSynchronously() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn("{}");

        String result = client.removePowerDeliveryDeviceSynchronously(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.SYNCHRONOUS));
        rp.setType(HttpMethodType.DELETE);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(result);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testRemovePowerDeliveryDeviceSynchronouslyWithNullParams() {
        client.removePowerDeliveryDeviceSynchronously(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testRemovePowerDeliveryDeviceSynchronouslyWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.removePowerDeliveryDeviceSynchronously(params, resourceId);
    }

    @Test
    public void testGetPowerDeliveryDevicePowerState() {
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn("\"On\"");

        Power powerDto = client.getPowerDeliveryDevicePowerState(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.POWER_STATE));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(powerDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetPowerDeliveryDevicePowerStateWithNullParams() {
        client.getPowerDeliveryDevicePowerState(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetPowerDeliveryDevicePowerStateWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getPowerDeliveryDevicePowerState(params, resourceId);
    }

    @Test
    public void testUpdatePowerDeliveryDevicePowerState() {
        OutletState outletStateDto = new OutletState();

        String jsonUpdateTaskCompleted = this.getJsonFromFile("PowerDeliveryDeviceTaskCompleted.json");
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

        TaskResourceV2 result = client.updatePowerDeliveryDevicePowerState(
                params,
                resourceId,
                outletStateDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.POWER_STATE));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update power delivery device power state call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDevicePowerStateWithNullParams() {
        client.updatePowerDeliveryDevicePowerState(null, resourceId, new OutletState(), false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDevicePowerStateWithNullDto() {
        client.updatePowerDeliveryDevicePowerState(params, resourceId, null, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdatePowerDeliveryDevicePowerStateWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.updatePowerDeliveryDevicePowerState(params, resourceId, new OutletState(), false);
    }

    @Test
    public void testUpdatePowerDeliveryDeviceRefreshState() {
        PowerDeliveryDeviceRefreshRequest pddRefreshRequestDto = new PowerDeliveryDeviceRefreshRequest();

        String jsonUpdateTaskCompleted = this.getJsonFromFile("PowerDeliveryDeviceTaskCompleted.json");
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

        TaskResourceV2 result = client.updatePowerDeliveryDeviceRefreshState(
                params,
                resourceId,
                pddRefreshRequestDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.REFRESH_STATE));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update power delivery device refresh state call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDeviceRefreshStateWithNullParams() {
        client.updatePowerDeliveryDeviceRefreshState(null, resourceId, new PowerDeliveryDeviceRefreshRequest(), false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDeviceRefreshStateWithNullDto() {
        client.updatePowerDeliveryDeviceRefreshState(params, resourceId, null, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdatePowerDeliveryDeviceRefreshStateWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.updatePowerDeliveryDeviceRefreshState(params, resourceId, new PowerDeliveryDeviceRefreshRequest(), false);
    }

    @Test
    public void testGetPowerDeliveryDeviceUidState() {
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn("\"On\"");

        Light lightDto = client.getPowerDeliveryDeviceUidState(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.UID_STATE));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(lightDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetPowerDeliveryDeviceUidStateWithNullParams() {
        client.getPowerDeliveryDeviceUidState(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetPowerDeliveryDeviceUidStateWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getPowerDeliveryDeviceUidState(params, resourceId);
    }

    @Test
    public void testUpdatePowerDeliveryDeviceUidState() {
        OutletState outletStateDto = new OutletState();

        String jsonUpdateTaskCompleted = this.getJsonFromFile("PowerDeliveryDeviceTaskCompleted.json");
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

        TaskResourceV2 result = client.updatePowerDeliveryDeviceUidState(
                params,
                resourceId,
                outletStateDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.UID_STATE));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update power delivery device UID state call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDeviceUidStateWithNullParams() {
        client.updatePowerDeliveryDeviceUidState(null, resourceId, new OutletState(), false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdatePowerDeliveryDeviceUidStateWithNullDto() {
        client.updatePowerDeliveryDeviceUidState(params, resourceId, null, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdatePowerDeliveryDeviceUidStateWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.updatePowerDeliveryDeviceUidState(params, resourceId, new OutletState(), false);
    }

    @Test
    public void testGetPowerDeliveryDeviceUtilization() {
        spJson = this.getJsonFromFile("PowerDeliveryDeviceGetUtilization.json");
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn(spJson);

        UtilizationData utilizationDataDto = client.getPowerDeliveryDeviceUtilization(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.UTILIZATION));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(rp));

        assertNotNull(utilizationDataDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetPowerDeliveryDeviceUtilizationWithNullParams() {
        client.getPowerDeliveryDeviceUtilization(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetPowerDeliveryDeviceUtilizationWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getPowerDeliveryDeviceUtilization(params, resourceId);
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
