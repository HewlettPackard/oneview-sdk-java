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
package com.hp.ov.sdk.enclosure;

import java.util.Arrays;

import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.FwBaselineConfig;
import com.hp.ov.sdk.dto.FwBaselineOptions;
import com.hp.ov.sdk.dto.LicensingIntent;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.RefreshStateConfig;
import com.hp.ov.sdk.dto.RefreshStateConfig.RefreshForceOptions;
import com.hp.ov.sdk.dto.SsoUrlData;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.EnclosureClient;
import com.hp.ov.sdk.rest.client.EnclosureClientImpl;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClientImpl;
import com.hp.ov.sdk.rest.client.FirmwareDriverClient;
import com.hp.ov.sdk.rest.client.FirmwareDriverClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * EnclosureClientSample is a sample program to consume the REST API of c7000 enclosure managed
 * by HP OneView. It invokes APIs of EnclosureClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on enclosure resource
 */
public class EnclosureClientSample {

    private final EnclosureClient enclosureClient;
    private final FirmwareDriverClient firmwareDriverClient;
    private final EnclosureGroupClient enclosureGroupClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String resourceName = "Encl1";
    private static final String enclosureGroupName = "Enclosure_Test";
    private static final String hostname = "172.18.1.11";
    private static final String username = "dcs";
    private static final String password = "dcs";
    private static final String firmware = "Service Pack for ProLiant";
    private static final String resourceId = "09SGH100X6J1";
    // ================================

    private EnclosureClientSample() {
        this.enclosureClient = EnclosureClientImpl.getClient();
        this.firmwareDriverClient = FirmwareDriverClientImpl.getClient();
        this.enclosureGroupClient = EnclosureGroupClientImpl.getClient();
    }

    private void getEnclosureById() throws InstantiationException, IllegalAccessException {
        Enclosures enclosureDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureDto = enclosureClient.getEnclosure(params, resourceId);

            System.out.println("EnclosureClientTest : getEnclosureById :" + " enclosure object returned to client : "
                    + enclosureDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getEnclosureById :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getEnclosureById :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("EnclosureClientTest : getEnclosureById :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("EnclosureClientTest : getEnclosureById :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getEnclosureById :" + " arguments are null ");
            return;
        }

    }

    private void getAllEnclosure() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        EnclosureCollectionV2 enclosureCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureCollectionDto = enclosureClient.getAllEnclosures(params);

            System.out.println("EnclosureClientTest : getAllEnclosure :" + " enclosure object returned to client : "
                    + enclosureCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getAllEnclosure :" + " arguments are null ");
            return;
        }

    }

    private void getEnclosureByName() throws InstantiationException, IllegalAccessException {
        Enclosures enclosureDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureDto = enclosureClient.getEnclosureByName(params, resourceName);

            System.out.println("EnclosureClientTest : getEnclosureByName :" + " enclosure object returned to client : "
                    + enclosureDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getEnclosureByName :" + " arguments are null ");
            return;
        }

    }

    private void createEnclosure() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final AddEnclosureV2 addEnclosureDto = buildTestEnclosureDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.createEnclosure(params, addEnclosureDto, false, false);

