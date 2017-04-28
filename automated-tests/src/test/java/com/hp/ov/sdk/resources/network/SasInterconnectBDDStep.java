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

import com.hp.ov.sdk.network.SasInterconnectResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class SasInterconnectBDDStep extends Background {

    @Given("^an instance of Sas Interconnect$")
    public void an_instance_of_Sas_Interconnect() throws Throwable {
        OneView.setResource(SasInterconnectResource.getInstance());
    }
    
    @When("^OneView runs Sas Interconnect patch$")
    public void oneview_runs_Sas_Interconnect_patch() throws Throwable {
        status = SasInterconnectResource.getInstance().patch(resourceID);
    }

    @When("^OneView runs Sas Interconnect refresh$")
    public void oneview_runs_Sas_Interconnect_refresh() throws Throwable {
        status = SasInterconnectResource.getInstance().refresh(resourceID);
    }
}
