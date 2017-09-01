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

import static com.hp.ov.sdk.rest.client.networking.FcoeNetworkClient.FCOE_NETWORK_URI;
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
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.fcoenetworks.FcoeNetwork;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class FcoeNetworkClientTest {

    private static final String ANY_FCOE_RESOURCE_ID = "random-UUID";
    private static final String ANY_FCOE_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private FcoeNetworkClient client = Reflection.newProxy(FcoeNetworkClient.class,
            new ClientRequestHandler<>(baseClient, FcoeNetworkClient.class));

    @Test
    public void shouldGetFcoeNetworkById() {
        client.getById(ANY_FCOE_RESOURCE_ID);

        String expectedUri = FCOE_NETWORK_URI + "/" + ANY_FCOE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(FcoeNetwork.class).getType());
    }

    @Test
    public void shouldGetAllFcoeNetwork() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, FCOE_NETWORK_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<FcoeNetwork>>() {}.getType());
    }

    @Test
    public void shouldGetFcoeNetworksByName() {
        client.getByName(ANY_FCOE_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, FCOE_NETWORK_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_FCOE_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<FcoeNetwork>>() {}.getType());
    }

    @Test
    public void shouldCreateFcoeNetwork() {
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        client.create(fcoeNetwork, TaskTimeout.of(123));

        Request expectedRequest = new Request(HttpMethod.POST, FCOE_NETWORK_URI, fcoeNetwork);
        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldPatchFcoeNetwork() {
        Patch patch = new Patch();

        client.patch(ANY_FCOE_RESOURCE_ID, patch, TaskTimeout.of(123));

        String expectedUri = FCOE_NETWORK_URI + "/" + ANY_FCOE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);
        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateFcoeNetwork() {
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        client.update(ANY_FCOE_RESOURCE_ID, fcoeNetwork);

        String expectedUri = FCOE_NETWORK_URI + "/" + ANY_FCOE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, fcoeNetwork);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteFcoeNetwork() {
        client.delete(ANY_FCOE_RESOURCE_ID);

        String expectedUri = FCOE_NETWORK_URI + "/" + ANY_FCOE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
