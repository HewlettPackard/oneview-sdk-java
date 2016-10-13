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
package com.hpe.i3s.rest.client.deployment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hpe.i3s.client.deployment.DeploymentPlanClient;
import com.hpe.i3s.dto.deployment.deploymentplan.DeploymentPlan;
import com.hpe.i3s.rest.client.ImageStreamerClient;

public class DeploymentPlanClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeploymentPlanClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String DEPLOYMENT_PLAN_NAME = "Sample Deployment Plan";
    private static final String DEPLOYMENT_PLAN_NAME_UPDATED =  DEPLOYMENT_PLAN_NAME + " Updated";
    // ================================

    private final DeploymentPlanClient deploymentPlanClient;

    public DeploymentPlanClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();
        ImageStreamerClient i3sClient = new ImageStreamerClient(oneViewClient);

        this.deploymentPlanClient = i3sClient.deploymentPlan();
    }

    private void getDeploymentPlanById() {
        DeploymentPlan deploymentPlan = this.deploymentPlanClient.getByName(DEPLOYMENT_PLAN_NAME).get(0);

        deploymentPlan = this.deploymentPlanClient.getById(deploymentPlan.getResourceId());

        LOGGER.info("Deployment Plan returned to client: {}", deploymentPlan.toJsonString());
    }

    private void getAllDeploymentPlans() {
        ResourceCollection<DeploymentPlan> deploymentPlans = this.deploymentPlanClient.getAll();

        LOGGER.info("Deployment Plans returned to client: {}", deploymentPlans.toJsonString());
    }

    private void createDeploymentPlan() {
        DeploymentPlan deploymentPlan = new DeploymentPlan();

        deploymentPlan.setName(DEPLOYMENT_PLAN_NAME);

        DeploymentPlan addedDeploymentPlan = this.deploymentPlanClient.create(deploymentPlan);

        LOGGER.info("Deployment Plan object returned to client: {}", addedDeploymentPlan.toJsonString());
    }

    private void updateDeploymentPlan() {
        DeploymentPlan deploymentPlan = this.deploymentPlanClient.getByName(DEPLOYMENT_PLAN_NAME).get(0);

        deploymentPlan.setName(DEPLOYMENT_PLAN_NAME_UPDATED);

        DeploymentPlan updatedDeploymentPlan = this.deploymentPlanClient.update(
                deploymentPlan.getResourceId(), deploymentPlan);

        LOGGER.info("Deployment Plan object returned to client: {}", updatedDeploymentPlan.toJsonString());
    }

    private void deleteDeploymentPlan() {
        DeploymentPlan deploymentPlan = this.deploymentPlanClient.getByName(DEPLOYMENT_PLAN_NAME_UPDATED).get(0);

        String response = this.deploymentPlanClient.delete(deploymentPlan.getResourceId());

        LOGGER.info("Response returned to client: {}", response);
    }

    public static void main(String[] args) {
        DeploymentPlanClientSample sample = new DeploymentPlanClientSample();

        sample.createDeploymentPlan();
        sample.getDeploymentPlanById();
        sample.getAllDeploymentPlans();
        sample.updateDeploymentPlan();
        sample.deleteDeploymentPlan();
    }

}
