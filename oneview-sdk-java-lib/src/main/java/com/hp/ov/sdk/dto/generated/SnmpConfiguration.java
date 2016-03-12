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
package com.hp.ov.sdk.dto.generated;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hp.ov.sdk.dto.BaseModelResource;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "readCommunity",
    "systemContact",
    "trapDestinations",
    "snmpAccess",
    "enabled"})
public class SnmpConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    @JsonProperty("readCommunity")
    private String readCommunity = "public";
    @JsonProperty("systemContact")
    private String systemContact = "";
    @JsonProperty("trapDestinations")
    private List<TrapDestination> trapDestinations = new ArrayList<TrapDestination>();
    @JsonProperty("snmpAccess")
    private List<String> snmpAccess = new ArrayList<String>();
    @JsonProperty("enabled")
    private Boolean enabled = false;

    /**
     *
     * @return The readCommunity
     */
    @JsonProperty("readCommunity")
    public String getReadCommunity() {
        return readCommunity;
    }

    /**
     *
     * @param readCommunity
     *            The readCommunity
     */
    @JsonProperty("readCommunity")
    public void setReadCommunity(final String readCommunity) {
        this.readCommunity = readCommunity;
    }

    /**
     *
     * @return The systemContact
     */
    @JsonProperty("systemContact")
    public String getSystemContact() {
        return systemContact;
    }

    /**
     *
     * @param systemContact
     *            The systemContact
     */
    @JsonProperty("systemContact")
    public void setSystemContact(final String systemContact) {
        this.systemContact = systemContact;
    }

    /**
     *
     * @return The trapDestinations
     */
    @JsonProperty("trapDestinations")
    public List<TrapDestination> getTrapDestinations() {
        return trapDestinations;
    }

    /**
     *
     * @param trapDestinations
     *            The trapDestinations
     */
    @JsonProperty("trapDestinations")
    public void setTrapDestinations(final List<TrapDestination> trapDestinations) {
        this.trapDestinations = trapDestinations;
    }

    /**
     *
     * @return The snmpAccess
     */
    @JsonProperty("snmpAccess")
    public List<String> getSnmpAccess() {
        return snmpAccess;
    }

    /**
     *
     * @param snmpAccess
     *            The snmpAccess
     */
    @JsonProperty("snmpAccess")
    public void setSnmpAccess(final List<String> snmpAccess) {
        this.snmpAccess = snmpAccess;
    }

    /**
     *
     * @return The enabled
     */
    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     *            The enabled
     */
    @JsonProperty("enabled")
    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(readCommunity)
                .append(systemContact)
                .append(trapDestinations)
                .append(snmpAccess)
                .append(enabled)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SnmpConfiguration) == false) {
            return false;
        }
        final SnmpConfiguration rhs = ((SnmpConfiguration) other);
        return new EqualsBuilder()
                .append(readCommunity, rhs.readCommunity)
                .append(systemContact, rhs.systemContact)
                .append(trapDestinations, rhs.trapDestinations)
                .append(snmpAccess, rhs.snmpAccess)
                .append(enabled, rhs.enabled)
                .appendSuper(super.equals(other))
                .isEquals();
    }

}
