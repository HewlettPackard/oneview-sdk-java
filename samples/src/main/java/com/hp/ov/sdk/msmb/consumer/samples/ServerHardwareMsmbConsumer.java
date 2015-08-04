/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.msmb.consumer.samples;

import com.hp.ov.sdk.dto.samples.MsmbMessage;

public class ServerHardwareMsmbConsumer implements ServerHardwareMsmbListener {
    
    @Override
    public void notifyPowerUsage(final MsmbMessage message) {
        // TODO - consumer logic to send it their analytics tools
        System.out.println("ServerHardwareMsmbConsumer: notifyPowerUsage : resouce uri = " + message.getResourceUri());
        System.out.println("ServerHardwareMsmbConsumer: notifyPowerUsage : resourceDataList = " + message.getResourceDataList());
        // TODO - other consumer logic
    }

    // TODO - other user methods

}
