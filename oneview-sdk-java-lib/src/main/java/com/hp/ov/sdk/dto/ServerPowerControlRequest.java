/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class ServerPowerControlRequest {

    private PhysicalServerPowerControl powerControl;
    private Boolean powerRequestIssued;
    private PhysicalServerPowerState powerState;

    /**
     * 
     * @return The powerControl
     */
    public PhysicalServerPowerControl getPowerControl() {
        return powerControl;
    }

    /**
     * 
     * @param powerControl
     *            The powerControl
     */
    public void setPowerControl(final PhysicalServerPowerControl powerControl) {
        this.powerControl = powerControl;
    }

    /**
     * 
     * @return The powerRequestIssued
     */
    public Boolean getPowerRequestIssued() {
        return powerRequestIssued;
    }

    /**
     * 
     * @param powerRequestIssued
     *            The powerRequestIssued
     */
    public void setPowerRequestIssued(final Boolean powerRequestIssued) {
        this.powerRequestIssued = powerRequestIssued;
    }

    /**
     * 
     * @return The powerState
     */
    public PhysicalServerPowerState getPowerState() {
        return powerState;
    }

    /**
     * 
     * @param powerState
     *            The powerState
     */
    public void setPowerState(final PhysicalServerPowerState powerState) {
        this.powerState = powerState;
    }

}
