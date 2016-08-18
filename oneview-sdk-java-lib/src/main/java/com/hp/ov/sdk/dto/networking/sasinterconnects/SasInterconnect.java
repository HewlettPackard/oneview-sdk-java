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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.Location;
import com.hp.ov.sdk.dto.networking.interconnect.UIDState;

public class SasInterconnect extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String enclosureName;
    private String enclosureUri;
    private String firmwareVersion;
    private HardResetState hardResetState;
    private String interconnectIP;
    private Location interconnectLocation;
    private String interconnectTypeUri;
    private String logicalSasInterconnectUri;
    private String model;
    private String partNumber;
    private int portCount;
    private PowerState powerState;
    private String productName;
    private String sasLogicalInterconnectUri;
    private List<SasPort> sasPorts = new ArrayList<SasPort>();
    private String sasWWN;
    private String serialNumber;
    private SoftResetState softResetState;
    private String stateReason;
    private UIDState uidState;

    /**
     * @return the enclosureName
     */
    public String getEnclosureName() {
        return enclosureName;
    }

    /**
     * @param enclosureName the enclosureName to set
     */
    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    /**
     * @return the enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     * @param enclosureUri the enclosureUri to set
     */
    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    /**
     * @return the firmwareVersion
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * @param firmwareVersion the firmwareVersion to set
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * @return the hardResetState
     */
    public HardResetState getHardResetState() {
        return hardResetState;
    }

    /**
     * @param hardResetState the hardResetState to set
     */
    public void setHardResetState(HardResetState hardResetState) {
        this.hardResetState = hardResetState;
    }

    /**
     * @return the interconnectIP
     */
    public String getInterconnectIP() {
        return interconnectIP;
    }

    /**
     * @param interconnectIP the interconnectIP to set
     */
    public void setInterconnectIP(String interconnectIP) {
        this.interconnectIP = interconnectIP;
    }

    /**
     * @return the interconnectLocation
     */
    public Location getInterconnectLocation() {
        return interconnectLocation;
    }

    /**
     * @param interconnectLocation the interconnectLocation to set
     */
    public void setInterconnectLocation(Location interconnectLocation) {
        this.interconnectLocation = interconnectLocation;
    }

    /**
     * @return the interconnectTypeUri
     */
    public String getInterconnectTypeUri() {
        return interconnectTypeUri;
    }

    /**
     * @param interconnectTypeUri the interconnectTypeUri to set
     */
    public void setInterconnectTypeUri(String interconnectTypeUri) {
        this.interconnectTypeUri = interconnectTypeUri;
    }

    /**
     * @return the logicalSasInterconnectUri
     */
    public String getLogicalSasInterconnectUri() {
        return logicalSasInterconnectUri;
    }

    /**
     * @param logicalSasInterconnectUri the logicalSasInterconnectUri to set
     */
    public void setLogicalSasInterconnectUri(String logicalSasInterconnectUri) {
        this.logicalSasInterconnectUri = logicalSasInterconnectUri;
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
     * @return the portCount
     */
    public int getPortCount() {
        return portCount;
    }

    /**
     * @param portCount the portCount to set
     */
    public void setPortCount(int portCount) {
        this.portCount = portCount;
    }

    /**
     * @return the powerState
     */
    public PowerState getPowerState() {
        return powerState;
    }

    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(PowerState powerState) {
        this.powerState = powerState;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the sasLogicalInterconnectUri
     */
    public String getSasLogicalInterconnectUri() {
        return sasLogicalInterconnectUri;
    }

    /**
     * @param sasLogicalInterconnectUri the sasLogicalInterconnectUri to set
     */
    public void setSasLogicalInterconnectUri(String sasLogicalInterconnectUri) {
        this.sasLogicalInterconnectUri = sasLogicalInterconnectUri;
    }

    /**
     * @return the sasPorts
     */
    public List<SasPort> getSasPorts() {
        return sasPorts;
    }

    /**
     * @param sasPorts the sasPorts to set
     */
    public void setSasPorts(List<SasPort> sasPorts) {
        this.sasPorts = sasPorts;
    }

    /**
     * @return the sasWWN
     */
    public String getSasWWN() {
        return sasWWN;
    }

    /**
     * @param sasWWN the sasWWN to set
     */
    public void setSasWWN(String sasWWN) {
        this.sasWWN = sasWWN;
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
     * @return the softResetState
     */
    public SoftResetState getSoftResetState() {
        return softResetState;
    }

    /**
     * @param softResetState the softResetState to set
     */
    public void setSoftResetState(SoftResetState softResetState) {
        this.softResetState = softResetState;
    }

    /**
     * @return the stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * @param stateReason the stateReason to set
     */
    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     * @return the uidState
     */
    public UIDState getUidState() {
        return uidState;
    }

    /**
     * @param uidState the uidState to set
     */
    public void setUidState(UIDState uidState) {
        this.uidState = uidState;
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
