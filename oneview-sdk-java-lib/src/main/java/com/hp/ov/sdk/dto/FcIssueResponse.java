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
package com.hp.ov.sdk.dto;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FcIssueResponse {

    private String hostName;
    private String sanName;
    private String sanUri;
    private String initiatorName;
    private String initiatorWwn;
    private String description;
    private String type;
    private String category;
    private String created;
    private List<EndpointReport> endpointReports;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getSanName() {
        return sanName;
    }

    public void setSanName(String sanName) {
        this.sanName = sanName;
    }

    public String getSanUri() {
        return sanUri;
    }

    public void setSanUri(String sanUri) {
        this.sanUri = sanUri;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getInitiatorWwn() {
        return initiatorWwn;
    }

    public void setInitiatorWwn(String initiatorWwn) {
        this.initiatorWwn = initiatorWwn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public List<EndpointReport> getEndpointReports() {
        return endpointReports;
    }

    public void setEndpointReports(List<EndpointReport> endpointReports) {
        this.endpointReports = endpointReports;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof FcIssueResponse) {
            FcIssueResponse that = (FcIssueResponse) obj;

            return new EqualsBuilder()
                    .append(hostName, that.hostName)
                    .append(sanName, that.sanName)
                    .append(sanUri, that.sanUri)
                    .append(initiatorName, that.initiatorName)
                    .append(initiatorWwn, that.initiatorWwn)
                    .append(description, that.description)
                    .append(type, that.type)
                    .append(category, that.category)
                    .append(created, that.created)
                    .append(endpointReports, that.endpointReports)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(hostName)
                .append(sanName)
                .append(sanUri)
                .append(initiatorName)
                .append(initiatorWwn)
                .append(description)
                .append(type)
                .append(category)
                .append(created)
                .append(endpointReports)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("hostName", hostName)
                .append("sanName", sanName)
                .append("sanUri", sanUri)
                .append("initiatorName", initiatorName)
                .append("initiatorWwn", initiatorWwn)
                .append("description", description)
                .append("type", type)
                .append("category", category)
                .append("created", created)
                .append("endpointReports", endpointReports)
                .toString();
    }
}
