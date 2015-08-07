/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.scmb.consumer.samples;

import com.hp.ov.sdk.dto.samples.ScmbMessage;

public class ServerAlertsConsumer implements ServerHardwareListener {
    @Override
    public void notifyPowerStatus(final ScmbMessage message) {
        // TODO - consumer logic
        if (message.getSeverity().equals("OK")) {
            System.out.println("ServerAlertsConsumer: notifyPowerStatus : Health is good ");
        } else {
            System.out.println("ServerAlertsConsumer: notifyPowerStatus : Health is not good ");
        }
        System.out.println("ServerAlertsConsumer: notifyPowerStatus : resouce Name = " + message.getResourceName());
        System.out.println("ServerAlertsConsumer: notifyPowerStatus : severity = " + message.getSeverity());
        // TODO - other consumer logic
    }

    @Override
    public void notifyHealthStatus(final ScmbMessage message) {
        // TODO consumer logic
        if (message.getSeverity().equals("OK")) {
            System.out.println("ServerAlertsConsumer: notifyHealthStatus : Health is good ");
        } else {
            System.out.println("ServerAlertsConsumer: notifyHealthStatus : Health is not good ");
        }
        System.out.println("ServerAlertsConsumer: notifyHealthStatus : resouce Name = " + message.getResourceName());
        System.out.println("ServerAlertsConsumer: notifyHealthStatus : severity = " + message.getSeverity());
        // TODO - other consumer logic

    }

}
