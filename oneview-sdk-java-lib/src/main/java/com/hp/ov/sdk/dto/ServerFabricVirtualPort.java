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

    public Integer getCurrentAllocatedVirtualFunctionCount() {
        return currentAllocatedVirtualFunctionCount;
    }

    public void setCurrentAllocatedVirtualFunctionCount(Integer currentAllocatedVirtualFunctionCount) {
        this.currentAllocatedVirtualFunctionCount = currentAllocatedVirtualFunctionCount;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPortFunction() {
        return portFunction;
    }

    public void setPortFunction(String portFunction) {
        this.portFunction = portFunction;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public String getWwnn() {
        return wwnn;
    }

    public void setWwnn(String wwnn) {
        this.wwnn = wwnn;
    }

    public String getWwpn() {
        return wwpn;
    }

    public void setWwpn(String wwpn) {
        this.wwpn = wwpn;
    }
}
