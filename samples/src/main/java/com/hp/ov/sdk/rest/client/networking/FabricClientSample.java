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
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.fabric.Fabric;
import com.hp.ov.sdk.dto.networking.fabric.VlanPool;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class FabricClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(FabricClientSample.class);

    private final FabricClient fabricClient;

    // test values - user input
    // ================================
    private static final String FABRIC_NAME = "DefaultFabric";
    private static final String FABRIC_RESOURCE_ID = "eeb22284-13b3-45e4-a14b-0e32e7fbe03e";
    // ================================

    public FabricClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.fabricClient = oneViewClient.fabric();
    }

    private void getFabric() {
        Fabric fabric = this.fabricClient.getById(FABRIC_RESOURCE_ID);

        LOGGER.info("Fabric object returned to client : " + fabric.toJsonString());
    }

    private void getAllFabrics() {
        ResourceCollection<Fabric> fabrics = this.fabricClient.getAll();

        LOGGER.info("Fabrics returned to client : " + fabrics.toJsonString());
    }

    private void getFabricByName() {
        Fabric fabric = this.fabricClient.getByName(FABRIC_NAME).get(0);

        LOGGER.info("Fabric object returned to client : " + fabric.toJsonString());
    }

    private void getReservedVlanRange() {
        VlanPool vlanRange = this.fabricClient.getReservedVlanRange(FABRIC_RESOURCE_ID);

        LOGGER.info("VlanPool object returned to client : " + vlanRange.toJsonString());
    }

    private void updateReservedVlanRange() {
        VlanPool vlanRange = this.fabricClient.getReservedVlanRange(FABRIC_RESOURCE_ID);

        vlanRange.setStart(vlanRange.getStart() - 1);

        TaskResource task = this.fabricClient.updateReservedVlanRange(FABRIC_RESOURCE_ID, vlanRange );

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    public static void main(String[] args) {
        FabricClientSample client = new FabricClientSample();

        client.getAllFabrics();
        client.getFabric();
        client.getFabricByName();

        client.getReservedVlanRange();
        client.updateReservedVlanRange();
    }

}
