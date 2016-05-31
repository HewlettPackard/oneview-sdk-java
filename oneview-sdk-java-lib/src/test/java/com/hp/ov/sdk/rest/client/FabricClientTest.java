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
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.Fabric;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class FabricClientTest {

    private RestParams params;

    @Spy
    private ResourceAdaptor adaptor;
    @Mock
    private HttpRestClient restClient;
    @InjectMocks
    private FabricClientImpl client;

    private String resourceId = "random-UUID";
    private String resourceName = "random-name";
    private String fabricJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }


    @Test
    public void testGetFabric() throws IOException {
        fabricJson = this.getJsonFromFile("FabricsGet.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(fabricJson);

        Fabric fabricDto = client.getFabricById(params, "random-UUID");

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(fabricDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetFabricWithNullParams() throws IOException {
        client.getFabricById(null, "random-UUID");
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetFabricWithNullResponse() throws IOException {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getFabricById(params, "random-UUID");
    }

    @Test
    public void testGetFabricByName() throws IOException {
        fabricJson = this.getJsonFromFile("FabricsGetByName.json");
        when(restClient.sendRequest(any(RestParams.class))).thenReturn(fabricJson);

        Fabric fabricDto = client.getFabricByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(fabricDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetFabricByNameWithNullParams() throws IOException {
        client.getFabricByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetFabricByNameWithNullResponse() throws IOException {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getFabricByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetFabricByNameWithNoMembers() throws IOException {
        fabricJson = new Gson().toJson(new ResourceCollection<Fabric>());

        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn(fabricJson);

        client.getFabricByName(params, resourceName);
    }

    @Test
    public void testGetAllFabrics() {
        fabricJson = this.getJsonFromFile("FabricsGetAll.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(fabricJson);

        ResourceCollection<Fabric> fabricCollection = client.getAllFabrics(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(fabricCollection);
        assertEquals("Based on the JSON file, the return object must have 1 elements",
                fabricCollection.getCount(), 1);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllFabricsWithNullParams() {
        client.getAllFabrics(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllFabricsWithNullResponse() {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getAllFabrics(params);
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
