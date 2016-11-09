/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.rest.http.core;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.ssl.SSLContextBuilder;

import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;

public class SSLContextFactory {

    public static SSLContext getContext(SDKConfiguration config) {
        if (config.isTrustStoreEnabled()) {
            return getDefaultContext(config);
        }
        return getTrustAllContext();
    }

    /**
     * The default SSLContext implementation.
     * <p>
     *     Creates a SSLContext with a TrustManager implementation for managing the specified <i>TrustStore</i> file.
     *     The information to access the TrustStore file must be provided through the {@link SDKConfiguration} parameter.
     *     These information include the path to the file, its password and the type. Currently, the only type supported
     *     is the default type JKS.
     * </p>
     *
     * @param config SDK configuration containing SSL properties that will be used to create the context.
     *               These properties comprehends the path to the TrustSore file and its password.
     * @return {@link SSLContext}
     */
    private static SSLContext getDefaultContext(SDKConfiguration config) {
        try {
            File trustStore = new File(config.getTrustStoreFile());

            SSLContext context = SSLContextBuilder.create()
                    .loadTrustMaterial(trustStore, config.getTrustStorePassword().toCharArray())
                    .build();

            return context;
        } catch (GeneralSecurityException | IOException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
    }

    /**
     * This SSLContext bypass any server validation which means that no verification is made to check
     * whether the response came from the real server or from a fake one.
     * <p>
     *     <b>This implementation is only intent for testing purposes.
     *     Do not use it in a production environment!</b>
     * </p>
     * @return {@link SSLContext}
     */
    private static SSLContext getTrustAllContext() {
        try {
            SSLContext context = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);

            context.init(null, new TrustManager[]{ new TrustAllX509TrustManager() }, new SecureRandom());

            return context;
        } catch (GeneralSecurityException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
    }

    /**
     * Uses the current JVM SSL context.
     * <br>
     * The context can be configured/modified by setting the following properties:
     * <ul>
     *     <li>javax.net.ssl.trustStore</li>
     *     <li>javax.net.ssl.trustStorePassword</li>
     *     <li>javax.net.ssl.trustStoreType</li>
     * </ul>
     * Below is an example that illustrates how these values can be configured programmatically:
     * <pre>{@code
     *     System.setProperty("javax.net.ssl.trustStore", path_to_file);
     *     System.setProperty("javax.net.ssl.trustStorePassword", password);
     *     System.setProperty("javax.net.ssl.trustStoreType", type);
     *     System.setProperty("https.protocol", "TLSv1.2");
     *     }
     * </pre>
     *
     * Be aware that once these values have been modified, the JVM will use them to verify
     * connections against any HTTPS (or other SSL connection). Thus, their modification can
     * cause issues when using the SDK within a application which also connects to others
     * SSL enabled services.
     * <br>
     * If a trust store location is not specified using this properties,
     * the SunJSSE implementation searches for and uses a keystore file in the
     * following locations (in order):
     * <ul>
     *     <li>$JAVA_HOME/lib/security/jssecacerts</li>
     *     <li>$JAVA_HOME/lib/security/cacerts</li>
     * </ul>
     *
     * @return {@link SSLContext}
     */
    private static SSLContext getAvailableContext() {
        try {
            SSLContext context = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);

            context.init(null, null, new SecureRandom());

            return context;
        } catch (GeneralSecurityException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
    }

    /**
     * Simple implementation of X509TrustManager that trusts all certificates.
     * <br>
     * <b>Do not use this class in a production environment!</b>
     */
    private static class TrustAllX509TrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { }
    }
}
