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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.facilities.datacenter.DataCenter;
import com.hp.ov.sdk.dto.facilities.datacenter.VisualContent;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class DataCenterClientImpl implements DataCenterClient {

    private Logger LOGGER = LoggerFactory.getLogger(DataCenterClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    private DataCenterClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static DataCenterClient getClient() {
        return new DataCenterClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public DataCenter getDataCenter(RestParams params, String resourceId) {
        LOGGER.trace("DataCenterClientImpl : getDataCenter : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("DataCenterClientImpl : getDataCenter : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        DataCenter dataCenter = adaptor.buildResourceObject(response, DataCenter.class);

        LOGGER.trace("DataCenterClientImpl : getDataCenter : End");

        return dataCenter;
    }

    @Override
    public ResourceCollection<DataCenter> getAllDataCenters(RestParams params) {
        LOGGER.trace("DataCenterClientImpl : getAllDataCenters : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("DataCenterClientImpl : getAllDataCenters : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTERS, null);
        }

        ResourceCollection<DataCenter> dataCenters = adaptor.buildResourceCollection(response, DataCenter.class);

        LOGGER.trace("DataCenterClientImpl : getAllDataCenters : End");

        return dataCenters;
    }

    @Override
    public DataCenter getDataCenterByName(RestParams params, String name) {
        LOGGER.trace("DataCenterClientImpl : getDataCenterByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("DataCenterClientImpl : getDataCenterByName : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTERS, null);
        }

        DataCenter dataCenter = null;
        ResourceCollection<DataCenter> dataCenters = adaptor.buildResourceCollection(response, DataCenter.class);

        if (!dataCenters.isEmpty()) {
            dataCenter = dataCenters.getMembers().get(0);
        }

        if (dataCenter == null) {
            LOGGER.warn("DataCenterClientImpl : getDataCenterByName : Not found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        LOGGER.trace("DataCenterClientImpl : getDataCenterByName : End");

        return dataCenter;
    }

    @Override
    public DataCenter addDataCenter(RestParams params, DataCenter dataCenter) {
        LOGGER.trace("DataCenterClientImpl : addDataCenter : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        if (dataCenter == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(dataCenter, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("DataCenterClientImpl : addDataCenter : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        DataCenter addedDataCenter = adaptor.buildResourceObject(response, DataCenter.class);

        LOGGER.trace("DataCenterClientImpl : addDataCenter : End");

        return addedDataCenter;
    }

    @Override
    public DataCenter updateDataCenter(RestParams params, String resourceId, DataCenter dataCenter) {
        LOGGER.trace("DataCenterClientImpl : updateDataCenter : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        if (dataCenter == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(dataCenter, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("DataCenterClientImpl : updateDataCenter : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        DataCenter updatedDataCenter = adaptor.buildResourceObject(response, DataCenter.class);

        LOGGER.trace("DataCenterClientImpl : updateDataCenter : End");

        return updatedDataCenter;
    }

    @Override
    public String removeDataCenter(RestParams params, String resourceId) {
        LOGGER.trace("DataCenterClientImpl : removeDataCenter : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("DataCenterClientImpl : removeDataCenter : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTERS, null);
        }

        LOGGER.trace("DataCenterClientImpl : removeDataCenter : End");

        return "Removed";
    }

    @Override
    public TaskResourceV2 removeDataCenterByFilter(RestParams params, String filter, boolean aSync) {
        LOGGER.trace("DataCenterClientImpl : removeDataCenterByFilter : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        if (StringUtils.isBlank(filter)) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        Map<String, String> query = new HashMap<>();
        query.put("filter", filter);
        params.setQuery(query);

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("DataCenterClientImpl : removeDataCenterByFilter : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTERS, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        if ((taskResource != null) && (aSync == false)) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }
        LOGGER.trace("DataCenterClientImpl : removeDataCenterByFilter : End");

        return taskResource;
    }

    @Override
    public List<VisualContent> getVisualContent(RestParams params, String resourceId) {
        LOGGER.trace("DataCenterClientImpl : getVisualContent : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.DATA_CENTER_URI,
                resourceId, ResourceUris.DATA_CENTER_VISUAL_CONTENT_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("DataCenterClientImpl : getVisualContent : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.DATA_CENTER, null);
        }

        Type listType = new TypeToken<List<VisualContent>>(){}.getType();

        List<VisualContent> visualContent = adaptor.buildListOfResourceObject(response, listType);

        LOGGER.trace("DataCenterClientImpl : getVisualContent : End");

        return visualContent;
    }

    @Override
    public String getId(RestParams params, String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        DataCenter dataCenter = this.getDataCenterByName(params, name);

        if (null != dataCenter) {
            resourceId = UrlUtils.getResourceIdFromUri(dataCenter.getUri());
        }
        return resourceId;
    }
}
