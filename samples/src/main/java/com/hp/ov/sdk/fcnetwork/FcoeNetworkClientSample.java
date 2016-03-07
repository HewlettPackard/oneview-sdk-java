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
package com.hp.ov.sdk.fcnetwork;


import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.FcoeNetwork;
import com.hp.ov.sdk.dto.FcoeNetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.FcoeNetworkClient;
import com.hp.ov.sdk.rest.client.FcoeNetworkClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class FcoeNetworkClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String FCOE_NETWORK_RESOURCE_ID = "09203d4b-6905-479c-bf2c-447f10e2d290";
    private static final String FCOE_NETWORK_NAME = "FCoE-Network_SAMPLE";
    private static final String FCOE_NETWORK_NAME_UPDATED = FCOE_NETWORK_NAME + "_Updated";
    // ================================

    private final FcoeNetworkClient fcoeNetworkClient;

    public FcoeNetworkClientSample() {
        this.fcoeNetworkClient = FcoeNetworkClientImpl.getClient();
    }

    private void getFcoeNetwork() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            FcoeNetwork fcoeNetwork = this.fcoeNetworkClient.getFcoeNetwork(params, FCOE_NETWORK_RESOURCE_ID);

            System.out.println("FcoeNetworkClientSample : getFcoeNetwork : " +
                    "FcoeNetwork object returned to client : " + fcoeNetwork);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetwork : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetwork : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetwork : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetwork : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetwork : " +
                    "arguments are null ");
        }
    }

    private void getAllFcoeNetworks() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            FcoeNetworkCollection fcoeNetworks = this.fcoeNetworkClient.getAllFcoeNetworks(params);

            System.out.println("FcoeNetworkClientSample : getAllFcoeNetworks : " +
                    "FcoeNetworkCollection object returned to client : " + fcoeNetworks);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcoeNetworkClientSample : getAllFcoeNetworks : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcoeNetworkClientSample : getAllFcoeNetworks : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcoeNetworkClientSample : getAllFcoeNetworks : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcoeNetworkClientSample : getAllFcoeNetworks : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcoeNetworkClientSample : getAllFcoeNetworks : " +
                    "arguments are null ");
        }
    }

    private void getFcoeNetworkByName() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            FcoeNetwork fcoeNetwork = this.fcoeNetworkClient.getFcoeNetworkByName(params, FCOE_NETWORK_NAME);

            System.out.println("FcoeNetworkClientSample : getFcoeNetworkByName : " +
                    "FcoeNetwork object returned to client : " + fcoeNetwork);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetworkByName : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetworkByName : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetworkByName : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetworkByName : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcoeNetworkClientSample : getFcoeNetworkByName : " +
                    "arguments are null ");
        }
    }

    private void createFcoeNetwork() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            FcoeNetwork fcoeNetwork = new FcoeNetwork();

            fcoeNetwork.setName(FCOE_NETWORK_NAME);
            fcoeNetwork.setType(ResourceCategory.RC_FCOE_NETWORK);
            fcoeNetwork.setVlanId(Integer.valueOf(400));

            TaskResourceV2 task = this.fcoeNetworkClient.createFcoeNetwork(params, fcoeNetwork, false);

            System.out.println("FcoeNetworkClientSample : createFcoeNetwork : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcoeNetworkClientSample : createFcoeNetwork : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcoeNetworkClientSample : createFcoeNetwork : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcoeNetworkClientSample : createFcoeNetwork : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcoeNetworkClientSample : createFcoeNetwork : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcoeNetworkClientSample : createFcoeNetwork : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("FcoeNetworkClientSample : createFcoeNetwork : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private void updateFcoeNetwork() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            FcoeNetwork fcoeNetwork = this.fcoeNetworkClient.getFcoeNetworkByName(params, FCOE_NETWORK_NAME);
            String resourceId = UrlUtils.getResourceIdFromUri(fcoeNetwork.getUri());

            fcoeNetwork.setName(FCOE_NETWORK_NAME_UPDATED);

            TaskResourceV2 task = this.fcoeNetworkClient.updateFcoeNetwork(params, resourceId, fcoeNetwork, false);

            System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private void deleteFcoeNetwork() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.fcoeNetworkClient.getId(params, FCOE_NETWORK_NAME_UPDATED);

            TaskResourceV2 task = this.fcoeNetworkClient.deleteFcoeNetwork(params, resourceId, false);

            System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : "
                    + "errors in task, please check task resource for more details");
        }
    }

    public static void main(String[] args) {
        FcoeNetworkClientSample client = new FcoeNetworkClientSample();

        client.createFcoeNetwork();

        client.getFcoeNetwork();
        client.getAllFcoeNetworks();
        client.getFcoeNetworkByName();
        client.updateFcoeNetwork();
        client.deleteFcoeNetwork();
    }

}
