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

public class StorageVolumeAttachmentPath extends BaseModelResource {

    private static final long serialVersionUID = 8687836337319335667L;

    private List<String> arrayPorts;
    private Boolean enabled;
    private String expectedNetworkUri;
    private String expectedSanUri;
    private String hostPort;
    private String initiatorName;
    private String proxyPort;
    private RefreshState refreshState;
    private String stateReason;
    private String transport;
    private String zoneServiceUri;

    public List<String> getArrayPorts() {
        return arrayPorts;
    }

    public void setArrayPorts(List<String> arrayPorts) {
        this.arrayPorts = arrayPorts;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getExpectedNetworkUri() {
        return expectedNetworkUri;
    }

    public void setExpectedNetworkUri(String expectedNetworkUri) {
        this.expectedNetworkUri = expectedNetworkUri;
    }

    public String getExpectedSanUri() {
        return expectedSanUri;
    }

    public void setExpectedSanUri(String expectedSanUri) {
        this.expectedSanUri = expectedSanUri;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getZoneServiceUri() {
        return zoneServiceUri;
    }

    public void setZoneServiceUri(String zoneServiceUri) {
        this.zoneServiceUri = zoneServiceUri;
    }
}
