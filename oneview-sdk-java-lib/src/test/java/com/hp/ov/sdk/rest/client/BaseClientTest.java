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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Supplier;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitor;

@RunWith(MockitoJUnitRunner.class)
public class BaseClientTest {

    private static final String ANY_URI_STRING = "random-URL";
    private static final String ANY_RESPONSE_STRING = "random-Response";
    private static final String ANY_RESOURCE = "{\"type\":\"random-Type\"}";
    private static final String ANY_TASK_RESOURCE = "{\"type\":\"task-resource\"}";

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
    public void shouldThrowExceptionWhenTryingToExecuteRequestWithoutType() {
        this.baseClient.executeRequest(new Request(HttpMethod.GET, ANY_URI_STRING), (Type) null);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToExecuteRequestWithoutClass() {
        this.baseClient.executeRequest(new Request(HttpMethod.GET, ANY_URI_STRING), null);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToExecuteRequestWithoutRequestObjectWithType() {
        this.baseClient.executeRequest(null, TypeToken.of(Object.class).getType());
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToExecuteRequestWithoutRequestObjectWithClass() {
        this.baseClient.executeRequest(null, Object.class);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForExecuteRequestWithType() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.executeRequest(new Request(HttpMethod.GET, ANY_URI_STRING),
                TypeToken.of(Object.class).getType());
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForExecuteRequestWithClass() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.executeRequest(new Request(HttpMethod.GET, ANY_URI_STRING), Object.class);
    }

    @Test
    public void shouldExecuteRequestWithType() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);

        Request request = new Request(HttpMethod.GET, ANY_URI_STRING);

        this.baseClient.executeRequest(request, TypeToken.of(Object.class).getType());

        then(httpClient).should().sendRequest(eq(params), eq(request));
    }

    @Test
    public void shouldExecuteRequestWithClass() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESOURCE);

        Request request = new Request(HttpMethod.GET, ANY_URI_STRING);

        this.baseClient.executeRequest(request, Object.class);

        then(httpClient).should().sendRequest(eq(params), eq(request));
    }

    @Test
    public void shouldExecuteRequestWithTypeAndReturnString() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESPONSE_STRING);

        Request request = new Request(HttpMethod.GET, ANY_URI_STRING);
        String response = (String) this.baseClient.executeRequest(request, TypeToken.of(String.class).getType());

        then(httpClient).should().sendRequest(eq(params), eq(request));

        assertThat(response, is(equalTo(ANY_RESPONSE_STRING)));
    }

    @Test
    public void shouldExecuteRequestWithClassAndReturnString() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_RESPONSE_STRING);

        Request request = new Request(HttpMethod.GET, ANY_URI_STRING);
        String response = this.baseClient.executeRequest(request, String.class);

        then(httpClient).should().sendRequest(eq(params), eq(request));

        assertThat(response, is(equalTo(ANY_RESPONSE_STRING)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToExecuteMonitorableRequestWithoutRequestObject() {
        this.baseClient.executeMonitorableRequest(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoResponseForExecuteMonitorableRequest() {
        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn("");

        this.baseClient.executeMonitorableRequest(new Request(HttpMethod.GET, ANY_URI_STRING));
    }

    @Test
    public void shouldExecuteMonitorableRequest() {
        TaskMonitor monitor = mock(TaskMonitor.class);

        given(httpClient.sendRequest(any(RestParams.class), any(Request.class))).willReturn(ANY_TASK_RESOURCE);
        given(adaptor.buildResource(anyString(), eq(TaskResource.class))).willReturn(new TaskResource());
        given(supplier.get()).willReturn(monitor);
        given(monitor.execute(any(BaseClient.class), any(TaskResource.class), anyInt())).willReturn(new TaskResource());

        Request request = new Request(HttpMethod.GET, ANY_URI_STRING);

        this.baseClient.executeMonitorableRequest(request);

        then(httpClient).should().sendRequest(eq(params), eq(request));
    }

}
