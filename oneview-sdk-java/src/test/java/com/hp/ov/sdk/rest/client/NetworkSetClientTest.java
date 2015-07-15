/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.NetworkSets;
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

public class NetworkSetClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(NetworkSetClientTest.class);

    private NetworkSetClient networkSetClient;

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
    private static final String resourceName = "NetworkSet_Test_one";
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402",
            "Prod_403", "Prod_404");
    private static final String resourceId = "d5a4b31d-7ec0-49c3-be83-486f41e87d14";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        networkSetClient = (NetworkSetClient) ctx
                .getBean("networkSetClientImpl");
        resourceDtoUtils = (ResourceDtoUtils) ctx.getBean("resourceDtoUtils");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetNetworkSetById()
            throws InstantiationException,
            IllegalAccessException
    {
        NetworkSets networkSetDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            networkSetDto = networkSetClient.getNetworkSets(params, resourceId);

        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetById : "
                    + "resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetById : "
                    + "no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetById : "
                    + "Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetById : "
                    + "No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetNetworkSetById : "
                    + "arguments are null ");
        }

        logger.info("NetworkSetClientTest : testGetNetworkSetById : "
                + "network set object returned to client : "
                + networkSetDto.toString());
    }

    @Test
    public void testGetAllNetworkSet()
            throws InstantiationException,
            IllegalAccessException
    {
        NetworkSetCollection networkSetCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            networkSetCollectionDto = networkSetClient
                    .getAllNetworkSets(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkSetClientTest : testGetAllNetworkSet : "
                    + "resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkSetClientTest : testGetAllNetworkSet : "
                    + "no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkSetClientTest : testGetAllNetworkSet : "
                    + "Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkSetClientTest : testGetAllNetworkSet : "
                    + "No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllNetworkSet : "
                    + "arguments are null ");
        }

        logger.info("NetworkSetClientTest : testGetAllNetworkSet : "
                + "network set object returned to client : "
                + networkSetCollectionDto.getCount());
    }

    @Test
    public void testGetNetworkSetByName()
            throws InstantiationException,
            IllegalAccessException
    {
        NetworkSets networkSetDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            networkSetDto = networkSetClient.getNetworkSetsByName(params,
                    resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetByName : "
                    + "resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetByName : "
                    + "no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetByName : "
                    + "Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkSetClientTest : testGetNetworkSetByName : "
                    + "No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetNetworkSetByName : "
                    + "arguments are null ");
        }

        logger.info("NetworkSetClientTest : testGetNetworkSetByName : "
                + "network set object returned to client : "
                + networkSetDto.toString());
    }

    @Test
    public void testCreateNetworkSet()
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

            // create network set request body
            final NetworkSets networkSetDto = resourceDtoUtils.buildNetworkSetDto(
                    params, resourceName, networkNames);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkSetClient.createNetworkSet(params,
                    networkSetDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkSetClientTest : testCreateNetworkSet :"
                    + " resource you are looking is not found ");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("NetworkSetClientTest : testCreateNetworkSet :"
                    + "may be duplicate resource name or invalid inputs. check inputs and try again : ");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkSetClientTest : testCreateNetworkSet : "
                    + "no such url : " + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkSetClientTest : testCreateNetworkSet : "
                    + "Applicance Not reachabe at : " + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateNetworkSet : "
                    + "arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateNetworkSet : "
                    + "errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("NetworkSetClientTest : testCreateNetworkSet : "
                + "network object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateNetworkSet()
            throws InstantiationException,
            IllegalAccessException
    {
        NetworkSets networkSetDto = null;
        String resourceId = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            networkSetDto = networkSetClient.getNetworkSetsByName(params,
                    resourceName);

            if (null != networkSetDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(networkSetDto
                        .getUri());
            }
            // Test name
            networkSetDto.setName(resourceName + "_updated");

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkSetClient.updateNetworkSet(params,
                    resourceId, networkSetDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkSetClientTest : testUpdateNetworkSet :"
                    + " resource you are looking is not found for update");
            fail("Test failed");
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("NetworkSetClientTest : testUpdateNetworkSet :"
                    + "may be duplicate resource name or invalid inputs. check inputs and try again : ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkSetClientTest : testUpdateNetworkSet :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkSetClientTest : testUpdateNetworkSet :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkSetClientTest : testUpdateNetworkSet :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateNetworkSet : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateNetworkSet : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("NetworkSetClientTest : testUpdateNetworkSet : "
                + "network object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteNetworkSet()
            throws InstantiationException,
            IllegalAccessException
    {
        NetworkSets networkSetDto = null;
        String resourceId = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            networkSetDto = networkSetClient.getNetworkSetsByName(params,
                    resourceName);
            if (null != networkSetDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(networkSetDto
                        .getUri());
            }

            // then make sdk service call to get resource
            taskResourceV2 = networkSetClient.deleteNetworkSet(params,
                    resourceId, false);

        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkSetClientTest : testDeleteNetworkSetSet :"
                    + " resource not found for deleting ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkSetClientTest : testDeleteNetworkSetSet :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkSetClientTest : testDeleteNetworkSet :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkSetClientTest : testDeleteNetworkSet : "
                    + "No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteNetworkSet :"
                    + " arguments are null ");
            fail("Test failed");
        }

        logger.info("NetworkSetClientTest : testDeleteNetworkSet : "
                + "network object returned to client : "
                + taskResourceV2.toString());
    }

}
