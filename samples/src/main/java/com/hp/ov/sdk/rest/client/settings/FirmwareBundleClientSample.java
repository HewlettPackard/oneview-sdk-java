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

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.OneViewClient;


public class FirmwareBundleClientSample {

    private final FirmwareBundleClient client;

    // These are variables to be defined by user
    // ================================
    private static final String FIRMWARE_BUNDLE_FILE = "src/main/resources/SPP2013020B.2013_0628.2.iso";
    // ================================

    private FirmwareBundleClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.firmwareBundle();
    }

    private void addFirmwareBundle() {
        File firmwareBundleFile = new File(FIRMWARE_BUNDLE_FILE);

        TaskResourceV2 task = this.client.add(firmwareBundleFile, false);

        System.out.println("FirmwareBundleClientSample : addFirmwareBundle : " +
                "Task object returned to client : " + task);
    }

    public static void main(final String[] args) {
        FirmwareBundleClientSample client = new FirmwareBundleClientSample();

        client.addFirmwareBundle();
    }
}