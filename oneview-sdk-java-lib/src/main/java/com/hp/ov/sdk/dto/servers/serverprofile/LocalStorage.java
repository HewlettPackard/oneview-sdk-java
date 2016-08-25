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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

public class LocalStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Since(200)
    private List<LocalStorageController> controllers = new ArrayList<>();
    @Until(199)
    private Boolean initialize;
    @Until(199)
    private List<LogicalDrive> logicalDrives = new ArrayList<>();
    @Until(199)
    private Boolean manageLocalStorage;
    @Since(300)
    private List<LogicalJbod> sasLogicalJBODs = new ArrayList<>();

    /**
     * @return the controllers
     */
    public List<LocalStorageController> getControllers() {
        return controllers;
    }

    /**
     * @param controllers the controllers to set
     */
    public void setControllers(List<LocalStorageController> controllers) {
        this.controllers = controllers;
    }

    /**
     * @return the initialize
     */
    public Boolean getInitialize() {
        return initialize;
    }

    /**
     * @param initialize the initialize to set
     */
    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }

    /**
     * @return the logicalDrives
     */
    public List<LogicalDrive> getLogicalDrives() {
        return logicalDrives;
    }

    /**
     * @param logicalDrives the logicalDrives to set
     */
    public void setLogicalDrives(List<LogicalDrive> logicalDrives) {
        this.logicalDrives = logicalDrives;
    }

    /**
     * @return the manageLocalStorage
     */
    public Boolean getManageLocalStorage() {
        return manageLocalStorage;
    }

    /**
     * @param manageLocalStorage the manageLocalStorage to set
     */
    public void setManageLocalStorage(Boolean manageLocalStorage) {
        this.manageLocalStorage = manageLocalStorage;
    }

    /**
     * @return the sasLogicalJBODs
     */
    public List<LogicalJbod> getSasLogicalJBODs() {
        return sasLogicalJBODs;
    }

    /**
     * @param sasLogicalJBODs the sasLogicalJBODs to set
     */
    public void setSasLogicalJBODs(List<LogicalJbod> sasLogicalJBODs) {
        this.sasLogicalJBODs = sasLogicalJBODs;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
