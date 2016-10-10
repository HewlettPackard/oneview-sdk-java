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

package com.hp.ov.sdk.rest.client.servers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareTypeClient;

public class ServerHardwareTypeClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHardwareTypeClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String SERVER_HARDWARE_TYPE_RESOURCE_NAME = "BL460c Gen8 1";
    private static final String SERVER_HARDWARE_TYPE_RESOURCE_ID = "53E2C267-CEDC-4A0B-961E-10034F5B7098";
    public static final String SERVER_HARDWARE_TYPE_URI = "/rest/server-hardware-types/653D52C0-1314-411B-BF76-831A21A06C8A";
    // ================================

    private final ServerHardwareTypeClient serverHardwareTypeClient;

    private ServerHardwareTypeClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.serverHardwareTypeClient = oneViewClient.serverHardwareType();
    }

    private void getServerHardwareType() {
        ServerHardwareType serverHardwareType = serverHardwareTypeClient.getById(SERVER_HARDWARE_TYPE_RESOURCE_ID);

        LOGGER.info("Server hardware type returned to client: {}", serverHardwareType.toJsonString());
    }

    private void getAllServerHardwareTypes() {
        ResourceCollection<ServerHardwareType> serverHardwareTypes = serverHardwareTypeClient.getAll();

        LOGGER.info("Server hardware types returned to client: {}", serverHardwareTypes.toJsonString());
    }

    private void getServerHardwareTypeByName() {
        ServerHardwareType serverHardwareType
                = serverHardwareTypeClient.getByName(SERVER_HARDWARE_TYPE_RESOURCE_NAME).get(0);

        LOGGER.info("Server hardware type returned to client: {}", serverHardwareType.toJsonString());
    }

    private void updateServerHardwareType() {
        ServerHardwareType serverHardwareType
                = serverHardwareTypeClient.getByName(SERVER_HARDWARE_TYPE_RESOURCE_NAME).get(0);

        ServerHardwareTypeUpdate update = new ServerHardwareTypeUpdate();

        update.setDescription("Sample description update");

        ServerHardwareType updated = this.serverHardwareTypeClient.update(serverHardwareType.getResourceId(), update);

        LOGGER.info("Server hardware type returned to client: {}", updated.toJsonString());
    }

    private void deleteServerHardwareType() {
        ServerHardwareType serverHardwareType
                = serverHardwareTypeClient.getByName(SERVER_HARDWARE_TYPE_RESOURCE_NAME).get(0);

        TaskResource taskResource = this.serverHardwareTypeClient.delete(serverHardwareType.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    public static void main(String[] args) {
        ServerHardwareTypeClientSample sample = new ServerHardwareTypeClientSample();

        sample.getServerHardwareType();
        sample.getAllServerHardwareTypes();
        sample.getServerHardwareTypeByName();
        sample.updateServerHardwareType();

        /*available from OV 2.0*/
        sample.deleteServerHardwareType();
    }

}
