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
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class FcNetworkClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String FC_NETWORK_URI = "/rest/fc-networks";

    private BaseClient baseClient = mock(BaseClient.class);
    private FcNetworkClient client = Reflection.newProxy(FcNetworkClient.class,
            new ClientRequestHandler<>(baseClient, FcNetworkClient.class));

    @Test
    public void shouldGetFcNetworkById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = FC_NETWORK_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(FcNetwork.class).getType());
    }

    @Test
    public void shouldGetAllFcNetworks() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, FC_NETWORK_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<FcNetwork>>() {}.getType());
    }

    @Test
    public void shouldGetFcNetworksByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, FC_NETWORK_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<FcNetwork>>() {}.getType());
    }

    @Test
    public void shouldCreateFcNetwork() {
        FcNetwork fcNetwork = new FcNetwork();

        client.create(fcNetwork);

        Request expectedRequest = new Request(HttpMethod.POST, FC_NETWORK_URI);
        expectedRequest.setEntity(fcNetwork);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateFcNetwork() {
        FcNetwork fcNetwork = new FcNetwork();

        client.update(ANY_RESOURCE_ID, fcNetwork);

        String expectedUri = FC_NETWORK_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(fcNetwork);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldDeleteFcNetwork() {
        client.delete(ANY_RESOURCE_ID);

        String expectedUri = FC_NETWORK_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

}
