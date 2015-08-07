/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.InterconnectFibData;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.PortMonitorUplinkPortCollection;
import com.hp.ov.sdk.dto.SwitchDumpDataInfo;
import com.hp.ov.sdk.dto.SwitchDumpGenerationInfo;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.dto.generated.TelemetryConfiguration;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalInterconnectClient {

    /**
     * The module aids in fetching the LogicalInterconnects details for the
     * specified LogicalInterconnects resourceId
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return logicalInterconnectsDto, which is a object containing the
     *         LogicalInterconnects details.
     */
    public LogicalInterconnects getLogicalInterconnect(RestParams params, String resourceId);

    /**
     * The module aids in fetching the LogicalInterconnects details for all
     * LogicalInterconnects found under current HP OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return logicalInterconnectsCollectionDto, which is a object containing a
     *         collection of LogicalInterconnects details.
     */
    public LogicalInterconnectCollectionV2 getAllLogicalInterconnects(final RestParams params);

    /**
     * Returns a logical interconnect to a consistent state. The current logical
     * interconnect state is compared to the associated logical interconnect
     * group. Any differences identified are corrected, bringing the logical
     * interconnect back to a consistent state. Changes are asynchronously
     * applied to all managed interconnects.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectComplianceById(final RestParams params, final String resourceId,
            final boolean asyncOrSyncMode);

    /**
     * Installs firmware to a logical interconnect. The three operations that
     * are supported for the firmware update are STAGE (uploads firmware to the
     * interconnect), ACTIVATE (installs firmware on the interconnect) and
     * UPDATE (which does a STAGE and ACTIVATE in a sequential manner).
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param lIFirmwareDto
     *            This is a lIFirmware object containing the update to be made
     *            to existing LogicalInterconnect pointed to by the above
     *            mentioned resourceId
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnect Object
     *            which takes in a String containing the update to be made,
     *            which is converted to LogicalInterconnect Object using adaptor
     *            and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectFirmwareById(final RestParams params, String resourceId,
            final LiFirmware lIFirmwareDto, final boolean asyncOrSyncMode, final boolean useJsonRequest);

    /**
     * Updates the SNMP configuration of a logical interconnect. Changes to the
     * SNMP configuration are asynchronously applied to all managed
     * interconnects.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param snmpConfigurationDto
     *            This is a snmpConfiguration object containing the update to be
     *            made to existing LogicalInterconnect pointed to by the above
     *            mentioned resourceId
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnect Object
     *            which takes in a String containing the update to be made,
     *            which is converted to LogicalInterconnect Object using adaptor
     *            and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectSnmpConfigurationById(final RestParams params, String resourceId,
            final SnmpConfiguration snmpConfigurationDto, final boolean asyncOrSyncMode, final boolean useJsonRequest);

    /**
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param logicalInterconnectName
     *            The logicalInterconnectName is the LogicalInterconnect name as
     *            seen in HP OneView.
     * @return LogicalInterconnectDto, which is a object containing the
     *         LogicalInterconnect Details.
     */
    public LogicalInterconnects getLogicalInterconnectByName(RestParams params, String logicalInterconnectName);

    /**
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return LiFirmwareDto, which is a object containing the LiFirmware
     *         details.
     */
    public LiFirmware getLogicalInterconnectFirmwareById(RestParams params, String resourceId);

    /**
     * This module aids in fetching the forwarding information base data for a
     * logical interconnect.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return interconnectFibData, which is a object containing the
     *         InterconnectFibData details.
     */
    public InterconnectFibData getLogicalInterconnectForwardingInformationBase(final RestParams params, String resourceId);

    /**
     * This module aids in generating the forwarding information base dump file
     * for a logical interconnect.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return interconnectFibDataInfo, which is a object containing the
     *         InterconnectFibDataInfo details.
     */
    public InterconnectFibDataInfo createLogicalInterconnectForwardingInformationBase(final RestParams params, String resourceId);

    /**
     * This module aids by downloading the generated logical interconnect
     * forwarding information base dump file from the appliance to the caller.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param fileName
     *            , file to which
     *            LogicalInterconnectForwardingInformationBaseDump is to be
     *            saved
     */
    public void getLogicalInterconnectForwardingInformationBaseDump(final RestParams params, String resourceId,
            final String fileName);

    /**
     * This module aids in fetching the SNMP configuration for a logical
     * interconnect that is specified by resourceId.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return snmpConfiguration, which is a object containing the
     *         snmpConfiguration details.
     */
    public SnmpConfiguration getLogicalInterconnectSnmpConfigurationById(final RestParams params, String resourceId);

    /**
     * This module aids in generating a logical interconnect support dump. This
     * is a synchronous call. If successful, it returns the dump file
     * information along with completion status of the operation (Completed or
     * Warning), and a short summary of the operation result.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param switchDumpGenerationInfo
     *            specifies the errorCode to create support dump.
     * @return switchDumpDataInfo, which is a object containing the
     *         switchDumpDataInfo details.
     */
    public SwitchDumpDataInfo createLogicalInterconnectSupportDump(final RestParams params, final String resourceId,
            final SwitchDumpGenerationInfo switchDumpGenerationInfo);

    /**
     * THis module aids in fetching a collection of uplink ports from the member
     * interconnects which are eligible for assignment to an analyzer port.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return portMonitorUplinkPortCollection, which is a object containing the
     *         portMonitorUplinkPortCollection details.
     */
    public PortMonitorUplinkPortCollection getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(final RestParams params,
            final String resourceId);

    /**
     * This module aids in asynchronously applying or re-applying the logical
     * interconnect configuration to all managed interconnects.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return taskResource
     */
    public TaskResourceV2 updateLogicalInterconnectConfiguration(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the port monitor configuration of a logical
     * interconnect.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return portMonitor, which is a object containing the portMonitor
     *         details.
     */
    public PortMonitor getLogicalInterconnectPortMonitorConfiguration(final RestParams params, final String resourceId);

    /**
     * This module aids in updating the port monitor configuration of a logical
     * interconnect.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return taskResource
     */
    public TaskResourceV2 updateLogicalInterconnectPortMonitorConfiguration(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the telemetry configuration of a logical
     * interconnect.
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param telementaryConfigurationId
     * @return telemetryConfiguration, which is a object containing the
     *         telemetryConfiguration details.
     */
    public TelemetryConfiguration getLogicalInterconnectTelementaryConfiguration(final RestParams params, final String resourceId,
            final String telementaryConfigurationId);

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resourceId
     * 
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param telementaryConfigurationId
     *            The telementaryConfigurationId that specifies the telementary
     *            Id for the resource specified by resourceId
     * @param telemetryConfiguration
     *            which is a object containing the update to be made to existing
     *            telementary configuration for LogicalInterconnect pointed to
     *            by the above mentioned telementaryConfigurationId specified
     *            for resourceId.
     * @return telemetryConfiguration, which is a object containing the
     *         telemetryConfiguration details.
     */
    public TelemetryConfiguration updateLogicalInterconnectTelementaryConfiguration(final RestParams params,
            final String resourceId, final String telementaryConfigurationId, final TelemetryConfiguration telemetryConfiguration);

}
