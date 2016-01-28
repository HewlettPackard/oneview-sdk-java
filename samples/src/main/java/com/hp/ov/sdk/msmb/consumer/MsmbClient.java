/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.msmb.consumer;

import com.hp.ov.sdk.certs.MessagingCertificateManager;
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

    private final MsmbConnectionManager objectUnderTest;

    private RestParams params;

    private MsmbClient() {
        this.objectUnderTest = new MsmbConnectionManager(MessagingCertificateManager.getInstance());
    }

    public void msmbProcessor() {
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = SampleRestParams.getInstance().getBasicRestParams();

            // update the parameters with version and sessionId
            params = SdkUtils.getInstance().createRestParams(params);

            // create MessageExecutionQueue object

            final MsmbMessageExecutionQueue messageQueue = new MsmbMessageExecutionQueue(
                    new MsmbHandler().getMsmbAlertsHandler());

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

        // LOGGER.info("ScmbConsumerManagerImplTest : testScmbProcessor : rabbitmq client certificate object returned to client : "
        // + rabbitMqClientCertDto.toString());
    }

    public void stopMsmb() {
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = SampleRestParams.getInstance().getBasicRestParams();

            // update the parameters with version and sessionId
            params = SdkUtils.getInstance().createRestParams(params);

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

    public static void main(final String[] args) {
        final MsmbClient msmbClient = new MsmbClient();

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
