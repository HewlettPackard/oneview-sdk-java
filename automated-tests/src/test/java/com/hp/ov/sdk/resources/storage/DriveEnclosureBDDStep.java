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
import com.hp.ov.sdk.storage.DriveEnclosureResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class DriveEnclosureBDDStep extends Background {

    @Given("^an instance of Drive Enclosure$")
    public void an_instance_of_Drive_Enclosure() throws Throwable {
        OneView.setResource(DriveEnclosureResource.getInstance());
    }

    @When("^OneView gets Port Map of Drive Enclosure$")
    public void oneview_gets_Port_Map_of_Drive_Enclosure() throws Throwable {
        resourceStr = DriveEnclosureResource.getInstance().getPortMap(resourceID);
    }

}
