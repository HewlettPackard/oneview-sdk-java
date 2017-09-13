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

package com.hp.ov.sdk.resources.activity;

import com.hp.ov.sdk.activity.ActivityAlertResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class AlertBDDStep extends Background {

    @Given("^an instance of Alert$")
    public void an_instance_of_Alert() throws Throwable {
        OneView.setResource(ActivityAlertResource.getInstance());
    }

    @When("^OneView gets Id of First Alert$")
    public void oneview_gets_Id_of_First_Alert() throws Throwable {
        resourceID = ActivityAlertResource.getInstance().getFirstAlertId();
    }

    @When("^OneView deletes Alert Change Log$")
    public void oneview_deletes_Alert_Change_Log() throws Throwable {
        status = ActivityAlertResource.getInstance().removeChangeLog();
    }

    @When("^OneView deletes Alert by filter$")
    public void oneview_deletes_Alert_by_filter() throws Throwable {
        status = ActivityAlertResource.getInstance().removeByFilter();
    }
}
