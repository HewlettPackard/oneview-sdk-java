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
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.LogicalLocationEntry;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectType;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.SwitchMapEntryTemplate;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.SwitchMapTemplate;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchGroupClient;
import com.hp.ov.sdk.rest.client.networking.SwitchTypeClient;

public class LogicalSwitchGroupResource extends BasicResource
        implements CreateResource, RemoveResource, UpdateResource {

    private static LogicalSwitchGroupResource instance;

    private LogicalSwitchGroupClient client;
    private SwitchTypeClient switchTypeClient;

    private LogicalSwitchGroupResource() {
        category.put("V_300", "logical-switch-groupV300");
        category.put("V_200", "logical-switch-group");
        client = oneViewClient.logicalSwitchGroup();
        switchTypeClient = oneViewClient.switchType();
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new LogicalSwitchGroupResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        LogicalSwitchGroup lsg = (LogicalSwitchGroup) getResource(client.getByName(name));
        return lsg == null ? "" : lsg.getResourceId();
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
        return taskToString(client.update(id, builderUpdate(client.getById(id))));
    }

    @Override
    public LogicalSwitchGroup builder() {
        LogicalSwitchGroup group = new LogicalSwitchGroup();
        group.setName(resourceProperties.get("name"));
        group.setType(getCategory());
        group.setState(resourceProperties.get("state"));

        SwitchMapTemplate switchMapTemplate = new SwitchMapTemplate();

        SwitchMapEntryTemplate switchMapEntryTemplate = builderSwitchTemplate();
        switchMapTemplate.setSwitchMapEntryTemplates(Lists.newArrayList(switchMapEntryTemplate));

        group.setSwitchMapTemplate(switchMapTemplate);

        return group;
    }

    private SwitchMapEntryTemplate builderSwitchTemplate() {
        InterconnectType type = switchTypeClient.getByName(resourceProperties.get("switchType")).get(0);

        SwitchMapEntryTemplate switchMapEntryTemplate = new SwitchMapEntryTemplate();
        LogicalLocation logicalLocation = new LogicalLocation();

        logicalLocation.setLocationEntries(
                Lists.newArrayList(new LogicalLocationEntry(Integer.valueOf(resourceProperties.get("locationEntry")),
                        null, LocationType.valueOf(resourceProperties.get("locationType")))));

        switchMapEntryTemplate.setLogicalLocation(logicalLocation);
        switchMapEntryTemplate.setPermittedSwitchTypeUri(type.getUri());

        return switchMapEntryTemplate;
    }

    public LogicalSwitchGroup builderUpdate(LogicalSwitchGroup group) {
        SwitchMapTemplate switchMapTemplate = group.getSwitchMapTemplate();

        SwitchMapEntryTemplate switchMapEntryTemplate = builderSwitchTemplate();
        switchMapTemplate.getSwitchMapEntryTemplates().add(switchMapEntryTemplate);

        return group;
    }

}
