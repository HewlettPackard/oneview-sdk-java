/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface MessagingCertificateManager
{

    public String generateRabbitMqClientCert(RestParams params,
            RabbitMqClientCert rabbitMqClientCertDto, final boolean aSync,
            final boolean useJsonRequest);

    public RabbitMqClientCert getRabbitMqClientCertificateKeyPair(RestParams params);

    public CaCert getCACertificate(RestParams params);
}
