/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.network.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.JsonRequest;
import com.hp.ov.sdk.dto.NetworkCollection;
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
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class NetworkClientSample {
    private RestParams params;
    private static NetworkClient networkClient;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String resourceId = "cd9c1548-d2b0-405e-95da-c575babb6a52";
    private static final String resourceName = "Eth-demo";
    private static final Double maxBandwidth = (double) 8000;
    private static final Double minBandwidth = (double) 2000;
    private static final Integer vlanId = 333;
    private static final String bulkNetworkName = "Prod";
    private static final String vlanRange = "401-410";

    // ================================

    private static void init() throws Exception {
        networkClient = HPOneViewSdkBeanFactory.getNetworkClient();
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
    }

    private void getNetworkById() throws InstantiationException, IllegalAccessException {
        Network networkDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
        NetworkCollection networkCollectionDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            networkCollectionDto = networkClient.getAllNetworks(params);

            System.out.println("NetworkClientTest : getAllNetwork : network object returned to client : "
                    + networkCollectionDto.toString());
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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
        String resourceId = null;
        Network networkDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            networkDto = networkClient.getNetworkByName(params, resourceName);
            // test values
            networkDto.setName(resourceName + "_updated");
            // Negative test case
            // networkDto.setVlanId(5000);
            // end
            if (null != networkDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(networkDto.getUri());
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
        String resourceId = null;
        Network networkDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            networkDto = networkClient.getNetworkByName(params, resourceName);

            if (null != networkDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(networkDto.getUri());
            }
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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            System.out
                    .println("NetworkClientTest : createNetworkUsingJsonRequest : errors in task, please check task resource for more details ");
            return;
        }

    }

    private Network buildNetworkDto() {
        final Network dto = new Network();
        dto.setVlanId(vlanId);
        dto.setPurpose(Network.Purpose.General);
        dto.setName(resourceName);
        dto.setPrivateNetwork(false);
        dto.setSmartLink(true);
        dto.setConnectionTemplateUri(null);
        dto.setEthernetNetworkType(Network.EthernetNetworkType.Tagged);
        dto.setType(ResourceCategory.RC_NETWORK);

        final ConnectionTemplate connectionTemplate = new ConnectionTemplate();

        final Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(maxBandwidth);
        bandwidth.setTypicalBandwidth(minBandwidth);
        connectionTemplate.setBandwidth(bandwidth);
        dto.setConnectionTemplate(connectionTemplate);

        return dto;

    }

    private BulkEthernetNetwork buildBulkEthernetNetworkDto() {
        final BulkEthernetNetwork bulkEthernetNetworkDto = new BulkEthernetNetwork();

        bulkEthernetNetworkDto.setVlanIdRange(vlanRange);
        bulkEthernetNetworkDto.setPurpose(BulkEthernetNetwork.Purpose.General);
        bulkEthernetNetworkDto.setNamePrefix(bulkNetworkName);
        bulkEthernetNetworkDto.setSmartLink(false);
        bulkEthernetNetworkDto.setPrivateNetwork(false);
        bulkEthernetNetworkDto.setType(ResourceCategory.RC_BULK_NETWORK);

        final Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(maxBandwidth);
        bandwidth.setTypicalBandwidth(minBandwidth);

        bulkEthernetNetworkDto.setBandwidth(bandwidth);
        return bulkEthernetNetworkDto;
    }

    private Network buildTestNetworkDtoWithJsonRequest() {

        final Network networkDto = new Network();
        final JsonRequest jsonRequest = new JsonRequest();
        jsonRequest
                .setBody("{\"type\":\"ethernet-networkV2\",\"vlanId\":103,\"smartLink\":true,\"privateNetwork\":false,\"purpose\":\"General\",\"ethernetNetworkType\":\"Tagged\",\"description\":null,\"name\":\"Test_3\",\"category\":\"ethernet-networks\"}");
        networkDto.setJsonRequest(jsonRequest);

        return networkDto;
    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        final NetworkClientSample client = new NetworkClientSample();
        client.getNetworkById();
        client.getAllNetwork();
        client.createNetwork();
        client.getNetworkByName();
        client.updateNetwork();
        client.createNetwork();
        client.deleteNetwork();
        client.createNetworkInBulk();
        client.createNetworkUsingJsonRequest();
    }
}
