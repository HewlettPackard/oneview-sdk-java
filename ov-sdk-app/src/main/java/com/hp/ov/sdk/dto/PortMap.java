/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PortMap implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<DeviceSlot> deviceSlots = new ArrayList<DeviceSlot>();

    /**
     * @return the deviceSlots
     */
    public List<DeviceSlot> getDeviceSlots()
    {
        return deviceSlots;
    }

    /**
     * @param deviceSlots the deviceSlots to set
     */
    public void setDeviceSlots(List<DeviceSlot> deviceSlots)
    {
        this.deviceSlots = deviceSlots;
    }
}
