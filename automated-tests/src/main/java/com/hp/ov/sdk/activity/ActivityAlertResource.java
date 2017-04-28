/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.activity;

import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.alerts.AlertResource;
import com.hp.ov.sdk.dto.alerts.AlertUpdate;
import com.hp.ov.sdk.dto.alerts.AlertUrgency;
import com.hp.ov.sdk.dto.alerts.ChangeLog;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.activity.AlertClient;
import com.hp.ov.sdk.util.URIUtils;

public class ActivityAlertResource extends BasicResource implements Resource, RemoveResource, UpdateResource {

    private static ActivityAlertResource instance;

    private AlertClient client;

    public ActivityAlertResource() {
        client = oneViewClient.alert();
    }

    public static ActivityAlertResource getInstance() {
        if (instance == null) {
            instance = new ActivityAlertResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        return null;
    }

    @Override
    public String findById(String id) {
        try {
            return objToString(client.getById(id));
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    public String getFirstAlertId() {
        ResourceCollection<AlertResource> collection = client.getAll();
        String id = "";
        for (AlertResource alert : collection.getMembers()) {
            if (alert.getResourceId() != null) {
                id = alert.getResourceId();
                break;
            }
        }
        return id;
    }

    @Override
    public String update(String id) {
        AlertResource alert = client.getById(id);
        return objToString(client.update(id, builderUpdate(alert)));
    }

    public AlertUpdate builderUpdate(AlertResource alert) {
        AlertUpdate alertUpdate = new AlertUpdate();
        alertUpdate.setETag(alert.getETag());
        alertUpdate.setNotes(resourceProperties.get("note"));
        return alertUpdate;
    }

    @Override
    public String remove(String id) {
        AlertResource alert = client.getById(id);
        try {
            return client.delete(alert.getResourceId());
        } catch (Exception e) {
            return "not-allowed";
        }

    }

    public String removeByFilter() {
        String alertUrgency = resourceProperties.get("alertUrgency");
        String filter = "urgency='" + AlertUrgency.valueOf(alertUrgency).toString() + "'";
        return taskToString(client.deleteByFilter(filter));
    }

    public String removeChangeLog() {
        for (AlertResource alert : client.getAll().getMembers()) {
            if (!alert.getChangeLog().isEmpty()) {
                for (ChangeLog changeLog : alert.getChangeLog()) {
                    if (Boolean.valueOf(changeLog.getUserEntered())) {
                        String changeLogId = URIUtils.getResourceIdFromUri(changeLog.getUri());
                        return client.deleteAlertChangeLog(changeLogId);
                    }
                }
            }
        }
        return "not-allowed";
    }
}
