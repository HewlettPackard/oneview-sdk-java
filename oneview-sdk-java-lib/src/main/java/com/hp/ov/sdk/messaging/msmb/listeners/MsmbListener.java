/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.listeners;

import com.hp.ov.sdk.dto.MsmbAlertsMessageDto;

public interface MsmbListener {

    /**
     * 
     * @param message
     */
    public void handleMsmbMessage(final MsmbAlertsMessageDto alertsDto);

}
