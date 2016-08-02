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
package com.hp.ov.sdk.dto.networking.logicaldownlinks;

public enum DownlinkCapabilities {

    /* Please add values to end of this list so that existing ordinal values are preserved. */
    FCoE("FCoE"),
    Flex10_4SubPort("Flex10_4SubPort"),
    Ethernet("Ethernet"),
    Flex20_4SubPort("Flex20_4SubPort"),
    PortExtender("PortExtender"),
    Unknown("Unknown"),
    NotApplicable("NotApplicable"),
    FibreChannel("FibreChannel"),
    Dcc ("Dcc"),
    Qbg("QBG"),
    ConnectionReservation("ConnectionReservation"),
    ConnectionDeployment("ConnectionDeployment"),
    ConnectionVirtualMAC("ConnectionVirtualMAC"),
    ConnectionVirtualWWN("ConnectionVirtualWWN"),
    ConnectionBandwidthConfiguration("ConnectionBandwidthConfiguration"),
    iSCSI("iSCSI"),
    SRIOV("SRIOV"),
    Flex20_8SubPort("Flex20_8SubPort");

    private String value;

    DownlinkCapabilities(String value) {
        this.value = value;
    }

    public static DownlinkCapabilities fromString(String value) {
        if(value != null) {
            for(DownlinkCapabilities downlinkCapabilities :DownlinkCapabilities.values()) {
                if(value.equalsIgnoreCase(downlinkCapabilities.value)) {
                    return downlinkCapabilities;
                }
            }
        }
        throw new IllegalArgumentException("No " + DownlinkCapabilities.class.getName() + " with value: " + value);
    }
}
