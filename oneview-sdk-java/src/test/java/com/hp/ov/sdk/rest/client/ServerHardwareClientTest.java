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

import com.hp.ov.sdk.dto.PhysicalServerPowerControl;
import com.hp.ov.sdk.dto.PhysicalServerPowerState;
import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ServerHardware;
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
import com.hp.ov.sdk.util.SdkUtils;

public class ServerHardwareClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(ServerHardwareClientTest.class);

    private ServerHardwareClient serverHardwareClient;

    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;

    private ServerHardware serverHardwareDto;
    private ServerHardwareCollection serverHardwareCollectionDto;
    private ServerPowerControlRequest serverPowerControlRequestDto;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private JSONObject jsonObject;

    @Autowired
    private SampleRestParams sampleRestParams;

    private TaskResourceV2 taskResourceV2;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "Encl1, bay 14";
    private static final String resourceId = "37333036-3831-4753-4831-30315838524E";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");

        serverHardwareClient = (ServerHardwareClient) ctx
                .getBean("serverHardwareClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetServerHardwareById()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id

        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make rest call to get resource
            serverHardwareDto = serverHardwareClient.getServerHardware(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareById : "
                    + "resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareById : "
                    + "no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareById : "
                    + "Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareById : "
                    + "No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareById : "
                    + "arguments are null ");
        }

        logger.info("ServerHardwareClientTest : testGetServerHardwareById : "
                + "server hardware object returned to client : "
                + serverHardwareDto.toString());
    }

    @Test
    public void testAllGetServerHardwares()
    {
        try
        {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make rest call to get resource
            serverHardwareCollectionDto = serverHardwareClient
                    .getAllServerHardwares(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ServerHardwareClientTest : testAllGetServerHardwares : resource you are looking is not found ");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ServerHardwareClientTest : testAllGetServerHardwares : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ServerHardwareClientTest : testAllGetServerHardwares : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("ServerHardwareClientTest : testAllGetServerHardwares : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ServerHardwareClientTest : testAllGetServerHardwares : arguments are null ");
        }
        logger.info("ServerHardwareClientTest : testAllGetServerHardwares : "
                + "server hardware object returned to client : "
                + serverHardwareCollectionDto.toString());

    }

    @Test
    public void testGetServerHardwareWithNoProfile()
            throws InstantiationException, IllegalAccessException
    {

        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);
            // then make rest call to get resource
            serverHardwareCollectionDto = serverHardwareClient
                    .getServerHardwareWithNoProfile(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareWithNoProfile : "
                    + "resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareWithNoProfile : "
                    + "no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareWithNoProfile : "
                    + "Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareWithNoProfile : "
                    + "No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ServerHardwareClientTest : testGetServerHardwareWithNoProfile : "
                    + "arguments are null ");
        }

        logger.info("ServerHardwareClientTest : testGetServerHardwareWithNoProfile : "
                + "server hardware collection object returned to client : "
                + serverHardwareCollectionDto.toString());
    }

    @Test
    public void GetServerHardwareByName()
            throws InstantiationException,
            IllegalAccessException
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);
            // then make rest call to get resource
            serverHardwareDto = serverHardwareClient.getServerHardwareByName(
                    params, resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ServerHardwareClientTest : GetServerHardwareByName : "
                    + "resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ServerHardwareClientTest : GetServerHardwareByName : "
                    + "no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ServerHardwareClientTest : GetServerHardwareByName : "
                    + "Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("ServerHardwareClientTest : GetServerHardwareByName : "
                    + "No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ServerHardwareClientTest : GetServerHardwareByName : "
                    + "arguments are null ");
        }
        if (serverHardwareDto != null)
        {
            logger.info("ServerHardwareClientTest : GetServerHardwareByName : "
                    + "server hardware object returned to client : "
                    + serverHardwareDto.toString());
        }
        else
        {
            logger.info("ServerHardwareClientTest : GetServerHardwareByName : "
                    + "server hardware object returned to client : no server hardware found for the name"
                    + resourceName);
        }
    }

    @Test
    public void testPowerServer()
            throws InstantiationException,
            IllegalAccessException
    {
        boolean testfailed = false;
        String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create network request body
            serverPowerControlRequestDto = buildTestserverPowerControlRequestDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */

            // fetch resource Id using name
            serverHardwareDto = serverHardwareClient
                    .getServerHardwareByName(params, resourceName);

            if (null != serverHardwareDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(serverHardwareDto
                        .getUri());
            }

            taskResourceV2 = serverHardwareClient.powerServer(params,
                    resourceId,
                    serverPowerControlRequestDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ServerHardwareClientTest : testPowerServer : resource you are looking is not found");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("ServerHardwareClientTest : testPowerServer : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ServerHardwareClientTest : testPowerServer : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ServerHardwareClientTest : testPowerServer : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ServerHardwareClientTest : testPowerServer : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("ServerHardwareClientTest : testPowerServer : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("ServerHardwareClientTest : testPowerServer : ServerPowerControlRequest group object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testGetPowerState()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id
        String powerState = null;
        String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);
            // then make rest call to get resource

            // fetch resource Id using name
            serverHardwareDto = serverHardwareClient
                    .getServerHardwareByName(params, resourceName);

            if (null != serverHardwareDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(serverHardwareDto
                        .getUri());
            }

            powerState = serverHardwareClient.getPowerState(params,
                    resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ServerHardwareClientTest : testGetPowerState : "
                    + "resource not found : ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ServerHardwareClientTest : testGetPowerState : "
                    + "no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ServerHardwareClientTest : testGetPowerState : "
                    + "Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("ServerHardwareClientTest : testGetPowerState : "
                    + "No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ServerHardwareClientTest : testGetPowerState : "
                    + "arguments are null ");
        }

        logger.info("ServerHardwareClientTest : testGetPowerStateResource : "
                + "server hardware power state returned to client : "
                + powerState);
    }

    private ServerPowerControlRequest buildTestserverPowerControlRequestDto()
    {
        final ServerPowerControlRequest serverPowerControlRequestDto = new ServerPowerControlRequest();
        serverPowerControlRequestDto
                .setPowerControl(PhysicalServerPowerControl.MomentaryPress);
        serverPowerControlRequestDto.setPowerState(PhysicalServerPowerState.On);
        return serverPowerControlRequestDto;
    }

}
