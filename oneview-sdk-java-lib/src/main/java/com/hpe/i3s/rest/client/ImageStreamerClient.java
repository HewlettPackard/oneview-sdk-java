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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.reflect.Reflection;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hpe.i3s.client.deployment.ArtifactsBundleClient;
import com.hpe.i3s.client.deployment.DeploymentPlanClient;
import com.hpe.i3s.client.deployment.GoldenImageClient;
import com.hpe.i3s.client.deployment.OsBuildPlanClient;
import com.hpe.i3s.client.deployment.PlanScriptClient;
import com.hpe.i3s.client.statelessserver.OsVolumeClient;

public class ImageStreamerClient {

    private final BaseClient baseClient;

    private final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public ImageStreamerClient(SDKConfiguration config) {
        this.baseClient = new BaseClient(config, config.getImageStreamerHostname());
    }

    public synchronized ArtifactsBundleClient artifactsBundle() {
        return this.getProxy(ArtifactsBundleClient.class);
    }

    public synchronized DeploymentPlanClient deploymentPlan() {
        return this.getProxy(DeploymentPlanClient.class);
    }

    public synchronized OsBuildPlanClient osBuildPlan() {
        return this.getProxy(OsBuildPlanClient.class);
    }

    public synchronized OsVolumeClient osVolume() {
        return this.getProxy(OsVolumeClient.class);
    }

    public synchronized PlanScriptClient planScript() {
        return this.getProxy(PlanScriptClient.class);
    }

    public synchronized GoldenImageClient goldenImage() {
        return this.getProxy(GoldenImageClient.class);
    }

    private <T> T getProxy(Class<T> clientClass) {
        T instance = (T) this.instances.get(clientClass);

        if (instance == null) {
            instance = Reflection.newProxy(clientClass, new ClientRequestHandler<>(this.baseClient, clientClass));

            this.instances.put(clientClass, instance);
        }
        return instance;
    }

}
