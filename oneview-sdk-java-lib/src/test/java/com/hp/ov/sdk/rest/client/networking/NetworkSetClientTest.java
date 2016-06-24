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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class NetworkSetClientTest {

    private static final String ANY_NETWORK_SET_RESOURCE_ID = "random-UUID";
    private static final String ANY_NETWORK_SET_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private NetworkSetClient networkSetClient;

    @Test
    public void shouldGetNetworkSet() {
        networkSetClient.getById(ANY_NETWORK_SET_RESOURCE_ID);

        String expectedUri = ResourceUris.NETWORK_SETS_URI + "/" + ANY_NETWORK_SET_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, NetworkSet.class);
    }

    @Test
    public void shouldGetAllNetworkSet() {
        networkSetClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.NETWORK_SETS_URI, NetworkSet.class);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyNetworkSetCollectionIsReturnedForTheGivenName() {
        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(new ResourceCollection());

        networkSetClient.getByName(ANY_NETWORK_SET_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.NETWORK_SETS_URI,
                NetworkSet.class, UrlParameter.getFilterByNameParameter(ANY_NETWORK_SET_RESOURCE_NAME));
    }

    @Test
    public void shouldGetNetworkSetCollectionByName() {
        ResourceCollection<NetworkSet> networkSets = new ResourceCollection();

        networkSets.setMembers(Lists.newArrayList(new NetworkSet()));

        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(networkSets);

        NetworkSet networkSet = networkSetClient.getByName(ANY_NETWORK_SET_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.NETWORK_SETS_URI,
                NetworkSet.class, UrlParameter.getFilterByNameParameter(ANY_NETWORK_SET_RESOURCE_NAME));

        assertThat(networkSet, is(notNullValue()));
    }

    @Test
    public void shouldCreateNetworkSet() {
        NetworkSet networkSet = new NetworkSet();

        networkSetClient.create(networkSet, false);

        then(baseClient).should().createResource(ResourceUris.NETWORK_SETS_URI, networkSet, false);
    }

    @Test
    public void shouldUpdateNetworkSet() {
        NetworkSet networkSet = new NetworkSet();

        networkSetClient.update(ANY_NETWORK_SET_RESOURCE_ID, networkSet, false);

        String expectedUri = ResourceUris.NETWORK_SETS_URI + "/" + ANY_NETWORK_SET_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, networkSet, false);
    }

    @Test
    public void shouldDeleteNetworkSet() {
        networkSetClient.delete(ANY_NETWORK_SET_RESOURCE_ID, false);

        String expectedUri = ResourceUris.NETWORK_SETS_URI + "/" + ANY_NETWORK_SET_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

}
