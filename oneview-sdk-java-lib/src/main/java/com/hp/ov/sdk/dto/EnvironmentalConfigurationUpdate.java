/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class EnvironmentalConfigurationUpdate {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer calibratedMaxPower;

    public Integer getCalibratedMaxPower() {
        return calibratedMaxPower;
    }

    public void setCalibratedMaxPower(Integer calibratedMaxPower) {
        this.calibratedMaxPower = calibratedMaxPower;
    }
}
