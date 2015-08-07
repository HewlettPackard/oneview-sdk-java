/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhysicalPort implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer interconnectPort;
    private String interconnectUri;
    private String mac;
    private Integer portNumber;
    private PortType type;
    private List<VirtualPort> virtualPorts = new ArrayList<VirtualPort>();
    private String wwn;

    /**
     * @return the interconnectPort
     */
    public Integer getInterconnectPort() {
        return interconnectPort;
    }

    /**
     * @param interconnectPort
     *            the interconnectPort to set
     */
    public void setInterconnectPort(final Integer interconnectPort) {
        this.interconnectPort = interconnectPort;
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
     * @return the mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac
     *            the mac to set
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     * @return the portNumber
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * @param portNumber
     *            the portNumber to set
     */
    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * @return the type
     */
    public PortType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final PortType type) {
        this.type = type;
    }

    /**
     * @return the virtualPorts
     */
    public List<VirtualPort> getVirtualPorts() {
        return virtualPorts;
    }

    /**
     * @param virtualPorts
     *            the virtualPorts to set
     */
    public void setVirtualPorts(final List<VirtualPort> virtualPorts) {
        this.virtualPorts = virtualPorts;
    }

    /**
     * @return the wwn
     */
    public String getWwn() {
        return wwn;
    }

    /**
     * @param wwn
     *            the wwn to set
     */
    public void setWwn(final String wwn) {
        this.wwn = wwn;
    }

}
