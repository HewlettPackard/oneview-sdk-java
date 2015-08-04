/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.EventTypes;

public class MsmbMessageExecutionQueue extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(MsmbMessageExecutionQueue.class);
    private final BlockingQueue<String> messagesInQueue = new ArrayBlockingQueue<String>(200);
    private static final String SHUTDOWN_REQ = "SHUTDOWN";
    private volatile boolean shuttingDown;
    private volatile boolean messagingTerminated;
    private final MsmbAlertsHandler handler;

    public MsmbMessageExecutionQueue(final MsmbAlertsHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = messagesInQueue.take()) != SHUTDOWN_REQ) {
                // TODO - processing logic
                // check for power off/on alerts

                if (message.contains(EventTypes.MSMB_SERVER_HARDWARE)) {
                    // TODO - call listeners
                    logger.debug("MsmbProcessor : run : Message Received: " + message);
                    handler.handleMessage(message);
                }

                else {// everything else, just print message
                    logger.debug("MsmbProcessor : run : other Message Received: " + message);
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
