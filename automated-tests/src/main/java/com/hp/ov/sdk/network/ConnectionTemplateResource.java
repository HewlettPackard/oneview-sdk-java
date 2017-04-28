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

import com.hp.ov.sdk.dto.networking.ethernet.Bandwidth;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.ConnectionTemplateClient;

public class ConnectionTemplateResource extends BasicResource implements Resource, UpdateResource {

    private static ConnectionTemplateResource instance;

    private ConnectionTemplateClient client;

    public ConnectionTemplateResource() {
        client = oneViewClient.connectionTemplate();
    }

    public static ConnectionTemplateResource getInstance() {
        if (instance == null) {
            instance = new ConnectionTemplateResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        ConnectionTemplate connection = (ConnectionTemplate) getResource(client.getByName(name));
        return connection == null ? "" : connection.getResourceId();
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

    public String getFirstNameConnectionTemplate() {
        ConnectionTemplate connection = (ConnectionTemplate) getResource(client.getAll());
        return connection != null ? connection.getName() : "";
    }

    public String getDefaultConnectionTemplate() {
        return objToString(client.getDefaultConnectionTemplate());
    }

    @Override
    public String update(String id) {
        ConnectionTemplate connection = client.getById(id);
        Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(Double.parseDouble(resourceProperties.get("maxBandwidth")));
        bandwidth.setTypicalBandwidth(Double.parseDouble(resourceProperties.get("typicalBandwidth")));

        connection.setBandwidth(bandwidth);
        connection.setETag(null);
        connection.setUri(null);
        
        return objToString(client.update(id, connection));
    }

}
