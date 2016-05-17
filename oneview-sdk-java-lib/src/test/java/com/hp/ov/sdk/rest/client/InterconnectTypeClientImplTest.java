package com.hp.ov.sdk.rest.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
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
public class InterconnectTypeClientImplTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    @Mock
    private HttpRestClient client;
    @Mock
    private ResourceAdaptor adaptor;
    @InjectMocks
    private InterconnectTypeClientImpl interconnectTypeClient;

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetInterconnectTypeWithoutParams() {
        this.interconnectTypeClient.getInterconnectType(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetInterconnectType() throws IOException {
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn("");

        this.interconnectTypeClient.getInterconnectType(new RestParams(), null);
    }

    @Test
    public void shouldGetInterconnectType() throws IOException {
        String interconnectTypeValue = "{\"type\":\"interconnect-type\"}";

        given(client.sendRequest(any(RestParams.class))).willReturn(interconnectTypeValue);
        given(adaptor.buildResourceObject(anyString(), eq(InterconnectType.class)))
                .willReturn(new InterconnectType());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.INTERCONNECT_TYPE_URI, ANY_RESOURCE_ID));

        this.interconnectTypeClient.getInterconnectType(new RestParams(), ANY_RESOURCE_ID);

        then(client).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(interconnectTypeValue, InterconnectType.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllInterconnectTypeWithoutParams() {
        this.interconnectTypeClient.getAllInterconnectType(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllInterconnectType() throws IOException {
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn("");

        this.interconnectTypeClient.getAllInterconnectType(new RestParams());
    }

    @Test
    public void shouldGetAllInterconnectType() throws IOException {
        String interconnectCollectionValue = "{\"type\":\"InterconnectTypeCollection\"," +
                "\"members\": [{\"type\":\"interconnect-type\"}]}";

        given(client.sendRequest(any(RestParams.class))).willReturn(interconnectCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(InterconnectType.class)))
                .willReturn(new ResourceCollection<InterconnectType>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.INTERCONNECT_TYPE_URI));

        this.interconnectTypeClient.getAllInterconnectType(new RestParams());

        then(client).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(interconnectCollectionValue, InterconnectType.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetInterconnectTypeByNameWithoutParams() {
        this.interconnectTypeClient.getInterconnectTypeByName(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetInterconnectTypeByName() throws IOException {
        given(client.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn("");

        this.interconnectTypeClient.getInterconnectTypeByName(new RestParams(), null);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoDeviceManagerIsFoundForTheGivenName() throws IOException {
        String anyName = "random-NAME";
        String interconnectCollectionValue = "{\"type\":\"InterconnectTypeCollection\"," +
                "\"members\": [{\"type\":\"interconnect-type\"}]}";

        ResourceCollection<InterconnectType> interconnectTypeCollection = new ResourceCollection<>();

        given(client.sendRequest(any(RestParams.class))).willReturn(interconnectCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(InterconnectType.class)))
                .willReturn(interconnectTypeCollection);

        this.interconnectTypeClient.getInterconnectTypeByName(new RestParams(), anyName);
    }

    @Test
    public void shouldGetInterconnectTypeByName() throws IOException {
        String anyName = "random-NAME";
        String interconnectCollectionValue = "{\"type\":\"InterconnectTypeCollection\"," +
                "\"members\": [{\"type\":\"interconnect-type\"}]}";
        ResourceCollection<InterconnectType> interconnectTypeCollection = new ResourceCollection<>();

        interconnectTypeCollection.setMembers(Lists.newArrayList(new InterconnectType()));

        given(client.sendRequest(any(RestParams.class))).willReturn(interconnectCollectionValue);
        given(adaptor.buildResourceCollection(anyString(), eq(InterconnectType.class)))
                .willReturn(interconnectTypeCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.INTERCONNECT_TYPE_URI));

        this.interconnectTypeClient.getInterconnectTypeByName(new RestParams(), anyName);

        then(client).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceCollection(interconnectCollectionValue, InterconnectType.class);
    }

    @Test
    public void shouldReturnEmptyStringForGetId() throws IOException {
        //given
        InterconnectTypeClient localInterconnectTypeClient = spy(this.interconnectTypeClient);
        InterconnectType interconnectType = new InterconnectType();

        doReturn(interconnectType).when(localInterconnectTypeClient).getInterconnectTypeByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localInterconnectTypeClient.getId(new RestParams(), null);

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String expectedResourceId = "c1067255-210d-454a-b199-c1faa98e0c97";
        String interconnectTypeUri = "rest/interconnect-types/c1067255-210d-454a-b199-c1faa98e0c97";

        //given
        InterconnectTypeClient localInterconnectTypeClient = spy(this.interconnectTypeClient);
        InterconnectType interconnectType = new InterconnectType();

        interconnectType.setUri(interconnectTypeUri);

        doReturn(interconnectType).when(localInterconnectTypeClient).getInterconnectTypeByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localInterconnectTypeClient.getId(new RestParams(), null);

        //then
        assertThat(resourceId, is(equalTo(expectedResourceId)));
    }

}
