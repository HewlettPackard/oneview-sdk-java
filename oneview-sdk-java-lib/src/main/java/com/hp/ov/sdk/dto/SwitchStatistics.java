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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SwitchStatistics implements Serializable {

    private static final long serialVersionUID = -7295293092877719173L;

    private List<SwitchPortStatistics> genericPortStatistics = new ArrayList<>();
    private ModuleStatistics moduleStatistics;
    private String switchId;
    private String type;

    public List<SwitchPortStatistics> getGenericPortStatistics() {
        return genericPortStatistics;
    }

    public void setGenericPortStatistics(List<SwitchPortStatistics> genericPortStatistics) {
        this.genericPortStatistics = genericPortStatistics;
    }

    public ModuleStatistics getModuleStatistics() {
        return moduleStatistics;
    }

    public void setModuleStatistics(ModuleStatistics moduleStatistics) {
        this.moduleStatistics = moduleStatistics;
    }

    public String getSwitchId() {
        return switchId;
    }

    public void setSwitchId(String switchId) {
        this.switchId = switchId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SwitchStatistics that = (SwitchStatistics) obj;

        return new EqualsBuilder()
                .append(genericPortStatistics, that.genericPortStatistics)
                .append(moduleStatistics, that.moduleStatistics)
                .append(switchId, that.switchId)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(genericPortStatistics)
                .append(moduleStatistics)
                .append(switchId)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("genericPortStatistics", genericPortStatistics)
                .append("moduleStatistics", moduleStatistics)
                .append("switchId", switchId)
                .append("type", type)
                .toString();
    }
}
