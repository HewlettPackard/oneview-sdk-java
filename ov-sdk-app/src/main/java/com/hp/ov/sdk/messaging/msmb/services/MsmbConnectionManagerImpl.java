/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messaging.msmb.services;

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
public class MsmbConnectionManagerImpl implements MsmbConnectionManager
{

    private static final Logger logger = LoggerFactory
            .getLogger(MsmbConnectionManagerImpl.class);
    public final ConcurrentMap<String, MsmbConnection> map = new ConcurrentHashMap<String, MsmbConnection>();
    public final ConcurrentMap<String, ExecutorService> executorMap = new ConcurrentHashMap<String, ExecutorService>();
    public final ConcurrentMap<String, Thread> scmbProcessThreadQueue = new ConcurrentHashMap<String, Thread>();
    private SSLContext sslContext;

    @Autowired
    private RabbitMqClientConnectionFactory factory;

    @Autowired
    private MessagingCertificateManager messagingertificate;

    @Autowired
    private CertificateStoreManager certStoreManager;

    @Autowired
    private CaCert caCert;

    private ConnectionFactory connectionFactory;

    @Autowired
    private MsmbConnection msmbConnection;

    @Autowired
    private MsmbAlertsHandler handler;

    @Override
    public void startMsmb(final RestParams params)
    {
        Connection conn = null;
        Channel channel = null;
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // check if connection object already exists in the map
        // scmbConnection = map.get(params.getHostname());
        if (!map.containsKey(params.getHostname()))
        {
            // get client cert
            RabbitMqClientCert mqClientCert = messagingertificate
                    .getRabbitMqClientCertificateKeyPair(params);
            // get CA cert
            caCert = messagingertificate.getCACertificate(params);
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
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            msmbConnection.setConn(conn);
            msmbConnection.setChannel(channel);
            msmbConnection.setState(MsmbState.START);
            // put into map
            synchronized (map)
            {
                map.putIfAbsent(params.getHostname(), msmbConnection);
            }
        }

    }

    public ExecutorService getExecutor(String key)
    {
        ExecutorService executor = executorMap.get(key);

        if (null == executor)
        {
            executor = Executors.newFixedThreadPool(5);
            executorMap.putIfAbsent(key, executor);
        }
        return executor;
    }

    public Thread createMsmbProcessThread(final RestParams params,
            final Connection conn, final Channel channel,
            final String routingKey,
            final MsmbMessageExecutionQueue messageQueue)
    {
        String key = params.getHostname();
        if (!scmbProcessThreadQueue.containsKey(key))
        {
            synchronized (scmbProcessThreadQueue)
            {
                scmbProcessThreadQueue.put(key, new MsmbProcessor(params, conn,
                        channel, routingKey, messageQueue));
            }
        }
        MsmbProcessor scmbProcessorThread = getMsmbProcessorThread(key);

        return scmbProcessorThread;
    }

    private MsmbProcessor getMsmbProcessorThread(final String key)
    {
        MsmbProcessor scmbProcessorThread = (MsmbProcessor) scmbProcessThreadQueue
                .get(key);

        return scmbProcessorThread;
    }

    @Override
    public void stopMsmb(final RestParams params)
    {
        if (map.containsKey(params.getHostname()))
        {
            String key = params.getHostname();
            if (scmbProcessThreadQueue.containsKey(key))
            {
                Thread scmbThread = scmbProcessThreadQueue.get(key);
                ((MsmbProcessor) scmbThread).releaseScmbThread();
            }
            removeMsmbConnection(params);
        }
    }

    // TODO - add proper exception
    public void removeMsmbConnection(final RestParams params)
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
        MsmbConnection msmbConnection = map.get(params.getHostname());
        if (null == msmbConnection)
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
                msmbConnection.getConn().close();
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
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                logger.error("ScmbConnectionManagerImpl : removeScmbConnection : error in closing connection");
            }
        }
    }

    @Override
    public void processConsumer(final RestParams params,
            final String routingKey,
            final MsmbMessageExecutionQueue messageQueue)
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
        MsmbProcessor processor = (MsmbProcessor) createMsmbProcessThread(
                params, conn, channel, routingKey, messageQueue);
        // processor.start();
        ExecutorService executor = getExecutor(params.getHostname());
        executor.execute(processor);

    }
}
