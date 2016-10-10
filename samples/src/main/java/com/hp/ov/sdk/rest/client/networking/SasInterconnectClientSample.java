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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.sasinterconnects.SasInterConnectRefreshRequest;
import com.hp.ov.sdk.dto.networking.sasinterconnects.SasInterconnect;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class SasInterconnectClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasInterconnectClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String SAS_INTERCONNECT_RESOURCE_ID = "2M220100SL";
    private static final String SAS_INTERCONNECT_NAME = "0000A66101, interconnect 1";
    // ================================

    private final SasInterconnectClient client;

    private SasInterconnectClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.sasInterconnects();
    }

    private void getSasInterconnect() {
        SasInterconnect sasInterconnect = client.getById(SAS_INTERCONNECT_RESOURCE_ID);

        LOGGER.info("SAS interconnect returned to client: {}", sasInterconnect.toJsonString());
    }

    private void getAllSasInterconnects() {
        ResourceCollection<SasInterconnect> sasInterconnects = client.getAll();

        LOGGER.info("SAS interconnects returned to client: {}", sasInterconnects.toJsonString());
    }

    private void getSasInterconnectByName() {
        SasInterconnect sasInterconnect = client.getByName(SAS_INTERCONNECT_NAME).get(0);

        LOGGER.info("SAS interconnect returned to client: {}", sasInterconnect.toJsonString());
    }

    private void patchSasInterconnect() {
        SasInterconnect interconnect = this.client.getByName(SAS_INTERCONNECT_NAME).get(0);
        Patch patch = new Patch();

        // Interconnect patch supports the update of 'powerState', 'uidState', 'deviceResetState'
        // 'softResetState' and 'hardResetState'
        patch.setOp(PatchOperation.replace);
        patch.setPath("/powerState");
        patch.setValue("On");

        TaskResource task = this.client.patch(interconnect.getResourceId(), patch);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void refreshSasInterconnect() {
        SasInterconnect interconnect = this.client.getByName(SAS_INTERCONNECT_NAME).get(0);

        SasInterConnectRefreshRequest requestBody = new SasInterConnectRefreshRequest();
        requestBody.setRefreshState(RefreshState.RefreshPending);

        TaskResource task = this.client.refreshState(interconnect.getResourceId(), requestBody);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    public static void main(String[] args) {
        SasInterconnectClientSample sample = new SasInterconnectClientSample();

        sample.getAllSasInterconnects();
        sample.getSasInterconnect();
        sample.getSasInterconnectByName();
        sample.patchSasInterconnect();
        sample.refreshSasInterconnect();
    }

}
