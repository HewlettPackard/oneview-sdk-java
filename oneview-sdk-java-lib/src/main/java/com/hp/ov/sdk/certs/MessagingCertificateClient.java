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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.messaging.core.CaCert;
import com.hp.ov.sdk.messaging.core.RabbitMqClientCert;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.client.Request;

public class MessagingCertificateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingCertificateClient.class);

    private static final String RABBIT_MQ_CLIENT_CERT_KEYPAIR = "/rest/certificates/client/rabbitmq/keypair/default";
    private static final String CA_CERT_URI = "/rest/certificates/ca";
    private static final String RABBIT_MQ_CLIENT_CERT = "/rest/certificates/client/rabbitmq";

    private final BaseClient baseClient;

    public MessagingCertificateClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    public RabbitMqClientCert getRabbitMqClientCertificateKeyPair() {
        LOGGER.info("MessagingCertificateManager : getRabbitMqClientCertificate : Start");

        RabbitMqClientCert certificate = this.baseClient.getResource(
                RABBIT_MQ_CLIENT_CERT_KEYPAIR,
                RabbitMqClientCert.class);

        String tempKeyData = certificate.getBase64SSLKeyData()
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll(" ", "\\\n");

        LOGGER.info("MessagingCertificateManager : getRabbitMqClientCertificate : tempKeyData :" + tempKeyData);

        certificate.setBase64SSLKeyData(tempKeyData);

        String tempCertData = certificate.getBase64SSLCertData()
                .replace("-----BEGIN CERTIFICATE-----", "")
                .replace("-----END CERTIFICATE-----", "")
                .replaceAll(" ", "\\\n");
        String tempCertDataUpdated = "-----BEGIN CERTIFICATE-----" + tempCertData + "-----END CERTIFICATE-----";

        certificate.setBase64SSLCertData(tempCertDataUpdated);

        LOGGER.info("MessagingCertificateManager : getRabbitMqClientCertificate : Base64SSLCertData :" + certificate.getBase64SSLCertData());
        LOGGER.info("MessagingCertificateManager : getRabbitMqClientCertificate : Base64SSLKeyData :" + certificate.getBase64SSLKeyData());
        LOGGER.info("MessagingCertificateManager : getRabbitMqClientCertificate : End");

        return certificate;
    }

    public CaCert getCACertificate() {
        LOGGER.info("MessagingCertificateManager : getCACertificate : Start");

        Request request = new Request(HttpMethod.GET, CA_CERT_URI);

        String certificate = this.baseClient.executeRequest(request, String.class);
        CaCert caCert = new CaCert(certificate.replaceAll("\"", "").replaceAll("\\\\n", "\\\n"));

        LOGGER.info("MessagingCertificateManager : getCACertificate : getCACertificate :" + caCert.getCaCert());
        LOGGER.info("MessagingCertificateManager : getCACertificate : End");

        return caCert;
    }

    public TaskResource generateRabbitMqClientCert(RabbitMqClientCert rabbitMqClientCert) {
        LOGGER.info("MessagingCertificateManager : generateRabbitMqClientCert : Start");

        Request request = new Request(HttpMethod.POST, RABBIT_MQ_CLIENT_CERT, rabbitMqClientCert);

        TaskResource taskResource = this.baseClient.executeMonitorableRequest(request, false);

        LOGGER.info("MessagingCertificateManager : generateRabbitMqClientCert : End");

        return taskResource;
    }

}
