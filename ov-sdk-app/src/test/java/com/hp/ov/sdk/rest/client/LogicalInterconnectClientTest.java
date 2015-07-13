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

import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.dto.Command;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.PhysicalInterconnectFirmware;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
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

public class LogicalInterconnectClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(LogicalInterconnectClientTest.class);

    private LogicalInterconnectClient logicalInterconnectClient;
    @Autowired
    private HttpRestClientTest httpRestClientTest;
    @Autowired
    private RestParams params;

    @Autowired
    private FirmwareDriverClient firmwareDriverClient;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private LoginSessions loginSessions;

    @Autowired
    private LoginSessionAdaptor loginSessionAdaptor;

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
    private static final String sppName = "HP Service Pack For ProLiant OneView 2014 11 13";
    private static final String resourceName = "Encl1-LI";
	private static final String resourceId = "3454560d-0dd9-43b4-ae61-8bc5a3e5dd60";

    // InterconnectUri
    private static final String interconnectNameOne = "Encl1, interconnect 1";
    private static final String interconnectNameTwo = "Encl1, interconnect 2";
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.
	
    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        logicalInterconnectClient = (LogicalInterconnectClient) ctx
                .getBean("logicalInterconnectClientImpl");
        firmwareDriverClient = (FirmwareDriverClient) ctx.getBean("firmwareDriverClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetLogicalInterconnectById()
            throws InstantiationException,
            IllegalAccessException
    {
    	LogicalInterconnects logicalInterconnectsDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

			// then make sdk service call to get resource
			logicalInterconnectsDto = logicalInterconnectClient
					.getLogicalInterconnect(params, resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testGetLogicalInterconnectById : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testGetLogicalInterconnectById : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testGetLogicalInterconnectById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testGetLogicalInterconnectById : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : "
                    + "testGetLogicalInterconnectById : arguments are null ");
        }

        logger.info("LogicalInterconnectClientTest : "
                + "testGetLogicalInterconnectById : logical interconnect"
                + "  object returned to client : "
                + logicalInterconnectsDto.toString());
    }

    @Test
    public void testGetAllLogicalInterconnects()
            throws InstantiationException,
            IllegalAccessException
    {
    	LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            logicalInterconnectCollectionDto = logicalInterconnectClient
                    .getAllLogicalInterconnects(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testGetAllLogicalInterconnects : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testGetAllLogicalInterconnects : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testGetAllLogicalInterconnects : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testGetAllLogicalInterconnects : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : "
                    + "testGetAllLogicalInterconnects : arguments are null ");
        }

        logger.info("LogicalInterconnectClientTest : testGetAllLogicalInterconnects "
                + ": logical interconnect object returned to client : "
                + logicalInterconnectCollectionDto.toString());
    }

    @Test
    public void testUpdateLogicalInterconnectSnmpConfigurationById()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
		String resourceId = null;
		LogicalInterconnects logicalInterconnectsDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

			// fetch logicalInterconnectsDto Uri
			logicalInterconnectsDto = logicalInterconnectClient
					.getLogicalInterconnectByName(params, resourceName);

			if (null != logicalInterconnectsDto.getUri()) 
			{
				resourceId = sdkUtils
						.getResourceIdFromUri(logicalInterconnectsDto.getUri());
			}

            logicalInterconnectsDto.getSnmpConfiguration().setReadCommunity(
                    "private");
            taskResourceV2 = logicalInterconnectClient
                    .updateLogicalInterconnectSnmpConfigurationById(params,
							resourceId,
                            logicalInterconnectsDto.getSnmpConfiguration(),
                            false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectSnmpConfigurationById : resource you are looking is not found for update");
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectSnmpConfigurationById : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectSnmpConfigurationById : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectSnmpConfigurationById : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectSnmpConfigurationById : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectSnmpConfigurationById : errors in task, "
                    + "please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("LogicalInterconnectClientTest : "
                + "testUpdateLogicalInterconnectSnmpConfigurationById : status of "
                + "logicalInterconnect object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateLogicalInterconnectComplianceById()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
		String resourceId = null;
		LogicalInterconnects logicalInterconnectsDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            //fetch logicalInterconnectsDto Uri
			logicalInterconnectsDto = logicalInterconnectClient
					.getLogicalInterconnectByName(params, resourceName);

			if (null != logicalInterconnectsDto.getUri()) 
			{
				resourceId = sdkUtils
						.getResourceIdFromUri(logicalInterconnectsDto.getUri());
			}

            taskResourceV2 = logicalInterconnectClient
                    .updateLogicalInterconnectComplianceById(params,
							resourceId, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectComplianceById : resource you are looking is not found for update");
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectComplianceById : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectComplianceById : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectComplianceById : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectComplianceById : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectComplianceById : errors in task, "
                    + "please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("LogicalInterconnectClientTest : "
                + "testUpdateLogicalInterconnectComplianceById : status of "
                + "logicalInterconnect object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateLogicalInterconnectFirmwareStageById()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
		String resourceId = null;
		LogicalInterconnects logicalInterconnectsDto = null;
		LiFirmware liFirmwareDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            //fetch logicalInterconnectsDto Uri
			logicalInterconnectsDto = logicalInterconnectClient
					.getLogicalInterconnectByName(params, resourceName);

			if (null != logicalInterconnectsDto.getUri()) 
			{
				resourceId = sdkUtils
						.getResourceIdFromUri(logicalInterconnectsDto.getUri());
			}

            liFirmwareDto = buildLIFirmwareStageDto();
            taskResourceV2 = logicalInterconnectClient
					.updateLogicalInterconnectFirmwareById(params, resourceId,
							liFirmwareDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectFirmwareStageById : resource you are looking is not found for update");
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectFirmwareStageById : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareStageById : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareStageById : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareStageById : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareStageById : errors in task, "
                    + "please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("LogicalInterconnectClientTest : "
                + "testUpdateLogicalInterconnectFirmwareStageById : status of "
                + "logicalInterconnect object returned to client : "
                + taskResourceV2.toString());
    }

    private LiFirmware buildLIFirmwareStageDto()
    {
        LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.STAGE);
        liFirmware.setSppUri(firmwareDriverClient.getFirmwareDriverByName(params, sppName).getUri());
        liFirmware.setForce(true);
        return liFirmware;
    }

    @Test
    public void testUpdateLogicalInterconnectFirmwareActiveById()
            throws InstantiationException, IllegalAccessException
    {

        boolean testfailed = false;
		String resourceId = null;
		LogicalInterconnects logicalInterconnectsDto = null;
		LiFirmware liFirmwareDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            //fetch logicalInterconnectsDto Uri
			logicalInterconnectsDto = logicalInterconnectClient
					.getLogicalInterconnectByName(params, resourceName);

			if (null != logicalInterconnectsDto.getUri()) 
			{
				resourceId = sdkUtils
						.getResourceIdFromUri(logicalInterconnectsDto.getUri());
			}

			liFirmwareDto = logicalInterconnectClient
					.getLogicalInterconnectFirmware(params, resourceId);
			liFirmwareDto = buildLIFirmwareActiveDto(liFirmwareDto);

			taskResourceV2 = logicalInterconnectClient
					.updateLogicalInterconnectFirmwareById(params, resourceId,
							liFirmwareDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectFirmwareActiveById : resource you are looking is not found for update");
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
					+ "testUpdateLogicalInterconnectFirmwareActiveById : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareActiveById : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareActiveById : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareActiveById : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareActiveById : errors in task, "
                    + "please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("LogicalInterconnectClientTest : "
                + "testUpdateLogicalInterconnectFirmwareActiveById : status of "
                + "logicalInterconnect object returned to client : "
                + taskResourceV2.toString());
    }

    private LiFirmware buildLIFirmwareActiveDto(LiFirmware initliFirmware)
    {
        LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.ACTIVATE);
        liFirmware.setSppUri(firmwareDriverClient.getFirmwareDriverByName(params, sppName).getUri());
        List<PhysicalInterconnectFirmware> interconnects = new ArrayList<PhysicalInterconnectFirmware>();
        for (int i = 0; i < initliFirmware.getInterconnects().size(); i++)
        {
            String interconnectName = null;
            if (initliFirmware.getInterconnects().get(i).getInterconnectName().equals(interconnectNameOne))
            {
                interconnectName = interconnectNameOne;
            }
            else if (initliFirmware.getInterconnects().get(i).getInterconnectName().equals(interconnectNameTwo))
            {
                interconnectName = interconnectNameTwo;
            }
            if (interconnectName != null)
            {
                PhysicalInterconnectFirmware interconnect = new PhysicalInterconnectFirmware();
                interconnect.setInterconnectUri(initliFirmware.getInterconnects().get(i).getInterconnectUri());
                interconnect.setInterconnectName(interconnectName);
                interconnects.add(interconnect);
            }
        }
        liFirmware.setInterconnects(interconnects);

        return liFirmware;
    }

    @Test
    public void testUpdateLogicalInterconnectFirmwareUpdateById()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
        String resourceId = null;
        LogicalInterconnects logicalInterconnectsDto = null;
        LiFirmware liFirmwareDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            liFirmwareDto = buildLIFirmwareUpdateDto();

            //fetch logicalInterconnectsDto Uri
			logicalInterconnectsDto = logicalInterconnectClient
					.getLogicalInterconnectByName(params, resourceName);

			if (null != logicalInterconnectsDto.getUri()) 
			{
				resourceId = sdkUtils
						.getResourceIdFromUri(logicalInterconnectsDto.getUri());
			}

			taskResourceV2 = logicalInterconnectClient
					.updateLogicalInterconnectFirmwareById(params, resourceId,
                            liFirmwareDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareUpdateById : resource you are looking is not found for update"
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareUpdateById : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareUpdateById : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareUpdateById : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareUpdateById : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LogicalInterconnectClientTest : "
                    + "testUpdateLogicalInterconnectFirmwareUpdateById : errors in task, "
                    + "please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("LogicalInterconnectClientTest : "
                + "testUpdateLogicalInterconnectFirmwareUpdateById : status of "
                + "logicalInterconnect object returned to client : "
                + taskResourceV2.toString());
    }

    private LiFirmware buildLIFirmwareUpdateDto()
    {
        LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.UPDATE);
        liFirmware.setSppUri(firmwareDriverClient.getFirmwareDriverByName(params, sppName).getUri());
        liFirmware.setForce(true);
        return liFirmware;
    }

}
