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
import com.hp.ov.sdk.server.ServerHardwareTypeResource;

import cucumber.api.java.en.Given;

public class ServerHardwareTypeBDDStep extends Background {

    @Given("^an instance of Server Hardware Type$")
    public void an_instance_of_Server_Hardware_Type() throws Throwable {
        OneView.setResource(ServerHardwareTypeResource.getInstance());
    }

}
