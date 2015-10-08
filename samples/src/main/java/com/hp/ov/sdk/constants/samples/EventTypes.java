/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.constants.samples;

public class EventTypes {

    /**
     * Server alerts/events
     */
    public static final String SERVER_RESET_EVENT = "Trap.cpqSm2ServerReset";
    public static final String SERVER_HEALTH_STATUS_EVENT = "Trap.cpqHoMibHealthStatusArrayChangeTrap";

    /**
     * interconnet alerts/events
     */
    public static final String UPLINK_SET_CHANGE_EVENT = "crm.uplinkSetStateChange";

    // For MSMB
    public static final String MSMB_SERVER_HARDWARE = "server-hardware";

}
