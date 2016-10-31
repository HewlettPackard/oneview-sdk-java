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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.util.JsonPrettyPrinter;
import com.hpe.i3s.client.deployment.PlanScriptClient;
import com.hpe.i3s.dto.deployment.planscript.PlanScript;
import com.hpe.i3s.dto.deployment.planscript.PlanType;
import com.hpe.i3s.dto.deployment.planscript.ScriptDifferences;
import com.hpe.i3s.rest.client.ImageStreamerClient;

public class PlanScriptClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanScriptClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String PLAN_SCRIPT_NAME = "Sample Plan Script";
    // ================================

    private final PlanScriptClient planScriptClient;

    public PlanScriptClientSample() {
        ImageStreamerClient i3sClient = new ImageStreamerClientSample().getImageStreamerClient();

        this.planScriptClient = i3sClient.planScript();
    }

    private void getPlanScriptById() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);

        planScript = this.planScriptClient.getById(planScript.getResourceId());

        LOGGER.info("Plan script object returned to client : " + planScript.toJsonString());
    }

    private void getAllPlanScripts() {
        ResourceCollection<PlanScript> planScripts = this.planScriptClient.getAll();

        LOGGER.info("Plan scripts returned to client : " + planScripts.toJsonString());
    }

    private void getPlanScriptByName() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);

        LOGGER.info("Plan script object returned to client : " + planScript.toJsonString());
    }

    private void createPlanScript() {
        PlanScript planScript = new PlanScript();

        planScript.setName(PLAN_SCRIPT_NAME);
        planScript.setPlanType(PlanType.general);
        planScript.setContent("Plan script content"); //required
        planScript.setType(PlanScript.TYPE); //required
        planScript.setDescription("This is a sample plan script"); //required

        PlanScript addedPlanScript = this.planScriptClient.create(planScript);

        LOGGER.info("Plan script object returned to client : " + addedPlanScript.toJsonString());
    }

    private void updatePlanScript() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);

        planScript.setHpProvided(!planScript.isHpProvided());

        PlanScript updatedPlanScript = this.planScriptClient.update(planScript.getResourceId(), planScript);

        LOGGER.info("Plan script object returned to client : " + updatedPlanScript.toJsonString());
    }

    private void deletePlanScript() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);
        String response = this.planScriptClient.delete(planScript.getResourceId());

        LOGGER.info("Response returned to client : " + response);
    }

    private void getPlanScriptDifferences() {
        PlanScript planScript = this.planScriptClient.getByName(PLAN_SCRIPT_NAME).get(0);

        ScriptDifferences scriptDifferences = this.planScriptClient.getDifferences(
                planScript.getResourceId(), StringUtils.EMPTY);

        LOGGER.info("Script differences returned to client : " + JsonPrettyPrinter.print(scriptDifferences));
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
