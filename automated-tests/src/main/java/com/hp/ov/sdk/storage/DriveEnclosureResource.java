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

package com.hp.ov.sdk.storage;

import java.util.Map;

import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosure;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosureRefreshRequest;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.storage.DriveEnclosureClient;

public class DriveEnclosureResource extends BasicResource implements Resource, UpdateResource {

    private static DriveEnclosureResource instance;

    private DriveEnclosureClient client;

    public DriveEnclosureResource() {
        client = oneViewClient.driveEnclosure();
    }

    public static DriveEnclosureResource getInstance() {
        if (instance == null) {
            instance = new DriveEnclosureResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        DriveEnclosure driveEnclosure = (DriveEnclosure) getResource(client.getByName(name));
        return driveEnclosure == null ? "" : driveEnclosure.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public String update(String id) {
        DriveEnclosureRefreshRequest refresh = new DriveEnclosureRefreshRequest();
        refresh.setRefreshState(RefreshState.valueOf(resourceProperties.get("refreshState")));
        return taskToString(client.updateRefreshState(id, refresh));
    }

    public String getPortMap(String id) {
        return objToString(client.getPortMap(id));
    }

    public String patch(String id) {
        Patch patch = new Patch();
        patch.setOp(PatchOperation.valueOf(resourceProperties.get("op")));
        patch.setPath(resourceProperties.get("path"));
        patch.setValue(resourceProperties.get("value"));

        return taskToString(client.patch(id, patch));
    }

}
