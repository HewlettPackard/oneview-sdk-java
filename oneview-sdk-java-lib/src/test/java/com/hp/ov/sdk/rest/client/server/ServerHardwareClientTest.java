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

package com.hp.ov.sdk.rest.client.server;

import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.ENVIRONMENT_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.POWER_STATE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_BIOS_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_FIRMWARE;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_FIRMWARE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_ILO_SSO_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_MP_FIRMWARE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_REFRESH_STATE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_REMOTE_CONSOLE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerHardwareClient.SERVER_HARDWARE_UTILIZATION_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.servers.serverhardware.AddServer;
import com.hp.ov.sdk.dto.servers.serverhardware.BiosSettings;
import com.hp.ov.sdk.dto.servers.serverhardware.IloSsoUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.RefreshStateRequest;
import com.hp.ov.sdk.dto.servers.serverhardware.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerFirmwareInventory;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerPowerControlRequest;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class ServerHardwareClientTest {

    private static final String ANY_SERVER_HARDWARE_RESOURCE_ID = "random-UUID";
    private static final String ANY_SERVER_HARDWARE_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private ServerHardwareClient serverHardwareClient = Reflection.newProxy(ServerHardwareClient.class,
            new ClientRequestHandler<>(baseClient, ServerHardwareClient.class));

    @Test
    public void shouldGetServerHardware() {
        serverHardwareClient.getById(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerHardware.class).getType());
    }

    @Test
    public void shouldGetAllServerHardware() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        serverHardwareClient.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, SERVER_HARDWARE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ServerHardware>>() {}.getType());
    }

    @Test
    public void shouldGetServerHardwareByName() {
        serverHardwareClient.getByName(ANY_SERVER_HARDWARE_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, SERVER_HARDWARE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_SERVER_HARDWARE_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ServerHardware>>() {}.getType());
    }

    @Test
    public void shouldAddServerHardware() {
        AddServer addServer = new AddServer();

        serverHardwareClient.add(addServer);

        Request expectedRequest = new Request(HttpMethod.POST, SERVER_HARDWARE_URI);
        expectedRequest.setEntity(addServer);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRemoveServerHardware() {
        serverHardwareClient.remove(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateServerHardwarePowerState() {
        ServerPowerControlRequest serverPowerControlRequest = new ServerPowerControlRequest();

        serverHardwareClient.updatePowerState(ANY_SERVER_HARDWARE_RESOURCE_ID, serverPowerControlRequest);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + POWER_STATE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(serverPowerControlRequest);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateServerHardwareRefreshState() {
        RefreshStateRequest refreshStateRequest = new RefreshStateRequest();

        serverHardwareClient.updateRefreshState(ANY_SERVER_HARDWARE_RESOURCE_ID, refreshStateRequest);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_REFRESH_STATE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(refreshStateRequest);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetServerHardwareBios() {
        serverHardwareClient.getBios(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_BIOS_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(BiosSettings.class).getType());
    }

    @Test
    public void shouldGetServerHardwareEnvironmentConfiguration() {
        serverHardwareClient.getEnvironmentConfiguration(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + ENVIRONMENT_CONFIGURATION_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EnvironmentalConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateServerHardwareEnvironmentConfiguration() {
        EnvironmentalConfigurationUpdate update = new EnvironmentalConfigurationUpdate();

        this.serverHardwareClient.updateEnvironmentConfiguration(ANY_SERVER_HARDWARE_RESOURCE_ID, update);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + ENVIRONMENT_CONFIGURATION_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(update);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EnvironmentalConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateServerHardwareMpFirmwareVersion() {
        this.serverHardwareClient.updateMpFirmwareVersion(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_MP_FIRMWARE_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetServerHardwareIloSsoUrl() {
        serverHardwareClient.getIloSsoUrl(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_ILO_SSO_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(IloSsoUrlResult.class).getType());
    }

    @Test
    public void shouldGetServerHardwareJavaRemoteConsoleUrl() {
        serverHardwareClient.getJavaRemoteConsoleUrl(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(JavaRemoteConsoleUrlResult.class).getType());
    }

    @Test
    public void shouldGetServerHardwareRemoteConsoleUrl() {
        serverHardwareClient.getRemoteConsoleUrl(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_REMOTE_CONSOLE_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(RemoteConsoleUrlResult.class).getType());
    }

    @Test
    public void shouldGetServerHardwareUtilization() {
        serverHardwareClient.getUtilization(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_UTILIZATION_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(UtilizationData.class).getType());
    }

    @Test
    public void shouldGetServerFirmwareInventoryByFilter() {
        FirmwareInventoryFilter filter = new FirmwareInventoryFilter();
        filter.setComponentLocation(ANY_SERVER_HARDWARE_RESOURCE_NAME);
        filter.setComponentName(ANY_SERVER_HARDWARE_RESOURCE_NAME);
        filter.setComponentVersion(ANY_SERVER_HARDWARE_RESOURCE_NAME);
        filter.setServerName(ANY_SERVER_HARDWARE_RESOURCE_NAME);
        filter.setServerModel(ANY_SERVER_HARDWARE_RESOURCE_NAME);

        serverHardwareClient.getServerFirmwareInventoryByFilter(filter);

        String expectedUri = SERVER_HARDWARE_URI + SERVER_HARDWARE_FIRMWARE;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);
        expectedRequest.addQuery(new UrlParameter("filter", "components.componentLocation='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        expectedRequest.addQuery(new UrlParameter("filter", "components.componentName='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        expectedRequest.addQuery(new UrlParameter("filter", "components.componentVersion='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        expectedRequest.addQuery(new UrlParameter("filter", "serverName='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        expectedRequest.addQuery(new UrlParameter("filter", "serverModel='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ServerFirmwareInventory>>() {}.getType());
    }

    @Test
    public void shouldGetServerFirmwareInventory() {
        serverHardwareClient.getServerFirmwareInventory(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + SERVER_HARDWARE_FIRMWARE_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerFirmwareInventory.class).getType());
    }

    @Test
    public void shouldPatchServerHardware() {
        Patch patch = new Patch();

        serverHardwareClient.patch(ANY_SERVER_HARDWARE_RESOURCE_ID, patch, TaskTimeout.of(123));

        String expectedUri = SERVER_HARDWARE_URI + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID;
        Request request = new Request(HttpMethod.PATCH, expectedUri, patch);

        request.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(request);
    }

}
