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
package com.hp.ov.sdk.rest.http.core.client;

public class RestParams {

    private final ApiVersion apiVersion;
    private final String domain;
    private final String hostname;
    private final String password;
    private final String userName;

    /* parameters for RabbitMQ connection */
    private final int messageBusPort;

    private String sessionId = null;

    public RestParams(SDKConfiguration sdkConfiguration) {
        this.apiVersion = sdkConfiguration.getOneViewApiVersion();
        this.domain = sdkConfiguration.getOneViewDomain();
        this.hostname = sdkConfiguration.getOneViewHostname();
        this.userName = sdkConfiguration.getOneViewUserName();
        this.password = sdkConfiguration.getOneViewPassword();
        this.messageBusPort = sdkConfiguration.getMessageBusPort();
    }

    /**
     * @return the apiVersion
     */
    public ApiVersion getApiVersion() {
        return apiVersion;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the messageBusPort
     */
    public int getMessageBusPort() {
        return messageBusPort;
    }

}

