/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.samples;

public class ScmbMessage {

    private String severity;// TODO make it enum class
    private String resourceName;
    private String resourceUri;
    private String resourceCategory;
    private String description;
    private String uri;
    private String alertType;
    private String alertState;

    public String getAlertState() {
        return alertState;
    }

    public void setAlertState(final String alertState) {
        this.alertState = alertState;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(final String severity) {
        this.severity = severity;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(final String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(final String resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(final String alertType) {
        this.alertType = alertType;
    }

}
