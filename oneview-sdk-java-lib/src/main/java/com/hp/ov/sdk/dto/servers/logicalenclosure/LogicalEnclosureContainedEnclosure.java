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
package com.hp.ov.sdk.dto.servers.logicalenclosure;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogicalEnclosureContainedEnclosure {

    private String enclosureUri;
    private Set<LogicalEnclosureInterconnectBay> interconnectBays = new HashSet<>();

    public String getEnclosureUri() {
        return enclosureUri;
    }

    public void setEnclosureUri(final String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    public Set<LogicalEnclosureInterconnectBay> getInterconnectBays() {
        return interconnectBays;
    }

    public void setInterconnectBays(final Set<LogicalEnclosureInterconnectBay> interconnectBays) {
        this.interconnectBays = interconnectBays;
    }

    public void addInterconnectBay(final LogicalEnclosureInterconnectBay bay) {
        if (interconnectBays == null) {
            interconnectBays = new HashSet<>();
        }
        if (bay != null) {
            interconnectBays.add(bay);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof LogicalEnclosureContainedEnclosure) {
            LogicalEnclosureContainedEnclosure that = (LogicalEnclosureContainedEnclosure) obj;

            return new EqualsBuilder()
                    .append(enclosureUri, that.enclosureUri)
                    .append(interconnectBays, that.interconnectBays)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(enclosureUri)
                .append(interconnectBays)
                .toHashCode();
    }
}
