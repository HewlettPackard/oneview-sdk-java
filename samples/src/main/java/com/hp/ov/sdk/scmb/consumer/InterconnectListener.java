/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.scmb.consumer;

import com.hp.ov.sdk.dto.samples.ScmbMessage;

public interface InterconnectListener {

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
