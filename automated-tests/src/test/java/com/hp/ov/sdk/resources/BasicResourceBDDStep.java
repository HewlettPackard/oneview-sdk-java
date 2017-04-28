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

package com.hp.ov.sdk.resources;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.Credential;
import com.hp.ov.sdk.oneview.OneView;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BasicResourceBDDStep extends Background {

    @Given("^an instance of OneView$")
    public void an_instance_of_OneView() {
        oneView = OneView.getInstance();
    }

    @Given("^OneView credentials located in \"(.*?)\"$")
    public void oneview_credentials_located_in(String propertiesFile) {
        credential = Credential.getInstance();
        credential.readProperties(propertiesFile);
    }

    @When("^OneView lists all$")
    public void oneview_lists_all() {
        count = OneView.getResource().count();
    }

    @Then("^I get a count$")
    public void i_get_a_count() {
        assertTrue(count != -1);
    }

    @Given("^name \"(.*?)\" for Resource$")
    public void name_for_Resource(String name) {
        resourceName = name;
    }

    @Given("^Resource values as follows:$")
    public void resource_values_as_follows(Map<String, String> map) {
        inputProperties = map;
        resourceName = inputProperties.get("name");
        OneView.getResource().setResourceProperties(map);
    }

    @Given("^Resource values will be updated as follows:$")
    public void resource_values_will_be_updated_as_follows(Map<String, String> map) {
        inputProperties = map;
        OneView.getResource().setResourceProperties(map);
    }

    @When("^OneView runs Resource creation$")
    public void oneview_runs_Resource_creation() {
        ((CreateResource) OneView.getResource()).create();
    }

    @When("^OneView gets Resource by Name$")
    public void oneview_gets_Resource_by_Name() {
        resourceID = OneView.getResource().findByName(resourceName);
    }

    @Then("^I get an ID$")
    public void i_get_an_ID() {
        assertTrue(!resourceID.isEmpty());
    }

    @When("^OneView gets Resource by ID$")
    public void oneview_gets_Resource_by_ID() {
        resourceName = OneView.getResource().findById(resourceID);
    }

    @Then("^I get a Resource Name$")
    public void i_get_a_Resource_Name() {
        assertTrue(!resourceName.isEmpty());
    }

    @When("^OneView runs Resource update$")
    public void oneview_runs_Resource_update() {
        status = ((UpdateResource) OneView.getResource()).update(resourceID);
    }

    @When("^OneView deletes the Resource$")
    public void oneview_deletes_the_Resource() {
        status = ((RemoveResource) OneView.getResource()).remove(resourceID);
    }

    @Then("^Resource is not found$")
    public void resource_is_not_found() {
        assertTrue(resourceName.isEmpty());
    }

    @Then("^Resource is found$")
    public void resource_is_found() {
        assertTrue(!resourceStr.isEmpty());
    }

    @When("^OneView gets Resource properties$")
    public void oneview_gets_Resource_properties() {
        resourceProperties = OneView.getResource().getResourceProperties(resourceID);
    }

    @Then("^I get previous values in Resource$")
    public void i_get_previous_values_in_Resource() {
        for (String key : inputProperties.keySet()) {
            assertTrue(inputProperties.get(key).equals(resourceProperties.get(key)));
        }
    }

    @Then("^I get a success status$")
    public void i_get_a_success_status() throws Throwable {
        assertTrue(!status.isEmpty());
    }

}
