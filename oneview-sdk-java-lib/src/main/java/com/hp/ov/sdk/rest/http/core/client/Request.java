/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.rest.http.core.client;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

public class Request {

    private final HttpMethod type;

    private String uri;
    private Map<String, String> query;
    private Optional<Object> entity;
    private ContentType contentType;
    private boolean forceTaskReturn;
    private int taskTimeoutMillis;

    public Request(HttpMethod type, String uri) {
        this(type, uri, null);
    }

    public Request(HttpMethod type, String uri, Object entity) {
        this.type = type;
        this.uri = uri;

        this.entity = Optional.fromNullable(entity);
        this.contentType = ContentType.APPLICATION_JSON;
        this.forceTaskReturn = false;
        this.taskTimeoutMillis = SdkConstants.NO_TASK_TIMEOUT;
    }

    public HttpMethod getType() {
        return type;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public Request addQuery(UrlParameter query) {
        this.queryMap().put(query.getKey(), query.getValue());
        return this;
    }

    public Map<String, String> getQuery() {
        return ImmutableMap.copyOf(this.queryMap());
    }

    private Map<String, String> queryMap() {
        if (query == null) {
            query = new LinkedHashMap();
        }
        return query;
    }

    public boolean hasEntity() {
        return entity.isPresent();
    }

    public Object getEntity() {
        return entity.orNull();
    }

    public void setEntity(Object entity) {
        this.entity = Optional.fromNullable(entity);
    }

    /**
     * @return the contentType
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public boolean isForceTaskReturn() {
        return forceTaskReturn;
    }

    public void setForceTaskReturn(boolean forceTaskReturn) {
        this.forceTaskReturn = forceTaskReturn;
    }

    public int getTimeout() {
        return taskTimeoutMillis;
    }

    public void setTimeout(int taskTimeoutMillis) {
        this.taskTimeoutMillis = taskTimeoutMillis;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
