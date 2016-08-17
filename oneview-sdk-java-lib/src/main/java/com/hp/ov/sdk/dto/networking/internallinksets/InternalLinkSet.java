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
package com.hp.ov.sdk.dto.networking.internallinksets;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.networking.PortConfigInfo;

public class InternalLinkSet extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String uplinkSetUri;
    private Location primaryPortLocation;
    private String logicalSwitchUri;
    private List<PortConfigInfo> portConfigInfos = new ArrayList<PortConfigInfo>();
    private String portGroupId;
    private String logicalSwitchPortGroupId;
    private String fexId;
    private List<String> networkUris = new ArrayList<String>();
    private List<String> fcoeNetworkUris = new ArrayList<String>();

    /**
     * @return the uplinkSetUri
     */
    public String getUplinkSetUri() {
        return uplinkSetUri;
    }

    /**
     * @param uplinkSetUri the uplinkSetUri to set
     */
    public void setUplinkSetUri(String uplinkSetUri) {
        this.uplinkSetUri = uplinkSetUri;
    }

    /**
     * @return the primaryPortLocation
     */
    public Location getPrimaryPortLocation() {
        return primaryPortLocation;
    }

    /**
     * @param primaryPortLocation the primaryPortLocation to set
     */
    public void setPrimaryPortLocation(Location primaryPortLocation) {
        this.primaryPortLocation = primaryPortLocation;
    }

    /**
     * @return the logicalSwitchUri
     */
    public String getLogicalSwitchUri() {
        return logicalSwitchUri;
    }

    /**
     * @param logicalSwitchUri the logicalSwitchUri to set
     */
    public void setLogicalSwitchUri(String logicalSwitchUri) {
        this.logicalSwitchUri = logicalSwitchUri;
    }

    /**
     * @return the portConfigInfos
     */
    public List<PortConfigInfo> getPortConfigInfos() {
        return portConfigInfos;
    }

    /**
     * @param portConfigInfos the portConfigInfos to set
     */
    public void setPortConfigInfos(List<PortConfigInfo> portConfigInfos) {
        this.portConfigInfos = portConfigInfos;
    }

    /**
     * @return the portGroupId
     */
    public String getPortGroupId() {
        return portGroupId;
    }

    /**
     * @param portGroupId the portGroupId to set
     */
    public void setPortGroupId(String portGroupId) {
        this.portGroupId = portGroupId;
    }

    /**
     * @return the logicalSwitchPortGroupId
     */
    public String getLogicalSwitchPortGroupId() {
        return logicalSwitchPortGroupId;
    }

    /**
     * @param logicalSwitchPortGroupId the logicalSwitchPortGroupId to set
     */
    public void setLogicalSwitchPortGroupId(String logicalSwitchPortGroupId) {
        this.logicalSwitchPortGroupId = logicalSwitchPortGroupId;
    }

    /**
     * @return the fexId
     */
    public String getFexId() {
        return fexId;
    }

    /**
     * @param fexId the fexId to set
     */
    public void setFexId(String fexId) {
        this.fexId = fexId;
    }

    /**
     * @return the networkUris
     */
    public List<String> getNetworkUris() {
        return networkUris;
    }

    /**
     * @param networkUris the networkUris to set
     */
    public void setNetworkUris(List<String> networkUris) {
        this.networkUris = networkUris;
    }

    /**
     * @return the fcoeNetworkUris
     */
    public List<String> getFcoeNetworkUris() {
        return fcoeNetworkUris;
    }

    /**
     * @param fcoeNetworkUris the fcoeNetworkUris to set
     */
    public void setFcoeNetworkUris(List<String> fcoeNetworkUris) {
        this.fcoeNetworkUris = fcoeNetworkUris;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
