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

import com.hp.ov.sdk.network.InterconnectLinkTopologyResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class InterconnectLinkTopologyBDDStep extends Background {

    @Given("^an instance of Interconnect Link Topology$")
    public void an_instance_of_Interconnect_Link_Topology() throws Throwable {
        OneView.setResource(InterconnectLinkTopologyResource.getInstance());
    }
    
    @When("^OneView gets Name of First Interconnect Link Topology$")
    public void oneview_gets_Name_of_First_Interconnect_Link_Topology() throws Throwable {
        resourceName = InterconnectLinkTopologyResource.getInstance().getNameFirstInterconnectLinkTopology();
    }

}
