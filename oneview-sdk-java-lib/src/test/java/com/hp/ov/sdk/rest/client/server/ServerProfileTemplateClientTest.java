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

import static com.hp.ov.sdk.rest.client.server.ServerProfileTemplateClient.SERVER_PROFILE_TEMPLATE_NEW_PROFILE_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileTemplateClient.SERVER_PROFILE_TEMPLATE_TRANSFORMATION_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileTemplateClient.SERVER_PROFILE_TEMPLATE_URI;
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
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofiletemplate.ServerProfileTemplate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class ServerProfileTemplateClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private ServerProfileTemplateClient client = Reflection.newProxy(ServerProfileTemplateClient.class,
            new ClientRequestHandler<>(baseClient, ServerProfileTemplateClient.class));

    @Test
    public void shouldGetServerProfileTemplateById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = SERVER_PROFILE_TEMPLATE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerProfileTemplate.class).getType());
    }

    @Test
    public void shouldGetAllServerProfileTemplates() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, SERVER_PROFILE_TEMPLATE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ServerProfileTemplate>>() {}.getType());
    }

    @Test
    public void shouldGetServerProfileTemplatesByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, SERVER_PROFILE_TEMPLATE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ServerProfileTemplate>>() {}.getType());
    }

    @Test
    public void shouldCreateServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate = new ServerProfileTemplate();

        client.create(serverProfileTemplate, TaskTimeout.of(1200000));

        Request request = new Request(HttpMethod.POST, SERVER_PROFILE_TEMPLATE_URI, serverProfileTemplate);
        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldDeleteServerProfileTemplate() {
        client.delete(ANY_RESOURCE_ID, TaskTimeout.of(1200000));

        String expectedUri = SERVER_PROFILE_TEMPLATE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldUpdateServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate = new ServerProfileTemplate();

        client.update(ANY_RESOURCE_ID, serverProfileTemplate);

        String expectedUri = SERVER_PROFILE_TEMPLATE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, serverProfileTemplate);

        then(baseClient).should().executeMonitorableRequest(request);
    }

    @Test
    public void shouldGetNewServerProfile() {
        client.getNewServerProfile(ANY_RESOURCE_ID);

        String expectedUri = SERVER_PROFILE_TEMPLATE_URI
                + "/" + ANY_RESOURCE_ID
                + SERVER_PROFILE_TEMPLATE_NEW_PROFILE_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerProfile.class).getType());
    }

    @Test
    public void shouldGetServerProfileTemplateTransformation() {
        client.getTransformation(ANY_RESOURCE_ID, ANY_RESOURCE_ID, ANY_RESOURCE_ID);

        String expectedUri = SERVER_PROFILE_TEMPLATE_URI
                + "/" + ANY_RESOURCE_ID
                + SERVER_PROFILE_TEMPLATE_TRANSFORMATION_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        expectedRequest.addQuery(new UrlParameter("serverHardwareTypeUri", ANY_RESOURCE_ID));
        expectedRequest.addQuery(new UrlParameter("enclosureGroupUri", ANY_RESOURCE_ID));

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerProfileTemplate.class).getType());
    }

}
