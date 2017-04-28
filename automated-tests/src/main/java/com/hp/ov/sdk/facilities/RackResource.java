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

package com.hp.ov.sdk.facilities;

import java.util.Map;

import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.facilities.RackClient;

public class RackResource extends BasicResource implements CreateResource, UpdateResource, RemoveResource {

    private static RackResource instance;

    private RackClient client;

    public RackResource() {
        client = oneViewClient.rack();
    }

    public static RackResource getInstance() {
        if (instance == null) {
            instance = new RackResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        Rack rack = (Rack) getResource(client.getByName(name));
        return rack == null ? "" : rack.getResourceId();
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
        client.add(builder());
    }

    @Override
    public Rack builder() {
        Rack rack = new Rack();
        rack.setName(resourceProperties.get("name"));
        return rack;
    }

    @Override
    public String update(String id) {
        Rack rack = client.getById(id);
        rack.setThermalLimit(Integer.valueOf(resourceProperties.get("thermalLimit")));
        return objToString(client.update(id, rack));
    }

    public String getDeviceTopology(String id) {
        return objToString(client.getDeviceTopology(id));
    }
    
    @Override
    public String remove(String id) {
        return client.remove(id);
    }
    
    public String removeByFilter() {
        String filter = "'name' = '" + resourceProperties.get("name") + "'";
        return taskToString(client.removeByFilter(filter));
    }

}
