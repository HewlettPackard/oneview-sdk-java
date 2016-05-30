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
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LogicalDownlink;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalDownlinkClientImpl implements LogicalDownlinkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalDownlinkClientImpl.class);

    private final HttpRestClient httpClient;
    private final ResourceAdaptor adaptor;

    private LogicalDownlinkClientImpl(HttpRestClient httpClient, ResourceAdaptor adaptor) {
        this.httpClient = httpClient;
        this.adaptor = adaptor;
    }

    public static LogicalDownlinkClient getClient() {
        return new LogicalDownlinkClientImpl(HttpRestClient.getClient(), new ResourceAdaptor());
    }

    @Override
    public LogicalDownlink getLogicalDownlinkById(RestParams params, String resourceId) {
        LogicalDownlink logicalDownlinkDto = null;
        LOGGER.info("LogicalDownlinkClientImpl : getLogicalDownlinkById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalDownlinkImpl : getLogicalDownlinkById : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_DOWNLINK, null);
        }

        logicalDownlinkDto = adaptor.buildResourceObject(returnObj, LogicalDownlink.class);

        LOGGER.debug("LogicalDownlinkImpl : getLogicalDownlinkById : name :" + logicalDownlinkDto.getName());
        LOGGER.info("LogicalDownlinkClientImpl : getLogicalDownlinkById : End");

        return logicalDownlinkDto;
    }

    @Override
    public LogicalDownlink getLogicalDownlinkByIdWithoutEthernet(RestParams params, String resourceId) {
        LogicalDownlink logicalDownlinkDto = null;
        LOGGER.info("LogicalDownlinkClientImpl : getLogicalDownlinkByIdWithoutEthernet : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI, resourceId, ResourceUris.WITHOUT_ETHERNET));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalDownlinkImpl : getLogicalDownlinkByIdWithoutEthernet : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_DOWNLINK, null);
        }

        logicalDownlinkDto = adaptor.buildResourceObject(returnObj, LogicalDownlink.class);

        LOGGER.debug("LogicalDownlinkImpl : getLogicalDownlinkByIdWithoutEthernet : name :" + logicalDownlinkDto.getName());
        LOGGER.info("LogicalDownlinkClientImpl : getLogicalDownlinkByIdWithoutEthernet : End");

        return logicalDownlinkDto;
    }

    @Override
    public LogicalDownlink getLogicalDownlinkByName(RestParams params, String resourceName) {
        LogicalDownlink logicalDownlinkDto = null;
        ResourceCollection<LogicalDownlink> logicalDownlinkCollectionDto = null;

        LOGGER.info("LogicalDownlinkClientImpl : getLogicalDownlinkByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalDownlinkClientImpl : getLogicalDownlinkByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_DOWNLINK, null);
        }
        // Call adaptor to convert to DTO

        logicalDownlinkCollectionDto = adaptor.buildResourceCollection(returnObj, LogicalDownlink.class);
        if (logicalDownlinkCollectionDto.getCount() != 0) {
            logicalDownlinkDto = logicalDownlinkCollectionDto.getMembers().get(0);
        } else {
            logicalDownlinkDto = null;
        }

        if (logicalDownlinkDto == null) {
            LOGGER.error("LogicalDownlinkClientImpl : getLogicalDownlinkByName : resource not Found for name :"
                    + resourceName);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.LOGICAL_DOWNLINK, null);
        }
        LOGGER.info("LogicalDownlinkClientImpl : getLogicalDownlinkByName : End");

        return logicalDownlinkDto;
    }

    @Override
    public ResourceCollection<LogicalDownlink> getAllLogicalDownlinks(RestParams params) {
        ResourceCollection<LogicalDownlink> logicalDownlinkCollectionDto = null;
        LOGGER.info("LogicalDownlinkClientImpl : getAllLogicalDownlinks : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalDownlinkClientImpl : getAllLogicalDownlinks : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_DOWNLINK, null);
        }
        logicalDownlinkCollectionDto = adaptor.buildResourceCollection(returnObj, LogicalDownlink.class);

        LOGGER.debug("LogicalDownlinkClientImpl : getAllLogicalDownlinks : count :" + logicalDownlinkCollectionDto.getCount());
        LOGGER.info("LogicalDownlinkClientImpl : getAllLogicalDownlinks : End");

        return logicalDownlinkCollectionDto;
    }

    @Override
    public ResourceCollection<LogicalDownlink> getAllLogicalDownlinksWithoutEthernet(RestParams params) {
        ResourceCollection<LogicalDownlink> logicalDownlinkCollectionDto = null;
        LOGGER.info("LogicalDownlinkClientImpl : getAllLogicalDownlinksWithoutEthernet : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_DOWNLINK_URI, ResourceUris.WITHOUT_ETHERNET));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalDownlinkClientImpl : getAllLogicalDownlinksWithoutEthernet : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_DOWNLINK, null);
        }
        logicalDownlinkCollectionDto = adaptor.buildResourceCollection(returnObj, LogicalDownlink.class);

        LOGGER.debug("LogicalDownlinkClientImpl : getAllLogicalDownlinksWithoutEthernet : count :" + logicalDownlinkCollectionDto.getCount());
        LOGGER.info("LogicalDownlinkClientImpl : getAllLogicalDownlinksWithoutEthernet : End");

        return logicalDownlinkCollectionDto;
    }

}
