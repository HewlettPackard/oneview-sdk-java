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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PhysicalInterconnectFirmware implements Serializable {

    private static final long serialVersionUID = 1L;

    private String desiredFw;
    private String deviceType;
    private String installedFw;
    private String interconnectBayNo;
    private String interconnectName;
    private String interconnectUri;
    private String sppName;
    private String sppUri;
    private FirmwareState state;
    private String updateFlagDesc;

    /**
     * @return the desiredFw
     */
    public String getDesiredFw() {
        return desiredFw;
    }

    /**
     * @param desiredFw the desiredFw to set
     */
    public void setDesiredFw(String desiredFw) {
        this.desiredFw = desiredFw;
    }

    /**
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the installedFw
     */
    public String getInstalledFw() {
        return installedFw;
    }

    /**
     * @param installedFw the installedFw to set
     */
    public void setInstalledFw(String installedFw) {
        this.installedFw = installedFw;
    }

    /**
     * @return the interconnectBayNo
     */
    public String getInterconnectBayNo() {
        return interconnectBayNo;
    }

    /**
     * @param interconnectBayNo the interconnectBayNo to set
     */
    public void setInterconnectBayNo(String interconnectBayNo) {
        this.interconnectBayNo = interconnectBayNo;
    }

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
     * @return the sppName
     */
    public String getSppName() {
        return sppName;
    }

    /**
     * @param sppName the sppName to set
     */
    public void setSppName(String sppName) {
        this.sppName = sppName;
    }

    /**
     * @return the sppUri
     */
    public String getSppUri() {
        return sppUri;
    }

    /**
     * @param sppUri the sppUri to set
     */
    public void setSppUri(String sppUri) {
        this.sppUri = sppUri;
    }

    /**
     * @return the state
     */
    public FirmwareState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(FirmwareState state) {
        this.state = state;
    }

    /**
     * @return the updateFlagDesc
     */
    public String getUpdateFlagDesc() {
        return updateFlagDesc;
    }

    /**
     * @param updateFlagDesc the updateFlagDesc to set
     */
    public void setUpdateFlagDesc(String updateFlagDesc) {
        this.updateFlagDesc = updateFlagDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof PhysicalInterconnectFirmware) {
            PhysicalInterconnectFirmware that = (PhysicalInterconnectFirmware) obj;

            return new EqualsBuilder()
                    .append(desiredFw, that.desiredFw)
                    .append(deviceType, that.deviceType)
                    .append(installedFw, that.installedFw)
                    .append(interconnectBayNo, that.interconnectBayNo)
                    .append(interconnectName, that.interconnectName)
                    .append(interconnectUri, that.interconnectUri)
                    .append(sppName, that.sppName)
                    .append(sppUri, that.sppUri)
                    .append(state, that.state)
                    .append(updateFlagDesc, that.updateFlagDesc)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(desiredFw)
                .append(deviceType)
                .append(installedFw)
                .append(interconnectBayNo)
                .append(interconnectName)
                .append(interconnectUri)
                .append(sppName)
                .append(sppUri)
                .append(state)
                .append(updateFlagDesc)
                .toHashCode();
    }

}
