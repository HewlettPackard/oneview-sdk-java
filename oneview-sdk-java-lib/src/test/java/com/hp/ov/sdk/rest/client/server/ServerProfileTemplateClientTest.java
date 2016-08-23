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
import com.hp.ov.sdk.dto.ServerProfileTemplate;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class ServerProfileTemplateClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private ServerProfileTemplateClient client;

    @Test
    public void shouldGetServerProfileTemplateById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.SERVER_PROFILE_TEMPLATE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, ServerProfileTemplate.class);
    }

    @Test
    public void shouldGetAllServerProfileTemplates() {
        client.getAll();

        then(baseClient).should().getResourceCollection(
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI, ServerProfileTemplate.class);
    }

    @Test
    public void shouldGetServerProfileTemplatesByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.SERVER_PROFILE_TEMPLATE_URI,
                ServerProfileTemplate.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldCreateServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate = new ServerProfileTemplate();

        client.create(serverProfileTemplate, false);

        Request request = new Request(HttpMethodType.POST,
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI, serverProfileTemplate);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldDeleteServerProfileTemplate() {
        client.delete(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.SERVER_PROFILE_TEMPLATE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.DELETE, expectedUri);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldUpdateServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate = new ServerProfileTemplate();

        client.update(ANY_RESOURCE_ID, serverProfileTemplate, false);

        String expectedUri = ResourceUris.SERVER_PROFILE_TEMPLATE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, serverProfileTemplate);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldGetNewServerProfile() {
        client.getNewServerProfile(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.SERVER_PROFILE_TEMPLATE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.SERVER_PROFILE_TEMPLATE_NEW_PROFILE_URI;

        then(baseClient).should().getResource(expectedUri, ServerProfile.class);
    }

}
