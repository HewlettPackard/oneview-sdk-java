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

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.alerts.AlertResource;
import com.hp.ov.sdk.dto.alerts.AlertUpdate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class AlertClientTest {

    private static final String ANY_ALERT_RESOURCE_ID = "random-UUID";
    private static final String ANY_ALERT_FILTER = "random-Filter";
    private static final String ANY_ALERT_CHANGE_LOG_ID = "random-ChangeLog";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private AlertClient alertClient;

    @Test
    public void shouldGetAlert() {
        alertClient.getById(ANY_ALERT_RESOURCE_ID);

        String expectedUri = AlertClient.ALERTS_URI + "/" + ANY_ALERT_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, AlertResource.class);
    }

    @Test
    public void shouldGetAllAlerts() {
        alertClient.getAll();

        then(baseClient).should().getResourceCollection(AlertClient.ALERTS_URI, AlertResource.class);
    }

    @Test
    public void shouldUpdateAlert() {
        AlertUpdate alertUpdate = new AlertUpdate();

        alertClient.update(ANY_ALERT_RESOURCE_ID, alertUpdate);

        String expectedUri = AlertClient.ALERTS_URI + "/" + ANY_ALERT_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, alertUpdate);

        then(baseClient).should().executeRequest(request, AlertResource.class);
    }

    @Test
    public void shouldDeleteAlert() {
        alertClient.delete(ANY_ALERT_RESOURCE_ID);

        String expectedUri = AlertClient.ALERTS_URI + "/" + ANY_ALERT_RESOURCE_ID;
        Request request = new Request(HttpMethodType.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldDeleteAlertsByFilterWithFilter() {
        alertClient.deleteByFilter(Optional.of(ANY_ALERT_FILTER), false);

        UrlParameter filter = new UrlParameter("filter", ANY_ALERT_FILTER);

        then(baseClient).should().deleteResource(AlertClient.ALERTS_URI, false, filter);
    }

    @Test
    public void shouldDeleteAlertsByFilterWithoutFilter() {
        alertClient.deleteByFilter(Optional.<String>absent(), false);

        then(baseClient).should().deleteResource(AlertClient.ALERTS_URI, false);
    }

    @Test
    public void shouldDeleteAlertChangeLog() {
        alertClient.deleteAlertChangeLog(ANY_ALERT_CHANGE_LOG_ID);

        String expectedUri = AlertClient.ALERTS_CHANGELOG_URI + "/" + ANY_ALERT_CHANGE_LOG_ID;
        Request request = new Request(HttpMethodType.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

}
