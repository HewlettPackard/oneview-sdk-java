/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class IOBayMapping implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer interconnectBay;
    private String logicalInterconnectGroupUri;

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
     * @return the logicalInterconnectGroupUri
     */
    public String getLogicalInterconnectGroupUri() {
        return logicalInterconnectGroupUri;
    }

    /**
     * @param logicalInterconnectGroupUri
     *            the logicalInterconnectGroupUri to set
     */
    public void setLogicalInterconnectGroupUri(final String logicalInterconnectGroupUri) {
        this.logicalInterconnectGroupUri = logicalInterconnectGroupUri;
    }
}
