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

import com.hp.ov.sdk.network.FabricResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class FabricBDDStep extends Background {

    @Given("^an instance of Fabric$")
    public void an_instance_of_Fabric() throws Throwable {
        OneView.setResource(FabricResource.getInstance());
    }

    @When("^OneView gets Reserved Vlan Range for Fabric$")
    public void oneview_gets_Reserved_Vlan_Range_for_Fabric() throws Throwable {
        resourceStr = FabricResource.getInstance().getReservedVlanRange(resourceID);
    }

}
