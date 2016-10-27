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
package com.hp.ov.sdk.dto.networking.logicalinterconnectgroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.StackingMode;
import com.hp.ov.sdk.dto.networking.EnclosureType;
import com.hp.ov.sdk.dto.networking.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.StackingHealth;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;

/**
 * The LogicalInterconnectGroups data transfer object (DTO) contains the
 * information used to represent LogicalInterconnectGroups in the system. The
 * LogicalInterconnectGroups Dto is a generic specification of a set of uplink
 * set that is used to channel the data(both vlan and fiber channel network) and
 * the type of interconnect that is used in the virtual connect to aid in the
 * purpose.It is passed in to the add/update logicalInterconnectGroups REST api,
 * as well as the add/update logicalInterconnectGroups through java client api.
 */
public class LogicalInterconnectGroup extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    @Since(200)
    private List<Integer> enclosureIndexes = new ArrayList<Integer>();
    @Since(200)
    private EnclosureType enclosureType;
    private EthernetInterconnectSettingsV2 ethernetSettings;
    @Since(200)
    private String fabricUri;
    @Until(299)
    private FcoeSettings fcoeSettings;
    private Integer interconnectBaySet;
    private InterconnectMapTemplate interconnectMapTemplate;
    @Since(200)
    private List<String> internalNetworkUris = new ArrayList<String>();
    @Since(200)
    private QosConfiguration qosConfiguration;
    @Since(200)
    private LogicalInterconnectGroup.RedundancyType redundancyType;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private SnmpConfiguration snmpConfiguration;
    private StackingHealth stackingHealth;
    private StackingMode stackingMode;
    private TelemetryConfiguration telemetryConfiguration;
    private List<UplinkSetGroup> uplinkSets = new ArrayList<UplinkSetGroup>();

    /**
     * @return the enclosureIndexes
     */
    public List<Integer> getEnclosureIndexes() {
        return enclosureIndexes;
    }

    /**
     * @param enclosureIndexes the enclosureIndexes to set
     */
    public void setEnclosureIndexes(List<Integer> enclosureIndexes) {
        this.enclosureIndexes = enclosureIndexes;
    }

    /**
     * @return the enclosureType
     */
    public EnclosureType getEnclosureType() {
        return enclosureType;
    }

    /**
     * @param enclosureType the enclosureType to set
     */
    public void setEnclosureType(EnclosureType enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     * @return the ethernetSettings
     */
    public EthernetInterconnectSettingsV2 getEthernetSettings() {
        return ethernetSettings;
    }

    /**
     * @param ethernetSettings the ethernetSettings to set
     */
    public void setEthernetSettings(EthernetInterconnectSettingsV2 ethernetSettings) {
        this.ethernetSettings = ethernetSettings;
    }

    /**
     * @return the fabricUri
     */
    public String getFabricUri() {
        return fabricUri;
    }

    /**
     * @param fabricUri the fabricUri to set
     */
    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    /**
     * @return the fcoeSettings
     */
    public FcoeSettings getFcoeSettings() {
        return fcoeSettings;
    }

    /**
     * @param fcoeSettings the fcoeSettings to set
     */
    public void setFcoeSettings(FcoeSettings fcoeSettings) {
        this.fcoeSettings = fcoeSettings;
    }

    /**
     * @return the interconnectBaySet
     */
    public Integer getInterconnectBaySet() {
        return interconnectBaySet;
    }

    /**
     * @param interconnectBaySet the interconnectBaySet to set
     */
    public void setInterconnectBaySet(Integer interconnectBaySet) {
        this.interconnectBaySet = interconnectBaySet;
    }

    /**
     * @return the interconnectMapTemplate
     */
    public InterconnectMapTemplate getInterconnectMapTemplate() {
        return interconnectMapTemplate;
    }

    /**
     * @param interconnectMapTemplate the interconnectMapTemplate to set
     */
    public void setInterconnectMapTemplate(InterconnectMapTemplate interconnectMapTemplate) {
        this.interconnectMapTemplate = interconnectMapTemplate;
    }

    /**
     * @return the internalNetworkUris
     */
    public List<String> getInternalNetworkUris() {
        return internalNetworkUris;
    }

    /**
     * @param internalNetworkUris the internalNetworkUris to set
     */
    public void setInternalNetworkUris(List<String> internalNetworkUris) {
        this.internalNetworkUris = internalNetworkUris;
    }

    /**
     * @return the qosConfiguration
     */
    public QosConfiguration getQosConfiguration() {
        return qosConfiguration;
    }

    /**
     * @param qosConfiguration the qosConfiguration to set
     */
    public void setQosConfiguration(QosConfiguration qosConfiguration) {
        this.qosConfiguration = qosConfiguration;
    }

    /**
     * @return the redundancyType
     */
    public LogicalInterconnectGroup.RedundancyType getRedundancyType() {
        return redundancyType;
    }

    /**
     * @param redundancyType the redundancyType to set
     */
    public void setRedundancyType(LogicalInterconnectGroup.RedundancyType redundancyType) {
        this.redundancyType = redundancyType;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    /**
     * @return the snmpConfiguration
     */
    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    /**
     * @param snmpConfiguration the snmpConfiguration to set
     */
    public void setSnmpConfiguration(SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     * @return the stackingHealth
     */
    public StackingHealth getStackingHealth() {
        return stackingHealth;
    }

    /**
     * @param stackingHealth the stackingHealth to set
     */
    public void setStackingHealth(StackingHealth stackingHealth) {
        this.stackingHealth = stackingHealth;
    }

    /**
     * @return the stackingMode
     */
    public StackingMode getStackingMode() {
        return stackingMode;
    }

    /**
     * @param stackingMode the stackingMode to set
     */
    public void setStackingMode(StackingMode stackingMode) {
        this.stackingMode = stackingMode;
    }

    /**
     * @return the telemetryConfiguration
     */
    public TelemetryConfiguration getTelemetryConfiguration() {
        return telemetryConfiguration;
    }

    /**
     * @param telemetryConfiguration the telemetryConfiguration to set
     */
    public void setTelemetryConfiguration(TelemetryConfiguration telemetryConfiguration) {
        this.telemetryConfiguration = telemetryConfiguration;
    }

    /**
     * @return the uplinkSets
     */
    public List<UplinkSetGroup> getUplinkSets() {
        return uplinkSets;
    }

    /**
     * @param uplinkSets the uplinkSets to set
     */
    public void setUplinkSets(List<UplinkSetGroup> uplinkSets) {
        this.uplinkSets = uplinkSets;
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
                .append(scopeUris)
                .append(interconnectMapTemplate)
                .append(internalNetworkUris)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof LogicalInterconnectGroup);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalInterconnectGroup) == false) {
            return false;
        }
        LogicalInterconnectGroup rhs = ((LogicalInterconnectGroup) other);
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
                .append(scopeUris, rhs.scopeUris)
                .append(interconnectMapTemplate, rhs.interconnectMapTemplate)
                .append(internalNetworkUris, rhs.internalNetworkUris)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    public static enum RedundancyType {

        HighlyAvailable("HighlyAvailable"),
        Redundant("Redundant"),
        NonRedundantASide("NonRedundantASide"),
        NonRedundantBSide("NonRedundantBSide");
        private final String value;
        private final static Map<String, LogicalInterconnectGroup.RedundancyType> CONSTANTS = new HashMap<String, LogicalInterconnectGroup.RedundancyType>();

        static {
            for (LogicalInterconnectGroup.RedundancyType c: values()) {
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

        public static LogicalInterconnectGroup.RedundancyType fromValue(String value) {
            LogicalInterconnectGroup.RedundancyType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
