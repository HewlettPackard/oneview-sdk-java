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
@CucumberOptions(
    format = { "pretty", "html:target/cucumber" }, 
    glue = { "com.hp.ov.sdk.resources" },
    features = { "classpath:cucumber/network/connectionTemplate.feature",
            "classpath:cucumber/network/ethernetNetwork.feature",
            "classpath:cucumber/network/fabric.feature",
            "classpath:cucumber/network/fcNetwork.feature",
            "classpath:cucumber/network/fcoeNetwork.feature",
            "classpath:cucumber/network/interconnectC7000.feature",
            "classpath:cucumber/network/interconnectTypeC7000.feature",
            "classpath:cucumber/network/internalLinkSet.feature",
            "classpath:cucumber/network/logicalDownlink.feature",
            "classpath:cucumber/network/logicalInterconnectC7000.feature",
            "classpath:cucumber/network/logicalInterconnectGroupC7000.feature",
            "classpath:cucumber/network/logicalSwitch.feature", // C7000 only
            "classpath:cucumber/network/logicalSwitchGroup.feature", //C7000 only
            "classpath:cucumber/network/networkSet.feature",
            "classpath:cucumber/network/switch.feature", // C7000 only
            "classpath:cucumber/network/switchType.feature",
            "classpath:cucumber/network/uplinkSetC7000.feature" },
    monochrome = true,
    tags = { "@getAll, @get, @create, @list, @update, @patch, @reset, @refresh, @remove, @onlyOV2, @onlyOV3, @C7000",
            "~@disabled", "~@synergy", "~@onlyOV1.2" })

public class AllNetworkTestsC7000 {

}
