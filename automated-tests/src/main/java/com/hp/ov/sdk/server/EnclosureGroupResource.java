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

package com.hp.ov.sdk.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StackingMode;
import com.hp.ov.sdk.dto.servers.IpAddressingMode;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.dto.servers.enclosuregroup.InterconnectBayMapping;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;

public class EnclosureGroupResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static EnclosureGroupResource instance;

    private EnclosureGroupClient client;

    private String ligUri;

    private EnclosureGroupResource() {
        category.put("V_300", "EnclosureGroupV300");
        category.put("V_200", "EnclosureGroupV200");
        client = oneViewClient.enclosureGroup();
    }

    public static EnclosureGroupResource getInstance() {
        if (instance == null) {
            instance = new EnclosureGroupResource();
        }
        return instance;
    }

    public void setLigUri(String ligUri) {
        this.ligUri = ligUri;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    public String getUri(String name) {
        EnclosureGroup enclosureGroup = (EnclosureGroup) getResource(client.getByName(name));
        return enclosureGroup == null ? "" : enclosureGroup.getUri();
    }

    public EnclosureGroup getByName(String name) {
        ResourceCollection<EnclosureGroup> collection = client.getByName(name);
        return !collection.getMembers().isEmpty() ? collection.getMembers().get(0) : null;
    }

    @Override
    public String findByName(String name) {
        EnclosureGroup enclosureGroup = (EnclosureGroup) getResource(client.getByName(name));
        return enclosureGroup == null ? "" : enclosureGroup.getResourceId();
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

    public void createSynergy() {
        client.create(builderSynergy());
    }

    @Override
    public String update(String id) {
        EnclosureGroup enclosureGroup = client.getById(id);
        enclosureGroup.setName(resourceProperties.get("name"));
        return objToString(client.update(id, enclosureGroup));
    }

    @Override
    public String remove(String id) {
        return objToString(client.delete(id));
    }

    public String getConfigurationScript(String id) {
        String configurationScript = client.getConfigurationScript(id);
        return configurationScript != null ? configurationScript : "";
    }

    public void updateConfigurationScript(String resourceID, String configurationScript) {
        client.updateConfigurationScript(resourceID, configurationScript);
    }

    public EnclosureGroup builder() {
        EnclosureGroup enclosure = new EnclosureGroup();
        enclosure.setName(resourceProperties.get("name"));
        enclosure.setType(getCategory());
        enclosure.setStackingMode(StackingMode.valueOf(resourceProperties.get("stackingMode")));
        enclosure.setInterconnectBayMappings(builderInterconnectBay());
        enclosure.setInterconnectBayMappingCount(enclosure.getInterconnectBayMappings().size());
        return enclosure;
    }

    private List<InterconnectBayMapping> builderInterconnectBay() {
        List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();
        String logicalInterconnectGroupUri = ligUri;
        List<Integer> interconnectEntries = Arrays.asList(Integer.valueOf(1), Integer.valueOf(2));
        for (int i = 0; i < 8; i++) {
            InterconnectBayMapping interconnectBayMapping = new InterconnectBayMapping();
            int interconnectBay = i + 1;
            interconnectBayMapping.setInterconnectBay(interconnectBay);

            if (interconnectEntries.contains(Integer.valueOf(interconnectBay))) {
                interconnectBayMapping.setLogicalInterconnectGroupUri(logicalInterconnectGroupUri);
            }
            interconnectBayMappings.add(interconnectBayMapping);
        }
        return interconnectBayMappings;
    }

    public EnclosureGroup builderSynergy() {
        EnclosureGroup enclosure = new EnclosureGroup();
        enclosure.setName(resourceProperties.get("name"));
        enclosure.setType(getCategory());
        enclosure.setStackingMode(StackingMode.valueOf(resourceProperties.get("stackingMode")));
        enclosure.setEnclosureCount(Integer.parseInt(resourceProperties.get("enclosureCount")));
        enclosure.setIpAddressingMode(IpAddressingMode.valueOf(resourceProperties.get("ipAddressingMode")));
        enclosure.setInterconnectBayMappings(builderInterconnectBaySynergy());
        enclosure.setInterconnectBayMappingCount(enclosure.getInterconnectBayMappings().size());
        return enclosure;
    }

    private List<InterconnectBayMapping> builderInterconnectBaySynergy() {

        int interconnectBayMappingCount = 6;

        List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();
        String logicalInterconnectGroupUri = ligUri;
        List<Integer> interconnectEntries = Arrays.asList(Integer.valueOf(resourceProperties.
                get("entryBayOne")), Integer.valueOf(Integer.valueOf(resourceProperties.get("entryBayTwo"))));
        for (int i = 0; i < interconnectBayMappingCount; i++) {
            InterconnectBayMapping interconnectBayMapping = new InterconnectBayMapping();
            int interconnectBay = i + 1;

            interconnectBayMapping.setInterconnectBay(interconnectBay);

            if (interconnectEntries.contains(Integer.valueOf(interconnectBay))) {
                interconnectBayMapping.setLogicalInterconnectGroupUri(logicalInterconnectGroupUri);
            }
            interconnectBayMappings.add(interconnectBayMapping);
        }
        return interconnectBayMappings;
    }
}
