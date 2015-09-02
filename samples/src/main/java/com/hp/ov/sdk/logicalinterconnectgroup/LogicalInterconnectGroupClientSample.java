/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.logicalinterconnectgroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.hp.ov.sdk.dto.samples.UplinkSetValue;
import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;
import com.hp.ov.sdk.util.samples.ResourceDtoUtilsWrapper;

/*
 * LogicalInterconnectGroupClientSample is a sample program, acts as a recipe for representing the available networks, 
 * uplink sets, stacking links, and interconnect settings for a set of physical interconnects in a single enclosure 
 * of HP OneView to a logical interconnect group. It invokes APIs of LogicalInterconnectGroupClient which is in sdk 
 * library to perform GET/PUT/POST/DELETE operations on logical interconnect group resource
 */
public class LogicalInterconnectGroupClientSample {
    private RestParams params;
    private static LogicalInterconnectGroupClient logicalInterconnectGroupClient;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static ResourceDtoUtils resourceDtoUtils;
    private static TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String resourceName = "LIG_PROD";
    private static final String permittedInterconnectType = "HP VC FlexFabric-20/40 F8 Module";
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402", "Prod_403");
    private static final List<String> fcNetworkName_A = Arrays.asList("FC_Network_A");
    private static final List<String> fcNetworkName_B = Arrays.asList("FC_Network_B");
    private static final List<String> ethPort = Arrays.asList("X5", "X6");
    private static final List<String> fcPort = Arrays.asList("X2");
    private static final String ethUplinkSetType = "Ethernet";
    private static final String fcUplinkSetType = "FibreChannel";
    private static final String ethUplinkSetName = "EthernetUplinkSet";
    private static final String fcAUplinkSetName = "FCUplinkSetA";
    private static final String fcBUplinkSetName = "FCUplinkSetB";
    private static final String resourceId = "2b156954-dd0c-44f2-b602-a0328d19d34c";
    private static final String settingId = "2a69ad5d-a3e9-4713-a947-22900e30355a";

    // ================================

    private static void init() throws Exception {
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
        logicalInterconnectGroupClient = HPOneViewSdkBeanFactory.getLogicalInterconnectGroupClient();
        resourceDtoUtils = HPOneViewSdkBeanFactory.getResourceDtoUtils();
    }

    private void getLogicalInterconnectGroupById() throws InstantiationException, IllegalAccessException {
        LogicalInterconnectGroups logicalInterconnectGroupDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            logicalInterconnectGroupDto = logicalInterconnectGroupClient.getLogicalInterconnectGroup(params, resourceId);

            System.out.println("LogicalInterconnectGroupClientTest : " + "getLogicalInterconnectGroupById : logical interconnect"
                    + " group object returned to client : " + logicalInterconnectGroupDto.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getLogicalInterconnectGroupById : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getLogicalInterconnectGroupById : no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getLogicalInterconnectGroupById : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getLogicalInterconnectGroupById : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getLogicalInterconnectGroupById : arguments are null ");
            return;
        }

    }

    private void getAllLogicalInterconnectGroups() throws InstantiationException, IllegalAccessException {
        LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            logicalInterconnectGroupCollectionDto = logicalInterconnectGroupClient.getAllLogicalInterconnectGroups(params);

            System.out.println("LogicalInterconnectGroupClientTest : getAllLogicalInterconnectGroups "
                    + ": logical interconnect group object returned to client : "
                    + logicalInterconnectGroupCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getAllLogicalInterconnectGroups : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getAllLogicalInterconnectGroups : no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getAllLogicalInterconnectGroups : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getAllLogicalInterconnectGroups : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getAllLogicalInterconnectGroups : arguments are null ");
            return;
        }

    }

    private void getLogicalInterconnectGroupByName() throws InstantiationException, IllegalAccessException {
        LogicalInterconnectGroups logicalInterconnectGroupDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            logicalInterconnectGroupDto = logicalInterconnectGroupClient.getLogicalInterconnectGroupByName(params, resourceName);

            System.out.println("LogicalInterconnectGroupClientTest : " + "getLogicalInterconnectGroupByName : logical interconnect"
                    + " group object returned to client : " + logicalInterconnectGroupDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getLogicalInterconnectGroupByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getLogicalInterconnectGroupByName : no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getLogicalInterconnectGroupByName : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getLogicalInterconnectGroupByName : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getLogicalInterconnectGroupByName : arguments are null ");
            return;
        }

    }

