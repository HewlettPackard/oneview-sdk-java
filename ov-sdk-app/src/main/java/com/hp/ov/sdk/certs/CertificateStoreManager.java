/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import javax.net.ssl.SSLContext;

import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;

public interface CertificateStoreManager
{

    public SSLContext getSslContext(final RabbitMqClientCert rabbitMqClientCert, final CaCert caCert);
}
