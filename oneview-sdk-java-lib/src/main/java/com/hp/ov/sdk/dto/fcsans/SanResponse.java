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
package com.hp.ov.sdk.dto.fcsans;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.RefreshState;

public class SanResponse extends BaseModelResource {

    private static final long serialVersionUID = -1495921065098648747L;

    private Integer aliasCount;
    private String aliasesUri;
    @Since(200)
    private List<NetworkResponse> associatedNetworks = new ArrayList<>();
    private String deviceManagerName;
    private String deviceManagerUri;
    private String deviceManagersUri;
    private String displayName;
    private String endpointsUri;
    @Since(300)
    private String fcMap;
    private Boolean imported;
    @Since(200)
    private Boolean isActualFc;
    @Since(200)
    private Boolean isActualFcoe;
    @Since(200)
    private Boolean isExpectedFc;
    @Since(200)
    private Boolean isExpectedFcoe;
    @Since(200)
    private Boolean isFcCapable;
    @Since(200)
    private Boolean isFcoeCapable;
    private Boolean isInternal;
    private Integer portsFreeCount;
    private Integer portsTotalCount;
    private String portsUri;
    private Integer portsUsedCount;
    private String principalSwitch;
    private String providerName;
    private List<Property> publicAttributes = new ArrayList<>();
    private RefreshState refreshState;
    private String routingId;
    private SanPolicy sanPolicy;
    private Integer sanServicesCount;
    private String sanServicesUri;
    private String sanType;
    private Integer switchCount;
    private String switchesUri;
    @Since(200)
    private Integer vLanId;
    private Integer zoneCount;
    private String zonesUri;
    private ZoningState zoningState;

    /**
     * @return the aliasCount
     */
    public Integer getAliasCount() {
        return aliasCount;
    }

    /**
     * @param aliasCount the aliasCount to set
     */
    public void setAliasCount(Integer aliasCount) {
        this.aliasCount = aliasCount;
    }

    /**
     * @return the aliasesUri
     */
    public String getAliasesUri() {
        return aliasesUri;
    }

    /**
     * @param aliasesUri the aliasesUri to set
     */
    public void setAliasesUri(String aliasesUri) {
        this.aliasesUri = aliasesUri;
    }

    /**
     * @return the associatedNetworks
     */
    public List<NetworkResponse> getAssociatedNetworks() {
        return associatedNetworks;
    }

    /**
     * @param associatedNetworks the associatedNetworks to set
     */
    public void setAssociatedNetworks(List<NetworkResponse> associatedNetworks) {
        this.associatedNetworks = associatedNetworks;
    }

    /**
     * @return the deviceManagerName
     */
    public String getDeviceManagerName() {
        return deviceManagerName;
    }

    /**
     * @param deviceManagerName the deviceManagerName to set
     */
    public void setDeviceManagerName(String deviceManagerName) {
        this.deviceManagerName = deviceManagerName;
    }

    /**
     * @return the deviceManagerUri
     */
    public String getDeviceManagerUri() {
        return deviceManagerUri;
    }

    /**
     * @param deviceManagerUri the deviceManagerUri to set
     */
    public void setDeviceManagerUri(String deviceManagerUri) {
        this.deviceManagerUri = deviceManagerUri;
    }

    /**
     * @return the deviceManagersUri
     */
    public String getDeviceManagersUri() {
        return deviceManagersUri;
    }

