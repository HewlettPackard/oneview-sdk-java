/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.certs;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class MessagingCertificateManagerImpl implements
        MessagingCertificateManager
{

    private static final Logger logger = LoggerFactory
            .getLogger(MessagingCertificateManagerImpl.class);

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private CaCert caCert;

    @Autowired
    private RabbitMqClientCertAdaptor rabbitMqadaptor;

    private JSONObject jsonObject;

    @Autowired
    private CaCertificateAdaptor caCertAdaptor;

    @Autowired
    private SdkUtils sdkUtils;

    public RabbitMqClientCert getRabbitMqClientCertificateKeyPair(
            RestParams params)
    {

        logger.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : Start");
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.RABBIT_MQ_CLIENT_CERT_KEYPAIR));

        String returnObj = restClient.sendRequestToHPOV(params, null);

        logger.debug("SCMBCertificatesImpl : getRabbitMqClientCertificate : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SCMB_CERTS, null);
        }
        // Call adaptor to convert to DTO
        RabbitMqClientCert clientCert = rabbitMqadaptor.buildDto(returnObj);
        String tempKeyData = clientCert.getBase64SSLKeyData()
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll(" ", "\\\n");
        logger.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : tempKeyData :"
                + tempKeyData);
        clientCert.setBase64SSLKeyData(tempKeyData);
        String tempCertData = clientCert.getBase64SSLCertData()
                .replace("-----BEGIN CERTIFICATE-----", "")
                .replace("-----END CERTIFICATE-----", "")
                .replaceAll(" ", "\\\n");
        String tempCertDataUpdated = "-----BEGIN CERTIFICATE-----"
                + tempCertData + "-----END CERTIFICATE-----";
        clientCert.setBase64SSLCertData(tempCertDataUpdated);
        logger.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : tempCertData :"
                + tempCertData);
        logger.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : tempCertDataUpdated :"
                + tempCertDataUpdated);
        logger.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : Base64SSLCertData :"
                + clientCert.getBase64SSLCertData());
        logger.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : Base64SSLKeyData :"
                + clientCert.getBase64SSLKeyData());
        logger.info("SCMBCertificatesImpl : getRabbitMqClientCertificate : End");

        return clientCert;
    }

    public CaCert getCACertificate(RestParams params)
    {

        logger.info("SCMBCertificatesImpl : getCACertificate : Start");
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.CA_CERT_URI));

        String returnObj = restClient.sendRequestToHPOV(params, null);

        logger.debug("SCMBCertificatesImpl : getCACertificate : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SCMB_CERTS, null);
        }
        String tempStr = returnObj.replaceAll("\"", "").replaceAll("\\\\n",
                "\\\n");
        caCert.setCaCert(tempStr);

        logger.info("SCMBCertificatesImpl : getCACertificate : getCACertificate :"
                + caCert.getCaCert());
        logger.info("SCMBCertificatesImpl : getCACertificate : End");

        return caCert;

    }

    @Override
    public String generateRabbitMqClientCert(RestParams params,
            RabbitMqClientCert rabbitMqClientCertDto, final boolean aSync,
            final boolean useJsonRequest)
    {
        logger.info("SCMBCertificatesImpl : generateRabbitMqClientCert : Start");
        // set the additional params
        if (rabbitMqClientCertDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.RABBITMQ, null);
        }
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.RABBIT_MQ_CLIENT_CERT));

        // check for json request in the input rabbitMqClientCertDto. if it is
        // present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network rabbitMqClientCertDto.

        jsonObject = rabbitMqadaptor
                .buildJsonObjectFromDto(rabbitMqClientCertDto);
        String returnObj = restClient.sendRequestToHPOV(params, jsonObject);

        if (returnObj.equals(""))
        {
            returnObj = "Created";
        }

        logger.debug("SCMBCertificatesImpl : generateRabbitMqClientCert : response from OV :"
                + returnObj);

        logger.info("SCMBCertificatesImpl : generateRabbitMqClientCert : generateRabbitMqClientCert :"
                + returnObj);
        logger.info("SCMBCertificatesImpl : generateRabbitMqClientCert : End");

        return returnObj;
    }
}
