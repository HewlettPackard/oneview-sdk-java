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
package com.hp.ov.sdk.network;

import java.util.List;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.JsonRequest;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Bandwidth;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.NetworkClient;
import com.hp.ov.sdk.rest.client.NetworkClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * NetworkClientSample is a sample program to consume ethernet networks resource of HPE OneView
 * It invokes APIs of NetworkClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on ethernet networks resource
 */
public class NetworkClientSample {

    private NetworkClient networkClient;
    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String resourceId = "609c1f1e-8def-431a-ad4e-e01ce9184005";
    private static final String resourceName = "Eth-demo";
    private static final Double maximumBandwidth = Double.valueOf(8000);
    private static final Double preferredBandwidth = Double.valueOf(3000);
    private static final Integer vlanId = 333;
    private static final String bulkNetworkName = "Prod";
    private static final String vlanRange = "401-405";
    // ================================

    private NetworkClientSample() {
        this.networkClient = NetworkClientImpl.getClient();
    }

    private void getNetworkById() throws InstantiationException, IllegalAccessException {
        Network networkDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            networkDto = networkClient.getNetwork(params, resourceId);

            System.out.println("NetworkClientTest : getNetworkById : network object returned to client : " + networkDto.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : getNetworkById : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : getNetworkById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : getNetworkById : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkClientTest : getNetworkById : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : getNetworkById : arguments are null ");
            return;
        }
    }

    private void getAllNetwork() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<Network> networkCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            networkCollectionDto = networkClient.getAllNetworks(params);

