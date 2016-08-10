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
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class FcNetworkClientSample {

    // These are variables to be defined by the user
    // ================================
    private static final String FC_NETWORK_RESOURCE_ID = "37a92c8f-a2ef-4817-85f4-80d27abb09e2";
    private static final String FC_NETWORK_NAME = "FC_Network_A";
    private static final String FC_NETWORK_NAME_UPDATED = FC_NETWORK_NAME + "_Updated";
    // ================================

    private final FcNetworkClient client;

    private FcNetworkClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.fcNetwork();
    }

    private void createFcNetwork() {
        FcNetwork fcNetwork = new FcNetwork();

        fcNetwork.setName(FC_NETWORK_NAME);
        fcNetwork.setType(ResourceCategory.RC_FCNETWORK);

        TaskResourceV2 task = this.client.create(fcNetwork, false);

        System.out.println("FcNetworkClientSample : createFcNetwork : " +
                "Task object returned to client : " + task);
    }

    private void getFcNetwork() {
        FcNetwork fcNetwork = client.getById(FC_NETWORK_RESOURCE_ID);

        System.out.println("FcNetworkClientSample : getFcNetwork : " +
                "FcNetwork object returned to client : " + fcNetwork);
    }

    private void getAllFcNetworks() {
        ResourceCollection<FcNetwork> fcNetworks = client.getAll();

        System.out.println("FcNetworkClientSample : getAllFcNetworks : " +
                "FcNetworks returned to client (count) : " + fcNetworks.getCount());
    }

    private void getFcNetworkByName() {
        FcNetwork fcNetwork = client.getByName(FC_NETWORK_NAME).get(0);

        System.out.println("FcNetworkClientSample : getFcNetworkByName : " +
                "FcNetwork object returned to client : " + fcNetwork);
    }

    private void updateFcNetwork() {
        FcNetwork fcNetwork = client.getByName(FC_NETWORK_NAME).get(0);

        fcNetwork.setName(FC_NETWORK_NAME_UPDATED);

        TaskResourceV2 task = this.client.update(fcNetwork.getResourceId(),
                fcNetwork, false);

        System.out.println("FcNetworkClientSample : updateFcNetwork : " +
                "Task object returned to client : " + task);
    }

    private void deleteFcNetwork() {
        FcNetwork fcNetwork = client.getByName(FC_NETWORK_NAME_UPDATED).get(0);

        TaskResourceV2 task = this.client.delete(fcNetwork.getResourceId(), false);

        System.out.println("FcNetworkClientSample : deleteFcNetwork : " +
                "Task object returned to client : " + task);
    }

    public static void main(String[] args) {
        FcNetworkClientSample sample = new FcNetworkClientSample();

        sample.getAllFcNetworks();
        sample.getFcNetwork();
        sample.createFcNetwork();
        sample.getFcNetworkByName();
        sample.updateFcNetwork();
        sample.deleteFcNetwork();
    }

}
