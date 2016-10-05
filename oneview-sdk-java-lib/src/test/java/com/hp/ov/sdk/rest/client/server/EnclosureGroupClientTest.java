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

import static com.hp.ov.sdk.rest.client.server.EnclosureGroupClient.ENCLOSURE_GROUP_SCRIPT_URI;
import static com.hp.ov.sdk.rest.client.server.EnclosureGroupClient.ENCLOSURE_GROUP_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class EnclosureGroupClientTest {

    private static final String ANY_ENCLOSURE_GROUP_RESOURCE_ID = "random-UUID";
    private static final String ANY_ENCLOSURE_GROUP_RESOURCE_NAME = "random-Name";
    private static final String ANY_ENCLOSURE_GROUP_CONFIGURATION_SCRIPT = "random-Script";

    private BaseClient baseClient = mock(BaseClient.class);
    private EnclosureGroupClient client = Reflection.newProxy(EnclosureGroupClient.class,
            new ClientRequestHandler<>(baseClient, EnclosureGroupClient.class));

    @Test
    public void shouldGetEnclosureGroupById() {
        client.getById(ANY_ENCLOSURE_GROUP_RESOURCE_ID);

        String expectedUri = ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EnclosureGroup.class).getType());
    }

    @Test
    public void shouldGetAllEnclosureGroup() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, ENCLOSURE_GROUP_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<EnclosureGroup>>() {}.getType());
    }

    @Test
    public void shouldGetEnclosureGroupsByName() {
        client.getByName(ANY_ENCLOSURE_GROUP_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, ENCLOSURE_GROUP_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_ENCLOSURE_GROUP_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<EnclosureGroup>>() {}.getType());
    }

    @Test
    public void shouldCreateEnclosureGroup() {
        EnclosureGroup enclosureGroup = new EnclosureGroup();

        client.create(enclosureGroup);

        Request request = new Request(HttpMethod.POST, ENCLOSURE_GROUP_URI, enclosureGroup);

        then(baseClient).should().executeRequest(request, TypeToken.of(EnclosureGroup.class).getType());
    }

    @Test
    public void shouldUpdateEnclosureGroup() {
        EnclosureGroup enclosureGroup = new EnclosureGroup();

        client.update(ANY_ENCLOSURE_GROUP_RESOURCE_ID, enclosureGroup);

        String expectedUri = ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, enclosureGroup);

        then(baseClient).should().executeRequest(request, TypeToken.of(EnclosureGroup.class).getType());
    }

    @Test
    public void shouldDeleteEnclosureGroup() {
        client.delete(ANY_ENCLOSURE_GROUP_RESOURCE_ID);

        String expectedUri = ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldGetConfigurationScript() {
        client.getConfigurationScript(ANY_ENCLOSURE_GROUP_RESOURCE_ID);

        String expectedUri = ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID + ENCLOSURE_GROUP_SCRIPT_URI;
        Request request = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(request, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldUpdateConfigurationScript() {
        client.updateConfigurationScript(ANY_ENCLOSURE_GROUP_RESOURCE_ID,
                ANY_ENCLOSURE_GROUP_CONFIGURATION_SCRIPT);

        String expectedUri = ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID + ENCLOSURE_GROUP_SCRIPT_URI;
        Request request = new Request(HttpMethod.PUT, expectedUri, ANY_ENCLOSURE_GROUP_CONFIGURATION_SCRIPT);

        request.setContentType(ContentType.TEXT_PLAIN);

        then(baseClient).should().executeRequest(request, TypeToken.of(String.class).getType());
    }
}
