/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.resources.network;

import java.util.Map;

import com.hp.ov.sdk.network.EthernetNetworkResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class EthernetNetworkBDDStep extends Background {

    @Given("^an instance of Ethernet Network$")
    public void an_instance_of_Ethernet_Network() {
        OneView.setResource(EthernetNetworkResource.getInstance());
    }

    @Given("^bandwidth values as follows:$")
    public void bandwidth_values_as_follows(Map<String, Double> map) {
        ((EthernetNetworkResource) OneView.getResource()).setBandwidthValues(map);
    }

    @When("^OneView gets the Associated Profile List$")
    public void oneview_gets_the_Associated_Profile_List() throws Throwable {
        count = ((EthernetNetworkResource) OneView.getResource()).getAssociatedProfiles(resourceID);
    }

    @When("^OneView gets the Associated Uplink Groups$")
    public void oneview_gets_the_Associated_Uplink_Groups() throws Throwable {
        count = ((EthernetNetworkResource) OneView.getResource()).getAssociatedUplinkGroups(resourceID);
    }
}
