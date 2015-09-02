/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.scmb.consumer;

import com.hp.ov.sdk.dto.samples.ScmbMessage;

public interface ServerHardwareListener {

    /**
     * 
     * @param message
     */
    public void notifyPowerStatus(final ScmbMessage message);

    /**
     * 
     * @param message
     */
    public void notifyHealthStatus(final ScmbMessage message);
}
