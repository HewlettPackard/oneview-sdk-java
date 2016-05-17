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
package com.hp.ov.sdk.interconnects;

import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.dto.generated.Port;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.InterconnectsClient;
import com.hp.ov.sdk.rest.client.InterconnectsClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * InterconnectClientSample is a sample program to consume the characteristics model of an interconnect in
 * HPE OneView.It invokes APIs of InterconnectsClient which is in sdk library to perform GET/PUT
 * operations on interconnect resource
 */
public class InterconnectClientSample {

    private final InterconnectsClient interconnectsClient;

    private RestParams params;

    public InterconnectClientSample() {
        this.interconnectsClient = InterconnectsClientImpl.getClient();
    }

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "Encl1, interconnect 2";
    private static final String resourceId = "683b67ce-ba57-4ebf-9c98-bc534afd8569";
    // ================================

    private void getInterconnectsById() throws InstantiationException, IllegalAccessException {
        Interconnects interconnectsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            interconnectsDto = interconnectsClient.getInterconnectById(params, resourceId);

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
        ResourceCollection<Interconnects> interconnectsCollectionDto = null;
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

    private void patchInterconnect() {
        TaskResourceV2 taskResourceV2 = null;
        String resourceId = null;
        Interconnects interconnectDto = null;
        Patch patchDto = new Patch();
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            interconnectDto = interconnectsClient.getInterconnectByName(params, resourceName);

            if (null != interconnectDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(interconnectDto.getUri());
            }

            // Interconnect patch supports the update of 'powerState', 'uidState' and 'deviceResetState'
            patchDto.setOp(PatchOperation.replace);
            patchDto.setPath("/powerState");
            patchDto.setValue("Off");

            /**
             * then make sdk service call to get resource
             */
            taskResourceV2 = interconnectsClient.patchInterconnect(params, resourceId, patchDto , false);

            System.out.println("InterconnectClientSample : patchInterconnect :" + " task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : patchInterconnect :" + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("InterconnectClientSample : patchInterconnect :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : patchInterconnect :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : patchInterconnect :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : patchInterconnect :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : patchInterconnect : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("InterconnectClientSample : patchInterconnect : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void updateInterconnectPort() {
        TaskResourceV2 taskResourceV2 = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            Interconnects interconnect = interconnectsClient.getInterconnectById(params, resourceId);

            Port portDto = interconnect.getPorts().get(0);
            portDto.setEnabled(!portDto.getEnabled());
            // then make sdk service call to get resource
            taskResourceV2 = interconnectsClient.updateInterconnectPort(params, resourceId, portDto , false, false);

            System.out.println("InterconnectClientSample : updateInterconnectPort :" + " task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : updateInterconnectPort :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : updateInterconnectPort :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("InterconnectClientSample : updateInterconnectPort :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("InterconnectClientSample : updateInterconnectPort :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : updateInterconnectPort :" + " arguments are null ");
            return;
        }
    }

    private void resetInterconnectPortProtection() {
        TaskResourceV2 taskResourceV2 = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            Interconnects interconnect = interconnectsClient.getInterconnectById(params, resourceId);

            // then make sdk service call to get resource
            taskResourceV2 = interconnectsClient.resetInterconnectPortProtection(params, resourceId, false);

            System.out.println("InterconnectClientSample : resetInterconnectPortProtection :" + " task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : resetInterconnectPortProtection :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : resetInterconnectPortProtection :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("InterconnectClientSample : resetInterconnectPortProtection :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("InterconnectClientSample : resetInterconnectPortProtection :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : resetInterconnectPortProtection :" + " arguments are null ");
            return;
        }
    }

    private void getInterconnectStatistics() {
        InterconnectsStatistics statisticsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            statisticsDto = interconnectsClient.getInterconnectStatistics(params, resourceId);

            System.out.println("InterconnectClientSample : getInterconnectStatistics :"
                    + " statistics object returned to client : " + statisticsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : getInterconnectStatistics :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : getInterconnectStatistics :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : getInterconnectStatistics :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : getInterconnectStatistics :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : getInterconnectStatistics :" + " arguments are null ");
            return;
        }
    }

    private void getInterconnectPortStatistics() {
        PortStatistics statisticsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            statisticsDto = interconnectsClient.getInterconnectPortStatistics(params, resourceId, "d1");

            System.out.println("InterconnectClientSample : getInterconnectPortStatistics :"
                    + " statistics object returned to client : " + statisticsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : getInterconnectPortStatistics :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : getInterconnectPortStatistics :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : getInterconnectPortStatistics :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : getInterconnectPortStatistics :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : getInterconnectPortStatistics :" + " arguments are null ");
            return;
        }
    }

    private void getInterconnectSubportStatistics() {
        SubportStatistics statisticsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            statisticsDto = interconnectsClient.getInterconnectSubportStatistics(params, resourceId, "d1", 1);

            System.out.println("InterconnectClientSample : getInterconnectSubportStatistics :"
                    + " statistics object returned to client : " + statisticsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : getInterconnectSubportStatistics :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : getInterconnectSubportStatistics :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : getInterconnectSubportStatistics :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : getInterconnectSubportStatistics :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : getInterconnectSubportStatistics :" + " arguments are null ");
            return;
        }
    }

    private void updateInterconnectPorts() {
        TaskResourceV2 taskResourceV2 = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            Interconnects interconnect = interconnectsClient.getInterconnectById(params, resourceId);

            Port portDto = interconnect.getPorts().get(16);
            portDto.setEnabled(!portDto.getEnabled());
            List<Port> ports = Arrays.asList(portDto);
            // then make sdk service call to get resource
            taskResourceV2 = interconnectsClient.updateInterconnectPorts(params, resourceId, ports , false, false);

            System.out.println("InterconnectClientSample : updateInterconnectPorts :" + " task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : updateInterconnectPorts :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : updateInterconnectPorts :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("InterconnectClientSample : updateInterconnectPorts :" + " Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("InterconnectClientSample : updateInterconnectPorts :" + " No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : updateInterconnectPorts :" + " arguments are null ");
            return;
        }
    }

    private void getInterconnectNamedServers() {
        List<NameServer> namedServers = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            namedServers = interconnectsClient.getInterconnectNamedServers(params, resourceId);

            System.out.println("InterconnectClientSample : getInterconnectNamedServers :"
                    + " named servers returned to client : " + Arrays.toString(namedServers.toArray()));
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("InterconnectClientSample : getInterconnectNamedServers :" + " resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("InterconnectClientSample : getInterconnectNamedServers :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("InterconnectClientSample : getInterconnectNamedServers :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("InterconnectClientSample : getInterconnectNamedServers :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("InterconnectClientSample : getInterconnectNamedServers :" + " arguments are null ");
            return;
        }
    }

    public static void main(final String[] args) throws Exception {
        InterconnectClientSample client = new InterconnectClientSample();

        client.getAllInterconnects();
        client.getInterconnectsById();
        client.getInterconnectsByName();

        client.patchInterconnect();
        client.updateInterconnectPort();
        client.resetInterconnectPortProtection();
        client.getInterconnectStatistics();

        client.getInterconnectPortStatistics();
        client.getInterconnectSubportStatistics();
        client.updateInterconnectPorts();
        client.getInterconnectNamedServers();
    }

}
