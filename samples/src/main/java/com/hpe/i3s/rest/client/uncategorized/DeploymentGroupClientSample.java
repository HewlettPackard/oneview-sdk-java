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
package com.hpe.i3s.rest.client.uncategorized;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hpe.i3s.client.uncategorized.DeploymentGroupClient;
import com.hpe.i3s.dto.uncategorized.deploymentgroup.DeploymentGroup;
import com.hpe.i3s.rest.client.ImageStreamerClient;
import com.hpe.i3s.rest.client.deployment.ImageStreamerClientSample;

public class DeploymentGroupClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeploymentGroupClientSample.class);

    private final DeploymentGroupClient deploymentGroupClient;

    public DeploymentGroupClientSample() {
        ImageStreamerClient i3sClient = new ImageStreamerClientSample().getImageStreamerClient();

        this.deploymentGroupClient = i3sClient.deploymentGroup();
    }

    private void getDeploymentGroupById() {
        ResourceCollection<DeploymentGroup> deploymentGroups = this.deploymentGroupClient.getAll();

        DeploymentGroup deploymentGroup = this.deploymentGroupClient.getById(deploymentGroups.get(0).getId());

        LOGGER.info("Deployment Group returned to client: {}", deploymentGroup.toJsonString());
    }

    private void getAllDeploymentGroups() {
        ResourceCollection<DeploymentGroup> deploymentGroups = this.deploymentGroupClient.getAll();

        LOGGER.info("Deployment Groups returned to client: {}", deploymentGroups.toJsonString());
    }

    public static void main(String[] args) {
        /* This resource is available only on HPE Synergy */
        DeploymentGroupClientSample sample = new DeploymentGroupClientSample();

        sample.getAllDeploymentGroups();
        sample.getDeploymentGroupById();
    }

}
