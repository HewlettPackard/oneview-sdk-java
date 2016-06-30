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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class LogicalInterconnectGroupClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_SETTING_ID = "settings-id";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private LogicalInterconnectGroupClient client;

    @Test
    public void shouldGetLogicalInterconnectGroup() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, LogicalInterconnectGroup.class);
    }

    @Test
    public void shouldGetAllLogicalInterconnectGroup() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, LogicalInterconnectGroup.class);
    }

    @Test
    public void shouldGetLogicalInterconnectGroupCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI,
                LogicalInterconnectGroup.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldCreateLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = new LogicalInterconnectGroup();

        client.create(logicalInterconnectGroup, false);

        then(baseClient).should().createResource(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, logicalInterconnectGroup, false);
    }

    @Test
    public void shouldUpdateLogicalInterconnectGroup() {
        List<UplinkSet> uplinkSets = new ArrayList<UplinkSet>();
        LogicalInterconnectGroup lig = new LogicalInterconnectGroup();
        lig.setUplinkSets(uplinkSets);

        given(baseClient.getResource(anyString(), any(Class.class)))
        .willReturn(lig);

        client.update(ANY_RESOURCE_ID, uplinkSets, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, lig, false);
    }

    @Test
    public void shouldDeleteLogicalInterconnectGroup() {
        client.delete(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldGetDefaultInterconnectSettings() {
        client.getDefaultInterconnectSettings();

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI + "/" + SdkConstants.DEFAULT_SETTINGS;

        then(baseClient).should().getResource(expectedUri, InterconnectSettingsV2.class);
    }

    @Test
    public void shouldGetInterconnectSettings() {
        client.getInterconnectSettings(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI
                + "/"
                + ANY_RESOURCE_ID
                + "/"
                + SdkConstants.SETTINGS;

        then(baseClient).should().getResource(expectedUri, InterconnectSettingsV2.class);
    }

    @Test
    public void shouldGetInterconnectSettingsUsingSettingId() {
        client.getInterconnectSettings(ANY_RESOURCE_ID, ANY_SETTING_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI
                + "/"
                + ANY_RESOURCE_ID
                + "/"
                + SdkConstants.SETTINGS
                + "/"
                + ANY_SETTING_ID;

        then(baseClient).should().getResource(expectedUri, InterconnectSettingsV2.class);
    }

}
