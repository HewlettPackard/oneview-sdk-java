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

import static com.hp.ov.sdk.rest.client.networking.NetworkSetClient.NETWORK_SET_URI;
import static com.hp.ov.sdk.rest.client.networking.NetworkSetClient.WITHOUT_ETHERNET_URI;
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
import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class NetworkSetClientTest {

    private static final String ANY_NETWORK_SET_RESOURCE_ID = "random-UUID";
    private static final String ANY_NETWORK_SET_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private NetworkSetClient client = Reflection.newProxy(NetworkSetClient.class,
            new ClientRequestHandler<>(baseClient, NetworkSetClient.class));

    @Test
    public void shouldGetNetworkSet() {
        client.getById(ANY_NETWORK_SET_RESOURCE_ID);

        String expectedUri = NETWORK_SET_URI + "/" + ANY_NETWORK_SET_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(NetworkSet.class).getType());
    }

    @Test
    public void shouldGetAllNetworkSet() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, NETWORK_SET_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<NetworkSet>>() {}.getType());
    }

    @Test
    public void shouldGetNetworkSetsByName() {
        client.getByName(ANY_NETWORK_SET_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, NETWORK_SET_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_NETWORK_SET_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<NetworkSet>>() {}.getType());
    }

    @SuppressWarnings("serial")
    @Test
    public void shouldGetAllNetworkSetWithoutEthernet() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAllWithoutEthernet();

        String expectedUri = NETWORK_SET_URI + WITHOUT_ETHERNET_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<NetworkSet>>() {}.getType());
    }

    @Test
    public void shouldGetNetworkSetByIdWithoutEthernet() {
        client.getByIdWithoutEthernet(ANY_NETWORK_SET_RESOURCE_ID);

        String expectedUri = NETWORK_SET_URI + "/" + ANY_NETWORK_SET_RESOURCE_ID + WITHOUT_ETHERNET_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(NetworkSet.class).getType());
    }

    @Test
    public void shouldCreateNetworkSet() {
        NetworkSet networkSet = new NetworkSet();

        client.create(networkSet);

        Request expectedRequest = new Request(HttpMethod.POST, NETWORK_SET_URI);
        expectedRequest.setEntity(networkSet);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateNetworkSet() {
        NetworkSet networkSet = new NetworkSet();

        client.update(ANY_NETWORK_SET_RESOURCE_ID, networkSet);

        String expectedUri = NETWORK_SET_URI + "/" + ANY_NETWORK_SET_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(networkSet);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteNetworkSet() {
        client.delete(ANY_NETWORK_SET_RESOURCE_ID);

        String expectedUri = NETWORK_SET_URI + "/" + ANY_NETWORK_SET_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
