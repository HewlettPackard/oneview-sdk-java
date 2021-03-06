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

package com.hp.ov.sdk.resources.storage;

import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;
import com.hp.ov.sdk.storage.FcSanDeviceManagerResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class FcSanDeviceManagerBDDStep extends Background {

    @Given("^an instance of Fc San Device Manager$")
    public void an_instance_of_Fc_San_Device_Manager() throws Throwable {
        OneView.setResource(FcSanDeviceManagerResource.getInstance());
    }

    @When("^OneView edit Fc San Device Manager Synergy$")
    public void oneview_edit_Fc_San_Device_Manager_Synergy() throws Throwable {
        status = FcSanDeviceManagerResource.getInstance().updateSynergy(resourceID);
    }
}
