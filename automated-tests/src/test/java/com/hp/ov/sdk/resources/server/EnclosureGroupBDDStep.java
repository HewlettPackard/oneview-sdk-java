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

import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasLogicalInterconnectGroup;
import com.hp.ov.sdk.network.LogicalInterconnectGroupResource;
import com.hp.ov.sdk.network.SasLogicalInterconnectGroupResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;
import com.hp.ov.sdk.server.EnclosureGroupResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class EnclosureGroupBDDStep extends Background {

    private String configurationScript;

    @Given("^an instance of Enclosure Groups$")
    public void an_instance_of_Enclosure_Groups() {
        OneView.setResource(EnclosureGroupResource.getInstance());
    }

    @Given("^a Configuration Script \"(.*?)\" for Enclosure Group$")
    public void a_Configuration_Script_for_Enclosure_Group(String configurationScript) throws Throwable {
        this.configurationScript = configurationScript;
    }

    @When("^Enclosure Group sets Uris$")
    public void enclosure_Group_sets_Uris() throws Throwable {
        if (inputProperties.containsKey("lig")) {
            LogicalInterconnectGroup lig = LogicalInterconnectGroupResource.getInstance().getByName(inputProperties.get("lig"));
            ((EnclosureGroupResource) OneView.getResource()).setLigUri(lig.getUri());
        }else if (inputProperties.containsKey("sasLig")) {
            SasLogicalInterconnectGroup sasLig = SasLogicalInterconnectGroupResource.getInstance().getByName(inputProperties.get("sasLig"));
            ((EnclosureGroupResource) OneView.getResource()).setLigUri(sasLig.getUri());
        }
    }

    @When("^gets Configuration Script of Enclosure Group$")
    public void gets_Configuration_Script_of_Enclosure_Group() throws Throwable {
        resourceStr = ((EnclosureGroupResource) OneView.getResource()).getConfigurationScript(resourceID);
    }

    @When("^Enclosure Group updates its Configuration Script$")
    public void enclosure_Group_updates_its_Configuration_Script() throws Throwable {
        ((EnclosureGroupResource) OneView.getResource()).updateConfigurationScript(resourceID, configurationScript);
    }

    @When("^OneView runs Enclosure Synergy creation$")
    public void oneview_runs_Enclosure_Synergy_creation() throws Throwable {
        ((EnclosureGroupResource) OneView.getResource()).createSynergy();
    }

}
