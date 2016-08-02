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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.servers.enclosure.AddEnclosure;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class EnclosureClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_CONFIGURATION_SCRIPT = "random-Script";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private EnclosureClient enclosureClient;

    @Test
    public void shouldGetEnclosureById() {
        enclosureClient.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, Enclosure.class);
    }

    @Test
    public void shouldGetAllEnclosureGroup() {
        enclosureClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.ENCLOSURE_URI, Enclosure.class);
    }

    @Test
    public void shouldGetEnclosureGroupsByName() {
        enclosureClient.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.ENCLOSURE_URI,
                Enclosure.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldAddEnclosure() {
        AddEnclosure addEnclosure = new AddEnclosure();

        enclosureClient.add(addEnclosure, false);

        Request expectedRequest = new Request(HttpMethodType.POST,
                ResourceUris.ENCLOSURE_URI, addEnclosure);

        expectedRequest.setTimeout(1200000);
        
        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateEnclosure() {
        Enclosure enclosure = new Enclosure();

        enclosureClient.update(ANY_RESOURCE_ID, enclosure, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, enclosure);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldPatchEnclosureV200() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_200);

        Patch patch = new Patch();

        enclosureClient.patch(ANY_RESOURCE_ID, patch, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.PATCH, expectedUri, patch);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldPatchEnclosureV300() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_300);

        Patch patch = new Patch();

        enclosureClient.patch(ANY_RESOURCE_ID, patch, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.PATCH, expectedUri, patch);

        expectedRequest.setTimeout(1200000);
        expectedRequest.setContentType(ContentType.APPLICATION_JSON_PATCH);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldRemoveEnclosure() {
        enclosureClient.remove(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.DELETE, expectedUri);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateEnclosureConfiguration() {
        enclosureClient.updateConfiguration(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetEnclosureConfigurationScript() {
        enclosureClient.getConfigurationScript(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_SCRIPT_URI;
        Request expectedRequest = new Request(HttpMethodType.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, String.class);
    }

    @Test
    public void shouldUpdateEnclosureConfigurationScript() {
        enclosureClient.updateConfigurationScript(ANY_RESOURCE_ID, ANY_CONFIGURATION_SCRIPT, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_SCRIPT_URI;

        then(baseClient).should().updateResource(expectedUri, ANY_CONFIGURATION_SCRIPT, false);
    }

    @Test
    public void shouldGetEnclosureActiveOaSsoUrlV120() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_120);

        enclosureClient.getActiveOaSsoUrl(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_ACTIVE_OA_SSO_URI;

        then(baseClient).should().getResource(expectedUri, SsoUrlData.class);
    }

    @Test
    public void shouldGetEnclosureActiveOaSsoUrlV200() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_200);

        enclosureClient.getActiveOaSsoUrl(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_OA_SSO_URI
                + "?role=Active";

        then(baseClient).should().getResource(expectedUri, SsoUrlData.class);
    }

    @Test
    public void shouldGetEnclosureStandbyOaSsoUrlV120() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_120);

        enclosureClient.getStandbyOaSsoUrl(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_STANDBY_OA_SSO_URI;

        then(baseClient).should().getResource(expectedUri, SsoUrlData.class);
    }

    @Test
    public void shouldGetEnclosureStandbyOaSsoUrlV200() {
        given(this.baseClient.getApiVersion()).willReturn(ApiVersion.V_200);

        enclosureClient.getStandbyOaSsoUrl(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_OA_SSO_URI
                + "?role=Standby";

        then(baseClient).should().getResource(expectedUri, SsoUrlData.class);
    }

    @Test
    public void shouldUpdateEnclosureCompliance() {
        enclosureClient.updateCompliance(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_COMPLIANCE_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateEnclosureFwBaseline() {
        FwBaselineConfig fwBaselineConfig = new FwBaselineConfig();

        enclosureClient.updateFwBaseline(ANY_RESOURCE_ID, fwBaselineConfig, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_FW_BASELINE_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, fwBaselineConfig);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetEnclosureUtilization() {
        enclosureClient.getUtilization(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_UTILIZATION_URI;

        then(baseClient).should().getResource(expectedUri, UtilizationData.class);
    }

    @Test
    public void shouldGetEnclosureEnvironmentalConfiguration() {
        enclosureClient.getEnvironmentalConfiguration(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENVIRONMENT_CONFIGURATION_URI;

        then(baseClient).should().getResource(expectedUri, EnvironmentalConfiguration.class);
    }

    @Test
    public void shouldUpdateEnclosureEnvironmentalConfiguration() {
        EnvironmentalConfigurationUpdate updateEnvironmentalConfiguration
                = new EnvironmentalConfigurationUpdate();

        enclosureClient.updateEnvironmentalConfiguration(ANY_RESOURCE_ID,
                updateEnvironmentalConfiguration);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENVIRONMENT_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri,
                updateEnvironmentalConfiguration);

        then(baseClient).should().executeRequest(expectedRequest, EnvironmentalConfiguration.class);
    }

    @Test
    public void shouldUpdateEnclosureRefreshState() {
        RefreshStateConfig refreshStateConfig = new RefreshStateConfig();

        enclosureClient.updateRefreshState(ANY_RESOURCE_ID, refreshStateConfig, false);

        String expectedUri = ResourceUris.ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_REFRESH_STATE_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, refreshStateConfig);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

}
