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
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The StorageSystemV2 data transfer object (DTO) contains the information used
 * to represent a storage system in the system. It is passed in to the
 * add/update storage system REST api, as well as the add/update storage system
 * through java client api.
 */

public class StorageSystemV2 extends BaseModelResource {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String allocatedCapacity;
    private StorageSystemCredentials credentials;
    private String firmware;
    private String freeCapacity;
    private String managedDomain;
    private List<StoragePool> managedPools = new ArrayList<StoragePool>();
    private List<StorageTargetPortV2> managedPorts = new ArrayList<StorageTargetPortV2>();
    private String model;
    private RefreshState refreshState;
    private String serialNumber;
    private String stateReason;
    private String totalCapacity;
    private List<String> unmanagedDomains = new ArrayList<String>();
    private List<StoragePool> unmanagedPools = new ArrayList<StoragePool>();
    private List<StorageTargetPort> unmanagedPorts = new ArrayList<StorageTargetPort>();

    /**
     * 
     * @return The allocatedCapacity
     */
    public String getAllocatedCapacity() {
        return allocatedCapacity;
    }

    /**
     * 
     * @param allocatedCapacity
     *            The allocatedCapacity
     */
    public void setAllocatedCapacity(final String allocatedCapacity) {
        this.allocatedCapacity = allocatedCapacity;
    }

    /**
     * 
     * @return The credentials
     */
    public StorageSystemCredentials getCredentials() {
        return credentials;
    }

    /**
     * 
     * @param credentials
     *            The credentials
     */
    public void setCredentials(final StorageSystemCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * 
     * @return The firmware
     */
    public String getFirmware() {
        return firmware;
    }

    /**
     * 
     * @param firmware
     *            The firmware
     */
    public void setFirmware(final String firmware) {
        this.firmware = firmware;
    }

    /**
     * 
     * @return The freeCapacity
     */
    public String getFreeCapacity() {
        return freeCapacity;
    }

    /**
     * 
     * @param freeCapacity
     *            The freeCapacity
     */
    public void setFreeCapacity(final String freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    /**
     * 
     * @return The managedDomain
     */
    public String getManagedDomain() {
        return managedDomain;
    }

    /**
     * 
     * @param managedDomain
     *            The managedDomain
     */
    public void setManagedDomain(final String managedDomain) {
        this.managedDomain = managedDomain;
    }

    /**
     * 
     * @return The managedPools
     */
    public List<StoragePool> getManagedPools() {
        return managedPools;
    }

    /**
     * 
     * @param managedPools
     *            The managedPools
     */
    public void setManagedPools(final List<StoragePool> managedPools) {
        this.managedPools = managedPools;
    }

    /**
     * 
     * @return The managedPorts
     */
    public List<StorageTargetPortV2> getManagedPorts() {
        return managedPorts;
    }

    /**
     * 
     * @param managedPorts
     *            The managedPorts
     */
    public void setManagedPorts(final List<StorageTargetPortV2> managedPorts) {
        this.managedPorts = managedPorts;
    }

    /**
     * 
     * @return The model
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *            The model
     */
    public void setModel(final String model) {
        this.model = model;
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
    public void setRefreshState(final RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * 
     * @return The serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 
     * @param serialNumber
     *            The serialNumber
     */
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 
     * @return The stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * 
     * @param stateReason
     *            The stateReason
     */
    public void setStateReason(final String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     * 
     * @return The totalCapacity
     */
    public String getTotalCapacity() {
        return totalCapacity;
    }

    /**
     * 
     * @param totalCapacity
     *            The totalCapacity
     */
    public void setTotalCapacity(final String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    /**
     * 
     * @return The unmanagedDomains
     */
    public List<String> getUnmanagedDomains() {
        return unmanagedDomains;
    }

    /**
     * 
     * @param unmanagedDomains
     *            The unmanagedDomains
     */
    public void setUnmanagedDomains(final List<String> unmanagedDomains) {
        this.unmanagedDomains = unmanagedDomains;
    }

    /**
     * 
     * @return The unmanagedPools
     */
    public List<StoragePool> getUnmanagedPools() {
        return unmanagedPools;
    }

    /**
     * 
     * @param unmanagedPools
     *            The unmanagedPools
     */
    public void setUnmanagedPools(final List<StoragePool> unmanagedPools) {
        this.unmanagedPools = unmanagedPools;
    }

    /**
     * 
     * @return The unmanagedPorts
     */
    public List<StorageTargetPort> getUnmanagedPorts() {
        return unmanagedPorts;
    }

    /**
     * 
     * @param unmanagedPorts
     *            The unmanagedPorts
     */
    public void setUnmanagedPorts(final List<StorageTargetPort> unmanagedPorts) {
        this.unmanagedPorts = unmanagedPorts;
    }

}
