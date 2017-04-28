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

import com.hp.ov.sdk.network.SwitchesResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class SwitchBDDStep extends Background {

    @Given("^an instance of Switch$")
    public void an_instance_of_Switch() throws Throwable {
        OneView.setResource(SwitchesResource.getInstance());
    }

    @When("^OneView gets Switch Environmental Configuration$")
    public void oneview_gets_Switch_Environmental_Configuration() throws Throwable {
        resourceStr = ((SwitchesResource) SwitchesResource.getInstance()).getEnvironmentalConfiguration(resourceID);
    }

    @When("^OneView runs Switch refresh$")
    public void oneview_runs_Switch_refresh() throws Throwable {
        status = ((SwitchesResource) SwitchesResource.getInstance()).refresh(resourceID);
    }

    @When("^OneView gets Switch Statistics$")
    public void oneview_gets_Switch_Statistics() throws Throwable {
        resourceStr = ((SwitchesResource) SwitchesResource.getInstance()).getStatistics(resourceID);
    }

    @When("^OneView gets Switch Port Statistics$")
    public void oneview_gets_Switch_Port_Statistics() throws Throwable {
        resourceStr = ((SwitchesResource) SwitchesResource.getInstance()).getPortStatistics(resourceID);
    }

    @When("^OneView runsSwitch Port update$")
    public void oneview_runsSwitch_Port_update() throws Throwable {
        status = ((SwitchesResource) SwitchesResource.getInstance()).updatePorts(resourceID);
    }

}
