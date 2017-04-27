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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hpe.i3s.client.deployment.OsBuildPlanClient;
import com.hpe.i3s.dto.deployment.osbuildplan.OeBuildPlan;
import com.hpe.i3s.rest.client.ImageStreamerClient;

public class OsBuildPlanClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsBuildPlanClientSample.class);

    // These are variables to be defined by user
    // ================================
    public static final String OS_BUILD_PLAN_RESOURCE_ID = "23f584b7-2c8d-4daa-8458-a4f997f20958";
    private static final String OS_BUILD_PLAN_NAME = "Sample OS Build Plan";
    // ================================

    private final OsBuildPlanClient osBuildPlanClient;

    public OsBuildPlanClientSample() {
        ImageStreamerClient i3sClient = new ImageStreamerClientSample().getImageStreamerClient();

        this.osBuildPlanClient = i3sClient.osBuildPlan();
    }

    private void getOsBuildPlanById() {
        OeBuildPlan osBuildPlan = this.osBuildPlanClient.getByName(OS_BUILD_PLAN_NAME).get(0);

        osBuildPlan = this.osBuildPlanClient.getById(osBuildPlan.getResourceId());

        LOGGER.info("OS Build Plan object returned to client : " + osBuildPlan.toJsonString());
    }

    private void getAllOsBuildPlans() {
        ResourceCollection<OeBuildPlan> osBuildPlans = this.osBuildPlanClient.getAll();

        LOGGER.info("OsBuildPlans returned to client : " + osBuildPlans.toJsonString());
    }

    private void getOsBuildPlanByName() {
        OeBuildPlan osBuildPlan = this.osBuildPlanClient.getByName(OS_BUILD_PLAN_NAME).get(0);

        LOGGER.info("OS Build Plan object returned to client : " + osBuildPlan.toJsonString());
    }

    private void createOsBuildPlan() {
        OeBuildPlan osBuildPlan = new OeBuildPlan();

        osBuildPlan.setName(OS_BUILD_PLAN_NAME);
        osBuildPlan.setDescription("Sample build plan description");
        osBuildPlan.setType(OeBuildPlan.TYPE);
        osBuildPlan.setOeBuildPlanType("deploy");

        OeBuildPlan addedOsBuildPlan = this.osBuildPlanClient.create(osBuildPlan);

        LOGGER.info("OS Build Plan object returned to client : " + addedOsBuildPlan.toJsonString());
    }

    private void updateOsBuildPlan() {
        OeBuildPlan osBuildPlan = this.osBuildPlanClient.getByName(OS_BUILD_PLAN_NAME).get(0);
        String resourceId = osBuildPlan.getResourceId();

        osBuildPlan.setHpProvided(!osBuildPlan.isHpProvided());

        OeBuildPlan updatedOsBuildPlan = this.osBuildPlanClient.update(resourceId, osBuildPlan);

        LOGGER.info("OS Build Plan object returned to client : " + updatedOsBuildPlan.toJsonString());
    }

    private void deleteOsBuildPlan() {
        OeBuildPlan osBuildPlan = this.osBuildPlanClient.getByName(OS_BUILD_PLAN_NAME).get(0);
        String response = this.osBuildPlanClient.delete(osBuildPlan.getResourceId());

        LOGGER.info("Response returned to client : " + response);
    }

    public static void main(String[] args) {
        /* This resource is available only on HPE Synergy */
        OsBuildPlanClientSample sample = new OsBuildPlanClientSample();

        sample.createOsBuildPlan();
        sample.getOsBuildPlanByName();
        sample.getOsBuildPlanById();
        sample.getAllOsBuildPlans();
        sample.updateOsBuildPlan();
        sample.deleteOsBuildPlan();
    }

}
