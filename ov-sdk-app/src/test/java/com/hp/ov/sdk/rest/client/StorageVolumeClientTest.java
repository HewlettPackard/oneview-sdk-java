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
import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.StorageVolumeCollection;
import com.hp.ov.sdk.dto.StorageVolumeProvisioningParameters;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
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

public class StorageVolumeClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(StorageVolumeClientTest.class);

    private StorageVolumeClient storageVolumeClient;
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
    private static final String resourceName = "Volume101";
    private static final String storageSystemName = "ThreePAR7200-3050";
    private static final String storagePoolName = "FST_CPG1";
    private static final String resourceNameForPrivateStorage = "volume103";
	private static final String resourceId = "2F48A4DB-ED73-425B-BF3E-64CF54E371CA";
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        
        storageVolumeClient = (StorageVolumeClient) ctx
                .getBean("storageVolumeClientImpl");
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
    public void testGetStorageVolumeById()
            throws InstantiationException,
            IllegalAccessException
    {
    	StorageVolumeV2 storageVolumeDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

			// then make sdk service call to get resource
			storageVolumeDto = storageVolumeClient.getStorageVolume(params, resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeClientTest : testGetStorageVolumeById : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeClientTest : testGetStorageVolumeById : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeClientTest : testGetStorageVolumeById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeClientTest : testGetStorageVolumeById : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStorageVolumeById : arguments are null ");
        }

        logger.info("StorageVolumeClientTest : testGetStorageVolumeById : storageVolume object returned to client : "
                + storageVolumeDto.toString());
    }

    @Test
    public void testGetAllStorageVolume()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
    	StorageVolumeCollection storageVolumeCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storageVolumeCollectionDto = storageVolumeClient
                    .getAllStorageVolumes(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeClientTest : testGetAllStorageVolume : resource you are looking is not found");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeClientTest : testGetAllStorageVolume : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeClientTest : testGetAllStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeClientTest : testGetAllStorageVolume : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllStorageVolume : arguments are null ");
        }
        logger.info("StorageVolumeClientTest : testGetAllStorageVolume : storageVolume object returned to client : "
                + storageVolumeCollectionDto.toString());
    }

    @Test
    public void testGetStorageVolumeByName()
            throws InstantiationException,
            IllegalAccessException
    {
    	StorageVolumeV2 storageVolumeDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storageVolumeDto = storageVolumeClient.getStorageVolumeByName(
                    params, resourceName);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeClientTest : testGetStorageVolumeByName : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeClientTest : testGetStorageVolumeByName : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeClientTest : testGetStorageVolumeByName : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeClientTest : testGetStorageVolumeByName : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStorageVolumeByName : arguments are null ");
        }

        logger.info("StorageVolumeClientTest : testGetStorageVolumeByName : storageVolume object returned to client : "
                + storageVolumeDto.toString());
    }

    @Test
    public void testCreateStorageVolume()
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

            // create storageVolume request body
            AddStorageVolumeV2 addStorageVolumeDto = buildTestStorageVolumeDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = storageVolumeClient.createStorageVolume(params,
                    addStorageVolumeDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeClientTest : testCreateStorageVolume : resource you are looking is not found");
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
			logger.error("StorageVolumeClientTest : testCreateStorageVolume : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeClientTest : testCreateStorageVolume : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeClientTest : testCreateStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateStorageVolume : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateStorageVolume : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("StorageVolumeClientTest : testCreateStorageVolume : storageVolume object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testCreatePrivateStorageVolume()
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

            // create storageVolume request body
            AddStorageVolumeV2 addStorageVolumeDto = buildTestPrivateStorageVolumeDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = storageVolumeClient.createStorageVolume(params,
                    addStorageVolumeDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeClientTest : testCreatePrivateStorageVolume : resource you are looking is not found");
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
			logger.error("StorageVolumeClientTest : testCreatePrivateStorageVolume : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeClientTest : testCreatePrivateStorageVolume : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeClientTest : testCreatePrivateStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreatePrivateStorageVolume : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreatePrivateStorageVolume : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("StorageVolumeClientTest : testCreatePrivateStorageVolume : storageVolume object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateStorageVolume()
            throws InstantiationException,
            IllegalAccessException
    {
    	StorageVolumeV2 storageVolumeDto = null;
        String updateStorage = null;
		String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storageVolumeDto = storageVolumeClient.getStorageVolumeByName(
                    params, resourceName);

			if (null != storageVolumeDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(storageVolumeDto
						.getUri());
			}
            storageVolumeDto.setName(resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            updateStorage = storageVolumeClient.updateStorageVolume(params,
					resourceId, storageVolumeDto, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("StorageVolumeClientTest : testUpdateStorageVolume :"
					+ " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("StorageVolumeClientTest : testUpdateStorageVolume :"
					+ " bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeClientTest : testUpdateStorageVolume :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeClientTest : testUpdateStorageVolume :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeClientTest : testUpdateStorageVolume :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateStorageVolume : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateStorageVolume : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("StorageVolumeClientTest : testUpdateStorageVolume : "
                + "storageVolume object returned to client : " + updateStorage);
    }

    @Test
    public void testDeleteStorageVolume()
            throws InstantiationException,
            IllegalAccessException
    {
    	StorageVolumeV2 storageVolumeDto = null;
		String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
			storageVolumeDto = storageVolumeClient
					.getStorageVolumeByName(params, resourceName);
			
			if (null != storageVolumeDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(storageVolumeDto
						.getUri());
			}

			// then make sdk service call to get resource
			taskResourceV2 = storageVolumeClient.deleteStorageVolume(params,
					resourceId, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("StorageVolumeClientTest : testDeleteStorageVolume : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("StorageVolumeClientTest : testDeleteStorageVolume : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("StorageVolumeClientTest : testDeleteStorageVolume : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("StorageVolumeClientTest : testDeleteStorageVolume : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteStorageVolume : arguments are null ");
        }

        logger.info("StorageVolumeClientTest : testDeleteStorageVolume : storageVolume object returned to client : "
                + taskResourceV2.toString());
    }

    private AddStorageVolumeV2 buildTestStorageVolumeDto()
    {
        AddStorageVolumeV2 dto = new AddStorageVolumeV2();

        dto.setName(resourceName);
        dto.setDescription("Volume description");
        dto.setStorageSystemUri(storageSystemClient.getStorageSystemByName(
                params, storageSystemName).getUri());
        dto.setType(ResourceCategory.RC_ADD_STORAGE_VOLUME);

        StorageVolumeProvisioningParameters provisioningParameters = new StorageVolumeProvisioningParameters();
        provisioningParameters.setProvisionType("Full");
        provisioningParameters.setShareable(true);
        provisioningParameters.setRequestedCapacity("1234567898");
        provisioningParameters.setStoragePoolUri(storagePoolClient
                .getStoragePoolByName(params, storagePoolName).getUri());
        dto.setProvisioningParameters(provisioningParameters);

        return dto;
    }

    private AddStorageVolumeV2 buildTestPrivateStorageVolumeDto()
    {
        AddStorageVolumeV2 dto = new AddStorageVolumeV2();

        dto.setName(resourceNameForPrivateStorage);
        dto.setDescription("Volume description");
        dto.setStorageSystemUri(storageSystemClient.getStorageSystemByName(
                params, storageSystemName).getUri());
        dto.setType(ResourceCategory.RC_ADD_STORAGE_VOLUME);

        StorageVolumeProvisioningParameters provisioningParameters = new StorageVolumeProvisioningParameters();
        provisioningParameters.setProvisionType("Full");
        provisioningParameters.setShareable(false);
        provisioningParameters.setRequestedCapacity("1234567898");
        provisioningParameters.setStoragePoolUri(storagePoolClient
                .getStoragePoolByName(params, storagePoolName).getUri());
        dto.setProvisioningParameters(provisioningParameters);

        return dto;
    }
}
