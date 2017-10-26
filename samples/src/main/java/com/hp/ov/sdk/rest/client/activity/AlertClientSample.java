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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.alerts.AlertResource;
import com.hp.ov.sdk.dto.alerts.AlertUpdate;
import com.hp.ov.sdk.dto.alerts.AlertUrgency;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.BasicURIQuery;
import com.hp.ov.sdk.rest.http.core.URIQuery;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

public class AlertClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String ALERT_RESOURCE_ID = "4";
    private static final String ALERT_CHANGE_LOG_ID = "9";
    // ================================

    private final AlertClient client;

    private AlertClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.client = oneViewClient.alert();
    }

    private void getAlertById() {
        AlertResource alertResource = this.client.getById(ALERT_RESOURCE_ID);

        LOGGER.info("Alert returned to client: {}", alertResource.toJsonString());
    }

    private void getAllAlerts() {
        ResourceCollection<AlertResource> alertResources = this.client.getAll();

        LOGGER.info("Alerts returned to client (count): {}", alertResources.getCount());
    }

    private void getAllAlertsByState() {
        BasicURIQuery basicUriQuery = new BasicURIQuery();
        basicUriQuery.addParameter(URIQuery.FILTER, "alertState='Active'"); //Filters alerts by Active state
        basicUriQuery.addParameter(UrlParameter.getCountParameter(this.client.getAll().getCount())); //It guarantees all alerts will be returned

        ResourceCollection<AlertResource> alerts = this.client.get(basicUriQuery);

        LOGGER.info("Active alerts returned to client: {}", alerts.toJsonString());
    }

    private void updateAlert() {
        AlertResource alertResource = this.client.getById(ALERT_RESOURCE_ID);
        AlertUpdate alertUpdate = new AlertUpdate();

        alertUpdate.setETag(alertResource.getETag());
        alertUpdate.setNotes("Sample notes.");

        AlertResource updatedAlert = this.client.update(alertResource.getResourceId(), alertUpdate);

        LOGGER.info("Alert returned to client: {}", updatedAlert.toJsonString());
    }

    private void deleteAlert() {
        String response = this.client.delete(ALERT_RESOURCE_ID);

        LOGGER.info("Task returned to client: {}", response);
    }

    private void deleteAlertChangeLog() {
        String response = this.client.deleteAlertChangeLog(ALERT_CHANGE_LOG_ID);

        LOGGER.info("Task returned to client: {}", response);
    }

    private void deleteAlertsByFilter() {
        String filter = "urgency='" + AlertUrgency.High.toString() +"'"; // "urgency='High'"

        TaskResource taskResource = this.client.deleteByFilter(filter);

        LOGGER.info("Task returned to client: {}", taskResource.toJsonString());
    }

    public static void main(String[] args) {
        AlertClientSample sample = new AlertClientSample();

        sample.getAllAlerts();
        sample.getAllAlertsByState();
        sample.getAlertById();
        sample.updateAlert();

        sample.deleteAlertChangeLog();
        sample.deleteAlert();
        sample.deleteAlertsByFilter();
    }

}
