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

import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.dto.StorageSystemCollection;
import com.hp.ov.sdk.dto.StorageSystemV2;
import com.hp.ov.sdk.dto.StorageTargetPort;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.StorageTargetPortV2;
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

public class StorageSystemClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(StorageSystemClientTest.class);

    private StorageSystemClient storageSystemClient;
    @Autowired
    private FcNetworkClient fcNetworkClient;

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
    private static final String targetPortId = "C0106578-A233-407F-B5C3-EE4FF4CAF57C";
    private static final String resourceName = "ThreePAR7200-3050";
    private static final String username = "dcs";
    private static final String password = "dcs";
    private static final String ipAddress = "172.18.11.11";
    private static final List<String> fcNetworkName_A = Arrays.asList("FC_Network_A");
    private static final List<String> fcNetworkName_B = Arrays.asList("FC_Network_B");
    private static final String resourceId = "TXQ1000307";

    // ================================

    //TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");

        storageSystemClient = (StorageSystemClient) ctx
                .getBean("storageSystemClientImpl");
        fcNetworkClient = (FcNetworkClient) ctx.getBean("fcNetworkClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetStorageSystemById()
            throws InstantiationException,
            IllegalAccessException
    {
        StorageSystemV2 storageSystemDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storageSystemDto = storageSystemClient.getStorageSystem(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemById : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemById : no such url : "
                    + params.getUrl());

        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemById : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStorageSystemById : arguments are null ");
        }

        logger.info("StorageSystemClientTest : testGetStorageSystemById : storageSystem object returned to client : "
                + storageSystemDto.toString());
    }

    @Test
    public void testGetStoragePoolsForStorageSystem()
            throws InstantiationException, IllegalAccessException,
            SDKResourceNotFoundException, SDKNoSuchUrlException
    {
        String resourceId = null;
        StorageSystemV2 storageSystemDto = null;
        StoragePoolCollection storagePoolCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storageSystemDto = storageSystemClient
                    .getStorageSystemByName(params, resourceName);

            if (null != storageSystemDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(storageSystemDto
                        .getUri());
            }

            // then make sdk service call to get resource
            storagePoolCollectionDto = storageSystemClient
                    .getStoragePoolsForStorageSystem(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testGetStoragePoolsForStorageSystem : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testGetStoragePoolsForStorageSystem : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testGetStoragePoolsForStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testGetStoragePoolsForStorageSystem : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStoragePoolsForStorageSystem : arguments are null ");
        }
        logger.info("StorageSystemClientTest : testGetStoragePoolsForStorageSystem : storageSystem object returned to client : "
                + storagePoolCollectionDto.toString());
    }

    //TODO-expand the failure scenario to help user 
    @Test
    public void testGetAllManagedPortsForStorageSystem()
            throws InstantiationException, IllegalAccessException,
            SDKResourceNotFoundException, SDKNoSuchUrlException
    {
        String resourceId = null;
        StorageSystemV2 storageSystemDto = null;
        StorageTargetPortCollection storageTargetPortCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storageSystemDto = storageSystemClient
                    .getStorageSystemByName(params, resourceName);

            if (null != storageSystemDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(storageSystemDto
                        .getUri());
            }

            // then make sdk service call to get resource
            storageTargetPortCollectionDto = storageSystemClient
                    .getAllManagedPortsForStorageSystem(params, resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testGetAllManagedPortsForStorageSystem : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testGetAllManagedPortsForStorageSystem : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testGetAllManagedPortsForStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testGetAllManagedPortsForStorageSystem : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllManagedPortsForStorageSystem : arguments are null ");
        }
        logger.info("StorageSystemClientTest : testGetAllManagedPortsForStorageSystem : storageSystem object returned to client : "
                + storageTargetPortCollectionDto.toString());
    }

    //TODO-expand the failure scenario to help user 
    @Test
    public void testGetManagedPortsForStorageSystem()
            throws InstantiationException, IllegalAccessException,
            SDKResourceNotFoundException, SDKNoSuchUrlException
    {
        String resourceId = null;
        StorageSystemV2 storageSystemDto = null;
        StorageTargetPortV2 storageTargetPortDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storageSystemDto = storageSystemClient
                    .getStorageSystemByName(params, resourceName);

            if (null != storageSystemDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(storageSystemDto
                        .getUri());
            }

            // then make sdk service call to get resource
            storageTargetPortDto = storageSystemClient
                    .getManagedPortsForStorageSystem(params, resourceId,
                            targetPortId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testGetManagedPortsForStorageSystem : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testGetManagedPortsForStorageSystem : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testGetManagedPortsForStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testGetManagedPortsForStorageSystem : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetManagedPortsForStorageSystem : arguments are null ");
        }
        logger.info("StorageSystemClientTest : testGetManagedPortsForStorageSystem : storageSystem object returned to client : "
                + storageTargetPortDto.toString());
    }

    @Test
    public void testGetAllStorageSystem()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        StorageSystemCollection storageSystemCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storageSystemCollectionDto = storageSystemClient
                    .getAllStorageSystems(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testGetAllStorageSystem : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testGetAllStorageSystem : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testGetAllStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testGetAllStorageSystem : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllStorageSystem : arguments are null ");
        }
        logger.info("StorageSystemClientTest : testGetAllStorageSystem : storageSystem object returned to client : "
                + storageSystemCollectionDto.toString());
    }

    @Test
    public void testGetStorageSystemByName()
            throws InstantiationException,
            IllegalAccessException
    {
        StorageSystemV2 storageSystemDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            storageSystemDto = storageSystemClient.getStorageSystemByName(
                    params, resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemByName : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemByName : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemByName : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testGetStorageSystemByName : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetStorageSystemByName : arguments are null ");
        }

        logger.info("StorageSystemClientTest : testGetStorageSystemByName : storageSystem object returned to client : "
                + storageSystemDto.toString());
    }

    @Test
    public void testCreateStorageSystem()
            throws InstantiationException,
            IllegalAccessException
    {
        boolean testfailed = false;
        String createStorage = null;
        AddStorageSystemCredentials addStorageSystemCredentialsDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create storageSystem request body
            addStorageSystemCredentialsDto = buildTestStorageSystemDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            createStorage = storageSystemClient.createStorageSystem(params,
                    addStorageSystemCredentialsDto, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testCreateStorageSystem : resource you are looking is not found "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("StorageSystemClientTest : testCreateStorageSystem : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testCreateStorageSystem : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testCreateStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateStorageSystem : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateStorageSystem : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("StorageSystemClientTest : testCreateStorageSystem : storageSystem object returned to client : "
                + createStorage);
    }

    @Test
    public void testUpdateStorageSystem()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id
        String updateStorage = null;
        String resourceId = null;
        StorageSystemV2 storageSystemDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storageSystemDto = storageSystemClient.getStorageSystemByName(
                    params, resourceName);

            if (null != storageSystemDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(storageSystemDto
                        .getUri());
            }

            final StorageSystemV2 updateStorageSystemDto = buildUpdateStorageSystemDto(storageSystemDto);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            updateStorage = storageSystemClient.updateStorageSystem(params,
                    resourceId, updateStorageSystemDto,
                    false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testUpdateStorageSystem :"
                    + " resource you are looking is not found for update ");
            fail("Test failed");
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("StorageSystemClientTest : testUpdateStorageSystem :"
                    + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testUpdateStorageSystem :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testUpdateStorageSystem :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testUpdateStorageSystem :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateStorageSystem : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateStorageSystem : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("StorageSystemClientTest : testUpdateStorageSystem : "
                + "storageSystem object returned to client : " + updateStorage);
    }

    @Test
    public void testDeleteStorageSystem()
            throws InstantiationException,
            IllegalAccessException
    {
        // first get the session Id
        String deleteStorage = null;
        String resourceId = null;
        StorageSystemV2 storageSystemDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // fetch resource Id using resource name
            storageSystemDto = storageSystemClient
                    .getStorageSystemByName(params, resourceName);

            if (null != storageSystemDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(storageSystemDto
                        .getUri());
            }

            // then make sdk service call to get resource
            deleteStorage = storageSystemClient.deleteStorageSystem(params,
                    resourceId);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("StorageSystemClientTest : testDeleteStorageSystem : resource you are looking is not found for delete ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("StorageSystemClientTest : testDeleteStorageSystem : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("StorageSystemClientTest : testDeleteStorageSystem : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("StorageSystemClientTest : testDeleteStorageSystem : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteStorageSystem : arguments are null ");
        }

        logger.info("StorageSystemClientTest : testDeleteStorageSystem : storageSystem object returned to client : "
                + deleteStorage);
    }

    //TODO - Move Uri fetch logic to SdkUtils

    private AddStorageSystemCredentials buildTestStorageSystemDto()
    {
        final AddStorageSystemCredentials dto = new AddStorageSystemCredentials();
        dto.setIp_hostname(ipAddress);
        dto.setUsername(username);
        dto.setPassword(password);
        return dto;

    }

    //TODO - Move Uri fetch logic to SdkUtils

    private StorageSystemV2 buildUpdateStorageSystemDto(
            final StorageSystemV2 storageSystemDto)
    {

        final List<StorageTargetPortV2> tempStorageTargetPort = new ArrayList<StorageTargetPortV2>();
        final List<StorageTargetPort> unMangedStorageTargetPort = new ArrayList<StorageTargetPort>();
        for (int i = 0; i < storageSystemDto.getUnmanagedPorts().size(); i++)
        {
            if (storageSystemDto.getUnmanagedPorts().get(i).getName()
                    .equalsIgnoreCase("0:1:1"))
            {
                final StorageTargetPortV2 managed_one = new StorageTargetPortV2();
                managed_one.setActualNetworkUri(storageSystemDto
                        .getUnmanagedPorts().get(i).getActualNetworkUri());
                managed_one.setExpectedNetworkName(storageSystemDto
                        .getUnmanagedPorts().get(i).getExpectedNetworkName());
                managed_one.setGroupName(storageSystemDto.getUnmanagedPorts()
                        .get(i).getGroupName());
                String fc_network_one = null;
                for (int j = 0; j < fcNetworkName_A.size(); j++)
                {
                    fc_network_one = fcNetworkClient.getFcNetworkByName(params,
                            fcNetworkName_A.get(j)).getUri();
                }
                managed_one.setExpectedNetworkUri(fc_network_one);
                managed_one.setPortName(storageSystemDto.getUnmanagedPorts()
                        .get(i).getPortName());
                managed_one.setPortWwn(storageSystemDto.getUnmanagedPorts()
                        .get(i).getPortWwn());
                managed_one.setRefreshState(storageSystemDto
                        .getUnmanagedPorts().get(i).getRefreshState());
                managed_one.setStateReason(storageSystemDto.getUnmanagedPorts()
                        .get(i).getStateReason());
                managed_one.setType(storageSystemDto.getUnmanagedPorts().get(i)
                        .getType());
                managed_one.setName(storageSystemDto.getUnmanagedPorts().get(i)
                        .getPortName());
                managed_one.setGroupName("Auto");
                tempStorageTargetPort.add(managed_one);
                unMangedStorageTargetPort.add(storageSystemDto
                        .getUnmanagedPorts().get(i));

            }
            else if (storageSystemDto.getUnmanagedPorts().get(i).getName()
                    .equalsIgnoreCase("0:1:2"))
            {
                final StorageTargetPortV2 managed_two = new StorageTargetPortV2();
                managed_two.setActualNetworkUri(storageSystemDto
                        .getUnmanagedPorts().get(i).getActualNetworkUri());
                managed_two.setExpectedNetworkName(storageSystemDto
                        .getUnmanagedPorts().get(i).getExpectedNetworkName());
                managed_two.setGroupName(storageSystemDto.getUnmanagedPorts()
                        .get(i).getGroupName());
                String fc_network_two = null;
                for (int j = 0; j < fcNetworkName_B.size(); j++)
                {
                    fc_network_two = fcNetworkClient.getFcNetworkByName(params,
                            fcNetworkName_B.get(j)).getUri();
                }
                managed_two.setExpectedNetworkUri(fc_network_two);
                managed_two.setPortName(storageSystemDto.getUnmanagedPorts()
                        .get(i).getPortName());
                managed_two.setPortWwn(storageSystemDto.getUnmanagedPorts()
                        .get(i).getPortWwn());
                managed_two.setRefreshState(storageSystemDto
                        .getUnmanagedPorts().get(i).getRefreshState());
                managed_two.setStateReason(storageSystemDto.getUnmanagedPorts()
                        .get(i).getStateReason());
                managed_two.setType(storageSystemDto.getUnmanagedPorts().get(i)
                        .getType());
                managed_two.setName(storageSystemDto.getUnmanagedPorts().get(i)
                        .getPortName());
                managed_two.setGroupName("Auto");
                tempStorageTargetPort.add(managed_two);
                unMangedStorageTargetPort.add(storageSystemDto
                        .getUnmanagedPorts().get(i));
            }
        }

        storageSystemDto.setManagedPorts(tempStorageTargetPort);
        for (final StorageTargetPort storageTargetPort : unMangedStorageTargetPort)
        {
            storageSystemDto.getUnmanagedPorts().remove(storageTargetPort);
        }
        storageSystemDto.setManagedDomain("TestDomain");
        final List<String> unmanagedDomains = storageSystemDto.getUnmanagedDomains();
        unmanagedDomains.remove("TestDomain");
        storageSystemDto.setUnmanagedDomains(unmanagedDomains);

        return storageSystemDto;
    }

}
