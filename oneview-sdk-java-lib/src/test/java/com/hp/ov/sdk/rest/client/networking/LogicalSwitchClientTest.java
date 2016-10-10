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

import static com.hp.ov.sdk.rest.client.networking.LogicalSwitchClient.LOGICAL_SWITCHES_REFRESH_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalSwitchClient.LOGICAL_SWITCHES_URI;
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
import com.hp.ov.sdk.dto.networking.logicalswitches.AddLogicalSwitch;
import com.hp.ov.sdk.dto.networking.logicalswitches.LogicalSwitch;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class LogicalSwitchClientTest {

    private static final String ANY_LOGICAL_SWITCH_RESOURCE_ID = "random-UUID";
    private static final String ANY_LOGICAL_SWITCH_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private LogicalSwitchClient client = Reflection.newProxy(LogicalSwitchClient.class,
            new ClientRequestHandler<>(baseClient, LogicalSwitchClient.class));

    @Test
    public void shouldGetLogicalSwitchById() {
        client.getById(ANY_LOGICAL_SWITCH_RESOURCE_ID);

        String expectedUri = LOGICAL_SWITCHES_URI + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LogicalSwitch.class).getType());
    }

    @Test
    public void shouldGetAllLogicalSwitch() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_SWITCHES_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalSwitch>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalSwitchesByName() {
        client.getByName(ANY_LOGICAL_SWITCH_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_SWITCHES_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_LOGICAL_SWITCH_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalSwitch>>() {}.getType());
    }

    @Test
    public void shouldCreateLogicalSwitch() {
        AddLogicalSwitch logicalSwitch = new AddLogicalSwitch();

        client.create(logicalSwitch);

        Request expectedRequest = new Request(HttpMethod.POST, LOGICAL_SWITCHES_URI, logicalSwitch);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalSwitch() {
        AddLogicalSwitch logicalSwitch = new AddLogicalSwitch();

        client.update(ANY_LOGICAL_SWITCH_RESOURCE_ID, logicalSwitch);

        String expectedUri = LOGICAL_SWITCHES_URI + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, logicalSwitch);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteLogicalSwitch() {
        client.delete(ANY_LOGICAL_SWITCH_RESOURCE_ID);

        String expectedUri = LOGICAL_SWITCHES_URI + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldRefreshLogicalSwitch() {
        client.refresh(ANY_LOGICAL_SWITCH_RESOURCE_ID);

        String expectedUri = LOGICAL_SWITCHES_URI
                + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID
                + LOGICAL_SWITCHES_REFRESH_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
