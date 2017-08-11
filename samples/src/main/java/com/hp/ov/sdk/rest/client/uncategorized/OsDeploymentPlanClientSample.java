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
import com.hp.ov.sdk.dto.uncategorized.OsDeploymentPlan;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class OsDeploymentPlanClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsDeploymentPlanClientSample.class);

    private final OsDeploymentPlanClient client;

    // These are variables to be defined by the user
    // =============================================
    private static final String OS_DEPLOYMENT_PLAN_RESOURCE_ID = "aaaba8d8-f181-433b-95fa-d5359e8b5082";
    private static final String OS_DEPLOYMENT_PLAN_NAME = "OS Deployment Plan Name";
    // =============================================

    public OsDeploymentPlanClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        client = oneViewClient.osDeploymentPlan();
    }

    private void getAllOsDeploymentPlans() {
        ResourceCollection<OsDeploymentPlan> osDeploymentPlans = client.getAll();

        LOGGER.info("OsDeploymentPlan objects returned to client: " + osDeploymentPlans.toJsonString());
    }

    private void getOsDeploymentPlanById() {
        OsDeploymentPlan osDeploymentPlan = client.getById(OS_DEPLOYMENT_PLAN_RESOURCE_ID);

        LOGGER.info("OsDeploymentPlan object returned to client: " + osDeploymentPlan.toJsonString());
    }

    private void getNumberOfOsDeploymentPlans() {
        ResourceCollection<OsDeploymentPlan> osDeploymentPlans = client.getAll();

        LOGGER.info("Number of OsDeploymentPlan objects returned to client (count): " + osDeploymentPlans.getCount());
    }

    private void getOsDeploymentPlanByName() {
        OsDeploymentPlan osDeploymentPlan = client.getByName(OS_DEPLOYMENT_PLAN_NAME).get(0);

        LOGGER.info("OsDeploymentPlan object returned to client: " + osDeploymentPlan.toJsonString());
    }

    public static void main(String[] args) {
        /* This resource is available only on HPE Synergy */
        OsDeploymentPlanClientSample osDeploymentPlanSample = new OsDeploymentPlanClientSample();

        osDeploymentPlanSample.getAllOsDeploymentPlans();
        osDeploymentPlanSample.getOsDeploymentPlanById();
        osDeploymentPlanSample.getOsDeploymentPlanByName();
        osDeploymentPlanSample.getNumberOfOsDeploymentPlans();
    }

}
