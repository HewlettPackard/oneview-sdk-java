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
package com.hp.ov.sdk.dto.storage.driveenclosures;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PhysicalPorts implements Serializable {

    private static final long serialVersionUID = 1L;

    private String interconnectName;
    private String interconnectPortNumber;
    private String interconnectUri;
    private String physicalInterconnectName;
    private String physicalInterconnectPortNumber;
    private String physicalInterconnectUri;
    private String type;

    /**
     * @return the interconnectName
     */
    public String getInterconnectName() {
        return interconnectName;
    }

    /**
     * @param interconnectName the interconnectName to set
     */
    public void setInterconnectName(String interconnectName) {
        this.interconnectName = interconnectName;
    }

    /**
     * @return the interconnectPortNumber
     */
    public String getInterconnectPortNumber() {
        return interconnectPortNumber;
    }

    /**
     * @param interconnectPortNumber the interconnectPortNumber to set
     */
    public void setInterconnectPortNumber(String interconnectPortNumber) {
        this.interconnectPortNumber = interconnectPortNumber;
    }

    /**
     * @return the interconnectUri
     */
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     * @param interconnectUri the interconnectUri to set
     */
    public void setInterconnectUri(String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the physicalInterconnectName
     */
    public String getPhysicalInterconnectName() {
        return physicalInterconnectName;
    }

    /**
     * @param physicalInterconnectName the physicalInterconnectName to set
     */
    public void setPhysicalInterconnectName(String physicalInterconnectName) {
        this.physicalInterconnectName = physicalInterconnectName;
    }

    /**
     * @return the physicalInterconnectPortNumber
     */
    public String getPhysicalInterconnectPortNumber() {
        return physicalInterconnectPortNumber;
    }

    /**
     * @param physicalInterconnectPortNumber the physicalInterconnectPortNumber to set
     */
    public void setPhysicalInterconnectPortNumber(String physicalInterconnectPortNumber) {
        this.physicalInterconnectPortNumber = physicalInterconnectPortNumber;
    }

    /**
     * @return the physicalInterconnectUri
     */
    public String getPhysicalInterconnectUri() {
        return physicalInterconnectUri;
    }

    /**
     * @param physicalInterconnectUri the physicalInterconnectUri to set
     */
    public void setPhysicalInterconnectUri(String physicalInterconnectUri) {
        this.physicalInterconnectUri = physicalInterconnectUri;
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
