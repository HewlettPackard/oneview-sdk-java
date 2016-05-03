/*
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
 */
package com.hp.ov.sdk.sanmanager;

import com.hp.ov.sdk.dto.EndpointResponse;
import com.hp.ov.sdk.dto.EndpointsCsvFileResponse;
import com.hp.ov.sdk.dto.FcIssueResponse;
import com.hp.ov.sdk.dto.FcSansManagedSanTask;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanPolicy;
import com.hp.ov.sdk.dto.SanRequest;
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.ZoningPolicy;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.FcSansManagedSanClient;
import com.hp.ov.sdk.rest.client.FcSansManagedSanClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * ManagedSanClientSample is a sample program capture/consume the physical or logical fibre channel SAN or a Flat SAN
 * managed by HP OneView. It invokes APIs of ManagedSanClientSample which is in sdk library to perform GET/PUT
 * operations on san resource
 */
public class FcSansManagedSanClientSample {

    private final FcSansManagedSanClient fcSansManagedSanClient;

    private RestParams params;

    // test values - user input
    // ================================
    private static final String resourceId = "e9d79564-1244-4f0e-83a3-215dcb1f6b1f";
    private static final String resourceName = "SAN1_0";
    // ================================

    private FcSansManagedSanClientSample() {
        this.fcSansManagedSanClient = FcSansManagedSanClientImpl.getClient();
    }

    private void getManagedSan() throws InstantiationException, IllegalAccessException {
        SanResponse sanResponseDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            sanResponseDto = fcSansManagedSanClient.getManagedSan(params, resourceId);

            System.out.println("ManagedSanClientTest : getManagedSan :" + " device manager object returned to client : "
                    + sanResponseDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ManagedSanClientTest : getManagedSan :" + " arguments are null ");
            return;
        }
    }

    private void getAllManagedSan() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<SanResponse> sanResponseCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            sanResponseCollectionDto = fcSansManagedSanClient.getAllManagedSan(params);

            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " device manager object returned to client : "
                    + sanResponseCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ManagedSanClientTest : getAllManagedSan :" + " arguments are null ");
            return;
        }
    }

    private void updateManagedSan() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            SanRequest sanRequest = new SanRequest();
            SanPolicy sanPolicy = new SanPolicy();

            sanPolicy.setEnableAliasing(Boolean.TRUE);
            sanPolicy.setZoningPolicy(ZoningPolicy.SingleInitiatorAllTargets);
            sanPolicy.setInitiatorNameFormat("sample_initiator_name_format");
            sanPolicy.setTargetGroupNameFormat("sample_target_group_name_format");
            sanPolicy.setTargetNameFormat("sample_target_name_format");
            sanPolicy.setZoneNameFormat("sample_zone_name_format");

            sanRequest.setSanPolicy(sanPolicy);
            sanRequest.setRefreshState(SanResponse.RefreshState.RefreshPending);

            SanResponse sanResponseReturnDto = fcSansManagedSanClient.updateManagedSan(params, resourceId, sanRequest, false, false);

            System.out.println("ManagedSanClientTest : updateManagedSan : " + " device manager object returned to client : "
                    + sanResponseReturnDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ManagedSanClientTest : updateManagedSan : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ManagedSanClientTest : updateManagedSan : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void getEndpointsOfManagedSan() throws InstantiationException, IllegalAccessException {
        try {
            params = HPOneViewCredential.createCredentials();

            ResourceCollection<EndpointResponse> endpointResponseCollection
                    = fcSansManagedSanClient.getEndpointsOfManagedSan(params, resourceId);

            System.out.println("FcSansManagedSanClientTest#getEndpointsOfManagedSan :" + " device manager object returned to client : "
                    + endpointResponseCollection.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("FcSansManagedSanClientTest#getEndpointsOfManagedSan :" + " resource you are looking is not found ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("FcSansManagedSanClientTest#getEndpointsOfManagedSan :" + " no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("FcSansManagedSanClientTest#getEndpointsOfManagedSan :" + " Applicance Not reachabe at : " + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("FcSansManagedSanClientTest#getEndpointsOfManagedSan :" + " No response from appliance : " + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("FcSansManagedSanClientTest#getEndpointsOfManagedSan :" + " arguments are null ");
        }
    }

    private void createManagedSanIssuesReport() throws InstantiationException, IllegalAccessException {
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = fcSansManagedSanClient.getId(params, resourceName);
            FcSansManagedSanTask task = fcSansManagedSanClient.createManagedSanIssuesReport(params, resourceId, false);

            for (FcIssueResponse issue : task.getIssues()) {
                System.out.println("FcSansManagedSanClientTest#createManagedSanIssuesReport :" + " issue : "
                        /*+ adaptor.buildDto(issue).toString());*/
                        + issue.toString());
            }
            System.out.println("FcSansManagedSanClientTest#createManagedSanIssuesReport :" + " device manager object returned to client : "
                    + task.getTask().toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("FcSansManagedSanClientTest#createManagedSanIssuesReport :" + " resource you are looking is not found ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("FcSansManagedSanClientTest#createManagedSanIssuesReport :" + " no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("FcSansManagedSanClientTest#createManagedSanIssuesReport :" + " Applicance Not reachable at : " + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("FcSansManagedSanClientTest#createManagedSanIssuesReport :" + " No response from appliance : " + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("FcSansManagedSanClientTest#createManagedSanIssuesReport :" + " arguments are null ");
        }
    }

    private void createEndpointsCsvOfManagedSan() throws InstantiationException, IllegalAccessException {
        try {
            params = HPOneViewCredential.createCredentials();

            EndpointsCsvFileResponse response = fcSansManagedSanClient.createEndpointsCsvOfManagedSan(params, resourceId);

            System.out.println("FcSansManagedSanClientTest#createEndpointsCsvOfManagedSan :" + " response object returned to client : "
                    + response);
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("FcSansManagedSanClientTest#createEndpointsCsvOfManagedSan :" + " resource you are looking is not found ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("FcSansManagedSanClientTest#createEndpointsCsvOfManagedSan :" + " no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("FcSansManagedSanClientTest#createEndpointsCsvOfManagedSan :" + " Applicance Not reachable at : " + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("FcSansManagedSanClientTest#createEndpointsCsvOfManagedSan :" + " No response from appliance : " + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("FcSansManagedSanClientTest#createEndpointsCsvOfManagedSan :" + " arguments are null ");
        }
    }

    public static void main(final String[] args) throws Exception {
        FcSansManagedSanClientSample client = new FcSansManagedSanClientSample();

        client.getAllManagedSan();
        client.getManagedSan();
        client.updateManagedSan();
        client.getEndpointsOfManagedSan();
        client.createManagedSanIssuesReport();
        client.createEndpointsCsvOfManagedSan();
    }
}
