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
package com.hp.ov.sdk.dto.servers.enclosure;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.servers.HealthStatus;

public class EnclosureManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bayNumber;
    private BayPowerState bayPowerState;
    private ChangeState changeState;
    private DevicePresence devicePresence;
    private String fwBuildDate;
    private String fwVersion;
    private String ipAddress;
    private Boolean linkPortIsolated;
    private String linkPortSpeedGbs;
    private PortState linkPortState;
    private HealthStatus linkPortStatus;
    private LinkedEnclosure linkedEnclosure;
    private PortState mgmtPortLinkState;
    private String mgmtPortSpeedGbs;
    private MgmtPortMode mgmtPortState;
    private HealthStatus mgmtPortStatus;
    private String model;
    private String negotiatedLinkPortSpeedGbs;
    private Integer negotiatedMgmtPortSpeedGbs;
    private String partNumber;
    private ManagerRole role;
    private String serialNumber;
    private String sparePartNumber;
    private HealthStatus status;
    private UidState uidState;

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber the bayNumber to set
     */
    public void setBayNumber(Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the bayPowerState
     */
    public BayPowerState getBayPowerState() {
        return bayPowerState;
    }

    /**
     * @param bayPowerState the bayPowerState to set
     */
    public void setBayPowerState(BayPowerState bayPowerState) {
        this.bayPowerState = bayPowerState;
    }

    /**
     * @return the changeState
     */
    public ChangeState getChangeState() {
        return changeState;
    }

    /**
     * @param changeState the changeState to set
     */
    public void setChangeState(ChangeState changeState) {
        this.changeState = changeState;
    }

    /**
     * @return the devicePresence
     */
    public DevicePresence getDevicePresence() {
        return devicePresence;
    }

    /**
     * @param devicePresence the devicePresence to set
     */
    public void setDevicePresence(DevicePresence devicePresence) {
        this.devicePresence = devicePresence;
    }

    /**
     * @return the fwBuildDate
     */
    public String getFwBuildDate() {
        return fwBuildDate;
    }

    /**
     * @param fwBuildDate the fwBuildDate to set
     */
    public void setFwBuildDate(String fwBuildDate) {
        this.fwBuildDate = fwBuildDate;
    }

    /**
     * @return the fwVersion
     */
    public String getFwVersion() {
        return fwVersion;
    }

    /**
     * @param fwVersion the fwVersion to set
     */
    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the linkPortIsolated
     */
    public Boolean getLinkPortIsolated() {
        return linkPortIsolated;
    }

    /**
     * @param linkPortIsolated the linkPortIsolated to set
     */
    public void setLinkPortIsolated(Boolean linkPortIsolated) {
        this.linkPortIsolated = linkPortIsolated;
    }

    /**
     * @return the linkPortSpeedGbs
     */
    public String getLinkPortSpeedGbs() {
        return linkPortSpeedGbs;
    }

    /**
     * @param linkPortSpeedGbs the linkPortSpeedGbs to set
     */
    public void setLinkPortSpeedGbs(String linkPortSpeedGbs) {
        this.linkPortSpeedGbs = linkPortSpeedGbs;
    }

    /**
     * @return the linkPortState
     */
    public PortState getLinkPortState() {
        return linkPortState;
    }

    /**
     * @param linkPortState the linkPortState to set
     */
    public void setLinkPortState(PortState linkPortState) {
        this.linkPortState = linkPortState;
    }

    /**
     * @return the linkPortStatus
     */
    public HealthStatus getLinkPortStatus() {
        return linkPortStatus;
    }

    /**
     * @param linkPortStatus the linkPortStatus to set
     */
    public void setLinkPortStatus(HealthStatus linkPortStatus) {
        this.linkPortStatus = linkPortStatus;
    }

    /**
     * @return the linkedEnclosure
     */
    public LinkedEnclosure getLinkedEnclosure() {
        return linkedEnclosure;
    }

    /**
     * @param linkedEnclosure the linkedEnclosure to set
     */
    public void setLinkedEnclosure(LinkedEnclosure linkedEnclosure) {
        this.linkedEnclosure = linkedEnclosure;
    }

    /**
     * @return the mgmtPortLinkState
     */
    public PortState getMgmtPortLinkState() {
        return mgmtPortLinkState;
    }

    /**
     * @param mgmtPortLinkState the mgmtPortLinkState to set
     */
    public void setMgmtPortLinkState(PortState mgmtPortLinkState) {
        this.mgmtPortLinkState = mgmtPortLinkState;
    }

    /**
     * @return the mgmtPortSpeedGbs
     */
    public String getMgmtPortSpeedGbs() {
        return mgmtPortSpeedGbs;
    }

    /**
     * @param mgmtPortSpeedGbs the mgmtPortSpeedGbs to set
     */
    public void setMgmtPortSpeedGbs(String mgmtPortSpeedGbs) {
        this.mgmtPortSpeedGbs = mgmtPortSpeedGbs;
    }

    /**
     * @return the mgmtPortState
     */
    public MgmtPortMode getMgmtPortState() {
        return mgmtPortState;
    }

    /**
     * @param mgmtPortState the mgmtPortState to set
     */
    public void setMgmtPortState(MgmtPortMode mgmtPortState) {
        this.mgmtPortState = mgmtPortState;
    }

    /**
     * @return the mgmtPortStatus
     */
    public HealthStatus getMgmtPortStatus() {
        return mgmtPortStatus;
    }

    /**
     * @param mgmtPortStatus the mgmtPortStatus to set
     */
    public void setMgmtPortStatus(HealthStatus mgmtPortStatus) {
        this.mgmtPortStatus = mgmtPortStatus;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the negotiatedLinkPortSpeedGbs
     */
    public String getNegotiatedLinkPortSpeedGbs() {
        return negotiatedLinkPortSpeedGbs;
    }

    /**
     * @param negotiatedLinkPortSpeedGbs the negotiatedLinkPortSpeedGbs to set
     */
    public void setNegotiatedLinkPortSpeedGbs(String negotiatedLinkPortSpeedGbs) {
        this.negotiatedLinkPortSpeedGbs = negotiatedLinkPortSpeedGbs;
    }

    /**
     * @return the negotiatedMgmtPortSpeedGbs
     */
    public Integer getNegotiatedMgmtPortSpeedGbs() {
        return negotiatedMgmtPortSpeedGbs;
    }

    /**
     * @param negotiatedMgmtPortSpeedGbs the negotiatedMgmtPortSpeedGbs to set
     */
    public void setNegotiatedMgmtPortSpeedGbs(Integer negotiatedMgmtPortSpeedGbs) {
        this.negotiatedMgmtPortSpeedGbs = negotiatedMgmtPortSpeedGbs;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the role
     */
    public ManagerRole getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(ManagerRole role) {
        this.role = role;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the sparePartNumber
     */
    public String getSparePartNumber() {
        return sparePartNumber;
    }

    /**
     * @param sparePartNumber the sparePartNumber to set
     */
    public void setSparePartNumber(String sparePartNumber) {
        this.sparePartNumber = sparePartNumber;
    }

    /**
     * @return the status
     */
    public HealthStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HealthStatus status) {
        this.status = status;
    }

    /**
     * @return the uidState
     */
    public UidState getUidState() {
        return uidState;
    }

    /**
     * @param uidState the uidState to set
     */
    public void setUidState(UidState uidState) {
        this.uidState = uidState;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EnclosureManager) == false) {
            return false;
        }
        final EnclosureManager rhs = ((EnclosureManager) other);
        return new EqualsBuilder()
                .append(bayNumber, rhs.bayNumber)
                .append(bayPowerState, rhs.bayPowerState)
                .append(changeState, rhs.changeState)
                .append(devicePresence, rhs.devicePresence)
                .append(fwBuildDate, rhs.fwBuildDate)
                .append(fwVersion, rhs.fwVersion)
                .append(ipAddress, rhs.ipAddress)
                .append(linkPortIsolated, rhs.linkPortIsolated)
                .append(linkPortSpeedGbs, rhs.linkPortSpeedGbs)
                .append(linkPortState, rhs.linkPortState)
                .append(linkPortStatus, rhs.linkPortStatus)
                .append(linkedEnclosure, rhs.linkedEnclosure)
                .append(mgmtPortLinkState, rhs.mgmtPortLinkState)
                .append(mgmtPortSpeedGbs, rhs.mgmtPortSpeedGbs)
                .append(mgmtPortState, rhs.mgmtPortState)
                .append(mgmtPortStatus, rhs.mgmtPortStatus)
                .append(model, rhs.model)
                .append(negotiatedLinkPortSpeedGbs, rhs.negotiatedLinkPortSpeedGbs)
                .append(negotiatedMgmtPortSpeedGbs, rhs.negotiatedMgmtPortSpeedGbs)
                .append(partNumber, rhs.partNumber)
                .append(role, rhs.role)
                .append(serialNumber, rhs.serialNumber)
                .append(sparePartNumber, rhs.sparePartNumber)
                .append(status, rhs.status)
                .append(uidState, rhs.uidState)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(bayNumber)
                .append(bayPowerState)
                .append(changeState)
                .append(devicePresence)
                .append(fwBuildDate)
                .append(fwVersion)
                .append(ipAddress)
                .append(linkPortIsolated)
                .append(linkPortSpeedGbs)
                .append(linkPortState)
                .append(linkPortStatus)
                .append(linkedEnclosure)
                .append(mgmtPortLinkState)
                .append(mgmtPortSpeedGbs)
                .append(mgmtPortState)
                .append(mgmtPortStatus)
                .append(model)
                .append(negotiatedLinkPortSpeedGbs)
                .append(negotiatedMgmtPortSpeedGbs)
                .append(partNumber)
                .append(role)
                .append(serialNumber)
                .append(sparePartNumber)
                .append(status)
                .append(uidState)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
