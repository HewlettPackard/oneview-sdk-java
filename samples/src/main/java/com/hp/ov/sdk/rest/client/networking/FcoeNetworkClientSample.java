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

package com.hp.ov.sdk.rest.client.networking;

import static com.hp.ov.sdk.rest.client.settings.ScopeClientSample.SCOPE_NAME;
import static com.hp.ov.sdk.rest.client.settings.ScopeClient.SCOPES_URI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.fcoenetworks.FcoeNetwork;
import com.hp.ov.sdk.dto.settings.Scope;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.settings.ScopeClient;

public class FcoeNetworkClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcoeNetworkClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String FCOE_NETWORK_NAME = "FCoE-Network_SAMPLE";
    private static final String FCOE_NETWORK_NAME_UPDATED = FCOE_NETWORK_NAME + "_Updated";
    // ================================

    private final FcoeNetworkClient client;
    private final OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

    private FcoeNetworkClientSample() {
        this.client = oneViewClient.fcoeNetwork();
    }

    private void createFcoeNetwork() {
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        fcoeNetwork.setName(FCOE_NETWORK_NAME);
        fcoeNetwork.setType(ResourceCategory.RC_FCOE_NETWORK); // v200
        fcoeNetwork.setType(ResourceCategory.RC_FCOE_NETWORK_V300); // v300 or v500
        fcoeNetwork.setVlanId(Integer.valueOf(400));

        TaskResource task = this.client.create(fcoeNetwork);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getFcoeNetwork() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME).get(0);

        fcoeNetwork = client.getById(fcoeNetwork.getResourceId());

        LOGGER.info("FcoeNetwork object returned to client : " + fcoeNetwork.toJsonString());
    }

    private void getAllFcoeNetworks() {
        ResourceCollection<FcoeNetwork> fcoeNetworks = client.getAll();

        LOGGER.info("FcoeNetworks returned to client (count) : " + fcoeNetworks.getCount());
    }

    private void getFcoeNetworkByName() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME).get(0);

        LOGGER.info("FcoeNetwork object returned to client : " + fcoeNetwork.toJsonString());
    }

    private void patchFcoeNetwork() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME).get(0);

        Patch patch = new Patch();

        // FCoE network patch supports the update of scopeUris
        patch.setOp(PatchOperation.replace);
        patch.setPath("/scopeUris");
        List<String> scopeUris = fcoeNetwork.getScopeUris(); // Gets the current scope(s)
        scopeUris.add(SCOPES_URI + "/" + getScopeId(SCOPE_NAME)); // Assigns FCoE network to a new scope
        // scopeUris.remove(SCOPES_URI + "/" + getScopeId(SCOPE_NAME)); // Unassigns FCoE network from a scope
        patch.setValue(scopeUris);

        TaskResource task = this.client.patch(fcoeNetwork.getResourceId(), patch);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateFcoeNetwork() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME).get(0);

        fcoeNetwork.setName(FCOE_NETWORK_NAME_UPDATED);

        TaskResource task = this.client.update(fcoeNetwork.getResourceId(), fcoeNetwork);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void deleteFcoeNetwork() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME_UPDATED).get(0);

        TaskResource task = this.client.delete(fcoeNetwork.getResourceId());

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private String getScopeId(String scopeName) {
        ScopeClient scopeClient = oneViewClient.scope();

        Scope scope = scopeClient.getByName(scopeName).get(0);

        return scope.getResourceId();
    }

    public static void main(String[] args) {
        FcoeNetworkClientSample sample = new FcoeNetworkClientSample();

        sample.createFcoeNetwork();
        sample.getFcoeNetwork();
        sample.getFcoeNetworkByName();
        sample.getAllFcoeNetworks();
        sample.patchFcoeNetwork();
        sample.updateFcoeNetwork();
        sample.deleteFcoeNetwork();
    }
}
