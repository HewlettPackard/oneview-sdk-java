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

import com.hp.ov.sdk.dto.networking.interconnectlinktopologies.InterconnectLinkTopology;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.networking.InterconnectLinkTopologyClient;

public class InterconnectLinkTopologyResource extends BasicResource implements Resource {

    private static InterconnectLinkTopologyResource instance;

    private InterconnectLinkTopologyClient client;

    public InterconnectLinkTopologyResource() {
        client = oneViewClient.interconnectLinkTopology();
    }

    public static InterconnectLinkTopologyResource getInstance() {
        if (instance == null) {
            instance = new InterconnectLinkTopologyResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        InterconnectLinkTopology interconnect = (InterconnectLinkTopology) getResource(client.getByName(name));
        return interconnect == null ? "" : interconnect.getResourceId();
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

    public String getNameFirstInterconnectLinkTopology() {
        InterconnectLinkTopology interconnectLinkTopology = (InterconnectLinkTopology) getResource(client.getAll());
        return interconnectLinkTopology == null ? "" : interconnectLinkTopology.getName();
    }
}
