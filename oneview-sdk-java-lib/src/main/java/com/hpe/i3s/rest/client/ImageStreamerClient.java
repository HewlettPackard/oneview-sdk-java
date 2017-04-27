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

package com.hpe.i3s.rest.client;

import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.hpe.core.AbstractClient;
import com.hpe.i3s.client.deployment.ArtifactsBundleClient;
import com.hpe.i3s.client.deployment.DeploymentPlanClient;
import com.hpe.i3s.client.deployment.GoldenImageClient;
import com.hpe.i3s.client.deployment.OsBuildPlanClient;
import com.hpe.i3s.client.deployment.PlanScriptClient;
import com.hpe.i3s.client.uncategorized.DeploymentGroupClient;
import com.hpe.i3s.client.statelessserver.OsVolumeClient;

public class ImageStreamerClient extends AbstractClient {

    private final BaseClient baseClient;

    public ImageStreamerClient(SDKConfiguration config, OneViewClient oneViewClient) {
        this.baseClient = new BaseClient(config, config.getImageStreamerHostname());

        this.baseClient.setSessionId(oneViewClient.getSessionId());
    }

    @Override
    protected BaseClient baseClient() {
        return this.baseClient;
    }

    /**
     * Creates or retrieves an existing instance of {@link ArtifactsBundleClient}.
     * This client provides an interface for managing artifact bundles.
     *
     * @return an interface to the artifact bundle REST API.
     */
    public synchronized ArtifactsBundleClient artifactsBundle() {
        return getProxy(ArtifactsBundleClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link DeploymentPlanClient}.
     * This client provides an interface for managing deployment plans.
     *
     * @return an interface to the deployment plan REST API.
     */
    public synchronized DeploymentPlanClient deploymentPlan() {
        return getProxy(DeploymentPlanClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link DeploymentGroupClient}.
     * This client provides an interface for managing deployment groups.
     *
     * @return an interface to the deployment group REST API.
     */
    public synchronized DeploymentGroupClient deploymentGroup() {
        return getProxy(DeploymentGroupClient.class);
    }
    
    /**
     * Creates or retrieves an existing instance of {@link OsBuildPlanClient}.
     * This client provides an interface for managing OS build plans.
     *
     * @return an interface to the OS build plan REST API.
     */
    public synchronized OsBuildPlanClient osBuildPlan() {
        return getProxy(OsBuildPlanClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link OsVolumeClient}.
     * This client provides an interface for managing OS volumes.
     *
     * @return an interface to the OS volume REST API.
     */
    public synchronized OsVolumeClient osVolume() {
        return getProxy(OsVolumeClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link PlanScriptClient}.
     * This client provides an interface for managing plan scripts.
     *
     * @return an interface to the plan script REST API.
     */
    public synchronized PlanScriptClient planScript() {
        return getProxy(PlanScriptClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link GoldenImageClient}.
     * This client provides an interface for managing golden images.
     *
     * @return an interface to the golden image REST API.
     */
    public synchronized GoldenImageClient goldenImage() {
        return getProxy(GoldenImageClient.class);
    }

}
