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

import static com.hp.ov.sdk.rest.client.networking.SwitchClient.SWITCHES_ENVIRONMENT_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.networking.SwitchClient.SWITCHES_REFRESH_URI;
import static com.hp.ov.sdk.rest.client.networking.SwitchClient.SWITCHES_STATISTICS_URI;
import static com.hp.ov.sdk.rest.client.networking.SwitchClient.SWITCHES_UPDATE_PORTS_URI;
import static com.hp.ov.sdk.rest.client.networking.SwitchClient.SWITCHES_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.SwitchPortStatistics;
import com.hp.ov.sdk.dto.networking.SwitchStatistics;
import com.hp.ov.sdk.dto.networking.switches.Switch;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class SwitchClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_PORT_NAME = "random-port-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private SwitchClient client = Reflection.newProxy(SwitchClient.class,
            new ClientRequestHandler<>(baseClient, SwitchClient.class));

    @Test
    public void shouldGetSwitch() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = SWITCHES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Switch.class).getType());
    }

    @Test
    public void shouldGetAllSwitch() {
        client.getAll();
        Request expectedRequest = new Request(HttpMethod.GET, SWITCHES_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Switch>>() {}.getType());
    }

    @Test
    public void shouldGetSwitchCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, SWITCHES_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Switch>>() {}.getType());
    }

    @Test
    public void shouldAddSwitch() {
        Switch switchObj = new Switch();

        client.add(switchObj);

        Request expectedRequest = new Request(HttpMethod.POST, SWITCHES_URI);
        expectedRequest.setEntity(switchObj);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateSwitch() {
        Switch switchObj = new Switch();

        client.update(ANY_RESOURCE_ID, switchObj);

        String expectedUri = SWITCHES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(switchObj);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRemoveSwitch() {
        client.remove(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = SWITCHES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRefreshSwitch() {
        client.refresh(ANY_RESOURCE_ID);

        String expectedUri = SWITCHES_URI
                + "/" + ANY_RESOURCE_ID
                + SWITCHES_REFRESH_URI;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri);
        expectedRequest.setEntity(ClientRequestHandler.EMPTY_OBJECT);
        expectedRequest.setContentType(ContentType.APPLICATION_JSON_PATCH);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetSwitchEnvironmentalConfiguration() {
        client.getEnvironmentalConfiguration(ANY_RESOURCE_ID);

        String expectedUri = SWITCHES_URI
                + "/" + ANY_RESOURCE_ID
                + SWITCHES_ENVIRONMENT_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EnvironmentalConfiguration.class).getType());
    }

    @Test
    public void shouldGetSwitchStatistics() {
        client.getStatistics(ANY_RESOURCE_ID);

        String expectedUri = SWITCHES_URI
                + "/" + ANY_RESOURCE_ID
                + SWITCHES_STATISTICS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SwitchStatistics.class).getType());
    }

    @Test
    public void shouldGetSwitchPortStatistics() {
        client.getPortStatistics(ANY_RESOURCE_ID, ANY_PORT_NAME);

        String expectedUri = SWITCHES_URI
                + "/" + ANY_RESOURCE_ID
                + SWITCHES_STATISTICS_URI
                + "/" + ANY_PORT_NAME;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SwitchPortStatistics.class).getType());
    }

    @Test
    public void shouldUpdatePorts() {
        List<Port> ports = new ArrayList<>();

        client.updatePorts(ANY_RESOURCE_ID, ports);

        String expectedUri = SWITCHES_URI
                + "/" + ANY_RESOURCE_ID
                + SWITCHES_UPDATE_PORTS_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(ports);
        expectedRequest.setForceReturnTask(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }
}
