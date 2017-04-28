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

import com.hp.ov.sdk.network.ConnectionTemplateResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class ConnectionTemplateBDDStep extends Background {

    @Given("^an instance of Connection Template$")
    public void an_instance_of_Connection_Template() throws Throwable {
        OneView.setResource(ConnectionTemplateResource.getInstance());
    }

    @When("^OneView gets Name of First Connection Template$")
    public void oneview_gets_Name_of_First_Connection_Template() throws Throwable {
        resourceName = ConnectionTemplateResource.getInstance().getFirstNameConnectionTemplate();
    }

    @When("^OneView gets Default Connection Template$")
    public void oneview_gets_Default_Connection_Template() throws Throwable {
        resourceStr = ConnectionTemplateResource.getInstance().getDefaultConnectionTemplate();
    }

}
