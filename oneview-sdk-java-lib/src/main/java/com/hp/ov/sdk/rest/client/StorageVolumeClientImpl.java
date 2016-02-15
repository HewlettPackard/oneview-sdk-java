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
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.adaptors.StorageVolumeAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.AttachableStorageVolumeCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StorageVolumeCollection;
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
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageVolumeClientImpl implements StorageVolumeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final StorageVolumeAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    private JSONObject jsonObject;

    protected StorageVolumeClientImpl(StorageVolumeAdaptor adaptor,
        TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {

        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static StorageVolumeClient getClient() {
        return new StorageVolumeClientImpl(
                new StorageVolumeAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public StorageVolumeV2 getStorageVolume(final RestParams params, final String resourceId) {
        LOGGER.info("StorageVolumeClientImpl : getStorageVolume : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("StorageVolumeClientImpl : getStorageVolume : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_VOLUME,
                    null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeV2 storageVolumeDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : getStorageVolume : name :" + storageVolumeDto.getName());
        LOGGER.info("StorageVolumeClientImpl : getStorageVolume : End");

        return storageVolumeDto;
    }

    @Override
    public StorageVolumeCollection getAllStorageVolumes(final RestParams params) {
        LOGGER.info("StorageVolumeClientImpl : getAllStorageVolumes : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("StorageVolumeClientImpl : getAllStorageVolumes : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_VOLUMES,
                    null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeCollection storageVolumeCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : getAllStorageVolumes : count :" + storageVolumeCollectionDto.getCount());
        LOGGER.info("StorageVolumeClientImpl : getAllStorageVolumes : End");

        return storageVolumeCollectionDto;
    }

    @Override
    public StorageVolumeV2 getStorageVolumeByName(final RestParams params, final String name) {
        StorageVolumeV2 storageVolumeDto = null;
        LOGGER.info("StorageVolumeClientImpl : getStorageVolumeByName : Start");

        // final String query = "filter=\"name=\'" + name + "\'\"";
        final String query = UrlUtils.createFilterString(name);
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("StorageVolumeClientImpl : getStorageVolumeByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_VOLUMES,
                    null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeCollection storageVolumeCollectionDto = adaptor.buildCollectionDto(returnObj);

        if (storageVolumeCollectionDto.getCount() != 0) {
            storageVolumeDto = storageVolumeCollectionDto.getMembers().get(0);
        } else {
            storageVolumeDto = null;
        }

        if (storageVolumeDto == null) {
            LOGGER.error("StorageVolumeClientImpl : getStorageVolumeByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.STORAGE_VOLUME,
                    null);
        }
        LOGGER.info("StorageVolumeClientImpl : getStorageVolumeByName : End");

        return storageVolumeDto;

    }

    @Override
    public TaskResourceV2 createStorageVolume(final RestParams params, final AddStorageVolumeV2 addStorageVolumeDto,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("StorageVolumeClientImpl : createStorageVolume : Start");
        String returnObj = null;

        // validate params
        if (addStorageVolumeDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.STORAGE_VOLUMES,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI));

        // TODO - check for json request in the input storageVolumeDto. if it is
        // present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolume storageVolumeDto.

        // create JSON request from storageVolumeDto
        jsonObject = adaptor.buildJsonObjectFromDto(addStorageVolumeDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : createStorageVolume : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeClientImpl : createStorageVolume : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("StorageVolumeClientImpl : createStorageVolume : End");

        return taskResourceV2;
    }

    @Override
    public String updateStorageVolume(final RestParams params, final String resourceId, final StorageVolumeV2 storageVolumeDto,
            final boolean useJsonRequest) {
        LOGGER.info("StorageVolumeClientImpl : updateStorageVolume : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (storageVolumeDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.STORAGE_VOLUMES,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolume dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storageVolumeDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Updated";
        }

        LOGGER.debug("StorageVolumeClientImpl : updateStorageVolume : returnObj =" + returnObj);

        LOGGER.info("StorageVolumeClientImpl : updateStorageVolume : End");

        return returnObj;
    }

    @Override
    public TaskResourceV2 deleteStorageVolume(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.info("StorageVolumeClientImpl : deleteStorageVolume : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolume : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_VOLUMES,
                    null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolume : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeClientImpl : deleteStorageVolume : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("StorageVolumeClientImpl : deleteStorageVolume : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        StorageVolumeV2 storageVolumeDto = getStorageVolumeByName(creds, name);

        if (null != storageVolumeDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(storageVolumeDto.getUri());
        }
        return resourceId;
    }

    @Override
    public AttachableStorageVolumeCollection getAttachableVolumes(final RestParams params) {
        // TODO Auto-generated method stub
        return null;
    }

}