    /**
     * @param deviceManagersUri the deviceManagersUri to set
     */
    public void setDeviceManagersUri(String deviceManagersUri) {
        this.deviceManagersUri = deviceManagersUri;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the endpointsUri
     */
    public String getEndpointsUri() {
        return endpointsUri;
    }

    /**
     * @param endpointsUri the endpointsUri to set
     */
    public void setEndpointsUri(String endpointsUri) {
        this.endpointsUri = endpointsUri;
    }

    /**
     * @return the fcMap
     */
    public String getFcMap() {
        return fcMap;
    }

    /**
     * @param fcMap the fcMap to set
     */
    public void setFcMap(String fcMap) {
        this.fcMap = fcMap;
    }

    /**
     * @return the imported
     */
    public Boolean getImported() {
        return imported;
    }

    /**
     * @param imported the imported to set
     */
    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    /**
     * @return the isActualFc
     */
    public Boolean getIsActualFc() {
        return isActualFc;
    }

    /**
     * @param isActualFc the isActualFc to set
     */
    public void setIsActualFc(Boolean isActualFc) {
        this.isActualFc = isActualFc;
    }

    /**
     * @return the isActualFcoe
     */
    public Boolean getIsActualFcoe() {
        return isActualFcoe;
    }

    /**
     * @param isActualFcoe the isActualFcoe to set
     */
    public void setIsActualFcoe(Boolean isActualFcoe) {
        this.isActualFcoe = isActualFcoe;
    }

    /**
     * @return the isExpectedFc
     */
    public Boolean getIsExpectedFc() {
        return isExpectedFc;
    }

    /**
     * @param isExpectedFc the isExpectedFc to set
     */
    public void setIsExpectedFc(Boolean isExpectedFc) {
        this.isExpectedFc = isExpectedFc;
    }

    /**
     * @return the isExpectedFcoe
     */
    public Boolean getIsExpectedFcoe() {
        return isExpectedFcoe;
    }

    /**
     * @param isExpectedFcoe the isExpectedFcoe to set
     */
    public void setIsExpectedFcoe(Boolean isExpectedFcoe) {
        this.isExpectedFcoe = isExpectedFcoe;
    }

    /**
     * @return the isFcCapable
     */
    public Boolean getIsFcCapable() {
        return isFcCapable;
    }

    /**
     * @param isFcCapable the isFcCapable to set
     */
    public void setIsFcCapable(Boolean isFcCapable) {
        this.isFcCapable = isFcCapable;
    }

    /**
     * @return the isFcoeCapable
     */
    public Boolean getIsFcoeCapable() {
        return isFcoeCapable;
    }

    /**
     * @param isFcoeCapable the isFcoeCapable to set
     */
    public void setIsFcoeCapable(Boolean isFcoeCapable) {
        this.isFcoeCapable = isFcoeCapable;
    }

    /**
     * @return the isInternal
     */
    public Boolean getIsInternal() {
        return isInternal;
    }

    /**
     * @param isInternal the isInternal to set
     */
    public void setIsInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    /**
     * @return the portsFreeCount
     */
    public Integer getPortsFreeCount() {
        return portsFreeCount;
    }

    /**
     * @param portsFreeCount the portsFreeCount to set
     */
    public void setPortsFreeCount(Integer portsFreeCount) {
        this.portsFreeCount = portsFreeCount;
    }

    /**
     * @return the portsTotalCount
     */
    public Integer getPortsTotalCount() {
        return portsTotalCount;
    }

    /**
     * @param portsTotalCount the portsTotalCount to set
     */
    public void setPortsTotalCount(Integer portsTotalCount) {
        this.portsTotalCount = portsTotalCount;
    }

    /**
     * @return the portsUri
     */
    public String getPortsUri() {
        return portsUri;
    }

    /**
     * @param portsUri the portsUri to set
     */
    public void setPortsUri(String portsUri) {
        this.portsUri = portsUri;
    }

    /**
     * @return the portsUsedCount
     */
    public Integer getPortsUsedCount() {
        return portsUsedCount;
    }

    /**
     * @param portsUsedCount the portsUsedCount to set
     */
    public void setPortsUsedCount(Integer portsUsedCount) {
        this.portsUsedCount = portsUsedCount;
    }

    /**
     * @return the principalSwitch
     */
    public String getPrincipalSwitch() {
        return principalSwitch;
    }

    /**
     * @param principalSwitch the principalSwitch to set
     */
    public void setPrincipalSwitch(String principalSwitch) {
        this.principalSwitch = principalSwitch;
    }

    /**
     * @return the providerName
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * @param providerName the providerName to set
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * @return the publicAttributes
     */
    public List<Property> getPublicAttributes() {
        return publicAttributes;
    }

    /**
     * @param publicAttributes the publicAttributes to set
     */
    public void setPublicAttributes(List<Property> publicAttributes) {
        this.publicAttributes = publicAttributes;
    }

    /**
     * @return the refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * @param refreshState the refreshState to set
     */
    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * @return the routingId
     */
    public String getRoutingId() {
        return routingId;
    }

    /**
     * @param routingId the routingId to set
     */
    public void setRoutingId(String routingId) {
        this.routingId = routingId;
    }

    /**
     * @return the sanPolicy
     */
    public SanPolicy getSanPolicy() {
        return sanPolicy;
    }

    /**
     * @param sanPolicy the sanPolicy to set
     */
    public void setSanPolicy(SanPolicy sanPolicy) {
        this.sanPolicy = sanPolicy;
    }

    /**
     * @return the sanServicesCount
     */
    public Integer getSanServicesCount() {
        return sanServicesCount;
    }

    /**
     * @param sanServicesCount the sanServicesCount to set
     */
    public void setSanServicesCount(Integer sanServicesCount) {
        this.sanServicesCount = sanServicesCount;
    }

    /**
     * @return the sanServicesUri
     */
    public String getSanServicesUri() {
        return sanServicesUri;
    }

    /**
     * @param sanServicesUri the sanServicesUri to set
     */
    public void setSanServicesUri(String sanServicesUri) {
        this.sanServicesUri = sanServicesUri;
    }

    /**
     * @return the sanType
     */
    public String getSanType() {
        return sanType;
    }

    /**
     * @param sanType the sanType to set
     */
    public void setSanType(String sanType) {
        this.sanType = sanType;
    }

    /**
     * @return the switchCount
     */
    public Integer getSwitchCount() {
        return switchCount;
    }

    /**
     * @param switchCount the switchCount to set
     */
    public void setSwitchCount(Integer switchCount) {
        this.switchCount = switchCount;
    }

    /**
     * @return the switchesUri
     */
    public String getSwitchesUri() {
        return switchesUri;
    }

    /**
     * @param switchesUri the switchesUri to set
     */
    public void setSwitchesUri(String switchesUri) {
        this.switchesUri = switchesUri;
    }

    /**
     * @return the vLanId
     */
    public Integer getvLanId() {
        return vLanId;
    }

    /**
     * @param vLanId the vLanId to set
     */
    public void setvLanId(Integer vLanId) {
        this.vLanId = vLanId;
    }

    /**
     * @return the zoneCount
     */
    public Integer getZoneCount() {
        return zoneCount;
    }

    /**
     * @param zoneCount the zoneCount to set
     */
    public void setZoneCount(Integer zoneCount) {
        this.zoneCount = zoneCount;
    }

    /**
     * @return the zonesUri
     */
    public String getZonesUri() {
        return zonesUri;
    }

    /**
     * @param zonesUri the zonesUri to set
     */
    public void setZonesUri(String zonesUri) {
        this.zonesUri = zonesUri;
    }

    /**
     * @return the zoningState
     */
    public ZoningState getZoningState() {
        return zoningState;
    }

    /**
     * @param zoningState the zoningState to set
     */
    public void setZoningState(ZoningState zoningState) {
        this.zoningState = zoningState;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof SanResponse) {
            SanResponse that = (SanResponse) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(aliasCount, that.aliasCount)
                    .append(aliasesUri, that.aliasesUri)
                    .append(associatedNetworks, that.associatedNetworks)
                    .append(deviceManagerName, that.deviceManagerName)
                    .append(deviceManagersUri, that.deviceManagersUri)
                    .append(deviceManagerUri, that.deviceManagerUri)
                    .append(displayName, that.displayName)
                    .append(endpointsUri, that.endpointsUri)
                    .append(imported, that.imported)
                    .append(fcMap, that.fcMap)
                    .append(isActualFc, that.isActualFc)
                    .append(isActualFcoe, that.isActualFcoe)
                    .append(isExpectedFc, that.isExpectedFc)
                    .append(isExpectedFcoe, that.isExpectedFcoe)
                    .append(isFcCapable, that.isFcCapable)
                    .append(isFcoeCapable, that.isFcoeCapable)
                    .append(isInternal, that.isInternal)
                    .append(portsFreeCount, that.portsFreeCount)
                    .append(portsTotalCount, that.portsTotalCount)
                    .append(portsUri, that.portsUri)
                    .append(portsUsedCount, that.portsUsedCount)
                    .append(principalSwitch, that.principalSwitch)
                    .append(providerName, that.providerName)
                    .append(publicAttributes, that.publicAttributes)
                    .append(refreshState, that.refreshState)
                    .append(routingId, that.routingId)
                    .append(sanPolicy, that.sanPolicy)
                    .append(sanServicesCount, that.sanServicesCount)
                    .append(sanServicesUri, that.sanServicesUri)
                    .append(sanType, that.sanType)
                    .append(switchCount, that.switchCount)
                    .append(switchesUri, that.switchesUri)
                    .append(vLanId, that.vLanId)
                    .append(zoneCount, that.zoneCount)
                    .append(zonesUri, that.zonesUri)
                    .append(zoningState, that.zoningState)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(aliasCount)
                .append(aliasesUri)
                .append(associatedNetworks)
                .append(deviceManagerName)
                .append(deviceManagersUri)
                .append(deviceManagerUri)
                .append(displayName)
                .append(endpointsUri)
                .append(imported)
                .append(fcMap)
                .append(isActualFc)
                .append(isActualFcoe)
                .append(isExpectedFc)
                .append(isExpectedFcoe)
                .append(isFcCapable)
                .append(isFcoeCapable)
                .append(isInternal)
                .append(portsFreeCount)
                .append(portsTotalCount)
                .append(portsUri)
                .append(portsUsedCount)
                .append(principalSwitch)
                .append(providerName)
                .append(publicAttributes)
                .append(refreshState)
                .append(routingId)
                .append(sanPolicy)
                .append(sanServicesCount)
                .append(sanServicesUri)
                .append(sanType)
                .append(switchCount)
                .append(switchesUri)
                .append(vLanId)
                .append(zoneCount)
                .append(zonesUri)
                .append(zoningState)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
