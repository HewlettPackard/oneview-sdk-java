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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class SwitchTypeClientTest {

    private static final String ANY_SWITCH_TYPE_RESOURCE_ID = "random-UUID";
    private static final String ANY_SWITCH_TYPE_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private SwitchTypeClient switchClient;

    @Test
    public void shouldGetSwitchType() {
        switchClient.getById(ANY_SWITCH_TYPE_RESOURCE_ID);

        String expectedUri = ResourceUris.SWITCH_TYPE_URI + "/" + ANY_SWITCH_TYPE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, InterconnectType.class);
    }

    @Test
    public void shouldGetAllSwitchType() {
        switchClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.SWITCH_TYPE_URI, InterconnectType.class);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptySwitchTypeCollectionIsReturnedForTheGivenName() {
        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(new ResourceCollection());

        switchClient.getByName(ANY_SWITCH_TYPE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.SWITCH_TYPE_URI,
                InterconnectType.class, UrlParameter.getFilterByNameParameter(ANY_SWITCH_TYPE_RESOURCE_NAME));
    }

    @Test
    public void shouldGetSwitchTypeCollectionByName() {
        ResourceCollection<InterconnectType> switchTypes = new ResourceCollection();

        switchTypes.setMembers(Lists.newArrayList(new InterconnectType()));

        given(baseClient.getResourceCollection(anyString(), any(Class.class), any(UrlParameter.class)))
                .willReturn(switchTypes);

        InterconnectType switchType = switchClient.getByName(ANY_SWITCH_TYPE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.SWITCH_TYPE_URI,
                InterconnectType.class, UrlParameter.getFilterByNameParameter(ANY_SWITCH_TYPE_RESOURCE_NAME));

        assertThat(switchType, is(notNullValue()));
    }
}