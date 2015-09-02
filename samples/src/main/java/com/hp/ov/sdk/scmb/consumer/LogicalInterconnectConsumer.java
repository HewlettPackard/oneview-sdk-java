/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.scmb.consumer;

import com.hp.ov.sdk.dto.samples.ScmbMessage;

public class LogicalInterconnectConsumer implements InterconnectListener {

    @Override
    public void notifyPowerStatus(final ScmbMessage message) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyLiUplinkSetStatus(final ScmbMessage message) {
        // TODO consumer logic
        if (message.getSeverity().equals("OK")) {
            System.out.println("ServerAlertsConsumer: notifyLiStatus : Health is good ");
        } else {
            System.out.println("ServerAlertsConsumer: notifyLiStatus : Health is not good ");
        }
        System.out.println("ServerAlertsConsumer: notifyLiStatus : resouce Name = " + message.getResourceName());
        System.out.println("ServerAlertsConsumer: notifyLiStatus : severity = " + message.getSeverity());

    }

}
