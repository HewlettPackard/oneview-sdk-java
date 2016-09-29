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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.ethernet.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
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

        String expectedUri = EthernetNetworkClient.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Network.class).getType());
    }

    @Test
    public void shouldGetAllEthernetNetwork() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, EthernetNetworkClient.ETHERNET_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Network>>() {}.getType());
    }

    @Test
    public void shouldGetEthernetNetworksByName() {
        client.getByName(ANY_ETHERNET_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, EthernetNetworkClient.ETHERNET_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_ETHERNET_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Network>>() {}.getType());
    }

    @Test
    public void shouldCreateEthernetNetwork() {
        Network network = new Network();

        client.create(network);

        Request expectedRequest = new Request(HttpMethod.POST, EthernetNetworkClient.ETHERNET_URI);
        expectedRequest.setEntity(network);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateEthernetNetwork() {
        Network network = new Network();

        client.update(ANY_ETHERNET_RESOURCE_ID, network);

        String expectedUri = EthernetNetworkClient.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(network);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldDeleteEthernetNetwork() {
        client.delete(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = EthernetNetworkClient.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldCreateEthernetNetworkInBulk() {
        BulkEthernetNetwork bulk = new BulkEthernetNetwork();

        client.createInBulk(bulk);

        Request expectedRequest = new Request(HttpMethod.POST, EthernetNetworkClient.BULK_ETHERNET_URI);
        expectedRequest.setEntity(bulk);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedProfiles() {
        client.getAssociatedProfiles(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = EthernetNetworkClient.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID
                + "/" + EthernetNetworkClient.ASSOCIATED_PROFILES;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, new TypeToken<List<String>>() {}.getType());
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedUplinkGroups() {
        client.getAssociatedUplinkGroups(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = EthernetNetworkClient.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID
                + "/" + EthernetNetworkClient.ASSOCIATED_UPLINK_GROUPS;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, new TypeToken<List<String>>() {}.getType());
    }
}
