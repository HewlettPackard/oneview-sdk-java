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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Oa implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    /**
     *
     * (Required)
     *
     */
    private Integer bayNumber;
    private String fwVersion;
    private Oa.Role role;
    private String ipAddress;
    private List<Ipv6Address> ipv6Addresses = new ArrayList<Ipv6Address>();
    private String fqdnHostName;
    private String fwBuildDate;
    private Boolean dhcpEnable;
    private Boolean dhcpIpv6Enable;
    private Oa.State state;

    /**
     *
     * (Required)
     *
     * @return The bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     *
     * (Required)
     *
     * @param bayNumber
     *            The bayNumber
     */
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     *
     * @return The fwVersion
     */
    public String getFwVersion() {
        return fwVersion;
    }

    /**
     *
     * @param fwVersion
     *            The fwVersion
     */
    public void setFwVersion(final String fwVersion) {
        this.fwVersion = fwVersion;
    }

    /**
     *
     * @return The role
     */
    public Oa.Role getRole() {
        return role;
    }

    /**
     *
     * @param role
     *            The role
     */
    public void setRole(final Oa.Role role) {
        this.role = role;
    }

    /**
     *
     * @return The ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     *
     * @param ipAddress
     *            The ipAddress
     */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     *
     * @return The ipv6Addresses
     */
    public List<Ipv6Address> getIpv6Addresses() {
        return ipv6Addresses;
    }

    /**
     *
     * @param ipv6Addresses
     *            The ipv6Addresses
     */
    public void setIpv6Addresses(final List<Ipv6Address> ipv6Addresses) {
        this.ipv6Addresses = ipv6Addresses;
    }

    /**
     *
     * @return The fqdnHostName
     */
    public String getFqdnHostName() {
        return fqdnHostName;
    }

    /**
     *
     * @param fqdnHostName
     *            The fqdnHostName
     */
    public void setFqdnHostName(final String fqdnHostName) {
        this.fqdnHostName = fqdnHostName;
    }

    /**
     *
     * @return The fwBuildDate
     */
    public String getFwBuildDate() {
        return fwBuildDate;
    }

    /**
     *
     * @param fwBuildDate
     *            The fwBuildDate
     */
    public void setFwBuildDate(final String fwBuildDate) {
        this.fwBuildDate = fwBuildDate;
    }

    /**
     *
     * @return The dhcpEnable
     */
    public Boolean getDhcpEnable() {
        return dhcpEnable;
    }

    /**
     *
     * @param dhcpEnable
     *            The dhcpEnable
     */
    public void setDhcpEnable(final Boolean dhcpEnable) {
        this.dhcpEnable = dhcpEnable;
    }

    /**
     *
     * @return The dhcpIpv6Enable
     */
    public Boolean getDhcpIpv6Enable() {
        return dhcpIpv6Enable;
    }

    /**
     *
     * @param dhcpIpv6Enable
     *            The dhcpIpv6Enable
     */
    public void setDhcpIpv6Enable(final Boolean dhcpIpv6Enable) {
        this.dhcpIpv6Enable = dhcpIpv6Enable;
    }

    /**
     *
     * @return The state
     */
    public Oa.State getState() {
        return state;
    }

    /**
     *
     * @param state
     *            The state
     */
    public void setState(final Oa.State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bayNumber).append(fwVersion).append(role).append(ipAddress).append(ipv6Addresses)
                .append(fqdnHostName).append(fwBuildDate).append(dhcpEnable).append(dhcpIpv6Enable).append(state).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Oa) == false) {
            return false;
        }
        final Oa rhs = ((Oa) other);
        return new EqualsBuilder().append(bayNumber, rhs.bayNumber).append(fwVersion, rhs.fwVersion).append(role, rhs.role)
                .append(ipAddress, rhs.ipAddress).append(ipv6Addresses, rhs.ipv6Addresses).append(fqdnHostName, rhs.fqdnHostName)
                .append(fwBuildDate, rhs.fwBuildDate).append(dhcpEnable, rhs.dhcpEnable).append(dhcpIpv6Enable, rhs.dhcpIpv6Enable)
                .append(state, rhs.state).isEquals();
    }

    public static enum Role {

        Unknown("Unknown"), OaAbsent("OaAbsent"), Standby("Standby"), Transition("Transition"), Active("Active");
        private final String value;
        private static Map<String, Oa.Role> constants = new HashMap<String, Oa.Role>();

        static {
            for (final Oa.Role c : values()) {
                constants.put(c.value, c);
            }
        }

        private Role(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Oa.Role fromValue(final String value) {
            final Oa.Role constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum State {

        Online("Online"), Offline("Offline");
        private final String value;
        private static Map<String, Oa.State> constants = new HashMap<String, Oa.State>();

        static {
            for (final Oa.State c : values()) {
                constants.put(c.value, c);
            }
        }

        private State(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Oa.State fromValue(final String value) {
            final Oa.State constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
