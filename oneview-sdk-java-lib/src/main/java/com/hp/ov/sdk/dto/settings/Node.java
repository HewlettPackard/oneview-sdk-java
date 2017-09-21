/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.dto.settings;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Node implements Serializable {

    private static final long serialVersionUID = -643183378943090508L;

    private String appliedDate;
    private String eTag;
    private String nodeId;
    private Boolean nodeManaged;
    private String nodeName;
    private String nodeUri;

    /**
     * @return the appliedDate
     */
    public String getAppliedDate() {
        return appliedDate;
    }

    /**
     * @param appliedDate
     *            the appliedDate to set
     */
    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    /**
     * @return the eTag
     */
    public String geteTag() {
        return eTag;
    }

    /**
     * @param eTag
     *            the eTag to set
     */
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the nodeManaged
     */
    public Boolean getNodeManaged() {
        return nodeManaged;
    }

    /**
     * @param nodeManaged
     *            the nodeManaged to set
     */
    public void setNodeManaged(Boolean nodeManaged) {
        this.nodeManaged = nodeManaged;
    }

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName
     *            the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @return the nodeUri
     */
    public String getNodeUri() {
        return nodeUri;
    }

    /**
     * @param nodeUri
     *            the nodeUri to set
     */
    public void setNodeUri(String nodeUri) {
        this.nodeUri = nodeUri;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof Node) == false) {
            return false;
        }
        final Node that = (Node) obj;
        return new EqualsBuilder()
                .append(appliedDate, that.appliedDate)
                .append(eTag, that.eTag)
                .append(nodeId, that.nodeId)
                .append(nodeManaged, that.nodeManaged)
                .append(nodeName, that.nodeName)
                .append(nodeUri, that.nodeUri)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(appliedDate)
                .append(eTag)
                .append(nodeId)
                .append(nodeManaged)
                .append(nodeName)
                .append(nodeUri)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
