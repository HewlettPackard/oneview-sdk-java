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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestParams {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestParams.class);

    private ApiVersion apiVersion = ApiVersion.V_300;
    private String domain = "LOCAL";
    private String hostname = null;
    private String locale = null;
    private String password = null;
    private String userName = null;

    private String sessionId = null;

    /* parameters for RabbitMQ connection */
    private int amqpPort = 5671; // default port
    private String routingKey = "scmb.alerts.#"; // default value
    private String exchange = "scmb";// default value

    public RestParams() {
    }

    public RestParams(SDKConfiguration sdkConfiguration) throws NumberFormatException {
        this.apiVersion = sdkConfiguration.getOneViewApiVersion();
        this.domain = sdkConfiguration.getOneViewDomain();
        this.hostname = sdkConfiguration.getOneViewHostname();
        this.userName = sdkConfiguration.getOneViewUserName();
        this.password = sdkConfiguration.getOneViewPassword();

        this.routingKey = sdkConfiguration.getMessageBusAlertsRoutingKey();
        this.exchange = sdkConfiguration.getMessageBusExchangeName();
        this.amqpPort = Integer.parseInt(sdkConfiguration.getRabbitMQPort());
    }

    /**
     * @return the apiVersion
     */
    public ApiVersion getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion the apiVersion to set
     */
    public void setApiVersion(ApiVersion apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * Controls which API version will be used for the connection.
     *
     * @param apiVersion the API version.
     *
     * @deprecated
     * Use {@link #setApiVersion(ApiVersion)} instead
     */
    @Deprecated
    public void setApiVersion(final String apiVersion) {
        try {
            this.apiVersion = ApiVersion.valueOf(ApiVersion.class, apiVersion);
        } catch (IllegalArgumentException | NullPointerException e) {
            LOGGER.error("Error settings API version. Using version " + this.apiVersion, e);
        }
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the amqpPort
     */
    public int getAmqpPort() {
        return amqpPort;
    }

    /**
     * @param amqpPort the amqpPort to set
     */
    public void setAmqpPort(int amqpPort) {
        this.amqpPort = amqpPort;
    }

    /**
     * @return the routingKey
     */
    public String getRoutingKey() {
        return routingKey;
    }

    /**
     * @param routingKey the routingKey to set
     */
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    /**
     * @return the exchange
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * @param exchange the exchange to set
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

