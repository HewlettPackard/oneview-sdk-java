/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;

@Component
public class SslPropertiesManager
{

    private static final String DEFAULT_PASSWORD = "changeit";
    private static final String STORE_TYPE = "jks";

    @Autowired
    private HttpSslProperties props;

    public HttpSslProperties getSslProperties(final String keyStoreFile, final String trustStoreFile)
    {

        props.setKeyStore(keyStoreFile);
        props.setTrustStore(trustStoreFile);
        props.setKeyStorePassword(DEFAULT_PASSWORD);
        props.setTrustStorePassword(DEFAULT_PASSWORD);
        props.setKeyStoreType(STORE_TYPE);
        props.setTrustStoreType(STORE_TYPE);

        return props;
    }

    public void loadSslProperties(final HttpSslProperties props)
    {

        System.setProperty("javax.net.ssl.keyStore", props.getKeyStore());
        System.setProperty("javax.net.ssl.trustStore", props.getTrustStore());
        System.setProperty("javax.net.ssl.trustStorePassword", props.getTrustStorePassword());
        System.setProperty("javax.net.ssl.keyStorePassword", props.getKeyStorePassword());
        System.setProperty("javax.net.ssl.trustStoreType", props.getTrustStoreType());
        System.setProperty("javax.net.ssl.keyStoreType", props.getKeyStoreType());
        //TODO - verify it later
        System.setProperty("https.protocol", "TLSv1.2");
        //System.setProperty("javax.net.debug", "all");
    }

    public HttpSslProperties getProps()
    {
        return props;
    }

    public void setProps(HttpSslProperties props)
    {
        this.props = props;
    }

}
