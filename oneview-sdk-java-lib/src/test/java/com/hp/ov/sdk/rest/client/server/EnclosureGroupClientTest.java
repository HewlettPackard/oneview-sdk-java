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

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class EnclosureGroupClientTest {

    private static final String ANY_ENCLOSURE_GROUP_RESOURCE_ID = "random-UUID";
    private static final String ANY_ENCLOSURE_GROUP_RESOURCE_NAME = "random-Name";
    private static final String ANY_ENCLOSURE_GROUP_CONFIGURATION_SCRIPT = "random-Script";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private EnclosureGroupClient enclosureGroupClient;

    @Test
    public void shouldGetEnclosureGroup() {
        enclosureGroupClient.getById(ANY_ENCLOSURE_GROUP_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, EnclosureGroup.class);
    }

    @Test
    public void shouldGetAllEnclosureGroup() {
        enclosureGroupClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.ENCLOSURE_GROUP_URI, EnclosureGroup.class);
    }

    @Test
    public void shouldGetEnclosureGroupsByName() {
        enclosureGroupClient.getByName(ANY_ENCLOSURE_GROUP_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.ENCLOSURE_GROUP_URI,
                EnclosureGroup.class, UrlParameter.getFilterByNameParameter(ANY_ENCLOSURE_GROUP_RESOURCE_NAME));
    }

    @Test
    public void shouldCreateEnclosureGroup() {
        EnclosureGroup enclosureGroup = new EnclosureGroup();

        enclosureGroupClient.create(enclosureGroup);

        Request request = new Request(HttpMethodType.POST, ResourceUris.ENCLOSURE_GROUP_URI, enclosureGroup);

        then(baseClient).should().executeRequest(request, EnclosureGroup.class);
    }

    @Test
    public void shouldUpdateEnclosureGroup() {
        EnclosureGroup enclosureGroup = new EnclosureGroup();

        enclosureGroupClient.update(ANY_ENCLOSURE_GROUP_RESOURCE_ID, enclosureGroup);

        String expectedUri = ResourceUris.ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, enclosureGroup);

        then(baseClient).should().executeRequest(request, EnclosureGroup.class);
    }

    @Test
    public void shouldDeleteEnclosureGroup() {
        enclosureGroupClient.delete(ANY_ENCLOSURE_GROUP_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_GROUP_URI + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID;
        Request request = new Request(HttpMethodType.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldGetConfigurationScript() {
        enclosureGroupClient.getConfigurationScript(ANY_ENCLOSURE_GROUP_RESOURCE_ID);

        String expectedUri = ResourceUris.ENCLOSURE_GROUP_URI
                + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_GROUP_SCRIPT_URI;
        Request request = new Request(HttpMethodType.GET, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldUpdateConfigurationScript() {
        enclosureGroupClient.updateConfigurationScript(ANY_ENCLOSURE_GROUP_RESOURCE_ID,
                ANY_ENCLOSURE_GROUP_CONFIGURATION_SCRIPT);

        String expectedUri = ResourceUris.ENCLOSURE_GROUP_URI
                + "/" + ANY_ENCLOSURE_GROUP_RESOURCE_ID
                + "/" + ResourceUris.ENCLOSURE_GROUP_SCRIPT_URI;
        Request request = new Request(HttpMethodType.PUT, expectedUri, ANY_ENCLOSURE_GROUP_CONFIGURATION_SCRIPT);

        request.setContentType(ContentType.TEXT_PLAIN);

        then(baseClient).should().executeRequest(request, String.class);
    }
}
