/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.util.samples;

import com.hp.ov.sdk.SamplesConstants;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class HPOneViewCredential {

    @Deprecated
    public static RestParams createCredentials() {
        return createRestParams();
    }

    public static RestParams createRestParams() {
        RestParams params = new RestParams();

        params.setHostname(SamplesConstants.HOSTNAME);
        params.setUserName(SamplesConstants.USERNAME);
        params.setPassword(SamplesConstants.PASSWORD);
        params.setApiVersion(SamplesConstants.VERSION);
        params.setDomain(SamplesConstants.DOMAIN);

        return params;
    }

    public static HttpSslProperties createHttpSslProperties() {
        HttpSslProperties httpSslProperties = new HttpSslProperties();

        httpSslProperties.setKeyStore(SamplesConstants.KEY_STORE_FILE);
        httpSslProperties.setKeyStorePassword(SamplesConstants.KEY_STORE_PASSWORD);
        httpSslProperties.setKeyStoreType(SamplesConstants.KEY_STORE_TYPE);

        httpSslProperties.setTrustStore(SamplesConstants.TRUST_STORE_FILE);
        httpSslProperties.setTrustStorePassword(SamplesConstants.TRUST_STORE_PASSWORD);
        httpSslProperties.setTrustStoreType(SamplesConstants.TRUST_STORE_TYPE);

        return httpSslProperties;
    }
}
