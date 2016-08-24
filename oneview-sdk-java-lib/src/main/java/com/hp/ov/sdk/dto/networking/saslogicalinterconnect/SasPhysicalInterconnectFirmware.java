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

package com.hp.ov.sdk.dto.networking.saslogicalinterconnect;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SasPhysicalInterconnectFirmware implements Serializable {

    private static final long serialVersionUID = -840401337325717734L;

    private String desiredFw;
    private String installedFw;
    private Integer interconnectBayNo;
    private String interconnectName;
    private String interconnectUri;
    private String model;
    private String selectedFw;
    private String sppName;
    private String sppUri;
    private SasFirmwareState state;
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
    public Integer getInterconnectBayNo() {
        return interconnectBayNo;
    }

    /**
     * @param interconnectBayNo the interconnectBayNo to set
     */
    public void setInterconnectBayNo(Integer interconnectBayNo) {
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
     * @return the selectedFw
     */
    public String getSelectedFw() {
        return selectedFw;
    }

    /**
     * @param selectedFw the selectedFw to set
     */
    public void setSelectedFw(String selectedFw) {
        this.selectedFw = selectedFw;
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
    public SasFirmwareState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(SasFirmwareState state) {
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
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
