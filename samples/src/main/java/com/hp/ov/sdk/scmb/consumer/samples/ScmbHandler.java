/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
// ScmbHandler.java - (insert one line description here)
// (C) Copyright 2015 Hewlett-Packard Development Company, L.P.

package com.hp.ov.sdk.scmb.consumer.samples;

import com.hp.ov.sdk.constants.samples.EventTypes;
import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;
import com.hp.ov.sdk.dto.samples.ScmbMessage;
import com.hp.ov.sdk.messaging.scmb.listeners.ScmbListener;
import com.hp.ov.sdk.messaging.scmb.services.ScmbAlertsHandler;

/**
 * 
 */
public class ScmbHandler implements ScmbListener {

    private final ScmbAlertsHandler scmbAlertsHandler;

    public ScmbHandler() {
        scmbAlertsHandler = new ScmbAlertsHandler(this);
    }

    public ScmbAlertsHandler getScmbAlertsHandler() {
        return scmbAlertsHandler;
    }

    /*
     * @see
     * com.hp.ov.sdk.messaging.scmb.listeners.ScmbListener#handleScmbMessage
     * (com.hp.ov.sdk.dto.ScmbAlertsMessageDto)
     */
    @Override
    public void handleScmbMessage(final ScmbAlertsMessageDto alertsDto) {
        System.out.println("ScmbAlertsHandler : handlMessage :  value from Dto : resourceUri: " + alertsDto.getResourceUri());
        final ScmbMessage scmbMessage = new ScmbMessage();
        // set the message
        scmbMessage.setSeverity(alertsDto.getResource().getSeverity());
        scmbMessage.setResourceName(alertsDto.getResource().getAssociatedResource().getResourceName());
        scmbMessage.setResourceUri(alertsDto.getResource().getAssociatedResource().getResourceUri());
        scmbMessage.setUri(alertsDto.getUri());

        final ServerAlertsConsumer serverHandler = new ServerAlertsConsumer();
        final LogicalInterconnectConsumer liHandler = new LogicalInterconnectConsumer();

        // notify server listener
        if (alertsDto.getResource().getAlertTypeID().equals(EventTypes.SERVER_RESET_EVENT)) {
            serverHandler.notifyPowerStatus(scmbMessage);
        } else if (alertsDto.getResource().getAlertTypeID().equals(EventTypes.SERVER_HEALTH_STATUS_EVENT)) { // server
                                                                                                             // health
                                                                                                             // listener
            serverHandler.notifyHealthStatus(scmbMessage);
        } else if (alertsDto.getResource().getAlertTypeID().equals(EventTypes.UPLINK_SET_CHANGE_EVENT)) { // notify
                                                                                                          // logical-interconnecs
                                                                                                          // listener
            liHandler.notifyLiUplinkSetStatus(scmbMessage);
        }

    }

}
