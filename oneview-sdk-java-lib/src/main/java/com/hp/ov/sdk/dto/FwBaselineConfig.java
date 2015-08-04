/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class FwBaselineConfig {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String firmwareUpdateOn;
    private Boolean forceInstallFirmware;
    private String fwBaselineUri;
    private Boolean isFwManaged;

    public String getFirmwareUpdateOn() {
        return firmwareUpdateOn;
    }

    public void setFirmwareUpdateOn(String firmwareUpdateOn) {
        this.firmwareUpdateOn = firmwareUpdateOn;
    }

    public Boolean getForceInstallFirmware() {
        return forceInstallFirmware;
    }

    public void setForceInstallFirmware(Boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }

    public String getFwBaselineUri() {
        return fwBaselineUri;
    }

    public void setFwBaselineUri(String fwBaselineUri) {
        this.fwBaselineUri = fwBaselineUri;
    }

    public Boolean getIsFwManaged() {
        return isFwManaged;
    }

    public void setIsFwManaged(Boolean isFwManaged) {
        this.isFwManaged = isFwManaged;
    }
}
