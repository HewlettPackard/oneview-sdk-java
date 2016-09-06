/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.networking;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.UrlUtils;

/*
 * NetworkSetClientSample is a sample program to consume a consolidated set of ethernet network
 * resource of HPE OneView. It invokes APIs of NetworkSetClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations network set resource
 */
public class NetworkSetClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkSetClientSample.class);

    private ResourceDtoUtils resourceDtoUtils;
    private NetworkSetClient client;

    // test values - user input
    // ================================
    private static final String resourceId = "58e65c0f-45e0-4003-bb8f-91922bf7cbf9";
    private static final String resourceName = "NetworkSet_Prod";
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402", "Prod_403", "Prod_404");
    // ================================

    private NetworkSetClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();
        this.client = oneViewClient.networkSet();

        this.resourceDtoUtils = new ResourceDtoUtils(oneViewClient);
    }

    private void getNetworkSetById() {
        NetworkSet networkSet = client.getById(resourceId);

        LOGGER.info("NetworkSet object returned to client : " + networkSet.toJsonString());
    }

    private void getAllNetworkSet() {

        ResourceCollection<NetworkSet> networkSets = client.getAll();

        LOGGER.info("NetworkSets returned to client (count) : " + networkSets.getCount());
    }

    private void getNetworkSetByName() {
        NetworkSet networkSet = client.getByName(resourceName).get(0);

        LOGGER.info("NetworkSet object returned to client : " + networkSet.toJsonString());
    }

    private void createNetworkSet() {
        NetworkSet networkSet = resourceDtoUtils.buildNetworkSetDto(resourceName, networkNames);

        networkSet.setName(resourceName);
        networkSet.setType(ResourceCategory.RC_NETWORKSET);
        networkSet.setType(ResourceCategory.RC_NETWORKSET_V300);

        TaskResourceV2 task = this.client.create(networkSet, false);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateNetworkSet() {
        NetworkSet networkSet = client.getByName(resourceName).get(0);

        networkSet.setName(resourceName + "_Updated");

        TaskResourceV2 task = this.client.update(UrlUtils.getResourceIdFromUri(networkSet.getUri()),
                networkSet, false);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void deleteNetworkSet() {
        NetworkSet networkSet = client.getByName(resourceName).get(0);

        TaskResourceV2 task = this.client.delete(UrlUtils.getResourceIdFromUri(networkSet.getUri()), false);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    public static void main(final String[] args) {
        NetworkSetClientSample client = new NetworkSetClientSample();

        client.getAllNetworkSet();
        client.createNetworkSet();
        client.getNetworkSetById();
        client.getNetworkSetByName();
        client.updateNetworkSet();
        client.deleteNetworkSet();
    }
}
