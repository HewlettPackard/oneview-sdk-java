/*
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
 */
package com.hp.ov.sdk.fabric;

import com.hp.ov.sdk.dto.Fabric;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.FabricClient;
import com.hp.ov.sdk.rest.client.FabricClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class FabricClientSample {

    private final ResourceDtoUtils resourceDtoUtils;
    private final FabricClient fabricClient;

    private RestParams params;

    // test values - user input
    // ================================
    private static final String resourceName = "DefaultFabric";
    private static final String resourceId = "77480fa6-d1ed-46ac-8117-858dad02b758";
    // ================================

    public FabricClientSample() {
        this.resourceDtoUtils = new ResourceDtoUtils();
        this.fabricClient = FabricClientImpl.getClient();
    }

    public void getFabricById() throws InstantiationException, IllegalAccessException {
        Fabric fabricDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fabricDto = fabricClient.getFabricById(params, resourceId);

            System.out.println(
                    "FabricClientSample : getFabricById : fabric object returned to client : " + fabricDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("FabricClientSample : getFabricById : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("FabricClientSample : getFabricById : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println(
                    "FabricClientSample : getFabricById : Applicance Not reachabe at : " + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println(
                    "FabricClientSample : getFabricById : No response from appliance : " + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("FabricClientSample : getFabricById : arguments are null ");
        }

    }

    public void getFabricByName() throws InstantiationException, IllegalAccessException {
        Fabric fabricDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fabricDto = fabricClient.getFabricByName(params, resourceName);

            System.out.println("FabricClientTest : getFabricByName :" + " fabric group object returned to client : "
                    + fabricDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FabricClientTest : getFabricByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FabricClientTest : getFabricByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FabricClientTest : getFabricByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FabricClientTest : getFabricByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FabricClientTest : getFabricByName :" + " arguments are null ");
            return;
        }
    }

    public void getAllFabrics() throws InstantiationException, IllegalAccessException {
        ResourceCollection<Fabric> fabricCollectionDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fabricCollectionDto = fabricClient.getAllFabrics(params);

            System.out.println("FabricClientSample : getAllFabrics : fabric object returned to client : "
                    + fabricCollectionDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("FabricClientSample : getAllFabrics : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("FabricClientSample : getAllFabrics : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out
                    .println("FabricClientSample : getAllFabrics : Applicance Not reachabe at : " + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out
                    .println("FabricClientSample : getAllFabrics : No response from appliance : " + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("FabricClientSample : getAllFabrics : arguments are null ");
        }
    }

    public static void main(String[] args) throws Exception {
        FabricClientSample client = new FabricClientSample();

        client.getAllFabrics();
        client.getFabricById();
        client.getFabricByName();
    }

}
