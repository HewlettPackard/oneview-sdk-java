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

import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.BiosSettings;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.RefreshStateRequest;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.servers.serverhardware.AddServer;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerFirmwareInventory;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerPowerControlRequest;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class ServerHardwareClientTest {

    private static final String ANY_SERVER_HARDWARE_RESOURCE_ID = "random-UUID";
    private static final String ANY_SERVER_HARDWARE_RESOURCE_NAME = "random-Name";
    
    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private ServerHardwareClient serverHardwareClient;

    @Test
    public void shouldGetServerHardware() {
        serverHardwareClient.getById(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, ServerHardware.class);
    }

    @Test
    public void shouldGetAllServerHardware() {
        serverHardwareClient.getAll();

        then(baseClient).should().getResourceCollection(ServerHardwareClient.SERVER_HARDWARE_URI, ServerHardware.class);
    }

    @Test
    public void shouldGetServerHardwareByName() {
        serverHardwareClient.getByName(ANY_SERVER_HARDWARE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ServerHardwareClient.SERVER_HARDWARE_URI,
                ServerHardware.class, UrlParameter.getFilterByNameParameter(ANY_SERVER_HARDWARE_RESOURCE_NAME));
    }

    @Test
    public void shouldAddServerHardware() {
        AddServer addServer = new AddServer();

        serverHardwareClient.add(addServer, false);

        Request expectedRequest = new Request(HttpMethodType.POST, ServerHardwareClient.SERVER_HARDWARE_URI, addServer);

        expectedRequest.setTimeout(240000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldRemoveServerHardware() {
        serverHardwareClient.remove(ANY_SERVER_HARDWARE_RESOURCE_ID, false);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.DELETE, expectedUri);

        expectedRequest.setTimeout(240000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateServerHardwarePowerState() {
        ServerPowerControlRequest serverPowerControlRequest = new ServerPowerControlRequest();

        serverHardwareClient.updatePowerState(ANY_SERVER_HARDWARE_RESOURCE_ID, serverPowerControlRequest, false);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.POWER_STATE_URI;

        then(baseClient).should().updateResource(expectedUri, serverPowerControlRequest, false);
    }

    @Test
    public void shouldUpdateServerHardwareRefreshState() {
        RefreshStateRequest refreshStateRequest = new RefreshStateRequest();

        serverHardwareClient.updateRefreshState(ANY_SERVER_HARDWARE_RESOURCE_ID, refreshStateRequest, false);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.SERVER_HARDWARE_REFRESH_STATE_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, refreshStateRequest);

        expectedRequest.setTimeout(240000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetServerHardwareBios() {
        serverHardwareClient.getBios(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.SERVER_HARDWARE_BIOS_URI;

        then(baseClient).should().getResource(expectedUri, BiosSettings.class);
    }

    @Test
    public void shouldGetServerHardwareEnvironmentConfiguration() {
        serverHardwareClient.getEnvironmentConfiguration(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ResourceUris.ENVIRONMENT_CONFIGURATION_URI;

        then(baseClient).should().getResource(expectedUri, EnvironmentalConfiguration.class);
    }

    @Test
    public void shouldUpdateServerHardwareEnvironmentConfiguration() {
        EnvironmentalConfigurationUpdate update = new EnvironmentalConfigurationUpdate();

        this.serverHardwareClient.updateEnvironmentConfiguration(ANY_SERVER_HARDWARE_RESOURCE_ID, update);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.ENVIRONMENT_CONFIGURATION_URI;

        Request request = new Request(HttpMethodType.PUT, expectedUri, update);

        then(baseClient).should().executeRequest(request, EnvironmentalConfiguration.class);
    }

    @Test
    public void shouldUpdateServerHardwareMpFirmwareVersion() {
        this.serverHardwareClient.updateMpFirmwareVersion(ANY_SERVER_HARDWARE_RESOURCE_ID, false);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.SERVER_HARDWARE_MP_FIRMWARE_URI;

        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri);

        expectedRequest.setTimeout(240000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetServerHardwareIloSsoUrl() {
        serverHardwareClient.getIloSsoUrl(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.SERVER_HARDWARE_ILO_SSO_URI;

        then(baseClient).should().getResource(expectedUri, IloSsoUrlResult.class);
    }

    @Test
    public void shouldGetServerHardwareJavaRemoteConsoleUrl() {
        serverHardwareClient.getJavaRemoteConsoleUrl(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI;

        then(baseClient).should().getResource(expectedUri, JavaRemoteConsoleUrlResult.class);
    }

    @Test
    public void shouldGetServerHardwareRemoteConsoleUrl() {
        serverHardwareClient.getRemoteConsoleUrl(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.SERVER_HARDWARE_REMOTE_CONSOLE_URI;

        then(baseClient).should().getResource(expectedUri, RemoteConsoleUrlResult.class);
    }

    @Test
    public void shouldGetServerHardwareUtilization() {
        serverHardwareClient.getUtilization(ANY_SERVER_HARDWARE_RESOURCE_ID);

        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI
                + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID
                + "/" + ServerHardwareClient.SERVER_HARDWARE_UTILIZATION_URI;

        then(baseClient).should().getResource(expectedUri, UtilizationData.class);
    }

    @Test
    public void shouldGetServerFirmwareInventoryByFilter() {
        serverHardwareClient.getServerFirmwareInventoryByFilter(Optional.of(ANY_SERVER_HARDWARE_RESOURCE_NAME),
                Optional.of(ANY_SERVER_HARDWARE_RESOURCE_NAME), Optional.of(ANY_SERVER_HARDWARE_RESOURCE_NAME),
                Optional.of(ANY_SERVER_HARDWARE_RESOURCE_NAME), Optional.of(ANY_SERVER_HARDWARE_RESOURCE_NAME));
        
        List<UrlParameter> parameters = new ArrayList<>();
        parameters.add(new UrlParameter("filter", "components.componentName='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        parameters.add(new UrlParameter("filter", "components.componentLocation='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        parameters.add(new UrlParameter("filter", "components.componentVersion='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        parameters.add(new UrlParameter("filter", "serverName='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
        parameters.add(new UrlParameter("filter", "serverModel='" + ANY_SERVER_HARDWARE_RESOURCE_NAME + "'"));
    
        then(baseClient).should().getResourceCollection(ServerHardwareClient.SERVER_HARDWARE_FIRMWARE, ServerFirmwareInventory.class,
                parameters.toArray(new UrlParameter[parameters.size()]));
    }
    
    
    @Test
    public void shouldGetServerFirmwareInventory() {
        serverHardwareClient.getServerFirmwareInventory(ANY_SERVER_HARDWARE_RESOURCE_ID);
       
        String expectedUri = ServerHardwareClient.SERVER_HARDWARE_URI + "/" + ANY_SERVER_HARDWARE_RESOURCE_ID + "/"
                + ServerHardwareClient.SERVER_HARDWARE_FIRMWARE_URI;
  
        then(baseClient).should().getResource(expectedUri, ServerFirmwareInventory.class);
    }
    
    @Test
    public void shouldPatchServerHardware() {
        Patch patch = new Patch();
        patch.setOp(Patch.PatchOperation.add);
        patch.setPath("/scopeUris/-");
        patch.setValue("/rest/scopes/random-UUID");

        serverHardwareClient.patch(ANY_SERVER_HARDWARE_RESOURCE_ID, patch, false);

        String resourceUri = UrlUtils.createUrl(ServerHardwareClient.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_RESOURCE_ID);

        Request request = new Request(HttpMethodType.PATCH, resourceUri, patch);
        request.setTimeout(240000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }
    
}
