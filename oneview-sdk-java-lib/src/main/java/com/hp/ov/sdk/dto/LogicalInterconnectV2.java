/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class LogicalInterconnectV2 extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Compliance consistencyStatus;
    private String domainUri;
    private List<String> enclosureUris = new ArrayList<String>();
    private EthernetInterconnectSettingsV2 ethernetSettings;
    private InterconnectMap interconnectMap;
    private List<String> interconnects = new ArrayList<String>();
    private String logicalInterconnectGroupUri;
    private PortMonitor portMonitor;
    private SnmpConfiguration snmpConfiguration;
    private StackingHealth stackingHealth;
    private TelemetryConfiguration telemetryConfiguration;

    /**
     * @return the consistencyStatus
     */
    public Compliance getConsistencyStatus() {
        return consistencyStatus;
    }

    /**
     * @param consistencyStatus
     *            the consistencyStatus to set
     */
    public void setConsistencyStatus(final Compliance consistencyStatus) {
        this.consistencyStatus = consistencyStatus;
    }

    /**
     * @return the domainUri
     */
    public String getDomainUri() {
        return domainUri;
    }

    /**
     * @param domainUri
     *            the domainUri to set
     */
    public void setDomainUri(final String domainUri) {
        this.domainUri = domainUri;
    }

    /**
     * @return the enclosureUris
     */
    public List<String> getEnclosureUris() {
        return enclosureUris;
    }

    /**
     * @param enclosureUris
     *            the enclosureUris to set
     */
    public void setEnclosureUris(final List<String> enclosureUris) {
        this.enclosureUris = enclosureUris;
    }

    /**
     * @return the ethernetSettings
     */
    public EthernetInterconnectSettingsV2 getEthernetSettings() {
        return ethernetSettings;
    }

    /**
     * @param ethernetSettings
     *            the ethernetSettings to set
     */
    public void setEthernetSettings(final EthernetInterconnectSettingsV2 ethernetSettings) {
        this.ethernetSettings = ethernetSettings;
    }

    /**
     * @return the interconnectMap
     */
    public InterconnectMap getInterconnectMap() {
        return interconnectMap;
    }

    /**
     * @param interconnectMap
     *            the interconnectMap to set
     */
    public void setInterconnectMap(final InterconnectMap interconnectMap) {
        this.interconnectMap = interconnectMap;
    }

    /**
     * @return the interconnects
     */
    public List<String> getInterconnects() {
        return interconnects;
    }

    /**
     * @param interconnects
     *            the interconnects to set
     */
    public void setInterconnects(final List<String> interconnects) {
        this.interconnects = interconnects;
    }

    /**
     * @return the logicalInterconnectGroupUri
     */
    public String getLogicalInterconnectGroupUri() {
        return logicalInterconnectGroupUri;
    }

    /**
     * @param logicalInterconnectGroupUri
     *            the logicalInterconnectGroupUri to set
     */
    public void setLogicalInterconnectGroupUri(final String logicalInterconnectGroupUri) {
        this.logicalInterconnectGroupUri = logicalInterconnectGroupUri;
    }

    /**
     * @return the portMonitor
     */
    public PortMonitor getPortMonitor() {
        return portMonitor;
    }

    /**
     * @param portMonitor
     *            the portMonitor to set
     */
    public void setPortMonitor(final PortMonitor portMonitor) {
        this.portMonitor = portMonitor;
    }

    /**
     * @return the snmpConfiguration
     */
    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    /**
     * @param snmpConfiguration
     *            the snmpConfiguration to set
     */
    public void setSnmpConfiguration(final SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     * @return the stackingHealth
     */
    public StackingHealth getStackingHealth() {
        return stackingHealth;
    }

    /**
     * @param stackingHealth
     *            the stackingHealth to set
     */
    public void setStackingHealth(final StackingHealth stackingHealth) {
        this.stackingHealth = stackingHealth;
    }

    /**
     * @return the telemetryConfiguration
     */
    public TelemetryConfiguration getTelemetryConfiguration() {
        return telemetryConfiguration;
    }

    /**
     * @param telemetryConfiguration
     *            the telemetryConfiguration to set
     */
    public void setTelemetryConfiguration(final TelemetryConfiguration telemetryConfiguration) {
        this.telemetryConfiguration = telemetryConfiguration;
    }

}
