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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class LogicalEnclosureClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_CONFIGURATION_SCRIPT = "random-Script";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private LogicalEnclosureClient logicalEnclosureClient;

    @Test
    public void shouldGetLogicalEnclosureById() {
        logicalEnclosureClient.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, LogicalEnclosure.class);
    }

    @Test
    public void shouldGetAllLogicalEnclosure() {
        logicalEnclosureClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_ENCLOSURE_URI, LogicalEnclosure.class);
    }

    @Test
    public void shouldGetLogicalEnclosuresByName() {
        logicalEnclosureClient.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_ENCLOSURE_URI,
                LogicalEnclosure.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }


    @Test
    public void shouldCreateLogicalEnclosure() {
        AddLogicalEnclosure logicalEnclosure = new AddLogicalEnclosure();

        logicalEnclosureClient.create(logicalEnclosure, false);

        then(baseClient).should().createResource(ResourceUris.LOGICAL_ENCLOSURE_URI, logicalEnclosure, false);
    }

    @Test
    public void shouldUpdateLogicalEnclosure() {
        LogicalEnclosure logicalEnclosure = new LogicalEnclosure();

        logicalEnclosureClient.update(ANY_RESOURCE_ID, logicalEnclosure, false);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, logicalEnclosure, false);
    }

    @Test
    public void shouldPatchLogicalEnclosure() {
        Patch patch = new Patch();

        logicalEnclosureClient.patch(ANY_RESOURCE_ID, patch, false);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PATCH, expectedUri, patch);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldDeleteLogicalEnclosure() {
        logicalEnclosureClient.delete(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldUpdateLogicalEnclosureFromGroup() {
        logicalEnclosureClient.updateFromGroup(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_ENCLOSURE_UPDATE_FROM_GROUP_URI;
        Request request = new Request(HttpMethodType.PUT, expectedUri);

        request.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldUpdateLogicalEnclosureConfiguration() {
        logicalEnclosureClient.updateConfiguration(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_ENCLOSURE_CONFIGURATION_URI;
        Request request = new Request(HttpMethodType.PUT, expectedUri);

        request.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldGetConfigurationScript() {
        logicalEnclosureClient.getConfigurationScript(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_ENCLOSURE_SCRIPT_URI;
        Request request = new Request(HttpMethodType.GET, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldUpdateConfigurationScript() {
        logicalEnclosureClient.updateConfigurationScript(ANY_RESOURCE_ID,
                ANY_CONFIGURATION_SCRIPT, false);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_ENCLOSURE_SCRIPT_URI;

        then(baseClient).should().updateResource(expectedUri, ANY_CONFIGURATION_SCRIPT, false);
    }

    @Test
    public void shouldCreateLogicalEnclosureSupportDump() {
        SupportDump supportDump = new SupportDump();

        logicalEnclosureClient.createSupportDump(ANY_RESOURCE_ID, supportDump, false);

        String expectedUri = ResourceUris.LOGICAL_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_ENCLOSURE_SUPPORT_DUMP_URI;

        Request expectedRequest = new Request(HttpMethodType.POST, expectedUri, supportDump);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }
}
