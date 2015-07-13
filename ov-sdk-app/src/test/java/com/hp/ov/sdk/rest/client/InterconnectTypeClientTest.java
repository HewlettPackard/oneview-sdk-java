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

import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.dto.InterconnectTypeCollectionV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.dto.generated.InterconnectTypes;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClientTest;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.LoginSessions;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.tasks.TaskServiceManager;
import com.hp.ov.sdk.util.SdkUtils;

public class InterconnectTypeClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(InterconnectTypeClientTest.class);

    private InterconnectTypeClient interconnectTypeClient;
    
    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;
    
    @Autowired
    private JSONObject jsonObject;

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
    private static final String resourceName = "HP VC FlexFabric-20/40 F8 Module";
	private static final String resourceId = "f4a7d44e-b0c5-4c9c-9c83-142e24aac7b3";
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.
	
    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        interconnectTypeClient = (InterconnectTypeClient) ctx
                .getBean("interconnectTypeClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetInterconnectTypeById()
            throws InstantiationException,
            IllegalAccessException
    {
    	InterconnectTypes interconnectTypeDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

			// then make sdk service call to get resource
			interconnectTypeDto = interconnectTypeClient.getInterconnectType(
					params, resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeById :"
					+ " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeById :"
                    + " no such url : " + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeById :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeById :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetInterconnectTypeById :"
                    + " arguments are null ");
        }

        logger.info("InterconnectTypeClientTest : testGetInterconnectTypeById :"
                + " interconnect type object returned to client : "
                + interconnectTypeDto.toString());
    }

    @Test
    public void testGetAllInterconnectType()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
    	InterconnectTypeCollectionV2 interconnectTypeCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            interconnectTypeCollectionDto = interconnectTypeClient
                    .getAllInterconnectType(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetAllInterconnectType "
					+ ": resource you are looking is not found");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetAllInterconnectType :"
                    + " no such url : " + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("InterconnectTypeClientTest : testGetAllInterconnectType :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetAllInterconnectType :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllInterconnectType :"
                    + " arguments are null ");
        }
        logger.info("InterconnectTypeClientTest : testGetAllInterconnectType :"
                + " interconnect type object returned to client : "
                + interconnectTypeCollectionDto.toString());
    }

    @Test
    public void testGetInterconnectTypeByName()
            throws InstantiationException,
            IllegalAccessException
    {
    	InterconnectTypes interconnectTypeDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            interconnectTypeDto = interconnectTypeClient
                    .getInterconnectTypeByName(params, resourceName);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeByName :"
                    + " resource not found : ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeByName :"
                    + " no such url : " + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeByName :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("InterconnectTypeClientTest : testGetInterconnectTypeByName :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetInterconnectTypeByName :"
                    + " arguments are null ");
        }

        logger.info("InterconnectTypeClientTest : testGetInterconnectTypeByName :"
                + " interconnect type object returned to client : "
                + interconnectTypeDto.toString());
    }
}
