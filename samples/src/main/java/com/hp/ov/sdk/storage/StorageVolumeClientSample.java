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
package com.hp.ov.sdk.storage;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.AttachableStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeProvisioningParameters;
import com.hp.ov.sdk.dto.StorageVolumeSnapshot;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
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
import com.hp.ov.sdk.rest.client.StorageVolumeClient;
import com.hp.ov.sdk.rest.client.StorageVolumeClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * StorageVolumeClientSample is a sample program consume the container that is defined over the storage pool in HPE OneView.
 * It invokes APIs of StorageVolumeClient which is in sdk library to perform GET/PUT/POST/DELETE operations on storage
 *  volume resource
 */
public class StorageVolumeClientSample {

    private final StorageVolumeClient storageVolumeClient;
    private final StoragePoolClient storagePoolClient;
    private final StorageSystemClient storageSystemClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "Volume101";
    private static final String resourceNameUpdate = "Volume101_Updated";
    private static final String storageSystemName = "ThreePAR7200-4310";
    private static final String storagePoolName = "FST_CPG1";
    private static final String resourceNameForPrivateStorage = "Volume103";
    private static final String resourceId = "907A557A-8EA4-4AB8-961E-13235A882F33";
    private static final String storageVolumeSnapshotId = "0863B609-DE0A-4E31-B7D2-3D2207F673E4";
    private static final String capacity = "1234567898";
    // ================================

    private StorageVolumeClientSample() {
        this.storageVolumeClient = StorageVolumeClientImpl.getClient();
        this.storagePoolClient = StoragePoolClientImpl.getClient();
        this.storageSystemClient = StorageSystemClientImpl.getClient();
    }

