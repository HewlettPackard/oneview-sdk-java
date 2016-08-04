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
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.logicaldownlinks.LogicalDownlink;
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * LogicalDownlinkClientSample is a sample program to consume default connection configuration characteristics of
 * HPE OneView. It invokes APIs of LogicalDownlinkClient which is in sdk library to perform GET operations on
 * logical downlink resource
 */
public class LogicalDownlinkClientSample {

    private final LogicalDownlinkClient logicalDownlinkClient;

    // test values - user input
    // ================================
    private static final String LOGICAL_DOWNLINK_RESOURCE_NAME = "LD75dcb24b-cea1-4d8e-9d38-4baf13a9e316 (HP VC FlexFabric-20/40 F8 Module)";
    private static final String LOGICAL_DOWNLINK_RESOURCE_ID = "fb5d9ead-4000-4023-a85d-dde520f13b68";
    // ================================

    private LogicalDownlinkClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.logicalDownlinkClient = oneViewClient.logicalDownlink();
    }

    private void getLogicalDownlinkById() {
        LogicalDownlink logicalDownlink = this.logicalDownlinkClient.getById(LOGICAL_DOWNLINK_RESOURCE_ID);

        System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkById : " +
                "LogicalDownlink object returned to client : " + logicalDownlink.toJsonString());
    }

    private void getAllLogicalDownlink() {
        ResourceCollection<LogicalDownlink> logicalDownlink = this.logicalDownlinkClient.getAll();

        System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlink : " +
                "LogicalDownlink collection returned to client : " + logicalDownlink.toJsonString());
    }

    private void getLogicalDownlinkByName() {
        LogicalDownlink logicalDownlink = this.logicalDownlinkClient.getByName(LOGICAL_DOWNLINK_RESOURCE_NAME).get(0);

        System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkByName : " +
                "LogicalDownlink object returned to client : " + logicalDownlink.toJsonString());
    }

    private void getLogicalDownlinkByIdWithoutEthernet() {
        LogicalDownlink logicalDownlink = this.logicalDownlinkClient.getByIdWithoutEthernet(LOGICAL_DOWNLINK_RESOURCE_ID);

        System.out.println("LogicalDownlinkClientSample : getLogicalDownlinkByIdWithoutEthernet : " +
                "LogicalDownlink object returned to client : " + logicalDownlink.toJsonString());
    }

    private void getAllLogicalDownlinkWithoutEthernet() {
        ResourceCollection<LogicalDownlink> logicalDownlink = this.logicalDownlinkClient.getAllWithoutEthernet();

        System.out.println("LogicalDownlinkClientSample : getAllLogicalDownlinkWithoutEthernet : " +
                "LogicalDownlink collection returned to client : " + logicalDownlink.toJsonString());
    }

    public static void main(String[] args) {
        LogicalDownlinkClientSample sample = new LogicalDownlinkClientSample();

        sample.getLogicalDownlinkById();
        sample.getAllLogicalDownlink();
        sample.getLogicalDownlinkByName();
        sample.getLogicalDownlinkByIdWithoutEthernet();
        sample.getAllLogicalDownlinkWithoutEthernet();
    }

}
