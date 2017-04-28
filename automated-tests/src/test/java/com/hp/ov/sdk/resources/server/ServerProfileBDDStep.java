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
import com.hp.ov.sdk.server.ServerProfileResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class ServerProfileBDDStep extends Background {

    @Given("^an instance of Server Profile$")
    public void an_instance_of_Server_Profile() {
        OneView.setResource(ServerProfileResource.getInstance());
    }

    @When("^Server Profile sets Uris$")
    public void server_Profile_sets_Uris() {
        ServerProfileResource.getInstance().setEnclosureUri(enclosureUri);
    }

    @When("^gets Available Servers For ServerProfile$")
    public void gets_Available_Servers_For_ServerProfile() {
        count = ServerProfileResource.getInstance().getAvailableServers();
    }

    @When("^gets Available Servers For ServerProfile using Profile$")
    public void gets_Available_Servers_For_ServerProfile_using_Profile() {
        count = ServerProfileResource.getInstance().getAvailableServersUsingProfile(resourceID);
    }

    @When("^gets ServerProfile Compliance Preview$")
    public void gets_ServerProfile_Compliance_Preview() {
        resourceStr = ServerProfileResource.getInstance().getCompliancePreview(resourceID);
    }

    @When("^gets ServerProfile Message$")
    public void gets_ServerProfile_Message() {
        resourceStr = ServerProfileResource.getInstance().getProfileMessages(resourceID);
    }

}
