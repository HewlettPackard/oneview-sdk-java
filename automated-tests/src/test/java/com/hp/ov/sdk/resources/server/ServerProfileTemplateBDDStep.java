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
import com.hp.ov.sdk.server.EnclosureGroupResource;
import com.hp.ov.sdk.server.ServerProfileTemplateResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class ServerProfileTemplateBDDStep extends Background {

    @Given("^an instance of Server Profile Template$")
    public void an_instance_of_Server_Profile_Template() throws Throwable {
        OneView.setResource(ServerProfileTemplateResource.getInstance());
    }

    @Given("^an Enclosure Group Uri$")
    public void an_Enclosure_Group_Uri() throws Throwable {
        enclosureUri = EnclosureGroupResource.getInstance().getUri(inputProperties.get("enclosureGroup"));
    }

    @When("^Server Profile Template sets Uris$")
    public void server_Profile_Template_sets_Uris() throws Throwable {
        ServerProfileTemplateResource.getInstance().setEnclosureUri(enclosureUri);
    }
    
    @When("^gets a Server Profile from Template$")
    public void gets_a_Server_Profile_from_Template() throws Throwable {
        resourceStr = ServerProfileTemplateResource.getInstance().getServerProfileFromTemplate(resourceID);
    }

}
