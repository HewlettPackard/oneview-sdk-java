/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.hp.ov.sdk.adaptors.MsmbAlertsMessageAdaptor;
import com.hp.ov.sdk.dto.MsmbAlertsMessageDto;
import com.hp.ov.sdk.messaging.msmb.listeners.MsmbListener;

@Component
public class MsmbAlertsHandler {

    private static final Logger logger = LoggerFactory.getLogger(MsmbAlertsHandler.class);

    private MsmbAlertsMessageAdaptor adaptor;
    private MsmbListener msmbListener;

    public MsmbAlertsHandler() {

    }

    public MsmbAlertsHandler(final MsmbListener msmbListener) {
        this.msmbListener = msmbListener;
    }

    public void handleMessage(final String message) {
        adaptor = new MsmbAlertsMessageAdaptor();
        logger.debug("MsmbAlertsHandler : handlMessage : Message Received: " + message);
        // call adaptor
        final MsmbAlertsMessageDto alertsDto = adaptor.buildDto(message);
        // invoke listener
        msmbListener.handleMsmbMessage(alertsDto);

    }
}
