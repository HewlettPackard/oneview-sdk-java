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
import com.hp.ov.sdk.dto.generated.Bandwidth;
import com.hp.ov.sdk.dto.generated.ConnectionTemplate;
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
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.SdkUtils;

public class ConnectionTemplateClientTest
{
    private static final Logger logger = LoggerFactory
            .getLogger(ConnectionTemplateClientTest.class);

    private ConnectionTemplateClient connectionTemplateClient;

    @Autowired
    private HttpRestClientTest httpRestClientTest;
    @Autowired
    private RestParams params;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private ResourceDtoUtils resourceDtoUtils;    

    @Autowired
    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private SampleRestParams sampleRestParams;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "name-163757359-1436772682106";

    private static final Double maxBandwidth = (double) 8000;
    private static final Double minBandwidth = (double) 2000;
	private static final String resourceId = "03521932-6d65-4a25-8a46-dfb50777acfb";
    // ================================

	//TODO - Refactor test case to get right failure messages for the user.
	
    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        connectionTemplateClient = (ConnectionTemplateClient) ctx
                .getBean("connectionTemplateClientImpl");
        resourceDtoUtils = (ResourceDtoUtils) ctx.getBean("resourceDtoUtils");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetConnectionTemplateById()
            throws InstantiationException,
            IllegalAccessException
    {
        ConnectionTemplate connectionTemplateDto = null;
        // first get the session Id
        try
        {

            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);


            // then make sdk service call to get resource
            connectionTemplateDto = connectionTemplateClient
                    .getConnectionTemplate(params, resourceId);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ConnectionTemplateClientTest : testGetConnectionTemplateById : resource you are looking is not found ");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
			logger.error("ConnectionTemplateClientTest : testGetConnectionTemplateById : no such url : "
                    + params.getUrl());
        }
        catch (SDKApplianceNotReachableException e)
        {
			logger.error("ConnectionTemplateClientTest : testGetConnectionTemplateById : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
			logger.error("ConnectionTemplateClientTest : testGetConnectionTemplateById : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
			logger.error("LoginSessionsImplTest : testGetConnectionTemplateById : arguments are null ");
        }

		logger.info("ConnectionTemplateClientTest : testGetConnectionTemplateById : connectionTemplate object returned to client : "
                + connectionTemplateDto.toString());
    }

    @Test
    public void testUpdateConnectionTemplate()
            throws InstantiationException,
            IllegalAccessException
    {
        ConnectionTemplate connectionTemplateDto = null;
		String resourceId = null;
        // first get the session Id
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            connectionTemplateDto = connectionTemplateClient
					.getConnectionTemplateByName(params, resourceName);
			
			if (null != connectionTemplateDto.getUri()) 
			{
				resourceId = sdkUtils.getResourceIdFromUri(connectionTemplateDto.getUri());
			}

			connectionTemplateDto = connectionTemplateClient
					.getConnectionTemplate(params, resourceId);
            Bandwidth bandwidth = resourceDtoUtils.buildBandwidth(maxBandwidth,
                    minBandwidth);
            connectionTemplateDto.setBandwidth(bandwidth);
            connectionTemplateDto.setETag(null);
            connectionTemplateDto.setUri(null);

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            connectionTemplateDto = connectionTemplateClient
					.updateConnectionTemplate(params, resourceId,
											connectionTemplateDto, false);
        }
        catch (SDKResourceNotFoundException ex)
        {
			logger.error("ConnectionTemplateClientTest : testUpdateConnectionTemplate :"
					+ " resource you are looking is not found for update");
			fail("Test failed");
        }
        catch (SDKBadRequestException ex)
        {
			logger.error("ConnectionTemplateClientTest : testUpdateConnectionTemplate :"
					+ " bad request, try again : "
					+ "may be duplicate resource name or invalid inputs. check inputs and try again");
            fail("Test failed");
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ConnectionTemplateClientTest : testUpdateConnectionTemplate :"
                    + " no such url : " + params.getUrl());
            fail("Test failed");
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ConnectionTemplateClientTest : testUpdateConnectionTemplate :"
                    + " Applicance Not reachabe at : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ConnectionTemplateClientTest : testUpdateConnectionTemplate :"
                    + " No response from appliance : " + params.getHostname());
            fail("Test failed");
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testUpdateConnectionTemplate : "
                    + "arguments are null ");
            fail("Test failed");
        }
        catch (SDKTasksException e)
        {
            logger.error("LoginSessionsImplTest : testUpdateConnectionTemplate : "
                    + "errors in task, please check task "
                    + "resource for more details ");
            fail("Test failed");
        }
        logger.info("ConnectionTemplateClientTest : testUpdateConnectionTemplate : "
                + "connectionTemplate object returned to client : "
                + connectionTemplateDto.toString());
    }
}
