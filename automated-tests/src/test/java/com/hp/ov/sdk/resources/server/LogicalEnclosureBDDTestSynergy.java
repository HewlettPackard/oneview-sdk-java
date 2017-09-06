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

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * The appliances Synergy could two difference between multiple and one logical
 * enclosure A multiple logical enclosure put a tag calling @createMultiple
 * only. No need @create and it changes the names (logical enclosure group and
 * enclosure group) in resource values on feature. Create one logical enclosure
 * put a tag calling @createOne and it need all @create too.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        glue = { "com.hp.ov.sdk.resources" },
        features = "classpath:cucumber/server/logicalEnclosureSynergy.feature",
        monochrome = true,
        tags = { "@create, @createOne, @createMultiple, @getAll, @get, @patch, @refresh, @update, @remove",
                "~@disabled" })

public class LogicalEnclosureBDDTestSynergy {

}
