/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.List;

import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalInterconnectGroupClient {

    /**
     * The module aids in fetching the LogicalInterconnectGroups details for the
     * specified LogicalInterconnectGroups resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnectGroups as seen in HP
     *            OneView.
     * @return logicalInterconnectGroupsDto, which is a object containing the
     *         LogicalInterconnectGroups details.
     */
    public LogicalInterconnectGroups getLogicalInterconnectGroup(RestParams params, String resourceId);

    /**
     * The module aids in fetching the LogicalInterconnects details for all the
     * LogicalInterconnectGroups found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return logicalInterconnectGroupsCollectionDto, which is a object
     *         containing a collection of LogicalInterconnectGroups details.
     */
    public LogicalInterconnectGroupCollectionV2 getAllLogicalInterconnectGroups(final RestParams params);

    /**
     * The module aids in fetching the LogicalInterconnectGroups details for the
     * LogicalInterconnectGroups name as specified in HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the LogicalInterconnectGroups name as seen
     *            in HP OneView.
     * @return logicalInterconnectGroupsDto, which is a object containing the
     *         LogicalInterconnectGroups details.
     */
    public LogicalInterconnectGroups getLogicalInterconnectGroupByName(RestParams params, String name);

    /**
     * The module aids in creation of LogicalInterconnectGroups when provided
     * with the LogicalInterconnectGroups details as LogicalInterconnectGroups
     * object or JsonRequest. It can process the request asynchronously or
     * synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param logicalInterconnectGroupDto
     *            This is a object containing the LogicalInterconnectGroups
     *            details, used to create a LogicalInterconnectGroups
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnectGroups
     *            Object which takes in a String containing the new
     *            LogicalInterconnectGroups details, which is converted to
     *            LogicalInterconnectGroups Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createLogicalInterconnectGroup(final RestParams params,
            final LogicalInterconnectGroups logicalInterconnectGroupDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * The module takes in an UplinkSet object or JsonRequest and updates the
     * existing LogicalInterconnect based on resource Id. It can process the
     * request asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnectGroups as seen in HP
     *            OneView.
     * @param uplinkSetDto
     *            This is a object containing the UplinkSet details, used to
     *            update a LogicalInterconnectGroups
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnectGroups
     *            Object which takes in a String containing the update to be
     *            made, which is converted to LogicalInterconnectGroups Object
     *            using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectGroup(final RestParams params, final String resourceId,
            final List<UplinkSet> uplinkSetDto, final boolean aSync, final boolean useJsonRequest);

    /**
     * The module aids in deleting a LogicalInterconnectGroup for the specified
     * LogicalInterconnectGroup resourceId. It can process the request
     * asynchronously or synchronously based on flag input.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnectGroups as seen in HP
     *            OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteLogicalInterconnectGroup(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the default interconnect settings details for
     * the LIG found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return interconnectSettingsDto, which is a object containing the
     *         interconnect setting details.
     */
    public InterconnectSettingsV2 getDefaultInterconnectSettings(RestParams params);

    /**
     * The module aids in fetching the interconnect settings details for the LIG
     * found under the current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnectGroups as seen in HP
     *            OneView.
     * @param settingId
     *            The settingId for LogicalInterconnectGroups as seen in HP
     *            OneView.
     * @return interconnectSettingsDto, which is a object containing the
     *         interconnect setting details.
     */
    public InterconnectSettingsV2 getInterconnectSettings(RestParams params, final String resourceId, final String settingId);

    /**
     * The module aids in fetching the LogicalInterconnectGroup details for the
     * LogicalInterconnectGroups name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the LogicalInterconnectGroups name as seen
     *            in HP OneView.
     * @return String, which is a resource Id for the LogicalInterconnectGroups
     *         name as seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
