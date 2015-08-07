/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.exceptions.SDKCertificateException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Component
public class CertificateStoreManagerImpl implements CertificateStoreManager {

    private SSLContext sslContext;

    @Override
    public SSLContext getSslContext(final RabbitMqClientCert rabbitMqClientCert, final CaCert caCert) {

        // Now initialize SSLContext with KeyStore and TrustStore.
        try {
            sslContext = SSLContext.getInstance(SdkConstants.SSL_VERSION);
            sslContext.init(getInitiazedKeyManager(rabbitMqClientCert).getKeyManagers(),
                    getInitiazedTrustManager(caCert.getCaCert()).getTrustManagers(), new SecureRandom());

        } catch (final NoSuchAlgorithmException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final KeyManagementException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        }

        return sslContext;

    }

    // TODO - exception
    private KeyManagerFactory getInitiazedKeyManager(final RabbitMqClientCert certDto) {
        KeyManagerFactory kmf = null;
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try {
            // Read client certificate and private key.
            final byte[] encoded = Base64.decode(certDto.getBase64SSLKeyData());

            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            final KeyFactory kf = KeyFactory.getInstance("RSA");
            final PrivateKey privateKey = kf.generatePrivate(keySpec);

            final String strClientCert = certDto.getBase64SSLCertData();
            final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            final Certificate cert = certFactory.generateCertificate(new ByteArrayInputStream(strClientCert
                    .getBytes(StandardCharsets.UTF_8)));

            // Add both client cert and private key to the keyStore.
            final KeyStore ks = KeyStore.getInstance("jks");
            ks.load(null, "password".toCharArray());
            ks.setEntry("rabbitmq-client", new KeyStore.PrivateKeyEntry(privateKey, new Certificate[] { cert }),
                    new KeyStore.PasswordProtection("password".toCharArray()));

            kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, "password".toCharArray());
            // TODO - add proper error key
        } catch (final CertificateException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final KeyStoreException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final NoSuchAlgorithmException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final IOException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final UnrecoverableKeyException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final InvalidKeySpecException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        }

        return kmf;
    }

    // TODO - exception
    private TrustManagerFactory getInitiazedTrustManager(final String caCert) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        TrustManagerFactory tmf = null;
        try {
            final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

            // Add CA certificate to TrustStore.
            final KeyStore tks = KeyStore.getInstance("jks");
            tks.load(null, "password".toCharArray());
            final Certificate caCErt = certFactory.generateCertificate(new ByteArrayInputStream(caCert
                    .getBytes(StandardCharsets.UTF_8)));

            tks.setEntry("ca-cert", new KeyStore.TrustedCertificateEntry(caCErt), null);
            tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(tks);
        } catch (final CertificateException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final KeyStoreException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final NoSuchAlgorithmException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        } catch (final IOException e) {
            throw new SDKCertificateException(SDKErrorEnum.certificateError, null, null, null, SdkConstants.CERTS, null);
        }

        return tmf;
    }

}
