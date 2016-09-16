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

package com.hp.ov.sdk.rest.client.networking;

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.networking.sasinterconnects.SasInterConnectRefreshRequest;
import com.hp.ov.sdk.dto.networking.sasinterconnects.SasInterconnect;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class SasInterconnectClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private SasInterconnectClient client;

    @Test
    public void shouldGetSasInterconnect() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = SasInterconnectClient.SAS_INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, SasInterconnect.class);
    }

    @Test
    public void shouldGetAllSasInterconnect() {
        client.getAll();

        then(baseClient).should().getResourceCollection(SasInterconnectClient.SAS_INTERCONNECT_URI, SasInterconnect.class);
    }

    @Test
    public void shouldGetSasInterconnectsByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(SasInterconnectClient.SAS_INTERCONNECT_URI,
                SasInterconnect.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldPatchInterconnect() {
        Patch patch = new Patch();

        client.patch(ANY_RESOURCE_ID, patch, false);

        String expectedUri = SasInterconnectClient.SAS_INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);
        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldRefreshSasInterconnectState() {
        SasInterConnectRefreshRequest requestBody = new SasInterConnectRefreshRequest();
        client.refreshState(ANY_RESOURCE_ID, requestBody, false);

        String expectedUri = SasInterconnectClient.SAS_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + SasInterconnectClient.SAS_INTERCONNECT_URI_REFRESH;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, requestBody);
        expectedRequest.setForceTaskReturn(true);
        expectedRequest.setTimeout(120000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

}
