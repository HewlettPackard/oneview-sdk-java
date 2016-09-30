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

package com.hp.ov.sdk.rest.client;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Supplier;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitor;

public class BaseClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);

    private final RestParams params;
    private final ResourceAdaptor adaptor;
    private final HttpRestClient client;
    private final Supplier<TaskMonitor> supplier;

    public BaseClient(RestParams params) {
        this(params, new ResourceAdaptor(), HttpRestClient.getClient(), new Supplier<TaskMonitor>() {
            @Override
            public TaskMonitor get() {
                return new TaskMonitor();
            }
        });
    }

    protected BaseClient(RestParams params,
            ResourceAdaptor adaptor,
            HttpRestClient client,
            Supplier<TaskMonitor> supplier) {

        this.params = params;
        this.adaptor = adaptor;
        this.client = client;
        this.supplier = supplier;
    }

    public ApiVersion getApiVersion() {
        return this.params.getApiVersion();
    }

    @Deprecated
    public <T> T getResource(String uri, Class<T> returnType, UrlParameter... queries) {
        this.validateNotNullArguments(uri, returnType);

        Request request = new Request(HttpMethod.GET, uri);

        for (UrlParameter query : queries) {
            request.addQuery(query);
        }

        String response = this.executeRequest(request);

        T resource = adaptor.buildResource(response, returnType);

        return resource;
    }

    @Deprecated
    public <T> List<T> getResourceList(String uri, Class<T> resourceType, UrlParameter... queries) {
        this.validateNotNullArguments(uri, resourceType);

        Request request = new Request(HttpMethod.GET, uri);

        for (UrlParameter query : queries) {
            request.addQuery(query);
        }

        String response = this.executeRequest(request);

        List<T> resource = adaptor.buildListOfResource(response, resourceType);

        return resource;
    }

    @Deprecated
    public <T> ResourceCollection<T> getResourceCollection(String uri, Class<T> resourceType,
            UrlParameter... queries) {
        this.validateNotNullArguments(uri, resourceType);

        Request request = new Request(HttpMethod.GET, uri);

        for (UrlParameter query : queries) {
            request.addQuery(query);
        }

        String response = this.executeRequest(request);
        ResourceCollection<T> resource = adaptor.buildResourceCollection(response, resourceType);

        return resource;
    }

    @Deprecated
    public TaskResource createResource(String uri, Object body, boolean aSync) {
        this.validateNotNullArguments(uri, body);

        Request request = new Request(HttpMethod.POST, uri, body);

        return this.executeMonitorableRequest(request, aSync);
    }

    @Deprecated
    public TaskResource updateResource(String uri, Object body, boolean aSync) {
        this.validateNotNullArguments(uri, body);

        Request request = new Request(HttpMethod.PUT, uri, body);

        return this.executeMonitorableRequest(request, aSync);
    }

    @Deprecated
    public TaskResource patchResource(String uri, Patch patch, boolean aSync) {
        this.validateNotNullArguments(uri, patch);

        Request request = new Request(HttpMethod.PATCH, uri, patch);

        return this.executeMonitorableRequest(request, aSync);
    }

    @Deprecated
    public TaskResource deleteResource(String uri, boolean aSync, UrlParameter... queries) {
        this.validateNotNullArguments(uri);

        Request request = new Request(HttpMethod.DELETE, uri);

        for (UrlParameter query : queries) {
            request.addQuery(query);
        }
        return this.executeMonitorableRequest(request, aSync);
    }

    public Object executeRequest(Request request, Type returnType) {
        this.validateNotNullArguments(request, returnType);

        String response = this.executeRequest(request);

        if (String.class.equals(returnType)) {
            return response;
        }
        return adaptor.buildResource(response, returnType);
    }

    @Deprecated
    public <T> T executeRequest(Request request, Class<T> returnType) {
        this.validateNotNullArguments(request, returnType);

        String response = this.executeRequest(request);

        if (String.class.equals(returnType)) {
            return (T) response;
        }
        return adaptor.buildResource(response, returnType);
    }

    public TaskResource executeMonitorableRequest(Request request) {
        String response = this.executeRequest(request);

        TaskResource taskResource = adaptor.buildResource(response, TaskResource.class);

        taskResource = supplier.get().execute(this, taskResource, request.getTimeout());

        return taskResource;
    }

    @Deprecated
    public TaskResource executeMonitorableRequest(Request request, boolean aSync) {
        String response = this.executeRequest(request);

        TaskResource taskResource = adaptor.buildResource(response, TaskResource.class);

        if (!aSync) {
            taskResource = supplier.get().execute(this, taskResource, request.getTimeout());
        }
        return taskResource;
    }

    private void validateNotNullArguments(Object ... arguments) {
        for (Object argument : arguments) {
            if (argument == null) {
                throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                        null, null, null, SdkConstants.APPLIANCE, null);
            }
        }
    }

    private String executeRequest(Request request) {
        String response = client.sendRequest(params, request);

        LOGGER.info("BaseClient : executeRequest : response from OV: " + response);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance,
                    null, null, null, request.getUri(), null);
        }
        return response;
    }

}


