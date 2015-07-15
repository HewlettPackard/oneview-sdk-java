/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.EnclosureGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.dto.generated.InterconnectBayMapping;
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

public class EnclosureGroupClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(EnclosureGroupClientTest.class);

    private EnclosureGroupClient enclosureGroupClient;
    @Autowired
    private LogicalInterconnectGroupClient logicalInterconnectGroupClient;

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
    private static final String resourceName = "Enclosure_Test";
    private static final String logicalInterconnectName = "LIG_PROD_1";
    private static final String resourceId = "b95fc9be-450f-400c-999c-2d6c88ea58cc";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        enclosureGroupClient = (EnclosureGroupClient) ctx
                .getBean("enclosureGroupClientImpl");
        logicalInterconnectGroupClient = (LogicalInterconnectGroupClient) ctx
                .getBean("logicalInterconnectGroupClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetEnclosureGroupById()
            throws InstantiationException,
            IllegalAccessException
    {
        EnclosureGroups enclosureGroupDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroup(params,
                    resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupById :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupById :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupById :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupById :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetEnclosureGroupById :"
                    + " arguments are null ");
        }

        logger.info("EnclosureGroupClientTest : testGetEnclosureGroupById :"
                + " enclosure group object returned to client : "
                + enclosureGroupDto.toString());
    }

    @Test
    public void testGetAllEnclosureGroup()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        EnclosureGroupCollectionV2 enclosureGroupCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureGroupCollectionDto = enclosureGroupClient
                    .getAllEnclosureGroups(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetAllEnclosureGroup "
                    + ": resource you are looking is not found");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetAllEnclosureGroup :"
                    + " no such url : " + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureGroupClientTest : testGetAllEnclosureGroup :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetAllEnclosureGroup :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllEnclosureGroup :"
                    + " arguments are null ");
        }
        logger.info("EnclosureGroupClientTest : testGetAllEnclosureGroup :"
                + " enclosure groups object returned to client : "
                + enclosureGroupCollectionDto.toString());
    }

    @Test
    public void testGetEnclosureGroupByName()
            throws InstantiationException,
            IllegalAccessException
    {
        EnclosureGroups enclosureGroupDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroupByName(
                    params, resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupByName :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupByName :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupByName :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureGroupClientTest : testGetEnclosureGroupByName :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetEnclosureGroupByName :"
                    + " arguments are null ");
        }

        logger.info("EnclosureGroupClientTest : testGetEnclosureGroupByName :"
                + " enclosure group object returned to client : "
                + enclosureGroupDto.toString());
    }

    @Test
    public void testCreateEnclosureGroup()
            throws InstantiationException,
            IllegalAccessException
    {
        boolean testfailed = false;
        EnclosureGroups enclosureGroupReturnDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create enclosure group request body
            final EnclosureGroups enclosureGroupDto = buildTestEnclosureGroupDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            enclosureGroupReturnDto = enclosureGroupClient
                    .createEnclosureGroup(params, enclosureGroupDto, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureGroupClientTest : testCreateEnclosureGroup : resource you are looking is not found");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("EnclosureGroupClientTest : testCreateEnclosureGroup : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureGroupClientTest : testCreateEnclosureGroup : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureGroupClientTest : testCreateEnclosureGroup : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("EnclosureGroupClientTest : testCreateEnclosureGroup : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("EnclosureGroupClientTest : testCreateEnclosureGroup : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("EnclosureGroupClientTest : testCreateEnclosureGroup : enclosure group object returned to client : "
                + enclosureGroupReturnDto.toString());
    }

    @Test
    public void testUpdateEnclosureGroup()
            throws InstantiationException,
            IllegalAccessException
    {
        EnclosureGroups enclosureGroupReturnDto = null;
        String resourceId = null;
        EnclosureGroups enclosureGroupDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroupByName(
                    params, resourceName);

            enclosureGroupDto.setName(resourceName + "_updated");

            if (null != enclosureGroupDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(enclosureGroupDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            enclosureGroupReturnDto = enclosureGroupClient
                    .updateEnclosureGroup(params, resourceId,
                            enclosureGroupDto, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureGroupClientTest : testUpdateEnclosureGroup :"
                    + " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("EnclosureGroupClientTest : testUpdateEnclosureGroup :"
                    + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureGroupClientTest : testUpdateEnclosureGroup :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureGroupClientTest : testUpdateEnclosureGroup :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureGroupClientTest : testUpdateEnclosureGroup :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateEnclosureGroup : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateEnclosureGroup : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("EnclosureGroupClientTest : testUpdateEnclosureGroup : "
                + "Enclosure group object returned to client : "
                + enclosureGroupReturnDto.toString());
    }

    @Test
    public void testDeleteEnclosureGroup()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id
        EnclosureGroups enclosureGroupDto = null;
        String deleteMsg = null;
        String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            enclosureGroupDto = enclosureGroupClient.getEnclosureGroupByName(
                    params, resourceName);

            if (null != enclosureGroupDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(enclosureGroupDto.getUri());
            }

            // then make sdk service call to get resource
            deleteMsg = enclosureGroupClient.deleteEnclosureGroup(params,
                    resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureGroupClientTest : testDeleteEnclosureGroupSet :"
                    + " resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureGroupClientTest : testDeleteEnclosureGroupSet :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureGroupClientTest : testDeleteEnclosureGroup :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureGroupClientTest : testDeleteEnclosureGroup : "
                    + "No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteEnclosureGroup :"
                    + " arguments are null ");
            fail("Test failed");
        }

        logger.info("EnclosureGroupClientTest : testDeleteEnclosureGroup : "
                + "enclosure group object returned to client : " + deleteMsg);
    }

    //TODO - Move Uri fetch logic to SdkUtils

    private EnclosureGroups buildTestEnclosureGroupDto()
    {

        // fetch resource Id using resource name
        final String logicalInterconnectGroupUri = logicalInterconnectGroupClient
                .getLogicalInterconnectGroupByName(params,
                        logicalInterconnectName).getUri();

        int i;
        final EnclosureGroups dto = new EnclosureGroups();
        dto.setType(ResourceCategory.RC_ENCLOSURE_GROUP);
        dto.setName(resourceName);
        dto.setStackingMode(EnclosureGroups.StackingMode.Enclosure);
        final List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();
        for (i = 0; i < 8; i++)
        {
            final InterconnectBayMapping interconnectBayMapping = new InterconnectBayMapping();
            interconnectBayMapping.setInterconnectBay(i + 1);
            interconnectBayMapping
                    .setLogicalInterconnectGroupUri(logicalInterconnectGroupUri);
            interconnectBayMappings.add(interconnectBayMapping);
        }
        dto.setInterconnectBayMappings(interconnectBayMappings);
        return dto;
    }

}
