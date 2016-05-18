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
import com.hp.ov.sdk.dto.StackingMode;

/**
 * The LogicalInterconnectGroups data transfer object (DTO) contains the
 * information used to represent a LogicalInterconnectGroups in the system. The
 * LogicalInterconnectGroups Dto is a generic specification of a set of uplink
 * set that is used to channel the data(both vlan and fiber channel network) and
 * the type of interconnect that is used in the virtual connect to aid in the
 * purpose.It is passed in to the add/update logicalInterconnectGroups REST api,
 * as well as the add/update logicalInterconnectGroups through java client api.
 */
public class LogicalInterconnectGroups extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    /**
     *
     * (Required)
     *
     */
    private List<UplinkSet> uplinkSets = new ArrayList<UplinkSet>();
    private TelemetryConfiguration telemetryConfiguration;
    private SnmpConfiguration snmpConfiguration;
    @Since(200)
    private QosConfiguration qosConfiguration;
    /**
     *
     * (Required)
     *
     */
    @Since(200)
    private LogicalInterconnectGroups.EnclosureType enclosureType;
    private LogicalInterconnectGroups.StackingHealth stackingHealth;
    @Since(200)
    private String fabricUri;
    /**
     *
     * (Required)
     *
     */
    private StackingMode stackingMode;
    private FcoeSettings fcoeSettings;
    private EthernetInterconnectSettingsV2 ethernetSettings;
    @Since(200)
    private List<Integer> enclosureIndexes = new ArrayList<Integer>();
    private Integer interconnectBaySet;
    @Since(200)
    private LogicalInterconnectGroups.RedundancyType redundancyType;
    /**
     *
     * (Required)
     *
     */
    private InterconnectMapTemplate interconnectMapTemplate;
    @Since(200)
    private List<String> internalNetworkUris = new ArrayList<String>();

    /**
     *
     * (Required)
     *
     * @return
     *     The uplinkSets
     */
    public List<UplinkSet> getUplinkSets() {
        return uplinkSets;
    }

    /**
     *
     * (Required)
     *
     * @param uplinkSets
     *     The uplinkSets
     */
    public void setUplinkSets(List<UplinkSet> uplinkSets) {
        this.uplinkSets = uplinkSets;
    }

    /**
     *
     * @return
     *     The telemetryConfiguration
     */
    public TelemetryConfiguration getTelemetryConfiguration() {
        return telemetryConfiguration;
    }

    /**
     *
     * @param telemetryConfiguration
     *     The telemetryConfiguration
     */
    public void setTelemetryConfiguration(TelemetryConfiguration telemetryConfiguration) {
        this.telemetryConfiguration = telemetryConfiguration;
    }

    /**
     *
     * @return
     *     The snmpConfiguration
     */
    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    /**
     *
     * @param snmpConfiguration
     *     The snmpConfiguration
     */
    public void setSnmpConfiguration(SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     *
     * @return
     *     The qosConfiguration
     */
    public QosConfiguration getQosConfiguration() {
        return qosConfiguration;
    }

    /**
     *
     * @param qosConfiguration
     *     The qosConfiguration
     */
    public void setQosConfiguration(QosConfiguration qosConfiguration) {
        this.qosConfiguration = qosConfiguration;
    }

    /**
     *
     * (Required)
     *
     * @return
     *     The enclosureType
     */
    public LogicalInterconnectGroups.EnclosureType getEnclosureType() {
        return enclosureType;
    }

    /**
     *
     * (Required)
     *
     * @param enclosureType
     *     The enclosureType
     */
    public void setEnclosureType(LogicalInterconnectGroups.EnclosureType enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     *
     * @return
     *     The stackingHealth
     */
    public LogicalInterconnectGroups.StackingHealth getStackingHealth() {
        return stackingHealth;
    }

    /**
     *
     * @param stackingHealth
     *     The stackingHealth
     */
    public void setStackingHealth(LogicalInterconnectGroups.StackingHealth stackingHealth) {
        this.stackingHealth = stackingHealth;
    }

    /**
     *
     * @return
     *     The fabricUri
     */
    public String getFabricUri() {
        return fabricUri;
    }

    /**
     *
     * @param fabricUri
     *     The fabricUri
     */
    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    /**
     *
     * (Required)
     *
     * @return
     *     The stackingMode
     */
    public StackingMode getStackingMode() {
        return stackingMode;
    }

    /**
     *
     * (Required)
     *
     * @param stackingMode
     *     The stackingMode
     */
    public void setStackingMode(StackingMode stackingMode) {
        this.stackingMode = stackingMode;
    }

    /**
     *
     * @return
     *     The fcoeSettings
     */
    public FcoeSettings getFcoeSettings() {
        return fcoeSettings;
    }

    /**
     *
     * @param fcoeSettings
     *     The fcoeSettings
     */
    public void setFcoeSettings(FcoeSettings fcoeSettings) {
        this.fcoeSettings = fcoeSettings;
    }

    /**
     *
     * @return
     *     The ethernetSettings
     */
    public EthernetInterconnectSettingsV2 getEthernetSettings() {
        return ethernetSettings;
    }

    /**
     *
     * @param ethernetSettings
     *     The ethernetSettings
     */
    public void setEthernetSettings(EthernetInterconnectSettingsV2 ethernetSettings) {
        this.ethernetSettings = ethernetSettings;
    }

    /**
     *
     * @return
     *     The enclosureIndexes
     */
    public List<Integer> getEnclosureIndexes() {
        return enclosureIndexes;
    }

    /**
     *
     * @param enclosureIndexes
     *     The enclosureIndexes
     */
    public void setEnclosureIndexes(List<Integer> enclosureIndexes) {
        this.enclosureIndexes = enclosureIndexes;
    }

    /**
     *
     * @return
     *     The interconnectBaySet
     */
    public Integer getInterconnectBaySet() {
        return interconnectBaySet;
    }

    /**
     *
     * @param interconnectBaySet
     *     The interconnectBaySet
     */
    public void setInterconnectBaySet(Integer interconnectBaySet) {
        this.interconnectBaySet = interconnectBaySet;
    }

    /**
     *
     * @return
     *     The redundancyType
     */
    public LogicalInterconnectGroups.RedundancyType getRedundancyType() {
        return redundancyType;
    }

    /**
     *
     * @param redundancyType
     *     The redundancyType
     */
    public void setRedundancyType(LogicalInterconnectGroups.RedundancyType redundancyType) {
        this.redundancyType = redundancyType;
    }

    /**
     *
     * (Required)
     *
     * @return
     *     The interconnectMapTemplate
     */
    public InterconnectMapTemplate getInterconnectMapTemplate() {
        return interconnectMapTemplate;
    }

    /**
     *
     * (Required)
     *
     * @param interconnectMapTemplate
     *     The interconnectMapTemplate
     */
    public void setInterconnectMapTemplate(InterconnectMapTemplate interconnectMapTemplate) {
        this.interconnectMapTemplate = interconnectMapTemplate;
    }

    /**
     *
     * @return
     *     The internalNetworkUris
     */
    public List<String> getInternalNetworkUris() {
        return internalNetworkUris;
    }

    /**
     *
     * @param internalNetworkUris
     *     The internalNetworkUris
     */
    public void setInternalNetworkUris(List<String> internalNetworkUris) {
        this.internalNetworkUris = internalNetworkUris;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uplinkSets)
                .append(telemetryConfiguration)
                .append(snmpConfiguration)
                .append(qosConfiguration)
                .append(enclosureType)
                .append(stackingHealth)
                .append(fabricUri)
                .append(stackingMode)
                .append(fcoeSettings)
                .append(ethernetSettings)
                .append(enclosureIndexes)
                .append(interconnectBaySet)
                .append(redundancyType)
                .append(interconnectMapTemplate)
                .append(internalNetworkUris)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalInterconnectGroups) == false) {
            return false;
        }
        LogicalInterconnectGroups rhs = ((LogicalInterconnectGroups) other);
        return new EqualsBuilder()
                .append(uplinkSets, rhs.uplinkSets)
                .append(telemetryConfiguration, rhs.telemetryConfiguration)
                .append(snmpConfiguration, rhs.snmpConfiguration)
                .append(qosConfiguration, rhs.qosConfiguration)
                .append(enclosureType, rhs.enclosureType)
                .append(stackingHealth, rhs.stackingHealth)
                .append(fabricUri, rhs.fabricUri)
                .append(stackingMode, rhs.stackingMode)
                .append(fcoeSettings, rhs.fcoeSettings)
                .append(ethernetSettings, rhs.ethernetSettings)
                .append(enclosureIndexes, rhs.enclosureIndexes)
                .append(interconnectBaySet, rhs.interconnectBaySet)
                .append(redundancyType, rhs.redundancyType)
                .append(interconnectMapTemplate, rhs.interconnectMapTemplate)
                .append(internalNetworkUris, rhs.internalNetworkUris)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    public static enum EnclosureType {

        Unknown("Unknown"),
        NotApplicable("NotApplicable"),
        C7000("C7000"),
        SY12000("SY12000");
        private final String value;
        private final static Map<String, LogicalInterconnectGroups.EnclosureType> CONSTANTS = new HashMap<String, LogicalInterconnectGroups.EnclosureType>();

        static {
            for (LogicalInterconnectGroups.EnclosureType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private EnclosureType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static LogicalInterconnectGroups.EnclosureType fromValue(String value) {
            LogicalInterconnectGroups.EnclosureType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum RedundancyType {

        HighlyAvailable("HighlyAvailable"),
        Redundant("Redundant"),
        NonRedundantASide("NonRedundantASide"),
        NonRedundantBSide("NonRedundantBSide");
        private final String value;
        private final static Map<String, LogicalInterconnectGroups.RedundancyType> CONSTANTS = new HashMap<String, LogicalInterconnectGroups.RedundancyType>();

        static {
            for (LogicalInterconnectGroups.RedundancyType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private RedundancyType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static LogicalInterconnectGroups.RedundancyType fromValue(String value) {
            LogicalInterconnectGroups.RedundancyType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum StackingHealth {

        Unknown("Unknown"),
        Connected("Connected"),
        BiConnected("BiConnected"),
        Disconnected("Disconnected"),
        DisconnectedShutdown("DisconnectedShutdown"),
        NotApplicable("NotApplicable");
        private final String value;
        private final static Map<String, LogicalInterconnectGroups.StackingHealth> CONSTANTS = new HashMap<String, LogicalInterconnectGroups.StackingHealth>();

        static {
            for (LogicalInterconnectGroups.StackingHealth c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private StackingHealth(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static LogicalInterconnectGroups.StackingHealth fromValue(String value) {
            LogicalInterconnectGroups.StackingHealth constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
