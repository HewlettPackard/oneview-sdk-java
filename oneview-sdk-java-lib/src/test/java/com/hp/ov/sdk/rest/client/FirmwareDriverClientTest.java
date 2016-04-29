package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

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

import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpRestClient.class, TaskMonitorManager.class})
public class FirmwareDriverClientTest {

    private RestParams params;
    private FirmwareDriverClient client;

    @Mock
    private TaskMonitorManager taskMonitorManager;
    private TaskAdaptor taskAdaptor;

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private String firmwareDriverJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
        taskAdaptor = TaskAdaptor.getInstance();
        PowerMockito.mockStatic(HttpRestClient.class);

        PowerMockito.mockStatic(TaskMonitorManager.class);
        PowerMockito.when(TaskMonitorManager.getInstance()).thenReturn(taskMonitorManager);

        this.client = FirmwareDriverClientImpl.getClient();
    }

    @Test
    public void testGetFirmwareDriver() {
        firmwareDriverJson = this.getJsonFromFile("FirmwareDriverGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(firmwareDriverJson);

        FwBaseline firmwareDriverDto = client.getFirmwareDriver(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(firmwareDriverDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetFirmwareDriverWithNullParams() {
        client.getFirmwareDriver(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetFirmwareDriverWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getFirmwareDriver(params, resourceId);
    }

    @Test
    public void testGetAllFirmwareDrivers() {
        firmwareDriverJson = this.getJsonFromFile("FirmwareDriverGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(firmwareDriverJson);

        FwBaselineCollection firmwareDriverCollection = client.getAllFirmwareDrivers(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(firmwareDriverCollection);
        assertEquals("Based on the JSON file, the return object must have 1 elements",
                firmwareDriverCollection.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllFirmwareDriversWithNullParams() {
        client.getAllFirmwareDrivers(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllFirmwareDriversWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getAllFirmwareDrivers(params);
    }

    @Test
    public void testGetFirmwareDriverByName() {
        firmwareDriverJson = this.getJsonFromFile("FirmwareDriverGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(firmwareDriverJson);

        FwBaseline firmwareDriverDto = client.getFirmwareDriverByName(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(firmwareDriverDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetFirmwareDriverByNameWithNullParams() {
        client.getFirmwareDriverByName(null, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetFirmwareDriverByNameNotFound() {
        firmwareDriverJson = this.getJsonFromFile("FirmwareDriverGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(firmwareDriverJson);

        client.getFirmwareDriverByName(params, "wrong name");
    }

    @Test
    public void testDeleteFirmwareDriver() {
        firmwareDriverJson = this.getJsonFromFile("FirmwareDriverDeleteTask.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(firmwareDriverJson);

        String jsonDeleteTaskCompleted = this.getJsonFromFile("FirmwareDriverDeleteTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonDeleteTaskCompleted);
        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.deleteFirmwareDriver(params, resourceId, false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI, resourceId));
        rp.setType(HttpMethodType.DELETE);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success delete firmware call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteFirmwareDriverWithNullParams() {
        client.deleteFirmwareDriver(null, resourceId, false, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testDeleteFirmwareDriverWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.deleteFirmwareDriver(params, resourceId, false, false);
    }

    @Test
    public void testGetId() {
        firmwareDriverJson = this.getJsonFromFile("FirmwareDriverGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(firmwareDriverJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI));
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
