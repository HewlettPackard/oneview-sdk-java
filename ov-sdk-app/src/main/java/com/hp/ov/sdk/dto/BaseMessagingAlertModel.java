/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class BaseMessagingAlertModel implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String data;
    private String eTag;
    private String resourceUri;
    private String changeType;
    private String newState;
    private String associatedTask;
    private String userInitiatedTask;
    private List<String> changedAttributes = new ArrayList<String>();
    private String newSubState;
    private String timestamp;
    private String uri;

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String geteTag()
    {
        return eTag;
    }

    public void seteTag(String eTag)
    {
        this.eTag = eTag;
    }

    public String getResourceUri()
    {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri)
    {
        this.resourceUri = resourceUri;
    }

    public String getChangeType()
    {
        return changeType;
    }

    public void setChangeType(String changeType)
    {
        this.changeType = changeType;
    }

    public String getNewState()
    {
        return newState;
    }

    public void setNewState(String newState)
    {
        this.newState = newState;
    }

    public String getAssociatedTask()
    {
        return associatedTask;
    }

    public void setAssociatedTask(String associatedTask)
    {
        this.associatedTask = associatedTask;
    }

    public String getUserInitiatedTask()
    {
        return userInitiatedTask;
    }

    public void setUserInitiatedTask(String userInitiatedTask)
    {
        this.userInitiatedTask = userInitiatedTask;
    }

    public List<String> getChangedAttributes()
    {
        return changedAttributes;
    }

    public void setChangedAttributes(List<String> changedAttributes)
    {
        this.changedAttributes = changedAttributes;
    }

    public String getNewSubState()
    {
        return newSubState;
    }

    public void setNewSubState(String newSubState)
    {
        this.newSubState = newSubState;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

}
