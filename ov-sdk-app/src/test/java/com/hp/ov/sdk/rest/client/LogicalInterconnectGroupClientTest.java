/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.rest.dto.UplinkSetValue;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.LoginSessions;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.tasks.TaskServiceManager;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.ResourceDtoUtilsWrapper;
import com.hp.ov.sdk.util.SdkUtils;

public class LogicalInterconnectGroupClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(LogicalInterconnectGroupClientTest.class);

    private LogicalInterconnectGroupClient logicalInterconnectGroupClient;

    @Autowired
    private ResourceDtoUtils resourceDtoUtils;

    @Autowired
    private ResourceDtoUtilsWrapper resourceDtoUtilsWrapper;

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
    private static final String resourceName = "LIG_PROD_1";
    private static final String permittedInterconnectType = "HP VC FlexFabric-20/40 F8 Module";
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402",
            "Prod_403");
    private static final List<String> fcNetworkName_A = Arrays.asList("FC_Network_A");
    private static final List<String> fcNetworkName_B = Arrays.asList("FC_Network_B");
    private static final List<String> ethPort = Arrays.asList("X5", "X6");
    private static final List<String> fcPort = Arrays.asList("X2");
    private static final String ethUplinkSetType = "Ethernet";
    private static final String fcUplinkSetType = "FibreChannel";
    private static final String ethUplinkSetName = "EthernetUplinkSet";
    private static final String fcAUplinkSetName = "FCUplinkSetA";
    private static final String fcBUplinkSetName = "FCUplinkSetB";
    private static final String resourceId = "5f5f0065-1578-454e-9e38-96881e0af3ba";
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        logicalInterconnectGroupClient = (LogicalInterconnectGroupClient) ctx
                .getBean("logicalInterconnectGroupClientImpl");
        resourceDtoUtils = (ResourceDtoUtils) ctx.getBean("resourceDtoUtils");
        resourceDtoUtilsWrapper = (ResourceDtoUtilsWrapper) ctx
                .getBean("resourceDtoUtilsWrapper");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetLogicalInterconnectGroupById()
            throws InstantiationException, IllegalAccessException
    {
        LogicalInterconnectGroups logicalInterconnectGroupDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            logicalInterconnectGroupDto = logicalInterconnectGroupClient
                    .getLogicalInterconnectGroup(params, resourceId);

        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupById : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupById : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupById : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : "
                    + "testGetLogicalInterconnectGroupById : arguments are null ");
        }

        logger.info("LogicalInterconnectGroupClientTest : "
                + "testGetLogicalInterconnectGroupById : logical interconnect"
                + " group object returned to client : "
                + logicalInterconnectGroupDto.toString());
    }

    @Test
    public void testGetAllLogicalInterconnectGroups()
            throws InstantiationException, IllegalAccessException
    {
        LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            logicalInterconnectGroupCollectionDto = logicalInterconnectGroupClient
                    .getAllLogicalInterconnectGroups(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
					+ "testGetAllLogicalInterconnectGroups : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetAllLogicalInterconnectGroups : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetAllLogicalInterconnectGroups : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetAllLogicalInterconnectGroups : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : "
                    + "testGetAllLogicalInterconnectGroups : arguments are null ");
        }

        logger.info("LogicalInterconnectGroupClientTest : testGetAllLogicalInterconnectGroups "
                + ": logical interconnect group object returned to client : "
                + logicalInterconnectGroupCollectionDto.toString());
    }

    @Test
    public void testGetLogicalInterconnectGroupByName()
            throws InstantiationException, IllegalAccessException
    {
        LogicalInterconnectGroups logicalInterconnectGroupDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            logicalInterconnectGroupDto = logicalInterconnectGroupClient
                    .getLogicalInterconnectGroupByName(params, resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupByName : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupByName : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupByName : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testGetLogicalInterconnectGroupByName : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : "
                    + "testGetLogicalInterconnectGroupByName : arguments are null ");
        }

        logger.info("LogicalInterconnectGroupClientTest : "
                + "testGetLogicalInterconnectGroupByName : logical interconnect"
                + " group object returned to client : "
                + logicalInterconnectGroupDto.toString());
    }

    @Test
    public void testCreateLogicalInterconnectGroup()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
        try
        {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create logicalInterconnectGroup request body
            final LogicalInterconnectGroups logicalInterconnectGroupDto = buildTestLogicalInterconnectGroup();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalInterconnectGroupClient
                    .createLogicalInterconnectGroup(params,
                            logicalInterconnectGroupDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
					+ "testCreateLogicalInterconnectGroup : resource you are looking is not found ");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
					+ "testCreateLogicalInterconnectGroup : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testCreateLogicalInterconnectGroup : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testCreateLogicalInterconnectGroup : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testCreateLogicalInterconnectGroup : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testCreateLogicalInterconnectGroup : errors in task, "
                    + "please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("LogicalInterconnectGroupClientTest : "
                + "testCreateLogicalInterconnectGroup : status of "
                + "logicalInterconnectGroup object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateLogicalInterconnectGroup()
            throws InstantiationException, IllegalAccessException
    {
        boolean testfailed = false;
        LogicalInterconnectGroups logicalInterconnectGroups = null;
        String resourceId = null;
        try
        {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // get resource Id using resource name
            logicalInterconnectGroups = logicalInterconnectGroupClient
                    .getLogicalInterconnectGroupByName(params, resourceName);

            if (null != logicalInterconnectGroups.getUri())
            {
                resourceId = sdkUtils
                        .getResourceIdFromUri(logicalInterconnectGroups
                                .getUri());
            }

            // create uplinksetDto request body
            final List<UplinkSet> uplinkSetDto = buildUplinkSetGroupDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = logicalInterconnectGroupClient
                    .updateLogicalInterconnectGroup(params, resourceId,
                            uplinkSetDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
					+ "testUpdateLogicalInterconnectGroup : resource you are looking is not found for update ");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testUpdateLogicalInterconnectGroup : bad request, try again : ");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testUpdateLogicalInterconnectGroup : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testUpdateLogicalInterconnectGroup : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testUpdateLogicalInterconnectGroup : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : "
                    + "testUpdateLogicalInterconnectGroup : errors in task, "
                    + "please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("LogicalInterconnectGroupClientTest : "
                + "testUpdateLogicalInterconnectGroup : status of "
                + "logicalInterconnectGroup object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteLogicalInterconnectGroup()
            throws InstantiationException, IllegalAccessException
    {
        LogicalInterconnectGroups logicalInterconnectGroups = null;
        String resourceId = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using name
            logicalInterconnectGroups = logicalInterconnectGroupClient
                    .getLogicalInterconnectGroupByName(params, resourceName);

            if (null != logicalInterconnectGroups.getUri())
            {
                resourceId = sdkUtils
                        .getResourceIdFromUri(logicalInterconnectGroups
                                .getUri());
            }

            // then make sdk service call to get resource
            taskResourceV2 = logicalInterconnectGroupClient
                    .deleteLogicalInterconnectGroup(params, resourceId, false);

        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : testDeleteLogicalInterconnectGroup : resource not found to delete : ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : testDeleteLogicalInterconnectGroup : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("LogicalInterconnectGroupClientTest : testDeleteLogicalInterconnectGroup : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("LogicalInterconnectGroupClientTest : testDeleteLogicalInterconnectGroup : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteLogicalInterconnectGroup : arguments are null ");
        }

        logger.info("LogicalInterconnectGroupClientTest : testDeleteLogicalInterconnectGroup : logical Interconnect group deleted status : "
                + taskResourceV2.toString());
    }

    private LogicalInterconnectGroups buildTestLogicalInterconnectGroup()
    {
        final HashMap<Integer, String> bayPermittedInterconnectMaps = new HashMap<Integer, String>();
        bayPermittedInterconnectMaps.put(1, permittedInterconnectType);
        bayPermittedInterconnectMaps.put(2, permittedInterconnectType);
        return resourceDtoUtils.buildLogicalInterconnectGroupDto(params,
                resourceName, bayPermittedInterconnectMaps);
    }

    private List<UplinkSet> buildUplinkSetGroupDto()
    {

        List<UplinkSet> uplinkSetGroupDto = new ArrayList<UplinkSet>();

        final HashMap<Integer, List<String>> ethBayPortMap = new HashMap<Integer, List<String>>();
        ethBayPortMap.put(1, ethPort);
        ethBayPortMap.put(2, ethPort);
        final HashMap<Integer, List<String>> fcBayPortMapA = new HashMap<Integer, List<String>>();
        fcBayPortMapA.put(1, fcPort);
        final HashMap<Integer, List<String>> fcBayPortMapB = new HashMap<Integer, List<String>>();
        fcBayPortMapB.put(2, fcPort);

        final List<UplinkSetValue> uplinkSetValues = new ArrayList<UplinkSetValue>();
        final UplinkSetValue ethUplinkSetValue = new UplinkSetValue();
        ethUplinkSetValue.setBayPortMap(ethBayPortMap);
        ethUplinkSetValue.setLigName(resourceName);
        ethUplinkSetValue.setNetworkNames(networkNames);
        ethUplinkSetValue.setUplinkSetName(ethUplinkSetName);
        ethUplinkSetValue.setUplinkSetType(ethUplinkSetType);

        final UplinkSetValue fcAUplinkSetValue = new UplinkSetValue();
        fcAUplinkSetValue.setBayPortMap(fcBayPortMapA);
        fcAUplinkSetValue.setLigName(resourceName);
        fcAUplinkSetValue.setNetworkNames(fcNetworkName_A);
        fcAUplinkSetValue.setUplinkSetName(fcAUplinkSetName);
        fcAUplinkSetValue.setUplinkSetType(fcUplinkSetType);

        final UplinkSetValue fcBUplinkSetValue = new UplinkSetValue();
        fcBUplinkSetValue.setBayPortMap(fcBayPortMapB);
        fcBUplinkSetValue.setLigName(resourceName);
        fcBUplinkSetValue.setNetworkNames(fcNetworkName_B);
        fcBUplinkSetValue.setUplinkSetName(fcBUplinkSetName);
        fcBUplinkSetValue.setUplinkSetType(fcUplinkSetType);

        uplinkSetValues.add(ethUplinkSetValue);
        uplinkSetValues.add(fcAUplinkSetValue);
        uplinkSetValues.add(fcBUplinkSetValue);

        uplinkSetGroupDto = resourceDtoUtilsWrapper.buildUplinkSetGroupDto(
                params, uplinkSetValues);

        return uplinkSetGroupDto;
    }

}
