/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class ServerPowerControlRequest
{

    private PhysicalServerPowerControl powerControl;
    private Boolean powerRequestIssued;
    private PhysicalServerPowerState powerState;

    /**
     * 
     * @return
     *         The powerControl
     */
    public PhysicalServerPowerControl getPowerControl()
    {
        return powerControl;
    }

    /**
     * 
     * @param powerControl
     *        The powerControl
     */
    public void setPowerControl(PhysicalServerPowerControl powerControl)
    {
        this.powerControl = powerControl;
    }

    /**
     * 
     * @return
     *         The powerRequestIssued
     */
    public Boolean getPowerRequestIssued()
    {
        return powerRequestIssued;
    }

    /**
     * 
     * @param powerRequestIssued
     *        The powerRequestIssued
     */
    public void setPowerRequestIssued(Boolean powerRequestIssued)
    {
        this.powerRequestIssued = powerRequestIssued;
    }

    /**
     * 
     * @return
     *         The powerState
     */
    public PhysicalServerPowerState getPowerState()
    {
        return powerState;
    }

    /**
     * 
     * @param powerState
     *        The powerState
     */
    public void setPowerState(PhysicalServerPowerState powerState)
    {
        this.powerState = powerState;
    }

}
