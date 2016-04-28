/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.dto.StorageSystemCollection;
import com.hp.ov.sdk.dto.StorageSystemV2;
import com.hp.ov.sdk.dto.StorageTargetPort;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.StorageTargetPortV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.FcNetworkClient;
import com.hp.ov.sdk.rest.client.FcNetworkClientImpl;
import com.hp.ov.sdk.rest.client.StorageSystemClient;
import com.hp.ov.sdk.rest.client.StorageSystemClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * StorageSystemClientSample is a sample program consume the storage server managed by HP OneView.
 * It invokes APIs of StorageSystemClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on storage system resource
 */
public class StorageSystemClientSample {

    private final StorageSystemClient storageSystemClient;
    private final FcNetworkClient fcNetworkClient;

    private RestParams params;

    // These are variables to be defined by user
    // ================================
    private static final String targetPortId = "2788DF7C-23BE-4E42-B4EB-61C59246AEE7";
    private static final String resourceName = "ThreePAR7200-4555";
    private static final String username = "dcs";
    private static final String password = "dcs";
    private static final String ipAddress = "172.18.11.12";
    private static final List<String> fcNetworkName_A = Arrays.asList("FC_Network_A");
    private static final List<String> fcNetworkName_B = Arrays.asList("FC_Network_B");
    private static final String resourceId = "TXQ1010307";
    private static final String unManagedPort_A = "0:1:1";
    private static final String unManagedPort_B = "0:1:2";
    private static final String managedDomain = "TestDomain";
    private static final String unManagedDomain = "TestDomain";
    // ================================

    private StorageSystemClientSample() {
        this.storageSystemClient = StorageSystemClientImpl.getClient();
        this.fcNetworkClient = FcNetworkClientImpl.getClient();
    }

    private void getStorageSystemById() throws InstantiationException, IllegalAccessException {
        StorageSystemV2 storageSystemDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageSystemDto = storageSystemClient.getStorageSystem(params, resourceId);

            System.out.println("StorageSystemClientTest : getStorageSystemById : storageSystem object returned to client : "
                    + storageSystemDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemById : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemById : no such url : " + params.getUrl());
            return;

        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : getStorageSystemById : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemById : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemById : arguments are null ");
            return;
        }

    }

    private void getStoragePoolsForStorageSystem() throws InstantiationException, IllegalAccessException,
            SDKResourceNotFoundException, SDKNoSuchUrlException {
        String resourceId = null;
        StoragePoolCollection storagePoolCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = storageSystemClient.getId(params, resourceName);

            // then make sdk service call to get resource
            storagePoolCollectionDto = storageSystemClient.getStoragePoolsForStorageSystem(params, resourceId);

            System.out
                    .println("StorageSystemClientTest : getStoragePoolsForStorageSystem : storageSystem object returned to client : "
                            + storagePoolCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : getStoragePoolsForStorageSystem : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : getStoragePoolsForStorageSystem : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : getStoragePoolsForStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : getStoragePoolsForStorageSystem : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : getStoragePoolsForStorageSystem : arguments are null ");
            return;
        }
    }

    private void getAllManagedPortsForStorageSystem() throws InstantiationException, IllegalAccessException,
            SDKResourceNotFoundException, SDKNoSuchUrlException {
        String resourceId = null;
        StorageTargetPortCollection storageTargetPortCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = storageSystemClient.getId(params, resourceName);

            // then make sdk service call to get resource
            storageTargetPortCollectionDto = storageSystemClient.getAllManagedPortsForStorageSystem(params, resourceId);

            System.out
                    .println("StorageSystemClientTest : getAllManagedPortsForStorageSystem : storageSystem object returned to client : "
                            + storageTargetPortCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("StorageSystemClientTest : getAllManagedPortsForStorageSystem : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : getAllManagedPortsForStorageSystem : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : getAllManagedPortsForStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : getAllManagedPortsForStorageSystem : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : getAllManagedPortsForStorageSystem : arguments are null ");
            return;
        }
    }

