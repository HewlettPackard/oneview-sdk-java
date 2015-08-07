/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
// ScmbHandler.java - (insert one line description here)
// (C) Copyright 2015 Hewlett-Packard Development Company, L.P.

package com.hp.ov.sdk.msmb.consumer.samples;

import com.hp.ov.sdk.dto.MsmbAlertsMessageDto;
import com.hp.ov.sdk.dto.samples.MsmbMessage;
import com.hp.ov.sdk.msmb.consumer.samples.ServerHardwareMsmbConsumer;
import com.hp.ov.sdk.messaging.msmb.listeners.MsmbListener;
import com.hp.ov.sdk.messaging.msmb.services.MsmbAlertsHandler;

/**
 * 
 */
public class MsmbHandler implements MsmbListener {

    private final MsmbAlertsHandler msmbAlertsHandler;

    public MsmbHandler() {
        msmbAlertsHandler = new MsmbAlertsHandler(this);
    }

    public MsmbAlertsHandler getMsmbAlertsHandler() {
        return msmbAlertsHandler;
    }

    /*
     * @see
     * com.hp.ov.sdk.messaging.scmb.listeners.ScmbListener#handleScmbMessage
     * (com.hp.ov.sdk.dto.ScmbAlertsMessageDto)
     */
    @Override
    public void handleMsmbMessage(final MsmbAlertsMessageDto alertsDto) {
        System.out.println("MsmbAlertsHandler : handlMessage : value from Dto : resourceUri: " + alertsDto.getResourceUri());
        MsmbMessage msmbMessage = new MsmbMessage();
        // set the message
        msmbMessage.setResourceUri(alertsDto.getResourceUri());
        msmbMessage.setResourceDataList(alertsDto.getResource().getResourceDataList());
        ServerHardwareMsmbConsumer serverConsumer = new ServerHardwareMsmbConsumer();
        // notify server power usage consumer
        // TODO - filter logic
        // if
        // (alertsDto.getResource().getResourceTye().equals(EventTypes.MSMB_SERVER_HARDWARE)){
        serverConsumer.notifyPowerUsage(msmbMessage);
        // }

    }

}
