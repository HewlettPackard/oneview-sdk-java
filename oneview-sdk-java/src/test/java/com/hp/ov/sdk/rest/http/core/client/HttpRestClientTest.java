/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.http.core.client;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.certs.SslPropertiesManager;
import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.login.LoginSessions;
import com.hp.ov.sdk.rest.login.LoginSessionsImpl;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class HttpRestClientTest
{

    private final Logger logger = LoggerFactory.getLogger(HttpRestClientTest.class);
    @Autowired
    private HttpSslProperties props;

    @Autowired
    private SslPropertiesManager util;

    @Autowired
    private RestParams params;

    private String sessionId;

    private LoginSessionDto loginSessionDto;

    private LoginSessionAdaptor loginSessionAdaptor;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private LoginSessions loginSessions;

    @Autowired
    private SampleRestParams sampleRestParams;

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        loginSessionAdaptor = (LoginSessionAdaptor) ctx.getBean("loginSessionAdaptor");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        loginSessions = (LoginSessionsImpl) ctx.getBean("loginSessionsImpl");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
    }

    @Test
    public void testGetSessionId()
    {
        //Get the basic REST parameters like hostname, username and password
        params = sampleRestParams.getBasicRestParams();

        //update parameters with version and sessionId
        params = sdkUtils.createRestParamsWithoutSessionId(params);
        //build the input json request
        loginSessionDto = loginSessionAdaptor.buildDto(params);

        try
        {
            sessionId = loginSessions.getLoginSessionId(params, loginSessionDto);
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Error code : " + e.getErrorCode());
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Error Message : " + e.getMessage());
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Applicance Not reachabe at :" + params.getHostname());
        }
        catch (final SDKNoSuchUrlException e)
        {
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Error code : " + e.getErrorCode());
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Error Message : " + e.getMessage());
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Applicance Not reachabe at :" + params.getHostname());
        }
        catch (final SDKResourceNotFoundException e)
        {
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Error code : " + e.getErrorCode());
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Error Message : " + e.getMessage());
            logger.error("HttpRestClientTest : getSessionIdFromHPOV : Applicance Not reachabe at :" + params.getHostname());
        }

        logger.info("HttpRestClientTest : getSessionIdFromHPOV : sessionId returned = " + sessionId);
    }

    //TODO - add missing test cases

}
