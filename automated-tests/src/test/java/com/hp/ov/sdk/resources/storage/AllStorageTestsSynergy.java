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

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
    format = { "pretty", "html:target/cucumber" }, 
    glue = {"com.hp.ov.sdk.resources" }, 
    features = {"classpath:cucumber/storage/driveEnclosure.feature",
            "classpath:cucumber/storage/fcSanDeviceManager.feature",
            "classpath:cucumber/storage/fcSanProvider.feature",
            "classpath:cucumber/storage/fcSansManagedSan.feature",
            "classpath:cucumber/storage/sasLogicalJbod.feature",
            "classpath:cucumber/storage/sasLogicalJbodAttachment.feature",
            "classpath:cucumber/storage/storagePool.feature",
            "classpath:cucumber/storage/storageSystem.feature",
            "classpath:cucumber/storage/storageVolume.feature",
            "classpath:cucumber/storage/storageVolumeAttachment.feature",
            "classpath:cucumber/storage/storageVolumeTemplate.feature"},
    tags = "@getAll")
public class AllStorageTestsSynergy {

}
