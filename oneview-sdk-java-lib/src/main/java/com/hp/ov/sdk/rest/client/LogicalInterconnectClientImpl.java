/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

import com.hp.ov.sdk.adaptors.LogicalInterconnectAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectFibData;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.PortMonitorUplinkPortCollection;
import com.hp.ov.sdk.dto.SwitchDumpDataInfo;
import com.hp.ov.sdk.dto.SwitchDumpGenerationInfo;
import com.hp.ov.sdk.dto.TaskResourceV2;
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
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


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

        final LogicalInterconnects logicalInterconnectDto = adaptor.buildDto(returnObj);

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
        final LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = getAllLogicalInterconnects(params);

        for (final LogicalInterconnects logicalInterconnectDto : new ArrayList<>(logicalInterconnectCollectionDto.getMembers())) {
            if (logicalInterconnectDto.getName().equals(logicalInterconnectName)) {
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
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId, "firmware"));

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
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId + "/"
                + "snmp-configuration"));
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
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId + "/"
                + "compliance"));
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
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId + "/"
                + "firmware"));
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
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        LogicalInterconnects logicalInterconnectsDto = getLogicalInterconnectByName(creds, name);

        if (null != logicalInterconnectsDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(logicalInterconnectsDto.getUri());
        }
        return resourceId;
    }

    // TODO
    @Override
    public InterconnectFibData getLogicalInterconnectForwardingInformationBase(RestParams params, String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public InterconnectFibDataInfo createLogicalInterconnectForwardingInformationBase(RestParams params, String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public void getLogicalInterconnectForwardingInformationBaseDump(RestParams params, String resourceId, String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public SnmpConfiguration getLogicalInterconnectSnmpConfigurationById(RestParams params, String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public SwitchDumpDataInfo createLogicalInterconnectSupportDump(RestParams params, String resourceId,
            SwitchDumpGenerationInfo switchDumpGenerationInfo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public PortMonitorUplinkPortCollection getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(RestParams params,
            String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public TaskResourceV2 updateLogicalInterconnectConfiguration(RestParams params, String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public PortMonitor getLogicalInterconnectPortMonitorConfiguration(RestParams params, String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public TaskResourceV2 updateLogicalInterconnectPortMonitorConfiguration(RestParams params, String resourceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public TelemetryConfiguration getLogicalInterconnectTelementaryConfiguration(RestParams params, String resourceId,
            String telementaryConfigurationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public TelemetryConfiguration updateLogicalInterconnectTelementaryConfiguration(RestParams params, String resourceId,
            String telementaryConfigurationId, TelemetryConfiguration telemetryConfiguration) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
