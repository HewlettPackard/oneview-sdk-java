/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.logicalinterconnectgroup.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
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
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.InterconnectsClient;
import com.hp.ov.sdk.rest.client.UplinkSetClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class UplinkSetClientSample {
    private RestParams params;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static TaskResourceV2 taskResourceV2;
    private static UplinkSetClient uplinkSetClient;
    private static FcNetworkClient fcNetworkClient;
    private static EnclosureClient enclosureClient;
    private static InterconnectsClient interconnectClient;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "Test_uplink_eth_one";
    private static final String resourceId = "85a84db0-9045-4afa-9ef2-79d394c21559";
    private static final String category = "logical-interconnects";
    private static final List<String> fcNetworkName_A = Arrays.asList("FC_Network_D");
    private static final String type = "uplink-setV2";
    private static final String enclosureName = "Encl1";
    private static final String portValue = "X3";
    private static final String bayValue = "2";

    // ================================

    private static void init() {
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
        uplinkSetClient = HPOneViewSdkBeanFactory.getUplinkSetClient();
        fcNetworkClient = HPOneViewSdkBeanFactory.getFcNetworkClient();
        enclosureClient = HPOneViewSdkBeanFactory.getEnclosureClient();
        interconnectClient = HPOneViewSdkBeanFactory.getInterconnectsClient();
    }

    private void getUplinkSetById() throws InstantiationException, IllegalAccessException {
        UplinkSets uplinkSetDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
        UplinkSetCollectionV2 uplinkSetCollectionDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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

    private void deleteUplinkSet() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        UplinkSets uplinkSetDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch uplink set uri
            uplinkSetDto = uplinkSetClient.getUplinkSetsByName(params, resourceName);

            if (null != uplinkSetDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(uplinkSetDto.getUri());
            }

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            uplinkSetDto = uplinkSetClient.getUplinkSetsByName(params, resourceName);
            // fetch uplinkset uri
            if (null != uplinkSetDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(uplinkSetDto.getUri());
            }

            // Change updateSetName
            uplinkSetDto.setName(uplinkSetDto.getName());

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create network request body
            final UplinkSets uplinkSetsDto = buildTestUplinkSetDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = uplinkSetClient.createUplinkSet(params, uplinkSetsDto, false, false);

            System.out.println("UplinkSetClientTest : createUplinkSet : enclosure object returned to client : "
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
        portConfigInfo.setDesiredSpeed(PortConfigInfo.DesiredSpeed.Auto);

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(bayValue) == enclosuresDto.getInterconnectBays().get(i).getBayNumber()) {
                if (null != enclosuresDto.getInterconnectBays().get(i).getInterconnectUri()) {
                    resourceId = urlUtils.getResourceIdFromUri(enclosuresDto.getInterconnectBays().get(i).getInterconnectUri());
                }

                Interconnects interconnectsDto = interconnectClient.getInterconnects(params, resourceId);
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

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        UplinkSetClientSample client = new UplinkSetClientSample();
        client.getUplinkSetById();
        client.getAllUplinkSet();
        //client.updateUplinkSet();
        //client.createUplinkSet();
        //client.deleteUplinkSet();
    }

}
