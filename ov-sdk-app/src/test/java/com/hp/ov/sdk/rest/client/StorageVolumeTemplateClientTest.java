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
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
import com.hp.ov.sdk.dto.TemplateProvisioningData;
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

public class StorageVolumeTemplateClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(StorageVolumeTemplateClientTest.class);

    private StorageVolumeTemplateClient storageVolumeTemplateClient;
    @Autowired
    private StoragePoolClient storagePoolClient;

    @Autowired
    private StorageSystemClient storageSystemClient;
    
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
    private static final String resourceName = "FusionTemplateExample";
    private static final String storageSystemName = "ThreePAR7200-3050";
    private static final String storagePoolName = "FST_CPG1";
    private static final String capacity = "235834383322";
	private static final String resourceId = "09a86bf2-3109-4c62-94b9-2350063feff9";
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        storageVolumeTemplateClient = (StorageVolumeTemplateClient) ctx
                .getBean("storageVolumeTemplateClientImpl");
        storageSystemClient = (StorageSystemClient) ctx
                .getBean("storageSystemClientImpl");
        storagePoolClient = (StoragePoolClient) ctx
                .getBean("storagePoolClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetStorageVolumeTemplateById()
            throws InstantiationException,
            IllegalAccessException
    {
    	StorageVolumeTemplate storageVolumeTemplateDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
			// then make sdk service call to get resource
			storageVolumeTemplateDto = storageVolumeTemplateClient
					.getStorageVolumeTemplate(params, resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateById : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateById : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateById : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStorageVolumeTemplateById : arguments are null ");
        }

        logger.info("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateById : storageVolumeTemplate object returned to client : "
                + storageVolumeTemplateDto.toString());
    }

    @Test
    public void testGetAllStorageVolumeTemplate()
            throws InstantiationException, IllegalAccessException,
            SDKResourceNotFoundException, SDKNoSuchUrlException
    {
    	StorageVolumeTemplateCollection storageVolumeTemplateCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storageVolumeTemplateCollectionDto = storageVolumeTemplateClient
                    .getAllStorageVolumeTemplates(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeTemplateClientTest : testGetAllStorageVolumeTemplate : resource you are looking is not found "
                    + params.getHostname());
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetAllStorageVolumeTemplate : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetAllStorageVolumeTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetAllStorageVolumeTemplate : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllStorageVolumeTemplate : arguments are null ");
        }
        logger.info("StorageVolumeTemplateClientTest : testGetAllStorageVolumeTemplate : storageVolumeTemplate object returned to client : "
                + storageVolumeTemplateCollectionDto.toString());
    }

    @Test
    public void testGetStorageVolumeTemplateByName()
            throws InstantiationException, IllegalAccessException
    {
    	StorageVolumeTemplate storageVolumeTemplateDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storageVolumeTemplateDto = storageVolumeTemplateClient
                    .getStorageVolumeTemplateByName(params, resourceName);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateByName : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateByName : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateByName : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateByName : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStorageVolumeTemplateByName : arguments are null ");
        }

        logger.info("StorageVolumeTemplateClientTest : testGetStorageVolumeTemplateByName : storageVolumeTemplate object returned to client : "
                + storageVolumeTemplateDto.toString());
    }

    @Test
    public void testCreateStorageVolumeTemplate()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
        StorageVolumeTemplate storageVolumeTemplateDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create storageVolumeTemplate request body
            storageVolumeTemplateDto = buildTestStorageVolumeTemplateDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            storageVolumeTemplateDto = storageVolumeTemplateClient
                    .createStorageVolumeTemplate(params,
                            storageVolumeTemplateDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeTemplateClientTest : testCreateStorageVolumeTemplate : resource you are looking is not found "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
			logger.error("StorageVolumeTemplateClientTest : testCreateStorageVolumeTemplate : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testCreateStorageVolumeTemplate : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeTemplateClientTest : testCreateStorageVolumeTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateStorageVolumeTemplate : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateStorageVolumeTemplate : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("StorageVolumeTemplateClientTest : testCreateStorageVolumeTemplate : storageVolumeTemplate object returned to client : "
                + storageVolumeTemplateDto.toString());
    }

    @Test
    public void testUpdateStorageVolumeTemplate()
            throws InstantiationException, IllegalAccessException
    {
    	StorageVolumeTemplate storageVolumeTemplateDto = null;
		String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storageVolumeTemplateDto = storageVolumeTemplateClient
                    .getStorageVolumeTemplateByName(params, resourceName);

			if (null != storageVolumeTemplateDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(storageVolumeTemplateDto
						.getUri());
			}
            storageVolumeTemplateDto.setName(resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            storageVolumeTemplateDto = storageVolumeTemplateClient
                    .updateStorageVolumeTemplate(params,
							resourceId,
							storageVolumeTemplateDto, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testUpdateStorageVolumeTemplate :"
					+ " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testUpdateStorageVolumeTemplate :"
					+ " bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testUpdateStorageVolumeTemplate :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeTemplateClientTest : testUpdateStorageVolumeTemplate :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testUpdateStorageVolumeTemplate :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateStorageVolumeTemplate : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateStorageVolumeTemplate : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("StorageVolumeTemplateClientTest : testUpdateStorageVolumeTemplate : "
                + "storageVolumeTemplate object returned to client : "
                + storageVolumeTemplateDto.toString());
    }

    @Test
    public void testDeleteStorageVolumeTemplate()
            throws InstantiationException, IllegalAccessException
    {
    	StorageVolumeTemplate storageVolumeTemplateDto = null;
        String deletedString = "";
		String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
			 storageVolumeTemplateDto = storageVolumeTemplateClient
					.getStorageVolumeTemplateByName(params, resourceName);
			 
			if (null != storageVolumeTemplateDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(storageVolumeTemplateDto
						.getUri());
			}

			// then make sdk service call to get resource
			deletedString = storageVolumeTemplateClient
					.deleteStorageVolumeTemplate(params,
							resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeTemplateClientTest : testDeleteStorageVolumeTemplate : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testDeleteStorageVolumeTemplate : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeTemplateClientTest : testDeleteStorageVolumeTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeTemplateClientTest : testDeleteStorageVolumeTemplate : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteStorageVolumeTemplate : arguments are null ");
        }

        logger.info("StorageVolumeTemplateClientTest : testDeleteStorageVolumeTemplate : storageVolumeTemplate object returned to client : "
                + deletedString);
    }

    private StorageVolumeTemplate buildTestStorageVolumeTemplateDto()
    {
        StorageVolumeTemplate dto = new StorageVolumeTemplate();

        dto.setName(resourceName);
        dto.setState("Normal");
        dto.setDescription("Example Template");
        dto.setStateReason("None");
        dto.setStorageSystemUri(storageSystemClient.getStorageSystemByName(
                params, storageSystemName).getUri());
        TemplateProvisioningData provisioning = new TemplateProvisioningData();
        provisioning.setProvisionType("Thin");
        provisioning.setShareable(true);
        provisioning.setCapacity(capacity);
        provisioning.setStoragePoolUri(storagePoolClient.getStoragePoolByName(
                params, storagePoolName).getUri());
        dto.setProvisioning(provisioning);
        dto.setType(ResourceCategory.RC_STORAGE_VOLUME_TEMPLATE);
        return dto;
    }
}
