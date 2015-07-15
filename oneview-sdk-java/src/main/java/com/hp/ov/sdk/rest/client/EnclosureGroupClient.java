/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.EnclosureGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface EnclosureGroupClient
{

    /**
     * The module aids in fetching the enclosure group details for the specified
     * enclosure group resourceId
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure group as seen in HP OneView.
     * @return enclosureGroupsDto, which is a object containing the enclosure
     *         group details.
     */
    public EnclosureGroups getEnclosureGroup(final RestParams params,
            final String resourceId);

    /**
     * The module aids in fetching the enclosure group details for all enclosure
     * group registered under current HP OneView
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return enclosureGroupsCollectionDto, which is a object containing a
     *         collection of enclosure group details.
     */
    public EnclosureGroupCollectionV2 getAllEnclosureGroups(
            final RestParams params);

    /**
     * The module aids in fetching the enclosure group details for the specified
     * enclosure group name
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param name
     *        The resourceName is the enclosure group name as seen in HP
     *        OneView.
     * @return enclosureGroupsDto, which is a object containing the enclosure
     *         group details.
     */
    public EnclosureGroups getEnclosureGroupByName(final RestParams params,
            final String name);

    /**
     * The module aids in creation of enclosure group when provided with the
     * enclosure group details as enclosure group object or JsonRequest.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param enclosureGroupsDto
     *        This is a object containing the EnclosureGroup details, used
     *        to create a Enclosure group.
     * @param useJsonRequest
     *        The JsonRequest body is part of EnclosureGroup Object which
     *        takes in a String containing the new EnclosureGroup details,
     *        which is converted to EnclosureGroup Object using adaptor and
     *        processed.
     * @return enclosureGroupsDto, which is a object containing the created
     *         enclosure group details.
     */
    public EnclosureGroups createEnclosureGroup(final RestParams params,
            final EnclosureGroups enclosureGroupsDto,
            final boolean useJsonRequest);

    /**
     * The module takes in an Enclosure group object or JsonRequest and updates
     * the existing enclosure group based on resource Id.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure group as seen in HP OneView.
     * @param enclosureGroup
     *        This is a object containing the update to be made to existing
     *        EnclosureGroup pointed to by the above mentioned resourceId
     * @param useJsonRequest
     *        The JsonRequest body is part of EnclosureGroup Object which
     *        takes in a String containing the update to be made, which is
     *        converted to EnclosureGroup Object using adaptor and
     *        processed.
     * @return enclosureGroupsDto, which is a object containing the updated
     *         enclosure group details.
     */
    public EnclosureGroups updateEnclosureGroup(final RestParams params,
            final String resourceId, final EnclosureGroups enclosureGroup,
            final boolean useJsonRequest);

    /**
     * The module aids in deleting a enclosure group for the specified enclosure
     * group resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure group as seen in HP OneView.
     * @return a empty String on successful deletion and error otherwise.
     */
    public String deleteEnclosureGroup(final RestParams params,
            final String resourceId);

    /**
     * The module aids in fetching the configuration script for the specified
     * enclosure
     * group resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure group as seen in HP OneView.
     * @return String, the configuration script for the specified enclosure
     *         group
     */
    public String getConfigurationScript(final RestParams params, final String resourceId);

    /**
     * The module aids in updating the configuration script for the specified
     * enclosure
     * group resourceId.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for enclosure group as seen in HP OneView.
     * @param scriptData
     *        THe script data to be updated for enclosure group
     * @return String, the configuration script for the specified enclosure
     *         group
     */
    public String updateConfigurationScript(final RestParams params, final String resourceId, final String scriptData);

}
