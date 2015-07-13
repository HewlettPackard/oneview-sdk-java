/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class PortInfoV2 extends BaseModelResource
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Boolean downlinkCapable;
    private String pairedPortName;
    private List<PortCapabilityType> portCapability = new ArrayList<PortCapabilityType>();
    private String portName;
    private Integer portNumber;
    private Boolean uplinkCapable;

    /**
     * @return the downlinkCapable
     */
    public Boolean getDownlinkCapable()
    {
        return downlinkCapable;
    }

    /**
     * @param downlinkCapable the downlinkCapable to set
     */
    public void setDownlinkCapable(Boolean downlinkCapable)
    {
        this.downlinkCapable = downlinkCapable;
    }

    /**
     * @return the pairedPortName
     */
    public String getPairedPortName()
    {
        return pairedPortName;
    }

    /**
     * @param pairedPortName the pairedPortName to set
     */
    public void setPairedPortName(String pairedPortName)
    {
        this.pairedPortName = pairedPortName;
    }

    /**
     * @return the portCapability
     */
    public List<PortCapabilityType> getPortCapability()
    {
        return portCapability;
    }

    /**
     * @param portCapability the portCapability to set
     */
    public void setPortCapability(List<PortCapabilityType> portCapability)
    {
        this.portCapability = portCapability;
    }

    /**
     * @return the portName
     */
    public String getPortName()
    {
        return portName;
    }

    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName)
    {
        this.portName = portName;
    }

    /**
     * @return the portNumber
     */
    public Integer getPortNumber()
    {
        return portNumber;
    }

    /**
     * @param portNumber the portNumber to set
     */
    public void setPortNumber(Integer portNumber)
    {
        this.portNumber = portNumber;
    }

    /**
     * @return the uplinkCapable
     */
    public Boolean getUplinkCapable()
    {
        return uplinkCapable;
    }

    /**
     * @param uplinkCapable the uplinkCapable to set
     */
    public void setUplinkCapable(Boolean uplinkCapable)
    {
        this.uplinkCapable = uplinkCapable;
    }

}
