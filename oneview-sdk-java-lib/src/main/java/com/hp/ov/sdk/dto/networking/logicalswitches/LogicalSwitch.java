/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.dto.networking.logicalswitches;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.Compliance;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.StackingHealth;

public final class LogicalSwitch extends BaseModelResource {

    private static final long serialVersionUID = -7639145707975657832L;

    private Compliance consistencyStatus;
    private String fabricUri;
    private LogicalSwitchDomainInfo logicalSwitchDomainInfo;
    private String logicalSwitchGroupUri;
    @Since(300)
    private ManagementLevel managementLevel;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private SnmpConfiguration snmpConfiguration;
    private StackingHealth stackingHealth;
    private List<SwitchCredentialConfiguration> switchCredentialConfiguration = new ArrayList<>();
    private Integer switchCredentialCount;
    private SwitchMap switchMap;
    private List<StackingGroup> switchStackingInfo = new ArrayList<>();
    private List<String> switchUris;

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
     * @return the logicalSwitchDomainInfo
     */
    public LogicalSwitchDomainInfo getLogicalSwitchDomainInfo() {
        return logicalSwitchDomainInfo;
    }

    /**
     * @param logicalSwitchDomainInfo the logicalSwitchDomainInfo to set
     */
    public void setLogicalSwitchDomainInfo(LogicalSwitchDomainInfo logicalSwitchDomainInfo) {
        this.logicalSwitchDomainInfo = logicalSwitchDomainInfo;
    }

    /**
     * @return the logicalSwitchGroupUri
     */
    public String getLogicalSwitchGroupUri() {
        return logicalSwitchGroupUri;
    }

    /**
     * @param logicalSwitchGroupUri the logicalSwitchGroupUri to set
     */
    public void setLogicalSwitchGroupUri(String logicalSwitchGroupUri) {
        this.logicalSwitchGroupUri = logicalSwitchGroupUri;
    }

    /**
     * @return the managementLevel
     */
    public ManagementLevel getManagementLevel() {
        return managementLevel;
    }

    /**
     * @param managementLevel the managementLevel to set
     */
    public void setManagementLevel(ManagementLevel managementLevel) {
        this.managementLevel = managementLevel;
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
     * @return the switchCredentialConfiguration
     */
    public List<SwitchCredentialConfiguration> getSwitchCredentialConfiguration() {
        return switchCredentialConfiguration;
    }

    /**
     * @param switchCredentialConfiguration the switchCredentialConfiguration to set
     */
    public void setSwitchCredentialConfiguration(List<SwitchCredentialConfiguration> switchCredentialConfiguration) {
        this.switchCredentialConfiguration = switchCredentialConfiguration;
    }

    /**
     * @return the switchCredentialCount
     */
    public Integer getSwitchCredentialCount() {
        return switchCredentialCount;
    }

    /**
     * @param switchCredentialCount the switchCredentialCount to set
     */
    public void setSwitchCredentialCount(Integer switchCredentialCount) {
        this.switchCredentialCount = switchCredentialCount;
    }

    /**
     * @return the switchMap
     */
    public SwitchMap getSwitchMap() {
        return switchMap;
    }

    /**
     * @param switchMap the switchMap to set
     */
    public void setSwitchMap(SwitchMap switchMap) {
        this.switchMap = switchMap;
    }

    /**
     * @return the switchStackingInfo
     */
    public List<StackingGroup> getSwitchStackingInfo() {
        return switchStackingInfo;
    }

    /**
     * @param switchStackingInfo the switchStackingInfo to set
     */
    public void setSwitchStackingInfo(List<StackingGroup> switchStackingInfo) {
        this.switchStackingInfo = switchStackingInfo;
    }

    /**
     * @return the switchUris
     */
    public List<String> getSwitchUris() {
        return switchUris;
    }

    /**
     * @param switchUris the switchUris to set
     */
    public void setSwitchUris(List<String> switchUris) {
        this.switchUris = switchUris;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof LogicalSwitch);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof LogicalSwitch) {
            LogicalSwitch that = (LogicalSwitch) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(consistencyStatus, that.consistencyStatus)
                    .append(fabricUri, that.fabricUri)
                    .append(logicalSwitchDomainInfo, that.logicalSwitchDomainInfo)
                    .append(logicalSwitchGroupUri, that.logicalSwitchGroupUri)
                    .append(managementLevel, that.managementLevel)
                    .append(scopeUris, that.scopeUris)
                    .append(snmpConfiguration, that.snmpConfiguration)
                    .append(stackingHealth, that.stackingHealth)
                    .append(switchCredentialConfiguration, that.switchCredentialConfiguration)
                    .append(switchCredentialCount, that.switchCredentialCount)
                    .append(switchMap, that.switchMap)
                    .append(switchStackingInfo, that.switchStackingInfo)
                    .append(switchUris, that.switchUris)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(consistencyStatus)
                .append(fabricUri)
                .append(logicalSwitchDomainInfo)
                .append(logicalSwitchGroupUri)
                .append(managementLevel)
                .append(scopeUris)
                .append(snmpConfiguration)
                .append(stackingHealth)
                .append(switchCredentialConfiguration)
                .append(switchCredentialCount)
                .append(switchMap)
                .append(switchStackingInfo)
                .append(switchUris)
                .toHashCode();
    }

}
