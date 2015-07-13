/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.MsmbAlertsMessageAdaptor;
import com.hp.ov.sdk.dto.MsmbAlertsMessageDto;
import com.hp.ov.sdk.dto.MsmbMessage;
import com.hp.ov.sdk.messaging.msmb.consumer.ServerHardwareMsmbConsumer;

@Component
public class MsmbAlertsHandler
{

    private static final Logger logger = LoggerFactory.getLogger(MsmbAlertsHandler.class);

    @Autowired
    private MsmbAlertsMessageAdaptor adaptor;

    @Autowired
    private ServerHardwareMsmbConsumer serverConsumer;

    @Autowired
    private MsmbMessage msmbMessage;

    public void handleMessage(final String message)
    {

        logger.debug("MsmbAlertsHandler : handlMessage : Message Received: " + message);
        //call adaptor
        MsmbAlertsMessageDto alertsDto = adaptor.buildDto(message);

        logger.debug("MsmbAlertsHandler : handlMessage :  value from Dto : resourceUri: " + alertsDto.getResourceUri());
        //set the message
        msmbMessage.setResourceUri(alertsDto.getResourceUri());
        msmbMessage.setResourceDataList(alertsDto.getResource().getResourceDataList());

        //notify server power usage consumer
        //TODO - filter logic
        //if (alertsDto.getResource().getResourceTye().equals(EventTypes.MSMB_SERVER_HARDWARE)){
        serverConsumer.notifyPowerUsage(msmbMessage);
        //}
    }
}
