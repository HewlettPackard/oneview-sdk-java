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
import java.util.Collections;

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
import com.hp.ov.sdk.adaptors.LogicalInterconnectAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectFibData;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.InternalVlanAssociationCollection;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.PortMonitorUplinkPortCollection;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.generated.Location;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.dto.generated.TelemetryConfiguration;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpRestClient.class, TaskMonitorManager.class})
public class LogicalInterconnectClientTest {

    private RestParams params;
    private LogicalInterconnectAdaptor adaptor;
    private LogicalInterconnectClient client;

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private static String telemetryId = "telemetry-id";
    private String liJson = "";

    @Mock
    private TaskMonitorManager taskMonitorManager;
    private TaskAdaptor taskAdaptor;

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
        taskAdaptor = TaskAdaptor.getInstance();
        adaptor = new LogicalInterconnectAdaptor();

        PowerMockito.mockStatic(HttpRestClient.class);
        PowerMockito.mockStatic(TaskMonitorManager.class);
        PowerMockito.when(TaskMonitorManager.getInstance()).thenReturn(taskMonitorManager);

        this.client = LogicalInterconnectClientImpl.getClient();
    }

    @Test
    public void testGetLogicalInterconnect() {
        liJson = this.getJsonFromFile("LogicalInterconnectGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        LogicalInterconnects liDto = client.getLogicalInterconnect(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(liDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectWithNullParams() {
        client.getLogicalInterconnect(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnect(params, resourceId);
    }

    @Test
    public void testGetAllLogicalInterconnects() {
        liJson = this.getJsonFromFile("LogicalInterconnectGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        LogicalInterconnectCollectionV2 liCollectionDto = client.getAllLogicalInterconnects(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(liCollectionDto);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                liCollectionDto.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllLogicalInterconnectsWithNullParams() {
        client.getAllLogicalInterconnects(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllLogicalInterconnectsWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllLogicalInterconnects(params);
    }

    @Test
    public void testUpdateLogicalInterconnectComplianceById() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateLogicalInterconnectComplianceById(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.COMPLIANCE));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update compliance call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectComplianceByIdWithNullParams() {
        client.updateLogicalInterconnectComplianceById(null, resourceId, false);
    }

    @Test
    public void testUpdateEthernetSettings() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateEthernetSettings(params, resourceId, new EthernetInterconnectSettingsV2(), false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.ETHERNET_SETTINGS));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update ethernet settings call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEthernetSettingsWithNullParams() {
        client.updateEthernetSettings(null, resourceId, new EthernetInterconnectSettingsV2(), false);
    }

    @Test
    public void testCreateLogicalInterconnect() {
        Location locationDto = new Location();

        // ENCLOSURE
        LocationEntry enclosureEntry = new LocationEntry();
        enclosureEntry.setType(LocationEntry.Type.Enclosure);
        enclosureEntry.setValue("");

        // BAY
        LocationEntry bayEntry = new LocationEntry();
        bayEntry.setType(LocationEntry.Type.Bay);
        bayEntry.setValue("");

        locationDto.setLocationEntries(Arrays.asList(enclosureEntry, bayEntry));

        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.createLogicalInterconnect(params, locationDto, false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                SdkConstants.LOCATIONS,
                SdkConstants.INTERCONNECTS));
        rp.setType(HttpMethodType.POST);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success create logical interconnect call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateLogicalInterconnectWithNullParams() {
        client.createLogicalInterconnect(null, new Location(), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateLogicalInterconnectWithNullDto() {
        client.createLogicalInterconnect(params, null, false, false);
    }

    @Test
    public void testDeleteLogicalInterconnect() {
        String jsonDeleteTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonDeleteTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonDeleteTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.deleteLogicalInterconnect(params, "/uri", "1", false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                SdkConstants.LOCATIONS,
                SdkConstants.INTERCONNECTS +
                "?location=Enclosure:/uri,Bay:1"));
        rp.setType(HttpMethodType.DELETE);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success delete logical interconnect call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteLogicalInterconnectWithNullParams() {
        client.deleteLogicalInterconnect(null, "/uri", "1", false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testDeleteLogicalInterconnectWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.deleteLogicalInterconnect(params, "/uri", "1", false);
    }

    @Test
    public void testUpdateLogicalInterconnectFirmwareById() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateLogicalInterconnectFirmwareById(params, resourceId, new LiFirmware(), false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.FIRMWARE));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update logical interconnect firmware call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectFirmwareByIdWithNullParams() {
        client.updateLogicalInterconnectFirmwareById(null, resourceId, new LiFirmware(), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectFirmwareByIdWithNullDto() {
        client.updateLogicalInterconnectFirmwareById(params, resourceId, null, false, false);
    }

    @Test
    public void testUpdateLogicalInterconnectSnmpConfigurationById() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateLogicalInterconnectSnmpConfigurationById(params, resourceId, new SnmpConfiguration(), false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.SNMP_CONFIGURATION));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update logical interconnect SNMP configuration call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectSnmpConfigurationByIdWithNullParams() {
        client.updateLogicalInterconnectSnmpConfigurationById(null, resourceId, new SnmpConfiguration(), false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectSnmpConfigurationByIdWithNullDto() {
        client.updateLogicalInterconnectSnmpConfigurationById(params, resourceId, null, false, false);
    }

    @Test
    public void testGetLogicalInterconnectByName() {
        liJson = this.getJsonFromFile("LogicalInterconnectGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        LogicalInterconnects liDto = client.getLogicalInterconnectByName(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(liDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectByNameWithNullParams() {
        client.getLogicalInterconnectByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectByNameWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetLogicalInterconnectByNameWithNoMembers() {
        LogicalInterconnectCollectionV2 liCollection = adaptor.buildCollectionDto(this.getJsonFromFile("LogicalInterconnectGetAll.json"));
        liCollection.setCount(0);
        liCollection.setMembers(Collections.<LogicalInterconnects>emptyList());
        liJson= new Gson().toJson(liCollection);
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        client.getLogicalInterconnectByName(params, resourceName);
    }

    @Test
    public void testGetLogicalInterconnectFirmwareById() {
        liJson = this.getJsonFromFile("LogicalInterconnectFirmwareGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        LiFirmware firmwareDto = client.getLogicalInterconnectFirmwareById(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.FIRMWARE));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(firmwareDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectFirmwareByIdWithNullParams() {
        client.getLogicalInterconnectFirmwareById(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectFirmwareByIdWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectFirmwareById(params, resourceId);
    }

    @Test
    public void testGetLogicalInterconnectForwardingInformationBase() {
        liJson = this.getJsonFromFile("LogicalInterconnectForwardingInfoBaseGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        InterconnectFibData fibDto = client.getLogicalInterconnectForwardingInformationBase(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.FORWARDING_INFORMATION_BASE));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(fibDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectForwardingInformationBaseWithNullParams() {
        client.getLogicalInterconnectForwardingInformationBase(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectForwardingInformationBaseWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectForwardingInformationBase(params, resourceId);
    }

    @Test
    public void testCreateLogicalInterconnectForwardingInformationBase() {
        liJson = this.getJsonFromFile("LogicalInterconnectForwardingInfoBaseGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        InterconnectFibDataInfo result = client.createLogicalInterconnectForwardingInformationBase(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.FORWARDING_INFORMATION_BASE));
        rp.setType(HttpMethodType.POST);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(result);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateLogicalInterconnectForwardingInformationBaseWithNullParams() {
        client.createLogicalInterconnectForwardingInformationBase(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testCreateLogicalInterconnectForwardingInformationBaseWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.createLogicalInterconnectForwardingInformationBase(params, resourceId);
    }

    @Test
    public void testUpdateLogicalInterconnectInternalNetworks() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonCreateTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONArray.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateLogicalInterconnectInternalNetworks(params, resourceId, Arrays.asList(""));

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.INTERNAL_NETWORKS));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONArray.class));

        assertEquals("A success update internal networks call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectInternalNetworksWithNullParams() {
        client.updateLogicalInterconnectInternalNetworks(null, resourceId, Arrays.asList(""));
    }

    @Test
    public void testGetLogicalInterconnectInternalVlans() {
        liJson = this.getJsonFromFile("LogicalInterconnectInternalVlanGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        InternalVlanAssociationCollection vlanCollectionDto = client.getLogicalInterconnectInternalVlans(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.INTERNAL_VLANS));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(vlanCollectionDto);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                6, vlanCollectionDto.getCount());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectInternalVlansWithNullParams() {
        client.getLogicalInterconnectInternalVlans(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectInternalVlansWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectInternalVlans(params, resourceId);
    }

    @Test
    public void testGetLogicalInterconnectQosAggregatedConfiguration() {
        liJson = this.getJsonFromFile("LogicalInterconnectQoSConfigGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        QosAggregatedConfiguration qosConfig = client.getLogicalInterconnectQosAggregatedConfiguration(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.QOS_AGGREGATED_CONFIGURATION));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(qosConfig);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectQosAggregatedConfigurationWithNullParams() {
        client.getLogicalInterconnectQosAggregatedConfiguration(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectQosAggregatedConfigurationWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectQosAggregatedConfiguration(params, resourceId);
    }

    @Test
    public void testUpdateLogicalInterconnectQosAggregatedConfiguration() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateLogicalInterconnectQosAggregatedConfiguration(params, resourceId, new QosAggregatedConfiguration());

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.QOS_AGGREGATED_CONFIGURATION));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update QoS configuration call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectQosAggregatedConfigurationWithNullParams() {
        client.updateLogicalInterconnectQosAggregatedConfiguration(null, resourceId, new QosAggregatedConfiguration());
    }

    @Test
    public void testUpdateLogicalInterconnectSettings() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateLogicalInterconnectSettings(params, resourceId, new InterconnectSettingsV2());

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.SETTINGS));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update interconnect settings call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectSettingsWithNullParams() {
        client.updateLogicalInterconnectSettings(null, resourceId, new InterconnectSettingsV2());
    }

    @Test
    public void testGetLogicalInterconnectSnmpConfigurationById() {
        liJson = this.getJsonFromFile("LogicalInterconnectSnmpConfigGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        SnmpConfiguration snmpConfig = client.getLogicalInterconnectSnmpConfigurationById(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.SNMP_CONFIGURATION));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(snmpConfig);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectSnmpConfigurationByIdWithNullParams() {
        client.getLogicalInterconnectSnmpConfigurationById(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectSnmpConfigurationByIdWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectSnmpConfigurationById(params, resourceId);
    }

    @Test
    public void testGetLogicalInterconnectUnassignedUplinkPortsForPortMonitor() {
        liJson = this.getJsonFromFile("LogicalInterconnectUplinkPortGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        PortMonitorUplinkPortCollection uplinkPortCollection = client.getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.UNASSIGNED_UPLINK_PORTS_FOR_PORT_MONITOR));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(uplinkPortCollection);
        assertEquals("Based on the JSON file, the return object must have 6 elements",
                6, uplinkPortCollection.getCount());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectUnassignedUplinkPortsForPortMonitorWithNullParams() {
        client.getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectUnassignedUplinkPortsForPortMonitorWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(params, resourceId);
    }

    @Test
    public void testUpdateLogicalInterconnectConfiguration() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(jsonCreateTaskCompleted);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateLogicalInterconnectConfiguration(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.CONFIGURATION));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success update interconnect configuration call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectConfigurationWithNullParams() {
        client.updateLogicalInterconnectConfiguration(null, resourceId);
    }

    @Test
    public void testGetLogicalInterconnectPortMonitorConfiguration() {
        liJson = this.getJsonFromFile("LogicalInterconnectPortMonitorGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        PortMonitor portMonitor = client.getLogicalInterconnectPortMonitorConfiguration(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.PORT_MONITOR));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(portMonitor);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectPortMonitorConfigurationWithNullParams() {
        client.getLogicalInterconnectPortMonitorConfiguration(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectPortMonitorConfigurationWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectPortMonitorConfiguration(params, resourceId);
    }

    @Test
    public void testUpdateLogicalInterconnectPortMonitorConfiguration() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateLogicalInterconnectPortMonitorConfiguration(params, resourceId, new PortMonitor());

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.PORT_MONITOR));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update port monitor configuration call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectPortMonitorConfigurationWithNullParams() {
        client.updateLogicalInterconnectPortMonitorConfiguration(null, resourceId, new PortMonitor());
    }

    @Test
    public void testGetLogicalInterconnectTelementaryConfiguration() {
        liJson = this.getJsonFromFile("LogicalInterconnectTelemetryGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        TelemetryConfiguration telemetryConfig = client.getLogicalInterconnectTelemetryConfiguration(params, resourceId, telemetryId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.TELEMETRY_CONFIGURATIONS,
                telemetryId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(telemetryConfig);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalInterconnectTelementaryConfigurationWithNullParams() {
        client.getLogicalInterconnectTelemetryConfiguration(null, resourceId, telemetryId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalInterconnectTelementaryConfigurationWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getLogicalInterconnectTelemetryConfiguration(params, resourceId, telemetryId);
    }

    @Test
    public void testUpdateLogicalInterconnectTelemetryConfiguration() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("LogicalInterconnectCreateTaskCompleted.json");
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

        TaskResourceV2 result = client.updateLogicalInterconnectTelemetryConfiguration(params, resourceId, telemetryId, new TelemetryConfiguration());

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.TELEMETRY_CONFIGURATIONS,
                telemetryId));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertEquals("A success update port monitor configuration call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateLogicalInterconnectTelemetryConfigurationWithNullParams() {
        client.updateLogicalInterconnectTelemetryConfiguration(null, resourceId, telemetryId, new TelemetryConfiguration());
    }

    @Test
    public void testGetId() {
        // There is no filter on Logical Interconnects.
        // The code retrieves all LI and iterate over them to find the specified name
        liJson = this.getJsonFromFile("LogicalInterconnectGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(liJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI));
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
