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
package com.hp.ov.sdk.dto.networking.interconnect;

import java.io.Serializable;

public class InterconnectFibDataEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String entryType;
    private String externalVlan;
    private String interconnectName;
    private String interconnectUri;
    private String internalVlan;
    private String macAddress;
    private String networkInterface;
    private String networkName;
    private String networkUri;

    /**
     * @return the entryType
     */
    public String getEntryType() {
        return entryType;
    }

    /**
     * @param entryType
     *            the entryType to set
     */
    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    /**
     * @return the externalVlan
     */
    public String getExternalVlan() {
        return externalVlan;
    }

    /**
     * @param externalVlan
     *            the externalVlan to set
     */
    public void setExternalVlan(String externalVlan) {
        this.externalVlan = externalVlan;
    }

    /**
     * @return the interconnectName
     */
    public String getInterconnectName() {
        return interconnectName;
    }

    /**
     * @param interconnectName
     *            the interconnectName to set
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
     * @param interconnectUri
     *            the interconnectUri to set
     */
    public void setInterconnectUri(String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the internalVlan
     */
    public String getInternalVlan() {
        return internalVlan;
    }

    /**
     * @param internalVlan
     *            the internalVlan to set
     */
    public void setInternalVlan(String internalVlan) {
        this.internalVlan = internalVlan;
    }

    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @param macAddress
     *            the macAddress to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return the networkInterface
     */
    public String getNetworkInterface() {
        return networkInterface;
    }

    /**
     * @param networkInterface
     *            the networkInterface to set
     */
    public void setNetworkInterface(String networkInterface) {
        this.networkInterface = networkInterface;
    }

    /**
     * @return the networkName
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * @param networkName
     *            the networkName to set
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    /**
     * @return the networkUri
     */
    public String getNetworkUri() {
        return networkUri;
    }

    /**
     * @param networkUri
     *            the networkUri to set
     */
    public void setNetworkUri(String networkUri) {
        this.networkUri = networkUri;
    }

}
