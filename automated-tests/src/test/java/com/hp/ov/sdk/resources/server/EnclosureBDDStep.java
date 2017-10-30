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
import com.hp.ov.sdk.server.EnclosureResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class EnclosureBDDStep extends Background {

    @Given("^an instance of Enclosure$")
    public void an_instance_of_Enclosure() throws Throwable {
        OneView.setResource(EnclosureResource.getInstance());
    }

    @When("^OneView gets Enclosure Name$")
    public void oneview_gets_Enclosure_Name() throws Throwable {
        resourceName = ((EnclosureResource) OneView.getResource()).getEnclosureName();
    }

    @When("^gets Enclosure Environmental Configuration$")
    public void gets_Enclosure_Environmental_Configuration() throws Throwable {
        resourceStr = ((EnclosureResource) OneView.getResource()).getEnvironmentalConfiguration(resourceID);
    }

    @When("^gets Enclosure Script Configuration$")
    public void gets_Enclosure_Script_Configuration() throws Throwable {
        resourceStr = ((EnclosureResource) OneView.getResource()).getScriptConfiguration(resourceID);
    }

    @When("^gets Enclosure Active Oa Sso Url$")
    public void gets_Enclosure_Active_Oa_Sso_Url() throws Throwable {
        resourceStr = ((EnclosureResource) OneView.getResource()).getActiveOaSsoUrl(resourceID);
    }

    @When("^gets Enclosure Utilization$")
    public void gets_Enclosure_Utilization() throws Throwable {
        resourceStr = ((EnclosureResource) OneView.getResource()).getEnclosurelUtilization(resourceID);
    }

    @When(value="^Oneview runs Enclosure refresh$", timeout=3600000)
    public void oneview_runs_Enclosure_refresh() throws Throwable {
        resourceStr = EnclosureResource.getInstance().refresh(resourceID);
    }

    @When("^OneView runs Remote Enclosure creation$")
    public void oneview_runs_Remote_Enclosure_creation() throws Throwable {
        EnclosureResource.getInstance().addRemoteEnclosure();
    }
}
