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
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class SwitchTypeClientSample {

    // These are variables to be defined by the user
    // ================================
    private static final String SWITCH_TYPE_RESOURCE_ID = "3a5f0656-17be-46b3-b604-e89059354012";

    protected static final String SWITCH_TYPE_NAME = "Cisco Nexus 55xx";
    // ================================

    private final SwitchTypeClient client;

    private SwitchTypeClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.switchType();
    }

    private void getSwitchType() {
        InterconnectType switchType = client.getById(SWITCH_TYPE_RESOURCE_ID);

        System.out.println("SwitchTypeClientSample : getSwitchType : " +
                "SwitchType object returned to client : " + switchType);
    }

    private void getAllSwitchTypes() {
        ResourceCollection<InterconnectType> switchTypes = client.getAll();

        System.out.println("SwitchTypeClientSample : getAllSwitchTypes : " +
                "SwitchTypes returned to client (count) : " + switchTypes.getCount());
    }

    private void getSwitchTypeByName() {
        InterconnectType switchType = client.getByName(SWITCH_TYPE_NAME);

        System.out.println("SwitchTypeClientSample : getSwitchTypeByName : " +
                "SwitchType object returned to client : " + switchType);
    }

    public static void main(String[] args) {
        SwitchTypeClientSample sample = new SwitchTypeClientSample();

        sample.getSwitchType();
        sample.getAllSwitchTypes();
        sample.getSwitchTypeByName();
    }
}
