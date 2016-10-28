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

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.alerts.AlertResource;
import com.hp.ov.sdk.dto.alerts.AlertUpdate;
import com.hp.ov.sdk.rest.client.common.RetrievableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(AlertClient.ALERTS_URI)
public interface AlertClient extends
        RetrievableResource<AlertResource> {

    String ALERTS_URI = "/rest/alerts";
    String ALERTS_CHANGELOG_URI = "/AlertChangeLog";

    /**
     * Updates the resource identified by the <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param resource object containing the details of the resource that should be created.
     *
     * @return the updated {@link AlertResource}.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    AlertResource update(@PathParam("resourceId") String resourceId, @BodyParam AlertUpdate resource);

    /**
     * Deletes the {@link AlertResource} identified by the given resource identifier.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the response of the process.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String delete(@PathParam("resourceId") String resourceId);

    /**
     * Deletes all the {@link AlertResource}(s) that match the filter. If no filter
     * is specified all alerts will be deleted (excepted those not permitted).
     * The actual deletion will proceed asynchronously. Although, the method can
     * process the request asynchronously or synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "?filter={filter}", method = HttpMethod.DELETE)
    TaskResource deleteByFilter(@PathParam("filter") String filter, RequestOption... options);

    /**
     * Deletes the alert change log item identified by changeLogId. Only user-entered change logs
     * can be deleted by the user (the change logs generated by the system cannot be deleted by the user).
     * Alerts cleared when processing an event with clearPriorEvents set to true have a system-generated
     * note indicating the automatic clearing of the alerts.
     *
     * @param changeLogId alert change log identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the response of the process.
     */
    @Endpoint(uri = ALERTS_CHANGELOG_URI + "/{changeLogId}", method = HttpMethod.DELETE)
    String deleteAlertChangeLog(@PathParam("changeLogId") String changeLogId);

}
