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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hp.ov.sdk.dto.networking.EnclosureType;
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.LogicalLocationEntry;
import com.hp.ov.sdk.dto.networking.NetworkType;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectTypeName;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.InterconnectMapTemplate;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup.RedundancyType;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.UplinkSetGroup;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.util.ResourceDtoUtils;

public class LogicalInterconnectGroupResource extends BasicResource
        implements CreateResource, RemoveResource, UpdateResource {

    private static LogicalInterconnectGroupResource instance;

    private LogicalInterconnectGroupClient client;

    private ResourceDtoUtils dtoUtils;

    private HashMap<Integer, InterconnectTypeName> interconnectionValues;

    private List<UplinkSetGroup> uplinkList;

    private LogicalInterconnectGroupResource() {
        category.put("V_300", "logical-interconnect-groupV300");
        category.put("V_200", "logical-interconnect-groupV3");
        client = oneViewClient.logicalInterconnectGroup();
        this.uplinkList = new ArrayList<UplinkSetGroup>();
        this.dtoUtils = new ResourceDtoUtils(oneViewClient);
    }

    public static LogicalInterconnectGroupResource getInstance() {
        if (instance == null) {
            instance = new LogicalInterconnectGroupResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    public LogicalInterconnectGroup getByName(String name) {
        return (LogicalInterconnectGroup) getResource(client.getByName(name));
    }

    public void setInterconnectionValues(List<Map<String, String>> values) {
        this.interconnectionValues = new HashMap<Integer, InterconnectTypeName>();
        for (Map<String, String> map : values) {
            InterconnectTypeName interType = getInterconnectType(map.get("type"));
            this.interconnectionValues.put(Integer.parseInt(map.get("entries")), interType);
        }
    }

    public void setUplinkValues(String ligName, List<Map<String, String>> values) {
        for (Map<String, String> map : values) {
            String uplinkSetName = map.get("name");
            NetworkType uplinkSetType = NetworkType.valueOf(map.get("type"));
            List<String> networkNames = Arrays.asList(map.get("networks").replaceAll(" ", "").split(","));
            HashMap<Integer, List<String>> bayPortMap = new HashMap<Integer, List<String>>();
            int i = 1;
            for (String bay : Arrays.asList(map.get("bayPort").replaceAll(" ", "").split(","))) {
                bayPortMap.put(i++, Arrays.asList(bay));
            }

            this.uplinkList
                    .add(dtoUtils.buildUplinkSetDto(ligName, uplinkSetName, uplinkSetType, bayPortMap, networkNames));
        }

    }

    @Override
    public String findByName(String name) {
        LogicalInterconnectGroup lig = (LogicalInterconnectGroup) getResource(client.getByName(name));
        return lig == null ? "" : lig.getResourceId();
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
        LogicalInterconnectGroup lig = client.getById(id);
        String statusTask = "";
        if (lig != null) {
            lig.setUplinkSets(uplinkList);
            statusTask = taskToString(client.update(id, lig));
        }
        return statusTask;
    }

    @Override
    public String remove(String id) {
        return objToString(client.delete(id));
    }

    public String getDefaultInterconnectSettings() {
        try {
            return client.getDefaultInterconnectSettings().getEthernetSettings().getResourceId();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    public String getInterconnectSettings(String resourceID, String settingID) {
        try {
            return client.getInterconnectSettings(resourceID).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    private InterconnectTypeName getInterconnectType(String strType) {
        InterconnectTypeName interType = null;
        for (InterconnectTypeName type : InterconnectTypeName.values()) {
            if (type.getValue().equals(strType)) {
                interType = type;
                break;
            }
        }
        return interType;
    }

    @Override
    public LogicalInterconnectGroup builder() {
        LogicalInterconnectGroup lig = dtoUtils.buildLogicalInterconnectGroupDto(resourceProperties.get("name"),
                interconnectionValues);
        lig.setType(getCategory());
        lig.setState(resourceProperties.get("state"));
        lig.setEnclosureIndexes(Arrays.asList(1));
        for (InterconnectMapEntryTemplate entry : lig.getInterconnectMapTemplate().getInterconnectMapEntryTemplates()) {
            entry.setEnclosureIndex(1);
        }
        return lig;
    }

    public LogicalInterconnectGroup builderSynergy() {
        LogicalInterconnectGroup lig = buildLogicalInterconnectGroupDtoSynergy(interconnectionValues);

        lig.setName(resourceProperties.get("name"));
        lig.setState(resourceProperties.get("state"));
        lig.setType(getCategory());
        lig.setEnclosureType(EnclosureType.valueOf(resourceProperties.get("enclosureType")));
        lig.setInterconnectBaySet(Integer.parseInt(resourceProperties.get("baySet")));
        lig.setRedundancyType(RedundancyType.valueOf(resourceProperties.get("redundancyType")));
        lig.setEnclosureIndexes(Arrays.asList(1));

        Iterator<Entry<Integer, InterconnectTypeName>> interator = interconnectionValues.entrySet().iterator();
        for (InterconnectMapEntryTemplate entry : lig.getInterconnectMapTemplate().getInterconnectMapEntryTemplates()) {
            String interconnectType = interator.next().getValue().toString();
            String interconnectTypeUri = oneViewClient.interconnectType().getByName(interconnectType).get(0).getUri();
            entry.setEnclosureIndex(1);
            entry.setPermittedInterconnectTypeUri(interconnectTypeUri);
        }
        return lig;
    }

    public LogicalInterconnectGroup buildLogicalInterconnectGroupDtoSynergy(
            HashMap<Integer, InterconnectTypeName> bayPermittedInterconnectMaps) {

        LogicalInterconnectGroup dtoSynergy = new LogicalInterconnectGroup();
        List<InterconnectMapEntryTemplate> interconnectMapEntryTemplatesDto = new ArrayList<InterconnectMapEntryTemplate>();
        Iterator<Entry<Integer, InterconnectTypeName>> interator = interconnectionValues.entrySet().iterator();
        int i, j;
        for (i = 0; i < 2; i++) {
            final InterconnectMapEntryTemplate interconnectMapEntryTemplateDto = new InterconnectMapEntryTemplate();
            interconnectMapEntryTemplateDto.setLogicalDownlinkUri(null);
            final List<LogicalLocationEntry> locationEntriesDto = new ArrayList<LogicalLocationEntry>();
            for (j = 0; j < 2; j++) {
                final LogicalLocationEntry locationEntryDto = new LogicalLocationEntry();
                if (j == 0) {
                    locationEntryDto.setRelativeValue(interator.next().getKey());
                    locationEntryDto.setType(LocationType.Bay);
                } else {
                    locationEntryDto.setRelativeValue(j);
                    locationEntryDto.setType(LocationType.Enclosure);
                }
                locationEntriesDto.add(locationEntryDto);

            }
            LogicalLocation logicalLocationDto = new LogicalLocation();

            logicalLocationDto.setLocationEntries(locationEntriesDto);
            interconnectMapEntryTemplateDto.setLogicalLocation(logicalLocationDto);

            if (bayPermittedInterconnectMaps.get((i + 1)) != null) {
                InterconnectTypeName bayPermittedInterconnect = bayPermittedInterconnectMaps.get(i + 1);
                String interconnectTypeUri = oneViewClient.interconnectType().getByName(bayPermittedInterconnect).get(0)
                        .getUri();

                interconnectMapEntryTemplateDto.setPermittedInterconnectTypeUri(interconnectTypeUri);
            } else {
                interconnectMapEntryTemplateDto.setPermittedInterconnectTypeUri(null);
            }
            interconnectMapEntryTemplatesDto.add(interconnectMapEntryTemplateDto);
        }

        final InterconnectMapTemplate interconnectMapTemplateDto = new InterconnectMapTemplate();
        interconnectMapTemplateDto.setInterconnectMapEntryTemplates(interconnectMapEntryTemplatesDto);
        dtoSynergy.setInterconnectMapTemplate(interconnectMapTemplateDto);

        dtoSynergy.setUri(null);

        return dtoSynergy;
    }
    
}
