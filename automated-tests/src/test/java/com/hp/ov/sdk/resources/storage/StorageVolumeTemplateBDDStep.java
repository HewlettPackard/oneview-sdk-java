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
import com.hp.ov.sdk.storage.StorageVolumeTemplateResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class StorageVolumeTemplateBDDStep extends Background {

    @Given("^an instance of Storage Volume Template$")
    public void an_instance_of_Storage_Volume_Template() throws Throwable {
        OneView.setResource(StorageVolumeTemplateResource.getInstance());
    }

    @When("^Storage Volume Template sets Uris$")
    public void storage_Volume_Template_sets_Uris() throws Throwable {
        StorageVolumeTemplateResource.getInstance().setStorageSystemUri(systemUri);
        StorageVolumeTemplateResource.getInstance().setStoragePoolUri(poolUri);
        OneView.setResource(StorageVolumeTemplateResource.getInstance());
    }
    
    @When("^gets a Connectable Storage Volume Template$")
    public void gets_a_Connectable_Storage_Volume_Template() throws Throwable {
        count = StorageVolumeTemplateResource.getInstance().getConnectableVolumeTemplate();
    }
}
