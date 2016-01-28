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
package com.hp.ov.sdk.scmb.consumer;

import com.hp.ov.sdk.certs.MessagingCertificateManager;
import com.hp.ov.sdk.constants.samples.SamplesConstants;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.msmb.services.MsmbConnectionManager;
import com.hp.ov.sdk.messaging.scmb.services.ScmbAlertsHandler;
import com.hp.ov.sdk.messaging.scmb.services.ScmbConnectionManager;
import com.hp.ov.sdk.messaging.scmb.services.ScmbMessageExecutionQueue;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.SdkUtils;
import com.hp.ov.sdk.util.samples.SampleRestParams;

public class ScmbClient {

    private final ScmbConnectionManager objectUnderTest;

    private RestParams params;

    private ScmbClient() {
        this.objectUnderTest = new ScmbConnectionManager(MessagingCertificateManager.getInstance());
    }

    public void scmbProcessor() {
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = SampleRestParams.getInstance().getBasicRestParams();

            // update the parameters with version and sessionId
            params = SdkUtils.getInstance().createRestParams(params);

            // create MessageExecutionQueue object
            final ScmbMessageExecutionQueue messageQueue = new ScmbMessageExecutionQueue(
                    new ScmbHandler().getScmbAlertsHandler());

            // start the dequeue process
            messageQueue.start();

            // then start scmb
            objectUnderTest.startScmb(params);
            objectUnderTest.processConsumer(params, SamplesConstants.SCMB_ALERTS_ROUTING_KEY, messageQueue);
            // TODO - start next processor with different routing key
            // objectUnderTest.processConsumer(params, "scmb.interconnects.#");
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : resource not found : " + params.getHostname());
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : no such url : " + params.getHostname());
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

    }

    private void stopScmb() {
        try {
            // Get the basic REST parameters like hostname, username and
            // password
            params = SampleRestParams.getInstance().getBasicRestParams();

            // update the parameters with version and sessionId
            params = SdkUtils.getInstance().createRestParams(params);

            // then stop scmb
            objectUnderTest.stopScmb(params);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : resource not found : " + params.getHostname());
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testGetAllNetwork : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : Applicance Not reachabe at : "
                    + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : arguments are null ");
        } catch (final SDKScmbConnectionNotFoundException e) {
            System.out.println("ScmbConnectionManagerImplTest : testStopScmb : connection not found ");
        }
    }

    public static void main(final String[] args) {
        ScmbClient scmbClient = new ScmbClient();

        scmbClient.scmbProcessor();

        try {
            Thread.sleep(300000); // Sample value to before stopping scmb ( 300
                                  // secs = 5 mins )
        } catch (final InterruptedException e) {
            System.out.println("ScmbConnectionManagerImplTest : mainTest : thread interrupted ");
        }
        scmbClient.stopScmb();
    }
}
