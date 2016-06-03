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
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BootSettingsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean manageBoot;
    private List<String> order;

    /**
     * @return the manageBoot
     */
    public Boolean getManageBoot() {
        return manageBoot;
    }

    /**
     * @param manageBoot the manageBoot to set
     */
    public void setManageBoot(Boolean manageBoot) {
        this.manageBoot = manageBoot;
    }

    /**
     * @return the order
     */
    public List<String> getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(List<String> order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        BootSettingsTemplate that = (BootSettingsTemplate) obj;

        return new EqualsBuilder()
                .append(manageBoot, that.manageBoot)
                .append(order, that.order)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(manageBoot)
                .append(order)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("manageBoot", manageBoot)
                .append("order", order)
                .toString();
    }

}
