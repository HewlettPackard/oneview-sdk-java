/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client.facilities;

import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.Light;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Power;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.PowerDeliveryDeviceClient;
import com.hp.ov.sdk.rest.client.PowerDeliveryDeviceClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class PowerDeliveryDeviceClientSample {

    private final PowerDeliveryDeviceClient powerDeliveryDeviceClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String resourceName = "172.18.8.11, PDU 1, L6,Outlet1";
    private static final String resourceId = "db5271f1-d905-47ce-89be-8f4032ac3f92";
    // ================================

    private PowerDeliveryDeviceClientSample() {
        powerDeliveryDeviceClient = PowerDeliveryDeviceClientImpl.getClient();
    }

    private void getPowerDeliveryDeviceById() throws InstantiationException, IllegalAccessException {
        PowerDeliveryDevice powerDeliveryDeviceDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            powerDeliveryDeviceDto = powerDeliveryDeviceClient.getPowerDeliveryDeviceById(params, resourceId);

            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceById : powerDeliveryDevice object returned to client : "
                    + powerDeliveryDeviceDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceById : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceById : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceById : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceById : arguments are null ");
            return;
        }

    }

    private void getAllPowerDeliveryDevices() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<PowerDeliveryDevice> powerDeliveryDeviceCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            powerDeliveryDeviceCollectionDto = powerDeliveryDeviceClient.getAllPowerDeliveryDevices(params);

            System.out.println("PowerDeliveryDeviceClientTest : getAllPowerDeliveryDevices : powerDeliveryDevice object returned to client : "
                    + powerDeliveryDeviceCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getAllPowerDeliveryDevices : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getAllPowerDeliveryDevices : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : getAllPowerDeliveryDevices : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getAllPowerDeliveryDevices : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getAllPowerDeliveryDevices : arguments are null ");
            return;
        }
    }

    private void getPowerDeliveryDeviceByName() throws InstantiationException, IllegalAccessException {
        PowerDeliveryDevice powerDeliveryDeviceDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            powerDeliveryDeviceDto = powerDeliveryDeviceClient.getPowerDeliveryDeviceByName(params, resourceName);

            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceByName : powerDeliveryDevice object returned to client : "
                    + powerDeliveryDeviceDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceByName : arguments are null ");
            return;
        }

    }

    private void getPowerDeliveryDevicePowerState() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            Power power = powerDeliveryDeviceClient.getPowerDeliveryDevicePowerState(params, resourceId);

            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDevicePowerState : Power object returned to client : "
                    + power.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDevicePowerState : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDevicePowerState : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDevicePowerState : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDevicePowerState : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDevicePowerState : arguments are null ");
            return;
        }

    }

    private void updatePowerDeliveryDevicePowerState() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            OutletState outletStateDto = new OutletState();
            outletStateDto.setPowerState(Power.On);

            taskResourceV2 = powerDeliveryDeviceClient.updatePowerDeliveryDevicePowerState(params, resourceId, outletStateDto, false);

            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevicePowerState : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevicePowerState : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevicePowerState : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevicePowerState : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevicePowerState : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevicePowerState : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevicePowerState : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void updatePowerDeliveryDeviceRefreshState() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();


            PowerDeliveryDeviceRefreshRequest refreshRequest = new PowerDeliveryDeviceRefreshRequest();
            refreshRequest.setRefreshState(RefreshState.RefreshPending);

            taskResourceV2 = powerDeliveryDeviceClient.updatePowerDeliveryDeviceRefreshState(params, resourceId, refreshRequest , false);

            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceRefreshState : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceRefreshState : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceRefreshState : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceRefreshState : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceRefreshState : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceRefreshState : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceRefreshState : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void getPowerDeliveryDeviceUidState() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            Light light = powerDeliveryDeviceClient.getPowerDeliveryDeviceUidState(params, resourceId);

            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUidState : Light object returned to client : "
                    + light.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUidState : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUidState : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUidState : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUidState : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUidState : arguments are null ");
            return;
        }

    }

    private void updatePowerDeliveryDeviceUidState() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            OutletState outletStateDto = new OutletState();
            outletStateDto.setUidState(Light.On);

            taskResourceV2 = powerDeliveryDeviceClient.updatePowerDeliveryDeviceUidState(params, resourceId, outletStateDto, false);

            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceUidState : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceUidState : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceUidState : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceUidState : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceUidState : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceUidState : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDeviceUidState : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void addPowerDeliveryDevice() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create powerDeliveryDevice request body
            PowerDeliveryDevice newPDD = new PowerDeliveryDevice();
            newPDD.setName("myPDD");
            newPDD.setRatedCapacity(40);

            newPDD = powerDeliveryDeviceClient.addPowerDeliveryDevice(params, newPDD, false);

            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDevice : Power Delivery Device returned to client : "
                    + newPDD.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDevice : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDevice : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDevice : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDevice : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDevice : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("PowerDeliveryDeviceClientTest : addPowerDeliveryDevice : errors in task, please check task resource for more details ");
            return;
        }

    }

    private void addPowerDeliveryDeviceByDiscover() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create powerDeliveryDevice request body
            final ImportPdd importPddDto = new ImportPdd();
            importPddDto.setHostname("172.18.8.12");
            importPddDto.setUsername("dcs");
            importPddDto.setPassword("dcs");
            importPddDto.setForce(true);

            taskResourceV2 = powerDeliveryDeviceClient.addPowerDeliveryDeviceByDiscover(params, importPddDto, false);

            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDeviceByDiscover : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDeviceByDiscover : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDeviceByDiscover : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDeviceByDiscover : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDeviceByDiscover : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : addPowerDeliveryDeviceByDiscover : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("PowerDeliveryDeviceClientTest : addPowerDeliveryDeviceByDiscover : errors in task, please check task resource for more details ");
            return;
        }

    }

    private void updatePowerDeliveryDevice() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        PowerDeliveryDevice powerDeliveryDeviceDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            powerDeliveryDeviceDto = powerDeliveryDeviceClient.getPowerDeliveryDeviceByName(params, resourceName);

            powerDeliveryDeviceDto.setName(resourceName + "_Update");

            if (null != powerDeliveryDeviceDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(powerDeliveryDeviceDto.getUri());
            }

            powerDeliveryDeviceDto = powerDeliveryDeviceClient.updatePowerDeliveryDevice(params, resourceId, powerDeliveryDeviceDto, false);

            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice : " + "powerDeliveryDevice object returned to client : "
                    + powerDeliveryDeviceDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("PowerDeliveryDeviceClientTest : updatePowerDeliveryDevice : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void removePowerDeliveryDevice() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            taskResourceV2 = powerDeliveryDeviceClient.removePowerDeliveryDevice(params, resourceId, false);

            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDevice : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDevice : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDevice : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDevice : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDevice : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDevice : arguments are null ");
            return;
        }

    }

    private void removePowerDeliveryDeviceSynchronously() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            String result = powerDeliveryDeviceClient.removePowerDeliveryDeviceSynchronously(params, resourceId);

            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceSynchronously : String object returned to client : "
                    + result);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceSynchronously : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceSynchronously : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceSynchronously : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceSynchronously : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceSynchronously : arguments are null ");
            return;
        }

    }

    private void removePowerDeliveryDeviceByFilter() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            String filter = "'name' matches 'my%'";
            taskResourceV2 = powerDeliveryDeviceClient.removePowerDeliveryDeviceByFilter(params, filter, false);

            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceByFilter : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceByFilter : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceByFilter : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceByFilter : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceByFilter : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : removePowerDeliveryDeviceByFilter : arguments are null ");
            return;
        }
    }

    private void getPowerDeliveryDeviceUtilization() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            UtilizationData utilizationDto = powerDeliveryDeviceClient.getPowerDeliveryDeviceUtilization(params, resourceId);

            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUtilization : UtilizationData object returned to client : "
                    + utilizationDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUtilization : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUtilization : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUtilization : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUtilization : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("PowerDeliveryDeviceClientTest : getPowerDeliveryDeviceUtilization : arguments are null ");
            return;
        }

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        PowerDeliveryDeviceClientSample client = new PowerDeliveryDeviceClientSample();

        client.getPowerDeliveryDeviceById();
        client.getAllPowerDeliveryDevices();
        client.getPowerDeliveryDeviceByName();

        client.getPowerDeliveryDevicePowerState();
        client.updatePowerDeliveryDevicePowerState();

        client.updatePowerDeliveryDeviceRefreshState();

        client.getPowerDeliveryDeviceUidState();
        client.updatePowerDeliveryDeviceUidState();

        client.getPowerDeliveryDeviceUtilization();

        client.addPowerDeliveryDevice();
        client.addPowerDeliveryDeviceByDiscover();

        client.updatePowerDeliveryDevice();

        client.removePowerDeliveryDevice();
        client.removePowerDeliveryDeviceByFilter();
        client.removePowerDeliveryDeviceSynchronously();
    }

}
