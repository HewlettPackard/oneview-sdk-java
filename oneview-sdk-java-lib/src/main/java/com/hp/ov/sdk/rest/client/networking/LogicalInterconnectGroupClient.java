/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.networking;

import com.hp.ov.sdk.dto.networking.InterconnectSettings;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(LogicalInterconnectGroupClient.LOGICAL_INTERCONNECT_GROUPS_URI)
public interface LogicalInterconnectGroupClient extends
        SearchableResource<LogicalInterconnectGroup>,
        CreatableResource<LogicalInterconnectGroup>,
        UpdatableResource<LogicalInterconnectGroup>,
        PatchableResource,
        DeletableResource {

    String LOGICAL_INTERCONNECT_GROUPS_URI = "/rest/logical-interconnect-groups";
    String LOGICAL_INTERCONNECT_GROUPS_DEFAULT_SETTINGS_URI = "/defaultSettings";
    String LOGICAL_INTERCONNECT_GROUPS_SETTINGS_URI = "/settings";

    /**
     * The module aids in fetching the default interconnect settings details for
     * the logical interconnect group found under the current HPE OneView.
     *
     * @return {@link InterconnectSettings} containing the interconnect setting details.
     */
    @Endpoint(uri = LOGICAL_INTERCONNECT_GROUPS_DEFAULT_SETTINGS_URI)
    InterconnectSettings getDefaultInterconnectSettings();

    /**
     * The module aids in fetching the interconnect settings details for the
     * logical interconnect group found under the current HPE OneView.
     *
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @return {@link InterconnectSettings} containing the interconnect setting details.
     *
     * @since HPE OneView 2.0
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_GROUPS_SETTINGS_URI)
    InterconnectSettings getInterconnectSettings(@PathParam("resourceId") String resourceId);

    /**
     * The module aids in fetching the interconnect settings details for the
     * logical interconnect group found under the current HPE OneView.
     *
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @param settingsId
     *            The settingsId for logical interconnect group as seen in HPE OneView.
     * @return {@link InterconnectSettings} containing the interconnect setting details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_GROUPS_SETTINGS_URI + "/{settingsId}")
    InterconnectSettings getInterconnectSettings_V120(@PathParam("resourceId") String resourceId,
            @PathParam("settingsId") String settingsId);

}
