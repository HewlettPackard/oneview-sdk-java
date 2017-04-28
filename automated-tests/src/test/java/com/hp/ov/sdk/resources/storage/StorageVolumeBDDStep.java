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
import com.hp.ov.sdk.storage.StorageVolumeResource;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class StorageVolumeBDDStep extends Background {

    @Given("^an instance of Storage volume$")
    public void an_instance_of_Storage_volume() {
        OneView.setResource(StorageVolumeResource.getInstance());
    }

    @Given("^a Storage Pool Uri$")
    public void a_Storage_Pool_Uri() throws Throwable {
        OneView.setResource(StoragePoolResource.getInstance());
        poolUri = ((StoragePoolResource) OneView.getResource()).getUri();
    }

    @When("^StorageVolume sets Uris$")
    public void storagevolume_sets_Uris() throws Throwable {
        OneView.setResource(StorageVolumeResource.getInstance());
        ((StorageVolumeResource) OneView.getResource()).setStorageSystemUri(systemUri);
        ((StorageVolumeResource) OneView.getResource()).setStoragePoolUri(poolUri);
    }

    @When("^gets Attachable Volumes$")
    public void gets_Attachable_Volumes() throws Throwable {
        count = ((StorageVolumeResource) OneView.getResource()).getAttachableVolumes();
    }

    @When("^gets All Storage Volume Snapshots$")
    public void gets_All_Storage_Volume_Snapshots() throws Throwable {
        count = ((StorageVolumeResource) OneView.getResource()).getAllStorageVolumeSnapshots(resourceID);
    }

    @When("^gets Storage Volume Snapshots$")
    public void gets_Storage_Volume_Snapshots() throws Throwable {
        resourceStr = ((StorageVolumeResource) OneView.getResource()).getStorageVolumeSnapshots(resourceID);
    }

    @When("^gets Extra Managed Storage Volume Paths$")
    public void gets_Extra_Managed_Storage_Volume_Paths() throws Throwable {
        count = ((StorageVolumeResource) OneView.getResource()).getExtraManagedStorageVolumePaths();
    }

    @When("^Storage Volume runs a snapshot$")
    public void storage_Volume_runs_a_snapshot() throws Throwable {
        ((StorageVolumeResource) OneView.getResource()).createSnapshot();
    }

    @When("^Storage Volume deletes a snapshot$")
    public void storage_Volume_deletes_a_snapshot() throws Throwable {
        count = ((StorageVolumeResource) OneView.getResource()).deleteSnapshot(resourceID);
    }

    @When("^Storage Volume repair an Extra Managed$")
    public void storage_Volume_repair_an_Extra_Managed() throws Throwable {
        count = ((StorageVolumeResource) OneView.getResource()).repairExtraManagedStorageVolumePath(resourceID);
    }

}
