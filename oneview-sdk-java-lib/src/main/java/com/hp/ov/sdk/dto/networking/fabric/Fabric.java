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
package com.hp.ov.sdk.dto.networking.fabric;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class Fabric extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String domainUri;
    private VlanPool reservedVlanRange;

    /**
     * @return the domain URI
     */
    public String getDomainUri() {
        return domainUri;
    }

    /**
     * @param domainUri
     *  The domain URI
     */
    public void setDomainUri(String domainUri) {
        this.domainUri = domainUri;
    }

    public VlanPool getReservedVlanRange() {
        return reservedVlanRange;
    }

    public void setReservedVlanRange(VlanPool reservedVlanRange) {
        this.reservedVlanRange = reservedVlanRange;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Fabric);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Fabric) {
            Fabric fabric = (Fabric) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(domainUri, fabric.domainUri)
                    .append(reservedVlanRange, fabric.reservedVlanRange)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(domainUri)
                .append(reservedVlanRange)
                .toHashCode();
    }
}
