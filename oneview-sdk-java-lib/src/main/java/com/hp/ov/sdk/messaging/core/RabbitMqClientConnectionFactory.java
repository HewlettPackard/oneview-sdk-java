/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.messaging.core;

import javax.net.ssl.SSLContext;

import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.rabbitmq.client.ConnectionFactory;

public interface RabbitMqClientConnectionFactory {

    public ConnectionFactory getConnectionFactory(final SSLContext sslContext, final RestParams params);

}
