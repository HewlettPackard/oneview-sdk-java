/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class SanResponse extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer aliasCount;
    private String aliasesUri;
    private String deviceManagerName;
    private String deviceManagerUri;
    private String deviceManagersUri;
    private String displayName;
    private String endpointsUri;
    private Boolean imported;
    private Boolean isInternal;
    private Integer portsFreeCount;
    private Integer portsTotalCount;
    private String portsUri;
    private Integer portsUsedCount;
    private String principalSwitch;
    private String providerName;
    private List<Property> publicAttributes = new ArrayList<Property>();
    private RefreshState refreshState;
    private String routingId;
    private SanPolicy sanPolicy;
    private Integer sanServicesCount;
    private String sanServicesUri;
    private String sanType;
    private Integer switchCount;
    private String switchesUri;
    private Integer zoneCount;
    private String zonesUri;
    private ZoningState zoningState;

    /**
     * 
     * @return The aliasCount
     */
    public Integer getAliasCount() {
        return aliasCount;
    }

    /**
     * 
     * @param aliasCount
     *            The aliasCount
     */
    public void setAliasCount(Integer aliasCount) {
        this.aliasCount = aliasCount;
    }

    /**
     * 
     * @return The aliasesUri
     */
    public String getAliasesUri() {
        return aliasesUri;
    }

    /**
     * 
     * @param aliasesUri
     *            The aliasesUri
     */
    public void setAliasesUri(String aliasesUri) {
        this.aliasesUri = aliasesUri;
    }

    /**
     * 
     * @return The deviceManagerName
     */
    public String getDeviceManagerName() {
        return deviceManagerName;
    }

    /**
     * 
     * @param deviceManagerName
     *            The deviceManagerName
     */
    public void setDeviceManagerName(String deviceManagerName) {
        this.deviceManagerName = deviceManagerName;
    }

    /**
     * 
     * @return The deviceManagerUri
     */
    public String getDeviceManagerUri() {
        return deviceManagerUri;
    }

    /**
     * 
     * @param deviceManagerUri
     *            The deviceManagerUri
     */
    public void setDeviceManagerUri(String deviceManagerUri) {
        this.deviceManagerUri = deviceManagerUri;
    }

    /**
     * 
     * @return The deviceManagersUri
     */
    public String getDeviceManagersUri() {
        return deviceManagersUri;
    }

    /**
     * 
     * @param deviceManagersUri
     *            The deviceManagersUri
     */
    public void setDeviceManagersUri(String deviceManagersUri) {
        this.deviceManagersUri = deviceManagersUri;
    }

    /**
     * 
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayName
     *            The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 
     * @return The endpointsUri
     */
    public String getEndpointsUri() {
        return endpointsUri;
    }

    /**
     * 
     * @param endpointsUri
     *            The endpointsUri
     */
    public void setEndpointsUri(String endpointsUri) {
        this.endpointsUri = endpointsUri;
    }

    /**
     * 
     * @return The imported
     */
    public Boolean getImported() {
        return imported;
    }

    /**
     * 
     * @param imported
     *            The imported
     */
    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    /**
     * 
     * @return The isInternal
     */
    public Boolean getIsInternal() {
        return isInternal;
    }

    /**
     * 
     * @param isInternal
     *            The isInternal
     */
    public void setIsInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    /**
     * 
     * @return The portsFreeCount
     */
    public Integer getPortsFreeCount() {
        return portsFreeCount;
    }

    /**
     * 
     * @param portsFreeCount
     *            The portsFreeCount
     */
    public void setPortsFreeCount(Integer portsFreeCount) {
        this.portsFreeCount = portsFreeCount;
    }

    /**
     * 
     * @return The portsTotalCount
     */
    public Integer getPortsTotalCount() {
        return portsTotalCount;
    }

    /**
     * 
     * @param portsTotalCount
     *            The portsTotalCount
     */
    public void setPortsTotalCount(Integer portsTotalCount) {
        this.portsTotalCount = portsTotalCount;
    }

    /**
     * 
     * @return The portsUri
     */
    public String getPortsUri() {
        return portsUri;
    }

    /**
     * 
     * @param portsUri
     *            The portsUri
     */
    public void setPortsUri(String portsUri) {
        this.portsUri = portsUri;
    }

    /**
     * 
     * @return The portsUsedCount
     */
    public Integer getPortsUsedCount() {
        return portsUsedCount;
    }

    /**
     * 
     * @param portsUsedCount
     *            The portsUsedCount
     */
    public void setPortsUsedCount(Integer portsUsedCount) {
        this.portsUsedCount = portsUsedCount;
    }

    /**
     * 
     * @return The principalSwitch
     */
    public String getPrincipalSwitch() {
        return principalSwitch;
    }

    /**
     * 
     * @param principalSwitch
     *            The principalSwitch
     */
    public void setPrincipalSwitch(String principalSwitch) {
        this.principalSwitch = principalSwitch;
    }

    /**
     * 
     * @return The providerName
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * 
     * @param providerName
     *            The providerName
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * 
     * @return The publicAttributes
     */
    public List<Property> getPublicAttributes() {
        return publicAttributes;
    }

    /**
     * 
     * @param publicAttributes
     *            The publicAttributes
     */
    public void setPublicAttributes(List<Property> publicAttributes) {
        this.publicAttributes = publicAttributes;
    }

    /**
     * 
     * @return The refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * 
     * @param refreshState
     *            The refreshState
     */
    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * 
     * @return The routingId
     */
    public String getRoutingId() {
        return routingId;
    }

    /**
     * 
     * @param routingId
     *            The routingId
     */
    public void setRoutingId(String routingId) {
        this.routingId = routingId;
    }

    /**
     * 
     * @return The sanPolicy
     */
    public SanPolicy getSanPolicy() {
        return sanPolicy;
    }

    /**
     * 
     * @param sanPolicy
     *            The sanPolicy
     */
    public void setSanPolicy(SanPolicy sanPolicy) {
        this.sanPolicy = sanPolicy;
    }

    /**
     * 
     * @return The sanServicesCount
     */
    public Integer getSanServicesCount() {
        return sanServicesCount;
    }

    /**
     * 
     * @param sanServicesCount
     *            The sanServicesCount
     */
    public void setSanServicesCount(Integer sanServicesCount) {
        this.sanServicesCount = sanServicesCount;
    }

    /**
     * 
     * @return The sanServicesUri
     */
    public String getSanServicesUri() {
        return sanServicesUri;
    }

    /**
     * 
     * @param sanServicesUri
     *            The sanServicesUri
     */
    public void setSanServicesUri(String sanServicesUri) {
        this.sanServicesUri = sanServicesUri;
    }

    /**
     * 
     * @return The sanType
     */
    public String getSanType() {
        return sanType;
    }

    /**
     * 
     * @param sanType
     *            The sanType
     */
    public void setSanType(String sanType) {
        this.sanType = sanType;
    }

    /**
     * 
     * @return The switchCount
     */
    public Integer getSwitchCount() {
        return switchCount;
    }

    /**
     * 
     * @param switchCount
     *            The switchCount
     */
    public void setSwitchCount(Integer switchCount) {
        this.switchCount = switchCount;
    }

    /**
     * 
     * @return The switchesUri
     */
    public String getSwitchesUri() {
        return switchesUri;
    }

    /**
     * 
     * @param switchesUri
     *            The switchesUri
     */
    public void setSwitchesUri(String switchesUri) {
        this.switchesUri = switchesUri;
    }

    /**
     * 
     * @return The zoneCount
     */
    public Integer getZoneCount() {
        return zoneCount;
    }

    /**
     * 
     * @param zoneCount
     *            The zoneCount
     */
    public void setZoneCount(Integer zoneCount) {
        this.zoneCount = zoneCount;
    }

    /**
     * 
     * @return The zonesUri
     */
    public String getZonesUri() {
        return zonesUri;
    }

    /**
     * 
     * @param zonesUri
     *            The zonesUri
     */
    public void setZonesUri(String zonesUri) {
        this.zonesUri = zonesUri;
    }

    /**
     * 
     * @return The zoningState
     */
    public ZoningState getZoningState() {
        return zoningState;
    }

    /**
     * 
     * @param zoningState
     *            The zoningState
     */
    public void setZoningState(ZoningState zoningState) {
        this.zoningState = zoningState;
    }

}
