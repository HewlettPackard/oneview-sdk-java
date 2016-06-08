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

package com.hp.ov.sdk.rack;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.RackClient;
import com.hp.ov.sdk.rest.client.RackClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class RackClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String RACK_RESOURCE_ID = "d1778269-9efe-4a5b-be70-c9f556a47685";
    private static final String RACK_NAME = "Sample Rack";
    // ================================

    private final RackClient rackClient;

    public RackClientSample() {
        this.rackClient = RackClientImpl.getClient();
    }

    private void getRack() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            Rack rack = this.rackClient.getRack(params, RACK_RESOURCE_ID);

            System.out.println("RackClientSample : getRack : " +
                    "Rack object returned to client : " + rack);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : getRack : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : getRack : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : getRack : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : getRack : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : getRack : " +
                    "arguments are null ");
        }
    }

    private void getAllRacks() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            ResourceCollection<Rack> racks = this.rackClient.getAllRacks(params);

            System.out.println("RackClientSample : getAllRacks : " +
                    "RackCollection object returned to client (count) : " + racks.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : getAllRacks : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : getAllRacks : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : getAllRacks : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : getAllRacks : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : getAllRacks : " +
                    "arguments are null ");
        }
    }

    private void getRackByName() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            Rack rack = this.rackClient.getRackByName(params, RACK_NAME);

            System.out.println("RackClientSample : getRackByName : " +
                    "Rack object returned to client : " + rack);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : getRackByName : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : getRackByName : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : getRackByName : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : getRackByName : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : getRackByName : " +
                    "arguments are null ");
        }
    }

    private void addRack() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            Rack rack = new Rack();

            rack.setName(RACK_NAME);

            Rack addedRack = this.rackClient.addRack(params, rack);

            System.out.println("RackClientSample : addRack : " +
                    "Rack object returned to client : " + addedRack);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : addRack : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : addRack : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : addRack : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : addRack : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : addRack : " +
                    "arguments are null ");
        }
    }

    private void updateRack() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            Rack rack = this.rackClient.getRackByName(params, RACK_NAME);
            String resourceId = UrlUtils.getResourceIdFromUri(rack.getUri());

            rack.setThermalLimit(Integer.valueOf(1000));

            Rack updatedRack = this.rackClient.updateRack(params, resourceId, rack);

            System.out.println("RackClientSample : updateRack : " +
                    "Rack object returned to client : " + updatedRack);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : updateRack : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : updateRack : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : updateRack : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : updateRack : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : updateRack : " +
                    "arguments are null ");
        }
    }

    private void getDeviceTopology() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.rackClient.getId(params, RACK_NAME);
            TopologyInformation topologyInformation = this.rackClient.getDeviceTopology(params, resourceId);

            System.out.println("RackClientSample : getDeviceTopology : " +
                    "TopologyInformation returned to client : " + topologyInformation);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : getDeviceTopology : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : getDeviceTopology : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : getDeviceTopology : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : getDeviceTopology : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : getDeviceTopology : " +
                    "arguments are null ");
        }
    }

    private void removeRack() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.rackClient.getId(params, RACK_NAME);
            String response = this.rackClient.removeRack(params, resourceId);

            System.out.println("RackClientSample : removeRack : " +
                    "response returned to client : " + response);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : removeRack : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : removeRack : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : removeRack : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : removeRack : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : removeRack : " +
                    "arguments are null ");
        }
    }

    private void removeRackByFilter() {
        RestParams params = new RestParams();
        try {
            params = HPOneViewCredential.createCredentials();

            String filter = "name='" + RACK_NAME + "'";
            TaskResourceV2 task = this.rackClient.removeRackByFilter(params, filter, false);

            System.out.println("RackClientSample : removeRackByFilter : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("RackClientSample : removeRackByFilter : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("RackClientSample : removeRackByFilter : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("RackClientSample : removeRackByFilter : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("RackClientSample : removeRackByFilter : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("RackClientSample : removeRackByFilter : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("RackClientSample : removeRackByFilter : "
                    + "errors in task, please check task resource for more details");
        }
    }

    public static void main(String[] args) {
        RackClientSample sample = new RackClientSample();

        sample.getRack();
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
