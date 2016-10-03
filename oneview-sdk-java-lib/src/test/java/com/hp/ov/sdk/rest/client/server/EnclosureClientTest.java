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

import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_ACTIVE_OA_SSO_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_COMPLIANCE_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_FW_BASELINE_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_OA_SSO_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_REFRESH_STATE_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_SCRIPT_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_STANDBY_OA_SSO_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENCLOSURE_UTILIZATION_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ENVIRONMENT_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ROLE_ACTIVE;
import static com.hp.ov.sdk.rest.client.server.EnclosureClient.ROLE_STANDBY;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class EnclosureClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_CONFIGURATION_SCRIPT = "random-Script";

    private BaseClient baseClient = mock(BaseClient.class);
    private EnclosureClient enclosureClient = Reflection.newProxy(EnclosureClient.class,
            new ClientRequestHandler<>(baseClient, EnclosureClient.class));

    @Test
    public void shouldGetEnclosureById() {
        enclosureClient.getById(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Enclosure.class).getType());
    }

    @Test
    public void shouldGetAllEnclosure() {
        enclosureClient.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, ENCLOSURE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Enclosure>>() {}.getType());
    }

    @Test
    public void shouldGetEnclosureByName() {
        enclosureClient.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, ENCLOSURE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Enclosure>>() {}.getType());
    }

    @Test
    public void shouldAddEnclosure() {
        AddEnclosure addEnclosure = new AddEnclosure();

        enclosureClient.add(addEnclosure);

        Request expectedRequest = new Request(HttpMethod.POST, ENCLOSURE_URI);
        expectedRequest.setEntity(addEnclosure);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateEnclosure() {
        Enclosure enclosure = new Enclosure();

        enclosureClient.update(ANY_RESOURCE_ID, enclosure);

        String expectedUri = ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(enclosure);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldPatchEnclosureV200() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_200);

        Patch patch = new Patch();

        enclosureClient.patch(ANY_RESOURCE_ID, patch, TaskTimeout.of(123));

        String expectedUri = ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);

        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldPatchEnclosureV300() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_300);

        Patch patch = new Patch();

        enclosureClient.patch(ANY_RESOURCE_ID, patch, TaskTimeout.of(123));

        String expectedUri = ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);

        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRemoveEnclosure() {
        enclosureClient.remove(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateEnclosureConfiguration() {
        enclosureClient.updateConfiguration(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetEnclosureConfigurationScript() {
        enclosureClient.getConfigurationScript(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_SCRIPT_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldUpdateEnclosureConfigurationScript() {
        enclosureClient.updateConfigurationScript(ANY_RESOURCE_ID, ANY_CONFIGURATION_SCRIPT);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_SCRIPT_URI;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, ANY_CONFIGURATION_SCRIPT);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetEnclosureActiveOaSsoUrlV120() {
        enclosureClient.getActiveOaSsoUrl_V120(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_ACTIVE_OA_SSO_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SsoUrlData.class).getType());
    }

    @Test
    public void shouldGetEnclosureActiveOaSsoUrlV200() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_200);

        enclosureClient.getActiveOaSsoUrl(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_OA_SSO_URI
                + ROLE_ACTIVE;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SsoUrlData.class).getType());
    }

    @Test
    public void shouldGetEnclosureStandbyOaSsoUrlV120() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_120);

        enclosureClient.getStandbyOaSsoUrl_V120(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_STANDBY_OA_SSO_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SsoUrlData.class).getType());
    }

    @Test
    public void shouldGetEnclosureStandbyOaSsoUrlV200() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_200);

        enclosureClient.getStandbyOaSsoUrl(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_OA_SSO_URI
                + ROLE_STANDBY;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SsoUrlData.class).getType());
    }

    @Test
    public void shouldUpdateEnclosureCompliance() {
        enclosureClient.updateCompliance(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_COMPLIANCE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateEnclosureFwBaseline() {
        FwBaselineConfig fwBaselineConfig = new FwBaselineConfig();

        enclosureClient.updateFwBaseline(ANY_RESOURCE_ID, fwBaselineConfig, TaskTimeout.of(321));

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_FW_BASELINE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, fwBaselineConfig);

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetEnclosureUtilization() {
        enclosureClient.getUtilization(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_UTILIZATION_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(UtilizationData.class).getType());
    }

    @Test
    public void shouldGetEnclosureEnvironmentalConfiguration() {
        enclosureClient.getEnvironmentalConfiguration(ANY_RESOURCE_ID);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENVIRONMENT_CONFIGURATION_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EnvironmentalConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateEnclosureEnvironmentalConfiguration() {
        EnvironmentalConfigurationUpdate updateEnvironmentalConfiguration
                = new EnvironmentalConfigurationUpdate();

        enclosureClient.updateEnvironmentalConfiguration(ANY_RESOURCE_ID,
                updateEnvironmentalConfiguration);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENVIRONMENT_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri,
                updateEnvironmentalConfiguration);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EnvironmentalConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateEnclosureRefreshState() {
        RefreshStateConfig refreshStateConfig = new RefreshStateConfig();

        enclosureClient.updateRefreshState(ANY_RESOURCE_ID, refreshStateConfig);

        String expectedUri = ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + ENCLOSURE_REFRESH_STATE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, refreshStateConfig);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
