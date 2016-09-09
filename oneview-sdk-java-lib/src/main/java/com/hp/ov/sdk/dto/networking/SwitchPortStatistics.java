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

package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SwitchPortStatistics implements Serializable {

    private static final long serialVersionUID = 2807817145727877275L;

    private CapabilityType capabilityType;
    private String ifInDiscards;
    private String ifInErrors;
    private String ifInOctets;
    private String ifInUcastPkts;
    private String ifOutDiscards;
    private String ifOutErrors;
    private String ifOutOctets;
    private String ifOutUcastPkts;
    private String interfaceIndexId;
    private String portName;
    private Integer portNumber;
    private String type;
    private List<VlanInfo> vlanId = new ArrayList<>();

    public CapabilityType getCapabilityType() {
        return capabilityType;
    }

    public void setCapabilityType(CapabilityType capabilityType) {
        this.capabilityType = capabilityType;
    }

    public String getIfInDiscards() {
        return ifInDiscards;
    }

    public void setIfInDiscards(String ifInDiscards) {
        this.ifInDiscards = ifInDiscards;
    }

    public String getIfInErrors() {
        return ifInErrors;
    }

    public void setIfInErrors(String ifInErrors) {
        this.ifInErrors = ifInErrors;
    }

    public String getIfInOctets() {
        return ifInOctets;
    }

    public void setIfInOctets(String ifInOctets) {
        this.ifInOctets = ifInOctets;
    }

    public String getIfInUcastPkts() {
        return ifInUcastPkts;
    }

    public void setIfInUcastPkts(String ifInUcastPkts) {
        this.ifInUcastPkts = ifInUcastPkts;
    }

    public String getIfOutDiscards() {
        return ifOutDiscards;
    }

    public void setIfOutDiscards(String ifOutDiscards) {
        this.ifOutDiscards = ifOutDiscards;
    }

    public String getIfOutErrors() {
        return ifOutErrors;
    }

    public void setIfOutErrors(String ifOutErrors) {
        this.ifOutErrors = ifOutErrors;
    }

    public String getIfOutOctets() {
        return ifOutOctets;
    }

    public void setIfOutOctets(String ifOutOctets) {
        this.ifOutOctets = ifOutOctets;
    }

    public String getIfOutUcastPkts() {
        return ifOutUcastPkts;
    }

    public void setIfOutUcastPkts(String ifOutUcastPkts) {
        this.ifOutUcastPkts = ifOutUcastPkts;
    }

    public String getInterfaceIndexId() {
        return interfaceIndexId;
    }

    public void setInterfaceIndexId(String interfaceIndexId) {
        this.interfaceIndexId = interfaceIndexId;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<VlanInfo> getVlanId() {
        return vlanId;
    }

    public void setVlanId(List<VlanInfo> vlanId) {
        this.vlanId = vlanId;
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
