/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.certs.CertificateStoreManager;
import com.hp.ov.sdk.certs.MessagingCertificateManager;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messagiing.core.RabbitMqClientConnectionFactory;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class ScmbConnectionManagerImpl implements ScmbConnectionManager
{

    private static final Logger logger = LoggerFactory
            .getLogger(ScmbConnectionManagerImpl.class);
    public final ConcurrentMap<String, ScmbConnection> map = new ConcurrentHashMap<String, ScmbConnection>();
    public final ConcurrentMap<String, ExecutorService> executorMap = new ConcurrentHashMap<String, ExecutorService>();
    public final ConcurrentMap<String, Thread> scmbProcessThreadQueue = new ConcurrentHashMap<String, Thread>();
    private SSLContext sslContext;

    @Autowired
    private RabbitMqClientConnectionFactory factory;

    @Autowired
    private MessagingCertificateManager scmbCertificate;

    @Autowired
    private CertificateStoreManager certStoreManager;

    @Autowired
    private CaCert caCert;

    private ConnectionFactory connectionFactory;

    @Autowired
    private ScmbConnection scmbConnection;

    @Override
    public void startScmb(final RestParams params)
    {
        Connection conn = null;
        Channel channel = null;
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        final String sessionId = params.getSessionId();
        // check if connection object already exists in the map
        // scmbConnection = map.get(params.getHostname());
        if (!map.containsKey(params.getHostname()))
        {
            // get client cert
            final RabbitMqClientCert mqClientCert = scmbCertificate
                    .getRabbitMqClientCertificateKeyPair(params);
            // get CA cert
            caCert = scmbCertificate.getCACertificate(params);
            // get SSLContext
            sslContext = certStoreManager.getSslContext(mqClientCert, caCert);
            connectionFactory = factory
                    .getConnectionFactory(sslContext, params);
            connectionFactory.setConnectionTimeout(1000);
            // TODO - exception
            try
            {
                conn = connectionFactory.newConnection();
                channel = conn.createChannel();
                // TODO - temp Solution

            }
            catch (final IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            scmbConnection.setConn(conn);
            scmbConnection.setChannel(channel);
            scmbConnection.setState(ScmbState.START);
            // put into map
            synchronized (map)
            {
                map.putIfAbsent(params.getHostname(), scmbConnection);
            }
        }

    }

    public ExecutorService getExecutor(final String key)
    {
        ExecutorService executor = executorMap.get(key);

        if (null == executor)
        {
            executor = Executors.newFixedThreadPool(5);
            executorMap.putIfAbsent(key, executor);
        }
        return executor;
    }

    public Thread createScmbProcessThread(final RestParams params,
            final Connection conn, final Channel channel,
            final String routingKey,
            final ScmbMessageExecutionQueue messageQueue)
    {
        final String key = params.getHostname();
        if (!scmbProcessThreadQueue.containsKey(key))
        {
            synchronized (scmbProcessThreadQueue)
            {
                scmbProcessThreadQueue.put(key, new ScmbProcessor(params, conn,
                        channel, routingKey, messageQueue));
            }
        }
        final ScmbProcessor scmbProcessorThread = getScmbProcessorThread(key);

        return scmbProcessorThread;
    }

    private ScmbProcessor getScmbProcessorThread(final String key)
    {
        final ScmbProcessor scmbProcessorThread = (ScmbProcessor) scmbProcessThreadQueue
                .get(key);

        return scmbProcessorThread;
    }

    @Override
    public void stopScmb(final RestParams params)
    {
        if (map.containsKey(params.getHostname()))
        {
            final String key = params.getHostname();
            if (scmbProcessThreadQueue.containsKey(key))
            {
                final Thread scmbThread = scmbProcessThreadQueue.get(key);
                ((ScmbProcessor) scmbThread).releaseScmbThread();
            }
            removeScmbConnection(params);
        }
    }

    // TODO - add proper exception
    @Override
    public void removeScmbConnection(final RestParams params)
            throws SDKInvalidArgumentException,
            SDKScmbConnectionNotFoundException
    {
        // validate params
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // check if connection object already exists in the map
        final ScmbConnection scmbConnection = map.get(params.getHostname());
        if (null == scmbConnection)
        {
            throw new SDKScmbConnectionNotFoundException(
                    SDKErrorEnum.scmbConnectionNotFound, null, null, null,
                    SdkConstants.SCMB_CONNECTION, null);
        }
        else
        {
            // TODO - exception
            try
            {
                scmbConnection.getConn().close();
                // TODO - got below exception, so commenting below code
                /**
                 * com.rabbitmq.client.AlreadyClosedException: connection is
                 * already closed due to clean connection shutdown; protocol
                 * method: #method<connection.close>(reply-code=200,
                 * reply-text=OK, class-id=0, method-id=0)
                 */
                // scmbConnection.getChannel().close();
                synchronized (map)
                {
                    map.remove(params.getHostname());
                }
            }
            catch (final IOException e)
            {
                // TODO Auto-generated catch block
                logger.error("ScmbConnectionManagerImpl : removeScmbConnection : error in closing connection");
            }
        }
    }

    @Override
    public void processConsumer(final RestParams params,
            final String routingKey,
            final ScmbMessageExecutionQueue messageQueue)
    {
        // TODO Auto-generated method stub
        Connection conn = null;
        Channel channel = null;
        // validate params
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        if (map.containsKey(params.getHostname()))
        {
            conn = map.get(params.getHostname()).getConn();
            channel = map.get(params.getHostname()).getChannel();
        }
        else
        {
            throw new SDKScmbConnectionNotFoundException(
                    SDKErrorEnum.scmbConnectionNotFound, null, null, null,
                    SdkConstants.SCMB_CONNECTION, null);
        }

        // create thread
        final ScmbProcessor processor = (ScmbProcessor) createScmbProcessThread(
                params, conn, channel, routingKey, messageQueue);
        // processor.start();
        final ExecutorService executor = getExecutor(params.getHostname());
        executor.execute(processor);

    }
}
