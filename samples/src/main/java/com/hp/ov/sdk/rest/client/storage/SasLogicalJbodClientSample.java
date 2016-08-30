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

package com.hp.ov.sdk.rest.client.storage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.Drive;
import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbod;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class SasLogicalJbodClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalJbodClientSample.class);

    private final SasLogicalJbodClient sasLogicalJbodClient;

    // These are variables to be defined by user
    // ================================
    private static final String SAS_LOGICAL_JBOD_RESOURCE_ID = "6e8807a8-ae78-4a58-98ef-e6ff592f9e70";
    private static final String SAS_LOGICAL_JBOD_NAME = "test-logical-JBOD";
    // ================================

    private SasLogicalJbodClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.sasLogicalJbodClient = oneViewClient.sasLogicalJbod();
    }

    private void getSasLogicalJbodById() {
        SasLogicalJbod sasLogicalJbod = this.sasLogicalJbodClient.getById(SAS_LOGICAL_JBOD_RESOURCE_ID);

        LOGGER.info("SAS logical JBOD returned to client: {}", sasLogicalJbod.toJsonString());
    }

    private void getAllSasLogicalJbods() {
        ResourceCollection<SasLogicalJbod> sasLogicalJbods = this.sasLogicalJbodClient.getAll();

        LOGGER.info("SAS logical JBODs returned to client: {}", sasLogicalJbods.toJsonString());
    }

    private void getSasLogicalJbodByName() {
        SasLogicalJbod sasLogicalJbod = this.sasLogicalJbodClient.getByName(SAS_LOGICAL_JBOD_NAME).get(0);

        LOGGER.info("SAS logical JBOD returned to client: {}", sasLogicalJbod.toJsonString());
    }

    private void getSasLogicalJbodDrives() {
        SasLogicalJbod sasLogicalJbod = this.sasLogicalJbodClient.getByName(SAS_LOGICAL_JBOD_NAME).get(0);
        List<Drive> drives = this.sasLogicalJbodClient.getDrives(sasLogicalJbod.getResourceId());

        LOGGER.info("SAS logical JBOD drives returned to client: {}", JsonPrettyPrinter.print(drives));
    }

    public static void main(String[] args) {
        SasLogicalJbodClientSample sample = new SasLogicalJbodClientSample();

        sample.getSasLogicalJbodById();
        sample.getAllSasLogicalJbods();
        sample.getSasLogicalJbodByName();
        sample.getSasLogicalJbodDrives();
    }

}
