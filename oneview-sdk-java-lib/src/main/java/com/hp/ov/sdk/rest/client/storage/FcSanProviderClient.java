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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.rest.client.BaseClient;

/**
 * A provider is a software plugin for the SAN Resource Manager that enables the resource manager
 * to communicate with some kind of device that provides SAN network zoning. For example,
 * The SAN Resource Manager includes a provider that communicates with Brocade Network Advisor or
 * HPE SAN Network Advisor systems, and another that communicates with HPE 5900 series switches.
 */
public class FcSanProviderClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSanProviderClient.class);

    private final BaseClient baseClient;

    public FcSanProviderClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SanProviderResponse}&gt; containing details
     * for all the available SAN providers found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SanProviderResponse}&gt; containing
     * the details for all found SAN providers.
     */
    public ResourceCollection<SanProviderResponse> getAll() {
        LOGGER.info("FcSanProviderClient : getAll : Start");

        ResourceCollection<SanProviderResponse> sanProviders = baseClient.getResourceCollection(
                ResourceUris.FC_SANS_PROVIDER_URI, SanProviderResponse.class);

        LOGGER.info("FcSanProviderClient : getAll : End");

        return sanProviders;
    }

    /**
     * Retrieves the SAN provider found under the current HPE OneView that match the name.
     *
     * @param displayName SAN provider name as seen in HPE OneView.
     *
     * @return {@link SanProviderResponse} details for the found SAN provider.
     */
    public SanProviderResponse getByName(String displayName) {
        LOGGER.info("FcSanProviderClient : getByName : start");

        SanProviderResponse sanProviderResponse = null;

        for (SanProviderResponse sanProvider : getAll().getMembers()) {
            if (sanProvider.getDisplayName().equals(displayName)) {
                sanProviderResponse = sanProvider;
                break;
            }
        }
        LOGGER.info("FcSanProviderClient : getByName : end");

        return sanProviderResponse;
    }

}
