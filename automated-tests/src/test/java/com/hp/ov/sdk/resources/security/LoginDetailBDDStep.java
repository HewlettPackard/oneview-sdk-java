/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.resources.security;

import com.hp.ov.sdk.dto.security.login.LoginDetail;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.security.LoginDetailResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LoginDetailBDDStep extends Background {

    public static LoginDetail loginDetail;
    public LoginDetailResource resource;

    @Given("^an instance of Login Detail$")
    public void an_instance_of_Login_Detail() throws Throwable {
        resource = LoginDetailResource.getInstance();
    }

    @When("^OneView gets Login Details$")
    public void gets_Login_Details() throws Throwable {
        resourceStr = resource.getLoginDetail();
    }

}
