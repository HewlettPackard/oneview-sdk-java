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

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SwitchPortStatistics;
import com.hp.ov.sdk.dto.SwitchStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ConnectionProperty;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.ValueFormat;
import com.hp.ov.sdk.dto.generated.ValueType;
import com.hp.ov.sdk.dto.networking.switches.Switch;
import com.hp.ov.sdk.dto.networking.switches.SwitchManagementConnection;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class SwitchClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String SWITCH_RESOURCE_ID = "fbc7dffe-e020-4efc-b89a-9db5b4469da4";
    private static final String SWITCH_NAME = "172.18.16.1";
    private static final String SWITCH_PORT_NAME = "1.5";
    // ================================

    private final SwitchClient switchClient;

    public SwitchClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.switchClient = oneViewClient.switches();
    }

    private void getSwitch() {
        Switch aSwitch = this.switchClient.getById(SWITCH_RESOURCE_ID);

        System.out.println("SwitchClientSample : getSwitch : " +
                "Switch object returned to client : " + aSwitch.toJsonString());
    }

    private void getAllSwitches() {
        ResourceCollection<Switch> switches = this.switchClient.getAll();

        System.out.println("SwitchClientSample : getAllSwitches : " +
                "Switches returned to client : " + switches.toJsonString());
    }

    private void getSwitchByName() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);

        System.out.println("SwitchClientSample : getSwitchByName : " +
                "Switch object returned to client : " + aSwitch.toJsonString());
    }

    private void addSwitch() {
        Switch aSwitch = this.buildSwitch();

        TaskResourceV2 taskResource = this.switchClient.add(aSwitch, false);

        System.out.println("SwitchClientSample : addSwitch : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateSwitch() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);
        String resourceId = aSwitch.getResourceId();

        //FIXME no changes due to problems after executing an update

        TaskResourceV2 taskResource = this.switchClient.update(resourceId, aSwitch, false);

        System.out.println("SwitchClientSample : updateSwitch : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void removeSwitch() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);

        TaskResourceV2 taskResource = this.switchClient.remove(aSwitch.getResourceId(), false);

        System.out.println("SwitchClientSample : removeSwitch : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void refreshSwitch() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);

        TaskResourceV2 taskResource = this.switchClient.refresh(aSwitch.getResourceId(), false);

        System.out.println("SwitchClientSample : refreshSwitch : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void getSwitchEnvironmentalConfiguration() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);
        EnvironmentalConfiguration environmentalConfiguration = this.switchClient.getEnvironmentalConfiguration(
                aSwitch.getResourceId());

        System.out.println("SwitchClientSample : getSwitchEnvironmentalConfiguration : " +
                "EnvironmentalConfiguration object returned to client  : " + environmentalConfiguration);
    }

    private void getSwitchStatistics() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);
        SwitchStatistics switchStatistics = this.switchClient.getStatistics(aSwitch.getResourceId());

        System.out.println("SwitchClientSample : getSwitchStatistics : " +
                "SwitchStatistics object returned to client  : " + switchStatistics);
    }

    private void getSwitchPortStatistics() {
        Switch aSwitch = this.switchClient.getByName(SWITCH_NAME).get(0);
        SwitchPortStatistics switchPortStatistics = this.switchClient.getPortStatistics(
                aSwitch.getResourceId(), SWITCH_PORT_NAME);

        System.out.println("SwitchClientSample : getSwitchPortStatistics : " +
                "SwitchPortStatistics object returned to client  : " + switchPortStatistics);
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
        SwitchClientSample client = new SwitchClientSample();

        client.getSwitch();
        client.getAllSwitches();
        client.getSwitchByName();

        client.getSwitchEnvironmentalConfiguration();

        /*methods available only in version 1.2*/
        client.refreshSwitch();
        client.addSwitch();
        client.updateSwitch();
        client.removeSwitch();

        /*methods available only in version 2.0*/
        client.getSwitchStatistics();
        client.getSwitchPortStatistics();
    }

}
