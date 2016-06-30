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
package com.hp.ov.sdk.dto.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.FcoeInterconnectSettings;
import com.hp.ov.sdk.dto.InterconnectMap;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;

/**
 * The LogicalInterconnects data transfer object (DTO) contains the information
 * used to represent a LogicalInterconnects in the system. The
 * LogicalInterconnects Dto specifies the set of uplink set that is used to
 * channel the data(both vlan and fiber channel network) and the type of
 * interconnect that is used in the virtual connect to aid in the purpose. The
 * dto is used to add/update interconnects at a location and also in generating
 * support dumps. It is passed in to the update logicalInterconnects REST api,
 * as well as the update logicalInterconnects through java client api.
 */
public class LogicalInterconnects extends BaseModelResource {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     * (Required)
     *
     */
    private LogicalInterconnects.StackingHealth stackingHealth;
    private SnmpConfiguration snmpConfiguration;
    private TelemetryConfiguration telemetryConfiguration;
    private EthernetInterconnectSettingsV2 ethernetSettings;
    @Since(200)
    private FcoeInterconnectSettings fcoeSettings;
    private Compliance consistencyStatus;
    private String domainUri;
    private List<String> enclosureUris = new ArrayList<String>();
    private InterconnectMap interconnectMap;
    private List<String> interconnects = new ArrayList<String>();
    private String logicalInterconnectGroupUri;
    private PortMonitor portMonitor;

    /**
     *
     * @return The stackingHealth
     */
    public LogicalInterconnects.StackingHealth getStackingHealth() {
        return stackingHealth;
    }

    /**
     *
     * @param stackingHealth
     *            The stackingHealth
     */
    public void setStackingHealth(final LogicalInterconnects.StackingHealth stackingHealth) {
        this.stackingHealth = stackingHealth;
    }

