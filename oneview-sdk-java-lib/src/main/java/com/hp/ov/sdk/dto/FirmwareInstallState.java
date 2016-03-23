/*
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
 */

package com.hp.ov.sdk.dto;

public enum FirmwareInstallState {

    /**
     * Indicates activation of one or more smart components failed on the server.
     */
    ActivateFailed,
    /**
     * Indicates all the smart components from the SPP bundle specified in the
     * firmware and driver baseline settings are installed and activated on the server.
     */
    Activated,
    /**
     * Indicates HP Smart Update Tool is activating the installed smart components.
     */
    Activating,
    /**
     *  Indicates HP Smart Update Tool has failed installing one or more smart components.
     */
    InstallFailed,
    /**
     * Indicates HP Smart Update Tool has completed installing the smart components,
     * however the server needs to be rebooted for the updates to take effect.
     */
    InstalledPendingReboot,
    /**
     * Indicates HP Smart Update Tool is installing the smart components from the staged location.
     */
    Installing,
    /**
     * Indicates the firmware and/or OS driver baseline settings have been applied
     * to the server hardware and will take effect when HP Smart Update tool updates
     * firmware and/or OS driver components based on these settings.
     */
    Pending,
    /**
     * Indicates HP Smart Update Tool has failed to stage the smart components from
     * the SPP bundle specified in the firmware and/or OS driver baseline settings.
     */
    StageFailed,
    /**
     * Indicates HP Smart Update Tool has completed staging the smart components
     * from the SPP bundle specified in the firmware and/or OS driver baseline settings.
     */
    Staged,
    /**
     * Indicates HP Smart Update Tool is staging the firmware and/or OS driver smart
     * components from the SPP bundle specified in the firmware and/or OS driver
     * baseline settings.
     */
    Staging,
    /**
     * Indicates the current firmware and/or OS driver settings have been cleared.
     * No components will be updated on the server.
     */
    Uninitialized,
    /**
     * Indicates the server failed to return the current settings, therefore, the
     * actual values might not be current.
     */
    Unknown

}
