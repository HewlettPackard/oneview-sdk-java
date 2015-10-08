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
package com.hp.ov.sdk.dto;

public class MonitorPortInfo extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Integer bayNumber;
    private String interconnectName;
    private String interconnectUri;
    private PortHealthStatusType portHealthStatus;
    private PortMonitorConfigInfo portMonitorConfigInfo;
    private String portName;
    private PortStatus portStatus;
    private String portUri;

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber
     *            the bayNumber to set
     */
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
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
    public void setInterconnectName(final String interconnectName) {
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
    public void setInterconnectUri(final String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the portHealthStatus
     */
    public PortHealthStatusType getPortHealthStatus() {
        return portHealthStatus;
    }

    /**
     * @param portHealthStatus
     *            the portHealthStatus to set
     */
    public void setPortHealthStatus(final PortHealthStatusType portHealthStatus) {
        this.portHealthStatus = portHealthStatus;
    }

    /**
     * @return the portMonitorConfigInfo
     */
    public PortMonitorConfigInfo getPortMonitorConfigInfo() {
        return portMonitorConfigInfo;
    }

    /**
     * @param portMonitorConfigInfo
     *            the portMonitorConfigInfo to set
     */
    public void setPortMonitorConfigInfo(final PortMonitorConfigInfo portMonitorConfigInfo) {
        this.portMonitorConfigInfo = portMonitorConfigInfo;
    }

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName
     *            the portName to set
     */
    public void setPortName(final String portName) {
        this.portName = portName;
    }

    /**
     * @return the portStatus
     */
    public PortStatus getPortStatus() {
        return portStatus;
    }

    /**
     * @param portStatus
     *            the portStatus to set
     */
    public void setPortStatus(final PortStatus portStatus) {
        this.portStatus = portStatus;
    }

    /**
     * @return the portUri
     */
    public String getPortUri() {
        return portUri;
    }

    /**
     * @param portUri
     *            the portUri to set
     */
    public void setPortUri(final String portUri) {
        this.portUri = portUri;
    }

}
