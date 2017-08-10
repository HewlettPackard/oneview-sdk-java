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

package com.hp.ov.sdk.rest.reflect;

import static com.hp.ov.sdk.rest.client.uncategorized.OsDeploymentPlanClient.OS_DEPLOYMENT_PLAN_URI;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.storage.FcSansManagedSanTask;
import com.hp.ov.sdk.dto.uncategorized.OsDeploymentPlan;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.RequestInterceptor;
import com.hp.ov.sdk.rest.http.core.URIQuery;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.BasicHeader;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;

public class ClientRequestHandler<T> extends AbstractInvocationHandler {

    private static final String GET_ALL_METHOD = "getAll";
    private static final String GET_BY_NAME_METHOD = "getByName";

    private final BaseClient baseClient;
    private final String baseUri;
    private final TypeToken<T> token;

    public ClientRequestHandler(BaseClient baseClient, Class<T> clientClass) {
        this.baseClient = baseClient;
        this.baseUri = clientClass.getAnnotation(Api.class).value();
        this.token = TypeToken.of(clientClass);
    }

    @Override
    protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = this.buildRequest(method, args);

        if (GET_ALL_METHOD.equals(method.getName())) {
            return this.handleGetAll(request, this.token.method(method).getReturnType().getType());
        } else if (OS_DEPLOYMENT_PLAN_URI.equals(request.getUri()) && GET_BY_NAME_METHOD.equals(method.getName())) {
            return this.handleGetOsDeploymentPlanByName(request, this.token.method(method).getReturnType().getType(), args);
        } else {
            if (TaskResource.class.equals(method.getReturnType())) {
                return this.baseClient.executeMonitorableRequest(request);
            } else if (FcSansManagedSanTask.class.equals(method.getReturnType())) {
                return new FcSansManagedSanTask(this.baseClient.executeMonitorableRequest(request), new ResourceAdaptor());
            }
            return this.baseClient.executeRequest(request, this.token.method(method).getReturnType().getType());
        }
    }

    private Request buildRequest(Method method, Object[] args) throws ReflectiveOperationException {
        Endpoint endpoint = method.getAnnotation(Endpoint.class);

        Request request = new Request(endpoint.method(), this.baseUri + endpoint.uri());
        request.setForceReturnTask(endpoint.forceReturnTask());

        this.fillRequestAccordingParams(request, this.token.method(method).getParameters(), args);
        this.fillRequestAccordingOptions(request, args);

        this.executeRequestInterceptors(request, this.token.method(method).getParameters(),
                args, endpoint.requestInterceptor());

        return request;
    }

    private void executeRequestInterceptors(Request request, List<Parameter> params,
            Object[] args, Class<? extends RequestInterceptor>[] classes) throws ReflectiveOperationException {
        for (Class<? extends RequestInterceptor> clazz : classes) {
            RequestInterceptor interceptor = clazz.newInstance();

            interceptor.intercept(request, params, args);
        }
    }

    private void fillRequestAccordingParams(Request request, List<Parameter> params, Object[] args) {
        for (int i = 0; i < params.size(); i++) {
            PathParam pathParam = params.get(i).getAnnotation(PathParam.class);
            QueryParam queryParam = params.get(i).getAnnotation(QueryParam.class);
            BodyParam bodyParam = params.get(i).getAnnotation(BodyParam.class);
            HeaderParam headerParam = params.get(i).getAnnotation(HeaderParam.class);

            if (pathParam != null) {
                request.setUri(request.getUri().replaceFirst("\\{" + pathParam.value() + "\\}", args[i].toString()));
            } else if (queryParam != null) {
                if (URIQuery.class.isAssignableFrom(args[i].getClass())) {
                    URIQuery query = (URIQuery) args[i];

                    for (UrlParameter parameter : query.value()) {
                        request.addQuery(parameter);
                    }
                } else {
                    String value = String.valueOf(args[i]);

                    request.addQuery(new UrlParameter(queryParam.key(), value));
                }
            } else if (headerParam != null) {
                String value = String.valueOf(args[i]);

                request.addHeader(new BasicHeader(headerParam.value(), value));
            } else if (bodyParam != null) {
                request.setEntity(args[i]);
                request.setContentType(bodyParam.type());
            }
        }
    }

    private void fillRequestAccordingOptions(Request request, Object[] args) {
        boolean isLastArgumentAnArray = (args != null)
                && (args.length > 0)
                && (args[args.length - 1].getClass().isArray());

        if (isLastArgumentAnArray) {
            for (RequestOption option : (RequestOption[]) args[args.length - 1]) {
                option.apply(request);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private ResourceCollection<Object> handleGetAll(Request request, Type returnType) {
        ResourceCollection<Object> resources = new ResourceCollection<>();
        boolean hasMoreItems = false;
        boolean hasNextPage = false;
        
        do {
            ResourceCollection<Object> response = (ResourceCollection<Object>)
                    this.baseClient.executeRequest(request, returnType);

            resources.addMembers(response.getMembers());
            resources.setTotal(response.getTotal());

            request = new Request(HttpMethod.GET, response.getNextPageUri());
            
            hasMoreItems = resources.getCount() < resources.getTotal(); 
            hasNextPage = resources.getNextPageUri() != null;
        } while (hasMoreItems && hasNextPage);

        return resources;
    }

    private ResourceCollection<OsDeploymentPlan> handleGetOsDeploymentPlanByName(Request request, Type returnType, Object[] args) {
        ResourceCollection<OsDeploymentPlan> resources = new ResourceCollection<>();
        boolean hasMoreItems = false;
        boolean hasNextPage = false;

        do {
            @SuppressWarnings("unchecked")
            ResourceCollection<OsDeploymentPlan> response = (ResourceCollection<OsDeploymentPlan>)
                    this.baseClient.executeRequest(request, returnType);

            resources.addMembers(response.getMembers());
            resources.setTotal(response.getTotal());
            resources.setNextPageUri(response.getNextPageUri());

            request = new Request(HttpMethod.GET, response.getNextPageUri());
            hasMoreItems = resources.getCount() < resources.getTotal();
            hasNextPage = resources.getNextPageUri() != null;
        } while (hasMoreItems && hasNextPage);

        ResourceCollection<OsDeploymentPlan> osDeploymentPlans = new ResourceCollection<>();

        for (OsDeploymentPlan osDeploymentPlan : resources.getMembers()) {
            for (int i = 0; i < args.length; i++) {
                if (osDeploymentPlan.getName().equals(args[i].toString())) {
                    osDeploymentPlans.addMembers(Lists.newArrayList(osDeploymentPlan));
                }
            }
        }

        return osDeploymentPlans;
    }
}
