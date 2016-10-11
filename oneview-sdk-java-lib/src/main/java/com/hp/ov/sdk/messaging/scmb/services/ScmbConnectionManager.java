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

import com.hp.ov.sdk.certs.CertificateStoreManager;
import com.hp.ov.sdk.certs.MessagingCertificateClient;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.core.CaCert;
import com.hp.ov.sdk.messaging.core.RabbitMqClientCert;
import com.hp.ov.sdk.messaging.core.RabbitMqClientConnectionFactory;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
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

    public void startScmb(final RestParams params) {
        Connection conn = null;
        Channel channel = null;
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        final String sessionId = params.getSessionId();
        // check if connection object already exists in the map
        // scmbConnection = map.get(params.getHostname());
        if (!map.containsKey(params.getHostname())) {
            // get client cert
            final RabbitMqClientCert mqClientCert = messagingCertificate.getRabbitMqClientCertificateKeyPair();
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
            map.putIfAbsent(params.getHostname(), new ScmbConnection(conn, channel, ScmbState.START));
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

    public Thread createScmbProcessThread(final RestParams params, final Connection conn, final Channel channel,
            final String routingKey, final ScmbMessageExecutionQueue messageQueue) {
        final String key = params.getHostname();

        ScmbProcessor scmbProcessorThread = (ScmbProcessor) scmbProcessThreadQueue.putIfAbsent(key, new ScmbProcessor(params, conn, channel, routingKey, messageQueue));
        if (scmbProcessorThread == null) {
            scmbProcessorThread = (ScmbProcessor) scmbProcessThreadQueue.get(key);
        }

        return scmbProcessorThread;
    }

    public void stopScmb(final RestParams params) {
        if (map.containsKey(params.getHostname())) {
            final String key = params.getHostname();
            if (scmbProcessThreadQueue.containsKey(key)) {
                final Thread scmbThread = scmbProcessThreadQueue.get(key);
                ((ScmbProcessor) scmbThread).releaseScmbThread();
            }
            removeScmbConnection(params);
        }
    }

    public void removeScmbConnection(final RestParams params) throws SDKInvalidArgumentException,
            SDKScmbConnectionNotFoundException {
        // validate params
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // check if connection object already exists in the map
        final ScmbConnection scmbConnection = map.get(params.getHostname());
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
                map.remove(params.getHostname());
            } catch (final IOException e) {
                LOGGER.error("ScmbConnectionManager : removeScmbConnection : error in closing connection");
            }
        }
    }

    public void processConsumer(final RestParams params, final String routingKey, final ScmbMessageExecutionQueue messageQueue) {
        Connection conn = null;
        Channel channel = null;
        // validate params
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        if (map.containsKey(params.getHostname())) {
            conn = map.get(params.getHostname()).getConn();
            channel = map.get(params.getHostname()).getChannel();
        } else {
            throw new SDKScmbConnectionNotFoundException(SDKErrorEnum.scmbConnectionNotFound, null, null, null,
                    SdkConstants.SCMB_CONNECTION, null);
        }

        // create thread
        final ScmbProcessor processor = (ScmbProcessor) createScmbProcessThread(params, conn, channel, routingKey, messageQueue);
        // processor.start();
        final ExecutorService executor = getExecutor(params.getHostname());
        executor.execute(processor);
    }

}
