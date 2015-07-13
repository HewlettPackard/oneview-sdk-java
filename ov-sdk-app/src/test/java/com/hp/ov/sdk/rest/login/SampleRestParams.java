/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.certs.SslPropertiesManager;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

@Component
public class SampleRestParams
{

    @Autowired
    private HttpSslProperties props;

    @Autowired
    private SslPropertiesManager util;

    public RestParams getBasicRestParams()
    {
        // Sample values - user needs set these values as per his environment

        RestParams params = new RestParams();
        params.setHostname("15.199.201.9");
        params.setUserName("administrator");
        params.setPassword("hpvse123");
        params.setDomain("LOCAL");
        params.setApiVersion(120);

        params.setKeyStoreFile("src/main/resources/KeyStore");
        params.setTrustStoreFile("src/main/resources/TrustStore");

        props = util.getSslProperties("src/main/resources/KeyStore",
                "src/main/resources/TrustStore");
        util.loadSslProperties(props);

        return params;

    }
}
