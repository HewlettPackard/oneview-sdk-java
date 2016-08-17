/*
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
 */
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.GsonBuilder;
import com.hp.ov.sdk.util.UrlUtils;

public class BaseModelResource implements Serializable {

    private static final long serialVersionUID = 5688679045442246487L;

    private String category;
    private String created;
    private String description;
    private String eTag;
    private String modified;
    private String name;
    private String state;
    private String status;
    private String type;
    private String uri;

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the created
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     * @param eTag the eTag to set
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * @return the modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getResourceId() {
        return UrlUtils.getResourceIdFromUri(this.getUri());
    }

    public boolean canEqual(Object obj) {
        return (obj instanceof BaseModelResource);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof BaseModelResource) {
            BaseModelResource that = (BaseModelResource) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .append(category, that.category)
                    .append(created, that.created)
                    .append(description, that.description)
                    .append(eTag, that.eTag)
                    .append(modified, that.modified)
                    .append(name, that.name)
                    .append(state, that.state)
                    .append(status, that.status)
                    .append(type, that.type)
                    .append(uri, that.uri)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(category)
                .append(created)
                .append(description)
                .append(eTag)
                .append(modified)
                .append(name)
                .append(state)
                .append(status)
                .append(type)
                .append(uri)
                .toHashCode();
    }

    public String toJsonString() {
        return System.getProperty("line.separator")
            + new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create().toJson(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
