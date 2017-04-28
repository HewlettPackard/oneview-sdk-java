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

import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.ProfileAffinity;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofiletemplate.ServerProfileTemplate;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.rest.client.server.ServerHardwareTypeClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileTemplateClient;

public class ServerProfileTemplateResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static ServerProfileTemplateResource instance;

    private ServerProfileTemplateClient client;

    private ServerHardwareTypeClient serverHardwareTypeClient;

    private String enclosureUri;

    private ServerProfileTemplateResource() {
        category.put("V_300", "ServerProfileTemplateV2");
        category.put("V_200", "ServerProfileTemplateV1");
        this.client = oneViewClient.serverProfileTemplate();
        this.serverHardwareTypeClient = oneViewClient.serverHardwareType();
    }

    public static ServerProfileTemplateResource getInstance() {
        if (instance == null) {
            instance = new ServerProfileTemplateResource();
        }
        return instance;
    }

    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        try {
            ServerProfileTemplate spt = getByName(name);
            if (spt != null) {
                String id = spt.getUri();
                id = id.substring(id.lastIndexOf("/") + 1);
                return id;
            } else {
                return "";
            }
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    public ServerProfileTemplate getByName(String name) {
        ServerProfileTemplate serverProfileTemplate = (ServerProfileTemplate) getResource(client.getByName(name));
        return serverProfileTemplate;
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
        return objToString(client.delete(id));
    }

    @Override
    public String update(String id) {
        ServerProfileTemplate spt = client.getById(id);
        spt.setName(resourceProperties.get("name"));
        return taskToString(client.update(id, spt));
    }

    private String getHardwareTypeUri() {
        ResourceCollection<ServerHardwareType> collection = serverHardwareTypeClient.getAll();
        return collection.getMembers().isEmpty() ? "" : collection.getMembers().get(0).getUri();
    }

    public String getServerProfileFromTemplate(String resourceID) {
        ServerProfile sp = client.getNewServerProfile(resourceID);
        return sp != null ? sp.toString() : "not-found";
    }

    @Override
    public ServerProfileTemplate builder() {
        ServerProfileTemplate stp = new ServerProfileTemplate();
        stp.setName(resourceProperties.get("name"));
        stp.setType(getCategory());
        stp.setServerHardwareTypeUri(getHardwareTypeUri());
        stp.setEnclosureGroupUri(enclosureUri);
        stp.setSerialNumberType(AssignmentType.valueOf(resourceProperties.get("serialNumberType")));
        stp.setMacType(AssignmentType.valueOf(resourceProperties.get("macType")));
        stp.setWwnType(AssignmentType.valueOf(resourceProperties.get("wwnType")));
        stp.setAffinity(ProfileAffinity.valueOf(resourceProperties.get("affinity")));
        stp.setHideUnusedFlexNics(true);
        return stp;
    }

}
