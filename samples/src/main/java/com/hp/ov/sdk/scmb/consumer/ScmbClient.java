/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
 */
package com.hp.ov.sdk.scmb.consumer;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.certs.MessagingCertificateClient;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.scmb.services.ScmbAlertsHandler;
import com.hp.ov.sdk.messaging.scmb.services.ScmbConnectionManager;
import com.hp.ov.sdk.messaging.scmb.services.ScmbMessageExecutionQueue;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.security.LoginSessionClient;
import com.hp.ov.sdk.rest.client.settings.VersionClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.OneViewConnector;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class ScmbClient {

    private static final String SCMB_TASKS_ROUTING_KEY = "scmb.tasks.#";
    private final ScmbConnectionManager objectUnderTest;

    private OneViewClient oneViewClient;
    private HPOneViewCredential credentials;

    private ScmbClient() {
        this.oneViewClient = OneViewClientSample.getOneViewClient();
        this.credentials = new HPOneViewCredential();

        MessagingCertificateClient messagingCertificateClient = oneViewClient.messagingCertificate();

        this.objectUnderTest = new ScmbConnectionManager(messagingCertificateClient);
    }

    public void scmbProcessor() {
        RestParams params = credentials.createRestParams();

        try {
            BaseClient baseClient = new BaseClient(credentials.getSDKConfiguration());

            OneViewConnector connector = new OneViewConnector(credentials.getSDKConfiguration(),
                    new VersionClient(baseClient), new LoginSessionClient(baseClient));

            baseClient.setSessionID(connector.connect());

            // create MessageExecutionQueue object
            final ScmbMessageExecutionQueue messageQueue = new ScmbMessageExecutionQueue(
                    new ScmbAlertsHandler(new TaskMessageHandler()));

            // start the dequeue process
            messageQueue.start();

            // then start scmb
            objectUnderTest.startScmb(credentials.getSDKConfiguration());
            objectUnderTest.processConsumer(params, SCMB_TASKS_ROUTING_KEY, messageQueue);

            // Optional:  Start next processor with different routing key
            // objectUnderTest.processConsumer(params, "scmb.interconnects.#");
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ScmbClient : scmbProcessor : resource not found : " + params.getHostname());
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ScmbClient : scmbProcessor : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ScmbClient : scmbProcessor : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("ScmbClient : scmbProcessor : No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ScmbClient : scmbProcessor : arguments are null ");
        } catch (final SDKScmbConnectionNotFoundException ex) {
            System.out.println("ScmbClient : scmbProcessor : scmb connection not found ");
        }
    }

    private void stopScmb() {
        RestParams params = credentials.createRestParams();

        try {
            objectUnderTest.stopScmb(params);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ScmbClient : stopScmb : resource not found : " + params.getHostname());
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ScmbClient : stopScmb : no such url : " + params.getHostname());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ScmbClient : stopScmb : Applicance Not reachabe at : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ScmbClient : stopScmb : arguments are null ");
        } catch (final SDKScmbConnectionNotFoundException e) {
            System.out.println("ScmbClient : stopScmb : connection not found ");
        }
    }

    public static void main(final String[] args) {
        ScmbClient scmbClient = new ScmbClient();

        scmbClient.scmbProcessor();

        try {
            Thread.sleep(300000); // Sample value to before stopping scmb ( 300 secs = 5 mins )
        } catch (final InterruptedException e) {
            System.out.println("ScmbClient : main : thread interrupted ");
        }
        scmbClient.stopScmb();
    }
}
