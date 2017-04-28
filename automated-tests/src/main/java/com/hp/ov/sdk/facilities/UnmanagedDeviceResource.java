/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.facilities;

import java.util.Map;

import com.hp.ov.sdk.dto.facilities.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.facilities.UnmanagedDeviceClient;

public class UnmanagedDeviceResource extends BasicResource implements CreateResource, UpdateResource, RemoveResource {

    private static UnmanagedDeviceResource instance;

    private UnmanagedDeviceClient client;

    public UnmanagedDeviceResource() {
        client = oneViewClient.unmanagedDevice();
    }

    public static UnmanagedDeviceResource getInstance() {
        if (instance == null) {
            instance = new UnmanagedDeviceResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        UnmanagedDevice unmanagedDevice = (UnmanagedDevice) getResource(client.getByName(name));
        return unmanagedDevice == null ? "" : unmanagedDevice.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        client.add(builder());
    }

    @Override
    public UnmanagedDevice builder() {
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();
        unmanagedDevice.setName(resourceProperties.get("name"));
        unmanagedDevice.setModel(resourceProperties.get("model"));
        unmanagedDevice.setDeviceType(resourceProperties.get("deviceType"));
        unmanagedDevice.setIpv4Address(resourceProperties.get("ipv4Address"));
        unmanagedDevice.setMac(resourceProperties.get("mac"));
        return unmanagedDevice;
    }

    @Override
    public String update(String id) {
        UnmanagedDevice unmanagedDevice = client.getById(id);
        unmanagedDevice.setDeviceType(resourceProperties.get("deviceType"));
        return objToString(client.update(id, unmanagedDevice));
    }

    @Override
    public String remove(String id) {
        return client.remove(id);
    }

    public String removeByFilter() {
        String filter = "'name' = '" + resourceProperties.get("name") + "'";
        return taskToString(client.removeByFilter(filter));
    }

    public String getEnvironmentalConfiguration(String id) {
        return objToString(client.getEnvironmentalConfiguration(id));
    }

}
