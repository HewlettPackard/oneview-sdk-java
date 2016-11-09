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

package com.hp.ov.sdk.rest.client.settings;

import static com.hp.ov.sdk.rest.client.settings.ScopeClient.RESOURCE_ASSIGNMENTS;
import static com.hp.ov.sdk.rest.client.settings.ScopeClient.SCOPES_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import java.lang.reflect.Type;

import org.apache.http.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.settings.ResourceAssignment;
import com.hp.ov.sdk.dto.settings.Scope;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.BasicHeader;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class ScopeClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_ETAG = "random-ETag";

    private BaseClient baseClient = mock(BaseClient.class);
    private ScopeClient client = Reflection.newProxy(ScopeClient.class,
            new ClientRequestHandler<>(baseClient, ScopeClient.class));

    @Test
    public void shouldGetScopeById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = SCOPES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Scope.class).getType());
    }

    @Test
    public void shouldGetAllScopes() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class)))
                .willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, SCOPES_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<Scope>>() {}.getType());
    }

    @Test
    public void shouldCreateScope() {
        Scope scope = new Scope();

        client.create(scope);

        Request expectedRequest = new Request(HttpMethod.POST, SCOPES_URI, scope);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Scope.class).getType());
    }

    @Test
    public void shouldUpdateScope() {
        Scope scope = new Scope();

        client.update(ANY_RESOURCE_ID, scope);

        String expectedUri = SCOPES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, scope);

        expectedRequest.addHeader(new BasicHeader(HttpHeaders.IF_MATCH, "*"));

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Scope.class).getType());
    }

    @Test
    public void shouldUpdateScopeUsingETag() {
        Scope scope = new Scope();
        scope.setETag(ANY_RESOURCE_ETAG);

        client.update(ANY_RESOURCE_ID, scope, scope.getETag());

        String expectedUri = SCOPES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, scope);
        expectedRequest.addHeader(new BasicHeader(HttpHeaders.IF_MATCH, ANY_RESOURCE_ETAG));

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(Scope.class).getType());
    }

    @Test
    public void shouldDeleteScope() {
        client.delete(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = SCOPES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.setTimeout(321);
        expectedRequest.setForceReturnTask(true);
        expectedRequest.addHeader(new BasicHeader(HttpHeaders.IF_MATCH, "*"));

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteScopeUsingETag() {
        client.delete(ANY_RESOURCE_ID, ANY_RESOURCE_ETAG, TaskTimeout.of(321));

        String expectedUri = SCOPES_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.setTimeout(321);
        expectedRequest.setForceReturnTask(true);
        expectedRequest.addHeader(new BasicHeader(HttpHeaders.IF_MATCH, ANY_RESOURCE_ETAG));

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldPatchScope() {
        ResourceAssignment assignment = new ResourceAssignment();

        client.patch(ANY_RESOURCE_ID, assignment, TaskTimeout.of(123));

        String expectedUri = SCOPES_URI + "/" + ANY_RESOURCE_ID + RESOURCE_ASSIGNMENTS;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, assignment);

        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
