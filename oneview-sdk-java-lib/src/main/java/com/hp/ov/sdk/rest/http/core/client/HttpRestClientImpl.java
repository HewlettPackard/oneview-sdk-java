/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.http.core.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKForbiddenException;
import com.hp.ov.sdk.exceptions.SDKMethodNotAllowed;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.util.StringUtil;

@Component
public class HttpRestClientImpl implements HttpRestClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpRestClientImpl.class);
    private static final String ACCEPT = "application/json";
    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";
    private static final String CONTENT_TYPE_STRING = "text/plain; charset=UTF-8";
    private static final String ACCEPT_LANGAUGE = "en_US";
    private static final String CHAR_SET = "UTF-8";
    private static final int TIMEOUT = 1000;

    /**
     * this method mainly used to get sync response from REST API
     * 
     * @throws SDKResourceNotFoundException
     * @throws SDKNoSuchUrlException
     */

    /*
     * 
     */
    @Override
    public String sendRequestToHPOV(final RestParams params, final JSONObject jsonObject) {

        logger.debug("HttpRestClientImpl : sendRequestToHPOV : Rest params passed, params=: " + params + " jsonObject = :"
                + jsonObject);
        BufferedReader bufferedReader = null;
        HttpsURLConnection connection = null;
        final StringBuilder sb = new StringBuilder();
        int responseCode = 0;
        try {
            final SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            final URL url_one = new URL(null, params.getUrl(), new sun.net.www.protocol.https.Handler());
            connection = (HttpsURLConnection) url_one.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            // TODO - move the headers to KeyValuePair
            if (params.getSessionId() != null) {
                connection.setRequestProperty("auth", params.getSessionId());
            }
            connection.setRequestProperty("Accept", ACCEPT);
            connection.setRequestProperty("X-API-Version", StringUtil.convertIntToString(params.getApiVersion()));
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            connection.setRequestProperty("accept-language", ACCEPT_LANGAUGE);
            connection.setSSLSocketFactory(sslsocketfactory);
            connection.setRequestMethod(params.getType().toString());
            connection.setConnectTimeout(TIMEOUT);

            // TODO- finally
            try {
                if ((params.getType() == HttpMethodType.POST || params.getType() == HttpMethodType.PUT) && null != jsonObject) {
                    final OutputStream os = connection.getOutputStream();
                    os.write(jsonObject.toString().getBytes(CHAR_SET));
                    os.close();
                }
                responseCode = connection.getResponseCode();
                logger.debug("HttpRestClientImpl : sendRequestToHPOV : Response code = " + responseCode);
                final DataInputStream inputStream = new DataInputStream(connection.getInputStream());
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String string = null;

                while ((string = bufferedReader.readLine()) != null && responseCode != 204) {
                    sb.append(string);
                }
                if (responseCode == 204) {
                    sb.append("{}");
                }
                // TODO - for responseCode == 202
                // Implement review comment from Geoff
                // Async APIs should return Task URI in header, eg response =
                // 202,
                // check location header vs returned object. SDK should check
                // 202,
                // get URI from location header then GET task as needed.
                logger.info("HttpRestClientImpl : sendRequestToHPOV :: string returned from server =: " + sb);
            } finally {
                // no need to check for null
                // any exceptions thrown here will be caught by
                // outer catch block
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                connection.disconnect();
            }

            // TODO - exceptions
        } catch (final MalformedURLException e) {
            throw new SDKNoSuchUrlException(SDKErrorEnum.invalidUrl, null, null, null, SdkConstants.APPLIANCE, null);
        } catch (final IOException e) {
            if (responseCode == SdkConstants.URL_NOT_FOUND) {
                throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.APPLIANCE,
                        null);
                // TODO - check for error code 400 - bad request ( duplicate
                // resource, wrongId, incorrect data)
                // throw SDKDuplicateResourceException
            } else if (responseCode == SdkConstants.BAD_REQUEST) {
                throw new SDKBadRequestException(SDKErrorEnum.badRequestError, null, null, null, SdkConstants.APPLIANCE, null);
            } else if (responseCode == SdkConstants.FORBIDDEN_REQUEST) {
                throw new SDKForbiddenException(SDKErrorEnum.forbiddenRequestError, null, null, null, SdkConstants.APPLIANCE, null);
            } else if (responseCode == SdkConstants.METHOD_NOT_ALLOWED) {
                throw new SDKMethodNotAllowed(SDKErrorEnum.methodNotFound, null, null, null, SdkConstants.APPLIANCE, null);
            } else {
                throw new SDKApplianceNotReachableException(SDKErrorEnum.applicanceNotReachable, null, null, null,
                        SdkConstants.APPLIANCE, null);
            }
        }

        return sb.toString();

    }

    @Override
    public String sendStringRequestToHPOV(RestParams params, String scriptObject) {

        logger.debug("HttpRestClientImpl : sendRequestToHPOV : Rest params passed, params=: " + params + " scriptObject = :"
                + scriptObject);
        BufferedReader bufferedReader = null;
        HttpsURLConnection connection = null;
        final StringBuilder sb = new StringBuilder();
        int responseCode = 0;
        try {
            final SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            final URL url_one = new URL(null, params.getUrl(), new sun.net.www.protocol.https.Handler());
            connection = (HttpsURLConnection) url_one.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            // TODO - move the headers to KeyValuePair
            if (params.getSessionId() != null) {
                connection.setRequestProperty("auth", params.getSessionId());
            }
            connection.setRequestProperty("Accept", ACCEPT);
            connection.setRequestProperty("X-API-Version", StringUtil.convertIntToString(params.getApiVersion()));
            connection.setRequestProperty("Content-Type", CONTENT_TYPE_STRING);
            connection.setRequestProperty("accept-language", ACCEPT_LANGAUGE);
            connection.setSSLSocketFactory(sslsocketfactory);
            connection.setRequestMethod(params.getType().toString());
            connection.setConnectTimeout(TIMEOUT);

            // TODO- finally
            try {
                if ((params.getType() == HttpMethodType.POST || params.getType() == HttpMethodType.PUT) && null != scriptObject) {
                    final OutputStream os = connection.getOutputStream();
                    os.write(scriptObject.toString().getBytes(CHAR_SET));
                    os.close();
                }
                responseCode = connection.getResponseCode();
                logger.debug("HttpRestClientImpl : sendRequestToHPOV : Response code = " + responseCode);
                final DataInputStream inputStream = new DataInputStream(connection.getInputStream());
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String string = null;

                while ((string = bufferedReader.readLine()) != null && responseCode != 204) {
                    sb.append(string);
                }
                if (responseCode == 204) {
                    sb.append("{}");
                }
                // TODO - for responseCode == 202
                // Implement review comment from Geoff
                // Async APIs should return Task URI in header, eg response =
                // 202,
                // check location header vs returned object. SDK should check
                // 202,
                // get URI from location header then GET task as needed.
                logger.info("HttpRestClientImpl : sendRequestToHPOV :: string returned from server =: " + sb);
            } finally {
                // no need to check for null
                // any exceptions thrown here will be caught by
                // outer catch block
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                connection.disconnect();
            }

            // TODO - exceptions
        } catch (final MalformedURLException e) {
            throw new SDKNoSuchUrlException(SDKErrorEnum.invalidUrl, null, null, null, SdkConstants.APPLIANCE, null);
        } catch (final IOException e) {
            if (responseCode == SdkConstants.URL_NOT_FOUND) {
                throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.APPLIANCE,
                        null);
                // TODO - check for error code 400 - bad request ( duplicate
                // resource, wrongId, incorrect data)
                // throw SDKDuplicateResourceException
            } else if (responseCode == SdkConstants.BAD_REQUEST) {
                throw new SDKBadRequestException(SDKErrorEnum.badRequestError, null, null, null, SdkConstants.APPLIANCE, null);
            } else if (responseCode == SdkConstants.FORBIDDEN_REQUEST) {
                throw new SDKForbiddenException(SDKErrorEnum.forbiddenRequestError, null, null, null, SdkConstants.APPLIANCE, null);
            } else if (responseCode == SdkConstants.METHOD_NOT_ALLOWED) {
                throw new SDKMethodNotAllowed(SDKErrorEnum.methodNotFound, null, null, null, SdkConstants.APPLIANCE, null);
            } else {
                throw new SDKApplianceNotReachableException(SDKErrorEnum.applicanceNotReachable, null, null, null,
                        SdkConstants.APPLIANCE, null);
            }
        }

        return sb.toString();
    }
}
