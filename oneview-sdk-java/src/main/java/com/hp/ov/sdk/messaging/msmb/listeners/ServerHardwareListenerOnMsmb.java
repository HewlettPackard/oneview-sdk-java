/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.listeners;

import com.hp.ov.sdk.dto.MsmbMessage;

public interface ServerHardwareListenerOnMsmb
{

    /**
     * 
     * @param message
     */
    public void notifyPowerUsage(final MsmbMessage message);

}
