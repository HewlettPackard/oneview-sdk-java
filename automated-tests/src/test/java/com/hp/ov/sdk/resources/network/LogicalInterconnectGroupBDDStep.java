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

import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.network.LogicalInterconnectGroupResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LogicalInterconnectGroupBDDStep extends Background {

    private String settingID;

    @Given("^an instance of Logical Interconnect Group$")
    public void an_instance_of_Logical_Interconnect_Group() throws Throwable {
        OneView.setResource(LogicalInterconnectGroupResource.getInstance());
    }

    @Given("^interconnection values as follows:$")
    public void interconnection_values_as_follows(List<Map<String, String>> values) throws Throwable {
        ((LogicalInterconnectGroupResource) OneView.getResource()).setInterconnectionValues(values);
    }

    @When("^OneView gets Default Interconnect Settings$")
    public void oneview_gets_Default_Interconnect_Settings() throws Throwable {
        resourceID = ((LogicalInterconnectGroupResource) OneView.getResource()).getDefaultInterconnectSettings();
        this.settingID = resourceID;
    }

    @When("^OneView gets Interconnect Settings$")
    public void oneview_gets_Interconnect_Settings() throws Throwable {
        resourceID = ((LogicalInterconnectGroupResource) OneView.getResource()).getInterconnectSettings(resourceID, this.settingID);
    }

    @Given("^Uplink values as follows:$")
    public void uplink_values_as_follows(List<Map<String, String>> map) throws Throwable {
        ((LogicalInterconnectGroupResource) OneView.getResource()).setUplinkValues(resourceName, map);
    }
    
    @When("^OneView runs Logical Interconnect Group Synergy creation$")
    public void oneview_runs_Logical_Interconnect_Group_Synergy_creation() throws Throwable {
        ((LogicalInterconnectGroupResource) OneView.getResource()).createSynergy();
    }

}