    private void getStorageVolumeById() throws InstantiationException, IllegalAccessException {
        StorageVolumeV2 storageVolumeDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageVolumeDto = storageVolumeClient.getStorageVolume(params, resourceId);

            System.out.println("StorageVolumeClientTest : getStorageVolumeById : " +
                    "storageVolume object returned to client : " + storageVolumeDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeById : " +
                    "resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeById : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeById : appliance not reachable at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeById : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeById : arguments are null ");
        }
    }

    private void getAllStorageVolume() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<StorageVolumeV2> storageVolumeCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageVolumeCollectionDto = storageVolumeClient.getAllStorageVolumes(params);

            System.out.println("StorageVolumeClientTest : getAllStorageVolume : storageVolume object returned to client : "
                    + storageVolumeCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolume : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolume : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolume : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolume : arguments are null ");
            return;
        }
    }

    private void getStorageVolumeByName() throws InstantiationException, IllegalAccessException {
        StorageVolumeV2 storageVolumeDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageVolumeDto = storageVolumeClient.getStorageVolumeByName(params, resourceName);

            System.out.println("StorageVolumeClientTest : getStorageVolumeByName : storageVolume object returned to client : "
                    + storageVolumeDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeByName : arguments are null ");
            return;
        }
    }

    private void createStorageVolume() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create storageVolume request body
            final AddStorageVolumeV2 addStorageVolumeDto = buildTestStorageVolumeDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = storageVolumeClient.createStorageVolume(params, addStorageVolumeDto, false, false);

            System.out.println("StorageVolumeClientTest : createStorageVolume : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolume : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolume : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolume : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : createStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolume : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeClientTest : createStorageVolume :" +
                    " errors in task, please check task resource for more details ");
            return;
        }
    }

    private void createPrivateStorageVolume() throws InstantiationException, IllegalAccessException {

        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create storageVolume request body
            final AddStorageVolumeV2 addStorageVolumeDto = buildTestPrivateStorageVolumeDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = storageVolumeClient.createStorageVolume(params, addStorageVolumeDto, false, false);

            System.out.println("StorageVolumeClientTest : createPrivateStorageVolume : storageVolume object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : createPrivateStorageVolume : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeClientTest : createPrivateStorageVolume : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : createPrivateStorageVolume : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : createPrivateStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : createPrivateStorageVolume : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeClientTest : createPrivateStorageVolume :" +
                    " errors in task, please check task resource for more details ");
            return;
        }
    }

    private void updateStorageVolume() throws InstantiationException, IllegalAccessException {
        StorageVolumeV2 storageVolumeDto = null;
        String updateStorage = null;
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            storageVolumeDto = storageVolumeClient.getStorageVolumeByName(params, resourceName);

            if (null != storageVolumeDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(storageVolumeDto.getUri());
            }
            storageVolumeDto.setName(resourceName + "_Updated");

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            updateStorage = storageVolumeClient.updateStorageVolume(params, resourceId, storageVolumeDto, false);

            System.out.println("StorageVolumeClientTest : updateStorageVolume : " + "storageVolume object returned to client : "
                    + updateStorage);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : updateStorageVolume :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeClientTest : updateStorageVolume :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : updateStorageVolume :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : updateStorageVolume :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : updateStorageVolume :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : updateStorageVolume : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeClientTest : updateStorageVolume : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteStorageVolume() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = storageVolumeClient.getId(params, resourceNameUpdate);

            // then make sdk service call to get resource
            taskResourceV2 = storageVolumeClient.deleteStorageVolume(params, resourceId, false);

            System.out.println("StorageVolumeClientTest : deleteStorageVolume : storageVolume object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolume : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolume : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolume : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolume : arguments are null ");
            return;
        }
    }

    private void getAttachableVolumes() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            ResourceCollection<AttachableStorageVolume> attachableVolumes = storageVolumeClient.getAttachableVolumes(params);

            System.out.println("StorageVolumeClientTest : getAttachableVolumes : attachableVolumes object returned to client : "
                    + attachableVolumes.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : getAttachableVolumes : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : getAttachableVolumes : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : getAttachableVolumes : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : getAttachableVolumes : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : getAttachableVolumes : arguments are null ");
            return;
        }
    }

    private void getStorageVolumeSnapshot() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            StorageVolumeSnapshot storageVolumeSnapshot = storageVolumeClient.getStorageVolumeSnapshot(params,
                    resourceId, storageVolumeSnapshotId);

            System.out.println("StorageVolumeClientTest : getStorageVolumeSnapshot :" +
                    " storageVolumeSnapshot object returned to client : " + storageVolumeSnapshot.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeSnapshot : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeSnapshot : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeSnapshot : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeSnapshot : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : getStorageVolumeSnapshot : arguments are null ");
            return;
        }
    }

    private void getAllStorageVolumeSnapshots() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            ResourceCollection<StorageVolumeSnapshot> storageVolumeSnapshots
                    = storageVolumeClient.getAllStorageVolumeSnapshots(params, resourceId);

            System.out.println("StorageVolumeClientTest : getAllStorageVolumeSnapshots :" +
                    " getAllStorageVolumeSnapshots object returned to client : " + storageVolumeSnapshots);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolumeSnapshots : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolumeSnapshots : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolumeSnapshots : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolumeSnapshots : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : getAllStorageVolumeSnapshots : arguments are null ");
            return;
        }
    }

    private void createStorageVolumeSnapshot() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            StorageVolumeSnapshot snapshot = buildTestStorageVolumeSnapshot();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            TaskResourceV2 taskResource = storageVolumeClient.createStorageVolumeSnapshot(params,
                    resourceId, snapshot, false);

            System.out.println("StorageVolumeClientTest : createStorageVolumeSnapshot : task object returned to client : "
                    + taskResource);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolumeSnapshot : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolumeSnapshot : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolumeSnapshot : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : createStorageVolumeSnapshot : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : createStorageVolumeSnapshot : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeClientTest : createStorageVolumeSnapshot : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void deleteStorageVolumeSnapshot() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            TaskResourceV2 taskResource = storageVolumeClient.deleteStorageVolumeSnapshot(params, resourceId,
                    storageVolumeSnapshotId, false);

            System.out.println("StorageVolumeClientTest : deleteStorageVolumeSnapshot : task object returned to client : "
                    + taskResource);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolumeSnapshot : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolumeSnapshot : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolumeSnapshot : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolumeSnapshot : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolumeSnapshot : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeClientTest : deleteStorageVolumeSnapshot : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void getExtraManagedStorageVolumePaths() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            ResourceCollection<ExtraStorageVolume> extraAccessList = storageVolumeClient.getExtraManagedStorageVolumePaths(params);

            System.out.println("StorageVolumeClientTest : getExtraManagedStorageVolumePaths : extra access list object returned to client : "
                    + extraAccessList);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : getExtraManagedStorageVolumePaths : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeClientTest : getExtraManagedStorageVolumePaths : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : getExtraManagedStorageVolumePaths : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : getExtraManagedStorageVolumePaths : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : getExtraManagedStorageVolumePaths : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeClientTest : getExtraManagedStorageVolumePaths : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void repairExtraManagedStorageVolumePath() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            ExtraStorageVolumeRepair deleteExtraManagedStorageVolume = new ExtraStorageVolumeRepair();

            deleteExtraManagedStorageVolume.setType("ExtraManagedStorageVolumePaths");
            deleteExtraManagedStorageVolume.setResourceUri("/rest/storage-volumes/" + resourceId);

            TaskResourceV2  taskResource = storageVolumeClient.repairExtraManagedStorageVolumePath(
                    params, deleteExtraManagedStorageVolume, false);

            System.out.println("StorageVolumeClientTest : repairExtraManagedStorageVolumePath : task object returned to client : "
                    + taskResource);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageVolumeClientTest : repairExtraManagedStorageVolumePath : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageVolumeClientTest : repairExtraManagedStorageVolumePath : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageVolumeClientTest : repairExtraManagedStorageVolumePath : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageVolumeClientTest : repairExtraManagedStorageVolumePath : Appliance not reachable at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageVolumeClientTest : repairExtraManagedStorageVolumePath : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageVolumeClientTest : repairExtraManagedStorageVolumePath : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private StorageVolumeSnapshot buildTestStorageVolumeSnapshot() {
        StorageVolumeSnapshot snapshot = new StorageVolumeSnapshot();

        snapshot.setType("Snapshot");
        snapshot.setName("{volumeName}_{timestamp}_SAMPLE");
        snapshot.setDescription("Custom description");

        return snapshot;
    }

    private AddStorageVolumeV2 buildTestStorageVolumeDto() {
        String storageSystemUri = storageSystemClient.getStorageSystemByName(params, storageSystemName).getUri();
        String storagePoolUri = storagePoolClient.getStoragePoolByName(params, storagePoolName, storageSystemUri).getUri();

        AddStorageVolumeV2 dto = new AddStorageVolumeV2();

        dto.setName(resourceName);
        dto.setDescription("Volume description");
        dto.setStorageSystemUri(storageSystemUri);
        dto.setSnapshotPoolUri(storagePoolUri); //v200
        dto.setType(ResourceCategory.RC_ADD_STORAGE_VOLUME); //v120
        dto.setType(ResourceCategory.RC_ADD_STORAGE_VOLUME_200); //v200

        StorageVolumeProvisioningParameters provisioningParameters = new StorageVolumeProvisioningParameters();

        provisioningParameters.setProvisionType("Full");
        provisioningParameters.setShareable(true);
        provisioningParameters.setRequestedCapacity(capacity);
        provisioningParameters.setStoragePoolUri(storagePoolUri);
        dto.setProvisioningParameters(provisioningParameters);

        return dto;
    }

    private AddStorageVolumeV2 buildTestPrivateStorageVolumeDto() {
        String storageSystemUri = storageSystemClient.getStorageSystemByName(params, storageSystemName).getUri();
        String storagePoolUri = storagePoolClient.getStoragePoolByName(params, storagePoolName, storageSystemUri).getUri();

        final AddStorageVolumeV2 dto = new AddStorageVolumeV2();

        dto.setName(resourceNameForPrivateStorage);
        dto.setDescription("Volume description");
        dto.setStorageSystemUri(storageSystemUri);
        dto.setSnapshotPoolUri(storagePoolUri); //v200
        dto.setType(ResourceCategory.RC_ADD_STORAGE_VOLUME);
        dto.setType(ResourceCategory.RC_ADD_STORAGE_VOLUME_200); //v200

        final StorageVolumeProvisioningParameters provisioningParameters = new StorageVolumeProvisioningParameters();
        provisioningParameters.setProvisionType("Full");
        provisioningParameters.setShareable(false);
        provisioningParameters.setRequestedCapacity("1234567898");
        provisioningParameters.setStoragePoolUri(storagePoolUri);
        dto.setProvisioningParameters(provisioningParameters);

        return dto;
    }

    public static void main(final String[] args) throws Exception {
        StorageVolumeClientSample client = new StorageVolumeClientSample();

        client.createStorageVolume();
        client.createPrivateStorageVolume();

        client.getStorageVolumeById();
        client.getAllStorageVolume();
        client.getStorageVolumeByName();
        client.updateStorageVolume();
        client.deleteStorageVolume();
        client.getAttachableVolumes();

        client.createStorageVolumeSnapshot();

        client.getStorageVolumeSnapshot();
        client.getAllStorageVolumeSnapshots();
        client.deleteStorageVolumeSnapshot();

        client.getExtraManagedStorageVolumePaths();
        client.repairExtraManagedStorageVolumePath();
    }
}