    private void getManagedPortsForStorageSystem() throws InstantiationException, IllegalAccessException,
            SDKResourceNotFoundException, SDKNoSuchUrlException {
        String resourceId = null;
        StorageTargetPortV2 storageTargetPortDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = storageSystemClient.getId(params, resourceName);

            // then make sdk service call to get resource
            storageTargetPortDto = storageSystemClient.getManagedPortsForStorageSystem(params, resourceId, targetPortId);

            System.out
                    .println("StorageSystemClientTest : getManagedPortsForStorageSystem : storageSystem object returned to client : "
                            + storageTargetPortDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : getManagedPortsForStorageSystem : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : getManagedPortsForStorageSystem : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : getManagedPortsForStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : getManagedPortsForStorageSystem : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : getManagedPortsForStorageSystem : arguments are null ");
            return;
        }
    }

    private void getStorageSystemHostTypes()
            throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException, SDKNoSuchUrlException {
        List<String> hostTypes = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            hostTypes = storageSystemClient.getStorageSystemHostTypes(params);

            System.out.println(
                    "StorageSystemClientTest : getStorageSystemHostTypes : list object returned to client : "
                            + Arrays.toString(hostTypes.toArray()));
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println(
                    "StorageSystemClientTest : getStorageSystemHostTypes : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemHostTypes : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("StorageSystemClientTest : getStorageSystemHostTypes : Applicance Not reachabe at : "
                            + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("StorageSystemClientTest : getStorageSystemHostTypes : No response from appliance : "
                            + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemHostTypes : arguments are null ");
            return;
        }
    }

    private void getAllStorageSystem() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        StorageSystemCollection storageSystemCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageSystemCollectionDto = storageSystemClient.getAllStorageSystems(params);

            System.out.println("StorageSystemClientTest : getAllStorageSystem : storageSystem object returned to client : "
                    + storageSystemCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : getAllStorageSystem : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : getAllStorageSystem : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : getAllStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : getAllStorageSystem : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : getAllStorageSystem : arguments are null ");
            return;
        }
    }

    private void getStorageSystemByName() throws InstantiationException, IllegalAccessException {
        StorageSystemV2 storageSystemDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            storageSystemDto = storageSystemClient.getStorageSystemByName(params, resourceName);

            System.out.println("StorageSystemClientTest : getStorageSystemByName : storageSystem object returned to client : "
                    + storageSystemDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : getStorageSystemByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : getStorageSystemByName : arguments are null ");
            return;
        }
    }

