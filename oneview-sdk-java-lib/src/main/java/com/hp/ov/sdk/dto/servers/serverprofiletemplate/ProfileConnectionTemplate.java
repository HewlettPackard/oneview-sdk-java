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
package com.hp.ov.sdk.dto.servers.serverprofiletemplate;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.servers.FunctionType;

public class ProfileConnectionTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private ConnectionBootTemplate boot;
    private FunctionType functionType;
    private Long id;
    private String name;
    private String networkUri;
    private String portId;
    private String requestedMbps;
    private String requestedVFs;

    /**
     * @return the boot
     */
    public ConnectionBootTemplate getBoot() {
        return boot;
    }

    /**
     * @param boot the boot to set
     */
    public void setBoot(ConnectionBootTemplate boot) {
        this.boot = boot;
    }

    /**
     * @return the functionType
     */
    public FunctionType getFunctionType() {
        return functionType;
    }

    /**
     * @param functionType the functionType to set
     */
    public void setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the networkUri
     */
    public String getNetworkUri() {
        return networkUri;
    }

    /**
     * @param networkUri the networkUri to set
     */
    public void setNetworkUri(String networkUri) {
        this.networkUri = networkUri;
    }

    /**
     * @return the portId
     */
    public String getPortId() {
        return portId;
    }

    /**
     * @param portId the portId to set
     */
    public void setPortId(String portId) {
        this.portId = portId;
    }

    /**
     * @return the requestedMbps
     */
    public String getRequestedMbps() {
        return requestedMbps;
    }

    /**
     * @param requestedMbps the requestedMbps to set
     */
    public void setRequestedMbps(String requestedMbps) {
        this.requestedMbps = requestedMbps;
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
