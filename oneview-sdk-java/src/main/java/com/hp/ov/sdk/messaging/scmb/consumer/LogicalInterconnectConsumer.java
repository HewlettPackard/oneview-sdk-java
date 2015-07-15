/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.ScmbMessage;
import com.hp.ov.sdk.messaging.scmb.listeners.InterconnectListener;

@Component
public class LogicalInterconnectConsumer implements InterconnectListener
{

    private static final Logger logger = LoggerFactory
            .getLogger(LogicalInterconnectConsumer.class);

    @Override
    public void notifyPowerStatus(final ScmbMessage message)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyLiUplinkSetStatus(final ScmbMessage message)
    {
        // TODO consumer logic
        if (message.getSeverity().equals("OK"))
        {
            logger.info("ServerAlertsConsumer: notifyLiStatus : Health is good ");
        }
        else
        {
            logger.info("ServerAlertsConsumer: notifyLiStatus : Health is not good ");
        }
        logger.debug("ServerAlertsConsumer: notifyLiStatus : resouce Name = "
                + message.getResourceName());
        logger.debug("ServerAlertsConsumer: notifyLiStatus : severity = "
                + message.getSeverity());

    }

}
