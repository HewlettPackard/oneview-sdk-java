/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import javax.net.ssl.SSLContext;

import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;

public interface CertificateStoreManager {

    public SSLContext getSslContext(final RabbitMqClientCert rabbitMqClientCert, final CaCert caCert);
}
