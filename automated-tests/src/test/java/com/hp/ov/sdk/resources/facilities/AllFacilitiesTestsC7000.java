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

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
    format = { "pretty", "html:target/cucumber" }, 
    glue = { "com.hp.ov.sdk.resources" },
    features = { "classpath:cucumber/facilities/dataCenter.feature",
            "classpath:cucumber/facilities/powerDeliveryDeviceC7000.feature",
            "classpath:cucumber/facilities/rack.feature",
            "classpath:cucumber/facilities/unmanagedDevice.feature" },
    monochrome = true,
    tags = { "@create, @createByDiscover, @getAll, @get, @update, @updatePowerState, "
            + "@updateRefresh, @updateUid, @remove, @removeByFilter, @removeSynchronously",
            "~@disabled" })

public class AllFacilitiesTestsC7000 {

}
