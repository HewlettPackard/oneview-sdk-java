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
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.AttachableStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeSnapshot;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class StorageVolumeClientImpl implements StorageVolumeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor resourceAdaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    private StorageVolumeClientImpl(HttpRestClient restClient,
            ResourceAdaptor resourceAdaptor,
            TaskAdaptor taskAdaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.resourceAdaptor = resourceAdaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static StorageVolumeClient getClient() {
        return new StorageVolumeClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public StorageVolumeV2 getStorageVolume(final RestParams params, final String resourceId) {
        LOGGER.trace("StorageVolumeClientImpl : getStorageVolume : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : getStorageVolume : response from OV : " + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME, null);
        }

        StorageVolumeV2 storageVolumeDto = resourceAdaptor.buildResourceObject(returnObj, StorageVolumeV2.class);

        LOGGER.debug("StorageVolumeClientImpl : getStorageVolume : name : " + storageVolumeDto.getName());
        LOGGER.trace("StorageVolumeClientImpl : getStorageVolume : End");

        return storageVolumeDto;
    }

    @Override
    public ResourceCollection<StorageVolumeV2> getAllStorageVolumes(final RestParams params) {
        LOGGER.trace("StorageVolumeClientImpl : getAllStorageVolumes : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : getAllStorageVolumes : response from OV : " + returnObj);
        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUMES, null);
        }

        ResourceCollection<StorageVolumeV2> storageVolumeCollectionDto = resourceAdaptor.buildResourceCollection(
                returnObj, StorageVolumeV2.class);

        LOGGER.debug("StorageVolumeClientImpl : getAllStorageVolumes : count : " + storageVolumeCollectionDto.getCount());
        LOGGER.trace("StorageVolumeClientImpl : getAllStorageVolumes : End");

        return storageVolumeCollectionDto;
    }

    @Override
    public StorageVolumeV2 getStorageVolumeByName(final RestParams params, final String name) {
        LOGGER.trace("StorageVolumeClientImpl : getStorageVolumeByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : getStorageVolumeByName : response from OV : " + returnObj);
        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUMES, null);
        }

        StorageVolumeV2 storageVolumeDto = null;
        ResourceCollection<StorageVolumeV2> storageVolumeCollectionDto = resourceAdaptor.buildResourceCollection(
                returnObj, StorageVolumeV2.class);

        if (!storageVolumeCollectionDto.isEmpty()) {
            storageVolumeDto = storageVolumeCollectionDto.getMembers().get(0);
        }

        if (storageVolumeDto == null) {
            LOGGER.error("StorageVolumeClientImpl : getStorageVolumeByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.STORAGE_VOLUME, null);
        }
        LOGGER.trace("StorageVolumeClientImpl : getStorageVolumeByName : End");

        return storageVolumeDto;
    }

    @Override
    public TaskResourceV2 createStorageVolume(final RestParams params, final AddStorageVolumeV2 addStorageVolumeDto,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.trace("StorageVolumeClientImpl : createStorageVolume : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (addStorageVolumeDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUMES, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI));

        // TODO - check for json request in the input storageVolumeDto. if it is
        // present, then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolume storageVolumeDto.

        // create JSON request from storageVolumeDto
        JSONObject jsonObject = resourceAdaptor.buildJsonRequest(addStorageVolumeDto, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : createStorageVolume : returnObj = " + returnObj);
        LOGGER.debug("StorageVolumeClientImpl : createStorageVolume : taskResource = " + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("StorageVolumeClientImpl : createStorageVolume : End");

        return taskResourceV2;
    }

    @Override
    public String updateStorageVolume(final RestParams params, final String resourceId,
            final StorageVolumeV2 storageVolumeDto, final boolean useJsonRequest) {

        LOGGER.trace("StorageVolumeClientImpl : updateStorageVolume : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (storageVolumeDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUMES, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI, resourceId));

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolume dto.

        // create JSON request from dto
        JSONObject jsonObject = resourceAdaptor.buildJsonRequest(storageVolumeDto, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);

        if (!Strings.isNullOrEmpty(returnObj)) {
            returnObj = "Updated";
        }

        LOGGER.debug("StorageVolumeClientImpl : updateStorageVolume : returnObj = " + returnObj);
        LOGGER.trace("StorageVolumeClientImpl : updateStorageVolume : End");

        return returnObj;
    }

    @Override
    public TaskResourceV2 deleteStorageVolume(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.trace("StorageVolumeClientImpl : deleteStorageVolume : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolume : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUMES, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolume : returnObj = " + returnObj);
        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolume : taskResource = " + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("StorageVolumeClientImpl : deleteStorageVolume : End");

        return taskResourceV2;
    }

    @Override
    public ResourceCollection<AttachableStorageVolume> getAttachableVolumes(final RestParams params) {
        LOGGER.trace("StorageVolumeClientImpl : getAttachableVolumes : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_ATTACHABLE_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : getAttachableVolumes : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_ATTACHABLE_VOLUMES, null);
        }

        ResourceCollection<AttachableStorageVolume> attachableVolumes = resourceAdaptor.buildResourceCollection(
                returnObj, AttachableStorageVolume.class);

        LOGGER.debug("StorageVolumeClientImpl : getAttachableVolumes : count :" + attachableVolumes.getCount());
        LOGGER.trace("StorageVolumeClientImpl : getAttachableVolumes : End");

        return attachableVolumes;
    }

    @Override
    public StorageVolumeSnapshot getStorageVolumeSnapshot(RestParams params, String storageVolumeId, String snapshotId) {
        LOGGER.trace("StorageVolumeClientImpl : getStorageVolumeSnapshot : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI, snapshotId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : getStorageVolumeSnapshot : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SNAPSHOT, null);
        }

        StorageVolumeSnapshot storageVolumeSnapshot = resourceAdaptor.buildResourceObject(returnObj,
                StorageVolumeSnapshot.class);

        LOGGER.debug("StorageVolumeClientImpl : getStorageVolumeSnapshot : name :" + storageVolumeSnapshot.getName());
        LOGGER.trace("StorageVolumeClientImpl : getStorageVolumeSnapshot : End");

        return storageVolumeSnapshot;
    }

    @Override
    public ResourceCollection<StorageVolumeSnapshot> getAllStorageVolumeSnapshots(RestParams params, String storageVolumeId) {
        LOGGER.trace("StorageVolumeClientImpl : getAllStorageVolumeSnapshots : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : getAllStorageVolumeSnapshots : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SNAPSHOTS, null);
        }

        ResourceCollection<StorageVolumeSnapshot> storageVolumeSnapshotCollection
                = resourceAdaptor.buildResourceCollection(returnObj, StorageVolumeSnapshot.class);

        LOGGER.debug("StorageVolumeClientImpl : getAllStorageVolumeSnapshots : count :"
                + storageVolumeSnapshotCollection.getCount());
        LOGGER.trace("StorageVolumeClientImpl : getAllStorageVolumeSnapshots : End");

        return storageVolumeSnapshotCollection;
    }

    @Override
    public TaskResourceV2 createStorageVolumeSnapshot(RestParams params, String storageVolumeId,
            StorageVolumeSnapshot snapshot, final boolean aSync) {

        LOGGER.trace("StorageVolumeClientImpl : createStorageVolumeSnapshot : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (snapshot == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_SNAPSHOT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI));

        // create JSON request from storageVolumeDto
        JSONObject jsonObject = resourceAdaptor.buildJsonRequest(snapshot, params.getApiVersion());

        String returnObj = restClient.sendRequest(params, jsonObject);
        TaskResourceV2 taskResource = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : createStorageVolumeSnapshot : returnObj = " + returnObj);
        LOGGER.debug("StorageVolumeClientImpl : createStorageVolumeSnapshot : taskResource = " + taskResource);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update once task is complete or exceeds the timeout.
        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("StorageVolumeClientImpl : createStorageVolumeSnapshot : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 deleteStorageVolumeSnapshot(RestParams params, String storageVolumeId,
            String snapshotId, boolean aSync) {

        LOGGER.trace("StorageVolumeClientImpl : deleteStorageVolumeSnapshot : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, storageVolumeId,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI, snapshotId));

        String returnObj = restClient.sendRequest(params);
        TaskResourceV2 taskResource = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolumeSnapshot : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolumeSnapshot : taskResource =" + taskResource);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update once task is complete or exceeds the timeout.
        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("StorageVolumeClientImpl : deleteStorageVolumeSnapshot : End");

        return taskResource;
    }

    @Override
    public ResourceCollection<ExtraStorageVolume> getExtraManagedStorageVolumePaths(RestParams params) {
        LOGGER.trace("StorageVolumeClientImpl : getExtraManagedStorageVolumePaths : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("alertFixType", "ExtraManagedStorageVolumePaths");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_REPAIR_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeClientImpl : getExtraManagedStorageVolumePaths : response from OV :" + returnObj);
        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUMES, null);
        }

        ResourceCollection<ExtraStorageVolume> extraAccessList
                = resourceAdaptor.buildResourceCollection(returnObj, ExtraStorageVolume.class);

        LOGGER.debug("StorageVolumeClientImpl : getExtraManagedStorageVolumePaths : count :"
                + extraAccessList.getCount());
        LOGGER.trace("StorageVolumeClientImpl : getExtraManagedStorageVolumePaths : End");

        return extraAccessList;
    }

    @Override
    public TaskResourceV2 repairExtraManagedStorageVolumePath(RestParams params, ExtraStorageVolumeRepair repair,
            boolean aSync) {
        LOGGER.trace("StorageVolumeClientImpl : repairExtraManagedStorageVolumePath : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (repair == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUMES, null);
        }

        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_REPAIR_URI));

        JSONObject jsonObject = resourceAdaptor.buildJsonRequest(repair, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : repairExtraManagedStorageVolumePath : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeClientImpl : repairExtraManagedStorageVolumePath : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2 if user is asking for sync mode, calling
        // the tasking polling method and send the update once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("StorageVolumeClientImpl : repairExtraManagedStorageVolumePath : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        StorageVolumeV2 storageVolumeDto = getStorageVolumeByName(params, name);

        if (null != storageVolumeDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(storageVolumeDto.getUri());
        }
        return resourceId;
    }

}
