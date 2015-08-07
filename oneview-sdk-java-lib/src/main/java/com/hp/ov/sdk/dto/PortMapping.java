/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class PortMapping implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer interconnectBay;
    private Integer midplanePort;

    /**
     * @return the interconnectBay
     */
    public Integer getInterconnectBay() {
        return interconnectBay;
    }

    /**
     * @param interconnectBay
     *            the interconnectBay to set
     */
    public void setInterconnectBay(final Integer interconnectBay) {
        this.interconnectBay = interconnectBay;
    }

    /**
     * @return the midplanePort
     */
    public Integer getMidplanePort() {
        return midplanePort;
    }

    /**
     * @param midplanePort
     *            the midplanePort to set
     */
    public void setMidplanePort(final Integer midplanePort) {
        this.midplanePort = midplanePort;
    }

}
