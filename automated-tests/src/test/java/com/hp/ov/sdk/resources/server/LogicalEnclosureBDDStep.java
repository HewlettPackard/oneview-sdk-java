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
import com.hp.ov.sdk.server.LogicalEnclosureResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LogicalEnclosureBDDStep extends Background {

    @Given("^an instance of Logical Enclosure$")
    public void an_instance_of_Logical_Enclosure() throws Throwable {
        OneView.setResource(LogicalEnclosureResource.getInstance());
    }

    @When("^OneView gets Logical Enclosure Configuration Script$")
    public void oneview_gets_Logical_Enclosure_Configuration_Script() throws Throwable {
        resourceStr = LogicalEnclosureResource.getInstance().getLogicalEnclosureConfigurationScript(resourceID);
    }

    @When("^OneView updates Logical Enclosure from Group$")
    public void oneview_updates_Logical_Enclosure_from_Group() throws Throwable {
        status = LogicalEnclosureResource.getInstance().updateLogicalEnclosureFromGroup(resourceID);
    }

    @When("^OneView updates Logical Enclosure Configuration$")
    public void oneview_updates_Logical_Enclosure_Configuration() throws Throwable {
        status = LogicalEnclosureResource.getInstance().updateLogicalEnclosureConfiguration(resourceID);
    }

    @When("^OneView updates Logical Enclosure Configuration Script$")
    public void oneview_updates_Logical_Enclosure_Configuration_Script() throws Throwable {
        status = LogicalEnclosureResource.getInstance().updateLogicalEnclosureConfigurationScript(resourceID);
    }

    @When("^OneView create a Logical Enclosure Support Dump$")
    public void oneview_create_a_Logical_Enclosure_Support_Dump() throws Throwable {
        status = LogicalEnclosureResource.getInstance().createSupportDump(resourceID);
    }

    @When("^OneView runs Logical Enclosure patch$")
    public void oneview_runs_Logical_Enclosure_patch() throws Throwable {
        status = LogicalEnclosureResource.getInstance().patch(resourceID);
    }

    @When("^OneView runs Multiple  enclosures creation$")
    public void oneview_runs_Multiple_enclosures_creation() throws Throwable {
        status = LogicalEnclosureResource.getInstance().createMultipleLogicalEnclosure(resourceID);
    }
}