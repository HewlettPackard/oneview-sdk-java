/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client.fcsans;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.FcSansDeviceManagerClient;
import com.hp.ov.sdk.rest.client.FcSansDeviceManagerClientImpl;
import com.hp.ov.sdk.rest.client.FcSansProviderClient;
import com.hp.ov.sdk.rest.client.FcSansProviderClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * DeviceManagerClientSample is a sample program to consume the network advisor by managing the
 * san manager  of HPE OneView. It invokes APIs of DeviceManagerClient which is in sdk library to
 * perform GET/PUT/POST/DELETE operations on san manager resource
 */

public class FcSansDeviceManagerClientSample {

    private final FcSansDeviceManagerClient fcSansDeviceManagerClient;
    private final FcSansProviderClient fcSansProviderClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String providerName = "Brocade Network Advisor";
    private static final String resourceId = "f7aae238-64a3-4008-878c-d46d0a5798fe";
    private static final String resourceName = "172.18.15.1";// example value
    private static final String hostName = "Host";
    private static final String hostValue = "172.18.15.1";// example value
    private static final String userName = "Username";
    private static final String userValue = "dcs";
    private static final String passwordName = "Password";
    private static final String passwordValue = "dcs";
    private static final String portName = "Port";
    private static final String useSSLName = "UseSsl";
    private static final String useSSLValue = "true";
    // ================================

    private FcSansDeviceManagerClientSample() {
        fcSansDeviceManagerClient = FcSansDeviceManagerClientImpl.getClient();
        fcSansProviderClient = FcSansProviderClientImpl.getClient();
    }

    private void createDeviceManager() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            final SanProviderResponse sanProviderResponseDto = getProviderUrl(params, providerName);

            // create network request body
            final DeviceManagerResponse addDeviceManagerResponseDto = buildTestDeviceManagerDto(sanProviderResponseDto);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcSansDeviceManagerClient.createDeviceManager(params,
                    sanProviderResponseDto.getDeviceManagersUri(), addDeviceManagerResponseDto, false);

            System.out.println("DeviceManagerClientTest : createDeviceManager : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DeviceManagerClientTest : createDeviceManager : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("DeviceManagerClientTest : createDeviceManager : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DeviceManagerClientTest : createDeviceManager : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DeviceManagerClientTest : createDeviceManager : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DeviceManagerClientTest : createDeviceManager : arguments are null ");
            return;
        }
    }

    private void getDeviceManager() throws InstantiationException, IllegalAccessException {
        DeviceManagerResponse deviceManagerResponseDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            deviceManagerResponseDto = fcSansDeviceManagerClient.getDeviceManager(params, resourceId);

            System.out.println("DeviceManagerClientTest : getDeviceManager :" + " device manager object returned to client : "
                    + deviceManagerResponseDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DeviceManagerClientTest : getDeviceManager :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DeviceManagerClientTest : getDeviceManager :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DeviceManagerClientTest : getDeviceManager :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("DeviceManagerClientTest : getDeviceManager :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DeviceManagerClientTest : getDeviceManager :" + " arguments are null ");
            return;
        }

    }

    private void getAllDeviceManager() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<DeviceManagerResponse> deviceManagerResponseCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            deviceManagerResponseCollectionDto = fcSansDeviceManagerClient.getAllDeviceManager(params);

            System.out.println("DeviceManagerClientTest : getAllDeviceManager :" + " device manager object returned to client : "
                    + deviceManagerResponseCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DeviceManagerClientTest : getAllDeviceManager " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DeviceManagerClientTest : getAllDeviceManager :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DeviceManagerClientTest : getAllDeviceManager :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("DeviceManagerClientTest : getAllDeviceManager :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DeviceManagerClientTest : getAllDeviceManager :" + " arguments are null ");
            return;
        }

    }

    private void updateDeviceManager() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        DeviceManagerResponse deviceManagerResponseDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            deviceManagerResponseDto = fcSansDeviceManagerClient.getDeviceManagerByName(params, resourceName);
            deviceManagerResponseDto.setRefreshState(RefreshState.RefreshPending);
            deviceManagerResponseDto = updateHostConnectionDetails(deviceManagerResponseDto);

            if (null != deviceManagerResponseDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(deviceManagerResponseDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcSansDeviceManagerClient.updateDeviceManager(params, resourceId,
                    deviceManagerResponseDto, false, false);

            System.out.println("DeviceManagerClientTest : updateDeviceManager : " + " task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DeviceManagerClientTest : updateDeviceManager :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("DeviceManagerClientTest : updateDeviceManager :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DeviceManagerClientTest : updateDeviceManager :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DeviceManagerClientTest : updateDeviceManager :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("DeviceManagerClientTest : updateDeviceManager :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DeviceManagerClientTest : updateDeviceManager : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("DeviceManagerClientTest : updateDeviceManager : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteDeviceManager() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            String resourceId = fcSansDeviceManagerClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = fcSansDeviceManagerClient.deleteDeviceManager(params, resourceId, false);

            System.out.println("DeviceManagerClientTest : deleteDeviceManager : " + "device manager object deleted!");
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("DeviceManagerClientTest : deleteDeviceManager:"
                    + " resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("DeviceManagerClientTest : deleteDeviceManager:" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("DeviceManagerClientTest : deleteDeviceManager :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("DeviceManagerClientTest : deleteDeviceManager : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("DeviceManagerClientTest : deleteDeviceManager :" + " arguments are null ");
            return;
        }
    }

    private SanProviderResponse getProviderUrl(final RestParams params, final String providerName) {
        return fcSansProviderClient.getProviderByName(params, providerName);
    }

    private DeviceManagerResponse updateHostConnectionDetails(DeviceManagerResponse deviceManagerResponseDto) {
        for (Property property : deviceManagerResponseDto.getConnectionInfo()) {
            if (property.getName().equalsIgnoreCase("host")) {
                property.setValue(hostValue);
            }
            if (property.getName().equalsIgnoreCase("password")) {
                property.setValue(passwordValue);
            }
        }
        return deviceManagerResponseDto;
    }

    private DeviceManagerResponse buildTestDeviceManagerDto(SanProviderResponse sanProviderResponseDto) {
        DeviceManagerResponse deviceManagerResponseDto = new DeviceManagerResponse();
        List<Property> connectionInfo = new ArrayList<Property>();
        String portValue = "";
        for (Property property : sanProviderResponseDto.getDefaultConnectionInfo()) {
            if (property.getDisplayName().contains("Port")) {
                portValue = property.getValue();
            }
        }

        Property host = new Property();
        host.setName(hostName);
        host.setValue(hostValue);

        Property port = new Property();
        port.setName(portName);
        port.setValue(portValue);

        Property user = new Property();
        user.setName(userName);
        user.setValue(userValue);

        Property password = new Property();
        password.setName(passwordName);
        password.setValue(passwordValue);

        Property useSsl = new Property();
        useSsl.setName(useSSLName);
        useSsl.setValue(useSSLValue);

        connectionInfo.add(host);
        connectionInfo.add(port);
        connectionInfo.add(user);
        connectionInfo.add(password);
        connectionInfo.add(useSsl);

        deviceManagerResponseDto.setConnectionInfo(connectionInfo);

        return deviceManagerResponseDto;
    }

    public static void main(final String[] args) throws Exception {
        FcSansDeviceManagerClientSample client = new FcSansDeviceManagerClientSample();

        client.createDeviceManager();
        client.updateDeviceManager();
        client.getAllDeviceManager();
        client.getDeviceManager();
        client.deleteDeviceManager();
    }
}
