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
import com.hp.ov.sdk.storage.StoragePoolResource;
import com.hp.ov.sdk.storage.StorageSystemResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class StoragePoolBDDStep extends Background {

    @Given("^an instance of Storage Pool$")
    public void an_instance_of_Storage_Pool() throws Throwable {
        OneView.setResource(StoragePoolResource.getInstance());
    }

    @Given("^a Storage System Uri$")
    public void a_Storage_System_Uri() throws Throwable {
        OneView.setResource(StorageSystemResource.getInstance());
        systemUri = ((StorageSystemResource) OneView.getResource()).getUri();
    }

    @When("^StoragePool sets Uris$")
    public void storagepool_sets_Uris() throws Throwable {
        OneView.setResource(StoragePoolResource.getInstance());
        ((StoragePoolResource) OneView.getResource()).setStorageSystemUri(systemUri);
    }

}
