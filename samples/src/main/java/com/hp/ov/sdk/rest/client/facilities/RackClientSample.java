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

package com.hp.ov.sdk.rest.client.facilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.rest.client.GenericFilter;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

public class RackClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(RackClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String RACK_RESOURCE_ID = "d1778269-9efe-4a5b-be70-c9f556a47685";
    private static final String RACK_NAME = "Sample Rack";
    // ================================

    private final RackClient rackClient;

    public RackClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.rackClient = oneViewClient.rack();
    }

    private void getRackById() {
        Rack rack = this.rackClient.getById(RACK_RESOURCE_ID);

        LOGGER.info("Rack object returned to client : " + rack.toJsonString());
    }

    private void getAllRacks() {
        ResourceCollection<Rack> racks = this.rackClient.getAll();

        LOGGER.info("Racks returned to client : " + racks.toJsonString());
    }

    private void getRackByName() {
        Rack rack = this.rackClient.getByName(RACK_NAME).get(0);

        LOGGER.info("Rack object returned to client : " + rack.toJsonString());
    }

    private void addRack() {
        Rack rack = new Rack();

        rack.setName(RACK_NAME);

        Rack addedRack = this.rackClient.add(rack);

        LOGGER.info("Rack object returned to client : " + addedRack.toJsonString());
    }

    private void updateRack() {
        Rack rack = this.rackClient.getByName(RACK_NAME).get(0);
        String resourceId = rack.getResourceId();

        rack.setThermalLimit(Integer.valueOf(1000));

        Rack updatedRack = this.rackClient.update(resourceId, rack);

        LOGGER.info("Rack object returned to client : " + updatedRack.toJsonString());
    }

    private void removeRack() {
        Rack rack = this.rackClient.getByName(RACK_NAME).get(0);
        String response = this.rackClient.remove(rack.getResourceId());

        LOGGER.info("Response returned to client : " + response);
    }

    private void removeRackByFilter() {
        GenericFilter filter = new GenericFilter();
        filter.setFilter("'name' = '" + RACK_NAME + "'");
        TaskResource task = this.rackClient.removeByFilter(filter);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getDeviceTopology() {
        TopologyInformation topologyInformation = this.rackClient.getDeviceTopology(RACK_RESOURCE_ID);

        LOGGER.info("TopologyInformation object returned to client : " + JsonPrettyPrinter.print(topologyInformation));
    }

    public static void main(String[] args) {
        RackClientSample sample = new RackClientSample();

        sample.getRackById();
        sample.getAllRacks();
        sample.addRack();
        sample.updateRack();
        sample.getRackByName();
        sample.getDeviceTopology();
        sample.removeRack();

        sample.addRack();
        sample.removeRackByFilter();
    }

}
