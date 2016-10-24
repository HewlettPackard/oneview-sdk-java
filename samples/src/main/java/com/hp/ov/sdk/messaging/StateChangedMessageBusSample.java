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
import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.messaging.core.MessageBusClient;
import com.hp.ov.sdk.messaging.scmb.ScmbMessage;
import com.hp.ov.sdk.messaging.scmb.ScmbMessageHandler;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;

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
        this.client.addScmbHandler("scmb.tasks.#", new ScmbMessageHandler<TaskResource>() {
            @Override
            public void handleMessage(ScmbMessage<TaskResource> message) {
                TaskResource task = message.getResource();

                LOGGER.info("Task received in State-Changed Message Bus: {}", task.toJsonString());
            }
            @Override
            public TypeToken<ScmbMessage<TaskResource>> typeToken() {
                return new TypeToken<ScmbMessage<TaskResource>>() {};
            }
        });
    }

    private void disconnect() {
        this.client.disconnect();
    }

    public static void main(String[] args) throws InterruptedException {
        StateChangedMessageBusSample sample = new StateChangedMessageBusSample();

        sample.subscribeToTasksStateChangedMessageBus();

        Thread.sleep(5 * 60 * 1000); //waits 10 min

        sample.disconnect();
    }

}
