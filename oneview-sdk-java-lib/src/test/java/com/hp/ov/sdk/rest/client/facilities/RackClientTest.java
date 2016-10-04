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

package com.hp.ov.sdk.rest.client.facilities;

import static com.hp.ov.sdk.rest.client.facilities.RackClient.RACK_DEVICE_TOPOLOGY;
import static com.hp.ov.sdk.rest.client.facilities.RackClient.RACK_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.client.GenericFilter;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class RackClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private RackClient client = Reflection.newProxy(RackClient.class,
            new ClientRequestHandler<>(baseClient, RackClient.class));

    @Test
    public void shouldGetRack() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = RACK_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Rack.class).getType());
    }

    @Test
    public void shouldGetAllRacks() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, RACK_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Rack>>() {}.getType());
    }

    @Test
    public void shouldGetRackCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, RACK_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Rack>>() {}.getType());
    }

    @Test
    public void shouldAddRack() {
        Rack rack = new Rack();

        client.add(rack);

        Request expectedRequest = new Request(HttpMethod.POST, RACK_URI);
        expectedRequest.setEntity(rack);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Rack.class).getType());
    }

    @Test
    public void shouldUpdateRack() {
        Rack rack = new Rack();

        client.update(ANY_RESOURCE_ID, rack);

        String expectedUri = RACK_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(rack);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Rack.class).getType());
    }

    @Test
    public void shouldRemoveRack() {
        client.remove(ANY_RESOURCE_ID, TaskTimeout.of(321));


        String expectedUri = RACK_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldRemoveRackByFilter() {
        GenericFilter filter = new GenericFilter();
        filter.setFilter("'name' = '" + ANY_RESOURCE_NAME + "'");
        client.removeByFilter(filter, TaskTimeout.of(321));

        String expectedUri = RACK_URI;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.addQuery(new UrlParameter("filter", filter.parameters().get(0).getValue()));

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetRackDeviceTopology() {
        client.getDeviceTopology(ANY_RESOURCE_ID);

        String expectedUri = RACK_URI
                + "/" + ANY_RESOURCE_ID
                + RACK_DEVICE_TOPOLOGY;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(TopologyInformation.class).getType());
    }

}
