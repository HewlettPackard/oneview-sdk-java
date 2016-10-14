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
package com.hpe.i3s.client.deployment;

import static com.hpe.i3s.client.deployment.DeploymentPlanClient.DEPLOYMENT_PLAN_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hpe.i3s.dto.deployment.deploymentplan.DeploymentPlan;

@RunWith(MockitoJUnitRunner.class)
public class DeploymentPlanClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private DeploymentPlanClient client = Reflection.newProxy(DeploymentPlanClient.class,
            new ClientRequestHandler<>(baseClient, DeploymentPlanClient.class));

    @Test
    public void shouldGetDeploymentPlanById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = DEPLOYMENT_PLAN_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DeploymentPlan.class).getType());
    }

    @Test
    public void shouldGetAllDeploymentPlans() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, DEPLOYMENT_PLAN_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<DeploymentPlan>>() {}.getType());
    }

    @Test
    public void shouldGetDeploymentPlanCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, DEPLOYMENT_PLAN_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<DeploymentPlan>>() {}.getType());
    }

    @Test
    public void shouldCreateDeploymentPlan() {
        DeploymentPlan deploymentPlan = new DeploymentPlan();

        client.create(deploymentPlan);

        Request expectedRequest = new Request(HttpMethod.POST, DEPLOYMENT_PLAN_URI);
        expectedRequest.setEntity(deploymentPlan);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DeploymentPlan.class).getType());
    }

    @Test
    public void shouldUpdateDeploymentPlan() {
        DeploymentPlan deploymentPlan = new DeploymentPlan();

        client.update(ANY_RESOURCE_ID, deploymentPlan);

        String expectedUri = DEPLOYMENT_PLAN_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(deploymentPlan);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DeploymentPlan.class).getType());
    }

    @Test
    public void shouldDeleteDeploymentPlan() {
        client.delete(ANY_RESOURCE_ID);


        String expectedUri = DEPLOYMENT_PLAN_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

}
