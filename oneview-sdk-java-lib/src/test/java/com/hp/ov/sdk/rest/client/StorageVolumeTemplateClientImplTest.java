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
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.doReturn;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.spy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.StorageVolumeTemplateAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplateCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeTemplateClientImplTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private StorageVolumeTemplateAdaptor adaptor;
    @InjectMocks
    private StorageVolumeTemplateClientImpl storageClient;

    @Test
    public void shouldReturnStoragePoolClient() {
        assertThat(StorageVolumeTemplateClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStorageVolumeTemplateWithoutParams() {
        this.storageClient.getStorageVolumeTemplate(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetStorageVolumeTemplate() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStorageVolumeTemplate(new RestParams(), null);
    }

    @Test
    public void shouldGetStorageVolumeTemplate() {
        String storageVolumeTemplateValue = "{\"type\":\"StorageVolumeTemplateV3\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeTemplateValue);
        given(adaptor.buildDto(anyString())).willReturn(new StorageVolumeTemplate());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, ANY_RESOURCE_ID));

        this.storageClient.getStorageVolumeTemplate(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildDto(storageVolumeTemplateValue);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllStorageVolumeTemplatesWithoutParams() {
        this.storageClient.getAllStorageVolumeTemplates(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllStorageVolumeTemplates() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getAllStorageVolumeTemplates(new RestParams());
    }

    @Test
    public void shouldGetAllStorageVolumeTemplates() {
        String storageVolumeTemplateListValue = "{\"type\":\"StorageVolumeTemplateList\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeTemplateListValue);
        given(adaptor.buildStorageVolumeTemplateCollection(anyString()))
                .willReturn(new StorageVolumeTemplateCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        this.storageClient.getAllStorageVolumeTemplates(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildStorageVolumeTemplateCollection(storageVolumeTemplateListValue);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStorageVolumeTemplateByNameWithoutParams() {
        String anyStorageVolumeTemplateName = "random-NAME";

        this.storageClient.getStorageVolumeTemplateByName(null, anyStorageVolumeTemplateName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetStorageVolumeTemplateByName() {
        String anyStorageVolumeTemplateName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStorageVolumeTemplateByName(new RestParams(), anyStorageVolumeTemplateName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyStorageVolumeTemplateCollectionIsReturnedForTheGivenName() {
        String anyStorageVolumeTemplateName = "random-NAME";
        String storageVolumeTemplateCollectionValue = "{\"type\":\"StorageVolumeTemplateList\"," +
                "\"members\": []}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeTemplateCollectionValue);
        given(adaptor.buildStorageVolumeTemplateCollection(anyString()))
                .willReturn(new StorageVolumeTemplateCollection());

        this.storageClient.getStorageVolumeTemplateByName(new RestParams(), anyStorageVolumeTemplateName);
    }

    @Test
    public void shouldGetStorageVolumeTemplateByGivenName() {
        String anyStorageVolumeTemplateName = "random-NAME";
        String storageVolumeTemplateCollectionValue = "{\"type\":\"StorageVolumeTemplateList\"," +
                "\"members\": [{\"type\":\"StorageVolumeTemplateV3\"}]}";

        StorageVolumeTemplateCollection storageVolumeTemplateCollection = new StorageVolumeTemplateCollection();
        StorageVolumeTemplate storageVolumeTemplate = new StorageVolumeTemplate();

        storageVolumeTemplateCollection.setMembers(Lists.newArrayList(storageVolumeTemplate));
        storageVolumeTemplateCollection.setCount(1);

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeTemplateCollectionValue);
        given(adaptor.buildStorageVolumeTemplateCollection(anyString())).willReturn(storageVolumeTemplateCollection);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestQueryUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, UrlUtils.createFilterString(anyStorageVolumeTemplateName)));

        StorageVolumeTemplate response = this.storageClient.getStorageVolumeTemplateByName(
                new RestParams(), anyStorageVolumeTemplateName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildStorageVolumeTemplateCollection(eq(storageVolumeTemplateCollectionValue));

        assertThat(response, is(sameInstance(storageVolumeTemplate)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStorageVolumeTemplateWithoutParams() {
        this.storageClient.createStorageVolumeTemplate(null, new StorageVolumeTemplate(), false, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStorageVolumeTemplateWithoutRequest() {
        this.storageClient.createStorageVolumeTemplate(new RestParams(), null, false, false);
    }

    @Test
    public void shouldCreateStoragePool() {
        String storageVolumeTemplateValue = "{\"type\":\"StorageVolumeTemplateV3\"}";
        JSONObject jsonObject = new JSONObject();

        given(adaptor.buildJsonObjectFromDto(any(StorageVolumeTemplate.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class)))
                .willReturn(storageVolumeTemplateValue);
        given(adaptor.buildDto(storageVolumeTemplateValue)).willReturn(new StorageVolumeTemplate());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        this.storageClient.createStorageVolumeTemplate(new RestParams(), new StorageVolumeTemplate(), false, false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(adaptor).should().buildDto(eq(storageVolumeTemplateValue));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateStorageVolumeTemplateWithoutParams() {
        this.storageClient.updateStorageVolumeTemplate(null, ANY_RESOURCE_ID, new StorageVolumeTemplate(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateStorageVolumeTemplateWithoutRequest() {
        this.storageClient.updateStorageVolumeTemplate(new RestParams(), ANY_RESOURCE_ID, null, false);
    }

    @Test
    public void shouldUpdateStoragePool() {
        String storageVolumeTemplateValue = "{\"type\":\"StorageVolumeTemplateV3\"}";
        JSONObject jsonObject = new JSONObject();

        given(adaptor.buildJsonObjectFromDto(any(StorageVolumeTemplate.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class)))
                .willReturn(storageVolumeTemplateValue);
        given(adaptor.buildDto(storageVolumeTemplateValue)).willReturn(new StorageVolumeTemplate());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, ANY_RESOURCE_ID));

        this.storageClient.updateStorageVolumeTemplate(new RestParams(), ANY_RESOURCE_ID, new StorageVolumeTemplate(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(adaptor).should().buildDto(eq(storageVolumeTemplateValue));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteStorageVolumeTemplateWithoutParams() {
        this.storageClient.deleteStorageVolumeTemplate(null, ANY_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteStorageVolumeTemplate() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.deleteStorageVolumeTemplate(new RestParams(), ANY_RESOURCE_ID);
    }

    @Test
    public void shouldDeleteStoragePool() {
        String nonEmptyResponse = "random-RESPONSE";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(nonEmptyResponse);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, ANY_RESOURCE_ID));

        String response = this.storageClient.deleteStorageVolumeTemplate(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));

        assertThat(response, is(equalTo("Deleted")));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetConnectableStorageVolumeTemplatesWithoutParams() {
        this.storageClient.getConnectableVolumeTemplates(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetConnectableStorageVolumeTemplates() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getConnectableVolumeTemplates(new RestParams());
    }

    @Test
    public void shouldGetConnectableStorageVolumeTemplates() {
        String connectableCollectionValue = "{\"type\":\"ConnectableStorageVolumeTemplateList\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(connectableCollectionValue);
        given(adaptor.buildConnectableStorageVolumeTemplateCollection(anyString()))
                .willReturn(new ConnectableStorageVolumeTemplateCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI,
                ResourceUris.STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI));

        this.storageClient.getConnectableVolumeTemplates(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildConnectableStorageVolumeTemplateCollection(connectableCollectionValue);
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        StorageVolumeTemplateClientImpl localStorageVolumeTemplate = spy(this.storageClient);
        StorageVolumeTemplate storageVolumeTemplate = new StorageVolumeTemplate();

        doReturn(storageVolumeTemplate).when(localStorageVolumeTemplate).getStorageVolumeTemplateByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localStorageVolumeTemplate.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String storagePoolId = "cee4cd83-7aff-449a-9923-26b081ac7552";
        String storagePoolUri = "/rest/storage-volume-templates/" + storagePoolId;

        //given
        StorageVolumeTemplateClientImpl localStorageVolumeTemplate = spy(this.storageClient);
        StorageVolumeTemplate storageVolumeTemplate = new StorageVolumeTemplate();

        storageVolumeTemplate.setUri(storagePoolUri);

        doReturn(storageVolumeTemplate).when(localStorageVolumeTemplate).getStorageVolumeTemplateByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localStorageVolumeTemplate.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(storagePoolId)));
    }
}