package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;
import com.hp.ov.sdk.adaptors.EnclosureGroupAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.EnclosureGroupCollectionV2;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpRestClient.class)
public class EnclosureGroupClientTest {

    private RestParams params;
    private EnclosureGroupAdaptor adaptor;
    private EnclosureGroupClient client;

    private static String resourceId = "random-UUID";
    private static String resourceName = "random-name";
    private static String resourceConfigScript = "#script";
    private String enclosureGroupJson = "";
    
    @Before
    public void setUp() throws Exception {
        params = new RestParams();
        adaptor = new EnclosureGroupAdaptor();
        PowerMockito.mockStatic(HttpRestClient.class);
        this.client = EnclosureGroupClientImpl.getClient();
    }

    @Test
    public void testGetEnclosureGroup() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureGroupJson);

        EnclosureGroups enclosureGroupDto = client.getEnclosureGroup(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(enclosureGroupDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetEnclosureGroupWithNullParams() {
        client.getEnclosureGroup(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetEnclosureGroupWithNullResponse() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getEnclosureGroup(params, resourceId);
    }

    @Test
    public void testGetAllEnclosureGroups() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureGroupJson);

        EnclosureGroupCollectionV2 enclosureGroupCollection = client.getAllEnclosureGroups(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(enclosureGroupCollection);
        assertEquals("Based on the JSON file, the return object must have 1 elements",
                enclosureGroupCollection.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllEnclosureGroupsWithNullParams() {
        client.getAllEnclosureGroups(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllEnclosureGroupsWithNullResponse() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getAllEnclosureGroups(params);
    }

    @Test
    public void testGetEnclosureGroupByName() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureGroupJson);

        EnclosureGroups enclosureGroupDto = client.getEnclosureGroupByName(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI,
                UrlUtils.createFilterString(resourceName)));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertNotNull(enclosureGroupDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetEnclosureGroupByNameWithNullParams() {
        client.getEnclosureGroupByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetEnclosureGroupByNameWithNullResponse() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getEnclosureGroupByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetEnclosureGroupByNameWithNoMembers() {
        EnclosureGroupCollectionV2 enclosureGroupCollectionDto = adaptor.buildCollectionDto(this.getJsonFromFile("EnclosureGroupGetByName.json"));
        enclosureGroupCollectionDto.setCount(0);
        enclosureGroupJson = new Gson().toJson(enclosureGroupCollectionDto);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureGroupJson);

        client.getEnclosureGroupByName(params, resourceName);
    }

    @Test
    public void testCreateEnclosureGroup() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGet.json");
        EnclosureGroups enclosureGroupDto = adaptor.buildDto(enclosureGroupJson);

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(enclosureGroupJson);

        enclosureGroupDto = client.createEnclosureGroup(
                params,
                enclosureGroupDto ,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI));
        rp.setType(HttpMethodType.POST);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(enclosureGroupDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateEnclosureGroupWithNullParams() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGet.json");
        EnclosureGroups enclosureGroupDto = adaptor.buildDto(enclosureGroupJson);
        enclosureGroupDto = client.createEnclosureGroup(
                null,
                enclosureGroupDto,
                false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testCreateEnclosureGroupWithNullDto() {
        client.createEnclosureGroup(params, null, false);
    }

    @Test
    public void testUpdateEnclosureGroup() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGet.json");
        EnclosureGroups enclosureGroupDto = adaptor.buildDto(enclosureGroupJson);
        enclosureGroupDto.setDescription("outdated");

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(enclosureGroupJson);

        enclosureGroupDto = client.updateEnclosureGroup(
                params,
                resourceId,
                enclosureGroupDto,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(enclosureGroupDto);
        assertFalse("Enclosure Group description is outdated", enclosureGroupDto.getDescription().contains("outdated"));
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnclosureGroupWithNullParams() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGet.json");
        EnclosureGroups enclosureGroupDto = adaptor.buildDto(enclosureGroupJson);
        enclosureGroupDto.setDescription("outdated");

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(enclosureGroupJson);

        enclosureGroupDto = client.updateEnclosureGroup(
                null,
                resourceId,
                enclosureGroupDto,
                false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateEnclosureGroupWithNullDto() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGet.json");
        EnclosureGroups enclosureGroupDto = adaptor.buildDto(enclosureGroupJson);
        enclosureGroupDto.setDescription("outdated");

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(enclosureGroupJson);

        enclosureGroupDto = client.updateEnclosureGroup(
                params,
                resourceId,
                null,
                false);
    }

    @Test
    public void testDeleteEnclosureGroup() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn("{}");

        String result = client.deleteEnclosureGroup(params, resourceId);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ENCLOSURE_GROUP_URI, resourceId));
        rp.setType(HttpMethodType.DELETE);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("A success delete enclosure group call returns \"Deleted\"", "Deleted", result);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testDeleteEnclosureGroupWithNullParams() {
        client.deleteEnclosureGroup(null, resourceId);
    }

    @Test
    public void testGetConfigurationScript() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(resourceConfigScript);

        String configScript = client.getConfigurationScript(params, "random-UUID");

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI, resourceId,
                SdkConstants.SCRIPT));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp));

        assertEquals("The initial script value is set to \"" + resourceConfigScript + "\"", resourceConfigScript, configScript);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetConfigurationScriptWithNullParams() {
        client.getConfigurationScript(null, "random-UUID");
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetConfigurationScriptWithNullResponse() {
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getConfigurationScript(params, "random-UUID");
    }

    @Test
    public void testUpdateConfigurationScript() {
        Mockito.when(HttpRestClient.sendStringRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.anyString()))
        .thenReturn(resourceConfigScript);

        String configScript = client.updateConfigurationScript(
                params,
                resourceId,
                resourceConfigScript);

        assertEquals("The script value must be \"" + resourceConfigScript + "\"", resourceConfigScript, configScript);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateConfigurationScriptWithNullParams() {
        client.updateConfigurationScript(
                null,
                resourceId,
                resourceConfigScript);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testUpdateConfigurationScriptWithNullResponse() {
        Mockito.when(HttpRestClient.sendStringRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.anyString()))
        .thenReturn(null);

        client.updateConfigurationScript(
                params,
                resourceId,
                resourceConfigScript);
    }

    @Test
    public void testGetId() {
        enclosureGroupJson = this.getJsonFromFile("EnclosureGroupGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class)))
        .thenReturn(enclosureGroupJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.ENCLOSURE_GROUP_URI,
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
