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
import com.hp.ov.sdk.util.JsonPrettyPrinter;
import com.hpe.i3s.client.deployment.PlanScriptClient;
import com.hpe.i3s.dto.deployment.planscript.PlanScript;
import com.hpe.i3s.dto.deployment.planscript.ScriptDifferences;
import com.hpe.i3s.rest.client.ImageStreamerClient;

public class PlanScriptClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanScriptClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String PLAN_SCRIPT_RESOURCE_ID = "d1778269-9efe-4a5b-be70-c9f556a47685";
    private static final String PLAN_SCRIPT_NAME = "Sample Plan Script";
    // ================================

    private final PlanScriptClient planScriptClient;

    public PlanScriptClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();
        ImageStreamerClient i3sClient = new ImageStreamerClient(oneViewClient);

        this.planScriptClient = i3sClient.planScript();
    }

    private void getPlanScriptById() {
        PlanScript planScript = this.planScriptClient.getById(PLAN_SCRIPT_RESOURCE_ID);

        LOGGER.info("Plan Script object returned to client : " + planScript.toJsonString());
    }

    private void getAllPlanScripts() {
        ResourceCollection<PlanScript> planScripts = this.planScriptClient.getAll();

        LOGGER.info("Plan Scripts returned to client : " + planScripts.toJsonString());
    }

    private void getPlanScriptByName() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);

        LOGGER.info("Plan Script object returned to client : " + planScript.toJsonString());
    }

    private void createPlanScript() {
        PlanScript planScript = new PlanScript();

        planScript.setName(PLAN_SCRIPT_NAME);

        PlanScript addedPlanScript = this.planScriptClient.create(planScript);

        LOGGER.info("Plan Script object returned to client : " + addedPlanScript.toJsonString());
    }

    private void updatePlanScript() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);
        String resourceId = planScript.getResourceId();

        planScript.setHpProvided(!planScript.isHpProvided());

        PlanScript updatedPlanScript = this.planScriptClient.update(resourceId, planScript);

        LOGGER.info("Plan Script object returned to client : " + updatedPlanScript.toJsonString());
    }

    private void deletePlanScript() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);
        String response = this.planScriptClient.delete(planScript.getResourceId());

        LOGGER.info("Response returned to client : " + response);
    }

    private void getPlanScriptDifferences() {
        ScriptDifferences scriptDifferences = this.planScriptClient.getDifferences(PLAN_SCRIPT_RESOURCE_ID, PLAN_SCRIPT_NAME);

        LOGGER.info("ScriptDifferences object returned to client : " + JsonPrettyPrinter.print(scriptDifferences));
    }

    public static void main(String[] args) {
        PlanScriptClientSample sample = new PlanScriptClientSample();

        sample.createPlanScript();
        sample.getPlanScriptByName();
        sample.getPlanScriptById();
        sample.getAllPlanScripts();
        sample.getPlanScriptDifferences();
        sample.updatePlanScript();
        sample.deletePlanScript();
    }

}
