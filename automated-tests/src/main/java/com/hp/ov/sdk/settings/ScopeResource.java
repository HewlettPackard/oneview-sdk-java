/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.settings;

import java.util.Map;

import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.dto.settings.ResourceAssignment;
import com.hp.ov.sdk.dto.settings.Scope;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.PatchResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;
import com.hp.ov.sdk.rest.client.settings.ScopeClient;

public class ScopeResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource, PatchResource {

    private static ScopeResource instance;
    private ScopeClient client;
    private final FcNetworkClient fcNetworkClient;

    public ScopeResource(){
        client = oneViewClient.scope();
        this.fcNetworkClient = oneViewClient.fcNetwork();
    }

    public static ScopeResource getInstance() {
        if (instance == null) {
            instance = new ScopeResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id){
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        Scope scope = (Scope) getResource(client.getByName(name));
        return scope == null ? "" : scope.getResourceId();
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
    public String patch(String id){
        return taskToString(client.patch(id, builderPatch(client.getById(id))));
    }

    @Override
    public String update(String id){
        return objToString(client.update(id, builderUpdate(client.getById(id))));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

    public Scope builder() {
        Scope scope = new Scope();
        scope.setType(Scope.TYPE);
        scope.setName(resourceProperties.get("name"));
        scope.setDescription(resourceProperties.get("description"));
        return scope;
    }

    private Scope builderUpdate(Scope scope) {
        scope.setName(resourceProperties.get("name"));
        scope.setDescription(resourceProperties.get("description"));
        return scope;
    }

    private ResourceAssignment builderPatch(Scope scope) {
        FcNetwork resourceType = this.fcNetworkClient.getByName(resourceProperties.get("resourceName")).get(0);
        ResourceAssignment assignment = new ResourceAssignment();
        assignment.getAddedResourceUris().add(resourceType.getUri());
        this.client.patch(scope.getResourceId(), assignment);
        return assignment;
    }

}
