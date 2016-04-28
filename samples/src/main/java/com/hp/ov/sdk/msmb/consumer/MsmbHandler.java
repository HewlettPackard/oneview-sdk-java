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

package com.hp.ov.sdk.msmb.consumer;

import com.hp.ov.sdk.dto.MsmbAlertsMessageDto;
import com.hp.ov.sdk.dto.samples.MsmbMessage;
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
        // Optional: - Create filter logic
        // if (alertsDto.getResource().getResourceType().equals(EventTypes.MSMB_SERVER_HARDWARE)){
        serverConsumer.notifyPowerUsage(msmbMessage);
        // }

    }

}
