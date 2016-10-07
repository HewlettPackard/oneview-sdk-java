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

import static com.hp.ov.sdk.rest.client.networking.LogicalDownlinkClient.LOGICAL_DOWNLINK_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalDownlinkClient.WITHOUT_ETHERNET_URI;
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
import com.hp.ov.sdk.dto.networking.logicaldownlinks.LogicalDownlink;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class LogicalDownlinkClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private LogicalDownlinkClient client = Reflection.newProxy(LogicalDownlinkClient.class,
            new ClientRequestHandler<>(baseClient, LogicalDownlinkClient.class));

    @Test
    public void shouldGetLogicalDownlinkById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_DOWNLINK_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LogicalDownlink.class).getType());
    }

    @Test
    public void shouldGetAllLogicalDownlink() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_DOWNLINK_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalDownlink>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalDownlinkCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_DOWNLINK_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalDownlink>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalDownlinkByIdWithoutEthernet() {
        client.getByIdWithoutEthernet(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_DOWNLINK_URI
                + "/" + ANY_RESOURCE_ID
                + WITHOUT_ETHERNET_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LogicalDownlink.class).getType());
    }

    @Test
    public void shouldGetAllLogicalDownlinkWithoutEthernet() {
        client.getAllWithoutEthernet();

        String expectedUri = LOGICAL_DOWNLINK_URI + WITHOUT_ETHERNET_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalDownlink>>() {}.getType());
    }

}
