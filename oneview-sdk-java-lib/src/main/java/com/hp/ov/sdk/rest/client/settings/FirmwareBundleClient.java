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
package com.hp.ov.sdk.rest.client.settings;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;

public class FirmwareBundleClient {

    protected static final String FIRMWARE_BUNDLE_URI = "/rest/firmware-bundles";

    private static final Logger LOGGER = LoggerFactory.getLogger(FirmwareBundleClient.class);
    private static final int TIMEOUT = 300000; // in milliseconds = 5 mins
    private final BaseClient baseClient;

    public FirmwareBundleClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Uploads an SPP ISO image file or a hotfix file to the appliance.
     *
     * The API supports uploading one hotfix at a time into the system.
     *
     * @param firmwareBundle path to the SPP ISO image file or hotfix file.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource add(File firmwareBundle, boolean aSync) {
        LOGGER.info("FirmwareBundleClient : add : Start");

        validateFirmwareBundleFile(firmwareBundle);

        Request request = new Request(HttpMethod.POST, FIRMWARE_BUNDLE_URI, firmwareBundle);

        request.setContentType(ContentType.MULTIPART_FORM_DATA);
        request.setTimeout(TIMEOUT);

        TaskResource taskResource = baseClient.executeMonitorableRequest(request);

        LOGGER.info("FirmwareBundleClient : add : End");

        return taskResource;
    }

    private void validateFirmwareBundleFile(File firmwareBundle) {
        if (!(firmwareBundle.exists() && firmwareBundle.isFile())) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, SdkConstants.FIRMWARE_BUNDLE);
        }
    }

}
