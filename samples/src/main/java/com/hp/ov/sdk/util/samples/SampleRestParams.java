/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.util.samples;

import com.hp.ov.sdk.certs.SslPropertiesManager;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class SampleRestParams {

    // @Autowired
    // private HttpSslProperties props;

    // @Autowired
    private SslPropertiesManager util;

    private HttpSslProperties httpSslProperties;

    public RestParams getBasicRestParams() {
        // Sample values - user needs set these values as per his environment

        final RestParams params = new RestParams();
        params.setHostname("15.199.201.9");
        params.setUserName("administrator");
        params.setPassword("hpvse123");
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
