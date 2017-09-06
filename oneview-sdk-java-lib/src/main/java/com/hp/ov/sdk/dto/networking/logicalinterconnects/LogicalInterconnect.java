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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.Compliance;
import com.hp.ov.sdk.dto.networking.EnclosureType;
import com.hp.ov.sdk.dto.networking.EthernetInterconnectSettings;
import com.hp.ov.sdk.dto.networking.IcmLicensesDto;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.StackingHealth;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;

/**
 * The LogicalInterconnects data transfer object (DTO) contains the information
 * used to represent LogicalInterconnects in the system. The
 * LogicalInterconnects Dto specifies the set of uplink set that is used to
 * channel the data(both vlan and fiber channel network) and the type of
 * interconnect that is used in the virtual connect to aid in the purpose. The
 * dto is used to add/update interconnects at a location and also in generating
 * support dumps. It is passed in to the update logicalInterconnects REST api,
 * as well as the update logicalInterconnects through java client api.
 */
public class LogicalInterconnect extends BaseModelResource {

    private static final long serialVersionUID = 1L;


    private Compliance consistencyStatus;
    private String domainUri;
    @Since(200)
    private EnclosureType enclosureType;
    private List<String> enclosureUris = new ArrayList<String>();
    private EthernetInterconnectSettings ethernetSettings;
    @Since(200)
    private FcoeInterconnectSettings fcoeSettings;
    @Since(300)
    private IcmLicensesDto icmLicenses;
    private InterconnectMap interconnectMap;
    private List<String> interconnects = new ArrayList<String>();
    private String logicalInterconnectGroupUri;
    private PortMonitor portMonitor;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
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
     * @param consistencyStatus the consistencyStatus to set
     */
    public void setConsistencyStatus(Compliance consistencyStatus) {
        this.consistencyStatus = consistencyStatus;
    }

    /**
     * @return the domainUri
     */
    public String getDomainUri() {
        return domainUri;
    }

    /**
     * @param domainUri the domainUri to set
     */
    public void setDomainUri(String domainUri) {
        this.domainUri = domainUri;
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
     * @return the enclosureUris
     */
    public List<String> getEnclosureUris() {
        return enclosureUris;
    }

    /**
     * @param enclosureUris the enclosureUris to set
     */
    public void setEnclosureUris(List<String> enclosureUris) {
        this.enclosureUris = enclosureUris;
    }

    /**
     * @return the ethernetSettings
     */
    public EthernetInterconnectSettings getEthernetSettings() {
        return ethernetSettings;
    }

    /**
     * @param ethernetSettings the ethernetSettings to set
     */
    public void setEthernetSettings(EthernetInterconnectSettings ethernetSettings) {
        this.ethernetSettings = ethernetSettings;
    }

    /**
     * @return the fcoeSettings
     */
    public FcoeInterconnectSettings getFcoeSettings() {
        return fcoeSettings;
    }

    /**
     * @param fcoeSettings the fcoeSettings to set
     */
    public void setFcoeSettings(FcoeInterconnectSettings fcoeSettings) {
        this.fcoeSettings = fcoeSettings;
    }

    /**
     * @return the icmLicenses
     */
    public IcmLicensesDto getIcmLicenses() {
        return icmLicenses;
    }

    /**
     * @param icmLicenses the icmLicenses to set
     */
    public void setIcmLicenses(IcmLicensesDto icmLicenses) {
        this.icmLicenses = icmLicenses;
    }

    /**
     * @return the interconnectMap
     */
    public InterconnectMap getInterconnectMap() {
        return interconnectMap;
    }

    /**
     * @param interconnectMap the interconnectMap to set
     */
    public void setInterconnectMap(InterconnectMap interconnectMap) {
        this.interconnectMap = interconnectMap;
    }

    /**
     * @return the interconnects
     */
    public List<String> getInterconnects() {
        return interconnects;
    }

    /**
     * @param interconnects the interconnects to set
     */
    public void setInterconnects(List<String> interconnects) {
        this.interconnects = interconnects;
    }

    /**
     * @return the logicalInterconnectGroupUri
     */
    public String getLogicalInterconnectGroupUri() {
        return logicalInterconnectGroupUri;
    }

    /**
     * @param logicalInterconnectGroupUri the logicalInterconnectGroupUri to set
     */
    public void setLogicalInterconnectGroupUri(String logicalInterconnectGroupUri) {
        this.logicalInterconnectGroupUri = logicalInterconnectGroupUri;
    }

    /**
     * @return the portMonitor
     */
    public PortMonitor getPortMonitor() {
        return portMonitor;
    }

    /**
     * @param portMonitor the portMonitor to set
     */
    public void setPortMonitor(PortMonitor portMonitor) {
        this.portMonitor = portMonitor;
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

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalInterconnect) == false) {
            return false;
        }
        final LogicalInterconnect rhs = ((LogicalInterconnect) other);
        return new EqualsBuilder()
                .append(consistencyStatus, rhs.consistencyStatus)
                .append(domainUri, rhs.domainUri)
                .append(enclosureType, rhs.enclosureType)
                .append(enclosureUris, rhs.enclosureUris)
                .append(ethernetSettings, rhs.ethernetSettings)
                .append(fcoeSettings, rhs.fcoeSettings)
                .append(icmLicenses, rhs.icmLicenses)
                .append(interconnectMap, rhs.interconnectMap)
                .append(interconnects, rhs.interconnects)
                .append(logicalInterconnectGroupUri, rhs.logicalInterconnectGroupUri)
                .append(portMonitor, rhs.portMonitor)
                .append(scopeUris, rhs.scopeUris)
                .append(snmpConfiguration, rhs.snmpConfiguration)
                .append(stackingHealth, rhs.stackingHealth)
                .append(telemetryConfiguration, rhs.telemetryConfiguration)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(consistencyStatus)
                .append(domainUri)
                .append(enclosureType)
                .append(enclosureUris)
                .append(ethernetSettings)
                .append(fcoeSettings)
                .append(icmLicenses)
                .append(interconnectMap)
                .append(interconnects)
                .append(logicalInterconnectGroupUri)
                .append(portMonitor)
                .append(scopeUris)
                .append(snmpConfiguration)
                .append(stackingHealth)
                .append(telemetryConfiguration)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
