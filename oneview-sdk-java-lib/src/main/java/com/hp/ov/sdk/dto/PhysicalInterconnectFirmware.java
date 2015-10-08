/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

import java.io.Serializable;

public class PhysicalInterconnectFirmware implements Serializable {

    /**
	 * 
	 */
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
     * 
     * @return The desiredFw
     */
    public String getDesiredFw() {
        return desiredFw;
    }

    /**
     * 
     * @param desiredFw
     *            The desiredFw
     */
    public void setDesiredFw(final String desiredFw) {
        this.desiredFw = desiredFw;
    }

    /**
     * 
     * @return The deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 
     * @param deviceType
     *            The deviceType
     */
    public void setDeviceType(final String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 
     * @return The installedFw
     */
    public String getInstalledFw() {
        return installedFw;
    }

    /**
     * 
     * @param installedFw
     *            The installedFw
     */
    public void setInstalledFw(final String installedFw) {
        this.installedFw = installedFw;
    }

    /**
     * 
     * @return The interconnectBayNo
     */
    public String getInterconnectBayNo() {
        return interconnectBayNo;
    }

    /**
     * 
     * @param interconnectBayNo
     *            The interconnectBayNo
     */
    public void setInterconnectBayNo(final String interconnectBayNo) {
        this.interconnectBayNo = interconnectBayNo;
    }

    /**
     * 
     * @return The interconnectName
     */
    public String getInterconnectName() {
        return interconnectName;
    }

    /**
     * 
     * @param interconnectName
     *            The interconnectName
     */
    public void setInterconnectName(final String interconnectName) {
        this.interconnectName = interconnectName;
    }

    /**
     * 
     * @return The interconnectUri
     */
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     * 
     * @param interconnectUri
     *            The interconnectUri
     */
    public void setInterconnectUri(final String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * 
     * @return The sppName
     */
    public String getSppName() {
        return sppName;
    }

    /**
     * 
     * @param sppName
     *            The sppName
     */
    public void setSppName(final String sppName) {
        this.sppName = sppName;
    }

    /**
     * 
     * @return The sppUri
     */
    public String getSppUri() {
        return sppUri;
    }

    /**
     * 
     * @param sppUri
     *            The sppUri
     */
    public void setSppUri(final String sppUri) {
        this.sppUri = sppUri;
    }

    /**
     * 
     * @return The state
     */
    public FirmwareState getState() {
        return state;
    }

    /**
     * 
     * @param state
     *            The state
     */
    public void setState(final FirmwareState state) {
        this.state = state;
    }

    /**
     * 
     * @return The updateFlagDesc
     */
    public String getUpdateFlagDesc() {
        return updateFlagDesc;
    }

    /**
     * 
     * @param updateFlagDesc
     *            The updateFlagDesc
     */
    public void setUpdateFlagDesc(final String updateFlagDesc) {
        this.updateFlagDesc = updateFlagDesc;
    }

}
