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
package com.hp.ov.sdk.storage;

import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.StoragePoolClient;
import com.hp.ov.sdk.rest.client.StoragePoolClientImpl;
import com.hp.ov.sdk.rest.client.StorageSystemClient;
import com.hp.ov.sdk.rest.client.StorageSystemClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * StoragePoolClientSample is a sample program consumes the set of disk from the storage system managed
 * by HP OneView. It invokes APIs of StoragePoolClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on storage pool resource
 */
public class StoragePoolClientSample {

    private final StoragePoolClient storagePoolClient;
    private final StorageSystemClient storageSystemClient;

    private RestParams params;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "FST_CPG1";
    private static final String storageSystemName = "ThreePAR7200-4166";
    private static final String resourceId = "EEDD9919-42DC-4BD1-804A-A8B3B1140C11";
    // ================================

    private StoragePoolClientSample() {
        this.storagePoolClient = StoragePoolClientImpl.getClient();
        this.storageSystemClient = StorageSystemClientImpl.getClient();
    }

    private void getStoragePoolById() throws InstantiationException, IllegalAccessException {
        StoragePool storagePoolDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

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
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

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
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storagePoolDto = storagePoolClient.getStoragePoolByName(params, resourceName, getStorageSystemUri());

            System.out.println("StoragePoolClientTest : getStoragePoolByName : storagePool object returned to client : "
                    + storagePoolDto.toString());
            System.out.println("StoragePoolClientTest : getStoragePoolByName : storagePool Name: " + storagePoolDto.getName()
                    + ", Storage system " + storagePoolDto.getStorageSystemUri());
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
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

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
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            storagePoolDto = storagePoolClient.getStoragePoolByName(params, resourceName, getStorageSystemUri());

            if (null != storagePoolDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(storagePoolDto.getUri());
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
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = storagePoolClient.getId(params, resourceName, getStorageSystemUri());

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
        dto.setStorageSystemUri(getStorageSystemUri());
        return dto;
    }

    private String getStorageSystemUri() {
        return storageSystemClient.getStorageSystemByName(params, storageSystemName).getUri();
    }

    public static void main(final String[] args) throws Exception {
        StoragePoolClientSample client = new StoragePoolClientSample();

        client.createStoragePool();
        client.getAllStoragePool();
        client.getStoragePoolById();
        client.getStoragePoolByName();
        client.updateStoragePool();
        client.deleteStoragePool();
    }

}
