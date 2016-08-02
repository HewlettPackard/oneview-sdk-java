/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.servers;

import java.util.Arrays;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.FirmwareUpdateOn;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.PatchFirmwareValue;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClientImpl;
import com.hp.ov.sdk.rest.client.LogicalEnclosureClient;
import com.hp.ov.sdk.rest.client.LogicalEnclosureClientImpl;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * LogicalEnclosureClientSample is a sample program to consume the REST API of c7000 enclosure managed
 * by HPE OneView. It invokes APIs of LogicalEnclosureClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on logical enclosure resource
 */
public class LogicalEnclosureClientSample {

    private final LogicalEnclosureClient logicalEnclosureClient;
    private final FirmwareDriverClient firmwareDriverClient;
    private final EnclosureGroupClient enclosureGroupClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String resourceName = "Encl1";
    private static final String enclosureGroupName = "Enclosure_Test";
    private static final String firmware = "Service Pack for ProLiant";
    private static final String resourceId = "86ccffe5-4f39-48c7-aa49-5a7cb99f452e";
    private static final String enclosureResourceId = "09SGH100X6J1";
    // ================================

    private LogicalEnclosureClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.logicalEnclosureClient = LogicalEnclosureClientImpl.getClient();
        this.firmwareDriverClient = FirmwareDriverClientImpl.getClient();
        this.enclosureGroupClient = oneViewClient.enclosureGroup();
    }

    private void getLogicalEnclosureById() throws InstantiationException, IllegalAccessException {
        LogicalEnclosure logicalEnclosureDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalEnclosureDto = logicalEnclosureClient.getLogicalEnclosure(params, resourceId);

            System.out.println("LogicalEnclosureClientTest : getLogicalEnclosureById : logical enclosure object returned to client : "
                    + logicalEnclosureDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureById : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureById : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureById : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureById : arguments are null ");
            return;
        }

    }

    private void getAllLogicalEnclosures() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<LogicalEnclosure> enclosureCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureCollectionDto = logicalEnclosureClient.getAllLogicalEnclosures(params);

            System.out.println("LogicalLogicalEnclosureClientTest : getAllLogicalEnclosures : enclosure object returned to client : "
                    + enclosureCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getAllLogicalEnclosures : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getAllLogicalEnclosures : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalLogicalEnclosureClientTest : getAllLogicalEnclosures : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getAllLogicalEnclosures : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getAllLogicalEnclosures : arguments are null ");
            return;
        }

    }

    private void getLogicalEnclosureByName() throws InstantiationException, IllegalAccessException {
        LogicalEnclosure logicalEnclosureDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalEnclosureDto = logicalEnclosureClient.getLogicalEnclosureByName(params, resourceName);

            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureByName : enclosure object returned to client : "
                    + logicalEnclosureDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : getLogicalEnclosureByName : arguments are null ");
            return;
        }

    }

    private void createLogicalEnclosure() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final AddLogicalEnclosure addLogicalEnclosureDto = buildTestLogicalEnclosureDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalEnclosureClient.createLogicalEnclosure(params, addLogicalEnclosureDto, false, false);

            System.out.println("LogicalLogicalEnclosureClientTest : createLogicalEnclosure : enclosure object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : createLogicalEnclosure : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : createLogicalEnclosure : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : createLogicalEnclosure : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : createLogicalEnclosure : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : createLogicalEnclosure : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("LogicalEnclosureClientTest : createLogicalEnclosure : errors in task, please check task resource for more details ");
            return;
        }

    }

    private void updateLogicalEnclosure() throws InstantiationException, IllegalAccessException {
        String logicalEnclosureResourceId = null;
        LogicalEnclosure logicalEnclosureDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            logicalEnclosureDto = logicalEnclosureClient.getLogicalEnclosureByName(params, resourceName);

            if (null != logicalEnclosureDto.getUri()) {
                logicalEnclosureResourceId = UrlUtils.getResourceIdFromUri(logicalEnclosureDto.getUri());
            }
            logicalEnclosureDto.setName(resourceName + "_Updated");

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalEnclosureClient.updateLogicalEnclosure(params, logicalEnclosureResourceId, logicalEnclosureDto, false, false);

            System.out.println("LogicalEnclosureClientTest : updateEnclosure : Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalEnclosureClientTest : updateEnclosure : resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalEnclosureClientTest : updateEnclosure : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : updateEnclosure : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : updateEnclosure : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalEnclosureClientTest : updateEnclosure : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : updateEnclosure : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("LogicalEnclosureClientTest : updateEnclosure : errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void patchLogicalEnclosure() throws InstantiationException, IllegalAccessException {
        String logicalEnclosureResourceId = null;
        LogicalEnclosure logicalEnclosureDto = null;
        Patch patchDto = new Patch();
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            logicalEnclosureDto = logicalEnclosureClient.getLogicalEnclosureByName(params, resourceName);

            if (null != logicalEnclosureDto.getUri()) {
                logicalEnclosureResourceId = UrlUtils.getResourceIdFromUri(logicalEnclosureDto.getUri());
            }

            // Enclosure patch supports the update of Name and Rack Name
            patchDto.setOp(PatchOperation.replace);
            patchDto.setPath("/firmware");
            patchDto.setValue(new PatchFirmwareValue(
                    logicalEnclosureDto.getFirmware().getFirmwareBaselineUri(),
                    FirmwareUpdateOn.EnclosureOnly,
                    true));

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalEnclosureClient.patchLogicalEnclosure(params, logicalEnclosureResourceId, patchDto, false, false);

            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("LogicalEnclosureClientTest : patchLogicalEnclosure : errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void deleteLogicalEnclosure() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalEnclosureClient.getId(params, resourceName);

            // make sdk service call to get resource
            taskResourceV2 = logicalEnclosureClient.deleteLogicalEnclosure(params, resourceId, false);

            System.out.println("LogicalEnclosureClientTest : deleteLogicalEnclosure : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalEnclosureClientTest : deleteLogicalEnclosure : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : deleteLogicalEnclosure : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : deleteLogicalEnclosure : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalEnclosureClientTest : deleteLogicalEnclosure : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : deleteLogicalEnclosure : arguments are null ");
            return;
        }

    }

    private void updateFromGroup() throws InstantiationException, IllegalAccessException {
        TaskResourceV2 taskResourceV2 = null;
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalEnclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = logicalEnclosureClient.updateFromGroup(params, resourceId, false);

            System.out.println("LogicalEnclosureClientTest : updateFromGroup : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalEnclosureClientTest : updateFromGroup : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : updateFromGroup : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("LogicalEnclosureClientTest : updateFromGroup : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("LogicalEnclosureClientTest : updateFromGroup : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : updateFromGroup : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("LogicalEnclosureClientTest : updateFromGroup : errors in task, please check task resource for more details ");
            System.out.println("Task Errors: " + Arrays.toString(e.getMessageParameters()));
            System.out.println("Task Recomendations: " + Arrays.toString(e.getRecommendedActionsParameters()));
            return;
        }

    }

    private void updateConfiguration() throws InstantiationException, IllegalAccessException {
        TaskResourceV2 taskResourceV2 = null;
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalEnclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = logicalEnclosureClient.updateConfiguration(params, resourceId, false);

            System.out.println("LogicalEnclosureClientTest : updateConfiguration : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalEnclosureClientTest : updateConfiguration : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : updateConfiguration : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : updateConfiguration : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalEnclosureClientTest : updateConfiguration : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : updateConfiguration : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("LogicalEnclosureClientTest : updateConfiguration : errors in task, please check task resource for more details ");
            System.out.println("Task Errors: " + Arrays.toString(e.getMessageParameters()));
            System.out.println("Task Recomendations: " + Arrays.toString(e.getRecommendedActionsParameters()));
            return;
        }

    }

    private void getScript() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        String script = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalEnclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            script = logicalEnclosureClient.getScript(params, resourceId);

            System.out.println("LogicalEnclosureClientTest : getScript : enclosure script returned to client : " + script);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalEnclosureClientTest : getScript : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : getScript : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : getScript : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalEnclosureClientTest : getScript : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : getScript : arguments are null ");
            return;
        }

    }

    private void updateScript() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = logicalEnclosureClient.getId(params, resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalEnclosureClient.updateScript(params, resourceId, "\"name=Enclosure_test_two\"", false);

            System.out.println("LogicalEnclosureClientTest : updateScript : Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalEnclosureClientTest : updateScript : resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalEnclosureClientTest : updateScript : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : updateScript : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : updateScript : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalEnclosureClientTest : updateScript : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : updateScript : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("LogicalEnclosureClientTest : updateScript : errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void createSupportDump() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final SupportDump supportDumpDto = new SupportDump("testDump01", true, false);

            // get resource ID
            String logicalEnclosureId = logicalEnclosureClient.getId(params, resourceName);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalEnclosureClient.createSupportDump(params, supportDumpDto, logicalEnclosureId, false, false);

            System.out.println("LogicalLogicalEnclosureClientTest : createSupportDump : task object returned to client : "
                    + taskResourceV2.toString());
            System.out.println("Support Dump download link: https://" + params.getHostname() + taskResourceV2.getAssociatedResource().getResourceUri());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : createSupportDump : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalLogicalEnclosureClientTest : createSupportDump : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalEnclosureClientTest : createSupportDump : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalEnclosureClientTest : createSupportDump : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalEnclosureClientTest : createSupportDump : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("LogicalEnclosureClientTest : createSupportDump : errors in task, please check task resource for more details ");
            return;
        }

    }

    private AddLogicalEnclosure buildTestLogicalEnclosureDto() {
        final EnclosureGroup enclosureGroup = enclosureGroupClient.getByName(enclosureGroupName).get(0);
        final AddLogicalEnclosure dto = new AddLogicalEnclosure();
        dto.setName(resourceName);
        dto.setEnclosureGroupUri(enclosureGroup.getUri());
        dto.setEnclosureUris(Arrays.asList("/rest/enclosures/" + enclosureResourceId));
        dto.setFirmwareBaselineUri(firmwareDriverClient.getFirmwareDriverByName(params, firmware).getUri());
        dto.setForceInstallFirmware(false);
        return dto;
    }

    public static void main(final String[] args) throws Exception {
        LogicalEnclosureClientSample client = new LogicalEnclosureClientSample();

        client.getLogicalEnclosureById();
        client.getAllLogicalEnclosures();
        client.getLogicalEnclosureByName();

        client.updateFromGroup();
        client.updateConfiguration();
        client.updateLogicalEnclosure();

        client.getScript();
        client.updateScript();
        client.createSupportDump();

        client.patchLogicalEnclosure();

        client.createLogicalEnclosure(); // only in 3.0
        client.deleteLogicalEnclosure(); // only in 3.0
    }
}
