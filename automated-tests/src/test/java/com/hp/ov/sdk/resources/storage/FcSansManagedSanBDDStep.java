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
import com.hp.ov.sdk.storage.FcSansManagedSanResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class FcSansManagedSanBDDStep extends Background {

    @Given("^an instance of Fc Sans Managed San$")
    public void an_instance_of_Fc_Sans_Managed_San() throws Throwable {
        OneView.setResource(FcSansManagedSanResource.getInstance());
    }

    @When("^OneView gets Fc Sans Managed San Endpoints$")
    public void oneview_gets_Fc_Sans_Managed_San_Endpoints() throws Throwable {
        resourceStr = FcSansManagedSanResource.getInstance().getEndpoints(resourceID);
    }

    @When("^OneView gets Fc Sans Managed San Wwn Associations$")
    public void oneview_gets_Fc_Sans_Managed_San_Wwn_Associations() throws Throwable {
        count = FcSansManagedSanResource.getInstance().getWwnAssociations(resourceID);
    }

    @When("^OneView runs Fc Sans Managed San Endpoints Csv creation$")
    public void oneview_runs_Fc_Sans_Managed_San_Endpoints_Csv_creation() throws Throwable {
        resourceStr = FcSansManagedSanResource.getInstance().createEndpointsCsv(resourceID);
    }

    @When("^OneView runs Fc Sans Managed San Issues Report creation$")
    public void oneview_runs_Fc_Sans_Managed_San_Issues_Report_creation() throws Throwable {
        resourceStr = FcSansManagedSanResource.getInstance().createIssuesReport(resourceID);
    }

}
