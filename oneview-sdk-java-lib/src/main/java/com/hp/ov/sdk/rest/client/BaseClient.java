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

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Supplier;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.SSLContextFactory;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.hp.ov.sdk.tasks.TaskMonitor;
import com.hp.ov.sdk.util.JsonSerializer;

public class BaseClient {

    private final ResourceAdaptor adaptor;
    private final HttpRestClient client;
    private final Supplier<TaskMonitor> supplier;

    private String sessionId;

    public BaseClient(SDKConfiguration config) {
        this(new ResourceAdaptor(),
             new HttpRestClient(config, new JsonSerializer(), SSLContextFactory.getDefaultContext(config)),
             new Supplier<TaskMonitor>() {
                @Override
                public TaskMonitor get() {
                    return new TaskMonitor();
                }
             }
        );
    }

    protected BaseClient(
            ResourceAdaptor adaptor,
            HttpRestClient client,
            Supplier<TaskMonitor> supplier) {

        this.adaptor = adaptor;
        this.client = client;
        this.supplier = supplier;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Object executeRequest(Request request, Type returnType) {
        this.validateNotNullArguments(request, returnType);

        String response = this.executeRequest(request);

        if (String.class.equals(returnType)) {
            return response;
        }
        return adaptor.buildResource(response, returnType);
    }

    public <T> T executeRequest(Request request, Class<T> returnType) {
        this.validateNotNullArguments(request, returnType);

        String response = this.executeRequest(request);

        if (String.class.equals(returnType)) {
            return (T) response;
        }
        return adaptor.buildResource(response, returnType);
    }

    public TaskResource executeMonitorableRequest(Request request) {
        this.validateNotNullArguments(request);

        String response = this.executeRequest(request);

        TaskResource taskResource = adaptor.buildResource(response, TaskResource.class);

        taskResource = supplier.get().execute(this, taskResource, request.getTimeout());

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
        String response = client.sendRequest(sessionId, request);

        if (StringUtils.isBlank(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance,
                    null, null, null, request.getUri(), null);
        }
        return response;
    }

}


