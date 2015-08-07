/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class EnclosureGroupV2 extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer interconnectBayMappingCount;
    private List<IOBayMapping> interconnectBayMappings = new ArrayList<IOBayMapping>();
    private Integer portMappingCount;
    private List<PortMapping> portMappings = new ArrayList<PortMapping>();
    private StackingMode stackingMode;

    /**
     * @return the interconnectBayMappingCount
     */
    public Integer getInterconnectBayMappingCount() {
        return interconnectBayMappingCount;
    }

    /**
     * @param interconnectBayMappingCount
     *            the interconnectBayMappingCount to set
     */
    public void setInterconnectBayMappingCount(final Integer interconnectBayMappingCount) {
        this.interconnectBayMappingCount = interconnectBayMappingCount;
    }

    /**
     * @return the interconnectBayMappings
     */
    public List<IOBayMapping> getInterconnectBayMappings() {
        return interconnectBayMappings;
    }

    /**
     * @param interconnectBayMappings
     *            the interconnectBayMappings to set
     */
    public void setInterconnectBayMappings(final List<IOBayMapping> interconnectBayMappings) {
        this.interconnectBayMappings = interconnectBayMappings;
    }

    /**
     * @return the portMappingCount
     */
    public Integer getPortMappingCount() {
        return portMappingCount;
    }

    /**
     * @param portMappingCount
     *            the portMappingCount to set
     */
    public void setPortMappingCount(final Integer portMappingCount) {
        this.portMappingCount = portMappingCount;
    }

    /**
     * @return the portMappings
     */
    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    /**
     * @param portMappings
     *            the portMappings to set
     */
    public void setPortMappings(final List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    /**
     * @return the stackingMode
     */
    public StackingMode getStackingMode() {
        return stackingMode;
    }

    /**
     * @param stackingMode
     *            the stackingMode to set
     */
    public void setStackingMode(final StackingMode stackingMode) {
        this.stackingMode = stackingMode;
    }

}
