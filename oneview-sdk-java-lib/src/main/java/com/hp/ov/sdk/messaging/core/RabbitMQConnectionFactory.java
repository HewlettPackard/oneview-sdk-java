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

package com.hp.ov.sdk.messaging.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.common.io.BaseEncoding;
import com.hp.ov.sdk.certs.MessagingCertificateClient;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.exceptions.SDKCertificateException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultSaslConfig;

class RabbitMQConnectionFactory {

    private final SDKConfiguration config;
    private final MessagingCertificateClient messagingCertificate;

    RabbitMQConnectionFactory(SDKConfiguration config, MessagingCertificateClient messagingCertificate) {
        this.config = config;
        this.messagingCertificate = messagingCertificate;
    }

    Connection getConnection() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(config.getOneViewHostname());
        factory.setPort(Integer.parseInt(config.getRabbitMQPort()));

        //Set Auth mechanism to "EXTERNAL" so that commonName of the client certificate
        //is mapped to AMQP user name. Hence, No need to set userId/password here.
        factory.setSaslConfig(DefaultSaslConfig.EXTERNAL);
        factory.useSslProtocol(this.createContext());
        factory.setAutomaticRecoveryEnabled(true);

        return factory.newConnection();
    }

    private SSLContext createContext() {
        RabbitMQClientCert rabbitMQClientCert = messagingCertificate.getRabbitMQClientCertificateKeyPair();
        CaCert caCert = messagingCertificate.getCACertificate();

        return createSSLContext(rabbitMQClientCert, caCert);
    }

    private SSLContext createSSLContext(RabbitMQClientCert rabbitMQClientCert, CaCert caCert) {
        // Now initialize SSLContext with KeyStore and TrustStore.
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance(SdkConstants.SSL_VERSION);

            sslContext.init(
                    buildKeyManager(rabbitMQClientCert).getKeyManagers(),
                    buildTrustManager(caCert.getCaCert()).getTrustManagers(),
                    new SecureRandom());
        } catch (final NoSuchAlgorithmException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError,
                    null, null, null, SdkConstants.CERTS, e);
        } catch (final KeyManagementException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError,
                    null, null, null, SdkConstants.CERTS, e);
        }
        return sslContext;
    }

    private KeyManagerFactory buildKeyManager(RabbitMQClientCert rabbitMQClientCert) {
        KeyManagerFactory kmf;

        Security.addProvider(new BouncyCastleProvider());

        try {
            // Read client certificate and private key.
            byte[] encoded = BaseEncoding.base64()
                    .withSeparator("\n", 65)
                    .decode(rabbitMQClientCert.getBase64SSLKeyData());

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(keySpec);

            String strClientCert = rabbitMQClientCert.getBase64SSLCertData();
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            Certificate cert = certFactory.generateCertificate(
                    new ByteArrayInputStream(strClientCert.getBytes(StandardCharsets.UTF_8)));

            // Add both client cert and private key to the keyStore.
            KeyStore ks = KeyStore.getInstance("jks");
            ks.load(null, "password".toCharArray());
            ks.setEntry("rabbitmq-client",
                    new KeyStore.PrivateKeyEntry(privateKey, new Certificate[] {cert}),
                    new KeyStore.PasswordProtection("password".toCharArray()));

            kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, "password".toCharArray());
        } catch (GeneralSecurityException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError,
                    null, null, null, SdkConstants.CERTS, e);
        } catch (IOException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError,
                    null, null, null, SdkConstants.CERTS, e);
        }
        return kmf;
    }

    private TrustManagerFactory buildTrustManager(String caCert) {
        TrustManagerFactory tmf;

        Security.addProvider(new BouncyCastleProvider());

        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

            // Add CA certificate to TrustStore.
            KeyStore tks = KeyStore.getInstance("jks");
            tks.load(null, "password".toCharArray());
            Certificate certificate = certFactory.generateCertificate(
                    new ByteArrayInputStream(caCert.getBytes(StandardCharsets.UTF_8)));

            tks.setEntry("ca-cert", new KeyStore.TrustedCertificateEntry(certificate), null);
            tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(tks);
        } catch (GeneralSecurityException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError,
                    null, null, null, SdkConstants.CERTS, e);
        } catch (IOException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError,
                    null, null, null, SdkConstants.CERTS, e);
        }
        return tmf;
    }

}
