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

import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FirmwareBundleClient {

    /**
     * Upload an SPP ISO image file or a hotfix file to the appliance.
     *
     * The API supports upload of one hotfix at a time into the system.
     *
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param firmwareBundle
     *            The path to the SPP ISO image file or hotfix file.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     *
     * @return {@link FwBaseline} containing the firmware driver details.
     */
    TaskResourceV2 addFirmwareBundle(RestParams params, File firmwareBundle, boolean aSync);

}
