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
package com.hp.ov.sdk.dto.networking.ethernet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;


public class ConnectionTemplate extends BaseModelResource {

    private static final long serialVersionUID = 9033278171943320889L;

    private Bandwidth bandwidth;

    public Bandwidth getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(final Bandwidth bandwidth) {
        this.bandwidth = bandwidth;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof ConnectionTemplate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof ConnectionTemplate) {
            ConnectionTemplate that = (ConnectionTemplate) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(bandwidth, that.bandwidth)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(bandwidth)
                .toHashCode();
    }

}
