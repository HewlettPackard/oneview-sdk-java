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
package com.hp.ov.sdk.logicalinterconnectgroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.dto.DesiredSpeed;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Enclosures;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.dto.generated.Location;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.PortConfigInfo;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.dto.generated.UplinkSets.ConnectionMode;
import com.hp.ov.sdk.dto.generated.UplinkSets.ManualLoginRedistributionState;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.EnclosureClient;
import com.hp.ov.sdk.rest.client.EnclosureClientImpl;
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.FcNetworkClientImpl;
import com.hp.ov.sdk.rest.client.InterconnectsClient;
import com.hp.ov.sdk.rest.client.InterconnectsClientImpl;
import com.hp.ov.sdk.rest.client.UplinkSetClient;
import com.hp.ov.sdk.rest.client.UplinkSetClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * UplinkSetClientSample is a sample program to assign/consume networks of HPE OneView to uplink ports of interconnect.
 * It invokes APIs of UplinkSetClient which is in sdk library to perform GET/PUT/POST/DELETE operations
 * on uplink set resource
 */
public class UplinkSetClientSample {

    private final UplinkSetClient uplinkSetClient;
    private final FcNetworkClient fcNetworkClient;
    private final EnclosureClient enclosureClient;
    private final InterconnectsClient interconnectClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "Test_uplink_eth_one";
    private static final String resourceId = "7cf7240e-6bb3-4b5a-87e1-843d6e00ba3a";
    private static final String category = "logical-interconnects";
    private static final List<String> fcNetworkName_A = Arrays.asList("FC_Network_A");
    private static final String type = "uplink-setV2";
    private static final String typeV200 = "uplink-setV3";
    private static final String enclosureName = "Encl1";
    private static final String portValue = "X3";
    private static final String bayValue = "2";
    // ================================
    private static final int API_200 = 200;

    private UplinkSetClientSample() {
        uplinkSetClient = UplinkSetClientImpl.getClient();
        fcNetworkClient = FcNetworkClientImpl.getClient();
        enclosureClient = EnclosureClientImpl.getClient();
        interconnectClient = InterconnectsClientImpl.getClient();
    }

    private void getUplinkSetById() throws InstantiationException, IllegalAccessException {
        UplinkSets uplinkSetDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            uplinkSetDto = uplinkSetClient.getUplinkSet(params, resourceId);

            System.out.println("UplinkSetClientTest : " + "getUplinkSetById : uplink set object returned to client : "
                    + uplinkSetDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UplinkSetClientTest : " + "getUplinkSetById : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UplinkSetClientTest : getUplinkSetById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("UplinkSetClientTest : " + "getUplinkSetById : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("UplinkSetClientTest : " + "getUplinkSetById : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UplinkSetClientTest : " + "getUplinkSetById : arguments are null ");
            return;
        }

    }

    private void getAllUplinkSet() throws InstantiationException, IllegalAccessException {
        ResourceCollection<UplinkSets> uplinkSetCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            uplinkSetCollectionDto = uplinkSetClient.getAllUplinkSet(params);

            System.out.println("UplinkSetClientTest : getAllUplinkSet " + ": uplink set object returned to client : "
                    + uplinkSetCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UplinkSetClientTest : " + "getAllUplinkSet : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UplinkSetClientTest : " + "getAllUplinkSet : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UplinkSetClientTest : " + "getAllUplinkSet : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("UplinkSetClientTest : " + "getAllUplinkSet : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UplinkSetClientTest : " + "getAllUplinkSet : arguments are null ");
            return;
        }

    }

    private void getUplinkSetByName() throws InstantiationException, IllegalAccessException {
        UplinkSets uplinkSetDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            uplinkSetDto = uplinkSetClient.getUplinkSetsByName(params, resourceName);

            System.out.println("UplinkSetClientTest : getUplinkSetByName :" + " uplink set object returned to client : "
                    + uplinkSetDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UplinkSetClientTest : getUplinkSetByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UplinkSetClientTest : getUplinkSetByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UplinkSetClientTest : getUplinkSetByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("UplinkSetClientTest : getUplinkSetByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UplinkSetClientTest : getUplinkSetByName :" + " arguments are null ");
            return;
        }
    }

    private void deleteUplinkSet() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = uplinkSetClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = uplinkSetClient.deleteUplinkSet(params, resourceId, false);

            System.out.println("UplinkSetClientTest : deleteUplinkSet : uplink set deleted, task status : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UplinkSetClientTest : deleteUplinkSet : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UplinkSetClientTest : deleteUplinkSet : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UplinkSetClientTest : deleteUplinkSet : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("UplinkSetClientTest : deleteUplinkSet : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UplinkSetClientTest : deleteUplinkSet : arguments are null ");
            return;
        }

    }

