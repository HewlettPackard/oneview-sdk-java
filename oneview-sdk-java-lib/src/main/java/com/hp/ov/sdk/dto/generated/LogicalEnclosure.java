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
package com.hp.ov.sdk.dto.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.BaseModelResource;

public class LogicalEnclosure extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String enclosureGroupUri;
    private List<String> enclosureUris = new ArrayList<String>();
    private Map<String, LogicalEnclosureContainedEnclosure> enclosures = new HashMap<String, LogicalEnclosureContainedEnclosure>();
    private List<String> logicalInterconnectUris = new ArrayList<String>();
    private Object ipAddressingMode;
    private List<Object> ipv4Ranges = new ArrayList<Object>();
    private Object powerMode;
    private FirmwareLogicalEnclosure firmware;
    private Boolean deleteFailed;

    /**
     * @return The enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     * @param enclosureGroupUri
     *            The enclosureGroupUri
     */
    public void setEnclosureGroupUri(String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * @return The enclosureUris
     */
    public List<String> getEnclosureUris() {
        return enclosureUris;
    }

    /**
     * @param enclosureUris
     *            The enclosureUris
     */
    public void setEnclosureUris(List<String> enclosureUris) {
        this.enclosureUris = enclosureUris;
    }

    /**
     * @return The enclosures
     */
    public Map<String, LogicalEnclosureContainedEnclosure> getEnclosures() {
        return enclosures;
    }

    /**
     * @param enclosures
     *            The enclosures
     */
    public void setEnclosures(Map<String, LogicalEnclosureContainedEnclosure> enclosures) {
        this.enclosures = enclosures;
    }

    /**
     * @return The logicalInterconnectUris
     */
    public List<String> getLogicalInterconnectUris() {
        return logicalInterconnectUris;
    }

    /**
     * @param logicalInterconnectUris
     *            The logicalInterconnectUris
     */
    public void setLogicalInterconnectUris(List<String> logicalInterconnectUris) {
        this.logicalInterconnectUris = logicalInterconnectUris;
    }

    /**
     * @return The ipAddressingMode
     */
    public Object getIpAddressingMode() {
        return ipAddressingMode;
    }

    /**
     * @param ipAddressingMode
     *            The ipAddressingMode
     */
    public void setIpAddressingMode(Object ipAddressingMode) {
        this.ipAddressingMode = ipAddressingMode;
    }

    /**
     * @return The ipv4Ranges
     */
    public List<Object> getIpv4Ranges() {
        return ipv4Ranges;
    }

    /**
     * @param ipv4Ranges
     *            The ipv4Ranges
     */
    public void setIpv4Ranges(List<Object> ipv4Ranges) {
        this.ipv4Ranges = ipv4Ranges;
    }

    /**
     * @return The powerMode
     */
    public Object getPowerMode() {
        return powerMode;
    }

    /**
     * @param powerMode
     *            The powerMode
     */
    public void setPowerMode(Object powerMode) {
        this.powerMode = powerMode;
    }

    /**
     * @return The firmware
     */
    public FirmwareLogicalEnclosure getFirmware() {
        return firmware;
    }

    /**
     * @param firmware
     *            The firmware
     */
    public void setFirmware(FirmwareLogicalEnclosure firmware) {
        this.firmware = firmware;
    }

    /**
     * @return The deleteFailed
     */
    public Boolean getDeleteFailed() {
        return deleteFailed;
    }

    /**
     * @param deleteFailed
     *            The deleteFailed
     */
    public void setDeleteFailed(Boolean deleteFailed) {
        this.deleteFailed = deleteFailed;
    }
}
