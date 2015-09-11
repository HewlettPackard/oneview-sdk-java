/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class DeviceManagerResponse extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<Property> connectionInfo = new ArrayList<Property>();
    private String deviceManagerUrl;
    private String deviceManagerVersion;
    private Boolean isInternal;
    private String managedSansUri;
    private String providerDisplayName;
    private String providerUri;
    private RefreshState refreshState;
    private String unimportedSansUri;

    /**
     * 
     * @return The connectionInfo
     */
    public List<Property> getConnectionInfo() {
        return connectionInfo;
    }

    /**
     * 
     * @param connectionInfo
     *            The connectionInfo
     */
    public void setConnectionInfo(List<Property> connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    /**
     * 
     * @return The deviceManagerUrl
     */
    public String getDeviceManagerUrl() {
        return deviceManagerUrl;
    }

    /**
     * 
     * @param deviceManagerUrl
     *            The deviceManagerUrl
     */
    public void setDeviceManagerUrl(String deviceManagerUrl) {
        this.deviceManagerUrl = deviceManagerUrl;
    }

    /**
     * 
     * @return The deviceManagerVersion
     */
    public String getDeviceManagerVersion() {
        return deviceManagerVersion;
    }

    /**
     * 
     * @param deviceManagerVersion
     *            The deviceManagerVersion
     */
    public void setDeviceManagerVersion(String deviceManagerVersion) {
        this.deviceManagerVersion = deviceManagerVersion;
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
     * @return The managedSansUri
     */
    public String getManagedSansUri() {
        return managedSansUri;
    }

    /**
     * 
     * @param managedSansUri
     *            The managedSansUri
     */
    public void setManagedSansUri(String managedSansUri) {
        this.managedSansUri = managedSansUri;
    }

    /**
     * 
     * @return The providerDisplayName
     */
    public String getProviderDisplayName() {
        return providerDisplayName;
    }

    /**
     * 
     * @param providerDisplayName
     *            The providerDisplayName
     */
    public void setProviderDisplayName(String providerDisplayName) {
        this.providerDisplayName = providerDisplayName;
    }

    /**
     * 
     * @return The providerUri
     */
    public String getProviderUri() {
        return providerUri;
    }

    /**
     * 
     * @param providerUri
     *            The providerUri
     */
    public void setProviderUri(String providerUri) {
        this.providerUri = providerUri;
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
     * @return The unimportedSansUri
     */
    public String getUnimportedSansUri() {
        return unimportedSansUri;
    }

    /**
     * 
     * @param unimportedSansUri
     *            The unimportedSansUri
     */
    public void setUnimportedSansUri(String unimportedSansUri) {
        this.unimportedSansUri = unimportedSansUri;
    }

}
