/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/

/**
 * A provider is a software plugin for the SAN Resource Manager that enables the resource manager 
 * to communicate with some kind of device that provides SAN network zoning. For example, 
 * The SAN Resource Manager includes a provider that communicates with Brocade Network Advisor or 
 * HP SAN Network Advisor systems, and another that communicates with HP 5900 series switches.
 */
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.dto.SanProviderResponseCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FcSansProviderClient {
    /*
     * The module aids in fetching all the SanProvider for all SanProvider
     * registered under current HP OneView
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @return sanProviderResponseCollectionDto, which is a object containing
     * the SanProviderResponse Collection details.
     */
    public SanProviderResponseCollection getAllProviders(final RestParams params);

    /*
     * The module aids in fetching the SanProvider for specific SanProvider
     * registered under current HP OneView
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @return sanProviderResponseDto, which is a object containing the
     * SanProviderResponse details.
     */
    public SanProviderResponse getProviderByName(final RestParams params, final String displayName);

    /**
     * The module aids in fetching the SanProvider details for the SanProvider
     * name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the SanProvider name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the SanProvider name as seen
     *         in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
