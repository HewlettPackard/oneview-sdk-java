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
package com.hp.ov.sdk.rest.client.networking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.Command;
import com.hp.ov.sdk.dto.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.InterconnectFibDataEntry;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.InternalVlanAssociation;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.PhysicalInterconnectFirmware;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.QosConfiguration.QosConfigType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Location;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.FirmwareDriverClient;
import com.hp.ov.sdk.rest.client.FirmwareDriverClientImpl;
import com.hp.ov.sdk.rest.client.LogicalInterconnectClient;
import com.hp.ov.sdk.rest.client.LogicalInterconnectClientImpl;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * LogicalInterconnectClientSample, sample program consumes the available networks through the interconnect uplinks and
 * downlink capabilities through a physical server’s interfaces of HPE OneView. It invokes APIs of LogicalInterconnectClient
 *  which is in sdk library to perform GET/PUT operations on logical interconnect resource
 */
public class LogicalInterconnectClientSample {

    private final LogicalInterconnectClient logicalInterconnectClient;
    private final FirmwareDriverClient firmwareDriverClient;
    private final OneViewClient oneViewClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // These are variables to be defined by user
    // ================================
    private static final String sppName = "Service Pack for ProLiant";
    private static final String resourceName = "Encl1-LI";
    private static final String resourceId = "b63caa92-556e-4816-99b8-76ac2b3ddb96";
    private static final String telemetryId = "2770fdeb-5c49-499c-aef7-3eac45f2887e";
    private static final String enclosureUri = "/rest/enclosures/09SGH100X6J1";
    private static final String networkName = "Prod_401";

    // InterconnectUri
    private static final String interconnectNameOne = "Encl1, interconnect 1";
    private static final String interconnectNameTwo = "Encl1, interconnect 2";
    // ================================

    private LogicalInterconnectClientSample() {
        this.oneViewClient = OneViewClientSample.getOneViewClient();
        this.logicalInterconnectClient = LogicalInterconnectClientImpl.getClient();
        this.firmwareDriverClient = FirmwareDriverClientImpl.getClient();
    }

    private void getLogicalInterconnectById() throws InstantiationException, IllegalAccessException {
        LogicalInterconnects logicalInterconnectsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalInterconnectsDto = logicalInterconnectClient.getLogicalInterconnect(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectById : logical interconnect"
                    + "  object returned to client : " + logicalInterconnectsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectById : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectById : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectById : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectById : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectById : arguments are null ");
        }

    }

