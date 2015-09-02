/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.connectiontemplate;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.ConnectionTemplateCollection;
import com.hp.ov.sdk.dto.generated.Bandwidth;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.ConnectionTemplateClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

/*
 * ConnectionTemplateClientSample is a sample program to consume default connection configuration characteristics of 
 * HP OneView. It invokes APIs of ConnectionTemplateClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on connection template resource
 */
public class ConnectionTemplateClientSample {
    private RestParams params;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static UrlUtils urlUtils;
    private static ResourceDtoUtils resourceDtoUtils;
    private static ConnectionTemplateClient connectionTemplateClient;

    // test values - user input
    // ================================
    private static final String resourceName = "name1238697625-1438946680695";
    private static final String resourceId = "a0f41234-8175-48e4-bcf4-a92e9206bd24";
    private static final Double maxBandwidth = (double) 8000;
    private static final Double minBandwidth = (double) 2000;

    // ================================

    private static void init() {
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        sampleRestParams = new SampleRestParams();
        resourceDtoUtils = HPOneViewSdkBeanFactory.getResourceDtoUtils();
        connectionTemplateClient = HPOneViewSdkBeanFactory.getConnectionTemplateClient();
    }

    public void getConnectionTemplate() throws InstantiationException, IllegalAccessException {
        ConnectionTemplate connectionTemplateDto = null;
        // first get the session Id
        try {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            System.out.println("LoginSessionsImplTest : getConnectionTemplate : arguments are null ");
        }

    }

    public void updateConnectionTemplate() throws InstantiationException, IllegalAccessException {
        ConnectionTemplate connectionTemplateDto = null;
        // first get the session Id
        String resourceId = null;
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            connectionTemplateDto = connectionTemplateClient.getConnectionTemplateByName(params, resourceName);

            if (null != connectionTemplateDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(connectionTemplateDto.getUri());
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
            System.out.println("ConnectionTemplateClientTest : updateConnectionTemplate :" + " resource not found to delete : ");
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
            System.out.println("LoginSessionsImplTest : updateConnectionTemplate : " + "errors in task, please check task "
                    + "resource for more details ");
        }

    }

    public void getAllConnectionTemplates() throws InstantiationException, IllegalAccessException {
        ConnectionTemplateCollection connectionTemplateCollectionDto = null;
        // first get the session Id
        try {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

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

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        ConnectionTemplateClientSample client = new ConnectionTemplateClientSample();
        client.getConnectionTemplate();
        client.updateConnectionTemplate();
        client.getAllConnectionTemplates();
        client.getDefaultConnectionTemplateForConnectionTemplate();
    }

}
