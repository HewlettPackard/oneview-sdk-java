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
package com.hp.ov.sdk.rest.client.facilities;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.ImportPdd;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.Light;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.OutletState;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PhaseType;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.Power;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerConnection;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDeviceType;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class PowerDeliveryDeviceClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerDeliveryDeviceClientSample.class);

    // test values - user input
    // ================================
    private static final String RESOURCE_ID = "35323930-4936-4450-5531-303153474820";
    private static final String RESOURCE_NAME = "172.18.8.11, PDU 1, L6,Outlet1";
    private static final String SAMPLE_RESOURCE_NAME = "SamplePDD";
    private static final String SAMPLE_RESOURCE_NAME_UPDATED = "SamplePDD-Updated";
    private static final String HOSTNAME = "172.18.8.12";
    private static final String USERNAME = "dcs";
    private static final String PASSWORD = "dcs";
    
    private static final int DEVICE_CONNECTION = 6;
    private static final String CONNECTION_URI = "/rest/enclosures/0000000000A66101";
    private static final String SERIAL_NUMBER = "SERIE1";
    private static final String PART_NUMBER = "1";
    private static final String FEED_ID = "A";
    private static final int LINE_VOLTAGE = 110;
    private static final int RATED_CAPACITY = 266;
    private static final String MODEL = "Model";
    // ================================

    private final PowerDeliveryDeviceClient client;

    private PowerDeliveryDeviceClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        client = oneViewClient.powerDeliveryDevice();
    }

    private void getPowerDeliveryDeviceById() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getById(RESOURCE_ID);

        LOGGER.info("PowerDeliveryDevice object returned to client: {}", powerDeliveryDevice.toJsonString());
    }

    private void getAllPowerDeliveryDevices() {
        ResourceCollection<PowerDeliveryDevice> powerDeliveryDevices = this.client.getAll();

        LOGGER.info("PowerDeliveryDevices returned to client: {}", powerDeliveryDevices.toJsonString());
    }

    private void getPowerDeliveryDeviceByName() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(RESOURCE_NAME).get(0);

        LOGGER.info("PowerDeliveryDevice object returned to client: {}", powerDeliveryDevice.toJsonString());
    }

    private void addPowerDeliveryDevice() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();
        powerDeliveryDevice.setName(SAMPLE_RESOURCE_NAME);
        powerDeliveryDevice.setRatedCapacity(40);

        PowerDeliveryDevice addedPowerDeliveryDevice = this.client.add(powerDeliveryDevice);

        LOGGER.info("PowerDeliveryDevice object returned to client: {}", addedPowerDeliveryDevice.toJsonString());
    }
    
    private void addPowerDeliveryDeviceWithPowerConnection() {
        PowerDeliveryDevice powerDeliveryDevice = new PowerDeliveryDevice();
        powerDeliveryDevice.setName(SAMPLE_RESOURCE_NAME);
        
        powerDeliveryDevice.setDeviceType(PowerDeliveryDeviceType.PowerStrip);
        powerDeliveryDevice.setModel(MODEL);
        powerDeliveryDevice.setRatedCapacity(RATED_CAPACITY);
        powerDeliveryDevice.setLineVoltage(LINE_VOLTAGE);
        powerDeliveryDevice.setPhaseType(PhaseType.SinglePhase);
        powerDeliveryDevice.setFeedIdentifier(FEED_ID);
        powerDeliveryDevice.setPartNumber(PART_NUMBER);
        powerDeliveryDevice.setSerialNumber(SERIAL_NUMBER);
        
        PowerConnection powerConnection = new PowerConnection();
        powerConnection.setConnectionUri(CONNECTION_URI);
        powerConnection.setDeviceConnection(DEVICE_CONNECTION);
        powerDeliveryDevice.setPowerConnections(Arrays.asList(powerConnection));

        PowerDeliveryDevice addedPowerDeliveryDevice = this.client.add(powerDeliveryDevice);

        LOGGER.info("PowerDeliveryDevice object returned to client: {}", addedPowerDeliveryDevice.toJsonString());
    }

    private void addPowerDeliveryDeviceByDiscover() {
        ImportPdd importPdd = new ImportPdd();

        importPdd.setHostname(HOSTNAME);
        importPdd.setUsername(USERNAME);
        importPdd.setPassword(PASSWORD);
        importPdd.setForce(true);

        TaskResource task = this.client.add(importPdd);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void updatePowerDeliveryDevice() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(SAMPLE_RESOURCE_NAME).get(0);
        String resourceId = powerDeliveryDevice.getResourceId();

        powerDeliveryDevice.setName(SAMPLE_RESOURCE_NAME_UPDATED);

        PowerDeliveryDevice updatedPowerDeliveryDevice = this.client.update(resourceId,
                powerDeliveryDevice);

        LOGGER.info("PowerDeliveryDevice object returned to client: {}", updatedPowerDeliveryDevice.toJsonString());
    }

    private void removePowerDeliveryDevice() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(SAMPLE_RESOURCE_NAME_UPDATED).get(0);

        TaskResource task = this.client.remove(powerDeliveryDevice.getResourceId(), TaskTimeout.of(60000));

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void removePowerDeliveryDeviceByFilter() {
        String filter = "'name' = '" + SAMPLE_RESOURCE_NAME + "'";
        TaskResource task = this.client.removeByFilter(filter);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void removePowerDeliveryDeviceSynchronously() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(SAMPLE_RESOURCE_NAME).get(0);

        String response = this.client.remove(powerDeliveryDevice.getResourceId());

        LOGGER.info("Response returned to client: {}", response);
    }

    private void getPowerDeliveryDevicePowerState() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(RESOURCE_NAME).get(0);

        Power power = client.getPowerState(powerDeliveryDevice.getResourceId());

        LOGGER.info("Power object returned to client: {}", JsonPrettyPrinter.print(power));
    }

    private void updatePowerDeliveryDevicePowerState() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(RESOURCE_NAME).get(0);
        OutletState outletState = new OutletState();

        outletState.setPowerState(Power.On);

        TaskResource task = this.client.updatePowerState(powerDeliveryDevice.getResourceId(), outletState);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void updatePowerDeliveryDeviceRefreshState() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(RESOURCE_NAME).get(0);
        PowerDeliveryDeviceRefreshRequest refreshState = new PowerDeliveryDeviceRefreshRequest();

        refreshState.setRefreshState(RefreshState.RefreshPending);

        TaskResource task = this.client.updateRefreshState(powerDeliveryDevice.getResourceId(), refreshState);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void getPowerDeliveryDeviceUidState() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(RESOURCE_NAME).get(0);

        Light light = client.getUidState(powerDeliveryDevice.getResourceId());

        LOGGER.info("Light object returned to client: {}", JsonPrettyPrinter.print(light));
    }

    private void updatePowerDeliveryDeviceUidState() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(RESOURCE_NAME).get(0);
        OutletState outletStateState = new OutletState();

        outletStateState.setUidState(Light.On);

        TaskResource task = this.client.updateUidState(powerDeliveryDevice.getResourceId(), outletStateState);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void getPowerDeliveryDeviceUtilization() {
        PowerDeliveryDevice powerDeliveryDevice = this.client.getByName(RESOURCE_NAME).get(0);

        UtilizationData utilization = client.getUtilization(powerDeliveryDevice.getResourceId());

        LOGGER.info("UtilizationData object returned to client: {}", JsonPrettyPrinter.print(utilization));
    }

    public static void main(String[] args) {
        PowerDeliveryDeviceClientSample client = new PowerDeliveryDeviceClientSample();

        client.addPowerDeliveryDevice();
        
        client.addPowerDeliveryDeviceByDiscover();
        client.addPowerDeliveryDeviceWithPowerConnection();
        
        client.getPowerDeliveryDeviceById();

        client.getAllPowerDeliveryDevices();
        client.getPowerDeliveryDeviceByName();

        client.getPowerDeliveryDevicePowerState();
        client.updatePowerDeliveryDevicePowerState();

        client.updatePowerDeliveryDeviceRefreshState();

        client.getPowerDeliveryDeviceUidState();
        client.updatePowerDeliveryDeviceUidState();

        client.getPowerDeliveryDeviceUtilization();

        client.updatePowerDeliveryDevice();

        client.removePowerDeliveryDevice();
        client.removePowerDeliveryDeviceByFilter();
        client.removePowerDeliveryDeviceSynchronously();
    }

}
