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
package com.hp.ov.sdk.dto;

import java.util.List;

public class FipSnoopingInfo extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private String externalVlan;
    private List<String> fcfMacAddress;
    private String fabricName;
    private String fcfName;
    private String fcMap;
    private String lagId;
    private String port;
    private String downLinks;
    private List<String> fcoeMacAddress;
    private String fcoeLoginCount;
    private String flexNic;
    private List<String> fcID;
    private String macAddress;
    private String network;

    /**
     * @return the externalVlan
     */
    public String getExternalVlan() {
        return externalVlan;
    }
    /**
     * @param externalVlan the externalVlan to set
     */
    public void setExternalVlan(String externalVlan) {
        this.externalVlan = externalVlan;
    }
    /**
     * @return the fcfMacAddress
     */
    public List<String> getFcfMacAddress() {
        return fcfMacAddress;
    }
    /**
     * @param fcfMacAddress the fcfMacAddress to set
     */
    public void setFcfMacAddress(List<String> fcfMacAddress) {
        this.fcfMacAddress = fcfMacAddress;
    }
    /**
     * @return the fabricName
     */
    public String getFabricName() {
        return fabricName;
    }
    /**
     * @param fabricName the fabricName to set
     */
    public void setFabricName(String fabricName) {
        this.fabricName = fabricName;
    }
    /**
     * @return the fcfName
     */
    public String getFcfName() {
        return fcfName;
    }
    /**
     * @param fcfName the fcfName to set
     */
    public void setFcfName(String fcfName) {
        this.fcfName = fcfName;
    }
    /**
     * @return the fcMap
     */
    public String getFcMap() {
        return fcMap;
    }
    /**
     * @param fcMap the fcMap to set
     */
    public void setFcMap(String fcMap) {
        this.fcMap = fcMap;
    }
    /**
     * @return the lagId
     */
    public String getLagId() {
        return lagId;
    }
    /**
     * @param lagId the lagId to set
     */
    public void setLagId(String lagId) {
        this.lagId = lagId;
    }
    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }
    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }
    /**
     * @return the downLinks
     */
    public String getDownLinks() {
        return downLinks;
    }
    /**
     * @param downLinks the downLinks to set
     */
    public void setDownLinks(String downLinks) {
        this.downLinks = downLinks;
    }
    /**
     * @return the fcoeMacAddress
     */
    public List<String> getFcoeMacAddress() {
        return fcoeMacAddress;
    }
    /**
     * @param fcoeMacAddress the fcoeMacAddress to set
     */
    public void setFcoeMacAddress(List<String> fcoeMacAddress) {
        this.fcoeMacAddress = fcoeMacAddress;
    }
    /**
     * @return the fcoeLoginCount
     */
    public String getFcoeLoginCount() {
        return fcoeLoginCount;
    }
    /**
     * @param fcoeLoginCount the fcoeLoginCount to set
     */
    public void setFcoeLoginCount(String fcoeLoginCount) {
        this.fcoeLoginCount = fcoeLoginCount;
    }
    /**
     * @return the flexNic
     */
    public String getFlexNic() {
        return flexNic;
    }
    /**
     * @param flexNic the flexNic to set
     */
    public void setFlexNic(String flexNic) {
        this.flexNic = flexNic;
    }
    /**
     * @return the fcID
     */
    public List<String> getFcID() {
        return fcID;
    }
    /**
     * @param fcID the fcID to set
     */
    public void setFcID(List<String> fcID) {
        this.fcID = fcID;
    }
    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }
    /**
     * @param macAddress the macAddress to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    /**
     * @return the network
     */
    public String getNetwork() {
        return network;
    }
    /**
     * @param network the network to set
     */
    public void setNetwork(String network) {
        this.network = network;
    }

}
