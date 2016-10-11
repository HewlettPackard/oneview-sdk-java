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
package com.hp.ov.sdk.dto.networking.interconnect;

public enum InterconnectTypeName {

    Cisco_Fabric_Extender_for_HP_BladeSystem("Cisco Fabric Extender for HP BladeSystem"),
    HP_VC_8Gb_20_Port_FC_Module("HP VC 8Gb 20-Port FC Module"),
    HP_VC_8Gb_24_Port_FC_Module("HP VC 8Gb 24-Port FC Module"),
    HP_VC_Flex_10_Enet_Module("HP VC Flex-10 Enet Module"),
    HP_VC_Flex_10_10D_Module("HP VC Flex-10/10D Module"),
    HP_VC_FlexFabric_10Gb_24_Port_Module("HP VC FlexFabric 10Gb/24-Port Module"),
    HP_VC_FlexFabric_20_40_F8_Module("HP VC FlexFabric-20/40 F8 Module");

    private String value;

    private InterconnectTypeName(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString(){
        return value;
    }
}
