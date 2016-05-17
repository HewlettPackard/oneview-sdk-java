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
package com.hp.ov.sdk.fcnetwork;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.JsonRequest;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.FcNetworkClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * FcNetworkClientSample is a sample program to consume fiber channel networks resource of HPE OneView
 * It invokes APIs of FcNetworkClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on fiber channel networks resource
 */
public class FcNetworkClientSample {

    private final FcNetworkClient fcNetworkClient;

    private TaskResourceV2 taskResourceV2;
    private RestParams params;

    // test values - user input
    // ================================
    private static final String resourceName = "FC_Network_A";
    private static final String resourceId = "fd735c74-ed67-4c71-b6b0-5b5112776d13";
    // ================================

    private FcNetworkClientSample() {
        this.fcNetworkClient = FcNetworkClientImpl.getClient();
    }

    private void getFcNetworkById() throws InstantiationException, IllegalAccessException {
        FcNetwork fcNetworkDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fcNetworkDto = fcNetworkClient.getFcNetwork(params, resourceId);

            System.out.println("FcNetworkClientTest : getFcNetworkById :" + " fcNetwork group object returned to client : "
                    + fcNetworkDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkById :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkById :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("FcNetworkClientTest : getFcNetworkById :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("FcNetworkClientTest : getFcNetworkById :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkById :" + " arguments are null ");
            return;
        }

    }

    private void getAllFcNetwork() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<FcNetwork> fcNetworkCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fcNetworkCollectionDto = fcNetworkClient.getAllFcNetworks(params);

            System.out.println("FcNetworkClientTest : getAllFcNetwork :" + " fcNetwork groups object returned to client : "
                    + fcNetworkCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcNetworkClientTest : getAllFcNetwork " + "resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcNetworkClientTest : getAllFcNetwork :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcNetworkClientTest : getAllFcNetwork :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcNetworkClientTest : getAllFcNetwork :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : getAllFcNetwork :" + " arguments are null ");
            return;
        }
    }

    private void getFcNetworkByFilter() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<FcNetwork> fcNetworkCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fcNetworkCollectionDto = fcNetworkClient.getFcNetworkByFilter(params, 0, 10);

            System.out.println("FcNetworkClientTest : getFcNetworkByFilter :" + " fcNetwork groups object returned to client : "
                    + fcNetworkCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByFilter " + ": resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByFilter :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcNetworkClientTest : getFcNetworkByFilter :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByFilter :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByFilter :" + " arguments are null ");
            return;
        }
    }

    private void getFcNetworkByName() throws InstantiationException, IllegalAccessException {
        FcNetwork fcNetworkDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fcNetworkDto = fcNetworkClient.getFcNetworkByName(params, resourceName);

            System.out.println("FcNetworkClientTest : getFcNetworkByName :" + " fcNetwork group object returned to client : "
                    + fcNetworkDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcNetworkClientTest : getFcNetworkByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : getFcNetworkByName :" + " arguments are null ");
            return;
        }
    }

    private void createFcNetwork() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final FcNetwork fcNetworkDto = this.buildFcNetworkDto(resourceName);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcNetworkClient.createFcNetwork(params, fcNetworkDto, false, false);

            System.out.println("FcNetworkClientTest : createFcNetwork : fcNetwork" + " group object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcNetworkClientTest : createFcNetwork : " + "resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("FcNetworkClientTest : createFcNetwork : " + "bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcNetworkClientTest : createFcNetwork : " + "no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcNetworkClientTest : createFcNetwork : " + "Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : createFcNetwork : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("FcNetworkClientTest : createFcNetwork : " + "errors in task, please check task resource for "
                    + "more details ");
            return;
        }

    }

    private void updateFcNetwork() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        FcNetwork fcNetworkDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using name

            fcNetworkDto = fcNetworkClient.getFcNetworkByName(params, resourceName);
            fcNetworkDto.setName(resourceName + "_updated");
            if (null != fcNetworkDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(fcNetworkDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcNetworkClient.updateFcNetwork(params, resourceId, fcNetworkDto, false, false);

            System.out.println("FcNetworkClientTest : updateFcNetwork : " + "FcNetwork group object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcNetworkClientTest : updateFcNetwork :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("FcNetworkClientTest : updateFcNetwork :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcNetworkClientTest : updateFcNetwork :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcNetworkClientTest : updateFcNetwork :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcNetworkClientTest : updateFcNetwork :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : updateFcNetwork : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("FcNetworkClientTest : updateFcNetwork : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }

    }

    private void deleteFcNetwork() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = fcNetworkClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = fcNetworkClient.deleteFcNetwork(params, resourceId, false);

            System.out.println("FcNetworkClientTest : deleteFcNetwork : " + "fcNetwork group object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FcNetworkClientTest : deleteFcNetwork :" + " resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FcNetworkClientTest : deleteFcNetwork :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcNetworkClientTest : deleteFcNetwork :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FcNetworkClientTest : deleteFcNetwork : " + "No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : deleteFcNetwork :" + " arguments are null ");
            return;
        }

    }

    private void createFcNetworkUsingJsonRequest() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final FcNetwork fcNetworkDto = buildTestFcNetworkDtoWithJsonRequest();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcNetworkClient.createFcNetwork(params, fcNetworkDto, false, true);

            System.out.println("FcNetworkClientTest : createFcNetworkUsingJsonRequest : fcNetwork"
                    + " group object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("FcNetworkClientTest : createFcNetworkUsingJsonRequest : " + "resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("FcNetworkClientTest : createFcNetworkUsingJsonRequest : " + "bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("FcNetworkClientTest : createFcNetworkUsingJsonRequest : " + "no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FcNetworkClientTest : createFcNetworkUsingJsonRequest : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FcNetworkClientTest : createFcNetworkUsingJsonRequest : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("FcNetworkClientTest : createFcNetworkUsingJsonRequest : "
                    + "errors in task, please check task resource for " + "more details ");
            return;
        }
    }

    private FcNetwork buildTestFcNetworkDtoWithJsonRequest() {
        final FcNetwork fcNetworkDto = new FcNetwork();

        final JsonRequest jsonRequest = new JsonRequest();
        jsonRequest
                .setBody("{\"type\":\"fc-networkV2\",\"fabricType\":\"FabricAttach\",\"linkStabilityTime\":30,\"managedSanUri\":null,\"autoLoginRedistribution\":true,\"description\":null,\"name\":\"FC_Network_D\",\"state\":\"Active\",\"category\":\"fc-networks\"}");
        fcNetworkDto.setJsonRequest(jsonRequest);

        return fcNetworkDto;
    }

    private FcNetwork buildFcNetworkDto(String fcNetworkName) {
        FcNetwork fcNetworkDto = new FcNetwork();

        fcNetworkDto.setName(fcNetworkName);
        fcNetworkDto.setConnectionTemplateUri(null);
        fcNetworkDto.setLinkStabilityTime(30);
        fcNetworkDto.setAutoLoginRedistribution(true);
        fcNetworkDto.setFabricType(FcNetwork.FabricType.FabricAttach);
        fcNetworkDto.setType(ResourceCategory.RC_FCNETWORK);

        return fcNetworkDto;
    }

    public static void main(final String[] args) throws Exception {
        FcNetworkClientSample client = new FcNetworkClientSample();

        client.createFcNetwork();
        client.getFcNetworkById();
        client.getFcNetworkByFilter();
        client.getFcNetworkByName();
        client.updateFcNetwork();
        client.createFcNetwork();
        client.getAllFcNetwork();
        client.deleteFcNetwork();
        client.createFcNetworkUsingJsonRequest();
    }
}
