/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class ScmbAlertsResource extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String severity;
    private AssociatedResource associatedResource;
    private String resourceUri;
    private String physicalResourceType;
    private String alertState;
    private List<String> associatedEventUris = new ArrayList<String>();
    private String healthCategory;
    private String urgency;
    private String clearedTime;
    private String lifeCycle;
    private String assignedToUser;
    private String clearedByUser;
    private String alertTypeID;
    private String activityUri;
    private String correctiveAction;
    private String resourceID;
    private List<ChangeLog> changeLog = new ArrayList<ChangeLog>();

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(final String severity) {
        this.severity = severity;
    }

    public AssociatedResource getAssociatedResource() {
        return associatedResource;
    }

    public void setAssociatedResource(final AssociatedResource associatedResource) {
        this.associatedResource = associatedResource;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(final String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getPhysicalResourceType() {
        return physicalResourceType;
    }

    public void setPhysicalResourceType(final String physicalResourceType) {
        this.physicalResourceType = physicalResourceType;
    }

    public String getAlertState() {
        return alertState;
    }

    public void setAlertState(final String alertState) {
        this.alertState = alertState;
    }

    public List<String> getAssociatedEventUris() {
        return associatedEventUris;
    }

    public void setAssociatedEventUris(final List<String> associatedEventUris) {
        this.associatedEventUris = associatedEventUris;
    }

    public String getHealthCategory() {
        return healthCategory;
    }

    public void setHealthCategory(final String healthCategory) {
        this.healthCategory = healthCategory;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(final String urgency) {
        this.urgency = urgency;
    }

    public String getClearedTime() {
        return clearedTime;
    }

    public void setClearedTime(final String clearedTime) {
        this.clearedTime = clearedTime;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(final String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getAssignedToUser() {
        return assignedToUser;
    }

    public void setAssignedToUser(final String assignedToUser) {
        this.assignedToUser = assignedToUser;
    }

    public String getClearedByUser() {
        return clearedByUser;
    }

    public void setClearedByUser(final String clearedByUser) {
        this.clearedByUser = clearedByUser;
    }

    public String getAlertTypeID() {
        return alertTypeID;
    }

    public void setAlertTypeID(final String alertTypeID) {
        this.alertTypeID = alertTypeID;
    }

    public String getActivityUri() {
        return activityUri;
    }

    public void setActivityUri(final String activityUri) {
        this.activityUri = activityUri;
    }

    public String getCorrectiveAction() {
        return correctiveAction;
    }

    public void setCorrectiveAction(final String correctiveAction) {
        this.correctiveAction = correctiveAction;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(final String resourceID) {
        this.resourceID = resourceID;
    }

    public List<ChangeLog> getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(final List<ChangeLog> changeLog) {
        this.changeLog = changeLog;
    }

}
