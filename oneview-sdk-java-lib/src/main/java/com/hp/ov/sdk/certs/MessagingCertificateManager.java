/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface MessagingCertificateManager {

    public String generateRabbitMqClientCert(RestParams params, RabbitMqClientCert rabbitMqClientCertDto, final boolean aSync,
            final boolean useJsonRequest);

    public RabbitMqClientCert getRabbitMqClientCertificateKeyPair(RestParams params);

    public CaCert getCACertificate(RestParams params);
}
