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
import java.util.List;

import com.google.common.reflect.Parameter;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.RequestInterceptor;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;

@Api(FirmwareBundleClient.FIRMWARE_BUNDLE_URI)
public interface FirmwareBundleClient {

    String FIRMWARE_BUNDLE_URI = "/rest/firmware-bundles";

    /**
     * Uploads an SPP ISO image file or a hotfix file to the appliance.
     * <p>The API supports uploading one hotfix at a time into the system.</p>
     *
     * @param firmwareBundle path to the SPP ISO image file or hotfix file.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(method = HttpMethod.POST, requestInterceptor = UploadFirmwareBundleRequestInterceptor.class)
    TaskResource upload(@BodyParam(type = ContentType.MULTIPART_FORM_DATA) File firmwareBundle,
            RequestOption... options);

    class UploadFirmwareBundleRequestInterceptor implements RequestInterceptor {
        @Override
        public Request intercept(Request request, List<Parameter> params, Object[] args) {
            File firmwareBundle = (File) args[0];

            this.validateFirmwareBundleFile(firmwareBundle);

            return request;
        }

        private void validateFirmwareBundleFile(File firmwareBundle) {
            if (!(firmwareBundle.exists() && firmwareBundle.isFile())) {
                throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                        "Invalid firmware bundle file (file does not exists or it is not a regular file)");
            }
        }
    }

}
