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

package com.hp.ov.sdk.rest.client.settings;

import com.hp.ov.sdk.dto.ApplianceVersions;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;

@Api(VersionClient.VERSIONS_URI)
public interface VersionClient {

    String VERSIONS_URI = "/rest/version";

    /**
     * Returns the range of possible API versions supported by the appliance. The response
     * contains the current version and the oldest version. The current version is the
     * recommended version to specify in the REST header. The other versions are supported
     * for backward compatibility, but might not support the most current features.
     *
     * @return {@link ApplianceVersions} containing the versions supported by the appliance.
     */
    @Endpoint
    ApplianceVersions getApplianceVersions();

}
