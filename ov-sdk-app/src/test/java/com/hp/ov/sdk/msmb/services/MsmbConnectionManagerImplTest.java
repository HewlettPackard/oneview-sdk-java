/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.msmb.services;

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
import com.hp.ov.sdk.exceptions.SDKMsmbConnectionNotFoundException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.msmb.services.MsmbAlertsHandler;
import com.hp.ov.sdk.messaging.msmb.services.MsmbConnectionManagerImpl;
import com.hp.ov.sdk.messaging.msmb.services.MsmbMessageExecutionQueue;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.login.SampleRestParams;
import com.hp.ov.sdk.util.SdkUtils;

public class MsmbConnectionManagerImplTest
{

    private static final Logger logger = LoggerFactory
            .getLogger(MsmbConnectionManagerImplTest.class);

    private RestParams params;
    private static MsmbConnectionManagerImpl objectUnderTest;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private SampleRestParams sampleRestParams;

    @Autowired
    private MsmbAlertsHandler handler;

    @Before
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        objectUnderTest = (MsmbConnectionManagerImpl) ctx
                .getBean("msmbConnectionManagerImpl");
        sdkUtils = (SdkUtils) ctx.getBean("sdkUtils");
        sampleRestParams = (SampleRestParams) ctx.getBean("sampleRestParams");
        handler = (MsmbAlertsHandler) ctx.getBean("msmbAlertsHandler");
    }

    @Test
    public void testMsmbProcessor()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create MessageExecutionQueue object
            MsmbMessageExecutionQueue messageQueue = new MsmbMessageExecutionQueue(
                    handler);

            // start the dequeue process
            messageQueue.start();

            // then start scmb
            objectUnderTest.startMsmb(params);
            objectUnderTest.processConsumer(params,
                    SdkConstants.MSMB_ALERTS_ROUTING_KEY, messageQueue);
            // TODO - start next processor with different routing key
            // objectUnderTest.processConsumer(params, "scmb.interconnects.#");
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : resource not found : "
                    + params.getHostname());
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKNoResponseException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : No response from appliance : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : arguments are null ");
        }
        catch (SDKScmbConnectionNotFoundException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testScmbProcessor : scmb connection not found ");
        }

        // logger.info("ScmbConsumerManagerImplTest : testScmbProcessor : rabbitmq client certificate object returned to client : "
        // + rabbitMqClientCertDto.toString());
    }

    @Test
    public void testStopMsmb()
    {
        try
        {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then stop scmb
            objectUnderTest.stopMsmb(params);
        }
        catch (SDKResourceNotFoundException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : resource not found : "
                    + params.getHostname());
        }
        catch (SDKNoSuchUrlException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : no such url : "
                    + params.getHostname());
        }
        catch (SDKApplianceNotReachableException e)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (SDKInvalidArgumentException ex)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : arguments are null ");
        }
        catch (SDKMsmbConnectionNotFoundException e)
        {
            logger.error("ScmbConnectionManagerImplTest : testStopScmb : connection not found ");
        }
    }

    // main
    @Test
    public void mainTest()
    {
        testMsmbProcessor();
        try
        {
            Thread.sleep(1200000); // Sample value to before stopping scmb (
                                   // secs = 20 mins )
        }
        catch (InterruptedException e)
        {
            logger.error("ScmbConnectionManagerImplTest : mainTest : thread interrupted ");
        }
        testStopMsmb();
    }
}
