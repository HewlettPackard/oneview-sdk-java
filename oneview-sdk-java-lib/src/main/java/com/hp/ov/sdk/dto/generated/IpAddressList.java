/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "ipAddress", "ipAddressType" })
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
    @JsonProperty("ipAddress")
    private String ipAddress;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("ipAddressType")
    private IpAddressList.IpAddressType ipAddressType;

    /**
     * 
     * (Required)
     * 
     * @return The ipAddress
     */
    @JsonProperty("ipAddress")
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
    @JsonProperty("ipAddress")
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 
     * (Required)
     * 
     * @return The ipAddressType
     */
    @JsonProperty("ipAddressType")
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
    @JsonProperty("ipAddressType")
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

    @Generated("org.jsonschema2pojo")
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

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
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
