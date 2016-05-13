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

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;

public class ScmbProcessor extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScmbProcessor.class);

    private final RestParams params;
    private final Connection conn;
    private final Channel channel;
    private final String routingKey;
    private final ScmbMessageExecutionQueue messageQueue;

    public ScmbProcessor(final RestParams params, final Connection conn, final Channel channel, final String routingKey,
            final ScmbMessageExecutionQueue queue) {
        this.params = params;
        this.conn = conn;
        this.channel = channel;
        this.routingKey = routingKey;
        this.messageQueue = queue;
    }

    @Override
    public void run() {

        try {
            // do not specify queue name. AMQP will create a queue with random
            // name starting with amq.gen* e.g. amq.gen-32sfQz9
            final DeclareOk queue = channel.queueDeclare("", true, false, true, null);

            // Now get the queue name from above call and bind it to required
            // Exchange with required routing key.
            channel.queueBind(queue.getQueue(), SdkConstants.SCMB_EXCHANGE_NAME, routingKey);
            // Now you should be able to receive messages from queue
            while (true) {
                final GetResponse chResponse = channel.basicGet(queue.getQueue(), false);
                if (chResponse == null) {
                    // LOGGER.debug("ScmbProcessor : run : No Message Received: ");
                } else {
                    final byte[] body = chResponse.getBody();
                    final String responseBody = new String(body);
                    messageQueue.add(responseBody);
                }
                Thread.sleep(5000);
            }
        } catch (final IOException | InterruptedException e) {
            LOGGER.error("ScmbProcessor : run : error in scmb processor : thread might have been interrupted by Stop user");
        }
    }

    public void releaseScmbThread() {
        this.interrupt();
        // also stop Message Queue thread
        try {
            messageQueue.shutDown();
        } catch (final InterruptedException e) {
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.APPLIANCE, null);
        }
    }

}
