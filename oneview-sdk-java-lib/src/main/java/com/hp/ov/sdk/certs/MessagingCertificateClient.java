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
import com.hp.ov.sdk.messaging.core.RabbitMQClientCert;
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

    public RabbitMQClientCert getRabbitMQClientCertificateKeyPair() {
        LOGGER.info("Retrieving RabbitMQ RSA key and SSL certificate");

        Request request = new Request(HttpMethod.GET, RABBIT_MQ_CLIENT_CERT_KEYPAIR);
        RabbitMQClientCert rabbitMQClientCert = this.baseClient.executeRequest(request, RabbitMQClientCert.class);

        String tempKeyData = rabbitMQClientCert.getBase64SSLKeyData()
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll(" ", "\\\n");

        rabbitMQClientCert.setBase64SSLKeyData(tempKeyData);

        String tempCertData = rabbitMQClientCert.getBase64SSLCertData()
                .replace("-----BEGIN CERTIFICATE-----", "")
                .replace("-----END CERTIFICATE-----", "")
                .replaceAll(" ", "\\\n");
        String tempCertDataUpdated = "-----BEGIN CERTIFICATE-----" + tempCertData + "-----END CERTIFICATE-----";

        rabbitMQClientCert.setBase64SSLCertData(tempCertDataUpdated);

        LOGGER.debug("RabbitMQ RSA private key data: {}", rabbitMQClientCert.getBase64SSLKeyData());
        LOGGER.debug("RabbitMQ SSL certificate data: {}", rabbitMQClientCert.getBase64SSLCertData());

        LOGGER.info("RabbitMQ RSA key and SSL certificate successfully retrieved!");

        return rabbitMQClientCert;
    }

    public CaCert getCACertificate() {
        LOGGER.debug("Retrieving RabbitMQ CA certificate");

        Request request = new Request(HttpMethod.GET, CA_CERT_URI);

        String certificate = this.baseClient.executeRequest(request, String.class);
        CaCert caCert = new CaCert(certificate.replaceAll("\"", "").replaceAll("\\\\n", "\\\n"));

        LOGGER.debug("RabbitMQ CA certificate successfully retrieved!");

        return caCert;
    }

    public TaskResource generateRabbitMQClientCert(RabbitMQClientCert rabbitMQClientCert) {
        LOGGER.debug("Requesting the creation of RabbitMQ self signed certificate");

        Request request = new Request(HttpMethod.POST, RABBIT_MQ_CLIENT_CERT, rabbitMQClientCert);
        TaskResource taskResource = this.baseClient.executeMonitorableRequest(request);

        LOGGER.debug("Creation of RabbitMQ self signed certificate successfully executed!");

        return taskResource;
    }

}
