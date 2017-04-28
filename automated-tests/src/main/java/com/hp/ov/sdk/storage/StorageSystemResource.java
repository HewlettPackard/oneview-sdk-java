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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.storage.StorageSystem;
import com.hp.ov.sdk.dto.storage.StorageTargetPort;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.network.FcNetworkResource;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.Credential;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.storage.StorageSystemClient;

public class StorageSystemResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static StorageSystemResource instance;

    private StorageSystemClient client;

    private static String storageName;
    private static String storageUri;

    private static String hostname;
    private static String username;
    private static String password;

    private StorageSystemResource() {
        category.put("V_300", "StorageSystemV3");
        category.put("V_200", "StorageSystemV3");
        client = oneViewClient.storageSystem();
        hostname = Credential.getInstance().getStorageSystemHostname();
        username = Credential.getInstance().getStorageSystemUsername();
        password = Credential.getInstance().getStorageSystemPassword();
    }

    public static StorageSystemResource getInstance() {
        if (instance == null) {
            instance = new StorageSystemResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    private StorageSystem getStorageSystem() {
        ResourceCollection<StorageSystem> storageCollection = client.getAll();
        for (StorageSystem storage : storageCollection.getMembers()) {
            if (storage.getCredentials().getIp_hostname().equals(hostname)) {
                return storage;
            }
        }
        return null;
    }

    public String getStorageSystemName() {
        if (storageName != null) {
            return storageName;
        } else {
            StorageSystem storage = getStorageSystem();
            if (storage != null) {
                storageName = storage.getName();
                return storageName;
            }
        }
        return "";
    }

    public String getUri() {
        if (storageUri != null) {
            return storageUri;
        } else {
            StorageSystem storage = getStorageSystem();
            if (storage != null) {
                storageUri = storage.getUri();
                return storage.getUri();
            }
        }
        return "";
    }

    @Override
    public String findByName(String name) {
        StorageSystem storageSystem = (StorageSystem) getResource(client.getByName(name));
        return storageSystem == null ? "" : storageSystem.getResourceId();
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
        try {
            client.add(builder());
        } finally {
            storageName = getStorageSystemName();
            storageUri = getUri();
        }
    }

    @Override
    public String update(String id) {
        StorageSystem storage = client.getById(id);
        return taskToString(client.update(id, buildUpdateStorageSystemDto(storage)));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.remove(id));
    }

    public int getAllManagedPorts(String id) {
        return getCount(client.getAllManagedPorts(id));
    }

    public String getManagedPort(String resourceID) {
        String strResource = "";
        ResourceCollection<StorageTargetPort> collection = client.getAllManagedPorts(resourceID);
        if (!collection.getMembers().isEmpty()) {
            strResource = client.getManagedPort(resourceID, collection.getMembers().get(0).getResourceId()).toString();
        }
        return strResource;
    }

    public int getHostTypes() {
        List<String> list = client.getHostTypes();
        return list != null ? list.size() : -1;
    }

    @Override
    public AddStorageSystemCredentials builder() {
        AddStorageSystemCredentials storage = new AddStorageSystemCredentials();
        storage.setIp_hostname(hostname);
        storage.setUsername(username);
        storage.setPassword(password);
        return storage;
    }

    private StorageSystem buildUpdateStorageSystemDto(StorageSystem storage) {
        LinkedList<String> fcNetworks = new LinkedList<String>(
                Arrays.asList(resourceProperties.get("fc-network").replace(" ", "").split(",")));
        LinkedList<String> ports = new LinkedList<String>(
                Arrays.asList(resourceProperties.get("port").replace(" ", "").split(",")));

        String domain = resourceProperties.get("domain");

        List<StorageTargetPort> tempStorageTargetPort = new ArrayList<>();
        List<StorageTargetPort> unMangedStorageTargetPort = new ArrayList<StorageTargetPort>();

        for (StorageTargetPort targetPort : storage.getUnmanagedPorts()) {
            for (String port : ports) {
                if (targetPort.getName().equalsIgnoreCase(port)) {
                    final StorageTargetPort managed_one = new StorageTargetPort();
                    managed_one.setActualNetworkUri(targetPort.getActualNetworkUri());
                    managed_one.setExpectedNetworkName(targetPort.getExpectedNetworkName());
                    managed_one.setGroupName(targetPort.getGroupName());
                    String fc_network_one = null;

                    String fcNetworkName = fcNetworks.poll();
                    fc_network_one = FcNetworkResource.getInstance().getByName(fcNetworkName).getUri();

                    managed_one.setExpectedNetworkUri(fc_network_one);
                    managed_one.setPortName(targetPort.getPortName());
                    managed_one.setPortWwn(targetPort.getPortWwn());
                    managed_one.setRefreshState(targetPort.getRefreshState());
                    managed_one.setStateReason(targetPort.getStateReason());
                    managed_one.setType(targetPort.getType());
                    managed_one.setName(targetPort.getPortName());
                    managed_one.setGroupName("Auto");
                    tempStorageTargetPort.add(managed_one);
                    unMangedStorageTargetPort.add(targetPort);
                }
            }
        }

        storage.setManagedPorts(tempStorageTargetPort);
        for (final StorageTargetPort storageTargetPort : unMangedStorageTargetPort) {
            storage.getUnmanagedPorts().remove(storageTargetPort);
        }
        storage.setManagedDomain(domain);
        List<String> unmanagedDomains = storage.getUnmanagedDomains();
        unmanagedDomains.remove(domain);
        storage.setUnmanagedDomains(unmanagedDomains);

        return storage;
    }
}
