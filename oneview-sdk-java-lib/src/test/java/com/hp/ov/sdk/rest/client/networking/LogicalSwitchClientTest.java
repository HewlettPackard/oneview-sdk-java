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
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.logicalswitches.AddLogicalSwitch;
import com.hp.ov.sdk.dto.networking.logicalswitches.LogicalSwitch;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class LogicalSwitchClientTest {

    private static final String ANY_LOGICAL_SWITCH_RESOURCE_ID = "random-UUID";
    private static final String ANY_LOGICAL_SWITCH_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private LogicalSwitchClient switchClient;

    @Test
    public void shouldGetLogicalSwitch() {
        switchClient.getById(ANY_LOGICAL_SWITCH_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_SWITCHES_URI + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, LogicalSwitch.class);
    }

    @Test
    public void shouldGetAllLogicalSwitch() {
        switchClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_SWITCHES_URI, LogicalSwitch.class);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyLogicalSwitchCollectionIsReturnedForTheGivenName() {
        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(new ResourceCollection());

        switchClient.getByName(ANY_LOGICAL_SWITCH_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_SWITCHES_URI,
                LogicalSwitch.class, UrlParameter.getFilterByNameParameter(ANY_LOGICAL_SWITCH_RESOURCE_NAME));
    }

    @Test
    public void shouldGetLogicalSwitchCollectionByName() {
        ResourceCollection<LogicalSwitch> logicalSwitches = new ResourceCollection();

        logicalSwitches.setMembers(Lists.newArrayList(new LogicalSwitch()));

        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(logicalSwitches);

        LogicalSwitch logicalSwitch = switchClient.getByName(ANY_LOGICAL_SWITCH_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_SWITCHES_URI,
                LogicalSwitch.class, UrlParameter.getFilterByNameParameter(ANY_LOGICAL_SWITCH_RESOURCE_NAME));

        assertThat(logicalSwitch, is(notNullValue()));
    }

    @Test
    public void shouldCreateLogicalSwitch() {
        AddLogicalSwitch logicalSwitch = new AddLogicalSwitch();

        switchClient.create(logicalSwitch, false);

        then(baseClient).should().createResource(ResourceUris.LOGICAL_SWITCHES_URI, logicalSwitch, false);
    }

    @Test
    public void shouldUpdateLogicalSwitch() {
        AddLogicalSwitch logicalSwitch = new AddLogicalSwitch();

        switchClient.update(ANY_LOGICAL_SWITCH_RESOURCE_ID, logicalSwitch, false);

        String expectedUri = ResourceUris.LOGICAL_SWITCHES_URI + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, logicalSwitch, false);
    }

    @Test
    public void shouldDeleteLogicalSwitch() {
        switchClient.delete(ANY_LOGICAL_SWITCH_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_SWITCHES_URI + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

    @Test
    public void shouldRefreshLogicalSwitch() {
        switchClient.refresh(ANY_LOGICAL_SWITCH_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_SWITCHES_URI + "/" + ANY_LOGICAL_SWITCH_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_SWITCHES_REFRESH_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

}
