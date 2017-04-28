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

package com.hp.ov.sdk.resources.facilities;

import com.hp.ov.sdk.facilities.DataCenterResource;
import com.hp.ov.sdk.oneview.Background;
import com.hp.ov.sdk.oneview.OneView;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class DataCenterBDDStep extends Background {

    @Given("^an instance of Data Center$")
    public void an_instance_of_Data_Center() throws Throwable {
        OneView.setResource(DataCenterResource.getInstance());
    }

    @When("^OneView gets Data Center Visual Content$")
    public void oneview_gets_Data_Center_Visual_Content() throws Throwable {
        resourceStr = DataCenterResource.getInstance().getDataCenterVisualContent(resourceID);
    }
    
    @When("^OneView deletes Data Center by Filter$")
    public void oneview_deletes_Data_Center_by_Filter() throws Throwable {
        status = DataCenterResource.getInstance().removeByFilter();
    }
}
