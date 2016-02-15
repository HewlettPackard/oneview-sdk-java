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

import com.hp.ov.sdk.adaptors.CaCertificateAdaptor;
import com.hp.ov.sdk.adaptors.RabbitMqClientCertAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.CaCert;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.RabbitMqClientCert;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagingCertificateManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingCertificateManager.class);

    private final RabbitMqClientCertAdaptor rabbitMqAdaptor;
    private final CaCertificateAdaptor caCertAdaptor;

    private static class MessagingCertificateManagerHolder {
        private static final MessagingCertificateManager INSTANCE = new MessagingCertificateManager(
            new RabbitMqClientCertAdaptor(), new CaCertificateAdaptor());
    }

    protected MessagingCertificateManager(RabbitMqClientCertAdaptor rabbitMqAdaptor,
            CaCertificateAdaptor caCertAdaptor) {

        this.rabbitMqAdaptor = rabbitMqAdaptor;
        this.caCertAdaptor = caCertAdaptor;
    }

    public static MessagingCertificateManager getInstance() {
        return MessagingCertificateManagerHolder.INSTANCE;
    }

    public RabbitMqClientCert getRabbitMqClientCertificateKeyPair(final RestParams params) {

        LOGGER.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : Start");
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RABBIT_MQ_CLIENT_CERT_KEYPAIR));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);

        LOGGER.debug("SCMBCertificatesImpl : getRabbitMqClientCertificate : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SCMB_CERTS, null);
        }
        // Call adaptor to convert to DTO
        final RabbitMqClientCert clientCert = rabbitMqAdaptor.buildDto(returnObj);
        final String tempKeyData = clientCert.getBase64SSLKeyData().replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "").replaceAll(" ", "\\\n");
        LOGGER.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : tempKeyData :" + tempKeyData);
        clientCert.setBase64SSLKeyData(tempKeyData);
        final String tempCertData = clientCert.getBase64SSLCertData().replace("-----BEGIN CERTIFICATE-----", "")
                .replace("-----END CERTIFICATE-----", "").replaceAll(" ", "\\\n");
        final String tempCertDataUpdated = "-----BEGIN CERTIFICATE-----" + tempCertData + "-----END CERTIFICATE-----";
        clientCert.setBase64SSLCertData(tempCertDataUpdated);
        LOGGER.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : tempCertData :" + tempCertData);
        LOGGER.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : tempCertDataUpdated :" + tempCertDataUpdated);
        LOGGER.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : Base64SSLCertData :" + clientCert.getBase64SSLCertData());
        LOGGER.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : Base64SSLKeyData :" + clientCert.getBase64SSLKeyData());
        LOGGER.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : End");

        return clientCert;
    }

    public CaCert getCACertificate(final RestParams params) {

        LOGGER.info("SCMBCertificatesImpl : getCACertificate : Start");
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CA_CERT_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);

        LOGGER.debug("SCMBCertificatesImpl : getCACertificate : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SCMB_CERTS, null);
        }
        final String tempStr = returnObj.replaceAll("\"", "").replaceAll("\\\\n", "\\\n");

        CaCert caCert = new CaCert(tempStr);

        LOGGER.info("SCMBCertificatesImpl : getCACertificate : getCACertificate :" + caCert.getCaCert());
        LOGGER.info("SCMBCertificatesImpl : getCACertificate : End");

        return caCert;

    }

    public String generateRabbitMqClientCert(final RestParams params, final RabbitMqClientCert rabbitMqClientCertDto,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("SCMBCertificatesImpl : generateRabbitMqClientCert : Start");
        // set the additional params
        if (rabbitMqClientCertDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.RABBITMQ, null);
        }
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RABBIT_MQ_CLIENT_CERT));

        // check for json request in the input rabbitMqClientCertDto. if it is
        // present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network rabbitMqClientCertDto.

        JSONObject jsonObject = rabbitMqAdaptor.buildJsonObjectFromDto(rabbitMqClientCertDto);
        String returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);

        if (returnObj.equals("")) {
            returnObj = "Created";
        }

        LOGGER.debug("SCMBCertificatesImpl : generateRabbitMqClientCert : response from OV :" + returnObj);

        LOGGER.info("SCMBCertificatesImpl : generateRabbitMqClientCert : generateRabbitMqClientCert :" + returnObj);
        LOGGER.info("SCMBCertificatesImpl : generateRabbitMqClientCert : End");

        return returnObj;
    }
}
