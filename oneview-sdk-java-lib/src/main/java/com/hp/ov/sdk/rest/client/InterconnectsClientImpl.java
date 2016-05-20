/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.InterconnectAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.dto.generated.Port;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;


public class InterconnectsClientImpl implements InterconnectsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectsClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final ResourceAdaptor resourceAdaptor;
    private final InterconnectAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    private InterconnectsClientImpl(HttpRestClient httpClient, ResourceAdaptor resourceAdaptor,
            InterconnectAdaptor adaptor, TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {

        this.httpClient = httpClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static InterconnectsClient getClient() {
        return new InterconnectsClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new InterconnectAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public Interconnects getInterconnectById(final RestParams params, final String resourceId) {
        LOGGER.info("InterconnectsClientImpl : getInterconnects : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("InterconnectsClientImpl : getInterconnects : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final Interconnects interconnectDto = adaptor.buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("InterconnectsClientImpl : getInterconnects : name :" + interconnectDto.getName());
        LOGGER.info("InterconnectsClientImpl : getInterconnects : End");

        return interconnectDto;
    }

    @Override
    public ResourceCollection<Interconnects> getAllInterconnects(final RestParams params) {
        LOGGER.info("InterconnectsClientImpl : getAllInterconnects : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI));

        // call rest client
        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("InterconnectsClientImpl : getAllInterconnects : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }

        ResourceCollection<Interconnects> interconnectsCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, Interconnects.class);

        LOGGER.debug("InterconnectsClientImpl : getAllInterconnects : members count :" + interconnectsCollectionDto.getCount());
        LOGGER.info("InterconnectsClientImpl : getAllInterconnects : End");

        return interconnectsCollectionDto;
    }

    @Override
    public Interconnects getInterconnectByName(RestParams params, String interconnectName) {
        Interconnects interconnectDto = null;
        LOGGER.info("InterconnectsClientImpl : getInterconnectByName : start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + interconnectName + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("InterconnectsClientImpl : getInterconnectByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.INTERCONNECT, null);
        }

        ResourceCollection<Interconnects> interconnectsCollection
                = resourceAdaptor.buildResourceCollection(returnObj, Interconnects.class);

        if (interconnectsCollection.getCount() != 0) {
            interconnectDto = interconnectsCollection.getMembers().get(0);
        } else {
            interconnectDto = null;
        }

        if (interconnectDto == null) {
            LOGGER.error(
                    "InterconnectsClientImpl : getInterconnectByName : resource not Found for name :"
                            + interconnectName);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.INTERCONNECT, null);
        }
        LOGGER.info("InterconnectsClientImpl : getInterconnectByName : End");

        return interconnectDto;
    }

    @Override
    public TaskResourceV2 patchInterconnect(RestParams params, String resourceId, Patch patchDto,  boolean aSync) {
        LOGGER.info("InterconnectsClientImpl : patchInterconnect : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // validate params
        if (patchDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.INTERCONNECT, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PATCH);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        JSONArray jsonArray = adaptor.buildJsonArrayDto(patchDto);
        returnObj = httpClient.sendRequest(params, jsonArray);

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : patchInterconnect : returnObj =" + returnObj);
        LOGGER.debug("InterconnectsClientImpl : patchInterconnect : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("InterconnectsClientImpl : patchInterconnect : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateInterconnectPort(RestParams params, String resourceId, Port portDto, boolean aSync,
            boolean useJsonRequest) {
        LOGGER.info("InterconnectsClientImpl : updateInterconnectPort : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (portDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.PORTS));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(portDto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : updateInterconnectPort : returnObj =" + returnObj);
        LOGGER.debug("InterconnectsClientImpl : updateInterconnectPort : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("InterconnectsClientImpl : updateInterconnectPort : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 resetInterconnectPortProtection(RestParams params, String resourceId, boolean aSync) {
        LOGGER.info("InterconnectsClientImpl : resetInterconnectPortProtection : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (resourceId == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.RESET_PORT_PROTECTION));

        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        returnObj = httpClient.sendRequest(params);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : resetInterconnectPortProtection : returnObj =" + returnObj);
        LOGGER.debug("InterconnectsClientImpl : resetInterconnectPortProtection : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("InterconnectsClientImpl : resetInterconnectPortProtection : End");

        return taskResourceV2;
    }

    @Override
    public InterconnectsStatistics getInterconnectStatistics(RestParams params, String resourceId) {
        LOGGER.info("InterconnectsClientImpl : getInterconnectStatistics : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.STATISTICS));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("InterconnectsClientImpl : getInterconnectStatistics : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final InterconnectsStatistics statisticsDto = adaptor.buildInterconnectStatisticsDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : getInterconnectStatistics : Object :" + statisticsDto.toString());
        LOGGER.info("InterconnectsClientImpl : getInterconnectStatistics : End");

        return statisticsDto;
    }

    @Override
    public PortStatistics getInterconnectPortStatistics(RestParams params, String resourceId, String portName) {
        LOGGER.info("InterconnectsClientImpl : getInterconnectPortStatistics : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.STATISTICS,
                portName));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("InterconnectsClientImpl : getInterconnectPortStatistics : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final PortStatistics statisticsDto = adaptor.buildInterconnectPortStatisticsDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : getInterconnectPortStatistics : Object :" + statisticsDto.toString());
        LOGGER.info("InterconnectsClientImpl : getInterconnectPortStatistics : End");

        return statisticsDto;
    }

    @Override
    public SubportStatistics getInterconnectSubportStatistics(RestParams params, String resourceId, String portName,
            int subportNumber) {
        LOGGER.info("InterconnectsClientImpl : getInterconnectSubportStatistics : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.STATISTICS,
                portName,
                SdkConstants.SUBPORT,
                subportNumber));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("InterconnectsClientImpl : getInterconnectSubportStatistics : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final SubportStatistics statisticsDto = adaptor.buildInterconnectSubportStatisticsDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : getInterconnectSubportStatistics : Object :" + statisticsDto.toString());
        LOGGER.info("InterconnectsClientImpl : getInterconnectSubportStatistics : End");

        return statisticsDto;
    }

    @Override
    public TaskResourceV2 updateInterconnectPorts(RestParams params, String resourceId, List<Port> portsDto,
            boolean aSync, boolean useJsonRequest) {
        LOGGER.info("InterconnectsClientImpl : updateInterconnectPorts : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (resourceId == null || portsDto == null || portsDto.size() < 1) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.UPDATE_PORTS));

        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        JSONArray jsonArray = adaptor.buildJsonArrayDto(portsDto, params.getApiVersion());

        returnObj = httpClient.sendRequest(params, jsonArray);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("InterconnectsClientImpl : updateInterconnectPorts : returnObj =" + returnObj);
        LOGGER.debug("InterconnectsClientImpl : updateInterconnectPorts : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("InterconnectsClientImpl : updateInterconnectPorts : End");

        return taskResourceV2;
    }

    @Override
    public List<NameServer> getInterconnectNamedServers(RestParams params, String resourceId) {
        LOGGER.info("InterconnectsClientImpl : getInterconnectNamedServers : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.INTERCONNECT_URI,
                resourceId,
                SdkConstants.NAME_SERVERS));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("InterconnectsClientImpl : getInterconnectNamedServers : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.INTERCONNECT,
                    null);
        }
        // Call adaptor to convert to DTO

        final List<NameServer> namedServers = adaptor.buildInterconnectNameServersCollection(returnObj);

        LOGGER.debug("InterconnectsClientImpl : getInterconnectNamedServers : Object :" + namedServers.toString());
        LOGGER.info("InterconnectsClientImpl : getInterconnectNamedServers : End");

        return namedServers;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Interconnects interconnectsDto = getInterconnectByName(params, name);

        if (null != interconnectsDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(interconnectsDto.getUri());
        }
        return resourceId;
    }

}
