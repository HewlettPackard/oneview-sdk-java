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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.uncategorized.OsDeploymentPlan;
import com.hp.ov.sdk.rest.client.common.RetrievableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;

@Api(OsDeploymentPlanClient.OS_DEPLOYMENT_PLAN_URI)
public interface OsDeploymentPlanClient extends RetrievableResource<OsDeploymentPlan> {

    String OS_DEPLOYMENT_PLAN_URI = "/rest/os-deployment-plans";

    @Endpoint
    ResourceCollection<OsDeploymentPlan> getByName(String name);

}
