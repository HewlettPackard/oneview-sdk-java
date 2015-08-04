/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.scmb.consumer.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.constants.samples.SamplesConstants;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.scmb.services.ScmbAlertsHandler;
import com.hp.ov.sdk.messaging.scmb.services.ScmbConnectionManager;
import com.hp.ov.sdk.messaging.scmb.services.ScmbMessageExecutionQueue;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class ScmbClient
{

    private RestParams params;
    private static ScmbConnectionManager objectUnderTest;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static ScmbAlertsHandler scmbAlertsHandler;

    public static void init()
    {
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        sampleRestParams = new SampleRestParams();
        objectUnderTest = HPOneViewSdkBeanFactory.getScmbConnectionManager();
    }

    public void scmbProcessor()
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
                    scmbAlertsHandler);

            // start the dequeue process
            messageQueue.start();

            // then start scmb
            objectUnderTest.startScmb(params);
            objectUnderTest.processConsumer(params,
                    SamplesConstants.SCMB_ALERTS_ROUTING_KEY, messageQueue);
            // TODO - start next processor with different routing key
            // objectUnderTest.processConsumer(params, "scmb.interconnects.#");
        }
        catch (final SDKResourceNotFoundException ex)
        {
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : resource not found : "
                    + params.getHostname());
        }
        catch (final SDKNoSuchUrlException ex)
        {
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKNoResponseException ex)
        {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : No response from appliance : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : arguments are null ");
        }
        catch (final SDKScmbConnectionNotFoundException ex)
        {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : scmb connection not found ");
        }

    }

    private void stopScmb()
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
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : resource not found : "
                    + params.getHostname());
        }
        catch (final SDKNoSuchUrlException ex)
        {
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : no such url : "
                    + params.getHostname());
        }
        catch (final SDKApplianceNotReachableException e)
        {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : Applicance Not reachabe at : "
                    + params.getHostname());
        }
        catch (final SDKInvalidArgumentException ex)
        {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : arguments are null ");
        }
        catch (final SDKScmbConnectionNotFoundException e)
        {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : connection not found ");
        }
    }

    // main

    public static void main(final String[] args)
    {
        init();
        final ScmbClient scmbClient = new ScmbClient();
        final ScmbHandler handler = new ScmbHandler();
        scmbAlertsHandler = handler.getScmbAlertsHandler();
        scmbClient.scmbProcessor();
        try
        {
            Thread.sleep(300000); // Sample value to before stopping scmb ( 300
                                  // secs = 5 mins )
        }
        catch (final InterruptedException e)
        {
            System.out.println("ScmbConnectionManagerImplTest : mainTest : thread interrupted ");
        }
        scmbClient.stopScmb();
    }
}
