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
package com.hp.ov.sdk.rest.client.networking;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.ConnectionProperty;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.SwitchPortStatistics;
import com.hp.ov.sdk.dto.networking.SwitchStatistics;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueFormat;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueType;
import com.hp.ov.sdk.dto.networking.switches.Switch;
import com.hp.ov.sdk.dto.networking.switches.SwitchManagementConnection;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;

public class SwitchClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String SWITCH_RESOURCE_ID = "fbc7dffe-e020-4efc-b89a-9db5b4469da4";
    private static final String SWITCH_NAME = "172.18.16.1";
    private static final String SWITCH_PORT_NAME = "1.5";
    // ================================

    private final SwitchClient switchClient;

    public SwitchClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.switchClient = oneViewClient.switches();
    }

    private void getSwitch() {
        Switch aSwitch = this.switchClient.getById(SWITCH_RESOURCE_ID);

        LOGGER.info("Switch object returned to client: {}", aSwitch.toJsonString());
    }

    private void getAllSwitches() {
        ResourceCollection<Switch> switches = this.switchClient.getAll();

        LOGGER.info("Switches returned to client: {}", switches.toJsonString());
    }

    private void getSwitchByName() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);

        LOGGER.info("Switch object returned to client: {}", aSwitch.toJsonString());
    }

    private void addSwitch() {
        Switch aSwitch = this.buildSwitch();

        TaskResource taskResource = this.switchClient.add(aSwitch);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateSwitch() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);
        String resourceId = aSwitch.getResourceId();

        SwitchManagementConnection switchManagementConnection = new SwitchManagementConnection();
        List<ConnectionProperty> connectionProperties = new ArrayList<>();

        ConnectionProperty e = new ConnectionProperty();
        e.setPropertyName("hostname");
        e.setValue(SWITCH_NAME);
        e.setValueFormat(ValueFormat.Unknown);
        e.setValueType(ValueType.String);
        connectionProperties.add(e);

        ConnectionProperty e1 = new ConnectionProperty();
        e.setPropertyName("password");
        e.setValue("dcs");
        e.setValueFormat(ValueFormat.SecuritySensitive);
        e.setValueType(ValueType.String);
        connectionProperties.add(e1);

        ConnectionProperty e2 = new ConnectionProperty();
        e.setPropertyName("userName");
        e.setValue("dcs");
        e.setValueFormat(ValueFormat.Unknown);
        e.setValueType(ValueType.String);
        connectionProperties.add(e2);

        switchManagementConnection.setConnectionProperties(connectionProperties);
        aSwitch.setSwitchManagementConnection(switchManagementConnection);

        // FIXME no changes due to problems after executing an update

        TaskResource taskResource = this.switchClient.update(resourceId, aSwitch);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void removeSwitch() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);

        TaskResource taskResource = this.switchClient.remove(aSwitch.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void refreshSwitch() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);

        TaskResource taskResource = this.switchClient.refresh(aSwitch.getResourceId(), TaskTimeout.of(60000));

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getSwitchEnvironmentalConfiguration() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);
        EnvironmentalConfiguration environmentalConfiguration = this.switchClient
                .getEnvironmentalConfiguration(aSwitch.getResourceId());

        LOGGER.info("EnvironmentalConfiguration object returned to client: {}",
                environmentalConfiguration.toJsonString());
    }

    private void getSwitchStatistics() {
        Switch aSwitch = this.switchClient.getById(SWITCH_RESOURCE_ID);
        SwitchStatistics switchStatistics = this.switchClient.getStatistics(aSwitch.getResourceId());

        LOGGER.info("SwitchStatistics object returned to client: {}", switchStatistics.toJsonString());
    }

    private void getSwitchPortStatistics() {
        Switch aSwitch = this.switchClient.getById(SWITCH_RESOURCE_ID);
        SwitchPortStatistics switchPortStatistics = this.switchClient.getPortStatistics(aSwitch.getResourceId(),
                SWITCH_PORT_NAME);

        LOGGER.info("SwitchPortStatistics object returned to client: {}", switchPortStatistics.toJsonString());
    }

    private void updatePorts() {
        Switch aSwitch = this.switchClient.getById(SWITCH_RESOURCE_ID);

        List<Port> ports = new ArrayList<>();

        for (Port p : aSwitch.getPorts()) {
            if (p.getName().equals("1.1")) {
                p.setEnabled(!p.getEnabled());
                ports.add(p);
                break;
            }
        }

        TaskResource taskResource = this.switchClient.updatePorts(aSwitch.getResourceId(), ports);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private Switch buildSwitch() {
        Switch switchObj = new Switch();
        SwitchManagementConnection mgmt = new SwitchManagementConnection();
        List<ConnectionProperty> properties = new ArrayList<>();

        properties.add(new ConnectionProperty("userName", "dcs", ValueFormat.Unknown, ValueType.String));
        properties.add(new ConnectionProperty("hostname", "172.18.16.1", ValueFormat.Unknown, ValueType.String));
        properties.add(new ConnectionProperty("password", "dcs", ValueFormat.SecuritySensitive, ValueType.String));

        mgmt.setConnectionProperties(properties);

        switchObj.setSwitchManagementConnection(mgmt);
        switchObj.setType(ResourceCategory.RC_SWITCH);

        return switchObj;
    }

    public static void main(String[] args) {
        
        /* This resource is available only on C7000 */
        SwitchClientSample client = new SwitchClientSample();

        client.getSwitch();
        client.getAllSwitches();
        client.getSwitchByName();

        client.getSwitchEnvironmentalConfiguration();

        /* methods available only in version 1.2 */
        client.refreshSwitch();
        client.addSwitch();
        client.updateSwitch();
        client.removeSwitch();

        /* methods available only in version 2.0 */
        client.getSwitchStatistics();
        client.getSwitchPortStatistics();

        /* methods available only in version 3.0 */
        client.updatePorts();
    }

}
