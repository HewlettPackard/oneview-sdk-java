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

import static com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient.ASSOCIATED_PROFILES;
import static com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient.ASSOCIATED_UPLINK_GROUPS;
import static com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient.BULK_ETHERNET_URI;
import static com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient.ETHERNET_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.ethernet.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class EthernetNetworkClientTest {

    private static final String ANY_ETHERNET_RESOURCE_ID = "random-UUID";
    private static final String ANY_ETHERNET_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private EthernetNetworkClient client = Reflection.newProxy(EthernetNetworkClient.class,
            new ClientRequestHandler<>(baseClient, EthernetNetworkClient.class));

    @Test
    public void shouldGetEthernetNetworkById() {
        client.getById(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Network.class).getType());
    }

    @Test
    public void shouldGetAllEthernetNetwork() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, ETHERNET_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Network>>() {}.getType());
    }

    @Test
    public void shouldGetEthernetNetworksByName() {
        client.getByName(ANY_ETHERNET_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, ETHERNET_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_ETHERNET_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Network>>() {}.getType());
    }

    @Test
    public void shouldCreateEthernetNetwork() {
        Network network = new Network();

        client.create(network);

        Request expectedRequest = new Request(HttpMethod.POST, ETHERNET_URI);
        expectedRequest.setEntity(network);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateEthernetNetwork() {
        Network network = new Network();

        client.update(ANY_ETHERNET_RESOURCE_ID, network);

        String expectedUri = ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(network);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldPatchEthernetNetwork() {
        Patch patch = new Patch();

        client.patch(ANY_ETHERNET_RESOURCE_ID, patch, TaskTimeout.of(123));

        String expectedUri = ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);

        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteEthernetNetwork() {
        client.delete(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldCreateEthernetNetworkInBulk() {
        BulkEthernetNetwork bulk = new BulkEthernetNetwork();

        client.createInBulk(bulk);

        String expectedUri = ETHERNET_URI + BULK_ETHERNET_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri);

        expectedRequest.setEntity(bulk);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedProfiles() {
        client.getAssociatedProfiles(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID + ASSOCIATED_PROFILES;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, new TypeToken<List<String>>() {}.getType());
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedUplinkGroups() {
        client.getAssociatedUplinkGroups(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID + ASSOCIATED_UPLINK_GROUPS;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, new TypeToken<List<String>>() {}.getType());
    }
}
