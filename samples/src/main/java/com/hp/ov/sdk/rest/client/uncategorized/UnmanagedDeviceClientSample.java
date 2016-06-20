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

package com.hp.ov.sdk.rest.client.uncategorized;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.uncategorized.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.UnmanagedDeviceClient;
import com.hp.ov.sdk.rest.client.UnmanagedDeviceClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class UnmanagedDeviceClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String UNMANAGED_DEVICE_RESOURCE_ID = "9ba5afcd-804f-4a00-9492-6482d26e80d8";
    private static final String UNMANAGED_DEVICE_NAME = "Sample UnmanagedDevice";
    // ================================

    private final UnmanagedDeviceClient unmanagedDeviceClient;

    public UnmanagedDeviceClientSample() {
        this.unmanagedDeviceClient = UnmanagedDeviceClientImpl.getClient();
    }

    private void getUnmanagedDevice() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            UnmanagedDevice unmanagedDevice = this.unmanagedDeviceClient.getUnmanagedDevice(params,
                    UNMANAGED_DEVICE_RESOURCE_ID);

            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDevice : " +
                    "UnmanagedDevice object returned to client : " + unmanagedDevice);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDevice : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDevice : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDevice : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDevice : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDevice : " +
                    "arguments are null ");
        }
    }

    private void getAllUnmanagedDevices() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            ResourceCollection<UnmanagedDevice> unmanagedDevices
                    = this.unmanagedDeviceClient.getAllUnmanagedDevices(params);

            System.out.println("UnmanagedDeviceClientSample : getAllUnmanagedDevices : " +
                    "UnmanagedDeviceCollection object returned to client (count) : " + unmanagedDevices.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : getAllUnmanagedDevices : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : getAllUnmanagedDevices : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : getAllUnmanagedDevices : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : getAllUnmanagedDevices : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : getAllUnmanagedDevices : " +
                    "arguments are null ");
        }
    }

    private void getUnmanagedDeviceByName() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            UnmanagedDevice unmanagedDevice = this.unmanagedDeviceClient.getUnmanagedDeviceByName(params,
                    UNMANAGED_DEVICE_NAME);

            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDeviceByName : " +
                    "UnmanagedDevice object returned to client : " + unmanagedDevice);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDeviceByName : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDeviceByName : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDeviceByName : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDeviceByName : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : getUnmanagedDeviceByName : " +
                    "arguments are null ");
        }
    }

    private void addUnmanagedDevice() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

            unmanagedDevice.setName(UNMANAGED_DEVICE_NAME);
            unmanagedDevice.setModel("Procurve 4200VL");
            unmanagedDevice.setDeviceType("Server");
            unmanagedDevice.setIpv4Address("192.168.0.2");
            unmanagedDevice.setMac("68:a5:99:az:71:wc");

            UnmanagedDevice addedUnmanagedDevice = this.unmanagedDeviceClient.addUnmanagedDevice(params,
                    unmanagedDevice);

            System.out.println("UnmanagedDeviceClientSample : addUnmanagedDevice : " +
                    "UnmanagedDevice object returned to client : " + addedUnmanagedDevice);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : addUnmanagedDevice : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : addUnmanagedDevice : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : addUnmanagedDevice : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : addUnmanagedDevice : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : addUnmanagedDevice : " +
                    "arguments are null ");
        }
    }

    private void updateUnmanagedDevice() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            UnmanagedDevice unmanagedDevice = this.unmanagedDeviceClient.getUnmanagedDeviceByName(params,
                    UNMANAGED_DEVICE_NAME);
            String resourceId = UrlUtils.getResourceIdFromUri(unmanagedDevice.getUri());

            unmanagedDevice.setDeviceType("Blade System");

            UnmanagedDevice updatedUnmanagedDevice = this.unmanagedDeviceClient.updateUnmanagedDevice(params,
                    resourceId, unmanagedDevice);

            System.out.println("UnmanagedDeviceClientSample : updateUnmanagedDevice : " +
                    "UnmanagedDevice object returned to client : " + updatedUnmanagedDevice);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : updateUnmanagedDevice : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : updateUnmanagedDevice : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : updateUnmanagedDevice : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : updateUnmanagedDevice : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : updateUnmanagedDevice : " +
                    "arguments are null ");
        }
    }

    private void getEnvironmentalConfiguration() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.unmanagedDeviceClient.getId(params, UNMANAGED_DEVICE_NAME);
            EnvironmentalConfiguration environmentalConfiguration
                    = this.unmanagedDeviceClient.getEnvironmentalConfiguration(params, resourceId);

            System.out.println("UnmanagedDeviceClientSample : getEnvironmentalConfiguration : " +
                    "EnvironmentalConfiguration returned to client : " + environmentalConfiguration);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : getEnvironmentalConfiguration : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : getEnvironmentalConfiguration : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : getEnvironmentalConfiguration : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : getEnvironmentalConfiguration : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : getEnvironmentalConfiguration : " +
                    "arguments are null ");
        }
    }

    private void removeUnmanagedDevice() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.unmanagedDeviceClient.getId(params, UNMANAGED_DEVICE_NAME);
            String response = this.unmanagedDeviceClient.removeUnmanagedDevice(params, resourceId);

            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDevice : " +
                    "response returned to client : " + response);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDevice : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDevice : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDevice : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDevice : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDevice : " +
                    "arguments are null ");
        }
    }

    private void removeUnmanagedDeviceByFilter() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String filter = "name='" + UNMANAGED_DEVICE_NAME +"'";
            TaskResourceV2 task = this.unmanagedDeviceClient.removeUnmanagedDeviceByFilter(params, filter, false);

            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDeviceByFilter : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDeviceByFilter : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDeviceByFilter : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDeviceByFilter : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDeviceByFilter : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDeviceByFilter : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("UnmanagedDeviceClientSample : removeUnmanagedDeviceByFilter : "
                    + "errors in task, please check task resource for more details");
        }
    }

    public static void main(String[] args) {
        UnmanagedDeviceClientSample sample = new UnmanagedDeviceClientSample();

        sample.getUnmanagedDevice();
        sample.getAllUnmanagedDevices();
        sample.addUnmanagedDevice();
        sample.updateUnmanagedDevice();
        sample.getUnmanagedDeviceByName();
        sample.getEnvironmentalConfiguration();

        sample.removeUnmanagedDevice();

        /*sample.addUnmanagedDevice();*/
        sample.removeUnmanagedDeviceByFilter();
    }

}
