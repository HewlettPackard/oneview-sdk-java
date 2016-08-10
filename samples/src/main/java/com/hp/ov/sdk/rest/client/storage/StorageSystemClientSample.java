/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
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
package com.hp.ov.sdk.rest.client.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StorageSystem;
import com.hp.ov.sdk.dto.StorageTargetPort;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * StorageSystemClientSample is a sample program consume the storage server managed by HPE OneView.
 * It invokes APIs of StorageSystemClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on storage system resource
 */
public class StorageSystemClientSample {

    private final OneViewClient oneViewClient;
    private final StorageSystemClient storageSystemClient;

    // These are variables to be defined by user
    // ================================
    public static final String STORAGE_SYSTEM_NAME = "ThreePAR7200-4555";

    private static final String STORAGE_SYSTEM_RESOURCE_ID = "TXQ1010307";
    private static final String TARGET_PORT_ID = "2788DF7C-23BE-4E42-B4EB-61C59246AEE7";
    private static final String USERNAME = "dcs";
    private static final String PASSWORD = "dcs";
    private static final String IP_ADDRESS = "172.18.11.12";
    private static final List<String> FC_NETWORK_A = Arrays.asList("FC_Network_A");
    private static final List<String> FC_NETWORK_B = Arrays.asList("FC_Network_B");
    private static final String UNMANAGED_PORT_A = "0:1:1";
    private static final String UNMANAGED_PORT_B = "0:1:2";
    private static final String MANAGED_DOMAIN = "TestDomain";
    private static final String UNMANAGED_DOMAIN = "TestDomain";
    // ================================

    private StorageSystemClientSample() {
        this.oneViewClient = OneViewClientSample.getOneViewClient();
        this.storageSystemClient = oneViewClient.storageSystem();
    }

    private void getStorageSystem() {
        StorageSystem storageSystem = this.storageSystemClient.getById(STORAGE_SYSTEM_RESOURCE_ID);

        System.out.println("StorageSystemClientSample : getStorageSystem : " +
                "StorageSystem object returned to client : " + storageSystem.toJsonString());
    }

    private void getAllStorageSystems() {
        ResourceCollection<StorageSystem> storageSystems = this.storageSystemClient.getAll();

        System.out.println("StorageSystemClientSample : getAllStorageSystems : " +
                "StorageSystems returned to client : " + storageSystems.toJsonString());
    }

    private void getStorageSystemByName() {
        StorageSystem storageSystem = this.storageSystemClient.getByName(STORAGE_SYSTEM_NAME).get(0);

        System.out.println("StorageSystemClientSample : getStorageSystemByName : " +
                "StorageSystem object returned to client : " + storageSystem.toJsonString());
    }

