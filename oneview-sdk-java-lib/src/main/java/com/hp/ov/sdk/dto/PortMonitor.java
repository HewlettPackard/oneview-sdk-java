/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class PortMonitor extends BaseModelResource {

    /**
	 * 
	 */
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

}
