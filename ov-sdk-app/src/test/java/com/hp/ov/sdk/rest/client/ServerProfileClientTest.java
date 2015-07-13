/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.ConnectionBoot.BootControl;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.Bios;
import com.hp.ov.sdk.dto.generated.Boot;
import com.hp.ov.sdk.dto.generated.Firmware;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.dto.generated.ServerProfile.ProfileAffinity;
import com.hp.ov.sdk.dto.generated.StoragePath.StorageTargetType;
import com.hp.ov.sdk.rest.dto.NetworkForServerProfile;
import com.hp.ov.sdk.rest.dto.SanStorageForServerProfile;
import com.hp.ov.sdk.rest.dto.SanStorageForServerProfile.StorageVolume;
import com.hp.ov.sdk.rest.dto.ServerProfileValue;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClientTest;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.LoginSessions;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.tasks.TaskServiceManager;
import com.hp.ov.sdk.util.ResourceDtoUtilsWrapper;
import com.hp.ov.sdk.util.SdkUtils;

public class ServerProfileClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(ServerProfileClientTest.class);

    private ServerProfileClient serverProfileClient;
    
    @Autowired
    private EnclosureGroupClient enclosureGroupClient;
    @Autowired
    private ServerHardwareClient serverHardwareClient;
    @Autowired
    private StorageVolumeClient storageVolumeClient;
    @Autowired
    private StoragePoolClient storagePoolClient;
    @Autowired
    private StorageSystemClient storageSystemClient;

    @Autowired
    private HttpRestClientTest httpRestClientTest;
    @Autowired
    private RestParams params;

    @Autowired
    private ResourceDtoUtilsWrapper resourceDtoUtilsWrapper;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private LoginSessions loginSessions;

    private ServerProfile serverProfileDto;
    private ServerProfileCollection serverProfileCollectionDto;
    private AvailableNetworks availableNetworksDto;
    private List<AvailableServers> availableServersCollectionDto;
    private ProfilePorts profilePortsDto;

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
    private static final String templateName = "ServerProfile_Template"; // used
    // for
    // delete,
    // get of server
    // profile or server
    // profile template
    private static final String bayName = "Encl1, bay 15";
    private static final String profileName = "ServerProfile-Bay15";
    private static final String enclosureGroupName = "Enclosure_Test";
    private static final List<String> networkNames = Arrays.asList("Prod_401",
            "Prod_402");
    private static final List<String> storageVolumeName = Arrays.asList(
            "Volume101");
    private static final List<String> fcNetworkNames = Arrays.asList(
            "FC_Network_A", "FC_Network_B");
    private static final Boolean useBayNameForServerHardwareUri = false;
	private static final String resourceId = "92a78b60-6bff-46d2-8f35-bdaad4e556c9";
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.	

    private Boolean matches;

    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        enclosureGroupClient = (EnclosureGroupClient) ctx
                .getBean("enclosureGroupClientImpl");
        storageSystemClient = (StorageSystemClient) ctx
                .getBean("storageSystemClientImpl");
        storagePoolClient = (StoragePoolClient) ctx
                .getBean("storagePoolClientImpl");
        storageVolumeClient = (StorageVolumeClient) ctx
                .getBean("storageVolumeClientImpl");
        serverProfileClient = (ServerProfileClient) ctx
                .getBean("serverProfileClientImpl");
        serverHardwareClient = (ServerHardwareClient) ctx
                .getBean("serverHardwareClientImpl");
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
    public void testGetServerProfileById()
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

			// then make sdk service call to get resource
			serverProfileDto = serverProfileClient.getServerProfile(params, resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetServerProfileById : resource you are looking is not found");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetServerProfileById : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetServerProfileById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetServerProfileById : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetServerProfileById : arguments are null ");
        }

        logger.info("ServerProfileClientTest : testGetServerProfileById : serverProfile object returned to client : "
                + serverProfileDto.toString());
    }

    @Test
    public void testGetAllServerProfile()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            serverProfileCollectionDto = serverProfileClient
                    .getAllServerProfile(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetAllServerProfile : resource you are looking is not found");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetAllServerProfile : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetAllServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetAllServerProfile : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllServerProfile : arguments are null ");
        }
        logger.info("ServerProfileClientTest : testGetAllServerProfile : serverProfile object returned to client : "
                + serverProfileCollectionDto.toString());
    }

    @Test
    public void testGetServerProfileByName()
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

            // then make sdk service call to get resource
            serverProfileDto = serverProfileClient.getServerProfileByName(
                    params, templateName);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetServerProfileByName : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetServerProfileByName : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetServerProfileByName : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetServerProfileByName : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetServerProfileByName : arguments are null ");
        }

        logger.info("ServerProfileClientTest : testGetServerProfileByName : serverProfile object returned to client : "
                + serverProfileDto.toString());
    }

    @Test
    public void testGetAvailableNetworksForServerProfile()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch enclosure group uri from enclosure name
            String enclosureGroupUri = enclosureGroupClient
                    .getEnclosureGroupByName(params, enclosureGroupName)
                    .getUri();

            // fetch server hardware type uri from server hardware
            String serverHardwareTypeUri = serverHardwareClient
                    .getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            availableNetworksDto = serverProfileClient
                    .getAvailableNetworksForServerProfile(params,
                            serverHardwareTypeUri, enclosureGroupUri);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetAvailableNetworksForServerProfile : resource you are looking is not found");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableNetworksForServerProfile : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetAvailableNetworksForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableNetworksForServerProfile : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAvailableNetworksForServerProfile : arguments are null ");
        }
        logger.info("ServerProfileClientTest : testGetAvailableNetworksForServerProfile : serverProfile object returned to client : "
                + availableNetworksDto.toString());
    }

    @Test
    public void testGetAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch enclosure group uri from enclosure name
            String enclosureGroupUri = enclosureGroupClient
                    .getEnclosureGroupByName(params, enclosureGroupName)
                    .getUri();

            // fetch server hardware type uri from server hardware
            String serverHardwareTypeUri = serverHardwareClient
                    .getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            availableServersCollectionDto = serverProfileClient
                    .getAvailableServersForServerProfile(params,
                            serverHardwareTypeUri, enclosureGroupUri);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : resource you are looking is not found");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : arguments are null ");
        }
        logger.info("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : serverProfile object returned to client : "
                + availableServersCollectionDto.toString());
    }

    @Test
    public void testGetAvailableServersForServerProfile()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            availableServersCollectionDto = serverProfileClient
                    .getAvailableServersForServerProfile(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfile : resource you are looking is not found");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfile : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfile : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAvailableServersForServerProfile : arguments are null ");
        }
        logger.info("ServerProfileClientTest : testGetAvailableServersForServerProfile : serverProfile object returned to client : "
                + availableServersCollectionDto.toString());
    }

    @Test
    public void testGetAvailableServersForServerProfileUsingProfile()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch server profile uri using template name
            String profileUri = serverProfileClient.getServerProfileByName(
                    params, templateName).getUri();
            // then make sdk service call to get resource
            availableServersCollectionDto = serverProfileClient
                    .getAvailableServersForServerProfile(params, profileUri);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingProfile : resource you are looking is not found");
			fail("Test case failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingProfile : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingProfile : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingProfile : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAvailableServersForServerProfileUsingProfile : arguments are null ");
        }
        logger.info("ServerProfileClientTest : testGetAvailableServersForServerProfileUsingProfile : serverProfile object returned to client : "
                + availableServersCollectionDto.toString());
    }

    @Test
    public void testGetProfilePortsForServerProfile()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch enclosure group uri from enclosure name
            String enclosureGroupUri = enclosureGroupClient
                    .getEnclosureGroupByName(params, enclosureGroupName)
                    .getUri();

            // fetch server hardware type uri from server hardware
            String serverHardwareTypeUri = serverHardwareClient
                    .getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            profilePortsDto = serverProfileClient
                    .getProfilePortsForServerProfile(params,
                            serverHardwareTypeUri, enclosureGroupUri);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testGetProfilePortsForServerProfile : resource you are looking is not found");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testGetProfilePortsForServerProfile : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testGetProfilePortsForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testGetProfilePortsForServerProfile : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetProfilePortsForServerProfile : arguments are null ");
        }
        logger.info("ServerProfileClientTest : testGetProfilePortsForServerProfile : serverProfile object returned to client : "
                + profilePortsDto.toString());
    }

    @Test
    public void testCreateServerProfile()
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

            // create serverProfile request body
            ServerProfile serverProfileDto = buildTestServerProfileDto(params);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverProfileClient.createServerProfile(params,
                    serverProfileDto, false, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testCreateServerProfile : resource you are looking is not found");
            testfailed = true;
        }
        catch (SDKBadRequestException ex)
        {
			logger.error("ServerProfileClientTest : testCreateServerProfile : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testCreateServerProfile : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testCreateServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateServerProfile : arguments are null ");
            testfailed = true;
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateServerProfile : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("ServerProfileClientTest : testCreateServerProfile : serverProfile object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateServerProfile()
            throws InstantiationException,
            IllegalAccessException
    {
		String resourceId = null;
        
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            serverProfileDto = serverProfileClient.getServerProfileByName(
                    params, templateName);

			serverProfileDto.setName(templateName);
			
			if (null != serverProfileDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(serverProfileDto
						.getUri());
			}
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverProfileClient.updateServerProfile(params,
					resourceId, serverProfileDto, false,
                    false);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("ServerProfileClientTest : testUpdateServerProfile :"
					+ " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (SDKBadRequestException ex)
        {
            logger.error("ServerProfileClientTest : testUpdateServerProfile :"
					+ " bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testUpdateServerProfile :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testUpdateServerProfile :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testUpdateServerProfile :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateServerProfile : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateServerProfile : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("ServerProfileClientTest : testUpdateServerProfile : "
                + "serverProfile object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteServerProfile()
            throws InstantiationException,
            IllegalAccessException
    {
		String resourceId = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
			serverProfileDto = serverProfileClient
					.getServerProfileByName(params, templateName);
			
			if (null != serverProfileDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(serverProfileDto
						.getUri());
			}

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfile(params,
					resourceId, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testDeleteServerProfile : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfile : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfile : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteServerProfile : arguments are null ");
        }

        logger.info("ServerProfileClientTest : testDeleteServerProfile : serverProfile object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteServerProfileByFilter()
            throws InstantiationException, IllegalAccessException
    {
        // first get the session Id
        matches = false;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfileByFilter(
                    params, templateName, matches, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testDeleteServerProfileByFilter : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfileByFilter : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfileByFilter : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfileByFilter : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteServerProfileByFilter : arguments are null ");
        }

        logger.info("ServerProfileClientTest : testDeleteServerProfileByFilter : serverProfile object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteServerProfileByFilterWithMatch()
            throws InstantiationException, IllegalAccessException
    {
        // first get the session Id
        matches = true;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfileByFilter(
                    params, templateName, matches, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ServerProfileClientTest : testDeleteServerProfileByFilterWithMatch : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfileByFilterWithMatch : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfileByFilterWithMatch : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testDeleteServerProfileByFilterWithMatch : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteServerProfileByFilterWithMatch : arguments are null ");
        }

        logger.info("ServerProfileClientTest : testDeleteServerProfileByFilterWithMatch : serverProfile object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testCopyServerProfile()
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

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.copyServerProfile(params,
                    templateName, bayName, profileName, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
        	logger.error("ServerProfileClientTest : testCopyServerProfile : resource you are looking is not found : ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ServerProfileClientTest : testCopyServerProfile : no such url : "
                    + params.getUrl());
            fail("Test failed");
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ServerProfileClientTest : testCopyServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            fail("Test failed");
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ServerProfileClientTest : testCopyServerProfile : No response from appliance : "
                    + params.getHostname());
            fail("Test failed");
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("ServerProfileClientTest : testCopyServerProfile : arguments are null ");
            fail("Test failed");
        }
        catch (SDKBadRequestException ex)
        {
			logger.error("ServerProfileClientTest : testCopyServerProfile : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (SDKTasksException e)
        {
            logger.error("ServerProfileClientTest : testCopyServerProfile : errors in task, please check task resource for more details ");
            fail("Test failed");
        }

        logger.info("ServerProfileClientTest : testCopyServerProfile : serverProfile object returned to client : "
                + taskResourceV2.toString());
    }

    private ServerProfile buildTestServerProfileDto(final RestParams params)
    {

        ServerProfileValue serverProfileValue = new ServerProfileValue();
        Firmware firmware = new Firmware();
        firmware.setFirmwareBaselineUri(null);
        firmware.setForceInstallFirmware(false);
        firmware.setManageFirmware(false);

        Boot boot = new Boot();
        List<String> order = new ArrayList<String>();
        order.add("PXE");
        order.add("CD");
        order.add("Floppy");
        order.add("USB");
        order.add("HardDisk");
        boot.setOrder(order);
        boot.setManageBoot(true);

        Bios bios = new Bios();
        bios.setManageBios(false);
        bios.setOverriddenSettings(null);

        List<NetworkForServerProfile> networkForServerProfiles = new ArrayList<NetworkForServerProfile>();
        for (String networkName : networkNames)
        {
            NetworkForServerProfile networkForServerProfile = new NetworkForServerProfile();
            networkForServerProfile.setNetworkName(networkName);
            networkForServerProfile.setBoot(BootControl.NotBootable);
            networkForServerProfile.setMaximumMbps(1000);
            networkForServerProfile.setAllocatedMbps(1000);
            networkForServerProfile.setRequestedMbps("1000");
            networkForServerProfile
                    .setNetworkType(ProfileConnectionV3.FunctionType.Ethernet);
            networkForServerProfiles.add(networkForServerProfile);
        }

        for (String networkName : fcNetworkNames)
        {
            NetworkForServerProfile networkForServerProfile = new NetworkForServerProfile();
            networkForServerProfile.setNetworkName(networkName);
            networkForServerProfile.setBoot(BootControl.NotBootable);
            networkForServerProfile.setMaximumMbps(2500);
            networkForServerProfile.setAllocatedMbps(2500);
            networkForServerProfile.setRequestedMbps("2500");
            networkForServerProfile
                    .setNetworkType(ProfileConnectionV3.FunctionType.FibreChannel);
            networkForServerProfiles.add(networkForServerProfile);
        }

        SanStorageForServerProfile sanStorageForServerProfile = new SanStorageForServerProfile();
        sanStorageForServerProfile.setHostOSType("Windows 2012 / WS2012 R2");
        List<StorageVolume> storageVolumes = new ArrayList<StorageVolume>();
        for (String volumeName : storageVolumeName)
        {
            StorageVolume storageVolume = sanStorageForServerProfile
                    .createStorageVolume();
            storageVolume.setIsEnabled(true);
            storageVolume.setStorageTargets(null);
            storageVolume.setStorageTargetType(StorageTargetType.Auto);
            storageVolume.setVolumeName(volumeName);
            storageVolume.setLunType("Auto");
            storageVolumes.add(storageVolume);
        }
        sanStorageForServerProfile.setStorageVolume(storageVolumes);

        serverProfileValue.setAffinity(ProfileAffinity.Bay);
        serverProfileValue.setBayName(bayName);
        serverProfileValue.setBios(bios);
        serverProfileValue.setBoot(boot);
        serverProfileValue.setDescription("Template Example");
        serverProfileValue.setEnclosureGroupName(enclosureGroupName);
        serverProfileValue.setFirmware(firmware);
        serverProfileValue.setLocalStorage(null);
        serverProfileValue.setMacType(ServerProfile.AssignmentType.Virtual);
        serverProfileValue.setNetworkForServerProfile(networkForServerProfiles);
        serverProfileValue
                .setSerialNumberType(ServerProfile.AssignmentType.Physical);
        serverProfileValue
                .setStorageVolumeForServerProfile(sanStorageForServerProfile);
        serverProfileValue.setTemplateName(templateName);
        serverProfileValue
                .setUseBayNameForServerHardwareUri(useBayNameForServerHardwareUri);
        serverProfileValue.setWwnType(ServerProfile.AssignmentType.Virtual);

        return resourceDtoUtilsWrapper.buildServerProfile(params,
                serverProfileValue);
    }
}
