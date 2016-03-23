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

public enum BiosSettingsState {

    /**
     * Indicates the current BIOS/UEFI settings were retrieved from the
     * server successfully and no profile changes are pending.
     */
    Applied,
    /**
     * Indicates there are BIOS/UEFI settings defined in the server
     * profile that have been applied to the server hardware and will
     * take effect when the server hardware is next powered on and completes
     * its power-on self-test.
     */
    Pending,
    /**
     * Indicates the server failed to return the current settings,
     * therefore, the actual values might not be current.
     */
    Unknown

}