            System.out.println("EnclosureClientTest : createEnclosure : enclosure object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : createEnclosure : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : createEnclosure : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : createEnclosure : errors in task, please check task resource for more details ");
            System.out.println("Task Errors: " + Arrays.toString(e.getMessageParameters()));
            System.out.println("Task Recomendations: " + Arrays.toString(e.getRecommendedActionsParameters()));
            return;
        }

    }

    private void updateEnclosure() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        Enclosures enclosureDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            enclosureDto = enclosureClient.getEnclosureByName(params, resourceName);

            if (null != enclosureDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(enclosureDto.getUri());
            }
            enclosureDto.setName(resourceName + "_Updated");

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.updateEnclosure(params, resourceId, enclosureDto, false, false);

            System.out.println("EnclosureClientTest : updateEnclosure : " + "Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : updateEnclosure : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void patchEnclosure() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        Enclosures enclosureDto = null;
        Patch patchDto = new Patch();
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            enclosureDto = enclosureClient.getEnclosureByName(params, resourceName);

            if (null != enclosureDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(enclosureDto.getUri());
            }

            // Enclosure patch supports the update of Name and Rack Name
            patchDto.setOp(PatchOperation.replace);
            patchDto.setPath("/name");
            patchDto.setValue(resourceName + "_Updated");

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.patchEnclosure(params, resourceId, patchDto, false);

            System.out.println("EnclosureClientTest : updateEnclosure : " + "Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateEnclosure : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : updateEnclosure : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void deleteEnclosure() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            // make sdk service call to get resource
            taskResourceV2 = enclosureClient.deleteEnclosure(params, resourceId, false);

            System.out.println("EnclosureClientTest : deleteEnclosure : " + "enclosure object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosureSet :" + " resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosureSet :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : deleteEnclosure :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosure : " + "No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : deleteEnclosure :" + " arguments are null ");
            return;
        }

    }

    private void getActiveOaSsoUrl() throws InstantiationException, IllegalAccessException {
        SsoUrlData ssoUrlDataDto = null;
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            ssoUrlDataDto = enclosureClient.getActiveOaSsoUrl(params, resourceId);

            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " enclosure sso url data object returned to client : "
                    + ssoUrlDataDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getActiveOaSsoUrl :" + " arguments are null ");
            return;
        }

    }

    private void updateCompliance() throws InstantiationException, IllegalAccessException {
        TaskResourceV2 taskResourceV2 = null;
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = enclosureClient.updateCompliance(params, resourceId, false);

            System.out.println("EnclosureClientTest : updateCompliance :" + " task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateCompliance :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateCompliance :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("EnclosureClientTest : updateCompliance :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("EnclosureClientTest : updateCompliance :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateCompliance :" + " arguments are null ");
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
            resourceId = enclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = enclosureClient.updateConfiguration(params, resourceId, false);

            System.out.println("EnclosureClientTest : updateConfiguration :" + " task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateConfiguration :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateConfiguration :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateConfiguration :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateConfiguration :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateConfiguration :" + " arguments are null ");
            return;
        }

    }

    private void updateEnclosureFwBaseline() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        FwBaselineConfig fwBaselineConfigDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            fwBaselineConfigDto = buildFwBaselineConfig();

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.updateEnclosureFwBaseline(params, resourceId, fwBaselineConfigDto, false);

            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline : " + "Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : updateEnclosureFwBaseline : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void getEnvironmentalConfiguration() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        EnvironmentalConfiguration environmentalConfigurationDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            environmentalConfigurationDto = enclosureClient.getEnvironmentalConfiguration(params, resourceId);

            System.out.println("EnclosureClientTest : getEnvironmentalConfiguration :"
                    + " enclosure environmental configuration object returned to client : "
                    + environmentalConfigurationDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getEnvironmentalConfiguration :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getEnvironmentalConfiguration :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getEnvironmentalConfiguration :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getEnvironmentalConfiguration :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getEnvironmentalConfiguration :" + " arguments are null ");
            return;
        }

    }

    private void updateEnvironmentalConfiguration() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        EnvironmentalConfiguration environmentalConfigurationDto = null;
        EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            environmentalConfigurationUpdateDto = buildEnvironmentalConfigurationUpdateConfig();

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            environmentalConfigurationDto = enclosureClient.updateEnvironmentalConfiguration(params, resourceId,
                    environmentalConfigurationUpdateDto);

            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration : "
                    + "Enclosure task object returned to client : " + environmentalConfigurationDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : updateEnvironmentalConfiguration : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void updateRefreshState() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            RefreshStateConfig refreshStateConfigDto = buildRefreshStateConfig();

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.updateRefreshState(params, resourceId, refreshStateConfigDto, false);

            System.out.println("EnclosureClientTest : updateRefreshState : " + "Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateRefreshState :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : updateRefreshState :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateRefreshState :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateRefreshState :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateRefreshState :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateRefreshState : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : updateRefreshState : " + "errors in task, please check task "
                    + "resource for more details ");
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
            resourceId = enclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            script = enclosureClient.getScript(params, resourceId);

            System.out.println("EnclosureClientTest : getScript :"
                    + " enclosure environmental configuration object returned to client : " + script);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getScript :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getScript :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getScript :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getScript :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getScript :" + " arguments are null ");
            return;
        }

    }

    private void updateScript() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.updateScript(params, resourceId, "name=Enclosure_test_two", false, false);

            System.out.println("EnclosureClientTest : updateScript : " + "Enclosure task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : updateScript :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureClientTest : updateScript :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : updateScript :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : updateScript :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : updateScript :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : updateScript : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureClientTest : updateScript : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    public void getStandbyOaSsoUrl() throws InstantiationException, IllegalAccessException {
        SsoUrlData ssoUrlDataDto = null;
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            ssoUrlDataDto = enclosureClient.getStandbyOaSsoUrl(params, resourceId);

            System.out.println("EnclosureClientTest : getStandbyOaSsoUrl :"
                    + " enclosure sso url data object returned to client : " + ssoUrlDataDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getStandbyOaSsoUrl :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getStandbyOaSsoUrl :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getStandbyOaSsoUrl :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getStandbyOaSsoUrl :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getStandbyOaSsoUrl :" + " arguments are null ");
            return;
        }

    }

    public void getUtilization() throws InstantiationException, IllegalAccessException {
        UtilizationData utilizationDataDto = null;
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureClient.getId(params, resourceName);

            // then make sdk service call to get resource
            params.getQuery().clear();
            utilizationDataDto = enclosureClient.getUtilization(params, resourceId);

            System.out.println("EnclosureClientTest : getUtilization :"
                    + " enclosure utilization data object returned to client : " + utilizationDataDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureClientTest : getUtilization :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureClientTest : getUtilization :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureClientTest : getUtilization :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureClientTest : getUtilization :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureClientTest : getUtilization :" + " arguments are null ");
            return;
        }

    }

    private AddEnclosureV2 buildTestEnclosureDto() {

        final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();
        final AddEnclosureV2 dto = new AddEnclosureV2();
        dto.setHostname(hostname);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setLicensingIntent(LicensingIntent.OneView);
        dto.setForce(false);
        dto.setEnclosureGroupUri(enclosureGroupUri);
        dto.setFirmwareBaselineUri(firmwareDriverClient.getFirmwareDriverByName(params, firmware).getUri());
        dto.setUpdateFirmwareOn(FwBaselineOptions.EnclosureOnly);
        dto.setForceInstallFirmware(false);
        return dto;
    }

    private FwBaselineConfig buildFwBaselineConfig() {
        FwBaselineConfig fwBaselineConfigDto = new FwBaselineConfig();

        fwBaselineConfigDto.setFwBaselineUri(firmwareDriverClient.getFirmwareDriverByName(params, firmware).getUri());
        fwBaselineConfigDto.setIsFwManaged(true);
        fwBaselineConfigDto.setFirmwareUpdateOn("EnclosureOnly");
        fwBaselineConfigDto.setForceInstallFirmware(false);

        return fwBaselineConfigDto;
    }

    private EnvironmentalConfigurationUpdate buildEnvironmentalConfigurationUpdateConfig() {
        EnvironmentalConfigurationUpdate environmentalConfigurationUpdateDto = new EnvironmentalConfigurationUpdate();
        environmentalConfigurationUpdateDto.setCalibratedMaxPower(2500);

        return environmentalConfigurationUpdateDto;
    }

    private RefreshStateConfig buildRefreshStateConfig() {
        RefreshStateConfig refreshStateConfigDto = new RefreshStateConfig();
        RefreshForceOptions refreshForceOptions = refreshStateConfigDto.getNewRefreshForceOptions();
        refreshForceOptions.setAddress(hostname);
        refreshForceOptions.setUsername(username);
        refreshForceOptions.setPassword(password);
        refreshStateConfigDto.setRefreshForceOptions(refreshForceOptions);
        refreshStateConfigDto.setRefreshState(RefreshState.RefreshPending);

        return refreshStateConfigDto;
    }

    public static void main(final String[] args) throws Exception {
        EnclosureClientSample client = new EnclosureClientSample();

        client.getAllEnclosure();
        client.createEnclosure();
        client.getEnclosureById();
        client.getActiveOaSsoUrl();
        client.getEnclosureByName();
        client.getActiveOaSsoUrl();
        client.updateConfiguration();

        client.updateCompliance(); // For 2.0 use LogicalEnclosure.updateFromGroup() method
        client.updateEnclosureFwBaseline(); // For 2.0 use LogicalEnclosure.patch() method

        client.getEnvironmentalConfiguration();
        // Check if the privilege exists
        client.updateEnvironmentalConfiguration();

        client.updateRefreshState();

        client.updateScript(); // For 2.0 use LogicalEnclosure.updateScript() method

        client.getScript();
        client.getStandbyOaSsoUrl();
        client.getUtilization();

        client.updateEnclosure(); // For 2.0 use patchEnclosure() method

        client.patchEnclosure();

        client.deleteEnclosure();
    }
}
