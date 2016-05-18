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
package com.hp.ov.sdk.enclosuregroup;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StackingMode;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.dto.generated.InterconnectBayMapping;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.logicalinterconnectgroup.LogicalInterconnectGroupClientSample;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClientImpl;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * EnclosureGroupClientSample is a sample program enables/consume to set a common configuration across the enclosure
 * resources of HPE OneView. It invokes APIs of EnclosureGroupClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on enclosure group resource
 */
public class EnclosureGroupClientSample {

    private final LogicalInterconnectGroupClient logicalInterconnectGroupClient;
    private final EnclosureGroupClient enclosureGroupClient;

    private RestParams params;

    // test values - user input
    // ================================
    private static final String resourceName = "Enclosure_Test";
    private static final String logicalInterconnectName = "LIG_PROD";
    private static final String resourceId = "7383eb8d-52ad-4c44-aea3-dc138cc9adbc";
    private static final String scriptData = "name=Enclosure_test";
    // ================================

    public EnclosureGroupClientSample() {
        this.logicalInterconnectGroupClient = LogicalInterconnectGroupClientImpl.getClient();
        this.enclosureGroupClient = EnclosureGroupClientImpl.getClient();
    }

    private void getEnclosureGroupById() throws InstantiationException, IllegalAccessException {
        EnclosureGroups enclosureGroupDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroup(params, resourceId);

            System.out.println("EnclosureGroupClientTest : getEnclosureGroupById :"
                    + " enclosure group object returned to client : " + enclosureGroupDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupById :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupById :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupById :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupById :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupById :" + " arguments are null ");
            return;
        }
    }

    private void getAllEnclosureGroup() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<EnclosureGroups> enclosureGroupCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureGroupCollectionDto = enclosureGroupClient.getAllEnclosureGroups(params);

