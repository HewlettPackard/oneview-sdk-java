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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.messaging.core.CertificateStoreManager;
import com.hp.ov.sdk.certs.MessagingCertificateClient;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.core.CaCert;
import com.hp.ov.sdk.messaging.core.RabbitMQClientCert;
import com.hp.ov.sdk.messaging.core.RabbitMqClientConnectionFactory;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ScmbConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScmbConnectionManager.class);

    private final ConcurrentMap<String, ScmbConnection> map = new ConcurrentHashMap<String, ScmbConnection>();
    private final ConcurrentMap<String, ExecutorService> executorMap = new ConcurrentHashMap<String, ExecutorService>();
    private final ConcurrentMap<String, Thread> scmbProcessThreadQueue = new ConcurrentHashMap<String, Thread>();

    private final MessagingCertificateClient messagingCertificate;

    public ScmbConnectionManager(MessagingCertificateClient messagingCertificate) {
        this.messagingCertificate = messagingCertificate;
    }

    public void startScmb(SDKConfiguration config) {
        Connection conn = null;
        Channel channel = null;
        // validate args
        if (null == config) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // check if connection object already exists in the map
        // scmbConnection = map.get(params.getHostname());
        if (!map.containsKey(config.getOneViewHostname())) {
            // get client cert
            final RabbitMQClientCert mqClientCert = messagingCertificate.getRabbitMQClientCertificateKeyPair();
            // get CA cert
            CaCert caCert = messagingCertificate.getCACertificate();
            // get SSLContext
            SSLContext sslContext = CertificateStoreManager.getSslContext(mqClientCert, caCert);
            ConnectionFactory connectionFactory = RabbitMqClientConnectionFactory.getConnectionFactory(sslContext, params);
            connectionFactory.setConnectionTimeout(1000);
            try {
                conn = connectionFactory.newConnection();
                channel = conn.createChannel();

            } catch (final IOException e) {
                LOGGER.error("ScmbConnectionManager : startScmb: IOException");
                throw new SDKScmbConnectionNotFoundException(SDKErrorEnum.scmbConnectionNotFound, null, null, null,
                        SdkConstants.SCMB_CONNECTION, null);
            }

            // put into map
            map.putIfAbsent(config.getOneViewHostname(), new ScmbConnection(conn, channel, ScmbState.START));
        }

    }

    public ExecutorService getExecutor(final String key) {
        ExecutorService executor = executorMap.get(key);

        if (null == executor) {
            executor = Executors.newFixedThreadPool(5);
            executorMap.put(key, executor);
        }
        return executor;
    }

    public Thread createScmbProcessThread(String hostname, final Channel channel,
            final String routingKey, final ScmbMessageExecutionQueue messageQueue) {

        ScmbProcessor scmbProcessorThread = (ScmbProcessor) scmbProcessThreadQueue.putIfAbsent(hostname,
                new ScmbProcessor(channel, routingKey, messageQueue));
        if (scmbProcessorThread == null) {
            scmbProcessorThread = (ScmbProcessor) scmbProcessThreadQueue.get(hostname);
        }

        return scmbProcessorThread;
    }

    public void stopScmb(String hostname) {
        if (map.containsKey(hostname)) {
            if (scmbProcessThreadQueue.containsKey(hostname)) {
                final Thread scmbThread = scmbProcessThreadQueue.get(hostname);
                ((ScmbProcessor) scmbThread).releaseScmbThread();
            }
            removeScmbConnection(hostname);
        }
    }

    public void removeScmbConnection(String hostname) throws SDKInvalidArgumentException,
            SDKScmbConnectionNotFoundException {

        // check if connection object already exists in the map
        final ScmbConnection scmbConnection = map.get(hostname);
        if (null == scmbConnection) {
            throw new SDKScmbConnectionNotFoundException(SDKErrorEnum.scmbConnectionNotFound, null, null, null,
                    SdkConstants.SCMB_CONNECTION, null);
        } else {
            try {
                scmbConnection.getConn().close();
                // got exception below, so commenting below code
                /**
                 * com.rabbitmq.client.AlreadyClosedException: connection is
                 * already closed due to clean connection shutdown; protocol
                 * method: #method<connection.close>(reply-code=200,
                 * reply-text=OK, class-id=0, method-id=0)
                 */
                // scmbConnection.getChannel().close();
                map.remove(hostname);
            } catch (final IOException e) {
                LOGGER.error("ScmbConnectionManager : removeScmbConnection : error in closing connection");
            }
        }
    }

    public void processConsumer(String hostname, final String routingKey, final ScmbMessageExecutionQueue messageQueue) {
        Channel channel = null;

        if (map.containsKey(hostname)) {
            channel = map.get(hostname).getChannel();
        } else {
            throw new SDKScmbConnectionNotFoundException(SDKErrorEnum.scmbConnectionNotFound, null, null, null,
                    SdkConstants.SCMB_CONNECTION, null);
        }

        // create thread
        final ScmbProcessor processor = (ScmbProcessor) createScmbProcessThread(hostname, channel, routingKey, messageQueue);
        // processor.start();
        final ExecutorService executor = getExecutor(hostname);
        executor.execute(processor);
    }

}
