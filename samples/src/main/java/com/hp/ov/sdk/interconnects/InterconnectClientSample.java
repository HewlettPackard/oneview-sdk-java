/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.interconnects;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.InterconnectsCollection;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.InterconnectsClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * InterconnectClientSample is a sample program to consume the characteristics model of an interconnect in 
 * HP OneView.It invokes APIs of InterconnectsClient which is in sdk library to perform GET/PUT
 * operations on interconnect resource
 */
public class InterconnectClientSample {
    private RestParams params;
    private static InterconnectsClient interconnectsClient;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "enc-05, interconnect 2";
    private static final String resourceId = "0da2b172-ccde-409d-8946-57df1e5c39b6";

    // ================================

    private static void init() {
        interconnectsClient = HPOneViewSdkBeanFactory.getInterconnectsClient();
    }

    private void getInterconnectsById() throws InstantiationException, IllegalAccessException {
        Interconnects interconnectsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            interconnectsDto = interconnectsClient.getInterconnects(params, resourceId);

            System.out.println("InterconnectClientSample : getInterconnectsById :"
                    + " interconnect type object returned to client : " + interconnectsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsById :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsById :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : getInterconnectsById :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsById :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsById :" + " arguments are null ");
            return;
        }

    }

    private void getAllInterconnects() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        InterconnectsCollection interconnectsCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            interconnectsCollectionDto = interconnectsClient.getAllInterconnects(params);

            System.out.println("InterconnectClientSample : getAllInterconnects :"
                    + " interconnect type object returned to client : " + interconnectsCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : getAllInterconnects " + ": resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : getAllInterconnects :" + " no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : getAllInterconnects :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : getAllInterconnects :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : getAllInterconnects :" + " arguments are null ");
            return;
        }
    }

    private void getInterconnectsByName() throws InstantiationException, IllegalAccessException {
        Interconnects interconnectsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            interconnectsDto = interconnectsClient.getInterconnectByName(params, resourceName);

            System.out.println("InterconnectClientSample : getInterconnectsByName :"
                    + " interconnect type object returned to client : " + interconnectsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsByName :" + " resource not found : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsByName :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : getInterconnectsByName :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsByName :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : getInterconnectsByName :" + " arguments are null ");
            return;
        }

    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        InterconnectClientSample client = new InterconnectClientSample();
        client.getInterconnectsById();
        client.getAllInterconnects();
        client.getInterconnectsByName();
    }

}
