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

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.JsonRequest;
import com.hp.ov.sdk.dto.NetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.dto.generated.Bandwidth;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.tasks.TaskServiceManager;
import com.hp.ov.sdk.util.SdkUtils;

public class NetworkClientTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(NetworkClientTest.class);

    private NetworkClient networkClient;
    
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
    private static final String resourceName = "Eth-demo";
    private static final Integer vlanId = 333;
    private static final String bulkNetworkName = "Prod";
    private static final String vlanRange = "401-410";
	private static final String resourceId = "c46b8213-bbde-4a3f-83d0-bcb93fc8aa02";
    private static final Double maxBandwidth = (double) 8000;
    private static final Double minBandwidth = (double) 2000;
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.	

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        
        networkClient = (NetworkClient) ctx.getBean("networkClientImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        taskMonitorManager = (TaskMonitorManager) ctx
                .getBean("taskMonitorManagerImpl");
        taskServiceManager = (TaskServiceManager) ctx
                .getBean("taskServiceManagerImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetNetworkById()
            throws InstantiationException,
            IllegalAccessException
    {
        Network networkDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            networkDto = networkClient.getNetwork(params, resourceId);

            logger.info("NetworkClientTest : testGetNetwork : network object returned to client : "
                    + networkDto.toString());

        }
        catch (final SDKResourceNotFoundException ex)
        {
			logger.error("NetworkClientTest : testGetNetworkById : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testGetNetworkById : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testGetNetworkById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkClientTest : testGetNetworkById : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetNetworkById : arguments are null ");
        }

    }

    @Test
    public void testGetAllNetwork()
            throws InstantiationException,
            IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException
    {
        NetworkCollection networkCollectionDto = null;
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            networkCollectionDto = networkClient.getAllNetworks(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
			logger.error("NetworkClientTest : testGetAllNetwork : resource you are looking is not found");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testGetAllNetwork : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testGetAllNetwork : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkClientTest : testGetAllNetwork : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetAllNetwork : arguments are null ");
        }
        logger.info("NetworkClientTest : testGetAllNetwork : network object returned to client : "
                + networkCollectionDto.toString());
    }

    @Test
    public void testGetNetworkByName()
            throws InstantiationException,
            IllegalAccessException
    {
        Network networkDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then make sdk service call to get resource
            networkDto = networkClient.getNetworkByName(params, resourceName);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkClientTest : testGetNetworkByName : resource you are looking is not found");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testGetNetworkByName : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testGetNetworkByName : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkClientTest : testGetNetworkByName : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("NetworkClientTest : testGetNetworkByName : arguments are null ");
        }

        logger.info("NetworkClientTest : testGetNetworkByName: network object returned to client : "
                + networkDto.toString());
    }

    @Test
    public void testCreateNetwork()
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
            final Network networkDto = buildNetworkDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.createNetwork(params, networkDto,
                    false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
			logger.error("NetworkClientTest : testCreateNetwork : resource you are looking is not found");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("NetworkClientTest : testCreateNetwork : bad request, "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testCreateNetwork : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testCreateNetwork : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateNetwork : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateNetwork : "
                    + "errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("NetworkClientTest : testCreateNetwork : network object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testUpdateNetwork()
            throws InstantiationException,
            IllegalAccessException
    {
        String resourceId = null;
        Network networkDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            networkDto = networkClient.getNetworkByName(params, resourceName);
            // test values
            networkDto.setName(resourceName + "_updated");
            // Negative test case
            // networkDto.setVlanId(5000);
            // end
            if (null != networkDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(networkDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.updateNetwork(params, resourceId,
                    networkDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("NetworkClientTest : testUpdateNetwork :"
					+ " resource you are looking is not found for update");
            fail("Test failed");
        }
        catch (final SDKBadRequestException ex)
        {
            logger.error("NetworkClientTest : testUpdateNetwork :"
                    + " bad request, may be duplicate resource name or invalids inputs. check inputs and try again : ");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testUpdateNetwork :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testUpdateNetwork :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkClientTest : testUpdateNetwork :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateNetwork : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateNetwork : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("NetworkClientTest : testUpdateNetwork : "
                + "network object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testDeleteNetwork()
            throws InstantiationException,
            IllegalAccessException
    {
        String resourceId = null;
        Network networkDto = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            networkDto = networkClient.getNetworkByName(params, resourceName);

            if (null != networkDto.getUri())
            {
                resourceId = sdkUtils.getResourceIdFromUri(networkDto.getUri());
            }
            // then make sdk service call to get resource
            taskResourceV2 = networkClient.deleteNetwork(params, resourceId,
                    false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
			logger.error("NetworkClientTest : testDeleteNetwork : resource you are looking is not found for delete");
            fail("Test failed");
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testDeleteNetwork : no such url : "
                    + params.getUrl());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testDeleteNetwork : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("NetworkClientTest : testDeleteNetwork : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testDeleteNetwork : arguments are null ");
        }

        logger.info("NetworkClientTest : testDeleteNetwork : network object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testCreateNetworkInBulk()
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
            final BulkEthernetNetwork bulkEthernetNetworkDto = buildBulkEthernetNetworkDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.createNetworkInBulk(params,
                    bulkEthernetNetworkDto, false, false);
        }
        catch (final SDKResourceNotFoundException ex)
        {
			logger.error("NetworkClientTest : testCreateNetworkInBulkInBulk : resource you are looking is not found ");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
			logger.error("NetworkClientTest : testCreateNetworkInBulkInBulk : bad request, try again  "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testCreateNetworkInBulkInBulk : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testCreateNetworkInBulk : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("NetworkClientTest : testCreateNetworkInBulk : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("NetworkClientTest : testCreateNetworkInBulk : "
                    + "errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("NetworkClientTest : testCreateNetworkInBulk : network object returned to client : "
                + taskResourceV2.toString());
    }

    @Test
    public void testCreateNetworkUsingJsonRequest()
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

            // create network request body
            final Network networkDto = buildTestNetworkDtoWithJsonRequest();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = networkClient.createNetwork(params, networkDto,
                    false, true);
        }
        catch (final SDKResourceNotFoundException ex)
        {
			logger.error("NetworkClientTest : testCreateNetworkUsingJsonRequest : resource you are looking is not found ");
            testfailed = true;
        }
        catch (final SDKBadRequestException ex)
        {
			logger.error("NetworkClientTest : testCreateNetworkUsingJsonRequest : bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            testfailed = true;
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("NetworkClientTest : testCreateNetworkUsingJsonRequest : no such url : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("NetworkClientTest : testCreateNetworkUsingJsonRequest : Applicance Not reachabe at : "
                    + params.getHostname());
            testfailed = true;
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testCreateNetworkUsingJsonRequest : arguments are null ");
            testfailed = true;
        }
        catch (final SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testCreateNetworkUsingJsonRequest : errors in task, please check task resource for more details ");
            testfailed = true;
        }

        if (testfailed == true)
        {
            fail("Test case failed");
        }
        logger.info("NetworkClientTest : testCreateNetworkUsingJsonRequest : network object returned to client : "
                + taskResourceV2.toString());
    }

    public Network buildTestNetworkDtoWithJsonRequest()
    {

        final Network networkDto = new Network();
        final JsonRequest jsonRequest = new JsonRequest();
        jsonRequest
                .setBody("{\"type\":\"ethernet-networkV2\",\"vlanId\":103,\"smartLink\":true,\"privateNetwork\":false,\"purpose\":\"General\",\"ethernetNetworkType\":\"Tagged\",\"description\":null,\"name\":\"Test_3\",\"category\":\"ethernet-networks\"}");
        networkDto.setJsonRequest(jsonRequest);

        return networkDto;
    }

    public Network buildNetworkDto()
    {
        final Network dto = new Network();
        dto.setVlanId(vlanId);
        dto.setPurpose(Network.Purpose.General);
        dto.setName(resourceName);
        dto.setPrivateNetwork(false);
        dto.setSmartLink(true);
        dto.setConnectionTemplateUri(null);
        dto.setEthernetNetworkType(Network.EthernetNetworkType.Tagged);
        dto.setType(ResourceCategory.RC_NETWORK);

        final ConnectionTemplate connectionTemplate = new ConnectionTemplate();

        final Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(maxBandwidth);
        bandwidth.setTypicalBandwidth(minBandwidth);
        connectionTemplate.setBandwidth(bandwidth);
        dto.setConnectionTemplate(connectionTemplate);

        return dto;

    }

    public BulkEthernetNetwork buildBulkEthernetNetworkDto()
    {
        final BulkEthernetNetwork bulkEthernetNetworkDto = new BulkEthernetNetwork();

        bulkEthernetNetworkDto.setVlanIdRange(vlanRange);
        bulkEthernetNetworkDto.setPurpose(BulkEthernetNetwork.Purpose.General);
        bulkEthernetNetworkDto.setNamePrefix(bulkNetworkName);
        bulkEthernetNetworkDto.setSmartLink(false);
        bulkEthernetNetworkDto.setPrivateNetwork(false);
        bulkEthernetNetworkDto.setType(ResourceCategory.RC_BULK_NETWORK);

        final Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(maxBandwidth);
        bandwidth.setTypicalBandwidth(minBandwidth);

        bulkEthernetNetworkDto.setBandwidth(bandwidth);
        return bulkEthernetNetworkDto;
    }

}
