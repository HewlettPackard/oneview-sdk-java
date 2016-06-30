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

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.fabric.Fabric;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class FabricClientSample {

    private final FabricClient fabricClient;

    // test values - user input
    // ================================
    private static final String FABRIC_NAME = "DefaultFabric";
    private static final String FABRIC_RESOURCE_ID = "77480fa6-d1ed-46ac-8117-858dad02b758";
    // ================================

    public FabricClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.fabricClient = oneViewClient.fabric();
    }

    private void getFabric() {
        Fabric fabric = this.fabricClient.getById(FABRIC_RESOURCE_ID);

        System.out.println("FabricClientSample : getFabric : " +
                "Fabric object returned to client : " + fabric.toJsonString());
    }

    private void getAllFabrics() {
        ResourceCollection<Fabric> fabrics = this.fabricClient.getAll();

        System.out.println("FabricClientSample : getAllFabrics : " +
                "Fabrics returned to client : " + fabrics.toJsonString());
    }

    private void getFabricByName() {
        Fabric fabric = this.fabricClient.getByName(FABRIC_NAME).get(0);

        System.out.println("FabricClientSample : getFabricByName : " +
                "Fabric object returned to client : " + fabric.toJsonString());
    }

    public static void main(String[] args) throws Exception {
        FabricClientSample client = new FabricClientSample();

        client.getFabric();
        client.getAllFabrics();
        client.getFabricByName();
    }

}
