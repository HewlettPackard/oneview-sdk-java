/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;

public class SslPropertiesManager {

    private HttpSslProperties httpSslProperties = new HttpSslProperties();

    public HttpSslProperties getHttpSslProperties() {
        return httpSslProperties;
    }

    public void setHttpSslProperties(final HttpSslProperties httpSslProperties) {
        if (httpSslProperties == null) {
            throw new IllegalArgumentException("Can not be null");
        }
        this.httpSslProperties = httpSslProperties;
    }

    // TODO- Refactor incase of param passing.
    public HttpSslProperties getSslProperties(final String keyStoreFile, final String trustStoreFile) {
        return getSslProperties(keyStoreFile, trustStoreFile, HttpSslProperties.DEFAULT_PASSWORD,
                HttpSslProperties.DEFAULT_PASSWORD);
    }

    public HttpSslProperties getSslProperties(final String keyStoreFile, final String trustStoreFile,
            final String keyStorePassword, final String trustStorePassword) {
        return getSslProperties(keyStoreFile, trustStoreFile, keyStorePassword, trustStorePassword, HttpSslProperties.DEFAULT_TYPE,
                HttpSslProperties.DEFAULT_TYPE);
    }

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

        if (httpSslProperties.getKeyStore() != null && !httpSslProperties.getKeyStore().trim().equals("")) {
            System.setProperty("javax.net.ssl.keyStore", httpSslProperties.getKeyStore());
        }
        if (httpSslProperties.getTrustStore() != null && !httpSslProperties.getTrustStore().trim().equals("")) {
            System.setProperty("javax.net.ssl.trustStore", httpSslProperties.getTrustStore());
        }
        if (httpSslProperties.getTrustStorePassword() != null && !httpSslProperties.getTrustStorePassword().trim().equals("")) {
            System.setProperty("javax.net.ssl.trustStorePassword", httpSslProperties.getTrustStorePassword());
        }
        if (httpSslProperties.getKeyStorePassword() != null && !httpSslProperties.getKeyStorePassword().trim().equals("")) {
            System.setProperty("javax.net.ssl.keyStorePassword", httpSslProperties.getKeyStorePassword());
        }
        if (httpSslProperties.getTrustStoreType() != null && !httpSslProperties.getTrustStoreType().trim().equals("")) {
            System.setProperty("javax.net.ssl.trustStoreType", httpSslProperties.getTrustStoreType());
        }
        if (httpSslProperties.getKeyStoreType() != null && !httpSslProperties.getKeyStoreType().trim().equals("")) {
            System.setProperty("javax.net.ssl.keyStoreType", httpSslProperties.getKeyStoreType());
        }
        // TODO - verify it later
        System.setProperty("https.protocol", "TLSv1.2");
        // System.setProperty("javax.net.debug", "all");
    }

}