    private void updateUplinkSet() throws InstantiationException, IllegalAccessException {
        UplinkSets uplinkSetDto = null;
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            uplinkSetDto = uplinkSetClient.getUplinkSetsByName(params, resourceName);
            // fetch uplinkset uri
            if (null != uplinkSetDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(uplinkSetDto.getUri());
            }

            // Change updateSetName
            uplinkSetDto.setName(resourceName + "_Updated");

            // update uplink set
            taskResourceV2 = uplinkSetClient.updateUplinkSet(params, resourceId, uplinkSetDto, false, false);

            System.out.println("UplinkSetClientTest :  updateUplinkSet : uplink set updated, task status : "
                    + taskResourceV2.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UplinkSetClientTest : " + "updateUplinkSet : resource you are looking is not found for update");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("UplinkSetClientTest : updateUplinkSet :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UplinkSetClientTest : updateUplinkSet : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UplinkSetClientTest : " + "updateUplinkSet : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("UplinkSetClientTest : " + "updateUplinkSet : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UplinkSetClientTest : " + "updateUplinkSet : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("UplinkSetClientTest : updateUplinkSet : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void createUplinkSet() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            UplinkSets uplinkSetsDto = buildTestUplinkSetDto();
            if (params.getApiVersion() >= API_200) {
                uplinkSetsDto.setType(typeV200);
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = uplinkSetClient.createUplinkSet(params, uplinkSetsDto, false, false);

            System.out.println("UplinkSetClientTest : createUplinkSet : uplink set object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UplinkSetClientTest : createUplinkSet : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("UplinkSetClientTest : createUplinkSet : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UplinkSetClientTest : createUplinkSet : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UplinkSetClientTest : createUplinkSet : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UplinkSetClientTest : createUplinkSet : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("UplinkSetClientTest : createUplinkSet : errors in task, please check task resource for more details ");
            return;
        }
    }

    private UplinkSets buildTestUplinkSetDto() {
        String resourceId = null;
        UplinkSets uplinkSetsDto = new UplinkSets();

        uplinkSetsDto.setCategory(category);
        Enclosures enclosuresDto = enclosureClient.getEnclosureByName(params, enclosureName);
        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(bayValue) == enclosuresDto.getInterconnectBays().get(i).getBayNumber()) {
                uplinkSetsDto.setLogicalInterconnectUri(enclosuresDto.getInterconnectBays().get(0).getLogicalInterconnectUri());
            }

        }
        uplinkSetsDto.setType(type);
        uplinkSetsDto.setConnectionMode(ConnectionMode.Auto);
        uplinkSetsDto.setEthernetNetworkType(UplinkSets.EthernetNetworkType.NotApplicable);
        uplinkSetsDto.setNetworkType(UplinkSets.NetworkType.FibreChannel);
        uplinkSetsDto.setName(resourceName);
        uplinkSetsDto.setManualLoginRedistributionState(ManualLoginRedistributionState.Supported);
        List<String> networkUris = new ArrayList<String>();
        for (int i = 0; i < fcNetworkName_A.size(); i++) {
            networkUris.add(fcNetworkClient.getFcNetworkByName(params, fcNetworkName_A.get(i)).getUri());
        }
        uplinkSetsDto.setFcNetworkUris(networkUris);

        List<PortConfigInfo> portConfigInfos = new ArrayList<PortConfigInfo>();
        PortConfigInfo portConfigInfo = new PortConfigInfo();
        portConfigInfo.setDesiredSpeed(DesiredSpeed.Auto);

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(bayValue) == enclosuresDto.getInterconnectBays().get(i).getBayNumber()) {
                if (null != enclosuresDto.getInterconnectBays().get(i).getInterconnectUri()) {
                    resourceId = UrlUtils.getResourceIdFromUri(enclosuresDto.getInterconnectBays().get(i).getInterconnectUri());
                }

                Interconnects interconnectsDto = interconnectClient.getInterconnectById(params, resourceId);
                for (int j = 0; j < interconnectsDto.getPortCount(); j++) {
                    if (interconnectsDto.getPorts().get(j).getPortName().equalsIgnoreCase(portValue)) {
                        portConfigInfo.setPortUri(interconnectsDto.getPorts().get(j).getUri());
                    }
                }

            }
        }
        Location location = new Location();
        List<LocationEntry> locationEntries = new ArrayList<LocationEntry>();
        LocationEntry locationEntry_two = new LocationEntry();
        LocationEntry locationEntry_one = new LocationEntry();
        locationEntry_one.setValue(enclosuresDto.getUri());
        locationEntry_one.setType(LocationEntry.Type.Enclosure);
        locationEntries.add(locationEntry_one);

        locationEntry_two.setValue(bayValue);
        locationEntry_two.setType(LocationEntry.Type.Bay);
        locationEntries.add(locationEntry_two);

        LocationEntry locationEntry_three = new LocationEntry();
        locationEntry_three.setValue(portValue);
        locationEntry_three.setType(LocationEntry.Type.Port);
        locationEntries.add(locationEntry_three);

        location.setLocationEntries(locationEntries);
        portConfigInfo.setLocation(location);
        portConfigInfos.add(portConfigInfo);
        uplinkSetsDto.setPortConfigInfos(portConfigInfos);

        return uplinkSetsDto;
    }

    public static void main(final String[] args) throws Exception {
        UplinkSetClientSample client = new UplinkSetClientSample();

        client.createUplinkSet();
        client.getAllUplinkSet();
        client.getUplinkSetByName();
        client.getUplinkSetById();
        client.updateUplinkSet();
        client.deleteUplinkSet();
    }
}
