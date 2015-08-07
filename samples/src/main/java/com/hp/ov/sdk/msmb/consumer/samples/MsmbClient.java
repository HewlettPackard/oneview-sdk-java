/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.msmb.consumer.samples;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.constants.samples.SamplesConstants;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKMsmbConnectionNotFoundException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.msmb.services.MsmbAlertsHandler;
import com.hp.ov.sdk.messaging.msmb.services.MsmbConnectionManager;
import com.hp.ov.sdk.messaging.msmb.services.MsmbMessageExecutionQueue;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class MsmbClient {

    private RestParams params;
    private static MsmbConnectionManager objectUnderTest;
    private static SdkUtils sdkUtils;
    private static SampleRestParams sampleRestParams;
    private static MsmbAlertsHandler msmbAlertsHandler;

    public static void init() {
        sdkUtils = HPOneViewSdkBeanFactory.getSdkUtils();
        sampleRestParams = new SampleRestParams();
        objectUnderTest = HPOneViewSdkBeanFactory.getMsmbConnectionManager();
    }

    public void msmbProcessor() {
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // create MessageExecutionQueue object
            final MsmbMessageExecutionQueue messageQueue = new MsmbMessageExecutionQueue(msmbAlertsHandler);

            // start the dequeue process
            messageQueue.start();

            // then start scmb
            objectUnderTest.startMsmb(params);
            objectUnderTest.processConsumer(params, SamplesConstants.MSMB_ALERTS_ROUTING_KEY, messageQueue);
            // TODO - start next processor with different routing key
            // objectUnderTest.processConsumer(params, "scmb.interconnects.#");
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : resource not found : " + params.getHostname());
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : No response from appliance : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : arguments are null ");
        } catch (final SDKScmbConnectionNotFoundException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testScmbProcessor : scmb connection not found ");
        }

        // logger.info("ScmbConsumerManagerImplTest : testScmbProcessor : rabbitmq client certificate object returned to client : "
        // + rabbitMqClientCertDto.toString());
    }

    public void stopMsmb() {
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = sampleRestParams.getBasicRestParams();

            // update the parameters with version and sessionId
            params = sdkUtils.createRestParams(params);

            // then stop scmb
            objectUnderTest.stopMsmb(params);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : resource not found : " + params.getHostname());
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : arguments are null ");
        } catch (final SDKMsmbConnectionNotFoundException e) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : connection not found ");
        }
    }

    // main

    public static void main(final String[] args) {
        init();
        final MsmbClient msmbClient = new MsmbClient();
        final MsmbHandler handler = new MsmbHandler();
        msmbAlertsHandler = handler.getMsmbAlertsHandler();
        msmbClient.msmbProcessor();
        try {
            Thread.sleep(300000); // Sample value to before stopping scmb ( 300
                                  // secs = 5 mins )
        } catch (final InterruptedException e) {
            System.out.println("ScmbConnectionManagerImplTest : mainTest : thread interrupted ");
        }
        msmbClient.stopMsmb();
    }
}
