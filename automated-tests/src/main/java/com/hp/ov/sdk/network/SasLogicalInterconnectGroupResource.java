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

package com.hp.ov.sdk.network;

import java.util.Map;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.dto.networking.EnclosureType;
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.LogicalLocationEntry;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectType;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectTypeName;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasInterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasInterconnectMapTemplate;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasLogicalInterconnectGroup;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.SasInterconnectTypeClient;
import com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectGroupClient;

public class SasLogicalInterconnectGroupResource extends BasicResource
        implements CreateResource, RemoveResource, UpdateResource {

    private static SasLogicalInterconnectGroupResource instance;

    private SasLogicalInterconnectGroupClient client;

    private SasInterconnectTypeClient typeClient;

    public SasLogicalInterconnectGroupResource() {
        category.put("V_300", "sas-logical-interconnect-group");
        category.put("V_200", "sas-logical-interconnect-group");
        client = oneViewClient.sasLogicalInterconnectGroup();
        typeClient = oneViewClient.sasInterconnectType();
    }

    public static SasLogicalInterconnectGroupResource getInstance() {
        if (instance == null) {
            instance = new SasLogicalInterconnectGroupResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        SasLogicalInterconnectGroup sasInterconnectGroup = (SasLogicalInterconnectGroup) getResource(
                client.getByName(name));
        return sasInterconnectGroup == null ? "" : sasInterconnectGroup.getResourceId();
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
    public String update(String id) {
        SasLogicalInterconnectGroup sasLogicalInterconnectGroup = client.getById(id);
        sasLogicalInterconnectGroup.setName(resourceProperties.get("name"));
        return taskToString(client.update(id, sasLogicalInterconnectGroup));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

    @Override
    public void create() {
        client.create(builder());
    }

    @Override
    public SasLogicalInterconnectGroup builder() {
        SasLogicalInterconnectGroup interconnectGroup = new SasLogicalInterconnectGroup();
        SasInterconnectType interconnectType = typeClient
                .getByName(SasInterconnectTypeName.SYNERGY_12GB_SAS_CONNECTION_MODULE).get(0);

        SasInterconnectMapTemplate interconnectMapTemplate = new SasInterconnectMapTemplate();

        LogicalLocationEntry bayOne = new LogicalLocationEntry(1, null, LocationType.Bay);
        LogicalLocationEntry bayFour = new LogicalLocationEntry(4, null, LocationType.Bay);
        LogicalLocationEntry enclOne = new LogicalLocationEntry(1, null, LocationType.Enclosure);

        LogicalLocation enclOneBayOne = new LogicalLocation();
        enclOneBayOne.setLocationEntries(Lists.newArrayList(bayOne, enclOne));

        SasInterconnectMapEntryTemplate enclOneBayOneEntry = new SasInterconnectMapEntryTemplate();
        enclOneBayOneEntry.setEnclosureIndex(1);
        enclOneBayOneEntry.setPermittedInterconnectTypeUri(interconnectType.getUri());
        enclOneBayOneEntry.setLogicalLocation(enclOneBayOne);

        LogicalLocation enclOneBayFour = new LogicalLocation();
        enclOneBayFour.setLocationEntries(Lists.newArrayList(bayFour, enclOne));

        SasInterconnectMapEntryTemplate enclOneBayFourEntry = new SasInterconnectMapEntryTemplate();
        enclOneBayFourEntry.setEnclosureIndex(1);
        enclOneBayFourEntry.setPermittedInterconnectTypeUri(interconnectType.getUri());
        enclOneBayFourEntry.setLogicalLocation(enclOneBayFour);

        interconnectMapTemplate
                .setInterconnectMapEntryTemplates(Lists.newArrayList(enclOneBayOneEntry, enclOneBayFourEntry));

        interconnectGroup.setInterconnectMapTemplate(interconnectMapTemplate);
        interconnectGroup.setEnclosureType(EnclosureType.SY12000);
        interconnectGroup.setInterconnectBaySet(1);
        interconnectGroup.setEnclosureIndexes(Lists.newArrayList(1));
        interconnectGroup.setName(resourceProperties.get("name"));
        interconnectGroup.setType(getCategory());

        return interconnectGroup;
    }

    public SasLogicalInterconnectGroup getByName(String name) {
        return (SasLogicalInterconnectGroup) getResource(client.getByName(name));
    }
    
}
