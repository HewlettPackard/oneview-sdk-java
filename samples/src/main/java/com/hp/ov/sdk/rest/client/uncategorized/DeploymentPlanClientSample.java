/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client.uncategorized;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.uncategorized.DeploymentPlan;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class DeploymentPlanClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeploymentPlanClientSample.class);

    // These are variables to be defined by the user
    // =============================================
    private static final String DEPLOYMENT_PLAN_RESOURCE_ID = "d248219e-2c2b-46bb-9f1c-d644ce5611eb";
    private static final String DEPLOYMENT_PLAN_NAME = "Basic Deployment Plan";
    // =============================================

    private final DeploymentPlanClient client;

    public DeploymentPlanClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        client = oneViewClient.deploymentPlan();
    }

    private void getDeploymentPlanById() {
        DeploymentPlan deploymentPlan = client.getById(DEPLOYMENT_PLAN_RESOURCE_ID);

        LOGGER.info("Deployment Plan returned to client: {}", deploymentPlan.toJsonString());
    }

    private void getAllDeploymentPlans() {
        ResourceCollection<DeploymentPlan> deploymentPlans = client.getAll();

        LOGGER.info("Deployment Plans returned to client (count): {}", deploymentPlans.toJsonString());
    }

    private void getDeploymentPlanByName() {
        DeploymentPlan deploymentPlan = client.getByName(DEPLOYMENT_PLAN_NAME).get(0);

        LOGGER.info("Deployment Plan object returned to client : " + deploymentPlan.toJsonString());
    }

    public static void main(String[] args) {
        /* This resource is available only on HPE Synergy */
        DeploymentPlanClientSample deploymentPlanSample = new DeploymentPlanClientSample();

        deploymentPlanSample.getAllDeploymentPlans();
        //deploymentPlanSample.getDeploymentPlanById();
        //deploymentPlanSample.getDeploymentPlanByName();
    }

}
