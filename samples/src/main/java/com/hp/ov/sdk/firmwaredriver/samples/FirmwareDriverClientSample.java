/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.firmwaredriver.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.FirmwareDriverClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class FirmwareDriverClientSample {
    private RestParams params;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static TaskResourceV2 taskResourceV2;
    private static FirmwareDriverClient firmwareDriverClient;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "ubuntu-13.04-desktop-i386.iso";// "HP Service Pack For ProLiant OneView 2014 11 13";
    private static final String resourceId = "ubuntu-13_04-desktop-i386";// "bp-hp-service-pack-for-proliant-oneview-2014-11-30-05";

    // ================================

    private static void init() {
        firmwareDriverClient = HPOneViewSdkBeanFactory.getFirmwareDriverClient();
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
    }

    private void getFirmwareDriverById() throws InstantiationException, IllegalAccessException {
        FwBaseline fwBaselineDto = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
        FwBaseline fwBaselineDto = null;
        // first get the session Id
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using name
            fwBaselineDto = firmwareDriverClient.getFirmwareDriverByName(params, resourceName);

            if (null != fwBaselineDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(fwBaselineDto.getUri());
            }

            // then make sdk service call to get resource
            taskResourceV2 = firmwareDriverClient.deleteFirmwareDriver(params, resourceId, false, false, false);

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

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        FirmwareDriverClientSample client = new FirmwareDriverClientSample();
        client.getFirmwareDriverById();
        client.getAllFirmwareDriver();
        client.getFirmwareDriverByName();
        client.deleteFirmwareDriver();
    }
}
