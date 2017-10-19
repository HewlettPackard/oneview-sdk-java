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

package com.hp.ov.sdk.resources.server;

import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;
import com.hp.ov.sdk.server.ServerHardwareResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class ServerHardwareBDDStep extends Background {

    @Given("^an instance of Server Hardware$")
    public void an_instance_of_Server_Hardware() throws Throwable {
        OneView.setResource(ServerHardwareResource.getInstance());
    }

    @When("^OneView runs Server Hardware Power State update$")
    public void oneview_runs_Server_Hardware_Power_State_update() throws Throwable {
        status = ((ServerHardwareResource) ServerHardwareResource.getInstance()).updatePowerState(resourceID);
    }

    @When("^OneView runs Server Hardware Refresh State update$")
    public void oneview_runs_Server_Hardware_Refresh_State_update() throws Throwable {
        status = ((ServerHardwareResource) ServerHardwareResource.getInstance()).updateRefreshState(resourceID);
    }

    @When("^OneView gets Server Hardware Environment Configuration$")
    public void oneview_gets_Server_Hardware_Environment_Configuration() throws Throwable {
        resourceStr = ((ServerHardwareResource) ServerHardwareResource.getInstance()).getEnvironmentConfiguration(resourceID);
    }
    
    @When("^OneView gets Server Hardware Ilo Sso Url$")
    public void oneview_gets_Server_Hardware_Ilo_Sso_Url() throws Throwable {
        resourceStr = ((ServerHardwareResource) ServerHardwareResource.getInstance()).getIloSsoUrl(resourceID);
    }

    @When("^OneView gets Server Hardware Java Remote Console Url$")
    public void oneview_gets_Server_Hardware_Java_Remote_Console_Url() throws Throwable {
        resourceStr = ((ServerHardwareResource) ServerHardwareResource.getInstance()).getJavaRemoteConsoleUrl(resourceID);
    }

    @When("^OneView gets Server Hardware Remote Console Url$")
    public void oneview_gets_Server_Hardware_Remote_Console_Url() throws Throwable {
        resourceStr = ((ServerHardwareResource) ServerHardwareResource.getInstance()).getRemoteConsoleUrl(resourceID);
    }

    @When("^OneView gets ServerHardware Utilization$")
    public void oneview_gets_ServerHardware_Utilization() throws Throwable {
        resourceStr = ((ServerHardwareResource) ServerHardwareResource.getInstance()).getUtilization(resourceID);
    }

    @When("^OneView gets Server Firmware Inventory By Filter$")
    public void oneview_gets_Server_Firmware_Inventory_By_Filter() throws Throwable {
        count = ((ServerHardwareResource) ServerHardwareResource.getInstance()).getFirmwareInventoryByFilter(resourceID);
    }

    @When("^OneView gets Server Firmware Inventory$")
    public void oneview_gets_Server_Firmware_Inventory() throws Throwable {
        resourceStr = ((ServerHardwareResource) ServerHardwareResource.getInstance()).getFirmwareInventory(resourceID);
    }
    
    @When("^OneView runs Environment Configuration update$")
    public void oneview_runs_Environment_Configuration_update() throws Throwable {
        resourceStr = ((ServerHardwareResource) ServerHardwareResource.getInstance()).updateEnvironmentConfiguration(resourceID);
    }
    
    @When("^OneView runs Mp Firmware Version update$")
    public void oneview_runs_Mp_Firmware_Version_update() throws Throwable {
        status = ((ServerHardwareResource) ServerHardwareResource.getInstance()).updateMpFirmwareVersion(resourceID);
    }

}
