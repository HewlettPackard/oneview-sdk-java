/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.constants;

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
