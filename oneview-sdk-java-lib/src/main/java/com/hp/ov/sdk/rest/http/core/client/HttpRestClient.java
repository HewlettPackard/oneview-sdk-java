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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKForbiddenException;
import com.hp.ov.sdk.exceptions.SDKInternalServerErrorException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKMethodNotAllowed;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKUnauthorizedException;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.util.JsonSerializer;
import com.hp.ov.sdk.util.UrlUtils;

public class HttpRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRestClient.class);

    private static final String LOCATION_HEADER = "Location";
    private static final int TIMEOUT = 20000; // milliseconds

    /*
    TODO this could be replaced by a one way converter (Object to JSON).
    We can also consider to have a Map containing several converters and
    choose the appropriate converter according to the Content-Type.
     */
    private final JsonSerializer serializer;

    public HttpRestClient(JsonSerializer serializer) {
        this.serializer = serializer;
    }

    private static final class HttpRestClientHolder {
        private static final HttpRestClient INSTANCE = new HttpRestClient(new JsonSerializer());
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
     * @param restParams unmodifiable connection parameters (hostname, sessionId, etc)
     * @param request contains the details specific to the current request.
     *
     * @return
     *  A string representing the response data.
     * @throws
     *  SDKBadRequestException on unsupported method (PUT, GET..)
     **/
    public String sendRequest(RestParams restParams, Request request) throws SDKBadRequestException {
        URI uri = buildURI(restParams, request);

        LOGGER.debug("Using URL: " + uri.toString());
        LOGGER.debug("Rest params passed, params = " + restParams + " object = " + request.getEntity());

        if (request.getType() == null) {
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        HttpRequestBase requestBase = null;

        switch (request.getType()) {
            case POST:
                HttpPost post = new HttpPost(uri);
                fillRequestEntity(post, restParams, request);
                requestBase = post;
                break;
            case GET:
                HttpGet get = new HttpGet(uri);
                requestBase = get;
                break;
            case PATCH:
                HttpPatch patch = new HttpPatch(uri);
                HttpEntity entity = EntityBuilder.create()
                        .setText(serializer.toJsonArray((Patch) request.getEntity(),
                                restParams.getApiVersion()))
                        .setContentType(toApacheContentType(request.getContentType())).build();

                patch.setEntity(entity);

                requestBase = patch;
                break;
            case PUT:
                HttpPut put = new HttpPut(uri);
                fillRequestEntity(put, restParams, request);
                requestBase = put;
                break;
            case DELETE:
                HttpDelete delete = new HttpDelete(uri);
                requestBase = delete;
                break;
            default:
                // Since request type is an ENUM, there is no way this will be executed
                LOGGER.error("Request type not supported.");
                throw new SDKBadRequestException(SDKErrorEnum.badRequestError,
                        null, null, null, SdkConstants.APPLIANCE, null);
        }

        if (requestBase != null) {
            requestBase.setConfig(createRequestTimeoutConfiguration());
            setRequestHeaders(restParams, requestBase);

            return getResponse(requestBase, restParams, request.isForceTaskReturn());
        }
        LOGGER.error("could not create a valid request.");
        throw new SDKBadRequestException(SDKErrorEnum.badRequestError, null, null, null,
                SdkConstants.APPLIANCE, null);
    }

    private void fillRequestEntity(HttpEntityEnclosingRequestBase base, RestParams params, Request request) {
        if (request.hasEntity()) {
            HttpEntity entity;
            ContentType contentType = request.getContentType();

            //TODO add support for Content-Type "application/json-patch+json" (PATCH)

            if (ContentType.APPLICATION_JSON == contentType) {
                String textEntity;

                if (request.getEntity() instanceof List) {
                    textEntity = serializer.toJsonArray((List) request.getEntity(),
                            params.getApiVersion());
                } else {
                    textEntity = serializer.toJson(request.getEntity(), params.getApiVersion());
                }

                entity = EntityBuilder.create()
                        .setContentType(toApacheContentType(contentType))
                        .setText(textEntity)
                        .build();
            } else if (ContentType.TEXT_PLAIN == contentType) {
                entity = EntityBuilder.create()
                        .setContentType(toApacheContentType(contentType))
                        .setText(request.getEntity().toString())
                        .build();
            } else if (ContentType.MULTIPART_FORM_DATA == contentType) {
                File file = (File) request.getEntity();
                entity = MultipartEntityBuilder.create()
                        .setContentType(toApacheContentType(contentType))
                        .addBinaryBody("file", file)
                        .build();

                //TODO add support for custom headers in Request object
                base.setHeader("uploadfilename", file.getName());
            } else {
                LOGGER.error("Unknown entity Content-Type");

                throw new SDKInvalidArgumentException(SDKErrorEnum.internalServerError,
                        null, null, null, "Unknown entity Content-Type", null);
            }
            base.setEntity(entity);
        }
    }

    private org.apache.http.entity.ContentType toApacheContentType(ContentType contentType) {
        return org.apache.http.entity.ContentType.create(contentType.getMimeType(), contentType.getCharset());
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
    private URI buildURI(RestParams params, Request request) throws SDKBadRequestException {
        try {
            URIBuilder uri = new URIBuilder(UrlUtils.createRestUrl(params.getHostname(), request.getUri()));

            for (Entry<String, String> entry : request.getQuery().entrySet()) {
                uri.addParameter(entry.getKey(), entry.getValue());
            }
            return uri.build();
        } catch (URISyntaxException e) {
            throw new SDKBadRequestException(SDKErrorEnum.badRequestError,
                    null, null, null, SdkConstants.APPLIANCE, e);
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
        if (StringUtils.isNotBlank(params.getSessionId())) {
            request.setHeader("Auth", params.getSessionId());
        }
        request.setHeader("Accept", "application/json");
        request.setHeader("accept-language", "en_US");
        request.setHeader("X-Api-Version", String.valueOf(params.getApiVersion().getValue()));
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

            LOGGER.info("Response Body: " + responseBody.toString());
            checkResponse(responseCode);

            if (forceReturnTask || ((responseCode == HttpURLConnection.HTTP_ACCEPTED) && (responseBody.length() == 0)
                    && response.containsHeader(LOCATION_HEADER))) {
                // Response contains a task
                String restUri = response.getFirstHeader(LOCATION_HEADER).getValue();
                restUri = restUri.substring(restUri.indexOf("/rest"));

                Request taskRequest = new Request(HttpMethodType.GET, restUri);

                return this.sendRequest(params, taskRequest);
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
