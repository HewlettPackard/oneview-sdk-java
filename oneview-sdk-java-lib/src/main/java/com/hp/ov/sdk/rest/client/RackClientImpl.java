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

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class RackClientImpl implements RackClient {

    private Logger LOGGER = LoggerFactory.getLogger(RackClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    private RackClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static RackClient getClient() {
        return new RackClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public Rack getRack(RestParams params, String resourceId) {
        LOGGER.trace("RackClientImpl : getRack : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("RackClientImpl : getRack : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACK, null);
        }

        Rack rack = adaptor.buildResourceObject(response, Rack.class);

        LOGGER.trace("RackClientImpl : getRack : End");

        return rack;
    }

    @Override
    public ResourceCollection<Rack> getAllRacks(RestParams params) {
        LOGGER.trace("RackClientImpl : getAllRacks : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("RackClientImpl : getAllRacks : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACK, null);
        }

        ResourceCollection<Rack> racks = adaptor.buildResourceCollection(response, Rack.class);

        LOGGER.trace("RackClientImpl : getAllRacks : End");

        return racks;
    }

    @Override
    public Rack getRackByName(RestParams params, String name) {
        LOGGER.trace("RackClientImpl : getRackByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("RackClientImpl : getRackByName : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACKS, null);
        }

        Rack rack = null;
        ResourceCollection<Rack> racks = adaptor.buildResourceCollection(response, Rack.class);

        if (!racks.isEmpty()) {
            rack = racks.getMembers().get(0);
        }

        if (rack == null) {
            LOGGER.warn("RackClientImpl : getRackByName : Not found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.RACK, null);
        }

        LOGGER.trace("RackClientImpl : getRackByName : End");

        return rack;
    }

    @Override
    public Rack addRack(RestParams params, Rack rack) {
        LOGGER.trace("RackClientImpl : addRack : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        if (rack == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.RACK, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(rack, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("RackClientImpl : addRack : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACK, null);
        }

        Rack addedRack = adaptor.buildResourceObject(response, Rack.class);

        LOGGER.trace("RackClientImpl : addRack : End");

        return addedRack;
    }

    @Override
    public Rack updateRack(RestParams params, String resourceId, Rack rack) {
        LOGGER.trace("RackClientImpl : updateRack : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        if (rack == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.RACK, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(rack, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("RackClientImpl : updateRack : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACK, null);
        }

        Rack updatedRack = adaptor.buildResourceObject(response, Rack.class);

        LOGGER.trace("RackClientImpl : updateRack : End");

        return updatedRack;
    }

    @Override
    public String removeRack(RestParams params, String resourceId) {
        LOGGER.trace("RackClientImpl : removeRack : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("RackClientImpl : removeRack : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACK, null);
        }

        LOGGER.trace("RackClientImpl : removeRack : End");

        return "Removed";
    }

    @Override
    public TaskResourceV2 removeRackByFilter(RestParams params, String filter, boolean aSync) {
        LOGGER.trace("RackClientImpl : removeRackByFilter : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        if (StringUtils.isBlank(filter)) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.RACK, null);
        }

        Map<String, String> query = new HashMap<>();
        query.put("filter", filter);
        params.setQuery(query);

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("RackClientImpl : removeRackByFilter : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACK, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        if ((taskResource != null) && (aSync == false)) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }
        LOGGER.trace("RackClientImpl : removeRackByFilter : End");

        return taskResource;
    }

    @Override
    public TopologyInformation getDeviceTopology(RestParams params, String resourceId) {
        LOGGER.trace("RackClientImpl : getDeviceTopology : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.RACK_URI,
                resourceId, ResourceUris.RACK_DEVICE_TOPOLOGY));

        String response = restClient.sendRequest(params);

        LOGGER.debug("RackClientImpl : getDeviceTopology : response from OV : " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.RACK, null);
        }

        TopologyInformation topologyInformation
                = adaptor.buildResourceObject(response, TopologyInformation.class);

        LOGGER.trace("RackClientImpl : getDeviceTopology : End");

        return topologyInformation;
    }

    @Override
    public String getId(RestParams params, String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Rack rack = this.getRackByName(params, name);

        if (null != rack) {
            resourceId = UrlUtils.getResourceIdFromUri(rack.getUri());
        }
        return resourceId;
    }
}
