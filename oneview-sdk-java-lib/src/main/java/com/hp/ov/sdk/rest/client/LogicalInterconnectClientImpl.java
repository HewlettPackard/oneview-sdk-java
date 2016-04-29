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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.LogicalInterconnectAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectFibData;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.InternalVlanAssociationCollection;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.PortMonitorUplinkPortCollection;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Location;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.dto.generated.TelemetryConfiguration;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;


public class LogicalInterconnectClientImpl implements LogicalInterconnectClient {

    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    public static final Logger LOGGER = LoggerFactory.getLogger(LogicalInterconnectClientImpl.class);

    private final LogicalInterconnectAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    private JSONObject jsonObject;

    protected LogicalInterconnectClientImpl(LogicalInterconnectAdaptor adaptor,
        TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {

        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static LogicalInterconnectClient getClient() {
        return new LogicalInterconnectClientImpl(new LogicalInterconnectAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public LogicalInterconnects getLogicalInterconnect(final RestParams params, final String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnect : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnect : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final LogicalInterconnects logicalInterconnectDto = adaptor.buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnect : Name :" + logicalInterconnectDto.getName());
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnect : End");

        return logicalInterconnectDto;
    }

    @Override
    public LogicalInterconnectCollectionV2 getAllLogicalInterconnects(final RestParams params) {
        LOGGER.info("LogicalInterconnectClientImpl : getAllLogicalInterconnects : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getAllLogicalInterconnects : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECTS, null);
        }
        // Call adaptor to convert to DTO

        final LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : getAllLogicalInterconnects : members count :"
                + logicalInterconnectCollectionDto.getCount());
        LOGGER.info("LogicalInterconnectClientImpl : getAllLogicalInterconnects : End");

        return logicalInterconnectCollectionDto;
    }

    @Override
    public LogicalInterconnects getLogicalInterconnectByName(final RestParams params, final String logicalInterconnectName) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectByName : start");

        // Filters are not supported
        final LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = getAllLogicalInterconnects(params);

        for (final LogicalInterconnects logicalInterconnectDto : new ArrayList<>(logicalInterconnectCollectionDto.getMembers())) {
            if (logicalInterconnectDto.getName().equalsIgnoreCase(logicalInterconnectName)) {
                System.out.println(logicalInterconnectDto.getName());
                LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectByName : End");
                return logicalInterconnectDto;
            }
        }
        LOGGER.error("LogicalInterconnectClientImpl : getLogicalInterconnectByName : resource not Found for name :"
                + logicalInterconnectName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.LOGICAL_INTERCONNECT,
                null);
    }

    @Override
    public LiFirmware getLogicalInterconnectFirmwareById(final RestParams params, final String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectFirmwareById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId, SdkConstants.FIRMWARE));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectFirmwareById : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final LiFirmware liFirmwareDto = adaptor.buildFirmwareDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectFirmwareById : End");

        return liFirmwareDto;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectSnmpConfigurationById(final RestParams params, final String resourceId,
            final SnmpConfiguration snmpConfigurationDto, final boolean asyncOrSyncMode, final boolean useJsonRequest) {

        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (snmpConfigurationDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.SNMP_CONFIGURATION));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating logical interconnect dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(snmpConfigurationDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById : taskResource ="
                + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && asyncOrSyncMode == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById  : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectComplianceById(final RestParams params, final String resourceId,
            final boolean asyncOrSyncMode) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.COMPLIANCE));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = null;
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById  : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectFirmwareById(final RestParams params, final String resourceId,
            final LiFirmware lIFirmwareDto, final boolean asyncOrSyncMode, final boolean useJsonRequest) {

        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (lIFirmwareDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.FIRMWARE));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating logical interconnect dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(lIFirmwareDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && asyncOrSyncMode == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById  : End");

        return taskResourceV2;
    }

