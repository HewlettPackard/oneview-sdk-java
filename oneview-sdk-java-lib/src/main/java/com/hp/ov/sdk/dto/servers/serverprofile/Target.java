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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;
import java.util.List;

public class Target implements Serializable {

    private static final long serialVersionUID = 1L;

    private String enclosureGroupName;
    private String enclosureName;
    private String enclosureUri;
    private int enclosureBay;
    private String serverHardwareName;
    private String serverHardwareUri;
    private String serverHardwareTypeName;
    private String serverHardwareTypeUri;
    private String enclosureGroupUri;
    private String powerState;
    private List<String> formFactor;

    /**
     * @return the enclosureGroupName
     */
    public String getEnclosureGroupName() {
        return enclosureGroupName;
    }
    /**
     * @param enclosureGroupName the enclosureGroupName to set
     */
    public void setEnclosureGroupName(String enclosureGroupName) {
        this.enclosureGroupName = enclosureGroupName;
    }
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
     * @return the enclosureBay
     */
    public int getEnclosureBay() {
        return enclosureBay;
    }
    /**
     * @param enclosureBay the enclosureBay to set
     */
    public void setEnclosureBay(int enclosureBay) {
        this.enclosureBay = enclosureBay;
    }
    /**
     * @return the serverHardwareName
     */
    public String getServerHardwareName() {
        return serverHardwareName;
    }
    /**
     * @param serverHardwareName the serverHardwareName to set
     */
    public void setServerHardwareName(String serverHardwareName) {
        this.serverHardwareName = serverHardwareName;
    }
    /**
     * @return the serverHardwareUri
     */
    public String getServerHardwareUri() {
        return serverHardwareUri;
    }
    /**
     * @param serverHardwareUri the serverHardwareUri to set
     */
    public void setServerHardwareUri(String serverHardwareUri) {
        this.serverHardwareUri = serverHardwareUri;
    }
    /**
     * @return the serverHardwareTypeName
     */
    public String getServerHardwareTypeName() {
        return serverHardwareTypeName;
    }
    /**
     * @param serverHardwareTypeName the serverHardwareTypeName to set
     */
    public void setServerHardwareTypeName(String serverHardwareTypeName) {
        this.serverHardwareTypeName = serverHardwareTypeName;
    }
    /**
     * @return the serverHardwareTypeUri
     */
    public String getServerHardwareTypeUri() {
        return serverHardwareTypeUri;
    }
    /**
     * @param serverHardwareTypeUri the serverHardwareTypeUri to set
     */
    public void setServerHardwareTypeUri(String serverHardwareTypeUri) {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }
    /**
     * @return the enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }
    /**
     * @param enclosureGroupUri the enclosureGroupUri to set
     */
    public void setEnclosureGroupUri(String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }
    /**
     * @return the powerState
     */
    public String getPowerState() {
        return powerState;
    }
    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }
    /**
     * @return the formFactor
     */
    public List<String> getFormFactor() {
        return formFactor;
    }
    /**
     * @param formFactor the formFactor to set
     */
    public void setFormFactor(List<String> formFactor) {
        this.formFactor = formFactor;
    }


}
