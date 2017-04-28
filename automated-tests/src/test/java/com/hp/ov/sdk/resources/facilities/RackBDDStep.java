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

package com.hp.ov.sdk.resources.facilities;

import com.hp.ov.sdk.facilities.RackResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class RackBDDStep extends Background {

    @Given("^an instance of Rack$")
    public void an_instance_of_Rack() throws Throwable {
        OneView.setResource(RackResource.getInstance());
    }
    
    @When("^OneView gets a Device Topology$")
    public void oneview_gets_a_Device_Topology() throws Throwable {
        resourceStr = RackResource.getInstance().getDeviceTopology(resourceID);
    }

    @When("^OneView deletes Rack by Filter$")
    public void oneview_deletes_Rack_by_Filter() throws Throwable {
        status = RackResource.getInstance().removeByFilter();
    }
    
}
