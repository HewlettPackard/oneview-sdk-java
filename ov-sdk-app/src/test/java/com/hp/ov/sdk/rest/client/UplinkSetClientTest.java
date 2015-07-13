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
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClientTest;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.LoginSessions;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.tasks.TaskServiceManager;
import com.hp.ov.sdk.util.SdkUtils;

public class UplinkSetClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(UplinkSetClientTest.class);
    
    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;  

    private UplinkSetClient uplinkSetClient;
    
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
    private static final String resourceName = "Test_uplink_eth";
	private static final String resourceId = "5699d4f9-e2b1-445c-953b-19f432ed3747";
	//================================

	//TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        
        uplinkSetClient = (UplinkSetClient) ctx.getBean("uplinkSetClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetUplinkSetById()
            throws InstantiationException,
            IllegalAccessException
    {
    	UplinkSets uplinkSetDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            uplinkSetDto = uplinkSetClient.getUplinkSet(params, resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("UplinkSetClientTest : "
					+ "testGetUplinkSetById : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
			logger.error("UplinkSetClientTest : testGetUplinkSetById : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("UplinkSetClientTest : "
					+ "testGetUplinkSetById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("UplinkSetClientTest : "
					+ "testGetUplinkSetById : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("UplinkSetClientTest : "
					+ "testGetUplinkSetById : arguments are null ");
        }

        logger.info("UplinkSetClientTest : "
				+ "testGetUplinkSetById : uplink set object returned to client : "
                + uplinkSetDto.toString());
    }

    @Test
    public void testGetAllUplinkSet()
            throws InstantiationException,
            IllegalAccessException
    {
    	UplinkSetCollectionV2 uplinkSetCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            uplinkSetCollectionDto = uplinkSetClient.getAllUplinkSet(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("UplinkSetClientTest : "
					+ "testGetAllUplinkSet : resource you are looking is not found");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("UplinkSetClientTest : "
                    + "testGetAllUplinkSet : no such url : " + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("UplinkSetClientTest : "
                    + "testGetAllUplinkSet : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("UplinkSetClientTest : "
                    + "testGetAllUplinkSet : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : "
                    + "testGetAllUplinkSet : arguments are null ");
        }

        logger.info("UplinkSetClientTest : testGetAllUplinkSet "
                + ": uplink set object returned to client : "
                + uplinkSetCollectionDto.toString());
    }

    @Test
    public void testDeleteUplinkSet()
            throws InstantiationException,
            IllegalAccessException
    {
		String resourceId = null;
		UplinkSets uplinkSetDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            //fetch uplink set uri
			uplinkSetDto = uplinkSetClient.getUplinkSetsByName(params,
					resourceName);
			
			if (null != uplinkSetDto.getUri())
			{
				resourceId = sdkUtils.getResourceIdFromUri(uplinkSetDto
						.getUri());
			}

			// then make sdk service call to get resource
			taskResourceV2 = uplinkSetClient
					.deleteUplinkSet(params, resourceId,
							false);
		}
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("UplinkSetClientTest : testDeleteUplinkSet : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("UplinkSetClientTest : testDeleteUplinkSet : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("UplinkSetClientTest : testDeleteUplinkSet : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("UplinkSetClientTest : testDeleteUplinkSet : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("UplinkSetClientTest : testDeleteUplinkSet : arguments are null ");
        }

        logger.info("UplinkSetClientTest : testDeleteUplinkSet : uplink set deleted, task status : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateUplinkSet()
            throws InstantiationException,
            IllegalAccessException
    {
    	UplinkSets uplinkSetDto = null;
		String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            uplinkSetDto = uplinkSetClient.getUplinkSetsByName(params, resourceName);
            //fetch uplinkset uri
			if (null != uplinkSetDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(uplinkSetDto
						.getUri());
			}

			// Change updateSetName
			uplinkSetDto.setName(uplinkSetDto.getName());

			// update uplink set
			taskResourceV2 = uplinkSetClient
					.updateUplinkSet(params, resourceId,
							uplinkSetDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("UplinkSetClientTest : "
					+ "testUpdateUplinkSet : resource you are looking is not found for update");
            fail("Test failed");
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("UplinkSetClientTest : testUpdateUplinkSet :"
					+ " bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("UplinkSetClientTest : testUpdateUplinkSet : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("UplinkSetClientTest : "
                    + "testUpdateUplinkSet : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("UplinkSetClientTest : "
                    + "testUpdateUplinkSet : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("UplinkSetClientTest : "
                    + "testUpdateUplinkSet : arguments are null ");
        }
        catch (SDKTasksException e)
        {
            logger.error("UplinkSetClientTest : testUpdateUplinkSet : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }

        logger.info("UplinkSetClientTest :  testUpdateUplinkSet : uplink set updated, task status : "
                + taskResourceV2.toString());
    }

}
