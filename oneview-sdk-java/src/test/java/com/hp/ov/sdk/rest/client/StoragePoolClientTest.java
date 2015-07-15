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

import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StoragePoolCollection;
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
import com.hp.ov.sdk.util.SdkUtils;

public class StoragePoolClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(StoragePoolClientTest.class);

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
    private static final String resourceName = "FST_CPG1";
    private static final String storageSystemName = "ThreePAR7200-3050";
    private static final String resourceId = "F017F7BE-3001-4B8E-8495-88E7FB31223E?";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        storagePoolClient = (StoragePoolClient) ctx
                .getBean("storagePoolClientImpl");
        storageSystemClient = (StorageSystemClient) ctx
                .getBean("storageSystemClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetStoragePoolById()
            throws InstantiationException,
            IllegalAccessException
    {
        StoragePool storagePoolDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storagePoolDto = storagePoolClient
                    .getStoragePool(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolById : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolById : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolById : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStoragePoolById : arguments are null ");
        }

        logger.info("StoragePoolClientTest : testGetStoragePoolById : storagePool object returned to client : "
                + storagePoolDto.toString());
    }

    @Test
    public void testGetAllStoragePool()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        StoragePoolCollection storagePoolCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storagePoolCollectionDto = storagePoolClient
                    .getAllStoragePools(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StoragePoolClientTest : testGetAllStoragePool : resource you are looking is not found ");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StoragePoolClientTest : testGetAllStoragePool : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StoragePoolClientTest : testGetAllStoragePool : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StoragePoolClientTest : testGetAllStoragePool : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllStoragePool : arguments are null ");
        }
        logger.info("StoragePoolClientTest : testGetAllStoragePool : storagePool object returned to client : "
                + storagePoolCollectionDto.toString());
    }

    @Test
    public void testGetStoragePoolByName()
            throws InstantiationException,
            IllegalAccessException
    {
        StoragePool storagePoolDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storagePoolDto = storagePoolClient.getStoragePoolByName(params,
                    resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolByName : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolByName : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolByName : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StoragePoolClientTest : testGetStoragePoolByName : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStoragePoolByName : arguments are null ");
        }

        logger.info("StoragePoolClientTest : testGetStoragePoolByName : storagePool object returned to client : "
                + storagePoolDto.toString());
    }

    @Test
    public void testCreateStoragePool()
            throws InstantiationException,
            IllegalAccessException
    {
        boolean testfailed = false;
        String createStoragePool = null;
        AddStoragePool addStoragePoolDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create storagePool request body
            addStoragePoolDto = buildTestStoragePoolDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            createStoragePool = storagePoolClient.createStoragePool(params,
                    addStoragePoolDto, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StoragePoolClientTest : testCreateStoragePool : resource you are looking is not found");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("StoragePoolClientTest : testCreateStoragePool : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StoragePoolClientTest : testCreateStoragePool : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StoragePoolClientTest : testCreateStoragePool : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateStoragePool : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateStoragePool : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("StoragePoolClientTest : testCreateStoragePool : storagePool object returned to client : "
                + createStoragePool);
    }

    @Test
    public void testUpdateStoragePool()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id
        String updateStoragePool = null;
        String resourceId = null;
        StoragePool storagePoolDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storagePoolDto = storagePoolClient.getStoragePoolByName(params,
                    resourceName);

            if (null != storagePoolDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(storagePoolDto
                        .getUri());
            }

            storagePoolDto.setRefreshState(RefreshState.RefreshPending);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            updateStoragePool = storagePoolClient
                    .updateStoragePool(params, resourceId,
                            storagePoolDto, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StoragePoolClientTest : testUpdateStoragePool :"
                    + " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("StoragePoolClientTest : testUpdateStoragePool :"
                    + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StoragePoolClientTest : testUpdateStoragePool :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StoragePoolClientTest : testUpdateStoragePool :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StoragePoolClientTest : testUpdateStoragePool :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateStoragePool : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateStoragePool : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("StoragePoolClientTest : testUpdateStoragePool : "
                + "storagePool object returned to client : "
                + updateStoragePool);
    }

    @Test
    public void testDeleteStoragePool()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id
        String deleteStoragePool = null;
        String resourceId = null;
        StoragePool storagePoolDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storagePoolDto = storagePoolClient.getStoragePoolByName(
                    params, resourceName);

            if (null != storagePoolDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(storagePoolDto
                        .getUri());
            }

            // then make sdk service call to get resource
            deleteStoragePool = storagePoolClient
                    .deleteStoragePool(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StoragePoolClientTest : testDeleteStoragePool : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StoragePoolClientTest : testDeleteStoragePool : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StoragePoolClientTest : testDeleteStoragePool : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StoragePoolClientTest : testDeleteStoragePool : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteStoragePool : arguments are null ");
        }

        logger.info("StoragePoolClientTest : testDeleteStoragePool : storagePool object returned to client : "
                + deleteStoragePool);
    }

    //TODO - Move Uri fetch logic to SdkUtils

    private AddStoragePool buildTestStoragePoolDto()
    {
        final AddStoragePool dto = new AddStoragePool();

        dto.setPoolName(resourceName);
        dto.setStorageSystemUri(storageSystemClient.getStorageSystemByName(
                params, storageSystemName).getUri());
        return dto;
    }

}
