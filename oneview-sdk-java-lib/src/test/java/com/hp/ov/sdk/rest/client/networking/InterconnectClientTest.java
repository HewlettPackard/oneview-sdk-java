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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_PORT_NAME = "random-Port-Name";
    private static final int ANY_SUBPORT_NUMBER = 1;

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private InterconnectClient client;

    @Test
    public void shouldGetInterconnect() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, Interconnect.class);
    }

    @Test
    public void shouldGetAllInterconnects() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.INTERCONNECT_URI, Interconnect.class);
    }

    @Test
    public void shouldGetInterconnectsByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.INTERCONNECT_URI,
                Interconnect.class,
                UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldPatchInterconnect() {
        Patch patch = new Patch();

        client.patch(ANY_RESOURCE_ID, patch, false);

        String expectedUri = ResourceUris.INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.PATCH, expectedUri, patch);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateInterconnectPort() {
        Port port = new Port();

        client.updatePort(ANY_RESOURCE_ID, port, false);

        String expectedUri = ResourceUris.INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.INTERCONNECT_PORTS_URI;

        then(baseClient).should().updateResource(expectedUri, port, false);
    }

    @Test
    public void shouldUpdateInterconnectPorts() {
        List<Port> ports = new ArrayList<>();

        client.updatePorts(ANY_RESOURCE_ID, ports, false);

        String expectedUri = ResourceUris.INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.INTERCONNECT_UPDATE_PORTS_URI;

        then(baseClient).should().updateResource(expectedUri, ports, false);
    }

    @Test
    public void shouldResetInterconnectPortProtection() {
        client.resetPortProtection(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.INTERCONNECT_RESET_PORT_PROTECTION_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetInterconnectStatistics() {
        client.getStatistics(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.INTERCONNECT_STATISTICS_URI;

        then(baseClient).should().getResource(expectedUri, InterconnectsStatistics.class);
    }

    @Test
    public void shouldGetInterconnectPortStatistics() {
        client.getPortStatistics(ANY_RESOURCE_ID, ANY_PORT_NAME);

        String expectedUri = ResourceUris.INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.INTERCONNECT_STATISTICS_URI
                + "/" + ANY_PORT_NAME;

        then(baseClient).should().getResource(expectedUri, PortStatistics.class);
    }

    @Test
    public void shouldGetInterconnectSubportStatistics() {
        client.getSubportStatistics(ANY_RESOURCE_ID, ANY_PORT_NAME, ANY_SUBPORT_NUMBER);

        String expectedUri = ResourceUris.INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.INTERCONNECT_STATISTICS_URI
                + "/" + ANY_PORT_NAME
                + "/" + ResourceUris.INTERCONNECT_SUBPORT_URI
                + "/" + ANY_SUBPORT_NUMBER;

        then(baseClient).should().getResource(expectedUri, SubportStatistics.class);
    }

    @Test
    public void shouldGetInterconnectNamedServers() {
        client.getNamedServers(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.INTERCONNECT_NAME_SERVERS_URI;

        then(baseClient).should().getResourceList(expectedUri,
                new TypeToken<List<NameServer>>(){});
    }

}
