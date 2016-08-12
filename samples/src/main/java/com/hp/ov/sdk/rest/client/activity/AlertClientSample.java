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

import com.google.common.base.Optional;
import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.alerts.AlertResource;
import com.hp.ov.sdk.dto.alerts.AlertUpdate;
import com.hp.ov.sdk.dto.alerts.AlertUrgency;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class AlertClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String ALERT_RESOURCE_ID = "56";
    private static final String ALERT_CHANGE_LOG_ID = "107";
    // ================================

    private final AlertClient client;

    private AlertClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.alert();
    }

    private void getAlertById() {
        AlertResource alertResource = this.client.getById(ALERT_RESOURCE_ID);

        LOGGER.info("Alert returned to client: {}", alertResource.toJsonString());
    }

    private void getAllAlerts() {
        ResourceCollection<AlertResource> alertResources = this.client.getAll();

        LOGGER.info("Alerts returned to client: {}", alertResources.toJsonString());
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
        TaskResourceV2 taskResource = this.client.delete(ALERT_RESOURCE_ID);

        LOGGER.info("Task returned to client: {}", taskResource.toJsonString());
    }

    private void deleteAlertChangeLog() {
        TaskResourceV2 taskResource = this.client.deleteAlertChangeLog(ALERT_CHANGE_LOG_ID);

        LOGGER.info("Task returned to client: {}", taskResource.toJsonString());
    }

    private void deleteAlertsByFilter() {
        String filter = "urgency='" + AlertUrgency.Medium.toString() +"'"; // "urgency='Medium'"

        TaskResourceV2 taskResource = this.client.deleteByFilter(Optional.of(filter), false);

        LOGGER.info("Task returned to client: {}", taskResource.toJsonString());
    }

    public static void main(String[] args) {
        AlertClientSample sample = new AlertClientSample();

        sample.getAlertById();
        sample.getAllAlerts();
        sample.updateAlert();

        sample.deleteAlertChangeLog();
        sample.deleteAlert();
        sample.deleteAlertsByFilter();
    }

}
