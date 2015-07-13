/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.login;

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
import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.LoginSessions;
import com.hp.ov.sdk.util.SdkUtils;

public class LoginSessionsImplTest
{
    private static final Logger logger = LoggerFactory.getLogger(LoginSessionsImplTest.class);

    @Autowired
    private RestParams params;

    @Autowired
    private LoginSessions loginSessions;

    @Autowired
    private LoginSessionAdaptor loginSessionAdaptor;

    @Autowired
    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private SampleRestParams sampleRestParams;

    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        loginSessions = (LoginSessions) ctx.getBean("loginSessionsImpl");
        loginSessionAdaptor = (LoginSessionAdaptor) ctx.getBean("loginSessionAdaptor");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");

    }

    @Test
    public void testGetLoginSession()
    {

        //Get the basic REST parameters like hostname, username and password
        params = sampleRestParams.getBasicRestParams();

        //update the parameters with version and sessionId
        params = sdkUtils.createRestParamsWithoutSessionId(params);
        //create input request body
        LoginSessionDto loginSessionDto = loginSessionAdaptor.buildDto(params);

        String sessionId = null;
        try
        {
            //call the service layer
            sessionId = loginSessions.getLoginSessionId(params, loginSessionDto);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("LoginSessionsImplTest : testGetLoginSession : resource you are looking is not found at: "
                    + params.getHostname());
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("LoginSessionsImplTest : testGetLoginSession : no such url : " + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("LoginSessionsImplTest : testGetLoginSession : Applicance Not reachabe at : "
                    + params.getHostname()
                    + ": message:"
                    + e.getMessageKey());
            fail("Test failed");
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("LoginSessionsImplTest : testGetLoginSession : arguments are null ");
            fail("Test failed");
        }

        logger.info("LoginSessionsTest - testGetLoginSession : " + sessionId);
    }

}
