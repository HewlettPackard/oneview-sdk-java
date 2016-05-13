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

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.HttpMethodType;

public class RestParams {

    private int apiVersion = 100;// default value
    private String hostname = null;
    private String userName = null;
    private String password = null;
    private Map<String, String> headers = new HashMap<String, String>();
    private String locale = null;
    private Map<String, String> query = new HashMap<String, String>();
    private String domain = "LOCAL";
    private String url = null;
    private HttpMethodType type = HttpMethodType.GET;
    private String sessionId = null;
    private int amqpPort = 5671; // default port
    private String routingKey = "scmb.alerts.#"; // default value
    private String exchange = "scmb";// default value

    private TrustManager trustManager = null;
    private HostnameVerifier hostVerifier = null;

    public RestParams() {
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("accept-language", "en_US");
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(final String routingKey) {
        this.routingKey = routingKey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(final String exchange) {
        this.exchange = exchange;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getAmqpPort() {
        return amqpPort;
    }

    public void setAmqpPort(final int amqpPort) {
        this.amqpPort = amqpPort;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    public HttpMethodType getType() {
        return type;
    }

    public void setType(final HttpMethodType type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(final String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(final int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(final Map<String, String> query) {
        this.query = query;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * Gets the current TrustManager.
     * If using the system default, null is returned.
     * @return the current TrustManager or null if default.
     **/
    public TrustManager getTrustManager() {
       return this.trustManager;
    }

    /**
     * Sets a new TrustManager for future connections.
     *
     * @param trustMgr {@link TrustManager} trust manager.
     *
     * @return the old TrustManager
     **/
    public TrustManager setTrustManager(final TrustManager trustMgr) {
       TrustManager old = getTrustManager();
       this.trustManager = trustMgr;
       return old;
    }

    /**
     * Is a non-default TrustManager to be used.
     * @return true if non-default, false otherwise
     **/
    public boolean hasTrustManager() {
       return getTrustManager() != null;
    }

    /**
     * Gets the current HostnameVerifier.
     * If using the system default, null is returned.
     * @return the current HostnameVerifier or null if default.
     **/
    public HostnameVerifier getHostnameVerifier() {
       return this.hostVerifier;
    }

    /**
     * Sets a new HostnameVerifier for future connections.
     *
     * @param verifier {@link HostnameVerifier} hostname verifier.
     *
     * @return the old HostnameVerifier
     **/
    public HostnameVerifier
    setHostnameVerifier(final HostnameVerifier verifier) {
       HostnameVerifier old = getHostnameVerifier();
       this.hostVerifier = verifier;
       return old;
    }

    /**
     * Is a non-default HostnameVerifier to be used.
     * @return true if non-default, false otherwise
     **/
    public boolean hasHostnameVerifier() {
       return getHostnameVerifier() != null;
    }

}

