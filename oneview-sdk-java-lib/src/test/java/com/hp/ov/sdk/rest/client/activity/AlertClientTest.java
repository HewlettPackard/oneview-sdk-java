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

package com.hp.ov.sdk.rest.client.activity;

import static com.hp.ov.sdk.rest.client.activity.AlertClient.ALERTS_CHANGELOG_URI;
import static com.hp.ov.sdk.rest.client.activity.AlertClient.ALERTS_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.alerts.AlertResource;
import com.hp.ov.sdk.dto.alerts.AlertUpdate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class AlertClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_FILTER = "random-Filter";

    private BaseClient baseClient = mock(BaseClient.class);
    private AlertClient client = Reflection.newProxy(AlertClient.class,
            new ClientRequestHandler<>(baseClient, AlertClient.class));

    @Test
    public void shouldGetAlert() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ALERTS_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(AlertResource.class).getType());
    }

    @Test
    public void shouldGetAllAlerts() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, ALERTS_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<AlertResource>>() {}.getType());
    }

    @Test
    public void shouldUpdateAlert() {
        AlertUpdate alertUpdate = new AlertUpdate();

        client.update(ANY_RESOURCE_ID, alertUpdate);

        String expectedUri = ALERTS_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, alertUpdate);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(AlertResource.class).getType());
    }

    @Test
    public void shouldDeleteAlert() {
        client.delete(ANY_RESOURCE_ID);

        String expectedUri = ALERTS_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldDeleteAlertsByFilterWithFilter() {
        client.deleteByFilter(ANY_FILTER, TaskTimeout.of(321));

        String expectedUri = ALERTS_URI + "?filter=" + ANY_FILTER;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteAlertChangeLog() {
        client.deleteAlertChangeLog(ANY_RESOURCE_ID);

        String expectedUri = ALERTS_URI + ALERTS_CHANGELOG_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

}
