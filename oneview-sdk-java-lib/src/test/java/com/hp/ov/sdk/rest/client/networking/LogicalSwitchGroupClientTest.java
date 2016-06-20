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
import com.hp.ov.sdk.dto.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class LogicalSwitchGroupClientTest {

    private static final String ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID = "random-UUID";
    private static final String ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private LogicalSwitchGroupClient groupClient;

    @Test
    public void shouldGetLogicalSwitchGroup() {
        groupClient.getById(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_SWITCH_GROUPS_URI + "/" + ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, LogicalSwitchGroup.class);
    }

    @Test
    public void shouldGetAllLogicalSwitchGroup() {
        groupClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_SWITCH_GROUPS_URI,
                LogicalSwitchGroup.class);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyLogicalSwitchGroupCollectionIsReturnedForTheGivenName() {
        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(new ResourceCollection());

        groupClient.getByName(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_SWITCH_GROUPS_URI,
                LogicalSwitchGroup.class, UrlParameter.getFilterByNameParameter(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME));
    }

    @Test
    public void shouldGetLogicalSwitchGroupCollectionByName() {
        ResourceCollection<LogicalSwitchGroup> logicalSwitchGroups = new ResourceCollection();

        logicalSwitchGroups.setMembers(Lists.newArrayList(new LogicalSwitchGroup()));

        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(logicalSwitchGroups);

        LogicalSwitchGroup logicalSwitchGroup = groupClient.getByName(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_SWITCH_GROUPS_URI,
                LogicalSwitchGroup.class, UrlParameter.getFilterByNameParameter(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_NAME));

        assertThat(logicalSwitchGroup, is(notNullValue()));
    }

    @Test
    public void shouldCreateLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();

        groupClient.create(logicalSwitchGroup, false);

        then(baseClient).should().createResource(ResourceUris.LOGICAL_SWITCH_GROUPS_URI, logicalSwitchGroup, false);
    }

    @Test
    public void shouldUpdateLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = new LogicalSwitchGroup();

        groupClient.update(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID, logicalSwitchGroup, false);

        String expectedUri = ResourceUris.LOGICAL_SWITCH_GROUPS_URI + "/" + ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, logicalSwitchGroup, false);
    }

    @Test
    public void shouldDeleteLogicalSwitchGroup() {
        groupClient.delete(ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_SWITCH_GROUPS_URI + "/" + ANY_LOGICAL_SWITCH_GROUP_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

}
