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

import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.ImportPdd;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.Light;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.OutletState;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PhaseType;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.Power;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDeviceType;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient;

public class PowerDeliveryDeviceResource extends BasicResource
        implements CreateResource, UpdateResource, RemoveResource {

    private static PowerDeliveryDeviceResource instance;

    private PowerDeliveryDeviceClient client;

    public PowerDeliveryDeviceResource() {
        client = oneViewClient.powerDeliveryDevice();
    }

    public static PowerDeliveryDeviceResource getInstance() {
        if (instance == null) {
            instance = new PowerDeliveryDeviceResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        PowerDeliveryDevice powerDevice = (PowerDeliveryDevice) getResource(client.getByName(name));
        return powerDevice == null ? "" : powerDevice.getResourceId();
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

    public void createSynergy() {
        client.add(builderSynergy());
    }

    @Override
    public PowerDeliveryDevice builder() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();
        powerDeliveryDevice.setName(resourceProperties.get("name"));
        powerDeliveryDevice.setModel(resourceProperties.get("model"));
        powerDeliveryDevice.setRatedCapacity(Integer.parseInt(resourceProperties.get("ratedCapacity")));
        powerDeliveryDevice.setLineVoltage(Integer.parseInt(resourceProperties.get("lineVoltage")));
        powerDeliveryDevice.setPhaseType(PhaseType.SinglePhase);
        
        return powerDeliveryDevice;
    }

    private PowerDeliveryDevice builderSynergy() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();

        powerDeliveryDevice.setDeviceType(PowerDeliveryDeviceType.valueOf(resourceProperties.get("type")));
        powerDeliveryDevice.setName(resourceProperties.get("name"));
        powerDeliveryDevice.setModel(resourceProperties.get("model"));
        powerDeliveryDevice.setRatedCapacity(Integer.parseInt(resourceProperties.get("ratedCapacity")));
        powerDeliveryDevice.setLineVoltage(Integer.parseInt(resourceProperties.get("lineVoltage")));
        powerDeliveryDevice.setPhaseType(PhaseType.valueOf(resourceProperties.get("volts")));
        powerDeliveryDevice.setFeedIdentifier(resourceProperties.get("feed"));
        powerDeliveryDevice.setPartNumber(resourceProperties.get("partNumber"));
        powerDeliveryDevice.setSerialNumber(resourceProperties.get("serialNumber"));

        return powerDeliveryDevice;
    }

    public String createByDiscover() {
        ImportPdd importPdd = new ImportPdd();
        importPdd.setHostname(resourceProperties.get("hostname"));
        importPdd.setUsername(resourceProperties.get("username"));
        importPdd.setPassword(resourceProperties.get("password"));
        importPdd.setForce(Boolean.valueOf(resourceProperties.get("force")));
        return taskToString(client.add(importPdd));
    }

    @Override
    public String update(String id) {
        PowerDeliveryDevice powerDevice = client.getById(id);
        powerDevice.setName(resourceProperties.get("name"));
        powerDevice.setModel(resourceProperties.get("model"));
        return objToString(client.update(id, powerDevice));
    }

    @Override
    public String remove(String id) {
        return client.remove(id);
    }

    public String removeByFilter() {
        String filter = "name='" + resourceProperties.get("name") + "'";
        return taskToString(client.removeByFilter(filter));
    }

    public String removeSynchronously(String id) {
        return client.remove(id);
    }

    public String getPowerState(String id) {
        return objToString(client.getPowerState(id));
    }

    public String getUidState(String id) {
        return objToString(client.getUidState(id));
    }

    public String getUtilization(String id) {
        return objToString(client.getUtilization(id));
    }

    public String updatePowerState(String id) {
        OutletState outletState = new OutletState();
        outletState.setPowerState(Power.valueOf(resourceProperties.get("powerState")));
        return taskToString(client.updatePowerState(id, outletState));
    }

    public String updateRefreshState(String id) {
        PowerDeliveryDeviceRefreshRequest refreshState = new PowerDeliveryDeviceRefreshRequest();
        refreshState.setRefreshState(RefreshState.valueOf(resourceProperties.get("refreshState")));
        return taskToString(client.updateRefreshState(id, refreshState));
    }

    public String updateUidState(String id) {
        OutletState outletStateState = new OutletState();
        outletStateState.setUidState(Light.valueOf(resourceProperties.get("uidState")));
        return taskToString(client.updateUidState(id, outletStateState));
    }

}