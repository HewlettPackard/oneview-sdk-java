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
package com.hp.ov.sdk.firmwaredriver;

import java.io.File;

import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.FirmwareBundleClient;
import com.hp.ov.sdk.rest.client.FirmwareBundleClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;


public class FirmwareBundleClientSample {

    private final FirmwareBundleClient firmwareBundleClient;

    // These are variables to be defined by user
    // ================================
    private static final String FIRMWARE_BUNDLE_FILE = "src/main/resources/SPP2013020B.2013_0628.2.iso";
    // ================================

    private FirmwareBundleClientSample() {
        this.firmwareBundleClient = FirmwareBundleClientImpl.getClient();
    }

    private void addFirmwareBundle() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            TaskResourceV2 taskResource = firmwareBundleClient.addFirmwareBundle(
                    params, new File(FIRMWARE_BUNDLE_FILE), false);

            System.out.println("FirmwareBundleClient : addFirmwareBundle :"
                    + " Task object returned to client : " + taskResource.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FirmwareBundleClient : addFirmwareBundle :" + " resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FirmwareBundleClient : addFirmwareBundle :" + " no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FirmwareBundleClient : addFirmwareBundle :" + " Appliance not reachable at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("FirmwareBundleClient : addFirmwareBundle :" + " No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FirmwareBundleClient : addFirmwareBundle :" + " arguments are null ");
        }
    }

    public static void main(final String[] args) throws Exception {
        FirmwareBundleClientSample client = new FirmwareBundleClientSample();

        client.addFirmwareBundle();
    }
}
