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

import static com.hp.ov.sdk.rest.client.networking.LogicalSwitchGroupClient.LOGICAL_SWITCH_GROUPS_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.LogicalSwitchGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class LogicalSwitchGroupClientTest {

    private static final String ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID = "random-UUID";
    private static final String ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private LogicalSwitchGroupClient client = Reflection.newProxy(LogicalSwitchGroupClient.class,
            new ClientRequestHandler<>(baseClient, LogicalSwitchGroupClient.class));

    @Test
    public void shouldGetLogicalSwitchGroupById() {
        client.getById(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID);

        String expectedUri = LOGICAL_SWITCH_GROUPS_URI + "/" + ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LogicalSwitchGroup.class).getType());
    }

    @Test
    public void shouldGetAllLogicalSwitchGroup() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_SWITCH_GROUPS_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalSwitchGroup>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalSwitchGroupsByName() {
        client.getByName(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_SWITCH_GROUPS_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalSwitchGroup>>() {}.getType());
    }

    @Test
    public void shouldCreateLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();

        client.create(logicalSwitchGroup);

        Request expectedRequest = new Request(HttpMethod.POST, LOGICAL_SWITCH_GROUPS_URI, logicalSwitchGroup);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();

        client.update(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID, logicalSwitchGroup);

        String expectedUri = LOGICAL_SWITCH_GROUPS_URI + "/" + ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, logicalSwitchGroup);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteLogicalSwitchGroup() {
        client.delete(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID);

        String expectedUri = LOGICAL_SWITCH_GROUPS_URI + "/" + ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
