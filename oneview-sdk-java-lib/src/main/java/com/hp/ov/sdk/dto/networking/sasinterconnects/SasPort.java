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
package com.hp.ov.sdk.dto.networking.sasinterconnects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class SasPort extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String containerDeviceUri;
    private boolean enabled;
    private String linkedPortUri;
    private int phyCount;
    private String portIdentifier;
    private String portLocation;
    private String portName;
    private SasPortStatusReason portStatusReason;
    private SasPortType portType;

    /**
     * @return the containerDeviceUri
     */
    public String getContainerDeviceUri() {
        return containerDeviceUri;
    }

    /**
     * @param containerDeviceUri the containerDeviceUri to set
     */
    public void setContainerDeviceUri(String containerDeviceUri) {
        this.containerDeviceUri = containerDeviceUri;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the linkedPortUri
     */
    public String getLinkedPortUri() {
        return linkedPortUri;
    }

    /**
     * @param linkedPortUri the linkedPortUri to set
     */
    public void setLinkedPortUri(String linkedPortUri) {
        this.linkedPortUri = linkedPortUri;
    }

    /**
     * @return the phyCount
     */
    public int getPhyCount() {
        return phyCount;
    }

    /**
     * @param phyCount the phyCount to set
     */
    public void setPhyCount(int phyCount) {
        this.phyCount = phyCount;
    }

    /**
     * @return the portIdentifier
     */
    public String getPortIdentifier() {
        return portIdentifier;
    }

    /**
     * @param portIdentifier the portIdentifier to set
     */
    public void setPortIdentifier(String portIdentifier) {
        this.portIdentifier = portIdentifier;
    }

    /**
     * @return the portLocation
     */
    public String getPortLocation() {
        return portLocation;
    }

    /**
     * @param portLocation the portLocation to set
     */
    public void setPortLocation(String portLocation) {
        this.portLocation = portLocation;
    }

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * @return the portStatusReason
     */
    public SasPortStatusReason getPortStatusReason() {
        return portStatusReason;
    }

    /**
     * @param portStatusReason the portStatusReason to set
     */
    public void setPortStatusReason(SasPortStatusReason portStatusReason) {
        this.portStatusReason = portStatusReason;
    }

    /**
     * @return the portType
     */
    public SasPortType getPortType() {
        return portType;
    }

    /**
     * @param portType the portType to set
     */
    public void setPortType(SasPortType portType) {
        this.portType = portType;
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
