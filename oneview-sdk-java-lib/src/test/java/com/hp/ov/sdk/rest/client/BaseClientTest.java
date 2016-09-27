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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Supplier;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitor;

@RunWith(MockitoJUnitRunner.class)
public class BaseClientTest {

    private static final String ANY_URI_STRING = "random-URL";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_RESOURCE = "{\"type\":\"random-Type\"}";

    @Mock
    private RestParams params;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private HttpRestClient httpClient;
    @Mock
    private Supplier<TaskMonitor> supplier;

    @InjectMocks
    private BaseClient baseClient;

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetResourceWithoutUri() {
        this.baseClient.getResource(null, Object.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetResourceWithoutClass() {
        this.baseClient.getResource(ANY_URI_STRING, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForGetResource() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.getResource(ANY_URI_STRING, Object.class);
    }

    @Test
    public void shouldGetResource() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);

        Request request = new Request(HttpMethod.GET, ANY_URI_STRING);

        this.baseClient.getResource(ANY_URI_STRING, Object.class);

        then(httpClient).should().sendRequest(eq(params), eq(request));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetResourceCollectionWithoutUri() {
        this.baseClient.getResourceCollection(null, Object.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetResourceCollectionWithoutClass() {
        this.baseClient.getResourceCollection(ANY_URI_STRING, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForGetResourceCollection() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.getResourceCollection(ANY_URI_STRING, Object.class);
    }

    @Test
    public void shouldGetResourceCollection() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);

        Request request = new Request(HttpMethod.GET, ANY_URI_STRING);

        request.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        this.baseClient.getResourceCollection(ANY_URI_STRING, Object.class,
                UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(httpClient).should().sendRequest(eq(params), eq(request));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateResourceWithoutUri() {
        this.baseClient.createResource(null, new Object(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateResourceWithoutBody() {
        this.baseClient.createResource(ANY_URI_STRING, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForCreateResource() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.createResource(ANY_URI_STRING, new Object(), false);
    }

    @Test
    public void shouldCreateResource() {
        Object body = new Object();

        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);
        given(adaptor.buildResource(anyString(), eq(TaskResource.class))).willReturn(new TaskResource());
        given(supplier.get()).willReturn(mock(TaskMonitor.class));

        Request expectedRequest = new Request(HttpMethod.POST, ANY_URI_STRING, body);

        this.baseClient.createResource(ANY_URI_STRING, body, false);

        then(httpClient).should().sendRequest(eq(params), eq(expectedRequest));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateResourceWithoutUri() {
        this.baseClient.updateResource(null, new Object(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateResourceWithoutBody() {
        this.baseClient.updateResource(ANY_URI_STRING, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForUpdateResource() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.createResource(ANY_URI_STRING, new Object(), false);
    }

    @Test
    public void shouldUpdateResource() {
        Object body = new Object();

        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);
        given(adaptor.buildResource(anyString(), eq(TaskResource.class))).willReturn(new TaskResource());
        given(supplier.get()).willReturn(mock(TaskMonitor.class));

        Request expectedRequest = new Request(HttpMethod.PUT, ANY_URI_STRING, body);

        this.baseClient.updateResource(ANY_URI_STRING, body, false);

        then(httpClient).should().sendRequest(eq(params), eq(expectedRequest));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToPatchResourceWithoutUri() {
        this.baseClient.patchResource(null, new Patch(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToPatchResourceWithoutBody() {
        this.baseClient.patchResource(ANY_URI_STRING, null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForPatchResource() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.patchResource(ANY_URI_STRING, new Patch(), false);
    }

    @Test
    public void shouldPatchResource() {
        Patch patch = new Patch();
        patch.setOp(Patch.PatchOperation.replace);

        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);
        given(adaptor.buildResource(anyString(), eq(TaskResource.class))).willReturn(new TaskResource());
        given(supplier.get()).willReturn(mock(TaskMonitor.class));

        Request expectedRequest = new Request(HttpMethod.PATCH, ANY_URI_STRING, patch);

        this.baseClient.patchResource(ANY_URI_STRING, patch, false);

        then(httpClient).should().sendRequest(eq(params), eq(expectedRequest));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteResourceWithoutUri() {
        this.baseClient.deleteResource(null, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForDeleteResource() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.deleteResource(ANY_URI_STRING, false);
    }

    @Test
    public void shouldDeleteResource() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);
        given(adaptor.buildResource(anyString(), eq(TaskResource.class))).willReturn(new TaskResource());
        given(supplier.get()).willReturn(mock(TaskMonitor.class));

        Request expectedRequest = new Request(HttpMethod.DELETE, ANY_URI_STRING);

        this.baseClient.deleteResource(ANY_URI_STRING, false);

        then(httpClient).should().sendRequest(eq(params), eq(expectedRequest));
    }
}
