/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.scmb.services;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.scmb.services.ScmbAlertsHandler;
import com.hp.ov.sdk.messaging.scmb.services.ScmbConnectionManagerImpl;
import com.hp.ov.sdk.messaging.scmb.services.ScmbMessageExecutionQueue;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.util.SdkUtils;

public class ScmbConnectionManagerImplTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(ScmbConnectionManagerImplTest.class);

    private RestParams params;
    private static ScmbConnectionManagerImpl objectUnderTest;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private SampleRestParams sampleRestParams;

    @Autowired
    private ScmbAlertsHandler handler;

    @Before
    public void init()
    {
        final ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");

        objectUnderTest = (ScmbConnectionManagerImpl) ctx
                .getBean("scmbConnectionManagerImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
        handler = (ScmbAlertsHandler) ctx.getBean("scmbAlertsHandler");
    }

    @Test
    public void testScmbProcessor()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create MessageExecutionQueue object
            final ScmbMessageExecutionQueue messageQueue = new ScmbMessageExecutionQueue(
                    handler);

            // start the dequeue process
            messageQueue.start();

            // then start scmb
            objectUnderTest.startScmb(params);
            objectUnderTest.processConsumer(params,
                    SdkConstants.SCMB_ALERTS_ROUTING_KEY, messageQueue);
            // TODO - start next processor with different routing key
            // objectUnderTest.processConsumer(params, "scmb.interconnects.#");
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testGetAllNetwork : resource not found : "
                    + params.getHostname());
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testGetAllNetwork : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : arguments are null ");
        }
        catch (final SDKScmbConnectionNotFoundException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : scmb connection not found ");
        }

    }

    @Test
    public void testStopScmb()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then stop scmb
            objectUnderTest.stopScmb(params);
        }
        catch (final SDKResourceNotFoundException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testGetAllNetwork : resource not found : "
                    + params.getHostname());
        }
        catch (final SDKNoSuchUrlException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testGetAllNetwork : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : arguments are null ");
        }
        catch (final SDKScmbConnectionNotFoundException e)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : connection not found ");
        }
    }

    // main
    @Test
    public void mainTest()
    {
        testScmbProcessor();
        try
        {
            Thread.sleep(300000); // Sample value to before stopping scmb ( 300
                                  // secs = 5 mins )
        }
        catch (final InterruptedException e)
        {
            logger.error("ScmbConnectionManagerImplTest : mainTest : thread interrupted ");
        }
        testStopScmb();
    }
}
