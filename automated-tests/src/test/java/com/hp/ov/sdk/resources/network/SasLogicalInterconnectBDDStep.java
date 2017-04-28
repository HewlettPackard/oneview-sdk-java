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

import com.hp.ov.sdk.network.SasLogicalInterconnectResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class SasLogicalInterconnectBDDStep extends Background {

    @Given("^an instance of Sas Logical Interconnect$")
    public void an_instance_of_Sas_Logical_Interconnect() throws Throwable {
        OneView.setResource(SasLogicalInterconnectResource.getInstance());
    }

    @When("^OneView gets Sas Logical Interconnect firmware$")
    public void oneview_gets_Sas_Logical_Interconnect_firmware() throws Throwable {
        resourceStr = SasLogicalInterconnectResource.getInstance().getFirmware(resourceID);
    }
    
    @When("^OneView runs replace Sas Logical Interconnect replace Drive Enclosure$")
    public void oneview_runs_replace_Sas_Logical_Interconnect_replace_Drive_Enclosure() throws Throwable {
        status = SasLogicalInterconnectResource.getInstance().replaceDriveEnclosure(resourceID);
    }

    @When("^OneView runs Sas Logical Interconnect apply configuration$")
    public void oneview_runs_Sas_Logical_Interconnect_apply_configuration() throws Throwable {
        status = SasLogicalInterconnectResource.getInstance().applyConfiguration(resourceID);
    }

    @When("^OneView runs Sas Logical Interconnect update compliance$")
    public void oneview_runs_Sas_Logical_Interconnect_update_compliance() throws Throwable {
        status = SasLogicalInterconnectResource.getInstance().updateCompliance(resourceID);
    }

    @When("^OneView runs Multiple Sas Logical Interconnect update compliance$")
    public void oneview_runs_Multiple_Sas_Logical_Interconnect_update_compliance() throws Throwable {
        status = SasLogicalInterconnectResource.getInstance().updateMultipleCompliance(resourceName);
    }

    @When("^OneView runs Sas Logical Interconnect update firmware$")
    public void oneview_runs_Sas_Logical_Interconnect_update_firmware() throws Throwable {
        status = SasLogicalInterconnectResource.getInstance().updateFirmware(resourceID);
    }

}
