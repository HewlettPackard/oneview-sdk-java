/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.util.samples;

import com.hp.ov.sdk.certs.SslPropertiesManager;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class SampleRestParams {

    private SslPropertiesManager util;

    private HttpSslProperties httpSslProperties;

    public RestParams getBasicRestParams() {
        // Sample values - user needs set these values as per his environment

        final RestParams params = new RestParams();
        params.setHostname("10.10.10.10");
        params.setUserName("admin");
        params.setPassword("admin");
        params.setDomain("LOCAL");
        params.setApiVersion(120);

        params.setKeyStoreFile("src/main/resources/KeyStore");
        params.setTrustStoreFile("src/main/resources/TrustStore");

        httpSslProperties = new HttpSslProperties();
        util = new SslPropertiesManager();
        util.setHttpSslProperties(httpSslProperties);

        httpSslProperties = util.getSslProperties("src/main/resources/KeyStore", "src/main/resources/TrustStore");
        util.loadSslProperties(httpSslProperties);

        return params;

    }

}
