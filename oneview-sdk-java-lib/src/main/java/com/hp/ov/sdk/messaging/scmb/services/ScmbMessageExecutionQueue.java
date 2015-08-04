/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.scmb.services;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.EventTypes;

public class ScmbMessageExecutionQueue extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ScmbMessageExecutionQueue.class);
    private final BlockingQueue<String> messagesInQueue = new ArrayBlockingQueue<String>(200);
    private static final String SHUTDOWN_REQ = "SHUTDOWN";
    private volatile boolean shuttingDown;
    private volatile boolean messagingTerminated;
    private final ScmbAlertsHandler handler;

    public ScmbMessageExecutionQueue(final ScmbAlertsHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = messagesInQueue.take()) != SHUTDOWN_REQ) {
                // TODO - processing logic
                // check for power off/on alerts
                if (message.contains(EventTypes.SERVER_RESET_EVENT)) {
                    // TODO - call listeners
                    // logger.debug("ScmbProcessor : run : Message Received: " +
                    // responseBody);
                    handler.handleMessage(message);
                } else if (message.contains(EventTypes.UPLINK_SET_CHANGE_EVENT)) { // UPLINK
                                                                                   // SET
                                                                                   // CHANGE
                    handler.handleMessage(message);
                } else if (message.contains(EventTypes.SERVER_HEALTH_STATUS_EVENT)) {
                    handler.handleMessage(message);
                }
                /*
                 * else {//everything else, just print message
                 * logger.debug("ScmbProcessor : run : Message Received: " +
                 * responseBody); }
                 */
            }
        } catch (final InterruptedException ex) {
            // TODO - exception
            logger.error(ex.getMessage());
        } finally {
            messagingTerminated = true;
        }
    }

    public void add(final String message) {
        if (shuttingDown || messagingTerminated) {
            return;
        }
        try {
            messagesInQueue.put(message);
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
            // TODO - logging and handling
            throw new RuntimeException("Unexpected Exception");
        }
    }

    public void shutDown() throws InterruptedException {
        shuttingDown = true;
        messagesInQueue.put(SHUTDOWN_REQ);
    }

}
