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

import com.hp.ov.sdk.network.InterconnectResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class InterconnectBDDStep extends Background {

    @Given("^an instance of Interconnect$")
    public void an_instance_of_Interconnect() throws Throwable {
        OneView.setResource(InterconnectResource.getInstance());
    }

    @When("^OneView gets Interconnect Statistics$")
    public void oneview_gets_Interconnect_Statistics() throws Throwable {
        resourceStr = InterconnectResource.getInstance().getStatistics(resourceID);
    }

    @When("^OneView gets Interconnect Port Statistics$")
    public void oneview_gets_Interconnect_Port_Statistics() throws Throwable {
        resourceStr = InterconnectResource.getInstance().getPortStatistics(resourceID);
    }

    @When("^OneView gets Interconnect Sub Port Statistics$")
    public void oneview_gets_Interconnect_Sub_Port_Statistics() throws Throwable {
        resourceStr = InterconnectResource.getInstance().getSubPortStatistics(resourceID);
    }

    @When("^OneView gets Interconnect Named Servers$")
    public void oneview_gets_Interconnect_Named_Servers() throws Throwable {
        resourceStr = InterconnectResource.getInstance().getNamedServers(resourceID);
    }

    @When("^OneView runs Interconnect Port Protection reset$")
    public void oneview_runs_Interconnect_Port_Protection_reset() throws Throwable {
        status = InterconnectResource.getInstance().resetInterconnectPortProtection(resourceID);
    }

    @When("^OneView runs update interconnect port$")
    public void oneview_runs_update_interconnect_port() throws Throwable {
        status = InterconnectResource.getInstance().updateInterconnectPort(resourceID);
    }

    @When("^OneView runs update interconnect ports$")
    public void oneview_runs_update_interconnect_ports() throws Throwable {
        status = InterconnectResource.getInstance().updateInterconnectPorts(resourceID);
    }

}
