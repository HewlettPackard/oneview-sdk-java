/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.ov.sdk.dto.FcNetworkCollection;
import com.hp.ov.sdk.dto.JsonRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.tasks.TaskServiceManager;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.SdkUtils;

public class FcNetworkClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(FcNetworkClientTest.class);

    private FcNetworkClient fcNetworkClient;

    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ResourceDtoUtils resourceDtoUtils;

    @Autowired
    private JSONObject jsonObject;

    private TaskResourceV2 taskResourceV2;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskMonitorManager taskMonitorManager;

    @Autowired
    private TaskServiceManager taskServiceManager;

    @Autowired
    private SampleRestParams sampleRestParams;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "FC_Network_C";
    private static final String resourceId = "1563cbe0-ae81-480c-8f65-18b8719de37a";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        fcNetworkClient = (FcNetworkClient) ctx.getBean("fcNetworkClientImpl");
        resourceDtoUtils = (ResourceDtoUtils) ctx.getBean("resourceDtoUtils");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetFcNetworkById()
            throws InstantiationException,
            IllegalAccessException
    {
        FcNetwork fcNetworkDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            fcNetworkDto = fcNetworkClient.getFcNetwork(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkById :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkById :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkById :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkById :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetFcNetworkById :"
                    + " arguments are null ");
        }

        logger.info("FcNetworkClientTest : testGetFcNetworkById :"
                + " fcNetwork group object returned to client : "
                + fcNetworkDto.toString());
    }

    @Test
    public void testGetAllFcNetwork()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        FcNetworkCollection fcNetworkCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            fcNetworkCollectionDto = fcNetworkClient.getAllFcNetworks(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testGetAllFcNetwork "
                    + "resource you are looking is not found");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testGetAllFcNetwork :"
                    + " no such url : " + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testGetAllFcNetwork :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FcNetworkClientTest : testGetAllFcNetwork :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllFcNetwork :"
                    + " arguments are null ");
        }
        logger.info("FcNetworkClientTest : testGetAllFcNetwork :"
                + " fcNetwork groups object returned to client : "
                + fcNetworkCollectionDto.toString());
    }

    @Test
    public void testGetFcNetworkByFilter()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        FcNetworkCollection fcNetworkCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            fcNetworkCollectionDto = fcNetworkClient.getFcNetworkByFilter(
                    params, 0, 10);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByFilter "
                    + ": resource you are looking is not found ");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByFilter :"
                    + " no such url : " + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByFilter :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByFilter :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetFcNetworkByFilter :"
                    + " arguments are null ");
        }
        logger.info("FcNetworkClientTest : testGetFcNetworkByFilter :"
                + " fcNetwork groups object returned to client : "
                + fcNetworkCollectionDto.toString());
    }

    @Test
    public void testGetFcNetworkByName()
            throws InstantiationException,
            IllegalAccessException
    {
        FcNetwork fcNetworkDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            fcNetworkDto = fcNetworkClient.getFcNetworkByName(params,
                    resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByName :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByName :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByName :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FcNetworkClientTest : testGetFcNetworkByName :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetFcNetworkByName :"
                    + " arguments are null ");
        }

        logger.info("FcNetworkClientTest : testGetFcNetworkByName :"
                + " fcNetwork group object returned to client : "
                + fcNetworkDto.toString());
    }

    @Test
    public void testCreateFcNetwork()
            throws InstantiationException,
            IllegalAccessException
    {
        boolean testfailed = false;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create network request body
            final FcNetwork fcNetworkDto = resourceDtoUtils
                    .buildFcNetworkDto(resourceName);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcNetworkClient.createFcNetwork(params,
                    fcNetworkDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetwork : "
                    + "resource you are looking is not found");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetwork : "
                    + "bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetwork : "
                    + "no such url : " + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetwork : "
                    + "Applicance Not reachabe at : " + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetwork : "
                    + "arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetwork : "
                    + "errors in task, please check task resource for "
                    + "more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("FcNetworkClientTest : testCreateFcNetwork : fcNetwork"
                + " group object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateFcNetwork()
            throws InstantiationException,
            IllegalAccessException
    {
        String resourceId = null;
        FcNetwork fcNetworkDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using name

            fcNetworkDto = fcNetworkClient.getFcNetworkByName(params,
                    resourceName);
            fcNetworkDto.setName(resourceName + "_updated");
            if (null != fcNetworkDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(fcNetworkDto
                        .getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcNetworkClient.updateFcNetwork(params,
                    resourceId, fcNetworkDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testUpdateFcNetwork :"
                    + " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("FcNetworkClientTest : testUpdateFcNetwork :"
                    + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testUpdateFcNetwork :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testUpdateFcNetwork :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FcNetworkClientTest : testUpdateFcNetwork :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateFcNetwork : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateFcNetwork : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("FcNetworkClientTest : testUpdateFcNetwork : "
                + "FcNetwork group object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteFcNetwork()
            throws InstantiationException,
            IllegalAccessException
    {
        String resourceId = null;
        FcNetwork fcNetworkDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using name
            fcNetworkDto = fcNetworkClient.getFcNetworkByName(params,
                    resourceName);

            if (null != fcNetworkDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(fcNetworkDto
                        .getUri());
            }

            // then make sdk service call to get resource
            taskResourceV2 = fcNetworkClient.deleteFcNetwork(params,
                    resourceId, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testDeleteFcNetworkSet :"
                    + " resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testDeleteFcNetworkSet :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testDeleteFcNetwork :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("FcNetworkClientTest : testDeleteFcNetwork : "
                    + "No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteFcNetwork :"
                    + " arguments are null ");
            fail("Test failed");
        }

        logger.info("FcNetworkClientTest : testDeleteFcNetwork : "
                + "fcNetwork group object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testCreateFcNetworkUsingJsonRequest()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create network request body
            final FcNetwork fcNetworkDto = buildTestFcNetworkDtoWithJsonRequest();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = fcNetworkClient.createFcNetwork(params,
                    fcNetworkDto, false, true);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetworkUsingJsonRequest : "
                    + "resource you are looking is not found");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetworkUsingJsonRequest : "
                    + "bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetworkUsingJsonRequest : "
                    + "no such url : " + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetworkUsingJsonRequest : "
                    + "Applicance Not reachabe at : " + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetworkUsingJsonRequest : "
                    + "arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("FcNetworkClientTest : testCreateFcNetworkUsingJsonRequest : "
                    + "errors in task, please check task resource for "
                    + "more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("FcNetworkClientTest : testCreateFcNetworkUsingJsonRequest : fcNetwork"
                + " group object returned to client : "
                + taskResourceV2.toString());
    }

    private FcNetwork buildTestFcNetworkDtoWithJsonRequest()
    {
        final FcNetwork fcNetworkDto = new FcNetwork();

        final JsonRequest jsonRequest = new JsonRequest();
        jsonRequest
                .setBody("{\"type\":\"fc-networkV2\",\"fabricType\":\"FabricAttach\",\"linkStabilityTime\":30,\"managedSanUri\":null,\"autoLoginRedistribution\":true,\"description\":null,\"name\":\"FC_Network_A\",\"state\":\"Active\",\"category\":\"fc-networks\"}");
        fcNetworkDto.setJsonRequest(jsonRequest);

        return fcNetworkDto;
    }
}
