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

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
import com.hp.ov.sdk.dto.TemplateProvisioningData;
import com.hp.ov.sdk.rest.client.StoragePoolClient;
import com.hp.ov.sdk.rest.client.StoragePoolClientImpl;
import com.hp.ov.sdk.rest.client.StorageSystemClient;
import com.hp.ov.sdk.rest.client.StorageSystemClientImpl;
import com.hp.ov.sdk.rest.client.StorageVolumeTemplateClient;
import com.hp.ov.sdk.rest.client.StorageVolumeTemplateClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * StorageVolumeTemplateClientSample is a sample program consumes the template that is defined over the storage pool
 * in HP OneView. It invokes APIs of StorageVolumeTemplateClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on storage volume template resource
 */
public class StorageVolumeTemplateClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "FusionTemplateExample";
    private static final String storageSystemName = "ThreePAR7200-4166";
    private static final String storagePoolName = "FST_CPG1";
    private static final String capacity = "235834383322";
    private static final String resourceId = "fd66576a-1e20-444e-b92a-9e719500ee17";
    private static final String provisionType = "Thin";
    // ================================

    private final StorageVolumeTemplateClient storageVolumeTemplateClient;
    private final StoragePoolClient storagePoolClient;
    private final StorageSystemClient storageSystemClient;

    private RestParams params;

    public StorageVolumeTemplateClientSample() {
        this.storageVolumeTemplateClient = StorageVolumeTemplateClientImpl.getClient();
        this.storagePoolClient = StoragePoolClientImpl.getClient();
        this.storageSystemClient = StorageSystemClientImpl.getClient();
    }

    private void getStorageVolumeTemplateById() throws InstantiationException, IllegalAccessException {
        StorageVolumeTemplate storageVolumeTemplateDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            // then make sdk service call to get resource
            storageVolumeTemplateDto = storageVolumeTemplateClient.getStorageVolumeTemplate(params, resourceId);

            System.out
                    .println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateById : storageVolumeTemplate object returned to client : "
                            + storageVolumeTemplateDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateById : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateById : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateById : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateById : arguments are null ");
            return;
        }
    }

    private void getAllStorageVolumeTemplate() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        StorageVolumeTemplateCollection storageVolumeTemplateCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageVolumeTemplateCollectionDto = storageVolumeTemplateClient.getAllStorageVolumeTemplates(params);

            System.out
                    .println("StorageVolumeTemplateClientTest : getAllStorageVolumeTemplate : storageVolumeTemplate object returned to client : "
                            + storageVolumeTemplateCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("StorageVolumeTemplateClientTest : getAllStorageVolumeTemplate : resource you are looking is not found "
                            + params.getHostname());
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getAllStorageVolumeTemplate : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeTemplateClientTest : getAllStorageVolumeTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getAllStorageVolumeTemplate : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getAllStorageVolumeTemplate : arguments are null ");
            return;
        }
    }

    private void getStorageVolumeTemplateByName() throws InstantiationException, IllegalAccessException {
        StorageVolumeTemplate storageVolumeTemplateDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageVolumeTemplateDto = storageVolumeTemplateClient.getStorageVolumeTemplateByName(params, resourceName);

            System.out
                    .println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateByName : storageVolumeTemplate object returned to client : "
                            + storageVolumeTemplateDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateByName : no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeTemplateClientTest : getStorageVolumeTemplateByName : arguments are null ");
            return;
        }

    }

    private void createStorageVolumeTemplate() throws InstantiationException, IllegalAccessException {

        StorageVolumeTemplate storageVolumeTemplateDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create storageVolumeTemplate request body
            storageVolumeTemplateDto = buildTestStorageVolumeTemplateDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            storageVolumeTemplateDto = storageVolumeTemplateClient.createStorageVolumeTemplate(params, storageVolumeTemplateDto,
                    false, false);

            System.out
                    .println("StorageVolumeTemplateClientTest : createStorageVolumeTemplate : storageVolumeTemplate object returned to client : "
                            + storageVolumeTemplateDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("StorageVolumeTemplateClientTest : createStorageVolumeTemplate : resource you are looking is not found "
                            + params.getHostname());
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeTemplateClientTest : createStorageVolumeTemplate : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeTemplateClientTest : createStorageVolumeTemplate : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeTemplateClientTest : createStorageVolumeTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeTemplateClientTest : createStorageVolumeTemplate : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("StorageVolumeTemplateClientTest : createStorageVolumeTemplate : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void updateStorageVolumeTemplate() throws InstantiationException, IllegalAccessException {
        StorageVolumeTemplate storageVolumeTemplateDto = null;
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            storageVolumeTemplateDto = storageVolumeTemplateClient.getStorageVolumeTemplateByName(params, resourceName);

            if (null != storageVolumeTemplateDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(storageVolumeTemplateDto.getUri());
            }
            storageVolumeTemplateDto.setName(resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            storageVolumeTemplateDto = storageVolumeTemplateClient.updateStorageVolumeTemplate(params, resourceId,
                    storageVolumeTemplateDto, false);

            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate : "
                    + "storageVolumeTemplate object returned to client : " + storageVolumeTemplateDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate :" + " no such url : "
                    + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeTemplateClientTest : updateStorageVolumeTemplate : "
                    + "errors in task, please check task " + "resource for more details ");
            return;
        }
    }

    private void deleteStorageVolumeTemplate() throws InstantiationException, IllegalAccessException {
        String deletedString = "";
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = storageVolumeTemplateClient.getId(params, resourceName);

            // then make sdk service call to get resource
            deletedString = storageVolumeTemplateClient.deleteStorageVolumeTemplate(params, resourceId);

            System.out
                    .println("StorageVolumeTemplateClientTest : deleteStorageVolumeTemplate : storageVolumeTemplate object returned to client : "
                            + deletedString);
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("StorageVolumeTemplateClientTest : deleteStorageVolumeTemplate : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeTemplateClientTest : deleteStorageVolumeTemplate : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeTemplateClientTest : deleteStorageVolumeTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeTemplateClientTest : deleteStorageVolumeTemplate : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeTemplateClientTest : deleteStorageVolumeTemplate : arguments are null ");
            return;
        }

    }

    // TODO - Move Uri fetch logic to SdkUtils

    private StorageVolumeTemplate buildTestStorageVolumeTemplateDto() {
        final StorageVolumeTemplate dto = new StorageVolumeTemplate();

        dto.setName(resourceName);
        dto.setState("Normal");
        dto.setDescription("Example Template");
        dto.setStateReason("None");
        dto.setStorageSystemUri(storageSystemClient.getStorageSystemByName(params, storageSystemName).getUri());
        final TemplateProvisioningData provisioning = new TemplateProvisioningData();
        provisioning.setProvisionType(provisionType);
        provisioning.setShareable(true);
        provisioning.setCapacity(capacity);
        provisioning.setStoragePoolUri(storagePoolClient.getStoragePoolByName(params, storagePoolName, getStorageSystemUri())
                .getUri());
        dto.setProvisioning(provisioning);
        dto.setType(ResourceCategory.RC_STORAGE_VOLUME_TEMPLATE);
        return dto;
    }

    private String getStorageSystemUri() {
        return storageSystemClient.getStorageSystemByName(params, storageSystemName).getUri();
    }

    public static void main(final String[] args) throws Exception {
        StorageVolumeTemplateClientSample client = new StorageVolumeTemplateClientSample();

        client.createStorageVolumeTemplate();
        client.getStorageVolumeTemplateById();
        client.getAllStorageVolumeTemplate();
        client.getStorageVolumeTemplateByName();
        client.updateStorageVolumeTemplate();
        client.deleteStorageVolumeTemplate();
    }
}
