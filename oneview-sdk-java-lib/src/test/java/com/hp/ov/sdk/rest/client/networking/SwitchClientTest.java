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

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.SwitchPortStatistics;
import com.hp.ov.sdk.dto.networking.SwitchStatistics;
import com.hp.ov.sdk.dto.networking.switches.Switch;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class SwitchClientTest {

    private static final String ANY_SWITCH_RESOURCE_ID = "random-UUID";
    private static final String ANY_SWITCH_RESOURCE_NAME = "random-Name";
    private static final String ANY_SWITCH_RESOURCE_PORT_NAME = "random-Port-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private SwitchClient switchClient;

    @Test
    public void shouldGetSwitch() {
        switchClient.getById(ANY_SWITCH_RESOURCE_ID);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, Switch.class);
    }

    @Test
    public void shouldGetAllSwitch() {
        switchClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.SWITCHES_URI, Switch.class);
    }

    @Test
    public void shouldGetSwitchCollectionByName() {
        switchClient.getByName(ANY_SWITCH_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.SWITCHES_URI, Switch.class,
                UrlParameter.getFilterByNameParameter(ANY_SWITCH_RESOURCE_NAME));
    }

    @Test
    public void shouldAddSwitch() {
        Switch switchObj = new Switch();

        switchClient.add(switchObj, false);

        then(baseClient).should().createResource(ResourceUris.SWITCHES_URI, switchObj, false);
    }

    @Test
    public void shouldUpdateSwitch() {
        Switch switchObj = new Switch();

        switchClient.update(ANY_SWITCH_RESOURCE_ID, switchObj, false);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, switchObj, false);
    }

    @Test
    public void shouldRemoveSwitch() {
        switchClient.remove(ANY_SWITCH_RESOURCE_ID, false);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldRefreshSwitch() {
        switchClient.refresh(ANY_SWITCH_RESOURCE_ID, false);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldGetSwitchEnvironmentalConfiguration() {
        switchClient.getEnvironmentalConfiguration(ANY_SWITCH_RESOURCE_ID);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID + "/"
                + ResourceUris.ENVIRONMENT_CONFIGURATION_URI;

        then(baseClient).should().getResource(expectedUri, EnvironmentalConfiguration.class);
    }

    @Test
    public void shouldGetSwitchStatistics() {
        switchClient.getStatistics(ANY_SWITCH_RESOURCE_ID);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID + "/"
                + ResourceUris.SWITCHES_STATISTICS_URI;

        then(baseClient).should().getResource(expectedUri, SwitchStatistics.class);
    }

    @Test
    public void shouldGetSwitchPortStatistics() {
        switchClient.getPortStatistics(ANY_SWITCH_RESOURCE_ID, ANY_SWITCH_RESOURCE_PORT_NAME);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID + "/"
                + ResourceUris.SWITCHES_STATISTICS_URI + "/" + ANY_SWITCH_RESOURCE_PORT_NAME;

        then(baseClient).should().getResource(expectedUri, SwitchPortStatistics.class);
    }

    @Test
    public void shouldUpdatePorts() {
        List<Port> ports = new ArrayList<>();

        switchClient.updatePorts(ANY_SWITCH_RESOURCE_ID, ports, false);

        String expectedUri = ResourceUris.SWITCHES_URI + "/" + ANY_SWITCH_RESOURCE_ID + "/"
                + ResourceUris.SWITCHES_UPDATE_PORTS_URI;

        Request request = new Request(HttpMethod.PUT, expectedUri, ports);
        request.setForceTaskReturn(true);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }
}
