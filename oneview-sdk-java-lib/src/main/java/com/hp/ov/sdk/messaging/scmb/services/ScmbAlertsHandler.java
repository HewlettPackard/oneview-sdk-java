/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ScmbAlertsMessageAdaptor;
import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;
import com.hp.ov.sdk.messaging.scmb.listeners.ScmbListener;

public class ScmbAlertsHandler
{
    private static final Logger logger = LoggerFactory.getLogger(ScmbAlertsHandler.class);

    private ScmbAlertsMessageAdaptor adaptor;

    private ScmbListener scmbListener;

    public ScmbAlertsHandler()
    {

    }

    public ScmbAlertsHandler(final ScmbListener scmbListener)
    {
        this.scmbListener = scmbListener;
    }

    public void handleMessage(final String message)
    {
        logger.debug("ScmbAlertsHandler : handlMessage : Message Received: " + message);

        adaptor = new ScmbAlertsMessageAdaptor();
        // call adaptor
        final ScmbAlertsMessageDto alertsDto = adaptor.buildDto(message);

        logger.debug("ScmbAlertsHandler : handlMessage :  value from Dto : resourceUri: " + alertsDto.getResourceUri());
        //invoke listener
        scmbListener.handleScmbMessage(alertsDto);
    }
}
