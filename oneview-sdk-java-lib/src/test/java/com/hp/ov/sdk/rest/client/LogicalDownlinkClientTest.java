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
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.logicaldownlinks.LogicalDownlink;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class LogicalDownlinkClientTest {

    private RestParams params;

    @Spy
    private ResourceAdaptor adaptor;
    @Mock
    private HttpRestClient restClient;
    @InjectMocks
    private LogicalDownlinkClientImpl client;

    private String resourceId = "random-UUID";
    private String resourceName = "random-name";
    private String logicalDownlinkJson = "";

    @Before
    public void setUp() throws Exception {
        params = new RestParams();
    }

    @Test
    public void testGetLogicalDownlinkById() throws IOException {
        logicalDownlinkJson = this.getJsonFromFile("LogicalDownlinkGet.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(logicalDownlinkJson);

        LogicalDownlink logicalDownlinkDto = client.getLogicalDownlinkById(params, "random-UUID");

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(logicalDownlinkDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalDownlinkByIdWithNullParams() throws IOException {
        client.getLogicalDownlinkById(null, "random-UUID");
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalDownlinkByIdWithNullResponse() throws IOException {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);
        client.getLogicalDownlinkById(params, resourceId);
    }

    @Test
    public void testGetLogicalDownlinkByIdWithoutEthernet() throws IOException {
        logicalDownlinkJson = this.getJsonFromFile("LogicalDownlinkGet.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(logicalDownlinkJson);

        LogicalDownlink logicalDownlinkDto = client.getLogicalDownlinkById(params, "random-UUID");

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI, resourceId));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(logicalDownlinkDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalDownlinkByIdWithoutEthernetWithNullParams() throws IOException {
        client.getLogicalDownlinkByIdWithoutEthernet(null, resourceId);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalDownlinkByIdWithoutEthernetWithNullResponse() throws IOException {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);
        client.getLogicalDownlinkByIdWithoutEthernet(params, resourceId);
    }

    @Test
    public void testGetLogicalDownlinkByName() throws IOException {
        logicalDownlinkJson = this.getJsonFromFile("LogicalDownlinkGetByName.json");
        when(restClient.sendRequest(any(RestParams.class))).thenReturn(logicalDownlinkJson);

        LogicalDownlink logicalDownlinkDto = client.getLogicalDownlinkByName(params, resourceName);

        RestParams rp = new RestParams();

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + resourceName + "'");
        rp.setQuery(query);

        rp.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_DOWNLINK_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(logicalDownlinkDto);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetLogicalDownlinkByNameWithNullParams() throws IOException {
        client.getLogicalDownlinkByName(null, resourceName);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetLogicalDownlinkByNameWithNullResponse() throws IOException {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getLogicalDownlinkByName(params, resourceName);
    }

    @Test (expected = SDKResourceNotFoundException.class)
    public void testGetLogicalDownlinkByNameWithNoMembers() throws IOException {
        logicalDownlinkJson = new Gson().toJson(new ResourceCollection<LogicalDownlink>());
        Mockito.when(restClient.sendRequest(Mockito.any(RestParams.class))).thenReturn(logicalDownlinkJson);
        client.getLogicalDownlinkByName(params, resourceName);
    }

    @Test
    public void testGetAllLogicalDownlinks() {
        logicalDownlinkJson = this.getJsonFromFile("LogicalDownlinkGetAll.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(logicalDownlinkJson);

        ResourceCollection<LogicalDownlink> logicalDownlinkCollection = client.getAllLogicalDownlinks(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(logicalDownlinkCollection);
        assertEquals("Based on the JSON file, the return object must have 2 elements",
                logicalDownlinkCollection.getCount(), 2);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllLogicalDownlinksWithNullParams() {
        client.getAllLogicalDownlinks(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllLogicalDownlinksWithNullResponse() {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getAllLogicalDownlinks(params);
    }

    @Test
    public void testGetAllLogicalDownlinksWithoutEthernet() {
        logicalDownlinkJson = this.getJsonFromFile("LogicalDownlinkGetAll.json");

        when(restClient.sendRequest(any(RestParams.class))).thenReturn(logicalDownlinkJson);

        ResourceCollection<LogicalDownlink> logicalDownlinkCollection = client.getAllLogicalDownlinks(params);

        RestParams rp = new RestParams();
        rp.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI));
        rp.setType(HttpMethodType.GET);

        verify(restClient, times(1)).sendRequest(eq(params));

        assertNotNull(logicalDownlinkCollection);
        assertEquals("Based on the JSON file, the return object must have 2 elements",
                logicalDownlinkCollection.getCount(), 2);
    }

    @Test (expected = SDKInvalidArgumentException.class)
    public void testGetAllLogicalDownlinksWithoutEthernetWithNullParams() {
        client.getAllLogicalDownlinksWithoutEthernet(null);
    }

    @Test (expected = SDKNoResponseException.class)
    public void testGetAllLogicalDownlinksWithoutEthernetWithNullResponse() {
        when(restClient.sendRequest(
                any(RestParams.class),
                any(JSONObject.class)))
        .thenReturn(null);

        client.getAllLogicalDownlinksWithoutEthernet(params);
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
