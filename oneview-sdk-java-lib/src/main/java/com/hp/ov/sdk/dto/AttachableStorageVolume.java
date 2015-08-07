/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttachableStorageVolume implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private List<String> availableNetworks = new ArrayList<String>();
    private String provisionType;
    private String provisionedCapacity;
    private String provisioningType;
    private String raidLevel;
    private Boolean shareable;
    private String storagePoolName;
    private String storagePoolUri;
    private String storageSystemName;
    private String storageSystemUri;
    private String name;
    private String uri;

    /**
     * 
     * @return The availableNetworks
     */
    public List<String> getAvailableNetworks() {
        return availableNetworks;
    }

    /**
     * 
     * @param availableNetworks
     *            The availableNetworks
     */
    public void setAvailableNetworks(final List<String> availableNetworks) {
        this.availableNetworks = availableNetworks;
    }

    /**
     * 
     * @return The provisionType
     */
    public String getProvisionType() {
        return provisionType;
    }

    /**
     * 
     * @param provisionType
     *            The provisionType
     */
    public void setProvisionType(final String provisionType) {
        this.provisionType = provisionType;
    }

    /**
     * 
     * @return The provisionedCapacity
     */
    public String getProvisionedCapacity() {
        return provisionedCapacity;
    }

    /**
     * 
     * @param provisionedCapacity
     *            The provisionedCapacity
     */
    public void setProvisionedCapacity(final String provisionedCapacity) {
        this.provisionedCapacity = provisionedCapacity;
    }

    /**
     * 
     * @return The provisioningType
     */
    public String getProvisioningType() {
        return provisioningType;
    }

    /**
     * 
     * @param provisioningType
     *            The provisioningType
     */
    public void setProvisioningType(final String provisioningType) {
        this.provisioningType = provisioningType;
    }

    /**
     * 
     * @return The raidLevel
     */
    public String getRaidLevel() {
        return raidLevel;
    }

    /**
     * 
     * @param raidLevel
     *            The raidLevel
     */
    public void setRaidLevel(final String raidLevel) {
        this.raidLevel = raidLevel;
    }

    /**
     * 
     * @return The shareable
     */
    public Boolean getShareable() {
        return shareable;
    }

    /**
     * 
     * @param shareable
     *            The shareable
     */
    public void setShareable(final Boolean shareable) {
        this.shareable = shareable;
    }

    /**
     * 
     * @return The storagePoolName
     */
    public String getStoragePoolName() {
        return storagePoolName;
    }

    /**
     * 
     * @param storagePoolName
     *            The storagePoolName
     */
    public void setStoragePoolName(final String storagePoolName) {
        this.storagePoolName = storagePoolName;
    }

    /**
     * 
     * @return The storagePoolUri
     */
    public String getStoragePoolUri() {
        return storagePoolUri;
    }

    /**
     * 
     * @param storagePoolUri
     *            The storagePoolUri
     */
    public void setStoragePoolUri(final String storagePoolUri) {
        this.storagePoolUri = storagePoolUri;
    }

    /**
     * 
     * @return The storageSystemName
     */
    public String getStorageSystemName() {
        return storageSystemName;
    }

    /**
     * 
     * @param storageSystemName
     *            The storageSystemName
     */
    public void setStorageSystemName(final String storageSystemName) {
        this.storageSystemName = storageSystemName;
    }

    /**
     * 
     * @return The storageSystemUri
     */
    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    /**
     * 
     * @param storageSystemUri
     *            The storageSystemUri
     */
    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

    /**
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *            The uri
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

}
