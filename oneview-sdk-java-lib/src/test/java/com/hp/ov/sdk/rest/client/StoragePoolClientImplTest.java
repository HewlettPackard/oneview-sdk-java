/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
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
import com.hp.ov.sdk.adaptors.StoragePoolAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;


@RunWith(MockitoJUnitRunner.class)
public class StoragePoolClientImplTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private StoragePoolAdaptor adaptor;
    @Mock
    private ResourceAdaptor resourceAdaptor;
    @InjectMocks
    private StoragePoolClientImpl storageClient;

    @Test
    public void shouldReturnStoragePoolClient() {
        assertThat(StoragePoolClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStoragePoolWithoutParams() {
        this.storageClient.getStoragePool(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetStoragePool() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStoragePool(new RestParams(), null);
    }

    @Test
    public void shouldGetStoragePool() {
        String storagePoolValue = "{\"type\":\"StoragePoolV2\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storagePoolValue);
        given(adaptor.buildDto(anyString())).willReturn(new StoragePool());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_POOL_URI, ANY_RESOURCE_ID));

        this.storageClient.getStoragePool(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildDto(storagePoolValue);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllStoragePoolsWithoutParams() {
        this.storageClient.getAllStoragePools(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllStoragePools() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getAllStoragePools(new RestParams());
    }

    @Test
    public void shouldGetAllStoragePools() {
        String storagePoolCollectionValue = "{\"type\":\"StoragePoolList\"," +
                "\"members\": [{\"type\":\"StoragePoolV2\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storagePoolCollectionValue);
        given(resourceAdaptor.buildResourceCollection(anyString(), eq(StoragePool.class)))
                .willReturn(new ResourceCollection<StoragePool>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_POOL_URI));

        this.storageClient.getAllStoragePools(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceCollection(storagePoolCollectionValue, StoragePool.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStoragePoolByNameWithoutParams() {
        String anyStoragePoolName = "random-NAME";
        String anyStorageSystemUri = "random-URI";

        this.storageClient.getStoragePoolByName(null, anyStoragePoolName, anyStorageSystemUri);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForStoragePoolByName() {
        String anyStoragePoolName = "random-NAME";
        String anyStorageSystemUri = "random-URI";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStoragePoolByName(new RestParams(), anyStoragePoolName, anyStorageSystemUri);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyStoragePoolCollectionIsReturnedForTheGivenName() {
        String anyStoragePoolName = "random-NAME";
        String anyStorageSystemUri = "random-URI";
        String storagePoolCollectionValue = "{\"type\":\"StoragePoolList\"," +
                "\"members\": [{\"type\":\"StoragePoolV2\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storagePoolCollectionValue);
        given(resourceAdaptor.buildResourceCollection(anyString(), eq(StoragePool.class)))
                .willReturn(new ResourceCollection<StoragePool>());

        this.storageClient.getStoragePoolByName(new RestParams(), anyStoragePoolName, anyStorageSystemUri);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenNoStoragePoolIsFoundForTheGivenName() {
        String anyStoragePoolName = "random-NAME";
        String anyStorageSystemUri = "random-URI";
        String storagePoolCollectionValue = "{\"type\":\"StoragePoolList\"," +
                "\"members\": [{\"type\":\"StoragePoolV2\"}]}";

        ResourceCollection<StoragePool> storagePoolCollection = new ResourceCollection<>();
        StoragePool storagePool = new StoragePool();

        storagePoolCollection.setMembers(Lists.newArrayList(storagePool));
        storagePoolCollection.setCount(1);

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storagePoolCollectionValue);
        given(resourceAdaptor.buildResourceCollection(anyString(), eq(StoragePool.class)))
                .willReturn(storagePoolCollection);

        this.storageClient.getStoragePoolByName(new RestParams(), anyStoragePoolName, anyStorageSystemUri);
    }

    @Test
    public void shouldGetStoragePoolByName() {
        String anyStoragePoolName = "random-NAME";
        String anyStorageSystemUri = "random-URI";
        String storagePoolCollectionValue = "{\"type\":\"StoragePoolList\"," +
                "\"members\": [{\"type\":\"StoragePoolV2\"}]}";

        ResourceCollection<StoragePool> storagePoolCollection = new ResourceCollection<>();
        StoragePool storagePool = new StoragePool();

        storagePool.setStorageSystemUri(anyStorageSystemUri);
        storagePoolCollection.setMembers(Lists.newArrayList(storagePool));
        storagePoolCollection.setCount(1);

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storagePoolCollectionValue);
        given(resourceAdaptor.buildResourceCollection(anyString(), eq(StoragePool.class)))
                .willReturn(storagePoolCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyStoragePoolName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_POOL_URI));

        StoragePool response = this.storageClient.getStoragePoolByName(new RestParams(),
                anyStoragePoolName, anyStorageSystemUri);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceCollection(storagePoolCollectionValue, StoragePool.class);

        assertThat(response, is(sameInstance(storagePool)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStoragePoolWithoutParams() {
        this.storageClient.createStoragePool(null, new AddStoragePool(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStoragePoolWithoutRequest() {
        this.storageClient.createStoragePool(new RestParams(), null, false);
    }

    @Test
    public void shouldCreateStoragePool() {
        String nonEmptyResponse = "random-RESPONSE";
        JSONObject jsonObject = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(nonEmptyResponse);
        given(adaptor.buildJsonObjectFromDto(any(AddStoragePool.class))).willReturn(jsonObject);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_POOL_URI));

        String response = this.storageClient.createStoragePool(new RestParams(), new AddStoragePool(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));

        assertThat(response, is(equalTo("Created")));
    }

    @Test
    public void shouldNotCreateStoragePoolWhenServerReturnsEmptyResponse() {
        String emptyResponse = "";
        JSONObject jsonObject = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(emptyResponse);
        given(adaptor.buildJsonObjectFromDto(any(AddStoragePool.class))).willReturn(jsonObject);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_POOL_URI));

        String response = this.storageClient.createStoragePool(new RestParams(), new AddStoragePool(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));

        assertThat(response, isEmptyString());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateStoragePoolWithoutParams() {
        this.storageClient.updateStoragePool(null, ANY_RESOURCE_ID, new StoragePool(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateStoragePoolWithoutRequest() {
        this.storageClient.updateStoragePool(new RestParams(), ANY_RESOURCE_ID, null, false);
    }

    @Test
    public void shouldUpdateStoragePools() {
        String nonEmptyResponse = "random-RESPONSE";
        JSONObject jsonObject = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(nonEmptyResponse);
        given(adaptor.buildJsonObjectFromDto(any(StoragePool.class), any(Integer.class))).willReturn(jsonObject);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_POOL_URI, ANY_RESOURCE_ID));

        String response = this.storageClient.updateStoragePool(new RestParams(), ANY_RESOURCE_ID, new StoragePool(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));

        assertThat(response, is(equalTo("Updated")));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteStoragePoolWithoutParams() {
        this.storageClient.deleteStoragePool(null, ANY_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteStoragePool() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.deleteStoragePool(new RestParams(), ANY_RESOURCE_ID);
    }

    @Test
    public void shouldDeleteStoragePool() {
        String nonEmptyResponse = "random-RESPONSE";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(nonEmptyResponse);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_POOL_URI, ANY_RESOURCE_ID));

        String response = this.storageClient.deleteStoragePool(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));

        assertThat(response, is(equalTo("Deleted")));
    }

    @Test
    public void shouldReturnEmptyStringForGetId() throws IOException {
        //given
        StoragePoolClient localStorageClient = spy(this.storageClient);
        StoragePool storagePool = new StoragePool();

        doReturn(storagePool).when(localStorageClient).getStoragePoolByName(
                any(RestParams.class), anyString(), anyString());

        //when
        String resourceId = localStorageClient.getId(new RestParams(), "random-NAME", "random-URI");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String storagePoolId = "20FCB7C5-3719-4AEC-AB06-8B51C0615743";
        String storagePoolUri = "/rest/storage-pools/" + storagePoolId;

        //given
        StoragePoolClient localStorageClient = spy(this.storageClient);
        StoragePool storagePool = new StoragePool();

        storagePool.setUri(storagePoolUri);

        doReturn(storagePool).when(localStorageClient).getStoragePoolByName(
                any(RestParams.class), anyString(), anyString());

        //when
        String resourceId = localStorageClient.getId(new RestParams(), "random-NAME", "random-URI");

        //then
        assertThat(resourceId, is(equalTo(storagePoolId)));
    }
}
