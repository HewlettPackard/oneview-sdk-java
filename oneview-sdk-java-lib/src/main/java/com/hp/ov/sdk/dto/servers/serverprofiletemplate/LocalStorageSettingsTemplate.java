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
package com.hp.ov.sdk.dto.servers.serverprofiletemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;

public class LocalStorageSettingsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<LocalStorageControllerTemplate> controllers = new ArrayList<>();
    @Since(300)
    private List<LogicalJbodTemplate> sasLogicalJBODs = new ArrayList<>();

    /**
     * @return the controllers
     */
    public List<LocalStorageControllerTemplate> getControllers() {
        return controllers;
    }

    /**
     * @param controllers the controllers to set
     */
    public void setControllers(List<LocalStorageControllerTemplate> controllers) {
        this.controllers = controllers;
    }

    /**
     * @return the sasLogicalJBODs
     */
    public List<LogicalJbodTemplate> getSasLogicalJBODs() {
        return sasLogicalJBODs;
    }

    /**
     * @param sasLogicalJBODs the sasLogicalJBODs to set
     */
    public void setSasLogicalJBODs(List<LogicalJbodTemplate> sasLogicalJBODs) {
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
