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
import com.hp.ov.sdk.dto.networking.interconnectlinktopologies.InterconnectLinkTopology;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class InterconnectLinkTopologyClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectLinkTopologyClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String INTERCONNECT_LINK_TOP_RESOURCE_ID = "fabb1603-f574-4e03-958d-cb277c880ce5";
    private static final String INTERCONNECT_LINK_TOP_NAME = "name1553803748-1471961425938";
    // ================================

    private final InterconnectLinkTopologyClient client;

    private InterconnectLinkTopologyClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.interconnectLinkTopology();
    }

    private void getInterconnectLinkTopology() {
        InterconnectLinkTopology interconnectLinkTopology = client.getById(INTERCONNECT_LINK_TOP_RESOURCE_ID);

        LOGGER.info("InterconnectLinkTopology object returned to client : " + interconnectLinkTopology.toJsonString());
    }

    private void getAllInterconnectLinkTopologies() {
        ResourceCollection<InterconnectLinkTopology> interconnectLinkTopologys = client.getAll();

        LOGGER.info("InterconnectLinkTopologies returned to client (count) : " + interconnectLinkTopologys.getCount());
    }

    private void getInterconnectLinkTopologyByName() {
        InterconnectLinkTopology interconnectLinkTopology = client.getByName(INTERCONNECT_LINK_TOP_NAME).get(0);

        LOGGER.info("InterconnectLinkTopology object returned to client : " + interconnectLinkTopology.toJsonString());
    }

    public static void main(String[] args) {
        InterconnectLinkTopologyClientSample sample = new InterconnectLinkTopologyClientSample();

        sample.getAllInterconnectLinkTopologies();
        sample.getInterconnectLinkTopology();
        sample.getInterconnectLinkTopologyByName();
    }

}
