/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.messaging.core;

import java.io.IOException;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.exceptions.SDKMessageBusException;
import com.hp.ov.sdk.messaging.msmb.MsmbMessage;
import com.hp.ov.sdk.messaging.msmb.MsmbMessageHandler;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageBusClient {

    private static final String MSMB_EXCHANGE = "msmb";
    private static final String SCMB_EXCHANGE = "scmb";

    private final Connection connection;
    private final ResourceAdaptor adaptor;

    private MessageBusClient(Connection connection) {
        this.connection = connection;

        this.adaptor = new ResourceAdaptor();
    }

    public static synchronized MessageBusClient connect(SDKConfiguration config, OneViewClient client) {
        RabbitMQConnectionFactory factory = new RabbitMQConnectionFactory(config, client.messagingCertificate());
        Connection connection;

        try {
            connection = factory.getConnection();
        } catch (IOException e) {
            throw new SDKMessageBusException("Connection to RabbitMQ failed!", e);
        }
        return new MessageBusClient(connection);
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (IOException e) {
            throw new SDKMessageBusException("Could not properly close the connection to RabbitMQ", e);
        }
    }

    public Channel subscribeToMetricStreaming(String routingKey, final MsmbMessageHandler handler) {
        Channel channel;

        try {
            channel = this.connection.createChannel();
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, MSMB_EXCHANGE, routingKey);

            channel.basicConsume(queue, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                        AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");

                    MsmbMessage msmbMessage = MessageBusClient.this.adaptor.buildResource(message, MsmbMessage.class);

                    handler.handleMessage(msmbMessage);
                }
            });
        } catch (IOException e) {
            throw new SDKMessageBusException("Could not subscribe to RabbitMQ metric streaming message bus", e);
        }
        return channel;
    }

    public void subscribeToStateChange(String routingKey) {

    }


}
