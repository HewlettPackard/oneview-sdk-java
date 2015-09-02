/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.util.samples;

import com.hp.ov.sdk.certs.SslPropertiesManager;
import com.hp.ov.sdk.constants.samples.SamplesConstants;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class SampleRestParams {

    private SslPropertiesManager util;

    private HttpSslProperties httpSslProperties;

    public RestParams getBasicRestParams() {
        // Sample values - user needs set these values as per his environment

        final RestParams params = new RestParams();
        params.setHostname(SamplesConstants.HOSTNAME);
        params.setUserName(SamplesConstants.USERNAME);
        params.setPassword(SamplesConstants.PASSWORD);
        params.setDomain(SamplesConstants.DOMAIN);
        params.setApiVersion(SamplesConstants.VERSION);

        httpSslProperties = new HttpSslProperties();
        util = new SslPropertiesManager();
        util.setHttpSslProperties(httpSslProperties);

        httpSslProperties = util.getSslProperties(SamplesConstants.KEY_STORE_FILE, SamplesConstants.TRUST_STORE_FILE,
                SamplesConstants.KEY_STORE_PASSWORD, SamplesConstants.TRUST_STORE_PASSWORD, SamplesConstants.KEY_STORE_TYPE,
                SamplesConstants.TRUST_STORE_TYPE);

        util.loadSslProperties(httpSslProperties);

        return params;

    }

}
