/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.fcnetwork.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.FcNetworkCollection;
import com.hp.ov.sdk.dto.JsonRequest;
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
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class FcNetworkClientSample {
    private RestParams params;
    private static FcNetworkClient fcNetworkClient;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static TaskResourceV2 taskResourceV2;
    private static ResourceDtoUtils resourceDtoUtils;

    // test values - user input
    // ================================
    private static final String resourceName = "FC_Network_A";
    private static final String resourceId = "3007c39d-db47-49d9-af69-0865a59228fc";

    // ================================

    private static void init() {
        fcNetworkClient = HPOneViewSdkBeanFactory.getFcNetworkClient();
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        resourceDtoUtils = HPOneViewSdkBeanFactory.getResourceDtoUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
    }

    private void getFcNetworkById() throws InstantiationException, IllegalAccessException {
        FcNetwork fcNetworkDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
        FcNetworkCollection fcNetworkCollectionDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
        FcNetworkCollection fcNetworkCollectionDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create network request body
            final FcNetwork fcNetworkDto = resourceDtoUtils.buildFcNetworkDto(resourceName);
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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using name

            fcNetworkDto = fcNetworkClient.getFcNetworkByName(params, resourceName);
            fcNetworkDto.setName(resourceName + "_updated");
            if (null != fcNetworkDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(fcNetworkDto.getUri());
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
        FcNetwork fcNetworkDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using name
            fcNetworkDto = fcNetworkClient.getFcNetworkByName(params, resourceName);

            if (null != fcNetworkDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(fcNetworkDto.getUri());
            }

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        FcNetworkClientSample client = new FcNetworkClientSample();
        client.getFcNetworkById();
        client.getAllFcNetwork();
        client.getFcNetworkByFilter();
        client.createFcNetwork();
        client.getFcNetworkByName();
        client.updateFcNetwork();
        client.createFcNetwork();
        client.deleteFcNetwork();
        client.createFcNetworkUsingJsonRequest();
    }
}
