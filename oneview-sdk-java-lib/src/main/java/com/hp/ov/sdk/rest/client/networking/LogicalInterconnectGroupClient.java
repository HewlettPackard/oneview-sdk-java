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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalInterconnectGroupClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalInterconnectGroupClient.class);

    private final BaseClient baseClient;

    public LogicalInterconnectGroupClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * The module aids in fetching the logical interconnect group details for the
     * specified logical interconnect group resource identifier.
     *
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @return {@link LogicalInterconnectGroup} containing the logical interconnect group details.
     */
    public LogicalInterconnectGroup getById(final String resourceId) {
        LOGGER.info("LogicalInterconnectGroupClient : getById : Start");

        LogicalInterconnectGroup lig = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId), LogicalInterconnectGroup.class);

        LOGGER.info("LogicalInterconnectGroupClient : getById : End");

        return lig;
    }

    /**
     * The module aids in fetching the logical interconnect group details for all the
     * logical interconnect groups found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalInterconnectGroup}&gt; containing
     * the details for all found logical interconnect groups.
     */
    public ResourceCollection<LogicalInterconnectGroup> getAll() {
        LOGGER.info("LogicalInterconnectGroupClient : getAll : Start");

        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, LogicalInterconnectGroup.class);

        LOGGER.info("LogicalInterconnectGroupClient : getAll : End");

        return logicalInterconnectGroups;
    }

    /**
     * The module aids in fetching the logical interconnect group details for the
     * logical interconnect group name as specified in HPE OneView.
     *
     * @param name
     *            The name is the logical interconnect group name as seen in HPE OneView.
     * @return {@link ResourceCollection}&lt;{@link LogicalInterconnectGroup}&gt; containing the logical
     * interconnect group details.
     */
    public ResourceCollection<LogicalInterconnectGroup> getByName(final String name) {
        LOGGER.info("LogicalInterconnectGroupClient : getByName : Start");

        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, LogicalInterconnectGroup.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("LogicalInterconnectGroupClient : getByName : End");

        return logicalInterconnectGroups;
    }

    /**
     * The module aids in creation of logical interconnect group when provided
     * with the logical interconnect group details as LogicalInterconnectGroups
     * object. It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param logicalInterconnectGroupDto
     *            Object containing the logical interconnect group details,
     *            used to create a logical interconnect group.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(final LogicalInterconnectGroup logicalInterconnectGroupDto, final boolean aSync) {
        LOGGER.info("LogicalInterconnectGroupClient : create : Start");

        TaskResourceV2 taskResource = baseClient.createResource(
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, logicalInterconnectGroupDto, aSync);

        LOGGER.info("LogicalInterconnectGroupClient : create : End");

        return taskResource;
    }

    /**
     * The module takes in a list of UplinkSet object and updates the
     * existing logical interconnect group based on the resource identifier.
     * It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @param logicalInterconnectGroupDto
     *            Object containing the logical interconnect group details,
     *            used to update a logical interconnect group.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(final String resourceId, final LogicalInterconnectGroup logicalInterconnectGroupDto, final boolean aSync) {
        LOGGER.info("LogicalInterconnectGroupClient : update : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId), logicalInterconnectGroupDto, aSync);

        LOGGER.info("LogicalInterconnectGroupClient : update : End");

        return taskResource;
    }

    /**
     * The module aids in deleting a logical interconnect group for the specified
     * logical interconnect group resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(final String resourceId, final boolean aSync) {
        LOGGER.info("LogicalInterconnectGroupClient : delete : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId), aSync);

        LOGGER.info("LogicalInterconnectGroupClient : delete : End");

        return taskResource;
    }

    /**
     * The module aids in fetching the default interconnect settings details for
     * the logical interconnect group found under the current HPE OneView.
     *
     * @return {@link InterconnectSettingsV2} containing the interconnect setting details.
     */
    public InterconnectSettingsV2 getDefaultInterconnectSettings() {
        LOGGER.info("LogicalInterconnectGroupClient : getDefaultInterconnectSettings : Start");

        InterconnectSettingsV2 settings = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, SdkConstants.DEFAULT_SETTINGS), InterconnectSettingsV2.class);

        LOGGER.info("LogicalInterconnectGroupClient : getDefaultInterconnectSettings : End");

        return settings;
    }

    /**
     * The module aids in fetching the interconnect settings details for the
     * logical interconnect group found under the current HPE OneView.
     *
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @return {@link InterconnectSettingsV2} containing the interconnect setting details.
     *
     * @since HPE OneView 2.0
     */
    public InterconnectSettingsV2 getInterconnectSettings(final String resourceId) {
        LOGGER.info("LogicalInterconnectGroupClient : getInterconnectSettings : Start");

        InterconnectSettingsV2 settings = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId, SdkConstants.SETTINGS), InterconnectSettingsV2.class);

        LOGGER.info("LogicalInterconnectGroupClient : getInterconnectSettings : End");

        return settings;
    }

    /**
     * The module aids in fetching the interconnect settings details for the
     * logical interconnect group found under the current HPE OneView.
     *
     * @param resourceId
     *            The resource identifier for logical interconnect group as seen in HPE OneView.
     * @param settingId
     *            The settingId for logical interconnect group as seen in HPE OneView.
     * @return {@link InterconnectSettingsV2} containing the interconnect setting details.
     */
    public InterconnectSettingsV2 getInterconnectSettings(final String resourceId, final String settingId) {
        LOGGER.info("LogicalInterconnectGroupClient : getInterconnectSettings : Start");

        InterconnectSettingsV2 settings = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId, SdkConstants.SETTINGS, settingId), InterconnectSettingsV2.class);

        LOGGER.info("LogicalInterconnectGroupClient : getInterconnectSettings : End");

        return settings;
    }

}
