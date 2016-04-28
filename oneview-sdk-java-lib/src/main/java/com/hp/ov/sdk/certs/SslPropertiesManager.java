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
package com.hp.ov.sdk.certs;

import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;

public class SslPropertiesManager {

    private HttpSslProperties httpSslProperties = new HttpSslProperties();

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

        System.setProperty("https.protocol", "TLSv1.2");
        // System.setProperty("javax.net.debug", "all");
    }

}
