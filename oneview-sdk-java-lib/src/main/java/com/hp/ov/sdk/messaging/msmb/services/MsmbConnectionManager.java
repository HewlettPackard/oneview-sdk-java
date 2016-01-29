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
package com.hp.ov.sdk.messaging.msmb.services;

import com.hp.ov.sdk.certs.CertificateStoreManager;
import com.hp.ov.sdk.certs.MessagingCertificateManager;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.core.RabbitMqClientConnectionFactory;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MsmbConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsmbConnectionManager.class);

    public final ConcurrentMap<String, MsmbConnection> map = new ConcurrentHashMap<String, MsmbConnection>();
    public final ConcurrentMap<String, ExecutorService> executorMap = new ConcurrentHashMap<String, ExecutorService>();
    public final ConcurrentMap<String, Thread> msmbProcessThreadQueue = new ConcurrentHashMap<String, Thread>();

    private final MessagingCertificateManager messagingCertificate;

    public MsmbConnectionManager(MessagingCertificateManager messagingCertificate) {
        this.messagingCertificate = messagingCertificate;
    }

    public void startMsmb(final RestParams params) {
        Connection conn = null;
        Channel channel = null;
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // check if connection object already exists in the map
        // msmbConnection = map.get(params.getHostname());
        if (!map.containsKey(params.getHostname())) {
            // get client cert
            final RabbitMqClientCert mqClientCert = messagingCertificate.getRabbitMqClientCertificateKeyPair(params);
            // get CA cert
            CaCert caCert = messagingCertificate.getCACertificate(params);
            // get SSLContext
            SSLContext sslContext = CertificateStoreManager.getSslContext(mqClientCert, caCert);
            ConnectionFactory connectionFactory = RabbitMqClientConnectionFactory.getConnectionFactory(sslContext, params);
            connectionFactory.setConnectionTimeout(1000);
            // TODO - exception
            try {
                conn = connectionFactory.newConnection();
                channel = conn.createChannel();
                // TODO - temp Solution

            } catch (final IOException e) {
                // TODO Auto-generated catch block
                throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.APPLIANCE,
                        null);
            }
            // put into map
            synchronized (map) {
                map.putIfAbsent(params.getHostname(), new MsmbConnection(conn, channel, MsmbState.START));
            }
        }

    }

    public ExecutorService getExecutor(final String key) {
        ExecutorService executor = executorMap.get(key);

        if (null == executor) {
            executor = Executors.newFixedThreadPool(5);
            executorMap.putIfAbsent(key, executor);
        }
        return executor;
    }

    public Thread createMsmbProcessThread(final RestParams params, final Connection conn, final Channel channel,
            final String routingKey, final MsmbMessageExecutionQueue messageQueue) {
        final String key = params.getHostname();
        if (!msmbProcessThreadQueue.containsKey(key)) {
            synchronized (msmbProcessThreadQueue) {
                msmbProcessThreadQueue.put(key, new MsmbProcessor(params, conn, channel, routingKey, messageQueue));
            }
        }
        final MsmbProcessor msmbProcessorThread = getMsmbProcessorThread(key);

        return msmbProcessorThread;
    }

    private MsmbProcessor getMsmbProcessorThread(final String key) {
        final MsmbProcessor msmbProcessorThread = (MsmbProcessor) msmbProcessThreadQueue.get(key);

        return msmbProcessorThread;
    }

    public void stopMsmb(final RestParams params) {
        if (map.containsKey(params.getHostname())) {
            final String key = params.getHostname();
            if (msmbProcessThreadQueue.containsKey(key)) {
                final Thread msmbThread = msmbProcessThreadQueue.get(key);
                ((MsmbProcessor) msmbThread).releaseScmbThread();
            }
            removeMsmbConnection(params);
        }
    }

    // TODO - add proper exception
    public void removeMsmbConnection(final RestParams params) throws SDKInvalidArgumentException,
            SDKScmbConnectionNotFoundException {
        // validate params
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // check if connection object already exists in the map
        final MsmbConnection msmbConnection = map.get(params.getHostname());
        if (null == msmbConnection) {
            //FIXME change scmbConnectionNotFound - it must be msmb connection
            throw new SDKScmbConnectionNotFoundException(SDKErrorEnum.scmbConnectionNotFound, null, null, null,
                    SdkConstants.SCMB_CONNECTION, null);
        } else {
            // TODO - exception
            try {
                msmbConnection.getConn().close();
                // TODO - got below exception, so commenting below code
                /**
                 * com.rabbitmq.client.AlreadyClosedException: connection is
                 * already closed due to clean connection shutdown; protocol
                 * method: #method<connection.close>(reply-code=200,
                 * reply-text=OK, class-id=0, method-id=0)
                 */
                // scmbConnection.getChannel().close();
                synchronized (map) {
                    map.remove(params.getHostname());
                }
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                LOGGER.error("MsmbConnectionManager : removeMsmbConnection : error in closing connection");
            }
        }
    }

    public void processConsumer(final RestParams params, final String routingKey, final MsmbMessageExecutionQueue messageQueue) {
        // TODO Auto-generated method stub
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
        final MsmbProcessor processor = (MsmbProcessor) createMsmbProcessThread(params, conn, channel, routingKey, messageQueue);
        // processor.start();
        final ExecutorService executor = getExecutor(params.getHostname());
        executor.execute(processor);

    }
}
