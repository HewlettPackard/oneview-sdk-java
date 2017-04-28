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

import com.hp.ov.sdk.network.LogicalDownlinkResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LogicalDownlinkBDDStep extends Background {

    @Given("^an instance of Logical Downlink$")
    public void an_instance_of_Logical_Downlink() throws Throwable {
        OneView.setResource(LogicalDownlinkResource.getInstance());
    }

    @When("^OneView gets Name of First Logical Downlink$")
    public void oneview_gets_Name_of_First_Logical_Downlink() throws Throwable {
        resourceName = LogicalDownlinkResource.getInstance().getNameFirstLogicalDownlink();
    }

    @When("^OneView lists all Logical Downlink Without Ethernet$")
    public void oneview_lists_all_Logical_Downlink_Without_Ethernet() throws Throwable {
        count = LogicalDownlinkResource.getInstance().getAllLogicalDownlinkWithoutEthernet();
    }

    @When("^OneView gets Logical Downlink by ID Without Ethernet$")
    public void oneview_gets_Logical_Downlink_by_ID_Without_Ethernet() throws Throwable {
        resourceStr = LogicalDownlinkResource.getInstance().getLogicalDownlinkWithoutEthernet(resourceID);
    }

}
