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

package com.hp.ov.sdk.rest.client.settings;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.settings.License;
import com.hp.ov.sdk.rest.client.common.RetrievableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(LicenseClient.LICENSES_URI)
public interface LicenseClient extends
        RetrievableResource<License> {

    String LICENSES_URI = "/rest/licenses";

    /**
     * Adds a license to the appliance.
     * Licenses are added to the appliance through a license key.
     *
     * @param license object containing the details of the license that should be added
     *
     * @return {@link License} the added license
     */
    @Endpoint(method = HttpMethod.POST)
    License add(@BodyParam License license);

    /**
     * Deletes the license identified by the provided <code>licenseId</code>.
     *
     * @param licenseId license identifier as seen in HPE OneView
     * @param options <code>varargs</code> of {@link RequestOption},
     * which can be used to specify some request options
     *
     * @return {@link TaskResource} task containing the result of this request
     */
    @Endpoint(uri = "/{licenseId}", method = HttpMethod.DELETE)
    TaskResource delete(@PathParam("licenseId") String licenseId, RequestOption... options);

}
