/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PortMap implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<DeviceSlot> deviceSlots = new ArrayList<DeviceSlot>();

    /**
     * @return the deviceSlots
     */
    public List<DeviceSlot> getDeviceSlots() {
        return deviceSlots;
    }

    /**
     * @param deviceSlots
     *            the deviceSlots to set
     */
    public void setDeviceSlots(final List<DeviceSlot> deviceSlots) {
        this.deviceSlots = deviceSlots;
    }
}
