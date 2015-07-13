/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.MsmbMessage;
import com.hp.ov.sdk.messaging.msmb.listeners.ServerHardwareListenerOnMsmb;

@Component
public class ServerHardwareMsmbConsumer implements ServerHardwareListenerOnMsmb
{
    private static final Logger logger = LoggerFactory
            .getLogger(ServerHardwareMsmbConsumer.class);

    @Override
    public void notifyPowerUsage(final MsmbMessage message)
    {
        // TODO - consumer logic to send it their analytics tools
        logger.debug("ServerHardwareMsmbConsumer: notifyPowerUsage : resouce uri = "
                + message.getResourceUri());
        logger.debug("ServerHardwareMsmbConsumer: notifyPowerUsage : resourceDataList = "
                + message.getResourceDataList());
        // TODO - other consumer logic
    }

    //TODO - other user methods

}
