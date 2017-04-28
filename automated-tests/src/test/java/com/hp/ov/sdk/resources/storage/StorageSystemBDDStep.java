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
import com.hp.ov.sdk.storage.StorageSystemResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class StorageSystemBDDStep extends Background {

    @Given("^an instance of Storage System$")
    public void an_instance_of_Storage_System() throws Throwable {
        OneView.setResource(StorageSystemResource.getInstance());
    }

    @When("^OneView gets Storage Name$")
    public void oneview_gets_Storage_Name() throws Throwable {
        resourceName = ((StorageSystemResource) OneView.getResource()).getStorageSystemName();
    }

    @When("^gets Storage System Managed Ports$")
    public void gets_Storage_System_Managed_Ports() throws Throwable {
        count = ((StorageSystemResource) OneView.getResource()).getAllManagedPorts(resourceID);
    }

    @When("^gets Storage System Managed Port$")
    public void gets_Storage_System_Managed_Port() throws Throwable {
        resourceStr = ((StorageSystemResource) OneView.getResource()).getManagedPort(resourceID);
    }

    @When("^gets Storage System Host Types$")
    public void gets_Storage_System_Host_Types() throws Throwable {
        count = ((StorageSystemResource) OneView.getResource()).getHostTypes();
    }
    
}
