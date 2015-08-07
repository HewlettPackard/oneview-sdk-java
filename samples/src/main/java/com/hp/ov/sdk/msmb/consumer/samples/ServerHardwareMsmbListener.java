/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.msmb.consumer.samples;

import com.hp.ov.sdk.dto.samples.MsmbMessage;

public interface ServerHardwareMsmbListener {

    /**
     * 
     * @param message
     */
    public void notifyPowerUsage(final MsmbMessage message);

}
