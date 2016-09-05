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

package com.hp.ov.sdk.certs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class MessagingCertificateClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingCertificateClientSample.class);

    private final MessagingCertificateClient client;

    private MessagingCertificateClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.messagingCertificate();
    }

    private void createRabbitMqClientCertificate() {
        RabbitMqClientCert certificate = new RabbitMqClientCert();

        certificate.setCommonName("sample");
        certificate.setSignedCert(false);
        certificate.setType("RabbitMqClientCertV2");

        TaskResourceV2 taskResource = client.generateRabbitMqClientCert(certificate);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getRabbitMqClientCertificateKeyPair() {
        RabbitMqClientCert certificate = client.getRabbitMqClientCertificateKeyPair();

        LOGGER.info("Rabbit MQ client certificate returned to client: {}", certificate.toJsonString());
    }

    private void getCACertificate() {
        CaCert certificate = client.getCACertificate();

        LOGGER.info("CA certificate returned to client: {}", JsonPrettyPrinter.print(certificate));
    }

    public static void main(String[] args) {
        MessagingCertificateClientSample sample = new MessagingCertificateClientSample();

        sample.createRabbitMqClientCertificate();
        sample.getRabbitMqClientCertificateKeyPair();
        sample.getCACertificate();
    }

}
