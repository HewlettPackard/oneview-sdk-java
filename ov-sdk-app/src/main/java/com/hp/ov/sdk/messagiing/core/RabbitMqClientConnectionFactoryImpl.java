/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.messagiing.core;

import javax.net.ssl.SSLContext;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultSaslConfig;

@Component
public class RabbitMqClientConnectionFactoryImpl implements RabbitMqClientConnectionFactory
{

    @Override
    public ConnectionFactory getConnectionFactory(final SSLContext sslContext, final RestParams params)
    {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(params.getHostname());
        factory.setPort(params.getAmqpPort());

        //Set Auth mechanism to "EXTERNAL" so that commonName of the client certificate is mapped to AMQP user name. Hence, No need to set userId/Password here. 
        factory.setSaslConfig(DefaultSaslConfig.EXTERNAL);
        factory.useSslProtocol(sslContext);
        factory.setAutomaticRecoveryEnabled(true);

        return factory;
    }

}
