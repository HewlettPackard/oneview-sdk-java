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
package com.hp.ov.sdk.certs;

import org.apache.commons.lang3.StringUtils;

import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;

public class SslPropertiesManager {

    public void loadSslProperties(HttpSslProperties httpSslProperties) {
        this.setupSslProperty("javax.net.ssl.keyStore", httpSslProperties.getKeyStore());
        this.setupSslProperty("javax.net.ssl.trustStore", httpSslProperties.getTrustStore());
        this.setupSslProperty("javax.net.ssl.trustStorePassword", httpSslProperties.getTrustStorePassword());
        this.setupSslProperty("javax.net.ssl.keyStorePassword", httpSslProperties.getKeyStorePassword());
        this.setupSslProperty("javax.net.ssl.trustStoreType", httpSslProperties.getTrustStoreType());
        this.setupSslProperty("javax.net.ssl.keyStoreType", httpSslProperties.getKeyStoreType());

        System.setProperty("https.protocol", "TLSv1.2");
        //System.setProperty("javax.net.debug", "all");
    }

    private void setupSslProperty(String sslProperty, String sslPropertyValue) {
        if (!StringUtils.isBlank(sslPropertyValue)) {
            System.setProperty(sslProperty, sslPropertyValue);
        }
    }

}
