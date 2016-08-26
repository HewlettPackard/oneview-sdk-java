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
package com.hp.ov.sdk.rest.client.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosure;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosurePortMap;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosureRefreshRequest;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class DriveEnclosureClientSample {

    private final DriveEnclosureClient driveEnclosureClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(DriveEnclosureClient.class);

    // test values - user input
    // ================================
    public static final String RESOURCE_NAME = "0000A66102, bay 1";
    private static final String RESOURCE_ID = "SN123101";
    // ================================

    private DriveEnclosureClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.driveEnclosureClient = oneViewClient.driveEnclosure();
    }

    private void getDriveEnclosureById() {
        DriveEnclosure driveEnclosure = driveEnclosureClient.getById(RESOURCE_ID);

        LOGGER.info("Drive Enclosure object returned to client : " + driveEnclosure.toJsonString());
    }

    private void getAllDriveEnclosures() {
        ResourceCollection<DriveEnclosure> driveEnclosures = driveEnclosureClient.getAll();

        LOGGER.info("Drive Enclosure returned to client (count) : " + driveEnclosures.getCount());
    }

    private void getDriveEnclosureByName() {
        DriveEnclosure driveEnclosure = driveEnclosureClient.getByName(RESOURCE_NAME).get(0);

        LOGGER.info("Drive Enclosure object returned to client : " + driveEnclosure.toJsonString());
    }

    private void getDriveEnclosurePortMap() {
        DriveEnclosurePortMap driveEnclosurePortMap = driveEnclosureClient.getPortMap(RESOURCE_ID);

        LOGGER.info("Drive Enclosure port map object returned to client : " + JsonPrettyPrinter.print(driveEnclosurePortMap));
    }

    private void patchDriveEnclosure() {
        DriveEnclosure driveEnclosure = this.driveEnclosureClient.getByName(RESOURCE_NAME).get(0);
        Patch patch = new Patch();

        // Drive Enclosure patch supports the update of power state, UID and hard reset
        patch.setOp(PatchOperation.replace);
        patch.setPath("/powerState");
        patch.setValue("On");

        TaskResourceV2 taskResource = this.driveEnclosureClient.patch(driveEnclosure.getResourceId(), patch, false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateEnclosureRefreshState() {
        DriveEnclosure driveEnclosure = this.driveEnclosureClient.getByName(RESOURCE_NAME).get(0);

        DriveEnclosureRefreshRequest refreshStateConfig = new DriveEnclosureRefreshRequest();
        refreshStateConfig.setRefreshState(RefreshState.RefreshPending);

        TaskResourceV2 taskResource = this.driveEnclosureClient.updateRefreshState(
                driveEnclosure.getResourceId(), refreshStateConfig, false);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    public static void main(final String[] args) throws Exception {
        DriveEnclosureClientSample client = new DriveEnclosureClientSample();

        client.getAllDriveEnclosures();
        client.getDriveEnclosureById();
        client.getDriveEnclosureByName();

        client.getDriveEnclosurePortMap();

        client.patchDriveEnclosure();

        client.updateEnclosureRefreshState();
    }
}
