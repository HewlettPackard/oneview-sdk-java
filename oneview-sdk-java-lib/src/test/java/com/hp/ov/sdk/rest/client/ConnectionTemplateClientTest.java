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
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ObjectToJsonConverter;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionTemplateClientTest {

    private RestParams params;

    @Spy
    private ResourceAdaptor adaptor;
    @Mock
    private HttpRestClient restClient;
    @InjectMocks
    private ConnectionTemplateClientImpl client;

    private String resourceId = "random-UUID";
    private String resourceName = "random-name";
    private String connectionTemplateJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }

    @Test
    public void testGetConnectionTemplate() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(connectionTemplateJson);

        ConnectionTemplate connectionTemplateDto = client.getConnectionTemplate(params, "random-UUID");

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(connectionTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetConnectionTemplateWithNullParams() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getConnectionTemplate(null, "random-UUID");
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetConnectionTemplateWithNullResponse() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getConnectionTemplate(params, "random-UUID");
    }

    @Test
    public void testGetConnectionTemplateByName() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        when(restClient.sendRequest(any(RestParams.class))).thenReturn(connectionTemplateJson);

        ConnectionTemplate connectionTemplateDto = client.getConnectionTemplateByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(connectionTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetConnectionTemplateByNameWithNullParams() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getConnectionTemplateByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetConnectionTemplateByNameWithNullResponse() throws IOException {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getConnectionTemplateByName(params, resourceName);
    }

    @Test
    public void testUpdateConnectionTemplate() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        ConnectionTemplate connectionTemplateDto = adaptor.buildResourceObject(connectionTemplateJson,
                ConnectionTemplate.class);
        connectionTemplateDto.setDescription("updated");

        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(ObjectToJsonConverter.getInstance().convertObjectToJsonString(connectionTemplateDto));

        connectionTemplateDto = client.updateConnectionTemplate(
                params,
                resourceId,
                connectionTemplateDto ,
                false);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));
        rp.setType(HttpMethodType.PUT);

        verify(restClient, times(1)).sendRequest(eq(params), any(JSONObject.class));

        assertNotNull(connectionTemplateDto);
        assertTrue("Template description is outdated", connectionTemplateDto.getDescription().contains("updated"));
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testUpdateConnectionTemplateWithNullParams() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        ConnectionTemplate connectionTemplateDto = adaptor.buildResourceObject(connectionTemplateJson,
                ConnectionTemplate.class);
        connectionTemplateDto.setDescription("outdated");

        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
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
        ConnectionTemplate connectionTemplateDto = adaptor.buildResourceObject(connectionTemplateJson,
                ConnectionTemplate.class);
        connectionTemplateDto.setDescription("outdated");

        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
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

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(connectionTemplateJson);

        ResourceCollection<ConnectionTemplate> connectionTemplateCollection = client.getAllConnectionTemplates(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(connectionTemplateCollection);
        assertEquals("Based on the JSON file, the return object must have 3 elements",
                connectionTemplateCollection.getCount(), 3);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllConnectionTemplatesWithNullParams() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetAll.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getAllConnectionTemplates(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllConnectionTemplatesWithNullResponse() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetAll.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getAllConnectionTemplates(params);
    }

    @Test
    public void testGetDefaultConnectionTemplateForConnectionTemplate() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(connectionTemplateJson);

        ConnectionTemplate connectionTemplateDto = client.getDefaultConnectionTemplateForConnectionTemplate(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DEFAULT_CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(connectionTemplateDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetDefaultConnectionTemplateForConnectionTemplateWithNullParams() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(connectionTemplateJson);

        client.getDefaultConnectionTemplateForConnectionTemplate(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetDefaultConnectionTemplateForConnectionTemplateWithNullResponse() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGet.json");
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getDefaultConnectionTemplateForConnectionTemplate(params);
    }

    @Test
    public void testGetId() {
        connectionTemplateJson = this.getJsonFromFile("ConnectionTemplatesGetByName.json");
        when(restClient.sendRequest(
                any(RestParams.class)))
        .thenReturn(connectionTemplateJson);

        String id = client.getId(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

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
