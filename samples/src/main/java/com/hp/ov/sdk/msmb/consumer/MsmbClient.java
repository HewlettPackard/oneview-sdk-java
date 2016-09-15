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

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.certs.MessagingCertificateClient;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKMsmbConnectionNotFoundException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKScmbConnectionNotFoundException;
import com.hp.ov.sdk.messaging.msmb.services.MsmbConnectionManager;
import com.hp.ov.sdk.messaging.msmb.services.MsmbMessageExecutionQueue;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.security.LoginSessionClient;
import com.hp.ov.sdk.rest.client.settings.VersionClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.OneViewConnector;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class MsmbClient {

    private final MsmbConnectionManager objectUnderTest;

    private OneViewClient oneViewClient;
    private HPOneViewCredential credentials;

    private MsmbClient() {
        this.oneViewClient = OneViewClientSample.getOneViewClient();
        this.credentials = new HPOneViewCredential();

        MessagingCertificateClient messagingCertificateClient = oneViewClient.messagingCertificate();

        this.objectUnderTest = new MsmbConnectionManager(messagingCertificateClient);
    }

    public void msmbProcessor() {
        RestParams params = credentials.createRestParams();

        try {
            HttpSslProperties httpSslProperties = credentials.createHttpSslProperties();
            BaseClient baseClient = new BaseClient(params, new ResourceAdaptor(),
                    HttpRestClient.getClient(), TaskMonitorManager.getInstance());

            OneViewConnector connector = new OneViewConnector(params, httpSslProperties,
                    new VersionClient(baseClient), new LoginSessionClient(baseClient));

            connector.connect();

            // create MessageExecutionQueue object

            final MsmbMessageExecutionQueue messageQueue = new MsmbMessageExecutionQueue(
                    new MsmbHandler().getMsmbAlertsHandler());

            // start the dequeue process
            messageQueue.start();

            // then start scmb
            objectUnderTest.startMsmb(params);
            objectUnderTest.processConsumer(params, credentials.getSdkConfiguration().getMessageBusAlertsRoutingKey(), messageQueue);
            // Optional: start next processor with different routing key
            // objectUnderTest.processConsumer(params, "scmb.interconnects.#", messageQueue);
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
        RestParams params = credentials.createRestParams();

        try {
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
