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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

                handler.handleMessage(message);

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
