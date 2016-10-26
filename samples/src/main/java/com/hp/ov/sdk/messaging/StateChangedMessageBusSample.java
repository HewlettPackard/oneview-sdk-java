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

package com.hp.ov.sdk.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.messaging.core.MessageBusClient;
import com.hp.ov.sdk.messaging.scmb.ScmbMessage;
import com.hp.ov.sdk.messaging.scmb.ScmbMessageHandler;
import com.hp.ov.sdk.messaging.scmb.ScmbTypedMessage;
import com.hp.ov.sdk.messaging.scmb.ScmbTypedMessageHandler;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class StateChangedMessageBusSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateChangedMessageBusSample.class);

    private final MessageBusClient client;

    public StateChangedMessageBusSample() {
        OneViewClientSample sample = new OneViewClientSample();

        OneViewClient oneViewClient = sample.getOneViewClient();
        SDKConfiguration config = sample.getSDKConfiguration();

        this.client = MessageBusClient.connect(config, oneViewClient);
    }

    private void subscribeToTasksStateChangedMessageBus() {
        this.client.addScmbTypedHandler("scmb.tasks.#", new ScmbTypedMessageHandler<TaskResource>() {
            @Override
            public void handleMessage(ScmbTypedMessage<TaskResource> message) {
                TaskResource task = message.getResource();

                LOGGER.info("Task received in State-Changed Message Bus: {}", task.toJsonString());
            }
            @Override
            public TypeToken<ScmbTypedMessage<TaskResource>> typeToken() {
                return new TypeToken<ScmbTypedMessage<TaskResource>>() {};
            }
        });
    }

    private void subscribeToFcNetworksStateChangedMessageBus() {
        this.client.addScmbTypedHandler("scmb.fc-networks.#", new ScmbTypedMessageHandler<FcNetwork>() {
            @Override
            public void handleMessage(ScmbTypedMessage<FcNetwork> message) {
                FcNetwork network = message.getResource();

                LOGGER.info("FC-Network received in State-Changed Message Bus: {}", network.toJsonString());
            }
            @Override
            public TypeToken<ScmbTypedMessage<FcNetwork>> typeToken() {
                return new TypeToken<ScmbTypedMessage<FcNetwork>>() {};
            }
        });
    }

    private void subscribeToAllCreatedResourcesMessageBus() {
        this.client.addScmbHandler("scmb.*.Created.#", new ScmbMessageHandler() {
            @Override
            public void handleMessage(ScmbMessage message) {
                String resource = message.getResource();

                JsonElement element = new JsonParser().parse(resource);

                LOGGER.info("Created resource message received in State-Changed Message Bus: {}",
                        JsonPrettyPrinter.print(element));
            }
        });
    }

    private void disconnect() {
        this.client.disconnect();
    }

    public static void main(String[] args) throws InterruptedException {
        StateChangedMessageBusSample sample = new StateChangedMessageBusSample();

        sample.subscribeToTasksStateChangedMessageBus();
        sample.subscribeToFcNetworksStateChangedMessageBus();
        sample.subscribeToAllCreatedResourcesMessageBus();

        Thread.sleep(5 * 60 * 1000); //waits 5 minutes

        sample.disconnect();
    }

}
