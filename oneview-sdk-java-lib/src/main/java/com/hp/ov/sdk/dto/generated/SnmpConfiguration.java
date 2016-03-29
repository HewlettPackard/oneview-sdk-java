/*
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
 */
package com.hp.ov.sdk.dto.generated;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class SnmpConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1756360245650389037L;

    private String readCommunity = "public";
    private String systemContact = "";
    private List<TrapDestination> trapDestinations = new ArrayList<>();
    private List<String> snmpAccess = new ArrayList<>();
    private Boolean enabled = false;

    public String getReadCommunity() {
        return readCommunity;
    }

    public void setReadCommunity(final String readCommunity) {
        this.readCommunity = readCommunity;
    }

    public String getSystemContact() {
        return systemContact;
    }

    public void setSystemContact(final String systemContact) {
        this.systemContact = systemContact;
    }

    public List<TrapDestination> getTrapDestinations() {
        return trapDestinations;
    }

    public void setTrapDestinations(final List<TrapDestination> trapDestinations) {
        this.trapDestinations = trapDestinations;
    }

    public List<String> getSnmpAccess() {
        return snmpAccess;
    }

    public void setSnmpAccess(final List<String> snmpAccess) {
        this.snmpAccess = snmpAccess;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SnmpConfiguration that = (SnmpConfiguration) obj;

        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(readCommunity, that.readCommunity)
                .append(systemContact, that.systemContact)
                .append(trapDestinations, that.trapDestinations)
                .append(snmpAccess, that.snmpAccess)
                .append(enabled, that.enabled)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(readCommunity)
                .append(systemContact)
                .append(trapDestinations)
                .append(snmpAccess)
                .append(enabled)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("readCommunity", readCommunity)
                .append("systemContact", systemContact)
                .append("trapDestinations", trapDestinations)
                .append("snmpAccess", snmpAccess)
                .append("enabled", enabled)
                .toString();
    }
}