    /**
     *
     * @return The snmpConfiguration
     */
    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    /**
     *
     * @param snmpConfiguration
     *            The snmpConfiguration
     */
    public void setSnmpConfiguration(final SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     *
     * @return The telemetryConfiguration
     */
    public TelemetryConfiguration getTelemetryConfiguration() {
        return telemetryConfiguration;
    }

    /**
     *
     * @param telemetryConfiguration
     *            The telemetryConfiguration
     */
    public void setTelemetryConfiguration(final TelemetryConfiguration telemetryConfiguration) {
        this.telemetryConfiguration = telemetryConfiguration;
    }

    /**
     *
     * @return The ethernetSettings
     */
    public EthernetInterconnectSettingsV2 getEthernetSettings() {
        return ethernetSettings;
    }

    /**
     *
     * @param ethernetSettings
     *            The ethernetSettings
     */
    public void setEthernetSettings(final EthernetInterconnectSettingsV2 ethernetSettings) {
        this.ethernetSettings = ethernetSettings;
    }

    /**
    *
    * @return The fcoeSettings
    */
    public FcoeInterconnectSettings getFcoeSettings() {
        return fcoeSettings;
    }

    /**
     *
     * @param fcoeSettings
     *            The fcoeSettings
     */
    public void setFcoeSettings(final FcoeInterconnectSettings fcoeSettings) {
        this.fcoeSettings = fcoeSettings;
    }

    /**
     *
     * (Required)
     *
     * @return The domainUri
     */
    public String getDomainUri() {
        return domainUri;
    }

    /**
     *
     * (Required)
     *
     * @param domainUri
     *            The domainUri
     */
    public void setDomainUri(final String domainUri) {
        this.domainUri = domainUri;
    }

    /**
     *
     * (Required)
     *
     * @return The enclosureUris
     */
    public List<String> getEnclosureUris() {
        return enclosureUris;
    }

    /**
     *
     * (Required)
     *
     * @param enclosureUris
     *            The enclosureUris
     */
    public void setEnclosureUris(final List<String> enclosureUris) {
        this.enclosureUris = enclosureUris;
    }

    /**
     *
     * (Required)
     *
     * @return The interconnectMap
     */
    public InterconnectMap getInterconnectMap() {
        return interconnectMap;
    }

    /**
     *
     * (Required)
     *
     * @param interconnectMap
     *            The interconnectMap
     */
    public void setInterconnectMap(final InterconnectMap interconnectMap) {
        this.interconnectMap = interconnectMap;
    }

    /**
     *
     * (Required)
     *
     * @return The interconnects
     */
    public List<String> getInterconnects() {
        return interconnects;
    }

    /**
     *
     * (Required)
     *
     * @param interconnects
     *            The interconnects
     */
    public void setInterconnects(final List<String> interconnects) {
        this.interconnects = interconnects;
    }

    /**
     *
     * (Required)
     *
     * @return The logicalInterconnectGroupUri
     */
    public String getLogicalInterconnectGroupUri() {
        return logicalInterconnectGroupUri;
    }

    /**
     *
     * (Required)
     *
     * @param logicalInterconnectGroupUri
     *            The logicalInterconnectGroupUri
     */
    public void setLogicalInterconnectGroupUri(final String logicalInterconnectGroupUri) {
        this.logicalInterconnectGroupUri = logicalInterconnectGroupUri;
    }

    /**
     *
     * (Required)
     *
     * @return The portMonitor
     */
    public PortMonitor getPortMonitor() {
        return portMonitor;
    }

    /**
     *
     * (Required)
     *
     * @param portMonitor
     *            The portMonitor
     */
    public void setPortMonitor(final PortMonitor portMonitor) {
        this.portMonitor = portMonitor;
    }

    /**
     *
     * (Required)
     *
     * @return The consistencyStatus
     */
    public Compliance getConsistencyStatus() {
        return consistencyStatus;
    }

    /**
     *
     * (Required)
     *
     * @param consistencyStatus
     *            The consistencyStatus
     */
    public void setConsistencyStatus(final Compliance consistencyStatus) {
        this.consistencyStatus = consistencyStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(consistencyStatus)
                .append(domainUri)
                .append(enclosureUris)
                .append(interconnectMap)
                .append(interconnects)
                .append(logicalInterconnectGroupUri)
                .append(portMonitor)
                .append(stackingHealth)
                .append(snmpConfiguration)
                .append(telemetryConfiguration)
                .append(ethernetSettings)
                .append(fcoeSettings)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalInterconnects) == false) {
            return false;
        }
        final LogicalInterconnects rhs = ((LogicalInterconnects) other);
        return new EqualsBuilder()
                .append(consistencyStatus, rhs.consistencyStatus)
                .append(domainUri, rhs.domainUri)
                .append(enclosureUris, rhs.enclosureUris)
                .append(interconnectMap, rhs.interconnectMap)
                .append(interconnects, rhs.interconnects)
                .append(logicalInterconnectGroupUri, rhs.logicalInterconnectGroupUri)
                .append(portMonitor, rhs.portMonitor)
                .append(stackingHealth, rhs.stackingHealth)
                .append(snmpConfiguration, rhs.snmpConfiguration)
                .append(telemetryConfiguration, rhs.telemetryConfiguration)
                .append(ethernetSettings, rhs.ethernetSettings)
                .append(fcoeSettings, rhs.fcoeSettings)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    public static enum StackingHealth {

        Unknown("Unknown"),
        Connected("Connected"),
        BiConnected("BiConnected"),
        Disconnected("Disconnected");

        private final String value;
        private static Map<String, LogicalInterconnects.StackingHealth> constants = new HashMap<String, LogicalInterconnects.StackingHealth>();

        static {
            for (final LogicalInterconnects.StackingHealth c : values()) {
                constants.put(c.value, c);
            }
        }

        private StackingHealth(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static LogicalInterconnects.StackingHealth fromValue(final String value) {
            final LogicalInterconnects.StackingHealth constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum Compliance {

        CONSISTENT("CONSISTENT"), NOT_CONSISTENT("NOT_CONSISTENT");
        private final String value;
        private static Map<String, LogicalInterconnects.Compliance> constants = new HashMap<String, LogicalInterconnects.Compliance>();

        static {
            for (final LogicalInterconnects.Compliance c : values()) {
                constants.put(c.value, c);
            }
        }

        private Compliance(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static LogicalInterconnects.Compliance fromValue(final String value) {
            final LogicalInterconnects.Compliance constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

}
