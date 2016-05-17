/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class StorageVolumeAttachmentsClientImpl implements StorageVolumeAttachmentsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeAttachmentsClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    protected StorageVolumeAttachmentsClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskAdaptor taskAdaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static StorageVolumeAttachmentsClient getClient() {
        return new StorageVolumeAttachmentsClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public StorageVolumeAttachment getStorageVolumeAttachment(RestParams params, String resourceId) {
        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getStorageVolumeAttachment : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("StorageVolumeAttachmentsClientImpl : getStorageVolumeAttachment : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_ATTACHMENTS, null);
        }

        StorageVolumeAttachment storageVolumeAttachment = adaptor.buildResourceObject(
                response, StorageVolumeAttachment.class);

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getStorageVolumeAttachment : End");

        return storageVolumeAttachment;
    }

    @Override
    public ResourceCollection<StorageVolumeAttachment> getAllStorageVolumeAttachments(RestParams params) {
        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getAllStorageVolumeAttachments : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("StorageVolumeAttachmentsClientImpl : " +
                "getAllStorageVolumeAttachments : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_ATTACHMENTS, null);
        }

        ResourceCollection<StorageVolumeAttachment> storageVolumeAttachmentCollection
                = adaptor.buildResourceCollection(response, StorageVolumeAttachment.class);

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getAllStorageVolumeAttachments : End");

        return storageVolumeAttachmentCollection;
    }

    @Override
    public StorageVolumeAttachmentPath getStorageVolumeAttachmentPath(RestParams params, String attachmentId,
            String pathId) {

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getStorageVolumeAttachmentPath : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, attachmentId,
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI, pathId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("StorageVolumeAttachmentsClientImpl : getStorageVolumeAttachmentPath : " +
                "response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_ATTACHMENTS, null);
        }

        StorageVolumeAttachmentPath path = adaptor.buildResourceObject(response, StorageVolumeAttachmentPath.class);

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getStorageVolumeAttachmentPath : End");

        return path;
    }

    @Override
    public List<StorageVolumeAttachmentPath> getAllStorageVolumeAttachmentPaths(RestParams params,
            String attachmentId) {

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getAllStorageVolumeAttachmentPaths : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, attachmentId,
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("StorageVolumeAttachmentsClientImpl : getAllStorageVolumeAttachmentPaths : " +
                "response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_ATTACHMENTS, null);
        }

        Type listType = new TypeToken<List<StorageVolumeAttachmentPath>>(){}.getType();

        List<StorageVolumeAttachmentPath> pathCollection = adaptor.buildListOfResourceObject(response, listType);

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getAllStorageVolumeAttachmentPaths : End");

        return pathCollection;
    }

    @Override
    public ResourceCollection<ExtraStorageVolume> getExtraUnmanagedStorageVolumeAttachments(RestParams params) {
        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getExtraUnmanagedStorageVolumeAttachments : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("alertFixType", "ExtraUnmanagedStorageVolumes");
        params.setQuery(query);

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("StorageVolumeAttachmentsClientImpl : " +
                "getExtraUnmanagedStorageVolumeAttachments : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_ATTACHMENTS, null);
        }

        ResourceCollection<ExtraStorageVolume> extraStorageVolumeCollection
                = adaptor.buildResourceCollection(response, ExtraStorageVolume.class);

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : getExtraUnmanagedStorageVolumeAttachments : End");

        return extraStorageVolumeCollection;
    }

    @Override
    public TaskResourceV2 repairExtraUnmanagedStorageVolumeAttachment(RestParams params,
            ExtraStorageVolumeRepair repair, boolean aSync) {

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : repairExtraUnmanagedStorageVolumeAttachment : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(repair, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("StorageVolumeAttachmentsClientImpl : " +
                "repairExtraUnmanagedStorageVolumeAttachment : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_ATTACHMENTS, null);
        }

        TaskResourceV2 task = taskAdaptor.buildDto(response);

        if (task != null && aSync == false) {
            task = taskMonitor.checkStatus(params, task.getUri(), TIMEOUT);
        }

        LOGGER.trace("StorageVolumeAttachmentsClientImpl : repairExtraUnmanagedStorageVolumeAttachment : End");

        return task;
    }

}
