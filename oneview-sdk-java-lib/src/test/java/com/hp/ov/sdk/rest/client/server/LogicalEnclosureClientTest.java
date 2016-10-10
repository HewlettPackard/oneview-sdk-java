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

import static com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient.CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient.LOGICAL_ENCLOSURE_URI;
import static com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient.SCRIPT_URI;
import static com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient.SUPPORT_DUMP_URI;
import static com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient.UPDATE_FROM_GROUP_URI;
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
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class LogicalEnclosureClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_CONFIGURATION_SCRIPT = "random-Script";

    private BaseClient baseClient = mock(BaseClient.class);
    private LogicalEnclosureClient client = Reflection.newProxy(LogicalEnclosureClient.class,
            new ClientRequestHandler<>(baseClient, LogicalEnclosureClient.class));

    @Test
    public void shouldGetLogicalEnclosureById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LogicalEnclosure.class).getType());
    }

    @Test
    public void shouldGetAllLogicalEnclosure() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_ENCLOSURE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalEnclosure>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalEnclosuresByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_ENCLOSURE_URI);

        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalEnclosure>>() {}.getType());
    }

    @Test
    public void shouldCreateLogicalEnclosure() {
        AddLogicalEnclosure logicalEnclosure = new AddLogicalEnclosure();

        client.create(logicalEnclosure, TaskTimeout.of(123));

        Request request = new Request(HttpMethod.POST, LOGICAL_ENCLOSURE_URI, logicalEnclosure);

        request.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldUpdateLogicalEnclosure() {
        LogicalEnclosure logicalEnclosure = new LogicalEnclosure();

        client.update(ANY_RESOURCE_ID, logicalEnclosure, TaskTimeout.of(321));

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, logicalEnclosure);

        request.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldPatchLogicalEnclosure() {
        Patch patch = new Patch();

        client.patch(ANY_RESOURCE_ID, patch, TaskTimeout.of(123));

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethod.PATCH, expectedUri, patch);

        request.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldDeleteLogicalEnclosure() {
        client.delete(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        request.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldUpdateLogicalEnclosureFromGroup() {
        client.updateFromGroup(ANY_RESOURCE_ID, TaskTimeout.of(123));

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID + UPDATE_FROM_GROUP_URI;
        Request request = new Request(HttpMethod.PUT, expectedUri);

        request.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldUpdateLogicalEnclosureConfiguration() {
        client.updateConfiguration(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID + CONFIGURATION_URI;
        Request request = new Request(HttpMethod.PUT, expectedUri);

        request.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldGetConfigurationScript() {
        client.getConfigurationScript(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID + SCRIPT_URI;
        Request request = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(request, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldUpdateConfigurationScript() {
        client.updateConfigurationScript(ANY_RESOURCE_ID, ANY_CONFIGURATION_SCRIPT);

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID + SCRIPT_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, ANY_CONFIGURATION_SCRIPT);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldCreateLogicalEnclosureSupportDump() {
        SupportDump supportDump = new SupportDump();

        client.createSupportDump(ANY_RESOURCE_ID, supportDump, TaskTimeout.of(123));

        String expectedUri = LOGICAL_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID + SUPPORT_DUMP_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, supportDump);

        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }
}
