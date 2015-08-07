/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class BaseMessagingAlertModel implements Serializable {

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

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(final String eTag) {
        this.eTag = eTag;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(final String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(final String changeType) {
        this.changeType = changeType;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(final String newState) {
        this.newState = newState;
    }

    public String getAssociatedTask() {
        return associatedTask;
    }

    public void setAssociatedTask(final String associatedTask) {
        this.associatedTask = associatedTask;
    }

    public String getUserInitiatedTask() {
        return userInitiatedTask;
    }

    public void setUserInitiatedTask(final String userInitiatedTask) {
        this.userInitiatedTask = userInitiatedTask;
    }

    public List<String> getChangedAttributes() {
        return changedAttributes;
    }

    public void setChangedAttributes(final List<String> changedAttributes) {
        this.changedAttributes = changedAttributes;
    }

    public String getNewSubState() {
        return newSubState;
    }

    public void setNewSubState(final String newSubState) {
        this.newSubState = newSubState;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

}
