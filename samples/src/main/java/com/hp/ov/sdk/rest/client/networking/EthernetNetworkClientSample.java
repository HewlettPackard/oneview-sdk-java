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

import static com.hp.ov.sdk.rest.client.settings.ScopeClient.SCOPES_URI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.EthernetNetworkType;
import com.hp.ov.sdk.dto.networking.ethernet.Bandwidth;
import com.hp.ov.sdk.dto.networking.ethernet.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.dto.networking.ethernet.Purpose;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.BasicURIQuery;
import com.hp.ov.sdk.rest.http.core.URIQuery;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class EthernetNetworkClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthernetNetworkClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String ETHERNET_NETWORK_NAME = "Ethernet-Network_SAMPLE";
    private static final String ETHERNET_NETWORK_NAME_UPDATED = ETHERNET_NETWORK_NAME + "_Updated";
    private static final String ETHERNET_NETWORK_BULK_VLAN_INTERVAL = "401-405";

    public static final String ETHERNET_NETWORK_PROD_401 = "Prod_401";
    public static final String ETHERNET_NETWORK_PROD_402 = "Prod_402";

    private static final String SCOPE_ID = "3cadbda5-45eb-4e0f-bdb6-2e9101317b77";
    // ================================

    private final EthernetNetworkClient client;

    private EthernetNetworkClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.client = oneViewClient.ethernetNetwork();
    }

    private void createEthernetNetwork() {
        Network network = new Network();

        network.setName(ETHERNET_NETWORK_NAME);
        network.setType(ResourceCategory.RC_NETWORK); //120
        network.setType(ResourceCategory.RC_NETWORK_V200); //200
        network.setType(ResourceCategory.RC_NETWORK_V300); //300 or 500
        network.setVlanId(Integer.valueOf(400));
        network.setSmartLink(true);
        network.setPrivateNetwork(false);
        network.setConnectionTemplateUri(null);
        network.setConnectionTemplate(null);
        network.setPurpose(Purpose.General);
        network.setEthernetNetworkType(EthernetNetworkType.Tagged);

        TaskResource task = this.client.create(network);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void getEthernetNetwork() {
        Network network = client.getByName(ETHERNET_NETWORK_NAME).get(0);

        network = client.getById(network.getResourceId());

        LOGGER.info("Ethernet network returned to client: {}", network.toJsonString());
    }

    private void getAllEthernetNetworks() {
        ResourceCollection<Network> networks = client.getAll();

        LOGGER.info("Ethernet networks returned to client : {}", networks.toJsonString());
    }

    private void getAllEthernetNetworksWithCount() {
        ResourceCollection<Network> networks = client.getAll(500);

        LOGGER.info("Number of ethernet networks returned to client (count): {}", networks.getCount());
    }

    private void getEthernetNetworksWithFilter() {
        BasicURIQuery query = new BasicURIQuery();

        query.addParameter(URIQuery.START, "2");
        query.addParameter(URIQuery.COUNT, "1");

        ResourceCollection<Network> networks = client.get(query);

        LOGGER.info("Ethernet networks returned to client: {}", networks.toJsonString());
    }

    private void getEthernetNetworkByName() {
        Network network = client.getByName(ETHERNET_NETWORK_NAME).get(0);

        LOGGER.info("Ethernet network returned to client: {}", network.toJsonString());
    }

    private void patchEthernetNetwork() {
        Network network = client.getByName(ETHERNET_NETWORK_NAME).get(0);

        Patch patch = new Patch();

        // Ethernet Network patch supports the update of scopeUris
        patch.setOp(PatchOperation.replace);
        patch.setPath("/scopeUris");
        List<String> scopeUris = network.getScopeUris(); // Gets the current scope(s)
        scopeUris.add(SCOPES_URI + "/" + SCOPE_ID); // Assigns Ethernet network to new a scope
        //scopeUris.remove(SCOPES_URI + "/" + SCOPE_ID); // Unassigns Ethernet network from a scope
        patch.setValue(scopeUris);

        TaskResource taskResource = this.client.patch(network.getResourceId(), patch);

        LOGGER.info("Task object returned to client: " + taskResource.toJsonString());
    }

    private void updateEthernetNetwork() {
        Network network = client.getByName(ETHERNET_NETWORK_NAME).get(0);

        network.setName(ETHERNET_NETWORK_NAME_UPDATED);

        TaskResource task = this.client.update(network.getResourceId(), network);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void deleteEthernetNetwork() {
        Network network = client.getByName(ETHERNET_NETWORK_NAME_UPDATED).get(0);

        TaskResource task = this.client.delete(network.getResourceId());

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void getAssociatedProfiles() {
        Network network = client.getByName(ETHERNET_NETWORK_NAME).get(0);

        List<String> uris = client.getAssociatedProfiles(network.getResourceId());

        LOGGER.info("Associated profile URIs returned to client: {}", JsonPrettyPrinter.print(uris));
    }

    private void getAssociatedUplinkGroups() {
        Network network = client.getByName(ETHERNET_NETWORK_NAME).get(0);

        List<String> uris = client.getAssociatedUplinkGroups(network.getResourceId());

        LOGGER.info("Associated uplink group URIs returned to client: {}", JsonPrettyPrinter.print(uris));
    }

    private void createEthernetNetworkInBulk() {
        BulkEthernetNetwork network = new BulkEthernetNetwork();

        network.setVlanIdRange(ETHERNET_NETWORK_BULK_VLAN_INTERVAL);
        network.setPurpose(Purpose.General);
        network.setNamePrefix("Prod");
        network.setSmartLink(false);
        network.setPrivateNetwork(false);
        network.setType(ResourceCategory.RC_BULK_NETWORK);

        Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(Double.valueOf(8000));
        bandwidth.setTypicalBandwidth(Double.valueOf(3000));

        network.setBandwidth(bandwidth);

        TaskResource task = this.client.createInBulk(network);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    public static void main(String[] args) {
        EthernetNetworkClientSample sample = new EthernetNetworkClientSample();

        sample.createEthernetNetwork();
        sample.getEthernetNetwork();
        sample.getEthernetNetworkByName();
        sample.getAllEthernetNetworks();

        sample.getAssociatedProfiles();
        sample.getAssociatedUplinkGroups();

        sample.patchEthernetNetwork();
        sample.updateEthernetNetwork();
        sample.deleteEthernetNetwork();

        sample.createEthernetNetworkInBulk();
        sample.getEthernetNetworksWithFilter();
        sample.getAllEthernetNetworksWithCount();
    }
}
