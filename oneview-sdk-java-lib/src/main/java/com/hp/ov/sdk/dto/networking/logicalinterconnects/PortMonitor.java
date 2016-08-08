/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class PortMonitor extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private MonitorPortInfo analyzerPort;
    private Boolean enablePortMonitor;
    private List<MonitorPortInfo> monitoredPorts = new ArrayList<MonitorPortInfo>();

    /**
     * @return the analyzerPort
     */
    public MonitorPortInfo getAnalyzerPort() {
        return analyzerPort;
    }

    /**
     * @param analyzerPort
     *            the analyzerPort to set
     */
    public void setAnalyzerPort(final MonitorPortInfo analyzerPort) {
        this.analyzerPort = analyzerPort;
    }

    /**
     * @return the enablePortMonitor
     */
    public Boolean getEnablePortMonitor() {
        return enablePortMonitor;
    }

    /**
     * @param enablePortMonitor
     *            the enablePortMonitor to set
     */
    public void setEnablePortMonitor(final Boolean enablePortMonitor) {
        this.enablePortMonitor = enablePortMonitor;
    }

    /**
     * @return the monitoredPorts
     */
    public List<MonitorPortInfo> getMonitoredPorts() {
        return monitoredPorts;
    }

    /**
     * @param monitoredPorts
     *            the monitoredPorts to set
     */
    public void setMonitoredPorts(final List<MonitorPortInfo> monitoredPorts) {
        this.monitoredPorts = monitoredPorts;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(analyzerPort)
                .append(enablePortMonitor)
                .append(monitoredPorts)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortMonitor) == false) {
            return false;
        }
        final PortMonitor rhs = ((PortMonitor) other);
        return new EqualsBuilder()
                .append(analyzerPort, rhs.analyzerPort)
                .append(enablePortMonitor, rhs.enablePortMonitor)
                .append(monitoredPorts, rhs.monitoredPorts)
                .appendSuper(super.equals(other))
                .isEquals();
    }

}
