/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.networking;

import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClient.LOGICAL_INTERCONNECT_GROUPS_DEFAULT_SETTINGS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClient.LOGICAL_INTERCONNECT_GROUPS_SETTINGS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClient.LOGICAL_INTERCONNECT_GROUPS_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class LogicalInterconnectGroupClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_SETTING_ID = "settings-id";

    private BaseClient baseClient = mock(BaseClient.class);
    private LogicalInterconnectGroupClient client = Reflection.newProxy(LogicalInterconnectGroupClient.class,
            new ClientRequestHandler<>(baseClient, LogicalInterconnectGroupClient.class));

    @Test
    public void shouldGetLogicalInterconnectGroupById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_GROUPS_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                TypeToken.of(LogicalInterconnectGroup.class).getType());
    }

    @Test
    public void shouldGetAllLogicalInterconnectGroup() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_INTERCONNECT_GROUPS_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalInterconnectGroup>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalInterconnectGroupCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_INTERCONNECT_GROUPS_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalInterconnectGroup>>() {}.getType());
    }

    @Test
    public void shouldCreateLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = new LogicalInterconnectGroup();

        client.create(logicalInterconnectGroup);

        Request expectedRequest = new Request(HttpMethod.POST, LOGICAL_INTERCONNECT_GROUPS_URI, logicalInterconnectGroup);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = new LogicalInterconnectGroup();

        client.update(ANY_RESOURCE_ID, logicalInterconnectGroup);

        String expectedUri = LOGICAL_INTERCONNECT_GROUPS_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, logicalInterconnectGroup);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteLogicalInterconnectGroup() {
        client.delete(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_GROUPS_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetDefaultInterconnectSettings() {
        client.getDefaultInterconnectSettings();

        String expectedUri = LOGICAL_INTERCONNECT_GROUPS_URI + LOGICAL_INTERCONNECT_GROUPS_DEFAULT_SETTINGS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(InterconnectSettingsV2.class).getType());
    }

    @Test
    public void shouldGetInterconnectSettings() {
        client.getInterconnectSettings(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_GROUPS_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_GROUPS_SETTINGS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(InterconnectSettingsV2.class).getType());
    }

    @Test
    public void shouldGetInterconnectSettingsUsingSettingId() {
        client.getInterconnectSettings_V120(ANY_RESOURCE_ID, ANY_SETTING_ID);

        String expectedUri = LOGICAL_INTERCONNECT_GROUPS_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_GROUPS_SETTINGS_URI
                + "/" + ANY_SETTING_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(InterconnectSettingsV2.class).getType());
    }

}