    private void getLogicalInterconnectByName() {
        LogicalInterconnects logicalInterconnectDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalInterconnectDto = logicalInterconnectClient.getLogicalInterconnectByName(params, resourceName);

            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectByName :" + " logical interconnect object returned to client : "
                    + logicalInterconnectDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectByName :" + " arguments are null ");
            return;
        }
    }

    private void getAllLogicalInterconnects() throws InstantiationException, IllegalAccessException {
        ResourceCollection<LogicalInterconnects> logicalInterconnectCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalInterconnectCollectionDto = logicalInterconnectClient.getAllLogicalInterconnects(params);

            System.out.println("LogicalInterconnectClientSample : getAllLogicalInterconnects "
                    + ": logical interconnect object returned to client : " + logicalInterconnectCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getAllLogicalInterconnects : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getAllLogicalInterconnects : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getAllLogicalInterconnects : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getAllLogicalInterconnects : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getAllLogicalInterconnects : arguments are null ");
        }

    }

    private void updateLogicalInterconnectSnmpConfigurationById() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        LogicalInterconnects logicalInterconnectsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch logicalInterconnectsDto Uri
            logicalInterconnectsDto = logicalInterconnectClient.getLogicalInterconnectByName(params, resourceName);

            if (null != logicalInterconnectsDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(logicalInterconnectsDto.getUri());
            }

            logicalInterconnectsDto.getSnmpConfiguration().setReadCommunity("private");
            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectSnmpConfigurationById(params, resourceId,
                    logicalInterconnectsDto.getSnmpConfiguration(), false, false);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectSnmpConfigurationById : status of "
                    + "logicalInterconnect object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectSnmpConfigurationById : resource you are looking is not found for update");
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectSnmpConfigurationById : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectSnmpConfigurationById : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectSnmpConfigurationById : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectSnmpConfigurationById : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectSnmpConfigurationById : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void updateLogicalInterconnectComplianceById() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectComplianceById(params, resourceId, false);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectComplianceById : status of "
                    + "logicalInterconnect object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectComplianceById : resource you are looking is not found for update");
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectComplianceById : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectComplianceById : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectComplianceById : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectComplianceById : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectComplianceById : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void updateLogicalInterconnectFirmwareStageById() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        LiFirmware liFirmwareDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            liFirmwareDto = buildLIFirmwareStageDto();
            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectFirmwareById(params, resourceId, liFirmwareDto,
                    false, false);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectFirmwareStageById : status of "
                    + "logicalInterconnect object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareStageById : resource you are looking is not found for update");
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareStageById : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectFirmwareStageById : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareStageById : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareStageById : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectFirmwareStageById : errors in task, "
                    + "please check task resource for more details ");
        }

    }

    private LiFirmware buildLIFirmwareStageDto() {
        final LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.STAGE);
        liFirmware.setSppUri(firmwareDriverClient.getFirmwareDriverByName(params, sppName).getUri());
        liFirmware.setForce(true);
        return liFirmware;
    }

    private void getLogicalInterconnectFirmwareById() throws InstantiationException, IllegalAccessException {
        LiFirmware firmwareDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            firmwareDto = logicalInterconnectClient.getLogicalInterconnectFirmwareById(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectFirmwareById : firmware"
                    + "  object returned to client : " + firmwareDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectFirmwareById : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectFirmwareById : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectFirmwareById : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectFirmwareById : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectFirmwareById : arguments are null ");
        }
    }

    private void updateLogicalInterconnectFirmwareActiveById() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        LiFirmware liFirmwareDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            liFirmwareDto = logicalInterconnectClient.getLogicalInterconnectFirmwareById(params, resourceId);
            liFirmwareDto = buildLIFirmwareActiveDto(liFirmwareDto);

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectFirmwareById(params, resourceId, liFirmwareDto,
                    false, false);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectFirmwareActiveById : status of "
                    + "logicalInterconnect object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareActiveById : resource you are looking is not found for update");
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareActiveById : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectFirmwareActiveById : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareActiveById : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareActiveById : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareActiveById : errors in task, "
                    + "please check task resource for more details ");
        }

    }

    private LiFirmware buildLIFirmwareActiveDto(final LiFirmware initliFirmware) {
        final LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.STAGE);
        liFirmware.setSppUri(firmwareDriverClient.getFirmwareDriverByName(params, sppName).getUri());
        final List<PhysicalInterconnectFirmware> interconnects = new ArrayList<PhysicalInterconnectFirmware>();
        for (int i = 0; i < initliFirmware.getInterconnects().size(); i++) {
            String interconnectName = null;
            if (initliFirmware.getInterconnects().get(i).getInterconnectName().equals(interconnectNameOne)) {
                interconnectName = interconnectNameOne;
            } else if (initliFirmware.getInterconnects().get(i).getInterconnectName().equals(interconnectNameTwo)) {
                interconnectName = interconnectNameTwo;
            }
            if (interconnectName != null) {
                final PhysicalInterconnectFirmware interconnect = new PhysicalInterconnectFirmware();
                interconnect.setInterconnectUri(initliFirmware.getInterconnects().get(i).getInterconnectUri());
                interconnect.setInterconnectName(interconnectName);
                interconnects.add(interconnect);
            }
        }
        liFirmware.setInterconnects(interconnects);

        return liFirmware;
    }

    private void updateLogicalInterconnectFirmwareUpdateById() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        LiFirmware liFirmwareDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            liFirmwareDto = buildLIFirmwareUpdateDto();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectFirmwareById(params, resourceId, liFirmwareDto,
                    false, false);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectFirmwareUpdateById : status of "
                    + "logicalInterconnect object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareUpdateById : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareUpdateById : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectFirmwareUpdateById : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareUpdateById : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectFirmwareUpdateById : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : createEnclosure : errors in task, please check task resource for more details ");
            System.out.println("Task Errors: " + Arrays.toString(e.getMessageParameters()));
            System.out.println("Task Recomendations: " + Arrays.toString(e.getRecommendedActionsParameters()));
            return;
        }
    }

    private LiFirmware buildLIFirmwareUpdateDto() {
        final LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.UPDATE);
        liFirmware.setSppUri(firmwareDriverClient.getFirmwareDriverByName(params, sppName).getUri());
        liFirmware.setForce(true);
        return liFirmware;
    }

    private void getLogicalInterconnectForwardingInformationBase() {
        ResourceCollection<InterconnectFibDataEntry> fibDataDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fibDataDto = logicalInterconnectClient.getLogicalInterconnectForwardingInformationBase(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectForwardingInformationBase : logical interconnect data"
                    + "  object returned to client : " + fibDataDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectForwardingInformationBase : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectForwardingInformationBase : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectForwardingInformationBase : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectForwardingInformationBase : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectForwardingInformationBase : arguments are null ");
        }

    }

    private void createLogicalInterconnectForwardingInformationBase() {
        InterconnectFibDataInfo fibDataDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fibDataDto = logicalInterconnectClient.createLogicalInterconnectForwardingInformationBase(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : " + "createLogicalInterconnectForwardingInformationBase : logical interconnect info"
                    + "  object returned to client : " + fibDataDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "createLogicalInterconnectForwardingInformationBase : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "createLogicalInterconnectForwardingInformationBase : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "createLogicalInterconnectForwardingInformationBase : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "createLogicalInterconnectForwardingInformationBase : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "createLogicalInterconnectForwardingInformationBase : arguments are null ");
        }

    }

    private void getLogicalInterconnectSnmpConfigurationById() {
        SnmpConfiguration snmpConfigDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            snmpConfigDto = logicalInterconnectClient.getLogicalInterconnectSnmpConfigurationById(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectSnmpConfigurationById : logical interconnect data"
                    + "  object returned to client : " + snmpConfigDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectSnmpConfigurationById : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectSnmpConfigurationById : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectSnmpConfigurationById : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectSnmpConfigurationById : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectSnmpConfigurationById : arguments are null ");
        }

    }

    private void getLogicalInterconnectUnassignedUplinkPortsForPortMonitor() {
        ResourceCollection<PortMonitorUplinkPort> uplinkPortCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            uplinkPortCollectionDto = logicalInterconnectClient.getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectUnassignedUplinkPortsForPortMonitor "
                    + ": uplink port object returned to client : " + uplinkPortCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : arguments are null ");
        }
    }

    private void updateLogicalInterconnectConfiguration() {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectConfiguration(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectConfiguration : status of "
                    + "logicalInterconnect object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectConfiguration : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectConfiguration : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectConfiguration : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectConfiguration : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectConfiguration : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectConfiguration : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void getLogicalInterconnectPortMonitorConfiguration() {
        PortMonitor portMonitorDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            portMonitorDto = logicalInterconnectClient.getLogicalInterconnectPortMonitorConfiguration(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectPortMonitorConfiguration "
                    + ": uplink port object returned to client : " + portMonitorDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectPortMonitorConfiguration : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectPortMonitorConfiguration : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectPortMonitorConfiguration : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectPortMonitorConfiguration : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectPortMonitorConfiguration : arguments are null ");
        }
    }

    private void updateLogicalInterconnectPortMonitorConfiguration() {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            PortMonitor portMonitorDto = logicalInterconnectClient.getLogicalInterconnectPortMonitorConfiguration(params, resourceId);
            portMonitorDto.setEnablePortMonitor(false);

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectPortMonitorConfiguration(params, resourceId, portMonitorDto);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectPortMonitorConfiguration : status of "
                    + "task object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectPortMonitorConfiguration : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectPortMonitorConfiguration : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectPortMonitorConfiguration : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectPortMonitorConfiguration : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectPortMonitorConfiguration : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectPortMonitorConfiguration : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void getLogicalInterconnectTelemetryConfiguration() {
        TelemetryConfiguration telemetryConfigDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            telemetryConfigDto = logicalInterconnectClient.getLogicalInterconnectTelemetryConfiguration(params, resourceId, telemetryId);

            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectTelemetryConfiguration "
                    + ": uplink port object returned to client : " + telemetryConfigDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectTelemetryConfiguration : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectTelemetryConfiguration : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectTelemetryConfiguration : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectTelemetryConfiguration : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectTelemetryConfiguration : arguments are null ");
        }
    }

    private void updateLogicalInterconnectTelemetryConfiguration() {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            TelemetryConfiguration telemetryDto = logicalInterconnectClient.getLogicalInterconnectTelemetryConfiguration(params, resourceId, telemetryId);
            telemetryDto.setEnableTelemetry(!telemetryDto.getEnableTelemetry());

            TelemetryConfiguration updatedDto = logicalInterconnectClient.updateLogicalInterconnectTelemetryConfiguration(params, resourceId, telemetryId, telemetryDto);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectTelemetryConfiguration : "
                    + "TelemetryConfiguration object returned to client : " + updatedDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfiguration : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfiguration : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectTelemetryConfiguration : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfiguration : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfiguration : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfiguration : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void updateLogicalInterconnectTelemetryConfigurationV200() {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalInterconnectClient.getId(params, resourceName);

            TelemetryConfiguration telemetryDto = logicalInterconnectClient.getLogicalInterconnectTelemetryConfiguration(params, resourceId, telemetryId);
            telemetryDto.setEnableTelemetry(!telemetryDto.getEnableTelemetry());

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectTelemetryConfigurationV200(params, resourceId, telemetryId, telemetryDto);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectTelemetryConfigurationV200 : status of "
                    + "task object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfigurationV200 : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfigurationV200 : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectTelemetryConfigurationV200 : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfigurationV200 : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfigurationV200 : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectTelemetryConfigurationV200 : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void updateEthernetSettings() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            LogicalInterconnects logicalInterconnectsDto = logicalInterconnectClient.getLogicalInterconnect(params, resourceId);

            EthernetInterconnectSettingsV2 ethSettingsDto = logicalInterconnectsDto.getEthernetSettings();
            ethSettingsDto.setEnablePauseFloodProtection(!ethSettingsDto.getEnablePauseFloodProtection());

            taskResourceV2 = logicalInterconnectClient.updateEthernetSettings(params, resourceId, ethSettingsDto, false);

            System.out.println("LogicalInterconnectClientSample : " + "updateEthernetSettings : status of "
                    + "task object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateEthernetSettings : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateEthernetSettings : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateEthernetSettings : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateEthernetSettings : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateEthernetSettings : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateEthernetSettings : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void createLogicalInterconnect() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            Location locationDto = new Location();

            // ENCLOSURE
            LocationEntry enclosureEntry = new LocationEntry();
            enclosureEntry.setType(LocationEntry.Type.Enclosure);
            enclosureEntry.setValue(enclosureUri);

            // BAY
            LocationEntry bayEntry = new LocationEntry();
            bayEntry.setType(LocationEntry.Type.Bay);
            bayEntry.setValue("1");

            locationDto.setLocationEntries(Arrays.asList(enclosureEntry, bayEntry));

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalInterconnectClient.createLogicalInterconnect(params, locationDto, false, false);

            System.out.println("LogicalInterconnectClientSample : createLogicalInterconnect : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : createLogicalInterconnect : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : createLogicalInterconnect : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : createLogicalInterconnect : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : createLogicalInterconnect : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : createLogicalInterconnect : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("LogicalInterconnectClientSample : createLogicalInterconnect : errors in task, please check task resource for more details ");
            return;
        }
    }
    private void deleteLogicalInterconnect() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // make sdk service call to get resource
            taskResourceV2 = logicalInterconnectClient.deleteLogicalInterconnect(params, enclosureUri, "1", false);

            System.out.println("LogicalInterconnectClientSample : deleteLogicalInterconnect : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : deleteLogicalInterconnect : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : deleteLogicalInterconnect : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : deleteLogicalInterconnect : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : deleteLogicalInterconnect : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : deleteLogicalInterconnect : arguments are null ");
            return;
        }
    }

    private void updateLogicalInterconnectInternalNetworks() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            String resourceId = logicalInterconnectClient.getId(params, resourceName);

            String networkUri = oneViewClient.ethernetNetwork().getByName(networkName).getUri();

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectInternalNetworks(params,
                    resourceId, Arrays.asList(networkUri));

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectCompliance : status of "
                    + "task object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectCompliance : resource you are looking is not found for update "
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectCompliance : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectCompliance : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectCompliance : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectCompliance : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectCompliance : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    private void getLogicalInterconnectInternalVlans() {
        ResourceCollection<InternalVlanAssociation> vlanCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            vlanCollectionDto = logicalInterconnectClient.getLogicalInterconnectInternalVlans(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectInternalVlans "
                    + ": vlan collection object returned to client : " + vlanCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectInternalVlans : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectInternalVlans : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectInternalVlans : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectInternalVlans : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectInternalVlans : arguments are null ");
        }
    }

    private void getLogicalInterconnectQosAggregatedConfiguration() {
        QosAggregatedConfiguration qosConfigurationDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            qosConfigurationDto = logicalInterconnectClient.getLogicalInterconnectQosAggregatedConfiguration(params, resourceId);

            System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectQosAggregatedConfiguration "
                    + ": vlan collection object returned to client : " + qosConfigurationDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "getLogicalInterconnectQosAggregatedConfiguration : resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("LogicalInterconnectClientSample : " + "getLogicalInterconnectQosAggregatedConfiguration : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectQosAggregatedConfiguration : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectQosAggregatedConfiguration : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "getLogicalInterconnectQosAggregatedConfiguration : arguments are null ");
        }
    }

    private void updateLogicalInterconnectQosAggregatedConfiguration() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            QosAggregatedConfiguration qosConfigDto = logicalInterconnectClient.getLogicalInterconnectQosAggregatedConfiguration(params, resourceId);
            qosConfigDto.getActiveQosConfig().setConfigType(QosConfigType.Passthrough);

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectQosAggregatedConfiguration(params, resourceId, qosConfigDto);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectQosAggregatedConfiguration : status of "
                    + "task object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectQosAggregatedConfiguration : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : errors in task, "
                    + "please check task resource for more details ");
        }

    }

    private void updateLogicalInterconnectSettings() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            LogicalInterconnects logicalInterconnectsDto = logicalInterconnectClient.getLogicalInterconnect(params, resourceId);

            InterconnectSettingsV2 settingsDto = new InterconnectSettingsV2();
            settingsDto.setType("InterconnectSettingsV3");
            settingsDto.setEthernetSettings(logicalInterconnectsDto.getEthernetSettings());
            settingsDto.setFcoeSettings(logicalInterconnectsDto.getFcoeSettings());
            settingsDto.getEthernetSettings().setMacRefreshInterval(6);

            taskResourceV2 = logicalInterconnectClient.updateLogicalInterconnectSettings(params, resourceId, settingsDto);

            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectQosAggregatedConfiguration : status of "
                    + "task object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : resource you are looking is not found for update"
                    + params.getHostname());
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectClientSample : " + "updateLogicalInterconnectQosAggregatedConfiguration : no such url : "
                    + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectClientSample : "
                    + "updateLogicalInterconnectQosAggregatedConfiguration : errors in task, "
                    + "please check task resource for more details ");
        }
    }

    public static void main(final String[] args) throws Exception {
        LogicalInterconnectClientSample client = new LogicalInterconnectClientSample();

        client.getLogicalInterconnectById();
        client.getLogicalInterconnectByName();
        client.getAllLogicalInterconnects();
        client.updateLogicalInterconnectComplianceById();
        client.updateLogicalInterconnectFirmwareStageById();

        client.getLogicalInterconnectFirmwareById();

        client.updateLogicalInterconnectSnmpConfigurationById();

        client.createLogicalInterconnectForwardingInformationBase();
        client.getLogicalInterconnectForwardingInformationBase();
        client.getLogicalInterconnectSnmpConfigurationById();
        client.getLogicalInterconnectUnassignedUplinkPortsForPortMonitor();
        client.updateLogicalInterconnectConfiguration();
        client.getLogicalInterconnectPortMonitorConfiguration();

        client.updateLogicalInterconnectPortMonitorConfiguration();
        client.getLogicalInterconnectTelemetryConfiguration();

        client.updateLogicalInterconnectTelemetryConfiguration(); //OneView 1.2
        client.updateLogicalInterconnectTelemetryConfigurationV200(); //OneView 2.0

        client.updateEthernetSettings();
        client.createLogicalInterconnect();
        client.deleteLogicalInterconnect();
        client.updateLogicalInterconnectInternalNetworks();
        client.getLogicalInterconnectInternalVlans();
        client.getLogicalInterconnectQosAggregatedConfiguration();
        client.updateLogicalInterconnectQosAggregatedConfiguration();
        client.updateLogicalInterconnectSettings();

        client.updateLogicalInterconnectFirmwareActiveById();
        client.updateLogicalInterconnectFirmwareUpdateById();

    }

}
