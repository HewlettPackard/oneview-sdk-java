/*
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
 */

package com.hp.ov.sdk.switches;

import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.SwitchTypeClient;
import com.hp.ov.sdk.rest.client.SwitchTypeClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class SwitchTypeClientSample {

    private final SwitchTypeClient switchClient;

    // These are variables to be defined by user
    // ================================
    private static final String SWITCH_TYPE_ID = "3a5f0656-17be-46b3-b604-e89059354012";

    public static final String SWITCH_TYPE_NAME = "Cisco Nexus 55xx";
    // ================================

    public SwitchTypeClientSample() {
        this.switchClient = SwitchTypeClientImpl.getClient();
    }

    private void getSwitchType() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            InterconnectType type = this.switchClient.getSwitchType(params, SWITCH_TYPE_ID);

            System.out.println("SwitchTypeClient : getSwitchType : " +
                    "SwitchType object returned to client : " + type);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchTypeClient : getSwitchType : the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchTypeClient : getSwitchType : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchTypeClient : getSwitchType : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchTypeClient : getSwitchType : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchTypeClient : getSwitchType : arguments are null");
        }
    }

    private void getSwitchTypeByName() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            InterconnectType type = this.switchClient.getSwitchTypeByName(params, SWITCH_TYPE_NAME);

            System.out.println("SwitchTypeClient : getSwitchTypeByName : " +
                    "SwitchType object returned to client : " + type);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchTypeClient : getSwitchTypeByName : the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchTypeClient : getSwitchTypeByName : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchTypeClient : getSwitchTypeByName : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchTypeClient : getSwitchTypeByName : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchTypeClient : getSwitchTypeByName : arguments are null");
        }
    }

    private void getAllSwitchTypes() {
        RestParams params = new RestParams();

        try {
            params = HPOneViewCredential.createCredentials();

            ResourceCollection<InterconnectType> collection = this.switchClient.getAllSwitchTypes(params);

            System.out.println("SwitchTypeClient : getAllSwitchTypes : " +
                    "InterconnectTypeCollection object returned to client (count) : " + collection.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("SwitchTypeClient : getAllSwitchTypes : the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("SwitchTypeClient : getAllSwitchTypes : no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("SwitchTypeClient : getAllSwitchTypes : appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("SwitchTypeClient : getAllSwitchTypes : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("SwitchTypeClient : getAllSwitchTypes : arguments are null");
        }
    }

    public static void main(String[] args) {
        SwitchTypeClientSample client = new SwitchTypeClientSample();

        client.getSwitchType();
        client.getSwitchTypeByName();
        client.getAllSwitchTypes();
    }

}
