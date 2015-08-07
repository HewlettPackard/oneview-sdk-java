/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.InterconnectTypeCollectionV2;
import com.hp.ov.sdk.dto.generated.InterconnectTypes;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface InterconnectTypeClient {

    /**
     * The module aids in fetching the InterconnectTypes details for the
     * specified InterconnectTypes resourceId
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnectTypes as seen in HP OneView.
     * @return interconnectTypesDto, which is a object containing the
     *         InterconnectTypes details.
     */
    public InterconnectTypes getInterconnectType(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the InterconnectType details for all
     * InterconnectType registered under current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return interconnectTypesCollectionDto, which is a object containing the
     *         collection of InterconnectTypes details.
     */
    public InterconnectTypeCollectionV2 getAllInterconnectType(final RestParams params);

    /**
     * The module aids in fetching the InterconnectTypes details for the
     * specified InterconnectTypes name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the InterconnectTypes name as seen in HP
     *            OneView.
     * @return interconnectTypesDto, which is a object containing the
     *         InterconnectTypes details.
     */
    public InterconnectTypes getInterconnectTypeByName(final RestParams params, final String name);

}
