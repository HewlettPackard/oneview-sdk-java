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

import java.util.List;

import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalInterconnectGroupClient {

    /**
     * The module aids in fetching the logical interconnect group details for the
     * specified logical interconnect group resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @return {@link LogicalInterconnectGroups} containing the logical interconnect group details.
     */
    LogicalInterconnectGroups getLogicalInterconnectGroup(RestParams params, String resourceId);

    /**
     * The module aids in fetching the logical interconnect group details for all the
     * logical interconnect groups found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link LogicalInterconnectGroups}&gt; containing
     * the details for all found logical interconnect groups.
     */
    ResourceCollection<LogicalInterconnectGroups> getAllLogicalInterconnectGroups(final RestParams params);

    /**
     * The module aids in fetching the logical interconnect group details for the
     * logical interconnect group name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the logical interconnect group name as seen in HPE OneView.
     * @return {@link LogicalInterconnectGroups} containing the logical interconnect group details.
     */
    LogicalInterconnectGroups getLogicalInterconnectGroupByName(RestParams params, String name);

    /**
     * The module aids in creation of logical interconnect group when provided
     * with the logical interconnect group details as LogicalInterconnectGroups
     * object or a JsonRequest. It can process the request asynchronously or
     * synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param logicalInterconnectGroupDto
     *            Object containing the logical interconnect group details,
     *            used to create a logical interconnect group.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnectGroups
     *            object which takes in a String containing the new
     *            logical interconnect group details, which is converted to
     *            LogicalInterconnectGroups object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createLogicalInterconnectGroup(final RestParams params,
            final LogicalInterconnectGroups logicalInterconnectGroupDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in a list of UplinkSet object or JsonRequest and updates the
     * existing logical interconnect group based on the resource identifier. It can process the
     * request asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @param uplinkSetDto
     *            List of UplinkSet details used to update a logical interconnect group.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of UplinkSet object which takes
     *            in a String containing the update to be made, which is
     *            converted to UplinkSet object using an adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectGroup(final RestParams params, final String resourceId,
            final List<UplinkSet> uplinkSetDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a logical interconnect group for the specified
     * logical interconnect group resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteLogicalInterconnectGroup(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the default interconnect settings details for
     * the logical interconnect group found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link InterconnectSettingsV2} containing the interconnect setting details.
     */
    InterconnectSettingsV2 getDefaultInterconnectSettings(RestParams params);

    /**
     * The module aids in fetching the interconnect settings details for the
     * logical interconnect group found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @return {@link InterconnectSettingsV2} containing the interconnect setting details.
     *
     * @since HPE OneView 2.0
     */
    InterconnectSettingsV2 getInterconnectSettings(RestParams params, String resourceId);

    /**
     * The module aids in fetching the interconnect settings details for the
     * logical interconnect group found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @param settingId
     *            The settingId for logical interconnect group as seen in HPE OneView.
     * @return {@link InterconnectSettingsV2} containing the interconnect setting details.
     */
    InterconnectSettingsV2 getInterconnectSettings(RestParams params, final String resourceId, final String settingId);

    /**
     * The module aids in fetching the logical interconnect group resource identifier for the
     * logical interconnect group name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the logical interconnect group name as seen in HPE OneView.
     * @return String which is the resource identifier for the logical interconnect group
     *         name as seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
