/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import org.springframework.stereotype.Component;

@Component
public class ScmbMessage
{

    private String severity;// TODO make it enum class
    private String resourceName;
    private String resourceUri;
    private String resourceCategory;
    private String description;
    private String uri;
    private String alertType;
    private String alertState;

    public String getAlertState()
    {
        return alertState;
    }

    public void setAlertState(String alertState)
    {
        this.alertState = alertState;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getResourceUri()
    {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri)
    {
        this.resourceUri = resourceUri;
    }

    public String getResourceCategory()
    {
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory)
    {
        this.resourceCategory = resourceCategory;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public String getAlertType()
    {
        return alertType;
    }

    public void setAlertType(String alertType)
    {
        this.alertType = alertType;
    }

}
