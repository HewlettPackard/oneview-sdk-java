/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
 */
package com.hp.ov.sdk.messaging.scmb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.messaging.scmb.listeners.ScmbListener;

public class ScmbAlertsHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScmbAlertsHandler.class);

    private final ScmbListener scmbListener;
    private final ResourceAdaptor adaptor;

    public ScmbAlertsHandler(ScmbListener scmbListener) {
        this.scmbListener = scmbListener;

        this.adaptor = new ResourceAdaptor();
    }

    public void handleMessage(final String message) {
        LOGGER.debug("ScmbAlertsHandler : handleMessage : Message Received: " + message);

        ScmbAlertsMessageDto alertsDto = adaptor.buildResource(message, ScmbAlertsMessageDto.class);

        LOGGER.debug("ScmbAlertsHandler : handleMessage :  value from Dto : resourceUri: " + alertsDto.getResourceUri());

        scmbListener.handleScmbMessage(alertsDto);
    }
}
