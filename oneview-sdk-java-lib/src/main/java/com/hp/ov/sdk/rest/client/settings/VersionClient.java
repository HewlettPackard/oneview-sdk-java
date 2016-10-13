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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.dto.ApplianceVersions;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;

public class VersionClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionClient.class);

    private static final String APPLIANCE_VERSION = "/rest/version";

    private final BaseClient baseClient;

    public VersionClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Returns the range of possible API versions supported by the appliance. The response
     * contains the current version and the minimum version. The current version is the
     * recommended version to specify in the REST header. The other versions are supported
     * for backward compatibility, but might not support the most current features.
     *
     * @return {@link ApplianceVersions} containing the versions supported by the appliance.
     */
    public ApplianceVersions getApplianceVersions() {
        LOGGER.info("VersionClient : getVersion : Start");

        Request request = new Request(HttpMethod.GET, APPLIANCE_VERSION);

        ApplianceVersions versions = baseClient.executeRequest(request, ApplianceVersions.class);

        LOGGER.info("VersionClient : getVersion : End");

        return versions;
    }
}
