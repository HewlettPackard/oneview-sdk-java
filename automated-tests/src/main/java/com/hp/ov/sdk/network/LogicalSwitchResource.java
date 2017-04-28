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

package com.hp.ov.sdk.network;

import java.util.Map;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.dto.networking.ConnectionProperty;
import com.hp.ov.sdk.dto.networking.logicalswitches.AddLogicalSwitch;
import com.hp.ov.sdk.dto.networking.logicalswitches.LogicalSwitch;
import com.hp.ov.sdk.dto.networking.logicalswitches.ManagementLevel;
import com.hp.ov.sdk.dto.networking.logicalswitches.SnmpV1Configuration;
import com.hp.ov.sdk.dto.networking.logicalswitches.SnmpVersion;
import com.hp.ov.sdk.dto.networking.logicalswitches.SwitchCredentialConfiguration;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueFormat;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueType;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.networking.switches.SwitchManagementConnection;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchClient;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchGroupClient;

public class LogicalSwitchResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static LogicalSwitchResource instance;

    private LogicalSwitchClient client;
    private LogicalSwitchGroupClient groupClient;

    private LogicalSwitchResource() {
        category.put("V_300", "logical-switchV300");
        category.put("V_200", "logical-switch");
        client = oneViewClient.logicalSwitch();
        groupClient = oneViewClient.logicalSwitchGroup();
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new LogicalSwitchResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        LogicalSwitch ls = (LogicalSwitch) getResource(client.getByName(name));
        return ls == null ? "" : ls.getResourceId();
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
        client.create(builder());
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

    @Override
    public String update(String id) {
        LogicalSwitch logicalSwitch = client.getById(id);
        return taskToString(client.update(id, buildCredentials(logicalSwitch)));
    }

    public String refresh(String id) {
        return taskToString(client.refresh(id));
    }

    @Override
    public AddLogicalSwitch builder() {
        LogicalSwitchGroup logicalSwitchGroup = this.groupClient.getByName(resourceProperties.get("switchGroup"))
                .get(0);

        SwitchCredentialConfiguration switchCredentialConfiguration = new SwitchCredentialConfiguration();
        SnmpV1Configuration snmpV1Configuration = new SnmpV1Configuration();

        snmpV1Configuration.setCommunityString(resourceProperties.get("communityString"));

        switchCredentialConfiguration.setSnmpPort(resourceProperties.get("snmpPort"));
        switchCredentialConfiguration.setLogicalSwitchManagementHost(resourceProperties.get("managementHost"));
        switchCredentialConfiguration.setSnmpVersion(SnmpVersion.valueOf(resourceProperties.get("snmpVersion")));
        switchCredentialConfiguration.setSnmpV1Configuration(snmpV1Configuration);

        LogicalSwitch logicalSwitch = new LogicalSwitch();

        logicalSwitch.setName(resourceProperties.get("name"));
        logicalSwitch.setType(getCategory());
        logicalSwitch.setManagementLevel(ManagementLevel.valueOf(resourceProperties.get("managementLevel")));

        logicalSwitch.setSwitchCredentialConfiguration(Lists.newArrayList(switchCredentialConfiguration));
        logicalSwitch.setLogicalSwitchGroupUri("/rest/logical-switch-groups/" + logicalSwitchGroup.getResourceId());

        AddLogicalSwitch addLogicalSwitch = buildCredentials(logicalSwitch);

        return addLogicalSwitch;
    }

    private AddLogicalSwitch buildCredentials(LogicalSwitch logicalSwitch) {
        AddLogicalSwitch addLogicalSwitch = new AddLogicalSwitch();
        SwitchManagementConnection switchManagementConnection = new SwitchManagementConnection();

        ConnectionProperty user = new ConnectionProperty("SshBasicAuthCredentialUser", resourceProperties.get("user"),
                ValueFormat.Unknown, ValueType.String);
        ConnectionProperty password = new ConnectionProperty("SshBasicAuthCredentialPassword",
                resourceProperties.get("password"), ValueFormat.SecuritySensitive, ValueType.String);

        switchManagementConnection.setConnectionProperties(Lists.newArrayList(user, password));

        addLogicalSwitch.setLogicalSwitchCredentials(Lists.newArrayList(switchManagementConnection));
        addLogicalSwitch.setLogicalSwitch(logicalSwitch);

        return addLogicalSwitch;
    }

}
