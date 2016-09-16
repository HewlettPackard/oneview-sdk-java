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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;

public class BaseClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);

    private final RestParams params;
    private final ResourceAdaptor adaptor;
    private final HttpRestClient client;
    private final TaskMonitorManager monitor;

    public BaseClient(RestParams params, ResourceAdaptor adaptor, HttpRestClient client, TaskMonitorManager monitor) {
        this.params = params;
        this.adaptor = adaptor;
        this.client = client;
        this.monitor = monitor;
    }

    public ApiVersion getApiVersion() {
        return this.params.getApiVersion();
    }

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

    public TaskResourceV2 createResource(String uri, Object body, boolean aSync) {
        this.validateNotNullArguments(uri, body);

        Request request = new Request(HttpMethod.POST, uri, body);

        return this.executeMonitorableRequest(request, aSync);
    }

    public TaskResourceV2 updateResource(String uri, Object body, boolean aSync) {
        this.validateNotNullArguments(uri, body);

        Request request = new Request(HttpMethod.PUT, uri, body);

        return this.executeMonitorableRequest(request, aSync);
    }

    public TaskResourceV2 patchResource(String uri, Patch patch, boolean aSync) {
        this.validateNotNullArguments(uri, patch);

        Request request = new Request(HttpMethod.PATCH, uri, patch);

        return this.executeMonitorableRequest(request, aSync);
    }

    public TaskResourceV2 deleteResource(String uri, boolean aSync, UrlParameter... queries) {
        this.validateNotNullArguments(uri);

        Request request = new Request(HttpMethod.DELETE, uri);

        for (UrlParameter query : queries) {
            request.addQuery(query);
        }
        return this.executeMonitorableRequest(request, aSync);
    }

    public <T> T executeRequest(Request request, Class<T> returnType) {
        this.validateNotNullArguments(request, returnType);

        String response = this.executeRequest(request);

        if (String.class.equals(returnType)) {
            return (T) response;
        }
        return adaptor.buildResource(response, returnType);
    }

    public TaskResourceV2 executeMonitorableRequest(Request request, boolean aSync) {
        String response = this.executeRequest(request);

        TaskResourceV2 taskResource = adaptor.buildResource(response, TaskResourceV2.class);

        if (!aSync) {
            taskResource = monitor.checkStatus(params, taskResource.getUri(), request.getTimeout());
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


