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

package com.hp.ov.sdk.rest.client.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.dto.settings.ResourceAssignment;
import com.hp.ov.sdk.dto.settings.Scope;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClientSample;

public class ScopeClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeClientSample.class);

    // These are variables to be defined by the user
    // ================================
    public static final String SCOPE_NAME = "Sample Scope";
    public static final String SCOPE_RESOURCE_ID = "b9962a0a-85e3-4268-ae85-7d4d466ba2b6";
    // ================================

    private final ScopeClient client;
    private final FcNetworkClient fcNetworkClient;

    public ScopeClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.client = oneViewClient.scope();
        this.fcNetworkClient = oneViewClient.fcNetwork();
    }

    private void createScope() {
        Scope scope = new Scope();

        scope.setName(SCOPE_NAME);
        scope.setType(Scope.TYPE);
        scope.setDescription("Sample scope description.");

        Scope createdScope = this.client.create(scope);

        LOGGER.info("Created scope object returned to client : " + createdScope.toJsonString());
    }

    private void getAllScopes() {
        ResourceCollection<Scope> scopes = client.getAll();

        LOGGER.info("Scopes returned to client (count) : " + scopes.getCount());
    }

    private void getScopeById() {
        Scope scope = client.getAll().get(0);

        scope = client.getById(scope.getResourceId());

        LOGGER.info("Scope object returned to client : " + scope.toJsonString());
    }

    private void updateScope() {
        Scope scope = this.client.getById(SCOPE_RESOURCE_ID);

        scope.setDescription("Sample description updated!");

        Scope updatedScope = this.client.update(scope.getResourceId(), scope, scope.getETag());

        LOGGER.info("Updated scope object returned to client : " + updatedScope.toJsonString());
    }

    private void deleteScope() {
        Scope scope = this.client.getById(SCOPE_RESOURCE_ID);

        TaskResource task = this.client.delete(scope.getResourceId(), scope.getETag());

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void patchScope() {
        Scope scope = this.client.getById(SCOPE_RESOURCE_ID);
        FcNetwork fcNetwork = this.fcNetworkClient.getByName(FcNetworkClientSample.FC_NETWORK_NAME_A).get(0);

        ResourceAssignment assignment = new ResourceAssignment();
        assignment.getAddedResourceUris().add(fcNetwork.getUri());

        TaskResource task = this.client.patch(scope.getResourceId(), assignment);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    public static void main(String[] args) {
        ScopeClientSample sample = new ScopeClientSample();

        sample.createScope();
        sample.getAllScopes();
        sample.getScopeById();
        sample.updateScope();
        sample.patchScope();
        sample.deleteScope();
    }

}
