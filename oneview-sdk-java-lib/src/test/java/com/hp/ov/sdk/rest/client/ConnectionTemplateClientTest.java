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

import com.hp.ov.sdk.adaptors.ConnectionTemplateAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ConnectionTemplateCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpRestClient.class)
public class ConnectionTemplateClientTest {

    private RestParams params;
    private ConnectionTemplateAdaptor adaptor;
    private ConnectionTemplateClient client;

    private String resourceId = "random-UUID";
    private String resourceName = "random-name";
    private String connectionTemplateJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
        adaptor = new ConnectionTemplateAdaptor();
        PowerMockito.mockStatic(HttpRestClient.class);
        this.client = ConnectionTemplateClientImpl.getClient();
    }

    @Test
    public void testGetConnectionTemplate() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        ConnectionTemplate connectionTemplateDto = client.getConnectionTemplate(params, "random-UUID");

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(connectionTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetConnectionTemplateWithNullParams() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getConnectionTemplate(null, "random-UUID");
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetConnectionTemplateWithNullResponse() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getConnectionTemplate(params, "random-UUID");
    }

    @Test
    public void testGetConnectionTemplateByName() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        ConnectionTemplate connectionTemplateDto = client.getConnectionTemplateByName(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI,
                UrlUtils.createFilterString(resourceName)));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(connectionTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetConnectionTemplateByNameWithNullParams() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getConnectionTemplateByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetConnectionTemplateByNameWithNullResponse() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getConnectionTemplateByName(params, resourceName);
    }

    @Test
    public void testUpdateConnectionTemplate() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        ConnectionTemplate connectionTemplateDto = adaptor.buildDto(connectionTemplateJson);
        connectionTemplateDto.setDescription("outdated");

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        connectionTemplateDto = client.updateConnectionTemplate(
                params,
                resourceId,
                connectionTemplateDto ,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(connectionTemplateDto);
        assertFalse("Template description is outdated", connectionTemplateDto.getDescription().contains("outdated"));
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateConnectionTemplateWithNullParams() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        ConnectionTemplate connectionTemplateDto = adaptor.buildDto(connectionTemplateJson);
        connectionTemplateDto.setDescription("outdated");

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        connectionTemplateDto = client.updateConnectionTemplate(
                null,
                resourceId,
                connectionTemplateDto ,
                false);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateConnectionTemplateWithNullDto() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        ConnectionTemplate connectionTemplateDto = adaptor.buildDto(connectionTemplateJson);
        connectionTemplateDto.setDescription("outdated");

        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        connectionTemplateDto = client.updateConnectionTemplate(
                params,
                resourceId,
                null ,
                false);
    }

    @Test
    public void testGetAllConnectionTemplates() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        ConnectionTemplateCollection connectionTemplateCollection = client.getAllConnectionTemplates(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(connectionTemplateCollection);
        assertEquals("Based on the JSON file, the return object must have 3 elements",
                connectionTemplateCollection.getCount(), 3);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllConnectionTemplatesWithNullParams() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getAllConnectionTemplates(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllConnectionTemplatesWithNullResponse() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetAll.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getAllConnectionTemplates(params);
    }

    @Test
    public void testGetDefaultConnectionTemplateForConnectionTemplate() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        ConnectionTemplate connectionTemplateDto = client.getDefaultConnectionTemplateForConnectionTemplate(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DEFAULT_CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(connectionTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetDefaultConnectionTemplateForConnectionTemplateWithNullParams() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getDefaultConnectionTemplateForConnectionTemplate(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetDefaultConnectionTemplateForConnectionTemplateWithNullResponse() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(null);

        client.getDefaultConnectionTemplateForConnectionTemplate(params);
    }

    @Test
    public void testGetId() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        Mockito.when(HttpRestClient.sendRequestToHPOV(
                Mockito.any(RestParams.class),
                Mockito.any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestQueryUrl(
                params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI,
                UrlUtils.createFilterString(resourceName)));
        rp.setType(HttpMethodType.GET);

        PowerMockito.verifyStatic();
        HttpRestClient.sendRequestToHPOV(Mockito.eq(rp), Mockito.any(JSONObject.class));

        assertNotNull(id);
        assertEquals("Based on the JSON file, the return ID must be \"defaultConnectionTemplate\"", "defaultConnectionTemplate", id);
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
