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

import com.hp.ov.sdk.facilities.PowerDeliveryDeviceResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class PowerDeliveryDeviceBDDStep extends Background {

    @Given("^an instance of Power Delivery Device$")
    public void an_instance_of_Power_Delivery_Device() throws Throwable {
        OneView.setResource(PowerDeliveryDeviceResource.getInstance());
    }

    @When("^OneView runs creation of Power Delivery Device by Discover$")
    public void oneview_runs_creation_of_Power_Delivery_Device_by_Discover() throws Throwable {
        status = PowerDeliveryDeviceResource.getInstance().createByDiscover();
    }

    @When("^OneView gets a Power State of Power Delivery Device$")
    public void oneview_gets_a_Power_State_of_Power_Delivery_Device() throws Throwable {
        resourceStr = PowerDeliveryDeviceResource.getInstance().getPowerState(resourceID);
    }

    @When("^OneView gets an Uid State of Power Delivery Device$")
    public void oneview_gets_an_Uid_State_of_Power_Delivery_Device() throws Throwable {
        resourceStr = PowerDeliveryDeviceResource.getInstance().getUidState(resourceID);
    }

    @When("^OneView gets Power Delivery Device Utilization$")
    public void oneview_gets_Power_Delivery_Device_Utilization() throws Throwable {
        resourceStr = PowerDeliveryDeviceResource.getInstance().getUtilization(resourceID);
    }

    @When("^OneView runs update Power State$")
    public void oneview_runs_update_Power_State() throws Throwable {
        status = PowerDeliveryDeviceResource.getInstance().updatePowerState(resourceID);
    }

    @When("^OneView runs update Refresh State$")
    public void oneview_runs_update_Refresh_State() throws Throwable {
        status = PowerDeliveryDeviceResource.getInstance().updateRefreshState(resourceID);
    }

    @When("^OneView runs update Uid State$")
    public void oneview_runs_update_Uid_State() throws Throwable {
        status = PowerDeliveryDeviceResource.getInstance().updateUidState(resourceID);
    }
    
    @When("^OneView deletes Power Delivery Device by Filter$")
    public void oneview_deletes_Power_Delivery_Device_by_Filter() throws Throwable {
        status = PowerDeliveryDeviceResource.getInstance().removeByFilter();
    }
    
    @When("^OneView deletes Power Delivery Device Synchronously$")
    public void oneview_deletes_Power_Delivery_Device_Synchronously() throws Throwable {
        status = PowerDeliveryDeviceResource.getInstance().removeSynchronously(resourceID);
    }

}
