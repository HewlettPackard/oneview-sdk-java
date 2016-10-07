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

import static com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectClient.COMPLIANCE_URI;
import static com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectClient.CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectClient.FIRMWARE_URI;
import static com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectClient.REPLACE_DRIVE_ENCLOSURE_URI;
import static com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.ReplaceDriveEnclosure;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLiFirmware;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLogicalInterconnect;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class SasLogicalInterconnectClientTest {

    private static final String ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID = "random-UUID";
    private static final String ANY_SAS_LOGICAL_INTERCONNECT_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private SasLogicalInterconnectClient client = Reflection.newProxy(SasLogicalInterconnectClient.class,
            new ClientRequestHandler<>(baseClient, SasLogicalInterconnectClient.class));

    @Test
    public void shouldGetSasLogicalInterconnectById() {
        client.getById(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID);

        String expectedUri = SAS_LOGICAL_INTERCONNECT_URI + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                TypeToken.of(SasLogicalInterconnect.class).getType());
    }

    @Test
    public void shouldGetAllSasLogicalInterconnects() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, SAS_LOGICAL_INTERCONNECT_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SasLogicalInterconnect>>() {}.getType());
    }

    @Test
    public void shouldGetSasLogicalInterconnectsByName() {
        client.getByName(ANY_SAS_LOGICAL_INTERCONNECT_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, SAS_LOGICAL_INTERCONNECT_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_SAS_LOGICAL_INTERCONNECT_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SasLogicalInterconnect>>() {}.getType());
    }

    @Test
    public void shouldGetSasLogicalInterconnectFirmware() {
        client.getFirmware(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID);

        String expectedUri = SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + FIRMWARE_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SasLiFirmware.class).getType());
    }

    @Test
    public void shouldUpdateSasLogicalInterconnectFirmware() {
        SasLiFirmware firmware = new SasLiFirmware();
        client.updateFirmware(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID, firmware);

        String expectedUri = SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + FIRMWARE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, firmware);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldReplaceSasLogicalInterconnectDriveEnclosure() {
        ReplaceDriveEnclosure replace = new ReplaceDriveEnclosure();
        client.replaceDriveEnclosure(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID, replace, TaskTimeout.of(900000));

        String expectedUri = SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + REPLACE_DRIVE_ENCLOSURE_URI;

        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, replace);

        expectedRequest.setTimeout(900000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldApplySasLogicalInterconnectConfiguration() {
        client.applyConfiguration(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID, TaskTimeout.of(900000));

        String expectedUri = SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + CONFIGURATION_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setTimeout(900000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateSasLogicalInterconnectCompliance() {
        SasLogicalInterconnect interconnect = new SasLogicalInterconnect();

        interconnect.setUri(SAS_LOGICAL_INTERCONNECT_URI + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID);

        client.updateCompliance(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID, interconnect, TaskTimeout.of(900000));

        String expectedUri = SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + COMPLIANCE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, interconnect);
        expectedRequest.setTimeout(900000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateSasLogicalInterconnectsCompliance() {
        String interconnectUri = SAS_LOGICAL_INTERCONNECT_URI + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID;
        List<String> interconnectUris = Lists.newArrayList(interconnectUri);

        client.updateCompliance(interconnectUris, TaskTimeout.of(900000));

        String expectedUri = SAS_LOGICAL_INTERCONNECT_URI + COMPLIANCE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, interconnectUris);

        expectedRequest.setTimeout(900000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