    private void createLogicalInterconnectGroup() throws InstantiationException, IllegalAccessException {
        try {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create logicalInterconnectGroup request body
            final LogicalInterconnectGroups logicalInterconnectGroupDto = buildTestLogicalInterconnectGroup();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalInterconnectGroupClient.createLogicalInterconnectGroup(params, logicalInterconnectGroupDto,
                    false, false);

            System.out.println("LogicalInterconnectGroupClientTest : " + "createLogicalInterconnectGroup : status of "
                    + "logicalInterconnectGroup object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "createLogicalInterconnectGroup : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "createLogicalInterconnectGroup : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "createLogicalInterconnectGroup : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "createLogicalInterconnectGroup : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "createLogicalInterconnectGroup : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "createLogicalInterconnectGroup : errors in task, "
                    + "please check task resource for more details ");
            return;
        }
    }

    private void updateLogicalInterconnectGroup() throws InstantiationException, IllegalAccessException {
        LogicalInterconnectGroups logicalInterconnectGroups = null;
        String resourceId = null;
        try {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // get resource Id using resource name
            logicalInterconnectGroups = logicalInterconnectGroupClient.getLogicalInterconnectGroupByName(params, resourceName);

            if (null != logicalInterconnectGroups.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(logicalInterconnectGroups.getUri());
            }

            // create uplinksetDto request body
            final List<UplinkSet> uplinkSetDto = buildUplinkSetGroupDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalInterconnectGroupClient.updateLogicalInterconnectGroup(params, resourceId, uplinkSetDto, false,
                    false);

            System.out.println("LogicalInterconnectGroupClientTest : " + "updateLogicalInterconnectGroup : status of "
                    + "logicalInterconnectGroup object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "updateLogicalInterconnectGroup : resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "updateLogicalInterconnectGroup : bad request, try again : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "updateLogicalInterconnectGroup : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "updateLogicalInterconnectGroup : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "updateLogicalInterconnectGroup : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "updateLogicalInterconnectGroup : errors in task, "
                    + "please check task resource for more details ");
            return;
        }

    }

