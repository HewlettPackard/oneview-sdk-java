/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client.servers;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StackingMode;
import com.hp.ov.sdk.dto.generated.InterconnectBayMapping;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClientSample;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;

/*
 * EnclosureGroupClientSample is a sample program enables/consume to set a common configuration across the enclosure
 * resources of HPE OneView. It invokes APIs of EnclosureGroupClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on enclosure group resource
 */
public class EnclosureGroupClientSample {

    private final EnclosureGroupClient enclosureGroupClient;
    private final LogicalInterconnectGroupClient interconnectGroupClient;

    // test values - user input
    // ================================
    private static final String ENCLOSURE_GROUP_RESOURCE_ID = "1ac77207-e5e0-432f-a6bb-3d6d7b7b247f";
    private static final String ENCLOSURE_GROUP_NAME = "Enclosure_Sample";
    private static final String ENCLOSURE_GROUP_NAME_UPDATED = ENCLOSURE_GROUP_NAME + "_Updated";
    private static final String ENCLOSURE_SCRIPT_DATA = "name=Enclosure_test";
    // ================================

    public EnclosureGroupClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.interconnectGroupClient = oneViewClient.logicalInterconnectGroup();
        this.enclosureGroupClient = oneViewClient.enclosureGroup();
    }

    private void getEnclosureGroup() {
        EnclosureGroup enclosureGroup = enclosureGroupClient.getById(ENCLOSURE_GROUP_RESOURCE_ID);

        System.out.println("EnclosureGroupClient : getEnclosureGroup : " + "EnclosureGroup object returned to client : "
                + enclosureGroup.toJsonString());
    }

    private void getAllEnclosureGroups() {
        ResourceCollection<EnclosureGroup> enclosureGroups = enclosureGroupClient.getAll();

        System.out.println("EnclosureGroupClient : getAllEnclosureGroups : "
                + "EnclosureGroups returned to client (count) : " + enclosureGroups.toJsonString());
    }

    private void getEnclosureGroupByName() {
        EnclosureGroup enclosureGroup = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0);

        System.out.println("EnclosureGroupClient : getEnclosureGroupByName : "
                + "EnclosureGroup object returned to client : " + enclosureGroup.toJsonString());
    }

    private void createEnclosureGroup() {
        EnclosureGroup enclosureGroup = this.buildEnclosureGroup();

        EnclosureGroup created = this.enclosureGroupClient.create(enclosureGroup);

        System.out.println("EnclosureGroupClient : createEnclosureGroup : "
                + "EnclosureGroup object returned to client : " + created.toJsonString());
    }

    private void updateEnclosureGroup() {
        EnclosureGroup enclosureGroup = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0);

        enclosureGroup.setName(ENCLOSURE_GROUP_NAME_UPDATED);

        EnclosureGroup updated = this.enclosureGroupClient.update(enclosureGroup.getResourceId(), enclosureGroup);

        System.out.println("EnclosureGroupClient : updateEnclosureGroup : "
                + "EnclosureGroup object returned to client : " + updated.toJsonString());
    }

    private void deleteEnclosureGroup() {
        EnclosureGroup enclosureGroup = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME_UPDATED).get(0);

        String response = this.enclosureGroupClient.delete(enclosureGroup.getResourceId());

        System.out.println(
                "EnclosureGroupClient : deleteEnclosureGroup : " + "Response returned to client : " + response);
    }

    private void getConfigurationScript() {
        EnclosureGroup enclosureGroup = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0);

        String response = this.enclosureGroupClient.getConfigurationScript(enclosureGroup.getResourceId());

        System.out.println("EnclosureGroupClient : getConfigurationScript : "
                + "Configuration script returned to client : " + response);
    }

    private void updateConfigurationScript() {
        EnclosureGroup enclosureGroup = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0);

        String response = this.enclosureGroupClient.updateConfigurationScript(enclosureGroup.getResourceId(),
                ENCLOSURE_SCRIPT_DATA);

        System.out.println("EnclosureGroupClient : updateConfigurationScript : "
                + "Configuration script returned to client : " + response);
    }

    private EnclosureGroup buildEnclosureGroup() {
        EnclosureGroup dto = new EnclosureGroup();

        dto.setType(ResourceCategory.RC_ENCLOSURE_GROUP); // OneView 1.2
        dto.setType(ResourceCategory.RC_ENCLOSURE_GROUP_V200); // OneView 2.0
        dto.setType(ResourceCategory.RC_ENCLOSURE_GROUP_V300); // OneView 3.0
        dto.setName(ENCLOSURE_GROUP_NAME);
        dto.setStackingMode(StackingMode.Enclosure);

        List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<>();
        LogicalInterconnectGroup lig = this.interconnectGroupClient.getByName(LogicalInterconnectGroupClientSample.resourceName).get(0);

        for (int i = 0; i < 8; i++) {
            InterconnectBayMapping interconnectBayMapping = new InterconnectBayMapping();
            int interconnectBay = i + 1;

            interconnectBayMapping.setInterconnectBay(interconnectBay);

            if (LogicalInterconnectGroupClientSample.interconnectEntries.contains(Integer.valueOf(interconnectBay))) {
                interconnectBayMapping.setLogicalInterconnectGroupUri(lig.getUri());
            }
            interconnectBayMappings.add(interconnectBayMapping);
        }
        dto.setInterconnectBayMappings(interconnectBayMappings);
        dto.setInterconnectBayMappingCount(dto.getInterconnectBayMappings().size());

        dto.setEnclosureCount(1);

        return dto;
    }

    public static void main(final String[] args) throws Exception {
        EnclosureGroupClientSample sample = new EnclosureGroupClientSample();

        sample.getEnclosureGroup();
        sample.createEnclosureGroup();
        sample.getAllEnclosureGroups();
        sample.getEnclosureGroupByName();
        sample.getConfigurationScript();
        sample.updateConfigurationScript();
        sample.updateEnclosureGroup();
        sample.deleteEnclosureGroup();
    }
}
