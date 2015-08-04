/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.http.core.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.dto.HttpMethodType;

@Component
public class RestParams {

    private int apiVersion = 100;// default value
    private String hostname = null;
    private String userName = null;
    private String password = null;
    private List<KeyValuePair> headers = new ArrayList<KeyValuePair>();
    private String locale = null;
    private List<KeyValuePair> query = new ArrayList<KeyValuePair>();
    private String domain = "LOCAL";
    private String url = null;
    private HttpMethodType type = HttpMethodType.GET;
    private String keyStoreFile = null;
    private String trustStoreFile = null;
    private String sessionId = null;
    private int amqpPort = 5671; // default port
    private String routingKey = "scmb.alerts.#"; // default value
    private String exchange = "scmb";// default value

    // TODO - add remaining ssl properties

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

    public String getKeyStoreFile() {
        return keyStoreFile;
    }

    public void setKeyStoreFile(final String keyStoreFile) {
        this.keyStoreFile = keyStoreFile;
    }

    public String getTrustStoreFile() {
        return trustStoreFile;
    }

    public void setTrustStoreFile(final String trustStoreFile) {
        this.trustStoreFile = trustStoreFile;
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

    public List<KeyValuePair> getHeaders() {
        return headers;
    }

    public void setHeaders() {
        // this.headers = headers;
        this.headers = createHeaders();
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

    public List<KeyValuePair> getQuery() {
        return query;
    }

    public void setQuery(final List<KeyValuePair> query) {
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

    private List<KeyValuePair> createHeaders() {
        final List<KeyValuePair> header = new ArrayList<KeyValuePair>();
        header.add(new KeyValuePair("Accept", "application/json"));
        header.add(new KeyValuePair("Content-Type", "application/json; charset=UTF-8"));
        header.add(new KeyValuePair("accept-language", "en_US"));

        return header;
    }
}
