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
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class FcNetworkClientTest {

    private static final String ANY_FC_RESOURCE_ID = "random-UUID";
    private static final String ANY_FC_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private FcNetworkClient fcClient;

    @Test
    public void shouldGetFcNetwork() {
        fcClient.getById(ANY_FC_RESOURCE_ID);

        String expectedUri = ResourceUris.FC_NETWORK_URI + "/" + ANY_FC_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, FcNetwork.class);
    }

    @Test
    public void shouldGetAllFcNetwork() {
        fcClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.FC_NETWORK_URI, FcNetwork.class);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyFcNetworkCollectionIsReturnedForTheGivenName() {
        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(new ResourceCollection());

        fcClient.getByName(ANY_FC_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.FC_NETWORK_URI,
                FcNetwork.class, UrlParameter.getFilterByNameParameter(ANY_FC_RESOURCE_NAME));
    }

    @Test
    public void shouldGetFcNetworkCollectionByName() {
        ResourceCollection<FcNetwork> fcNetworks = new ResourceCollection();

        fcNetworks.setMembers(Lists.newArrayList(new FcNetwork()));

        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(fcNetworks);

        FcNetwork fcNetwork = fcClient.getByName(ANY_FC_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.FC_NETWORK_URI,
                FcNetwork.class, UrlParameter.getFilterByNameParameter(ANY_FC_RESOURCE_NAME));

        assertThat(fcNetwork, is(notNullValue()));
    }

    @Test
    public void shouldCreateFcNetwork() {
        FcNetwork fcNetwork = new FcNetwork();

        fcClient.create(fcNetwork, false);

        then(baseClient).should().createResource(ResourceUris.FC_NETWORK_URI, fcNetwork, false);
    }

    @Test
    public void shouldUpdateFcNetwork() {
        FcNetwork fcNetwork = new FcNetwork();

        fcClient.update(ANY_FC_RESOURCE_ID, fcNetwork, false);

        String expectedUri = ResourceUris.FC_NETWORK_URI + "/" + ANY_FC_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, fcNetwork, false);
    }

    @Test
    public void shouldDeleteFcNetwork() {
        fcClient.delete(ANY_FC_RESOURCE_ID, false);

        String expectedUri = ResourceUris.FC_NETWORK_URI + "/" + ANY_FC_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

}
