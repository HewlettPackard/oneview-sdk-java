/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.alerts;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.servers.HealthStatus;

public class AlertResource extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String activityUri;
    private AlertState alertState;
    private String alertTypeID;
    private String assignedToUser;
    private List<String> associatedEventUris = new ArrayList<String>();
    private AssociatedResource associatedResource;
    private List<ChangeLog> changeLog = new ArrayList<ChangeLog>();
    private List<String> childAlerts = new ArrayList<String>();
    private String clearedByUser;
    private String clearedTime;
    private String correctiveAction;
    private String healthCategory;
    private Boolean lifeCycle;
    private String parentAlert;
    private String physicalResourceType;
    private String resourceID;
    private String resourceUri;
    private ServiceEventData serviceEventDetails;
    private Boolean serviceEventSource;
    private HealthStatus severity;
    private AlertUrgency urgency;

    /**
     * @return the activityUri
     */
    public String getActivityUri() {
        return activityUri;
    }

    /**
     * @param activityUri the activityUri to set
     */
    public void setActivityUri(String activityUri) {
        this.activityUri = activityUri;
    }

    /**
     * @return the alertState
     */
    public AlertState getAlertState() {
        return alertState;
    }

    /**
     * @param alertState the alertState to set
     */
    public void setAlertState(AlertState alertState) {
        this.alertState = alertState;
    }

    /**
     * @return the alertTypeID
     */
    public String getAlertTypeID() {
        return alertTypeID;
    }

    /**
     * @param alertTypeID the alertTypeID to set
     */
    public void setAlertTypeID(String alertTypeID) {
        this.alertTypeID = alertTypeID;
    }

    /**
     * @return the assignedToUser
     */
    public String getAssignedToUser() {
        return assignedToUser;
    }

    /**
     * @param assignedToUser the assignedToUser to set
     */
    public void setAssignedToUser(String assignedToUser) {
        this.assignedToUser = assignedToUser;
    }

    /**
     * @return the associatedEventUris
     */
    public List<String> getAssociatedEventUris() {
        return associatedEventUris;
    }

    /**
     * @param associatedEventUris the associatedEventUris to set
     */
    public void setAssociatedEventUris(List<String> associatedEventUris) {
        this.associatedEventUris = associatedEventUris;
    }

    /**
     * @return the associatedResource
     */
    public AssociatedResource getAssociatedResource() {
        return associatedResource;
    }

    /**
     * @param associatedResource the associatedResource to set
     */
    public void setAssociatedResource(AssociatedResource associatedResource) {
        this.associatedResource = associatedResource;
    }

    /**
     * @return the changeLog
     */
    public List<ChangeLog> getChangeLog() {
        return changeLog;
    }

    /**
     * @param changeLog the changeLog to set
     */
    public void setChangeLog(List<ChangeLog> changeLog) {
        this.changeLog = changeLog;
    }

    /**
     * @return the childAlerts
     */
    public List<String> getChildAlerts() {
        return childAlerts;
    }

    /**
     * @param childAlerts the childAlerts to set
     */
    public void setChildAlerts(List<String> childAlerts) {
        this.childAlerts = childAlerts;
    }

    /**
     * @return the clearedByUser
     */
    public String getClearedByUser() {
        return clearedByUser;
    }

    /**
     * @param clearedByUser the clearedByUser to set
     */
    public void setClearedByUser(String clearedByUser) {
        this.clearedByUser = clearedByUser;
    }

    /**
     * @return the clearedTime
     */
    public String getClearedTime() {
        return clearedTime;
    }

    /**
     * @param clearedTime the clearedTime to set
     */
    public void setClearedTime(String clearedTime) {
        this.clearedTime = clearedTime;
    }

    /**
     * @return the correctiveAction
     */
    public String getCorrectiveAction() {
        return correctiveAction;
    }

    /**
     * @param correctiveAction the correctiveAction to set
     */
    public void setCorrectiveAction(String correctiveAction) {
        this.correctiveAction = correctiveAction;
    }

    /**
     * @return the healthCategory
     */
    public String getHealthCategory() {
        return healthCategory;
    }

    /**
     * @param healthCategory the healthCategory to set
     */
    public void setHealthCategory(String healthCategory) {
        this.healthCategory = healthCategory;
    }

    /**
     * @return the lifeCycle
     */
    public Boolean getLifeCycle() {
        return lifeCycle;
    }

    /**
     * @param lifeCycle the lifeCycle to set
     */
    public void setLifeCycle(Boolean lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    /**
     * @return the parentAlert
     */
    public String getParentAlert() {
        return parentAlert;
    }

    /**
     * @param parentAlert the parentAlert to set
     */
    public void setParentAlert(String parentAlert) {
        this.parentAlert = parentAlert;
    }

    /**
     * @return the physicalResourceType
     */
    public String getPhysicalResourceType() {
        return physicalResourceType;
    }

    /**
     * @param physicalResourceType the physicalResourceType to set
     */
    public void setPhysicalResourceType(String physicalResourceType) {
        this.physicalResourceType = physicalResourceType;
    }

    /**
     * @return the resourceID
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * @param resourceID the resourceID to set
     */
    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * @return the resourceUri
     */
    public String getResourceUri() {
        return resourceUri;
    }

    /**
     * @param resourceUri the resourceUri to set
     */
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    /**
     * @return the serviceEventDetails
     */
    public ServiceEventData getServiceEventDetails() {
        return serviceEventDetails;
    }

    /**
     * @param serviceEventDetails the serviceEventDetails to set
     */
    public void setServiceEventDetails(ServiceEventData serviceEventDetails) {
        this.serviceEventDetails = serviceEventDetails;
    }

    /**
     * @return the serviceEventSource
     */
    public Boolean getServiceEventSource() {
        return serviceEventSource;
    }

    /**
     * @param serviceEventSource the serviceEventSource to set
     */
    public void setServiceEventSource(Boolean serviceEventSource) {
        this.serviceEventSource = serviceEventSource;
    }

    /**
     * @return the severity
     */
    public HealthStatus getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    public void setSeverity(HealthStatus severity) {
        this.severity = severity;
    }

    /**
     * @return the urgency
     */
    public AlertUrgency getUrgency() {
        return urgency;
    }

    /**
     * @param urgency the urgency to set
     */
    public void setUrgency(AlertUrgency urgency) {
        this.urgency = urgency;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(activityUri)
                .append(alertState)
                .append(alertTypeID)
                .append(assignedToUser)
                .append(associatedEventUris)
                .append(associatedResource)
                .append(changeLog)
                .append(childAlerts)
                .append(clearedByUser)
                .append(clearedTime)
                .append(correctiveAction)
                .append(healthCategory)
                .append(lifeCycle)
                .append(parentAlert)
                .append(physicalResourceType)
                .append(resourceID)
                .append(resourceUri)
                .append(serviceEventDetails)
                .append(serviceEventSource)
                .append(severity)
                .append(urgency)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AlertResource) == false) {
            return false;
        }
        AlertResource rhs = ((AlertResource) other);
        return new EqualsBuilder()
                .append(activityUri, rhs.activityUri)
                .append(alertState, rhs.alertState)
                .append(alertTypeID, rhs.alertTypeID)
                .append(assignedToUser, rhs.assignedToUser)
                .append(associatedEventUris, rhs.associatedEventUris)
                .append(associatedResource, rhs.associatedResource)
                .append(changeLog, rhs.changeLog)
                .append(childAlerts, rhs.childAlerts)
                .append(clearedByUser, rhs.clearedByUser)
                .append(clearedTime, rhs.clearedTime)
                .append(correctiveAction, rhs.correctiveAction)
                .append(healthCategory, rhs.healthCategory)
                .append(lifeCycle, rhs.lifeCycle)
                .append(parentAlert, rhs.parentAlert)
                .append(physicalResourceType, rhs.physicalResourceType)
                .append(resourceID, rhs.resourceID)
                .append(resourceUri, rhs.resourceUri)
                .append(serviceEventDetails, rhs.serviceEventDetails)
                .append(serviceEventSource, rhs.serviceEventSource)
                .append(severity, rhs.severity)
                .append(urgency, rhs.urgency)
                .appendSuper(super.equals(other))
                .isEquals();
        }

}

