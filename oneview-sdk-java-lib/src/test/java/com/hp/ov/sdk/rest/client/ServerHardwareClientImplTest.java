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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddServer;
import com.hp.ov.sdk.dto.BiosSettings;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.RefreshStateRequest;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerHardware;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class ServerHardwareClientImplTest {

    private static final String ANY_SERVER_HARDWARE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private ServerHardwareClientImpl serverClient;

    @Test
    public void shouldReturnServerHardwareClient() {
        assertThat(ServerHardwareClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareWithoutParams() {
        this.serverClient.getServerHardware(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardware() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardware(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardware() {
        String serverHardwareValue = "{\"type\":\"server-hardware-4\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareValue);
        given(adaptor.buildResourceObject(anyString(), eq(ServerHardware.class))).willReturn(new ServerHardware());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID));

        this.serverClient.getServerHardware(new RestParams(), ANY_SERVER_HARDWARE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareValue), eq(ServerHardware.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareByNameWithoutParams() {
        String anyServerHardwareName = "random-NAME";

        this.serverClient.getServerHardwareByName(null, anyServerHardwareName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareByName() {
        String anyServerHardwareName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareByName(new RestParams(), anyServerHardwareName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoServerHardwareIsFoundForTheGivenName() {
        String anyServerHardwareName = "random-NAME";
        String serverHardwareCollectionValue = "{\"type\":\"server-hardware-list-4\"," +
                "\"members\":[]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(ServerHardware.class)))
                .willReturn(new ResourceCollection<ServerHardware>());

        this.serverClient.getServerHardwareByName(new RestParams(), anyServerHardwareName);
    }

    @Test
    public void shouldGetServerHardwareByName() {
        String anyServerHardwareName = "random-NAME";
        String serverHardwareCollectionValue = "{\"type\":\"server-hardware-list-4\"," +
                "\"members\":[{\"type\":\"server-hardware-4\"}]}";
        ResourceCollection<ServerHardware> serverHardwareCollection = new ResourceCollection<>();

        ServerHardware serverHardware = new ServerHardware();
        serverHardwareCollection.setMembers(Lists.newArrayList(serverHardware));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareCollectionValue);
        given(adaptor.buildResourceCollection(serverHardwareCollectionValue, ServerHardware.class))
                .willReturn(serverHardwareCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyServerHardwareName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI));

        ServerHardware response = this.serverClient.getServerHardwareByName(new RestParams(), anyServerHardwareName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(serverHardwareCollectionValue),
                eq(ServerHardware.class));

        assertThat(response, sameInstance(serverHardware));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllServerHardwareWithoutParams() {
        this.serverClient.getAllServerHardware(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllServerHardware() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getAllServerHardware(new RestParams());
    }

    @Test
    public void shouldGetAllServerHardware() {
        String serverHardwareCollectionValue = "{\"type\":\"server-hardware-list-4\"," +
                "\"members\":[{\"type\":\"server-hardware-4\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(ServerHardware.class)))
                .willReturn(new ResourceCollection<ServerHardware>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI));

        this.serverClient.getAllServerHardware(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(serverHardwareCollectionValue),
                eq(ServerHardware.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateServerHardwareWithoutParams() {
        this.serverClient.createServerHardware(null, new AddServer(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateServerHardwareWithoutRequest() {
        this.serverClient.createServerHardware(new RestParams(), null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForCreateServerHardware() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.createServerHardware(new RestParams(), new AddServer(), false);
    }

    @Test
    public void shouldCreateServerHardware() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        AddServer addServer = new AddServer();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(addServer), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI));

        TaskResourceV2 task = this.serverClient.createServerHardware(new RestParams(), addServer, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);

        assertThat(task, is(sameInstance(taskResource)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteServerHardwareWithoutParams() {
        this.serverClient.deleteServerHardware(null, ANY_SERVER_HARDWARE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteServerHardware() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.deleteServerHardware(new RestParams(), ANY_SERVER_HARDWARE_ID, false);
    }

    @Test
    public void shouldDeleteServerHardware() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID));

        this.serverClient.deleteServerHardware(new RestParams(), ANY_SERVER_HARDWARE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareBiosWithoutParams() {
        this.serverClient.getServerHardwareBios(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareBios() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareBios(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardwareBios() {
        String serverHardwareBiosValue = "{\"biosSettingsState\":\"Applied\", \"currentSettings\":[]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareBiosValue);
        given(adaptor.buildResourceObject(anyString(), eq(BiosSettings.class))).willReturn(new BiosSettings());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID, ResourceUris.SERVER_HARDWARE_BIOS_URI));

        this.serverClient.getServerHardwareBios(new RestParams(), ANY_SERVER_HARDWARE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareBiosValue), eq(BiosSettings.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareEnvironmentalConfigurationWithoutParams() {
        this.serverClient.getServerHardwareEnvironmentConfiguration(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareEnvironmentalConfiguration() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareEnvironmentConfiguration(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardwareEnvironmentalConfiguration() {
        String serverHardwareEnvironmentalConfigurationValue = "{\"powerHistorySupported\":true," +
                "\"thermalHistorySupported\":true}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareEnvironmentalConfigurationValue);
        given(adaptor.buildResourceObject(anyString(), eq(EnvironmentalConfiguration.class)))
                .willReturn(new EnvironmentalConfiguration());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID, ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        this.serverClient.getServerHardwareEnvironmentConfiguration(new RestParams(), ANY_SERVER_HARDWARE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareEnvironmentalConfigurationValue),
                eq(EnvironmentalConfiguration.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwareEnvironmentalConfigurationWithoutParams() {
        this.serverClient.updateServerHardwareEnvironmentConfiguration(null, ANY_SERVER_HARDWARE_ID,
                new EnvironmentalConfigurationUpdate());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwareEnvironmentalConfigurationWithoutRequest() {
        this.serverClient.updateServerHardwareEnvironmentConfiguration(new RestParams(), ANY_SERVER_HARDWARE_ID, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateServerHardwareEnvironmentalConfiguration() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.updateServerHardwareEnvironmentConfiguration(new RestParams(), ANY_SERVER_HARDWARE_ID,
                new EnvironmentalConfigurationUpdate());
    }
    @Test
    public void shouldUpdateServerHardwareEnvironmentalConfiguration() {
        String serverHardwareEnvironmentalConfigurationValue = "{\"powerHistorySupported\":true," +
                "\"thermalHistorySupported\":true}";
        EnvironmentalConfigurationUpdate update = new EnvironmentalConfigurationUpdate();
        EnvironmentalConfiguration result = new EnvironmentalConfiguration();
        JSONObject jsonObject = new JSONObject();

        given(adaptor.buildJsonRequest(eq(update), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject)))
                .willReturn(serverHardwareEnvironmentalConfigurationValue);
        given(adaptor.buildResourceObject(serverHardwareEnvironmentalConfigurationValue,
                EnvironmentalConfiguration.class)).willReturn(result);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID, ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        EnvironmentalConfiguration configuration = this.serverClient.updateServerHardwareEnvironmentConfiguration(
                new RestParams(), ANY_SERVER_HARDWARE_ID, update);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(serverHardwareEnvironmentalConfigurationValue,
                EnvironmentalConfiguration.class);

        assertThat(configuration, is(sameInstance(result)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareIloSsoUrlWithoutParams() {
        this.serverClient.getServerHardwareIloSsoUrl(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareIloSsoUrl() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareIloSsoUrl(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardwareIloSsoUrl() {
        String serverHardwareIloSsoUrlValue = "{\"iloSsoUrl\":" +
                "\"https://172.18.6.10:443/Proxy/SSO?TKN=927a1a2cebd83bf23104e5276834923d16cb708\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareIloSsoUrlValue);
        given(adaptor.buildResourceObject(anyString(), eq(IloSsoUrlResult.class)))
                .willReturn(new IloSsoUrlResult());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID, ResourceUris.SERVER_HARDWARE_ILO_SSO_URI));

        this.serverClient.getServerHardwareIloSsoUrl(new RestParams(), ANY_SERVER_HARDWARE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareIloSsoUrlValue), eq(IloSsoUrlResult.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareJavaRemoteConsoleUrlWithoutParams() {
        this.serverClient.getServerHardwareJavaRemoteConsoleUrl(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareJavaRemoteConsoleUrl() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareJavaRemoteConsoleUrl(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardwareJavaRemoteConsoleUrl() {
        String serverHardwareJavaRemoteConsoleUrlValue = "{\"javaRemoteConsoleUrl\":" +
                "\"https://172.18.6.10:443/Proxy/SSO?TKN=927a1a2cebd83bf23104e5276834923d16cb708\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareJavaRemoteConsoleUrlValue);
        given(adaptor.buildResourceObject(anyString(), eq(JavaRemoteConsoleUrlResult.class)))
                .willReturn(new JavaRemoteConsoleUrlResult());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID,
                ResourceUris.SERVER_HARDWARE_JAVA_REMOTE_CONSOLE_URI));

        this.serverClient.getServerHardwareJavaRemoteConsoleUrl(new RestParams(), ANY_SERVER_HARDWARE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareJavaRemoteConsoleUrlValue),
                eq(JavaRemoteConsoleUrlResult.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwareMpFirmwareVersionWithoutParams() {
        this.serverClient.updateServerHardwareMpFirmwareVersion(null, ANY_SERVER_HARDWARE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateServerHardwareMpFirmwareVersion() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.updateServerHardwareMpFirmwareVersion(new RestParams(), ANY_SERVER_HARDWARE_ID, false);
    }

    @Test
    public void shouldUpdateServerHardwareMpFirmwareVersion() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(anyString(), eq(TaskResourceV2.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID,
                ResourceUris.SERVER_HARDWARE_MP_FIRMWARE_URI));

        this.serverClient.updateServerHardwareMpFirmwareVersion(new RestParams(), ANY_SERVER_HARDWARE_ID, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(taskResourceValue), eq(TaskResourceV2.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwarePowerStateWithoutParams() {
        this.serverClient.updateServerHardwarePowerState(null, ANY_SERVER_HARDWARE_ID,
                new ServerPowerControlRequest(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwarePowerStateWithoutRequest() {
        this.serverClient.updateServerHardwarePowerState(new RestParams(), ANY_SERVER_HARDWARE_ID, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateServerHardwarePowerState() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.updateServerHardwarePowerState(new RestParams(), ANY_SERVER_HARDWARE_ID,
                new ServerPowerControlRequest(), false);
    }

    @Test
    public void shouldUpdateServerHardwarePowerState() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        ServerPowerControlRequest request = new ServerPowerControlRequest();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(request), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID, ResourceUris.POWER_STATE_URI));

        this.serverClient.updateServerHardwarePowerState(new RestParams(), ANY_SERVER_HARDWARE_ID, request, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwareRefreshStateWithoutParams() {
        this.serverClient.updateServerHardwareRefreshState(null, ANY_SERVER_HARDWARE_ID,
                new RefreshStateRequest(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateServerHardwareRefreshStateWithoutRequest() {
        this.serverClient.updateServerHardwareRefreshState(new RestParams(), ANY_SERVER_HARDWARE_ID, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForUpdateServerHardwareRefreshState() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.updateServerHardwareRefreshState(new RestParams(), ANY_SERVER_HARDWARE_ID,
                new RefreshStateRequest(), false);
    }

    @Test
    public void shouldUpdateServerHardwareRefreshState() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        RefreshStateRequest request = new RefreshStateRequest();
        JSONObject jsonObject = new JSONObject();
        TaskResourceV2 taskResource = new TaskResourceV2();

        given(adaptor.buildJsonRequest(eq(request), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), eq(jsonObject))).willReturn(taskResourceValue);
        given(adaptor.buildResourceObject(taskResourceValue, TaskResourceV2.class))
                .willReturn(taskResource);
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(taskResource);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID,
                ResourceUris.SERVER_HARDWARE_REFRESH_STATE_URI));

        this.serverClient.updateServerHardwareRefreshState(new RestParams(), ANY_SERVER_HARDWARE_ID, request, false);

        then(restClient).should().sendRequest(expectedRestParams, jsonObject);
        then(adaptor).should().buildResourceObject(taskResourceValue, TaskResourceV2.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareRemoteConsoleUrlWithoutParams() {
        this.serverClient.getServerHardwareRemoteConsoleUrl(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareRemoteConsoleUrl() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareRemoteConsoleUrl(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardwareRemoteConsoleUrl() {
        String serverHardwareRemoteConsoleUrlValue = "{\"remoteConsoleUrl\":" +
                "\"https://172.18.6.10:443/Proxy/SSO?TKN=927a1a2cebd83bf23104e5276834923d16cb708\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareRemoteConsoleUrlValue);
        given(adaptor.buildResourceObject(anyString(), eq(RemoteConsoleUrlResult.class)))
                .willReturn(new RemoteConsoleUrlResult());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID,
                ResourceUris.SERVER_HARDWARE_REMOTE_CONSOLE_URI));

        this.serverClient.getServerHardwareRemoteConsoleUrl(new RestParams(), ANY_SERVER_HARDWARE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareRemoteConsoleUrlValue),
                eq(RemoteConsoleUrlResult.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetServerHardwareUtilizationWithoutParams() {
        this.serverClient.getServerHardwareUtilization(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetServerHardwareUtilization() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.serverClient.getServerHardwareUtilization(new RestParams(), null);
    }

    @Test
    public void shouldGetServerHardwareUtilization() {
        String serverHardwareUtilizationValue = "{}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(serverHardwareUtilizationValue);
        given(adaptor.buildResourceObject(anyString(), eq(UtilizationData.class))).willReturn(new UtilizationData());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SERVER_HARDWARE_URI, ANY_SERVER_HARDWARE_ID,
                ResourceUris.SERVER_HARDWARE_UTILIZATION_URI));

        this.serverClient.getServerHardwareUtilization(new RestParams(), ANY_SERVER_HARDWARE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(serverHardwareUtilizationValue), eq(UtilizationData.class));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        ServerHardwareClient localServerHardwareClient = spy(this.serverClient);

        doReturn(new ServerHardware()).when(localServerHardwareClient).getServerHardwareByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localServerHardwareClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String serverHardwareId = "AF5FBE7D-271F-4F4F-B4B4-D0017970E590";
        String serverHardwareUri = "/rest/server-hardware/" + serverHardwareId;

        //given
        ServerHardwareClient localServerHardwareClient = spy(this.serverClient);
        ServerHardware serverHardware = new ServerHardware();

        serverHardware.setUri(serverHardwareUri);

        doReturn(serverHardware).when(localServerHardwareClient).getServerHardwareByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localServerHardwareClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(serverHardwareId)));
    }

}