    private void addStorageSystem() {
        AddStorageSystemCredentials addStorageSystemCredentials = buildStorageSystem();

        TaskResourceV2 taskResource = storageSystemClient.add(addStorageSystemCredentials, false);

        System.out.println("StorageSystemClientSample : addStorageSystem : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateStorageSystem() {
        StorageSystem storageSystem = this.storageSystemClient.getByName(STORAGE_SYSTEM_NAME).get(0);
        StorageSystem updatedStorageSystem = buildUpdateStorageSystem(storageSystem);

        TaskResourceV2 taskResource = storageSystemClient.update(storageSystem.getResourceId(),
                updatedStorageSystem, false);

        System.out.println("StorageSystemClientSample : updateStorageSystem : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void removeStorageSystem() {
        StorageSystem storageSystem = this.storageSystemClient.getByName(STORAGE_SYSTEM_NAME).get(0);
        TaskResourceV2 taskResource = this.storageSystemClient.remove(storageSystem.getResourceId(), false);

        System.out.println("StorageSystemClientSample : removeStorageSystem : " +
                "Task object returned to client : " + taskResource);
    }

    private void getStorageSystemStoragePools() {
        StorageSystem storageSystem = this.storageSystemClient.getByName(STORAGE_SYSTEM_NAME).get(0);
        ResourceCollection<StoragePool> storagePools = this.storageSystemClient.getStoragePools(
                storageSystem.getResourceId());

        System.out.println("StorageSystemClientSample : getStorageSystemStoragePools : " +
                "StoragePools returned to client : " + storagePools.toJsonString());
    }

    private void getStorageSystemManagedPorts() {
        StorageSystem storageSystem = this.storageSystemClient.getByName(STORAGE_SYSTEM_NAME).get(0);
        ResourceCollection<StorageTargetPort> storageTargetPorts
                = this.storageSystemClient.getAllManagedPorts(storageSystem.getResourceId());

        System.out.println("StorageSystemClientSample : getStorageSystemManagedPorts : " +
                "StoragePools returned to client : " + storageTargetPorts.toJsonString());
    }

    private void getStorageSystemManagedPort() {
        StorageSystem storageSystem = this.storageSystemClient.getByName(STORAGE_SYSTEM_NAME).get(0);

        StorageTargetPort storageTargetPort = storageSystemClient.getManagedPort(storageSystem.getResourceId(),
                TARGET_PORT_ID);

        System.out.println("StorageSystemClientSample : getStorageSystemManagedPort : " +
                "StorageTargetPort object returned to client : " + storageTargetPort.toJsonString());
    }

    private void getStorageSystemHostTypes() {
        List<String> hostTypes = storageSystemClient.getHostTypes();

        System.out.println("StorageSystemClientSample : getStorageSystemHostTypes : " +
                "Host types returned to client : " + Arrays.toString(hostTypes.toArray()));
    }

    private AddStorageSystemCredentials buildStorageSystem() {
        AddStorageSystemCredentials credentials = new AddStorageSystemCredentials();

        credentials.setIp_hostname(IP_ADDRESS);
        credentials.setUsername(USERNAME);
        credentials.setPassword(PASSWORD);

        return credentials;
    }

    private StorageSystem buildUpdateStorageSystem(StorageSystem storageSystemDto) {
        List<StorageTargetPort> tempStorageTargetPort = new ArrayList<>();
        List<StorageTargetPort> unMangedStorageTargetPort = new ArrayList<>();

        for (int i = 0; i < storageSystemDto.getUnmanagedPorts().size(); i++) {
            if (storageSystemDto.getUnmanagedPorts().get(i).getName().equalsIgnoreCase(UNMANAGED_PORT_A)) {
                StorageTargetPort managed_one = new StorageTargetPort();
                managed_one.setActualNetworkUri(storageSystemDto.getUnmanagedPorts().get(i).getActualNetworkUri());
                managed_one.setExpectedNetworkName(storageSystemDto.getUnmanagedPorts().get(i).getExpectedNetworkName());
                managed_one.setGroupName(storageSystemDto.getUnmanagedPorts().get(i).getGroupName());
                String fc_network_one = null;
                for (int j = 0; j < FC_NETWORK_A.size(); j++) {
                    fc_network_one = oneViewClient.fcNetwork().getByName(FC_NETWORK_A.get(j)).get(0).getUri();
                }
                managed_one.setExpectedNetworkUri(fc_network_one);
                managed_one.setPortName(storageSystemDto.getUnmanagedPorts().get(i).getPortName());
                managed_one.setPortWwn(storageSystemDto.getUnmanagedPorts().get(i).getPortWwn());
                managed_one.setRefreshState(storageSystemDto.getUnmanagedPorts().get(i).getRefreshState());
                managed_one.setStateReason(storageSystemDto.getUnmanagedPorts().get(i).getStateReason());
                managed_one.setType(storageSystemDto.getUnmanagedPorts().get(i).getType());
                managed_one.setName(storageSystemDto.getUnmanagedPorts().get(i).getPortName());
                managed_one.setGroupName("Auto");
                tempStorageTargetPort.add(managed_one);
                unMangedStorageTargetPort.add(storageSystemDto.getUnmanagedPorts().get(i));

            } else if (storageSystemDto.getUnmanagedPorts().get(i).getName().equalsIgnoreCase(UNMANAGED_PORT_B)) {
                StorageTargetPort managed_two = new StorageTargetPort();
                managed_two.setActualNetworkUri(storageSystemDto.getUnmanagedPorts().get(i).getActualNetworkUri());
                managed_two.setExpectedNetworkName(storageSystemDto.getUnmanagedPorts().get(i).getExpectedNetworkName());
                managed_two.setGroupName(storageSystemDto.getUnmanagedPorts().get(i).getGroupName());
                String fc_network_two = null;
                for (int j = 0; j < FC_NETWORK_B.size(); j++) {
                    fc_network_two = oneViewClient.fcNetwork().getByName(FC_NETWORK_B.get(j)).get(0).getUri();
                }
                managed_two.setExpectedNetworkUri(fc_network_two);
                managed_two.setPortName(storageSystemDto.getUnmanagedPorts().get(i).getPortName());
                managed_two.setPortWwn(storageSystemDto.getUnmanagedPorts().get(i).getPortWwn());
                managed_two.setRefreshState(storageSystemDto.getUnmanagedPorts().get(i).getRefreshState());
                managed_two.setStateReason(storageSystemDto.getUnmanagedPorts().get(i).getStateReason());
                managed_two.setType(storageSystemDto.getUnmanagedPorts().get(i).getType());
                managed_two.setName(storageSystemDto.getUnmanagedPorts().get(i).getPortName());
                managed_two.setGroupName("Auto");
                tempStorageTargetPort.add(managed_two);
                unMangedStorageTargetPort.add(storageSystemDto.getUnmanagedPorts().get(i));
            }
        }

        storageSystemDto.setManagedPorts(tempStorageTargetPort);
        for (StorageTargetPort storageTargetPort : unMangedStorageTargetPort) {
            storageSystemDto.getUnmanagedPorts().remove(storageTargetPort);
        }
        storageSystemDto.setManagedDomain(MANAGED_DOMAIN);
        List<String> unmanagedDomains = storageSystemDto.getUnmanagedDomains();
        unmanagedDomains.remove(UNMANAGED_DOMAIN);
        storageSystemDto.setUnmanagedDomains(unmanagedDomains);

        return storageSystemDto;
    }

    public static void main(final String[] args) throws Exception {
        StorageSystemClientSample sample = new StorageSystemClientSample();

        sample.getStorageSystem();
        sample.addStorageSystem();
        sample.getAllStorageSystems();
        sample.getStorageSystemByName();
        sample.getStorageSystemManagedPorts();
        sample.getStorageSystemManagedPort();
        sample.getStorageSystemStoragePools();
        sample.getStorageSystemHostTypes();
        sample.updateStorageSystem();
        sample.removeStorageSystem();
    }

}