    @Override
    public InterconnectFibData getLogicalInterconnectForwardingInformationBase(RestParams params, String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectForwardingInformationBase : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.FORWARDING_INFORMATION_BASE));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectForwardingInformationBase : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final InterconnectFibData fibDataDto = adaptor.buildInterconnectFibDataDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectForwardingInformationBase : End");

        return fibDataDto;
    }

    @Override
    public InterconnectFibDataInfo createLogicalInterconnectForwardingInformationBase(RestParams params, String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : createLogicalInterconnectForwardingInformationBase : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.FORWARDING_INFORMATION_BASE));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : createLogicalInterconnectForwardingInformationBase : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final InterconnectFibDataInfo fibDataDto = adaptor.buildInterconnectFibDataInfoDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : createLogicalInterconnectForwardingInformationBase : End");

        return fibDataDto;
    }

    @Override
    public SnmpConfiguration getLogicalInterconnectSnmpConfigurationById(RestParams params, String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectSnmpConfigurationById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.SNMP_CONFIGURATION));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectSnmpConfigurationById : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final SnmpConfiguration snmpConfigDto = adaptor.buildSnmpConfigurationDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectSnmpConfigurationById : End");

        return snmpConfigDto;
    }

    @Override
    public PortMonitorUplinkPortCollection getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(RestParams params,
            String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.UNASSIGNED_UPLINK_PORTS_FOR_PORT_MONITOR));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final PortMonitorUplinkPortCollection uplinkPortCollection = adaptor.buildPortMonitorUplinkPortCollectioDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : End");

        return uplinkPortCollection;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectConfiguration(RestParams params, String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.CONFIGURATION));
        String returnObj = null;

        // create JSON request from dto
        returnObj = HttpRestClient.sendRequestToHPOV(params);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectConfiguration : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectConfiguration : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectConfiguration  : End");

        return taskResourceV2;
    }

    // TODO
    @Override
    public PortMonitor getLogicalInterconnectPortMonitorConfiguration(RestParams params, String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectPortMonitorConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.PORT_MONITOR));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectPortMonitorConfiguration : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final PortMonitor portMonitorDto = adaptor.buildPortMonitorDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectPortMonitorConfiguration : End");

        return portMonitorDto;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectPortMonitorConfiguration(RestParams params, String resourceId, PortMonitor portMonitorDto) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectPortMonitorConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.PORT_MONITOR));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(portMonitorDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectPortMonitorConfiguration : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectPortMonitorConfiguration : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectPortMonitorConfiguration  : End");

        return taskResourceV2;
    }

    @Override
    public TelemetryConfiguration getLogicalInterconnectTelemetryConfiguration(RestParams params, String resourceId,
            String telemetryConfigurationId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectTelemetryConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.TELEMETRY_CONFIGURATIONS,
                telemetryConfigurationId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectTelemetryConfiguration : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final TelemetryConfiguration telemetryConfigDto = adaptor.buildTelemetryConfigurationsDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectTelemetryConfiguration : End");

        return telemetryConfigDto;
    }

    @Override
    public TelemetryConfiguration updateLogicalInterconnectTelemetryConfiguration(RestParams params, String resourceId,
            String TelemetryConfigurationId, TelemetryConfiguration telemetryConfigurationDto) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectTelemetryConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.TELEMETRY_CONFIGURATIONS,
                TelemetryConfigurationId));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(telemetryConfigurationDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectTelemetryConfiguration : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final TelemetryConfiguration telemetryConfigUpdatedDto = adaptor.buildTelemetryConfigurationsDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectTelemetryConfiguration : End");

        return telemetryConfigUpdatedDto;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectTelemetryConfigurationV200(RestParams params, String resourceId,
            String TelemetryConfigurationId, TelemetryConfiguration telemetryConfigurationDto) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectTelemetryConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.TELEMETRY_CONFIGURATIONS,
                TelemetryConfigurationId));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(telemetryConfigurationDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectTelemetryConfiguration : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectTelemetryConfiguration : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectTelemetryConfiguration  : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateEthernetSettings(RestParams params, String resourceId,
            EthernetInterconnectSettingsV2 ethernetInterconnectSettingsDto, boolean asyncOrSyncMode) {
        LOGGER.info("LogicalInterconnectClientImpl : updateEthernetSettings : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.ETHERNET_SETTINGS));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(ethernetInterconnectSettingsDto, params.getApiVersion());
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateEthernetSettings : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateEthernetSettings : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && asyncOrSyncMode == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateEthernetSettings  : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 createLogicalInterconnect(RestParams params, Location locationDto, boolean aSync,
            boolean useJsonRequest) {
        LOGGER.info("LogicalInterconnectClientImpl : createLogicalInterconnect : Start");
        String returnObj = null;

        // validate params
        if (locationDto == null || params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                SdkConstants.LOCATIONS,
                SdkConstants.INTERCONNECTS));

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(locationDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : createLogicalInterconnect : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : createLogicalInterconnect : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : createLogicalInterconnect : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteLogicalInterconnect(RestParams params, String enclosureUri, String bay, boolean aSync) {
        LOGGER.info("LogicalInterconnectClientImpl : deleteLogicalInterconnect : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                SdkConstants.LOCATIONS,
                SdkConstants.INTERCONNECTS +
                "?location=Enclosure:" + enclosureUri + ",Bay:" + bay));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : deleteLogicalInterconnect : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : deleteLogicalInterconnect : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : deleteLogicalInterconnect : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : deleteLogicalInterconnect : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectInternalNetworks(RestParams params, String resourceId, List<String> networkUris) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectInternalNetworks : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.INTERNAL_NETWORKS));
        String returnObj = null;

        // create JSON request from dto
        returnObj = HttpRestClient.sendRequestToHPOV(params, new JSONArray(networkUris));
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectInternalNetworks : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectInternalNetworks : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectInternalNetworks  : End");

        return taskResourceV2;
    }

    @Override
    public InternalVlanAssociationCollection getLogicalInterconnectInternalVlans(RestParams params, String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectInternalVlans : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.INTERNAL_VLANS));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectInternalVlans : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final InternalVlanAssociationCollection vlanCollectionDto = adaptor.buildInternalVlanCollectionDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectInternalVlans : End");

        return vlanCollectionDto;
    }

    @Override
    public QosAggregatedConfiguration getLogicalInterconnectQosAggregatedConfiguration(RestParams params, String resourceId) {
        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectQosAggregatedConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.QOS_AGGREGATED_CONFIGURATION));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectClientImpl : getLogicalInterconnectQosAggregatedConfiguration : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final QosAggregatedConfiguration qosConfigurationDto = adaptor.buildQosConfigurationDto(returnObj);

        LOGGER.info("LogicalInterconnectClientImpl : getLogicalInterconnectQosAggregatedConfiguration : End");

        return qosConfigurationDto;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectQosAggregatedConfiguration(RestParams params, String resourceId, QosAggregatedConfiguration qosAggregatedConfigurationDto) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectQosAggregatedConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.QOS_AGGREGATED_CONFIGURATION));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(qosAggregatedConfigurationDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectQosAggregatedConfiguration : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectQosAggregatedConfiguration : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectQosAggregatedConfiguration  : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectSettings(RestParams params, String resourceId, InterconnectSettingsV2 interconnectSettings) {
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectSettings : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId,
                SdkConstants.SETTINGS));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(interconnectSettings, params.getApiVersion());
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectSettings : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectSettings : taskResource =" + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectClientImpl : updateLogicalInterconnectSettings  : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        LogicalInterconnects logicalInterconnectsDto = getLogicalInterconnectByName(params, name);

        if (null != logicalInterconnectsDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(logicalInterconnectsDto.getUri());
        }
        return resourceId;
    }
}