            System.out.println("EnclosureGroupClientTest : getAllEnclosureGroup :"
                    + " enclosure groups object returned to client : " + enclosureGroupCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureGroupClientTest : getAllEnclosureGroup " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : getAllEnclosureGroup :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : getAllEnclosureGroup :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureGroupClientTest : getAllEnclosureGroup :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : getAllEnclosureGroup :" + " arguments are null ");
            return;
        }
    }

    private void getEnclosureGroupByName() throws InstantiationException, IllegalAccessException {
        EnclosureGroups enclosureGroupDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroupByName(params, resourceName);

            System.out.println("EnclosureGroupClientTest : getEnclosureGroupByName :"
                    + " enclosure group object returned to client : " + enclosureGroupDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : getEnclosureGroupByName :" + " arguments are null ");
            return;
        }
    }

    private void createEnclosureGroup() throws InstantiationException, IllegalAccessException {
        EnclosureGroups enclosureGroupReturnDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create enclosure group request body
            final EnclosureGroups enclosureGroupDto = buildTestEnclosureGroupDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            enclosureGroupReturnDto = enclosureGroupClient.createEnclosureGroup(params, enclosureGroupDto, false);

            System.out.println("EnclosureGroupClientTest : createEnclosureGroup : enclosure group object returned to client : "
                    + enclosureGroupReturnDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureGroupClientTest : createEnclosureGroup : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureGroupClientTest : createEnclosureGroup : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : createEnclosureGroup : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : createEnclosureGroup : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : createEnclosureGroup : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("EnclosureGroupClientTest : createEnclosureGroup : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void updateEnclosureGroup() throws InstantiationException, IllegalAccessException {
        EnclosureGroups enclosureGroupReturnDto = null;
        String resourceId = null;
        EnclosureGroups enclosureGroupDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroupByName(params, resourceName);

            enclosureGroupDto.setName(resourceName + "_updated");

            if (null != enclosureGroupDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(enclosureGroupDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            enclosureGroupReturnDto = enclosureGroupClient.updateEnclosureGroup(params, resourceId, enclosureGroupDto, false);

            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup : "
                    + "Enclosure group object returned to client : " + enclosureGroupReturnDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("EnclosureGroupClientTest : updateEnclosureGroup : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteEnclosureGroup() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String deleteMsg = null;
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = enclosureGroupClient.getId(params, resourceName);

            // then make sdk service call to get resource
            deleteMsg = enclosureGroupClient.deleteEnclosureGroup(params, resourceId);

            System.out.println("EnclosureGroupClientTest : deleteEnclosureGroup : "
                    + "enclosure group object returned to client : " + deleteMsg);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureGroupClientTest : deleteEnclosureGroupSet :"
                    + " resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : deleteEnclosureGroupSet :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : deleteEnclosureGroup :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureGroupClientTest : deleteEnclosureGroup : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : deleteEnclosureGroup :" + " arguments are null ");
            return;
        }
    }

    private void getConfigurationScript() {
        String enclosureScript = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            enclosureScript = enclosureGroupClient.getConfigurationScript(params, resourceId);

            System.out.println("EnclosureGroupClientTest : getConfigurationScript :" + " enclosure script returned to client : "
                    + enclosureScript);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("EnclosureGroupClientTest : getConfigurationScript :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : getConfigurationScript :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : getConfigurationScript :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureGroupClientTest : getConfigurationScript :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : getConfigurationScript :" + " arguments are null ");
            return;
        }
    }

    private void updateConfigurationScript() throws InstantiationException, IllegalAccessException {
        String enclosureScript = null;
        EnclosureGroups enclosureGroupDto = null;
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroupByName(params, resourceName);

            if (null != enclosureGroupDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(enclosureGroupDto.getUri());
            }

            // then make sdk service call to get resource
            enclosureScript = enclosureGroupClient.updateConfigurationScript(params, resourceId, scriptData);

            System.out.println("EnclosureGroupClientTest : updateConfigurationScript :" + " enclosure script returned to client : "
                    + enclosureScript);
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("EnclosureGroupClientTest : updateConfigurationScript :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("EnclosureGroupClientTest : updateConfigurationScript :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("EnclosureGroupClientTest : updateConfigurationScript :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("EnclosureGroupClientTest : updateConfigurationScript :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("EnclosureGroupClientTest : updateConfigurationScript :" + " arguments are null ");
            return;
        }
    }

    private EnclosureGroups buildTestEnclosureGroupDto() {
        // fetch resource Id using resource name
        final String logicalInterconnectGroupUri = logicalInterconnectGroupClient.getLogicalInterconnectGroupByName(params,
                logicalInterconnectName).getUri();

        final EnclosureGroups dto = new EnclosureGroups();
        dto.setType(ResourceCategory.RC_ENCLOSURE_GROUP); //OneView 1.2
        dto.setType(ResourceCategory.RC_ENCLOSURE_GROUP_V200); //OneView 2.0
        dto.setName(resourceName);
        dto.setStackingMode(StackingMode.Enclosure);
        final List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();

        for (int i = 0; i < 8; i++) {
            InterconnectBayMapping interconnectBayMapping = new InterconnectBayMapping();
            int interconnectBay = i + 1;

            interconnectBayMapping.setInterconnectBay(interconnectBay);

            if (LogicalInterconnectGroupClientSample.interconnectEntries.contains(Integer.valueOf(interconnectBay))) {
                interconnectBayMapping.setLogicalInterconnectGroupUri(logicalInterconnectGroupUri);
            }
            interconnectBayMappings.add(interconnectBayMapping);
        }
        dto.setInterconnectBayMappings(interconnectBayMappings);
        dto.setInterconnectBayMappingCount(dto.getInterconnectBayMappings().size());

        dto.setEnclosureCount(1);

        return dto;
    }

    public static void main(final String[] args) throws Exception {
        EnclosureGroupClientSample client = new EnclosureGroupClientSample();

        client.getAllEnclosureGroup();
        client.createEnclosureGroup();
        client.getEnclosureGroupById();
        client.getEnclosureGroupByName();
        client.updateConfigurationScript();
        client.getConfigurationScript();
        client.updateEnclosureGroup();
        client.createEnclosureGroup();
        client.deleteEnclosureGroup();
    }
}
