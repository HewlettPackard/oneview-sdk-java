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

import com.hp.ov.sdk.network.LogicalSwitchResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LogicalSwitchBDDStep extends Background {

    @Given("^an instance of Logical Switch$")
    public void an_instance_of_Logical_Switch() throws Throwable {
        OneView.setResource(LogicalSwitchResource.getInstance());
    }

    @When("^OneView runs Logical Switch refresh$")
    public void oneview_runs_Logical_Switch_refresh() throws Throwable {
        status = ((LogicalSwitchResource) LogicalSwitchResource.getInstance()).refresh(resourceID);
    }
}
