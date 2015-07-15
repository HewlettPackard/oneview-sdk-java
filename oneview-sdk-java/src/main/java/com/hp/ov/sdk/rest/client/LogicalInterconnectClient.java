/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalInterconnectClient
{

    /**
     * The module aids in fetching the LogicalInterconnects details for the
     * specified LogicalInterconnects resourceId
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return logicalInterconnectsDto, which is a object containing the
     *         LogicalInterconnects details.
     */
    public LogicalInterconnects getLogicalInterconnect(RestParams params,
            String resourceId);

    /**
     * The module aids in fetching the LogicalInterconnects details for all
     * LogicalInterconnects found under current HP OneView.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @return logicalInterconnectsCollectionDto, which is a object containing a
     *         collection of LogicalInterconnects details.
     */
    public LogicalInterconnectCollectionV2 getAllLogicalInterconnects(
            final RestParams params);

    /**
     * Returns a logical interconnect to a consistent state. The current logical
     * interconnect state is compared to the associated logical interconnect
     * group. Any differences identified are corrected, bringing the logical
     * interconnect back to a consistent state. Changes are asynchronously
     * applied to all managed interconnects.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param asyncOrSyncMode
     *        Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectComplianceById(
            final RestParams params, final String resourceId,
            final boolean asyncOrSyncMode);

    /**
     * Installs firmware to a logical interconnect. The three operations that
     * are supported for the firmware update are STAGE (uploads firmware to the
     * interconnect), ACTIVATE (installs firmware on the interconnect) and
     * UPDATE (which does a STAGE and ACTIVATE in a sequential manner).
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param lIFirmwareDto
     *        This is a lIFirmware object containing the update to be made
     *        to existing LogicalInterconnect pointed to by the above
     *        mentioned resourceId
     * @param asyncOrSyncMode
     *        Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *        The JsonRequest body is part of LogicalInterconnect Object
     *        which takes in a String containing the update to be made,
     *        which is converted to LogicalInterconnect Object using adaptor
     *        and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectFirmwareById(
            final RestParams params, String resourceId,
            final LiFirmware lIFirmwareDto, final boolean asyncOrSyncMode,
            final boolean useJsonRequest);

    /**
     * Updates the SNMP configuration of a logical interconnect. Changes to the
     * SNMP configuration are asynchronously applied to all managed
     * interconnects.
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param snmpConfigurationDto
     *        This is a snmpConfiguration object containing the update to be
     *        made to existing LogicalInterconnect pointed to by the above
     *        mentioned resourceId
     * @param asyncOrSyncMode
     *        Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *        The JsonRequest body is part of LogicalInterconnect Object
     *        which takes in a String containing the update to be made,
     *        which is converted to LogicalInterconnect Object using adaptor
     *        and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectSnmpConfigurationById(
            final RestParams params, String resourceId,
            final SnmpConfiguration snmpConfigurationDto,
            final boolean asyncOrSyncMode, final boolean useJsonRequest);

    /**
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param logicalInterconnectName
     *        The logicalInterconnectName is the LogicalInterconnect name as
     *        seen in HP OneView.
     * @return LogicalInterconnectDto, which is a object containing the
     *         LogicalInterconnect Details.
     */
    public LogicalInterconnects getLogicalInterconnectByName(RestParams params,
            String logicalInterconnectName);

    /**
     * 
     * @param params
     *        The RestParams is a structure containing the connection
     *        details.
     * @param resourceId
     *        The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return LiFirmwareDto, which is a object containing the LiFirmware
     *         details.
     */
    public LiFirmware getLogicalInterconnectFirmware(RestParams params,
            String resourceId);

    // TODO - implement the remaining update methods and GetByName method   

}
