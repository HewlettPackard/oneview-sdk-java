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
package com.hp.ov.sdk.rest.client.storage;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.fcsans.DeviceManagerResponse;
import com.hp.ov.sdk.dto.fcsans.SanProviderResponse;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

/**
 * A provider is a software plugin for the SAN Resource Manager that enables the resource manager
 * to communicate with a device that provides SAN network zoning. For example,
 * The SAN Resource Manager includes a provider that communicates with Brocade Network Advisor or
 * HPE SAN Network Advisor systems, and another that communicates with HPE 5900 series switches.
 */
@Api(FcSanProviderClient.FC_SANS_PROVIDER_URI)
public interface FcSanProviderClient extends
        SearchableResource<SanProviderResponse> {

    String FC_SANS_PROVIDER_URI = "/rest/fc-sans/providers";
    String FC_SANS_PROVIDER_DEVICE_MANAGER_URI = "/device-managers";

    /**
     * Adds a resource according to the provided <code>resource</code> object.
     *
     * <p>Depending on the resource type, the add action can take some time to complete, therefore,
     * you can specify a timeout using an implementation of {@link RequestOption} called
     * {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout is specified,
     * the default behavior is to wait until the add action completes. The following example shows
     * how to specify the timeout:
     *
     * <pre>{@code
     *     SomeClient client = oneViewClient.someClient();
     *     SomeResource resource = new SomeResource();
     *     TaskResource task = client.add(resource, TaskTimeout.of(5000)); //5 secs
     * }</pre>
     *
     * @param providerId The ID of the provider of the device manager
     * @param deviceManager object containing the SAN manager credential details.
     * @param options varargs of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{providerId}" + FC_SANS_PROVIDER_DEVICE_MANAGER_URI ,method = HttpMethod.POST)
    public TaskResource addSanManager(@PathParam("providerId") String providerId,
            @BodyParam DeviceManagerResponse deviceManager, RequestOption... options);

}
