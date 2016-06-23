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
import com.hp.ov.sdk.dto.networking.fcoenetworks.FcoeNetwork;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class FcoeNetworkClientTest {

    private static final String ANY_FCOE_RESOURCE_ID = "random-UUID";
    private static final String ANY_FCOE_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private FcoeNetworkClient fcoeClient;

    @Test
    public void shouldGetFcoeNetwork() {
        fcoeClient.getById(ANY_FCOE_RESOURCE_ID);

        String expectedUri = ResourceUris.FCOE_NETWORK_URI + "/" + ANY_FCOE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, FcoeNetwork.class);
    }

    @Test
    public void shouldGetAllFcoeNetwork() {
        fcoeClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.FCOE_NETWORK_URI, FcoeNetwork.class);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyFcoeNetworkCollectionIsReturnedForTheGivenName() {
        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(new ResourceCollection());

        fcoeClient.getByName(ANY_FCOE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.FCOE_NETWORK_URI,
                FcoeNetwork.class, UrlParameter.getFilterByNameParameter(ANY_FCOE_RESOURCE_NAME));
    }

    @Test
    public void shouldGetFcoeNetworkCollectionByName() {
        ResourceCollection<FcoeNetwork> fcoeNetworks = new ResourceCollection();

        fcoeNetworks.setMembers(Lists.newArrayList(new FcoeNetwork()));

        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(fcoeNetworks);

        FcoeNetwork fcoeNetwork = fcoeClient.getByName(ANY_FCOE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.FCOE_NETWORK_URI,
                FcoeNetwork.class, UrlParameter.getFilterByNameParameter(ANY_FCOE_RESOURCE_NAME));

        assertThat(fcoeNetwork, is(notNullValue()));
    }

    @Test
    public void shouldCreateFcoeNetwork() {
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        fcoeClient.create(fcoeNetwork, false);

        then(baseClient).should().createResource(ResourceUris.FCOE_NETWORK_URI, fcoeNetwork, false);
    }

    @Test
    public void shouldUpdateFcoeNetwork() {
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        fcoeClient.update(ANY_FCOE_RESOURCE_ID, fcoeNetwork, false);

        String expectedUri = ResourceUris.FCOE_NETWORK_URI + "/" + ANY_FCOE_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, fcoeNetwork, false);
    }

    @Test
    public void shouldDeleteFcoeNetwork() {
        fcoeClient.delete(ANY_FCOE_RESOURCE_ID, false);

        String expectedUri = ResourceUris.FCOE_NETWORK_URI + "/" + ANY_FCOE_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

}
