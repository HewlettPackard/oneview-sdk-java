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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.dto.alerts.AlertResource;
import com.hp.ov.sdk.dto.alerts.AlertUpdate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class AlertClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertClient.class);

    protected static final String ALERTS_URI = "/rest/alerts";
    protected static final String ALERTS_CHANGELOG_URI = "/rest/alerts/AlertChangeLog";

    private final BaseClient baseClient;

    public AlertClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link AlertResource} details for the specified alert.
     *
     * @param resourceId alert resource identifier as seen in HPE OneView.
     *
     * @return {@link AlertResource} object containing the details.
     */
    public AlertResource getById(String resourceId) {
        LOGGER.info("AlertClient : getById : Start");

        AlertResource alert = baseClient.getResource(UrlUtils.createUrl(ALERTS_URI, resourceId), AlertResource.class);

        LOGGER.info("AlertClient : getById : End");

        return alert;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link AlertResource}&gt; containing details
     * for all the available alerts found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link AlertResource}&gt; containing
     * the details for all found alerts.
     */
    public ResourceCollection<AlertResource> getAll() {
        LOGGER.info("AlertClient : getAll : Start");

        ResourceCollection<AlertResource> alerts = baseClient.getResourceCollection(ALERTS_URI, AlertResource.class);

        LOGGER.info("AlertClient : getAll : End");

        return alerts;
    }

    /**
     * Updates a {@link AlertResource} identified by the given resource identifier.
     *
     * @param resourceId alert resource identifier as seen in HPE OneView.
     * @param alertUpdate object containing the alert details.
     *
     * @return {@link AlertResource} containing the updated alert.
     */
    public AlertResource update(String resourceId, AlertUpdate alertUpdate) {
        LOGGER.info("AlertClient : update : Start");

        Request request = new Request(HttpMethod.PUT, UrlUtils.createUrl(ALERTS_URI, resourceId), alertUpdate);

        AlertResource updatedAlert = this.baseClient.executeRequest(request, AlertResource.class);

        LOGGER.info("AlertClient : update : End");

        return updatedAlert;
    }

    /**
     * Deletes the {@link AlertResource} identified by the given resource identifier.
     *
     * @param resourceId alert resource identifier as seen in HPE OneView.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource delete(String resourceId) {
        LOGGER.info("DataCenterClient : delete : Start");

        Request request = new Request(HttpMethod.DELETE, UrlUtils.createUrl(ALERTS_URI, resourceId));

        this.baseClient.executeRequest(request, String.class);

        LOGGER.info("DataCenterClient : delete : End");

        return this.buildCompleteTask("Delete alert.");
    }

    /**
     * Deletes all the {@link AlertResource}(s) that match the filter. If no filter
     * is specified all alerts will be deleted (excepted those not permitted).
     * The actual deletion will proceed asynchronously. Although, the method can
     * process the request asynchronously or synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource deleteByFilter(Optional<String> filter, boolean aSync) {
        LOGGER.info("AlertClient : deleteByFilter : Start");

        TaskResource taskResource;

        if (filter.isPresent()) {
            taskResource = baseClient.deleteResource(ALERTS_URI, aSync,
                    new UrlParameter("filter", filter.get()));
        } else {
            taskResource = baseClient.deleteResource(ALERTS_URI, aSync);
        }

        LOGGER.info("AlertClient : deleteByFilter : End");

        return taskResource;
    }

    /**
     * Deletes the alert change log item identified by changeLogId. Only user-entered change logs
     * can be deleted by the user (the change logs generated by the system cannot be deleted by the user).
     * Alerts cleared when processing an event with clearPriorEvents set to true have a system-generated
     * note indicating the automatic clearing of the alerts.
     *
     * @param changeLogId alert change log identifier as seen in HPE OneView.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource deleteAlertChangeLog(String changeLogId) {
        LOGGER.info("DataCenterClient : deleteAlertChangeLog : Start");

        Request request = new Request(HttpMethod.DELETE, UrlUtils.createUrl(ALERTS_CHANGELOG_URI, changeLogId));

        this.baseClient.executeRequest(request, String.class);

        LOGGER.info("DataCenterClient : deleteAlertChangeLog : End");

        return this.buildCompleteTask("Delete alert change log.");
    }

    private TaskResource buildCompleteTask(String name) {
        TaskResource task = new TaskResource();
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date());

        task.setName(name);
        task.setComputedPercentComplete(Integer.valueOf(100));
        task.setPercentComplete(Integer.valueOf(100));
        task.setTaskState(TaskState.Completed);
        task.setCreated(date);
        task.setModified(date);

        return task;
    }

}
