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
package com.hp.ov.sdk.dto.samples;

import java.util.HashMap;
import java.util.List;

public class UplinkSetValue {

    private HashMap<Integer, List<String>> bayPortMap = new HashMap<Integer, List<String>>();
    private String ligName;
    private String uplinkSetName;
    private String uplinkSetType;
    private List<String> networkNames;

    /**
     * @return the bayPortMap
     */
    public HashMap<Integer, List<String>> getBayPortMap() {
        return bayPortMap;
    }

    /**
     * @param bayPortMap
     *            the bayPortMap to set
     */
    public void setBayPortMap(final HashMap<Integer, List<String>> bayPortMap) {
        this.bayPortMap = bayPortMap;
    }

    /**
     * @return the ligName
     */
    public String getLigName() {
        return ligName;
    }

    /**
     * @param ligName
     *            the ligName to set
     */
    public void setLigName(final String ligName) {
        this.ligName = ligName;
    }

    /**
     * @return the uplinkSetName
     */
    public String getUplinkSetName() {
        return uplinkSetName;
    }

    /**
     * @param uplinkSetName
     *            the uplinkSetName to set
     */
    public void setUplinkSetName(final String uplinkSetName) {
        this.uplinkSetName = uplinkSetName;
    }

    /**
     * @return the uplinkSetType
     */
    public String getUplinkSetType() {
        return uplinkSetType;
    }

    /**
     * @param uplinkSetType
     *            the uplinkSetType to set
     */
    public void setUplinkSetType(final String uplinkSetType) {
        this.uplinkSetType = uplinkSetType;
    }

    /**
     * @return the networkNames
     */
    public List<String> getNetworkNames() {
        return networkNames;
    }

    /**
     * @param networkNames
     *            the networkNames to set
     */
    public void setNetworkNames(final List<String> networkNames) {
        this.networkNames = networkNames;
    }
}
