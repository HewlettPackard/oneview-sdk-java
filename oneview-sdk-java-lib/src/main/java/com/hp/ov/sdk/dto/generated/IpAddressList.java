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
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class IpAddressList implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    private String ipAddress;
    /**
     * 
     * (Required)
     * 
     */
    private IpAddressList.IpAddressType ipAddressType;

    /**
     * 
     * (Required)
     * 
     * @return The ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 
     * (Required)
     * 
     * @param ipAddress
     *            The ipAddress
     */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 
     * (Required)
     * 
     * @return The ipAddressType
     */
    public IpAddressList.IpAddressType getIpAddressType() {
        return ipAddressType;
    }

    /**
     * 
     * (Required)
     * 
     * @param ipAddressType
     *            The ipAddressType
     */
    public void setIpAddressType(final IpAddressList.IpAddressType ipAddressType) {
        this.ipAddressType = ipAddressType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ipAddress).append(ipAddressType).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof IpAddressList) == false) {
            return false;
        }
        final IpAddressList rhs = ((IpAddressList) other);
        return new EqualsBuilder().append(ipAddress, rhs.ipAddress).append(ipAddressType, rhs.ipAddressType).isEquals();
    }

    public static enum IpAddressType {

        Unknown("Unknown"), Ipv4("Ipv4"), Ipv6Static("Ipv6Static"), Ipv6Dhcp("Ipv6Dhcp"), Ipv6Slaac("Ipv6Slaac"), Ipv6LinkLocal(
                "Ipv6LinkLocal");
        private final String value;
        private static Map<String, IpAddressList.IpAddressType> constants = new HashMap<String, IpAddressList.IpAddressType>();

        static {
            for (final IpAddressList.IpAddressType c : values()) {
                constants.put(c.value, c);
            }
        }

        private IpAddressType(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static IpAddressList.IpAddressType fromValue(final String value) {
            final IpAddressList.IpAddressType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
