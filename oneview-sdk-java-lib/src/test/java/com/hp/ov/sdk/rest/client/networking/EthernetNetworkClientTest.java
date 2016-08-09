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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.networking.ethernet.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class EthernetNetworkClientTest {

    private static final String ANY_ETHERNET_RESOURCE_ID = "random-UUID";
    private static final String ANY_ETHERNET_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private EthernetNetworkClient ethClient;

    @Test
    public void shouldGetEthernetNetwork() {
        ethClient.getById(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = ResourceUris.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, Network.class);
    }

    @Test
    public void shouldGetAllEthernetNetwork() {
        ethClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.ETHERNET_URI, Network.class);
    }

    @Test
    public void shouldGetEthernetNetworksByName() {
        ethClient.getByName(ANY_ETHERNET_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.ETHERNET_URI,
                Network.class, UrlParameter.getFilterByNameParameter(ANY_ETHERNET_RESOURCE_NAME));
    }

    @Test
    public void shouldCreateEthernetNetwork() {
        Network network = new Network();

        ethClient.create(network, false);

        then(baseClient).should().createResource(ResourceUris.ETHERNET_URI, network, false);
    }

    @Test
    public void shouldUpdateEthernetNetwork() {
        Network network = new Network();

        ethClient.update(ANY_ETHERNET_RESOURCE_ID, network, false);

        String expectedUri = ResourceUris.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, network, false);
    }

    @Test
    public void shouldDeleteEthernetNetwork() {
        ethClient.delete(ANY_ETHERNET_RESOURCE_ID, false);

        String expectedUri = ResourceUris.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldCreateEthernetNetworkInBulk() {
        BulkEthernetNetwork bulk = new BulkEthernetNetwork();

        ethClient.createInBulk(bulk, false);

        then(baseClient).should().createResource(ResourceUris.BULK_ETHERNET_URI, bulk, false);
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedProfiles() {
        ethClient.getAssociatedProfiles(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = ResourceUris.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID
                + "/" + ResourceUris.ASSOCIATED_PROFILES;

        then(baseClient).should().getResourceList(expectedUri, new TypeToken<List<String>>() {});
    }

    @Test
    public void shouldGetEthernetNetworkAssociatedUplinkGroups() {
        ethClient.getAssociatedUplinkGroups(ANY_ETHERNET_RESOURCE_ID);

        String expectedUri = ResourceUris.ETHERNET_URI + "/" + ANY_ETHERNET_RESOURCE_ID
                + "/" + ResourceUris.ASSOCIATED_UPLINK_GROUPS;

        then(baseClient).should().getResourceList(expectedUri, new TypeToken<List<String>>() {});
    }
}
