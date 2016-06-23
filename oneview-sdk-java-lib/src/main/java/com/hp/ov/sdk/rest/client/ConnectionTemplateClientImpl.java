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
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

public class ConnectionTemplateClientImpl implements ConnectionTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionTemplateClientImpl.class);

    private final HttpRestClient httpClient;
    private final ResourceAdaptor adaptor;

    private JSONObject jsonObject;

    private ConnectionTemplateClientImpl(HttpRestClient httpClient, ResourceAdaptor adaptor) {
        this.httpClient = httpClient;
        this.adaptor = adaptor;
    }

    public static ConnectionTemplateClient getClient() {
        return new ConnectionTemplateClientImpl(HttpRestClient.getClient(), new ResourceAdaptor());
    }

    @Override
    public ConnectionTemplate getConnectionTemplate(final RestParams params, final String resourceId) {
        ConnectionTemplate connectionTemplateDto = null;
        LOGGER.info("ConnectionTemplateImpl : getConnectionTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ConnectionTemplateImpl : getConnectionTemplate : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }

        connectionTemplateDto = adaptor.buildResourceObject(returnObj, ConnectionTemplate.class);

        LOGGER.debug("ConnectionTemplateImpl : getConnectionTemplate : name :" + connectionTemplateDto.getName());
        LOGGER.info("ConnectionTemplateImpl : getConnectionTemplate : End");

        return connectionTemplateDto;
    }

    @Override
    public ConnectionTemplate getConnectionTemplateByName(final RestParams params, final String resourceName) {
        ConnectionTemplate connectionTemplateDto = null;
        ResourceCollection<ConnectionTemplate> connectionTemplateCollectionDto = null;

        LOGGER.info("ConnectionTemplateClientImpl : getConnectionTemplateByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ConnectionTemplateClientImpl : getConnectionTemplateByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }
        // Call adaptor to convert to DTO

        connectionTemplateCollectionDto = adaptor.buildResourceCollection(returnObj, ConnectionTemplate.class);
        if (connectionTemplateCollectionDto.getCount() != 0) {
            connectionTemplateDto = connectionTemplateCollectionDto.getMembers().get(0);
        } else {
            connectionTemplateDto = null;
        }

        if (connectionTemplateDto == null) {
            LOGGER.error("ConnectionTemplateClientImpl : getConnectionTemplateByName : resource not Found for name :"
                    + resourceName);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }
        LOGGER.info("ConnectionTemplateClientImpl : getConnectionTemplateByName : End");

        return connectionTemplateDto;
    }

    @Override
    public ConnectionTemplate updateConnectionTemplate(final RestParams params, final String resourceId,
            ConnectionTemplate connectionTemplateDto, final boolean useJsonRequest) {
        LOGGER.info("ConnectionTemplateClientImpl : updateConnectionTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (connectionTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.CONNECTION_TEMPLATE,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating connectionTemplate dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonRequest(connectionTemplateDto, params.getApiVersion());

        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        connectionTemplateDto = adaptor.buildResourceObject(returnObj, ConnectionTemplate.class);

        LOGGER.debug("ConnectionTemplateClientImpl : updateConnectionTemplate : returnObj =" + returnObj);

        LOGGER.info("ConnectionTemplateClientImpl : updateConnectionTemplate : End");

        return connectionTemplateDto;
    }

    @Override
    public ResourceCollection<ConnectionTemplate> getAllConnectionTemplates(final RestParams params) {
        ResourceCollection<ConnectionTemplate> connectionTemplateCollectionDto = null;
        LOGGER.info("ConnectionTemplateImpl : getAllConnectionTemplates : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.CONNECTION_TEMPLATE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ConnectionTemplateImpl : getAllConnectionTemplates : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }
        connectionTemplateCollectionDto = adaptor.buildResourceCollection(returnObj, ConnectionTemplate.class);

        LOGGER.debug("ConnectionTemplateImpl : getAllConnectionTemplates : count :" + connectionTemplateCollectionDto.getCount());
        LOGGER.info("ConnectionTemplateImpl : getAllConnectionTemplates : End");

        return connectionTemplateCollectionDto;
    }

    @Override
    public ConnectionTemplate getDefaultConnectionTemplateForConnectionTemplate(final RestParams params) {
        ConnectionTemplate connectionTemplateDto = null;
        LOGGER.info("ConnectionTemplateImpl : getDefaultConnectionTemplateForConnectionTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DEFAULT_CONNECTION_TEMPLATE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ConnectionTemplateImpl : getDefaultConnectionTemplateForConnectionTemplate : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.CONNECTION_TEMPLATE, null);
        }

        connectionTemplateDto = adaptor.buildResourceObject(returnObj, ConnectionTemplate.class);

        LOGGER.debug("ConnectionTemplateImpl : getDefaultConnectionTemplateForConnectionTemplate : name :"
                + connectionTemplateDto.getName());
        LOGGER.info("ConnectionTemplateImpl : getDefaultConnectionTemplateForConnectionTemplate : End");

        return connectionTemplateDto;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        ConnectionTemplate connectionTemplateDto = getConnectionTemplateByName(params, name);

        if (null != connectionTemplateDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(connectionTemplateDto.getUri());
        }
        return resourceId;
    }

}
