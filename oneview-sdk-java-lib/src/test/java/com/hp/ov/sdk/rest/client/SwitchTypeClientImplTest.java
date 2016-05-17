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

package com.hp.ov.sdk.rest.client;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class SwitchTypeClientImplTest {

    private static final String ANY_SWITCH_TYPE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @InjectMocks
    private SwitchTypeClientImpl switchClient;

    @Test
    public void shouldReturnSwitchTypeClient() {
        assertThat(SwitchTypeClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetSwitchTypeWithoutParams() {
        this.switchClient.getSwitchType(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetSwitchType() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getSwitchType(new RestParams(), null);
    }

    @Test
    public void shouldGetSwitchType() {
        String switchTypeValue = "{\"type\":\"interconnect-typeV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchTypeValue);
        given(adaptor.buildResourceObject(anyString(), eq(InterconnectType.class)))
                .willReturn(new InterconnectType());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCH_TYPE_URI, ANY_SWITCH_TYPE_ID));

        this.switchClient.getSwitchType(new RestParams(), ANY_SWITCH_TYPE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(switchTypeValue), eq(InterconnectType.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetSwitchTypeByNameWithoutParams() {
        this.switchClient.getSwitchTypeByName(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetSwitchTypeByName() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getSwitchTypeByName(new RestParams(), null);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoSwitchTypeIsFoundForTheGivenName() {
        String anySwitchTypeName = "random-NAME";
        String switchTypeCollectionValue = "{\"type\":\"InterconnectTypeCollectionV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchTypeCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(InterconnectType.class)))
                .willReturn(new ResourceCollection<InterconnectType>());

        this.switchClient.getSwitchTypeByName(new RestParams(), anySwitchTypeName);
    }

    @Test
    public void shouldGetSwitchTypeByName() {
        String anySwitchTypeName = "random-NAME";
        String switchTypeCollectionValue = "{\"type\":\"InterconnectTypeCollectionV2\"," +
                "\"members\":[{\"type\":\"interconnect-typeV2\"}]}";
        ResourceCollection<InterconnectType> interconnectTypeCollection = new ResourceCollection<>();

        InterconnectType interconnectType = new InterconnectType();
        interconnectTypeCollection.setMembers(Lists.newArrayList(interconnectType));

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchTypeCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(InterconnectType.class)))
                .willReturn(interconnectTypeCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anySwitchTypeName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCH_TYPE_URI));

        InterconnectType type = this.switchClient.getSwitchTypeByName(new RestParams(), anySwitchTypeName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(switchTypeCollectionValue),
                eq(InterconnectType.class));

        assertThat(type, sameInstance(interconnectType));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllSwitchTypesWithoutParams() {
        this.switchClient.getAllSwitchTypes(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllSwitchTypes() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.switchClient.getAllSwitchTypes(new RestParams());
    }

    @Test
    public void shouldGetAllSwitchTypes() {
        String switchTypeCollectionValue = "{\"type\":\"InterconnectTypeCollectionV2\"," +
                "\"members\":[{\"type\":\"interconnect-typeV2\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(switchTypeCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(InterconnectType.class)))
                .willReturn(new ResourceCollection<InterconnectType>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.SWITCH_TYPE_URI));

        this.switchClient.getAllSwitchTypes(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(eq(switchTypeCollectionValue),
                eq(InterconnectType.class));
    }

}