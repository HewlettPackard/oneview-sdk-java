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
package com.hp.ov.sdk.messaging.scmb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ScmbAlertsMessageAdaptor;
import com.hp.ov.sdk.dto.ScmbAlertsMessageDto;
import com.hp.ov.sdk.messaging.scmb.listeners.ScmbListener;

public class ScmbAlertsHandler {
    private static final Logger logger = LoggerFactory.getLogger(ScmbAlertsHandler.class);

    private ScmbAlertsMessageAdaptor adaptor;

    private ScmbListener scmbListener;

    public ScmbAlertsHandler() {

    }

    public ScmbAlertsHandler(final ScmbListener scmbListener) {
        this.scmbListener = scmbListener;
    }

    public void handleMessage(final String message) {
        logger.debug("ScmbAlertsHandler : handlMessage : Message Received: " + message);

        adaptor = new ScmbAlertsMessageAdaptor();
        // call adaptor
        final ScmbAlertsMessageDto alertsDto = adaptor.buildDto(message);

        logger.debug("ScmbAlertsHandler : handlMessage :  value from Dto : resourceUri: " + alertsDto.getResourceUri());
        // invoke listener
        scmbListener.handleScmbMessage(alertsDto);
    }
}
