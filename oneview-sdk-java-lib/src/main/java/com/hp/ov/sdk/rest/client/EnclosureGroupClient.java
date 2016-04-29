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

import com.hp.ov.sdk.dto.EnclosureGroupCollectionV2;
import com.hp.ov.sdk.dto.generated.EnclosureGroups;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface EnclosureGroupClient {

    /**
     * The module aids in fetching the enclosure group details for the specified
     * enclosure group resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure group as seen in HPE OneView.
     * @return {@link EnclosureGroups} containing the enclosure group details.
     */
    EnclosureGroups getEnclosureGroup(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the enclosure group details for all enclosure
     * groups registered under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link EnclosureGroupCollectionV2} containing a collection of enclosure group details.
     */
    EnclosureGroupCollectionV2 getAllEnclosureGroups(final RestParams params);

    /**
     * The module aids in fetching the enclosure group details for the specified
     * enclosure group name.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the enclosure group name as seen in HPE OneView.
     * @return {@link EnclosureGroups} containing the enclosure group details.
     */
    EnclosureGroups getEnclosureGroupByName(final RestParams params, final String name);

    /**
     * The module aids in the creation of an enclosure group when provided with the
     * enclosure group details as EnclosureGroups object or JsonRequest.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param enclosureGroupsDto
     *            This is an object containing the enclosure group details, used
     *            to create an enclosure group.
     * @param useJsonRequest
     *            The JsonRequest body is part of {@link EnclosureGroups} object which
     *            takes in a String containing the new enclosure group details,
     *            which is converted to {@link EnclosureGroups} object using adaptor and
     *            processed.
     * @return {@link EnclosureGroups} containing the enclosure group details.
     */
    EnclosureGroups createEnclosureGroup(final RestParams params, final EnclosureGroups enclosureGroupsDto,
            final boolean useJsonRequest);

    /**
     * The module takes in an EnclosureGroups object or JsonRequest and updates
     * the existing enclosure group based on resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure group as seen in HPE OneView.
     * @param enclosureGroup
     *            This is a object containing the update to be made to existing
     *            EnclosureGroup pointed to by the above mentioned resource identifier.
     * @param useJsonRequest
     *            The JsonRequest body is part of {@link EnclosureGroups} object which
     *            takes in a String containing the update to be made, which is
     *            converted to {@link EnclosureGroups} object using adaptor and
     *            processed.
     * @return {@link EnclosureGroups} containing the enclosure group details.
     */
    EnclosureGroups updateEnclosureGroup(final RestParams params, final String resourceId,
            final EnclosureGroups enclosureGroup, final boolean useJsonRequest);

    /**
     * The module aids in deleting an enclosure group for the specified enclosure
     * group resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure group as seen in HPE OneView.
     * @return an empty String on successful deletion. Otherwise, returns an error message.
     */
    String deleteEnclosureGroup(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the configuration script for the specified
     * enclosure group resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure group as seen in HPE OneView.
     * @return the configuration script for the specified enclosure group.
     */
    String getConfigurationScript(final RestParams params, final String resourceId);

    /**
     * The module aids in updating the configuration script for the specified
     * enclosure group resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for enclosure group as seen in HPE OneView.
     * @param scriptData
     *            THe script data to be updated for enclosure group.
     * @return the configuration script for the specified enclosure group.
     */
    String updateConfigurationScript(final RestParams params, final String resourceId, final String scriptData);

    /**
     * The module aids in fetching the enclosure group resource identifier for the
     * enclosure group name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the enclosure group name as seen in HPE OneView.
     * @return String which is the resource identifier for the enclosure group name
     *         as seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
