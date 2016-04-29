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
package com.hp.ov.sdk.firmwaredriver;

import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.FirmwareDriverClient;
import com.hp.ov.sdk.rest.client.FirmwareDriverClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * FirmwareDriverClientSample is a sample program to consume the firmware driver software resource of HP OneView
 * It invokes APIs of FirmwareDriverClient which is in sdk library to perform GET/DELETE
 * operations on firmware driver resource
 */
public class FirmwareDriverClientSample {

    private final FirmwareDriverClient firmwareDriverClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "HP Service Pack For ProLiant OneView 2014 11 13";
    private static final String resourceId = "bp-hp-service-pack-for-proliant-oneview-2014-11-30-05";
    // ================================

    private FirmwareDriverClientSample() {
        this.firmwareDriverClient = FirmwareDriverClientImpl.getClient();
    }

    private void getFirmwareDriverById() throws InstantiationException, IllegalAccessException {
        FwBaseline fwBaselineDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fwBaselineDto = firmwareDriverClient.getFirmwareDriver(params, resourceId);

            System.out.println("FirmwareDriverClientTest : getFirmwareDriverById :"
                    + " fwBaseline group object returned to client : " + fwBaselineDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverById :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverById :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverById :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverById :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverById :" + " arguments are null ");
            return;
        }
    }

    private void getAllFirmwareDriver() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        FwBaselineCollection fwBaselineCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fwBaselineCollectionDto = firmwareDriverClient.getAllFirmwareDrivers(params);

            System.out.println("FirmwareDriverClientTest : getAllFirmwareDriver :"
                    + " fwBaseline groups object returned to client : " + fwBaselineCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FirmwareDriverClientTest : getAllFirmwareDriver " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FirmwareDriverClientTest : getAllFirmwareDriver :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FirmwareDriverClientTest : getAllFirmwareDriver :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FirmwareDriverClientTest : getAllFirmwareDriver :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FirmwareDriverClientTest : getAllFirmwareDriver :" + " arguments are null ");
            return;
        }
    }

    private void getFirmwareDriverByName() throws InstantiationException, IllegalAccessException {
        FwBaseline fwBaselineDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            fwBaselineDto = firmwareDriverClient.getFirmwareDriverByName(params, resourceName);

            System.out.println("FirmwareDriverClientTest : getFirmwareDriverByName :"
                    + " fwBaseline group object returned to client : " + fwBaselineDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverByName :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FirmwareDriverClientTest : getFirmwareDriverByName :" + " arguments are null ");
            return;
        }
    }

    private void deleteFirmwareDriver() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = firmwareDriverClient.getId(params, resourceName);

            // then make sdk service call to get resource
            taskResourceV2 = firmwareDriverClient.deleteFirmwareDriver(params, resourceId, false, false);

            System.out.println("FirmwareDriverClientTest : deleteFirmwareDriver : "
                    + "fcNetwork group object returned to client : " + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("FirmwareDriverClientTest : deleteFirmwareDriver :"
                    + " resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("FirmwareDriverClientTest : deleteFirmwareDriver :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("FirmwareDriverClientTest : deleteFirmwareDriver :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("FirmwareDriverClientTest : deleteFirmwareDriver : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("FirmwareDriverClientTest : deleteFirmwareDriver :" + " arguments are null ");
            return;
        }
    }

    public static void main(final String[] args) throws Exception {
        FirmwareDriverClientSample client = new FirmwareDriverClientSample();

        client.getFirmwareDriverById();
        client.getAllFirmwareDriver();
        client.getFirmwareDriverByName();
        client.deleteFirmwareDriver();
    }
}
