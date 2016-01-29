/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
// ScmbHandler.java - (insert one line description here)
// (C) Copyright 2015 Hewlett-Packard Development Company, L.P.

package com.hp.ov.sdk.scmb.consumer;

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
