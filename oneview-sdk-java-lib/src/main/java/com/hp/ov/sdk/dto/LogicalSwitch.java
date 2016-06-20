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

package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.generated.SnmpConfiguration;

public final class LogicalSwitch extends BaseModelResource {

    private static final long serialVersionUID = -7639145707975657832L;

    private Compliance consistencyStatus;
    private String fabricUri;
    private LogicalSwitchDomainInfo logicalSwitchDomainInfo;
    private String logicalSwitchGroupUri;
    private SnmpConfiguration snmpConfiguration;
    private StackingHealth stackingHealth;
    private List<SwitchCredentialConfiguration> switchCredentialConfiguration = new ArrayList<>();
    private Integer switchCredentialCount;
    private SwitchMap switchMap;
    private List<StackingGroup> switchStackingInfo = new ArrayList<>();
    private List<String> switchUris;

    public Compliance getConsistencyStatus() {
        return consistencyStatus;
    }

    public void setConsistencyStatus(Compliance consistencyStatus) {
        this.consistencyStatus = consistencyStatus;
    }

    public String getFabricUri() {
        return fabricUri;
    }

    public void setFabricUri(String fabricUri) {
        this.fabricUri = fabricUri;
    }

    public LogicalSwitchDomainInfo getLogicalSwitchDomainInfo() {
        return logicalSwitchDomainInfo;
    }

    public void setLogicalSwitchDomainInfo(LogicalSwitchDomainInfo logicalSwitchDomainInfo) {
        this.logicalSwitchDomainInfo = logicalSwitchDomainInfo;
    }

    public String getLogicalSwitchGroupUri() {
        return logicalSwitchGroupUri;
    }

    public void setLogicalSwitchGroupUri(String logicalSwitchGroupUri) {
        this.logicalSwitchGroupUri = logicalSwitchGroupUri;
    }

    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    public void setSnmpConfiguration(SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    public StackingHealth getStackingHealth() {
        return stackingHealth;
    }

    public void setStackingHealth(StackingHealth stackingHealth) {
        this.stackingHealth = stackingHealth;
    }

    public List<SwitchCredentialConfiguration> getSwitchCredentialConfiguration() {
        return switchCredentialConfiguration;
    }

    public void setSwitchCredentialConfiguration(List<SwitchCredentialConfiguration> switchCredentialConfiguration) {
        this.switchCredentialConfiguration = switchCredentialConfiguration;
    }

    public Integer getSwitchCredentialCount() {
        return switchCredentialCount;
    }

    public void setSwitchCredentialCount(Integer switchCredentialCount) {
        this.switchCredentialCount = switchCredentialCount;
    }

    public SwitchMap getSwitchMap() {
        return switchMap;
    }

    public void setSwitchMap(SwitchMap switchMap) {
        this.switchMap = switchMap;
    }

    public List<StackingGroup> getSwitchStackingInfo() {
        return switchStackingInfo;
    }

    public void setSwitchStackingInfo(List<StackingGroup> switchStackingInfo) {
        this.switchStackingInfo = switchStackingInfo;
    }

    public List<String> getSwitchUris() {
        return switchUris;
    }

    public void setSwitchUris(List<String> switchUris) {
        this.switchUris = switchUris;
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
                .append(snmpConfiguration)
                .append(stackingHealth)
                .append(switchCredentialConfiguration)
                .append(switchCredentialCount)
                .append(switchMap)
                .append(switchStackingInfo)
                .append(switchUris)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("consistencyStatus", consistencyStatus)
                .append("fabricUri", fabricUri)
                .append("logicalSwitchDomainInfo", logicalSwitchDomainInfo)
                .append("logicalSwitchGroupUri", logicalSwitchGroupUri)
                .append("snmpConfiguration", snmpConfiguration)
                .append("stackingHealth", stackingHealth)
                .append("switchCredentialConfiguration", switchCredentialConfiguration)
                .append("switchCredentialCount", switchCredentialCount)
                .append("switchMap", switchMap)
                .append("switchStackingInfo", switchStackingInfo)
                .append("switchUris", switchUris)
                .toString();
    }

}
