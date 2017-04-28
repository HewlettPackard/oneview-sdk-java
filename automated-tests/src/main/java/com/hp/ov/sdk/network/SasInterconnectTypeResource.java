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

import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectType;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.networking.SasInterconnectTypeClient;

public class SasInterconnectTypeResource extends BasicResource implements Resource {

    private static SasInterconnectTypeResource instance;
    
    private SasInterconnectTypeClient client;
    
    public SasInterconnectTypeResource() {
        client = oneViewClient.sasInterconnectType();
    }

    public static SasInterconnectTypeResource getInstance() {
        if (instance == null) {
            instance = new SasInterconnectTypeResource();
        }
        return instance;
    }
    
    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        SasInterconnectType sasInterconnectType = (SasInterconnectType) getResource(client.getByName(name));
        return sasInterconnectType == null ? "" : sasInterconnectType.getResourceId();
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

}
