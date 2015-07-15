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

import com.hp.ov.sdk.dto.AddEnclosureV2;
import com.hp.ov.sdk.dto.EnclosureCollectionV2;
import com.hp.ov.sdk.dto.FwBaselineOptions;
import com.hp.ov.sdk.dto.LicensingIntent;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Enclosures;
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

public class EnclosureClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(EnclosureClientTest.class);

    private EnclosureClient enclosureClient;

    @Autowired
    private EnclosureGroupClient enclosureGroupClient;

    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private FirmwareDriverClient firmwareDriverClient;

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
    private static final String resourceName = "Encl1";
    private static final String enclosureGroupName = "Enclosure_Test";
    private static final String hostname = "172.18.1.11";
    private static final String username = "dcs";
    private static final String password = "dcs";
    private static final String firmware = "HP Service Pack For ProLiant OneView 2014 11 13";
    private static final String resourceId = "09SGH102X6J1";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        enclosureClient = (EnclosureClient) ctx.getBean("enclosureClientImpl");
        enclosureGroupClient = (EnclosureGroupClient) ctx
                .getBean("enclosureGroupClientImpl");
        firmwareDriverClient = (FirmwareDriverClient) ctx
                .getBean("firmwareDriverClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetEnclosureById()
            throws InstantiationException,
            IllegalAccessException
    {
        Enclosures enclosureDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureDto = enclosureClient
                    .getEnclosure(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureClientTest : testGetEnclosureById :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureClientTest : testGetEnclosureById :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureClientTest : testGetEnclosureById :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureClientTest : testGetEnclosureById :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetEnclosureById :"
                    + " arguments are null ");
        }

        logger.info("EnclosureClientTest : testGetEnclosureById :"
                + " enclosure group object returned to client : "
                + enclosureDto.toString());
    }

    @Test
    public void testGetAllEnclosure()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        EnclosureCollectionV2 enclosureCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureCollectionDto = enclosureClient.getAllEnclosures(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureClientTest : testGetAllEnclosure "
                    + ": resource you are looking is not found");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureClientTest : testGetAllEnclosure :"
                    + " no such url : " + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureClientTest : testGetAllEnclosure :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureClientTest : testGetAllEnclosure :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllEnclosure :"
                    + " arguments are null ");
        }
        logger.info("EnclosureClientTest : testGetAllEnclosure :"
                + " enclosure groups object returned to client : "
                + enclosureCollectionDto.toString());
    }

    @Test
    public void testGetEnclosureByName()
            throws InstantiationException,
            IllegalAccessException
    {
        Enclosures enclosureDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            enclosureDto = enclosureClient.getEnclosureByName(params,
                    resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureClientTest : testGetEnclosureByName :"
                    + " resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureClientTest : testGetEnclosureByName :"
                    + " no such url : " + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureClientTest : testGetEnclosureByName :"
                    + " Applicance Not reachabe at : " + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureClientTest : testGetEnclosureByName :"
                    + " No response from appliance : " + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetEnclosureByName :"
                    + " arguments are null ");
        }

        logger.info("EnclosureClientTest : testGetEnclosureByName :"
                + " enclosure group object returned to client : "
                + enclosureDto.toString());
    }

    @Test
    public void testCreateEnclosure()
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
            final AddEnclosureV2 addEnclosureDto = buildTestEnclosureDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient.createEnclosure(params,
                    addEnclosureDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureClientTest : testCreateEnclosure : resource you are looking is not found ");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("EnclosureClientTest : testCreateEnclosure : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureClientTest : testCreateEnclosure : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureClientTest : testCreateEnclosure : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("EnclosureClientTest : testCreateEnclosure : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("EnclosureClientTest : testCreateEnclosure : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("EnclosureClientTest : testCreateEnclosure : enclosure group object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateEnclosure()
            throws InstantiationException,
            IllegalAccessException
    {
        String resourceId = null;
        Enclosures enclosureDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            enclosureDto = enclosureClient.getEnclosureByName(params,
                    resourceName);

            if (null != enclosureDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(enclosureDto.getUri());
            }
            enclosureDto.setName(resourceName);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = enclosureClient
                    .updateEnclosure(params, resourceId,
                            enclosureDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureClientTest : testUpdateEnclosure :"
                    + " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("EnclosureClientTest : testUpdateEnclosure :"
                    + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureClientTest : testUpdateEnclosure :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureClientTest : testUpdateEnclosure :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureClientTest : testUpdateEnclosure :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateEnclosure : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateEnclosure : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("EnclosureClientTest : testUpdateEnclosure : "
                + "Enclosure group object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteEnclosure()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id
        String resourceId = null;
        Enclosures enclosureDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            enclosureDto = enclosureClient.getEnclosureByName(params,
                    resourceName);

            if (null != enclosureDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(enclosureDto.getUri());
            }

            // make sdk service call to get resource
            taskResourceV2 = enclosureClient
                    .deleteEnclosure(params, resourceId,
                            false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("EnclosureClientTest : testDeleteEnclosureSet :"
                    + " resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("EnclosureClientTest : testDeleteEnclosureSet :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("EnclosureClientTest : testDeleteEnclosure :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("EnclosureClientTest : testDeleteEnclosure : "
                    + "No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteEnclosure :"
                    + " arguments are null ");
            fail("Test failed");
        }

        logger.info("EnclosureClientTest : testDeleteEnclosure : "
                + "enclosure group object returned to client : "
                + taskResourceV2.toString());
    }

    //TODO - Move Uri fetch logic to SdkUtils

    private AddEnclosureV2 buildTestEnclosureDto()
    {

        final String enclosureGroupUri = enclosureGroupClient
                .getEnclosureGroupByName(params, enclosureGroupName).getUri();
        final AddEnclosureV2 dto = new AddEnclosureV2();
        dto.setHostname(hostname);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setLicensingIntent(LicensingIntent.OneView);
        dto.setForce(false);
        dto.setEnclosureGroupUri(enclosureGroupUri);
        dto.setFirmwareBaselineUri(firmwareDriverClient
                .getFirmwareDriverByName(params, firmware).getUri());
        dto.setUpdateFirmwareOn(FwBaselineOptions.EnclosureOnly);
        dto.setForceInstallFirmware(false);
        return dto;
    }
}
