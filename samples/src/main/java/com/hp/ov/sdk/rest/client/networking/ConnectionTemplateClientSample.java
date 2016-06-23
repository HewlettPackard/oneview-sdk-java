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
package com.hp.ov.sdk.rest.client.networking;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.ethernet.Bandwidth;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.ConnectionTemplateClient;
import com.hp.ov.sdk.rest.client.ConnectionTemplateClientImpl;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * ConnectionTemplateClientSample is a sample program to consume default connection configuration characteristics of
 * HPE OneView. It invokes APIs of ConnectionTemplateClient which is in sdk library to perform GET/PUT
 * operations on connection template resource
 */
public class ConnectionTemplateClientSample {

    private final ResourceDtoUtils resourceDtoUtils;
    private final ConnectionTemplateClient connectionTemplateClient;

    private RestParams params;

    // test values - user input
    // ================================
    private static final String resourceName = "name242646746-1453232034511";
    private static final String resourceId = "270c1680-51dc-4a2e-82f9-da6f5097ea93";
    private static final Double maxBandwidth = (double) 8000;
    private static final Double minBandwidth = (double) 2000;
    // ================================

    private ConnectionTemplateClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.resourceDtoUtils = new ResourceDtoUtils(oneViewClient);
        this.connectionTemplateClient = ConnectionTemplateClientImpl.getClient();
    }

    public void getConnectionTemplate() throws InstantiationException, IllegalAccessException {
        ConnectionTemplate connectionTemplateDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            connectionTemplateDto = connectionTemplateClient.getConnectionTemplate(params, resourceId);

            System.out
                    .println("ConnectionTemplateClientTest : getConnectionTemplate : connectionTemplate object returned to client : "
                            + connectionTemplateDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("ConnectionTemplateClientTest : getConnectionTemplate : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("ConnectionTemplateClientTest : getConnectionTemplate : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("ConnectionTemplateClientTest : getConnectionTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("ConnectionTemplateClientTest : getConnectionTemplate : No response from appliance : "
                    + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("ConnectionTemplateClientTest : getConnectionTemplate : arguments are null ");
        }

    }

    public void updateConnectionTemplate() throws InstantiationException, IllegalAccessException {
        ConnectionTemplate connectionTemplateDto = null;
        // first get the session Id
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            connectionTemplateDto = connectionTemplateClient.getConnectionTemplateByName(params, resourceName);

            if (null != connectionTemplateDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(connectionTemplateDto.getUri());
            }

            Bandwidth bandwidth = resourceDtoUtils.buildBandwidth(maxBandwidth, minBandwidth);
            connectionTemplateDto.setBandwidth(bandwidth);
            connectionTemplateDto.setETag(null);
            connectionTemplateDto.setUri(null);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            connectionTemplateDto = connectionTemplateClient.updateConnectionTemplate(params, resourceId, connectionTemplateDto,
                    false);

            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate : "
                    + "connectionTemplate object returned to client : " + connectionTemplateDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate :" + " resource not found to update : ");
        } catch (SDKBadRequestException ex) {
            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate :" + " bad request, try again : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate :" + " no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate :" + " Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate :" + " No response from appliance : "
                    + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("LoginSessionsImplTest : updateConnectionTemplate : " + "arguments are null ");
        } catch (SDKTasksException e) {
            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate : " + "errors in task, please check task "
                    + "resource for more details ");
        }

    }

    public void getAllConnectionTemplates() throws InstantiationException, IllegalAccessException {
        ResourceCollection<ConnectionTemplate> connectionTemplateCollectionDto = null;
        // first get the session Id
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            connectionTemplateCollectionDto = connectionTemplateClient.getAllConnectionTemplates(params);

            System.out
                    .println("ConnectionTemplateClientTest : getAllConnectionTemplates : connectionTemplate object returned to client : "
                            + connectionTemplateCollectionDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("ConnectionTemplateClientTest : getAllConnectionTemplates : resource not found : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("ConnectionTemplateClientTest : getAllConnectionTemplates : no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("ConnectionTemplateClientTest : getAllConnectionTemplates : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("ConnectionTemplateClientTest : getAllConnectionTemplates : No response from appliance : "
                    + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("ConnectionTemplateClientTest : getAllConnectionTemplates : arguments are null ");
        }
    }

    public void getDefaultConnectionTemplateForConnectionTemplate() throws InstantiationException, IllegalAccessException {
        ConnectionTemplate connectionTemplateDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            connectionTemplateDto = connectionTemplateClient.getDefaultConnectionTemplateForConnectionTemplate(params);

            System.out.println("ConnectionTemplateClientTest : getDefaultConnectionTemplateForConnectionTemplate : "
                    + "connectionTemplate object returned to client : " + connectionTemplateDto.toString());
        } catch (SDKResourceNotFoundException ex) {
            System.out.println("ConnectionTemplateClientTest : getDefaultConnectionTemplateForConnectionTemplate :"
                    + " resource not found to delete : ");
        } catch (SDKBadRequestException ex) {
            System.out.println("ConnectionTemplateClientTest : getDefaultConnectionTemplateForConnectionTemplate :"
                    + " bad request, try again : ");
        } catch (SDKNoSuchUrlException ex) {
            System.out.println("ConnectionTemplateClientTest : getDefaultConnectionTemplateForConnectionTemplate :"
                    + " no such url : " + params.getUrl());
        } catch (SDKApplianceNotReachableException e) {
            System.out.println("ConnectionTemplateClientTest : getDefaultConnectionTemplateForConnectionTemplate :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        } catch (SDKNoResponseException ex) {
            System.out.println("ConnectionTemplateClientTest : getDefaultConnectionTemplateForConnectionTemplate :"
                    + " No response from appliance : " + params.getHostname());
        } catch (SDKInvalidArgumentException ex) {
            System.out.println("LoginSessionsImplTest : getDefaultConnectionTemplateForConnectionTemplate : "
                    + "arguments are null ");
        } catch (SDKTasksException e) {
            System.out.println("LoginSessionsImplTest : getDefaultConnectionTemplateForConnectionTemplate : "
                    + "errors in task, please check task " + "resource for more details ");
        }

    }

    public static void main(final String[] args) throws Exception {
        ConnectionTemplateClientSample client = new ConnectionTemplateClientSample();

        client.getConnectionTemplate();
        client.updateConnectionTemplate();
        client.getAllConnectionTemplates();
        client.getDefaultConnectionTemplateForConnectionTemplate();
    }

}
