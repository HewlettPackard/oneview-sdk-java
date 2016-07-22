/*
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
 */
package com.hp.ov.sdk.rest.client;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class FirmwareBundleClientImpl implements FirmwareBundleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirmwareBundleClientImpl.class);
    private static final int TIMEOUT = 300000; // in milliseconds = 5 mins

    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private FirmwareBundleClientImpl(HttpRestClient httpClient,
        TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {
        this.httpClient = httpClient;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static FirmwareBundleClientImpl getClient() {
        return new FirmwareBundleClientImpl(
                HttpRestClient.getClient(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public TaskResourceV2 addFirmwareBundle(RestParams params, File firmwareBundle, boolean aSync) {
        LOGGER.info("FirmwareBundleClientImpl : addFirmwareBundle : Start");

        if ((null == params) || (firmwareBundle == null)) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }

        validateFirmwareBundleFile(firmwareBundle);

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_BUNDLE_URI));

        String returnObj = httpClient.sendMultipartPostRequest(params, firmwareBundle);

        LOGGER.debug("FirmwareBundleClientImpl : addFirmwareBundle : response from OV :" + returnObj);

        if (StringUtils.isBlank(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance,
                    null, null, null, SdkConstants.FIRMWARE_DRIVER, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("FirmwareBundleClientImpl : addFirmwareBundle : taskResource =" + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }

        LOGGER.info("FirmwareBundleClientImpl : addFirmwareBundle : End");

        return taskResourceV2;
    }

    private void validateFirmwareBundleFile(File firmwareBundle) {
        if (!(firmwareBundle.exists() && firmwareBundle.isFile())) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.FIRMWARE_BUNDLE, null);
        }
    }

}
