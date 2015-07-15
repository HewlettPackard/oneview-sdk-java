/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.ScmbMessage;
import com.hp.ov.sdk.messaging.scmb.listeners.ServerHardwareListenerOnScmb;

@Component
public class ServerAlertsConsumer implements ServerHardwareListenerOnScmb
{
    private static final Logger logger = LoggerFactory
            .getLogger(ServerAlertsConsumer.class);

    @Override
    public void notifyPowerStatus(final ScmbMessage message)
    {
        // TODO - consumer logic
        if (message.getSeverity().equals("OK"))
        {
            logger.info("ServerAlertsConsumer: notifyPowerStatus : Health is good ");
        }
        else
        {
            logger.info("ServerAlertsConsumer: notifyPowerStatus : Health is not good ");
        }
        logger.debug("ServerAlertsConsumer: notifyPowerStatus : resouce Name = "
                + message.getResourceName());
        logger.debug("ServerAlertsConsumer: notifyPowerStatus : severity = "
                + message.getSeverity());
        // TODO - other consumer logic
    }

    @Override
    public void notifyHealthStatus(final ScmbMessage message)
    {
        // TODO consumer logic
        if (message.getSeverity().equals("OK"))
        {
            logger.info("ServerAlertsConsumer: notifyHealthStatus : Health is good ");
        }
        else
        {
            logger.info("ServerAlertsConsumer: notifyHealthStatus : Health is not good ");
        }
        logger.debug("ServerAlertsConsumer: notifyHealthStatus : resouce Name = "
                + message.getResourceName());
        logger.debug("ServerAlertsConsumer: notifyHealthStatus : severity = "
                + message.getSeverity());
        // TODO - other consumer logic

    }

}
