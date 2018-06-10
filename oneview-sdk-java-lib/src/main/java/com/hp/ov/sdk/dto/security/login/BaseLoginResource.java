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
package com.hp.ov.sdk.dto.security.login;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.GsonBuilder;
import com.hp.ov.sdk.util.URIUtils;

public class BaseLoginResource implements Serializable {

    private static final long serialVersionUID = 8429633103952122801L;

    private String type;
    private String eTag;
    private Date created;
    private Date modified;
    private String category;
    private String uri;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     * @param eTag
     *            the eTag to set
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified
     *            the modified to set
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri
     *            the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getResourceId() {
        return URIUtils.getResourceIdFromUri(this.getUri());
    }

    public boolean canEqual(Object obj) {
        return (obj instanceof BaseLoginResource);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj instanceof BaseLoginResource) {
            BaseLoginResource that = (BaseLoginResource) obj;

            return that.canEqual(this) && new EqualsBuilder().append(category, that.category)
                    .append(created, that.created).append(eTag, that.eTag).append(modified, that.modified)
                    .append(type, that.type).append(uri, that.uri).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(category).append(created).append(eTag).append(modified).append(type)
                .append(uri).toHashCode();
    }

    public String toJsonString() {
        return System.getProperty("line.separator")
                + new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
