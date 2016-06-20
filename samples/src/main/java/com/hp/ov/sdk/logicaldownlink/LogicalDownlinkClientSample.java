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
package com.hp.ov.sdk.logicaldownlink;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.LogicalDownlink;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.LogicalDownlinkClient;
import com.hp.ov.sdk.rest.client.LogicalDownlinkClientImpl;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * LogicalDownlinkClientSample is a sample program to consume default connection configuration characteristics of
 * HPE OneView. It invokes APIs of LogicalDownlinkClient which is in sdk library to perform GET operations on
 * logical downlink resource
 */
public class LogicalDownlinkClientSample {

    private final ResourceDtoUtils resourceDtoUtils;
    private final LogicalDownlinkClient logicalDownlinkClient;

    private RestParams params;

    // test values - user input
    // ================================
    private static final String resourceName = "LD03544a65-c7d2-480f-975e-baaede358d15 (HP VC FlexFabric-20/40 F8 Module)";
    private static final String resourceId = "7462ff65-124b-47a9-b097-e6a2aa195094";
    // ================================

    private LogicalDownlinkClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.resourceDtoUtils = new ResourceDtoUtils(oneViewClient);
        this.logicalDownlinkClient = LogicalDownlinkClientImpl.getClient();
    }

    public void getLogicalDownlinkById() throws InstantiationException, IllegalAccessException {
        LogicalDownlink logicalDownlinkDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalDownlinkDto = logicalDownlinkClient.getLogicalDownlinkById(params, resourceId);

            System.out
                    .println("LogicalDownlinkClientSample : getLogicalDownlinkById : LogicalDownlink object returned to client : "
                            + logicalDownlinkDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : No response from appliance : "
                    + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : arguments are null ");
        }

    }

    public void getLogicalDownlinkByIdWithoutEthernet() throws InstantiationException, IllegalAccessException {
        LogicalDownlink logicalDownlinkDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalDownlinkDto = logicalDownlinkClient.getLogicalDownlinkByIdWithoutEthernet(params, resourceId);

            System.out
                    .println("LogicalDownlinkClientSample : getLogicalDownlinkById : LogicalDownlink object returned to client : "
                            + logicalDownlinkDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : No response from appliance : "
                    + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : arguments are null ");
        }

    }

    public void getAllLogicalDownlinks() throws InstantiationException, IllegalAccessException {
        ResourceCollection<LogicalDownlink> logicalDownlinkCollectionDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalDownlinkCollectionDto = logicalDownlinkClient.getAllLogicalDownlinks(params);

            System.out
                    .println("LogicalDownlinkClientSample : getAllLogicalDownlinks : LogicalDownlink object returned to client : "
                            + logicalDownlinkCollectionDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinks : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinks : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinks : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinks : No response from appliance : "
                    + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinks : arguments are null ");
        }
    }

    public void getAllLogicalDownlinksWithoutEthernet() throws InstantiationException, IllegalAccessException {
        ResourceCollection<LogicalDownlink> logicalDownlinkCollectionDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            logicalDownlinkCollectionDto = logicalDownlinkClient.getAllLogicalDownlinksWithoutEthernet(params);

            System.out
                    .println("LogicalDownlinkClientSample : getAllLogicalDownlinksWithoutEthernet : LogicalDownlink object returned to client : "
                            + logicalDownlinkCollectionDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinksWithoutEthernet : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinksWithoutEthernet : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinksWithoutEthernet : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinksWithoutEthernet : No response from appliance : "
                    + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinksWithoutEthernet : arguments are null ");
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        LogicalDownlinkClientSample client = new LogicalDownlinkClientSample();

        client.getAllLogicalDownlinks();
        client.getAllLogicalDownlinksWithoutEthernet();
        client.getLogicalDownlinkById();
        client.getLogicalDownlinkByIdWithoutEthernet();
    }

}
