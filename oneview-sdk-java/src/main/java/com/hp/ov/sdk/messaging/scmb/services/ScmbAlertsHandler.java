/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.ScmbAlertsMessageAdaptor;
import com.hp.ov.sdk.constants.EventTypes;
import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;
import com.hp.ov.sdk.dto.ScmbMessage;
import com.hp.ov.sdk.messaging.scmb.consumer.LogicalInterconnectConsumer;
import com.hp.ov.sdk.messaging.scmb.consumer.ServerAlertsConsumer;

@Component
public class ScmbAlertsHandler
{

    private static final Logger logger = LoggerFactory.getLogger(ScmbAlertsHandler.class);

    @Autowired
    private ScmbAlertsMessageAdaptor adaptor;

    @Autowired
    private ServerAlertsConsumer serverConsumer;

    @Autowired
    private LogicalInterconnectConsumer liConsumer;

    @Autowired
    private ScmbMessage scmbMessage;

    public void handleMessage(final String message)
    {

        logger.debug("ScmbAlertsHandler : handlMessage : Message Received: " + message);
        //call adaptor
        final ScmbAlertsMessageDto alertsDto = adaptor.buildDto(message);

        logger.debug("ScmbAlertsHandler : handlMessage :  value from Dto : resourceUri: " + alertsDto.getResourceUri());
        //set the message
        scmbMessage.setSeverity(alertsDto.getResource().getSeverity());
        scmbMessage.setResourceName(alertsDto.getResource().getAssociatedResource().getResourceName());
        scmbMessage.setResourceUri(alertsDto.getResource().getAssociatedResource().getResourceUri());
        scmbMessage.setUri(alertsDto.getUri());

        //notify server listener
        if (alertsDto.getResource().getAlertTypeID().equals(EventTypes.SERVER_RESET_EVENT))
        {
            serverConsumer.notifyPowerStatus(scmbMessage);
        }
        else if (alertsDto.getResource().getAlertTypeID().equals(EventTypes.SERVER_HEALTH_STATUS_EVENT))
        { //server health listener
            serverConsumer.notifyHealthStatus(scmbMessage);
        }
        else if (alertsDto.getResource().getAlertTypeID().equals(EventTypes.UPLINK_SET_CHANGE_EVENT))
        { //notify logical-interconnecs listener
            liConsumer.notifyLiUplinkSetStatus(scmbMessage);
        }
    }
}
