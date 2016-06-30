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
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * ConnectionTemplateClientSample is a sample program to consume default connection configuration characteristics of
 * HPE OneView. It invokes APIs of ConnectionTemplateClient which is in sdk library to perform GET/PUT
 * operations on connection template resource
 */
public class ConnectionTemplateClientSample {

    private final ConnectionTemplateClient connectionTemplateClient;

    // test values - user input
    // ================================
    private static final String CONNECTION_TEMPLATE_RESOURCE_NAME = "name-187284325-1466114054049";
    private static final String CONNECTION_TEMPLATE_RESOURCE_ID = "0817b9e1-8ed3-4509-96d5-9a10a755bbe5";
    private static final Double CONNECTION_TEMPLATE_MAX_BANDWIDTH = Double.valueOf(7000);
    private static final Double CONNECTION_TEMPLATE_TYPICAL_BANDWIDTH = Double.valueOf(2000);
    // ================================

    private ConnectionTemplateClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.connectionTemplateClient = oneViewClient.connectionTemplate();
    }

    private void getConnectionTemplate() {
        ConnectionTemplate connectionTemplate = this.connectionTemplateClient.getById(CONNECTION_TEMPLATE_RESOURCE_ID);

        System.out.println("ConnectionTemplateClientSample : getConnectionTemplate : " +
                "ConnectionTemplate object returned to client : " + connectionTemplate.toJsonString());
    }

    private void getAllConnectionTemplates() {
        ResourceCollection<ConnectionTemplate> connectionTemplates = this.connectionTemplateClient.getAll();

        System.out.println("ConnectionTemplateClientSample : getAllConnectionTemplates : " +
                "ConnectionTemplates returned to client : " + connectionTemplates.toJsonString());
    }

    private void getConnectionTemplateByName() {
        ConnectionTemplate connectionTemplate = this.connectionTemplateClient.getByName(CONNECTION_TEMPLATE_RESOURCE_NAME).get(0);

        System.out.println("ConnectionTemplateClientSample : getConnectionTemplateByName : " +
                "ConnectionTemplate object returned to client : " + connectionTemplate.toJsonString());
    }

    private void getDefaultConnectionTemplate() {
        ConnectionTemplate connectionTemplate = this.connectionTemplateClient.getDefaultConnectionTemplate();

        System.out.println("ConnectionTemplateClientSample : getDefaultConnectionTemplate : " +
                "ConnectionTemplate object returned to client : " + connectionTemplate.toJsonString());
    }

    private void updateConnectionTemplate() {
        ConnectionTemplate connectionTemplate = this.connectionTemplateClient.getByName(CONNECTION_TEMPLATE_RESOURCE_NAME).get(0);
        String resourceId = connectionTemplate.getResourceId();

        Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(CONNECTION_TEMPLATE_MAX_BANDWIDTH);
        bandwidth.setTypicalBandwidth(CONNECTION_TEMPLATE_TYPICAL_BANDWIDTH);

        connectionTemplate.setBandwidth(bandwidth);
        connectionTemplate.setETag(null);
        connectionTemplate.setUri(null);

        ConnectionTemplate updatedConnectionTemplate = this.connectionTemplateClient.update(resourceId, connectionTemplate);

        System.out.println("ConnectionTemplateClientSample : updateConnectionTemplate : " +
                "ConnectionTemplate object returned to client : " + updatedConnectionTemplate.toJsonString());
    }

    public static void main(final String[] args) {
        ConnectionTemplateClientSample client = new ConnectionTemplateClientSample();

        client.getAllConnectionTemplates();
        client.getConnectionTemplate();
        client.getConnectionTemplateByName();
        client.getDefaultConnectionTemplate();
        client.updateConnectionTemplate();
    }

}
