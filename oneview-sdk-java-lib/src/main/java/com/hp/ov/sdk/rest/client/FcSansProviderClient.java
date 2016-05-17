/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

/**
 * A provider is a software plugin for the SAN Resource Manager that enables the resource manager
 * to communicate with some kind of device that provides SAN network zoning. For example,
 * The SAN Resource Manager includes a provider that communicates with Brocade Network Advisor or
 * HP SAN Network Advisor systems, and another that communicates with HP 5900 series switches.
 */
public interface FcSansProviderClient {

    /**
     * The module aids in fetching all the SAN providers registered under the current HPE OneView.
     * 
     * @param params The {@link RestParams} is a structure containing the connection details.
     *
     * @return {@link ResourceCollection}&lt;{@link SanProviderResponse}&gt; containing
     * the details for all found SAN providers.
     */
    ResourceCollection<SanProviderResponse> getAllProviders(final RestParams params);

    /**
     * The module aids in fetching the SAN provider for specific SAN provider
     * registered under current HPE OneView.
     * 
     * @param params The {@link RestParams} is a structure containing the connection details.
     * @param displayName The name of the provider.
     * 
     * @return {@link SanProviderResponse} containing the SAN provider details.
     */
    SanProviderResponse getProviderByName(final RestParams params, final String displayName);

    /**
     * The module aids in fetching the SAN provider resource identifier for the SAN provider
     * name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the SAN provider name as seen in HPE OneView.
     * @return String which is the resource identifier for the SAN provider name as seen
     *         in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
