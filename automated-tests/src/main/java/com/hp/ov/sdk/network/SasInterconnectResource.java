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

package com.hp.ov.sdk.network;

import java.util.Map;

import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterConnectRefreshRequest;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnect;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.networking.SasInterconnectClient;

public class SasInterconnectResource extends BasicResource implements Resource {

    private static SasInterconnectResource instance;

    private SasInterconnectClient client;

    public SasInterconnectResource() {
        client = oneViewClient.sasInterconnects();
    }

    public static SasInterconnectResource getInstance() {
        if (instance == null) {
            instance = new SasInterconnectResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        SasInterconnect sasInterconnect = (SasInterconnect) getResource(client.getByName(name));
        return sasInterconnect == null ? "" : sasInterconnect.getResourceId();
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

    public String refresh(String id) {
        SasInterConnectRefreshRequest refresh = new SasInterConnectRefreshRequest();
        refresh.setRefreshState(RefreshState.RefreshPending);
        return taskToString(client.refreshState(id, refresh));
    }

    public String patch(String id) {
        Patch patch = new Patch();
        patch.setOp(PatchOperation.valueOf(resourceProperties.get("op")));
        patch.setPath(resourceProperties.get("path"));
        patch.setValue(resourceProperties.get("value"));

        return taskToString(client.patch(id, patch));
    }

}
