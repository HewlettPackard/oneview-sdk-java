/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class ApplianceDetailsDto {

    private int currentVersion;
    private int minimumVersion;

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(final int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public int getMinimumVersion() {
        return minimumVersion;
    }

    public void setMinimumVersion(final int minimumVersion) {
        this.minimumVersion = minimumVersion;
    }

}