            System.out.println("NetworkClientTest : getAllNetwork : network object returned to client (count) : "
                    + networkCollectionDto.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : getAllNetwork : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : getAllNetwork : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : getAllNetwork : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkClientTest : getAllNetwork : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : getAllNetwork : arguments are null ");
            return;
        }
    }

    private void getNetworkByName() throws InstantiationException, IllegalAccessException {
        Network networkDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            networkDto = networkClient.getNetworkByName(params, resourceName);

            System.out
                    .println("NetworkClientTest : getNetworkByName: network object returned to client : " + networkDto.toString());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : getNetworkByName : resource you are looking is not found");
            return;

        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : getNetworkByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : getNetworkByName : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkClientTest : getNetworkByName : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : getNetworkByName : arguments are null ");
            return;
        }
    }

    private void createNetwork() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final Network networkDto = buildNetworkDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.createNetwork(params, networkDto, false, false);

            System.out.println("NetworkClientTest : createNetwork : network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : createNetwork : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("NetworkClientTest : createNetwork : bad request, "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : createNetwork : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : createNetwork : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : createNetwork : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("NetworkClientTest : createNetwork : "
                    + "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void updateNetwork() throws InstantiationException, IllegalAccessException {
        try {
            // first get the session Id
            String resourceId = null;
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            Network networkDto = networkClient.getNetworkByName(params, resourceName);
            // test values
            networkDto.setName(resourceName + "_updated");
            networkDto.setPurpose(Network.Purpose.VMMigration);

            if (null != networkDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(networkDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.updateNetwork(params, resourceId, networkDto, false, false);

            System.out.println("NetworkClientTest : updateNetwork : " + "network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : updateNetwork :" + " resource you are looking is not found for update");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("NetworkClientTest : updateNetwork :"
                    + " bad request, may be duplicate resource name or invalids inputs. check inputs and try again : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : updateNetwork :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : updateNetwork :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkClientTest : updateNetwork :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : updateNetwork : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("NetworkClientTest : updateNetwork : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteNetwork() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            String resourceId = networkClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = networkClient.deleteNetwork(params, resourceId, false);

            System.out.println("NetworkClientTest : deleteNetwork : network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : deleteNetwork : resource you are looking is not found for delete");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : deleteNetwork : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : deleteNetwork : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkClientTest : deleteNetwork : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : deleteNetwork : arguments are null ");
            return;
        }
    }

    private void createNetworkInBulk() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final BulkEthernetNetwork bulkEthernetNetworkDto = buildBulkEthernetNetworkDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.createNetworkInBulk(params, bulkEthernetNetworkDto, false, false);

            System.out.println("NetworkClientTest : createNetworkInBulk : network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : createNetworkInBulk : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("NetworkClientTest : createNetworkInBulk : bad request, try again  "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : createNetworkInBulk : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : createNetworkInBulk : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : createNetworkInBulk : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("NetworkClientTest : createNetworkInBulk : "
                    + "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void createNetworkUsingJsonRequest() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final Network networkDto = buildTestNetworkDtoWithJsonRequest();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.createNetwork(params, networkDto, false, true);

            System.out.println("NetworkClientTest : createNetworkUsingJsonRequest : network object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : createNetworkUsingJsonRequest : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("NetworkClientTest : createNetworkUsingJsonRequest : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : createNetworkUsingJsonRequest : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : createNetworkUsingJsonRequest : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : createNetworkUsingJsonRequest : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("NetworkClientTest : createNetworkUsingJsonRequest : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void getNetworkAssociatedProfiles() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            List<String> uris = networkClient.getNetworkAssociatedProfiles(params, resourceId);

            for (String uri : uris) {
                System.out.println("NetworkClientTest : getNetworkAssociatedProfiles : uri object returned to client : " + uri);
            }
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedProfiles : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedProfiles : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : getNetworkAssociatedProfiles : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedProfiles : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedProfiles : arguments are null ");
            return;
        }
    }

    private void getNetworkAssociatedUplinkGroups() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            List<String> uris = networkClient.getNetworkAssociatedUplinkGroups(params, resourceId);

            for (String uri : uris) {
                System.out.println("NetworkClientTest : getNetworkAssociatedUplinkGroups : uri object returned to client : " + uri);
            }
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedUplinkGroups : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedUplinkGroups : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("NetworkClientTest : getNetworkAssociatedUplinkGroups : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedUplinkGroups : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("NetworkClientTest : getNetworkAssociatedUplinkGroups : arguments are null ");
            return;
        }
    }

    private Network buildNetworkDto() {
        Network dto = new Network();
        dto.setVlanId(vlanId);
        dto.setPurpose(Network.Purpose.General);
        dto.setName(resourceName);
        dto.setPrivateNetwork(false);
        dto.setSmartLink(true);
        dto.setEthernetNetworkType(Network.EthernetNetworkType.Tagged);
        dto.setConnectionTemplateUri(null);
        //dto.setType(ResourceCategory.RC_NETWORK); // v120
        dto.setType(ResourceCategory.RC_NETWORK_V200); // v200

        Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(maximumBandwidth);
        bandwidth.setTypicalBandwidth(preferredBandwidth);

        ConnectionTemplate connectionTemplate = new ConnectionTemplate();
        connectionTemplate.setBandwidth(bandwidth);

        dto.setConnectionTemplate(connectionTemplate);

        return dto;
    }

    private BulkEthernetNetwork buildBulkEthernetNetworkDto() {
        BulkEthernetNetwork bulkEthernetNetworkDto = new BulkEthernetNetwork();

        bulkEthernetNetworkDto.setVlanIdRange(vlanRange);
        bulkEthernetNetworkDto.setPurpose(Network.Purpose.General);
        bulkEthernetNetworkDto.setNamePrefix(bulkNetworkName);
        bulkEthernetNetworkDto.setSmartLink(false);
        bulkEthernetNetworkDto.setPrivateNetwork(false);
        bulkEthernetNetworkDto.setType(ResourceCategory.RC_BULK_NETWORK);

        Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(maximumBandwidth);
        bandwidth.setTypicalBandwidth(preferredBandwidth);

        bulkEthernetNetworkDto.setBandwidth(bandwidth);

        return bulkEthernetNetworkDto;
    }

    private Network buildTestNetworkDtoWithJsonRequest() {
        Network networkDto = new Network();
        JsonRequest jsonRequest = new JsonRequest();

        jsonRequest.setBody("{" +
//                "\"type\":\"ethernet-networkV2\"," + //v120
                "\"type\":\"ethernet-networkV3\"," + //v200
                "\"vlanId\":321," +
                "\"smartLink\":true," +
                "\"privateNetwork\":false," +
                "\"purpose\":\"General\"," +
                "\"ethernetNetworkType\":\"Tagged\"," +
                "\"description\":null," +
                "\"name\":\"Test_321\"," +
                "\"category\":\"ethernet-networks\"," +
                "\"connectionTemplate\" : {" +
                    "\"bandwidth\" : {" +
                        "\"maximumBandwidth\" : 8500," +
                        "\"typicalBandwidth\" : 5500" +
                "}}}");

        networkDto.setJsonRequest(jsonRequest);

        return networkDto;
    }

    public static void main(final String[] args) throws Exception {
        final NetworkClientSample client = new NetworkClientSample();

        client.getAllNetwork();
        client.createNetwork();
        client.getNetworkById();
        client.getNetworkByName();
        client.updateNetwork();
        client.createNetwork();
        client.getAllNetwork();
        client.deleteNetwork();
        client.createNetworkInBulk();
        client.createNetworkUsingJsonRequest();
        client.getNetworkAssociatedProfiles();
        client.getNetworkAssociatedUplinkGroups();
    }

}
