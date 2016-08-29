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
import com.hp.ov.sdk.dto.networking.internallinksets.InternalLinkSet;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class InternalLinkSetClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternalLinkSetClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String INTERNAL_LINK_SET_RESOURCE_ID = "410673a1-478c-4696-b576-25132ff2a2d1";
    private static final String INTERNAL_LINK_SET_NAME = "Encl2-LIG1/uplink2/logicalSwitch";
    // ================================

    private final InternalLinkSetClient client;

    private InternalLinkSetClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.internalLinkSet();
    }

    private void getInternalLinkSet() {
        InternalLinkSet internalLinkSet = client.getById(INTERNAL_LINK_SET_RESOURCE_ID);

        LOGGER.info("InternalLinkSet object returned to client : " + internalLinkSet.toJsonString());
    }

    private void getAllInternalLinkSets() {
        ResourceCollection<InternalLinkSet> internalLinkSets = client.getAll();

        LOGGER.info("InternalLinkSets returned to client (count) : " + internalLinkSets.getCount());
    }

    private void getInternalLinkSetByName() {
        InternalLinkSet internalLinkSet = client.getByName(INTERNAL_LINK_SET_NAME).get(0);

        LOGGER.info("InternalLinkSet object returned to client : " + internalLinkSet.toJsonString());
    }

    public static void main(String[] args) {
        InternalLinkSetClientSample sample = new InternalLinkSetClientSample();

        sample.getAllInternalLinkSets();
        sample.getInternalLinkSet();
        sample.getInternalLinkSetByName();
    }

}
