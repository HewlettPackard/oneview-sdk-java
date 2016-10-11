/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.dto.servers.logicalenclosure;

public class PatchFirmwareValue {
    private String firmwareBaselineUri;
    private FirmwareUpdateOn firmwareUpdateOn;
    private boolean forceInstallFirmware;

    public PatchFirmwareValue(String firmwareBaselineUri, FirmwareUpdateOn firmwareUpdateOn, boolean forceInstallFirmware) {
        this.firmwareBaselineUri = firmwareBaselineUri;
        this.firmwareUpdateOn = firmwareUpdateOn;
        this.forceInstallFirmware = forceInstallFirmware;
    }

    /**
     * @return the firmwareBaselineUri
     */
    public String getFirmwareBaselineUri() {
        return firmwareBaselineUri;
    }
    /**
     * @param firmwareBaselineUri the firmwareBaselineUri to set
     */
    public void setFirmwareBaselineUri(String firmwareBaselineUri) {
        this.firmwareBaselineUri = firmwareBaselineUri;
    }
    /**
     * @return the firmwareUpdateOn
     */
    public FirmwareUpdateOn getFirmwareUpdateOn() {
        return firmwareUpdateOn;
    }
    /**
     * @param firmwareUpdateOn the firmwareUpdateOn to set
     */
    public void setFirmwareUpdateOn(FirmwareUpdateOn firmwareUpdateOn) {
        this.firmwareUpdateOn = firmwareUpdateOn;
    }
    /**
     * @return the forceInstallFirmware
     */
    public boolean isForceInstallFirmware() {
        return forceInstallFirmware;
    }
    /**
     * @param forceInstallFirmware the forceInstallFirmware to set
     */
    public void setForceInstallFirmware(boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }
}