    private void createStorageSystem() throws InstantiationException, IllegalAccessException {
        String createStorage = null;
        AddStorageSystemCredentials addStorageSystemCredentialsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create storageSystem request body
            addStorageSystemCredentialsDto = buildTestStorageSystemDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            createStorage = storageSystemClient.createStorageSystem(params, addStorageSystemCredentialsDto, false);

            System.out.println("StorageSystemClientTest : createStorageSystem : storageSystem object returned to client : "
                    + createStorage);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : createStorageSystem : resource you are looking is not found "
                    + params.getHostname());
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageSystemClientTest : createStorageSystem : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : createStorageSystem : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : createStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : createStorageSystem : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("StorageSystemClientTest : createStorageSystem : errors in task, please check task resource for more details ");
            return;
        }

    }

    private void updateStorageSystem() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String updateStorage = null;
        String resourceId = null;
        StorageSystemV2 storageSystemDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            storageSystemDto = storageSystemClient.getStorageSystemByName(params, resourceName);

            if (null != storageSystemDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(storageSystemDto.getUri());
            }

            final StorageSystemV2 updateStorageSystemDto = buildUpdateStorageSystemDto(storageSystemDto);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            updateStorage = storageSystemClient.updateStorageSystem(params, resourceId, updateStorageSystemDto, false);

            System.out.println("StorageSystemClientTest : updateStorageSystem : " + "storageSystem object returned to client : "
                    + updateStorage);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : updateStorageSystem :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("StorageSystemClientTest : updateStorageSystem :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : updateStorageSystem :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : updateStorageSystem :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : updateStorageSystem :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : updateStorageSystem : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("StorageSystemClientTest : updateStorageSystem : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteStorageSystem() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String deleteStorage = null;
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = storageSystemClient.getId(params, resourceName);

            // then make sdk service call to get resource
            deleteStorage = storageSystemClient.deleteStorageSystem(params, resourceId);

            System.out.println("StorageSystemClientTest : deleteStorageSystem : storageSystem object returned to client : "
                    + deleteStorage);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("StorageSystemClientTest : deleteStorageSystem : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("StorageSystemClientTest : deleteStorageSystem : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("StorageSystemClientTest : deleteStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("StorageSystemClientTest : deleteStorageSystem : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("StorageSystemClientTest : deleteStorageSystem : arguments are null ");
            return;
        }

    }

    private AddStorageSystemCredentials buildTestStorageSystemDto() {
        final AddStorageSystemCredentials dto = new AddStorageSystemCredentials();
        dto.setIp_hostname(ipAddress);
        dto.setUsername(username);
        dto.setPassword(password);
        return dto;
    }

    private StorageSystemV2 buildUpdateStorageSystemDto(final StorageSystemV2 storageSystemDto) {

        final List<StorageTargetPortV2> tempStorageTargetPort = new ArrayList<StorageTargetPortV2>();
        final List<StorageTargetPort> unMangedStorageTargetPort = new ArrayList<StorageTargetPort>();
        for (int i = 0; i < storageSystemDto.getUnmanagedPorts().size(); i++) {
            if (storageSystemDto.getUnmanagedPorts().get(i).getName().equalsIgnoreCase(unManagedPort_A)) {
                final StorageTargetPortV2 managed_one = new StorageTargetPortV2();
                managed_one.setActualNetworkUri(storageSystemDto.getUnmanagedPorts().get(i).getActualNetworkUri());
                managed_one.setExpectedNetworkName(storageSystemDto.getUnmanagedPorts().get(i).getExpectedNetworkName());
                managed_one.setGroupName(storageSystemDto.getUnmanagedPorts().get(i).getGroupName());
                String fc_network_one = null;
                for (int j = 0; j < fcNetworkName_A.size(); j++) {
                    fc_network_one = fcNetworkClient.getFcNetworkByName(params, fcNetworkName_A.get(j)).getUri();
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

            } else if (storageSystemDto.getUnmanagedPorts().get(i).getName().equalsIgnoreCase(unManagedPort_B)) {
                final StorageTargetPortV2 managed_two = new StorageTargetPortV2();
                managed_two.setActualNetworkUri(storageSystemDto.getUnmanagedPorts().get(i).getActualNetworkUri());
                managed_two.setExpectedNetworkName(storageSystemDto.getUnmanagedPorts().get(i).getExpectedNetworkName());
                managed_two.setGroupName(storageSystemDto.getUnmanagedPorts().get(i).getGroupName());
                String fc_network_two = null;
                for (int j = 0; j < fcNetworkName_B.size(); j++) {
                    fc_network_two = fcNetworkClient.getFcNetworkByName(params, fcNetworkName_B.get(j)).getUri();
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
        for (final StorageTargetPort storageTargetPort : unMangedStorageTargetPort) {
            storageSystemDto.getUnmanagedPorts().remove(storageTargetPort);
        }
        storageSystemDto.setManagedDomain(managedDomain);
        final List<String> unmanagedDomains = storageSystemDto.getUnmanagedDomains();
        unmanagedDomains.remove(unManagedDomain);
        storageSystemDto.setUnmanagedDomains(unmanagedDomains);

        return storageSystemDto;
    }

    public static void main(final String[] args) throws Exception {
        StorageSystemClientSample client = new StorageSystemClientSample();

        client.createStorageSystem();
        client.getStorageSystemById();
        client.updateStorageSystem();
        client.getAllManagedPortsForStorageSystem();
        client.getAllStorageSystem();
        client.getManagedPortsForStorageSystem();
        client.getStoragePoolsForStorageSystem();
        client.getStorageSystemHostTypes();
        client.getStorageSystemByName();
        client.deleteStorageSystem();
    }

}
