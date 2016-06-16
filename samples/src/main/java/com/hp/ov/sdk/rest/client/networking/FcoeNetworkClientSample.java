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
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.FcoeNetwork;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class FcoeNetworkClientSample {

    // These are variables to be defined by the user
    // ================================
    private static final String FCOE_NETWORK_RESOURCE_ID = "060af776-fb29-4d6c-a1f1-7e513ab4e677";
    private static final String FCOE_NETWORK_NAME = "FCoE-Network_SAMPLE";
    private static final String FCOE_NETWORK_NAME_UPDATED = FCOE_NETWORK_NAME + "_Updated";
    // ================================

    private final FcoeNetworkClient client;

    private FcoeNetworkClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.fcoeNetwork();
    }

    private void createFcoeNetwork() {
        FcoeNetwork fcoeNetwork = new FcoeNetwork();

        fcoeNetwork.setName(FCOE_NETWORK_NAME);
        fcoeNetwork.setType(ResourceCategory.RC_FCOE_NETWORK);
        fcoeNetwork.setVlanId(Integer.valueOf(400));

        TaskResourceV2 task = this.client.create(fcoeNetwork, false);

        System.out.println("FcoeNetworkClientSample : createFcoeNetwork : " +
                "Task object returned to client : " + task);
    }

    private void getFcoeNetwork() {
        FcoeNetwork fcoeNetwork = client.getById(FCOE_NETWORK_RESOURCE_ID);

        System.out.println("FcoeNetworkClientSample : getFcoeNetwork : " +
                "FcoeNetwork object returned to client : " + fcoeNetwork);
    }

    private void getAllFcoeNetworks() {
        ResourceCollection<FcoeNetwork> fcoeNetworks = client.getAll();

        System.out.println("FcoeNetworkClientSample : getAllFcoeNetworks : " +
                "FcoeNetworks returned to client (count) : " + fcoeNetworks.getCount());
    }

    private void getFcoeNetworkByName() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME);

        System.out.println("FcoeNetworkClientSample : getFcoeNetworkByName : " +
                "FcoeNetwork object returned to client : " + fcoeNetwork);
    }

    private void updateFcoeNetwork() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME);

        fcoeNetwork.setName(FCOE_NETWORK_NAME_UPDATED);

        TaskResourceV2 task = this.client.update(fcoeNetwork.getResourceId(),
                fcoeNetwork, false);

        System.out.println("FcoeNetworkClientSample : updateFcoeNetwork : " +
                "Task object returned to client : " + task);
    }

    private void deleteFcoeNetwork() {
        FcoeNetwork fcoeNetwork = client.getByName(FCOE_NETWORK_NAME_UPDATED);

        TaskResourceV2 task = this.client.delete(fcoeNetwork.getResourceId(), false);

        System.out.println("FcoeNetworkClientSample : deleteFcoeNetwork : " +
                "Task object returned to client : " + task);
    }

    public static void main(String[] args) {
        FcoeNetworkClientSample sample = new FcoeNetworkClientSample();

        sample.createFcoeNetwork();
        sample.getFcoeNetwork();
        sample.getFcoeNetworkByName();
        sample.getAllFcoeNetworks();
        sample.updateFcoeNetwork();
        sample.deleteFcoeNetwork();
    }
}
