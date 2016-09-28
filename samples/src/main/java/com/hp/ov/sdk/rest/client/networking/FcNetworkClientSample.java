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
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class FcNetworkClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcNetworkClientSample.class);

    // These are variables to be defined by the user
    // ================================
    public static final String FC_NETWORK_NAME_A = "FC_Network_A";
    public static final String FC_NETWORK_NAME_B = "FC_Network_B";

    private static final String FC_NETWORK_NAME_UPDATED = FC_NETWORK_NAME_A + "_Updated";
    // ================================

    private final FcNetworkClient client;

    private FcNetworkClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.fcNetwork();
    }

    private void createFcNetwork() {
        FcNetwork fcNetwork = new FcNetwork();

        fcNetwork.setName(FC_NETWORK_NAME_A);
        fcNetwork.setType(ResourceCategory.RC_FCNETWORK); //v200
        fcNetwork.setType(ResourceCategory.RC_FCNETWORK_V300); //v300

        TaskResource task = this.client.create(fcNetwork);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getFcNetwork() {
        FcNetwork fcNetwork = client.getByName(FC_NETWORK_NAME_A).get(0);

        fcNetwork = client.getById(fcNetwork.getResourceId());

        LOGGER.info("FC network object returned to client : " + fcNetwork.toJsonString());
    }

    private void getAllFcNetworks() {
        ResourceCollection<FcNetwork> fcNetworks = client.getAll();

        LOGGER.info("FC networks returned to client (count) : " + fcNetworks.getCount());
    }

    private void getFcNetworkByName() {
        FcNetwork fcNetwork = client.getByName(FC_NETWORK_NAME_A).get(0);

        LOGGER.info("FC network object returned to client : " + fcNetwork.toJsonString());
    }

    private void updateFcNetwork() {
        FcNetwork fcNetwork = client.getByName(FC_NETWORK_NAME_A).get(0);

        fcNetwork.setName(FC_NETWORK_NAME_UPDATED);

        TaskResource task = this.client.update(fcNetwork.getResourceId(), fcNetwork);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void deleteFcNetwork() {
        FcNetwork fcNetwork = client.getByName(FC_NETWORK_NAME_UPDATED).get(0);

        TaskResource task = this.client.delete(fcNetwork.getResourceId());

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    public static void main(String[] args) {
        FcNetworkClientSample sample = new FcNetworkClientSample();

        sample.createFcNetwork();

        sample.getAllFcNetworks();
        sample.getFcNetwork();
        sample.getFcNetworkByName();
        sample.updateFcNetwork();
        sample.deleteFcNetwork();
    }

}
