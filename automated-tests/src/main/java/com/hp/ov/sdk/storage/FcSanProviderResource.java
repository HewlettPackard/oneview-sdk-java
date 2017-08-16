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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.fcsans.DeviceManagerResponse;
import com.hp.ov.sdk.dto.fcsans.SanProviderResponse;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.rest.client.storage.FcSanProviderClient;

public class FcSanProviderResource extends BasicResource implements CreateResource {

    private static FcSanProviderResource instance;

    private FcSanProviderClient client;

    public FcSanProviderResource() {
        client = oneViewClient.fcSanProvider();
    }

    public static FcSanProviderResource getInstance() {
        if (instance == null) {
            instance = new FcSanProviderResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        SanProviderResponse provider = (SanProviderResponse) getResource(client.getByName(name));
        return provider == null ? "" : provider.getResourceId();
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
    public void create() {
        SanProviderResponse sanProvider = (SanProviderResponse) getResource(client.getByName(resourceProperties.get("provider")));
        client.addSanManager(sanProvider.getResourceId(), builder());
    }

    public void createSynergy() {      
        ResourceCollection<SanProviderResponse> sanProviders = client.getAll();
                
        for(SanProviderResponse sanProvider : sanProviders.getMembers())
        {
            if (sanProvider.getName().equals(resourceProperties.get("type"))) {
                client.addSanManager(sanProvider.getResourceId(), builderSynergy());
            }
        }        
    }

    @Override
    public DeviceManagerResponse builder() {
        SanProviderResponse sanProvider = (SanProviderResponse) getResource(client.getByName(resourceProperties.get("provider")));

        DeviceManagerResponse deviceManagerResponseDto = new DeviceManagerResponse();
        List<Property> connectionInfo = new ArrayList<>();
        Object portValue = "";
        for (Property property : sanProvider.getDefaultConnectionInfo()) {
            if (property.getDisplayName().contains("Port")) {
                portValue = property.getValue();
            }
        }

        Property host = new Property();
        host.setName(resourceProperties.get("hostname"));
        host.setValue(resourceProperties.get("name"));

        Property port = new Property();
        port.setName(resourceProperties.get("port"));
        port.setValue(portValue);

        Property user = new Property();
        user.setName("Username");
        user.setValue(resourceProperties.get("user"));

        Property password = new Property();
        password.setName("Password");
        password.setValue(resourceProperties.get("password"));

        Property useSsl = new Property();
        useSsl.setName(resourceProperties.get("use_ssl"));
        useSsl.setValue(resourceProperties.get("use_ssl_value"));

        connectionInfo.add(host);
        connectionInfo.add(port);
        connectionInfo.add(user);
        connectionInfo.add(password);
        connectionInfo.add(useSsl);

        deviceManagerResponseDto.setConnectionInfo(connectionInfo);

        return deviceManagerResponseDto;
    }
    public DeviceManagerResponse builderSynergy() {

        DeviceManagerResponse deviceManagerResponseDtoSynergy = new DeviceManagerResponse();
        List<Property> connectionInfo = new ArrayList<>();

        Property host = new Property();
        
        host.setName("Host");
        host.setValue(resourceProperties.get("name"));

        Property port = new Property();
        port.setName("SnmpPort");
        port.setValue(Integer.valueOf(resourceProperties.get("snmpPort")));

        Property user = new Property();
        user.setName("snmpUserName");
        user.setValue(resourceProperties.get("snmpUserName"));

        Property authLevel = new Property();
        authLevel.setName("snmpAuthLevel");
        authLevel.setValue(resourceProperties.get("securityLevel"));

        Property authProtocol = new Property();
        authProtocol.setName("snmpAuthProtocol");
        authProtocol.setValue(resourceProperties.get("authProtocol"));

        Property password = new Property();
        password.setName("snmpAuthString");
        password.setValue(resourceProperties.get("password"));

        connectionInfo.add(host);
        connectionInfo.add(port);
        connectionInfo.add(user);
        connectionInfo.add(authLevel);
        connectionInfo.add(authProtocol);
        connectionInfo.add(password);

        deviceManagerResponseDtoSynergy.setConnectionInfo(connectionInfo);

        return deviceManagerResponseDtoSynergy;
    }
}
