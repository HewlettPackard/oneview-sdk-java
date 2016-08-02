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

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class RackClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private RackClient client;

    @Test
    public void shouldGetRack() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.RACK_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, Rack.class);
    }

    @Test
    public void shouldGetAllRacks() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.RACK_URI, Rack.class);
    }

    @Test
    public void shouldGetRackCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.RACK_URI,
                Rack.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldAddRack() {
        Rack rack = new Rack();

        client.add(rack);

        Request request = new Request(HttpMethodType.POST, ResourceUris.RACK_URI, rack);

        then(baseClient).should().executeRequest(request, Rack.class);
    }

    @Test
    public void shouldUpdateRack() {
        Rack rack = new Rack();

        client.update(ANY_RESOURCE_ID, rack);

        String expectedUri = ResourceUris.RACK_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, rack);

        then(baseClient).should().executeRequest(request, Rack.class);
    }

    @Test
    public void shouldRemoveRack() {
        client.remove(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.RACK_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldRemoveRackByFilter() {
        client.removeByFilter(ANY_RESOURCE_NAME, false);

        UrlParameter filter = new UrlParameter("filter", ANY_RESOURCE_NAME);

        then(baseClient).should().deleteResource(ResourceUris.RACK_URI, false, filter);
    }

    @Test
    public void shouldGetRackDeviceTopology() {
        client.getDeviceTopology(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.RACK_URI + "/" + ANY_RESOURCE_ID + "/" + ResourceUris.RACK_DEVICE_TOPOLOGY;

        then(baseClient).should().getResource(expectedUri, TopologyInformation.class);
    }

}
