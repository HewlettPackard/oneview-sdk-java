/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeviceSlot implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String deviceName;
    private String location;
    private Integer oaSlotNumber;
    private List<PhysicalPort> physicalPorts = new ArrayList<PhysicalPort>();
    private Integer slotNumber;

    /**
     * @return the deviceName
     */
    public String getDeviceName()
    {
        return deviceName;
    }

    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(final String deviceName)
    {
        this.deviceName = deviceName;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(final String location)
    {
        this.location = location;
    }

    /**
     * @return the oaSlotNumber
     */
    public Integer getOaSlotNumber()
    {
        return oaSlotNumber;
    }

    /**
     * @param oaSlotNumber the oaSlotNumber to set
     */
    public void setOaSlotNumber(final Integer oaSlotNumber)
    {
        this.oaSlotNumber = oaSlotNumber;
    }

    /**
     * @return the physicalPorts
     */
    public List<PhysicalPort> getPhysicalPorts()
    {
        return physicalPorts;
    }

    /**
     * @param physicalPorts the physicalPorts to set
     */
    public void setPhysicalPorts(final List<PhysicalPort> physicalPorts)
    {
        this.physicalPorts = physicalPorts;
    }

    /**
     * @return the slotNumber
     */
    public Integer getSlotNumber()
    {
        return slotNumber;
    }

    /**
     * @param slotNumber the slotNumber to set
     */
    public void setSlotNumber(final Integer slotNumber)
    {
        this.slotNumber = slotNumber;
    }

}
