/*
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
 */

package com.hp.ov.sdk.dto.fcsans;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EndpointsCsvFileResponse implements Serializable {

    private static final long serialVersionUID = 6040235784309182917L;

    private String category;
    private String created;
    private String csvFileName;
    private String eTag;
    private String modified;
    private String type;
    private String uri;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof EndpointsCsvFileResponse) {
            EndpointsCsvFileResponse that = (EndpointsCsvFileResponse) o;

            return new EqualsBuilder()
                    .append(category, that.category)
                    .append(created, that.created)
                    .append(csvFileName, that.csvFileName)
                    .append(eTag, that.eTag)
                    .append(modified, that.modified)
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
                .append(csvFileName)
                .append(eTag)
                .append(modified)
                .append(type)
                .append(uri)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("category", category)
                .append("created", created)
                .append("csvFileName", csvFileName)
                .append("eTag", eTag)
                .append("modified", modified)
                .append("type", type)
                .append("uri", uri)
                .toString();
    }
}