    private void deleteLogicalInterconnectGroup() throws InstantiationException, IllegalAccessException {
        LogicalInterconnectGroups logicalInterconnectGroups = null;
        String resourceId = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using name
            logicalInterconnectGroups = logicalInterconnectGroupClient.getLogicalInterconnectGroupByName(params, resourceName);

            if (null != logicalInterconnectGroups.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(logicalInterconnectGroups.getUri());
            }

            // then make sdk service call to get resource
            taskResourceV2 = logicalInterconnectGroupClient.deleteLogicalInterconnectGroup(params, resourceId, false);

            System.out
                    .println("LogicalInterconnectGroupClientTest : deleteLogicalInterconnectGroup : logical Interconnect group deleted status : "
                            + taskResourceV2.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("LogicalInterconnectGroupClientTest : deleteLogicalInterconnectGroup : resource not found to delete : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : deleteLogicalInterconnectGroup : no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("LogicalInterconnectGroupClientTest : deleteLogicalInterconnectGroup : Applicance Not reachabe at : "
                            + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("LogicalInterconnectGroupClientTest : deleteLogicalInterconnectGroup : No response from appliance : "
                            + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : deleteLogicalInterconnectGroup : arguments are null ");
            return;
        }
    }

    private void getDefaultInterconnectSettings() {
        InterconnectSettingsV2 interconnectSettingsDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            interconnectSettingsDto = logicalInterconnectGroupClient.getDefaultInterconnectSettings(params);

            System.out.println("LogicalInterconnectGroupClientTest : " + "getDefaultInterconnectSettings : logical interconnect"
                    + " setting returned to client : " + interconnectSettingsDto.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getDefaultInterconnectSettings : resource setting you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getDefaultInterconnectSettings : no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getDefaultInterconnectSettings : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getDefaultInterconnectSettings : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getDefaultInterconnectSettings : arguments are null ");
            return;
        }

    }

    private void getInterconnectSettings() {
        InterconnectSettingsV2 interconnectSettingsDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            interconnectSettingsDto = logicalInterconnectGroupClient.getInterconnectSettings(params, resourceId, settingId);

            System.out.println("LogicalInterconnectGroupClientTest : " + "getInterconnectSettings : logical interconnect"
                    + " setting returned to client : " + interconnectSettingsDto.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : "
                    + "getInterconnectSettings : resource setting you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getInterconnectSettings : no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getInterconnectSettings : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getInterconnectSettings : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalInterconnectGroupClientTest : " + "getInterconnectSettings : arguments are null ");
            return;
        }

    }

    private LogicalInterconnectGroups buildTestLogicalInterconnectGroup() {
        final HashMap<Integer, String> bayPermittedInterconnectMaps = new HashMap<Integer, String>();
        bayPermittedInterconnectMaps.put(1, permittedInterconnectType);
        bayPermittedInterconnectMaps.put(2, permittedInterconnectType);
        return resourceDtoUtils.buildLogicalInterconnectGroupDto(params, resourceName, bayPermittedInterconnectMaps);
    }

    private List<UplinkSet> buildUplinkSetGroupDto() {

        List<UplinkSet> uplinkSetGroupDto = new ArrayList<UplinkSet>();

        final HashMap<Integer, List<String>> ethBayPortMap = new HashMap<Integer, List<String>>();
        ethBayPortMap.put(1, ethPort);
        ethBayPortMap.put(2, ethPort);
        final HashMap<Integer, List<String>> fcBayPortMapA = new HashMap<Integer, List<String>>();
        fcBayPortMapA.put(1, fcPort);
        final HashMap<Integer, List<String>> fcBayPortMapB = new HashMap<Integer, List<String>>();
        fcBayPortMapB.put(2, fcPort);

        final List<UplinkSetValue> uplinkSetValues = new ArrayList<UplinkSetValue>();
        final UplinkSetValue ethUplinkSetValue = new UplinkSetValue();
        ethUplinkSetValue.setBayPortMap(ethBayPortMap);
        ethUplinkSetValue.setLigName(resourceName);
        ethUplinkSetValue.setNetworkNames(networkNames);
        ethUplinkSetValue.setUplinkSetName(ethUplinkSetName);
        ethUplinkSetValue.setUplinkSetType(ethUplinkSetType);

        final UplinkSetValue fcAUplinkSetValue = new UplinkSetValue();
        fcAUplinkSetValue.setBayPortMap(fcBayPortMapA);
        fcAUplinkSetValue.setLigName(resourceName);
        fcAUplinkSetValue.setNetworkNames(fcNetworkName_A);
        fcAUplinkSetValue.setUplinkSetName(fcAUplinkSetName);
        fcAUplinkSetValue.setUplinkSetType(fcUplinkSetType);

        final UplinkSetValue fcBUplinkSetValue = new UplinkSetValue();
        fcBUplinkSetValue.setBayPortMap(fcBayPortMapB);
        fcBUplinkSetValue.setLigName(resourceName);
        fcBUplinkSetValue.setNetworkNames(fcNetworkName_B);
        fcBUplinkSetValue.setUplinkSetName(fcBUplinkSetName);
        fcBUplinkSetValue.setUplinkSetType(fcUplinkSetType);

        uplinkSetValues.add(ethUplinkSetValue);
        uplinkSetValues.add(fcAUplinkSetValue);
        uplinkSetValues.add(fcBUplinkSetValue);

        ResourceDtoUtilsWrapper resourceDtoUtilsWrapper = new ResourceDtoUtilsWrapper();
        uplinkSetGroupDto = resourceDtoUtilsWrapper.buildUplinkSetGroupDto(params, uplinkSetValues);

        return uplinkSetGroupDto;
    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        LogicalInterconnectGroupClientSample client = new LogicalInterconnectGroupClientSample();

        client.getAllLogicalInterconnectGroups();
        client.getLogicalInterconnectGroupById();
        client.getDefaultInterconnectSettings();
        client.getInterconnectSettings();
        client.createLogicalInterconnectGroup();
        client.getLogicalInterconnectGroupByName();
        client.updateLogicalInterconnectGroup();
        client.deleteLogicalInterconnectGroup();
    }
}
