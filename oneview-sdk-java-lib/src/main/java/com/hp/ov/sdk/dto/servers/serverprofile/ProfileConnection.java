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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.FunctionType;

public class ProfileConnection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer allocatedMbps;
    @Since(200)
    private Integer allocatedVFs;
    private ConnectionBoot boot;
    private ConnectionStatus deploymentStatus;
    private FunctionType functionType;
    private Integer id;
    private String interconnectUri;
    private String mac;
    private AssignmentType macType;
    private Integer maximumMbps;
    private String name;
    private String networkUri;
    private String portId;
    private String requestedMbps;
    @Since(200)
    private String requestedVFs;
    private String wwnn;
    private String wwpn;
    private AssignmentType wwpnType;

    /**
     *
     * @return The allocatedMbps
     */
    public Integer getAllocatedMbps() {
        return allocatedMbps;
    }

    /**
     *
     * @param allocatedMbps
     *            The allocatedMbps
     */
    public void setAllocatedMbps(final Integer allocatedMbps) {
        this.allocatedMbps = allocatedMbps;
    }

    /**
     *
     * @return The boot
     */
    public ConnectionBoot getBoot() {
        return boot;
    }

    /**
     *
     * @param boot
     *            The boot
     */
    public void setBoot(final ConnectionBoot boot) {
        this.boot = boot;
    }

    /**
     *
     * @return The deploymentStatus
     */
    public ConnectionStatus getDeploymentStatus() {
        return deploymentStatus;
    }

    /**
     *
     * @param deploymentStatus
     *            The deploymentStatus
     */
    public void setDeploymentStatus(final ConnectionStatus deploymentStatus) {
        this.deploymentStatus = deploymentStatus;
    }

    /**
     *
     * @return The functionType
     */
    public FunctionType getFunctionType() {
        return functionType;
    }

    /**
     *
     * @param functionType
     *            The functionType
     */
    public void setFunctionType(final FunctionType functionType) {
        this.functionType = functionType;
    }

    /**
     *
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *            The id
     */
    public void setId(final Integer id) {
        this.id = id;
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
     * @return The mac
     */
    public String getMac() {
        return mac;
    }

    /**
     *
     * @param mac
     *            The mac
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     *
     * @return The macType
     */
    public AssignmentType getMacType() {
        return macType;
    }

    /**
     *
     * @param macType
     *            The macType
     */
    public void setMacType(final AssignmentType macType) {
        this.macType = macType;
    }

    /**
     *
     * @return The maximumMbps
     */
    public Integer getMaximumMbps() {
        return maximumMbps;
    }

    /**
     *
     * @param maximumMbps
     *            The maximumMbps
     */
    public void setMaximumMbps(final Integer maximumMbps) {
        this.maximumMbps = maximumMbps;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return The networkUri
     */
    public String getNetworkUri() {
        return networkUri;
    }

    /**
     *
     * @param networkUri
     *            The networkUri
     */
    public void setNetworkUri(final String networkUri) {
        this.networkUri = networkUri;
    }

    /**
     *
     * @return The portId
     */
    public String getPortId() {
        return portId;
    }

    /**
     *
     * @param portId
     *            The portId
     */
    public void setPortId(final String portId) {
        this.portId = portId;
    }

    /**
     *
     * @return The requestedMbps
     */
    public String getRequestedMbps() {
        return requestedMbps;
    }

    /**
     *
     * @param requestedMbps
     *            The requestedMbps
     */
    public void setRequestedMbps(final String requestedMbps) {
        this.requestedMbps = requestedMbps;
    }

    /**
     *
     * @return The wwnn
     */
    public String getWwnn() {
        return wwnn;
    }

    /**
     *
     * @param wwnn
     *            The wwnn
     */
    public void setWwnn(final String wwnn) {
        this.wwnn = wwnn;
    }

    /**
     *
     * @return The wwpn
     */
    public String getWwpn() {
        return wwpn;
    }

    /**
     *
     * @param wwpn
     *            The wwpn
     */
    public void setWwpn(final String wwpn) {
        this.wwpn = wwpn;
    }

    /**
     *
     * @return The wwpnType
     */
    public AssignmentType getWwpnType() {
        return wwpnType;
    }

    /**
     *
     * @param wwpnType
     *            The wwpnType
     */
    public void setWwpnType(final AssignmentType wwpnType) {
        this.wwpnType = wwpnType;
    }

    /**
     * @return the requestedVFs
     */
    public String getRequestedVFs() {
        return requestedVFs;
    }

    /**
     * @param requestedVFs the requestedVFs to set
     */
    public void setRequestedVFs(String requestedVFs) {
        this.requestedVFs = requestedVFs;
    }

    /**
     * @return the allocatedVFs
     */
    public Integer getAllocatedVFs() {
        return allocatedVFs;
    }

    /**
     * @param allocatedVFs the allocatedVFs to set
     */
    public void setAllocatedVFs(Integer allocatedVFs) {
        this.allocatedVFs = allocatedVFs;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
