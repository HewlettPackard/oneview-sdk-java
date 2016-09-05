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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;

public class ServerFabricVirtualPort implements Serializable {

    private static final long serialVersionUID = -5087503882656435993L;

    @Since(200)
    private Integer currentAllocatedVirtualFunctionCount;

    private String mac;
    private String portFunction;
    private Integer portNumber;
    private String wwnn;
    private String wwpn;

    /**
     * @return the currentAllocatedVirtualFunctionCount
     */
    public Integer getCurrentAllocatedVirtualFunctionCount() {
        return currentAllocatedVirtualFunctionCount;
    }

    /**
     * @param currentAllocatedVirtualFunctionCount the currentAllocatedVirtualFunctionCount to set
     */
    public void setCurrentAllocatedVirtualFunctionCount(Integer currentAllocatedVirtualFunctionCount) {
        this.currentAllocatedVirtualFunctionCount = currentAllocatedVirtualFunctionCount;
    }

    /**
     * @return the mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac the mac to set
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * @return the portFunction
     */
    public String getPortFunction() {
        return portFunction;
    }

    /**
     * @param portFunction the portFunction to set
     */
    public void setPortFunction(String portFunction) {
        this.portFunction = portFunction;
    }

    /**
     * @return the portNumber
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * @param portNumber the portNumber to set
     */
    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * @return the wwnn
     */
    public String getWwnn() {
        return wwnn;
    }

    /**
     * @param wwnn the wwnn to set
     */
    public void setWwnn(String wwnn) {
        this.wwnn = wwnn;
    }

    /**
     * @return the wwpn
     */
    public String getWwpn() {
        return wwpn;
    }

    /**
     * @param wwpn the wwpn to set
     */
    public void setWwpn(String wwpn) {
        this.wwpn = wwpn;
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
