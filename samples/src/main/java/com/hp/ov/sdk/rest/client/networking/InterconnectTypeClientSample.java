/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.InterconnectTypeName;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * InterconnectTypeClientSample is a sample program to consume the characteristics model of an interconnect in
 * HPE OneView.It invokes APIs of InterconnectTypeClient which is in sdk library to perform GET
 * operations on interconnect type resource
 */
public class InterconnectTypeClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectTypeClientSample.class);

    private final InterconnectTypeClient interconnectTypeClient;

    // These are variables to be defined by user
    // ================================
    private static final String INTERCONNECT_TYPE_RESOURCE_ID = "15943359-6e16-4b42-9900-4b98118914ad";
    // ================================

    public InterconnectTypeClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.interconnectTypeClient = oneViewClient.interconnectType();
    }

    private void getInterconnectType() {
        InterconnectType interconnectType = this.interconnectTypeClient.getById(INTERCONNECT_TYPE_RESOURCE_ID);

        LOGGER.info("Interconnect type returned to client: {}", interconnectType.toJsonString());
    }

    private void getAllInterconnectTypes() {
        ResourceCollection<InterconnectType> interconnectTypes = this.interconnectTypeClient.getAll();

        LOGGER.info("Interconnect types returned to client: {}", interconnectTypes.toJsonString());
    }

    private void getInterconnectTypeByName() {
        InterconnectType interconnectType = this.interconnectTypeClient.getByName(
                InterconnectTypeName.HP_VC_FlexFabric_20_40_F8_Module).get(0);

        LOGGER.info("Interconnect type returned to client: {}", interconnectType.toJsonString());
    }

    private void getInterconnectTypeByNameString() {
        InterconnectType interconnectType = this.interconnectTypeClient.getByName("HP VC FlexFabric-20/40 F8 Module").get(0);

        LOGGER.info("Interconnect type returned to client: {}", interconnectType.toJsonString());
    }

    public static void main(final String[] args) throws Exception {
        InterconnectTypeClientSample client = new InterconnectTypeClientSample();

        client.getInterconnectType();
        client.getAllInterconnectTypes();
        client.getInterconnectTypeByName();
        client.getInterconnectTypeByNameString();
    }
}
