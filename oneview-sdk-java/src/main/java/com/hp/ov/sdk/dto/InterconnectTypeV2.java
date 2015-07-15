/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class InterconnectTypeV2 extends BaseModelResource
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<DownlinkCapabilities> downlinkCapabilities = new ArrayList<DownlinkCapabilities>();
    private Integer downlinkCount;
    private String maximumFirmwareVersion;
    private String minimumFirmwareVersion;
    private String partNumber;
    private List<PortInfoV2> portInfos = new ArrayList<PortInfoV2>();
    private List<UnsupportedCapabilities> unsupportedCapabilities = new ArrayList<UnsupportedCapabilities>();

    /**
     * @return the downlinkCapabilities
     */
    public List<DownlinkCapabilities> getDownlinkCapabilities()
    {
        return downlinkCapabilities;
    }

    /**
     * @param downlinkCapabilities the downlinkCapabilities to set
     */
    public void setDownlinkCapabilities(
            final List<DownlinkCapabilities> downlinkCapabilities)
    {
        this.downlinkCapabilities = downlinkCapabilities;
    }

    /**
     * @return the downlinkCount
     */
    public Integer getDownlinkCount()
    {
        return downlinkCount;
    }

    /**
     * @param downlinkCount the downlinkCount to set
     */
    public void setDownlinkCount(final Integer downlinkCount)
    {
        this.downlinkCount = downlinkCount;
    }

    /**
     * @return the maximumFirmwareVersion
     */
    public String getMaximumFirmwareVersion()
    {
        return maximumFirmwareVersion;
    }

    /**
     * @param maximumFirmwareVersion the maximumFirmwareVersion to set
     */
    public void setMaximumFirmwareVersion(final String maximumFirmwareVersion)
    {
        this.maximumFirmwareVersion = maximumFirmwareVersion;
    }

    /**
     * @return the minimumFirmwareVersion
     */
    public String getMinimumFirmwareVersion()
    {
        return minimumFirmwareVersion;
    }

    /**
     * @param minimumFirmwareVersion the minimumFirmwareVersion to set
     */
    public void setMinimumFirmwareVersion(final String minimumFirmwareVersion)
    {
        this.minimumFirmwareVersion = minimumFirmwareVersion;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber()
    {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(final String partNumber)
    {
        this.partNumber = partNumber;
    }

    /**
     * @return the portInfos
     */
    public List<PortInfoV2> getPortInfos()
    {
        return portInfos;
    }

    /**
     * @param portInfos the portInfos to set
     */
    public void setPortInfos(final List<PortInfoV2> portInfos)
    {
        this.portInfos = portInfos;
    }

    /**
     * @return the unsupportedCapabilities
     */
    public List<UnsupportedCapabilities> getUnsupportedCapabilities()
    {
        return unsupportedCapabilities;
    }

    /**
     * @param unsupportedCapabilities the unsupportedCapabilities to set
     */
    public void setUnsupportedCapabilities(
            final List<UnsupportedCapabilities> unsupportedCapabilities)
    {
        this.unsupportedCapabilities = unsupportedCapabilities;
    }

}
