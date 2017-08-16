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

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber" },
    glue = {"com.hp.ov.sdk.resources" },
    features = "classpath:cucumber/network/logicalInterconnectSynergy.feature",
    monochrome = true,
    tags = {"@create, @createOne, @createMultiple, @getAll, @get, @update, @remove",
            "~@disabled"})

public class LogicalInterconnectBDDTestSynergy {
    /** NOTE:
    If your appliance has 1 only enclosure, then add @disabled to scenario @createMultiple
    and remove it from @createOne in the .feature file */
}
