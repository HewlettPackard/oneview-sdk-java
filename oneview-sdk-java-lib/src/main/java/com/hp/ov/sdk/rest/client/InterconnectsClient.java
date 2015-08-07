/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface InterconnectsClient {
    /**
     * The module aids in fetching the Interconnect details for the specified
     * Interconnect resourceId
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     * @return interconnectTypesDto, which is a object containing the
     *         InterconnectTypes details.
     */
    public Interconnects getInterconnects(final RestParams params, final String resourceId);
}
