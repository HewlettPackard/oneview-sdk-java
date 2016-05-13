package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.HashMap;
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
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.hp.ov.sdk.adaptors.EnclosureAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.RefreshStateConfig.RefreshForceOptions;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class EnclosureClientTest {

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private static String enclosureScript = "#script";
    private String enclosureJson = "";
    private RestParams params;

    @Mock
    private EnclosureAdaptor adaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitorManager;
    @Mock
    private HttpRestClient restClient;

    @InjectMocks
    private EnclosureClientImpl client;

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }

    @Test
    public void testGetEnclosure() {
        enclosureJson = this.getJsonFromFile("EnclosureGet.json");
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class)))
        .thenReturn(enclosureJson);

        Mockito.when(adaptor.buildDto(enclosureJson))
        .thenReturn(new EnclosureAdaptor().buildDto(enclosureJson));

        Enclosures enclosureDto = client.getEnclosure(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(enclosureDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetEnclosureWithNullParams() {
        client.getEnclosure(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetEnclosureWithNullResponse() {
        enclosureJson = this.getJsonFromFile("EnclosureGet.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getEnclosure(params, resourceId);
    }

    @Test
    public void testGetAllEnclosures() {
        enclosureJson = this.getJsonFromFile("EnclosureGetAll.json");

        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class)))
        .thenReturn(enclosureJson);

        Mockito.when(adaptor.buildCollectionDto(enclosureJson))
        .thenReturn(new EnclosureAdaptor().buildCollectionDto(enclosureJson));

        EnclosureCollectionV2 enclosureCollection = client.getAllEnclosures(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(enclosureCollection);
        assertEquals("Based on the JSON file, the return object must have 1 element",
                enclosureCollection.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllEnclosuresWithNullParams() {
        client.getAllEnclosures(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllEnclosuresWithNullResponse() {
        enclosureJson = this.getJsonFromFile("EnclosureGetAll.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getAllEnclosures(params);
    }

    @Test
    public void testGetEnclosureByName() {
        enclosureJson = this.getJsonFromFile("EnclosureGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureJson);

        Mockito.when(adaptor.buildCollectionDto(enclosureJson))
        .thenReturn(new EnclosureAdaptor().buildCollectionDto(enclosureJson));

        Enclosures enclosureDto = client.getEnclosureByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.ENCLOSURE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(enclosureDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetEnclosureByNameWithNullParams() {
        client.getEnclosureByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetEnclosureByNameWithNullResponse() {
        enclosureJson = this.getJsonFromFile("EnclosureGetByName.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getEnclosureByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetEnclosureGroupByNameWithNoMembers() {
        EnclosureCollectionV2 enclosureCollectionDto = new EnclosureAdaptor().buildCollectionDto(this.getJsonFromFile("EnclosureGetByName.json"));
        enclosureCollectionDto.setCount(0);
        enclosureJson = new Gson().toJson(enclosureCollectionDto);

        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn(enclosureJson);

        Mockito.when(adaptor.buildCollectionDto(enclosureJson)).thenReturn(enclosureCollectionDto);

        client.getEnclosureByName(params, resourceName);
    }

    @Test
    public void testCreateEnclosure() {
        enclosureJson = this.getJsonFromFile("EnclosureGet.json");
        AddEnclosureV2 enclosureDto = new EnclosureAdaptor().buildAddEnclosureDto(enclosureJson);

        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.any(String.class))).thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.createEnclosure(
                params,
                enclosureDto,
                false,
                false);

        verify(restClient, times(1)).sendRequest(Mockito.any(RestParams.class), Mockito.any(JSONObject.class));

        assertEquals("A success create enclosure call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateEnclosureWithNullParams() {
        enclosureJson = this.getJsonFromFile("EnclosureGet.json");
        AddEnclosureV2 enclosureDto = adaptor.buildAddEnclosureDto(enclosureJson);
        client.createEnclosure(null, enclosureDto, false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateEnclosureWithNullDto() {
        client.createEnclosure(params, null, false, false);
    }

    @Test
    public void testUpdateEnclosure() {
        enclosureJson = this.getJsonFromFile("EnclosureGet.json");
        Enclosures enclosureDto = new EnclosureAdaptor().buildDto(enclosureJson);

        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(
                Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateEnclosure(
                params,
                resourceId,
                enclosureDto,
                false,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params), Mockito.any(JSONObject.class));

        assertEquals("A success update enclosure call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnclosureWithNullParams() {
        enclosureJson = this.getJsonFromFile("EnclosureGet.json");
        Enclosures enclosureDto = adaptor.buildDto(enclosureJson);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(enclosureJson);

        client.updateEnclosure( null, resourceId, enclosureDto, false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnclosureWithNullDto() {
        client.updateEnclosure( params, resourceId, null, false, false);
    }

    @Test
    public void testPatchEnclosure() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONArray.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(
                Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        Patch patchDto = new Patch();
        patchDto.setOp(PatchOperation.replace);
        patchDto.setPath("/name");
        patchDto.setValue(resourceName);

        TaskResourceV2 result = client.patchEnclosure( params, resourceId, patchDto , false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.PATCH);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params), Mockito.any(JSONArray.class));

        assertEquals("A success patch enclosure call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testPatchEnclosureWithNullParams() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONArray.class)))
        .thenReturn(enclosureJson);

        client.patchEnclosure( null, resourceId, new Patch(), false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testPatchEnclosureWithNullDto() {
        client.patchEnclosure( params, resourceId, null, false);
    }

    @Test
    public void testDeleteEnclosure() {
        String jsonDeleteTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonDeleteTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonDeleteTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(
                Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        taskResourceV2 = client.deleteEnclosure(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId));
        rp.setType(HttpMethodType.DELETE);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertEquals("A success delete enclosure call returns \"Completed\"", "Completed", taskResourceV2.getTaskState().toString());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteEnclosureWithNullParams() {
        client.deleteEnclosure(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testDeleteEnclosureWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.deleteEnclosure(params, resourceId, false);
    }

    @Test
    public void testGetActiveOaSsoUrl() {
        String jsonSsoData = new Gson().toJson(new SsoUrlData());
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonSsoData);

        Mockito.when(adaptor.buildSsoUrlData(Mockito.anyString()))
        .thenReturn(new SsoUrlData());

        SsoUrlData ssoData = client.getActiveOaSsoUrl(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.ACTIVE_OA_SSO_URL));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(ssoData);
    }

    @Test
    public void testGetActiveOaSsoUrlV200() {
        String jsonSsoData = new Gson().toJson(new SsoUrlData());
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonSsoData);

        Mockito.when(adaptor.buildSsoUrlData(
                Mockito.any(String.class)))
        .thenReturn(new SsoUrlData());

        params.setApiVersion(200);
        SsoUrlData ssoData = client.getActiveOaSsoUrl(params, resourceId);

        RestParams rp = new RestParams();
        rp.setApiVersion(200);
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.ACTIVE_OA_SSO_URL_V200 + "?role=Active"));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(ssoData);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetActiveOaSsoUrlWithNullParams() {
        client.getActiveOaSsoUrl(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetActiveOaSsoUrlWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getActiveOaSsoUrl(params, resourceId);
    }

    @Test
    public void testUpdateCompliance() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(
                Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateCompliance(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.COMPLIANCE));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertEquals("A success update compliance call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateComplianceWithNullParams() {
        client.updateCompliance(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdateComplianceWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.updateCompliance(params, resourceId, false);
    }

    @Test
    public void testUpdateConfiguration() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(
                Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateConfiguration(params, resourceId, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.CONFIGURATION));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertEquals("A success update configuration call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateConfigurationWithNullParams() {
        client.updateConfiguration(null, resourceId, false);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdateConfigurationWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.updateConfiguration(params, resourceId, false);
    }

    @Test
    public void testUpdateEnclosureFwBaseline() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(
                Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        FwBaselineConfig fwDto = new FwBaselineConfig();
        TaskResourceV2 result = client.updateEnclosureFwBaseline(params, resourceId, fwDto , false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.ENCLOSURE_FW_BASELINE));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params), Mockito.any(JSONObject.class));

        assertEquals("A success update firmware baseline call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnclosureFwBaselineWithNullParams() {
        FwBaselineConfig fwDto = new FwBaselineConfig();
        client.updateEnclosureFwBaseline(null, resourceId, fwDto , false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnclosureFwBaselineWithNullDto() {
        client.updateEnclosureFwBaseline(
                params,
                resourceId,
                null,
                false);
    }

    @Test
    public void testGetEnvironmentalConfiguration() {
        enclosureJson = this.getJsonFromFile("EnclosureGetEnvironmental.json");
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureJson);

        Mockito.when(adaptor.buildEnvironmentalConfigurationDto(
                Mockito.any(String.class)))
        .thenReturn(new EnvironmentalConfiguration());

        EnvironmentalConfiguration enclosureDto = client.getEnvironmentalConfiguration(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENVIRONMENT_CONFIGURATION_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(enclosureDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetEnvironmentalConfigurationWithNullParams() {
        client.getEnvironmentalConfiguration(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetEnvironmentalConfigurationWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getEnvironmentalConfiguration(params, resourceId);
    }

    @Test
    public void testUpdateEnvironmentalConfiguration() {
        String configJson = this.getJsonFromFile("EnclosureGetEnvironmental.json");
        EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto = new EnvironmentalConfigurationUpdate();
        environmentalConfigurationUpdateDto.setCalibratedMaxPower(1660);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(configJson);

        EnvironmentalConfiguration envConfigUpdated = new EnvironmentalConfiguration();
        envConfigUpdated.setCalibratedMaxPower(environmentalConfigurationUpdateDto.getCalibratedMaxPower());
        Mockito.when(adaptor.buildEnvironmentalConfigurationDto(
                Mockito.anyString()))
        .thenReturn(envConfigUpdated );

        EnvironmentalConfiguration config = client.updateEnvironmentalConfiguration(
                params,
                resourceId,
                environmentalConfigurationUpdateDto);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId,
                ResourceUris.ENVIRONMENT_CONFIGURATION_URI));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params), Mockito.any(JSONObject.class));

        assertNotNull(config);
        assertTrue("Configuration is outdated", config.getCalibratedMaxPower() == 1660);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnvironmentalConfigurationWithNullParams() {
        EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto = new EnvironmentalConfigurationUpdate();
        client.updateEnvironmentalConfiguration(
                null,
                resourceId,
                environmentalConfigurationUpdateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnvironmentalConfigurationWithNullDto() {
        client.updateEnvironmentalConfiguration(
                params,
                resourceId,
                null);
    }

    @Test
    public void testUpdateRefreshState() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(
                Mockito.anyString()))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        RefreshStateConfig refreshStateConfigDto = new RefreshStateConfig();
        RefreshForceOptions refreshForceOptions = refreshStateConfigDto.getNewRefreshForceOptions();
        refreshStateConfigDto.setRefreshForceOptions(refreshForceOptions);
        refreshStateConfigDto.setRefreshState(RefreshState.RefreshPending);
        TaskResourceV2 result = client.updateRefreshState(params, resourceId, refreshStateConfigDto , false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.REFRESH_STATE));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params), Mockito.any(JSONObject.class));

        assertEquals("A success update firmware baseline call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateRefreshStateWithNullParams() {
        RefreshStateConfig refreshStateConfigDto = new RefreshStateConfig();
        RefreshForceOptions refreshForceOptions = refreshStateConfigDto.getNewRefreshForceOptions();
        refreshStateConfigDto.setRefreshForceOptions(refreshForceOptions);
        refreshStateConfigDto.setRefreshState(RefreshState.RefreshPending);
        client.updateRefreshState(
                null,
                resourceId,
                refreshStateConfigDto,
                false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateRefreshStateWithNullDto() {
        client.updateRefreshState(
                params,
                resourceId,
                null,
                false);
    }

    @Test
    public void testGetScript() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureScript);

        String script = client.getScript(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(script);
        assertEquals(enclosureScript, script);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetScriptWithNullParams() {
        client.getScript(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetScriptWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getScript(params, resourceId);
    }

    @Test
    public void testUpdateScript() {
        String jsonCreateTaskCompleted = this.getJsonFromFile("EnclosureCreateTaskCompleted.json");
        TaskResourceV2 taskResourceV2 = TaskAdaptor.getInstance().buildDto(jsonCreateTaskCompleted);

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class),
                Mockito.any(String.class)))
        .thenReturn(jsonCreateTaskCompleted);

        Mockito.when(taskAdaptor.buildDto(Mockito.any(String.class)))
        .thenReturn(taskResourceV2);

        Mockito.when(taskMonitorManager.checkStatus(
                Mockito.any(RestParams.class),
                Mockito.anyString(),
                Mockito.anyInt()))
        .thenReturn(taskResourceV2);

        TaskResourceV2 result = client.updateScript(params, resourceId, enclosureScript , false, false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params), Mockito.eq(enclosureScript));

        assertEquals("A success update script call returns task state \"Completed\"", TaskState.Completed, result.getTaskState());
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateScriptWithNullParams() {
        client.updateScript(null, resourceId, enclosureScript , false, false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateScriptWithNullScript() {
        client.updateScript(params, resourceId, null , false, false);
    }

    @Test
    public void testGetStandbyOaSsoUrl() {
        String jsonSsoData = new Gson().toJson(new SsoUrlData());
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonSsoData);

        Mockito.when(adaptor.buildSsoUrlData(Mockito.anyString()))
        .thenReturn(new SsoUrlData());

        SsoUrlData ssoData = client.getStandbyOaSsoUrl(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.STANDBY_OA_SSO_URL));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(ssoData);
    }

    @Test
    public void testGetStandbyOaSsoUrlV200() {
        String jsonSsoData = new Gson().toJson(new SsoUrlData());
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(jsonSsoData);

        Mockito.when(adaptor.buildSsoUrlData(Mockito.anyString()))
        .thenReturn(new SsoUrlData());

        params.setApiVersion(200);
        SsoUrlData ssoData = client.getStandbyOaSsoUrl(params, resourceId);

        RestParams rp = new RestParams();
        rp.setApiVersion(200);
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.STANDBY_OA_SSO_URL_V200 + "?role=Standby"));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(ssoData);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetStandbyOaSsoUrlWithNullParams() {
        client.getStandbyOaSsoUrl(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetStandbyOaSsoUrlWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getStandbyOaSsoUrl(params, resourceId);
    }

    @Test
    public void testGetUtilization() {
        String utilizationJson = this.getJsonFromFile("EnclosureGetUtilization.json");

        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(utilizationJson);

        Mockito.when(adaptor.buildUtilizationData(Mockito.anyString()))
        .thenReturn(new UtilizationData());

        UtilizationData utilizationDataDto = client.getUtilization(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_URI, resourceId, SdkConstants.UTILIZATION));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

        assertNotNull(utilizationDataDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetUtilizationWithNullParams() {
        client.getUtilization(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetUtilizationWithNullResponse() {
        Mockito.when(restClient.sendRequest(
                Mockito.any(RestParams.class)))
        .thenReturn(null);

        client.getUtilization(params, resourceId);
    }

    @Test
    public void testGetId() {
        enclosureJson = this.getJsonFromFile("EnclosureGetByName.json");
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class)))
        .thenReturn(enclosureJson);

        Mockito.when(adaptor.buildCollectionDto(enclosureJson))
        .thenReturn(new EnclosureAdaptor().buildCollectionDto(enclosureJson));

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.ENCLOSURE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(Mockito.eq(params));

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
