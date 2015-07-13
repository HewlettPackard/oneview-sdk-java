/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.listeners;

import com.hp.ov.sdk.dto.ScmbMessage;

public interface InterconnectListener
{

    /**
     * 
     * @param message
     */
    public void notifyPowerStatus(final ScmbMessage message);

    /**
     * 
     * @param message
     */
    public void notifyLiUplinkSetStatus(final ScmbMessage message);
}
