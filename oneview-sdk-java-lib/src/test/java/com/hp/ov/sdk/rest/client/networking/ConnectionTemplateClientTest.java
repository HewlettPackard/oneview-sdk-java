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

package com.hp.ov.sdk.rest.client.networking;

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionTemplateClientTest {

    private static final String ANY_CONNECTION_TEMPLATE_RESOURCE_ID = "random-UUID";
    private static final String ANY_CONNECTION_TEMPLATE_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private ConnectionTemplateClient connectionTemplateClient;

    @Test
    public void shouldGetConnectionTemplate() {
        connectionTemplateClient.getById(ANY_CONNECTION_TEMPLATE_RESOURCE_ID);

        String expectedUri = ResourceUris.CONNECTION_TEMPLATE_URI + "/" + ANY_CONNECTION_TEMPLATE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, ConnectionTemplate.class);
    }

    @Test
    public void shouldGetAllConnectionTemplate() {
        connectionTemplateClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.CONNECTION_TEMPLATE_URI, ConnectionTemplate.class);
    }

    @Test
    public void shouldGetConnectionTemplateCollectionByName() {
        connectionTemplateClient.getByName(ANY_CONNECTION_TEMPLATE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.CONNECTION_TEMPLATE_URI,
                ConnectionTemplate.class, UrlParameter.getFilterByNameParameter(ANY_CONNECTION_TEMPLATE_RESOURCE_NAME));
    }

    @Test
    public void shouldGetDefaultConnectionTemplate() {
        connectionTemplateClient.getDefaultConnectionTemplate();

        String expectedUri = ResourceUris.DEFAULT_CONNECTION_TEMPLATE_URI;

        then(baseClient).should().getResource(expectedUri, ConnectionTemplate.class);
    }

    @Test
    public void shouldUpdateConnectionTemplate() {
        ConnectionTemplate connectionTemplate = new ConnectionTemplate();

        connectionTemplateClient.update(ANY_CONNECTION_TEMPLATE_RESOURCE_ID, connectionTemplate);

        String expectedUri = ResourceUris.CONNECTION_TEMPLATE_URI + "/" + ANY_CONNECTION_TEMPLATE_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, connectionTemplate);

        then(baseClient).should().executeRequest(request, ConnectionTemplate.class);
    }

}
