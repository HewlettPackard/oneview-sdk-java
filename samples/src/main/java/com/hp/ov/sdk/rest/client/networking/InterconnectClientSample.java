/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.networking;

import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

/*
 * InterconnectClientSample is a sample program to consume the characteristics model of an interconnect in
 * HPE OneView.It invokes APIs of InterconnectsClient which is in sdk library to perform GET/PUT
 * operations on interconnect resource
 */
public class InterconnectClientSample {

    private final InterconnectClient interconnectClient;

    public InterconnectClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.interconnectClient = oneViewClient.interconnect();
    }

    // These are variables to be defined by user
    // ================================
    private static final String RESOURCE_NAME = "Encl1, interconnect 2";
    private static final String RESOURCE_ID = "84046213-425f-4d37-8aaf-ba27fd6dfcda";
    private static final String PORT_NAME = "d1";
    // ================================

    private void getInterconnectById() {
        Interconnect interconnect = this.interconnectClient.getById(RESOURCE_ID);

        System.out.println("InterconnectClientSample : getInterconnectById : " +
                "Interconnect object returned to client : " + interconnect.toJsonString());
    }

    private void getAllInterconnects() {
        ResourceCollection<Interconnect> interconnects = this.interconnectClient.getAll();

        System.out.println("InterconnectClientSample : getAllInterconnects : " +
                "Interconnects returned to client : " + interconnects.toJsonString());
    }

    private void getInterconnectByName() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);

        System.out.println("InterconnectClientSample : getInterconnectByName : " +
                "Interconnect object returned to client : " + interconnect.toJsonString());
    }

    private void patchInterconnect() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);
        Patch patch = new Patch();

        // Interconnect patch supports the update of 'powerState', 'uidState' and 'deviceResetState'
        patch.setOp(PatchOperation.replace);
        patch.setPath("/powerState");
        patch.setValue("Off");

        TaskResourceV2 task = this.interconnectClient.patch(interconnect.getResourceId(), patch, false);

        System.out.println("InterconnectClientSample : patchInterconnect : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateInterconnectPort() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);
        Port port = interconnect.getPorts().get(0);

        port.setEnabled(!port.getEnabled());

        TaskResourceV2 task = interconnectClient.updatePort(interconnect.getResourceId(), port , false);

        System.out.println("InterconnectClientSample : updateInterconnectPort : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateInterconnectPorts() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);
        Port port = interconnect.getPorts().get(16);

        port.setEnabled(!port.getEnabled());

        List<Port> ports = Arrays.asList(port);

        TaskResourceV2 task = interconnectClient.updatePorts(interconnect.getResourceId(), ports , false);

        System.out.println("InterconnectClientSample : updateInterconnectPorts : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void resetInterconnectPortProtection() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);

        TaskResourceV2 task = interconnectClient.resetPortProtection(interconnect.getResourceId(), false);

        System.out.println("InterconnectClientSample : resetInterconnectPortProtection : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void getInterconnectStatistics() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);

        InterconnectsStatistics statistics = this.interconnectClient.getStatistics(interconnect.getResourceId());

        System.out.println("InterconnectClientSample : getInterconnectStatistics : " +
                "InterconnectsStatistics object returned to client : " + JsonPrettyPrinter.print(statistics));
    }

    private void getInterconnectPortStatistics() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);

        PortStatistics statistics = this.interconnectClient.getPortStatistics(
                interconnect.getResourceId(), PORT_NAME);

        System.out.println("InterconnectClientSample : getInterconnectPortStatistics : " +
                "PortStatistics object returned to client : " + JsonPrettyPrinter.print(statistics));
    }

    private void getInterconnectSubportStatistics() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);

        SubportStatistics statistics = this.interconnectClient.getSubportStatistics(
                interconnect.getResourceId(), PORT_NAME, 1);

        System.out.println("InterconnectClientSample : getInterconnectSubportStatistics : " +
                "SubportStatistics object returned to client : " + JsonPrettyPrinter.print(statistics));
    }

    private void getInterconnectNamedServers() {
        Interconnect interconnect = this.interconnectClient.getByName(RESOURCE_NAME).get(0);

        List<NameServer> namedServers = interconnectClient.getNamedServers(interconnect.getResourceId());

        System.out.println("InterconnectClientSample : getInterconnectNamedServers : " +
                "NamedServers returned to client : " + JsonPrettyPrinter.print(namedServers));
    }

    public static void main(final String[] args) {
        InterconnectClientSample client = new InterconnectClientSample();

        client.getAllInterconnects();
        client.getInterconnectById();
        client.getInterconnectByName();

        client.patchInterconnect();
        client.updateInterconnectPort();
        client.updateInterconnectPorts();
        client.resetInterconnectPortProtection();

        client.getInterconnectStatistics();
        client.getInterconnectPortStatistics();
        client.getInterconnectSubportStatistics();
        client.getInterconnectNamedServers();
    }

}
