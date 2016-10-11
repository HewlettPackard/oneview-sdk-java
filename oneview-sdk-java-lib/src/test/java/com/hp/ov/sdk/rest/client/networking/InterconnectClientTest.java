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

import static com.hp.ov.sdk.rest.client.networking.InterconnectClient.INTERCONNECT_NAME_SERVERS_URI;
import static com.hp.ov.sdk.rest.client.networking.InterconnectClient.INTERCONNECT_PORTS_URI;
import static com.hp.ov.sdk.rest.client.networking.InterconnectClient.INTERCONNECT_RESET_PORT_PROTECTION_URI;
import static com.hp.ov.sdk.rest.client.networking.InterconnectClient.INTERCONNECT_STATISTICS_URI;
import static com.hp.ov.sdk.rest.client.networking.InterconnectClient.INTERCONNECT_SUBPORT_URI;
import static com.hp.ov.sdk.rest.client.networking.InterconnectClient.INTERCONNECT_UPDATE_PORTS_URI;
import static com.hp.ov.sdk.rest.client.networking.InterconnectClient.INTERCONNECT_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.InterconnectsStatistics;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.dto.networking.interconnect.NameServer;
import com.hp.ov.sdk.dto.networking.interconnect.PortStatistics;
import com.hp.ov.sdk.dto.networking.interconnect.SubportStatistics;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_PORT_NAME = "random-Port-Name";
    private static final int ANY_SUBPORT_NUMBER = 1;

    private BaseClient baseClient = mock(BaseClient.class);
    private InterconnectClient client = Reflection.newProxy(InterconnectClient.class,
            new ClientRequestHandler<>(baseClient, InterconnectClient.class));

    @Test
    public void shouldGetInterconnect() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Interconnect.class).getType());
    }

    @Test
    public void shouldGetAllInterconnects() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, INTERCONNECT_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Interconnect>>() {}.getType());
    }

    @Test
    public void shouldGetInterconnectsByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, INTERCONNECT_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Interconnect>>() {}.getType());
    }

    @Test
    public void shouldPatchInterconnect() {
        Patch patch = new Patch();

        client.patch(ANY_RESOURCE_ID, patch);

        String expectedUri = INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri);
        expectedRequest.setEntity(patch);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateInterconnectPort() {
        Port port = new Port();

        client.updatePort(ANY_RESOURCE_ID, port);

        String expectedUri = INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + INTERCONNECT_PORTS_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(port);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateInterconnectPorts() {
        List<Port> ports = new ArrayList<>();

        client.updatePorts(ANY_RESOURCE_ID, ports);

        String expectedUri = INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + INTERCONNECT_UPDATE_PORTS_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(ports);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldResetInterconnectPortProtection() {
        client.resetPortProtection(ANY_RESOURCE_ID);

        String expectedUri = INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + INTERCONNECT_RESET_PORT_PROTECTION_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetInterconnectStatistics() {
        client.getStatistics(ANY_RESOURCE_ID);

        String expectedUri = INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + INTERCONNECT_STATISTICS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(InterconnectsStatistics.class).getType());
    }

    @Test
    public void shouldGetInterconnectPortStatistics() {
        client.getPortStatistics(ANY_RESOURCE_ID, ANY_PORT_NAME);

        String expectedUri = INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + INTERCONNECT_STATISTICS_URI
                + "/" + ANY_PORT_NAME;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(PortStatistics.class).getType());
    }

    @Test
    public void shouldGetInterconnectSubportStatistics() {
        client.getSubportStatistics(ANY_RESOURCE_ID, ANY_PORT_NAME, ANY_SUBPORT_NUMBER);

        String expectedUri = INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + INTERCONNECT_STATISTICS_URI
                + "/" + ANY_PORT_NAME
                + INTERCONNECT_SUBPORT_URI
                + "/" + ANY_SUBPORT_NUMBER;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SubportStatistics.class).getType());
    }

    @Test
    public void shouldGetInterconnectNamedServers() {
        client.getNamedServers(ANY_RESOURCE_ID);

        String expectedUri = INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + INTERCONNECT_NAME_SERVERS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<NameServer>>() {}.getType());
    }

}
