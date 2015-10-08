/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.util.samples;

import com.hp.ov.sdk.certs.SslPropertiesManager;
import com.hp.ov.sdk.constants.samples.SamplesConstants;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class SampleRestParams {

    private SslPropertiesManager util;

    private HttpSslProperties httpSslProperties;

    private static volatile SampleRestParams sample = null;

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

    public static SampleRestParams getInstance() {
        if (sample == null) {
            synchronized (SampleRestParams.class) {
                if (sample == null) {
                    sample = new SampleRestParams();
                }
            }
        }
        return sample;
    }
}
