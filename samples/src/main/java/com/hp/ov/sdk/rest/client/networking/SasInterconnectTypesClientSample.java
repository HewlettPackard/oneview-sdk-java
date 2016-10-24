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
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectType;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectTypeName;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class SasInterconnectTypesClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasInterconnectTypesClientSample.class);

    private final SasInterconnectTypeClient sasInterconnectTypeClient;

    // These are variables to be defined by user
    // ================================
    private static final String SAS_INTERCONNECT_TYPE_RESOURCE_ID = "Synergy12GbSASConnectionModule";
    // ================================

    public SasInterconnectTypesClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.sasInterconnectTypeClient = oneViewClient.sasInterconnectType();
    }

    private void getSasInterconnectTypeById() {
        SasInterconnectType interconnectType = this.sasInterconnectTypeClient.getById(
                SAS_INTERCONNECT_TYPE_RESOURCE_ID);

        LOGGER.info("SAS interconnect type returned to client: {}", interconnectType.toJsonString());
    }

    private void getAllSasInterconnectTypes() {
        ResourceCollection<SasInterconnectType> interconnectTypes = this.sasInterconnectTypeClient.getAll();

        LOGGER.info("SAS interconnect types returned to client: {}", interconnectTypes.toJsonString());
    }

    private void getSasInterconnectTypeByName() {
        SasInterconnectType interconnectType = this.sasInterconnectTypeClient.getByName(
                SasInterconnectTypeName.SYNERGY_12GB_SAS_CONNECTION_MODULE).get(0);

        LOGGER.info("SAS interconnect type returned to client: {}", interconnectType.toJsonString());
    }

    private void getSasInterconnectTypeByNameString() {
        SasInterconnectType interconnectType = this.sasInterconnectTypeClient.getByName("Synergy 12Gb SAS Connection Module").get(0);

        LOGGER.info("SAS interconnect type returned to client: {}", interconnectType.toJsonString());
    }

    public static void main(final String[] args) {
        SasInterconnectTypesClientSample client = new SasInterconnectTypesClientSample();

        client.getAllSasInterconnectTypes();
        client.getSasInterconnectTypeById();
        client.getSasInterconnectTypeByName();
        client.getSasInterconnectTypeByNameString();
    }

}
