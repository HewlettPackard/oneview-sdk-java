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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseModelResource implements Serializable {

    private static final long serialVersionUID = 5688679045442246487L;

    private String name;
    private String description;
    private String state;
    private String status;
    private String uri;
    private String category;
    private String type;
    private String eTag;
    private String created;
    private String modified;

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(final String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(final String modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
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
                    .append(name, that.name)
                    .append(description, that.description)
                    .append(state, that.state)
                    .append(status, that.status)
                    .append(uri, that.uri)
                    .append(category, that.category)
                    .append(type, that.type)
                    .append(eTag, that.eTag)
                    .append(created, that.created)
                    .append(modified, that.modified)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(description)
                .append(state)
                .append(status)
                .append(uri)
                .append(category)
                .append(type)
                .append(eTag)
                .append(created)
                .append(modified)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("description", description)
                .append("state", state)
                .append("status", status)
                .append("uri", uri)
                .append("category", category)
                .append("type", type)
                .append("eTag", eTag)
                .append("created", created)
                .append("modified", modified)
                .toString();
    }
}
