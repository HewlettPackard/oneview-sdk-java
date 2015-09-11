/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.InterconnectsCollection;
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

    /**
     * The module aids in fetching the Interconnect details for the
     * FirmwareDriver name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param interconnectName
     *            The interconnectName is the Interconnect name as seen in HP
     *            OneView.
     * @return InterconnectsDto, which is a object containing the FirmwareDriver
     *         details.
     */
    public Interconnects getInterconnectByName(final RestParams params, final String interconnectName);

    /**
     * The module aids in fetching the Interconnects details for all the
     * Interconnects found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return InterconnectsCollectionDto, which is a object containing a
     *         collection of Interconnects details.
     */
    public InterconnectsCollection getAllInterconnects(final RestParams params);

    /**
     * The module aids in fetching the Interconnect driver details for the
     * Interconnect driver name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Interconnect driver name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the Interconnect driver name
     *         as seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
