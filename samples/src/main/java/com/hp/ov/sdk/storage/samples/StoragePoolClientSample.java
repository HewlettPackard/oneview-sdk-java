/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.storage.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.rest.client.StoragePoolClient;
import com.hp.ov.sdk.rest.client.StorageSystemClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class StoragePoolClientSample {
    private RestParams params;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static StoragePoolClient storagePoolClient;
    private static StorageSystemClient storageSystemClient;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "FST_CPG1";
    private static final String storageSystemName = "ThreePAR7200-3050";
    private static final String resourceId = "8D86FFEC-7437-4DAB-A147-3A28E2FF990D";

    // ================================

    private static void init() {
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
        storagePoolClient = HPOneViewSdkBeanFactory.getStoragePoolClient();
        storageSystemClient = HPOneViewSdkBeanFactory.getStorageSystemClient();
    }

    private void getStoragePoolById() throws InstantiationException, IllegalAccessException {
        StoragePool storagePoolDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storagePoolDto = storagePoolClient.getStoragePool(params, resourceId);

            System.out.println("StoragePoolClientTest : getStoragePoolById : storagePool object returned to client : "
                    + storagePoolDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolById : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StoragePoolClientTest : getStoragePoolById : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolById : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolById : arguments are null ");
            return;
        }
    }

    private void getAllStoragePool() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        StoragePoolCollection storagePoolCollectionDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storagePoolCollectionDto = storagePoolClient.getAllStoragePools(params);

            System.out.println("StoragePoolClientTest : getAllStoragePool : storagePool object returned to client : "
                    + storagePoolCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StoragePoolClientTest : getAllStoragePool : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StoragePoolClientTest : getAllStoragePool : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StoragePoolClientTest : getAllStoragePool : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StoragePoolClientTest : getAllStoragePool : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StoragePoolClientTest : getAllStoragePool : arguments are null ");
            return;
        }
    }

    private void getStoragePoolByName() throws InstantiationException, IllegalAccessException {
        StoragePool storagePoolDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storagePoolDto = storagePoolClient.getStoragePoolByName(params, resourceName);

            System.out.println("StoragePoolClientTest : getStoragePoolByName : storagePool object returned to client : "
                    + storagePoolDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StoragePoolClientTest : getStoragePoolByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StoragePoolClientTest : getStoragePoolByName : arguments are null ");
            return;
        }

    }

    private void createStoragePool() throws InstantiationException, IllegalAccessException {

        String createStoragePool = null;
        AddStoragePool addStoragePoolDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create storagePool request body
            addStoragePoolDto = buildTestStoragePoolDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            createStoragePool = storagePoolClient.createStoragePool(params, addStoragePoolDto, false);

            System.out.println("StoragePoolClientTest : createStoragePool : storagePool object returned to client : "
                    + createStoragePool);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StoragePoolClientTest : createStoragePool : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StoragePoolClientTest : createStoragePool : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StoragePoolClientTest : createStoragePool : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StoragePoolClientTest : createStoragePool : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StoragePoolClientTest : createStoragePool : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("StoragePoolClientTest : createStoragePool : errors in task, please check task resource for more details ");
            return;
        }

    }

    private void updateStoragePool() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String updateStoragePool = null;
        String resourceId = null;
        StoragePool storagePoolDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storagePoolDto = storagePoolClient.getStoragePoolByName(params, resourceName);

            if (null != storagePoolDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(storagePoolDto.getUri());
            }

            storagePoolDto.setRefreshState(RefreshState.RefreshPending);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            updateStoragePool = storagePoolClient.updateStoragePool(params, resourceId, storagePoolDto, false);

            System.out.println("StoragePoolClientTest : updateStoragePool : " + "storagePool object returned to client : "
                    + updateStoragePool);
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("StoragePoolClientTest : updateStoragePool :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StoragePoolClientTest : updateStoragePool :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StoragePoolClientTest : updateStoragePool :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StoragePoolClientTest : updateStoragePool :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StoragePoolClientTest : updateStoragePool :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StoragePoolClientTest : updateStoragePool : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StoragePoolClientTest : updateStoragePool : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteStoragePool() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String deleteStoragePool = null;
        String resourceId = null;
        StoragePool storagePoolDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storagePoolDto = storagePoolClient.getStoragePoolByName(params, resourceName);

            if (null != storagePoolDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(storagePoolDto.getUri());
            }

            // then make sdk service call to get resource
            deleteStoragePool = storagePoolClient.deleteStoragePool(params, resourceId);

            System.out.println("StoragePoolClientTest : deleteStoragePool : storagePool object returned to client : "
                    + deleteStoragePool);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StoragePoolClientTest : deleteStoragePool : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StoragePoolClientTest : deleteStoragePool : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StoragePoolClientTest : deleteStoragePool : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StoragePoolClientTest : deleteStoragePool : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StoragePoolClientTest : deleteStoragePool : arguments are null ");
            return;
        }

    }

    // TODO - Move Uri fetch logic to SdkUtils

    private AddStoragePool buildTestStoragePoolDto() {
        final AddStoragePool dto = new AddStoragePool();

        dto.setPoolName(resourceName);
        dto.setStorageSystemUri(storageSystemClient.getStorageSystemByName(params, storageSystemName).getUri());
        return dto;
    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        StoragePoolClientSample client = new StoragePoolClientSample();
        client.getStoragePoolById();
        client.createStoragePool();
        client.getAllStoragePool();
        client.getStoragePoolByName();
        client.updateStoragePool();
        client.deleteStoragePool();
    }

}
