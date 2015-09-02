/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;

public class SslPropertiesManager {

    private HttpSslProperties httpSslProperties;

    public HttpSslProperties getHttpSslProperties() {
        return httpSslProperties;
    }

    public void setHttpSslProperties(final HttpSslProperties httpSslProperties) {
        this.httpSslProperties = httpSslProperties;
    }

    // TODO- Refactor incase of param passing.
    public HttpSslProperties getSslProperties(final String keyStoreFile, final String trustStoreFile,
            final String keyStorePassword, final String trustStorePassword, final String keyStoreType, final String trustStoreType) {

        httpSslProperties.setKeyStore(keyStoreFile);
        httpSslProperties.setTrustStore(trustStoreFile);
        httpSslProperties.setKeyStorePassword(keyStorePassword);
        httpSslProperties.setTrustStorePassword(trustStorePassword);
        httpSslProperties.setKeyStoreType(keyStoreType);
        httpSslProperties.setTrustStoreType(trustStoreType);

        return httpSslProperties;
    }

    public void loadSslProperties(final HttpSslProperties httpSslProperties) {

        System.setProperty("javax.net.ssl.keyStore", httpSslProperties.getKeyStore());
        System.setProperty("javax.net.ssl.trustStore", httpSslProperties.getTrustStore());
        System.setProperty("javax.net.ssl.trustStorePassword", httpSslProperties.getTrustStorePassword());
        System.setProperty("javax.net.ssl.keyStorePassword", httpSslProperties.getKeyStorePassword());
        System.setProperty("javax.net.ssl.trustStoreType", httpSslProperties.getTrustStoreType());
        System.setProperty("javax.net.ssl.keyStoreType", httpSslProperties.getKeyStoreType());
        // TODO - verify it later
        System.setProperty("https.protocol", "TLSv1.2");
        // System.setProperty("javax.net.debug", "all");
    }

}
