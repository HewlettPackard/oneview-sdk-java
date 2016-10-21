/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.messaging.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BaseMessageModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String associatedTask;
    private List<Object> changedAttributes = new ArrayList<>();
    private ChangeType changeType;
    private Object data;
    private String eTag;
    private String newState;
    private String newSubState;
    private String resourceUri;
    private Date timestamp;
    private Boolean userInitiatedTask;

    private T resource;

    /**
     * @return the associatedTask
     */
    public String getAssociatedTask() {
        return associatedTask;
    }

    /**
     * @param associatedTask the associatedTask to set
     */
    public void setAssociatedTask(String associatedTask) {
        this.associatedTask = associatedTask;
    }

    /**
     * @return the changedAttributes
     */
    public List<Object> getChangedAttributes() {
        return changedAttributes;
    }

    /**
     * @param changedAttributes the changedAttributes to set
     */
    public void setChangedAttributes(List<Object> changedAttributes) {
        this.changedAttributes = changedAttributes;
    }

    /**
     * @return the changeType
     */
    public ChangeType getChangeType() {
        return changeType;
    }

    /**
     * @param changeType the changeType to set
     */
    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the eTag
     */
    public String geteTag() {
        return eTag;
    }

    /**
     * @param eTag the eTag to set
     */
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * @return the newState
     */
    public String getNewState() {
        return newState;
    }

    /**
     * @param newState the newState to set
     */
    public void setNewState(String newState) {
        this.newState = newState;
    }

    /**
     * @return the newSubState
     */
    public String getNewSubState() {
        return newSubState;
    }

    /**
     * @param newSubState the newSubState to set
     */
    public void setNewSubState(String newSubState) {
        this.newSubState = newSubState;
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
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the userInitiatedTask
     */
    public Boolean getUserInitiatedTask() {
        return userInitiatedTask;
    }

    /**
     * @param userInitiatedTask the userInitiatedTask to set
     */
    public void setUserInitiatedTask(Boolean userInitiatedTask) {
        this.userInitiatedTask = userInitiatedTask;
    }

    /**
     * @return the resource
     */
    public T getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(T resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
