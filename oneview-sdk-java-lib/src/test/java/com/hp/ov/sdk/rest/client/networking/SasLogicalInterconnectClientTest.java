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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.ReplaceDriveEnclosure;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLiFirmware;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLogicalInterconnect;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class SasLogicalInterconnectClientTest {

    private static final String ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID = "random-UUID";
    private static final String ANY_SAS_LOGICAL_INTERCONNECT_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private SasLogicalInterconnectClient sasLogicalInterconnectClient;

    @Test
    public void shouldGetSasLogicalInterconnectById() {
        sasLogicalInterconnectClient.getById(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID);

        String expectedUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, SasLogicalInterconnect.class);
    }

    @Test
    public void shouldGetAllSasLogicalInterconnects() {
        sasLogicalInterconnectClient.getAll();

        then(baseClient).should().getResourceCollection(
                SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI,
                SasLogicalInterconnect.class);
    }

    @Test
    public void shouldGetSasLogicalInterconnectsByName() {
        sasLogicalInterconnectClient.getByName(ANY_SAS_LOGICAL_INTERCONNECT_NAME);

        then(baseClient).should().getResourceCollection(
                SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI,
                SasLogicalInterconnect.class,
                UrlParameter.getFilterByNameParameter(ANY_SAS_LOGICAL_INTERCONNECT_NAME));
    }

    @Test
    public void shouldGetSasLogicalInterconnectFirmware() {
        sasLogicalInterconnectClient.getFirmware(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID);

        String expectedUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + "/" + SasLogicalInterconnectClient.FIRMWARE_URI;

        then(baseClient).should().getResource(expectedUri, SasLiFirmware.class);
    }

    @Test
    public void shouldUpdateSasLogicalInterconnectFirmware() {
        SasLiFirmware firmware = new SasLiFirmware();
        sasLogicalInterconnectClient.updateFirmware(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID,
                firmware, false);

        String expectedUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + "/" + SasLogicalInterconnectClient.FIRMWARE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, firmware);

        expectedRequest.setTimeout(900000);
        expectedRequest.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldReplaceSasLogicalInterconnectDriveEnclosure() {
        ReplaceDriveEnclosure replace = new ReplaceDriveEnclosure();
        sasLogicalInterconnectClient.replaceDriveEnclosure(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID,
                replace, false);

        String expectedUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + "/" + SasLogicalInterconnectClient.REPLACE_DRIVE_ENCLOSURE_URI;

        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, replace);

        expectedRequest.setTimeout(900000);
        expectedRequest.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldApplySasLogicalInterconnectConfiguration() {
        sasLogicalInterconnectClient.applyConfiguration(ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID, false);

        String expectedUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + "/" + SasLogicalInterconnectClient.CONFIGURATION_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);

        expectedRequest.setTimeout(900000);
        expectedRequest.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateSasLogicalInterconnectCompliance() {
        SasLogicalInterconnect interconnect = new SasLogicalInterconnect();

        interconnect.setUri(SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID);

        sasLogicalInterconnectClient.updateCompliance(interconnect, false);

        String expectedUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID
                + "/" + SasLogicalInterconnectClient.COMPLIANCE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, interconnect);

        expectedRequest.setTimeout(900000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateSasLogicalInterconnectsCompliance() {
        String interconnectUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_RESOURCE_ID;
        List<String> interconnectUris = Lists.newArrayList(interconnectUri);

        sasLogicalInterconnectClient.updateCompliance(interconnectUris, false);

        String expectedUri = SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI
                + "/" + SasLogicalInterconnectClient.COMPLIANCE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, interconnectUris);

        expectedRequest.setTimeout(900000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }
}
