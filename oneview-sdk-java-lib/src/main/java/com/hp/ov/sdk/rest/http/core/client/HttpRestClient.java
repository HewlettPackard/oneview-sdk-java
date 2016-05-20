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
package com.hp.ov.sdk.rest.http.core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKForbiddenException;
import com.hp.ov.sdk.exceptions.SDKInternalServerErrorException;
import com.hp.ov.sdk.exceptions.SDKMethodNotAllowed;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKUnauthorizedException;
import com.hp.ov.sdk.util.UrlUtils;

public class HttpRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRestClient.class);

    private static final String CHAR_SET = "UTF-8";
    private static final String LOCATION_HEADER = "Location";
    private static final int TIMEOUT = 10000; // milliseconds

    private static final class HttpRestClientHolder {
        private static final HttpRestClient INSTANCE = new HttpRestClient();
    }

    /**
     * Gets an instance of the HttpRestClient object.
     *
     * @return
     *  An instance of the HttpRestClient object.
     */
    public static HttpRestClient getClient() {
        return HttpRestClientHolder.INSTANCE;
    }

    /**
     * Send the request to OV and read the response.
     *
     * @param restParams connection parameters.
     *
     * @return
     *  A string representing the response data.
     * @throws
     *  SDKBadRequestException on unsupported method (PUT, GET..)
     **/
    public String sendRequest(RestParams restParams) throws SDKBadRequestException {
        return processRequestType(restParams, null, false);
    }

    /**
     * Send the request to OV and read the response.
     *
     * @param restParams connection parameters.
     * @param jsonObject request body.
     *
     * @return
     *  A string representing the response data.
     * @throws
     *  SDKBadRequestException on unsupported method (PUT, GET..)
     **/
    public String sendRequest(RestParams restParams, JSONObject jsonObject) throws SDKBadRequestException {
         return processRequestType(restParams, jsonObject.toString(), false);
    }

    /**
     * Send the request to OV and read the response.
     *
     * @param restParams connection parameters.
     * @param
     *  jsonObject request body.
     *
     * @return
     *  A string representing the response data.
     * @throws
     *  SDKBadRequestException on unsupported method (PUT, GET..)
     **/
    public String sendRequest(RestParams restParams, JSONArray jsonObject) throws SDKBadRequestException {
         return processRequestType(restParams, jsonObject.toString(), false);
    }

    /**
     * Send the request to OV and read the response.
     *
     * @param restParams connection parameters.
     * @param jsonObject
     *  request body.
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     *
     * @return
     *  A string representing the response data.
     * @throws
     *  SDKBadRequestException on unsupported method (PUT, GET..)
     **/
    public String sendRequest(RestParams restParams, JSONObject jsonObject, boolean forceReturnTask)
            throws SDKBadRequestException {
        return processRequestType(restParams, jsonObject.toString(), forceReturnTask);
    }

    /**
     * Send the request to OV and read the response.
     *
     * @param restParams connection parameters.
     * @param requestBody
     *  request body.
     *
     * @return
     *  A string representing the response data.
     * @throws
     *  SDKBadRequestException on unsupported method (PUT, GET..)
     **/
    public String sendRequest(RestParams restParams, String requestBody)
            throws SDKBadRequestException {
        return processRequestType(restParams, requestBody, false);
    }

    /**
     * Process the request type to be sent to HPE OneView.
     *
     * @param params
     *  connection parameters.
     * @param requestBody
     *  request body.
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     *
     * @return
     *  A string representing the response data.
     * @throws SDKBadRequestException on unsupported method (PUT, GET..)
     **/
    private String processRequestType(final RestParams params,
                                     final String requestBody,
                                     final boolean forceReturnTask)
                                     throws SDKBadRequestException {

        LOGGER.debug("Using URL: " + params.getUrl());
        LOGGER.debug("Rest params passed, params= " + params
                     + "object = " + requestBody);

        String response = "";

        if (params.getType() == null) {
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError, null, null, null, SdkConstants.APPLIANCE,
                    null);
        }

        switch (params.getType()) {
        case POST:
            response = sendPostRequest(params, requestBody, forceReturnTask);
            break;
        case GET:
            response = sendGetRequest(params, requestBody, forceReturnTask);
            break;
        case PATCH:
            response = sendPatchRequest(params, requestBody, forceReturnTask);
            break;
        case PUT:
            response = sendPutRequest(params, requestBody, forceReturnTask);
            break;
        case DELETE:
            response = sendDeleteRequest(params, forceReturnTask);
            break;
        default:
            // Since request type is an ENUM, there is no way this will be executed
            LOGGER.error("Request type not supported.");
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError,
                    null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        return response;
    }

    /**
     * Sends a DELETE request to HPE OneView.
     *
     * @param params
     *  connection parameters.
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     * @return
     *  The response from HPE OneView
     * @throws SDKBadRequestException
     */
    private String sendDeleteRequest(RestParams params, boolean forceReturnTask)
            throws SDKBadRequestException {
        HttpDelete delete = new HttpDelete(buildURI(params));
        setRequestHeaders(params, delete);
        delete.setConfig(createRequestTimeoutConfiguration());
        return getResponse(delete, params, forceReturnTask);
    }

    /**
     * Sends a PUT request to HPE OneView.
     *
     * @param params
     *  connection parameters.
     * @param requestBody
     *  The request body information
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     * @return
     *  The response from HPE OneView
     * @throws SDKBadRequestException
     */
    private String sendPutRequest(RestParams params, String requestBody, boolean forceReturnTask)
            throws SDKBadRequestException {
        try {
            HttpPut put = new HttpPut(buildURI(params));
            if (requestBody == null) {
                requestBody = "";
            }
            put.setEntity(new StringEntity(requestBody, CHAR_SET));
            setRequestHeaders(params, put);
            put.setConfig(createRequestTimeoutConfiguration());
            return getResponse(put, params, forceReturnTask);
        } catch (IllegalArgumentException e) {
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError,
                    null, null, null,
                    SdkConstants.APPLIANCE, e);
        }
    }

    /**
     * Sends a GET request to HPE OneView.
     *
     * @param params
     *  connection parameters.
     * @param requestBody
     *  The request body information
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     * @return
     *  The response from HPE OneView
     * @throws SDKBadRequestException
     */
    private String sendGetRequest(RestParams params, String requestBody, boolean forceReturnTask)
            throws SDKBadRequestException {
        HttpGet get = new HttpGet(buildURI(params));
        setRequestHeaders(params, get);
        get.setConfig(createRequestTimeoutConfiguration());
        return getResponse(get, params, forceReturnTask);
    }

    /**
     * Sends a POST request to HPE OneView.
     *
     * @param params
     *  connection parameters.
     * @param requestBody
     *  The request body information
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     * @return
     *  The response from HPE OneView
     * @throws SDKBadRequestException
     */
    private String sendPostRequest(RestParams params, String requestBody, final boolean forceReturnTask)
            throws SDKBadRequestException {
        try {
            HttpPost post = new HttpPost(buildURI(params));
            if (requestBody == null) {
                requestBody = "";
            }
            post.setEntity(new StringEntity(requestBody, CHAR_SET));
            setRequestHeaders(params, post);
            post.setConfig(createRequestTimeoutConfiguration());
            return getResponse(post, params, forceReturnTask);
        } catch (IllegalArgumentException e) {
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError,
                    null, null, null,
                    SdkConstants.APPLIANCE, e);
        }
    }

    /**
     * Sends a PATCH request to HPE OneView.
     *
     * @param params
     *  connection parameters.
     * @param requestBody
     *  The request body information
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     * @return
     *  The response from HPE OneView
     * @throws SDKBadRequestException
     */
    private String sendPatchRequest(RestParams params, String requestBody, final boolean forceReturnTask)
            throws SDKBadRequestException {
        try {
            HttpPatch patch = new HttpPatch(buildURI(params));
            if (requestBody == null) {
                requestBody = "";
            }
            patch.setEntity(new StringEntity(requestBody, CHAR_SET));
            setRequestHeaders(params, patch);
            patch.setConfig(createRequestTimeoutConfiguration());
            return getResponse(patch, params, forceReturnTask);
        } catch (IllegalArgumentException e) {
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError,
                    null, null, null,
                    SdkConstants.APPLIANCE, e);
        }
    }

    /**
     * Creates the timeout configuration for the request.
     *
     * @return
     *  A request configuration.
     */
    private RequestConfig createRequestTimeoutConfiguration() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(TIMEOUT)
                .setConnectTimeout(TIMEOUT)
                .setSocketTimeout(TIMEOUT)
                .build();
    }

    /**
     * Builds the URI used in the request.
     *
     * @param params
     *  connection parameters.
     * @return
     *  The request URI
     * @throws SDKBadRequestException
     */
    private URI buildURI(RestParams params) throws SDKBadRequestException {
        try {
            URIBuilder uri = new URIBuilder(params.getUrl());
            for (Entry<String, String> entry : params.getQuery().entrySet()) {
                uri.addParameter(entry.getKey(), entry.getValue());
            }
            return uri.build();
        } catch (URISyntaxException e) {
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError,
                    null, null, null,
                    SdkConstants.APPLIANCE, e);
        }
    }

    /**
     * Configure the request headers.
     *
     * @param params
     *  connection parameters.
     * @param request
     *  Request information
     */
    private void setRequestHeaders(RestParams params, HttpUriRequest request) {
        if (params.getSessionId() != null) {
            request.setHeader("auth", params.getSessionId());
        }
        request.setHeader("X-API-Version", String.valueOf(params.getApiVersion().getValue()));
        request.setHeader("Content-Type", params.getHeaders().get("Content-Type"));
        request.setHeader("Accept", params.getHeaders().get("Accept"));
        request.setHeader("accept-language", params.getHeaders().get("accept-language"));
    }

    /**
     * Gets the response from HPE OneView.
     *
     * @param request
     *  Request information.
     * @param params
     *  connection parameters.
     * @param forceReturnTask
     *  Forces the check for the Location header (task) even when the response code is not 202.
     * @return
     */
    private String getResponse(HttpUriRequest request, RestParams params, final boolean forceReturnTask) {
        CloseableHttpClient client = buildHttpClient(params);

        StringBuffer responseBody = new StringBuffer();
        try {
            HttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            LOGGER.debug("Response code: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_NO_CONTENT) {
                responseBody.append("{}");
            } else {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    responseBody.append(line);
                }
            }

            LOGGER.error("Response Body: " + responseBody.toString());
            checkResponse(responseCode);

            if (forceReturnTask || ((responseCode == HttpURLConnection.HTTP_ACCEPTED) && (responseBody.length() == 0)
                    && response.containsHeader(LOCATION_HEADER))) {
                // Response contains a task
                String restUri = response.getFirstHeader(LOCATION_HEADER).getValue();
                restUri = restUri.substring(restUri.indexOf("/rest"));
                params.setUrl(UrlUtils.createRestUrl(params.getHostname(), restUri));
                params.setType(HttpMethodType.GET);

                return this.sendGetRequest(params, null, false);
            }

        } catch (IOException e) {
            LOGGER.error("IO Error: ", e);
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError, null, null, null, SdkConstants.APPLIANCE, e);
        }

        return responseBody.toString();
    }

    /**
     * Creates the HTTP client.
     *
     * @param params
     *  connection parameters.
     * @return
     *  A HTTP client.
     */
    private CloseableHttpClient buildHttpClient(RestParams params) {
        HttpClientBuilder clientBuilder = HttpClients.custom();

        // We dont need to set it the best available is used.
        // See SSLContext.init()
        TrustManager[] trustManagers = null;

        // If they want a different manager we need to use that.
        if (params.hasTrustManager()) {
            LOGGER.debug("Using user supplied trust manager: " + params.getTrustManager().getClass().getName());
            trustManagers = new TrustManager[] { params.getTrustManager() };
        }

        try {
            SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, // Use the best available key manager
                    trustManagers, // If null best available is used
                    new java.security.SecureRandom());

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sc, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());

            clientBuilder.setSSLSocketFactory(sslsf);

        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("Unable to set TrustManager", ex);
        } catch (KeyManagementException ex) {
            LOGGER.error("Unable to set TrustManager", ex);
        }

        // Use a different host verifier?
        if (params.hasHostnameVerifier()) {
            LOGGER.debug("Using user supplied host verifier: " + params.getHostnameVerifier().getClass().getName());
            clientBuilder.setSSLHostnameVerifier(params.getHostnameVerifier());
        }

        return clientBuilder.build();
    }

    /**
     * Checks the HTTP response codes, on error throws the correct exception.
     * Sets the exception cause as e if it throws one.
     *
     * @param responseCode
     *  response code.
     * @return
     *  the response code.
     */
    private int checkResponse(final int responseCode) {
        switch (responseCode) {
        case HttpsURLConnection.HTTP_OK:
        case HttpsURLConnection.HTTP_CREATED:
        case HttpsURLConnection.HTTP_ACCEPTED:
        case HttpsURLConnection.HTTP_NOT_AUTHORITATIVE:
        case HttpsURLConnection.HTTP_NO_CONTENT:
        case HttpsURLConnection.HTTP_RESET:
        case HttpsURLConnection.HTTP_PARTIAL:
            return responseCode;

        case HttpsURLConnection.HTTP_NOT_FOUND:
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.APPLIANCE, null);
        case HttpsURLConnection.HTTP_BAD_REQUEST:
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError, null, null, null, SdkConstants.APPLIANCE, null);
        case HttpsURLConnection.HTTP_FORBIDDEN:
            throw new SDKForbiddenException(SDKErrorEnum.forbiddenRequestError, null, null, null,
                    SdkConstants.APPLIANCE, null);
        case HttpsURLConnection.HTTP_BAD_METHOD:
            throw new SDKMethodNotAllowed(SDKErrorEnum.methodNotFound, null, null, null, SdkConstants.APPLIANCE, null);
        case HttpsURLConnection.HTTP_UNAUTHORIZED:
            throw new SDKUnauthorizedException(SDKErrorEnum.unauthorized, null, null, null, SdkConstants.APPLIANCE, null);
        case HttpsURLConnection.HTTP_CONFLICT:
        case HttpsURLConnection.HTTP_PRECON_FAILED:
        case HttpsURLConnection.HTTP_UNSUPPORTED_TYPE:
        case HttpsURLConnection.HTTP_INTERNAL_ERROR:
        case HttpsURLConnection.HTTP_UNAVAILABLE:
            throw new SDKInternalServerErrorException(SDKErrorEnum.internalServerError, null, null, null,
                    SdkConstants.APPLIANCE, null);
        case HttpsURLConnection.HTTP_BAD_GATEWAY:
        case HttpsURLConnection.HTTP_CLIENT_TIMEOUT:
        case HttpsURLConnection.HTTP_ENTITY_TOO_LARGE:
        case HttpsURLConnection.HTTP_GATEWAY_TIMEOUT:
        case HttpsURLConnection.HTTP_GONE:
        case HttpsURLConnection.HTTP_LENGTH_REQUIRED:
        case HttpsURLConnection.HTTP_MOVED_PERM:
        case HttpsURLConnection.HTTP_MOVED_TEMP:
        case HttpsURLConnection.HTTP_MULT_CHOICE:
        case HttpsURLConnection.HTTP_NOT_ACCEPTABLE:
        case HttpsURLConnection.HTTP_NOT_IMPLEMENTED:
        case HttpsURLConnection.HTTP_NOT_MODIFIED:
        case HttpsURLConnection.HTTP_PAYMENT_REQUIRED:
        case HttpsURLConnection.HTTP_PROXY_AUTH:
        case HttpsURLConnection.HTTP_REQ_TOO_LONG:
        case HttpsURLConnection.HTTP_SEE_OTHER:
        case HttpsURLConnection.HTTP_USE_PROXY:
        case HttpsURLConnection.HTTP_VERSION:
        default:
            throw new SDKApplianceNotReachableException(SDKErrorEnum.applicanceNotReachable, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
    }

}
